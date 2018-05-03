package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipTrackModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.ShipTrackModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.IShipTrackView;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipInfoPresenter.java
 * Date: 2017/11/28 9:39
 * Description:
 */

public class ShipTrackPresenter extends BaseMvpPresenter<IShipTrackView> {
    private IShipTrackModel shipTrackModel;

    public ShipTrackPresenter() {
        shipTrackModel = new ShipTrackModel();
    }

    public void getShipTrack(Dialog dialog, boolean isShowing, String mmsi, String startTime, String stopTime) {
        if (mView == null) return;
        shipTrackModel.getShipTrack(dialog,isShowing,mmsi, startTime, stopTime, new ShipTrackModel.OnLoadShipTracklitener(){

            @Override
            public void onSuccess(List<TrackData> trackData) {
                mView.setShipTrack(trackData);
            }

            @Override
            public void onFailure(String error) {
                mView.onLoadShipTrackFail(error);
            }
        });
    }

    public void cancelRequest() {
        shipTrackModel.onRequestCancel();
    }

}
