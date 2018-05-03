package com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;

/**
 * Created by HoBo on 2018/3/9.
 */

public interface IOrdinaryGoodsKaiXiangModel {
    void subData(Dialog dialog, OrdinaryGoodsKaiXiangData ordinaryGoodsKaiXiangData, String qmPath,OrdinaryGoodsKaiXiangModel.OnSubDataListenner onSubDataListenner);

}
