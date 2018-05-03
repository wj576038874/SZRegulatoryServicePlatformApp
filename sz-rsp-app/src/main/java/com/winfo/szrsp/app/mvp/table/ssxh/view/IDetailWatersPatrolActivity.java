package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordObject;

/**
 * Created by ChengQi on 2017/12/9.
 *
 */

public interface IDetailWatersPatrolActivity {

    Dialog getDialog();
    void onSuccess(CtWaterCruiseRecordObject ctWaterCruiseRecordObject);
    void onFailure(String msg);
    void setData(CtWaterCruiseRecordObject ctWaterCruiseRecordObject);

    void loginExpired(String msg);
}
