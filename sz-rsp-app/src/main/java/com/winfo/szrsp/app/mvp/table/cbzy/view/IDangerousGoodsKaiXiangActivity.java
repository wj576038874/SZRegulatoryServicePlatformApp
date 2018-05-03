package com.winfo.szrsp.app.mvp.table.cbzy.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;

/**
 * Created by HoBo on 2018/3/8.
 */

public interface IDangerousGoodsKaiXiangActivity {
    Dialog getDialog();

    void OnSuccess(String s, String resultData);

    void OnFaile(String msg);

    DangerousGoodsKaiXiangData getData();

    void setDetailData(String msg, DangerousGoodsKaiXiangData detailData);

    void loginExpired(String msg);
}
