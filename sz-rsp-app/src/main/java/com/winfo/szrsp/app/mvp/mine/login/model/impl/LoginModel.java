package com.winfo.szrsp.app.mvp.mine.login.model.impl;

import android.app.Dialog;

import com.lidroid.xutils.exception.DbException;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.user.HistoryUser;
import com.winfo.szrsp.app.mvp.mine.login.model.ILoginModel;
import com.winfo.szrsp.app.sdk.entity.user.UserData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.UserService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageName: com.winfo.szrsp.app.mvp.mine.login.model.impl
 * FileName: com.winfo.szrsp.app.mvp.mine.login.model.impl.LoginModel.java
 * Author: wenjie
 * Date: 2017-10-31 16:10
 * Description:
 */
public class LoginModel extends BaseModel<UserService> implements ILoginModel {

    public LoginModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void login(final Dialog dialog, final String username, String password, final OnLoginListener loginListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("username", username);
        mapParams.put("password", password);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<UserData>> observable = mService.login(params);
        Subscriber<ResponseResult<UserData>> subscriber = new DialogSubscriber<ResponseResult<UserData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<UserData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        loginListener.onSuccess(responseResult.getDatas());
                        break;
                    default:
                        loginListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                loginListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getHistoryUser(Dialog dialog, final OnLoadHistoryUserListener onLoadHistoryUserListener) {
        Observable<List<HistoryUser>> observable = Observable.create(new Observable.OnSubscribe<List<HistoryUser>>() {
            @Override
            public void call(Subscriber<? super List<HistoryUser>> subscriber) {
                try {
                    List<HistoryUser> historyUsers = SzRspApplication.db.findAll(HistoryUser.class);
                    subscriber.onNext(historyUsers);
                    subscriber.onCompleted();
                } catch (DbException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });

        Subscriber<List<HistoryUser>> subscriber = new DialogSubscriber<List<HistoryUser>>(dialog, false) {
            @Override
            public void onSuccess(List<HistoryUser> historyUsers) {
                onLoadHistoryUserListener.onSuccess(historyUsers);
            }

            @Override
            public void onFailure(String msg) {
                onLoadHistoryUserListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnLoginListener {
        void onSuccess(UserData userData);

        void onFailure(String msg);
    }

    public interface OnLoadHistoryUserListener {
        void onSuccess(List<HistoryUser> historyUsers);

        void onFailure(String msg);
    }

}
