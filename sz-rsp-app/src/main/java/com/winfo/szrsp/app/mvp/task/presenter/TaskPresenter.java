package com.winfo.szrsp.app.mvp.task.presenter;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.ITaskModel;
import com.winfo.szrsp.app.mvp.task.model.imp.TaskModel;
import com.winfo.szrsp.app.mvp.task.view.ITaskDetActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;

import java.util.List;

/**
 * Created by Guan on 2017-12-12.
 */

public class TaskPresenter {

    private ITaskDetActivity activity;
    private ITaskModel model;

    public TaskPresenter(ITaskDetActivity activity){
        this.activity = activity;
        this.model = new TaskModel();
    }

    public void rejectTask(boolean bol,String reason) {
        model.rejectTask(activity.getDailog(), bol, activity.getTaskid(), reason, new TaskModel.OnLoadRejectTaskListListener() {
            @Override
            public void OnSuccess(String data) {
                activity.rejectSucceed(data);
            }

            @Override
            public void OnFaile(String msg) {
                activity.rejectFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public  void finishTask(boolean bol){
        model.finishTask(activity.getDailog(), bol, activity.getTaskFinishSubData(), new TaskModel.OnLoadFinshTaskListListener() {
            @Override
            public void OnSuccess(TaskFinishData data,String msg) {
                activity.finishSucceed(data,msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.finishFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public  void acceptTask(boolean bol){
        model.acceptTask(activity.getDailog(), bol, activity.getTaskAcceptData(), new TaskModel.OnLoadAcceptTaskListListener() {
            @Override
            public void OnSuccess(String data) {
                activity.acceptSucceed(data);
            }

            @Override
            public void OnFaile(String msg) {
                activity.acceptFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

//    public  void acceptTask(String taskId,boolean bol){
//        model.acceptTask(activity.getDailog(), bol, taskId, new TaskModel.OnLoadAcceptTaskListListener() {
//            @Override
//            public void OnSuccess(String data) {
//                activity.acceptSucceed(data);
//            }
//
//            @Override
//            public void OnFaile(String msg) {
//                activity.acceptFaile(msg);
//            }
//            @Override
//            public void OnLoginExpired(String msg) {
//                activity.loginExpired(msg);
//            }
//        });
//    }



    public  void getUsersShipListByUserDept(boolean bol){
        model.getUsersListByUserDept(activity.getDailog(), bol, new TaskModel.OnLoadUsersListByUserDeptListener() {
            @Override
            public void OnSuccess(TaskPersonResouse taskPersonResouse) {
                activity.getUsersListSucceed(taskPersonResouse);
            }

            @Override
            public void OnFaile(String msg) {
                activity.getUsersListFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void getAisData(String mmsi){
        model.getAisDtat(activity.getDailog(), mmsi, new TaskModel.OnLoadAisDataTaskListener() {
            @Override
            public void OnSuccess(Ais ais) {
                activity.setAisData(ais);
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile(msg);
            }
        });
    }

    public void getTaskUser(Dialog dialog,String deptcode){
        model.getUser(dialog, deptcode, new TaskModel.OnLoadUserListener() {
            @Override
            public void OnSuccess(List<UserInfo> userInfos) {
                activity.setUsetDatas(userInfos);
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void distributionTask(Dialog dialog, String taskId, String taskAssignUuid, String lawEnforcementShip){
        model.distributionTask(dialog, taskId, taskAssignUuid, lawEnforcementShip, new TaskModel.OnDistributionTaskListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.OnSuccessTask(msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
    public void getResouseByUserDept(boolean b) {
        model.getResouseByUserDept(activity.getDailog(), b, new TaskModel.OnLoadUsersListByUserDeptListener() {
            @Override
            public void OnSuccess(TaskPersonResouse taskPersonResouse) {
                activity.getResouseSucceed(taskPersonResouse);
            }

            @Override
            public void OnFaile(String msg) {
                activity.getResouseFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
}
