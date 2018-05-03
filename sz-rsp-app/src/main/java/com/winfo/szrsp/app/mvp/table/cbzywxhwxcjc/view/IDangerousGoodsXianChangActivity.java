package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.DetailDangerousGoodsXianChangData;

/**
 * Created by HoBo on 2018/3/12.
 */

public interface IDangerousGoodsXianChangActivity {

    Dialog getDialog();

    void OnSuccess(String s, String resultData);

    void OnFaile(String msg);

    DangerousGoodsXianChangData getData();

    void setDetailData(String msg, DangerousGoodsXianChangData detailData);

    void loginExpired(String msg);
}
