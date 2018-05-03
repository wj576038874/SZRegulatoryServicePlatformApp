package com.winfo.szrsp.app.mvp.task.model.imp;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.task.model.ITaskInspectionModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.task.model
 * @Filename: TaskInspectionModel
 * @Author: lsj
 * @Date: 2017/12/9  21:45
 * @Description:
 * @Version:
 */
public class TaskInspectionModel extends BaseModel<TableService> implements ITaskInspectionModel {

    public TaskInspectionModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getData(Dialog dialog, String taskTypeId, final OnLoadTaskInspectionListener onLoadTaskInspectionListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("checkFormIds", taskTypeId);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<List<TaskInspectionItemData>>> observable = mService.getTaskItemData(params);
        Subscriber<ResponseResult<List<TaskInspectionItemData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskInspectionItemData>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<TaskInspectionItemData>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadTaskInspectionListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadTaskInspectionListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadTaskInspectionListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadTaskInspectionListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnLoadTaskInspectionListener{
        void OnSuccess(List<TaskInspectionItemData> taskInspectionItemData);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }
}
