package com.winfo.szrsp.app.mvp.table.dzxhyc.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;

/**
 * Created by wly on 2018/1/26.
 *
 */

public interface IElectronicCruiseAbnormalActivity {

    void showMsg(String msg);

    Dialog getDialog();

    CtElectronicCruiseException getCtElectronicCruiseException();

    void onSuccess(String msg,String resultData);

    void onFailure(String msg);

    void loginExpired(String msg);
}
