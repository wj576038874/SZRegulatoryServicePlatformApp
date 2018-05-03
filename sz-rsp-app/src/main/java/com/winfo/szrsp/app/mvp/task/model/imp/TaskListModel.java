package com.winfo.szrsp.app.mvp.task.model.imp;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.task.model.ITaskListModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskListData;
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
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.model
 * @Filename: FindAllTableModel
 * @Author: lsj
 * @Date: 2017/12/7  9:43
 * @Description:
 * @Version:
 */
public class TaskListModel extends BaseModel<TaskService> implements ITaskListModel {
    public TaskListModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getTaskListData(Dialog dialog, String pageNum, String pageSize,String taskAssignStatus, boolean bol,final OnLoadGetTaskListListener onLoadGetTaskListListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("taskAssignStatus", taskAssignStatus);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskListData>> observable = mService.getTaskListData(params);
        Subscriber<ResponseResult<TaskListData>> subscriber = new DialogSubscriber<ResponseResult<TaskListData>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<TaskListData> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadGetTaskListListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadGetTaskListListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadGetTaskListListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadGetTaskListListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }


    public interface OnLoadGetTaskListListener{
        void OnSuccess(TaskListData data);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }



}
