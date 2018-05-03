package com.winfo.szrsp.app.mvp.task.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.listener.ZoomControlClickListenner;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.mvp.shipinfo.view.ShipInfoLayout;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.widget.ZoomControlsView;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.task.view
 * @Filename: ShipMapActivity
 * @Author: lsj
 * @Date: 2017/12/20  11:38
 * @Description:
 * @Version:船位
 */
public class ShipMapActivity extends Activity implements IMapLayerView {
    private View titleView;
    private ImageButton back_btn;
    private TextView tv_title;
    private WinfoDNCView winfoDNCView;
    private ZoomControlsView zoomControlsView;
    private Ais ais;
    private AisPresenter aisPresenter;
    // 主布局
    private CoordinatorLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipmap);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        zoomControlsView.setOnZoomControlClickListenner(new ZoomControlClickListenner(winfoDNCView, this));
//        shipInfoLayout.setShowAisData(ais);
        mHandler.sendEmptyMessageDelayed(1000,50);
    }
    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what==1000){
                shipInfoLayout.setShowAisData(ais);
            }

            return false;
        }
    });


    @SuppressLint("SetTextI18n")
    private void initData() {
        tv_title.setText("船舶位置");
//        ais = (Ais) DataHolder.getInstance().retrieve("AisData");
        ais = (Ais) getIntent().getSerializableExtra("AisData");
        if (PreferenceUtils.getBoolean(this, "islogin", false)) {
            aisPresenter.getPhoneByMmsi(ais.getID(), false);
        }
        if (ais.getIMO().equals("0")) {
            ais.setIMO("");
        }
        winfoDNCView.addTaskAisData(ais);
        winfoDNCView.isTaskShip = true;
        winfoDNCView.type = 1;
        winfoDNCView.isShipMapActivity = false;
        winfoDNCView.locationTaskShip(ais);
        winfoDNCView.refreshMap();

    }

    ShipInfoLayout shipInfoLayout;

    private void initView() {
//        rl_top = findViewById(R.id.rl_top);
//        rl_top.setVisibility(View.GONE);
        aisPresenter = new AisPresenter(this);
        mainLayout = findViewById(R.id.main);
        shipInfoLayout = new ShipInfoLayout(this, winfoDNCView, null, null);
        mainLayout.addView(shipInfoLayout);
        titleView = findViewById(R.id.title);
        tv_title = titleView.findViewById(R.id.table_titleBar_titleText);
        back_btn = titleView.findViewById(R.id.table_titleBar_imgbtn_back);
        winfoDNCView = findViewById(R.id.shipmap_map);
        zoomControlsView = findViewById(R.id.shipmap_zoom);
        shipInfoLayout.setRLTopGONE();
    }

    public void showAisData() {
        shipInfoLayout.setShowAisData(ais);
    }

    public void dissmissAisData() {
        shipInfoLayout.getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public AisRequestModel getAisRequestModel() {
        return null;
    }

    @Override
    public void setAisData(List<Ais> aisData, String shipTypeCode, String shipTypeNameCn) {

    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {

    }

    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {

    }

    @Override
    public void setIsShowAis(boolean bol) {

    }

    @Override
    public void setIsShowAllZhifa(boolean bol) {

    }

    @Override
    public void setIsShowThys(boolean bol) {

    }

    @Override
    public void setIsShowZhifaPerson(boolean bol) {

    }

    @Override
    public Dialog getDialog() {
        return null;
    }

    @Override
    public void setZhifaPerson(List<MobileTerminalInfo2> list) {

    }

    @Override
    public void loginExpired(String msg) {

    }

    @Override
    public void setShipPhone(String msg, TelephoneMmsi data) {
        shipInfoLayout.showShipPhone(data);
    }
}
