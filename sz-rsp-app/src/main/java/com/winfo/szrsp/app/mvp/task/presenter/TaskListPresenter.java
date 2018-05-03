package com.winfo.szrsp.app.mvp.task.presenter;

import com.winfo.szrsp.app.mvp.task.model.ITaskListModel;
import com.winfo.szrsp.app.mvp.task.model.imp.TaskListModel;
import com.winfo.szrsp.app.mvp.task.view.ITaskListActivity;
import com.winfo.szrsp.app.sdk.entity.task.TaskListData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.presenter
 * @Filename: FindAllTablePresenter
 * @Author: lsj
 * @Date: 2017/12/7  10:08
 * @Description:
 * @Version:
 */
public class TaskListPresenter {
    private ITaskListActivity activity;
    private ITaskListModel model;

    public TaskListPresenter(ITaskListActivity activity){
        this.activity = activity;
        this.model = new TaskListModel();
    }

    public void getTaskListData(String pageNum,String pageSize,String taskAssignStatus,boolean bol){
        model.getTaskListData(activity.getDailog(), pageNum, pageSize,taskAssignStatus,bol, new TaskListModel.OnLoadGetTaskListListener() {
            @Override
            public void OnSuccess(TaskListData data) {
                activity.setTaskListData(data);
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
}
