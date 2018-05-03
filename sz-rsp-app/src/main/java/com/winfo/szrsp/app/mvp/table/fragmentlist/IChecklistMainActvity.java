package com.winfo.szrsp.app.mvp.table.fragmentlist;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.fragmentlist
 * @Filename: IChecklistMainActvity
 * @Author: lsj
 * @Date: 2017/12/19  13:10
 * @Description:
 * @Version:
 */
public interface IChecklistMainActvity {
    Dialog getDiaole();

    void setTaskDefectItemData(List<TaskDefectItemData> taskDefectItemData);

    void OnFaile(String msg);

    void setUsersListSucceed(TaskPersonResouse taskPersonResouse);

    void setDefectCodeData(List<DefectCode> defectCodes);

    TaskFinishSubData getTaskFinishSubData ();

    void finishSucceed(TaskFinishData data, String msg);

    void loginExpired(String msg);

    void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList);
}
