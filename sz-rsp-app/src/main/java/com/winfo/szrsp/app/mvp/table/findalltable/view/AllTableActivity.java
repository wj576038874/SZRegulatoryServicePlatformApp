package com.winfo.szrsp.app.mvp.table.findalltable.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.table.CBWFXCTableAdapter;
import com.winfo.szrsp.app.adapter.table.ContainerWeightInspectAdapter;
import com.winfo.szrsp.app.adapter.table.CruiseWorkAdapter;
import com.winfo.szrsp.app.adapter.table.DXSHCTableAdapter;
import com.winfo.szrsp.app.adapter.table.DangerousGoodsKaiXiangAdapter;
import com.winfo.szrsp.app.adapter.table.DangerousGoodsXianChangAdapter;
import com.winfo.szrsp.app.adapter.table.ElectronicCruiseExceptionAdapter;
import com.winfo.szrsp.app.adapter.table.GoodSecneOutRestServiceAdapter;
import com.winfo.szrsp.app.adapter.table.PSCAdapter;
import com.winfo.szrsp.app.adapter.table.WatersProtralTableAdapter;
import com.winfo.szrsp.app.adapter.table.cbxcTableAdapter;
import com.winfo.szrsp.app.adapter.table.jdbgTableAdapter;
import com.winfo.szrsp.app.adapter.table.xhtjTableAdapter;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view.DetailCBWFXCActivity;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.DetailJdbgActivity;
import com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.view.DetailContainerWeightInspectActivity;
import com.winfo.szrsp.app.mvp.table.cbzy.view.DetailDangerousGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.DetailOrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.DetailDangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.DetailSupervisionActivity;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.view.DetailDXSHCActivity;
import com.winfo.szrsp.app.mvp.table.dzxhyc.view.DetailElectronicCruiseAbnormalActivity;
import com.winfo.szrsp.app.mvp.table.findalltable.presenter.FindAllTablePresenter;
import com.winfo.szrsp.app.mvp.table.psca.view.DetailPSCFormActivity;
import com.winfo.szrsp.app.mvp.table.ssxh.view.DetailWatersPatrolActivity;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.CruiseWorkRecordActivity;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.DetailCruisrWorkRecordActivity;
import com.winfo.szrsp.app.mvp.table.xhtj.view.DetailCruiseStatisticsActivity;
import com.winfo.szrsp.app.sdk.entity.table.ContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFrom;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspect;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordInfo;
import com.winfo.szrsp.app.sdk.entity.table.DetailCruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.DetailDangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.DetailDangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.ListCBWFXCData;
import com.winfo.szrsp.app.sdk.entity.table.ListContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.ListCruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.ListDXSHCData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.ListElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.ListGoodSecneOutRestServiceData;
import com.winfo.szrsp.app.sdk.entity.table.ListPSCData;
import com.winfo.szrsp.app.sdk.entity.table.ListWatersPatrolData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbjdjgData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbxcData;
import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatistics;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.ListXHTJData;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.view
 * @Filename: AllTableActivity
 * @Author: lsj
 * @Date: 2017/12/7  9:08
 * @Description:
 * @Version:
 */
