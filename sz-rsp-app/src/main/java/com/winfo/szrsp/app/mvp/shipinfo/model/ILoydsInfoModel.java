package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.LoydsInfoModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.ILoydsInfoModel.java
 * Date: 2017/12/7 14:45
 * Description:
 */
public interface ILoydsInfoModel extends RequestCancelListener{
    void getLowsData(String mmsi,String ywcm , LoydsInfoModel.OnLoadLowsDataListenner onLoadLowsDataListenner);
}
