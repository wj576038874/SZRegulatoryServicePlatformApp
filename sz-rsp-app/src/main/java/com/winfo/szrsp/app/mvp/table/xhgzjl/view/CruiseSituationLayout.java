package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.xhgzjl.presenter.CruiseWorkRecordPresenter;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.mvp.task.view.Level2Item;
import com.winfo.szrsp.app.mvp.task.view.Level3Item;
import com.winfo.szrsp.app.mvp.task.view.Level4Item;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 巡航异常情况
 * Created by HoBo on 2018/4/11.
 */

public class CruiseSituationLayout extends LinearLayout implements View.OnClickListener, ICruiseWorkRecordActivity {
    @BindView(R.id.etContext)
    TextView etContext;
    @BindView(R.id.etDecision)
    TextView etDecision;
    private Context mContext;
    private ProcessingDecisionDialog dialog;
    private CruiseWorkRecordPresenter presenter;
    //    private int[] selectPosition=new int[]{10086,10086,10086,10086};
    private String decision;
    private String decisionCode;

    public CruiseSituationLayout(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_xhgzjl_ycqk, this);
        ButterKnife.bind(this);
        initView();
        initEvent();
        dialogLoading = DialogUtils.createLoadingDialog(mContext, "请稍后...");
    }

    private void initView() {
        presenter = new CruiseWorkRecordPresenter();
        presenter.attachMvpView(this);
    }

    private void initEvent() {
        etDecision.setOnClickListener(this);
        etContext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etDecision:
                dialog = new ProcessingDecisionDialog(mContext);
                dialog.show(etDecision.getText().toString().trim());
                dialog.setSetStrData(new ProcessingDecisionDialog.SetStrData() {
                    @Override
                    public void setNum(String s) {
                        if (s.equals("01")) {
                            etDecision.setText("警示教育");
                            decision = etDecision.getText().toString().trim();
                            decisionCode = "01";
                        } else if (s.equals("02")) {
                            etDecision.setText("立案调查");
                            decision = etDecision.getText().toString().trim();
                            decisionCode = "02";
                        } else if (s.equals("03")) {
                            etDecision.setText("通报有关部门");
                            decision = etDecision.getText().toString().trim();
                            decisionCode = "03";
                        } else {
                            etDecision.setText(s);
                            decision = etDecision.getText().toString().trim();
                            decisionCode = "04";
                        }
                    }
                });
                break;
            case R.id.etContext:
                CruiseWorkRecordActivity cruiseWorkRecordActivity = (CruiseWorkRecordActivity) mContext;
                List<TaskInspectionItemData> inspectionItemData = cruiseWorkRecordActivity.getInspectionData();
                if (inspectionItemData == null) {
                    presenter.getTaskInspectionAll(dialogLoading, true);
                    return;
                }
                List<MultiItemEntity> datas = new ArrayList<>();

                for (int i = 0; i < inspectionItemData.size(); i++) {
                    Level0Item level0Item = new Level0Item();
                    level0Item.setItemFuseName(inspectionItemData.get(i).getItemFuseName());
                    level0Item.setFscCode(inspectionItemData.get(i).getFscCode());
                    level0Item.setIsMustCheck(inspectionItemData.get(i).getIsMustCheck());
                    level0Item.setItemFuseDetails(inspectionItemData.get(i).getItemFuseDetails());
                    level0Item.setItemFuseFaterId(inspectionItemData.get(i).getItemFuseFaterId());
                    level0Item.setRemarks(inspectionItemData.get(i).getRemarks());
                    level0Item.setItemFuseId(inspectionItemData.get(i).getItemFuseId());
                    if (inspectionItemData.get(i).getItemFuseName().equals(selectFuseName)) {
                        level0Item.setChecked(true);
//                        selectPosition=i;
                    } else {
                        level0Item.setChecked(false);
                    }
//                    level0Item.setChecked(taskInspectionItemDataList.get(i).isChecked());
                    level0Item.setCanClick(inspectionItemData.get(i).isCanClick());
                    for (int j = 0; j < inspectionItemData.get(i).getChild().size(); j++) {
                        if (inspectionItemData.get(i).getChild().size() > 0 && inspectionItemData.get(i).getChild() != null) {
                            Level1Item level1Item = new Level1Item();

                            level1Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getItemFuseName());
                            level1Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getFscCode());
                            level1Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getIsMustCheck());
                            level1Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getItemFuseDetails());
                            level1Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getItemFuseFaterId());
                            level1Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getRemarks());
                            level1Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getItemFuseId());
                            if (inspectionItemData.get(i).getChild().get(j).getItemFuseName().equals(selectFuseName)) {
                                level1Item.setChecked(true);
//                                level0Item.setExpanded(true);
//                                selectPosition=i;
//                                selectPosition[0]=i;
                            } else {
                                level1Item.setChecked(false);
                            }
