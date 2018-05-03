package com.winfo.szrsp.app.mvp.shipinfo.model.impl;

import android.content.Context;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipRegisterInfoModel;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model.impl
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipRegisterInfoModel.java
 * Date: 2017/12/7 16:08
 * Description:
 */

public class ShipRegisterInfoModel extends BaseModel<ShipDataService> implements IShipRegisterInfoModel {

    public ShipRegisterInfoModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getShipRegisterInfo(String mmsi, String ywcm, final OnLoadShipRegisterInfoListener onLoadShipRegisterInfoListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ywcm", ywcm);
        paramMap.put("mmsi", mmsi);
        String params = FastJsonUtil.mapToJsonStr(paramMap);
        Observable<ResponseResult<List<ShipRegistrationInfo>>> observable = mService.getShipRegistrationInformationByConditions(params);
        Subscriber<ResponseResult<List<ShipRegistrationInfo>>> subscriber = new DialogSubscriber<ResponseResult<List<ShipRegistrationInfo>>>(null) {
            @Override
            public void onSuccess(ResponseResult<List<ShipRegistrationInfo>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadShipRegisterInfoListener.onSuccess(responseResult.getDatas().get(0));
                        break;
                    case 10008:
                        onLoadShipRegisterInfoListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadShipRegisterInfoListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadShipRegisterInfoListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    public interface OnLoadShipRegisterInfoListener {
        void onSuccess(ShipRegistrationInfo shipRegistrationInfo);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
}