public class AllTableActivity extends Activity implements IAllTableActivity, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ImageButton ibtn_back;
    private TextView tv_title;
    private SmartRefreshLayout mRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private ListView lv;
    private View notfoundView;
    private View loadFailedView;
    private TextView tv_loadfile;


    private List<ListcbjdjgData.list> lists;
    private List<ListcbxcData.list> listcbxcData;
    private List<CtSpecialShipType0203> listDXSHCData;
    private List<CtWaterCruiseRecordInfo> listWatersPatrolData;
    private List<CtPscFrom> listCtPscFromData;
    private List<CtCruiseStatistics> listXHTJData;
    private List<CtElectronicCruiseException> listElectronicCruiseException;
    private List<OrdinaryGoodsKaiXiangData> listGoodSecneOutRestServiceData;
    private List<DetailDangerousGoodsXianChangData> listDetailDangerousGoodsXianChangData;
    private List<DetailDangerousGoodsKaiXiangData> listDetailDangerousGoodsKaiXiangData;
    private List<CtSafetySceneInspect> listCBWEXCData;
    private List<ContainerWeightInspectData> listContainerWeightInspectData;
    private List<DetailCruiseWorkData> listCruiseWorkData;
    private FindAllTablePresenter presenter;

    private jdbgTableAdapter jdbgTableAdapter;
    private cbxcTableAdapter cbxcTableAdapter;
    private DXSHCTableAdapter dxshcTableAdapter;
    private WatersProtralTableAdapter watersProtralTableAdapter;
    private PSCAdapter pscAdapter;
    private xhtjTableAdapter xhtjTableAdapter;
    private ElectronicCruiseExceptionAdapter electronicCruiseExceptionAdapter;
    private DangerousGoodsXianChangAdapter dangerousGoodsXianChangAdapter;
    private DangerousGoodsKaiXiangAdapter dangerousGoodsKaiXiangAdapter;
    private GoodSecneOutRestServiceAdapter goodSecneOutRestServiceAdapter;
    private CBWFXCTableAdapter cbwfxcTableAdapter;
    private ContainerWeightInspectAdapter containerWeightInspectAdapter;
    private CruiseWorkAdapter cruiseWorkAdapter;

    private int value = 2;
    private int page_1 = 1;
    private int page_2 = 1;
    private int page_3 = 1;
    private int page_4 = 1;
    private int page_5 = 1;
    private int page_6 = 1;
    private int page_7 = 1;
    private int page_8 = 1;
    private int page_9 = 1;
    private int page_10 = 1;
    private int page_11 = 1;
    private int page_12 = 1;
    private int page_13 = 1;
    private int page_14 = 1;
    private int page_15 = 1;
    private boolean bol1, bol2, bol3, bol4, bol5, bol6, bol7, bol8, bol9, bol10, bol11, bol12, bol13, bol14, bol15 = false;//判断加载全部数据
    private boolean showFaile1, showFaile2, showFaile3, showFaile4, showFaile5, showFaile6, showFaile7, showFaile8, showFaile9, showFaile10, showFaile11, showFaile12, showFaile13, showFaile14, showFaile15 = false;//判断是否加载失败

    @Override
    protected void onStart() {
        super.onStart();
        if (jdbgTableAdapter != null) {
            jdbgTableAdapter.notifyDataSetChanged();
        }
        if (cbxcTableAdapter != null) {
            cbxcTableAdapter.notifyDataSetChanged();
        }
        if (dxshcTableAdapter != null) {
            dxshcTableAdapter.notifyDataSetChanged();
        }
        if (watersProtralTableAdapter != null) {
            watersProtralTableAdapter.notifyDataSetChanged();
        }
        if (pscAdapter != null) {
            pscAdapter.notifyDataSetChanged();
        }
        if (xhtjTableAdapter != null) {
            xhtjTableAdapter.notifyDataSetChanged();
        }
        if (electronicCruiseExceptionAdapter != null) {
            electronicCruiseExceptionAdapter.notifyDataSetChanged();
        }
        if (cbwfxcTableAdapter != null) {
            cbwfxcTableAdapter.notifyDataSetChanged();
        }
        if (goodSecneOutRestServiceAdapter != null) {
            goodSecneOutRestServiceAdapter.notifyDataSetChanged();
        }
        if (containerWeightInspectAdapter != null) {
            containerWeightInspectAdapter.notifyDataSetChanged();
        }
//        if (cruiseWorkAdapter != null) {
//            cbwfxcTableAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltable);
        initView();
        initData();
        initEvent();
        alertPop();
    }

    private void initEvent() {
        ibtn_back.setOnClickListener(this);
        tv_title.setOnClickListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (value == 1) {

                } else if (value == 2) {
                    Intent intent = new Intent(AllTableActivity.this, DetailJdbgActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cbxcShipNo", listcbxcData.get(i).getInspectNo());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (value == 3) {
                    Intent intent = new Intent(AllTableActivity.this, DetailDXSHCActivity.class);
                    intent.putExtra("dxshcData", listDXSHCData.get(i));
                    startActivity(intent);
                } else if (value == 4) {
                    Intent intent = new Intent(AllTableActivity.this, DetailWatersPatrolActivity.class);
                    intent.putExtra("watersPatrolInspectNo", listWatersPatrolData.get(i).getInspectNo());
                    startActivity(intent);
                } else if (value == 6) {
                    Intent intent = new Intent(AllTableActivity.this, DetailSupervisionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("jdbgShipNo", lists.get(i).getInspectNo());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (value == 7) {
                    Intent intent = new Intent(AllTableActivity.this, DetailPSCFormActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("inspectNo", listCtPscFromData.get(i).getInspectNo());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (value == 8) {
                    Intent intent = new Intent(AllTableActivity.this, DetailCruiseStatisticsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("inspectNo", listXHTJData.get(i).getInspectNo());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (value == 9) {
                    Intent intent = new Intent(AllTableActivity.this, DetailElectronicCruiseAbnormalActivity.class);
                    intent.putExtra("CtElectronicCruiseException", listElectronicCruiseException.get(i));
                    startActivity(intent);

                } else if (value == 10) {
                    Intent intent = new Intent(AllTableActivity.this, DetailCBWFXCActivity.class);
                    intent.putExtra("inspectNo", listCBWEXCData.get(i).getInspectNo());
                    startActivity(intent);

                } else if (value == 11) {
                    Intent intent = new Intent(AllTableActivity.this, DetailDangerousGoodsXianChangActivity.class);
                    intent.putExtra("DetailDangerousGoodsXianChangData", listDetailDangerousGoodsXianChangData.get(i).getInspectNo());
                    startActivity(intent);
                } else if (value == 12) {
                    Intent intent = new Intent(AllTableActivity.this, DetailDangerousGoodsKaiXiangActivity.class);
                    intent.putExtra("DetailDangerousGoodsKaiXiangData", listDetailDangerousGoodsKaiXiangData.get(i).getInspectNo());
                    startActivity(intent);
                } else if (value == 13) {
                    Intent intent = new Intent(AllTableActivity.this, DetailOrdinaryGoodsKaiXiangActivity.class);
                    intent.putExtra("OrdinaryGoodsKaiXiangData", listGoodSecneOutRestServiceData.get(i));
                    startActivity(intent);
                } else if (value == 14) {
                    Intent intent = new Intent(AllTableActivity.this, DetailContainerWeightInspectActivity.class);
                    intent.putExtra("ContainerWeightInspectData", listContainerWeightInspectData.get(i));
                    startActivity(intent);
                } else if (value == 15) {
                    Intent intent = new Intent(AllTableActivity.this, DetailCruisrWorkRecordActivity.class);
                    intent.putExtra("CruiseWorkData", listCruiseWorkData.get(i).getInspectNo());
                    startActivity(intent);
                }

            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (value == 1) {

                } else if (value == 2) {
                    presenter.loadCBXCData("" + page_2, "20", "1", false);
                } else if (value == 3) {
                    presenter.loadDXSHCData("" + page_3, "20", "1", false);
                } else if (value == 4) {
                    presenter.loadWatersPatrolData("" + page_4, "20", "1", false);
                } else if (value == 5) {

                } else if (value == 6) {
                    presenter.loadJGBGData("" + page_6, "20", "1", false);
                } else if (value == 7) {
                    presenter.loadPSCData("" + page_7, "20", "1", false);
                } else if (value == 8) {
                    presenter.loadXHTJData("" + page_8, "20", "1", false);
                } else if (value == 9) {
                    presenter.loadCtElectronicCruiseException("" + page_9, "20", "1", false);
                } else if (value == 10) {
                    presenter.loadCBWFXCData("" + page_10, "20", "1", false);
                } else if (value == 11) {
                    presenter.loadDangerousGoodsXianChangData("" + page_11, "20", "1", false);
                } else if (value == 12) {
                    presenter.loadDangerousGoodsKaiXiangData("" + page_12, "20", "1", false);
                } else if (value == 13) {
                    presenter.loadGoodSecneOutRestServiceData("" + page_13, "20", "1", false);
                } else if (value == 14) {
                    presenter.loadContainerWeightInspectData("" + page_14, "20", "1", false);
                } else if (value == 15) {
                    presenter.loadCruiseWorkData("" + page_15, "20", "1", false);
                }

            }
        });
        loadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value == 1) {

                } else if (value == 2) {
                    presenter.loadCBXCData("" + page_2, "20", "1", true);
                } else if (value == 3) {
                    presenter.loadDXSHCData("" + page_3, "20", "1", true);
                } else if (value == 4) {
                    presenter.loadWatersPatrolData("" + page_4, "20", "1", true);
                } else if (value == 5) {

                } else if (value == 6) {
                    presenter.loadJGBGData("" + page_6, "20", "1", true);
                } else if (value == 7) {
                    presenter.loadPSCData("" + page_7, "20", "1", true);
                } else if (value == 8) {
                    presenter.loadXHTJData("" + page_8, "20", "1", true);
                } else if (value == 9) {
                    presenter.loadCtElectronicCruiseException("" + page_9, "20", "1", true);
                } else if (value == 10) {
                    presenter.loadCBWFXCData("" + page_10, "20", "1", true);
                } else if (value == 11) {
                    presenter.loadDangerousGoodsXianChangData("" + page_11, "20", "1", true);
                } else if (value == 12) {
                    presenter.loadDangerousGoodsKaiXiangData("" + page_12, "20", "1", true);
                } else if (value == 13) {
                    presenter.loadGoodSecneOutRestServiceData("" + page_13, "20", "1", true);
                } else if (value == 14) {
                    presenter.loadContainerWeightInspectData("" + page_14, "20", "1", true);
                } else if (value == 15) {
                    presenter.loadCruiseWorkData("" + page_15, "20", "1", true);
                }
            }
        });
    }

    private void initData() {
        tv_title.setText("船舶现场监督报告");
        lists = new ArrayList<>();
        listcbxcData = new ArrayList<>();
        listDXSHCData = new ArrayList<>();
        listWatersPatrolData = new ArrayList<>();
        listCtPscFromData = new ArrayList<>();
        listXHTJData = new ArrayList<>();
        listElectronicCruiseException = new ArrayList<>();
        listCBWEXCData = new ArrayList<>();
        listGoodSecneOutRestServiceData = new ArrayList<>();
        listDetailDangerousGoodsXianChangData = new ArrayList<>();
        listDetailDangerousGoodsKaiXiangData = new ArrayList<>();
//        listDangerousGoodsXianChangData = new ArrayList<>();
        listContainerWeightInspectData = new ArrayList<>();
        listCruiseWorkData = new ArrayList<>();
        presenter.loadCBXCData("" + page_2, "20", "1", true);
    }

    private void initView() {
        notfoundView = findViewById(R.id.area_data_notfound);
        loadFailedView = findViewById(R.id.area_data_load_faild);
        tv_loadfile = (TextView) loadFailedView.findViewById(R.id.id_tv_data_load_faild);

        presenter = new FindAllTablePresenter(this);
        ibtn_back = findViewById(R.id.titleBar_imgbtn_back);
        tv_title = findViewById(R.id.titleBar_titleText);
        lv = findViewById(R.id.lv_area);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(false);
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader) mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis() - deta));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time = format.format(new Date());
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于" + time));
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.titleBar_titleText:
                popupWindow.showAsDropDown(ibtn_back, 0, 0);
                this.activityAlphaParams = getWindow().getAttributes();
                if (popupWindow.isShowing()) {
                    activityAlphaParams.alpha = 0.5f;
                    getWindow().setAttributes(activityAlphaParams);
                }
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        activityAlphaParams.alpha = 1f;
                        getWindow().setAttributes(activityAlphaParams);
                    }
                });
                break;
        }
    }

    private PopupWindow popupWindow;
    private View popView;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;
    private RadioButton btn_xqdc;
    private RadioButton btn_xcjd;
    private RadioButton btn_dxshc;
    private RadioButton btn_thsy;
    private RadioButton btn_tzs;
    private RadioButton btn_cbaj;
    private RadioButton btn_psc_form;
    private RadioButton btn_xhtj;
    private RadioButton btn_dzxh_yc;
    private RadioButton id_btn_cbwfxc;
    private RadioButton btn_cbzy_wxhw_xcjc;
    private RadioButton btn_cbzy_wxhw_kxjc;
    private RadioButton btn_cbzy_pthw_kxjc;
    private RadioButton btn_cbzy_zlyz;
    private RadioButton btn_xhgz;

    private void alertPop() {
        //初始化视图
        popView = View.inflate(AllTableActivity.this, R.layout.pop_arealist, null);
        btn_xqdc = popView.findViewById(R.id.id_btn_xqdc);
        btn_xcjd = popView.findViewById(R.id.id_btn_xcjd);
        btn_dxshc = popView.findViewById(R.id.id_btn_dxshc);
        btn_thsy = popView.findViewById(R.id.id_btn_thsy);
        btn_tzs = popView.findViewById(R.id.id_btn_tzs);
        btn_cbaj = popView.findViewById(R.id.id_btn_cqgjdjcbg);
        btn_psc_form = popView.findViewById(R.id.id_btn_psc_form);
        btn_xhtj = popView.findViewById(R.id.id_btn_xhtj);
        btn_dzxh_yc = popView.findViewById(R.id.id_btn_dzxh_yc);
        id_btn_cbwfxc = popView.findViewById(R.id.id_btn_cbwfxc);
        btn_cbzy_wxhw_xcjc = popView.findViewById(R.id.id_btn_cbzy_wxhw_xcjc);
        btn_cbzy_wxhw_kxjc = popView.findViewById(R.id.id_btn_cbzy_wxhw_kxjc);
        btn_cbzy_pthw_kxjc = popView.findViewById(R.id.id_btn_cbzy_pthw_kxjc);
        btn_cbzy_zlyz = popView.findViewById(R.id.id_btn_cbzy_zlyz);
        btn_xhgz = popView.findViewById(R.id.id_btn_xhgz);

        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.pyxc_popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        btn_xqdc.setOnCheckedChangeListener(this);
        btn_dxshc.setOnCheckedChangeListener(this);
        btn_thsy.setOnCheckedChangeListener(this);
        btn_xcjd.setOnCheckedChangeListener(this);
        btn_tzs.setOnCheckedChangeListener(this);
        btn_cbaj.setOnCheckedChangeListener(this);
        btn_psc_form.setOnCheckedChangeListener(this);
        btn_xhtj.setOnCheckedChangeListener(this);
        btn_dzxh_yc.setOnCheckedChangeListener(this);
        id_btn_cbwfxc.setOnCheckedChangeListener(this);
        btn_cbzy_wxhw_xcjc.setOnCheckedChangeListener(this);
        btn_cbzy_wxhw_kxjc.setOnCheckedChangeListener(this);
        btn_cbzy_pthw_kxjc.setOnCheckedChangeListener(this);
        btn_cbzy_zlyz.setOnCheckedChangeListener(this);
        btn_xhgz.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.id_btn_xcjd:
                if (isChecked) {
                    value = 2;
                    btn_xcjd.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("船舶现场监督报告");
                    mRefreshLayout.setLoadmoreFinished(bol2);
                    if (listcbxcData.size() == 0) {
                        presenter.loadCBXCData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(cbxcTableAdapter);
                        cbxcTableAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_dxshc:
                if (isChecked) {
                    btn_dxshc.setChecked(true);
                    value = 3;
                    popupWindow.dismiss();
                    tv_title.setText("大型散货船检查表");
                    mRefreshLayout.setLoadmoreFinished(bol3);
                    if (lists.size() == 0) {
                        presenter.loadDXSHCData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(dxshcTableAdapter);
                        dxshcTableAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_thsy:
                if (isChecked) {
                    btn_thsy.setChecked(true);
                    value = 4;
                    popupWindow.dismiss();
                    tv_title.setText("水上巡航表格");
                    mRefreshLayout.setLoadmoreFinished(bol4);
                    if (lists.size() == 0) {
                        presenter.loadWatersPatrolData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(watersProtralTableAdapter);
                        watersProtralTableAdapter.notifyDataSetChanged();
                    }
                }
                break;

            case R.id.id_btn_cqgjdjcbg:
                if (isChecked) {
                    value = 6;
                    btn_cbaj.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("船旗国监督检查报告");
                    mRefreshLayout.setLoadmoreFinished(bol6);
                    if (lists.size() == 0) {
                        presenter.loadJGBGData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(jdbgTableAdapter);
                        jdbgTableAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_psc_form:
                if (btn_psc_form.isChecked()) {
                    value = 7;
                    btn_psc_form.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("PSC");
                    mRefreshLayout.setLoadmoreFinished(bol7);
                    if (listCtPscFromData.size() == 0) {
                        presenter.loadPSCData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(pscAdapter);
                        pscAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_xhtj:
                if (btn_xhtj.isChecked()) {
                    value = 8;
                    btn_xhtj.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("巡航统计");
                    mRefreshLayout.setLoadmoreFinished(bol8);
                    if (listXHTJData.size() == 0) {
                        presenter.loadXHTJData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(xhtjTableAdapter);
                        xhtjTableAdapter.notifyDataSetChanged();
                    }
                }
                break;

            case R.id.id_btn_dzxh_yc:
                if (btn_dzxh_yc.isChecked()) {
                    value = 9;
                    btn_dzxh_yc.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("电子巡航异常通报");
                    mRefreshLayout.setLoadmoreFinished(bol9);
                    if (listElectronicCruiseException.size() == 0) {
                        presenter.loadCtElectronicCruiseException("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(electronicCruiseExceptionAdapter);
                        electronicCruiseExceptionAdapter.notifyDataSetChanged();
                    }

                }
                break;
            case R.id.id_btn_cbwfxc:
                if (id_btn_cbwfxc.isChecked()) {
                    value = 10;
                    id_btn_cbwfxc.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("船舶危防现场监督检查记录表");
                    mRefreshLayout.setLoadmoreFinished(bol10);
                    if (listCBWEXCData.size() == 0) {
                        presenter.loadCBWFXCData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(cbwfxcTableAdapter);
                        cbwfxcTableAdapter.notifyDataSetChanged();
                    }
                }
                break;

            case R.id.id_btn_cbzy_wxhw_xcjc:
                if (btn_cbzy_wxhw_xcjc.isChecked()) {
                    value = 11;
                    btn_cbzy_wxhw_xcjc.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("舶载运集装箱危险货物/污染危害性货物现场检查记录表");
                    mRefreshLayout.setLoadmoreFinished(bol11);
                    if (listDetailDangerousGoodsXianChangData.size() == 0) {
                        presenter.loadDangerousGoodsXianChangData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(dangerousGoodsXianChangAdapter);
                        dangerousGoodsXianChangAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_cbzy_wxhw_kxjc:
                if (btn_cbzy_wxhw_kxjc.isChecked()) {
                    value = 12;
                    btn_cbzy_wxhw_kxjc.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("船舶载运危险货物/污染危害性货物集装箱开箱检查记录表");
                    mRefreshLayout.setLoadmoreFinished(bol12);
                    if (listDetailDangerousGoodsKaiXiangData.size() == 0) {
                        presenter.loadDangerousGoodsKaiXiangData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(dangerousGoodsKaiXiangAdapter);
                        dangerousGoodsKaiXiangAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_cbzy_pthw_kxjc:
                if (btn_cbzy_pthw_kxjc.isChecked()) {
                    value = 13;
                    btn_cbzy_pthw_kxjc.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("船舶载运普通货物集装箱开箱检查");
                    mRefreshLayout.setLoadmoreFinished(bol13);
                    if (listGoodSecneOutRestServiceData.size() == 0) {
                        presenter.loadGoodSecneOutRestServiceData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(goodSecneOutRestServiceAdapter);
                        goodSecneOutRestServiceAdapter.notifyDataSetChanged();
                    }
                }
                break;

            case R.id.id_btn_cbzy_zlyz:
                if (btn_cbzy_zlyz.isChecked()) {
                    value = 14;
                    btn_cbzy_zlyz.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("船舶载运货物集装箱重量验证检查记录");
                    mRefreshLayout.setLoadmoreFinished(bol14);
                    if (listContainerWeightInspectData.size() == 0) {
                        presenter.loadContainerWeightInspectData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(containerWeightInspectAdapter);
                        containerWeightInspectAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.id_btn_xhgz:
                if (btn_xhgz.isChecked()) {
                    value = 15;
                    btn_xhgz.setChecked(true);
                    popupWindow.dismiss();
                    tv_title.setText("巡航工作记录表");
                    mRefreshLayout.setLoadmoreFinished(bol15);
                    if (listCruiseWorkData.size() == 0) {
                        presenter.loadCruiseWorkData("1", "20", "1", true);
                    } else {
                        notfoundView.setVisibility(View.GONE);
                        mRefreshLayout.setVisibility(View.VISIBLE);
                        loadFailedView.setVisibility(View.GONE);
                        lv.setAdapter(containerWeightInspectAdapter);
                        cruiseWorkAdapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }

    @Override
    public Dialog getDailog() {
        Dialog dialog = DialogUtils.createLoadingDialog(this, "加载中...");
        return dialog;
    }

    @Override
    public void setCBXCData(ListcbxcData data) {
        showFaile2 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listcbxcData.size() == 0) {
            this.listcbxcData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_2 = page + 1;
            cbxcTableAdapter = new cbxcTableAdapter(this, this.listcbxcData, R.layout.item_areaboat_layout);
            lv.setAdapter(cbxcTableAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol2 = true;
            }
            cbxcTableAdapter.notifyDataSetChanged();
        } else {
            this.listcbxcData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            page_2 = page + 1;
            mRefreshLayout.finishLoadmore();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol2 = true;
            }
            cbxcTableAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setDXSHCData(ListDXSHCData data) {
        showFaile3 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listDXSHCData.size() == 0) {
            this.listDXSHCData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_3 = page + 1;
            dxshcTableAdapter = new DXSHCTableAdapter(this, this.listDXSHCData, R.layout.item_areaboat_layout);
            lv.setAdapter(dxshcTableAdapter);
            dxshcTableAdapter.notifyDataSetChanged();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol3 = true;
            }
        } else {
            this.listDXSHCData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            dxshcTableAdapter.notifyDataSetChanged();
            page_3 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol3 = true;
            }
        }

    }

    @Override
    public void setWatersPatrolData(ListWatersPatrolData data) {
        showFaile4 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listWatersPatrolData.size() == 0) {
            this.listWatersPatrolData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_4 = page + 1;
            watersProtralTableAdapter = new WatersProtralTableAdapter(this, this.listWatersPatrolData, R.layout.item_areaboat_layout);
            lv.setAdapter(watersProtralTableAdapter);
            watersProtralTableAdapter.notifyDataSetChanged();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol4 = true;
            }
        } else {
            this.listWatersPatrolData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            watersProtralTableAdapter.notifyDataSetChanged();
            page_4 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol4 = true;
            }
        }

    }

    @Override
    public void setPSCData(ListPSCData data) {
        showFaile7 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listCtPscFromData.size() == 0) {
            this.listCtPscFromData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_7 = page + 1;
            pscAdapter = new PSCAdapter(this, this.listCtPscFromData, R.layout.item_areaboat_layout);
            lv.setAdapter(pscAdapter);
            pscAdapter.notifyDataSetChanged();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol7 = true;
            }
        } else {
            this.listCtPscFromData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            pscAdapter.notifyDataSetChanged();
            page_7 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol7 = true;
            }
        }
    }

    @Override
    public void setXHTjData(ListXHTJData data) {
        showFaile8 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listXHTJData.size() == 0) {
            this.listXHTJData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_8 = page + 1;
            xhtjTableAdapter = new xhtjTableAdapter(this, this.listXHTJData, R.layout.item_areaboat_layout);
            lv.setAdapter(xhtjTableAdapter);
            xhtjTableAdapter.notifyDataSetChanged();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol8 = true;
            }
        } else {
            this.listXHTJData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            xhtjTableAdapter.notifyDataSetChanged();
            page_8 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol8 = true;
            }
        }
    }

    @Override
    public void setElectronicCruiseException(ListElectronicCruiseException data) {

        showFaile9 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listElectronicCruiseException.size() == 0) {
            this.listElectronicCruiseException = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_9 = page + 1;

            electronicCruiseExceptionAdapter = new ElectronicCruiseExceptionAdapter(this, this.listElectronicCruiseException, R.layout.item_areaboat_layout);
            lv.setAdapter(electronicCruiseExceptionAdapter);
            electronicCruiseExceptionAdapter.notifyDataSetChanged();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol9 = true;
            }
        } else {
            this.listElectronicCruiseException.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            electronicCruiseExceptionAdapter.notifyDataSetChanged();
            page_9 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol9 = true;
            }
        }

    }

    @Override
    public void setCBWFXCData(ListCBWFXCData data) {
        showFaile10 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listCBWEXCData.size() == 0) {
            this.listCBWEXCData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_10 = page + 1;

            cbwfxcTableAdapter = new CBWFXCTableAdapter(this, this.listCBWEXCData, R.layout.item_areaboat_layout);
            lv.setAdapter(cbwfxcTableAdapter);
            cbwfxcTableAdapter.notifyDataSetChanged();
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol10 = true;
            }
        } else {
            this.listCBWEXCData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            cbwfxcTableAdapter.notifyDataSetChanged();
            page_10 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol10 = true;
            }
        }
    }


    @Override
    public void setDangerousGoodsXianChangData(ListDangerousGoodsXianChangData data) {
        showFaile11 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listDetailDangerousGoodsXianChangData.size() == 0) {
            this.listDetailDangerousGoodsXianChangData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_11 = page + 1;
            dangerousGoodsXianChangAdapter = new DangerousGoodsXianChangAdapter(this, this.listDetailDangerousGoodsXianChangData, R.layout.item_areaboat_layout);
            lv.setAdapter(dangerousGoodsXianChangAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol11 = true;
            }
            dangerousGoodsXianChangAdapter.notifyDataSetChanged();
        } else {
            this.listDetailDangerousGoodsXianChangData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_11 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol11 = true;
            }
            dangerousGoodsXianChangAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setDangerousGoodsKaiXiangData(ListDangerousGoodsKaiXiangData data) {
        showFaile12 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listDetailDangerousGoodsKaiXiangData.size() == 0) {
            this.listDetailDangerousGoodsKaiXiangData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_12 = page + 1;
            dangerousGoodsKaiXiangAdapter = new DangerousGoodsKaiXiangAdapter(this, this.listDetailDangerousGoodsKaiXiangData, R.layout.item_areaboat_layout);
            lv.setAdapter(dangerousGoodsKaiXiangAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol12 = true;
            }
            dangerousGoodsKaiXiangAdapter.notifyDataSetChanged();
        } else {
            this.listDetailDangerousGoodsKaiXiangData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_12 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol12 = true;
            }
            dangerousGoodsKaiXiangAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setCruiseWorkData(ListCruiseWorkData data) {
        showFaile15 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listCruiseWorkData.size() == 0) {
            this.listCruiseWorkData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_15 = page + 1;
            cruiseWorkAdapter = new CruiseWorkAdapter(this, this.listCruiseWorkData, R.layout.item_areaboat_layout);
            lv.setAdapter(cruiseWorkAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol15 = true;
            }
            cruiseWorkAdapter.notifyDataSetChanged();
        } else {
            this.listCruiseWorkData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_15 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol15 = true;
            }
            cruiseWorkAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setContainerWeightInspectData(ListContainerWeightInspectData data) {
        showFaile14 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listContainerWeightInspectData.size() == 0) {
            this.listContainerWeightInspectData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_14 = page + 1;
            containerWeightInspectAdapter = new ContainerWeightInspectAdapter(this, this.listContainerWeightInspectData, R.layout.item_areaboat_layout);
            lv.setAdapter(containerWeightInspectAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol14 = true;
            }
            containerWeightInspectAdapter.notifyDataSetChanged();
        } else {
            this.listContainerWeightInspectData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_14 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol14 = true;
            }
            containerWeightInspectAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void setGoodSecneOutRestServiceData(ListGoodSecneOutRestServiceData data) {
        showFaile13 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.listGoodSecneOutRestServiceData.size() == 0) {
            this.listGoodSecneOutRestServiceData = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_13 = page + 1;
            goodSecneOutRestServiceAdapter = new GoodSecneOutRestServiceAdapter(this, this.listGoodSecneOutRestServiceData, R.layout.item_areaboat_layout);
            lv.setAdapter(goodSecneOutRestServiceAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol13 = true;
            }
            goodSecneOutRestServiceAdapter.notifyDataSetChanged();
        } else {
            this.listGoodSecneOutRestServiceData.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_13 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol13 = true;
            }
            goodSecneOutRestServiceAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setJDBGData(ListcbjdjgData data) {
        showFaile6 = true;
        int page = data.getPageNum();//当前页
        int pagenum = data.getPages();//总页数
        if (this.lists.size() == 0) {
            this.lists = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_6 = page + 1;
            jdbgTableAdapter = new jdbgTableAdapter(this, this.lists, R.layout.item_areaboat_layout);
            lv.setAdapter(jdbgTableAdapter);
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol6 = true;
            }
            jdbgTableAdapter.notifyDataSetChanged();
        } else {
            this.lists.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            page_6 = page + 1;
            if (page == pagenum) {
                ToastUtils.showToast(this, "数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol6 = true;
            }
            jdbgTableAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void showOnFaile2(String msg) {
        if (showFaile2) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile3(String msg) {
        if (showFaile3) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile4(String msg) {
        if (showFaile4) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }

    }

    @Override
    public void showOnFaile6(String msg) {
        if (showFaile6) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile7(String msg) {
        if (showFaile7) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile8(String msg) {
        if (showFaile8) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile9(String msg) {
        if (showFaile9) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile10(String msg) {
        if (showFaile10) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile11(String msg) {
        if (showFaile11) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnFaile12(String msg) {
        if (showFaile12) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnfaile13(String msg) {
        if (showFaile13) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }

    }

    @Override
    public void showOnfaile14(String msg) {
        if (showFaile14) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void showOnfaile15(String msg) {
        if (showFaile15) {
            mRefreshLayout.finishLoadmore(true);
        } else {
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.VISIBLE);
            tv_loadfile.setText(msg);
        }
    }

    @Override
    public void notFound() {
        notfoundView.setVisibility(View.VISIBLE);
        mRefreshLayout.setVisibility(View.GONE);
        loadFailedView.setVisibility(View.GONE);
    }
}
