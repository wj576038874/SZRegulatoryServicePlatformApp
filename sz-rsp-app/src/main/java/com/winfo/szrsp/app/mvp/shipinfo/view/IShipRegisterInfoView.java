package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.IShipRegisterInfoView.java
 * Date: 2017/12/7 16:05
 * Description:
 */

public interface IShipRegisterInfoView extends IBaseMvpView {
    /**
     * 公司信息数据加载失败
     *
     * @param msg 提示信息
     */
    void onLoadShipRegisterInfoFail(String msg);

    /**
     * 将登记信息传递给view层
     *
     * @param shipRegisterInfo 登记信息
     */
    void setShipRegisterInfo(ShipRegistrationInfo shipRegisterInfo);

    void loginExpired(String msg);

}
