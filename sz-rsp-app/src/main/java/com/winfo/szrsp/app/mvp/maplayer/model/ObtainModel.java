package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;

import java.util.HashMap;
import java.util.List;

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

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.model
 * @Filename: ObtainModel
 * @Author: lsj
 * @Date: 2018/1/31  16:42
 * @Description:
 * @Version:
 */
public class ObtainModel implements IObtainModel {

    @Override
    public void subObtain(Dialog dialog, final Ais ais, List<String> list1, List<String> list2, final OnSubObtainListener onSubObtainListener) {
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
                        onSubObtainListener.onSuccess(datas);
                    }else if(result == 10008){
                        onSubObtainListener.OnLoginExpired(msg);
                    } else{
                        onSubObtainListener.onFaile(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    onSubObtainListener.onFaile("");
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
                onSubObtainListener.onFaile(msg);
            }
        });
    }

    public interface OnSubObtainListener{
        void onSuccess(String msg);
        void onFaile(String msg);

        void OnLoginExpired(String msg);
    }
}
