package com.winfo.szrsp.app.mvp.shipinfo.model.impl;


import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipSelectionRiskModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.param.Params;
import com.winfo.szrsp.app.mvp.shipinfo.model.param.ParamsShip;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRiskInfo;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import rx.Observable;
import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model.impl
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipSelectionRiskModel.java
 * Date: 2018/1/13 16:06
 * Description:
 */

public class ShipSelectionRiskModel extends BaseModel<ShipDataService> implements IShipSelectionRiskModel {

    public ShipSelectionRiskModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    @Override
    public void getShipSelectionRisk(String mmsi, String ywcm, final OnLoadShipSelectionRiskListener onLoadShipSelectionRiskListener) {
        ParamsShip paramsShip = new ParamsShip();
//        paramsShip.setMmsi(mmsi);
        paramsShip.setShip_name_en(ywcm);
        Params params = new Params();
        params.setArg0(paramsShip);
        String jsontr = FastJsonUtil.beanToJsonStr(params);
        Observable<ShipRiskInfo> observable = mService.getShipSelectionRisk("SZZC", "shipRiskInfo", jsontr);
        Subscriber<ShipRiskInfo> subscriber = new DialogSubscriber<ShipRiskInfo>(null) {
            @Override
            public void onSuccess(ShipRiskInfo responseResult) {
                if (responseResult != null) {
                    if (responseResult.getShip_no() == null && responseResult.getShip_name_en() == null
                            && responseResult.getShip_id() == null) {
                        onLoadShipSelectionRiskListener.onFailure("没有查询到数据");
                    } else {
                        onLoadShipSelectionRiskListener.onSuccess(responseResult);
                    }

                } else {
                    onLoadShipSelectionRiskListener.onFailure("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadShipSelectionRiskListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnLoadShipSelectionRiskListener {
        void onSuccess(ShipRiskInfo shipRiskInfo);

        void onFailure(String msg);
    }
}
