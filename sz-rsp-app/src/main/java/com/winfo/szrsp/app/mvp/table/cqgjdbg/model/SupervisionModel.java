package com.winfo.szrsp.app.mvp.table.cqgjdbg.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.JsonUtil;

import org.json.JSONException;

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
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.model
 * @Filename: SupervisionModel
 * @Author: lsj
 * @Date: 2017/12/6  10:29
 * @Description:
 * @Version:
 */
public class SupervisionModel extends BaseModel<TableService> implements ISupervisionModel {

    public SupervisionModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void SubSupervisionData(jdbgData jdbgData, String qm_path, String jcry_qm_path, Dialog dialog, final OnLoadSubSupervisionListener onLoadSubSupervisionListener) {
//        String params = FastJsonUtil.beanToJsonStr(jdbgData);
//        Observable<ResponseResult<String>> observable = mService.addjdbgData(params);
//        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,true) {
//            @Override
//            public void onSuccess(ResponseResult<String> responseResult) {
//                switch (responseResult.getCode()){
//                    case 1:
//                        onLoadSubSupervisionListener.OnSuccess(responseResult.getDatas());
//                        break;
//                    default:
//                        onLoadSubSupervisionListener.OnFaile(responseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                onLoadSubSupervisionListener.OnFaile(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);

        final String str = FastJsonUtil.beanToJsonStr(jdbgData);
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
                .connectTimeout(40000, TimeUnit.MILLISECONDS)
                .readTimeout(40000, TimeUnit.MILLISECONDS)
                .writeTimeout(40000, TimeUnit.MILLISECONDS)
                .build();

        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File file = new File(qm_path);
        File file_jcry = new File(jcry_qm_path);
        mbody.addFormDataPart("fileCaptain", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        mbody.addFormDataPart("fileInspector", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file_jcry));
        final RequestBody requestBody = mbody.build();
        final Request request = new Request.Builder().url(ServerReqAddress.BASE_ADDRESS + "sz/tFlagStateControlRestService/insertSelective").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                org.json.JSONObject jsonObject = JsonUtil.stringToJson(body);
                try {
                    int result = jsonObject.getInt("code");
                    String msg = jsonObject.getString("msg");
                    if (result == 1) {
                        String id = jsonObject.getString("datas");
                        onLoadSubSupervisionListener.OnSuccess(id);
                    } else if (result == 10008) {
                        onLoadSubSupervisionListener.OnLoginExpired(msg);
                    } else {
                        onLoadSubSupervisionListener.OnFaile(msg);
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
                } else {
                    msg = "请求未能成功，请稍后重试！";
                }
                onLoadSubSupervisionListener.OnFaile(msg);
            }
        });

    }

    @Override
    public void FindSupervisionData(Dialog dialog, String inspectNo, final OnLoadFindSupervisionListener onLoadFindSupervisionListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", inspectNo);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<DetailjdbgData>> observable = mService.findCQGDetailData(params);
        Subscriber<ResponseResult<DetailjdbgData>> subscriber = new DialogSubscriber<ResponseResult<DetailjdbgData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<DetailjdbgData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindSupervisionListener.OnSuccess(responseResult.getDatas());
                        break;
                    default:
                        onLoadFindSupervisionListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindSupervisionListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getDefectCodeData(Dialog dialog, final OnLoadDefectCodeListener onLoadDefectCodeListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<DefectCode>>> observable = mService.getDefectCode(params);
        Subscriber<ResponseResult<List<DefectCode>>> subscriber = new DialogSubscriber<ResponseResult<List<DefectCode>>>(null, false) {
            @Override
            public void onSuccess(ResponseResult<List<DefectCode>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadDefectCodeListener.OnSuccess(responseResult.getDatas());
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
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getInspectorInfo(String uuid, Dialog dialog, final OnLoadInspectorsListener listener) {

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("uuid", uuid);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<List<SecurityInspectorInformation>>> observable = mService.getInspectorInfo(params);
        Subscriber<ResponseResult<List<SecurityInspectorInformation>>> subscriber = new DialogSubscriber<ResponseResult<List<SecurityInspectorInformation>>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<List<SecurityInspectorInformation>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        listener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        listener.OnLoginExpired(responseResult.getMsg());
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
        toSubscribe(observable, subscriber);

    }

    @Override
    public void findShipInfo(String mmsi, String ywcm, Dialog dialog, final OnLoadShipInfoListener onLoadShipInfoListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi", mmsi);
        mapParams.put("shipnameEn", ywcm);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<TaskInfoDetails>> observable = mService.getShipData(params);
        Subscriber<ResponseResult<TaskInfoDetails>> subscriber = new DialogSubscriber<ResponseResult<TaskInfoDetails>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<TaskInfoDetails> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadShipInfoListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadShipInfoListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadShipInfoListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnLoadDefectCodeListener {
        void OnSuccess(List<DefectCode> defectCodes);

        void OnFaile(String msg);
    }

    public interface OnLoadFindSupervisionListener {
        void OnSuccess(DetailjdbgData detailjdbgData);

        void OnFaile(String msg);
    }

    public interface OnLoadSubSupervisionListener {
        void OnSuccess(String msg);

        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadInspectorsListener {
        void onSuccess(List<SecurityInspectorInformation> inspectorInfoList);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadShipInfoListener {
        void onSuccess(TaskInfoDetails infoDetails);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
}
