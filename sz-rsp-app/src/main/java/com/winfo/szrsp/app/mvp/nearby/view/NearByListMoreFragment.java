package com.winfo.szrsp.app.mvp.nearby.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.dnc.sdk.Point;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.presenter.NavigableElementsPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.thys.ShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.Utility;
import com.winfo.szrsp.app.widget.NearbyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoBo on 2018/4/3.
 */

public class NearByListMoreFragment extends Fragment implements IMapLayerView {
    private ImageButton imgBtnBack;
    private TextView titleText;
    private NearbyGridView thysGrid;
    private NearbyGridView fjcbGrid;
    private List<ThysData> thysData;
    private List<ShipData> shipData;
    private int type;
    private Dialog dialog; // 加载的dialog
    private NavigableElementsPresenter navigableElementsPresenter;
    private AisPresenter aisPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.nearby_more_fragment, container, false);
        initView(view);
        initEvent();
        initData();
        return view;
    }

    private void initView(View view) {
        thysData = new ArrayList<>();
        shipData = new ArrayList<>();
        aisPresenter = new AisPresenter(this);
        navigableElementsPresenter = new NavigableElementsPresenter(this);
        dialog = DialogUtils.createLoadingDialog(getActivity(), "数据加载中...");
        imgBtnBack = view.findViewById(R.id.table_titleBar_imgbtn_back);
        titleText = view.findViewById(R.id.table_titleBar_titleText);
        thysGrid = view.findViewById(R.id.nearby_gv_thys);
        fjcbGrid = view.findViewById(R.id.nearby_gv_boats);
    }

    private void initEvent() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof backthysFragment) {
                    ((backthysFragment) getActivity()).backFragment();
                }
            }
        });
        thysGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                navigableElementsPresenter.getThysData(null, thysData.get(position).getTypeName(), thysData.get(position).getTypeId());
            }
        });
        fjcbGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                aisPresenter.getShipAisData(null, true, shipData.get(position).getShipTypeCode(), shipData.get(position).getShipTypeNameCn());
            }
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        if (type == 1) {
            titleText.setText("通航要素");
            thysData = (List<ThysData>) bundle.getSerializable("thysList");
            ThysAdapter adapter = new ThysAdapter(getActivity(), thysData, R.layout.nearby_control_area_gridview_item);
            thysGrid.setAdapter(adapter);
            Utility.setGridViewHeightBasedOnChildren(thysGrid);
        } else if (type == 2) {
            titleText.setText("附近船舶");
            shipData = (List<ShipData>) bundle.getSerializable("shipList");
            ShipAdapter adapter2 = new ShipAdapter(getActivity(), shipData, R.layout.nearby_control_area_gridview_item);
            fjcbGrid.setAdapter(adapter2);
            Utility.setGridViewHeightBasedOnChildren(fjcbGrid);
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public AisRequestModel getAisRequestModel() {
        AisRequestModel aisRequestModel = new AisRequestModel();
        Point pointScreenStart = SzRspApplication.getWinfoDNCView().getPointScreen()[0];
        Point pointScreenEnd = SzRspApplication.getWinfoDNCView().getPointScreen()[1];
        String point1 = String.valueOf(pointScreenStart.lon) + "," + pointScreenStart.lat;
        String point2 = String.valueOf(pointScreenEnd.lon) + "," + pointScreenEnd.lat;
        aisRequestModel.setHeight(SzRspApplication.getWinfoDNCView().winfoDNCParams.mapViewInfo.vp.Height + "");
        aisRequestModel.setStartPoint(point1);
        aisRequestModel.setStopPoint(point2);
        aisRequestModel.setMinX(SzRspApplication.getWinfoDNCView().winfoDNCParams.mapViewInfo.vp.MinX + "");
        aisRequestModel.setMinY(SzRspApplication.getWinfoDNCView().winfoDNCParams.mapViewInfo.vp.MinY + "");
        aisRequestModel.setPageSize("2000");
        aisRequestModel.setSpanPerPixel(SzRspApplication.getWinfoDNCView().winfoDNCParams.mapViewInfo.vp.SpanPerPixel + "");
        aisRequestModel.setLevel(SzRspApplication.getWinfoDNCView().winfoDNCParams.mapViewInfo.vp.Level + "");
        return aisRequestModel;
    }


    @Override
    public void setAisData(List<Ais> aisData, String shipTypeCode, String ShipTypeNameCn) {
        dialog.dismiss();
        if (getActivity() instanceof ShipItemBtnClickListner) {
            ((ShipItemBtnClickListner) getActivity()).onShipMoreItemClick(aisData, null, 2, shipTypeCode, ShipTypeNameCn);
        }
    }

    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {
        dialog.dismiss();
        if (getActivity() instanceof ThysItemBtnClickListner) {
            ((ThysItemBtnClickListner) getActivity()).onThysMoreItemClick(null, navigationElementsData, 1, typeName, typeId);
        }
    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {

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
        return dialog;
    }

    @Override
    public void setZhifaPerson(List<MobileTerminalInfo2> list) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(getActivity(), msg, msg);
    }

    @Override
    public void setShipPhone(String msg, TelephoneMmsi data) {

    }


    public interface backthysFragment {
        void backFragment();
    }

    /**
     * 列表数据item的点击事件包括（通航要素和船舶）
     *
     * @author winfo-wj
     */
    public interface ShipItemBtnClickListner {
        void onShipMoreItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String shipCode, String shipTypeNameCn);
    }

    public interface ThysItemBtnClickListner {
        void onThysMoreItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String typeName, String typeId);
    }
}
