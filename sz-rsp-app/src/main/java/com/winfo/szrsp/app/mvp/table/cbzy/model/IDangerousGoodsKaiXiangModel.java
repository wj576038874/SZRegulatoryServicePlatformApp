package com.winfo.szrsp.app.mvp.table.cbzy.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;

/**
 * Created by HoBo on 2018/3/8.
 */

public interface IDangerousGoodsKaiXiangModel {
    void subData(Dialog dialog, String path, DangerousGoodsKaiXiangData dangerousGoodsKaiXiangData, DangerousGoodsKaiXiangModel.OnSubDataListenner onSubDataListenner);

    void findDataByPrimaryKey(Dialog dialog, String id, DangerousGoodsKaiXiangModel.OnFindDataByPrimaryKeyListenner onFindDataByPrimaryKeyListenner);
}
