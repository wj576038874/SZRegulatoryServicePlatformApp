package com.winfo.szrsp.app.mvp.task.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.imp.AssignTaskModel;
import com.winfo.szrsp.app.sdk.entity.task.ReleaseTaskModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * Created by winfo053 on 2018/3/7.
 */

public interface IAassigTaskModel extends RequestCancelListener {
    void getTaskType(Dialog dialog, boolean bol,AssignTaskModel.OnLoadTaskTypeListener onLoadTaskTypeListener);
    //获取接收人数据
    void getDeptUser(Dialog dialog, String deptCode, AssignTaskModel.OnLoadDeptUserListener onLoadDeptUserListener);
    void getTaskInspectionAll(Dialog dialog, boolean bol,AssignTaskModel.OnLoadInspectionAllListener onLoadInspectionAllListener);
    void getTaskInspectionTaskTypeId(Dialog dialog, boolean bol,String  taskTypeId,AssignTaskModel.OnLoadTaskInspectionTaskTypeIdListener onLoadTaskInspectionTaskTypeIdListener );

    //获取任务选船数据
    void getTaskShipDataByKey(Dialog dialog, String key, AssignTaskModel.OnLoadTaskShipDataListener onLoadTaskShipDataListener);
    void getTaskShipDataByMMSI(Dialog dialog, String key, AssignTaskModel.OnLoadTaskShipDataListener onLoadTaskShipDataListener);
    //发布任务
    void releaseTask(Dialog dialog, ReleaseTaskModel releaseTaskModel, AssignTaskModel.ReleaseTaskListener releaseTaskListener);
}
