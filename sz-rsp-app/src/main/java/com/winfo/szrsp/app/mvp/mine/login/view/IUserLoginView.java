package com.winfo.szrsp.app.mvp.mine.login.view;

import android.app.Dialog;

import com.winfo.szrsp.app.entity.user.HistoryUser;
import com.winfo.szrsp.app.sdk.entity.user.UserData;

import java.util.List;

public interface IUserLoginView {
    /**
     * 获取用户名
     *
     * @return
     */
    String getUserNmae();

    /**
     * 获取密码
     *
     * @return
     */
    String getUserPwd();

    /**
     * 登陆成功之后的界面需要显示什么 就返回什么
     */
    void loginSuccess(UserData userData);

    /**
     * 吐司消息
     */
    void showMsg(String msg);

    void setHistoryUser(List<HistoryUser> historyUsers);

    Dialog getLoadingDialog();

}
