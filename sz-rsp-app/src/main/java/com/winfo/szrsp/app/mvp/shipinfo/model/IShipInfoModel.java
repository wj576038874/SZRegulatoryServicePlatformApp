package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipInfoModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.IShipInfoModel.java
 * Date: 2017/11/27 19:55
 * Description:
 */

public interface IShipInfoModel extends RequestCancelListener {
    void getShipInfo(String mmsi,String ywcm , ShipInfoModel.OnLoadShipStaticDatalitener loadShipStaticDatalitener);

    void getShipInfoNew(String mmsi,String ywcm , ShipInfoModel.OnLoadShipStaticNewDatalitener loadShipStaticNewDatalitener);
}
