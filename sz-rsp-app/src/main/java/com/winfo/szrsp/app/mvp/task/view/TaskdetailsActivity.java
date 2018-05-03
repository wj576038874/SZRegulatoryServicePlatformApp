package com.winfo.szrsp.app.mvp.task.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.task.TaskDetCbAdapter;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.dialog.ExceptionShipFinishDialog;
import com.winfo.szrsp.app.mvp.table.kxjc.view.KxjcActivity;
import com.winfo.szrsp.app.mvp.table.psca.view.PSCFormAActivity;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.CruiseWorkRecordActivity;
import com.winfo.szrsp.app.mvp.table.xhtj.view.CruiseStatisticsActivity;
import com.winfo.szrsp.app.mvp.task.presenter.TaskDetPresenter;
import com.winfo.szrsp.app.mvp.task.presenter.TaskPresenter;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.task.LawEnforcementShip;
import com.winfo.szrsp.app.sdk.entity.task.TaskAcceptData;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskDeptData;
import com.winfo.szrsp.app.sdk.entity.task.TaskDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.task.TaskResources;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DataHolder;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.JsonUtil;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.MyListView;
import com.winfo.szrsp.app.widget.spinner.OnSpinnerConfirmClickListener;
import com.winfo.szrsp.app.widget.spinner.SpinnearModel;
import com.winfo.szrsp.app.widget.spinner.SpinnerViewMultiDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskdetailsActivity extends Activity implements View.OnClickListener, ITaskDetActivity {
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;

    @BindView(R.id.task_detail_title)
    TextView task_detail_title;

    @BindView(R.id.task_detail_taskname)
    TextView task_detail_taskname;
    @BindView(R.id.task_detail_taskdesc)
    TextView task_detail_taskdesc;
    @BindView(R.id.task_detail_taskoriginid)
    TextView task_detail_taskoriginid;
    @BindView(R.id.task_detail_taskreleaseorgname)
    TextView task_detail_taskreleaseorgname;
    @BindView(R.id.task_detail_taskreleasedeptname)
    TextView task_detail_taskreleasedeptname;

    @BindView(R.id.task_detail_estimatedstarttime)
    TextView task_detail_estimatedstarttime;
    @BindView(R.id.task_detail_estimatedendtime)
    TextView task_detail_estimatedendtime;
    @BindView(R.id.task_detail_taskreleasetime)
    TextView task_detail_taskreleasetime;
    @BindView(R.id.task_detail_taskstarttime)
    TextView task_detail_taskstarttime;
    @BindView(R.id.task_detail_taskendtime)
    TextView task_detail_taskendtime;

    @BindView(R.id.task_detail_tasktype)
    TextView task_detail_tasktype;
    @BindView(R.id.task_detail_location)
    TextView task_detail_location;
    @BindView(R.id.task_detail_tv_cb)
    TextView task_detail_tv_cb;

    @BindView(R.id.task_detail_list_cb)
    MyListView task_detail_list_cb;

    @BindView(R.id.id_btn_accept)
    Button id_btn_accept;
    @BindView(R.id.id_btn_tuihui)
    Button id_btn_tuihui;
    @BindView(R.id.id_btn_zhipai)
    Button btn_zhipai;

    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.task_detail_disposalDecision)
    TextView task_detail_disposalDecision;

    private TaskDetCbAdapter adapter;

    private TaskRejectDialog taskRejectDialog;
    private KaiXiangInspectDialog kaiXiangInspectDialog;

    Dialog dialog;
    private String taskId = "";
    private TaskDetPresenter presenter;
    private TaskDetails taskDetails;

    private TaskPresenter taskPresenter;
    private String mmsi;
    private MyHandler myHandler;

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    TaskInfo taskInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);
        ButterKnife.bind(this);
        taskInfo = (TaskInfo) getIntent().getSerializableExtra("task_detail_data");
        taskId = taskInfo.getTaskId();
        myHandler = new MyHandler(this);
        initData();
        initEvent();
    }

    private void initData() {
        dialog = DialogUtils.createLoadingDialog(this, "加载中...");
        presenter = new TaskDetPresenter(this);
        presenter.getTaskDetData(taskId, true);
        taskPresenter = new TaskPresenter(this);

//        TaskAssignStatus
        switch (Integer.parseInt(taskInfo.getStatus().toString())) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                //待接收
                id_btn_accept.setText("接收任务");
                id_btn_tuihui.setClickable(false);
                id_btn_tuihui.setEnabled(false);
                id_btn_tuihui.setTextColor(Color.parseColor("#848484"));
                break;
            case 3:
                //待分发
                id_btn_tuihui.setClickable(false);
                id_btn_tuihui.setEnabled(false);
                id_btn_tuihui.setTextColor(Color.parseColor("#848484"));
                btn_zhipai.setClickable(true);
                btn_zhipai.setEnabled(true);
                btn_zhipai.setTextColor(Color.parseColor("#FFFFFF"));
                id_btn_accept.setClickable(false);
                id_btn_accept.setEnabled(false);
                id_btn_accept.setTextColor(Color.parseColor("#848484"));
                break;
            case 4:
                id_btn_accept.setText("执行任务");
                break;
            case 5:
                id_btn_tuihui.setText("已取消");
                id_btn_tuihui.setClickable(false);
                id_btn_tuihui.setEnabled(false);
                id_btn_tuihui.setTextColor(Color.parseColor("#848484"));

                id_btn_accept.setClickable(false);
                id_btn_accept.setEnabled(false);
                id_btn_accept.setTextColor(Color.parseColor("#848484"));
                break;
            case 6:
                break;
            case 8:
                id_btn_accept.setText("已完成");
                id_btn_accept.setClickable(false);
                id_btn_accept.setEnabled(false);
                id_btn_accept.setTextColor(Color.parseColor("#848484"));

                id_btn_tuihui.setClickable(false);
                id_btn_tuihui.setEnabled(false);
                id_btn_tuihui.setTextColor(Color.parseColor("#848484"));
                break;
//            case 17:
//                //tvTaskStatus.setText("已完成");
//                id_btn_accept.setText("他人已完成");
//                id_btn_accept.setClickable(false);
//                id_btn_accept.setEnabled(false);
//                id_btn_accept.setTextColor(Color.parseColor("#848484"));
//                break;
            case 9:

                break;
            default:
                break;
        }


    }

    private void initEvent() {
        titleBar_imgbtn_back.setOnClickListener(this);
        id_btn_accept.setOnClickListener(this);
        id_btn_tuihui.setOnClickListener(this);
        btn_zhipai.setOnClickListener(this);
        tv_location.setOnClickListener(this);
    }

    TaskAcceptDialog taskAcceptDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.id_btn_zhipai:
                assignPop();
                break;
            case R.id.id_btn_accept:
