package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipSelectionRiskModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.IShipSelectionRiskModel.java
 * Date: 2018/1/13 16:05
 * Description:
 */

public interface IShipSelectionRiskModel extends RequestCancelListener {
    void getShipSelectionRisk(String mmsi , String ywcm , ShipSelectionRiskModel.OnLoadShipSelectionRiskListener onLoadShipSelectionRiskListener);
}
