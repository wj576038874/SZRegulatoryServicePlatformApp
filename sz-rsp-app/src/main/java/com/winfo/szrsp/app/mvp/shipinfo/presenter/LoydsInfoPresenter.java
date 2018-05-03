package com.winfo.szrsp.app.mvp.shipinfo.presenter;


import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.ILoydsInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.LoydsInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.ILoydsInfoView;
import com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoShiShip;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.LoydsInfoPresenter.java
 * Date: 2017/11/28 9:39
 * Description:
 */
public class LoydsInfoPresenter extends BaseMvpPresenter<ILoydsInfoView> {

    private ILoydsInfoModel loydsInfoModel;

    public LoydsInfoPresenter() {
        this.loydsInfoModel = new LoydsInfoModel();
    }

    public void getLowsInfoData(String mmsi, String ywcm) {
        if (mView == null) return;
        loydsInfoModel.getLowsData(mmsi, ywcm, new LoydsInfoModel.OnLoadLowsDataListenner() {

            @Override
            public void onSuccess(LaoShiShip laoShiShip) {
                mView.setLowsInfoData(laoShiShip);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadShipLoydsInfoFail(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest(){
        loydsInfoModel.onRequestCancel();
    }
}
