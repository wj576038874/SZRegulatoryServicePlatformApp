package com.winfo.szrsp.app.mvp.table.kxjc.view;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.presenter.DangerousGoodsXianChangPresenter;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.DangerousGoodsLayout;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.DangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.IDangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 舶载运集装箱危险货物/污染危害性货物现场检查记录表
 * Created by HoBo on 2018/3/19.
 */

public class DangerousGoodsXianChangFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.table_titleBar)
    RelativeLayout table_titleBar;
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_chinaName)
    EditText et_ship_chinaName;
    @BindView(R.id.et_ship_englishName)
    EditText et_ship_englishName;
    @BindView(R.id.et_ship_nationality)
    EditText et_ship_nationality;
    @BindView(R.id.et_ship_weight)
    EditText et_ship_weight;
    @BindView(R.id.et_ship_buildTime)
    TextView et_ship_buildTime;
    @BindView(R.id.ck_imported)
    CheckBox ck_imported;
    @BindView(R.id.ck_transit)
    CheckBox ck_transit;
    @BindView(R.id.ck_exit)
    CheckBox ck_exit;
    @BindView(R.id.ck_transfer)
    CheckBox ck_transfer;
    @BindView(R.id.et_ship_loading_port)
    EditText et_ship_loading_port;
    @BindView(R.id.et_ship_unloading_port)
    EditText et_ship_unloading_port;
    @BindView(R.id.et_ship_berth)
    EditText et_ship_berth;
    @BindView(R.id.et_ship_route)
    EditText et_ship_route;
    @BindView(R.id.et_ship_agent)
    EditText et_ship_agent;
    @BindView(R.id.et_ship_inspect)
    EditText et_ship_inspect;
    @BindView(R.id.et_ship_company)
    EditText et_ship_company;
    @BindView(R.id.tableLin2)
    TableLayout tableLin2;
    @BindView(R.id.add_img)
    ImageView add_img;
    @BindView(R.id.del_img)
    ImageView del_img;
    @BindView(R.id.img_close_up)
    ImageView img_close_up;
    @BindView(R.id.img_close_down)
    ImageView img_close_down;
    @BindView(R.id.tabLin)
    TableLayout tabLin;
    @BindView(R.id.et_ship_zhifa1)
    EditText et_ship_zhifa1;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.btn_save)
    Button btn_save;


    private TimePickerView timePickerView;
    private DangerousGoodsXianChangData data;
    private DangerousGoodsXianChangData.ctDangerPolluteScene sceneData;
    private List<DangerousGoodsXianChangData.ctDangerPolluteSceneDetail> list;

    private DangerousGoodsLayout dangerousGoodsLayout;
    private String str = "";
    private String nameCn, nameEn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_cbzy_wxhwxcjc, null);
        ButterKnife.bind(this, view);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {
        btn_save.setVisibility(View.GONE);
        table_titleBar.setVisibility(View.GONE);
        dangerousGoodsLayout = new DangerousGoodsLayout(getActivity());
        tableLin2.addView(dangerousGoodsLayout);

        data = new DangerousGoodsXianChangData();
        sceneData = new DangerousGoodsXianChangData.ctDangerPolluteScene();
        list = new ArrayList<>();

        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        et_ship_inspectTime.setText(time);
        et_ship_zhifa1.setText(ACache.get(getActivity()).getAsString("userName"));
    }

    private void initData() {
        Bundle bundle = getArguments();
        nameCn = bundle.getString("shipNameCn");
        nameEn = bundle.getString("shipNameEn");
        et_ship_chinaName.setText(nameCn);
        et_ship_englishName.setText(nameEn);
    }


    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        et_ship_inspectTime.setOnClickListener(this);
        et_ship_buildTime.setOnClickListener(this);
        img_close_up.setOnClickListener(this);
        img_close_down.setOnClickListener(this);
        ck_imported.setOnCheckedChangeListener(this);
        ck_transit.setOnCheckedChangeListener(this);
        ck_exit.setOnCheckedChangeListener(this);
        ck_transfer.setOnCheckedChangeListener(this);
        add_img.setOnClickListener(this);
        del_img.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ck_imported:
                if (isCheck) {
                    ck_imported.setChecked(true);
                    ck_transit.setChecked(false);
                    ck_exit.setChecked(false);
                    ck_transfer.setChecked(false);
                    str = "1";
                }
                break;
            case R.id.ck_transit:
                if (isCheck) {
                    ck_imported.setChecked(false);
                    ck_transit.setChecked(true);
                    ck_exit.setChecked(false);
                    ck_transfer.setChecked(false);
                    str = "2";
                }
                break;
            case R.id.ck_exit:
                if (isCheck) {
                    ck_imported.setChecked(false);
                    ck_transit.setChecked(false);
                    ck_exit.setChecked(true);
                    ck_transfer.setChecked(false);
                    str = "3";
                }
                break;
            case R.id.ck_transfer:
                if (isCheck) {
                    ck_imported.setChecked(false);
                    ck_transit.setChecked(false);
                    ck_exit.setChecked(false);
                    ck_transfer.setChecked(true);
                    str = "4";
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_ship_inspectTime:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        et_ship_inspectTime.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.et_ship_buildTime:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        et_ship_buildTime.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.img_close_up:
                tabLin.setVisibility(View.GONE);
                img_close_up.setVisibility(View.GONE);
                img_close_down.setVisibility(View.VISIBLE);
                break;
            case R.id.img_close_down:
                tabLin.setVisibility(View.VISIBLE);
                img_close_up.setVisibility(View.VISIBLE);
                img_close_down.setVisibility(View.GONE);
                break;
            case R.id.add_img:
                dangerousGoodsLayout = new DangerousGoodsLayout(getActivity());
                tableLin2.addView(dangerousGoodsLayout);
                break;
            case R.id.del_img:
                if (tableLin2.getChildCount() != 1) {
                    tableLin2.removeViewAt(tableLin2.getChildCount() - 1);
                }
                break;

        }
    }

    public interface GetDataCallBack {
        void getResult(DangerousGoodsXianChangData data);
    }

    public void getData(GetDataCallBack callBack) {
        list.clear();
        sceneData.setShipNameCn(et_ship_chinaName.getText().toString().trim());
        sceneData.setShipNameEn(et_ship_englishName.getText().toString().trim());
        sceneData.setRegportName(et_ship_nationality.getText().toString().trim());
        sceneData.setShipGrosston(et_ship_weight.getText().toString().trim());
        sceneData.setBuildDate(et_ship_buildTime.getText().toString().trim());
        sceneData.setFlowTo(str);
        sceneData.setLastPort(et_ship_loading_port.getText().toString().trim());
        sceneData.setNextPort(et_ship_unloading_port.getText().toString().trim());
        sceneData.setTaskPort(et_ship_berth.getText().toString().trim());
        sceneData.setRoute(et_ship_route.getText().toString().trim());
        sceneData.setAgent(et_ship_agent.getText().toString().trim());
        sceneData.setBerthCode(et_ship_inspect.getText().toString().trim());
        sceneData.setCompanyName(et_ship_company.getText().toString().trim());
        sceneData.setInspector(et_ship_zhifa1.getText().toString().trim());
        sceneData.setInspectDate(et_ship_inspectTime.getText().toString().trim());
        for (int i = 0; i < tableLin2.getChildCount(); i++) {
            DangerousGoodsLayout dangerousGoodsLayout = (DangerousGoodsLayout) tableLin2.getChildAt(i);
            if (dangerousGoodsLayout.getDetail() == null) {

            } else {
                list.add(dangerousGoodsLayout.getDetail());
            }
        }
        data.setCtDangerPolluteScene(sceneData);
        data.setCtDangerPolluteSceneDetail(list);
        callBack.getResult(data);
    }

}
