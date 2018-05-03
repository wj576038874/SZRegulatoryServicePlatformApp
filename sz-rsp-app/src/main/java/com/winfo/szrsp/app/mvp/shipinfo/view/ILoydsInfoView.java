package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoShiShip;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.ILoydsInfoView.java
 * Date: 2017/12/7 16:05
 * Description:
 */
public interface ILoydsInfoView extends IBaseMvpView {
    /**
     * 获取劳氏数据
     *
     * @param laoShiShip 劳氏数据
     */
    void setLowsInfoData(LaoShiShip laoShiShip);

    /**
     * 加载失败
     *
     * @param err 失败信息
     */
    void onLoadShipLoydsInfoFail(String err);

    void loginExpired(String msg);
}
