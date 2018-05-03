package com.winfo.szrsp.app.mvp.exceptionship.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShipList;

/**
 * Created by Guan on 2017-12-21.
 */

public interface IExceptionShipView {
    void showOnFaile(String msg);
    void notFound();
    Dialog getDailog();
    void setExceptionShipListData(ExceptionShipList data);

    void loginExpired(String msg);
}
