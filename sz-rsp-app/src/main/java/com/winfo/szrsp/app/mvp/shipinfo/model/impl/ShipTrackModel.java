package com.winfo.szrsp.app.mvp.shipinfo.model.impl;


import android.app.Dialog;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipTrackModel;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import java.util.List;
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

public class ShipTrackModel extends BaseModel<ShipDataService> implements IShipTrackModel {

    public ShipTrackModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getShipTrack(Dialog dialog,boolean isShowing,final String mmsi, final String startTime, final String stopTime, final OnLoadShipTracklitener loadShipTracklitener) {
        Observable<List<TrackData>> observable = mService.getShipTrackByMMSI("SingleShip", mmsi, startTime,stopTime,"0");
        Subscriber<List<TrackData>> subscriber = new DialogSubscriber<List<TrackData>>(dialog,isShowing) {
            @Override
            public void onSuccess(List<TrackData> trackData) {
                if(trackData.size()>0){
                    loadShipTracklitener.onSuccess(trackData);
                }else {
                    loadShipTracklitener.onFailure("无数据！");
                }

            }

            @Override
            public void onFailure(String msg) {
                loadShipTracklitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
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
    public interface OnLoadShipTracklitener {
        /**
         * 成功
         */
        void onSuccess(List<TrackData> trackData);

        /**
         * 失败
         *
         * @param error 错误信息
         */
        void onFailure(String error);
    }
}

