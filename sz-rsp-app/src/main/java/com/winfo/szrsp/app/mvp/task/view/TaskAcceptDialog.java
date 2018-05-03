package com.winfo.szrsp.app.mvp.task.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.task.LawEnforcementCar;
import com.winfo.szrsp.app.sdk.entity.task.LawEnforcementShip;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.task.TaskResources;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
import com.winfo.szrsp.app.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guan on 2017-12-13.
 */

public class TaskAcceptDialog {
    private View rejectView;
    private Dialog dialogReject;
    Button btnCancel,btnSubmit;
    Context mContext;
    private LinearLayout accept_data_ll_resouse_car;
    private LinearLayout accept_data_ll_resouse_ship;
    OnGetData mOnGetData;
//    private List<UserInfo> userInfoList;
//    private List<TaskResources>  taskResourcesList;
    private FlowLayout pop_accept_fl_resouse_car;
    private FlowLayout pop_accept_fl_resouse_ship;
    private FlowLayout pop_accept_fl_person;

    private String taskArriveUserUuid;
    private String taskId;

    private TaskPersonResouse taskPersonResouse;
    private boolean isShowCarShip;
    public  TaskAcceptDialog(Context context, TaskPersonResouse taskPersonResouse, String taskArriveUserUuid,String taskId,boolean isShowCarShip) {
        this.mContext=context;
        this.taskId=taskId;
        this.isShowCarShip=isShowCarShip;
        this.taskPersonResouse=taskPersonResouse;
        this.taskArriveUserUuid=taskArriveUserUuid;
        dialogReject = new AlertDialog.Builder(mContext).create();
        dialogReject.setCancelable(true);//
        dialogReject.setCanceledOnTouchOutside(false);//
        rejectView = LayoutInflater.from(mContext).inflate(R.layout.pop_accept_data, null);
        dialogReject.show();
        dialogReject.setContentView(rejectView);
        Window window = dialogReject.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width =ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initData();
        initEvent();
    }


