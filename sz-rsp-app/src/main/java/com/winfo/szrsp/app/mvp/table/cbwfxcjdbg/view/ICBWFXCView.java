package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;

/**
 * Created by Guan on 2017-12-06.
 */

public interface ICBWFXCView {

    CtSafeInspectNoticeObject getCtSafeInspectNoticeObject();
    /**
     * 提交成功
     */
    void subSuccessfully(String msg, String inspectNo);
    /**
     * 提交失败
     */
    void subOnFail(String msg);


    Dialog getDialog();

    void setDetailData(CtSafeInspectNoticeObject ctSafeInspectNoticeObject);

    void loginExpired(String msg);
}
