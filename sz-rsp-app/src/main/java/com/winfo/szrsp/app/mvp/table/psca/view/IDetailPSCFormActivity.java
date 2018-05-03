package com.winfo.szrsp.app.mvp.table.psca.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;

/**
 * Created by wly on 2017/12/22.
 *
 */

public interface IDetailPSCFormActivity {

    Dialog getDialog();
    void onSuccess(CtPscFromObjectDetail ctPscFromObjectDetail);
    void onFailure(String msg);

    void loginExpired(String msg);
}
