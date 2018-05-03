package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;
import android.content.Context;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.AisService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.utils.PreferenceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.maplayer.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.maplayer.model.ZhifaPersonModel.java
 * Date: 2017/12/27 13:19
 * Description:
 */

public class ZhifaPersonModel extends BaseModel<AisService> implements IZhifaPersonModel{
    public ZhifaPersonModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getZhifaPersonData(Dialog dialog, final OnLoadZhiFapPersonListener onLoadZhiFapPersonListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        rx.Observable<ResponseResult<List<MobileTerminalInfo2>>> observable = mService.getZhifaPersonData(params);
        Subscriber<ResponseResult<List<MobileTerminalInfo2>>> subscriber = new DialogSubscriber<ResponseResult<List<MobileTerminalInfo2>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<MobileTerminalInfo2>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadZhiFapPersonListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
//                        onLoadZhiFapPersonListener.OnFaile("账号异地登陆！");
//                        PreferenceUtils.setBoolean(SzRspApplication.getContext(),"islogin",false);
                        onLoadZhiFapPersonListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadZhiFapPersonListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }
            @Override
            public void onFailure(String msg) {
                onLoadZhiFapPersonListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    public interface OnLoadZhiFapPersonListener{
        void OnSuccess(List<MobileTerminalInfo2> mobileTerminalInfo2s);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

}
