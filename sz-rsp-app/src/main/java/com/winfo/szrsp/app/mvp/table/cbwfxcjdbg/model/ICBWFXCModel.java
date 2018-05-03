package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;

/**
 * Created by Guan on 2018-01-29.
 */

public interface ICBWFXCModel {
    void subData(final Dialog dialog, CtSafeInspectNoticeObject ctSafeInspectNoticeObject, CBWFXCModel.OnSubDataListenner onSubDataListenner);
    void getDetailData(final Dialog dialog, String id, CBWFXCModel.OnGetDetailDataListenner onGetDetailDataListenner);

}
