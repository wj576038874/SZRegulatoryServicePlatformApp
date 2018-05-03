package com.winfo.szrsp.app.mvp.exceptionship.presenter;

import com.winfo.szrsp.app.mvp.exceptionship.model.ExceptionShipOperateModel;
import com.winfo.szrsp.app.mvp.exceptionship.model.IExceptionShipOperateModel;
import com.winfo.szrsp.app.mvp.exceptionship.view.IExceptionShipOperateView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;

/**
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipOperatePresenter {
    private IExceptionShipOperateView activity;
    private IExceptionShipOperateModel model;
    public ExceptionShipOperatePresenter(IExceptionShipOperateView activity){
        this.activity = activity;
        this.model = new ExceptionShipOperateModel();
    }

    //addExceptionShip deleteExceptionShip queryExceptionShip

    public void addExceptionShip(String dec,Ais aisData, boolean bol){
        model.addExceptionShip(activity.getDialog(), aisData, dec, bol, new ExceptionShipOperateModel.OnExceptionShipAddListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.addSucceed(msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.addFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void deleteExceptionShip(String mmsi, boolean bol){
        model.deleteExceptionShip(activity.getDialog(), mmsi, bol, new ExceptionShipOperateModel.OnExceptionShipDeleteListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.deleteSucceed(msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.deleteFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void queryExceptionShip(String mmsi, boolean bol){
        model.queryExceptionShip(activity.getDialog(), mmsi, bol, new ExceptionShipOperateModel.OnExceptionShipQueryListener() {
            @Override
            public void OnSuccess(ExceptionShip exceptionShip) {
                activity.setIsOrNotExceptionShip(exceptionShip);
            }

            @Override
            public void OnFaile(String msg) {
                activity.queryFaild(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void getAisDtat(String mmsi){
        model.getAisDtat(activity.getDialog(), mmsi, new ExceptionShipOperateModel.OnLoadAisDataExceptionListener() {
            @Override
            public void OnSuccess(Ais ais) {
                activity.setAisData(ais);
            }

            @Override
            public void OnFaile(String msg) {
                activity.searchAsiFaild(msg);
            }
        });
    }
}
