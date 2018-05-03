package com.winfo.szrsp.app.mvp.table.dxshcjcb.model;


import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by Guan on 2017-12-06.
 */

public class DXSHCJCBModel extends BaseModel<TableService> implements IDXSHCJCBModel{

    public DXSHCJCBModel() {
        super(SzRspApplication.getContext());
    }
    @Override
    public void subData(final Dialog dialog, CtSpecialShipType0203 ctSpecialShipType0203, final OnSubDataListenner onSubDataListenner) {
        String params = FastJsonUtil.beanToJsonStr(ctSpecialShipType0203);
        Observable<ResponseResult<String>> observable = mService.insertSelectiveDXSHCJCB(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onSubDataListenner.onSucess(responseResult.getMsg(),responseResult.getDatas());
                        break;

                    case 10008:
                        onSubDataListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onSubDataListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onSubDataListenner.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnSubDataListenner {
        /**
         * 成功
         */
        void onSucess(String msg ,String resultData);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }
}
