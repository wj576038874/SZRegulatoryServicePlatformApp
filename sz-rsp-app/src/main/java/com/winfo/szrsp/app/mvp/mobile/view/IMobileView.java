package com.winfo.szrsp.app.mvp.mobile.view;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.mobile.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.mobile.view.IMobileView.java
 * Date: 2017/12/15 13:39
 * Description:
 */

public interface IMobileView {
    /**
     * 设备参数保存成功
     */
    void onDeviceParamsSaveSuccess();

    void loginExpired(String msg);
}
