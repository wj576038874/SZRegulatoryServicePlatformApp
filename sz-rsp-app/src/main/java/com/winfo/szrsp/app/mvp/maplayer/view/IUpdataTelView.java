package com.winfo.szrsp.app.mvp.maplayer.view;

import android.app.Dialog;


/**
 * Created by HoBo on 2018/3/30.
 */

public interface IUpdataTelView {
    Dialog getDialog();

    void OnSuccess(String s, String resultData);

    void OnFaile(String msg);

    void loginExpired(String msg);
}
