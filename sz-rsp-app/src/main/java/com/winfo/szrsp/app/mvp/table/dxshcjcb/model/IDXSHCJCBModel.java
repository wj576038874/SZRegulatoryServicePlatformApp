package com.winfo.szrsp.app.mvp.table.dxshcjcb.model;


import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;

/**
 * Created by Guan on 2017-12-06.
 */

public interface IDXSHCJCBModel {


   void subData(Dialog dialog, CtSpecialShipType0203 ctSpecialShipType0203, DXSHCJCBModel.OnSubDataListenner onSubDataListenner);

}
