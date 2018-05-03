package com.winfo.szrsp.app.mvp.nearby.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.IWinfoDNCView;
import com.winfo.szrsp.app.activity.MainFragment;
import com.winfo.szrsp.app.adapter.nearby.NearbyThysAdapter;
import com.winfo.szrsp.app.pulltorfesh.PullToRefreshLayout;
import com.winfo.szrsp.app.pulltorfesh.PullableListView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.ListPageUtil;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.TransformUtil;
import com.winfo.szrsp.app.utils.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 附近Item点击查询结果页面
 * Created by HoBo on 2018/4/4.
 */

public class NearByItemResultFragment extends Fragment implements View.OnClickListener {
    private EditText editText;
    private TextView contrilarea_page;
    private PullToRefreshLayout refreshLayout;
    private PullableListView dataListView;
    private Button fanweiBut;
    private LinearLayout nodataLin;
    private ImageButton btnlocation;
    private ImageButton imgbtn_main_location;
    private TextView nearby_tv_titleText;
    private SlidingDrawer mDrawer;
    private List<Ais> aisList;//所有船舶信息
    private List<Ais> shipAisList;//分类船舶信息
    private List<NavigableElementsData> nearThysList;//所有通航要素信息
    private List<NavigableElementsData> thysList;//分类通航要素信息
    private List<Ais> shipList5;//船舶5000米
    private List<Ais> shipList2;//船舶2000米
    private List<Ais> shipList1;//船舶1000米
    private List<NavigableElementsData> thysList5;//通航要素5000米
    private List<NavigableElementsData> thysList2;//通航要素2000米
    private List<NavigableElementsData> thysList1;//通航要素1000米
    private int type;
    private String shipCode;
    private String shipName;
    private String typeName;
    private String typeId;
    private NearbyShipAapter shipAdapter;
    private NearbyThysAdapter thysAdapter;
    private IWinfoDNCView iWinfoDNCView;
    private WinfoDNCView winfoDNCView;
    View distanceView;//布局
    TextView tv_distance_a;
    TextView tv_distance_b;
    TextView tv_distance_c;
    int width;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;
    private PopupWindow distancePopupWindow;//控制距离的pop
    private String range;
    //长按点的经纬度
    private double longitude;
    private double latitude;
    private int totalNum = 0;//总条数
    private int pageTotalCount = 0;//总页数
    private int pageIndex = 1;//当前页数
    private ShowThysDialog thysDialog;//点击通航要素弹窗
    private ShipAisView shipAisView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        winfoDNCView = iWinfoDNCView.getWinfoDNCView();
        WinfoDNCView.isShowNearby = true;
        if (winfoDNCView.showLongPressPoint) {
            longitude = winfoDNCView.winfoDNCParams.nearbyPoint.lon;
            latitude = winfoDNCView.winfoDNCParams.nearbyPoint.lat;
        } else {
            longitude = winfoDNCView.winfoDNCParams.myLocationPoint.lon;
            latitude = winfoDNCView.winfoDNCParams.myLocationPoint.lat;
        }
        View view = inflater.inflate(R.layout.nearby_item_result_fragment, container, false);
        initView(view);
        initEvent();
        initData();
        return view;
    }

    @SuppressLint({"WrongViewCast", "CutPasteId"})
    private void initView(View view) {
        aisList = new ArrayList<>();
        shipAisList = new ArrayList<>();
        nearThysList = new ArrayList<>();
        thysList = new ArrayList<>();
        shipList5 = new ArrayList<>();
        shipList2 = new ArrayList<>();
        shipList1 = new ArrayList<>();
        thysList5 = new ArrayList<>();
        thysList2 = new ArrayList<>();
        thysList1 = new ArrayList<>();
        editText = view.findViewById(R.id.id_nearbydata_etSearch);
        imgbtn_main_location = view.findViewById(R.id.imgbtn_main_location);
        contrilarea_page = view.findViewById(R.id.contrilarea_page);
        nearby_tv_titleText = view.findViewById(R.id.nearby_tv_titleText);
        btnlocation = view.findViewById(R.id.imgbtn_main_location);
        dataListView = view.findViewById(R.id.lv_area);
        dataListView.setDivider(null);
        fanweiBut = view.findViewById(R.id.range);
        nodataLin = view.findViewById(R.id.nearby_no_datas);
        mDrawer = (SlidingDrawer) view.findViewById(R.id.pm_drawer);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new MyListener());
        int displayHeight; //屏幕高度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayHeight = displayMetrics.heightPixels;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                (int) (displayHeight * 0.5f + 0.5f));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mDrawer.setLayoutParams(params);
        mDrawer.animateOpen();
    }

    private void initEvent() {
        fanweiBut.setOnClickListener(this);
        editText.setOnClickListener(this);
        imgbtn_main_location.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");//type 1通航要素2船舶
            if (type == 1) {
                range = "5000";
                typeName = bundle.getString("typeName");
                typeId = bundle.getString("typeId");
//                nearThysList = (List<NavigableElementsData>) bundle.getSerializable("nearthysData");
                nearThysList = winfoDNCView.winfoDNCParams.thysjsonData;
                editText.setHint(typeName);
                if (nearThysList.size() == 0 || nearThysList == null) {
                    dataListView.setVisibility(View.GONE);
                    nodataLin.setVisibility(View.VISIBLE);
                } else {
                    thysList.clear();
                    for (int i = 0; i < nearThysList.size(); i++) {
                        if (nearThysList.get(i).getTypeId().startsWith("NE0001") && typeId.equals("NE0001")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0001000004") && typeId.equals("NE0001000004")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0001000005") && typeId.equals("NE0001000005")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0001000006") && typeId.equals("NE0001000006")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0001000007") && typeId.equals("NE0001000007")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0001000008") && typeId.equals("NE0001000008")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().startsWith("NE0002") && typeId.equals("NE0002")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000002") && typeId.equals("NE0002000002")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000003") && typeId.equals("NE0002000003")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000004") && typeId.equals("NE0002000004")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000005") && typeId.equals("NE0002000005")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000006") && typeId.equals("NE0002000006")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000007") && typeId.equals("NE0002000007")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000008") && typeId.equals("NE0002000008")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000009") && typeId.equals("NE0002000009")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000010") && typeId.equals("NE0002000010")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000011") && typeId.equals("NE0002000011")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000012") && typeId.equals("NE0002000012")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000013") && typeId.equals("NE0002000013")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000014") && typeId.equals("NE0002000014")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000015") && typeId.equals("NE0002000015")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000016") && typeId.equals("NE0002000016")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000017") && typeId.equals("NE0002000017")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000020") && typeId.equals("NE0002000020")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000021") && typeId.equals("NE0002000021")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000022") && typeId.equals("NE0002000022")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000023") && typeId.equals("NE0002000023")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        } else if (nearThysList.get(i).getTypeId().equals("NE0002000024") && typeId.equals("NE0002000024")) {
                            thysList.add(nearThysList.get(i));
                            for (int j = 0; j < thysList.size(); j++) {
                                thysList.get(j).setDistance(getThysDistance(thysList.get(j).getCenterLatLon()));
                            }
                            arrayThysDistance(thysList);
                            selectThysDistance(thysList);
                        }
                    }
                    if (thysList5.size() == 0 || thysList5 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = thysList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = thysList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList5, pageIndex, 10);
                        thysAdapter = new NearbyThysAdapter(getActivity(), listPage.getPagedList(), R.layout.nearby_list_thys);
                        dataListView.setAdapter(thysAdapter);
                        Utility.setListViewHeightBasedOnChildren(dataListView);
                        locationThys(winfoDNCView, listPage.getPagedList());
                        changeButton(pageIndex);
                    }
                }
            } else if (type == 2) {
                range = "5000";
                shipName = bundle.getString("shipTypeNameCn");
                shipCode = bundle.getString("shipCode");
//                aisList = (List<Ais>) bundle.getSerializable("aisList");
                aisList = winfoDNCView.winfoDNCParams.nearShipData;
                editText.setHint(shipName);
                if (aisList.size() == 0 || aisList == null) {
                    dataListView.setVisibility(View.GONE);
                    nodataLin.setVisibility(View.VISIBLE);
                } else {
                    shipAisList.clear();
                    for (int i = 0; i < aisList.size(); i++) {
                        if (aisList.get(i).getSZCLX().startsWith("01") && shipCode.equals("0100")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);//对数据进行排序
                            selectShipDistance(shipAisList);//对数据进行距离筛选
                        } else if (aisList.get(i).getSZCLX().equals("0101") && shipCode.equals("0101")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0102") && shipCode.equals("0102")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0103") && shipCode.equals("0103")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0104") && shipCode.equals("0104")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0105") && shipCode.equals("0105")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0106") && shipCode.equals("0106")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0107") && shipCode.equals("0107")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0108") && shipCode.equals("0108")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0109") && shipCode.equals("0109")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0110") && shipCode.equals("0110")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0111") && shipCode.equals("0111")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0112") && shipCode.equals("0112")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("02") && shipCode.equals("0200")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0201") && shipCode.equals("0201")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0202") && shipCode.equals("0202")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0203") && shipCode.equals("0203")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0204") && shipCode.equals("0204")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0205") && shipCode.equals("0205")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0206") && shipCode.equals("0206")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0207") && shipCode.equals("0207")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0208") && shipCode.equals("0208")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0209") && shipCode.equals("0209")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0210") && shipCode.equals("0210")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0211") && shipCode.equals("0211")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0212") && shipCode.equals("0212")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0213") && shipCode.equals("0213")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0214") && shipCode.equals("0214")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0215") && shipCode.equals("0215")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0216") && shipCode.equals("0216")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0217") && shipCode.equals("0217")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("03") && shipCode.equals("0300")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0301") && shipCode.equals("0301")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0302") && shipCode.equals("0302")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0303") && shipCode.equals("0303")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0304") && shipCode.equals("0304")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0305") && shipCode.equals("0305")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0306") && shipCode.equals("0306")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0307") && shipCode.equals("0307")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("04") && shipCode.equals("0400")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0401") && shipCode.equals("0401")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0402") && shipCode.equals("0402")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0403") && shipCode.equals("0403")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0404") && shipCode.equals("0404")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0405") && shipCode.equals("0405")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0406") && shipCode.equals("0406")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0407") && shipCode.equals("0407")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0408") && shipCode.equals("0408")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0409") && shipCode.equals("0409")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0410") && shipCode.equals("0410")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0411") && shipCode.equals("0411")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0412") && shipCode.equals("0412")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0413") && shipCode.equals("0413")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0414") && shipCode.equals("0414")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("05") && shipCode.equals("0500")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0501") && shipCode.equals("0501")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0502") && shipCode.equals("0502")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0503") && shipCode.equals("0503")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0504") && shipCode.equals("0504")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0505") && shipCode.equals("0505")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0506") && shipCode.equals("0506")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("06") && shipCode.equals("0600")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0601") && shipCode.equals("0601")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0602") && shipCode.equals("0602")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("90") && shipCode.equals("9001")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().startsWith("09") && shipCode.equals("0900")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0901") && shipCode.equals("0901")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0902") && shipCode.equals("0902")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0903") && shipCode.equals("0903")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0904") && shipCode.equals("0904")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0905") && shipCode.equals("0905")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0906") && shipCode.equals("0906")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0907") && shipCode.equals("0907")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0908") && shipCode.equals("0908")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0909") && shipCode.equals("0909")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0910") && shipCode.equals("0910")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0911") && shipCode.equals("0911")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0912") && shipCode.equals("0912")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0913") && shipCode.equals("0913")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("0914") && shipCode.equals("0914")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0900")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0901")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0902")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0903")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0904")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0905")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0906")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0907")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0908")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0909")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0910")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0911")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0912")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0913")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("") && shipCode.equals("0914")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        } else if (aisList.get(i).getSZCLX().equals("90") && shipCode.startsWith("9001")) {
                            shipAisList.add(aisList.get(i));
                            for (int j = 0; j < shipAisList.size(); j++) {
                                shipAisList.get(j).setDistance(getShipDistance(shipAisList.get(j).getJD(), shipAisList.get(j).getWD()));
                            }
                            arrayShipDistance(shipAisList);
                            selectShipDistance(shipAisList);
                        }
                    }
                    if (shipList5.size() == 0 || shipList5 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = shipList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = shipList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");

                        ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList5, pageIndex, 10);
                        shipAdapter = new NearbyShipAapter(getActivity(), listPage.getPagedList(), R.layout.nearby_list_ship);
                        dataListView.setAdapter(shipAdapter);
                        Utility.setListViewHeightBasedOnChildren(dataListView);
                        locationShip(winfoDNCView, listPage.getPagedList());
                        changeButton(pageIndex);
                    }
                }
            }
            dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    if (type == 1) {//通航要素
                        //点击数据定位时解开不锁定当前位置   并且更换图标
                        winfoDNCView.winfoDNCParams.isLockAngle = false;
                        btnlocation.setImageResource(R.mipmap.location_pressedl_rp);
                        //点击前将所有的文本设置成黑色
                        for (int i = 0; i < dataListView.getChildCount(); i++) {
                            View vv = dataListView.getChildAt(i);
                            TextView tv = (TextView) vv.findViewById(R.id.nearby_list_item_name_thy);
                            tv.setTextColor(Color.BLACK);
                        }
                        TextView v = (TextView) view.findViewById(R.id.nearby_list_item_name_thy);
                        if (v == null) {
                            return;
                        }
                        v.setTextColor(Color.RED);
                        PreferenceUtils.setInt(getActivity(), "nearbyposition", position);
                        NavigableElementsData thys = (NavigableElementsData) v.getTag();
                        CustomTarget customTarget = new CustomTarget();
                        String lonlat = thys.getCenterLatLon();
                        String[] ll = lonlat.split(",");
                        Point point = new Point();
                        point.lon = Double.valueOf(ll[0]);
                        point.lat = Double.valueOf(ll[1]);
                        customTarget.setPoint(point);
                        customTarget.setPosition(position);
                        winfoDNCView.setTarget(customTarget);
                        thysAdapter.notifyDataSetInvalidated();
                    } else if (type == 2) {//船舶
                        //点击数据定位时解开不锁定当前位置   并且更换图标
                        winfoDNCView.winfoDNCParams.isLockAngle = false;
                        btnlocation.setImageResource(R.mipmap.location_pressedl_rp);
                        //点击前将所有的文本设置成黑色
                        for (int i = 0; i < dataListView.getChildCount(); i++) {
                            View vv = dataListView.getChildAt(i);
                            TextView tv = (TextView) vv.findViewById(R.id.nearby_list_item_names);
                            tv.setTextColor(Color.BLACK);
                        }
                        TextView v = (TextView) view.findViewById(R.id.nearby_list_item_names);
                        if (v == null) {
                            return;
                        }
                        v.setTextColor(Color.RED);
                        PreferenceUtils.setInt(getActivity(), "nearbyposition", position);
                        Ais ship = (Ais) v.getTag();
                        CustomTarget customTarget = new CustomTarget();
                        Point point = new Point();
                        point.lon = ship.getJD();
                        point.lat = ship.getWD();
                        customTarget.setPoint(point);
                        customTarget.setPosition(position);
                        winfoDNCView.setTarget(customTarget);
                        shipAdapter.notifyDataSetInvalidated();
                    }
                }
            });
        }
    }

    public void setiGetWinfoDNCView(IWinfoDNCView iWinfoDNCView) {
        this.iWinfoDNCView = iWinfoDNCView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_nearbydata_etSearch:
                if (getActivity() instanceof backThisFragment) {
                    ((backThisFragment) getActivity()).popBackStack();
                }
                break;
            case R.id.range:
                alertpopwindow(fanweiBut);
                break;
            case R.id.imgbtn_main_location:
                //没有锁定 则锁定并更换图标为锁定图标
                if (!winfoDNCView.winfoDNCParams.isLockAngle) {
                    winfoDNCView.winfoDNCParams.isLockAngle = true;
                    imgbtn_main_location.setImageResource(R.mipmap.location_pressedl_rp);
                    if (getActivity() instanceof MainFragment.LocationBntClickListener) {
                        ((MainFragment.LocationBntClickListener) getActivity()).location();
                    }
                } else {
                    winfoDNCView.winfoDNCParams.isLockAngle = false;
                    imgbtn_main_location.setImageResource(R.mipmap.leftbar_location);
                }
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        winfoDNCView.isOpenShipTrack = false;
        winfoDNCView.winfoDNCParams.trackPointList = null;
        winfoDNCView.isShowShipTrack = false;
        winfoDNCView.setOnTouchListener(null);
        winfoDNCView.isShowNearby = false;
        winfoDNCView.isShowNearbyThys = false;
        winfoDNCView.isShowNearbyShips = false;
        aisList.clear();
        shipAisList.clear();
        nearThysList.clear();
        thysList.clear();
        shipList5.clear();
        shipList2.clear();
        shipList1.clear();
        thysList5.clear();
        thysList2.clear();
        thysList1.clear();
        winfoDNCView.refreshMap();
        pageIndex = 0;
    }

    /**
     * 对附近船舶距离排序
     */
    private void arrayShipDistance(List<Ais> shipAisList) {
        Collections.sort(shipAisList, new Comparator<Ais>() {
            @Override
            public int compare(Ais t0, Ais t1) {
                if (t0.getDistance() > t1.getDistance()) {
                    return 1;
                }
                if (t0.getDistance() == t1.getDistance()) {
                    return 0;
                }
                return -1;
            }
        });
    }

    /**
     * 对附近要素距离排序
     *
     * @param thysList
     */
    private void arrayThysDistance(List<NavigableElementsData> thysList) {
        Collections.sort(thysList, new Comparator<NavigableElementsData>() {
            @Override
            public int compare(NavigableElementsData t0, NavigableElementsData t1) {
                if (t0.getDistance() > t1.getDistance()) {
                    return 1;
                }
                if (t0.getDistance() == t1.getDistance()) {
                    return 0;
                }
                return -1;
            }
        });
    }

    /**
     * 筛选附近船舶数据(5000 2000 1000)
     *
     * @param shipAisList
     */
    private void selectShipDistance(List<Ais> shipAisList) {
        shipList5.clear();
        shipList2.clear();
        shipList1.clear();
        for (int i = 0; i < shipAisList.size(); i++) {
            if (shipAisList.get(i).getDistance() <= 5000.00) {
                shipList5.add(shipAisList.get(i));
            }
            if (shipAisList.get(i).getDistance() <= 2000.00) {
                shipList2.add(shipAisList.get(i));
            }
            if (shipAisList.get(i).getDistance() <= 1000.00) {
                shipList1.add(shipAisList.get(i));
            }
        }
    }

    /**
     * 筛选附近通航要素(5000 2000 1000)
     *
     * @param
     */
    private void selectThysDistance(List<NavigableElementsData> thysList) {
        thysList5.clear();
        thysList2.clear();
        thysList1.clear();
        for (int i = 0; i < thysList.size(); i++) {
            if (thysList.get(i).getDistance() <= 5000.00) {
                thysList5.add(thysList.get(i));
            }
            if (thysList.get(i).getDistance() <= 2000.00) {
                thysList2.add(thysList.get(i));
            }
            if (thysList.get(i).getDistance() <= 1000.00) {
                thysList1.add(thysList.get(i));
            }
        }
    }

    /**
     * 计算当前坐标与船坐标的距离
     */
    private Double getShipDistance(double jd, double wd) {
        double distance = TransformUtil.getDistance(jd, wd, longitude, latitude);
        return distance;
    }

    /**
     * 计算当前坐标与通航要素的距离
     */
    private Double getThysDistance(String centerLatLon) {
        String[] latLon = centerLatLon.split(",");
        return TransformUtil.getDistance(Double.valueOf(latLon[0]), Double.valueOf(latLon[1]), longitude, latitude);
    }

    /**
     * 定位当前页的所有的船舶
     */
    public void locationShip(final WinfoDNCView winfoDNCView, List<Ais> shipList) {
        if (shipList == null) {
            return;
        }
        winfoDNCView.addNearbyShips(shipList);
    }

    /**
     * 定位当前页的所有的通航要素
     */
    public void locationThys(final WinfoDNCView winfoDNCView, List<NavigableElementsData> thysList) {
        if (thysList == null) {
            return;
        }
        winfoDNCView.addNearbyNavigationElements(thysList);
    }

    /**
     * 弹出控制范围的窗体
     *
     */
    @SuppressLint("ResourceAsColor")
    private void alertpopwindow(final Button rangeButton) {
        //初始化加载视图的xml文件
        distanceView = View.inflate(getActivity(), R.layout.pw_distence_layer, null);
        tv_distance_a = (TextView) distanceView.findViewById(R.id.distance_a);
        tv_distance_b = (TextView) distanceView.findViewById(R.id.distance_b);
        tv_distance_c = (TextView) distanceView.findViewById(R.id.distance_c);
        final ImageButton img_dis_a = (ImageButton) distanceView.findViewById(R.id.img_dis_a);
        final ImageButton img_dis_b = (ImageButton) distanceView.findViewById(R.id.img_dis_b);
        final ImageButton img_dis_c = (ImageButton) distanceView.findViewById(R.id.img_dis_c);
        // 获取手机屏幕的宽度 单位像素px
        width = DeviceUtils.getScreenSize(getActivity())[0]; // 屏幕宽度（像素）
        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
//        int magin = DimensUtils.dp2px(getActivity(), 10);
        this.activityAlphaParams = getActivity().getWindow().getAttributes();
        // 初始化popupwindow
        distancePopupWindow = new PopupWindow(distanceView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        distancePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        distancePopupWindow.setFocusable(true);// 设置焦点
        distancePopupWindow.setAnimationStyle(R.style.popwin_anim_style); // 动画效果
        distancePopupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        distancePopupWindow.showAsDropDown(rangeButton);
        if (distancePopupWindow.isShowing()) {
            activityAlphaParams.alpha = 0.5f;
            getActivity().getWindow().setAttributes(activityAlphaParams);
        }
        distancePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                activityAlphaParams.alpha = 1f;
                getActivity().getWindow().setAttributes(activityAlphaParams);
            }
        });

        switch (range) {
            case "1000":
                img_dis_a.setVisibility(View.VISIBLE);
                tv_distance_a.setTextColor(Color.parseColor("#333333"));
                break;
            case "2000":
                img_dis_b.setVisibility(View.VISIBLE);
                tv_distance_b.setTextColor(Color.parseColor("#333333"));
                break;
            case "5000":
                img_dis_c.setVisibility(View.VISIBLE);
                tv_distance_c.setTextColor(Color.parseColor("#333333"));
                break;
        }

        tv_distance_a.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                distancePopupWindow.dismiss();
                winfoDNCView.isShowNearbyThys = false;
                winfoDNCView.isShowNearbyShips = false;
                if (type == 1) {
                    if (thysList1.size() == 0 || thysList1 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = thysList1.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "1000";
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = thysList1.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "1000";
                        ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList1, pageIndex, 10);
//                        thysAdapter = new NearbyThysAdapter(getActivity(), listPage.getPagedList(), R.layout.nearby_list_thys);
//                        dataListView.setAdapter(thysAdapter);
//                        thysAdapter.notifyDataSetChanged();
//                        Utility.setListViewHeightBasedOnChildren(dataListView);
//                        locationThys(winfoDNCView, listPage.getPagedList());
                        thysAdapter.chengeDatas(listPage.getPagedList());
                        thysAdapter.notifyDataSetChanged();
                        locationThys(winfoDNCView, listPage.getPagedList());
                        changeState(1);
                        changeButton(pageIndex);
                    }
                } else if (type == 2) {
                    if (shipList1.size() == 0 || shipList1 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = shipList1.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "1000";
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = shipList1.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "1000";
                        ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList1, pageIndex, 10);
                        shipAdapter.chengeDatas(listPage.getPagedList());
                        shipAdapter.notifyDataSetChanged();
                        locationShip(winfoDNCView, listPage.getPagedList());
                        changeState(1);
//                        changType(3);
                        changeButton(pageIndex);
                    }
                }
            }
        });

        tv_distance_b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                distancePopupWindow.dismiss();
                winfoDNCView.isShowNearbyThys = false;
                winfoDNCView.isShowNearbyShips = false;
                if (type == 1) {
                    if (thysList2.size() == 0 || thysList2 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = thysList2.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "2000";
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = thysList2.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "2000";
                        ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList2, pageIndex, 10);
                        thysAdapter.chengeDatas(listPage.getPagedList());
                        thysAdapter.notifyDataSetChanged();
                        locationThys(winfoDNCView, listPage.getPagedList());
                        changeState(1);
                        changeButton(pageIndex);
                    }
                } else if (type == 2) {
                    if (shipList2.size() == 0 || shipList2 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = shipList2.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "2000";
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = shipList2.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "2000";
                        ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList2, pageIndex, 10);
                        shipAdapter.chengeDatas(listPage.getPagedList());
                        shipAdapter.notifyDataSetChanged();
                        locationShip(winfoDNCView, listPage.getPagedList());
                        changeState(1);
//                        changType(3);
                        changeButton(pageIndex);
                    }
                }
            }
        });

        tv_distance_c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                distancePopupWindow.dismiss();
                winfoDNCView.isShowNearbyThys = false;
                winfoDNCView.isShowNearbyShips = false;
                if (type == 1) {
                    if (thysList5.size() == 0 || thysList5 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = thysList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "5000";
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = thysList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "5000";
                        ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList5, pageIndex, 10);
                        thysAdapter.chengeDatas(listPage.getPagedList());
                        thysAdapter.notifyDataSetChanged();
                        locationThys(winfoDNCView, listPage.getPagedList());
                        changeState(1);
                        changeButton(pageIndex);
                    }
                } else if (type == 2) {
                    if (shipList5.size() == 0 || shipList5 == null) {
                        pageIndex = 1;
                        dataListView.setVisibility(View.GONE);
                        nodataLin.setVisibility(View.VISIBLE);
                        totalNum = shipList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + 0 + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "5000";
                        changeButton(pageIndex);
                    } else {
                        pageIndex = 1;
                        dataListView.setVisibility(View.VISIBLE);
                        nodataLin.setVisibility(View.GONE);
                        totalNum = shipList5.size();
                        pageTotalCount = totalNum % 10 == 0 ? totalNum / 10 : totalNum / 10 + 1;
                        contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
                        nearby_tv_titleText.setText("共" + totalNum + "条数据");
                        range = "5000";
                        ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList5, pageIndex, 10);
                        shipAdapter.chengeDatas(listPage.getPagedList());
                        shipAdapter.notifyDataSetChanged();
                        locationShip(winfoDNCView, listPage.getPagedList());
                        changeState(1);
//                        changType(3);
                        changeButton(pageIndex);
                    }
                }
            }
        });
    }

    public void showNearThys(NavigableElementsData data) {
        thysDialog = new ShowThysDialog(getActivity(), data);
        thysDialog.show();
    }

