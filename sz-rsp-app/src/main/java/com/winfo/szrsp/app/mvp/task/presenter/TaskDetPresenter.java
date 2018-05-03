package com.winfo.szrsp.app.mvp.task.presenter;

import com.winfo.szrsp.app.mvp.task.model.ITaskDetModel;
import com.winfo.szrsp.app.mvp.task.model.imp.TaskDetModel;
import com.winfo.szrsp.app.mvp.task.view.ITaskDetActivity;
import com.winfo.szrsp.app.sdk.entity.task.TaskDetails;

/**
 * Created by Guan on 2017-12-10.
 */

public class TaskDetPresenter {
    private ITaskDetActivity activity;
    private ITaskDetModel model;

    public TaskDetPresenter(ITaskDetActivity activity){
        this.activity = activity;
        this.model = new TaskDetModel();
    }

    public void getTaskDetData(String taskId, boolean bol) {
        model.getTaskDetData(activity.getDailog(),taskId,bol, new TaskDetModel.OnLoadGetTaskDetListener() {
            @Override
            public void OnSuccess(TaskDetails data) {
                activity.setTaskDetData(data);
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
    //完成任务
    public void finishTask(boolean bol,String taskId,String dec){
        model.finishTask(activity.getDailog(), bol, taskId,dec, new TaskDetModel.OnLoadFinishTaskDetListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.finishSucceedDet(msg);
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
}
