package com.winfo.szrsp.app.mvp.maplayer.model;


import android.app.Dialog;

/**
 * Created by HoBo on 2018/3/30.
 */

public interface IUpdataTelModel {

    void updataPhoneByMmsi(Dialog dialog, String mmsi, String telephoneNum, String teleName, boolean isShowDialog, UpdataTelModel.OnUpdataPhoneDataLitener onUpdataPhoneDataLitener);
}
