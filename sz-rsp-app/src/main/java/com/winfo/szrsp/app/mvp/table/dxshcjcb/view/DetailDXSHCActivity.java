package com.winfo.szrsp.app.mvp.table.dxshcjcb.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2017-12-08.
 */

public class DetailDXSHCActivity extends Activity implements View.OnClickListener {
    @BindView(R.id.table_ferry_titleBar)
    View table_ferry_titleBar;
    @BindView(R.id.dxchcjcb_tv_time)
    TextView dxchcjcb_tv_time;
    @BindView(R.id.dxshcjcb_et_cm)
    TextView dxshcjcb_et_cm;
    @BindView(R.id.dxshcjcb_et_imo)
    TextView dxshcjcb_et_imo;
    @BindView(R.id.dxshcjcb_et_gk)
    TextView dxshcjcb_et_gk;
    @BindView(R.id.dxshcjcb_print)
    Button dxshcjcb_print;

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

    private Dialog dialog;
    private DownInfo apkApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dxshcjcb_detail);
        ButterKnife.bind(this);
        initData();
        dialog = DialogUtils.createLoadingDialog(this,"下载中...");
        initEvent();
        downloadPDF();
    }
    CtSpecialShipType0203 ctSpecialShipType0203;
    private void initData() {
        ctSpecialShipType0203= (CtSpecialShipType0203) getIntent().getSerializableExtra("dxshcData");

        if(ctSpecialShipType0203!=null){
            dxchcjcb_tv_time.setText(ctSpecialShipType0203.getInspectDate());

            dxshcjcb_et_cm.setText(ctSpecialShipType0203.getShipNameCn());

            dxshcjcb_et_imo.setText(ctSpecialShipType0203.getShipImo());

            dxshcjcb_et_gk.setText(ctSpecialShipType0203.getBerthCode());

            setCheckBoxItem(dxchcjcb_check1_1_1,dxchcjcb_check1_1_2,dxchcjcb_check1_1_3,ctSpecialShipType0203.getIpspectItem10());
            setCheckBoxItem(dxchcjcb_check1_2_1,dxchcjcb_check1_1_2,dxchcjcb_check1_2_3,ctSpecialShipType0203.getIpspectItem11());

            setCheckBoxItem(dxchcjcb_check2_1_1,dxchcjcb_check2_1_2,dxchcjcb_check2_1_3,ctSpecialShipType0203.getIpspectItem20());
            setCheckBoxItem(dxchcjcb_check2_2_1,dxchcjcb_check2_2_2,dxchcjcb_check2_2_3,ctSpecialShipType0203.getIpspectItem21());

            setCheckBoxItem(dxchcjcb_check3_1_1,dxchcjcb_check3_1_2,dxchcjcb_check3_1_3,ctSpecialShipType0203.getIpspectItem30());
            setCheckBoxItem(dxchcjcb_check3_2_1,dxchcjcb_check3_2_2,dxchcjcb_check3_2_3,ctSpecialShipType0203.getIpspectItem31());
            setCheckBoxItem(dxchcjcb_check3_3_1,dxchcjcb_check3_3_2,dxchcjcb_check3_3_3,ctSpecialShipType0203.getIpspectItem32());

            setCheckBoxItem(dxchcjcb_check4_1_1,dxchcjcb_check4_1_2,dxchcjcb_check4_1_3,ctSpecialShipType0203.getIpspectItem40());
            setCheckBoxItem(dxchcjcb_check4_2_1,dxchcjcb_check4_2_2,dxchcjcb_check4_2_3,ctSpecialShipType0203.getIpspectItem41());

            setCheckBoxItem(dxchcjcb_check5_1_1,dxchcjcb_check5_1_2,dxchcjcb_check5_1_3,ctSpecialShipType0203.getIpspectItem50());
            setCheckBoxItem(dxchcjcb_check5_2_1,dxchcjcb_check5_2_2,dxchcjcb_check5_2_3,ctSpecialShipType0203.getIpspectItem51());

            setCheckBoxItem(dxchcjcb_check6_1_1,dxchcjcb_check6_1_2,dxchcjcb_check6_1_3,ctSpecialShipType0203.getIpspectItem60());
            setCheckBoxItem(dxchcjcb_check6_2_1,dxchcjcb_check6_2_2,dxchcjcb_check6_2_3,ctSpecialShipType0203.getIpspectItem61());
            setCheckBoxItem(dxchcjcb_check6_3_1,dxchcjcb_check6_3_2,dxchcjcb_check6_3_3,ctSpecialShipType0203.getIpspectItem62());

            setCheckBoxItem(dxchcjcb_check7_1_1,dxchcjcb_check7_1_2,dxchcjcb_check7_1_3,ctSpecialShipType0203.getIpspectItem70());
            setCheckBoxItem(dxchcjcb_check7_2_1,dxchcjcb_check7_2_2,dxchcjcb_check7_2_3,ctSpecialShipType0203.getIpspectItem71());
            setCheckBoxItem(dxchcjcb_check7_3_1,dxchcjcb_check7_3_2,dxchcjcb_check7_3_3,ctSpecialShipType0203.getIpspectItem72());

            setCheckBoxItem(dxchcjcb_check8_1_1,dxchcjcb_check8_1_2,dxchcjcb_check8_1_3,ctSpecialShipType0203.getIpspectItem80());
            setCheckBoxItem(dxchcjcb_check8_2_1,dxchcjcb_check8_2_2,dxchcjcb_check8_2_3,ctSpecialShipType0203.getIpspectItem81());
            setCheckBoxItem(dxchcjcb_check8_3_1,dxchcjcb_check8_3_2,dxchcjcb_check8_3_3,ctSpecialShipType0203.getIpspectItem82());

            setCheckBoxItem(dxchcjcb_check9_1_1,dxchcjcb_check9_1_2,dxchcjcb_check9_1_3,ctSpecialShipType0203.getIpspectItem90());
            setCheckBoxItem(dxchcjcb_check9_2_1,dxchcjcb_check9_2_2,dxchcjcb_check9_2_3,ctSpecialShipType0203.getIpspectItem91());
            setCheckBoxItem(dxchcjcb_check9_3_1,dxchcjcb_check9_3_2,dxchcjcb_check9_3_3,ctSpecialShipType0203.getIpspectItem92());

            setCheckBoxItem(dxchcjcb_check10_1_1,dxchcjcb_check10_1_2,dxchcjcb_check10_1_3,ctSpecialShipType0203.getIpspectItem100());
            setCheckBoxItem(dxchcjcb_check10_2_1,dxchcjcb_check10_2_2,dxchcjcb_check10_2_3,ctSpecialShipType0203.getIpspectItem101());

            setCheckBoxItem(dxchcjcb_check11_1_1,dxchcjcb_check11_1_2,dxchcjcb_check11_1_3,ctSpecialShipType0203.getIpspectItem110());
            setCheckBoxItem(dxchcjcb_check11_2_1,dxchcjcb_check11_2_2,dxchcjcb_check11_2_3,ctSpecialShipType0203.getIpspectItem111());
            setCheckBoxItem(dxchcjcb_check11_3_1,dxchcjcb_check11_3_2,dxchcjcb_check11_3_3,ctSpecialShipType0203.getIpspectItem112());

            setCheckBoxItem(dxchcjcb_check12_1_1,dxchcjcb_check12_1_2,dxchcjcb_check12_1_3,ctSpecialShipType0203.getIpspectItem120());
            setCheckBoxItem(dxchcjcb_check12_2_1,dxchcjcb_check12_2_2,dxchcjcb_check12_2_3,ctSpecialShipType0203.getIpspectItem121());
            setCheckBoxItem(dxchcjcb_check12_3_1,dxchcjcb_check12_3_2,dxchcjcb_check12_3_3,ctSpecialShipType0203.getIpspectItem122());

            setCheckBoxItem(dxchcjcb_check13_1_1,dxchcjcb_check13_1_2,dxchcjcb_check13_1_3,ctSpecialShipType0203.getIpspectItem130());
            setCheckBoxItem(dxchcjcb_check13_2_1,dxchcjcb_check13_2_2,dxchcjcb_check13_2_3,ctSpecialShipType0203.getIpspectItem131());

        }
    }

    private void setCheckBoxItem(CheckBox dxchcjcb_check1, CheckBox dxchcjcb_check2, CheckBox dxchcjcb_check3, String ipspectItem10) {
        if (ipspectItem10==null||ipspectItem10.equals("")){
            return;
        }else if(ipspectItem10.equals("0")){
            dxchcjcb_check1.setChecked(true);
        }else if(ipspectItem10.equals("1")){
            dxchcjcb_check2.setChecked(true);
        }else if(ipspectItem10.equals("2")){
            dxchcjcb_check3.setChecked(true);
        }


    }

    private void initEvent() {
        table_ferry_titleBar.findViewById(R.id.table_titleBar_imgbtn_back).setOnClickListener(this);
        dxshcjcb_print.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.dxshcjcb_print:
                File file = new File(namepath_pdf);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(DetailDXSHCActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2",namepath_pdf);
                    startActivity(intent);
                }else{
                    dialog.show();
                    httpDownManager.startDown(apkApi_pdf);
                }
                break;

        }

    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    String path_pdf = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath_pdf = "";
    DownInfo apkApi_pdf;
    private void downloadPDF(){

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", ctSpecialShipType0203.getInspectNo());
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name = ctSpecialShipType0203.getShipNameCn()+"_"+ctSpecialShipType0203.getInspectNo()+".pdf";
        namepath_pdf = path_pdf + name;
        apkApi_pdf = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctSpecialShipType0203RestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
        File outputFile = new File(namepath_pdf);

        apkApi_pdf.setSavePath(outputFile.getAbsolutePath());
        apkApi_pdf.setListener(new HttpProgressOnNextListener() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onComplete() {
                dialog.dismiss();
                Intent intent=new Intent(DetailDXSHCActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2",namepath_pdf);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
                super.onError(e);
                File file = new File(namepath_pdf);
                if (file.isFile()) {
                    file.delete();
                }
//                deleteFile(namepath_pdf);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }
}
