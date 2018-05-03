package com.winfo.szrsp.app.mvp.shipinfo.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipTrackModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.IShipInfoModel.java
 * Date: 2017/11/27 19:55
 * Description:
 */

public interface IShipTrackModel extends RequestCancelListener {
    void getShipTrack(Dialog dialog, boolean isShowing, String mmsi, String startTime, String stopTime, ShipTrackModel.OnLoadShipTracklitener loadShipTracklitener);
}