//                if (kaiXiangInspectDialog == null) {
//                    kaiXiangInspectDialog = new KaiXiangInspectDialog(TaskdetailsActivity.this, taskDetails.getTaskInfo().getTaskId(), taskDetails.getTaskInfoDetailsList().get(0).getShipNameCn(), taskDetails.getTaskInfoDetailsList().get(0).getShipNameEn());
//                } else {
//                    kaiXiangInspectDialog.showDialog();
//                }
                if (taskInfo.getStatus().toString().equals("4") || id_btn_accept.getText().toString().equals("执行任务")) {
                    if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000011")) {
                        DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                            @Override
                            public void btnConfirmClick(Dialog dialog) {
                                dialog.dismiss();
                                if (exceptionShipDialog == null) {
                                    exceptionShipDialog = new ExceptionShipFinishDialog(TaskdetailsActivity.this);
                                    exceptionShipDialog.setOnData(new ExceptionShipFinishDialog.OnGetData() {
                                        @Override
                                        public void onDataCallBack(String dec) {
                                            presenter.finishTask(true, taskId, dec);
                                        }
                                    });
                                } else {
                                    exceptionShipDialog.showDialog();
                                }
                            }

                            @Override
                            public void btnCancelClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
                    } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000003")) {
                        Intent intent = new Intent(TaskdetailsActivity.this, PSCFormAActivity.class);
                        intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
                        startActivity(intent);
                    } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RO_00000003")) {
                        Intent intent = new Intent(TaskdetailsActivity.this, CruiseWorkRecordActivity.class);
                        intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
                        startActivity(intent);
                    } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000006")) {
