package com.winfo.szrsp.app.mvp.plotting.model;

import android.app.Dialog;
import android.content.Context;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.sdk.entity.plotting.PlottingListData;
import com.winfo.szrsp.app.sdk.entity.task.TaskListData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.PlottingService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by winfo053 on 2018/4/9.
 */

public class PlottingModel extends BaseModel<PlottingService> implements IPlottingModel{
    public PlottingModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getPlottingListData(Dialog dialog, String pageNum, String pageSize, String bhname, String drawingType, boolean bol,final OnLoadGetPlottingListListener onLoadGetPlottingListListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("bhname", bhname);
        mapParams.put("drawingType", drawingType);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<PlottingListData>> observable = mService.selectByConditions(params);
        Subscriber<ResponseResult<PlottingListData>> subscriber = new DialogSubscriber<ResponseResult<PlottingListData>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<PlottingListData> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadGetPlottingListListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadGetPlottingListListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadGetPlottingListListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }
            @Override
            public void onFailure(String msg) {
                onLoadGetPlottingListListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void deletePlottingById(Dialog dialog, String id, boolean bol,final OnDeletePlottingListener onDeletePlottingListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("id", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.deleteByPrimaryKey(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onDeletePlottingListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        onDeletePlottingListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onDeletePlottingListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }
            @Override
            public void onFailure(String msg) {
                onDeletePlottingListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void addPlotting(Dialog dialog, Plotting plotting, boolean bol,final OnAddPlottingListener onAddPlottingListener) {
        String params=FastJsonUtil.beanToJsonStr(plotting);
        Observable<ResponseResult<String>> observable = mService.insertSelective(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onAddPlottingListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        onAddPlottingListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onAddPlottingListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }
            @Override
            public void onFailure(String msg) {
                onAddPlottingListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void editPlotting(Dialog dialog, Plotting plotting, boolean bol, final OnEditPlottingListener onEditPlottingListener) {
        String params=FastJsonUtil.beanToJsonStr(plotting);
        Observable<ResponseResult<String>> observable = mService.updateByPrimaryKeySelective(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onEditPlottingListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        onEditPlottingListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onEditPlottingListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }
            @Override
            public void onFailure(String msg) {
                onEditPlottingListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }


    public interface OnLoadGetPlottingListListener{
        void OnSuccess(PlottingListData data);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
    public interface OnDeletePlottingListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
    public interface OnAddPlottingListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
    public interface OnEditPlottingListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
}
