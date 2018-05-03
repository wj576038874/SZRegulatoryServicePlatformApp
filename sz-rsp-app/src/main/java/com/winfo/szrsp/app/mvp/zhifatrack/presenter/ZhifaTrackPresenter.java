package com.winfo.szrsp.app.mvp.zhifatrack.presenter;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.zhifatrack.model.IZhifaTrackModel;
import com.winfo.szrsp.app.mvp.zhifatrack.model.ZhifaTrackModel;
import com.winfo.szrsp.app.mvp.zhifatrack.view.IZhifaTrackView;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalHistory;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipInfoPresenter.java
 * Date: 2017/11/28 9:39
 * Description:
 */

public class ZhifaTrackPresenter extends BaseMvpPresenter<IZhifaTrackView> {
    private IZhifaTrackModel zhifaTrackModel;

    public ZhifaTrackPresenter() {
        zhifaTrackModel = new ZhifaTrackModel();
    }

    public void getZhifaTrack(Dialog dialog, boolean isShowing, String uuid, String startTime, String stopTime) {
        if (mView == null) return;
        zhifaTrackModel.getZhifaTrack(dialog,isShowing,uuid, startTime, stopTime, new ZhifaTrackModel.OnLoadZhifaTracklitener(){

            @Override
            public void onSuccess(List<MobileTerminalHistory> trackData) {
                mView.setZhifaTrack(trackData);
            }

            @Override
            public void onFailure(String error) {
                mView.onLoadZhifaTrackFail(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest() {
        zhifaTrackModel.onRequestCancel();
    }

}
