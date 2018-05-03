package com.winfo.szrsp.app.mvp.table.ssxh.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.WatersPatrol;

/**
 * Created by ChengQi on 2017/12/7.
 *
 */

public interface IWaterPatrolModel {

     void subWatersPatrolData(Dialog dialog, WatersPatrol watersPatrol, String qmPath,WatersPatrolModel.OnSubWatersPatrolDataListener onSubWatersPatrolDataListener);

}
