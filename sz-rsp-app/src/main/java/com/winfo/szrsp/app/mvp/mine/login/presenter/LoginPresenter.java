package com.winfo.szrsp.app.mvp.mine.login.presenter;

import com.winfo.szrsp.app.entity.user.HistoryUser;
import com.winfo.szrsp.app.mvp.mine.login.model.ILoginModel;
import com.winfo.szrsp.app.mvp.mine.login.model.impl.LoginModel;
import com.winfo.szrsp.app.mvp.mine.login.view.IUserLoginView;
import com.winfo.szrsp.app.sdk.entity.user.UserData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @PackageName: com.winfo.szrsp.app.mvp.mine.login.presenter
 * @FileName: com.winfo.szrsp.app.mvp.mine.login.presenter.LoginPresenter.java
 * @Author: wenjie
 * @Date: 2017-10-31 16:11
 * @Description:
 * @Version:
 */
public class LoginPresenter {
    private ILoginModel loginModel;
    private IUserLoginView userLoginView;

    public LoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.loginModel = new LoginModel();
    }

    public void login() {
        String username = userLoginView.getUserNmae();
        String password = userLoginView.getUserPwd();
        if (username.equals("") || password.equals("")) {
            userLoginView.showMsg("用户名或密码不能为空");
            return;
        }
        loginModel.login(userLoginView.getLoadingDialog(), username, password, new LoginModel.OnLoginListener() {
            @Override
            public void onSuccess(UserData userData) {
                userLoginView.loginSuccess(userData);
            }

            @Override
            public void onFailure(String msg) {
                userLoginView.showMsg(msg);
            }
        });
    }

    public void getHistoryUser(){
        loginModel.getHistoryUser(userLoginView.getLoadingDialog(), new LoginModel.OnLoadHistoryUserListener() {
            @Override
            public void onSuccess(List<HistoryUser> historyUsers) {
                userLoginView.setHistoryUser(historyUsers);
            }

            @Override
            public void onFailure(String msg) {
                userLoginView.showMsg(msg);
            }
        });

    }

}
