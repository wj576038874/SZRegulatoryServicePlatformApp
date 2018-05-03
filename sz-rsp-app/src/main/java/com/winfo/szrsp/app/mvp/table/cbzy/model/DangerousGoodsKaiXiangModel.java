package com.winfo.szrsp.app.mvp.table.cbzy.model;


import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;
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
 * Created by HoBo on 2018/3/8.
 */

public class DangerousGoodsKaiXiangModel extends BaseModel<TableService> implements IDangerousGoodsKaiXiangModel {
    public DangerousGoodsKaiXiangModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subData(Dialog dialog, String path, DangerousGoodsKaiXiangData dangerousGoodsKaiXiangData, final OnSubDataListenner onSubDataListenner) {
//        String parames = FastJsonUtil.beanToJsonStr(dangerousGoodsKaiXiangData);
//        Observable<ResponseResult<String>> observable = mService.addWXHWKXJCData(parames);
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

        final String str = FastJsonUtil.beanToJsonStr(dangerousGoodsKaiXiangData);
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
                .readTimeout(4000, TimeUnit.MILLISECONDS)
                .writeTimeout(4000, TimeUnit.MILLISECONDS)
                .build();

        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File file = new File(path);
        mbody.addFormDataPart("ctDangerPolluteOutFile", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        final RequestBody requestBody = mbody.build();
        final Request request = new Request.Builder().url(ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteOutRestService/insertSelective").post(requestBody).build();
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
                        onSubDataListenner.onSucess(msg, id);
                    }else if(result==10008){
                        onSubDataListenner.OnLoginExpired(msg);
                    }
                    else {
                        onSubDataListenner.onFaile(msg);
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
                onSubDataListenner.onFaile(msg);
            }
        });
    }

    @Override
    public void findDataByPrimaryKey(Dialog dialog, String id, final OnFindDataByPrimaryKeyListenner onFindDataByPrimaryKeyListenner) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String parames = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<DangerousGoodsKaiXiangData>> observable = mService.findWXHWKXJCData(parames);
        Subscriber<ResponseResult<DangerousGoodsKaiXiangData>> subscriber = new DialogSubscriber<ResponseResult<DangerousGoodsKaiXiangData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<DangerousGoodsKaiXiangData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onFindDataByPrimaryKeyListenner.onSucess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    default:
                        onFindDataByPrimaryKeyListenner.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onFindDataByPrimaryKeyListenner.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
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

    public interface OnFindDataByPrimaryKeyListenner {
        /**
         * 成功
         */
        void onSucess(String msg, DangerousGoodsKaiXiangData detailData);

        /**
         * 失败
         */
        void onFaile(String error);
    }
}
