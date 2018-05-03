package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.CrewInfoModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.ICrewInfoModel.java
 * Date: 2017/12/22 10:47
 * Description:
 */

public interface ICrewInfoModel extends RequestCancelListener{
    void loadCrewInfo(String mmsi, String ywcm, CrewInfoModel.OnLoadCrewInfoListener onLoadCrewInfoListener);
}
