package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomationNew;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view.fragment
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.IShipInfoView.java
 * Date: 2017/11/28 9:36
 * Description:
 */

public interface IShipInfoView extends IBaseMvpView {
    /**
     * 数据加载失败
     *
     * @param error 提示信息
     */
    void onLoadShipInfoFail(String error);

    void onLoadShipInfoFail_level(String error);

    /**
     * 通过接口将基本信息传递给view
     *
     * @param shipInfomation 基本信息
     */
    void setShipInfo(ShipInfomation shipInfomation);

    void setShipInfoNew(List<ShipInfomationNew> shipInfomationNews);

}
