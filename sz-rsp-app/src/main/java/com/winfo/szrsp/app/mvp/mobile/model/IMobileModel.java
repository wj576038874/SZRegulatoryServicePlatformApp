package com.winfo.szrsp.app.mvp.mobile.model;

import com.winfo.szrsp.app.mvp.mobile.model.impl.MobileModel;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.mobile.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.mobile.model.IMobileModel.java
 * Date: 2017/12/15 13:41
 * Description:
 */

public interface IMobileModel {
    /**
     * 保存设备参数
     *
     * @param devicepParams      设备参数
     * @param onSaveDeviceParams 接口回调
     */
    void setDeviceParams(String devicepParams, MobileModel.OnSaveDeviceParams onSaveDeviceParams);

    /**
     * 更新设备位置
     *
     * @param location 位置
     */
    void updateDeviceLocation(String location);
}
