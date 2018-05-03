package com.winfo.szrsp.app.sdk.http;

import android.app.Dialog;
import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.winfo.szrsp.app.sdk.utils.NoNetWorkException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.jar.JarException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.winfo.wenjie.request
 * FileName: com.winfo.wenjie.request.DialogSubscriber.java
 * Author: wenjie
 * Date: 2016-12-12 14:23
 * Description: 订阅者 设置为一个抽象类 两个抽象方法 一个成功 一个失败  子类实例化时  需要实现这两个方法来进行数据传递
 * 当然也可以用接口来做  吧这个类改为普通类 将回调接口作为成员变量传进来 并实现接口的两个方法进行
 * 数据传递也可
 */
public abstract class DialogSubscriber<T> extends Subscriber<T> implements DialogCancelListener {

    /**
     * 定义一个请求成功的抽象方法 子类必须实现并在实现中进行处理服务器返回的数据
     *
     * @param responseResult 服务器返回的数据
     */
    public abstract void onSuccess(T responseResult);

    /**
     * 定义一个请求失败的抽象方法 子类必须实现并在实现中进行服务器返回数据的处理
     *
     * @param msg 服务器返回的错误信息
     */
    public abstract void onFailure(String msg);

    private DialogHandler dialogHandler;

    /**
     * 是否需要显示加载的对话框 有的需要有的不需要 所以在创建订阅者时 直接给true或者false即可
     */
    private boolean isShowDialog = false;

    /**
     * @param dialog       对话框
     * @param isShowDialog 是否显示
     * @see #DialogSubscriber(Dialog)
     */
    @Deprecated
    protected DialogSubscriber(Dialog dialog, boolean isShowDialog) {
        dialogHandler = new DialogHandler(dialog, this);
        this.isShowDialog = isShowDialog;
    }

    /**
     * @param dialog 加载框 不需要显示加载框的时候  直接传递null
     */
    protected DialogSubscriber(Dialog dialog) {
        isShowDialog = dialog != null;
        dialogHandler = new DialogHandler(dialog, this);
    }

    /**
     * 显示对话框 发送一个显示对话框的消息给dialoghandler  由他自己处理（也就是dialog中hanldermesage处理该消息）
     */
    private void showProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.obtainMessage(DialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    /**
     * 隐藏对话框 ....
     */
    private void dismissProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.obtainMessage(DialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            dialogHandler = null;
        }
    }

    /**
     * 请求开始
     */
    @Override
    public void onStart() {
        if (isShowDialog) {
            showProgressDialog();
        }
    }

    /**
     * 请求完成
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }


    /**
     * 请求出错
     *
     * @param e 异常
     */
    @Override
    public void onError(Throwable e) {
        String msg;
        if (e instanceof SocketTimeoutException) {
            msg = "请求超时,请稍后重试！";
        } else if (e instanceof ConnectException) {
            msg = "请求超时,请稍后重试！";
        } else if (e instanceof JsonSyntaxException) {
            msg = "服务器返回数据格式出错！";
        } else if (e instanceof RequestCancelExpection) {
            msg = "请求已被取消";
        } else if (e instanceof NoNetWorkException) {
            msg = "无网络链接,请检查您的网络";
        } else if (e instanceof HttpException) {
            int code = ((HttpException) e).code();
            if (code == 502) {
                msg = "服务正在重启，请稍后";
            } else {
                msg = ((HttpException) e).message();
            }
        } else {
            msg = e.getMessage();
        }
        dismissProgressDialog();
        if (!TextUtils.isEmpty(msg)) {
            onFailure(msg);
        }
    }


    /**
     * 请求成功
     *
     * @param t 数据
     */
    @Override
    public void onNext(T t) {
        /*
         * 请求成功将数据发出去
         */
        onSuccess(t);
    }


    /**
     * 请求被取消
     */
    @Override
    public void onCancel() {
        /*
         * 如果订阅者和被订阅者 没有取消订阅 则取消订阅 以取消网络请求
         */
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
//            this.onError(new RequestCancelExpection());
        }
    }
}
