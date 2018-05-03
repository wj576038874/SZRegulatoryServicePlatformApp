package com.winfo.szrsp.app.mvp.table.dzxhyc.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wly on 2018/1/26.
 * 电子巡航异常
 */

public class ElectronicCruiseAbnormalModel extends BaseModel<TableService> implements IElectronicCruiseAbnormalModel{

    public ElectronicCruiseAbnormalModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subCtElectronicCruiseException(Dialog dialog, CtElectronicCruiseException ctElectronicCruiseException, final OnSubCtElectronicCruiseExceptionListener onSubCtElectronicCruiseExceptionListener) {

        String params = FastJsonUtil.beanToJsonStr(ctElectronicCruiseException);
        Observable<ResponseResult<String>> observable=mService.subCtElectronicCruiseException(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,true) {

            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onSubCtElectronicCruiseExceptionListener.onSuccess(responseResult.getMsg(),responseResult.getDatas());
                        break;

                    case 10008:
                        onSubCtElectronicCruiseExceptionListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onSubCtElectronicCruiseExceptionListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onSubCtElectronicCruiseExceptionListener.onFailure(msg);
            }
        };

        toSubscribe(observable,subscriber);
    }

    public interface OnSubCtElectronicCruiseExceptionListener{
        void onSuccess(String msg,String resultData);
        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

}
