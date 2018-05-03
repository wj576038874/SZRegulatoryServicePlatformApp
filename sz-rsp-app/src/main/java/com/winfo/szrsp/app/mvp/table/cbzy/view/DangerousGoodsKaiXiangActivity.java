package com.winfo.szrsp.app.mvp.table.cbzy.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbzy.presenter.DangerousGoodsKaiXiangPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.BitmapUtils;
import com.winfo.szrsp.app.utils.DateFormatUtils;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.LinePathView;
import com.winfo.szrsp.app.widget.SignatureView;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 船舶载运危险货物/污染危害性货物集装箱开箱检查记录表
 * Created by HoBo on 2018/3/7.
 */

public class DangerousGoodsKaiXiangActivity extends Activity implements IDangerousGoodsKaiXiangActivity, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.et_ship_deeSea)
    EditText et_ship_deeSea;
    @BindView(R.id.et_ship_time)
    EditText et_ship_time;
    @BindView(R.id.et_ship_num)
    EditText et_ship_num;
    @BindView(R.id.et_packing_company)
    EditText et_packing_company;
    @BindView(R.id.et_packing_inspector)
    EditText et_packing_inspector;
    @BindView(R.id.et_packing_tel)
    EditText et_packing_tel;
    @BindView(R.id.et_carrier_ship)
    EditText et_carrier_ship;
    @BindView(R.id.et_carrier_people)
    EditText et_carrier_people;
    @BindView(R.id.et_voyage_number)
    EditText et_voyage_number;
    @BindView(R.id.et_consignment_people)
    EditText et_consignment_people;
    @BindView(R.id.et_order_num)
    EditText et_order_num;
    @BindView(R.id.et_box_num)
    EditText et_box_num;
    @BindView(R.id.ed_open_box_book_num)
    EditText ed_open_box_book_num;
    @BindView(R.id.et_inspect_before_num)
    EditText et_inspect_before_num;
    @BindView(R.id.et_inspect_after_num)
    EditText et_inspect_after_num;
    @BindView(R.id.et_goods_name)
    EditText et_goods_name;
    @BindView(R.id.et_un_num)
    EditText et_un_num;
    @BindView(R.id.et_packing_form)
    EditText et_packing_form;
    @BindView(R.id.et_goods_type)
    EditText et_goods_type;
    @BindView(R.id.et_goods_danger)
    EditText et_goods_danger;
    @BindView(R.id.et_goods_contaminants)
    EditText et_goods_contaminants;

    @BindView(R.id.ck_1_yes)
    CheckBox ck_1_yes;
    @BindView(R.id.ck_2_yes)
    CheckBox ck_2_yes;
    @BindView(R.id.ck_3_yes)
    CheckBox ck_3_yes;
    @BindView(R.id.ck_4_yes)
    CheckBox ck_4_yes;
    @BindView(R.id.ck_5_yes)
    CheckBox ck_5_yes;
    @BindView(R.id.ck_6_yes)
    CheckBox ck_6_yes;
    @BindView(R.id.ck_7_yes)
    CheckBox ck_7_yes;
    @BindView(R.id.ck_8_yes)
    CheckBox ck_8_yes;
    @BindView(R.id.ck_9_yes)
    CheckBox ck_9_yes;
    @BindView(R.id.ck_10_yes)
    CheckBox ck_10_yes;
    @BindView(R.id.ck_11_yes)
    CheckBox ck_11_yes;
    @BindView(R.id.ck_12_yes)
    CheckBox ck_12_yes;
    @BindView(R.id.ck_13_yes)
    CheckBox ck_13_yes;
    @BindView(R.id.ck_14_yes)
    CheckBox ck_14_yes;
    @BindView(R.id.ck_15_yes)
    CheckBox ck_15_yes;
    @BindView(R.id.ck_16_yes)
    CheckBox ck_16_yes;
    @BindView(R.id.ck_17_yes)
    CheckBox ck_17_yes;
    @BindView(R.id.ck_18_yes)
    CheckBox ck_18_yes;
    @BindView(R.id.ck_19_yes)
    CheckBox ck_19_yes;


    @BindView(R.id.ck_1_no)
    CheckBox ck_1_no;
    @BindView(R.id.ck_2_no)
    CheckBox ck_2_no;
    @BindView(R.id.ck_3_no)
    CheckBox ck_3_no;
    @BindView(R.id.ck_4_no)
    CheckBox ck_4_no;
    @BindView(R.id.ck_5_no)
    CheckBox ck_5_no;
    @BindView(R.id.ck_6_no)
    CheckBox ck_6_no;
    @BindView(R.id.ck_7_no)
    CheckBox ck_7_no;
    @BindView(R.id.ck_8_no)
    CheckBox ck_8_no;
    @BindView(R.id.ck_9_no)
    CheckBox ck_9_no;
    @BindView(R.id.ck_10_no)
    CheckBox ck_10_no;
    @BindView(R.id.ck_11_no)
    CheckBox ck_11_no;
    @BindView(R.id.ck_12_no)
    CheckBox ck_12_no;
    @BindView(R.id.ck_13_no)
    CheckBox ck_13_no;
    @BindView(R.id.ck_14_no)
    CheckBox ck_14_no;
    @BindView(R.id.ck_15_no)
    CheckBox ck_15_no;
    @BindView(R.id.ck_16_no)
    CheckBox ck_16_no;
    @BindView(R.id.ck_17_no)
    CheckBox ck_17_no;
    @BindView(R.id.ck_18_no)
    CheckBox ck_18_no;
    @BindView(R.id.ck_19_no)
    CheckBox ck_19_no;


    @BindView(R.id.et_exceptions_record)
    EditText et_exceptions_record;
    @BindView(R.id.ck_correct)
    CheckBox ck_correct;
    @BindView(R.id.ck_defects_exist)
    CheckBox ck_defects_exist;
    @BindView(R.id.ck_false)
    CheckBox ck_false;
    @BindView(R.id.ck_other)
    CheckBox ck_other;
    @BindView(R.id.et_other)
    EditText et_other;
    @BindView(R.id.et_process_results)
    EditText et_process_results;
    @BindView(R.id.et_zhifa_people1)
    EditText et_zhifa_people1;
    @BindView(R.id.et_zhifa_cardNum1)
    EditText et_zhifa_cardNum1;
    @BindView(R.id.et_autograph)
    TextView et_autograph;
    @BindView(R.id.img_autograph)
    ImageView img_autograph;
    @BindView(R.id.btn_save)
    Button btn_save;

    private DangerousGoodsKaiXiangData data;
    private TimePickerView timePickerView;
    private Dialog dialog;
    private Dialog downloadDialog;
    private MyHandler myHandler;
    private DangerousGoodsKaiXiangPresenter presenter;
    private String ckItem1, ckItem2, ckItem3, ckItem4, ckItem5, ckItem6, ckItem7, ckItem8, ckItem9, ckItem10,
            ckItem11, ckItem12, ckItem13, ckItem14, ckItem15, ckItem16, ckItem17, ckItem18, ckItem19;
    private String ckOpinion;
    private String qm_path;
    private Bitmap bm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbzy_wxhwkxjc);
        ButterKnife.bind(this);
        myHandler = new MyHandler(this);
        initView();
        initEvent();
    }

    private void initView() {
        data = new DangerousGoodsKaiXiangData();
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        presenter = new DangerousGoodsKaiXiangPresenter(this);

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        et_ship_inspectTime.setText(time);
        et_zhifa_people1.setText(ACache.get(this).getAsString("userName"));
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        et_ship_inspectTime.setOnClickListener(this);
        ck_1_yes.setOnCheckedChangeListener(this);
        ck_2_yes.setOnCheckedChangeListener(this);
        ck_3_yes.setOnCheckedChangeListener(this);
        ck_4_yes.setOnCheckedChangeListener(this);
        ck_5_yes.setOnCheckedChangeListener(this);
        ck_6_yes.setOnCheckedChangeListener(this);
        ck_7_yes.setOnCheckedChangeListener(this);
        ck_8_yes.setOnCheckedChangeListener(this);
        ck_9_yes.setOnCheckedChangeListener(this);
        ck_10_yes.setOnCheckedChangeListener(this);
        ck_11_yes.setOnCheckedChangeListener(this);
        ck_12_yes.setOnCheckedChangeListener(this);
        ck_13_yes.setOnCheckedChangeListener(this);
        ck_14_yes.setOnCheckedChangeListener(this);
        ck_15_yes.setOnCheckedChangeListener(this);
        ck_16_yes.setOnCheckedChangeListener(this);
        ck_17_yes.setOnCheckedChangeListener(this);
        ck_18_yes.setOnCheckedChangeListener(this);
        ck_19_yes.setOnCheckedChangeListener(this);


        ck_1_no.setOnCheckedChangeListener(this);
        ck_2_no.setOnCheckedChangeListener(this);
        ck_3_no.setOnCheckedChangeListener(this);
        ck_4_no.setOnCheckedChangeListener(this);
        ck_5_no.setOnCheckedChangeListener(this);
        ck_6_no.setOnCheckedChangeListener(this);
        ck_7_no.setOnCheckedChangeListener(this);
        ck_8_no.setOnCheckedChangeListener(this);
        ck_9_no.setOnCheckedChangeListener(this);
        ck_10_no.setOnCheckedChangeListener(this);
        ck_11_no.setOnCheckedChangeListener(this);
        ck_12_no.setOnCheckedChangeListener(this);
        ck_13_no.setOnCheckedChangeListener(this);
        ck_14_no.setOnCheckedChangeListener(this);
        ck_15_no.setOnCheckedChangeListener(this);
        ck_16_no.setOnCheckedChangeListener(this);
        ck_17_no.setOnCheckedChangeListener(this);
        ck_18_no.setOnCheckedChangeListener(this);
        ck_19_no.setOnCheckedChangeListener(this);


        ck_correct.setOnCheckedChangeListener(this);
        ck_defects_exist.setOnCheckedChangeListener(this);
        ck_false.setOnCheckedChangeListener(this);
        ck_other.setOnCheckedChangeListener(this);
        et_autograph.setOnClickListener(this);
        img_autograph.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ck_1_yes:
                if (isCheck) {
                    ck_1_yes.setChecked(true);
                    ck_1_no.setChecked(false);
                    ckItem1 = "1";
                }
                break;
            case R.id.ck_2_yes:
                if (isCheck) {
                    ck_2_yes.setChecked(true);
                    ck_2_no.setChecked(false);
                    ckItem2 = "1";
                }
                break;
            case R.id.ck_3_yes:
                if (isCheck) {
                    ck_3_yes.setChecked(true);
                    ck_3_no.setChecked(false);
                    ckItem3 = "1";
                }
                break;
            case R.id.ck_4_yes:
                if (isCheck) {
                    ck_4_yes.setChecked(true);
                    ck_4_no.setChecked(false);
                    ckItem4 = "1";
                }
                break;
            case R.id.ck_5_yes:
                if (isCheck) {
                    ck_5_yes.setChecked(true);
                    ck_5_no.setChecked(false);
                    ckItem5 = "1";
                }
                break;
            case R.id.ck_6_yes:
                if (isCheck) {
                    ck_6_yes.setChecked(true);
                    ck_6_no.setChecked(false);
                    ckItem6 = "1";
                }
                break;
            case R.id.ck_7_yes:
                if (isCheck) {
                    ck_7_yes.setChecked(true);
                    ck_7_no.setChecked(false);
                    ckItem7 = "1";
                }
                break;
            case R.id.ck_8_yes:
                if (isCheck) {
                    ck_8_yes.setChecked(true);
                    ck_8_no.setChecked(false);
                    ckItem8 = "1";
                }
                break;
            case R.id.ck_9_yes:
                if (isCheck) {
                    ck_9_yes.setChecked(true);
                    ck_9_no.setChecked(false);
                    ckItem9 = "1";
                }
                break;
            case R.id.ck_10_yes:
                if (isCheck) {
                    ck_10_yes.setChecked(true);
                    ck_10_no.setChecked(false);
                    ckItem10 = "1";
                }
                break;
            case R.id.ck_11_yes:
                if (isCheck) {
                    ck_11_yes.setChecked(true);
                    ck_11_no.setChecked(false);
                    ckItem11 = "1";
                }
                break;
            case R.id.ck_12_yes:
                if (isCheck) {
                    ck_12_yes.setChecked(true);
                    ck_12_no.setChecked(false);
                    ckItem12 = "1";
                }
                break;
            case R.id.ck_13_yes:
                if (isCheck) {
                    ck_13_yes.setChecked(true);
                    ck_13_no.setChecked(false);
                    ckItem13 = "1";
                }
                break;
            case R.id.ck_14_yes:
                if (isCheck) {
                    ck_14_yes.setChecked(true);
                    ck_14_no.setChecked(false);
                    ckItem14 = "1";
                }
                break;
            case R.id.ck_15_yes:
                if (isCheck) {
                    ck_15_yes.setChecked(true);
                    ck_15_no.setChecked(false);
                    ckItem15 = "1";
                }
                break;
            case R.id.ck_16_yes:
                if (isCheck) {
                    ck_16_yes.setChecked(true);
                    ck_16_no.setChecked(false);
                    ckItem16 = "1";
                }
                break;
            case R.id.ck_17_yes:
                if (isCheck) {
                    ck_17_yes.setChecked(true);
                    ck_17_no.setChecked(false);
                    ckItem17 = "1";
                }
                break;
            case R.id.ck_18_yes:
                if (isCheck) {
                    ck_18_yes.setChecked(true);
                    ck_18_no.setChecked(false);
                    ckItem18 = "1";
                }
                break;
            case R.id.ck_19_yes:
                if (isCheck) {
                    ck_19_yes.setChecked(true);
                    ck_19_no.setChecked(false);
                    ckItem19 = "1";
                }
                break;


            case R.id.ck_1_no:
                if (isCheck) {
                    ck_1_no.setChecked(true);
                    ck_1_yes.setChecked(false);
                    ckItem1 = "0";
                }
                break;
            case R.id.ck_2_no:
                if (isCheck) {
                    ck_2_no.setChecked(true);
                    ck_2_yes.setChecked(false);
                    ckItem2 = "0";
                }
                data.setItemCase2(ckItem2);
                break;
            case R.id.ck_3_no:
                if (isCheck) {
                    ck_3_no.setChecked(true);
                    ck_3_yes.setChecked(false);
                    ckItem3 = "0";
                }
                break;
            case R.id.ck_4_no:
                if (isCheck) {
                    ck_4_no.setChecked(true);
                    ck_4_yes.setChecked(false);
                    ckItem4 = "0";
                }
                break;
            case R.id.ck_5_no:
                if (isCheck) {
                    ck_5_no.setChecked(true);
                    ck_5_yes.setChecked(false);
                    ckItem5 = "0";
                }
                break;
            case R.id.ck_6_no:
                if (isCheck) {
                    ck_6_no.setChecked(true);
                    ck_6_yes.setChecked(false);
                    ckItem6 = "0";
                }
                break;
            case R.id.ck_7_no:
                if (isCheck) {
                    ck_7_no.setChecked(true);
                    ck_7_yes.setChecked(false);
                    ckItem7 = "0";
                }
                break;
            case R.id.ck_8_no:
                if (isCheck) {
                    ck_8_no.setChecked(true);
                    ck_8_yes.setChecked(false);
                    ckItem8 = "0";
                }
                break;
            case R.id.ck_9_no:
                if (isCheck) {
                    ck_9_no.setChecked(true);
                    ck_9_yes.setChecked(false);
                    ckItem9 = "0";
                }
                break;
            case R.id.ck_10_no:
                if (isCheck) {
                    ck_10_no.setChecked(true);
                    ck_10_yes.setChecked(false);
                    ckItem10 = "0";
                }
                break;
            case R.id.ck_11_no:
                if (isCheck) {
                    ck_11_no.setChecked(true);
                    ck_11_yes.setChecked(false);
                    ckItem11 = "0";
                }
                break;
            case R.id.ck_12_no:
                if (isCheck) {
                    ck_12_no.setChecked(true);
                    ck_12_yes.setChecked(false);
                    ckItem12 = "0";
                }
                break;
            case R.id.ck_13_no:
                if (isCheck) {
                    ck_13_no.setChecked(true);
                    ck_13_yes.setChecked(false);
                    ckItem13 = "0";
                }
                break;
            case R.id.ck_14_no:
                if (isCheck) {
                    ck_14_no.setChecked(true);
                    ck_14_yes.setChecked(false);
                    ckItem14 = "0";
                }
                break;
            case R.id.ck_15_no:
                if (isCheck) {
                    ck_15_no.setChecked(true);
                    ck_15_yes.setChecked(false);
                    ckItem15 = "0";
                }
                break;
            case R.id.ck_16_no:
                if (isCheck) {
                    ck_16_no.setChecked(true);
                    ck_16_yes.setChecked(false);
                    ckItem16 = "0";
                }
            case R.id.ck_17_no:
                if (isCheck) {
                    ck_17_no.setChecked(true);
                    ck_17_yes.setChecked(false);
                    ckItem17 = "0";
                }
                break;
            case R.id.ck_18_no:
                if (isCheck) {
                    ck_18_no.setChecked(true);
                    ck_18_yes.setChecked(false);
                    ckItem18 = "0";
                }
                break;
            case R.id.ck_19_no:
                if (isCheck) {
                    ck_19_no.setChecked(true);
                    ck_19_yes.setChecked(false);
                    ckItem19 = "0";
                }
                break;

            case R.id.ck_correct:
                if (isCheck) {
                    ck_correct.setChecked(true);
                    ck_defects_exist.setChecked(false);
                    ck_false.setChecked(false);
                    ck_other.setChecked(false);
                    ckOpinion = "1";
                    et_other.setText("");
                }
                break;
            case R.id.ck_defects_exist:
                if (isCheck) {
                    ck_correct.setChecked(false);
                    ck_defects_exist.setChecked(true);
                    ck_false.setChecked(false);
                    ck_other.setChecked(false);
                    ckOpinion = "2";
                    et_other.setText("");
                    et_other.setEnabled(false);
                }
                break;
            case R.id.ck_false:
                if (isCheck) {
                    ck_correct.setChecked(false);
                    ck_defects_exist.setChecked(false);
                    ck_false.setChecked(true);
                    ck_other.setChecked(false);
                    ckOpinion = "3";
                    et_other.setText("");
                    et_other.setEnabled(false);
                }
                break;
            case R.id.ck_other:
                if (isCheck) {
                    ck_correct.setChecked(false);
                    ck_defects_exist.setChecked(false);
                    ck_false.setChecked(false);
                    ck_other.setChecked(true);
                    ckOpinion = "4";
                    et_other.setEnabled(true);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
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
            case R.id.et_autograph:
                writeLine();
                break;
            case R.id.img_autograph:
                showQMDialog(qm_path, 101);
                break;
            case R.id.btn_save:
//                if ("".equals(et_packing_company.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将装箱单位填写完整");
//                } else if ("".equals(et_packing_inspector.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将装箱检查员填写完整");
//                } else if ("".equals(et_packing_tel.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将联系电话填写完整");
//                } else if ("".equals(et_carrier_ship.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将承运船舶填写完整");
//                } else if ("".equals(et_carrier_people.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将承运人/代理人填写完整");
//                } else if ("".equals(et_voyage_number.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将航次填写完整");
//                } else if ("".equals(et_consignment_people.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将托运人/代理人填写完整");
//                } else if ("".equals(et_order_num.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将提单号填写完整");
//                } else if ("".equals(et_box_num.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将箱号填写完整");
//                } else if ("".equals(ed_open_box_book_num.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将开箱检查通知书编号填写完整");
//                } else if ("".equals(et_inspect_before_num.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将检查前铅封号填写完整");
//                } else if ("".equals(et_inspect_after_num.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将检查后铅封号填写完整");
//                } else if ("".equals(et_goods_name.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将货物名称填写完整");
//                } else if ("".equals(et_un_num.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将联合国编号填写完整");
//                } else if ("".equals(et_packing_form.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将包装形式填写完整");
//                } else if ("".equals(et_goods_type.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将类别填写完整");
//                } else if ("".equals(et_goods_danger.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将副危险性填写完整");
//                } else if ("".equals(et_goods_contaminants.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将海洋污染物填写完整");
//                } else if ("".equals(et_exceptions_record.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将异常情况填写完整");
//                } else if ("".equals(et_process_results.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将处理结果填写完整");
//                } else if ("".equals(et_zhifa_people1.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将执法证号码填写完整");
//                } else if ("".equals(et_zhifa_cardNum1.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将签名填写完整");
//                } else {
                if (ck_other.isChecked() && "".equals(et_other.getText().toString().trim())) {
                    ToastUtils.showToast(this, "请将勾选的其它内容填写完整");
                } else if (qm_path != null) {
                    presenter.subData(qm_path);
                } else {
                    ToastUtils.showToast(this, "请签名！");
                }
                break;
        }
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    private String sub_msg;
    private String id;

    @Override
    public void OnSuccess(String msg, String resultData) {
        this.sub_msg = msg;
        this.id = resultData;
        this.namepath = "";
        myHandler.sendEmptyMessage(500);
    }

    @Override
    public void OnFaile(String msg) {
        this.sub_msg = msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public DangerousGoodsKaiXiangData getData() {
        data.setInspectTime(et_ship_inspectTime.getText().toString().trim());
        data.setDeepSea(et_ship_deeSea.getText().toString().trim());
        data.setDeepSeaTime(et_ship_time.getText().toString().trim());
        data.setDeepSeaNum(et_ship_num.getText().toString().toString().trim());
        data.setPackOrg(et_packing_company.getText().toString().trim());
        data.setPackInspector(et_packing_inspector.getText().toString().trim());
        data.setTelephone(et_packing_tel.getText().toString().trim());
        data.setCarrierShip(et_carrier_ship.getText().toString().trim());
        data.setCarrierAgent(et_carrier_people.getText().toString().trim());
        data.setVoyageId(et_voyage_number.getText().toString().trim());
        data.setConsignAgent(et_consignment_people.getText().toString().trim());
        data.setBillNumber(et_order_num.getText().toString().trim());
        data.setCaseNumber(et_box_num.getText().toString().trim());
        data.setOutInspectNotice(ed_open_box_book_num.getText().toString().trim());
        data.setBeforeInspectNum(et_inspect_before_num.getText().toString().trim());
        data.setAfterInspectNum(et_inspect_after_num.getText().toString().trim());
        data.setGoodName(et_goods_name.getText().toString().trim());
        data.setUnNum(et_un_num.getText().toString().trim());
        data.setPackType(et_packing_form.getText().toString().trim());
        data.setTypeName(et_goods_type.getText().toString().trim());
        data.setDanger(et_goods_danger.getText().toString().trim());
        data.setPollute(et_goods_contaminants.getText().toString().trim());
        data.setExceptionDescribe(et_exceptions_record.getText().toString().trim());
        data.setHandleResult(et_process_results.getText().toString().trim());
        data.setInspector(et_zhifa_people1.getText().toString().trim());
        data.setInspectorCode(et_zhifa_cardNum1.getText().toString().trim());
//        data.setConsignAgentName(et_autograph.getText().toString().trim());
        data.setRemark(et_other.getText().toString().trim());
        data.setItemCase1(ckItem1);
        data.setItemCase2(ckItem2);
        data.setItemCase31(ckItem3);
        data.setItemCase32(ckItem4);
        data.setItemCase33(ckItem5);
        data.setItemCase34(ckItem6);
        data.setItemCase41(ckItem7);
        data.setItemCase42(ckItem8);
        data.setItemDanger1(ckItem9);
        data.setItemDanger2(ckItem10);
        data.setItemDanger3(ckItem11);
        data.setItemDanger4(ckItem12);
        data.setItemPack1(ckItem13);
        data.setItemPack2(ckItem14);
        data.setItemPack3(ckItem15);
        data.setItemPack4(ckItem16);
        data.setItemPack5(ckItem17);
        data.setItemPack6(ckItem18);
        data.setItemPack7(ckItem19);
        data.setItemHandle(ckOpinion);
//        data.setWidth(bm.getWidth());
//        data.setHeight(bm.getHeight());
        return data;
    }

    @Override
    public void setDetailData(String msg, DangerousGoodsKaiXiangData detailData) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }


    private static class MyHandler extends Handler {

        WeakReference<DangerousGoodsKaiXiangActivity> dangerousGoodsKaiXiangActivityWeakReference;

        MyHandler(DangerousGoodsKaiXiangActivity dangerousGoodsKaiXiangActivity) {
            this.dangerousGoodsKaiXiangActivityWeakReference = new WeakReference<>(dangerousGoodsKaiXiangActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final DangerousGoodsKaiXiangActivity supervisionActivity = dangerousGoodsKaiXiangActivityWeakReference.get();
            int what = msg.what;
            if (what == 500) {
                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg + "是否打印检查表？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        String name = "集装箱危险货物开箱检查记录表" + "_" + supervisionActivity.id + ".pdf";
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
                ToastUtils.showToast(supervisionActivity, supervisionActivity.sub_msg);
//                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg + "是否重新提交？", "重新提交", "返回修改", new DialogUtils.DialogOnClickListenner() {
//
//                    @Override
//                    public void btnConfirmClick(Dialog dialog) {
//                        dialog.dismiss();
//                        supervisionActivity.presenter.subData(qm_path);
//                    }
//
//                    @Override
//                    public void btnCancelClick(Dialog dialog) {
//                        dialog.dismiss();
//                    }
//
//                });
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
        String name = "集装箱危险货物开箱检查记录表" + "_" + id + ".pdf";
        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }

        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteOutRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params
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
//                taskDataPresenter.finishTask(userId,taskId,token);
                downloadDialog.dismiss();
                Intent intent = new Intent(DangerousGoodsKaiXiangActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2", namepath);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                downloadDialog.dismiss();
                super.onError(e);
                ToastUtils.showToast(SzRspApplication.getContext(), "下载失败！");
                File file = new File(namepath);
                if (file.isFile()) {
                    file.delete();
                }
//                deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }


    /**
     * 签名
     */
    private void writeLine() {
        ScrollView ll_sc = findViewById(R.id.ll_sc);
        SignatureView view1 = new SignatureView(this, ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                qm_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate() + "qm.png";
                try {
                    linePathView.save(qm_path, false, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BitmapUtils.getImageThumbnail(qm_path, 80, 80);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                bm = BitmapFactory.decodeFile(qm_path, options);
                img_autograph.setImageBitmap(bm);
                et_autograph.setVisibility(View.GONE);
                img_autograph.setVisibility(View.VISIBLE);
            }
        });
    }

    private int width;
    // 距离两边的像素
    private int magin;
    private View qmView;// 签名所承载的view
    private Dialog qm_dialog;
    private ImageView dialog_record_pen_iv;
    private Button dialog_record_ok;
    private Button dialog_record_reautograph;

    @SuppressLint("InflateParams")
    private void showQMDialog(String path, final int result) {

        width = DeviceUtils.getScreenSize(this)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(this, 10);

        qm_dialog = new AlertDialog.Builder(this).create();
        qm_dialog.setCancelable(true);
        qm_dialog.setCanceledOnTouchOutside(true);
        qmView = LayoutInflater.from(this).inflate(R.layout.dialog_qianming, null);
        qm_dialog.show();
        qm_dialog.setContentView(qmView);
        Window window = qm_dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        assert window != null;
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        dialog_record_pen_iv = (ImageView) qmView.findViewById(R.id.dialog_record_pen_iv);
        Uri uri = Uri.fromFile(new File(path));
        dialog_record_pen_iv.setImageURI(uri);

        dialog_record_ok = (Button) qmView.findViewById(R.id.dialog_record_ok);
        dialog_record_reautograph = (Button) qmView.findViewById(R.id.dialog_record_reautograph);

        dialog_record_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
            }
        });
        dialog_record_reautograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
                writeLine();
            }
        });
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
    }
}
