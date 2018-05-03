package com.winfo.szrsp.app.mvp.setting.model;


import android.app.Dialog;

import com.nostra13.universalimageloader.utils.StorageUtils;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.utils.DataCleanManager;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ProjectName: gdmsaecApp
 * PackageNmae: com.winfo.gdmsaec.app.mvp.model.system.impl
 * Author: wenjie
 * FileName: com.winfo.gdmsaec.app.mvp.model.system.impl.MapCacheModel.java
 * Date: 2018-03-26 10:24
 * Description:
 */
public class MapCacheModel implements IMapCacheModel {

    @Override
    public void loadMapCacheSize(final OnLoadMapCacheSizeListener onLoadMapCacheSizeListener) {
        final File cacheDir = StorageUtils.getOwnCacheDirectory(SzRspApplication.getContext(), "WFTCache");
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String str = DataCleanManager.getCacheSize(cacheDir);
                    subscriber.onNext(str);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
        Subscriber<String> subscriber = new DialogSubscriber<String>(null) {
            @Override
            public void onSuccess(String size) {
                onLoadMapCacheSizeListener.onSuccess(size);
            }

            @Override
            public void onFailure(String msg) {
                onLoadMapCacheSizeListener.onFailure(msg);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void deleteMapCache(Dialog dialog, final OnDeleteMapCacheListener onDeleteMapCacheListener) {
        final String filepath = SDCardUtils.getRootDirectory() + "/WFTCache";
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                DataCleanManager.cleanCustomCache(filepath);
                subscriber.onNext("");
                subscriber.onCompleted();
            }
        });
        Subscriber<String> subscriber = new DialogSubscriber<String>(dialog) {
            @Override
            public void onSuccess(String msg) {
               onDeleteMapCacheListener.onSuccess("清理成功");
            }

            @Override
            public void onFailure(String msg) {
                onDeleteMapCacheListener.onFailure("清理失败");
            }
        };
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public interface OnLoadMapCacheSizeListener{
            void onSuccess(String size);
            void onFailure(String msg);
    }

    public interface OnDeleteMapCacheListener{
        void onSuccess(String msg);
        void onFailure(String msg);
    }

}
