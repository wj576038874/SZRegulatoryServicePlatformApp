package com.winfo.szrsp.app.mvp.table.fragmentlist.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;

/**
 * Created by ChengQi on 2017/12/12.
 */

public interface IChecklistModel {
    void getTaskDefectItemData(String taskTypeId, Dialog dialog, ChecklistModel.OnLoadTaskDefectItemDataListener onLoadTaskDefectItemDataListener);

    void getUsersListByUserDept(Dialog dialog, boolean b, ChecklistModel.OnLoadUsersListByUserDeptListener onLoadUsersListByUserDeptListener);

    void getDefectCodeData(ChecklistModel.OnLoadDefectCodeListener onLoadDefectCodeListener);

    void finishTask(Dialog dialog , boolean bol, TaskFinishSubData taskFinishSubData,String siteCaptain,String fileCaptain,String fileInspector, ChecklistModel.FinishTaskListener finishTaskListener);

    void getInspectorInfo(String uuid,Dialog dialog, ChecklistModel.OnLoadInspectorsListener listener);

}
