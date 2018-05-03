package com.winfo.szrsp.app.mvp.table.fragmentlist.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by ChengQi on 2017/12/12.
 *
 */

public class ChecklistModel extends BaseModel<TableService> implements IChecklistModel {


    public ChecklistModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getTaskDefectItemData(String taskTypeId, Dialog dialog, final OnLoadTaskDefectItemDataListener onLoadTaskDefectItemDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("taskTypeId", taskTypeId);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<TaskDefectItemData>>> observable = mService.getTaskDefectItemData(params);
        Subscriber<ResponseResult<List<TaskDefectItemData>>> subscriber = new DialogSubscriber<ResponseResult<List<TaskDefectItemData>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<TaskDefectItemData>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadTaskDefectItemDataListener.OnSuccess(responseResult.getDatas());
                        break;

                    case 10008:
                        onLoadTaskDefectItemDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadTaskDefectItemDataListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadTaskDefectItemDataListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void getUsersListByUserDept( Dialog dialog,boolean b, final OnLoadUsersListByUserDeptListener onLoadUsersListByUserDeptListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskPersonResouse>> observable = mService.getUsersListByUserDept(params);
        Subscriber<ResponseResult<TaskPersonResouse>> subscriber = new DialogSubscriber<ResponseResult<TaskPersonResouse>>(dialog,true) {
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
    public void getDefectCodeData(final OnLoadDefectCodeListener onLoadDefectCodeListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<DefectCode>>> observable = mService.getDefectCode(params);
        Subscriber<ResponseResult<List<DefectCode>>> subscriber = new DialogSubscriber<ResponseResult<List<DefectCode>>>(null,false) {
            @Override
            public void onSuccess(ResponseResult<List<DefectCode>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadDefectCodeListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadDefectCodeListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadDefectCodeListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadDefectCodeListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

//    @Override
//    public void finishTask(Dialog dialog, boolean bol, TaskFinishSubData taskFinishSubData, final FinishTaskListener finishTaskListener) {
//        String params = FastJsonUtil.beanToJsonStr(taskFinishSubData);
//        Observable<ResponseResult<TaskFinishData>> observable = mService.finishTask(params);
//        Subscriber<ResponseResult<TaskFinishData>> subscriber = new DialogSubscriber<ResponseResult<TaskFinishData>>(dialog,bol) {
//            @Override
//            public void onSuccess(ResponseResult<TaskFinishData> responseResult) {
//                switch (responseResult.getCode()) {
//                    case 1:
//                        finishTaskListener.OnSuccess(responseResult.getDatas(),responseResult.getMsg());
//                        break;
//                    case 10008:
//                        finishTaskListener.OnLoginExpired(responseResult.getMsg());
//                        break;
//                    default:
//                        finishTaskListener.OnFaile(responseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                finishTaskListener.OnFaile(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
//    }
@Override
public void finishTask(final Dialog dialog, boolean bol, TaskFinishSubData taskFinishSubData, String siteCaptain, String fileCaptain, String fileInspector, final FinishTaskListener finishTaskListener) {
    final String str=FastJsonUtil.beanToJsonStr(taskFinishSubData);
    dialog.show();

    if(siteCaptain.equals("")&&fileCaptain.equals("")&&fileInspector.equals("")){
        String params = FastJsonUtil.beanToJsonStr(taskFinishSubData);
        Observable<ResponseResult<TaskFinishData>> observable = mService.finishTask(params);
        Subscriber<ResponseResult<TaskFinishData>> subscriber = new DialogSubscriber<ResponseResult<TaskFinishData>>(dialog,bol) {
            @Override
            public void onSuccess(ResponseResult<TaskFinishData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        finishTaskListener.OnSuccess(responseResult.getDatas(),responseResult.getMsg());
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
    }else {

        OkHttpClient client;
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        //添加固定请求参数
                        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
                        if (access_token == null || access_token.length() == 0) {
                            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
                        }
                        HttpUrl url = original.url().newBuilder()
                                .addQueryParameter("accessToken", access_token)
                                .addQueryParameter("projectSu", "SZMSA")
                                .addQueryParameter("requestSource", "")
                                .addQueryParameter("platform", "APP")
                                .addQueryParameter("parameterJson", str)
                                .build();
                        //添加请求头
                        Request request = original.newBuilder()
                                .url(url)
                                .build();
                        return chain.proceed(request);

                    }
                })
                .connectTimeout(400000, TimeUnit.MILLISECONDS)
                .readTimeout(400000,TimeUnit.MILLISECONDS)
                .writeTimeout(400000, TimeUnit.MILLISECONDS)
                .build();

        MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(siteCaptain!=null&&!siteCaptain.equals("")){
            File file_siteCaptain = new File(siteCaptain);
            mbody.addFormDataPart("siteCaptain",file_siteCaptain.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file_siteCaptain));
        }
        if(fileCaptain!=null&&!fileCaptain.equals("")){
            File file_fileCaptain = new File(fileCaptain);
            mbody.addFormDataPart("fileCaptain",file_fileCaptain.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file_fileCaptain));

        }
        if(fileInspector!=null&&!fileInspector.equals("")){
            File file_fileInspector = new File(fileInspector);
            mbody.addFormDataPart("fileInspector",file_fileInspector.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file_fileInspector));

        }

        final RequestBody requestBody =mbody.build();
        final Request request = new Request.Builder().url(ServerReqAddress.BASE_ADDRESS+"sz/taskInfoSZRestService/achieveTask").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                dialog.dismiss();
                org.json.JSONObject jsonObject = JsonUtil.stringToJson(body);
                try {
                    int result = jsonObject.getInt("code");
                    String msg = jsonObject.getString("msg");
                    if (result == 1){
                        JSONObject datas = jsonObject.getJSONObject("datas");
                        TaskFinishData taskFinishData = FastJsonUtil.jsonStrToBean(datas.toString(), TaskFinishData.class);
                        finishTaskListener.OnSuccess(taskFinishData,msg);
                    }
                    else if(result==10008){
                        finishTaskListener.OnLoginExpired(msg);
                    }else{
                        finishTaskListener.OnFaile(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                String msg;
                if (e instanceof SocketTimeoutException) {
                    msg = "请求超时,请稍后重试！";
                } else if (e instanceof ConnectException) {
                    msg = "请求超时,请稍后重试！";
                }else{
                    msg = "请求未能成功，请稍后重试！";
                }
                finishTaskListener.OnFaile(msg);
                dialog.dismiss();
            }
        });
    }

}

    @Override
    public void getInspectorInfo(String uuid, Dialog dialog, final OnLoadInspectorsListener listener) {

        Map<String,String>mapParams=new HashMap<>();
        mapParams.put("uuid",uuid);
        String params=FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<SecurityInspectorInformation>>>observable=mService.getInspectorInfo(params);
        Subscriber<ResponseResult<List<SecurityInspectorInformation>>>subscriber=new DialogSubscriber<ResponseResult<List<SecurityInspectorInformation>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<SecurityInspectorInformation>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        listener.onSuccess(responseResult.getDatas());
                        break;

                    default:
                        listener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        };
        toSubscribe(observable,subscriber);

    }


    public interface OnLoadTaskDefectItemDataListener{
        void OnSuccess(List<TaskDefectItemData> taskDefectItemData);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface OnLoadUsersListByUserDeptListener{
        void OnSuccess(TaskPersonResouse taskPersonResouse);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadDefectCodeListener{
        void OnSuccess(List<DefectCode> defectCodes);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface FinishTaskListener{
        void OnSuccess(TaskFinishData data, String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface OnLoadInspectorsListener{
        void onSuccess(List<SecurityInspectorInformation> inspectorInfoList);
        void onFailure(String msg);
    }
}
