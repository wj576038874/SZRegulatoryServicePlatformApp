package com.winfo.szrsp.app.mvp.task.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.imp.TaskListModel;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.model
 * @Filename: IFindAllTableModel
 * @Author: lsj
 * @Date: 2017/12/7  9:43
 * @Description:
 * @Version:
 */
public interface ITaskListModel {
    //获取任务列表数据
    void getTaskListData(Dialog dialog, String pageNum, String pageSize, String taskAssignStatus, boolean bol, TaskListModel.OnLoadGetTaskListListener onLoadGetTaskListListener);


}
