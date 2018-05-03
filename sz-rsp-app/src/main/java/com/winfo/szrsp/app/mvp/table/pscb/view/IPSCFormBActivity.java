package com.winfo.szrsp.app.mvp.table.pscb.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObject;

/**
 * Created by wly on 2017/12/21.
 *
 */

public interface IPSCFormBActivity {
    CtPscFromObject getCtPscFromObject();

    Dialog getDialog();

    void onSuccess(String msg,String inspectNo);

    void onFailure(String msg);

    void loginExpired(String msg);
}
