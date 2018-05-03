package com.winfo.szrsp.app.mvp.task.presenter;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.task.model.IAassigTaskModel;
import com.winfo.szrsp.app.mvp.task.model.imp.AssignTaskModel;
import com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.IAssignTaskView;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType2;
import com.winfo.szrsp.app.sdk.entity.task.ReleaseTaskModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskShipData;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;

import java.util.List;


/**
 * Created by winfo053 on 2018/3/7.
 */

public class AssignTaskPresenter extends BaseMvpPresenter<IAssignTaskView> {
    private IAassigTaskModel assignTaskModel;

    public AssignTaskPresenter() {
        assignTaskModel = new AssignTaskModel();
    }

//    public void getTaskType(Dialog dialog,boolean isShowDialog) {
//        if (mView == null) return;
//        assignTaskModel.getTaskType(dialog, isShowDialog, new AssignTaskModel.OnLoadTaskTypeListener() {
//            @Override
//            public void onSueecss(List<AllTaskType> allTaskTypeList) {
//                mView.setAllTaskType(allTaskTypeList);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.onLoadFaild(msg);
//            }
//
//            @Override
//            public void OnLoginExpired(String msg) {
//                mView.loginExpired(msg);
//            }
//        });
//    }

        public void getTaskType(Dialog dialog,boolean isShowDialog) {
        if (mView == null) return;
        assignTaskModel.getTaskType(dialog, isShowDialog, new AssignTaskModel.OnLoadTaskTypeListener() {
            @Override
            public void onSueecss(AllTaskType2 allTaskType) {
                mView.setAllTaskType(allTaskType);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadFaild(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }


    public void getTaskInspectionAll(Dialog dialog,boolean isShowDialog) {
        if (mView == null) return;
        assignTaskModel.getTaskInspectionAll(dialog, isShowDialog, new AssignTaskModel.OnLoadInspectionAllListener() {
            @Override
            public void onSueecss(List<TaskInspectionItemData> taskInspectionItemData) {
                mView.setTaskInspectionAll(taskInspectionItemData);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void getTaskInspectionTaskTypeId(Dialog dialog,boolean isShowDialog,String  taskTypeId) {
        if (mView == null) return;
        assignTaskModel.getTaskInspectionTaskTypeId(dialog, isShowDialog, taskTypeId,new AssignTaskModel.OnLoadTaskInspectionTaskTypeIdListener() {
            @Override
            public void onSueecss(List<TaskInspectionItemData> taskInspectionItemData) {
                mView.setTaskInspectionTaskTypeId(taskInspectionItemData);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    //获取接收人数据
    public void getDeptUser(Dialog dialog,String deptCode){
        if (mView == null) return;
        assignTaskModel.getDeptUser(dialog, deptCode, new AssignTaskModel.OnLoadDeptUserListener() {
            @Override
            public void onSuccess(List<UserInfo> userInfos) {
                mView.setdeptUser(userInfos);
            }

            @Override
            public void onFaile(String msg) {
                mView.onLoadFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    //选船数据
    public void getTaskShipDataByKey(Dialog dialog,String key){
        if (mView == null) return;
        assignTaskModel.getTaskShipDataByKey(dialog, key, new AssignTaskModel.OnLoadTaskShipDataListener() {
            @Override
            public void OnSuccess(List<TaskShipData> taskShipData) {
                mView.setTaskShipData(taskShipData);
            }

            @Override
            public void OnFaile(String msg) {
                mView.onLoadFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    //选船数据
    public void getTaskShipDataByMMSI(Dialog dialog,String key){
        if (mView == null) return;
        assignTaskModel.getTaskShipDataByMMSI(dialog, key, new AssignTaskModel.OnLoadTaskShipDataListener() {
            @Override
            public void OnSuccess(List<TaskShipData> taskShipData) {
                mView.setTaskShipData(taskShipData);
            }

            @Override
            public void OnFaile(String msg) {
                mView.onLoadFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    //发布任务
    public void releaseTask(Dialog dialog,ReleaseTaskModel releaseTaskModel){
        assignTaskModel.releaseTask(dialog, releaseTaskModel, new AssignTaskModel.ReleaseTaskListener() {
            @Override
            public void OnSuccess(String msg) {
                mView.onLoadFaild(msg);
            }

            @Override
            public void OnFaile(String msg) {
                mView.onLoadFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest() {
        assignTaskModel.onRequestCancel();
    }
}
