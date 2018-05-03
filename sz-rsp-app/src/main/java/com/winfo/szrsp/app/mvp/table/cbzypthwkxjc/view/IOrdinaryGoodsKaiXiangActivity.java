package com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;

/**
 * Created by HoBo on 2018/3/9.
 */

public interface IOrdinaryGoodsKaiXiangActivity {
    Dialog getDialog();

    void OnSuccess(String s, String resultData);

    void OnFaile(String msg);

    OrdinaryGoodsKaiXiangData getData();

    void loginExpired(String msg);
}
