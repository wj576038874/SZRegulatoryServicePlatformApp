package com.winfo.szrsp.app.mvp.exceptionship.model;

import android.app.Dialog;

/**
 * Created by Guan on 2017-12-21.
 */

public interface IExceptionShipModel {
    //查询异常船舶数据
    void getExceptionShipListData(Dialog dialog, String pageNum, String pageSize, boolean bol, ExceptionShipModel.OnExceptionShipListListener onExceptionShipListListener);

}
