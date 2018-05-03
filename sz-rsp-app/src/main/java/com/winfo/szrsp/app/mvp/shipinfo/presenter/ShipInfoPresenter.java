package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.IShipInfoView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomationNew;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipInfoPresenter.java
 * Date: 2017/11/28 9:39
 * Description:
 */

public class ShipInfoPresenter extends BaseMvpPresenter<IShipInfoView> {
    private IShipInfoModel shipInfoModel;

    public ShipInfoPresenter() {
        shipInfoModel = new ShipInfoModel();
    }

    public void getShipInfo(String mmsi, String ywcm) {
        if (mView == null) return;
        shipInfoModel.getShipInfo(mmsi, ywcm, new ShipInfoModel.OnLoadShipStaticDatalitener() {

            @Override
            public void onSuccess(ShipInfomation shipInfomation) {
                mView.setShipInfo(shipInfomation);
            }

            @Override
            public void onFailure(String error) {
                mView.onLoadShipInfoFail_level(error);
            }
        });
    }

    public void cancelRequest() {
        shipInfoModel.onRequestCancel();
    }

    public void getShipInfoNew(String mmsi, String ywcm){
        if (mView == null) return;
        shipInfoModel.getShipInfoNew(mmsi,ywcm, new ShipInfoModel.OnLoadShipStaticNewDatalitener() {
            @Override
            public void onSuccess(List<ShipInfomationNew> shipInfomationNews) {
                mView.setShipInfoNew(shipInfomationNews);
            }

            @Override
            public void onFailure(String error) {
                mView.onLoadShipInfoFail(error);
            }
        });
    }

}
