package com.winfo.szrsp.app.mvp.table.kxjc.model;


import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.KxjcData;
import com.winfo.szrsp.app.sdk.entity.task.FourTableSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by HoBo on 2018/3/26.
 */

public class KxjcModel extends BaseModel<TableService> implements IKxjcModel {
    public KxjcModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subData(Dialog dialog, KxjcData data, final OnSubDataListenner onSubDataListenner) {
        String parames = FastJsonUtil.beanToJsonStr(data);
        Observable<ResponseResult<FourTableSubData>> observable = mService.subTable(parames);
        Subscriber<ResponseResult<FourTableSubData>> subscriber = new DialogSubscriber<ResponseResult<FourTableSubData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<FourTableSubData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onSubDataListenner.onSucess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    case 10008:
                        onSubDataListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onSubDataListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onSubDataListenner.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnSubDataListenner {
        /**
         * 成功
         */
        void onSucess(String msg, FourTableSubData data);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }
}
