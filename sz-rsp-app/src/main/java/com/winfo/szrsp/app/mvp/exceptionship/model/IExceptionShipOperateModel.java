package com.winfo.szrsp.app.mvp.exceptionship.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;

/**
 * Created by Guan on 2017-12-21.
 */

public interface IExceptionShipOperateModel {
    //添加异常船舶
    void addExceptionShip(Dialog dialog, Ais aisData, String dec,boolean bol, ExceptionShipOperateModel.OnExceptionShipAddListener onExceptionShipAddListener);
    //删除异常船舶
    void deleteExceptionShip(Dialog dialog,String mmsi, boolean bol, ExceptionShipOperateModel.OnExceptionShipDeleteListener onExceptionShipDeleteListener);
    //查询是否是异常船舶
    void queryExceptionShip(Dialog dialog,String mmsi, boolean bol, ExceptionShipOperateModel.OnExceptionShipQueryListener onExceptionShipQueryListener);
    void getAisDtat(Dialog dialog, String mmsi, ExceptionShipOperateModel.OnLoadAisDataExceptionListener onLoadAisDataExceptionListener);
}
