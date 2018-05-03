package com.winfo.szrsp.app.mvp.table.dxshcjcb.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.presenter.DXSHCJCBPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2017-12-04.
 */

public class DXSHCJCBActivity extends Activity implements View.OnClickListener, IDXSHCJCBView {

    @BindView(R.id.table_ferry_titleBar)
    View table_ferry_titleBar;
    @BindView(R.id.dxchcjcb_tv_time)
    TextView dxchcjcb_tv_time;
    @BindView(R.id.dxshcjcb_et_cm)
    EditText dxshcjcb_et_cm;
    @BindView(R.id.dxshcjcb_et_imo)
    EditText dxshcjcb_et_imo;
    @BindView(R.id.dxshcjcb_et_gk)
    EditText dxshcjcb_et_gk;
    @BindView(R.id.dxshcjcb_sub)
    Button dxshcjcb_sub;

    @BindView(R.id.dxchcjcb_check1_1_1)
    CheckBox dxchcjcb_check1_1_1;
    @BindView(R.id.dxchcjcb_check1_1_2)
    CheckBox dxchcjcb_check1_1_2;
    @BindView(R.id.dxchcjcb_check1_1_3)
    CheckBox dxchcjcb_check1_1_3;
    @BindView(R.id.dxchcjcb_check1_2_1)
    CheckBox dxchcjcb_check1_2_1;
    @BindView(R.id.dxchcjcb_check1_2_2)
    CheckBox dxchcjcb_check1_2_2;
    @BindView(R.id.dxchcjcb_check1_2_3)
    CheckBox dxchcjcb_check1_2_3;

    @BindView(R.id.dxchcjcb_check2_1_1)
    CheckBox dxchcjcb_check2_1_1;
    @BindView(R.id.dxchcjcb_check2_1_2)
    CheckBox dxchcjcb_check2_1_2;
    @BindView(R.id.dxchcjcb_check2_1_3)
    CheckBox dxchcjcb_check2_1_3;
    @BindView(R.id.dxchcjcb_check2_2_1)
    CheckBox dxchcjcb_check2_2_1;
    @BindView(R.id.dxchcjcb_check2_2_2)
    CheckBox dxchcjcb_check2_2_2;
    @BindView(R.id.dxchcjcb_check2_2_3)
    CheckBox dxchcjcb_check2_2_3;

    @BindView(R.id.dxchcjcb_check3_1_1)
    CheckBox dxchcjcb_check3_1_1;
    @BindView(R.id.dxchcjcb_check3_1_2)
    CheckBox dxchcjcb_check3_1_2;
    @BindView(R.id.dxchcjcb_check3_1_3)
    CheckBox dxchcjcb_check3_1_3;
    @BindView(R.id.dxchcjcb_check3_2_1)
    CheckBox dxchcjcb_check3_2_1;
    @BindView(R.id.dxchcjcb_check3_2_2)
    CheckBox dxchcjcb_check3_2_2;
    @BindView(R.id.dxchcjcb_check3_2_3)
    CheckBox dxchcjcb_check3_2_3;
    @BindView(R.id.dxchcjcb_check3_3_1)
    CheckBox dxchcjcb_check3_3_1;
    @BindView(R.id.dxchcjcb_check3_3_2)
    CheckBox dxchcjcb_check3_3_2;
    @BindView(R.id.dxchcjcb_check3_3_3)
    CheckBox dxchcjcb_check3_3_3;

    @BindView(R.id.dxchcjcb_check4_1_1)
    CheckBox dxchcjcb_check4_1_1;
    @BindView(R.id.dxchcjcb_check4_1_2)
    CheckBox dxchcjcb_check4_1_2;
    @BindView(R.id.dxchcjcb_check4_1_3)
    CheckBox dxchcjcb_check4_1_3;
    @BindView(R.id.dxchcjcb_check4_2_1)
    CheckBox dxchcjcb_check4_2_1;
    @BindView(R.id.dxchcjcb_check4_2_2)
    CheckBox dxchcjcb_check4_2_2;
    @BindView(R.id.dxchcjcb_check4_2_3)
    CheckBox dxchcjcb_check4_2_3;

