package com.winfo.szrsp.app.mvp.table.xhtj.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj.view
 * @Filename: ICruiseStatisticsActivity
 * @Author: lsj
 * @Date: 2018/1/23  13:41
 * @Description:
 * @Version:
 */
public interface ICruiseStatisticsActivity {
    Dialog getDialog();
    void onSuccess(String msg);
    void onFaile(String msg);
    void setData(CtCruiseStatisticsObject ctCruiseStatisticsObject);

    void loginExpired(String msg);
}
