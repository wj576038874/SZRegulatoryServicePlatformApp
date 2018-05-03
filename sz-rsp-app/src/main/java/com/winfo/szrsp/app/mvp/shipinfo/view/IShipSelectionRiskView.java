package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRiskInfo;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.IShipSelectionRiskView.java
 * Date: 2018/1/13 16:03
 * Description:
 */

public interface IShipSelectionRiskView extends IBaseMvpView{
    /**
     *
     * @param msg 提示信息
     */
    void onLoadShipSelectionRiskFail(String msg);

    /**
     * 将信息传递给view层
     *
     */
    void setShipRegisterInfo(ShipRiskInfo shipRiskInfo);
}
