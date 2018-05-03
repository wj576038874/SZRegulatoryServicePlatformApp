package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.WatersPatrol;

/**
 * Created by ChengQi on 2017/12/6.
 *
 */

public interface IWaterPatrolActivity {

    Dialog getDialog();

    WatersPatrol getWaterPatrol();

    void showMsg(String msg);

    void onSuccess(String msg,String resultData);

    void onFailure(String msg);

    void loginExpired(String msg);
}
