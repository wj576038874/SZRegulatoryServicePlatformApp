package com.winfo.szrsp.app.mvp.task.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.task.TaskListData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.view
 * @Filename: IAllTableActivity
 * @Author: lsj
 * @Date: 2017/12/7  10:09
 * @Description:
 * @Version:
 */
public interface ITaskListActivity {

    void showOnFaile(String msg);
    void notFound();
    Dialog getDailog();
    void setTaskListData(TaskListData data);


    void loginExpired(String msg);
}
