package com.winfo.szrsp.app.listener;

import android.app.Activity;
import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.ZoomControlsView;

import java.util.List;

public class ZoomControlClickListenner implements ZoomControlsView.OnZoomControlClickListenner,IMapLayerView {
    private WinfoDNCView winfoDNCView;
    private Activity _Activity;

    private AisPresenter aisPresenter;

    public ZoomControlClickListenner(WinfoDNCView winfoDNCView, Activity activity) {
        this.winfoDNCView = winfoDNCView;
        this._Activity = activity;
        aisPresenter = new AisPresenter(this);
    }

    @Override
    public void in() {
        winfoDNCView.changeLevel(1);

        if (winfoDNCView.isShowShipAis) {
            if (winfoDNCView.getLevel() > 13) {
                aisPresenter.getShipAisData(null, false, null, null);
            } else if (winfoDNCView.getLevel() > 11 && winfoDNCView.getLevel() < 14) {
                aisPresenter.loadNetAisPoint(winfoDNCView.getAisRequestModel(),null, false,null);
            } else {
                aisPresenter.loadNetAisPoint(winfoDNCView.getAisRequestModel(),null, false,null);
            }
        }
    }

    @Override
    public void out() {
        winfoDNCView.changeLevel(2);

        if (winfoDNCView.isShowShipAis) {
            if (winfoDNCView.getLevel() > 13) {
                aisPresenter.getShipAisData(null, false, null, null);
            } else if (winfoDNCView.getLevel() > 11 && winfoDNCView.getLevel() < 14) {
                aisPresenter.loadNetAisPoint(winfoDNCView.getAisRequestModel(),null, false,null);
            } else {
                aisPresenter.loadNetAisPoint(winfoDNCView.getAisRequestModel(),null, false,null);
            }
        }
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(_Activity, msg);
    }


    @Override
    public void setAisData(List<Ais> aisDatas, String typeCode, String shipTypeCode) {
        winfoDNCView.addScreenAisDatas(aisDatas);
        winfoDNCView.invalidate();
    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {
        winfoDNCView.addAllZhifaDatas(aisData);
        winfoDNCView.invalidate();
    }


    @Override
    public AisRequestModel getAisRequestModel() {
        AisRequestModel aisRequestModel = new AisRequestModel();
        Point pointScreenStart = winfoDNCView.getPointScreen()[0];
        Point pointScreenEnd = winfoDNCView.getPointScreen()[1];

        StringBuilder sb1 = new StringBuilder();
        sb1.append(pointScreenStart.lon);
        sb1.append(",");
        sb1.append(pointScreenStart.lat);
        String point1 = sb1.toString();

        StringBuilder sb2 = new StringBuilder();
        sb2.append(pointScreenEnd.lon);
        sb2.append(",");
        sb2.append(pointScreenEnd.lat);
        String point2 = sb2.toString();

        aisRequestModel.setHeight(winfoDNCView.winfoDNCParams.mapViewInfo.vp.Height + "");
        aisRequestModel.setStartPoint(point1);
        aisRequestModel.setStopPoint(point2);
        aisRequestModel.setMinX(winfoDNCView.winfoDNCParams.mapViewInfo.vp.MinX + "");
        aisRequestModel.setMinY(winfoDNCView.winfoDNCParams.mapViewInfo.vp.MinY + "");
        aisRequestModel.setPageSize("2000");
        aisRequestModel.setSpanPerPixel(winfoDNCView.winfoDNCParams.mapViewInfo.vp.SpanPerPixel + "");
        aisRequestModel.setLevel(winfoDNCView.winfoDNCParams.mapViewInfo.vp.Level + "");
        return aisRequestModel;
    }



    @Override
    public void setIsShowAis(boolean bol) {
        winfoDNCView.isShowShipAis = bol;
    }

    @Override
    public void setIsShowAllZhifa(boolean bol) {
        winfoDNCView.isShowaAllZhifa = bol;
    }

    @Override
    public void setIsShowThys(boolean bol) {
        winfoDNCView.isShowThys = bol;
    }
    @Override
    public void setIsShowZhifaPerson(boolean bol) {
        winfoDNCView.isShowZhifaPerson = bol;
    }
    @Override
    public Dialog getDialog() {
        return DialogUtils.createLoadingDialog(_Activity, "加载中...");
    }

    @Override
    public void setZhifaPerson(List<MobileTerminalInfo2> list) {

    }

    @Override
    public void loginExpired(String msg) {

    }

    @Override
    public void setShipPhone(String msg, TelephoneMmsi data) {

    }



    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {
    }
}
