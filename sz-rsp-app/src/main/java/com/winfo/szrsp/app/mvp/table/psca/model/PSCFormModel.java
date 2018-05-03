package com.winfo.szrsp.app.mvp.table.psca.model;

import android.app.Dialog;

import com.alibaba.fastjson.JSONObject;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObject;
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
 * Created by wly on 2017/12/20.
 *
 */

public class PSCFormModel extends BaseModel<TableService> implements IPSCFormModel {


    public PSCFormModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void subPSCFormAData(Dialog dialog, CtPscFromObject ctPscFromObject,String  captainQmPath ,String  orgQmPath,final OnSubPSCFormADataListener onSubPSCFormADataListener) {

        final String str = FastJsonUtil.beanToJsonStr(ctPscFromObject);
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
        File file1 = new File(captainQmPath);
        mbody.addFormDataPart("fileCaptain",file1.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file1));
        File file2=new File(orgQmPath);
        mbody.addFormDataPart("fileOrg",file2.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file2));
        final RequestBody requestBody =mbody.build();
        final Request request = new Request.Builder().url(ServerReqAddress.BASE_ADDRESS+"sz/ctPscFromRestService/insertSelective").post(requestBody).build();
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
                        onSubPSCFormADataListener.onSuccess("提交成功",id);
                        break;

                    case 10008:
                        onSubPSCFormADataListener.OnLoginExpired(msg);
                        break;
                    default:
                        
                        onSubPSCFormADataListener.onFailure(msg);
                        break;
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

            }

        });



//        String params = FastJsonUtil.beanToJsonStr(ctPscFromObject);
//        Observable<ResponseResult<String>> observable=mService.addPSCFormData(params);
//        Subscriber<ResponseResult<String>> subscriber=new DialogSubscriber<ResponseResult<String>>(dialog,true) {
//            @Override
//            public void onSuccess(ResponseResult<String> responseResult) {
//                switch (responseResult.getCode()){
//                    case 1:
//                        onSubPSCFormADataListener.onSuccess(responseResult.getMsg(),responseResult.getDatas());
//                        break;
//                    default:
//                        onSubPSCFormADataListener.onFailure(responseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                onSubPSCFormADataListener.onFailure(msg);
//            }
//        };
//        toSubscribe(observable,subscriber);

    }

    public interface OnSubPSCFormADataListener{
        void onSuccess(String msg,String resultData);
        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

}
