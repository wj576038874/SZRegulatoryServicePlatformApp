package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.TaskShipDataAdapter;
import com.winfo.szrsp.app.mvp.task.presenter.AssignTaskPresenter;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.mvp.task.view.Level2Item;
import com.winfo.szrsp.app.mvp.task.view.Level3Item;
import com.winfo.szrsp.app.mvp.task.view.Level4Item;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType2;
import com.winfo.szrsp.app.sdk.entity.task.ReleaseTaskModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskDeptData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskShipData;
import com.winfo.szrsp.app.sdk.entity.task.TaskType2;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.JsonUtil;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.ReSpinner;
import com.winfo.szrsp.app.widget.spinner.OnSpinnerConfirmClickListener;
import com.winfo.szrsp.app.widget.spinner.SpinnearModel;
import com.winfo.szrsp.app.widget.spinner.SpinnerViewMultiDialog;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.ExpandableTaskInspectionAdapter.TYPE_LEVEL_0;



public class AssignTaskActivity extends Activity implements IAssignTaskView,View.OnClickListener {
    @BindView(R.id.assign_cb_rcjc)
    CheckBox assign_cb_rcjc;
    @BindView(R.id.assign_cb_zxjc)
    CheckBox assign_cb_zxjc;
    @BindView(R.id.assign_cb_lsjc)
    CheckBox assign_cb_lsjc;

    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;
    @BindView(R.id.assign_cb_ship)
    CheckBox assign_cb_ship;
    @BindView(R.id.assign_cb_notship)
    CheckBox assign_cb_notship;
    @BindView(R.id.assign_et_shipname)
    EditText assign_et_shipname;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.assign_tv_task_item)
    TextView assign_tv_task_item;
    @BindView(R.id.assign_tv_task_item_num)
    TextView assign_tv_task_item_num;
//    List<MultiItemEntity> mDatas;
    List<TaskType2> mDatas;
    List<MultiItemEntity> mDatas_InspectionAll;
    @BindView(R.id.btn_search_ship)
    Button btn_search_ship;
    @BindView(R.id.spinner_jsbm)
    ReSpinner sp_jsbm;
    @BindView(R.id.spinner_jsbm_bj)
    ReSpinner sp_jsbm_bj;
    @BindView(R.id.tv_jsbm)
    TextView tv_jsbm;
    @BindView(R.id.spinner_person_1)
    SpinnerViewMultiDialog sp_person;
    @BindView(R.id.assign_tv_start_time)
    TextView tv_starttime;
    @BindView(R.id.assign_tv_end_time)
    TextView tv_endtime;
    @BindView(R.id.btn_releasetask)
    Button btn_releasetask;
    @BindView(R.id.assign_tv_inventory)
    TextView assign_tv_inventory;

    private AssignTaskPresenter assignTaskPresenter;
    private Dialog dialog ;
//    ExpandableItemTaskTypeAdapter adapter;
    SelectTaskTypeAdapter adapter;

