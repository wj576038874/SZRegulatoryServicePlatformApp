package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipRegisterInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipRegisterInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.IShipRegisterInfoView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipRegisterInfoPresenter.java
 * Date: 2017/12/7 16:06
 * Description:
 */

public class ShipRegisterInfoPresenter extends BaseMvpPresenter<IShipRegisterInfoView> {

    private IShipRegisterInfoModel shipRegisterInfoModel;

    public ShipRegisterInfoPresenter() {
        this.shipRegisterInfoModel = new ShipRegisterInfoModel();
    }

    public void getShipRegisterInfo(String mmsi, String ywcm) {
        if (mView == null) return;
        shipRegisterInfoModel.getShipRegisterInfo(mmsi, ywcm, new ShipRegisterInfoModel.OnLoadShipRegisterInfoListener() {
            @Override
            public void onSuccess(ShipRegistrationInfo shipRegistrationInfo) {
                mView.setShipRegisterInfo(shipRegistrationInfo);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadShipRegisterInfoFail(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest() {
        shipRegisterInfoModel.onRequestCancel();
    }
}
