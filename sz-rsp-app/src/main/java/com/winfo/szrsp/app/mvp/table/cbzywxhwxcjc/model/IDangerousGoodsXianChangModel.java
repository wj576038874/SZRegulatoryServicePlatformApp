package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;

/**
 * Created by HoBo on 2018/3/12.
 */

public interface IDangerousGoodsXianChangModel {
    void subData(Dialog dialog, DangerousGoodsXianChangData data, DangerousGoodsXianChangModel.OnSubDataListenner onSubDataListenner);

    void findDataByPrimaryKey(Dialog dialog, String id, DangerousGoodsXianChangModel.OnFindDataByPrimaryKeyListenner onFindDataListenner);
}
