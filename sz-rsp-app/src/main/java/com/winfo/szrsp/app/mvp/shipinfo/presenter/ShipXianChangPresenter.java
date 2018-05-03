package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.IXianChangModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipXianChangModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.IShipXianChangView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;

import java.util.List;

/**
 * Created by HoBo on 2018/4/12.
 */

public class ShipXianChangPresenter extends BaseMvpPresenter<IShipXianChangView> {
    private IXianChangModel model;

    public ShipXianChangPresenter() {
        this.model = new ShipXianChangModel();
    }

    public void getShipXianChangData(final String mmsi, final String cm) {
        if (mView == null) return;
        model.getShipXianChangData(mmsi, cm, new ShipXianChangModel.OnLoadXianChangListener() {
            @Override
            public void onSuccess(ShipXianChangInfo shipXianChangInfos) {
                mView.setXianChangInfo(shipXianChangInfos,mmsi,cm);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadXianChangFail(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest() {
        model.onRequestCancel();
    }
}
