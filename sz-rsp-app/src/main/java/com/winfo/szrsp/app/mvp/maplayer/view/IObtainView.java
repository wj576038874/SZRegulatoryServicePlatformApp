package com.winfo.szrsp.app.mvp.maplayer.view;

import android.app.Dialog;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.view
 * @Filename: IObtainView
 * @Author: lsj
 * @Date: 2018/1/31  16:40
 * @Description:
 * @Version:
 */
public interface IObtainView {
    Dialog getDialog();
    void showMsg(String msg);

    void loginExpired(String msg);
}
