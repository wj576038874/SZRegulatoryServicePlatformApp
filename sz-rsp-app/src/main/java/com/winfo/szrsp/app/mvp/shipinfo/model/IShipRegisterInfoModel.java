package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipRegisterInfoModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.IShipRegisterInfoModel.java
 * Date: 2017/12/7 16:07
 * Description:
 */

public interface IShipRegisterInfoModel extends RequestCancelListener{

    void getShipRegisterInfo(String mmsi ,String ywcm, ShipRegisterInfoModel.OnLoadShipRegisterInfoListener onLoadShipRegisterInfoListener);
}