//                        Intent intent = new Intent(TaskdetailsActivity.this, KxjcActivity.class);
//                        intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
//                        intent.putExtra("shipNameCn", taskDetails.getTaskInfoDetailsList().get(0).getShipNameCn());
//                        intent.putExtra("shipNameEn", taskDetails.getTaskInfoDetailsList().get(0).getShipNameEn());
//                        startActivity(intent);
                        if (kaiXiangInspectDialog == null) {
                            kaiXiangInspectDialog = new KaiXiangInspectDialog(TaskdetailsActivity.this, taskDetails.getTaskInfo().getTaskId(), taskDetails.getTaskInfoDetailsList().get(0).getShipNameCn(), taskDetails.getTaskInfoDetailsList().get(0).getShipNameEn());
                        } else {
                            kaiXiangInspectDialog.showDialog();
                        }
                    } else {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < taskInfo.getTaskTypeList().size(); i++) {
                            list.add(taskInfo.getTaskTypeList().get(i).getTaskTypeId());
                        }
                        Intent intent = new Intent(TaskdetailsActivity.this, TaskInspectionActivity.class);
                        intent.putExtra("typeId", (Serializable) list);
                        intent.putExtra("checkFormIds", taskDetails.getTaskInfo().getCheckFormIds());
                        intent.putExtra("taskInfo", taskInfo);
                        intent.putExtra("ShipInfo", (Serializable) taskDetails.getTaskInfoDetailsList());
                        intent.putExtra("taskAssignList", (Serializable) taskDetails.getTaskAssignList());
                        startActivity(intent);
                    }
                } else {
                    //TODO 暂不显示执法人执法资源
                    taskPresenter.getResouseByUserDept(true);

//                    DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "确认接受任务？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
//                        @Override
//                        public void btnConfirmClick(Dialog dialog) {
//                            dialog.dismiss();
//                            taskPresenter.acceptTask(taskId, true);
//                        }
//
//                        @Override
//                        public void btnCancelClick(Dialog dialog) {
//                            dialog.dismiss();
//
//                        }
//                    });
                }
                break;
            case R.id.id_btn_tuihui:
                if (taskRejectDialog == null) {
                    taskRejectDialog = new TaskRejectDialog(TaskdetailsActivity.this);
                    taskRejectDialog.setOnData(new TaskRejectDialog.OnGetData() {
                        @Override
                        public void onDataCallBack(String dec) {
                            taskPresenter.rejectTask(true, dec);
                            taskRejectDialog.dismiss();
                        }
                    });
                } else {
                    taskRejectDialog.showDialog();
                }
                break;
            case R.id.tv_location:
                String mmsi = taskDetails.getTaskInfoDetailsList().get(0).getMmsi();
                taskPresenter.getAisData(mmsi);
                break;
            case R.id.btn_fenfa:
                showDailog();
                break;
        }
    }

    @Override
    public void showOnFaile(String msg) {
        ToastUtils.showToast(TaskdetailsActivity.this, msg);
    }

    @Override
    public Dialog getDailog() {

        return dialog;
    }

    @Override
    public void setTaskDetData(TaskDetails data) {
        this.taskDetails = data;
        task_detail_title.setText(taskDetails.getTaskInfo().getTaskName());
        task_detail_taskname.setText(taskDetails.getTaskInfo().getTaskName());
        String dec = "";
        if (taskInfo.getTaskTypeList().size() > 0) {
            for (int i = 0; i < taskInfo.getTaskTypeList().size(); i++) {
                String task = taskInfo.getTaskTypeList().get(i).getTaskTypeTitleTemp();
                if (i == taskInfo.getTaskTypeList().size() - 1) {
                    dec += task;
                } else {
                    dec += task + "\n";
                    ;
                }
            }
        }
        task_detail_taskdesc.setText(dec);

        //task_detail_taskdesc.setText(taskDetails.getTaskInfo().getTaskDesc());

        task_detail_taskoriginid.setText(taskDetails.getTaskInfo().getTaskOriginId());

        task_detail_taskreleaseorgname.setText(taskDetails.getTaskInfo().getTaskReleaseOrgName());

        task_detail_taskreleasedeptname.setText(taskDetails.getTaskInfo().getTaskReleaseDeptName());

        task_detail_estimatedstarttime.setText(taskDetails.getTaskInfo().getEstimatedStartTime());

        task_detail_estimatedendtime.setText(taskDetails.getTaskInfo().getEstimatedEndTime());

        task_detail_taskreleasetime.setText(taskDetails.getTaskInfo().getTaskReleaseTime());

        task_detail_taskstarttime.setText(taskDetails.getTaskInfo().getTaskStartTime());

        task_detail_taskendtime.setText(taskDetails.getTaskInfo().getTaskEndTime());

        task_detail_disposalDecision.setText(taskDetails.getTaskInfo().getDisposalDecision());
        task_detail_tasktype.setText(taskDetails.getTaskInfo().getRemark());

        task_detail_location.setText("" + taskDetails.getTaskInfo().getLatitude() + "   " + taskDetails.getTaskInfo().getLongitude());

        if (taskDetails.getTaskInfoDetailsList() != null && taskDetails.getTaskInfoDetailsList().size() > 0) {
            task_detail_tv_cb.setVisibility(View.VISIBLE);
            tv_location.setVisibility(View.VISIBLE);
            adapter = new TaskDetCbAdapter(this, taskDetails.getTaskInfoDetailsList(), R.layout.task_det_cb_item);
            task_detail_list_cb.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            task_detail_tv_cb.setVisibility(View.GONE);
            tv_location.setVisibility(View.GONE);
        }
    }

    @Override
    public void rejectSucceed(String msg) {
        ToastUtils.showToast(this, msg);
        id_btn_tuihui.setClickable(false);
        id_btn_tuihui.setEnabled(false);
        id_btn_tuihui.setTextColor(Color.parseColor("#848484"));

        id_btn_accept.setClickable(false);
        id_btn_accept.setEnabled(false);
        id_btn_accept.setTextColor(Color.parseColor("#848484"));


        btn_zhipai.setClickable(false);
        btn_zhipai.setEnabled(false);
        btn_zhipai.setTextColor(Color.parseColor("#848484"));
    }

    @Override
    public void rejectFaile(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public TaskFinishSubData getTaskFinishSubData() {
        return null;
    }

    @Override
    public TaskAcceptData getTaskAcceptData() {

        if (taskResourcesListFromDialog == null) {
            taskResourcesListFromDialog = new ArrayList<>();
        }
        if (userInfoListFromDialog == null) {
            userInfoListFromDialog = new ArrayList<>();
        }

        TaskAcceptData taskAcceptData = new TaskAcceptData();
        taskAcceptData.setTaskId(taskId);
        //uuid
        String uuid = "";

        for (int i = 0; i < userInfoListFromDialog.size(); i++) {
            uuid = uuid + userInfoListFromDialog.get(i).getUuid() + ",";
        }
        taskAcceptData.setUuid(uuid.substring(0, uuid.length() - 1));
        String lawEnforcementCar = "";
        String lawEnforcementShip = "";

        for (int i = 0; i < taskResourcesListFromDialog.size(); i++) {
            if (taskResourcesListFromDialog.get(i).getPatrolBoatMmsi() == null || taskResourcesListFromDialog.get(i).getPatrolBoatMmsi().equals("")) {
                lawEnforcementCar = lawEnforcementCar + taskResourcesListFromDialog.get(i).getEnforceVehicleLicense() + ",";
            } else {
                lawEnforcementShip = lawEnforcementShip + taskResourcesListFromDialog.get(i).getPatrolBoatMmsi() + ",";
            }

        }
        if (lawEnforcementCar.length() > 0) {
            taskAcceptData.setLawEnforcementCar(lawEnforcementCar.substring(0, lawEnforcementCar.length() - 1));
        } else {
            taskAcceptData.setLawEnforcementCar(lawEnforcementCar);
        }

        if (lawEnforcementShip.length() > 0) {
            taskAcceptData.setLawEnforcementShip(lawEnforcementShip.substring(0, lawEnforcementShip.length() - 1));
        } else {
            taskAcceptData.setLawEnforcementShip(lawEnforcementShip);
        }


//        if(taskResourcesListFromDialog.size()>0){
//            taskAcceptData.setTaskResources(taskResourcesListFromDialog);
//        }
//        List<TaskAssign> listTaskAssign=new ArrayList<>();
//
//        for(int i=0;i<userInfoListFromDialog.size();i++){
//            TaskAssign taskAssign=(TaskAssign)taskDetails.getTaskAssignList().get(0).clone();
//            taskAssign.setTaskArriveUserUuid(userInfoListFromDialog.get(i).getUuid());
//            taskAssign.setTaskArriveDeptCode(userInfoListFromDialog.get(i).getDeptCode());
//            taskAssign.setTaskArriveUserName(userInfoListFromDialog.get(i).getUserName());
//            taskAssign.setTaskArriveDeptName(userInfoListFromDialog.get(i).getDeptName());
//            taskAssign.setTaskArriveOrgName(userInfoListFromDialog.get(i).getOrgName());
//            listTaskAssign.add(taskAssign);
//            }
//        taskAcceptData.setTaskAssign(listTaskAssign);
        return taskAcceptData;
    }

    @Override
    public void finishSucceed(TaskFinishData data, String msg) {

    }

    @Override
    public void finishSucceedDet(String msg) {
        ToastUtils.showToast(TaskdetailsActivity.this, msg);
        finish();
    }


    @Override
    public void finishFaile(String msg) {
        ToastUtils.showToast(TaskdetailsActivity.this, msg);
    }

    ExceptionShipFinishDialog exceptionShipDialog;

    @Override
    public void acceptSucceed(String msg) {
        taskAcceptDialog.dismiss();
        id_btn_accept.setText("执行任务");
        taskInfo.setStatus(new BigDecimal("4"));
        for (int i = 0; i < taskDetails.getTaskAssignList().size(); i++) {
            if (taskDetails.getTaskAssignList().get(i).getTaskArriveUserUuid().equals(ACache.get(SzRspApplication.getContext()).getAsString("uuid"))) {
                taskDetails.getTaskAssignList().get(i).setTaskAssignStatus(new BigDecimal(1));
            }
        }
        if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000011")) {
            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                @Override
                public void btnConfirmClick(Dialog dialog) {
                    dialog.dismiss();
                    if (exceptionShipDialog == null) {
                        exceptionShipDialog = new ExceptionShipFinishDialog(TaskdetailsActivity.this);
                        exceptionShipDialog.setOnData(new ExceptionShipFinishDialog.OnGetData() {
                            @Override
                            public void onDataCallBack(String dec) {
                                presenter.finishTask(true, taskId, dec);
                            }
                        });
                    } else {
                        exceptionShipDialog.showDialog();
                    }
                }

                @Override
                public void btnCancelClick(Dialog dialog) {
                    dialog.dismiss();
                }
            });
        } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000003")) {
            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "已接收！是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                @Override
                public void btnConfirmClick(Dialog dialog) {
                    dialog.dismiss();
                    Intent intent = new Intent(TaskdetailsActivity.this, PSCFormAActivity.class);
                    intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
                    startActivity(intent);
                }

                @Override
                public void btnCancelClick(Dialog dialog) {
                    dialog.dismiss();
                }
            });
        } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RO_00000003")) {
            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "已接收！是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                @Override
                public void btnConfirmClick(Dialog dialog) {
                    dialog.dismiss();
                    Intent intent = new Intent(TaskdetailsActivity.this, CruiseWorkRecordActivity.class);
                    intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
                    startActivity(intent);
                }

                @Override
                public void btnCancelClick(Dialog dialog) {
                    dialog.dismiss();
                }
            });


        } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000006")) {
