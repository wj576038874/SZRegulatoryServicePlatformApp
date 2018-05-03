package com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;
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
 * Created by wly on 2018/3/12.
 *
 */

public class DetailOrdinaryGoodsKaiXiangActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton ib_table_titleBar_imgbtn_back;
    @BindView(R.id.table_titleBar_titleText)
    TextView tv_table_titleBar_titleText;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.et_ship_deeSea)
    TextView et_ship_deeSea;
    @BindView(R.id.et_ship_time)
    TextView et_ship_time;
    @BindView(R.id.et_ship_num)
    TextView et_ship_num;
    @BindView(R.id.et_carrier_ship)
    TextView et_carrier_ship;
    @BindView(R.id.et_carrier_people)
    TextView et_carrier_people;
    @BindView(R.id.et_voyage_number)
    TextView et_voyage_number;
    @BindView(R.id.et_consignment_people)
    TextView et_consignment_people;
    @BindView(R.id.et_order_num)
    TextView et_order_num;
    @BindView(R.id.et_box_num)
    TextView et_box_num;
    @BindView(R.id.ed_open_box_book_num)
    TextView ed_open_box_book_num;
    @BindView(R.id.et_inspect_before_num)
    TextView et_inspect_before_num;
    @BindView(R.id.et_inspect_after_num)
    TextView et_inspect_after_num;
    @BindView(R.id.et_goods_name)
    TextView et_goods_name;

    @BindView(R.id.ck_1_yes)
    CheckBox ck_1_yes;
    @BindView(R.id.ck_2_yes)
    CheckBox ck_2_yes;
    @BindView(R.id.ck_1_no)
    CheckBox ck_1_no;
    @BindView(R.id.ck_2_no)
    CheckBox ck_2_no;

    @BindView(R.id.et_exceptions_record)
    TextView et_exceptions_record;
    @BindView(R.id.ck_correct)
    CheckBox ck_correct;
    @BindView(R.id.ck_defects_exist)
    CheckBox ck_defects_exist;
    @BindView(R.id.ck_false)
    CheckBox ck_false;
    @BindView(R.id.ck_other)
    CheckBox ck_other;
    @BindView(R.id.et_process_results)
    TextView et_process_results;
    @BindView(R.id.et_zhifa_people1)
    TextView et_zhifa_people1;
    @BindView(R.id.et_zhifa_cardNum1)
    TextView et_zhifa_cardNum1;
    //    @BindView(R.id.et_autograph)
//    TextView et_autograph;
    @BindView(R.id.btn_print_pdf)
    Button btn_print_pdf;
    @BindView(R.id.iv_qm)
    ImageView iv_qm;
    @BindView(R.id.ed_other)
    TextView ed_other;
    private String id;

    private Dialog downloadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cbzy_pthwkxjc);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();

    }

    private void initView() {
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
    }

    private void initEvent() {
        ib_table_titleBar_imgbtn_back.setOnClickListener(this);
        btn_print_pdf.setOnClickListener(this);
    }

    private void initData() {
        tv_table_titleBar_titleText.setText("船舶载运普通货物集装箱开箱检查记录表");
        OrdinaryGoodsKaiXiangData ordinaryGoodsKaiXiangData = (OrdinaryGoodsKaiXiangData) getIntent().getSerializableExtra("OrdinaryGoodsKaiXiangData");
        if (ordinaryGoodsKaiXiangData != null) {
            id = ordinaryGoodsKaiXiangData.getInspectNo();
            et_ship_inspectTime.setText(ordinaryGoodsKaiXiangData.getInspectTime());
            et_ship_deeSea.setText(ordinaryGoodsKaiXiangData.getDeepSea());
            et_ship_time.setText(ordinaryGoodsKaiXiangData.getDeepSeaTime());
            et_ship_deeSea.setText(ordinaryGoodsKaiXiangData.getDeepSea());
            et_ship_num.setText(ordinaryGoodsKaiXiangData.getDeepSeaNum());
            et_carrier_ship.setText(ordinaryGoodsKaiXiangData.getCarrierShip());
            et_carrier_people.setText(ordinaryGoodsKaiXiangData.getCarrierAgent());
            et_voyage_number.setText(ordinaryGoodsKaiXiangData.getVoyageId());
            et_consignment_people.setText(ordinaryGoodsKaiXiangData.getConsignAgent());
            et_order_num.setText(ordinaryGoodsKaiXiangData.getBillNumber());
            et_box_num.setText(ordinaryGoodsKaiXiangData.getCaseNumber());
            ed_open_box_book_num.setText(ordinaryGoodsKaiXiangData.getOutInspectNotice());
            et_inspect_before_num.setText(ordinaryGoodsKaiXiangData.getBeforeInspectNum());
            et_inspect_after_num.setText(ordinaryGoodsKaiXiangData.getAfterInspectNum());
            et_goods_name.setText(ordinaryGoodsKaiXiangData.getGoodName());
            et_exceptions_record.setText(ordinaryGoodsKaiXiangData.getExceptionDescribe());
            et_process_results.setText(ordinaryGoodsKaiXiangData.getHandleResult());
            et_zhifa_people1.setText(ordinaryGoodsKaiXiangData.getInspector());
            et_zhifa_cardNum1.setText(ordinaryGoodsKaiXiangData.getInspectorCode());
//            et_autograph.setText(ordinaryGoodsKaiXiangData.getConsignAgentName());

            if (ordinaryGoodsKaiXiangData.getItem1().equals("1")) {
                ck_1_yes.setChecked(true);
            } else if (ordinaryGoodsKaiXiangData.getItem1().equals("0")){
                ck_1_no.setChecked(true);
            }
            ck_1_yes.setClickable(false);
            ck_1_no.setClickable(false);

            if (ordinaryGoodsKaiXiangData.getItem2().equals("1")) {
                ck_2_yes.setChecked(true);
            }else if (ordinaryGoodsKaiXiangData.getItem2().equals("0")){
                ck_2_no.setChecked(true);
            }
            ck_2_yes.setClickable(false);
            ck_2_no.setClickable(false);

            String handle = ordinaryGoodsKaiXiangData.getHandle();
            if (handle.equals("1")) {
                ck_correct.setChecked(true);
            } else if (handle.equals("2")) {
                ck_defects_exist.setChecked(true);
            } else if (handle.equals("3")) {
                ck_false.setChecked(true);
            } else if (handle.equals("4")) {
                ck_other.setChecked(true);
                ed_other.setText(ordinaryGoodsKaiXiangData.getRemark());
            }
            ck_correct.setClickable(false);
            ck_defects_exist.setClickable(false);
            ck_false.setClickable(false);
            ck_other.setClickable(false);

            downqm(id, iv_qm);

        }
    }

    //加载签名图片
    private void downqm(String id, ImageView iv_qm) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }
        String url = ServerReqAddress.BASE_ADDRESS + "sz/ctGoodSecneOutRestService/downAutograph?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params;
        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(iv_qm);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.btn_print_pdf:
                String name = "普通货物集装箱开箱检查记录表" + "_" + id + ".pdf";
                namepath = path + name;
                File file = new File(namepath);
                if (file.exists()) {
                    //直接打印
                    Intent intent = new Intent(this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2", namepath);
                    startActivity(intent);
                    finish();
                } else {
                    downloadPDF();
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi);
                }
                break;

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

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }


        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctGoodSecneOutRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(DetailOrdinaryGoodsKaiXiangActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2", namepath);
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


}
