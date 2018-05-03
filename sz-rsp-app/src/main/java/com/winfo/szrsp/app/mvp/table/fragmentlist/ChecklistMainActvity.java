package com.winfo.szrsp.app.mvp.table.fragmentlist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.finishtask.view.FinishTaskActivity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.presenter.ChecklistPresenter;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.CBWFXCFragment;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.CruiseStatisticsFragment;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.ElectronicCruiseAbnormalFragment;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.ShipbulkcargoFragment;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.ShipflagFragment;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.ShipsiteFragment;
import com.winfo.szrsp.app.mvp.table.fragmentlist.view.WatersPatrolFragment;
import com.winfo.szrsp.app.mvp.task.view.LevelItem;
import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.WatersPatrol;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.task.TaskType;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.MySmartTabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.fragmentlist
 * @Filename: ChecklistMainActvity
 * @Author: lsj
 * @Date: 2017/12/19  10:56
 * @Description:
 * @Version:
 */
public class ChecklistMainActvity extends FragmentActivity implements IChecklistMainActvity,View.OnClickListener{
    private ImageButton table_titleBar_imgbtn_back;
    private TextView table_titleBar_titleText;
    private TextView table_titleBar_tx_refresh;
    private TaskInfo taskInfo;
    private List<TaskInfoDetails> taskInfoDetails;//船舶信息
    private List<LevelItem> levelItems = new ArrayList<>();
    private CtSpecialShipType0203 ctSpecialShipType0203;
    private CtCruiseStatisticsObject ctCruiseStatisticsObject;
    private CtElectronicCruiseException ctElectronicCruiseException;
    private com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData cbxcjdbgData;
    private CtSafeInspectNoticeObject ctSafeInspectNoticeObject;
    private WatersPatrol watersPatrol;
    private com.winfo.szrsp.app.sdk.entity.table.jdbgData jdbgData;
    private DisposalDecisionDialog disposalDecisionDialog;
    private String disposalDecision;
    private FragmentPagerItems pages;
    private ChecklistPresenter presenter;
    private List<TaskAssign> taskAssignList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        initView();
        initEvent();
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        table_titleBar_tx_refresh.setOnClickListener(this);
    }

    private void initView() {
        presenter = new ChecklistPresenter(this);
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        table_titleBar_tx_refresh=findViewById(R.id.table_titleBar_tx_refresh);
        table_titleBar_titleText.setText("执行任务表格");
        table_titleBar_tx_refresh.setVisibility(View.VISIBLE);
        table_titleBar_tx_refresh.setText("完成任务");

        //获取传递过来的数据
        levelItems = (List<LevelItem>) getIntent().getSerializableExtra("taskItem");
        taskInfo= (TaskInfo) getIntent().getSerializableExtra("taskInfo");
        taskInfoDetails = (List<TaskInfoDetails>) getIntent().getSerializableExtra("ShipInfo");
        taskAssignList = (List<TaskAssign>)getIntent().getSerializableExtra("taskAssignList");

        pages = new FragmentPagerItems(this);
        List<TaskType> taskTypeList=taskInfo.getTaskTypeList();
        int value = 0;
        for (TaskType taskType:taskTypeList){
            String TaskTypeId = taskType.getTaskTypeId();
            if (TaskTypeId.equals("RS_00000001")){
                value = value +1;
                pages.add(FragmentPagerItem.of("船舶现场监督报告", ShipsiteFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("taskAssignList", (Serializable) taskAssignList).putSerializable("taskInfo",taskInfo).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }else if (TaskTypeId.equals("RS_00000005")){
                value = value +1;
                pages.add(FragmentPagerItem.of("船舶危防现场监督检查记录表", CBWFXCFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }else if(TaskTypeId.equals("RS_00000002")){
                value = value +1;
                pages.add(FragmentPagerItem.of("船旗国监督检查报告", ShipflagFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("taskAssignList", (Serializable) taskAssignList).putSerializable("taskInfo",taskInfo).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }else if(TaskTypeId.equals("RO_00000001")){
                value = value +1;
                pages.add(FragmentPagerItem.of("水上巡航检查表", WatersPatrolFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }else if(TaskTypeId.equals("RS_00000009")){
                value = value +1;
                pages.add(FragmentPagerItem.of("大型散货船", ShipbulkcargoFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }else if(TaskTypeId.equals("RO_00000003")){
                value = value +1;
                pages.add(FragmentPagerItem.of("巡航工作统计", CruiseStatisticsFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }else if (TaskTypeId.equals("RO_00000002")){
                value = value +1;
                pages.add(FragmentPagerItem.of("电子巡航异常情况", ElectronicCruiseAbnormalFragment.class,new Bundler().putSerializable("taskInfoDetails", (Serializable) taskInfoDetails).putSerializable("level1Items",(Serializable)levelItems).putInt("value",value).get()));
            }
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(pages.size());
        MySmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public Dialog getDiaole() {
        Dialog dialog = DialogUtils.createLoadingDialog(ChecklistMainActvity.this,"加载中...");
        return dialog;
    }

    @Override
    public void setTaskDefectItemData(List<TaskDefectItemData> taskDefectItemData) {

    }

    @Override
    public void OnFaile(String msg) {
        ToastUtils.showToast(ChecklistMainActvity.this,msg);
        disposalDecisionDialog.dismiss();
    }

    @Override
    public void setUsersListSucceed(TaskPersonResouse taskPersonResouse) {

    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {

    }

    @Override
    public TaskFinishSubData getTaskFinishSubData() {
        TaskFinishSubData taskFinishSubData=new TaskFinishSubData();
        List<TaskType> taskTypeList=taskInfo.getTaskTypeList();
        for (TaskType taskType:taskTypeList){
            String taskTypeId =taskType.getTaskTypeId();
            if (taskTypeId.equals("RS_00000001")){
                if (cbxcjdbgData != null){
                    taskFinishSubData.settSiteSupervisionDetail(cbxcjdbgData.getDetail());
                    taskFinishSubData.settSiteSupervisionInfo(cbxcjdbgData.getInfo());
                }
            }else if (taskTypeId.equals("RS_00000002")){
                if (jdbgData != null){
                    taskFinishSubData.settFlagStateControlDetail(jdbgData.getDetail());
                    taskFinishSubData.settFlagStateControlInfo(jdbgData.getInfo());
                }
            }else if (taskTypeId.equals("RO_00000001")){
                if(watersPatrol != null){
                    taskFinishSubData.setCtWaterCruiseRecordInfo(watersPatrol.getInfo());
                    taskFinishSubData.setCtWaterCruiseReportDetail(watersPatrol.getDetailArea());
                    taskFinishSubData.setCtWaterCruiseShipInspectDetail(watersPatrol.getDetailShip());
                }
            }else if (taskTypeId.equals("RS_00000009")){
                if (ctSpecialShipType0203 != null){
                    taskFinishSubData.setCtSpecialShipType0203(ctSpecialShipType0203);
                }
            }else if (taskTypeId.equals("RO_00000003")){
                if (ctCruiseStatisticsObject != null){
                    taskFinishSubData.setCtCruiseStatisticsObject(ctCruiseStatisticsObject);
                }
            }else if (taskTypeId.equals("RO_00000002")){
                if (ctElectronicCruiseException!=null){
                    taskFinishSubData.setCtElectronicCruiseException(ctElectronicCruiseException);
                }
            }else if (taskTypeId.equals("RS_00000005")){
                if (ctSafeInspectNoticeObject!=null){
                    taskFinishSubData.setCtSafeInspectNoticeObject(ctSafeInspectNoticeObject);
                }
            }
        }
        taskFinishSubData.setTaskId(taskInfo.getTaskId());
        taskFinishSubData.setDisposalDecision(disposalDecision);
        return taskFinishSubData;
    }

    @Override
    public void finishSucceed(TaskFinishData data, String msg) {
        disposalDecisionDialog.dismiss();
        Intent intent=new Intent(this, FinishTaskActivity.class);
        intent.putExtra("data",data);
        String shipNameCn=taskInfoDetails.get(0).getShipNameCn();
        String shipNameEn=taskInfoDetails.get(0).getShipNameEn();
        if (null!=shipNameCn&&!"".equals(shipNameCn)){
            intent.putExtra("shipName",shipNameCn);
        }else if (null!=shipNameEn&&!"".equals(shipNameEn)){
            intent.putExtra("shipName",shipNameEn);
        }else {
            intent.putExtra("shipName","");
        }
        startActivity(intent);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);

    }

    @Override
    public void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.table_titleBar_tx_refresh:
                finishTask();
                break;
        }
    }

    private  String  siteCaptain_path="";
    private  String  fileCaptain_path="";
    private  String  fileInspector_path="";
    private void finishTask() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
           if (fragment instanceof ShipsiteFragment){
                ShipsiteFragment shipsiteFragment=(ShipsiteFragment)fragment;
                shipsiteFragment.getJdbgData(new ShipsiteFragment.GetJdbgDataCallBack() {
                    @Override
                    public void getResult(cbxcjdbgData data,String siteCaptain) {
                        cbxcjdbgData=data;
                        siteCaptain_path=siteCaptain;
                    }
                });
                if (cbxcjdbgData==null){
                    break;//当前表格未填写完整，跳出遍历
                }
            }else if (fragment instanceof ShipflagFragment){
               ShipflagFragment shipflagFragment = (ShipflagFragment) fragment;
               shipflagFragment.getSupervisionData(new ShipflagFragment.GetSupervisionDataCallback() {
                   @Override
                   public void getResult(jdbgData data,String fileCaptain,String fileInspector) {
                       if (data!=null&&data.getDetail() !=null){
                           jdbgData=data;
                           fileCaptain_path=fileCaptain;
                           fileInspector_path=fileInspector;

                       }else {
                           jdbgData=null;
                       }
                   }
               });
               if (jdbgData==null){
                   break;//当前表格未填写完整，跳出遍历
               }
           } else if (fragment instanceof ShipbulkcargoFragment){
                ShipbulkcargoFragment shipbulkcargoFragment = (ShipbulkcargoFragment) fragment;
                shipbulkcargoFragment.getDXSHCData(new ShipbulkcargoFragment.GetDXSHCDataCallBack() {
                    @Override
                    public void getResult(CtSpecialShipType0203 data) {
                        ctSpecialShipType0203=data;
                    }
                });
                if (ctSpecialShipType0203==null){
                    break;//当前表格未填写完整，跳出遍历
                }
            }else if (fragment instanceof WatersPatrolFragment){
               WatersPatrolFragment watersPatrolFragment=(WatersPatrolFragment)fragment;
               watersPatrolFragment.getWatersPatrolData(new WatersPatrolFragment.GetWatersPatrolDataCallback() {
                   @Override
                   public void getResult(WatersPatrol data) {
                       watersPatrol=data;
                   }
               });
               if (watersPatrol==null){
                   break;//当前表格未填写完整，跳出遍历
               }
           }else if (fragment instanceof CruiseStatisticsFragment){
                CruiseStatisticsFragment statisticsFragment = (CruiseStatisticsFragment) fragment;
                statisticsFragment.getXHTJData(new CruiseStatisticsFragment.GetCruiseStatisticsDataListener() {
                    @Override
                    public void GetCruiseStatisticsData(CtCruiseStatisticsObject data) {
                        ctCruiseStatisticsObject = data;
                    }
                });
                if (ctCruiseStatisticsObject==null){
                    break;//当前表格未填写完整，跳出遍历
                }
           }else if (fragment instanceof ElectronicCruiseAbnormalFragment){
               ElectronicCruiseAbnormalFragment electronicCruiseAbnormalFragment=(ElectronicCruiseAbnormalFragment)fragment;
               electronicCruiseAbnormalFragment.getCtElectronicCruiseException(new ElectronicCruiseAbnormalFragment.GetCtElectronicCruiseException() {
                   @Override
                   public void getResult(CtElectronicCruiseException data) {
                        ctElectronicCruiseException=data;
                   }
               });
           }else if(fragment instanceof CBWFXCFragment){
               CBWFXCFragment cbwfxcFragment=(CBWFXCFragment)fragment;
               cbwfxcFragment.getCBEFXCData(new CBWFXCFragment.GetSupervisionDataCallback() {
                   @Override
                   public void getResult(CtSafeInspectNoticeObject data) {
                       ctSafeInspectNoticeObject=data;
                   }
               });
               if (ctSafeInspectNoticeObject==null){
                   break;//当前表格未填写完整，跳出当前遍历
               }
           }
        }

        boolean bol = true;
        List<TaskType> taskTypeList=taskInfo.getTaskTypeList();
        for (int i = 0; i < taskTypeList.size(); i++) {
            if (taskTypeList.get(i).getTaskTypeId().equals("RS_00000001")){
                if (cbxcjdbgData == null){
                    bol = false;
                }
            }else if (taskTypeList.get(i).getTaskTypeId().equals("RS_00000002")){
                if (jdbgData == null){
                    bol = false;
                }
            }else if (taskTypeList.get(i).getTaskTypeId().equals("RO_00000001")){
                if (watersPatrol == null){
                    bol = false;
                }
            }else if (taskTypeList.get(i).getTaskTypeId().equals("RS_00000009")){
                if (ctSpecialShipType0203 == null){
                    bol = false;
                }
            }else if (taskTypeList.get(i).getTaskTypeId().equals("RO_00000003")){
                if (ctCruiseStatisticsObject == null){
                    bol = false;
                }
            }else if (taskTypeList.get(i).getTaskTypeId().equals("RO_00000002")){
                if (ctElectronicCruiseException==null){
                    bol=false;
                }
            }else if (taskTypeList.get(i).getTaskTypeId().equals("RS_00000005")){
                if (ctSafeInspectNoticeObject==null){
                    bol=false;
                }
            }
        }

        if (bol){
            if(disposalDecisionDialog==null){
                disposalDecisionDialog=new DisposalDecisionDialog(ChecklistMainActvity.this);
                disposalDecisionDialog.showDialog();
                disposalDecisionDialog.setOnData(new DisposalDecisionDialog.OnGetData() {
                    @Override
                    public void onDataCallBack(String disposalDecision) {
                        ChecklistMainActvity.this.disposalDecision=disposalDecision;
                        presenter.finishTask(true,siteCaptain_path,fileCaptain_path,fileInspector_path);
                    }
                });
            }else {
                disposalDecisionDialog.showDialog();
            }
        }
    }
}
