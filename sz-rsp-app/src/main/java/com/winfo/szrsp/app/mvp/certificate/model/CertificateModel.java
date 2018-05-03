package com.winfo.szrsp.app.mvp.certificate.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TaskService;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.JsonUtil;

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
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.certificate.model
 * @Filename: CertificateModel
 * @Author: lsj
 * @Date: 2018/3/26  15:01
 * @Description:
 * @Version:
 */
public class CertificateModel extends BaseModel<TaskService> implements ICertificateModel{
    public CertificateModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void loadCertificateData(Dialog dialog,boolean isShowDialog, String mmsi,String message, final OnloadCertificateDataListener onloadCertificateDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi", mmsi);
        mapParams.put("message", message);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<CertificateInfo>> observable = mService.getCertificateByMmsi(params);
        Subscriber<ResponseResult<CertificateInfo>> subscriber = new DialogSubscriber<ResponseResult<CertificateInfo>>(dialog,isShowDialog) {
            @Override
            public void onSuccess(ResponseResult<CertificateInfo> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onloadCertificateDataListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onloadCertificateDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    case 0:
                        onloadCertificateDataListener.OnFaile(responseResult.getMsg());
                        break;
                    default:
                        onloadCertificateDataListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onloadCertificateDataListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void subObtain(Dialog dialog, Ais ais, List<String> list1, List<String> list2,List<String> list3,List<String> list4, final UploadCertificateListener uploadCertificateListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi", ais.getID());
        final String str= FastJsonUtil.beanToJsonStr(mapParams);
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
                .connectTimeout(4000, TimeUnit.MILLISECONDS)
                .readTimeout(4000,TimeUnit.MILLISECONDS)
                .writeTimeout(4000, TimeUnit.MILLISECONDS)
                .build();

        MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list1.size() > 0){
            for (int i = 0; i < list1.size(); i++) {
                File file = new File(list1.get(i));
                mbody.addFormDataPart("ais",file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file));
            }
        }
        if (list2.size() > 0){
            for (int i = 0; i < list2.size(); i++) {
                File file = new File(list2.get(i));
                mbody.addFormDataPart("certificate",file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file));
            }
        }
        if (list3.size() > 0){
            for (int i = 0; i < list3.size(); i++) {
                File file = new File(list3.get(i));
                mbody.addFormDataPart("nationalShipCertificate",file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file));
            }
        }
        if (list4.size() > 0){
            for (int i = 0; i < list4.size(); i++) {
                File file = new File(list4.get(i));
                mbody.addFormDataPart("other",file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file));
            }
        }
        final RequestBody requestBody =mbody.build();
        final Request request = new Request.Builder().url(ServerReqAddress.BASE_ADDRESS+"sz/fileUpload/aisUpload").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                try {
                    JSONObject jsonObject = JsonUtil.stringToJson(body);
                    int result = jsonObject.getInt("code");
                    String msg = jsonObject.getString("msg");
                    if (result == 1){
                        String datas = jsonObject.getString("datas");
                        uploadCertificateListener.OnSuccess(datas);
                    }else if(result == 10008){
                        uploadCertificateListener.OnLoginExpired(msg);
                    } else{
                        uploadCertificateListener.OnFaile(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    uploadCertificateListener.OnFaile("");
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
                uploadCertificateListener.OnFaile(msg);
            }
        });
    }

    @Override
    public void deleteCertificateData(Dialog dialog, String itemId,boolean isShowDialog, final OnDeleteCertificateListener onDeleteCertificateListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("idItem",itemId);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<String>> observable = mService.deleteCertificateByUrl(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,isShowDialog) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onDeleteCertificateListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onDeleteCertificateListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    case 0:
                        onDeleteCertificateListener.OnFaile(responseResult.getMsg());
                        break;
                    default:
                        onDeleteCertificateListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onDeleteCertificateListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }


    public interface OnloadCertificateDataListener{
        void OnSuccess(CertificateInfo certificateInfo);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface UploadCertificateListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface OnDeleteCertificateListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);
        void OnLoginExpired(String msg);
    }
}
