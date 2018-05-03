package com.winfo.szrsp.app.mvp.table.xhgzjl.model;

import android.app.Dialog;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;

/**
 * Created by HoBo on 2018/4/13.
 */

public interface ICruiseWorkRecordModel {
    void getInspectionAll(Dialog dialog, boolean isShowDialog, CruiseWorkRecordModel.OnLoadInspectionAllListener onLoadInspectionAllListener) ;
    void subData(Dialog dialog, CruiseWorkData data, CruiseWorkRecordModel.OnSubDataListenner onSubDataListenner);

    void findDataByPrimaryKey(Dialog dialog, String id, CruiseWorkRecordModel.OnFindDataByPrimaryKeyListenner onFindDataByPrimaryKeyListenner);

    void findShip(Dialog dialog, CruiseWorkRecordModel.OnFindShipListenner onFindShipListenner);

    void getTaskInspectionByName(Dialog dialog, String name, CruiseWorkRecordModel.OnLoadInspectionByNameListenner onLoadInspectionByNameListenner);
}
