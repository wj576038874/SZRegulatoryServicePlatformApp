package com.winfo.szrsp.app.sdk.model;


import android.content.Context;
import android.support.annotation.NonNull;

import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.NetworkUtil;
import com.winfo.szrsp.app.sdk.utils.NoNetWorkException;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.model.BaseModel.java
 * Date: 2017/11/25 12:01
 * Description:
 */

public class BaseModel<Service> {
    protected Service mService;
    private static Retrofit mRetrofit;
    protected Context mContext;
    private Subscription mSubscription;

    public BaseModel(Context context) {
        initRetrofit();
        this.mService = mRetrofit.create(getServiceClass());
        this.mContext = context;
    }

    private void initRetrofit() {
        if (null != mRetrofit)
            return;
        // 配置 client
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new BaseInterceptor())        // 添加请求拦截器
                .retryOnConnectionFailure(true)                      // 是否重试
                .connectTimeout(30, TimeUnit.SECONDS)        // 连接超时事件
                .readTimeout(30, TimeUnit.SECONDS)           // 读取超时时间
                .build();

        // 配置 Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ServerReqAddress.BASE_ADDRESS)// 设置 base url
                .client(client)                                     // 设置 client
                .addConverterFactory(GsonConverterFactory.create()) // 设置 Json 转换工具
//                .addConverterFactory(ScalarsConverterFactory.create())//添加primitives, boxed, and String转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * 请求拦截器
     */
    private class BaseInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();
            //获取请求的headers中的值，代表是ais请求不添加其他无效参数以免出错
            List<String> headerValues = original.headers("AisService");
            if (headerValues != null && headerValues.size() > 0) {
                Request.Builder newBuilder = original.newBuilder();
                newBuilder.removeHeader("AisService");
                return chain.proceed(original);
            } else {
                //添加固定请求参数
                String access_token = ACache.get(mContext).getAsString("access_token");
                if (access_token == null || access_token.length() == 0) {
                    access_token = "5B3F7BB51BE4C055E050007F0100E58F";
                }
                HttpUrl url = original.url().newBuilder()
                        .addQueryParameter("accessToken", access_token)
                        .addQueryParameter("projectSu", "SZMSA")
                        .addQueryParameter("requestSource", "")
                        .addQueryParameter("platform", "APP")
                        .build();
                //添加请求头
                Request request = original.newBuilder()
//                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                    .addHeader("Connection", "keep-alive")
//                    .method(original.method(), original.body())
                        .url(url)
                        .build();
                return chain.proceed(request);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Service> getServiceClass() {
        return (Class<Service>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 订阅者和被订阅者 建立关系 进行网络请求
     *
     * @param observable 被订阅者
     * @param subscriber 订阅者
     */
    protected <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        if (NetworkUtil.checkedNetWork(mContext)) {
            mSubscription = observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        } else {
            subscriber.onError(new NoNetWorkException());
        }
    }

    /**
     * 取消订阅，取消请求
     */
    protected void unSubscribeRequest() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
