package com.winfo.szrsp.app.mvp.task.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.imp.TaskInspectionModel;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.task.model
 * @Filename: ITaskInspectionModel
 * @Author: lsj
 * @Date: 2017/12/9  21:45
 * @Description:
 * @Version:
 */
public interface ITaskInspectionModel {
    void getData(Dialog dialog, String taskTypeId, TaskInspectionModel.OnLoadTaskInspectionListener onLoadTaskInspectionListener);
}
