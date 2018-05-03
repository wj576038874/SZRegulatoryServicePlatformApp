package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.model;


import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.DetailDangerousGoodsXianChangData;
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
 * Created by HoBo on 2018/3/12.
 */

public class DangerousGoodsXianChangModel extends BaseModel<TableService> implements IDangerousGoodsXianChangModel {
    public DangerousGoodsXianChangModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subData(Dialog dialog, DangerousGoodsXianChangData data, final OnSubDataListenner onSubDataListenner) {
        String parames = FastJsonUtil.beanToJsonStr(data);
        Observable<ResponseResult<String>> observable = mService.addWXHWXCJCData(parames);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
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


    @Override
    public void findDataByPrimaryKey(Dialog dialog, String id, final OnFindDataByPrimaryKeyListenner onFindDataByPrimaryKeyListenner) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String parames = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<DangerousGoodsXianChangData>> observable = mService.findWXHWXCJCData(parames);
        Subscriber<ResponseResult<DangerousGoodsXianChangData>> subscriber = new DialogSubscriber<ResponseResult<DangerousGoodsXianChangData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<DangerousGoodsXianChangData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onFindDataByPrimaryKeyListenner.onSucess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    case 10008:
                        onFindDataByPrimaryKeyListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onFindDataByPrimaryKeyListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onFindDataByPrimaryKeyListenner.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnSubDataListenner {
        /**
         * 成功
         */
        void onSucess(String msg, String resultData);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }

    public interface OnFindDataByPrimaryKeyListenner {
        /**
         * 成功
         */
        void onSucess(String msg, DangerousGoodsXianChangData detailData);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }
}
