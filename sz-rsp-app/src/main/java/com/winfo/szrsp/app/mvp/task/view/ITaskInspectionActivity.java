package com.winfo.szrsp.app.mvp.task.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table
 * @Filename: ITaskInspectionActivity
 * @Author: lsj
 * @Date: 2017/12/9  21:35
 * @Description:
 * @Version:
 */
public  interface ITaskInspectionActivity {
    Dialog getDialog();
    void setData(List<TaskInspectionItemData> taskInspectionItemData);
    void OnFaile(String msg);

    void loginExpired(String msg);
}
