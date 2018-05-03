package com.winfo.szrsp.app.mvp.table.xhtj.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj.model
 * @Filename: ICruiseStatisticsModel
 * @Author: lsj
 * @Date: 2018/1/23  13:44
 * @Description:
 * @Version:
 */
public interface ICruiseStatisticsModel {
    void addData(Dialog dialog, CtCruiseStatisticsObject ctCruiseStatisticsObject, CruiseStatisticsModel.OnSubCruiseStatisticsListener onSubCruiseStatisticsListener);
    void findData(Dialog dialog, String id, CruiseStatisticsModel.OnLoadCruiseStatisticsDataListener onLoadCruiseStatisticsDataListener);
}
