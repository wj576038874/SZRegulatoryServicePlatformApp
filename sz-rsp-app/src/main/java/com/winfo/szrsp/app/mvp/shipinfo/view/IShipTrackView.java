package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view.fragment
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.IShipInfoView.java
 * Date: 2017/11/28 9:36
 * Description:
 */

public interface IShipTrackView extends IBaseMvpView {
    /**
     * 数据加载失败
     *
     * @param error 提示信息
     */
    void onLoadShipTrackFail(String error);


    void setShipTrack(List<TrackData> trackData);

}
