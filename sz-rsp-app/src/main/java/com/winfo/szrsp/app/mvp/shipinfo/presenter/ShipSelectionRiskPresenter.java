package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipSelectionRiskModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipSelectionRiskModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.IShipSelectionRiskView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRiskInfo;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipSelectionRiskPresenter.java
 * Date: 2018/1/13 16:04
 * Description:
 */

public class ShipSelectionRiskPresenter extends BaseMvpPresenter<IShipSelectionRiskView> {

    private IShipSelectionRiskModel shipSelectionRiskModel;

    public ShipSelectionRiskPresenter() {
        shipSelectionRiskModel = new ShipSelectionRiskModel();
    }

    public void getShipSelectionRisk(String mmsi , String ywcm) {
        if (mView == null) return;
        shipSelectionRiskModel.getShipSelectionRisk(mmsi, ywcm, new ShipSelectionRiskModel.OnLoadShipSelectionRiskListener() {
            @Override
            public void onSuccess(ShipRiskInfo shipRiskInfo) {
                mView.setShipRegisterInfo(shipRiskInfo);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadShipSelectionRiskFail(msg);
            }
        });
    }

    public void cancelRequest() {
        shipSelectionRiskModel.onRequestCancel();
    }

}