//    List<AllTaskType> allTaskTypeListDataIsShip=new ArrayList<>();
//    List<AllTaskType> allTaskTypeListDataNotShip=new ArrayList<>();
    private int userOrgCode;
    private String userDeptCode;
    private int infoStatus;
    private String orgJson;
    private String deptJson;
    private List<TaskDeptData> taskOrgDataList = new ArrayList<>();
    private List<TaskDeptData> taskDeptDataList = new ArrayList<>();
    private List<String> list_jsbm;//接受部门
    private List<String> list_jsbm_id;//接受部门id
    private List<String> list_jsbm_bj;//接受分支局
    private List<String> list_jsbm_bj_id;//接受分支局id
    private ArrayList<SpinnearModel> spinnearModels;//接收人数据
    private boolean isFirst = true;
    private String DistributionDepartment;
    private String DistributionDepartment_id;
    private TimePickerView timePickerView;

    private TaskAssign taskAssign;
    private TaskInfo taskInfo;
    private TaskInfoDetails taskInfoDetails;
    private List<String> TaskTypeId=new ArrayList<>();
    private List<String> Uuid;
    private String jsbm_name = "";
    private String jsbm_id = "";
    private String jsbm_bj_name = "";
    private String jsbm_bj_id = "";
    private String start_time = "";
    private String end_time = "";
    private Ais aisData;
    private String taskType = "ShipType";

    List<TaskInspectionItemData> taskInspectionAll;
    boolean  isInspectionAllLoadAgain;

    List<MultiItemEntity> inspectionSelect;
    List<String> taskTypeIdList=new ArrayList<>();
    private String isShipType="1";//是否是船舶类型 1是  0否
    private String typeFather="1";//检查项目类型单选框 1日常检查  2专项检查  3临时检查

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assign);
        ButterKnife.bind(this);
        timePickerView = new TimePickerView(this,TimePickerView.Type.ALL);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        taskAssign = new TaskAssign();
        taskInfo = new TaskInfo();
        taskInfoDetails = new TaskInfoDetails();
        aisData = (Ais) getIntent().getSerializableExtra("aisdata");
        initData();
        initEvent();
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser&&taskInspectionAll==null){
//            assignTaskPresenter.getTaskInspectionAll(dialog,true);
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        if(taskInspectionAll==null){
            assignTaskPresenter.getTaskInspectionAll(dialog,true);
        }
        if(allTaskTypeListData==null){
            assignTaskPresenter.getTaskType(dialog,true);
        }
    }

    private void initData() {

        dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        assignTaskPresenter=new AssignTaskPresenter();
        assignTaskPresenter.attachMvpView(this);
        // TODO: 2018/3/7
        assignTaskPresenter.getTaskType(dialog,true);
        assignTaskPresenter.getTaskInspectionAll(dialog,true);

        userOrgCode = Integer.parseInt(ACache.get(this).getAsString("orgCode"));
        userDeptCode  = ACache.get(this).getAsString("deptCode");
        if(userOrgCode == 14){
            if(userDeptCode.equals("140007")){
                infoStatus = 1;
                orgJson = "[{'id': '1401', 'name': '大亚湾海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1402', 'name': '蛇口海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1403', 'name': '盐田海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1404', 'name': '宝安海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1405', 'name': '南山海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1406', 'name': '大铲海事局', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14010004', 'name': '指挥中心', 'pid': '1401', 'checked': ''}," +
                        "{'id': '14020005', 'name': '指挥中心', 'pid': '1402', 'checked': ''}," +
                        "{'id': '14030007', 'name': '指挥中心', 'pid': '1403', 'checked': ''}," +
                        "{'id': '14040004', 'name': '指挥中心', 'pid': '1404', 'checked': ''}," +
                        "{'id': '14050007', 'name': '指挥中心', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14060004', 'name': '指挥中心', 'pid': '1406', 'checked': ''}]";
            }else {
                orgJson = "[{'id': '14', 'name': '深圳海事局', 'pid': '00', 'checked': ''}," +
                        "{'id': '1401', 'name': '大亚湾海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1402', 'name': '蛇口海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1403', 'name': '盐田海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1404', 'name': '宝安海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1405', 'name': '南山海事局', 'pid': '14', 'checked': ''}," +
                        "{'id': '1406', 'name': '大铲海事局', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '140007', 'name': '指挥中心', 'pid': '14', 'checked': ''}," +
                        "{'id': '14010004', 'name': '指挥中心', 'pid': '1401', 'checked': ''}," +
                        "{'id': '14020005', 'name': '指挥中心', 'pid': '1402', 'checked': ''}," +
                        "{'id': '14030007', 'name': '指挥中心', 'pid': '1403', 'checked': ''}," +
                        "{'id': '14040004', 'name': '指挥中心', 'pid': '1404', 'checked': ''}," +
                        "{'id': '14050007', 'name': '指挥中心', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14060004', 'name': '指挥中心', 'pid': '1406', 'checked': ''}]";
            }
        }else if(userOrgCode == 1401) {
            if (userDeptCode.equals("14010004")) {
                infoStatus = 1;
                orgJson = "[{'id': '1401', 'name': '大亚湾海事局指挥中心', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14010005', 'name': '南澳海巡执法大队', 'pid': '1401', 'checked': ''}," +
                        "{'id': '14010008', 'name': '大鹏海巡执法大队', 'pid': '1401', 'checked': ''}]";
            } else {
                infoStatus = 3;
                orgJson = "[{'id': '1401', 'name': '大亚湾海事局', 'pid': '00', 'checked': ''}]";
                deptJson = "[{'id': '14010004', 'name': '指挥中心', 'pid': '1401', 'checked': ''}," +
                        "{'id': '14010005', 'name': '南澳海巡执法大队', 'pid': '1401', 'checked': ''}," +
                        "{'id': '14010008', 'name': '大鹏海巡执法大队', 'pid': '1401', 'checked': ''}]";
            }
        }else if (userOrgCode == 1402){
            if(userDeptCode.equals("14020005")){
                infoStatus = 1;
                orgJson = "[{'id': '1402', 'name': '蛇口海事局指挥中心', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14020041', 'name': '深圳湾海巡执法大队', 'pid': '1402', 'checked': ''}," +
                        "{'id': '14020006', 'name': '太子湾海巡执法大队', 'pid': '1402', 'checked': ''},]";
            }else {
                infoStatus = 3;
                orgJson = "[{'id': '1402', 'name': '蛇口海事局', 'pid': '00', 'checked': ''}]";
                deptJson = "[{'id': '14020005', 'name': '指挥中心', 'pid': '1402', 'checked': ''}," +
                        "{'id': '14020041', 'name': '深圳湾海巡执法大队', 'pid': '1402', 'checked': ''}," +
                        "{'id': '14020006', 'name': '太子湾海巡执法大队', 'pid': '1402', 'checked': ''}]";
            }
        }else if (userOrgCode == 1403){
            if(userDeptCode.equals("14030007")){
                infoStatus = 1;
                orgJson = "[{'id': '1403', 'name': '盐田海事局指挥中心', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14030006', 'name': '大鹏湾海巡执法大队', 'pid': '1403', 'checked': ''}," +
                        "{'id': '14030041', 'name': '梅沙海巡执法大队', 'pid': '1403', 'checked': ''}," +
                        "{'id': '14030005', 'name': '沙头角海巡执法大队', 'pid': '1403', 'checked': ''}]";
            }else {
                infoStatus = 3;
                orgJson = "[{'id': '1403', 'name': '盐田海事局', 'pid': '00', 'checked': ''}]";
                deptJson = "[{'id': '14030007', 'name': '指挥中心', 'pid': '1403', 'checked': ''}," +
                        "{'id': '14030041', 'name': '梅沙海巡执法大队', 'pid': '1403', 'checked': ''}," +
                        "{'id': '14030005', 'name': '沙头角海巡执法大队', 'pid': '1403', 'checked': ''}]";
            }
        }else if(userOrgCode == 1404){
            if(userDeptCode.equals("14040004")){
                infoStatus = 1;
                orgJson = "[{'id': '1404', 'name': '宝安海事局指挥中心', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14040005', 'name': '西堤海巡执法大队', 'pid': '1404', 'checked': ''}," +
                        "{'id': '14040041', 'name': '机场海巡执法大队', 'pid': '1404', 'checked': ''}]";
            }else {
                infoStatus = 3;
                orgJson = "[{'id': '1404', 'name': '宝安海事局', 'pid': '00', 'checked': ''}]";
                deptJson = "[{'id': '14040004', 'name': '指挥中心', 'pid': '1404', 'checked': ''}," +
                        "{'id': '14040005', 'name': '西堤海巡执法大队', 'pid': '1404', 'checked': ''}," +
                        "{'id': '14040041', 'name': '机场海巡执法大队', 'pid': '1404', 'checked': ''}]";
            }
        }else if (userOrgCode == 1405){
            if(userDeptCode.equals("14050007")){
                infoStatus = 1;
                orgJson = "[{'id': '1405', 'name': '南山海事局指挥中心', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14050005', 'name': '内伶仃海巡执法大队', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14050006', 'name': '赤湾海巡执法大队', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14050041', 'name': '妈湾海巡执法大队', 'pid': '1405', 'checked': ''}]";
            }else {
                infoStatus = 3;
                orgJson = "[{'id': '1405', 'name': '南山海事局', 'pid': '00', 'checked': ''}]";
                deptJson = "[{'id': '14050007', 'name': '指挥中心', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14050005', 'name': '内伶仃海巡执法大队', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14050006', 'name': '赤湾海巡执法大队', 'pid': '1405', 'checked': ''}," +
                        "{'id': '14050041', 'name': '妈湾海巡执法大队', 'pid': '1405', 'checked': ''}]";
            }
        }else if (userOrgCode == 1406){
            if(userDeptCode.equals("14060004")){
                infoStatus = 1;
                orgJson = "[{'id': '1406', 'name': '大铲海事局指挥中心', 'pid': '14', 'checked': ''}]";
                deptJson = "[{'id': '14060041', 'name': '大铲海巡执法大队', 'pid': '1406', 'checked': ''}," +
                        "{'id': '14060005', 'name': '前海湾海巡执法大队', 'pid': '1406', 'checked': ''}]";
            }else {
                infoStatus = 3;
                orgJson = "[{'id': '1406', 'name': '大铲海事局', 'pid': '00', 'checked': ''}]";
                deptJson = "[{'id': '14060004', 'name': '指挥中心', 'pid': '1406', 'checked': ''}," +
                        "{'id': '14060041', 'name': '大铲海巡执法大队', 'pid': '1406', 'checked': ''}," +
                        "{'id': '14060005', 'name': '前海湾海巡执法大队', 'pid': '1406', 'checked': ''}]";
            }
        }

        try {
            JSONArray jsonArray = new JSONArray(orgJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TaskDeptData taskDeptData = JsonUtil.jsonToBean(jsonObject.toString(), TaskDeptData.class);
                taskOrgDataList.add(taskDeptData);
            }

            JSONArray jsonArray1 = new JSONArray(deptJson);
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                TaskDeptData taskDeptData = JsonUtil.jsonToBean(jsonObject.toString(), TaskDeptData.class);
                taskDeptDataList.add(taskDeptData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        list_jsbm = new ArrayList<>();
        list_jsbm_id = new ArrayList<>();
        list_jsbm.add("--请选择部门名称--");
        for (int i = 0; i < taskOrgDataList.size(); i++) {
            list_jsbm.add(taskOrgDataList.get(i).getName());
            list_jsbm_id.add(taskOrgDataList.get(i).getId());
        }
        ArrayAdapter<String> sp_Adapter1 = new ArrayAdapter<>(this,R.layout.spinner_item_1,list_jsbm);
        sp_Adapter1.setDropDownViewResource(R.layout.dropdown_style_1);
        sp_jsbm.setAdapter(sp_Adapter1);
    }

    private void initEvent() {
        setOnlyOneCheck(assign_cb_ship,assign_cb_notship);
        setOnlyOneCheck(assign_cb_rcjc,assign_cb_zxjc,assign_cb_lsjc);
        assign_cb_ship.setChecked(true);
        assign_cb_rcjc.setChecked(true);
        if (aisData !=null){
            assign_et_shipname.setText(aisData.getCM());
            taskInfoDetails.setShipNameEn(aisData.getCM());
            taskInfoDetails.setMmsi(aisData.getID());
        }
        sp_jsbm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                list_jsbm_bj = new ArrayList<>();
                list_jsbm_bj_id = new ArrayList<>();
                if (!list_jsbm.get(position).equals("--请选择部门名称--")){
                    DistributionDepartment = list_jsbm.get(position);
                    jsbm_name = list_jsbm.get(position);
                    jsbm_id = list_jsbm_id.get(position-1);
                    for (int i = 0; i < taskDeptDataList.size(); i++) {
                        if(list_jsbm_id.get(position-1).equals(taskDeptDataList.get(i).getPid())){
                            list_jsbm_bj.add(taskDeptDataList.get(i).getName());
                            list_jsbm_bj_id.add(taskDeptDataList.get(i).getId());
                        }
                    }
                    list_jsbm_bj.add("--重新选择部门--");
                    ArrayAdapter<String> sp_Adapter2 = new ArrayAdapter<>(AssignTaskActivity.this,R.layout.spinner_item_1,list_jsbm_bj);
                    sp_Adapter2.setDropDownViewResource(R.layout.dropdown_style_1);
                    sp_jsbm_bj.setAdapter(sp_Adapter2);

                    sp_jsbm.setVisibility(View.GONE);
                    sp_jsbm_bj.setVisibility(View.VISIBLE);
                    sp_jsbm_bj.performClick();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_jsbm_bj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirst){
                    isFirst = false;
                    return;
                }
                if (list_jsbm_bj.get(position).equals("--重新选择部门--")){
                    isFirst = true;
                    sp_jsbm.setVisibility(View.VISIBLE);
                    sp_jsbm_bj.setVisibility(View.GONE);
                    sp_jsbm.performClick();
                }else {
                    isFirst = true;
                    jsbm_bj_name = list_jsbm_bj.get(position);
                    jsbm_bj_id = list_jsbm_bj_id.get(position);
                    sp_jsbm.setVisibility(View.GONE);
                    sp_jsbm_bj.setVisibility(View.GONE);
                    tv_jsbm.setVisibility(View.VISIBLE);
                    DistributionDepartment_id = list_jsbm_bj_id.get(position);
                    tv_jsbm.setText(DistributionDepartment+"/"+list_jsbm_bj.get(position));
                    assignTaskPresenter.getDeptUser(dialog,DistributionDepartment_id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //接收人多选选择事件
        sp_person.setOnSpinnerConfirmClickListener(new OnSpinnerConfirmClickListener() {
            @Override
            public void OnConfirmed(ArrayList<Boolean> selecteIndexList) {
                StringBuilder str1;
                str1 = new StringBuilder();
                Uuid = new ArrayList<>();
                for(int i=0;i<selecteIndexList.size();i++){
                    if(selecteIndexList.get(i)){//如果为true,则执行下面的代码
                        str1.append(spinnearModels.get(i).getParaName()).append(":").append(spinnearModels.get(i).getParaValue()).append("\n");
                        Uuid.add(spinnearModels.get(i).getParaValue());
                    }
                }

                StringBuilder str = new StringBuilder();
                for(int i=0;i<spinnearModels.size();i++){
                    str.append(spinnearModels.get(i).getParaName()).append(":").append(spinnearModels.get(i).isSelectedState()).append("\n");
                }
            }
        });

        tv_jsbm.setOnClickListener(this);
        btn_search_ship.setOnClickListener(this);
        tv_starttime.setOnClickListener(this);
        tv_endtime.setOnClickListener(this);
        btn_releasetask.setOnClickListener(this);
        assign_tv_inventory.setOnClickListener(this);
        titleBar_imgbtn_back.setOnClickListener(this);
    }
    /**
     * 设置三个checkbox只能单选
     * @param check1 check1
     * @param check2 check2
     * @param check3 check3
     */
    private void setOnlyOneCheck(final CheckBox check1,final CheckBox check2,final CheckBox check3) {

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check1.setClickable(false);
                    check2.setClickable(true);
                    check2.setChecked(false);
                    check3.setClickable(true);
                    check3.setChecked(false);
                    typeFather="1";
                    assign_tv_task_item_num.setText("已选0条任务");
                    assign_tv_task_item.setText("");
                    if(allTaskTypeListData==null){
                        assignTaskPresenter.getTaskType(dialog,true);
                    }else {
                        recyclerSetAdapter();
                    }


                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check2.setClickable(false);
                    check1.setClickable(true);
                    check1.setChecked(false);
                    check3.setClickable(true);
                    check3.setChecked(false);
                    typeFather="2";
                    assign_tv_task_item_num.setText("已选0条任务");
                    assign_tv_task_item.setText("");
                    if(allTaskTypeListData==null){
                        assignTaskPresenter.getTaskType(dialog,true);
                    }else {
                        recyclerSetAdapter();
                    }
                }
            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check3.setClickable(false);
                    check2.setClickable(true);
                    check2.setChecked(false);
                    check1.setClickable(true);
                    check1.setChecked(false);
                    typeFather="3";
                    assign_tv_task_item_num.setText("已选0条任务");
                    assign_tv_task_item.setText("");
                    if(allTaskTypeListData==null){
                        assignTaskPresenter.getTaskType(dialog,true);
                    }else {
                        recyclerSetAdapter();
                    }
                }
            }
        });
    }


    /**
     * 设置两个个checkbox只能单选
     * @param check1 check1
     * @param check2 check2
     */
    private void setOnlyOneCheck(final CheckBox check1, final CheckBox check2) {
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check1.setClickable(false);
                    check2.setClickable(true);
                    check2.setChecked(false);
                    assign_et_shipname.setHint("请输入船舶名称");
                    btn_search_ship.setVisibility(View.VISIBLE);
                    taskType = "ShipType";
                    assign_et_shipname.setText("");
                    assign_tv_task_item_num.setText("已选0条任务");
                    assign_tv_task_item.setText("");
                    isShipType="1";
                    if(allTaskTypeListData==null){
                        assignTaskPresenter.getTaskType(dialog,true);
                    }else {
                        recyclerSetAdapter();
                    }
//                   try {
//                       List<MultiItemEntity> datas = getMultiItemEntities(allTaskTypeListDataIsShip);
//                       adapter=new ExpandableItemTaskTypeAdapter(datas,AssignTaskActivity.this);
//                       final GridLayoutManager manager = new GridLayoutManager(AssignTaskActivity.this, 2);
//                       manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                           @Override
//                           public int getSpanSize(int position) {
//                               return adapter.getItemViewType(position) == ExpandableItemTaskTypeAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//                           }
//                       });
//                       recyclerView.setAdapter(adapter);
//                       recyclerView.setLayoutManager(manager);
//                       setTaskItemText();
//                       adapter.expand(0 , true);
//                   }catch(Exception e){
//                   }
                }

            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check1.setClickable(true);
                    check2.setClickable(false);
                    check1.setChecked(false);
                    assign_et_shipname.setHint("请输入任务名称");
                    btn_search_ship.setVisibility(View.GONE);
                    taskType = "UnShipType";
                    isShipType="0";
                    assign_et_shipname.setText("");
                    assign_tv_task_item_num.setText("已选0条任务");
                    assign_tv_task_item.setText("");
                    if(allTaskTypeListData==null){
                        assignTaskPresenter.getTaskType(dialog,true);
                    }else {
                        recyclerSetAdapter();
                    }
