//package com.winfo.szrsp.app.mvp;
//
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * @ProjectName: gdmsaecApp
// * @PackageName: com.winfo.gdmsaec.app.mvp.model
// * @FileName: com.winfo.gdmsaec.app.mvp.model.BaseModel.java
// * @Author: wenjie
// * @Date: 2017-01-03 14:34
// * @Description:
// * @Version:
// */
//public class BaseModel {
//    /**
//     * 订阅者和被订阅者 建立关系 进行网络请求
//     * @param observable 被订阅者
//     * @param subscriber  订阅者
//     */
//    protected <T>void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//}