    @BindView(R.id.dxchcjcb_check5_1_1)
    CheckBox dxchcjcb_check5_1_1;
    @BindView(R.id.dxchcjcb_check5_1_2)
    CheckBox dxchcjcb_check5_1_2;
    @BindView(R.id.dxchcjcb_check5_1_3)
    CheckBox dxchcjcb_check5_1_3;
    @BindView(R.id.dxchcjcb_check5_2_1)
    CheckBox dxchcjcb_check5_2_1;
    @BindView(R.id.dxchcjcb_check5_2_2)
    CheckBox dxchcjcb_check5_2_2;
    @BindView(R.id.dxchcjcb_check5_2_3)
    CheckBox dxchcjcb_check5_2_3;


    @BindView(R.id.dxchcjcb_check6_1_1)
    CheckBox dxchcjcb_check6_1_1;
    @BindView(R.id.dxchcjcb_check6_1_2)
    CheckBox dxchcjcb_check6_1_2;
    @BindView(R.id.dxchcjcb_check6_1_3)
    CheckBox dxchcjcb_check6_1_3;
    @BindView(R.id.dxchcjcb_check6_2_1)
    CheckBox dxchcjcb_check6_2_1;
    @BindView(R.id.dxchcjcb_check6_2_2)
    CheckBox dxchcjcb_check6_2_2;
    @BindView(R.id.dxchcjcb_check6_2_3)
    CheckBox dxchcjcb_check6_2_3;
    @BindView(R.id.dxchcjcb_check6_3_1)
    CheckBox dxchcjcb_check6_3_1;
    @BindView(R.id.dxchcjcb_check6_3_2)
    CheckBox dxchcjcb_check6_3_2;
    @BindView(R.id.dxchcjcb_check6_3_3)
    CheckBox dxchcjcb_check6_3_3;

    @BindView(R.id.dxchcjcb_check7_1_1)
    CheckBox dxchcjcb_check7_1_1;
    @BindView(R.id.dxchcjcb_check7_1_2)
    CheckBox dxchcjcb_check7_1_2;
    @BindView(R.id.dxchcjcb_check7_1_3)
    CheckBox dxchcjcb_check7_1_3;
    @BindView(R.id.dxchcjcb_check7_2_1)
    CheckBox dxchcjcb_check7_2_1;
    @BindView(R.id.dxchcjcb_check7_2_2)
    CheckBox dxchcjcb_check7_2_2;
    @BindView(R.id.dxchcjcb_check7_2_3)
    CheckBox dxchcjcb_check7_2_3;
    @BindView(R.id.dxchcjcb_check7_3_1)
    CheckBox dxchcjcb_check7_3_1;
    @BindView(R.id.dxchcjcb_check7_3_2)
    CheckBox dxchcjcb_check7_3_2;
    @BindView(R.id.dxchcjcb_check7_3_3)
    CheckBox dxchcjcb_check7_3_3;

    @BindView(R.id.dxchcjcb_check8_1_1)
    CheckBox dxchcjcb_check8_1_1;
    @BindView(R.id.dxchcjcb_check8_1_2)
    CheckBox dxchcjcb_check8_1_2;
    @BindView(R.id.dxchcjcb_check8_1_3)
    CheckBox dxchcjcb_check8_1_3;
    @BindView(R.id.dxchcjcb_check8_2_1)
    CheckBox dxchcjcb_check8_2_1;
    @BindView(R.id.dxchcjcb_check8_2_2)
    CheckBox dxchcjcb_check8_2_2;
    @BindView(R.id.dxchcjcb_check8_2_3)
    CheckBox dxchcjcb_check8_2_3;
    @BindView(R.id.dxchcjcb_check8_3_1)
    CheckBox dxchcjcb_check8_3_1;
    @BindView(R.id.dxchcjcb_check8_3_2)
    CheckBox dxchcjcb_check8_3_2;
    @BindView(R.id.dxchcjcb_check8_3_3)
    CheckBox dxchcjcb_check8_3_3;

