package com.winfo.szrsp.app.mvp.exceptionship.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;

/**
 * Created by Guan on 2017-12-21.
 */

public interface IExceptionShipOperateView {

    void addFaild(String msg);

    void addSucceed(String msg);

    void deleteFaild(String msg);

    void deleteSucceed(String msg);
    Dialog getDialog();

    void queryFaild(String msg);
    void setIsOrNotExceptionShip(ExceptionShip exceptionShip);
    void setAisData(Ais aisData);
    void searchAsiFaild(String msg);

    void loginExpired(String msg);
}