//                    try{
//                        final List<MultiItemEntity> datas = getMultiItemEntities(allTaskTypeListDataNotShip);
//                        adapter=new ExpandableItemTaskTypeAdapter(datas,AssignTaskActivity.this);
//                        final GridLayoutManager manager = new GridLayoutManager(AssignTaskActivity.this, 2);
//                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                            @Override
//                            public int getSpanSize(int position) {
//                                return adapter.getItemViewType(position) == ExpandableItemTaskTypeAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//                            }
//                        });
//                        recyclerView.setAdapter(adapter);
//                        recyclerView.setLayoutManager(manager);
//                        setTaskItemText();
//
//                    }catch (Exception e){
//                    }
                }

            }
        });
    }
    boolean isLoadInspectAagain=false;
    private void setTaskItemText() {
        adapter.setOnCheckChangListener(new SelectTaskTypeAdapter.OnCheckChangListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void setData(List<TaskType2> data) {
                TaskTypeId.clear();
                isLoadInspectAagain=true;
                mDatas=data;
                StringBuilder task_item= new StringBuilder();
                int num=1;
                for (int i = 0; i < mDatas.size(); i++){
                    if(mDatas.get(i).isChecked()){
                        task_item.append("").append(num).append("、 ").append(mDatas.get(i).getTaskTypeName()).append("\n");
                                TaskTypeId.add(mDatas.get(i).getTaskTypeId());
                                num+=1;
                    }
                }
//                for (int i = 0; i < mDatas.size(); i++) {
//                    switch (mDatas.get(i).getItemType()){
//                        case ExpandableItemTaskTypeAdapter.TYPE_LEVEL_1:
//                            Level1ItemTaskType level1Item = (Level1ItemTaskType) mDatas.get(i);
//
//                            if(level1Item.isChecked()){
//                               task_item=task_item+""+num+"、 "+level1Item.getName()+"\n";
//                                TaskTypeId.add(level1Item.getId());
//                                num+=1;
//
//                            }
//                            break;
//                    }
//
//                }
                assign_tv_task_item_num.setText("已选"+(num-1)+"条任务");
                assign_tv_task_item.setText(task_item.toString());

            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoadFaild(String error) {
        ToastUtils.showToast(this,error);
    }
    AllTaskType2 allTaskTypeListData;
    List<TaskType2> otherDaily=new ArrayList<>();
    List<TaskType2> otherLinShi=new ArrayList<>();
    List<TaskType2> otherZhuangXiang=new ArrayList<>();
    List<TaskType2> shipDaily=new ArrayList<>();
    List<TaskType2> shipLinShi=new ArrayList<>();
    List<TaskType2> shipZhuangXiang=new ArrayList<>();
    @Override
    public void setAllTaskType(AllTaskType2 allTaskType) {
        allTaskTypeListData=allTaskType;
        otherDaily=allTaskType.getOtherDaily();
        otherLinShi=allTaskType.getOtherLinShi();
        otherZhuangXiang=allTaskType.getOtherZhuangXiang();
        shipDaily=allTaskType.getShipDaily();
        shipLinShi=allTaskType.getShipLinShi();
        shipZhuangXiang=allTaskType.getShipZhuangXiang();
        recyclerSetAdapter();
    }

    private void recyclerSetAdapter() {
        if(isShipType.equals("1")){
            //船舶类
            if("1".equals(typeFather)){
                //日常
                for (int i = 0; i <shipDaily.size() ; i++) {
                    shipDaily.get(i).setChecked(false);
                    shipDaily.get(i).setCanClick(true);
                }
                adapter=new SelectTaskTypeAdapter(shipDaily);
            }else if(typeFather.equals("2")){
                //专项
                for (int i = 0; i <shipZhuangXiang.size() ; i++) {
                    shipZhuangXiang.get(i).setChecked(false);
                    shipZhuangXiang.get(i).setCanClick(true);
                }
                adapter=new SelectTaskTypeAdapter(shipZhuangXiang);
            }else if(typeFather.equals("3")){
                //临时
                for (int i = 0; i <shipLinShi.size() ; i++) {
                    shipLinShi.get(i).setChecked(false);
                    shipLinShi.get(i).setCanClick(true);
                }
                adapter=new SelectTaskTypeAdapter(shipLinShi);
            }
        }else if (isShipType.equals("0")){
            //其他类
            if("1".equals(typeFather)){
                //日常
                for (int i = 0; i <otherDaily.size() ; i++) {
                    otherDaily.get(i).setChecked(false);
                    otherDaily.get(i).setCanClick(true);
                }
                adapter=new SelectTaskTypeAdapter(otherDaily);
            }else if("2".equals(typeFather)){
                //专项
                for (int i = 0; i <otherZhuangXiang.size() ; i++) {
                    otherZhuangXiang.get(i).setChecked(false);
                    otherZhuangXiang.get(i).setCanClick(true);
                }
                adapter=new SelectTaskTypeAdapter(otherZhuangXiang);
            }else if("3".equals(typeFather)){
                //临时
                for (int i = 0; i <otherLinShi.size() ; i++) {
                    otherLinShi.get(i).setChecked(false);
                    otherLinShi.get(i).setCanClick(true);
                }
                adapter=new SelectTaskTypeAdapter(otherLinShi);
            }
        }

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        setTaskItemText();
    }
//    @Override
//    public void setAllTaskType(List<AllTaskType> allTaskTypeList) {
//        allTaskTypeListData = bulidAllTaskType(allTaskTypeList);
//        //封装非船舶类
//        for (int i = 0; i <allTaskTypeListData.size() ; i++) {
//            if(!allTaskTypeListData.get(i).getId().equals("RS")){
//                allTaskTypeListDataNotShip.add(allTaskTypeListData.get(i));
//            }
//        }
//        //封装船舶类
//        for (int i = 0; i <allTaskTypeListData.size() ; i++) {
//            if(!allTaskTypeListData.get(i).getId().equals("RC")){
//                allTaskTypeListDataIsShip.add(allTaskTypeListData.get(i));
//            }
//        }
//
//        if(assign_cb_ship.isChecked()){
//            List<MultiItemEntity> datas = getMultiItemEntities(allTaskTypeListDataIsShip);
//            adapter=new ExpandableItemTaskTypeAdapter(datas,this);
//            final GridLayoutManager manager = new GridLayoutManager(this, 2);
//            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    return adapter.getItemViewType(position) == ExpandableItemTaskTypeAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//                }
//            });
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(manager);
//            setTaskItemText();
//            adapter.expand(0 , true);
//        }else {
//            List<MultiItemEntity> datas = getMultiItemEntities(allTaskTypeListDataNotShip);
//            adapter=new ExpandableItemTaskTypeAdapter(datas,this);
//            final GridLayoutManager manager = new GridLayoutManager(this, 2);
//            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    return adapter.getItemViewType(position) == ExpandableItemTaskTypeAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//                }
//            });
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(manager);
//            setTaskItemText();
//        }
//
//
//    }

    @Override
    public void setdeptUser(List<UserInfo> userInfos) {
        spinnearModels = new ArrayList<>();
        for (int i = 0; i < userInfos.size(); i++) {
            SpinnearModel spinnearModel = new SpinnearModel();
            spinnearModel.setParaName(userInfos.get(i).getUserName());
            spinnearModel.setParaValue(userInfos.get(i).getUuid());
            spinnearModels.add(spinnearModel);
        }
        if (spinnearModels != null && spinnearModels.size() > 0){
            sp_person.setData(spinnearModels);//设置下拉菜单列表集合源
            sp_person.setHint("选择接收人");
        }
    }

    //选船数据显示
    private PopupWindow popupWindow;

    @Override
    public void setTaskShipData(final List<TaskShipData> taskShipData) {
        //初始化加载视图的xml文件
        View view = View.inflate(this, R.layout.activity_listview, null);
        ListView listView = view.findViewById(R.id.lv);
        // 获取手机屏幕的宽度 单位像素px
        int width = DeviceUtils.getScreenSize(this)[0];
        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        int magin = DimensUtils.dp2px(this, 10);
        // 初始化popupwindow
        popupWindow = new PopupWindow(view, width -magin, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        popupWindow.showAsDropDown(btn_search_ship);
        TaskShipDataAdapter adapter = new TaskShipDataAdapter(this,taskShipData,R.layout.item_listview_ferry);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                assign_et_shipname.setText(taskShipData.get(position).getZwcm());
                taskInfoDetails.setMmsi(taskShipData.get(position).getMmsi());
                taskInfoDetails.setShipNameCn(taskShipData.get(position).getZwcm());
                taskInfoDetails.setShipNameEn(taskShipData.get(position).getYwcm());
            }
        });
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }



    @Override
    public void setTaskInspectionAll(List<TaskInspectionItemData> taskInspectionAll) {
        this.taskInspectionAll=taskInspectionAll;
            StringBuilder s= new StringBuilder();
            if(TaskTypeId.size()==0){
                //ToastUtils.showToast(this,"请选择任务类型");
                return;
            }
            List<String> stringList=removeDuplicate(TaskTypeId);


            for (int i = 0; i <stringList.size() ; i++) {
                s.append(stringList.get(i)).append(",");
            }
            String taskTypeId=s.substring(0,s.length()-1);
            assignTaskPresenter.getTaskInspectionTaskTypeId(dialog,true,taskTypeId);

    }

    @Override
    public void setTaskInspectionTaskTypeId(List<TaskInspectionItemData> taskInspectionAll) {
        for (int i = 0; i < this.taskInspectionAll.size(); i++) {
            for (int j = 0; j < taskInspectionAll.size(); j++) {
                this.taskInspectionAll.get(i).setChecked(false);
                this.taskInspectionAll.get(i).setCanClick(true);

            }
        }
        //设置查所有不可点击
        for (int i = 0; i < this.taskInspectionAll.size(); i++) {
            for (int j = 0; j < taskInspectionAll.size(); j++) {
                this.taskInspectionAll.get(i).setCanClick(false);
                if(this.taskInspectionAll.get(i).getItemFuseId().equals(taskInspectionAll.get(j).getItemFuseId())){
                    this.taskInspectionAll.get(i).setChecked(true);
                }
            }
        }


        //设置查询出的结果不可点击
//        for (int i = 0; i < this.taskInspectionAll.size(); i++) {
//            for (int j = 0; j < taskInspectionAll.size(); j++) {
//                if(this.taskInspectionAll.get(i).getItemFuseId().equals(taskInspectionAll.get(j).getItemFuseId())){
//                    this.taskInspectionAll.get(i).setChecked(true);
//                    this.taskInspectionAll.get(i).setCanClick(false);
//                }
//            }
//        }


        List<MultiItemEntity> datas = new ArrayList<>();
        List<TaskInspectionItemData> taskInspectionItemDataList = bulid(this.taskInspectionAll);

        for (int i = 0; i < taskInspectionItemDataList.size(); i++) {
            Level0Item level0Item = new Level0Item();
            level0Item.setItemFuseName(taskInspectionItemDataList.get(i).getItemFuseName());
            level0Item.setFscCode(taskInspectionItemDataList.get(i).getFscCode());
            level0Item.setIsMustCheck(taskInspectionItemDataList.get(i).getIsMustCheck());
            level0Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getItemFuseDetails());
            level0Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getItemFuseFaterId());
            level0Item.setRemarks(taskInspectionItemDataList.get(i).getRemarks());
            level0Item.setItemFuseId(taskInspectionItemDataList.get(i).getItemFuseId());
            level0Item.setChecked(taskInspectionItemDataList.get(i).isChecked());
            level0Item.setCanClick(taskInspectionItemDataList.get(i).isCanClick());
            for (int j = 0; j < taskInspectionItemDataList.get(i).getChild().size(); j++) {
                if (taskInspectionItemDataList.get(i).getChild().size() > 0 && taskInspectionItemDataList.get(i).getChild() !=null){
                    Level1Item level1Item = new Level1Item();

                    level1Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseName());
                    level1Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getFscCode());
                    level1Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getIsMustCheck());
                    level1Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseDetails());
                    level1Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseFaterId());
                    level1Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getRemarks());
                    level1Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseId());
                    level1Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).isChecked());
                    level1Item.setCanClick(taskInspectionItemDataList.get(i).getChild().get(j).isCanClick());
                    for (int k = 0; k < taskInspectionItemDataList.get(i).getChild().get(j).getChild().size(); k++) {
                        Level2Item level2Item = new Level2Item();
                        level2Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseName());

                        level2Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getFscCode());
                        level2Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getIsMustCheck());
                        level2Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseDetails());
                        level2Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseFaterId());
                        level2Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getRemarks());
                        level2Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseId());
                        level2Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).isChecked());
                        level2Item.setCanClick(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).isCanClick());
                        level1Item.addSubItem(level2Item);
                        for (int l = 0; l < taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().size(); l++) {
                            Level3Item level3Item = new Level3Item();
                            level3Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseName());

                            level3Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getFscCode());
                            level3Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getIsMustCheck());
                            level3Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseDetails());
                            level3Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseFaterId());
                            level3Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getRemarks());
                            level3Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseId());
                            level3Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).isChecked());
                            level3Item.setCanClick(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).isCanClick());
                            level2Item.addSubItem(level3Item);
                            for (int m = 0; m < taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().size(); m++) {
                                Level4Item level4Item = new Level4Item();
                                level4Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseName());

                                level4Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getFscCode());
                                level4Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getIsMustCheck());
                                level4Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseDetails());
                                level4Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseFaterId());
                                level4Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getRemarks());
                                level4Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseId());
                                level4Item.setChecked(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).isChecked());
                                level4Item.setCanClick(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).isCanClick());
                                level3Item.addSubItem(level4Item);
                            }
                        }
                    }
                    level0Item.addSubItem(level1Item);
                }
            }
            datas.add(level0Item);
        }

        mDatas_InspectionAll=datas;


        if(inspectionDialog!=null){
            if(isLoadInspectAagain){
                inspectionDialog=new InspectionDialog(this,mDatas_InspectionAll);
                inspectionDialog.showDialog();
                inspectionDialog.setOnData(new InspectionDialog.OnGetData() {
                    @Override
                    public void onDataCallBack(List<MultiItemEntity> data) {
                        inspectionSelect=data;
                    }
                });
                isLoadInspectAagain=false;
            }else {
                inspectionDialog.showDialog();
            }

        }else {

            inspectionDialog=new InspectionDialog(this,mDatas_InspectionAll);
            inspectionDialog.showDialog();
            inspectionDialog.setOnData(new InspectionDialog.OnGetData() {
                @Override
                public void onDataCallBack(List<MultiItemEntity> data) {
                    inspectionSelect=data;
                }
            });
        }
    }

