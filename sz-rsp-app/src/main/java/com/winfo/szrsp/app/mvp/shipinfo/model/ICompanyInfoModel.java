package com.winfo.szrsp.app.mvp.shipinfo.model;


import com.winfo.szrsp.app.mvp.shipinfo.model.impl.CompanyInfoModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.ICompanyInfoModel.java
 * Date: 2017/12/7 14:45
 * Description:
 */

public interface ICompanyInfoModel extends RequestCancelListener {
    void getShipCompanyInfo(String mmsi, String ywcm, CompanyInfoModel.OnLoadCompanyInfoListener onLoadCompanyInfoListener);
}
