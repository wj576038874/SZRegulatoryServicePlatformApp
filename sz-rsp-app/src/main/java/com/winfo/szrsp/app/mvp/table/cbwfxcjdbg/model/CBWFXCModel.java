package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Guan on 2018-01-29.
 */

public class CBWFXCModel extends BaseModel<TableService> implements ICBWFXCModel {
    public CBWFXCModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subData(final Dialog dialog, CtSafeInspectNoticeObject ctSafeInspectNoticeObject,final OnSubDataListenner onSubDataListenner) {
        String params = FastJsonUtil.beanToJsonStr(ctSafeInspectNoticeObject);
        Observable<ResponseResult<String>> observable = mService.addCBWFXCData(params);
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

    @Override
    public void getDetailData(Dialog dialog, String id, final  OnGetDetailDataListenner onGetDetailDataListenner) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<CtSafeInspectNoticeObject>> observable = mService.findCBWFXCDetailData(params);
        Subscriber<ResponseResult<CtSafeInspectNoticeObject>> subscriber = new DialogSubscriber<ResponseResult<CtSafeInspectNoticeObject>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<CtSafeInspectNoticeObject> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onGetDetailDataListenner.onSucess(responseResult.getDatas());
                        break;
                    case 10008:
                        onGetDetailDataListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onGetDetailDataListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onGetDetailDataListenner.onFaile(msg);
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

    public interface OnGetDetailDataListenner {
        /**
         * 成功
         */
        void onSucess(CtSafeInspectNoticeObject ctSafeInspectNoticeObject);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);

    }
}
