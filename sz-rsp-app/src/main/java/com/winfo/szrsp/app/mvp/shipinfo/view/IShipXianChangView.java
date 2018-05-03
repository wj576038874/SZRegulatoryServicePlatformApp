package com.winfo.szrsp.app.mvp.shipinfo.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;

/**
 * Created by HoBo on 2018/4/12.
 */

public interface IShipXianChangView extends IBaseMvpView {
    void setXianChangInfo(ShipXianChangInfo shipXianChangInfos, String mmsi, String cm);

    void onLoadXianChangFail(String msg);

    void loginExpired(String msg);
}
