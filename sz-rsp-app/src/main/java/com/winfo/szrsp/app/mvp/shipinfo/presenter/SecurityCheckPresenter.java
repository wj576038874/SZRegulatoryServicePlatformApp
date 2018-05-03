package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.map.searchais.view.ISearchShipView;
import com.winfo.szrsp.app.mvp.shipinfo.model.ISecurityCheckModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.SecurityCheckModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.ISecurityCheckView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ObjFlagStateControl;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSecurityCheckInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.SecurityCheckPresenter.java
 * Date: 2017/12/22 16:51
 * Description:
 */

public class SecurityCheckPresenter extends BaseMvpPresenter<ISecurityCheckView> {

    private ISecurityCheckModel securityCheckModel;

    public SecurityCheckPresenter() {
        this.securityCheckModel = new SecurityCheckModel();
    }

    public void getSecurityCheckInfoByMmsiOrYwcm(final String mmsi, final String ywcm) {
        if (mView == null) return;
        securityCheckModel.getSecurityCheckInfoByMmsiOrYwcm(mmsi, ywcm, new SecurityCheckModel.OnLoadSecurityCheckListener() {
            @Override
            public void onSuccess(StateControlData stateControlData) {
                mView.setSecurityCheckInfo(stateControlData,mmsi,ywcm);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadSecurityCheckFail(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest(){
        securityCheckModel.onRequestCancel();
    }
}
