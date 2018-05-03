package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CruiseShipAdapter;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.view.ICrewInfoView;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.DangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.mvp.table.xhgzjl.model.ICruiseWorkRecordModel;
import com.winfo.szrsp.app.mvp.table.xhgzjl.presenter.CruiseWorkRecordPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;
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
 * 巡航工作记录表
 * Created by HoBo on 2018/4/11.
 */

public class CruiseWorkRecordActivity extends FragmentActivity implements ICruiseWorkRecordActivity, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton imgBack;
    @BindView(R.id.etShipName)
    Spinner etShipName;
    @BindView(R.id.etShipPeople)
    EditText etShipPeople;
    @BindView(R.id.etCruiseDate)
    TextView etCruiseDate;
    @BindView(R.id.etCruiseTime)
    TextView etCruiseTime;
    @BindView(R.id.etCruiseWeather)
    EditText etCruiseWeather;
    @BindView(R.id.etCruiseSea)
    EditText etCruiseSea;
    @BindView(R.id.ck_rcxh)
    CheckBox ck_rcxh;
    @BindView(R.id.ck_jjhh)
    CheckBox ck_jjhh;
    @BindView(R.id.ck_hsdc)
    CheckBox ck_hsdc;
    @BindView(R.id.ck_yjjy)
    CheckBox ck_yjjy;
    @BindView(R.id.ck_zs)
    CheckBox ck_zs;
    @BindView(R.id.ck_qt)
    CheckBox ck_qt;
    @BindView(R.id.ck_hq)
    CheckBox ck_hq;
    @BindView(R.id.ck_gq)
    CheckBox ck_gq;
    @BindView(R.id.ck_kxq)
    CheckBox ck_kxq;
    @BindView(R.id.etItem1)
    TextView etItem1;
    @BindView(R.id.etItem2)
    TextView etItem2;
    @BindView(R.id.etItem3)
    EditText etItem3;
    @BindView(R.id.etItem4)
    EditText etItem4;
    @BindView(R.id.etItem5)
    EditText etItem5;
    @BindView(R.id.etItem6)
    EditText etItem6;
    @BindView(R.id.etItem7)
    EditText etItem7;
    @BindView(R.id.etOther)
    EditText etOther;
    @BindView(R.id.ckItem1_0)
    CheckBox ckItem1_0;
    @BindView(R.id.ckItem1_1)
    CheckBox ckItem1_1;
    @BindView(R.id.ckItem1_2)
    CheckBox ckItem1_2;
    @BindView(R.id.ckItem2_0)
    CheckBox ckItem2_0;
    @BindView(R.id.ckItem2_1)
    CheckBox ckItem2_1;
    @BindView(R.id.ckItem2_2)
    CheckBox ckItem2_2;
    @BindView(R.id.ckItem3_0)
    CheckBox ckItem3_0;
    @BindView(R.id.ckItem3_1)
    CheckBox ckItem3_1;
    @BindView(R.id.ckItem3_2)
    CheckBox ckItem3_2;
    @BindView(R.id.ckItem4_0)
    CheckBox ckItem4_0;
    @BindView(R.id.ckItem4_1)
    CheckBox ckItem4_1;
    @BindView(R.id.ckItem4_2)
    CheckBox ckItem4_2;
    @BindView(R.id.ckItem5_0)
    CheckBox ckItem5_0;
    @BindView(R.id.ckItem5_1)
    CheckBox ckItem5_1;
    @BindView(R.id.ckItem5_2)
    CheckBox ckItem5_2;
    @BindView(R.id.ckItem6_0)
    CheckBox ckItem6_0;
    @BindView(R.id.ckItem6_1)
    CheckBox ckItem6_1;
    @BindView(R.id.ckItem6_2)
    CheckBox ckItem6_2;
    @BindView(R.id.ckItem7_0)
    CheckBox ckItem7_0;
    @BindView(R.id.ckItem7_1)
    CheckBox ckItem7_1;
    @BindView(R.id.ckItem7_2)
    CheckBox ckItem7_2;
    @BindView(R.id.ckItem8_0)
    CheckBox ckItem8_0;
    @BindView(R.id.ckItem8_1)
    CheckBox ckItem8_1;
    @BindView(R.id.ckItem8_2)
    CheckBox ckItem8_2;
    @BindView(R.id.etItemOther)
    EditText etItemOther;
    @BindView(R.id.etCruiseNum)
    EditText etCruiseNum;
    @BindView(R.id.etCruiseHour)
    EditText etCruiseHour;
    @BindView(R.id.etCruiseVoyage)
    EditText etCruiseVoyage;
    @BindView(R.id.etCruisePeopleNum)
    EditText etCruisePeopleNum;
    @BindView(R.id.etCruiseGarrisonNum)
    EditText etCruiseGarrisonNum;
    @BindView(R.id.etCruiseGarrisonTime)
    EditText etCruiseGarrisonTime;
    @BindView(R.id.etCruiseInspectShipNum)
    EditText etCruiseInspectShipNum;
    @BindView(R.id.etCruiseFindPeccancyNum)
    EditText etCruiseFindPeccancyNum;
    @BindView(R.id.etCruisePunishNum)
    EditText etCruisePunishNum;
    @BindView(R.id.etCruisePunishMoney)
    EditText etCruisePunishMoney;
    @BindView(R.id.etCruiseEscortNum)
    EditText etCruiseEscortNum;
    @BindView(R.id.etCruiseRescueNum)
    EditText etCruiseRescueNum;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.lin_xhjy)
    LinearLayout lin_xhjy;
    @BindView(R.id.lin_ycwt)
    LinearLayout lin_ycwt;
    @BindView(R.id.imgAdd1)
    ImageView imgAdd1;
    @BindView(R.id.imgDel1)
    ImageView imgDel1;
    @BindView(R.id.imgAdd2)
    ImageView imgAdd2;
    @BindView(R.id.imgDel2)
    ImageView imgDel2;
    private TimePickerView timePickerView;
    private MinuteTimePickerView mTimePickerView;
    private Dialog dialog;
    private Dialog downloadDialog;
    private CruiseWorkRecordPresenter presenter;
    private CruiseWorkData data;
    private CruiseWorkData.ctCruisingRecore ctCruisingRecore;
    private List<CruiseWorkData.ctCruisingRiskDetail> ctCruisingRiskDetailsList;
    private List<CruiseWorkData.ctCruisingSummaryDatail> ctCruisingSummaryDatailsList;
    private HangLuDialog hangLuDialog;
    private MaoDiDialog maoDiDialog;
    private CruiseSummaryLayout cruiseSummaryLayout;
    private CruiseSituationLayout cruiseSituationLayout;

    private String year = "";
    private String month = "";
    private String day = "";
    private String weekDay = "";

    private String beginTime = "";
    private String endTime = "";

    private String temp1 = "0";
    private String temp2 = "0";
    private String temp3 = "0";
    private String temp4 = "0";
    private String temp5 = "0";
    private String temp6 = "0";
    private String temp7 = "0";
    private String temp8 = "0";
    private String temp9 = "0";

    private String item1 = "0";
    private String item2 = "0";
    private String item3 = "0";
    private String item4 = "0";
    private String item5 = "0";
    private String item6 = "0";
    private String item7 = "0";
    private String item8 = "0";


    private MyHandler myHandler;
    private boolean isSummary;
    private String taskTypeId;

    private String shipName;
    private String shipMMsi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xhgzjl_table);
        ButterKnife.bind(this);
        myHandler = new MyHandler(this);
        initView();
        getTaskTypeId();
        initData();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getTaskInspectionAll(dialog, false);

    }

    private void initData() {
        presenter.findShip();
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        data = new CruiseWorkData();
        ctCruisingRecore = new CruiseWorkData.ctCruisingRecore();
        ctCruisingRiskDetailsList = new ArrayList<>();
        ctCruisingSummaryDatailsList = new ArrayList<>();
        presenter = new CruiseWorkRecordPresenter();
        presenter.attachMvpView(this);

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日E", Locale.CHINA);
        String time = format.format(new Date());
        etCruiseDate.setText(time);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy", Locale.CHINA);
        year = format1.format(new Date());
        SimpleDateFormat format2 = new SimpleDateFormat("MM", Locale.CHINA);
        month = format2.format(new Date());
        SimpleDateFormat format3 = new SimpleDateFormat("dd", Locale.CHINA);
        day = format3.format(new Date());
        SimpleDateFormat format4 = new SimpleDateFormat("E", Locale.CHINA);
        weekDay = format4.format(new Date());
        etShipPeople.setText(ACache.get(this).getAsString("userName"));
        mTimePickerView = new MinuteTimePickerView(this, MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());


        cruiseSummaryLayout = new CruiseSummaryLayout(this);
        lin_xhjy.addView(cruiseSummaryLayout);
        cruiseSituationLayout = new CruiseSituationLayout(this);
        lin_ycwt.addView(cruiseSituationLayout);
    }

    private void getTaskTypeId() {
        taskTypeId = getIntent().getStringExtra("taskTypeId");
    }

    private void initEvent() {
        imgBack.setOnClickListener(this);
        etCruiseDate.setOnClickListener(this);
        etCruiseTime.setOnClickListener(this);
        etItem1.setOnClickListener(this);
        etItem2.setOnClickListener(this);
        ck_rcxh.setOnCheckedChangeListener(this);
        ck_jjhh.setOnCheckedChangeListener(this);
        ck_hsdc.setOnCheckedChangeListener(this);
        ck_yjjy.setOnCheckedChangeListener(this);
        ck_zs.setOnCheckedChangeListener(this);
        ck_qt.setOnCheckedChangeListener(this);
        ck_hq.setOnCheckedChangeListener(this);
        ck_gq.setOnCheckedChangeListener(this);
        ck_kxq.setOnCheckedChangeListener(this);
        ckItem1_0.setOnCheckedChangeListener(this);
        ckItem1_1.setOnCheckedChangeListener(this);
        ckItem1_2.setOnCheckedChangeListener(this);
        ckItem2_0.setOnCheckedChangeListener(this);
        ckItem2_1.setOnCheckedChangeListener(this);
        ckItem2_2.setOnCheckedChangeListener(this);
        ckItem3_0.setOnCheckedChangeListener(this);
        ckItem3_1.setOnCheckedChangeListener(this);
        ckItem3_2.setOnCheckedChangeListener(this);
        ckItem4_0.setOnCheckedChangeListener(this);
        ckItem4_1.setOnCheckedChangeListener(this);
        ckItem4_2.setOnCheckedChangeListener(this);
        ckItem5_0.setOnCheckedChangeListener(this);
        ckItem5_1.setOnCheckedChangeListener(this);
        ckItem5_2.setOnCheckedChangeListener(this);
        ckItem6_0.setOnCheckedChangeListener(this);
        ckItem6_1.setOnCheckedChangeListener(this);
        ckItem6_2.setOnCheckedChangeListener(this);
        ckItem7_0.setOnCheckedChangeListener(this);
        ckItem7_1.setOnCheckedChangeListener(this);
        ckItem7_2.setOnCheckedChangeListener(this);
        ckItem8_0.setOnCheckedChangeListener(this);
        ckItem8_1.setOnCheckedChangeListener(this);
        ckItem8_2.setOnCheckedChangeListener(this);
        imgAdd1.setOnClickListener(this);
        imgDel1.setOnClickListener(this);
        imgAdd2.setOnClickListener(this);
        imgDel2.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.etCruiseDate:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日E", Locale.CHINA);
                        String time = format.format(date);
                        etCruiseDate.setText(time);
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy", Locale.CHINA);
                        year = format1.format(date);
                        SimpleDateFormat format2 = new SimpleDateFormat("MM", Locale.CHINA);
                        month = format2.format(date);
                        SimpleDateFormat format3 = new SimpleDateFormat("dd", Locale.CHINA);
                        day = format3.format(date);
                        SimpleDateFormat format4 = new SimpleDateFormat("E", Locale.CHINA);
                        weekDay = format4.format(date);
                    }
                });
                timePickerView.show();
                break;
            case R.id.etCruiseTime:
                showTimePickView();
                break;
            case R.id.etItem1:
                hangLuDialog = new HangLuDialog(this);
                hangLuDialog.show(etItem1.getText().toString().trim());
                hangLuDialog.setSetStrData(new HangLuDialog.SetStrData() {
                    @Override
                    public void setNum(String s) {
                        etItem1.setText(s);
                    }
                });
                break;
            case R.id.etItem2:
                maoDiDialog = new MaoDiDialog(this);
                maoDiDialog.show(etItem2.getText().toString().trim());
                maoDiDialog.setSetStrData(new MaoDiDialog.SetStrData() {
                    @Override
                    public void setNum(String s) {
                        etItem2.setText(s);
                    }
                });
                break;
            case R.id.imgAdd1:
                cruiseSummaryLayout = new CruiseSummaryLayout(this);
                lin_xhjy.addView(cruiseSummaryLayout);
                break;
            case R.id.imgDel1:
                if (lin_xhjy.getChildCount() != 1) {
                    lin_xhjy.removeViewAt(lin_xhjy.getChildCount() - 1);
                }
                break;
            case R.id.imgAdd2:
                cruiseSituationLayout = new CruiseSituationLayout(this);
                lin_ycwt.addView(cruiseSituationLayout);
                break;
            case R.id.imgDel2:
                if (lin_ycwt.getChildCount() != 1) {
                    lin_ycwt.removeViewAt(lin_ycwt.getChildCount() - 1);
                }
                break;
            case R.id.btn_save:
                for (int i = 0; i < lin_xhjy.getChildCount(); i++) {
                    CruiseSummaryLayout cruiseSummaryLayout = (CruiseSummaryLayout) lin_xhjy.getChildAt(i);
                    if (cruiseSummaryLayout.getSummaryDatail() == null) {
                        isSummary = false;
                    } else {
                        isSummary = true;
                    }
                }
                if (!isSummary) {
                    ToastUtils.showToast(this, "至少填写一行巡航纪要");
                } else {
                    presenter.subData();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ck_rcxh:
                if (isCheck) {
                    temp1 = "1";
                } else {
                    temp1 = "0";
                }
                break;
            case R.id.ck_jjhh:
                if (isCheck) {
                    temp2 = "1";
                } else {
                    temp2 = "0";
                }
                break;
            case R.id.ck_hsdc:
                if (isCheck) {
                    temp3 = "1";
                } else {
                    temp3 = "0";
                }
                break;
            case R.id.ck_yjjy:
                if (isCheck) {
                    temp4 = "1";
                } else {
                    temp4 = "0";
                }
                break;
            case R.id.ck_zs:
                if (isCheck) {
                    temp5 = "1";
                } else {
                    temp5 = "0";
                }
                break;
            case R.id.ck_qt:
                if (isCheck) {
                    temp6 = "1";
                } else {
                    temp6 = "0";
                }
                break;
            case R.id.ck_hq:
                if (isCheck) {
                    ck_gq.setChecked(false);
                    ck_kxq.setChecked(false);
                    temp7 = "1";
                    temp8 = "0";
                    temp9 = "0";
                } else {
                    temp7 = "0";
                    temp8 = "0";
                    temp9 = "0";
                }
                break;
            case R.id.ck_gq:
                if (isCheck) {
                    ck_hq.setChecked(false);
                    ck_kxq.setChecked(false);
                    temp8 = "1";
                    temp7 = "0";
                    temp9 = "0";
                } else {
                    temp8 = "0";
                    temp7 = "0";
                    temp9 = "0";
                }
                break;
            case R.id.ck_kxq:
                if (isCheck) {
                    ck_hq.setChecked(false);
                    ck_gq.setChecked(false);
                    temp9 = "1";
                    temp7 = "0";
                    temp8 = "0";
                } else {
                    temp9 = "0";
                    temp7 = "0";
                    temp8 = "0";
                }
                break;
            case R.id.ckItem1_0:
                if (ckItem1_0.isChecked()) {
                    ckItem1_1.setChecked(false);
                    ckItem1_2.setChecked(false);
                    item1 = "1";
                }
                break;
            case R.id.ckItem1_1:
                if (ckItem1_1.isChecked()) {
                    ckItem1_0.setChecked(false);
                    ckItem1_2.setChecked(false);
                    item1 = "2";
                }
                break;
            case R.id.ckItem1_2:
                if (ckItem1_2.isChecked()) {
                    ckItem1_0.setChecked(false);
                    ckItem1_1.setChecked(false);
                    item1 = "3";
                }
                break;
            case R.id.ckItem2_0:
                if (ckItem2_0.isChecked()) {
                    ckItem2_1.setChecked(false);
                    ckItem2_2.setChecked(false);
                    item2 = "1";
                }
                break;
            case R.id.ckItem2_1:
                if (ckItem2_1.isChecked()) {
                    ckItem2_0.setChecked(false);
                    ckItem2_2.setChecked(false);
                    item2 = "2";
                }
                break;
            case R.id.ckItem2_2:
                if (ckItem2_2.isChecked()) {
                    ckItem2_0.setChecked(false);
                    ckItem2_1.setChecked(false);
                    item2 = "3";
                }
                break;
            case R.id.ckItem3_0:
                if (ckItem3_0.isChecked()) {
                    ckItem3_1.setChecked(false);
                    ckItem3_2.setChecked(false);
                    item3 = "1";
                }
                break;
            case R.id.ckItem3_1:
                if (ckItem3_1.isChecked()) {
                    ckItem3_0.setChecked(false);
                    ckItem3_2.setChecked(false);
                    item3 = "2";
                }
                break;
            case R.id.ckItem3_2:
                if (ckItem3_2.isChecked()) {
                    ckItem3_0.setChecked(false);
                    ckItem3_1.setChecked(false);
                    item3 = "3";
                }
                break;
            case R.id.ckItem4_0:
                if (ckItem4_0.isChecked()) {
                    ckItem4_1.setChecked(false);
                    ckItem4_2.setChecked(false);
                    item4 = "1";
                }
                break;
            case R.id.ckItem4_1:
                if (ckItem4_1.isChecked()) {
                    ckItem4_0.setChecked(false);
                    ckItem4_2.setChecked(false);
                    item4 = "2";
                }
                break;
            case R.id.ckItem4_2:
                if (ckItem4_2.isChecked()) {
                    ckItem4_0.setChecked(false);
                    ckItem4_1.setChecked(false);
                    item4 = "3";
                }
                break;
            case R.id.ckItem5_0:
                if (ckItem5_0.isChecked()) {
                    ckItem5_1.setChecked(false);
                    ckItem5_2.setChecked(false);
                    item5 = "1";
                }
                break;
            case R.id.ckItem5_1:
                if (ckItem5_1.isChecked()) {
                    ckItem5_0.setChecked(false);
                    ckItem5_2.setChecked(false);
                    item5 = "2";
                }
                break;
            case R.id.ckItem5_2:
                if (ckItem5_2.isChecked()) {
                    ckItem5_0.setChecked(false);
                    ckItem5_1.setChecked(false);
                    item5 = "3";
                }
                break;
            case R.id.ckItem6_0:
                if (ckItem6_0.isChecked()) {
                    ckItem6_1.setChecked(false);
                    ckItem6_2.setChecked(false);
                    item6 = "1";
                }
                break;
            case R.id.ckItem6_1:
                if (ckItem6_1.isChecked()) {
                    ckItem6_0.setChecked(false);
                    ckItem6_2.setChecked(false);
                    item6 = "2";
                }
                break;
            case R.id.ckItem6_2:
                if (ckItem6_2.isChecked()) {
                    ckItem6_0.setChecked(false);
                    ckItem6_1.setChecked(false);
                    item6 = "3";
                }
                break;
            case R.id.ckItem7_0:
                if (ckItem7_0.isChecked()) {
                    ckItem7_1.setChecked(false);
                    ckItem7_2.setChecked(false);
                    item7 = "1";
                }
                break;
            case R.id.ckItem7_1:
                if (ckItem7_1.isChecked()) {
                    ckItem7_0.setChecked(false);
                    ckItem7_2.setChecked(false);
                    item7 = "2";
                }
                break;
            case R.id.ckItem7_2:
                if (ckItem7_2.isChecked()) {
                    ckItem7_0.setChecked(false);
                    ckItem7_1.setChecked(false);
                    item7 = "3";
                }
                break;
            case R.id.ckItem8_0:
                if (ckItem8_0.isChecked()) {
                    ckItem8_1.setChecked(false);
                    ckItem8_2.setChecked(false);
                    item8 = "1";
                }
                break;
            case R.id.ckItem8_1:
                if (ckItem8_1.isChecked()) {
                    ckItem8_0.setChecked(false);
                    ckItem8_2.setChecked(false);
                    item8 = "2";
                }
                break;
            case R.id.ckItem8_2:
                if (ckItem8_2.isChecked()) {
                    ckItem8_0.setChecked(false);
                    ckItem8_1.setChecked(false);
                    item8 = "3";
                }
                break;
        }
    }

    /**
     * 当前选择时间的TextView
     */
    private void showTimePickView() {
        String trim = etCruiseTime.getText().toString().trim();
        mTimePickerView.setPicker(trim);
        mTimePickerView.show();
        mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                etCruiseTime.setText(time);
                String[] str = null;
                str = time.split("-");
                beginTime = str[0];
                endTime = str[1];
            }
        });
    }


    @Override
    public CruiseWorkData getData() {
        ctCruisingSummaryDatailsList.clear();
        ctCruisingRiskDetailsList.clear();
        ctCruisingRecore.setTaskId(taskTypeId);
        ctCruisingRecore.setShipName(shipName);
        ctCruisingRecore.setMmsi(shipMMsi);
        ctCruisingRecore.setLawEnforcementName(etShipPeople.getText().toString().trim());
        ctCruisingRecore.setDateYear(year);
        ctCruisingRecore.setDateMonth(month);
        ctCruisingRecore.setDateDay(day);
        ctCruisingRecore.setWeekNum(weekDay);
        ctCruisingRecore.setBeginTime(beginTime);
        ctCruisingRecore.setEndTime(endTime);
        ctCruisingRecore.setWeather(etCruiseWeather.getText().toString().trim());
        ctCruisingRecore.setSeaState(etCruiseSea.getText().toString().trim());
        ctCruisingRecore.setTemp1(temp1);
        ctCruisingRecore.setTemp2(temp2);
        ctCruisingRecore.setTemp3(temp3);
        ctCruisingRecore.setTemp4(temp4);
        ctCruisingRecore.setTemp5(temp5);
        ctCruisingRecore.setTemp6(temp6);
        ctCruisingRecore.setTemp7(temp7);
        ctCruisingRecore.setTemp8(temp8);
        ctCruisingRecore.setTemp9(temp9);
        ctCruisingRecore.setSeaRoute(etItem1.getText().toString().trim());
        ctCruisingRecore.setAnchorageArea(etItem2.getText().toString().trim());
        ctCruisingRecore.setRoadWorkArea(etItem3.getText().toString().trim());
        ctCruisingRecore.setWharf(etItem4.getText().toString().trim());
        ctCruisingRecore.setMudDumpingArea(etItem5.getText().toString().trim());
        ctCruisingRecore.setRiskWharf(etItem6.getText().toString().trim());
        ctCruisingRecore.setBridge(etItem7.getText().toString().trim());
        ctCruisingRecore.setOtherArea(etOther.getText().toString().trim());
        ctCruisingRecore.setItem1(item1);
        ctCruisingRecore.setItem2(item2);
        ctCruisingRecore.setItem3(item3);
        ctCruisingRecore.setItem4(item4);
        ctCruisingRecore.setItem5(item5);
        ctCruisingRecore.setItem6(item6);
        ctCruisingRecore.setItem7(item7);
        ctCruisingRecore.setItem8(item8);
        ctCruisingRecore.setOtherAbnormal(etItemOther.getText().toString().trim());
        ctCruisingRecore.setCruiseTimes(etCruiseNum.getText().toString().trim());
        ctCruisingRecore.setCruiseHours(etCruiseHour.getText().toString().trim());
        ctCruisingRecore.setCruiseSeamile(etCruiseVoyage.getText().toString().trim());
        ctCruisingRecore.setLawEnforcementNumber(etCruisePeopleNum.getText().toString().trim());
        ctCruisingRecore.setGarrisonTimes(etCruiseGarrisonNum.getText().toString().trim());
        ctCruisingRecore.setGarrisonHours(etCruiseGarrisonTime.getText().toString().trim());
        ctCruisingRecore.setVoyageTimes(etCruiseInspectShipNum.getText().toString().trim());
        ctCruisingRecore.setViolationTimes(etCruiseFindPeccancyNum.getText().toString().trim());
        ctCruisingRecore.setEscortTimes(etCruiseEscortNum.getText().toString().trim());
        ctCruisingRecore.setSalvationTimes(etCruiseRescueNum.getText().toString().trim());

        for (int i = 0; i < lin_xhjy.getChildCount(); i++) {
            CruiseSummaryLayout cruiseSummaryLayout = (CruiseSummaryLayout) lin_xhjy.getChildAt(i);
            if (cruiseSummaryLayout.getSummaryDatail() == null) {
            } else {
                ctCruisingSummaryDatailsList.add(cruiseSummaryLayout.getSummaryDatail());
            }
        }

        for (int i = 0; i < lin_ycwt.getChildCount(); i++) {
            CruiseSituationLayout cruiseSituationLayout = (CruiseSituationLayout) lin_ycwt.getChildAt(i);
            if (cruiseSituationLayout.getTaskData() == null) {

            } else {
                ctCruisingRiskDetailsList.add(cruiseSituationLayout.getTaskData());
            }
        }

        data.setCtCruisingRecore(ctCruisingRecore);
        data.setCtCruisingSummaryDatailList(ctCruisingSummaryDatailsList);
        data.setCtCruisingRiskDetailList(ctCruisingRiskDetailsList);
        return data;
    }

    @Override
    public void setDetailData(String msg, CruiseWorkData data) {

    }

    private String sub_msg;
    private String id;

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSuccess(String s, String resultData) {
        dialog.dismiss();
        this.sub_msg = s;
        this.id = resultData;
        this.namepath = "";
        myHandler.sendEmptyMessage(500);
    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
        this.sub_msg = msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void setInspectionAll(List<TaskInspectionItemData> taskInspectionItemData) {
        this.taskInspectionItemData = bulid(taskInspectionItemData);
    }

    List<TaskInspectionItemData> taskInspectionItemData;

    @Override
    public void onLoadFaild(String msg) {
        dialog.dismiss();
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void setShip(final List<CruiseShipData> data) {
        dialog.dismiss();
        CruiseShipAdapter adapter = new CruiseShipAdapter(this, data);
        etShipName.setAdapter(adapter);
        etShipName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shipName = data.get(i).getShipNameCn();
                shipMMsi = data.get(i).getMmsi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onFindShipFaile(String msg) {
        dialog.dismiss();
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void setInspectionByName(List<TaskInspectionItemByNameData> data) {

    }

    public List<TaskInspectionItemData> getInspectionData() {
        return taskInspectionItemData;
    }

    public List<TaskInspectionItemData> bulid(List<TaskInspectionItemData> taskInspectionItemDataList) {

        List<TaskInspectionItemData> taskInspectionItemDatas = new ArrayList<>();

        for (TaskInspectionItemData taskInspectionItemData : taskInspectionItemDataList) {

            if ("".equals(taskInspectionItemData.getItemFuseFaterId())) {
                taskInspectionItemDatas.add(taskInspectionItemData);
            }

            for (TaskInspectionItemData it : taskInspectionItemDataList) {
                if (it.getItemFuseFaterId().equals(taskInspectionItemData.getItemFuseId())) {
                    if (taskInspectionItemData.getChild() == null) {
                        taskInspectionItemData.setChild(new ArrayList<TaskInspectionItemData>());
                    }
                    taskInspectionItemData.getChild().add(it);
                }
            }
        }
        return taskInspectionItemDatas;
    }

    private static class MyHandler extends Handler {

        WeakReference<CruiseWorkRecordActivity> cruiseWorkRecordActivityWeakReference;

        MyHandler(CruiseWorkRecordActivity cruiseWorkRecordActivity) {
            this.cruiseWorkRecordActivityWeakReference = new WeakReference<>(cruiseWorkRecordActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final CruiseWorkRecordActivity supervisionActivity = cruiseWorkRecordActivityWeakReference.get();
            int what = msg.what;
            if (what == 500) {
                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg + "是否打印检查表？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        String name = "巡航工作记录表" + "_" + supervisionActivity.id + ".pdf";
                        supervisionActivity.namepath = supervisionActivity.path + name;
                        File file = new File(supervisionActivity.namepath);
                        if (file.exists()) {
                            //直接打印
                            Intent intent = new Intent(supervisionActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2", supervisionActivity.namepath);
                            supervisionActivity.startActivity(intent);
                        } else {
                            supervisionActivity.downloadPDF();
                            supervisionActivity.downloadDialog.show();
                            supervisionActivity.httpDownManager.startDown(supervisionActivity.apkApi);
                        }
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });
            }
            if (what == 600) {

                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg + "是否重新提交？", "重新提交", "返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        supervisionActivity.presenter.subData();
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                    }

                });
            }
        }
    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    String path = SDCardUtils.getRootDirectory() + "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath = "";
    DownInfo apkApi;

    private void downloadPDF() {
        String name = "巡航工作记录表" + "_" + id + ".pdf";
        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }

        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctCruisingRecoreRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params
        );
        File outputFile = new File(namepath);

        apkApi.setSavePath(outputFile.getAbsolutePath());
        apkApi.setListener(new HttpProgressOnNextListener() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onComplete() {
                downloadDialog.dismiss();
                Intent intent = new Intent(CruiseWorkRecordActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2", namepath);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                downloadDialog.dismiss();
//                ToastUtils.showToast(CruiseWorkRecordActivity.this, e.toString());
                super.onError(e);
                File file = new File(namepath);
                if (file.isFile()) {
                    file.delete();
                }
                //           deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
    }
}
