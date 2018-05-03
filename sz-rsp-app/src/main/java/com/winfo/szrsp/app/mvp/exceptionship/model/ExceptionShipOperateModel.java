package com.winfo.szrsp.app.mvp.exceptionship.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;
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
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipOperateModel  extends BaseModel<ShipDataService> implements IExceptionShipOperateModel{
    public ExceptionShipOperateModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void addExceptionShip(Dialog dialog, Ais aisData,String dec, boolean bol,final OnExceptionShipAddListener onExceptionShipAddListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("shipNameCn","" );
        mapParams.put("shipNameEn", aisData.getCM());
        mapParams.put("mmsi",aisData.getID() );
        mapParams.put("exceptionRemark",dec );
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.addExceptionShip(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onExceptionShipAddListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        onExceptionShipAddListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onExceptionShipAddListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onExceptionShipAddListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void deleteExceptionShip(Dialog dialog, String mmsi, boolean bol,final OnExceptionShipDeleteListener onExceptionShipDeleteListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi",mmsi);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.deleteExceptionShip(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onExceptionShipDeleteListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        onExceptionShipDeleteListener.OnLoginExpired(responseResult.getMsg());
                    default:
                        onExceptionShipDeleteListener.OnSuccess(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onExceptionShipDeleteListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void queryExceptionShip(Dialog dialog, String mmsi, boolean bol,final OnExceptionShipQueryListener onExceptionShipQueryListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi",mmsi );
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ExceptionShip>> observable = mService.queryIsExceptionShip(params);
        Subscriber<ResponseResult<ExceptionShip>> subscriber = new DialogSubscriber<ResponseResult<ExceptionShip>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<ExceptionShip> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onExceptionShipQueryListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onExceptionShipQueryListener.OnLoginExpired(responseResult.getMsg());
                    default:
                        onExceptionShipQueryListener.OnSuccess(responseResult.getDatas());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onExceptionShipQueryListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getAisDtat(Dialog dialog, String mmsi, final OnLoadAisDataExceptionListener onLoadAisDataExceptionListener) {
        Observable<List<Ais>> observable = mService.searShipByMMSI("AISFewShip", mmsi, true);
        Subscriber<List<Ais>> subscriber = new DialogSubscriber<List<Ais>>(dialog) {
            @Override
            public void onSuccess(List<Ais> responseResult) {
                if (responseResult.size() > 0) {
                    onLoadAisDataExceptionListener.OnSuccess(responseResult.get(0));
                } else {
                    onLoadAisDataExceptionListener.OnFaile("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadAisDataExceptionListener.OnFaile("查询失败");
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnExceptionShipAddListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }


    public interface OnLoadAisDataExceptionListener{
        void OnSuccess(Ais ais);
        void OnFaile(String msg);
    }
    public interface OnExceptionShipDeleteListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface OnExceptionShipQueryListener{
        void OnSuccess(ExceptionShip exceptionShip);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
}
