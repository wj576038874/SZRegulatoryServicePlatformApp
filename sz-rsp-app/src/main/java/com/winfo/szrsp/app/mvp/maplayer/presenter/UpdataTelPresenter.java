package com.winfo.szrsp.app.mvp.maplayer.presenter;

import android.content.Context;

import com.winfo.szrsp.app.mvp.maplayer.model.AisModel;
import com.winfo.szrsp.app.mvp.maplayer.model.IUpdataTelModel;
import com.winfo.szrsp.app.mvp.maplayer.model.UpdataTelModel;
import com.winfo.szrsp.app.mvp.maplayer.view.IUpdataTelView;

/**
 * Created by HoBo on 2018/3/30.
 */

public class UpdataTelPresenter {
    private IUpdataTelView view;
    private IUpdataTelModel model;

    public UpdataTelPresenter(IUpdataTelView mContext) {
        this.view = mContext;
        this.model = new UpdataTelModel();
    }

    /**
     * 修改号码
     */
    public void updataPhoneByMmsi(String mmsi, String telephoneNum, String teleName, boolean isShowDialog) {
        model.updataPhoneByMmsi(view.getDialog(), mmsi, telephoneNum, teleName, isShowDialog, new UpdataTelModel.OnUpdataPhoneDataLitener() {
            @Override
            public void onSuccess(String msg, String data) {
                view.OnSuccess(msg, data);
            }

            @Override
            public void onFailure(String msg) {
                view.OnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                view.loginExpired(msg);
            }
        });
    }
}
