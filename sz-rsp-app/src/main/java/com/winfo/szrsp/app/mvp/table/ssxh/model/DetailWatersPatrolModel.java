package com.winfo.szrsp.app.mvp.table.ssxh.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordObject;
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
 * Created by ChengQi on 2017/12/9.
 *
 */

public class DetailWatersPatrolModel extends BaseModel<TableService> implements IDetailWatersPatrolModel {


    public DetailWatersPatrolModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void findWatersPatrolData(Dialog dialog, String inspectNo, final OnLoadWatersPatrolDataListener onLoadWatersPatrolDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", inspectNo);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<CtWaterCruiseRecordObject>> observable=mService.findWatersPatrolDetailData(params);
        Subscriber<ResponseResult<CtWaterCruiseRecordObject>>subscriber=new DialogSubscriber<ResponseResult<CtWaterCruiseRecordObject>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<CtWaterCruiseRecordObject> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadWatersPatrolDataListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadWatersPatrolDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadWatersPatrolDataListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadWatersPatrolDataListener.onFailure(msg);
            }
        };
        toSubscribe(observable,subscriber);


    }

    public interface OnLoadWatersPatrolDataListener{
        void onSuccess(CtWaterCruiseRecordObject ctWaterCruiseRecordObject);
        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

}
