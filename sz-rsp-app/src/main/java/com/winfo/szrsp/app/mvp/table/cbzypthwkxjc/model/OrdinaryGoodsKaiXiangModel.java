package com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.model;


import android.app.Dialog;

import com.alibaba.fastjson.JSONObject;
import com.winfo.dnc.sdk.model.ResponseModel;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
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
 * Created by HoBo on 2018/3/9.
 *
 */

public class OrdinaryGoodsKaiXiangModel extends BaseModel<TableService> implements IOrdinaryGoodsKaiXiangModel {
    public OrdinaryGoodsKaiXiangModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subData(Dialog dialog, OrdinaryGoodsKaiXiangData ordinaryGoodsKaiXiangData, String qmPath,final OnSubDataListenner onSubDataListenner) {

        final String str=FastJsonUtil.beanToJsonStr(ordinaryGoodsKaiXiangData);
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
        File file1 = new File(qmPath);
        mbody.addFormDataPart("ctGoodSecneOutFile",file1.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file1));
        final RequestBody requestBody =mbody.build();
        final Request request = new Request.Builder().url(ServerReqAddress.BASE_ADDRESS+"sz/ctGoodSecneOutRestService/insertSelective").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body=response.body().string();
                JSONObject jsonObject=FastJsonUtil.jsonStrToJSONObject(body);
                int result=jsonObject.getInteger("code");
                String msg=jsonObject.getString("msg");
                switch (result){
                    case 1:
                        String id = jsonObject.getString("datas");
                        onSubDataListenner.onSucess("提交成功",id);
                        break;
                    case 10008:
                        onSubDataListenner.OnLoginExpired(msg);
                        break;
                    default:
                        onSubDataListenner.onFaile(msg);
                        break;
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                String msg;
                if (e instanceof SocketTimeoutException) {
                    msg = "请求超时,";
                } else if (e instanceof ConnectException) {
                    msg = "请求超时,";
                }else{
                    msg = "请求未能成功,";
                }
                onSubDataListenner.onFaile(msg);

            }

        });


//        String parames = FastJsonUtil.beanToJsonStr(ordinaryGoodsKaiXiangData);
//        Observable<ResponseResult<String>> observable = mService.addPTHWKXJCData(parames);
//        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog, true) {
//            @Override
//            public void onSuccess(ResponseResult<String> responseResult) {
//                switch (responseResult.getCode()) {
//                    case 1:
//                        onSubDataListenner.onSucess(responseResult.getMsg(), responseResult.getDatas());
//                        break;
//                    default:
//                        onSubDataListenner.onFaile(responseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                onSubDataListenner.onFaile(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
    }


    public interface OnSubDataListenner {
        /**
         * 成功
         */
        void onSucess(String msg, String resultData);

        /**
         * 失败
         */
        void onFaile(String error);

        void OnLoginExpired(String msg);
    }
}
