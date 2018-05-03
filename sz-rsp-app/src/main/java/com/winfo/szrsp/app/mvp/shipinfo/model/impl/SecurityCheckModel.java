package com.winfo.szrsp.app.mvp.shipinfo.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.ISecurityCheckModel;
import com.winfo.szrsp.app.sdk.entity.shipdata.ObjFlagStateControl;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSecurityCheckInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;
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
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl.SecurityCheckModel.java
 * Date: 2017/12/22 16:52
 * Description:
 */

public class SecurityCheckModel extends BaseModel<ShipDataService> implements ISecurityCheckModel {

    public SecurityCheckModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getSecurityCheckInfoByMmsiOrYwcm(String mmsi, String ywcm, final OnLoadSecurityCheckListener onLoadSecurityCheckListener) {
        Map<String, String> map = new HashMap<>();
        map.put("mmsi", mmsi);
        map.put("shipNameEn", ywcm);
        String params = FastJsonUtil.mapToJsonStr(map);
        Observable<ResponseResult<StateControlData>> observable = mService.getSecurityCheckByMmsiOrYwcm(params);
        Subscriber<ResponseResult<StateControlData>> subscriber = new DialogSubscriber<ResponseResult<StateControlData>>(null) {
            @Override
            public void onSuccess(ResponseResult<StateControlData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadSecurityCheckListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadSecurityCheckListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    case 0:
                        String msg = responseResult.getMsg();
                        JSONObject jsonObject = FastJsonUtil.jsonStrToJSONObject(msg);
                        String resultinfo = jsonObject.getString("resultinfo");
                        onLoadSecurityCheckListener.onFailure(resultinfo);
                        break;
                    default:
                        onLoadSecurityCheckListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadSecurityCheckListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    public interface OnLoadSecurityCheckListener {
        void onSuccess(StateControlData stateControlData);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
}
