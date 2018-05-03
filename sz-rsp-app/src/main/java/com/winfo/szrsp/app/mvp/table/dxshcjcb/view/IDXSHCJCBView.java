package com.winfo.szrsp.app.mvp.table.dxshcjcb.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;

/**
 * Created by Guan on 2017-12-06.
 */

public interface IDXSHCJCBView {

    CtSpecialShipType0203 getCtSpecialShipType0203();
    /**
     * 提交成功
     */
    void subSuccessfully(String msg, String inspectNo);
    /**
     * 提交失败
     */
    void subOnFail(String msg);


    Dialog getDialog();


    void loginExpired(String msg);
}
