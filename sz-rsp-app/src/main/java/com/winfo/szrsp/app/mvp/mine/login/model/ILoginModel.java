package com.winfo.szrsp.app.mvp.mine.login.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.mine.login.model.impl.LoginModel;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @PackageName: com.winfo.szrsp.app.mvp.mine.login.model
 * @FileName: com.winfo.szrsp.app.mvp.mine.login.model.ILoginModel.java
 * @Author: wenjie
 * @Date: 2017-10-31 16:11
 * @Description:
 * @Version:
 */
public interface ILoginModel {
    void login(Dialog dialog, String username, String password, LoginModel.OnLoginListener loginListener);

    void getHistoryUser(Dialog dialog , LoginModel.OnLoadHistoryUserListener onLoadHistoryUserListener);
}
