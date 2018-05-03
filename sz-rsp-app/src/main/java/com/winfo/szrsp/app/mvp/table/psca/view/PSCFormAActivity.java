package com.winfo.szrsp.app.mvp.table.psca.view;

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
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.psca.presenter.PSCFormAPresenter;
import com.winfo.szrsp.app.mvp.table.pscb.view.PSCFormBActivity;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFrom;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromA;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObject;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2017-12-19.
 *
 */

public class PSCFormAActivity extends Activity implements View.OnClickListener,IPSCFormAActivity {
    @BindView(R.id.table_ferry_titleBar)
    View table_ferry_titleBar;

    @BindView(R.id.psc_a_ck_1_no)
    CheckBox psc_a_ck_1_no;
    @BindView(R.id.psc_a_ck_1_yes)
    CheckBox psc_a_ck_1_yes;
    @BindView(R.id.psc_a_ck_2_no)
    CheckBox psc_a_ck_2_no;
    @BindView(R.id.psc_a_ck_2_yes)
    CheckBox psc_a_ck_2_yes;
    @BindView(R.id.psc_a_ck_3_no)
    CheckBox psc_a_ck_3_no;
    @BindView(R.id.psc_a_ck_3_yes)
    CheckBox psc_a_ck_3_yes;

    @BindView(R.id.tv_qm)
    TextView tv_qm;
    @BindView(R.id.qm_img)
    ImageView qm_img;

