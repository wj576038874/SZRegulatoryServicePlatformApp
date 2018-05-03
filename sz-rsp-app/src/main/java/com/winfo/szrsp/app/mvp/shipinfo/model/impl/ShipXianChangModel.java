package com.winfo.szrsp.app.mvp.shipinfo.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.IXianChangModel;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSecurityCheckInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
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
import rx.Subscription;

/**
 * Created by HoBo on 2018/4/12.
 */

public class ShipXianChangModel extends BaseModel<ShipDataService> implements IXianChangModel {
    public ShipXianChangModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    @Override
    public void getShipXianChangData(String mmsi, String cm, final OnLoadXianChangListener onLoadXianChangListener) {
        Map<String, String> map = new HashMap<>();
        map.put("mmsi", mmsi);
        map.put("shipNameEn", cm);
        String params = FastJsonUtil.mapToJsonStr(map);
        Observable<ResponseResult<ShipXianChangInfo>> observable = mService.getSupervisionByMmsiOrYwcm(params);
        Subscriber<ResponseResult<ShipXianChangInfo>> subscriber = new DialogSubscriber<ResponseResult<ShipXianChangInfo>>(null) {
            @Override
            public void onSuccess(ResponseResult<ShipXianChangInfo> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadXianChangListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadXianChangListener.onFailure(responseResult.getMsg());
                        break;
                    case 0:
                        String msg=responseResult.getMsg();
                        JSONObject jsonObject = FastJsonUtil.jsonStrToJSONObject(msg);
                        String resultinfo = jsonObject.getString("resultinfo");
                        onLoadXianChangListener.onFailure(resultinfo);
                        break;
                    default:
                        onLoadXianChangListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadXianChangListener.onFailure(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    public interface OnLoadXianChangListener {
        void onSuccess(ShipXianChangInfo shipXianChangInfos);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
}
