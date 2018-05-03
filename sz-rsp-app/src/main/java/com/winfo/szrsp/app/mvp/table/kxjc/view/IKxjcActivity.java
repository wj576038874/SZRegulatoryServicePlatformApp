package com.winfo.szrsp.app.mvp.table.kxjc.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.KxjcData;
import com.winfo.szrsp.app.sdk.entity.task.FourTableSubData;

/**
 * Created by HoBo on 2018/3/26.
 */

public interface IKxjcActivity {
    Dialog getDialog();

    void OnSuccess(String s, FourTableSubData resultData);

    void OnFaile(String msg);

    KxjcData getData();

    void loginExpired(String msg);
}
