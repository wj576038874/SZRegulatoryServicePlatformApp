package com.winfo.szrsp.app.mvp.exceptionship.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShipList;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipModel  extends BaseModel<ShipDataService> implements IExceptionShipModel{
    public ExceptionShipModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getExceptionShipListData(Dialog dialog, String pageNum, String pageSize, boolean bol,final OnExceptionShipListListener onExceptionShipListListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ExceptionShipList>> observable = mService.getExceptionShipList(params);
        Subscriber<ResponseResult<ExceptionShipList>> subscriber = new DialogSubscriber<ResponseResult<ExceptionShipList>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<ExceptionShipList> responseResult) {
                switch (responseResult.getCode()){
                    case 1:

                        if(responseResult.getDatas().getList().size()>0){
                            onExceptionShipListListener.OnSuccess(responseResult.getDatas());
                        }else {
                            onExceptionShipListListener.NotFound();
                        }

                        break;
                    case 10008:
                        onExceptionShipListListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onExceptionShipListListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onExceptionShipListListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    public interface OnExceptionShipListListener{
        void OnSuccess(ExceptionShipList data);

        void NotFound();

        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

}
