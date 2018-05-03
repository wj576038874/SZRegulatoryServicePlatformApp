package com.winfo.szrsp.app.mvp.table.psca.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObject;

/**
 * Created by wly on 2017/12/20.
 *
 */

public interface IPSCFormAActivity {

    CtPscFromObject getCtPscFromObject();

    Dialog getDialog();

    void onSuccess(String msg,String inspectNo);

    void onFailure(String msg);


    void loginExpired(String msg);
}
