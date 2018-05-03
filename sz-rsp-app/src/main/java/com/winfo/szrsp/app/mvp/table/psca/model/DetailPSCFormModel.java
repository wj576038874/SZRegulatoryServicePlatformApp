package com.winfo.szrsp.app.mvp.table.psca.model;

import android.app.Dialog;
import android.content.Context;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;
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
 * Created by wly on 2017/12/22.
 *
 */

public class DetailPSCFormModel extends BaseModel<TableService> implements IDetailPSCFormModel{


    public DetailPSCFormModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void loadPSCFormDetailData(Dialog dialog, String inspectNo, final OnLoadPSCFormDetailDataListener onLoadPSCFormDetailDataListener) {

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", inspectNo);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<CtPscFromObjectDetail>> observable=mService.findPSCFormDetailData(params);
        Subscriber<ResponseResult<CtPscFromObjectDetail>> subscriber=new DialogSubscriber<ResponseResult<CtPscFromObjectDetail>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<CtPscFromObjectDetail> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadPSCFormDetailDataListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadPSCFormDetailDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadPSCFormDetailDataListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadPSCFormDetailDataListener.onFailure(msg);
            }
        };
        toSubscribe(observable,subscriber);



    }

    public interface OnLoadPSCFormDetailDataListener{
        void onSuccess(CtPscFromObjectDetail ctPscFromObjectDetail);
        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }


}
