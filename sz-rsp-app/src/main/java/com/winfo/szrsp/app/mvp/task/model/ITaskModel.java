package com.winfo.szrsp.app.mvp.task.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.imp.TaskModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskAcceptData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;

/**
 * Created by Guan on 2017-12-12.
 */

public interface ITaskModel {
    void acceptTask(Dialog dialog, boolean isShowLoading , TaskAcceptData taskAcceptData, TaskModel.OnLoadAcceptTaskListListener onLoadAcceptTaskListListener);
//    void acceptTask(Dialog dialog, boolean isShowLoading , String taskID, TaskModel.OnLoadAcceptTaskListListener onLoadAcceptTaskListListener);


    void finishTask(Dialog dialog, boolean isShowLoading, TaskFinishSubData taskFinishSubData, TaskModel.OnLoadFinshTaskListListener onLoadFinshTaskListListener);
    void rejectTask(Dialog dialog,boolean isShowLoading, String taskId, String reason, TaskModel.OnLoadRejectTaskListListener onLoadRejectTaskListListener);
    void getUsersListByUserDept(Dialog dialog, boolean isShowLoading , TaskModel.OnLoadUsersListByUserDeptListener onLoadUsersListByUserDeptListener);
    void getAisDtat(Dialog dialog, String mmsi, TaskModel.OnLoadAisDataTaskListener onLoadAisDataTaskListener);
    void getUser(Dialog dialog, String deptcode, TaskModel.OnLoadUserListener onLoadUserListener);
    void distributionTask(Dialog dialog, String taskId, String taskAssignUuid, String lawEnforcementShip, TaskModel.OnDistributionTaskListener onDistributionTaskListener);

    void getResouseByUserDept(Dialog dialog, boolean isShowLoading , TaskModel.OnLoadUsersListByUserDeptListener onLoadUsersListByUserDeptListener);

}
