package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipCompanyInfo;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.view.ICompanyInfoView.java
 * Date: 2017/12/7 14:43
 * Description:
 */

public interface ICompanyInfoView extends IBaseMvpView {
    /**
     * 公司信息数据加载失败
     *
     * @param error 提示信息
     */
    void onLoadCompanyInfoFail(String error);

    /**
     * 把公司信息传递给view
     *
     * @param shipCompanyInfo 公司信息
     */
    void setCompanyInfo(ShipCompanyInfo shipCompanyInfo);

    void loginExpired(String msg);
}
