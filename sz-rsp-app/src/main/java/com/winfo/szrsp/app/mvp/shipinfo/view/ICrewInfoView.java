package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.ICrewInfoView.java
 * Date: 2017/12/22 10:15
 * Description:
 */

public interface ICrewInfoView extends IBaseMvpView {

    /**
     * 传递船员列表给view层
     *
     * @param crewInfoList 船员列表
     */
    void setCrewInfoData(List<CrewInfo> crewInfoList);

    /**
     * 加载失败
     *
     * @param msg 信息
     */
    void onLoadCrewInfoFail(String msg);

    void loginExpired(String msg);
}