//    @NonNull
//    private List<MultiItemEntity> getMultiItemEntities(List<AllTaskType> allTaskTypeListData) {
//        List<MultiItemEntity> datas = new ArrayList<>();
//        for (int i = 0; i <allTaskTypeListData.size() ; i++) {
//            Level0ItemTaskType level0ItemTaskType=new Level0ItemTaskType();
//
//            level0ItemTaskType.setChecked(allTaskTypeListData.get(i).getChecked());
//            level0ItemTaskType.setPid(allTaskTypeListData.get(i).getPid());
//            level0ItemTaskType.setName(allTaskTypeListData.get(i).getName());
//            level0ItemTaskType.setId(allTaskTypeListData.get(i).getId());
//
//            for (int j = 0; j <allTaskTypeListData.get(i).getChild().size() ; j++) {
//                Level1ItemTaskType level1ItemTaskType=new Level1ItemTaskType();
//
//                level1ItemTaskType.setChecked(allTaskTypeListData.get(i).getChild().get(j).getChecked());
//                level1ItemTaskType.setPid(allTaskTypeListData.get(i).getChild().get(j).getPid());
//                level1ItemTaskType.setName(allTaskTypeListData.get(i).getChild().get(j).getName());
//                level1ItemTaskType.setId(allTaskTypeListData.get(i).getChild().get(j).getId());
//                level0ItemTaskType.addSubItem(level1ItemTaskType);
//            }
//            datas.add(level0ItemTaskType);
//    }
//        return datas;
//    }
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

