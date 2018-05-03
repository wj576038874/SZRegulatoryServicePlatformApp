package com.winfo.szrsp.app.mvp.shipinfo.model;

import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipXianChangModel;
import com.winfo.szrsp.app.sdk.http.RequestCancelListener;

/**
 * Created by HoBo on 2018/4/12.
 */

public interface IXianChangModel extends RequestCancelListener {

    void getShipXianChangData(String mmsi, String cm, ShipXianChangModel.OnLoadXianChangListener onLoadXianChangListener);
}
