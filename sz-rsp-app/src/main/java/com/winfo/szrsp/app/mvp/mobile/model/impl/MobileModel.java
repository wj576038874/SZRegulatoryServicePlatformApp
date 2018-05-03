package com.winfo.szrsp.app.mvp.mobile.model.impl;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.mobile.model.IMobileModel;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.MobileService;

import rx.Observable;
import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.mobilelocation.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.mobilelocation.model.MobileModel.java
 * Date: 2017/12/15 11:55
 * Description:
 */

public class MobileModel extends BaseModel<MobileService> implements IMobileModel {

    public MobileModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void setDeviceParams(String params, final OnSaveDeviceParams onSaveDeviceParams) {
        Observable<ResponseResult<String>> observable = mService.add(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(null) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                onSaveDeviceParams.onSuccess();
                switch (responseResult.getCode()){
                    case 1:
                        onSaveDeviceParams.onSuccess();
                        break;
                    case 10008:
                        onSaveDeviceParams.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        break;
                }


            }

            @Override
            public void onFailure(String msg) {
                //不作处理
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void updateDeviceLocation(String location) {
        Observable<ResponseResult<String>> observable = mService.updateLocation(location);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(null) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                //不作处理
            }

            @Override
            public void onFailure(String msg) {
                //不作处理
            }
        };
        toSubscribe(observable, subscriber);
    }


    /**
     * 保存设备参数成功的监听接口
     */
    public interface OnSaveDeviceParams {
        /**
         * 保存设备参数成功
         */
        void onSuccess();

        void OnLoginExpired(String msg);
    }
}
