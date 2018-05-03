package com.winfo.szrsp.app.mvp.task.model.imp;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.task.model.ITaskDetModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TaskService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Guan on 2017-12-10.
 */

public class TaskDetModel extends BaseModel<TaskService> implements ITaskDetModel {
    public TaskDetModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getTaskDetData(Dialog dialog, String taskId, boolean bol, final OnLoadGetTaskDetListener onLoadGetTaskDetListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("taskId", taskId);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskDetails>> observable = mService.getTaskDetData(params);
        Subscriber<ResponseResult<TaskDetails>> subscriber = new DialogSubscriber<ResponseResult<TaskDetails>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<TaskDetails> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadGetTaskDetListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadGetTaskDetListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadGetTaskDetListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadGetTaskDetListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void finishTask(Dialog dialog, boolean bol, String taskId, String dec,final OnLoadFinishTaskDetListener finishTaskListener) {
        //private  String disposalDecision;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("taskId", taskId);
        mapParams.put("disposalDecision", dec);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskFinishData>> observable = mService.finishTask(params);
        Subscriber<ResponseResult<TaskFinishData>> subscriber = new DialogSubscriber<ResponseResult<TaskFinishData>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<TaskFinishData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        finishTaskListener.OnSuccess(responseResult.getMsg());
                        break;

                    case 10008:
                        finishTaskListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        finishTaskListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                finishTaskListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }


    public interface OnLoadGetTaskDetListener{
        void OnSuccess(TaskDetails data);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFinishTaskDetListener{
        void OnSuccess(String data);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

}
