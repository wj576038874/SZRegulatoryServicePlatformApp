package com.winfo.szrsp.app.mvp.nearby.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.thys.ShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysAndShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ApiService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by HoBo on 2018/4/2.
 */

public class NearModel extends BaseModel<ApiService> implements INearModel {

    public NearModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void loadData(boolean isLogin, Dialog dialog, final OnloadDatasListener onloadDatasListener) {
        Observable<ResponseResult<List<ThysData>>> observable1 = mService.loadThysData();
        Observable<ResponseResult<List<ShipData>>> observable2 = mService.loadBoatsData();

        Observable<ThysAndShipData> observable = Observable.zip(observable1, observable2, new Func2<ResponseResult<List<ThysData>>, ResponseResult<List<ShipData>>, ThysAndShipData>() {
            @Override
            public ThysAndShipData call(ResponseResult<List<ThysData>> listThysResult, ResponseResult<List<ShipData>> listShipResult) {
                ThysAndShipData thysAndShipData = new ThysAndShipData();
                if (listThysResult.getCode() == 1 && listShipResult.getCode() == 1) {
                    thysAndShipData.setListThysResult(listThysResult);
                    thysAndShipData.setListShipResult(listShipResult);
                    return thysAndShipData;
                } else {
                    return null;
                }
            }
        });

        Subscriber<ThysAndShipData> subscriber = new DialogSubscriber<ThysAndShipData>(dialog) {
            @Override
            public void onSuccess(ThysAndShipData responseResult) {
                onloadDatasListener.onSuccess(responseResult);
            }

            @Override
            public void onFailure(String msg) {
                onloadDatasListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnloadDatasListener {
        void onSuccess(ThysAndShipData data);

        void onFailure(String error);
    }
}
