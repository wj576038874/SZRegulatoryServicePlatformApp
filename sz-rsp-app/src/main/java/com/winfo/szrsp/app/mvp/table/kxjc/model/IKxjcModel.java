package com.winfo.szrsp.app.mvp.table.kxjc.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.KxjcData;

/**
 * Created by HoBo on 2018/3/26.
 */

public interface IKxjcModel {
     void subData(Dialog dialog, KxjcData data, KxjcModel.OnSubDataListenner onSubDataListenner);
}