//    public void showNearShipAis(Ais data) {
//        MainFragment mainFragment = new MainFragment();
//        mainFragment.setAisData(data);
//        linLook.setVisibility(View.GONE);
//        behaviorList.setState(BottomSheetBehavior.STATE_HIDDEN);
//        shipAisView = new ShipAisView(getActivity());
//        linAis.addView(shipAisView);
//    }

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

    /**
     * 根据当前页的索引控制上拉下拉的状态
     *
     * @param pageIndex
     */
    private void changeButton(int pageIndex) {
        if (pageIndex == 1 && pageIndex < pageTotalCount) {
            refreshLayout.canPullDown = false;
            refreshLayout.canPullUp = true;
        } else if (pageIndex > 1 && pageIndex < pageTotalCount) {
            refreshLayout.canPullDown = true;
            refreshLayout.canPullUp = true;
        } else if (pageIndex == pageTotalCount) {
            if (pageTotalCount == 1) {
                refreshLayout.canPullDown = false;
                refreshLayout.canPullUp = false;
            } else {
                refreshLayout.canPullDown = true;
                refreshLayout.canPullUp = false;
            }
        }
        if (pageTotalCount == 0) {
            refreshLayout.canPullDown = false;
            refreshLayout.canPullUp = false;
        }
    }

    /**
     * 上拉下拉事件
     *
     * @创建者: yanfeijun
     */
    public class MyListener implements PullToRefreshLayout.OnRefreshListener {
        @SuppressLint("HandlerLeak")
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载上一页操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (type == 1) {//通航要素
//                        if (pageIndex > 0) {//船舶
//                            pageIndex = pageIndex--;
//                        }
                        pageIndex--;
                        if (range.equals("5000")) {
                            ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList5, pageIndex, 10);
                            thysAdapter.chengeDatas(listPage.getPagedList());
                            thysAdapter.notifyDataSetChanged();
                            locationThys(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(1);
                            changeButton(pageIndex);
                        } else if (range.equals("2000")) {
                            ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList2, pageIndex, 10);
                            thysAdapter.chengeDatas(listPage.getPagedList());
                            thysAdapter.notifyDataSetChanged();
                            locationThys(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(1);
                            changeButton(pageIndex);
                        } else if (range.equals("1000")) {
                            ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList1, pageIndex, 10);
                            thysAdapter.chengeDatas(listPage.getPagedList());
                            thysAdapter.notifyDataSetChanged();
                            locationThys(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(1);
                            changeButton(pageIndex);
                        }
                    } else if (type == 2) {
                        pageIndex--;
                        if (range.equals("5000")) {
                            ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList5, pageIndex, 10);
                            shipAdapter.chengeDatas(listPage.getPagedList());
                            shipAdapter.notifyDataSetChanged();
                            locationShip(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(1);
                            changeButton(pageIndex);
                        } else if (range.equals("2000")) {
                            ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList2, pageIndex, 10);
                            shipAdapter.chengeDatas(listPage.getPagedList());
                            shipAdapter.notifyDataSetChanged();
                            locationShip(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(1);
                            changeButton(pageIndex);
                        } else if (range.equals("1000")) {
                            ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList1, pageIndex, 10);
                            shipAdapter.chengeDatas(listPage.getPagedList());
                            shipAdapter.notifyDataSetChanged();
                            locationShip(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(1);
                            changeButton(pageIndex);
                        }
                    }
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }

        @SuppressLint("HandlerLeak")
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载下一页操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (type == 1) {//通航要素
                        pageIndex++;
                        if (range.equals("5000")) {
                            ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList5, pageIndex, 10);
                            thysAdapter.chengeDatas(listPage.getPagedList());
                            thysAdapter.notifyDataSetChanged();
                            locationThys(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(3);
                            changeButton(pageIndex);
                        } else if (range.equals("2000")) {
                            ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList2, pageIndex, 10);
                            thysAdapter.chengeDatas(listPage.getPagedList());
                            thysAdapter.notifyDataSetChanged();
                            locationThys(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(3);
                            changeButton(pageIndex);
                        } else if (range.equals("1000")) {
                            ListPageUtil<NavigableElementsData> listPage = new ListPageUtil<>(thysList1, pageIndex, 10);
                            thysAdapter.chengeDatas(listPage.getPagedList());
                            thysAdapter.notifyDataSetChanged();
                            locationThys(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(3);
                            changeButton(pageIndex);
                        }

                    } else if (type == 2) {//船舶
                        pageIndex++;
                        if (range.equals("5000")) {
                            ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList5, pageIndex, 10);
//                            shipAdapter = new NearbyShipAapter(getActivity(), listPage.getPagedList(), R.layout.nearby_list_ship);
//                            dataListView.setAdapter(shipAdapter);
//                            shipAdapter.notifyDataSetChanged();
//                            Utility.setListViewHeightBasedOnChildren(dataListView);
                            shipAdapter.chengeDatas(listPage.getPagedList());
                            shipAdapter.notifyDataSetChanged();
                            locationShip(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(3);
                            changeButton(pageIndex);
                        } else if (range.equals("2000")) {
                            ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList2, pageIndex, 10);
                            shipAdapter.chengeDatas(listPage.getPagedList());
                            shipAdapter.notifyDataSetChanged();
                            locationShip(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(3);
                            changeButton(pageIndex);
                        } else if (range.equals("1000")) {
                            ListPageUtil<Ais> listPage = new ListPageUtil<>(shipList1, pageIndex, 10);
                            shipAdapter.chengeDatas(listPage.getPagedList());
                            shipAdapter.notifyDataSetChanged();
                            locationShip(winfoDNCView, listPage.getPagedList());
                            changeState(1);
                            changType(3);
                            changeButton(pageIndex);
                        }
                    }
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
//    /**
//     * 将listview所有文本设置成黑色
//     *
//     */
//    private void setTextColorBlack() {
//        for(int i = 0; i < dataListView.getChildCount(); i++){
//            View vv = dataListView.getChildAt(i);
//            TextView tv = (TextView) vv.findViewById(R.id.nearby_list_item_names);
//            tv.setTextColor(Color.BLACK);
//        }
//    }
    @SuppressLint("SetTextI18n")
    public void changeState(int state) {
        contrilarea_page.setVisibility(View.VISIBLE);
        if (state == 1) {
            //成功
            dataListView.setSelection(0);
            contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
            changeButton(pageIndex);
        } else if (state == 2) {
            //失败
            contrilarea_page.setText("第" + pageIndex + "页" + "/" + "共" + pageTotalCount + "页");
        }

    }
    public void changType(int type) {
        if (type == 1) {
            //上一页加载成功
            refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        } else if (type == 2) {
            //上一页加载失败
            refreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
        } else if (type == 3) {
            //下一页加载成功
            refreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        } else if (type == 4) {
            //下一页加载失败
            refreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
        }
    }

}