//                        Intent intent = new Intent(TaskdetailsActivity.this, KxjcActivity.class);
//                        intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
//                        intent.putExtra("shipNameCn", taskDetails.getTaskInfoDetailsList().get(0).getShipNameCn());
//                        intent.putExtra("shipNameEn", taskDetails.getTaskInfoDetailsList().get(0).getShipNameEn());
//                        startActivity(intent);

            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "已接收！是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                @Override
                public void btnConfirmClick(Dialog dialog) {
                    dialog.dismiss();
                    if (kaiXiangInspectDialog == null) {
                        kaiXiangInspectDialog = new KaiXiangInspectDialog(TaskdetailsActivity.this, taskDetails.getTaskInfo().getTaskId(), taskDetails.getTaskInfoDetailsList().get(0).getShipNameCn(), taskDetails.getTaskInfoDetailsList().get(0).getShipNameEn());
                    } else {
                        kaiXiangInspectDialog.showDialog();
                    }
                }

                @Override
                public void btnCancelClick(Dialog dialog) {
                    dialog.dismiss();
                }
            });

        } else {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < taskInfo.getTaskTypeList().size(); i++) {
                list.add(taskInfo.getTaskTypeList().get(i).getTaskTypeId());
            }
            Intent intent = new Intent(TaskdetailsActivity.this, TaskInspectionActivity.class);
            intent.putExtra("typeId", (Serializable) list);
            intent.putExtra("checkFormIds", taskDetails.getTaskInfo().getCheckFormIds());
            intent.putExtra("taskInfo", taskInfo);
            intent.putExtra("ShipInfo", (Serializable) taskDetails.getTaskInfoDetailsList());
            intent.putExtra("taskAssignList", (Serializable) taskDetails.getTaskAssignList());
            startActivity(intent);
        }


