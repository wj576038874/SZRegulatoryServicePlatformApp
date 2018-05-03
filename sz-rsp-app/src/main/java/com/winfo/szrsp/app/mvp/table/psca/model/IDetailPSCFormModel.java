package com.winfo.szrsp.app.mvp.table.psca.model;


import android.app.Dialog;

/**
 * Created by wly on 2017/12/22.
 *
 */

public interface IDetailPSCFormModel {


        void loadPSCFormDetailData(Dialog dialog, String inspectNo, DetailPSCFormModel.OnLoadPSCFormDetailDataListener onLoadPSCFormDetailDataListener);

}
