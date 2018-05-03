package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.ISecurityCheckView.java
 * Date: 2017/12/22 16:50
 * Description:
 */

public interface ISecurityCheckView extends IBaseMvpView {

    /**
     * 通过接口将安检数据集合传递给view
     */
    void setSecurityCheckInfo(StateControlData stateControlData, String mmsi, String ywcm);

    /**
     * 加载失败
     *
     * @param msg 信息
     */
    void onLoadSecurityCheckFail(String msg);

    void loginExpired(String msg);
}
