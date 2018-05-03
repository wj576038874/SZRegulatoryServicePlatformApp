package com.winfo.szrsp.app.mvp.task.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.imp.TaskDetModel;

/**
 * Created by Guan on 2017-12-10.
 */

public interface ITaskDetModel {
    void getTaskDetData(Dialog dialog, String taskId, boolean bol, TaskDetModel.OnLoadGetTaskDetListener onLoadGetTaskDetListener);
    void finishTask(Dialog dialog , boolean bol, String taskId,String dec, TaskDetModel.OnLoadFinishTaskDetListener onLoadFinishTaskDetListener);
}

