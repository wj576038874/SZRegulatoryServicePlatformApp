package com.winfo.szrsp.app.mvp.task.model.imp;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.task.model.ITaskModel;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.task.TaskAcceptData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
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
 * Created by Guan on 2017-12-12.
 */

public class TaskModel extends BaseModel<TaskService> implements ITaskModel{

    public TaskModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void acceptTask(Dialog dialog, boolean isShowLoading, TaskAcceptData taskAcceptData, final OnLoadAcceptTaskListListener onLoadAcceptTaskListListener) {
        String params = FastJsonUtil.beanToJsonStr(taskAcceptData);
        Observable<ResponseResult<String>> observable = mService.acceptTask(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,isShowLoading) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadAcceptTaskListListener.OnSuccess(responseResult.getMsg());
                        break;
                    case 10008:
                        onLoadAcceptTaskListListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadAcceptTaskListListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadAcceptTaskListListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

//    @Override
//    public void acceptTask(Dialog dialog,boolean isShowLoading, String taskId,final OnLoadAcceptTaskListListener onLoadAcceptTaskListListener) {
//        Map<String, String> mapParams = new HashMap<>();
//        mapParams.put("taskId", taskId);
//        String params = FastJsonUtil.mapToJsonStr(mapParams);
//        Observable<ResponseResult<String>> observable = mService.acceptTask(params);
//        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,isShowLoading) {
//            @Override
//            public void onSuccess(ResponseResult<String> responseResult) {
//                switch (responseResult.getCode()) {
//                    case 1:
//                        onLoadAcceptTaskListListener.OnSuccess(responseResult.getMsg());
//                        break;
//
//                    case 10008:
//                        onLoadAcceptTaskListListener.OnLoginExpired(responseResult.getMsg());
//                        break;
//                    default:
//                        onLoadAcceptTaskListListener.OnFaile(responseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                onLoadAcceptTaskListListener.OnFaile(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
//    }

    @Override
    public void finishTask(Dialog dialog, boolean isShowLoading, TaskFinishSubData taskFinishSubData, final OnLoadFinshTaskListListener onLoadFinshTaskListListener) {
        String params = FastJsonUtil.beanToJsonStr(taskFinishSubData);
        Observable<ResponseResult<TaskFinishData>> observable = mService.finishTask(params);
        Subscriber<ResponseResult<TaskFinishData>> subscriber = new DialogSubscriber<ResponseResult<TaskFinishData>>(dialog,isShowLoading) {
            @Override
            public void onSuccess(ResponseResult<TaskFinishData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFinshTaskListListener.OnSuccess(responseResult.getDatas(),responseResult.getMsg());
                        break;

                    case 10008:
                        onLoadFinshTaskListListener.OnLoginExpired(responseResult.getMsg());

                        break;
                    default:
                        onLoadFinshTaskListListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFinshTaskListListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void rejectTask(Dialog dialog, boolean isShowLoading, String taskId, String reason,final OnLoadRejectTaskListListener onLoadRejectTaskListListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("taskId", taskId);
        mapParams.put("taskReturnRemark", reason);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.rejectTask(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,isShowLoading) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadRejectTaskListListener.OnSuccess(responseResult.getMsg());
                        break;

                    case 10008:
                        onLoadRejectTaskListListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadRejectTaskListListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadRejectTaskListListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getUsersListByUserDept(Dialog dialog, boolean isShowLoading, final OnLoadUsersListByUserDeptListener onLoadUsersListByUserDeptListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskPersonResouse>> observable = mService.getUsersListByUserDept(params);
        Subscriber<ResponseResult<TaskPersonResouse>> subscriber = new DialogSubscriber<ResponseResult<TaskPersonResouse>>(dialog,isShowLoading) {
            @Override
            public void onSuccess(ResponseResult<TaskPersonResouse> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadUsersListByUserDeptListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadUsersListByUserDeptListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadUsersListByUserDeptListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadUsersListByUserDeptListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getAisDtat(Dialog dialog, String mmsi, final OnLoadAisDataTaskListener onLoadAisDataTaskListener) {
        Observable<List<Ais>> observable = mService.searShipByMMSI("AISFewShip", mmsi, true);
        Subscriber<List<Ais>> subscriber = new DialogSubscriber<List<Ais>>(dialog) {
            @Override
            public void onSuccess(List<Ais> responseResult) {
                if (responseResult.size() > 0) {
                    onLoadAisDataTaskListener.OnSuccess(responseResult.get(0));
                } else {
                    onLoadAisDataTaskListener.OnFaile("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadAisDataTaskListener.OnFaile("查询失败");
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getUser(Dialog dialog, String deptcode, final OnLoadUserListener onLoadUserListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("deptCode", deptcode);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<UserInfo>>> observable = mService.getdeptUser(params);
        Subscriber<ResponseResult<List<UserInfo>>> subscriber = new DialogSubscriber<ResponseResult<List<UserInfo>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<UserInfo>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadUserListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadUserListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadUserListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadUserListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void distributionTask(Dialog dialog, String taskId, String taskAssignUuid, String lawEnforcementShip, final OnDistributionTaskListener onDistributionTaskListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("taskId", taskId);
        mapParams.put("taskAssignUuid", taskAssignUuid);
        mapParams.put("lawEnforcementCar", "");
        mapParams.put("lawEnforcementShip", lawEnforcementShip);
        mapParams.put("checkFormIds", "");
        mapParams.put("deptCodeStr", "");
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.distributionTask(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onDistributionTaskListener.OnSuccess(responseResult.getMsg());
                        break;

                    case 10008:
                        onDistributionTaskListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onDistributionTaskListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onDistributionTaskListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }
    @Override
    public void getResouseByUserDept(Dialog dialog, boolean isShowLoading, final OnLoadUsersListByUserDeptListener onLoadUsersListByUserDeptListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskPersonResouse>> observable = mService.getUsersListByUserDept(params);
        Subscriber<ResponseResult<TaskPersonResouse>> subscriber = new DialogSubscriber<ResponseResult<TaskPersonResouse>>(dialog,isShowLoading) {
            @Override
            public void onSuccess(ResponseResult<TaskPersonResouse> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadUsersListByUserDeptListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadUsersListByUserDeptListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadUsersListByUserDeptListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadUsersListByUserDeptListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }
    public interface OnLoadAisDataTaskListener{
        void OnSuccess(Ais ais);
        void OnFaile(String msg);
    }

    public interface OnLoadFinshTaskListListener{
        void OnSuccess(TaskFinishData data,String msg);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadAcceptTaskListListener{
        void OnSuccess(String data);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadRejectTaskListListener{
        void OnSuccess(String data);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }
    public interface OnLoadUsersListByUserDeptListener{
        void OnSuccess(TaskPersonResouse taskPersonResouse);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadUserListener{
        void OnSuccess(List<UserInfo> userInfos);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnDistributionTaskListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }
}