//                            level1Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).isChecked());
                            level1Item.setCanClick(inspectionItemData.get(i).getChild().get(j).isCanClick());
                            for (int k = 0; k < inspectionItemData.get(i).getChild().get(j).getChild().size(); k++) {
                                Level2Item level2Item = new Level2Item();
                                level2Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseName());

                                level2Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getFscCode());
                                level2Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getIsMustCheck());
                                level2Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseDetails());
                                level2Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseFaterId());
                                level2Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getRemarks());
                                level2Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseId());

                                if (inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseName().equals(selectFuseName)) {
                                    level2Item.setChecked(true);
//                                    level0Item.setExpanded(true);
//                                    level1Item.setExpanded(true);
//                                    selectPosition=i;
//                                    selectPosition[0]=i;
//                                    selectPosition[1]=j;

                                } else {
                                    level2Item.setChecked(false);
                                }

                                //level2Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).isChecked());
                                level2Item.setCanClick(inspectionItemData.get(i).getChild().get(j).getChild().get(k).isCanClick());
                                level1Item.addSubItem(level2Item);
                                for (int l = 0; l < inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().size(); l++) {
                                    Level3Item level3Item = new Level3Item();
                                    level3Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseName());

                                    level3Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getFscCode());
                                    level3Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getIsMustCheck());
                                    level3Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseDetails());
                                    level3Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseFaterId());
                                    level3Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getRemarks());
                                    level3Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseId());

                                    if (inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseName().equals(selectFuseName)) {
                                        level3Item.setChecked(true);
//                                        level0Item.setExpanded(true);
//                                        level1Item.setExpanded(true);
//                                        level2Item.setExpanded(true);
//                                        selectPosition=i;
//                                        selectPosition[0]=i;
//                                        selectPosition[1]=j;
//                                        selectPosition[2]=k;
                                    } else {
                                        level3Item.setChecked(false);
                                    }
//                                    level3Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).isChecked());
                                    level3Item.setCanClick(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).isCanClick());
                                    level2Item.addSubItem(level3Item);
                                    for (int m = 0; m < inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().size(); m++) {
                                        Level4Item level4Item = new Level4Item();
                                        level4Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseName());

                                        level4Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getFscCode());
                                        level4Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getIsMustCheck());
                                        level4Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseDetails());
                                        level4Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseFaterId());
                                        level4Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getRemarks());
                                        level4Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseId());
                                        if (inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseName().equals(selectFuseName)) {
                                            level4Item.setChecked(true);
//                                            level0Item.setExpanded(true);
//                                            level1Item.setExpanded(true);
//                                            level2Item.setExpanded(true);
//                                            level3Item.setExpanded(true);
//                                            selectPosition=i;
//                                            selectPosition[0]=i;
//                                            selectPosition[1]=j;
//                                            selectPosition[2]=k;
//                                            selectPosition[3]=l;
                                        } else {
                                            level4Item.setChecked(false);
                                        }
