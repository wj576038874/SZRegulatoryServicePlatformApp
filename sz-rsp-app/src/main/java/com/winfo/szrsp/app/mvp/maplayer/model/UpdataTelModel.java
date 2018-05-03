package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.AisService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by HoBo on 2018/3/30.
 */

public class UpdataTelModel extends BaseModel<AisService> implements IUpdataTelModel {
    public UpdataTelModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void updataPhoneByMmsi(Dialog dialog, String mmsi, String telephoneNum, String teleName, boolean isShowDialog, final OnUpdataPhoneDataLitener onUpdataPhoneDataLitener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi", mmsi);
        mapParams.put("telephoneNum", telephoneNum);
        mapParams.put("teleName", teleName);
        String parames = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.insertOrUpdateMmsiPhone(parames);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog, isShowDialog) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onUpdataPhoneDataLitener.onSuccess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    case 1008:
                        onUpdataPhoneDataLitener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onUpdataPhoneDataLitener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onUpdataPhoneDataLitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnUpdataPhoneDataLitener {
        void onSuccess(String msg, String data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
}
