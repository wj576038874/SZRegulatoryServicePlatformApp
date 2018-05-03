package com.winfo.szrsp.app.mvp.zhifatrack.model;


import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalHistory;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.MobileService;
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
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl.IShipInfoModel.java
 * Date: 2017/11/27 19:55
 * Description:
 */

public class ZhifaTrackModel extends BaseModel<MobileService> implements IZhifaTrackModel {

    public ZhifaTrackModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getZhifaTrack(Dialog dialog,boolean isShowing,final String uuid, final String startTime, final String stopTime, final OnLoadZhifaTracklitener loadZhifaTracklitener) {

        Map<String, String> map = new HashMap<>();
        map.put("userUuid", uuid);
        map.put("start", startTime);
        map.put("end", stopTime);
        String params = FastJsonUtil.mapToJsonStr(map);
        Observable<ResponseResult<List<MobileTerminalHistory>>> observable = mService.getZhifaTrack(params);
        Subscriber<ResponseResult<List<MobileTerminalHistory>>> subscriber = new DialogSubscriber<ResponseResult<List<MobileTerminalHistory>>>(dialog,isShowing) {
            @Override
            public void onSuccess(ResponseResult<List<MobileTerminalHistory>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        loadZhifaTracklitener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        loadZhifaTracklitener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        loadZhifaTracklitener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                loadZhifaTracklitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);


//        Observable<List<MobileTerminalHistory>> observable = mService.getZhifaTrack(params);
//        Subscriber<List<MobileTerminalHistory>> subscriber = new DialogSubscriber<List<MobileTerminalHistory>>(dialog,isShowing) {
//            @Override
//            public void onSuccess(List<MobileTerminalHistory> trackData) {
//                loadZhifaTracklitener.onSuccess(trackData);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                loadZhifaTracklitener.onFailure(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    /**
     * 加载船舶资料的数据回调接口
     *
     * @author winfo-wj
     */
    public interface OnLoadZhifaTracklitener {
        /**
         * 成功
         */
        void onSuccess(List<MobileTerminalHistory> trackData);

        /**
         * 失败
         *
         * @param error 错误信息
         */
        void onFailure(String error);

        void OnLoginExpired(String msg);
    }
}

