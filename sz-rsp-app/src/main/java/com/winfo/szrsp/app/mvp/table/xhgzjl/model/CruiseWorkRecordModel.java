package com.winfo.szrsp.app.mvp.table.xhgzjl.model;

import android.app.Dialog;
import android.content.Context;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.service.TaskService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

import android.app.Dialog;
import android.content.Context;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
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
 * Created by HoBo on 2018/4/13.
 */

public class CruiseWorkRecordModel extends BaseModel<TableService> implements ICruiseWorkRecordModel {
    public CruiseWorkRecordModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subData(Dialog dialog, CruiseWorkData data, final OnSubDataListenner onSubDataListenner) {
        String parames = FastJsonUtil.beanToJsonStr(data);
        Observable<ResponseResult<String>> observable = mService.addXHGZJLData(parames);
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
        Observable<ResponseResult<CruiseWorkData>> observable = mService.findXHGZJLData(parames);
        Subscriber<ResponseResult<CruiseWorkData>> subscriber = new DialogSubscriber<ResponseResult<CruiseWorkData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<CruiseWorkData> responseResult) {
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

    @Override
    public void findShip(Dialog dialog, final OnFindShipListenner onFindShipListenner) {
        Observable<ResponseResult<List<CruiseShipData>>> observable = mService.findShip("{}");
        Subscriber<ResponseResult<List<CruiseShipData>>> subscriber = new DialogSubscriber<ResponseResult<List<CruiseShipData>>>(dialog, false) {
            @Override
            public void onSuccess(ResponseResult<List<CruiseShipData>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onFindShipListenner.onSucess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    case 10008:
                        onFindShipListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onFindShipListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onFindShipListenner.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getTaskInspectionByName(Dialog dialog, String name, final OnLoadInspectionByNameListenner onLoadInspectionByNameListenner) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("itemFuseName", name);
        String parames = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<List<TaskInspectionItemByNameData>>> observable = mService.getTaskInspectionByName(parames);
        Subscriber<ResponseResult<List<TaskInspectionItemByNameData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskInspectionItemByNameData>>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<List<TaskInspectionItemByNameData>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadInspectionByNameListenner.onSucess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadInspectionByNameListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadInspectionByNameListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadInspectionByNameListenner.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getInspectionAll(Dialog dialog, boolean isShowDialog, final OnLoadInspectionAllListener onLoadInspectionAllListener) {
        Observable<ResponseResult<List<TaskInspectionItemData>>> observable = mService.getTaskInspectionAll("{}");
        Subscriber<ResponseResult<List<TaskInspectionItemData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskInspectionItemData>>>(dialog, isShowDialog) {
            @Override
            public void onSuccess(ResponseResult<List<TaskInspectionItemData>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadInspectionAllListener.onSueecss(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadInspectionAllListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadInspectionAllListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadInspectionAllListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnLoadInspectionAllListener {
        void onSueecss(List<TaskInspectionItemData> allTaskTypeList);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
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
        void onSucess(String msg, CruiseWorkData detailData);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }

    public interface OnFindShipListenner {
        /**
         * 成功
         */
        void onSucess(String msg, List<CruiseShipData> cruiseShipData);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadInspectionByNameListenner {
        /**
         * 成功
         */
        void onSucess(List<TaskInspectionItemByNameData> data);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }
}
