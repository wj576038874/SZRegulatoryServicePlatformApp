package com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.ContainerWeightInspectData;

/**
 * Created by HoBo on 2018/3/9.
 */

public interface IContainerWeightInspectModel {
    void subData(Dialog dialog, ContainerWeightInspectData containerWeightInspectData, String path,ContainerWeightInspectModel.OnSubDataListenner onSubDataListenner);
}