    @BindView(R.id.dxchcjcb_check9_1_1)
    CheckBox dxchcjcb_check9_1_1;
    @BindView(R.id.dxchcjcb_check9_1_2)
    CheckBox dxchcjcb_check9_1_2;
    @BindView(R.id.dxchcjcb_check9_1_3)
    CheckBox dxchcjcb_check9_1_3;
    @BindView(R.id.dxchcjcb_check9_2_1)
    CheckBox dxchcjcb_check9_2_1;
    @BindView(R.id.dxchcjcb_check9_2_2)
    CheckBox dxchcjcb_check9_2_2;
    @BindView(R.id.dxchcjcb_check9_2_3)
    CheckBox dxchcjcb_check9_2_3;
    @BindView(R.id.dxchcjcb_check9_3_1)
    CheckBox dxchcjcb_check9_3_1;
    @BindView(R.id.dxchcjcb_check9_3_2)
    CheckBox dxchcjcb_check9_3_2;
    @BindView(R.id.dxchcjcb_check9_3_3)
    CheckBox dxchcjcb_check9_3_3;

    @BindView(R.id.dxchcjcb_check10_1_1)
    CheckBox dxchcjcb_check10_1_1;
    @BindView(R.id.dxchcjcb_check10_1_2)
    CheckBox dxchcjcb_check10_1_2;
    @BindView(R.id.dxchcjcb_check10_1_3)
    CheckBox dxchcjcb_check10_1_3;
    @BindView(R.id.dxchcjcb_check10_2_1)
    CheckBox dxchcjcb_check10_2_1;
    @BindView(R.id.dxchcjcb_check10_2_2)
    CheckBox dxchcjcb_check10_2_2;
    @BindView(R.id.dxchcjcb_check10_2_3)
    CheckBox dxchcjcb_check10_2_3;

    @BindView(R.id.dxchcjcb_check11_1_1)
    CheckBox dxchcjcb_check11_1_1;
    @BindView(R.id.dxchcjcb_check11_1_2)
    CheckBox dxchcjcb_check11_1_2;
    @BindView(R.id.dxchcjcb_check11_1_3)
    CheckBox dxchcjcb_check11_1_3;
    @BindView(R.id.dxchcjcb_check11_2_1)
    CheckBox dxchcjcb_check11_2_1;
    @BindView(R.id.dxchcjcb_check11_2_2)
    CheckBox dxchcjcb_check11_2_2;
    @BindView(R.id.dxchcjcb_check11_2_3)
    CheckBox dxchcjcb_check11_2_3;
    @BindView(R.id.dxchcjcb_check11_3_1)
    CheckBox dxchcjcb_check11_3_1;
    @BindView(R.id.dxchcjcb_check11_3_2)
    CheckBox dxchcjcb_check11_3_2;
    @BindView(R.id.dxchcjcb_check11_3_3)
    CheckBox dxchcjcb_check11_3_3;

    @BindView(R.id.dxchcjcb_check12_1_1)
    CheckBox dxchcjcb_check12_1_1;
    @BindView(R.id.dxchcjcb_check12_1_2)
    CheckBox dxchcjcb_check12_1_2;
    @BindView(R.id.dxchcjcb_check12_1_3)
    CheckBox dxchcjcb_check12_1_3;
    @BindView(R.id.dxchcjcb_check12_2_1)
    CheckBox dxchcjcb_check12_2_1;
    @BindView(R.id.dxchcjcb_check12_2_2)
    CheckBox dxchcjcb_check12_2_2;
    @BindView(R.id.dxchcjcb_check12_2_3)
    CheckBox dxchcjcb_check12_2_3;
    @BindView(R.id.dxchcjcb_check12_3_1)
    CheckBox dxchcjcb_check12_3_1;
    @BindView(R.id.dxchcjcb_check12_3_2)
    CheckBox dxchcjcb_check12_3_2;
    @BindView(R.id.dxchcjcb_check12_3_3)
    CheckBox dxchcjcb_check12_3_3;

