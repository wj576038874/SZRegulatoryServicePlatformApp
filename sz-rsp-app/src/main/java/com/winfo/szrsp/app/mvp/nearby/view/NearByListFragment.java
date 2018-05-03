package com.winfo.szrsp.app.mvp.nearby.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.winfo.dnc.sdk.Point;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.entity.HistoryThsy;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.mvp.map.searchais.presenter.SearchShipPresenter;
import com.winfo.szrsp.app.mvp.map.searchais.view.ISearchShipView;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.presenter.NavigableElementsPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.mvp.nearby.presenter.NearPreserter;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.thys.ShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysAndShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.Utility;
import com.winfo.szrsp.app.widget.NearbyGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 附近页面
 * Created by HoBo on 2018/4/2.
 */

public class NearByListFragment extends Fragment implements IMapLayerView, ISearchShipView, INearView, View.OnClickListener {
    private ImageButton imgBtnBack;
    private TextView titleText;
    private ImageButton butBack;
    private ImageButton butDown;
    private EditText nearbyEtSearch;// 关键字搜索
    private NearbyGridView thysGrid;
    private NearbyGridView fjcbGrid;
    private Button thysMore;
    private Button shipmore;
    private Dialog dialog; // 加载的dialog
    private NearPreserter nearPreserter;
    private SearchShipPresenter searchShipPresenter;
    private AisPresenter aisPresenter;
    private NavigableElementsPresenter navigableElementsPresenter;
    private List<ThysData> thysData;
    private List<ShipData> shipData;
    // 请求的经纬度参数
    private double longitude;
    private double latitude;
    int magin;
    int width;
    private int type = 1;
    private PopupWindow popupWindow;//选择搜索窗体

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.nearby_fragment, container, false);
        initView(view);
        initEvent();
        initData();
        return view;
    }


    private void initView(View view) {
        thysData = new ArrayList<>();
        shipData = new ArrayList<>();
        imgBtnBack = view.findViewById(R.id.table_titleBar_imgbtn_back);
        titleText = view.findViewById(R.id.table_titleBar_titleText);
        titleText.setText("附近");
        butBack = view.findViewById(R.id.nearby_imgbtn_back);
        butDown = view.findViewById(R.id.img_btn_change);
        nearbyEtSearch = view.findViewById(R.id.nearby_et_search);
        thysGrid = view.findViewById(R.id.nearby_gv_thys);
        fjcbGrid = view.findViewById(R.id.nearby_gv_boats);
        thysMore = view.findViewById(R.id.nearby_btn_thys_more);
        shipmore = view.findViewById(R.id.nearby_btn_boats_more);
        dialog = DialogUtils.createLoadingDialog(getActivity(), "数据加载中...");
        nearPreserter = new NearPreserter(this);
        searchShipPresenter = new SearchShipPresenter();
        searchShipPresenter.attachMvpView(this);
        aisPresenter = new AisPresenter(this);
        navigableElementsPresenter = new NavigableElementsPresenter(this);
    }

    private void initEvent() {
        imgBtnBack.setOnClickListener(this);
        butBack.setOnClickListener(this);
        butDown.setOnClickListener(this);
        thysMore.setOnClickListener(this);
        shipmore.setOnClickListener(this);
        nearbyEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (type == 1) {
                        if (!textView.getText().toString().trim().equals("")) {
                            Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
                            Matcher matcher = pattern.matcher(nearbyEtSearch.getText().toString().trim());
                            if (matcher.matches() && nearbyEtSearch.getText().toString().trim().length() == 9) {
                                searchShipPresenter.searShipByMmsi(nearbyEtSearch.getText().toString().trim());
                            } else {
                                searchShipPresenter.getShipsByName(nearbyEtSearch.getText().toString().trim());
                            }
                        } else {
                            ShowMsg("请输入船名或者mmsi");
                        }
                    } else if (type == 2) {
                        searchShipPresenter.serachNavigableElementsDataByNameCn(nearbyEtSearch.getText().toString().trim());
                    }
                }
                return true;
            }
        });
        thysGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                ToastUtils.showToast(getActivity(), thysData.get(position).getTypeName() + "----" + thysData.get(position).getTypeId() + "-----" + thysData.get(position).getTypePid());
                navigableElementsPresenter.getThysData(null, thysData.get(position).getTypeName(), thysData.get(position).getTypeId());
            }
        });
        fjcbGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                ToastUtils.showToast(getActivity(), shipData.get(position).getShipTypeNameCn());
                aisPresenter.getShipAisData(null, true, shipData.get(position).getShipTypeCode(), shipData.get(position).getShipTypeNameCn());
            }
        });
    }

    private void initData() {
        nearPreserter.getData(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                if (getActivity() instanceof backThisFragment) {
                    ((backThisFragment) getActivity()).popBackStack();
                }
                break;
            case R.id.nearby_imgbtn_back:
                if (getActivity() instanceof backThisFragment) {
                    ((backThisFragment) getActivity()).popBackStack();
                }
                break;
            case R.id.img_btn_change:
                alertPop();
                break;
            case R.id.nearby_btn_thys_more:
                if (thysData != null) {
                    ((LoadMoreFragment) getActivity()).toMoreFragment(thysData, null, 1, longitude, longitude);
                }
                break;
            case R.id.nearby_btn_boats_more:
                if (shipData != null) {
                    ((LoadMoreFragment) getActivity()).toMoreFragment(null, shipData, 2, longitude, longitude);
                }
                break;
        }
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
            ((ShipItemBtnClickListner) getActivity()).onShipItemClick(aisData, null, 2, shipTypeCode, ShipTypeNameCn);
        }
    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {

    }

    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {
        dialog.dismiss();
        if (getActivity() instanceof ThysItemBtnClickListner) {
            ((ThysItemBtnClickListner) getActivity()).onThysItemClick(null, navigationElementsData, 1, typeName, typeId);
        }
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
    public void showMsg(String msg) {

    }

    @Override
    public void setShipPhone(String msg, TelephoneMmsi data) {

    }


    public void ShowMsg(String msg) {
        ToastUtils.showToast(getActivity(), msg);
    }

    @Override
    public Dialog getLoadingDialog() {
        return dialog;
    }

    @Override
    public void setSearchShips(List<ShipSearch> shipSearches) {
        dialog.dismiss();
        ToastUtils.showToast(getActivity(), "搜索船舶成功");
    }

    @Override
    public void setSearchShipByMMSI(Ais ais) {
        dialog.dismiss();
        ToastUtils.showToast(getActivity(), "搜索mmsi船舶成功");
    }

    @Override
    public void setHistoryAis(List<HistoryAis> historyAis) {

    }

    @Override
    public void setHistoryThys(List<HistoryThsy> historyAis) {

    }

    @Override
    public void setNavigableElementsData(List<NavigableElementsData> navigableElementsData) {
        dialog.dismiss();
        ToastUtils.showToast(getActivity(), "通航要素成功");
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(getActivity(), msg, msg);
    }

    @Override
    public void OnSuccess(ThysAndShipData resultData) {
        dialog.dismiss();
        thysData = resultData.getListThysResult().getDatas();
        shipData = resultData.getListShipResult().getDatas();
        ThysAdapter adapter = new ThysAdapter(getActivity(), thysData.subList(0, 9), R.layout.nearby_control_area_gridview_item);
        thysGrid.setAdapter(adapter);
        Utility.setGridViewHeightBasedOnChildren(thysGrid);
        ShipAdapter adapter2 = new ShipAdapter(getActivity(), shipData.subList(0, 9), R.layout.nearby_control_area_gridview_item);
        fjcbGrid.setAdapter(adapter2);
        Utility.setGridViewHeightBasedOnChildren(fjcbGrid);
    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
        ToastUtils.showToast(getActivity(), msg);
    }


    /**
     * 弹出选择搜索类型窗体
     */
    private void alertPop() {
        View view = View.inflate(getActivity(), R.layout.pw_search, null);
        final ImageButton img_cb = (ImageButton) view.findViewById(R.id.img_cb);
        final ImageButton img_thys = (ImageButton) view.findViewById(R.id.img_thys);
        final LinearLayout layout_cb = (LinearLayout) view.findViewById(R.id.lay_cb);
        final LinearLayout layout_thys = (LinearLayout) view.findViewById(R.id.lay_thys);
        // 获取手机屏幕的宽度 单位像素px
        width = DeviceUtils.getScreenSize(getActivity())[0]; // 屏幕宽度（像素）
        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(getActivity(), 10);
        // 初始化popupwindow
        popupWindow = new PopupWindow(view, width - (width - DimensUtils.dp2px(getActivity(), 140)), ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        popupWindow.showAsDropDown(butDown, magin * 5 - popupWindow.getWidth(), DimensUtils.dp2px(getActivity(), -4));

        if (type == 1) {
            img_cb.setVisibility(View.VISIBLE);
            img_thys.setVisibility(View.INVISIBLE);
        } else if (type == 2) {
            img_cb.setVisibility(View.INVISIBLE);
            img_thys.setVisibility(View.VISIBLE);
        }

        layout_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                img_thys.setVisibility(View.VISIBLE);
                img_cb.setVisibility(View.INVISIBLE);
                popupWindow.dismiss();
                nearbyEtSearch.setHint("搜索附近船舶");
            }
        });

        layout_thys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                img_thys.setVisibility(View.VISIBLE);
                img_cb.setVisibility(View.INVISIBLE);
                popupWindow.dismiss();
                nearbyEtSearch.setHint("搜索附近要素");
            }
        });
    }

    /**
     * 列表数据item的点击事件包括（通航要素和船舶）
     *
     * @author winfo-wj
     */
    public interface ShipItemBtnClickListner {
        void onShipItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String shipCode, String shipTypeNameCn);
    }

    public interface ThysItemBtnClickListner {
        void onThysItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String typeName, String typeId);
    }

    public interface LoadMoreFragment {
        /**
         * 跳转到更多的页面
         */

        void toMoreFragment(List<ThysData> thysData, List<ShipData> shipData, int i, double longitude, double longitude1);
    }

    /**
     * 回退栈的操作  弹出当前的fragment
     *
     * @author winfo-wj
     */
    public interface backThisFragment {
        /**
         * 关闭当前fragment弹出栈
         */
        void popBackStack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity activity = (MainActivity) getActivity();
        activity.openDrawLayouy();
    }
}
