package com.winfo.szrsp.app.mvp.table.ssxh.model;

import android.app.Dialog;

/**
 * Created by ChengQi on 2017/12/9.
 */

public interface IDetailWatersPatrolModel {

    void findWatersPatrolData(Dialog dialog, String inspectNo, DetailWatersPatrolModel.OnLoadWatersPatrolDataListener onLoadWatersPatrolDataListener);


}
