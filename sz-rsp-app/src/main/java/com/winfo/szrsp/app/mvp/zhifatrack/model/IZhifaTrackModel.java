package com.winfo.szrsp.app.mvp.zhifatrack.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.IShipInfoModel.java
 * Date: 2017/11/27 19:55
 * Description:
 */

public interface IZhifaTrackModel extends RequestCancelListener {
    void getZhifaTrack(Dialog dialog, boolean isShowing, String uuid, String startTime, String stopTime, ZhifaTrackModel.OnLoadZhifaTracklitener loadZhifaTracklitener);
}