    private void initView() {

        btnCancel= rejectView.findViewById(R.id.btnCancel);
        btnSubmit= rejectView.findViewById(R.id.btnSubmit);
        accept_data_ll_resouse_car=rejectView.findViewById(R.id.accept_data_ll_resouse_car);
        accept_data_ll_resouse_ship=rejectView.findViewById(R.id.accept_data_ll_resouse_ship);
        pop_accept_fl_resouse_car=rejectView.findViewById(R.id.pop_accept_fl_resouse_car);
        pop_accept_fl_resouse_ship=rejectView.findViewById(R.id.pop_accept_fl_resouse_ship);

        pop_accept_fl_person=rejectView.findViewById(R.id.pop_accept_fl_person);
    }
    private void initData() {

        if(isShowCarShip){
            if(taskPersonResouse.getLawEnforcementCarList().size()==0){
                accept_data_ll_resouse_car.setVisibility(View.GONE);
            }else {
                List<LawEnforcementCar> lawEnforcementCarList= taskPersonResouse.getLawEnforcementCarList();
                for(int i=0;i<lawEnforcementCarList.size();i++){
                    CheckBox checkBox =new CheckBox(mContext) ;
                    checkBox.setText(lawEnforcementCarList.get(i).getBrand());
                    checkBox.setPadding(0,0,80,0);
                    pop_accept_fl_resouse_car.addView(checkBox);
                }

            }

            if(taskPersonResouse.getLawEnforcementShipList().size()==0){
                accept_data_ll_resouse_ship.setVisibility(View.GONE);
            }else {
                List<LawEnforcementShip> lawEnforcementShipList= taskPersonResouse.getLawEnforcementShipList();
                for(int i=0;i<lawEnforcementShipList.size();i++){
                    CheckBox checkBox =new CheckBox(mContext) ;

                    if(lawEnforcementShipList.get(i).getShipNameCn().equals("")){
                        checkBox.setText(lawEnforcementShipList.get(i).getShipNameEn());
                    }else {
                        checkBox.setText(lawEnforcementShipList.get(i).getShipNameCn());
                    }
                    checkBox.setPadding(0,0,80,0);
                    pop_accept_fl_resouse_ship.addView(checkBox);
                }

            }
        }else {
            accept_data_ll_resouse_car.setVisibility(View.GONE);
            accept_data_ll_resouse_ship.setVisibility(View.GONE);
        }







        if(taskPersonResouse.getUserInfoList().size()>0){

            for(int i=0;i<taskPersonResouse.getUserInfoList().size();i++){
                CheckBox checkBox =new CheckBox(mContext) ;
                checkBox.setText(taskPersonResouse.getUserInfoList().get(i).getUserName());
                checkBox.setPadding(0,0,80,0);
                if(taskArriveUserUuid.equals(taskPersonResouse.getUserInfoList().get(i).getUuid())){
                    checkBox.setChecked(true);
                    checkBox.setClickable(false);
                }
                pop_accept_fl_person.addView(checkBox);
            }
        }
    }
    private List<UserInfo> userInfoListReturn;
    private List<TaskResources>  taskResourcesListReturn;
    private void initEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogReject.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnGetData!=null){
                    taskResourcesListReturn=new ArrayList<>();
                    userInfoListReturn=new ArrayList<>();

                    if(accept_data_ll_resouse_car.getVisibility()==View.VISIBLE){

                        int num = pop_accept_fl_resouse_car.getChildCount();
                        for (int i = 0; i < num; i++) {
                            View view = pop_accept_fl_resouse_car.getChildAt(i);
                            if (view instanceof CheckBox) {
                                CheckBox checkBox = (CheckBox) view;
                                if(checkBox.isChecked()){
                                  LawEnforcementCar lawEnforcementCar=  taskPersonResouse.getLawEnforcementCarList().get(i);
                                    TaskResources taskResources=new TaskResources();
                                    taskResources.setEnforceVehicleName(lawEnforcementCar.getBrand());
                                    taskResources.setTaskId(taskId);
                                    taskResources.setEnforceVehicleLicense(lawEnforcementCar.getPlateNum());
                                    taskResourcesListReturn.add(taskResources);
                                }
                            }
                        }
                    }

                    if(accept_data_ll_resouse_ship.getVisibility()==View.VISIBLE){

                        int num_ship = pop_accept_fl_resouse_ship.getChildCount();
                        for (int i = 0; i < num_ship; i++) {
                            View view = pop_accept_fl_resouse_ship.getChildAt(i);
                            if (view instanceof CheckBox) {
                                CheckBox checkBox = (CheckBox) view;
                                if(checkBox.isChecked()){
                                    LawEnforcementShip lawEnforcementShip=  taskPersonResouse.getLawEnforcementShipList().get(i);
                                    TaskResources taskResources=new TaskResources();
                                    taskResources.setTaskId(taskId);
                                    taskResources.setPatrolBoatMmsi(lawEnforcementShip.getMmsi());
                                    taskResources.setPatrolBoatName(lawEnforcementShip.getShipNameCn());
                                    taskResourcesListReturn.add(taskResources);
                                }
                            }
                        }


                    }

                    int count = pop_accept_fl_person.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View view = pop_accept_fl_person.getChildAt(i);
                        if (view instanceof CheckBox) {
                            CheckBox checkBox = (CheckBox) view;
                            if(checkBox.isChecked()){
                                userInfoListReturn.add(taskPersonResouse.getUserInfoList().get(i));
                            }
                        }
                    }
                    mOnGetData.onDataCallBack(userInfoListReturn,taskResourcesListReturn);
                }


            }
        });
    }
    public void showDialog(){
        dialogReject.show();
    }
    public void dismiss(){
        dialogReject.dismiss();
    }

    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }
    // 数据接口抽象方法
    public interface OnGetData {
        abstract void onDataCallBack(List<UserInfo> userInfoListReturn,List<TaskResources>  taskResourcesListReturn);
    }
}