//    public List<AllTaskType> bulidAllTaskType(List<AllTaskType> allTaskTypeList) {
//        List<AllTaskType> allTaskTypeListData = new ArrayList<>();
//        for (AllTaskType allTaskType : allTaskTypeList) {
//            if ("ROOT".equals(allTaskType.getPid())) {
//                allTaskTypeListData.add(allTaskType);
//            }
//            for (AllTaskType it : allTaskTypeList) {
//                if(it.getPid().equals(allTaskType.getId())){
//                    if(allTaskType.getChild()==null){
//                        allTaskType.setChild(new ArrayList<AllTaskType>());
//                    }
//                    allTaskType.getChild().add(it);
//                }
//            }
//        }
//        return allTaskTypeListData;
//    }

    //去重
    public  List removeDuplicate(List list){
        List listTemp = new ArrayList();
        for(int i=0;i<list.size();i++){
            if(!listTemp.contains(list.get(i))){
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }
    InspectionDialog inspectionDialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.tv_jsbm:
                sp_jsbm.setVisibility(View.VISIBLE);
                sp_jsbm_bj.setVisibility(View.GONE);
                tv_jsbm.setVisibility(View.GONE);
                sp_jsbm.performClick();
                break;
            case R.id.btn_search_ship:
                String key = assign_et_shipname.getText().toString();
                if (!"".equals(key)){
                    boolean isNum = key.matches("[0-9]+");
                    if (isNum){
                        if (key.length() == 9){
                            assignTaskPresenter.getTaskShipDataByMMSI(dialog,key);
                        }else {
                            assignTaskPresenter.getTaskShipDataByKey(dialog,key);
                        }
                    }else {
                        assignTaskPresenter.getTaskShipDataByKey(dialog,key);
                    }
                }else {
                    ToastUtils.showToast(this,"请输入船舶名称");
                }

                break;
            case R.id.assign_tv_start_time:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.CHINA);
                        String time = format.format(date);
                        tv_starttime.setText(time);
                        start_time = time;
                    }
                });
                timePickerView.show();
                break;
            case R.id.assign_tv_end_time:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.CHINA);
                        String time = format.format(date);
                        tv_endtime.setText(time);
                        end_time = time;
                    }
                });
                timePickerView.show();
                break;
            case R.id.btn_releasetask:
                String checkFormIds;
                if(inspectionSelect!=null&&inspectionSelect.size()!=0){
                    for (int i = 0; i <inspectionSelect.size() ; i++) {
                        switch (inspectionSelect.get(i).getItemType()){
                            case TYPE_LEVEL_0:
                                Level0Item level0Item = (Level0Item) inspectionSelect.get(i);
                                if(level0Item.isChecked()){
                                    //第一层
                                    taskTypeIdList.add(level0Item.getItemFuseId());
                                    if(level0Item.hasSubItem()){
                                        for (int j = 0; j <level0Item.getSubItems().size() ; j++) {
                                            if(level0Item.getSubItem(j).isChecked()){
                                                //第二层
                                                taskTypeIdList.add(level0Item.getSubItem(j).getItemFuseId());
                                                if(level0Item.getSubItem(j).hasSubItem()){
                                                    for (int k = 0; k <level0Item.getSubItem(j).getSubItems().size() ; k++) {
                                                        if(level0Item.getSubItem(j).getSubItem(k).isChecked()){
                                                            //第三层
                                                            taskTypeIdList.add(level0Item.getSubItem(j).getSubItem(k).getItemFuseId());
                                                            if(level0Item.getSubItem(j).getSubItem(k).hasSubItem()){
                                                                for (int l = 0; l <level0Item.getSubItem(j).getSubItem(k).getSubItems().size() ; l++) {
                                                                    if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).isChecked()){
                                                                        //第四层
                                                                        taskTypeIdList.add(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseId());
                                                                        if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).hasSubItem()){
                                                                            for (int m = 0; m < level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItems().size(); m++) {
                                                                                if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).isChecked()){
                                                                                    //第五层
                                                                                    taskTypeIdList.add(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).getItemFuseId());
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                        }
                    }
                    List<String> remveDuplicate= removeDuplicate(taskTypeIdList);
                    StringBuilder sb=new StringBuilder("");
                    for (int i = 0; i <remveDuplicate.size() ; i++) {
                        if(i==remveDuplicate.size()-1){
                            sb.append(remveDuplicate.get(i));
                        }else {
                            sb.append(remveDuplicate.get(i)).append(",");
                        }
                    }
//                    taskInfo.setCheckFormIds(sb.toString());
                    checkFormIds = sb.toString();
                }else {
//                    taskInfo.setCheckFormIds("");
                    checkFormIds = "";
                }

                if (taskType.equals("ShipType")){
                    if (taskInfoDetails.getMmsi() == null){
                        ToastUtils.showToast(this,"请选择任务船舶！");
                        return;
                    }
                }else {
                    if (assign_et_shipname.getText().toString().equals("")){
                        ToastUtils.showToast(this,"请填写任务名称！");
                        return;
                    }
                }

                if (TaskTypeId == null || TaskTypeId.size() <= 0 ){
                    ToastUtils.showToast(this,"请选择任务类型！");
                    return;
                }
                if (start_time.equals("") || end_time.equals("")){
                    ToastUtils.showToast(this,"请选择任务时间！");
                    return;
                }
//                if (jsbm_bj_id.equals("") || jsbm_bj_name.equals("") || jsbm_id.equals("") || jsbm_name.equals("")){
//                    ToastUtils.showToast(this,"请选择接受部门！");
//                    return;
//                }
//                if (checkFormIds.equals("")){
//                    ToastUtils.showToast(this,"请选择任务清单！");
//                    return;
//                }
//                if (Uuid == null){
//                    ToastUtils.showToast(this,"请选择任务接收人！");
//                    return;
//                }

                ReleaseTaskModel releaseTaskModel = new ReleaseTaskModel();
//                taskAssign.setTaskArriveDeptCode(jsbm_bj_id);
//                taskAssign.setTaskArriveDeptName(jsbm_bj_name);
//                taskAssign.setTaskArriveOrgId(jsbm_id);
//                taskAssign.setTaskArriveOrgId(jsbm_name);

                taskInfo.setCheckFormIds(checkFormIds);
                taskInfo.setEstimatedStartTime(tv_starttime.getText().toString());
                taskInfo.setEstimatedEndTime(tv_endtime.getText().toString());
                taskInfo.setStatus(BigDecimal.valueOf(infoStatus));
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < TaskTypeId.size(); i++) {
                    if (i < TaskTypeId.size() - 1) {
                        text.append(TaskTypeId.get(i));
                        text.append(",");
                    } else {
                        text.append(TaskTypeId.get(i));
                    }
                }
                String str = text.toString();
                if (taskType.equals("ShipType")){
                    taskInfo.setTaskTypeId(str);
                }else {
                    taskInfo.setTaskTypeId(str);
                    taskInfo.setTaskName(assign_et_shipname.getText().toString());
                }
                releaseTaskModel.setTaskInfo(taskInfo);
                releaseTaskModel.setTaskAssign(taskAssign);
                releaseTaskModel.setTaskInfoDetails(taskInfoDetails);
//                StringBuilder text1 = new StringBuilder();
//                for (int i = 0; i < Uuid.size(); i++) {
//                    if (i < Uuid.size() - 1) {
//                        text1.append(Uuid.get(i));
//                        text1.append(",");
//                    } else {
//                        text1.append(Uuid.get(i));
//                    }
//                }
//                String str1 = text1.toString();
//                releaseTaskModel.setTaskAssignUuid(str1);
                assignTaskPresenter.releaseTask(dialog,releaseTaskModel);
                break;
            case R.id.assign_tv_inventory:
                if(taskInspectionAll==null||taskInspectionAll.size()==0){
                    isInspectionAllLoadAgain=true;
                    assignTaskPresenter.getTaskInspectionAll(dialog,true);
                }else {
                    StringBuilder s= new StringBuilder();
                    if(TaskTypeId.size()==0){
                        ToastUtils.showToast(this,"请选择任务类型");
                        return;
                    }
                    List<String> stringList=removeDuplicate(TaskTypeId);


                    for (int i = 0; i <stringList.size() ; i++) {
                        s.append(stringList.get(i)).append(",");
                    }
                    String taskTypeId=s.substring(0,s.length()-1);
                    assignTaskPresenter.getTaskInspectionTaskTypeId(dialog,true,taskTypeId);

                }
                break;
        }
    }
}