//        if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000011")) {
//
//            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "已接收！是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
//                @Override
//                public void btnConfirmClick(Dialog dialog) {
//                    dialog.dismiss();
//                    if (exceptionShipDialog == null) {
//                        exceptionShipDialog = new ExceptionShipFinishDialog(TaskdetailsActivity.this);
//                        exceptionShipDialog.setOnData(new ExceptionShipFinishDialog.OnGetData() {
//                            @Override
//                            public void onDataCallBack(String dec) {
//                                presenter.finishTask(true, taskId, dec);
//                            }
//                        });
//                    } else {
//                        exceptionShipDialog.showDialog();
//                    }
//                }
//
//                @Override
//                public void btnCancelClick(Dialog dialog) {
//                    dialog.dismiss();
//                }
//            });
//
//        } else if (taskDetails.getTaskInfo().getTaskTypeId().equals("RS_00000003")) {
//
//            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "已接收！是否执行任务并完成？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
//                @Override
//                public void btnConfirmClick(Dialog dialog) {
//                    dialog.dismiss();
//                    Intent intent = new Intent(TaskdetailsActivity.this, PSCFormAActivity.class);
//                    intent.putExtra("taskTypeId", taskDetails.getTaskInfo().getTaskId());
//                    startActivity(intent);
//                }
//
//                @Override
//                public void btnCancelClick(Dialog dialog) {
//                    dialog.dismiss();
//                }
//            });
//
//
//        } else {
//
//
//            DialogUtils.showDialog(TaskdetailsActivity.this, "温馨提示", "已接收！是否执行任务？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
//                @Override
//                public void btnConfirmClick(Dialog dialog) {
//                    dialog.dismiss();
//                    List<String> list = new ArrayList<>();
//                    for (int i = 0; i < taskInfo.getTaskTypeList().size(); i++) {
//                        list.add(taskInfo.getTaskTypeList().get(i).getTaskTypeId());
//                    }
//                    Intent intent = new Intent(TaskdetailsActivity.this, TaskInspectionActivity.class);
//                    intent.putExtra("typeId", (Serializable) list);
//                    intent.putExtra("checkFormIds", taskDetails.getTaskInfo().getCheckFormIds());
//                    intent.putExtra("taskInfo", taskInfo);
//                    intent.putExtra("ShipInfo", (Serializable) taskDetails.getTaskInfoDetailsList());
//                    intent.putExtra("taskAssignList", (Serializable) taskDetails.getTaskAssignList());
//                    startActivity(intent);
//                }
//
//                @Override
//                public void btnCancelClick(Dialog dialog) {
//                    dialog.dismiss();
//                    //finish();
//                }
//            });
//        }
    }

    @Override
    public void acceptFaile(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public String getTaskid() {
        return taskId;
    }

    private List<UserInfo> userInfoListFromDialog;
    private List<TaskResources> taskResourcesListFromDialog;

    @Override
    public void getUsersListFaile(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void setAisData(Ais aisData) {
        Intent intent = new Intent(TaskdetailsActivity.this, ShipMapActivity.class);
//        DataHolder.getInstance().save("AisData", aisData);
        intent.putExtra("AisData", aisData);
        startActivity(intent);
    }

    private List<String> Uuid;

    @Override
    public void setUsetDatas(List<UserInfo> usetDatas) {
        final ArrayList<SpinnearModel> spinnearModels = new ArrayList<>();
        for (int i = 0; i < usetDatas.size(); i++) {
            SpinnearModel spinnearModel = new SpinnearModel();
            spinnearModel.setParaName(usetDatas.get(i).getUserName());
            spinnearModel.setParaValue(usetDatas.get(i).getUuid());
            spinnearModels.add(spinnearModel);
        }
        if (spinnearModels != null && spinnearModels.size() > 0) {
            sp_zfry.setData(spinnearModels);//设置下拉菜单列表集合源
            sp_zfry.setHint("--选择执法人员--");
        }
        sp_zfry.setOnSpinnerConfirmClickListener(new OnSpinnerConfirmClickListener() {
            @Override
            public void OnConfirmed(ArrayList<Boolean> selecteIndexList) {
                StringBuffer str1 = new StringBuffer();
                Uuid = new ArrayList<>();
                for (int i = 0; i < selecteIndexList.size(); i++) {
                    if (selecteIndexList.get(i)) {//如果为true,则执行下面的代码
                        str1.append(spinnearModels.get(i).getParaName() + ":" + spinnearModels.get(i).getParaValue() + "\n");
                        Uuid.add(spinnearModels.get(i).getParaValue());
                    }
                }

                StringBuffer str = new StringBuffer();
                for (int i = 0; i < spinnearModels.size(); i++) {
                    str.append(spinnearModels.get(i).getParaName() + ":" + spinnearModels.get(i).isSelectedState() + "\n");
                }
            }
        });
    }


    //任务分发窗体
    private PopupWindow popupWindow;
    private View view;
    private int width;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;

    private void assignPop() {
        //初始化加载视图的xml文件
        view = View.inflate(this, R.layout.assigntask_pop, null);
        this.activityAlphaParams = getWindow().getAttributes();
        // 初始化popupwindow
        int magin = DimensUtils.dp2px(this, 40);
        width = DeviceUtils.getScreenSize(this)[0]; // 屏幕宽度（像素）
        popupWindow = new PopupWindow(view, width - magin, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.pyxc_popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        popupWindow.showAtLocation(btn_zhipai, Gravity.CENTER, 0, 0);
        if (popupWindow.isShowing()) {
            activityAlphaParams.alpha = 0.5f;
            getWindow().setAttributes(activityAlphaParams);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                activityAlphaParams.alpha = 1f;
                getWindow().setAttributes(activityAlphaParams);
            }
        });
        TextView tv_task_name = view.findViewById(R.id.task_name);
        TextView tv_task_listname = view.findViewById(R.id.task_listname);
        TextView tv_task_listname_id = view.findViewById(R.id.task_listname_id);
        TextView tv_task_type = view.findViewById(R.id.task_type);
        TextView tv_task_source = view.findViewById(R.id.task_source);
        TextView tv_task_time = view.findViewById(R.id.task_time);
        TextView tv_task_lowerhairunit = view.findViewById(R.id.task_lowerhairunit);
        TextView tv_task_executiveagency = view.findViewById(R.id.task_executiveagency);
        TextView tv_task_state = view.findViewById(R.id.task_state);
        TextView tv_task_executivedepartment = view.findViewById(R.id.task_executivedepartment);
        TextView tv_task_place = view.findViewById(R.id.task_place);
        TextView tv_task_member = view.findViewById(R.id.task_member);
        TextView tv_close = view.findViewById(R.id.tv_close);
        Button btn_fenfa = view.findViewById(R.id.btn_fenfa);
        tv_task_name.setText(taskInfo.getTaskName());
        String str = "";
        String str1 = "";
        if (taskInfo.getTaskTypeList() != null) {
            for (int i = 0; i < taskInfo.getTaskTypeList().size(); i++) {
                if (taskInfo.getTaskTypeList().size() == i + 1) {
                    str = str + taskInfo.getTaskTypeList().get(i).getTaskTypeTitleTemp();
                    str1 = str1 + taskInfo.getTaskTypeList().get(i).getTaskTypeId();
                } else {
                    str = str + taskInfo.getTaskTypeList().get(i).getTaskTypeTitleTemp() + "\n";
                    str1 = str1 + taskInfo.getTaskTypeList().get(i).getTaskTypeId() + "\n";
                }
            }
            tv_task_listname.setText(str);
            tv_task_listname_id.setText(str1);
        }
        tv_task_type.setText(taskInfo.getTaskTypeList().get(0).getTypeFatherName());
        tv_task_source.setText(taskDetails.getTaskAssignList().get(0).getTaskAssignOrgName());
        tv_task_time.setText(taskDetails.getTaskInfo().getTaskReleaseTime());
        tv_task_lowerhairunit.setText(taskDetails.getTaskAssignList().get(0).getTaskAssignOrgName());
        tv_task_executiveagency.setText(taskDetails.getTaskAssignList().get(0).getTaskArriveOrgName());
        tv_task_state.setText("待分发");
        tv_task_executivedepartment.setText(taskDetails.getTaskAssignList().get(0).getTaskArriveDeptName());
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < taskDetails.getTaskAssignList().size(); i++) {
            if (i < taskDetails.getTaskAssignList().size() - 1) {
                text.append(taskDetails.getTaskAssignList().get(i).getTaskArriveUserName());
                text.append(",");
            } else {
                text.append(taskDetails.getTaskAssignList().get(i).getTaskArriveUserName());
            }
        }
        String user = text.toString();
        tv_task_member.setText(user);
        btn_fenfa.setOnClickListener(this);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    //分发部门及执法人员窗体
    private int magin;
    private Dialog taskDialog;
    private Spinner sp_zfbm;
    private SpinnerViewMultiDialog sp_zfry;
    private SpinnerViewMultiDialog sp_zfc;
    private Button btn_qd;
    private TextView tv_zfbm;
    private TextView tv_close;
    private TextView tv_zfc;
    private String deptJson;

    private void showDailog() {
        width = DeviceUtils.getScreenSize(this)[0]; // 屏幕宽度（像素）
        magin = DimensUtils.dp2px(this, 50);
        taskDialog = new AlertDialog.Builder(this).create();
        taskDialog.setCancelable(true);
        taskDialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(this).inflate(R.layout.taskdialog_activity, null);
        sp_zfbm = view.findViewById(R.id.sp_zfbm);
        sp_zfry = view.findViewById(R.id.sp_zfry);
        sp_zfc = view.findViewById(R.id.sp_zfc);
        tv_zfbm = view.findViewById(R.id.tv_zfbm);
        tv_zfc = view.findViewById(R.id.tv_zfc);
        btn_qd = view.findViewById(R.id.btn_confirm);
        tv_close = view.findViewById(R.id.tv_close);
        taskDialog.show();
        taskDialog.setContentView(view);
        Window window = taskDialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String status = String.valueOf(taskDetails.getTaskInfo().getStatus());
        if (status.equals("1")) {
            tv_zfc.setVisibility(View.GONE);
            sp_zfc.setVisibility(View.GONE);
            if (taskDetails.getTaskAssignList().get(0).getTaskArriveDeptCode().equals("14010004")) {
                deptJson = "[{id: '14010005', name: '南澳海巡执法大队', pid: '1401', checked: ''},\n" +
                        "{id: '14010008', name: '大鹏海巡执法大队', pid: '1401', checked: ''}]";
            } else if (taskDetails.getTaskAssignList().get(0).getTaskArriveDeptCode().equals("14020005")) {
                deptJson = "[{id: '14020041', name: '深圳湾海巡执法大队', pid: '1402', checked: ''},\n" +
                        "{id: '14020006', name: '太子湾海巡执法大队', pid: '1402', checked: ''}]";
            } else if (taskDetails.getTaskAssignList().get(0).getTaskArriveDeptCode().equals("14030007")) {
                deptJson = "[{id: '14030006', name: '大鹏湾海巡执法大队', pid: '1403', checked: ''},\n" +
                        "{id: '14030041', name: '梅沙海巡执法大队', pid: '1403', checked: ''},\n" +
                        "{id: '14030005', name: '沙头角海巡执法大队', pid: '1403', checked: ''}]";
            } else if (taskDetails.getTaskAssignList().get(0).getTaskArriveDeptCode().equals("14040004")) {
                deptJson = "[{id: '14040005', name: '西堤海巡执法大队', pid: '1404', checked: ''},\n" +
                        "{id: '14040041', name: '机场海巡执法大队', pid: '1404', checked: ''}]";
            } else if (taskDetails.getTaskAssignList().get(0).getTaskArriveDeptCode().equals("14050007")) {
                deptJson = "[{id: '14050005', name: '内伶仃海巡执法大队', pid: '1405', checked: ''},\n" +
                        "{id: '14050006', name: '赤湾海巡执法大队', pid: '1405', checked: ''},\n" +
                        "{id: '14050041', name: '妈湾海巡执法大队', pid: '1405', checked: ''}]";
            } else if (taskDetails.getTaskAssignList().get(0).getTaskArriveDeptCode().equals("14060004")) {
                deptJson = "[{id: '14060041', name: '大铲海巡执法大队', pid: '1406', checked: ''},\n" +
                        "{id: '14060005', name: '前海湾海巡执法大队', pid: '1406', checked: ''}]";
            }
            List<TaskDeptData> taskDeptDataList = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(deptJson);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    TaskDeptData taskDeptData = JsonUtil.jsonToBean(jsonObject.toString(), TaskDeptData.class);
                    taskDeptDataList.add(taskDeptData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            final List<String> list_jsbm = new ArrayList<>();
            final List<String> list_jsbm_id = new ArrayList<>();
            list_jsbm.add("--请选择执法部门--");
            for (int i = 0; i < taskDeptDataList.size(); i++) {
                list_jsbm.add(taskDeptDataList.get(i).getName());
                list_jsbm_id.add(taskDeptDataList.get(i).getId());
            }
            ArrayAdapter<String> sp_Adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_1, list_jsbm);
            sp_Adapter1.setDropDownViewResource(R.layout.dropdown_style_1);
            sp_zfbm.setAdapter(sp_Adapter1);
            sp_zfbm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!list_jsbm.get(position).equals("--请选择执法部门--")) {
                        taskPresenter.getTaskUser(dialog, list_jsbm_id.get(position - 1));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (status.equals("3")) {
            tv_zfbm.setVisibility(View.GONE);
            sp_zfbm.setVisibility(View.GONE);
            taskPresenter.getUsersShipListByUserDept(true);
        }
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskId = taskInfo.getTaskId();
                String taskAssignUuid = "";
                String lawEnforcementShip = "";
                if (Uuid != null) {
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < Uuid.size(); i++) {
                        if (i < Uuid.size() - 1) {
                            text.append(Uuid.get(i));
                            text.append(",");
                        } else {
                            text.append(Uuid.get(i));
                        }
                    }
                    String str = text.toString();
                    taskAssignUuid = str;
                }
                if (list_zfc != null) {
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < list_zfc.size(); i++) {
                        if (i < list_zfc.size() - 1) {
                            text.append(list_zfc.get(i));
                            text.append(",");
                        } else {
                            text.append(list_zfc.get(i));
                        }
                    }
                    String str = text.toString();
                    lawEnforcementShip = str;
                }
                if (!taskAssignUuid.equals("")) {
                    taskPresenter.distributionTask(dialog, taskId, taskAssignUuid, lawEnforcementShip);
                } else {
                    ToastUtils.showToast(TaskdetailsActivity.this, "请选择执法人员！");
                }
            }
        });
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDialog.dismiss();
            }
        });
    }

    private List<String> list_zfc;

    @Override
    public void getUsersListSucceed(TaskPersonResouse taskPersonResouse) {

        List<UserInfo> userInfoList = taskPersonResouse.getUserInfoList();
        List<LawEnforcementShip> lawEnforcementShipList = taskPersonResouse.getLawEnforcementShipList();
        if (lawEnforcementShipList != null && lawEnforcementShipList.size() > 0) {
            tv_zfc.setVisibility(View.VISIBLE);
            sp_zfc.setVisibility(View.VISIBLE);
            final ArrayList<SpinnearModel> spinnearModels = new ArrayList<>();
            for (int i = 0; i < lawEnforcementShipList.size(); i++) {
                SpinnearModel spinnearModel = new SpinnearModel();
                spinnearModel.setParaName(lawEnforcementShipList.get(i).getShipNameCn());
                spinnearModel.setParaValue(lawEnforcementShipList.get(i).getMmsi());
                spinnearModels.add(spinnearModel);
            }
            if (spinnearModels != null && spinnearModels.size() > 0) {
                sp_zfc.setData(spinnearModels);//设置下拉菜单列表集合源
                sp_zfc.setHint("--选择执法船--");
            }
            sp_zfc.setOnSpinnerConfirmClickListener(new OnSpinnerConfirmClickListener() {
                @Override
                public void OnConfirmed(ArrayList<Boolean> selecteIndexList) {
                    StringBuffer str1 = new StringBuffer();
                    list_zfc = new ArrayList<>();
                    for (int i = 0; i < selecteIndexList.size(); i++) {
                        if (selecteIndexList.get(i)) {//如果为true,则执行下面的代码
                            str1.append(spinnearModels.get(i).getParaName() + ":" + spinnearModels.get(i).getParaValue() + "\n");
                            list_zfc.add(spinnearModels.get(i).getParaValue());
                        }
                    }

                    StringBuffer str = new StringBuffer();
                    for (int i = 0; i < spinnearModels.size(); i++) {
                        str.append(spinnearModels.get(i).getParaName() + ":" + spinnearModels.get(i).isSelectedState() + "\n");
                    }
                }
            });
        }
        final ArrayList<SpinnearModel> spinnearModels = new ArrayList<>();
        for (int i = 0; i < userInfoList.size(); i++) {
            SpinnearModel spinnearModel = new SpinnearModel();
            spinnearModel.setParaName(userInfoList.get(i).getUserName());
            spinnearModel.setParaValue(userInfoList.get(i).getUuid());
            spinnearModels.add(spinnearModel);
        }
        if (spinnearModels != null && spinnearModels.size() > 0) {
            sp_zfry.setData(spinnearModels);//设置下拉菜单列表集合源
            sp_zfry.setHint("--选择执法人员--");
        }
        sp_zfry.setOnSpinnerConfirmClickListener(new OnSpinnerConfirmClickListener() {
            @Override
            public void OnConfirmed(ArrayList<Boolean> selecteIndexList) {
                StringBuffer str1 = new StringBuffer();
                Uuid = new ArrayList<>();
                for (int i = 0; i < selecteIndexList.size(); i++) {
                    if (selecteIndexList.get(i)) {//如果为true,则执行下面的代码
                        str1.append(spinnearModels.get(i).getParaName() + ":" + spinnearModels.get(i).getParaValue() + "\n");
                        Uuid.add(spinnearModels.get(i).getParaValue());
                    }
                }

                StringBuffer str = new StringBuffer();
                for (int i = 0; i < spinnearModels.size(); i++) {
                    str.append(spinnearModels.get(i).getParaName() + ":" + spinnearModels.get(i).isSelectedState() + "\n");
                }
            }
        });
    }

    private String sub_msg;

    @Override
    public void OnSuccessTask(final String msg) {
        sub_msg = msg;
        myHandler.sendEmptyMessage(500);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void getResouseSucceed(TaskPersonResouse taskPersonResouse) {
        if(taskAcceptDialog==null){
            taskAcceptDialog=new TaskAcceptDialog(TaskdetailsActivity.this,taskPersonResouse, ACache.get(SzRspApplication.getContext()).getAsString("uuid"),taskId,true);
            taskAcceptDialog.showDialog();
            taskAcceptDialog.setOnData(new TaskAcceptDialog.OnGetData() {
                @Override
                public void onDataCallBack(List<UserInfo> userInfoListReturn, List<TaskResources> taskResourcesListReturn) {
                    taskResourcesListFromDialog=taskResourcesListReturn;
                    userInfoListFromDialog=userInfoListReturn;
                    //TODO
                    taskPresenter.acceptTask(true);
                }
            });
        }else {
            taskAcceptDialog.showDialog();
        }
    }

    @Override
    public void getResouseFaile(String msg) {
        ToastUtils.showToast(this, msg);
    }

    private static class MyHandler extends Handler {

        WeakReference<TaskdetailsActivity> taskdetailsActivityWeakReference;

        MyHandler(TaskdetailsActivity taskdetailsActivity) {
            this.taskdetailsActivityWeakReference = new WeakReference<>(taskdetailsActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final TaskdetailsActivity taskdetailsActivity = taskdetailsActivityWeakReference.get();
            int what = msg.what;
            if (what == 500) {
                ToastUtils.showToast(taskdetailsActivity, taskdetailsActivity.sub_msg);
                taskdetailsActivity.taskDialog.dismiss();
                taskdetailsActivity.popupWindow.dismiss();
                taskdetailsActivity.finish();
            }
        }
    }
}