//                                        level4Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).isChecked());
                                        level4Item.setCanClick(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).isCanClick());
                                        level3Item.addSubItem(level4Item);
                                    }
                                }
                            }
                            level0Item.addSubItem(level1Item);
                        }
                    }
                    datas.add(level0Item);
                }
                InspectionAllDialog inspectionAllDialog = new InspectionAllDialog(mContext, datas);
                inspectionAllDialog.setOnData(new InspectionAllDialog.OnGetData() {
                    @Override
                    public void onDataCallBack(Map<String, Object> map) {
                        etContext.setText(map.get("name").toString());
                        selectFuseName = map.get("name").toString();
                        selectFuseId = map.get("id").toString();
                    }
                });
                inspectionAllDialog.showDialog();

                inspectionAllDialog.setOnSearchShipData(new InspectionAllDialog.OnSearchShipData() {
                    @Override
                    public void onSearchDataCallBack(String taskCode, String taskContent) {
                        etContext.setText(taskContent);
                        selectFuseName = taskContent;
                        selectFuseId = taskCode;
                    }
                });
                break;

        }
    }

    public CruiseWorkData.ctCruisingRiskDetail getTaskData() {
        CruiseWorkData.ctCruisingRiskDetail detail = new CruiseWorkData.ctCruisingRiskDetail();
        if ("".equals(etContext.getText().toString().trim())) {
            return null;
        } else {
            detail.setTheDecisionCode(decisionCode);
            detail.setTheDecision(decision);
            detail.setTaskTypeDetailCode(selectFuseId);
            detail.setTaskTypeDetail(selectFuseName);
            return detail;
        }
    }

    String selectFuseName = "", selectFuseId = "";
    Dialog dialogLoading;

    @Override
    public Dialog getDialog() {
        return dialogLoading;
    }

    @Override
    public void OnSuccess(String s, String resultData) {

    }

    @Override
    public void OnFaile(String msg) {

    }

    @Override
    public CruiseWorkData getData() {
        return null;
    }

    @Override
    public void setDetailData(String msg, CruiseWorkData data) {

    }

    @Override
    public void loginExpired(String msg) {

    }

    @Override
    public void setInspectionAll(List<TaskInspectionItemData> taskInspectionItemData) {
        List<TaskInspectionItemData> inspectionItemData = bulid(taskInspectionItemData);
        List<MultiItemEntity> datas = new ArrayList<>();

        for (int i = 0; i < inspectionItemData.size(); i++) {
            Level0Item level0Item = new Level0Item();
            level0Item.setItemFuseName(inspectionItemData.get(i).getItemFuseName());
            level0Item.setFscCode(inspectionItemData.get(i).getFscCode());
            level0Item.setIsMustCheck(inspectionItemData.get(i).getIsMustCheck());
            level0Item.setItemFuseDetails(inspectionItemData.get(i).getItemFuseDetails());
            level0Item.setItemFuseFaterId(inspectionItemData.get(i).getItemFuseFaterId());
            level0Item.setRemarks(inspectionItemData.get(i).getRemarks());
            level0Item.setItemFuseId(inspectionItemData.get(i).getItemFuseId());
            if (inspectionItemData.get(i).getItemFuseName().equals(selectFuseName)) {
                level0Item.setChecked(true);
//                selectPosition=i;
            } else {
                level0Item.setChecked(false);
            }
//                    level0Item.setChecked(taskInspectionItemDataList.get(i).isChecked());
            level0Item.setCanClick(inspectionItemData.get(i).isCanClick());
            for (int j = 0; j < inspectionItemData.get(i).getChild().size(); j++) {
                if (inspectionItemData.get(i).getChild().size() > 0 && inspectionItemData.get(i).getChild() != null) {
                    Level1Item level1Item = new Level1Item();

                    level1Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getItemFuseName());
                    level1Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getFscCode());
                    level1Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getIsMustCheck());
                    level1Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getItemFuseDetails());
                    level1Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getItemFuseFaterId());
                    level1Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getRemarks());
                    level1Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getItemFuseId());
                    if (inspectionItemData.get(i).getChild().get(j).getItemFuseName().equals(selectFuseName)) {
                        level1Item.setChecked(true);
//                        level0Item.setExpanded(true);
//                        selectPosition=i;
//                        selectPosition[0]=i;
                    } else {
                        level1Item.setChecked(false);
                    }
