package com.winfo.szrsp.app.mvp.task.presenter;

import com.winfo.szrsp.app.mvp.task.model.ITaskInspectionModel;
import com.winfo.szrsp.app.mvp.task.model.imp.TaskInspectionModel;
import com.winfo.szrsp.app.mvp.task.view.ITaskInspectionActivity;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.task.presenter
 * @Filename: TaskInspectionPresenter
 * @Author: lsj
 * @Date: 2017/12/9  21:57
 * @Description:
 * @Version:
 */
public class TaskInspectionPresenter {
    private ITaskInspectionActivity activity;
    private ITaskInspectionModel model;

    public TaskInspectionPresenter(ITaskInspectionActivity activity){
        this.activity = activity;
        model = new TaskInspectionModel();
    }

    public void GetTaskItemData(String taskTypeId){
        model.getData(activity.getDialog(), taskTypeId, new TaskInspectionModel.OnLoadTaskInspectionListener() {
            @Override
            public void OnSuccess(List<TaskInspectionItemData> taskInspectionItemData) {
                activity.setData(taskInspectionItemData);
            }

            @Override
            public void OnFaile(String msg) {
                activity.OnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
}
