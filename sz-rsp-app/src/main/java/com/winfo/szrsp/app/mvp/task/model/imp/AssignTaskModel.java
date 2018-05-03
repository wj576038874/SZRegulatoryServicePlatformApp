package com.winfo.szrsp.app.mvp.task.model.imp;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.task.model.IAassigTaskModel;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType2;
import com.winfo.szrsp.app.sdk.entity.task.ReleaseTaskModel;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskShipData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TaskService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by winfo053 on 2018/3/7.
 */

public class AssignTaskModel extends BaseModel<TaskService> implements IAassigTaskModel {
    public AssignTaskModel() {
        super(SzRspApplication.getContext());
    }

//    @Override
//    public void getTaskType(Dialog dialog,boolean bol, final OnLoadTaskTypeListener onLoadTaskTypeListener) {
//        Observable<ResponseResult<List<AllTaskType>>> observable = mService.getTaskType("{}");
//        Subscriber<ResponseResult<List<AllTaskType>>> subscriber = new DialogSubscriber<ResponseResult<List<AllTaskType>>>(dialog,bol) {
//            @Override
//            public void onSuccess(ResponseResult<List<AllTaskType>> responseResult) {
//                switch (responseResult.getCode()) {
//                    case 1:
//                        onLoadTaskTypeListener.onSueecss(responseResult.getDatas());
//                        break;
//
//                    case 10008:
//                        onLoadTaskTypeListener.OnLoginExpired(responseResult.getMsg());
//                        break;
//                    default:
//                        onLoadTaskTypeListener.onFailure(responseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                onLoadTaskTypeListener.onFailure(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
//    }
    @Override
    public void getTaskType(Dialog dialog,boolean bol, final OnLoadTaskTypeListener onLoadTaskTypeListener) {
        Observable<ResponseResult<AllTaskType2>> observable = mService.getTaskType("{}");
        Subscriber<ResponseResult<AllTaskType2>> subscriber = new DialogSubscriber<ResponseResult<AllTaskType2>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<AllTaskType2> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadTaskTypeListener.onSueecss(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadTaskTypeListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadTaskTypeListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadTaskTypeListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getDeptUser(Dialog dialog, String deptCode, final OnLoadDeptUserListener onLoadDeptUserListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("deptCode", deptCode);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<UserInfo>>> observable = mService.getdeptUser(params);
        Subscriber<ResponseResult<List<UserInfo>>> subscriber = new DialogSubscriber<ResponseResult<List<UserInfo>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<UserInfo>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadDeptUserListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadDeptUserListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadDeptUserListener.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadDeptUserListener.onFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getTaskShipDataByKey(Dialog dialog, String key, final OnLoadTaskShipDataListener onLoadTaskShipDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("search_text", key);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<TaskShipData>>> observable = mService.getShipDataByKey(params);
        Subscriber<ResponseResult<List<TaskShipData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskShipData>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<TaskShipData>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadTaskShipDataListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadTaskShipDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadTaskShipDataListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadTaskShipDataListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getTaskShipDataByMMSI(Dialog dialog, String key, final OnLoadTaskShipDataListener onLoadTaskShipDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi", key);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<TaskShipData>>> observable = mService.getShipDataByMMSI(params);
        Subscriber<ResponseResult<List<TaskShipData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskShipData>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<TaskShipData>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadTaskShipDataListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadTaskShipDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadTaskShipDataListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadTaskShipDataListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void releaseTask(Dialog dialog, ReleaseTaskModel releaseTaskModel, final ReleaseTaskListener releaseTaskListener) {
        String params = FastJsonUtil.beanToJsonStr(releaseTaskModel);
        Observable<ResponseResult<TaskInfo>> observable = mService.releaseTask(params);
        Subscriber<ResponseResult<TaskInfo>> subscriber = new DialogSubscriber<ResponseResult<TaskInfo>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<TaskInfo> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        releaseTaskListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        releaseTaskListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        releaseTaskListener.OnSuccess(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                releaseTaskListener.OnSuccess(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getTaskInspectionAll(Dialog dialog, boolean bol, final OnLoadInspectionAllListener onLoadInspectionAllListener) {
        Observable<ResponseResult<List<TaskInspectionItemData>>> observable = mService.getTaskInspectionAll("{}");
        Subscriber<ResponseResult<List<TaskInspectionItemData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskInspectionItemData>>>(dialog,bol) {
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

    @Override
    public void getTaskInspectionTaskTypeId(Dialog dialog, boolean bol,String  taskTypeId, final OnLoadTaskInspectionTaskTypeIdListener onLoadTaskInspectionTaskTypeIdListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("taskTypeId", taskTypeId);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<TaskInspectionItemData>>> observable = mService.getTaskInspectionTaskTypeId(params);
        Subscriber<ResponseResult<List<TaskInspectionItemData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskInspectionItemData>>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<List<TaskInspectionItemData>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadTaskInspectionTaskTypeIdListener.onSueecss(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadTaskInspectionTaskTypeIdListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadTaskInspectionTaskTypeIdListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadTaskInspectionTaskTypeIdListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    public interface OnLoadTaskTypeListener {
        void onSueecss(AllTaskType2 allTaskType);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
    public interface OnLoadInspectionAllListener {
        void onSueecss(List<TaskInspectionItemData> allTaskTypeList);

        void onFailure(String msg);
        void OnLoginExpired(String msg);
    }
    public interface OnLoadTaskInspectionTaskTypeIdListener {
        void onSueecss(List<TaskInspectionItemData> allTaskTypeList);

        void onFailure(String msg);
        void OnLoginExpired(String msg);
    }
    public interface OnLoadDeptUserListener{
        void onSuccess(List<UserInfo> userInfos);
        void onFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadTaskShipDataListener{
        void OnSuccess(List<TaskShipData> taskShipData);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface ReleaseTaskListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
}
