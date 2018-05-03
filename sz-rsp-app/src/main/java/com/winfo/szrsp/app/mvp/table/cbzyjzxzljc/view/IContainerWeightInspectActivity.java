package com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.ContainerWeightInspectData;

/**
 * Created by HoBo on 2018/3/9.
 */

public interface IContainerWeightInspectActivity {
    Dialog getDialog();

    void OnSuccess(String s, String resultData);

    void OnFaile(String msg);

    ContainerWeightInspectData getData();

    void loginExpired(String msg);
}
