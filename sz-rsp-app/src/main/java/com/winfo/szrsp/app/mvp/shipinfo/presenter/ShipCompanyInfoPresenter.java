package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.ICompanyInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.CompanyInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.ICompanyInfoView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipCompanyInfo;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipCompanyInfoPresenter.java
 * Date: 2017/12/7 14:45
 * Description:
 */

public class ShipCompanyInfoPresenter extends BaseMvpPresenter<ICompanyInfoView> {

    private ICompanyInfoModel companyInfoModel;

    public ShipCompanyInfoPresenter() {
        companyInfoModel = new CompanyInfoModel();
    }

    public void getShipCompanyInfo(String mmsi, String ywcm) {
        if (mView == null) return;
        companyInfoModel.getShipCompanyInfo(mmsi, ywcm, new CompanyInfoModel.OnLoadCompanyInfoListener() {
            @Override
            public void onSueecss(ShipCompanyInfo shipCompanyInfo) {
                mView.setCompanyInfo(shipCompanyInfo);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadCompanyInfoFail(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest() {
        companyInfoModel.onRequestCancel();
    }
}