    @BindView(R.id.dxchcjcb_check13_1_1)
    CheckBox dxchcjcb_check13_1_1;
    @BindView(R.id.dxchcjcb_check13_1_2)
    CheckBox dxchcjcb_check13_1_2;
    @BindView(R.id.dxchcjcb_check13_1_3)
    CheckBox dxchcjcb_check13_1_3;
    @BindView(R.id.dxchcjcb_check13_2_1)
    CheckBox dxchcjcb_check13_2_1;
    @BindView(R.id.dxchcjcb_check13_2_2)
    CheckBox dxchcjcb_check13_2_2;
    @BindView(R.id.dxchcjcb_check13_2_3)
    CheckBox dxchcjcb_check13_2_3;

    private TimePickerView mTimePickerView;

    private Dialog dialog;
    private Dialog downloadDialog;
    private DXSHCJCBPresenter presenter;

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dxshcjcb);
        ButterKnife.bind(this);
        myHandler = new MyHandler(this);
        initData();
        initEvent();
    }

    private void initData() {
        dialog= DialogUtils.createLoadingDialog(this, "请稍后...");
        presenter=new DXSHCJCBPresenter(this);

        TextView table_titleBar_titleText= table_ferry_titleBar.findViewById(R.id.table_titleBar_titleText);
        table_titleBar_titleText.setText("执行表格");
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        dxchcjcb_tv_time.setText(time);

        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");
        mTimePickerView= new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());
    }
    private void initEvent() {
        table_ferry_titleBar.findViewById(R.id.table_titleBar_imgbtn_back).setOnClickListener(this);
        dxchcjcb_tv_time.setOnClickListener(this);
        dxshcjcb_sub.setOnClickListener(this);

        setOnlyOneCheck(dxchcjcb_check1_1_1,dxchcjcb_check1_1_2,dxchcjcb_check1_1_3);
        setOnlyOneCheck(dxchcjcb_check1_2_1,dxchcjcb_check1_2_2,dxchcjcb_check1_2_3);

        setOnlyOneCheck(dxchcjcb_check2_1_1,dxchcjcb_check2_1_2,dxchcjcb_check2_1_3);
        setOnlyOneCheck(dxchcjcb_check2_2_1,dxchcjcb_check2_2_2,dxchcjcb_check2_2_3);

        setOnlyOneCheck(dxchcjcb_check3_1_1,dxchcjcb_check3_1_2,dxchcjcb_check3_1_3);
        setOnlyOneCheck(dxchcjcb_check3_2_1,dxchcjcb_check3_2_2,dxchcjcb_check3_2_3);
        setOnlyOneCheck(dxchcjcb_check3_3_1,dxchcjcb_check3_3_2,dxchcjcb_check3_3_3);

        setOnlyOneCheck(dxchcjcb_check4_1_1,dxchcjcb_check4_1_2,dxchcjcb_check4_1_3);
        setOnlyOneCheck(dxchcjcb_check4_2_1,dxchcjcb_check4_2_2,dxchcjcb_check4_2_3);

        setOnlyOneCheck(dxchcjcb_check5_1_1,dxchcjcb_check5_1_2,dxchcjcb_check5_1_3);
        setOnlyOneCheck(dxchcjcb_check5_2_1,dxchcjcb_check5_2_2,dxchcjcb_check5_2_3);

        setOnlyOneCheck(dxchcjcb_check6_1_1,dxchcjcb_check6_1_2,dxchcjcb_check6_1_3);
        setOnlyOneCheck(dxchcjcb_check6_2_1,dxchcjcb_check6_2_2,dxchcjcb_check6_2_3);
        setOnlyOneCheck(dxchcjcb_check6_3_1,dxchcjcb_check6_3_2,dxchcjcb_check6_3_3);

        setOnlyOneCheck(dxchcjcb_check7_1_1,dxchcjcb_check7_1_2,dxchcjcb_check7_1_3);
        setOnlyOneCheck(dxchcjcb_check7_2_1,dxchcjcb_check7_2_2,dxchcjcb_check7_2_3);
        setOnlyOneCheck(dxchcjcb_check7_3_1,dxchcjcb_check7_3_2,dxchcjcb_check7_3_3);

        setOnlyOneCheck(dxchcjcb_check8_1_1,dxchcjcb_check8_1_2,dxchcjcb_check8_1_3);
        setOnlyOneCheck(dxchcjcb_check8_2_1,dxchcjcb_check8_2_2,dxchcjcb_check8_2_3);
        setOnlyOneCheck(dxchcjcb_check8_3_1,dxchcjcb_check8_3_2,dxchcjcb_check8_3_3);

        setOnlyOneCheck(dxchcjcb_check9_1_1,dxchcjcb_check9_1_2,dxchcjcb_check9_1_3);
        setOnlyOneCheck(dxchcjcb_check9_2_1,dxchcjcb_check9_2_2,dxchcjcb_check9_2_3);
        setOnlyOneCheck(dxchcjcb_check9_3_1,dxchcjcb_check9_3_2,dxchcjcb_check9_3_3);

        setOnlyOneCheck(dxchcjcb_check10_1_1,dxchcjcb_check10_1_2,dxchcjcb_check10_1_3);
        setOnlyOneCheck(dxchcjcb_check10_2_1,dxchcjcb_check10_2_2,dxchcjcb_check10_2_3);

        setOnlyOneCheck(dxchcjcb_check11_1_1,dxchcjcb_check11_1_2,dxchcjcb_check11_1_3);
        setOnlyOneCheck(dxchcjcb_check11_2_1,dxchcjcb_check11_2_2,dxchcjcb_check11_2_3);
        setOnlyOneCheck(dxchcjcb_check11_3_1,dxchcjcb_check11_3_2,dxchcjcb_check11_3_3);

        setOnlyOneCheck(dxchcjcb_check12_1_1,dxchcjcb_check12_1_2,dxchcjcb_check12_1_3);
        setOnlyOneCheck(dxchcjcb_check12_2_1,dxchcjcb_check12_2_2,dxchcjcb_check12_2_3);
        setOnlyOneCheck(dxchcjcb_check12_3_1,dxchcjcb_check12_3_2,dxchcjcb_check12_3_3);

        setOnlyOneCheck(dxchcjcb_check13_1_1,dxchcjcb_check13_1_2,dxchcjcb_check13_1_3);
        setOnlyOneCheck(dxchcjcb_check13_2_1,dxchcjcb_check13_2_2,dxchcjcb_check13_2_3);

    }

    /**
     * 设置三个checkbox只能单选
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     * @param dxchcjcb_check3
     */
    private void setOnlyOneCheck(final CheckBox dxchcjcb_check1, final CheckBox dxchcjcb_check2,final CheckBox dxchcjcb_check3) {
        dxchcjcb_check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                }

            }
        });
        dxchcjcb_check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check1.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                }

            }
        });
        dxchcjcb_check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check1.setChecked(false);
                }

            }
        });
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
            case R.id.dxchcjcb_tv_time:
                showTimePickView(dxchcjcb_tv_time);
                break;
            case R.id.dxshcjcb_sub:


                if(dxshcjcb_et_gk.getText().toString().trim().equals("")
                        ||dxshcjcb_et_cm.getText().toString().trim().equals("")){
                    ToastUtils.showToast(DXSHCJCBActivity.this,"请将大型散货船检查表填写完整！");
                    return;
                }


                presenter.subData();
                break;

        }
    }

    @Override
    public CtSpecialShipType0203 getCtSpecialShipType0203() {
        CtSpecialShipType0203 ctSpecialShipType0203=new CtSpecialShipType0203();

        ctSpecialShipType0203.setShipNameCn(dxshcjcb_et_cm.getText().toString().trim());
        ctSpecialShipType0203.setInspectDate(dxchcjcb_tv_time.getText().toString().trim());
        ctSpecialShipType0203.setShipImo(dxshcjcb_et_imo.getText().toString().trim());
        ctSpecialShipType0203.setBerthCode(dxshcjcb_et_gk.getText().toString().trim());

        ctSpecialShipType0203.setInspectOrg("090622");


        ctSpecialShipType0203.setIpspectItem10(getCheckedId(dxchcjcb_check1_1_1,dxchcjcb_check1_1_2,dxchcjcb_check1_1_3));
        ctSpecialShipType0203.setIpspectItem11(getCheckedId(dxchcjcb_check1_2_1,dxchcjcb_check1_2_2,dxchcjcb_check1_2_3));

        ctSpecialShipType0203.setIpspectItem20(getCheckedId(dxchcjcb_check2_1_1,dxchcjcb_check2_1_2,dxchcjcb_check2_1_3));
        ctSpecialShipType0203.setIpspectItem21(getCheckedId(dxchcjcb_check2_2_1,dxchcjcb_check2_2_2,dxchcjcb_check2_2_3));

        ctSpecialShipType0203.setIpspectItem30(getCheckedId(dxchcjcb_check3_1_1,dxchcjcb_check3_1_2,dxchcjcb_check3_1_3));
        ctSpecialShipType0203.setIpspectItem31(getCheckedId(dxchcjcb_check3_2_1,dxchcjcb_check3_2_2,dxchcjcb_check3_2_3));
        ctSpecialShipType0203.setIpspectItem32(getCheckedId(dxchcjcb_check3_3_1,dxchcjcb_check3_3_2,dxchcjcb_check3_3_3));

        ctSpecialShipType0203.setIpspectItem40(getCheckedId(dxchcjcb_check4_1_1,dxchcjcb_check4_1_2,dxchcjcb_check4_1_3));
        ctSpecialShipType0203.setIpspectItem41(getCheckedId(dxchcjcb_check4_2_1,dxchcjcb_check4_2_2,dxchcjcb_check4_2_3));

        ctSpecialShipType0203.setIpspectItem50(getCheckedId(dxchcjcb_check5_1_1,dxchcjcb_check5_1_2,dxchcjcb_check5_1_3));
        ctSpecialShipType0203.setIpspectItem51(getCheckedId(dxchcjcb_check5_2_1,dxchcjcb_check5_2_2,dxchcjcb_check5_2_3));

        ctSpecialShipType0203.setIpspectItem60(getCheckedId(dxchcjcb_check6_1_1,dxchcjcb_check6_1_2,dxchcjcb_check6_1_3));
        ctSpecialShipType0203.setIpspectItem61(getCheckedId(dxchcjcb_check6_2_1,dxchcjcb_check6_2_2,dxchcjcb_check6_2_3));
        ctSpecialShipType0203.setIpspectItem62(getCheckedId(dxchcjcb_check6_3_1,dxchcjcb_check6_3_2,dxchcjcb_check6_3_3));

        ctSpecialShipType0203.setIpspectItem70(getCheckedId(dxchcjcb_check7_1_1,dxchcjcb_check7_1_2,dxchcjcb_check7_1_3));
        ctSpecialShipType0203.setIpspectItem71(getCheckedId(dxchcjcb_check7_2_1,dxchcjcb_check7_2_2,dxchcjcb_check7_2_3));
        ctSpecialShipType0203.setIpspectItem72(getCheckedId(dxchcjcb_check7_3_1,dxchcjcb_check7_3_2,dxchcjcb_check7_3_3));

        ctSpecialShipType0203.setIpspectItem80(getCheckedId(dxchcjcb_check8_1_1,dxchcjcb_check8_1_2,dxchcjcb_check8_1_3));
        ctSpecialShipType0203.setIpspectItem81(getCheckedId(dxchcjcb_check8_2_1,dxchcjcb_check8_2_2,dxchcjcb_check8_2_3));
        ctSpecialShipType0203.setIpspectItem82(getCheckedId(dxchcjcb_check8_3_1,dxchcjcb_check8_3_2,dxchcjcb_check8_3_3));

        ctSpecialShipType0203.setIpspectItem90(getCheckedId(dxchcjcb_check9_1_1,dxchcjcb_check9_1_2,dxchcjcb_check9_1_3));
        ctSpecialShipType0203.setIpspectItem91(getCheckedId(dxchcjcb_check9_2_1,dxchcjcb_check9_2_2,dxchcjcb_check9_2_3));
        ctSpecialShipType0203.setIpspectItem92(getCheckedId(dxchcjcb_check9_3_1,dxchcjcb_check9_3_2,dxchcjcb_check9_3_3));

        ctSpecialShipType0203.setIpspectItem100(getCheckedId(dxchcjcb_check10_1_1,dxchcjcb_check10_1_2,dxchcjcb_check10_1_3));
        ctSpecialShipType0203.setIpspectItem101(getCheckedId(dxchcjcb_check10_2_1,dxchcjcb_check10_2_2,dxchcjcb_check10_2_3));

        ctSpecialShipType0203.setIpspectItem110(getCheckedId(dxchcjcb_check11_1_1,dxchcjcb_check11_1_2,dxchcjcb_check11_1_3));
        ctSpecialShipType0203.setIpspectItem111(getCheckedId(dxchcjcb_check11_2_1,dxchcjcb_check11_2_2,dxchcjcb_check11_2_3));
        ctSpecialShipType0203.setIpspectItem112(getCheckedId(dxchcjcb_check11_3_1,dxchcjcb_check11_3_2,dxchcjcb_check11_3_3));

        ctSpecialShipType0203.setIpspectItem120(getCheckedId(dxchcjcb_check12_1_1,dxchcjcb_check12_1_2,dxchcjcb_check12_1_3));
        ctSpecialShipType0203.setIpspectItem121(getCheckedId(dxchcjcb_check12_2_1,dxchcjcb_check12_2_2,dxchcjcb_check12_2_3));
        ctSpecialShipType0203.setIpspectItem122(getCheckedId(dxchcjcb_check12_3_1,dxchcjcb_check12_3_2,dxchcjcb_check12_3_3));

        ctSpecialShipType0203.setIpspectItem130(getCheckedId(dxchcjcb_check13_1_1,dxchcjcb_check13_1_2,dxchcjcb_check13_1_3));
        ctSpecialShipType0203.setIpspectItem131(getCheckedId(dxchcjcb_check13_2_1,dxchcjcb_check13_1_2,dxchcjcb_check13_2_3));

        return ctSpecialShipType0203;
    }

    /**
     * 获得checkbox选中的值
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     * @param dxchcjcb_check3
     * @return
     */
    private String getCheckedId(CheckBox dxchcjcb_check1, CheckBox dxchcjcb_check2, CheckBox dxchcjcb_check3) {

        if(dxchcjcb_check1.isChecked()){
            return "0";
        }else if(dxchcjcb_check2.isChecked()){
            return "1";
        }else if(dxchcjcb_check3.isChecked()){
            return "2";
        }else {
            return "";
        }

    }
    private String sub_msg;
    private String id;
    @Override
    public void subSuccessfully(String msg,String inspectNo) {
        this.sub_msg=msg;
        dialog.dismiss();
        this.id=inspectNo;
        this.namepath="";
        myHandler.sendEmptyMessage(500);
    }

    private static class MyHandler extends Handler {

        WeakReference<DXSHCJCBActivity> dxshcjcbActivityWeakReference;

        MyHandler(DXSHCJCBActivity dxshcjcbActivity) {
            this.dxshcjcbActivityWeakReference = new WeakReference<>(dxshcjcbActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final DXSHCJCBActivity supervisionActivity = dxshcjcbActivityWeakReference.get();
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg+"是否打印大型散货船检查表？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        String name =  supervisionActivity.dxshcjcb_et_cm.getText().toString()+"_"+supervisionActivity.id+".pdf";
                        supervisionActivity.namepath = supervisionActivity.path + name;
                        File file = new File(supervisionActivity.namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(supervisionActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",supervisionActivity.namepath);
                            supervisionActivity.startActivity(intent);
                        }else{
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
            if(what == 600){

                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg+"是否重新提交？", "重新提交","返回修改", new DialogUtils.DialogOnClickListenner() {

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
    String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath = "";
    DownInfo apkApi;
    private void downloadPDF(){
        String name =  dxshcjcb_et_cm.getText().toString()+"_"+id+".pdf";
        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctSpecialShipType0203RestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params
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
                Intent intent=new Intent(DXSHCJCBActivity.this, Activity_PrintPdf.class);
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
     //           deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }
    @Override
    public void subOnFail(String msg) {
        dialog.dismiss();
        this.sub_msg=msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
    }
}
