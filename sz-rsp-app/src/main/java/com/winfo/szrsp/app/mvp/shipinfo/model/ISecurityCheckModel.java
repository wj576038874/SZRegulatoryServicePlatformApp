package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.SecurityCheckModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.ISecurityCheckModel.java
 * Date: 2017/12/22 16:52
 * Description:
 */

public interface ISecurityCheckModel extends RequestCancelListener {
    void getSecurityCheckInfoByMmsiOrYwcm(String mmsi, String ywcm, SecurityCheckModel.OnLoadSecurityCheckListener onLoadSecurityCheckListener);
}