//                            level1Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).isChecked());
                    level1Item.setCanClick(inspectionItemData.get(i).getChild().get(j).isCanClick());
                    for (int k = 0; k < inspectionItemData.get(i).getChild().get(j).getChild().size(); k++) {
                        Level2Item level2Item = new Level2Item();
                        level2Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseName());

                        level2Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getFscCode());
                        level2Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getIsMustCheck());
                        level2Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseDetails());
                        level2Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseFaterId());
                        level2Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getRemarks());
                        level2Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseId());

                        if (inspectionItemData.get(i).getChild().get(j).getChild().get(k).getItemFuseName().equals(selectFuseName)) {
                            level2Item.setChecked(true);
//                            level0Item.setExpanded(true);
//                            level1Item.setExpanded(true);
//                            selectPosition=i;
//                            selectPosition[0]=i;
//                            selectPosition[1]=j;
                        } else {
                            level2Item.setChecked(false);
                        }

                        //level2Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).isChecked());
                        level2Item.setCanClick(inspectionItemData.get(i).getChild().get(j).getChild().get(k).isCanClick());
                        level1Item.addSubItem(level2Item);
                        for (int l = 0; l < inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().size(); l++) {
                            Level3Item level3Item = new Level3Item();
                            level3Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseName());

                            level3Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getFscCode());
                            level3Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getIsMustCheck());
                            level3Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseDetails());
                            level3Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseFaterId());
                            level3Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getRemarks());
                            level3Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseId());

                            if (inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseName().equals(selectFuseName)) {
                                level3Item.setChecked(true);
//                                level0Item.setExpanded(true);
//                                level1Item.setExpanded(true);
//                                level2Item.setExpanded(true);
//                                selectPosition=i;
//                                selectPosition[0]=i;
//                                selectPosition[1]=j;
//                                selectPosition[2]=k;

                            } else {
                                level3Item.setChecked(false);
                            }
//                                    level3Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).isChecked());
                            level3Item.setCanClick(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).isCanClick());
                            level2Item.addSubItem(level3Item);
                            for (int m = 0; m < inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().size(); m++) {
                                Level4Item level4Item = new Level4Item();
                                level4Item.setItemFuseName(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseName());

                                level4Item.setFscCode(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getFscCode());
                                level4Item.setIsMustCheck(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getIsMustCheck());
                                level4Item.setItemFuseDetails(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseDetails());
                                level4Item.setItemFuseFaterId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseFaterId());
                                level4Item.setRemarks(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getRemarks());
                                level4Item.setItemFuseId(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseId());
                                if (inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseName().equals(selectFuseName)) {
                                    level4Item.setChecked(true);
//                                    level0Item.setExpanded(true);
//                                    level1Item.setExpanded(true);
//                                    level2Item.setExpanded(true);
//                                    level3Item.setExpanded(true);
//                                    selectPosition=i;
//                                    selectPosition[0]=i;
//                                    selectPosition[1]=j;
//                                    selectPosition[2]=k;
//                                    selectPosition[3]=l;
                                } else {
                                    level4Item.setChecked(false);
                                }
//                                        level4Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).isChecked());
                                level4Item.setCanClick(inspectionItemData.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).isCanClick());
                                level3Item.addSubItem(level4Item);
                            }
                        }
                    }
                    level0Item.addSubItem(level1Item);
                }
            }
            datas.add(level0Item);
        }
        InspectionAllDialog inspectionAllDialog = new InspectionAllDialog(mContext, datas);
        inspectionAllDialog.setOnData(new InspectionAllDialog.OnGetData() {
            @Override
            public void onDataCallBack(Map<String, Object> map) {
                etContext.setText(map.get("name").toString());
                selectFuseName = map.get("name").toString();
                selectFuseId = map.get("id").toString();
            }
        });
        inspectionAllDialog.showDialog();
    }

    //private int selectPosition=10086;
    @Override
    public void onLoadFaild(String msg) {

    }

    @Override
    public void setShip(List<CruiseShipData> data) {

    }

    @Override
    public void onFindShipFaile(String msg) {

    }

    @Override
    public void setInspectionByName(List<TaskInspectionItemByNameData> data) {

    }

    public List<TaskInspectionItemData> bulid(List<TaskInspectionItemData> taskInspectionItemDataList) {

        List<TaskInspectionItemData> taskInspectionItemDatas = new ArrayList<>();

        for (TaskInspectionItemData taskInspectionItemData : taskInspectionItemDataList) {

            if ("".equals(taskInspectionItemData.getItemFuseFaterId())) {
                taskInspectionItemDatas.add(taskInspectionItemData);
            }

            for (TaskInspectionItemData it : taskInspectionItemDataList) {
                if (it.getItemFuseFaterId().equals(taskInspectionItemData.getItemFuseId())) {
                    if (taskInspectionItemData.getChild() == null) {
                        taskInspectionItemData.setChild(new ArrayList<TaskInspectionItemData>());
                    }
                    taskInspectionItemData.getChild().add(it);
                }
            }
        }
        return taskInspectionItemDatas;
    }
}