    @BindView(R.id.tv_qm2)
    TextView tv_qm2;
    @BindView(R.id.qm_img2)
    ImageView qm_img2;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.et_name_of_reporting)
    EditText et_name_of_reporting;

    @BindView(R.id.et_name_of_ship)
    EditText et_name_of_ship;

    @BindView(R.id.et_flag_of_ship)
    EditText et_flag_of_ship;

    @BindView(R.id.et_type_of_ship)
    EditText et_type_of_ship;

    @BindView(R.id.et_a_call_sign)
    EditText et_a_call_sign;

    @BindView(R.id.et_mmsi)
    EditText et_mmsi;

    @BindView(R.id.et_imo_number)
    EditText et_imo_number;

    @BindView(R.id.et_gross_tonnage)
    EditText et_gross_tonnage;

    @BindView(R.id.et_dead_weight)
    EditText et_dead_weight;

    @BindView(R.id.et_year_keel_laid)
    EditText et_year_keel_laid;

    @BindView(R.id.et_date_of_inspection)
    TextView et_date_of_inspection;

    @BindView(R.id.et_place_of_inspection)
    EditText et_place_of_inspection;

    @BindView(R.id.et_classification_society)
    EditText et_classification_society;

    @BindView(R.id.et_date_of_release)
    TextView et_date_of_release;

    @BindView(R.id.et_imo_company_number)
    EditText et_imo_company_number;

    @BindView(R.id.et_particulars_of_company)
    EditText et_particulars_of_company;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_issuing_authority_1)
    EditText et_issuing_authority_1;
    @BindView(R.id.et_issuing_authority_2)
    EditText et_issuing_authority_2;
    @BindView(R.id.et_issuing_authority_3)
    EditText et_issuing_authority_3;
    @BindView(R.id.et_issuing_authority_4)
    EditText et_issuing_authority_4;
    @BindView(R.id.et_issuing_authority_5)
    EditText et_issuing_authority_5;
    @BindView(R.id.et_issuing_authority_6)
    EditText et_issuing_authority_6;
    @BindView(R.id.et_issuing_authority_7)
    EditText et_issuing_authority_7;
    @BindView(R.id.et_issuing_authority_8)
    EditText et_issuing_authority_8;
    @BindView(R.id.et_issuing_authority_9)
    EditText et_issuing_authority_9;
    @BindView(R.id.et_issuing_authority_10)
    EditText et_issuing_authority_10;
    @BindView(R.id.et_issuing_authority_11)
    EditText et_issuing_authority_11;
    @BindView(R.id.et_issuing_authority_12)
    EditText et_issuing_authority_12;
    @BindView(R.id.et_issuing_authority_13)
    EditText et_issuing_authority_13;
    @BindView(R.id.et_issuing_authority_14)
    EditText et_issuing_authority_14;
    @BindView(R.id.et_issuing_authority_15)
    EditText et_issuing_authority_15;
    @BindView(R.id.et_issuing_authority_16)
    EditText et_issuing_authority_16;



    @BindView(R.id.et_date_of_issue_1)
    TextView et_date_of_issue_1;
    @BindView(R.id.et_date_of_issue_2)
    TextView et_date_of_issue_2;
    @BindView(R.id.et_date_of_issue_3)
    TextView et_date_of_issue_3;
    @BindView(R.id.et_date_of_issue_4)
    TextView et_date_of_issue_4;
    @BindView(R.id.et_date_of_issue_5)
    TextView et_date_of_issue_5;
    @BindView(R.id.et_date_of_issue_6)
    TextView et_date_of_issue_6;
    @BindView(R.id.et_date_of_issue_7)
    TextView et_date_of_issue_7;
    @BindView(R.id.et_date_of_issue_8)
    TextView et_date_of_issue_8;
    @BindView(R.id.et_date_of_issue_9)
    TextView et_date_of_issue_9;
    @BindView(R.id.et_date_of_issue_10)
    TextView et_date_of_issue_10;
    @BindView(R.id.et_date_of_issue_11)
    TextView et_date_of_issue_11;
    @BindView(R.id.et_date_of_issue_12)
    TextView et_date_of_issue_12;
    @BindView(R.id.et_date_of_issue_13)
    TextView et_date_of_issue_13;
    @BindView(R.id.et_date_of_issue_14)
    TextView et_date_of_issue_14;
    @BindView(R.id.et_date_of_issue_15)
    TextView et_date_of_issue_15;
    @BindView(R.id.et_date_of_issue_16)
    TextView et_date_of_issue_16;



    @BindView(R.id.et_and_expiry_1)
    TextView et_and_expiry_1;
    @BindView(R.id.et_and_expiry_2)
    TextView et_and_expiry_2;
    @BindView(R.id.et_and_expiry_3)
    TextView et_and_expiry_3;
    @BindView(R.id.et_and_expiry_4)
    TextView et_and_expiry_4;
    @BindView(R.id.et_and_expiry_5)
    TextView et_and_expiry_5;
    @BindView(R.id.et_and_expiry_6)
    TextView et_and_expiry_6;
    @BindView(R.id.et_and_expiry_7)
    TextView et_and_expiry_7;
    @BindView(R.id.et_and_expiry_8)
    TextView et_and_expiry_8;
    @BindView(R.id.et_and_expiry_9)
    TextView et_and_expiry_9;
    @BindView(R.id.et_and_expiry_10)
    TextView et_and_expiry_10;
    @BindView(R.id.et_and_expiry_11)
    TextView et_and_expiry_11;
    @BindView(R.id.et_and_expiry_12)
    TextView et_and_expiry_12;
    @BindView(R.id.et_and_expiry_13)
    TextView et_and_expiry_13;
    @BindView(R.id.et_and_expiry_14)
    TextView et_and_expiry_14;
    @BindView(R.id.et_and_expiry_15)
    TextView et_and_expiry_15;
    @BindView(R.id.et_and_expiry_16)
    TextView et_and_expiry_16;



    @BindView(R.id.et_date_1)
    TextView et_date_1;
    @BindView(R.id.et_date_2)
    TextView et_date_2;
    @BindView(R.id.et_date_3)
    TextView et_date_3;
    @BindView(R.id.et_date_4)
    TextView et_date_4;
    @BindView(R.id.et_date_5)
    TextView et_date_5;
    @BindView(R.id.et_date_6)
    TextView et_date_6;
    @BindView(R.id.et_date_7)
    TextView et_date_7;
    @BindView(R.id.et_date_8)
    TextView et_date_8;
    @BindView(R.id.et_date_9)
    TextView et_date_9;
    @BindView(R.id.et_date_10)
    TextView et_date_10;
    @BindView(R.id.et_date_11)
    TextView et_date_11;
    @BindView(R.id.et_date_12)
    TextView et_date_12;
    @BindView(R.id.et_date_13)
    TextView et_date_13;
    @BindView(R.id.et_date_14)
    TextView et_date_14;
    @BindView(R.id.et_date_15)
    TextView et_date_15;
    @BindView(R.id.et_date_16)
    TextView et_date_16;



    @BindView(R.id.et_surveying_authority_1)
    EditText et_surveying_authority_1;
    @BindView(R.id.et_surveying_authority_2)
    EditText et_surveying_authority_2;
    @BindView(R.id.et_surveying_authority_3)
    EditText et_surveying_authority_3;
    @BindView(R.id.et_surveying_authority_4)
    EditText et_surveying_authority_4;
    @BindView(R.id.et_surveying_authority_5)
    EditText et_surveying_authority_5;
    @BindView(R.id.et_surveying_authority_6)
    EditText et_surveying_authority_6;
    @BindView(R.id.et_surveying_authority_7)
    EditText et_surveying_authority_7;
    @BindView(R.id.et_surveying_authority_8)
    EditText et_surveying_authority_8;
    @BindView(R.id.et_surveying_authority_9)
    EditText et_surveying_authority_9;
    @BindView(R.id.et_surveying_authority_10)
    EditText et_surveying_authority_10;
    @BindView(R.id.et_surveying_authority_11)
    EditText et_surveying_authority_11;
    @BindView(R.id.et_surveying_authority_12)
    EditText et_surveying_authority_12;
    @BindView(R.id.et_surveying_authority_13)
    EditText et_surveying_authority_13;
    @BindView(R.id.et_surveying_authority_14)
    EditText et_surveying_authority_14;
    @BindView(R.id.et_surveying_authority_15)
    EditText et_surveying_authority_15;
    @BindView(R.id.et_surveying_authority_16)
    EditText et_surveying_authority_16;




    @BindView(R.id.et_place_1)
    EditText et_place_1;
    @BindView(R.id.et_place_2)
    EditText et_place_2;
    @BindView(R.id.et_place_3)
    EditText et_place_3;
    @BindView(R.id.et_place_4)
    EditText et_place_4;
    @BindView(R.id.et_place_5)
    EditText et_place_5;
    @BindView(R.id.et_place_6)
    EditText et_place_6;
    @BindView(R.id.et_place_7)
    EditText et_place_7;
    @BindView(R.id.et_place_8)
    EditText et_place_8;
    @BindView(R.id.et_place_9)
    EditText et_place_9;
    @BindView(R.id.et_place_10)
    EditText et_place_10;
    @BindView(R.id.et_place_11)
    EditText et_place_11;
    @BindView(R.id.et_place_12)
    EditText et_place_12;
    @BindView(R.id.et_place_13)
    EditText et_place_13;
    @BindView(R.id.et_place_14)
    EditText et_place_14;
    @BindView(R.id.et_place_15)
    EditText et_place_15;
    @BindView(R.id.et_place_16)
    EditText et_place_16;

    @BindView(R.id.et_issuing_office)
    EditText et_issuing_office;
    @BindView(R.id.et_name_2)
    EditText et_name_2;
    @BindView(R.id.et_telephone)
    EditText et_telephone;
    @BindView(R.id.et_telefax)
    EditText et_telefax;
    @BindView(R.id.et_Email)
    EditText et_Email;

    private CtPscFromObject ctPscFromObject;

    CtPscFrom ctpscfrom;


    private TimePickerView mTimePickerView;


    private String qm_path;
    private String qm_path2;

    private boolean isSkipToFormB=false;//是否跳转到FormB

    private PSCFormAPresenter presenter;

    private Dialog dialog;
    private Dialog downloadDialog;
    private String taskTypeId;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psc_a);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        myHandler = new MyHandler(this);
        dialog= DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");

        mTimePickerView= new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());
        try {
            taskTypeId = getIntent().getExtras().getString("taskTypeId");
        }catch (Exception e){
        }
    }


    private void initData() {
        presenter=new PSCFormAPresenter(this);
        TextView table_titleBar_titleText= table_ferry_titleBar.findViewById(R.id.table_titleBar_titleText);
        table_titleBar_titleText.setText("Execution Form");
        ctPscFromObject=new CtPscFromObject();
        ctpscfrom=new CtPscFrom();
    }
    private void initEvent() {
        table_ferry_titleBar.findViewById(R.id.table_titleBar_imgbtn_back).setOnClickListener(this);
//        setOnlyOneCheck(psc_a_ck_1_no,psc_a_ck_1_yes);

        psc_a_ck_1_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    psc_a_ck_1_yes.setChecked(false);
                    isSkipToFormB=false;
                    btn_submit.setText("提交表格");

                }
            }
        });

        psc_a_ck_1_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    psc_a_ck_1_no.setChecked(false);
                    isSkipToFormB=true;
                    btn_submit.setText("填写FormB");
                }else {
                    isSkipToFormB=false;
                    btn_submit.setText("提交表格");
                }
            }
        });



        setOnlyOneCheck(psc_a_ck_2_no,psc_a_ck_2_yes);
        setOnlyOneCheck(psc_a_ck_3_no,psc_a_ck_3_yes);

        qm_img.setOnClickListener(this);
        tv_qm.setOnClickListener(this);

        qm_img2.setOnClickListener(this);
        tv_qm2.setOnClickListener(this);

        btn_submit.setOnClickListener(this);


        et_date_of_issue_1.setOnClickListener(this);
        et_date_of_issue_2.setOnClickListener(this);
        et_date_of_issue_3.setOnClickListener(this);
        et_date_of_issue_4.setOnClickListener(this);
        et_date_of_issue_5.setOnClickListener(this);
        et_date_of_issue_6.setOnClickListener(this);
        et_date_of_issue_7.setOnClickListener(this);
        et_date_of_issue_8.setOnClickListener(this);
        et_date_of_issue_9.setOnClickListener(this);
        et_date_of_issue_10.setOnClickListener(this);
        et_date_of_issue_11.setOnClickListener(this);
        et_date_of_issue_12.setOnClickListener(this);
        et_date_of_issue_13.setOnClickListener(this);
        et_date_of_issue_14.setOnClickListener(this);
        et_date_of_issue_15.setOnClickListener(this);
        et_date_of_issue_16.setOnClickListener(this);



        et_and_expiry_1.setOnClickListener(this);
        et_and_expiry_2.setOnClickListener(this);
        et_and_expiry_3.setOnClickListener(this);
        et_and_expiry_4.setOnClickListener(this);
        et_and_expiry_5.setOnClickListener(this);
        et_and_expiry_6.setOnClickListener(this);
        et_and_expiry_7.setOnClickListener(this);
        et_and_expiry_8.setOnClickListener(this);
        et_and_expiry_9.setOnClickListener(this);
        et_and_expiry_10.setOnClickListener(this);
        et_and_expiry_11.setOnClickListener(this);
        et_and_expiry_12.setOnClickListener(this);
        et_and_expiry_13.setOnClickListener(this);
        et_and_expiry_14.setOnClickListener(this);
        et_and_expiry_15.setOnClickListener(this);
        et_and_expiry_16.setOnClickListener(this);



        et_date_1.setOnClickListener(this);
        et_date_2.setOnClickListener(this);
        et_date_3.setOnClickListener(this);
        et_date_4.setOnClickListener(this);
        et_date_5.setOnClickListener(this);
        et_date_6.setOnClickListener(this);
        et_date_7.setOnClickListener(this);
        et_date_8.setOnClickListener(this);
        et_date_9.setOnClickListener(this);
        et_date_10.setOnClickListener(this);
        et_date_11.setOnClickListener(this);
        et_date_12.setOnClickListener(this);
        et_date_13.setOnClickListener(this);
        et_date_14.setOnClickListener(this);
        et_date_15.setOnClickListener(this);
        et_date_16.setOnClickListener(this);


        et_date_of_inspection.setOnClickListener(this);
        et_date_of_release.setOnClickListener(this);


    }


    private  void  showTimePickView(final TextView textView){
        String trim = textView.getText().toString().trim();
        if(trim.length()<1){
            mTimePickerView.setTime(new Date());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date parse = null;
            try {
                parse = format.parse(trim);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mTimePickerView.setTime(parse);
        }


        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(date);
                textView.setText(time);

            }
        });
        mTimePickerView.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.tv_qm:
                writeLine(101,qm_img,tv_qm);
                break;
            case R.id.qm_img:
                showQMDialog(qm_path, 101);
                break;
            case R.id.tv_qm2:
                writeLine(201,qm_img2,tv_qm2);
                break;
            case R.id.qm_img2:
                showQMDialog(qm_path2, 201);
                break;

            case R.id.et_date_of_issue_1:
                showTimePickView(et_date_of_issue_1);
                break;
            case R.id.et_date_of_issue_2:
                showTimePickView(et_date_of_issue_2);
                break;
            case R.id.et_date_of_issue_3:
                showTimePickView(et_date_of_issue_3);
                break;
            case R.id.et_date_of_issue_4:
                showTimePickView(et_date_of_issue_4);
                break;
            case R.id.et_date_of_issue_5:
                showTimePickView(et_date_of_issue_5);
                break;
            case R.id.et_date_of_issue_6:
                showTimePickView(et_date_of_issue_6);
                break;
            case R.id.et_date_of_issue_7:
                showTimePickView(et_date_of_issue_7);
                break;
            case R.id.et_date_of_issue_8:
                showTimePickView(et_date_of_issue_8);
                break;
            case R.id.et_date_of_issue_9:
                showTimePickView(et_date_of_issue_9);
                break;
            case R.id.et_date_of_issue_10:
                showTimePickView(et_date_of_issue_10);
                break;
            case R.id.et_date_of_issue_11:
                showTimePickView(et_date_of_issue_11);
                break;
            case R.id.et_date_of_issue_12:
                showTimePickView(et_date_of_issue_12);
                break;
            case R.id.et_date_of_issue_13:
                showTimePickView(et_date_of_issue_13);
                break;
            case R.id.et_date_of_issue_14:
                showTimePickView(et_date_of_issue_14);
                break;
            case R.id.et_date_of_issue_15:
                showTimePickView(et_date_of_issue_15);
                break;
            case R.id.et_date_of_issue_16:
                showTimePickView(et_date_of_issue_16);
                break;


            case R.id.et_and_expiry_1:
                showTimePickView(et_and_expiry_1);
                break;
            case R.id.et_and_expiry_2:
                showTimePickView(et_and_expiry_2);
                break;
            case R.id.et_and_expiry_3:
                showTimePickView(et_and_expiry_3);
                break;
            case R.id.et_and_expiry_4:
                showTimePickView(et_and_expiry_4);
                break;
            case R.id.et_and_expiry_5:
                showTimePickView(et_and_expiry_5);
                break;
            case R.id.et_and_expiry_6:
                showTimePickView(et_and_expiry_6);
                break;
            case R.id.et_and_expiry_7:
                showTimePickView(et_and_expiry_7);
                break;
            case R.id.et_and_expiry_8:
                showTimePickView(et_and_expiry_8);
                break;
            case R.id.et_and_expiry_9:
                showTimePickView(et_and_expiry_9);
                break;
            case R.id.et_and_expiry_10:
                showTimePickView(et_and_expiry_10);
                break;
            case R.id.et_and_expiry_11:
                showTimePickView(et_and_expiry_11);
                break;
            case R.id.et_and_expiry_12:
                showTimePickView(et_and_expiry_12);
                break;
            case R.id.et_and_expiry_13:
                showTimePickView(et_and_expiry_13);
                break;
            case R.id.et_and_expiry_14:
                showTimePickView(et_and_expiry_14);
                break;
            case R.id.et_and_expiry_15:
                showTimePickView(et_and_expiry_15);
                break;
            case R.id.et_and_expiry_16:
                showTimePickView(et_and_expiry_16);
                break;


            case R.id.et_date_1:
                showTimePickView(et_date_1);
                break;
            case R.id.et_date_2:
                showTimePickView(et_date_2);
                break;
            case R.id.et_date_3:
                showTimePickView(et_date_3);
                break;
            case R.id.et_date_4:
                showTimePickView(et_date_4);
                break;
            case R.id.et_date_5:
                showTimePickView(et_date_5);
                break;
            case R.id.et_date_6:
                showTimePickView(et_date_6);
                break;
            case R.id.et_date_7:
                showTimePickView(et_date_7);
                break;
            case R.id.et_date_8:
                showTimePickView(et_date_8);
                break;
            case R.id.et_date_9:
                showTimePickView(et_date_9);
                break;
            case R.id.et_date_10:
                showTimePickView(et_date_10);
                break;
            case R.id.et_date_11:
                showTimePickView(et_date_11);
                break;
            case R.id.et_date_12:
                showTimePickView(et_date_12);
                break;
            case R.id.et_date_13:
                showTimePickView(et_date_13);
                break;
            case R.id.et_date_14:
                showTimePickView(et_date_14);
                break;
            case R.id.et_date_15:
                showTimePickView(et_date_15);
                break;
            case R.id.et_date_16:
                showTimePickView(et_date_16);
                break;

            case R.id.et_date_of_inspection:
                showTimePickView(et_date_of_inspection);
                break;

            case R.id.et_date_of_release:
                showTimePickView(et_date_of_release);
                break;


            case R.id.btn_submit:

                if ("".equals(et_name_of_reporting.getText().toString().trim())
                        ||"".equals(et_name_of_ship.getText().toString().trim())
                        ||"".equals(et_flag_of_ship.getText().toString().trim())
                        ||"".equals(et_type_of_ship.getText().toString().trim())
                        ||"".equals(et_a_call_sign.getText().toString().trim())
                        ||"".equals(et_mmsi.getText().toString().trim())
                        ||"".equals(et_imo_number.getText().toString().trim())
                        ||"".equals(et_gross_tonnage.getText().toString().trim())
                        ||"".equals(et_dead_weight.getText().toString().trim())
                        ||"".equals(et_year_keel_laid.getText().toString().trim())
                        ||"".equals(et_date_of_inspection.getText().toString().trim())
                        ||"".equals(et_place_of_inspection.getText().toString().trim())
                        ||"".equals(et_classification_society.getText().toString().trim())
                        ||"".equals(et_date_of_release.getText().toString().trim())
                        ||"".equals(et_imo_company_number.getText().toString().trim())
                        ||"".equals(et_particulars_of_company.getText().toString().trim())
                        ||"".equals(et_name.getText().toString().trim())
                        ||"".equals(et_issuing_authority_1.getText().toString().trim())
                        ||"".equals(et_issuing_authority_2.getText().toString().trim())
                        ||"".equals(et_issuing_authority_3.getText().toString().trim())
                        ||"".equals(et_issuing_authority_4.getText().toString().trim())
                        ||"".equals(et_issuing_authority_5.getText().toString().trim())
                        ||"".equals(et_issuing_authority_6.getText().toString().trim())
                        ||"".equals(et_issuing_authority_7.getText().toString().trim())
                        ||"".equals(et_issuing_authority_8.getText().toString().trim())
                        ||"".equals(et_issuing_authority_9.getText().toString().trim())
                        ||"".equals(et_issuing_authority_10.getText().toString().trim())
                        ||"".equals(et_issuing_authority_11.getText().toString().trim())
                        ||"".equals(et_issuing_authority_12.getText().toString().trim())
                        ||"".equals(et_issuing_authority_13.getText().toString().trim())
                        ||"".equals(et_issuing_authority_14.getText().toString().trim())
                        ||"".equals(et_issuing_authority_15.getText().toString().trim())
                        ||"".equals(et_issuing_authority_16.getText().toString().trim())
                        ||!psc_a_ck_1_no.isChecked()&&!psc_a_ck_1_yes.isChecked()
                        ||!psc_a_ck_2_no.isChecked()&&!psc_a_ck_2_yes.isChecked()
                        ||!psc_a_ck_3_no.isChecked()&&!psc_a_ck_3_yes.isChecked()
                        ||"".equals(et_issuing_office.getText().toString().trim())
                        ||"".equals(et_name_2.getText().toString().trim())
                        ||"".equals(et_telephone.getText().toString().trim())
                        ||"".equals(et_telefax.getText().toString().trim())
                        ||"".equals(et_Email.getText().toString().trim())){
                    ToastUtils.showToast(this,"请将表格填写完整");
                    return;
                }
                if (qm_path==null||qm_path2==null){
                    ToastUtils.showToast(this,"请点击签名");
                    return;
                }


                if (isSkipToFormB){
                    Intent intent=new Intent(this, PSCFormBActivity.class);
                    intent.putExtra("CtPscFromObject",getCtPscFromObject());
                    intent.putExtra("qm_path",qm_path);
                    intent.putExtra("qm_path2",qm_path2);
                    startActivityForResult(intent,100);
                }else {
                    //17  如果只提交表A，则检查是否有存储过B表内容，有存储则删除，否则导致提交失败
                    if (psc_a_ck_1_no.isChecked()&&ctPscFromObject.getDetailb()!=null){//0：no 1:yes
                        ctPscFromObject.getDetailb().clear();
                    }
                    //提交表格
                    presenter.subPSCFormAData(qm_path,qm_path2);

                }
                break;

        }
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
                if (result == 101){
                    writeLine(101,qm_img,tv_qm);
                }else {
                    writeLine(201,qm_img2,tv_qm2);
                }
                qm_dialog.dismiss();
            }
        });
    }
    /**
     * 签名
     */
    private void writeLine(final int result, final ImageView imageView, final TextView textView){
        ScrollView ll_sc = findViewById(R.id.ll_sc);
        SignatureView view1 = new SignatureView(this,ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                if (result == 101){
                    qm_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate()+"qm.png";
                    try {
                        linePathView.save(qm_path,false,10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BitmapUtils.getImageThumbnail(qm_path, 80, 80);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(qm_path, options);
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    ctpscfrom.setCaptainWidth(new BigDecimal(bm.getWidth()));
                    ctpscfrom.setCaptainHeight(new BigDecimal(bm.getHeight()));
                }else{
                    qm_path2 = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate()+"qm.png";
                    try {
                        linePathView.save(qm_path2,false,10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BitmapUtils.getImageThumbnail(qm_path2, 80, 80);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(qm_path2, options);
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    ctpscfrom.setOrgWidth(new BigDecimal(bm.getWidth()));
                    ctpscfrom.setOrgHeight(new BigDecimal(bm.getHeight()));
                }
            }
        });
    }


    /**
     * 设置两个个checkbox只能单选
     * @param check1
     * @param check2
     */
    private void setOnlyOneCheck(final CheckBox check1, final CheckBox check2) {
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check2.setChecked(false);
                }

            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check1.setChecked(false);
                }

            }
        });

    }

    @Override
    public CtPscFromObject getCtPscFromObject() {
        //以下数字标识对应界数字面标识

        if (taskTypeId!=null){
            ctpscfrom.setTaskId(taskTypeId);
        }
        ctpscfrom.setInspectDate(et_date_of_inspection.getText().toString().trim());//10
        ctpscfrom.setAuthorityName(et_name_of_reporting.getText().toString().trim());//1
        ctpscfrom.setShipNameEn(et_name_of_ship.getText().toString().trim());//2
        ctpscfrom.setShipFlag(et_flag_of_ship.getText().toString().trim());//3
        ctpscfrom.setShipTypeNameEn(et_type_of_ship.getText().toString().trim());//4
        ctpscfrom.setSignCall(et_a_call_sign.getText().toString().trim());//5a
        ctpscfrom.setMmsi(et_mmsi.getText().toString().trim());//5b
        ctpscfrom.setShipImo(et_imo_number.getText().toString().trim());//6
        ctpscfrom.setShipGrosston(new BigDecimal(et_gross_tonnage.getText().toString().trim()));//7
        ctpscfrom.setShipDwt(new BigDecimal(et_dead_weight.getText().toString().trim()));//8
        ctpscfrom.setKeelYear(new BigDecimal(et_year_keel_laid.getText().toString().trim()));//9
        ctpscfrom.setBerthCode(et_place_of_inspection.getText().toString().trim());//11
        ctpscfrom.setSociety(et_classification_society.getText().toString().trim());//12
        ctpscfrom.setReleaseTime(et_date_of_release.getText().toString().trim());//13
        ctpscfrom.setCompanyImo(et_imo_company_number.getText().toString().trim());//14a
        ctpscfrom.setCompanyPariculars(et_particulars_of_company.getText().toString().trim());//14b
        ctpscfrom.setCaptainName(et_name.getText().toString().trim());//15

        //17
        if (psc_a_ck_1_no.isChecked()){//0：no 1:yes
            ctpscfrom.setIsDefect("0");
        }else {
            ctpscfrom.setIsDefect("1");
        }

        //18
        if (psc_a_ck_2_no.isChecked()){//0：no 1:yes
            ctpscfrom.setDetentionMark("0");
        }else {
            ctpscfrom.setDetentionMark("1");
        }

        //19
        if (psc_a_ck_3_no.isChecked()){//0：no 1:yes
            ctpscfrom.setIsDocumentat("0");
        }else {
            ctpscfrom.setIsDocumentat("1");
        }
        ctpscfrom.setIssuingOffice(et_issuing_office.getText().toString());
        ctpscfrom.setIssuingName(et_name_2.getText().toString().trim());
        ctpscfrom.setOrgTelephone(et_telephone.getText().toString().trim());
        ctpscfrom.setOrgTelefax(et_telefax.getText().toString().trim());
        ctpscfrom.setOrgMail(et_Email.getText().toString().trim());


        //1
        List<CtPscFromA>ctPscFromAs=new ArrayList<>();
        CtPscFromA ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01102");
        ctPscFromA.setTitleDesc("Cargo Ship Safety Construction Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_1.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_1.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_1.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_1.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_1.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_1.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //2
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01101");
        ctPscFromA.setTitleDesc("Cargo Ship Safety Equipment Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_2.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_2.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_2.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_2.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_2.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_2.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //3
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01104");
        ctPscFromA.setTitleDesc("Cargo Ship Safety Radio Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_3.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_3.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_3.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_3.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_3.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_3.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //4
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01117");
        ctPscFromA.setTitleDesc("IOPP Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_4.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_4.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_4.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_4.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_4.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_4.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);


        //5
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01124");
        ctPscFromA.setTitleDesc("IAPP Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_5.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_5.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_5.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_5.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_5.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_5.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);


        //6
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01119");
        ctPscFromA.setTitleDesc("ISPP Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_6.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_6.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_6.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_6.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_6.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_6.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //7
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01108");
        ctPscFromA.setTitleDesc("Load line Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_7.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_7.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_7.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_7.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_7.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_7.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //8
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01106");
        ctPscFromA.setTitleDesc("Document of Compliance");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_8.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_8.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_8.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_8.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_8.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_8.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //9
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01107");
        ctPscFromA.setTitleDesc("Safety Management Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_9.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_9.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_9.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_9.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_9.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_9.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //10
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01122");
        ctPscFromA.setTitleDesc("International Ship Security  Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_10.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_10.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_10.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_10.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_10.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_10.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //11
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01113");
        ctPscFromA.setTitleDesc("Minimum Safe Manning Document");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_11.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_11.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_11.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_11.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_11.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_11.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //12
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01132");
        ctPscFromA.setTitleDesc("Tonnage");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_12.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_12.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_12.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_12.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_12.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_12.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //13
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01131");
        ctPscFromA.setTitleDesc("International Anti-fouling-system Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_13.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_13.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_13.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_13.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_13.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_13.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //14
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01123");
        ctPscFromA.setTitleDesc("Continuous synopsis record NO.( )");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_14.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_14.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_14.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_14.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_14.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_14.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //15
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01138");
        ctPscFromA.setTitleDesc("International Energy Efficiency Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_15.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_15.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_15.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_15.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_15.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_15.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);

        //16
        ctPscFromA=new CtPscFromA();
        ctPscFromA.setTitleCode("01139");
        ctPscFromA.setTitleDesc("Maritime Labour Cert");
        ctPscFromA.setIssuingAuthority(et_issuing_authority_16.getText().toString().trim());
        ctPscFromA.setIssueTime(et_date_of_issue_16.getText().toString().trim());
        ctPscFromA.setIssuExpiry(et_and_expiry_16.getText().toString().trim());
        ctPscFromA.setSurveyDate(et_date_16.getText().toString().trim());
        ctPscFromA.setSurveyingAuthority(et_surveying_authority_16.getText().toString().trim());
        ctPscFromA.setSurveyingPlace(et_place_16.getText().toString().trim());
        ctPscFromAs.add(ctPscFromA);


        ctPscFromObject.setInfo(ctpscfrom);
        ctPscFromObject.setDetaila(ctPscFromAs);

        return ctPscFromObject;
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    private String sub_msg;
    private String id;

    @Override
    public void onSuccess(String msg, String inspectNo) {
        this.sub_msg=msg;
        this.id=inspectNo;
        this.namepath="";
        dialog.dismiss();
        myHandler.sendEmptyMessage(500);
    }


    @Override
    public void onFailure(String msg) {
        this.sub_msg=msg;
        dialog.dismiss();
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }


    private static class MyHandler extends Handler {

        WeakReference<PSCFormAActivity> pscFormAActivityWeakReference;

        MyHandler(PSCFormAActivity pscFormAActivity) {
            this.pscFormAActivityWeakReference = new WeakReference<>(pscFormAActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final PSCFormAActivity pscFormAActivity = pscFormAActivityWeakReference.get();
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(pscFormAActivity, "温馨提示", pscFormAActivity.sub_msg+",是否打印表格？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        File file = new File(pscFormAActivity.namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(pscFormAActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",pscFormAActivity.namepath);
                            pscFormAActivity.startActivity(intent);
                            pscFormAActivity.finish();
                        }else{
                            pscFormAActivity.downloadPDF();
                            pscFormAActivity.downloadDialog.show();
                            pscFormAActivity.httpDownManager.startDown(pscFormAActivity.apkApi);
                        }
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        pscFormAActivity.finish();
                    }
                });
            }

            if(what == 600){
                DialogUtils.showDialog(pscFormAActivity, "温馨提示", pscFormAActivity.sub_msg+",是否重新提交？", "重新提交","返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        pscFormAActivity.presenter.subPSCFormAData(pscFormAActivity.qm_path,pscFormAActivity.qm_path2);
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
    private String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    private String namepath = "";
    private DownInfo apkApi;

    private void downloadPDF(){

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name =  "PSCForm"+"_"+id+".pdf";

        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctPscFromRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
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
                Intent intent=new Intent(PSCFormAActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2",namepath);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                downloadDialog.dismiss();
                super.onError(e);
                File file = new File(namepath);
                if (file.isFile()) {
                    file.delete();
                }
            }

            @Override
            public void updateProgress(long readLength, long countLength) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case 100:
                if (resultCode==300){
                    if (intent!=null){
                        ctPscFromObject = (CtPscFromObject) intent.getSerializableExtra("CtPscFromObject");
                    }
                }
                break;
        }




    }
}
