package com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.DetailOrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.ContainerWeightInspectData;
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
 * Created by wly on 2018/3/13.
 */

public class DetailContainerWeightInspectActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton ib_table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.table_titleBar_titleText)
    TextView tv_table_titleBar_titleText;
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
    @BindView(R.id.et_weight_book_id)
    TextView et_weight_book_id;
    @BindView(R.id.ck_whole)
    CheckBox ck_whole;
    @BindView(R.id.ck_add)
    CheckBox ck_add;
    @BindView(R.id.ck_isInspect_1)
    CheckBox ck_isInspect_1;
    @BindView(R.id.ck_isInspect_2)
    CheckBox ck_isInspect_2;
    @BindView(R.id.ck_1_yes)
    CheckBox ck_1_yes;
    @BindView(R.id.ck_2_yes)
    CheckBox ck_2_yes;
    @BindView(R.id.ck_3_yes)
    CheckBox ck_3_yes;
    @BindView(R.id.ck_1_no)
    CheckBox ck_1_no;
    @BindView(R.id.ck_2_no)
    CheckBox ck_2_no;
    @BindView(R.id.ck_3_no)
    CheckBox ck_3_no;
    @BindView(R.id.ck_item1)
    CheckBox ck_item1;
    @BindView(R.id.ck_item2)
    CheckBox ck_item2;
    @BindView(R.id.ck_item3)
    CheckBox ck_item3;
    @BindView(R.id.ck_item4)
    CheckBox ck_item4;
    @BindView(R.id.ck_item5)
    CheckBox ck_item5;
    @BindView(R.id.ck_item6)
    CheckBox ck_item6;
    @BindView(R.id.et_goods_weight)
    TextView et_goods_weight;
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

    private Dialog downloadDialog;

    private String id = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cbzy_jzxzljc);
        ButterKnife.bind(this);

        initView();
        initData();
        initEvent();

    }

    private void initEvent() {
        ib_table_titleBar_imgbtn_back.setOnClickListener(this);
        btn_print_pdf.setOnClickListener(this);

    }

    private void initView() {
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        tv_table_titleBar_titleText.setText("船舶载运货物集装箱重量验证检查记录表");
    }

    private void initData() {
        ContainerWeightInspectData data = (ContainerWeightInspectData) getIntent().getSerializableExtra("ContainerWeightInspectData");
        if (data != null) {
            id = data.getInspectNo();
            et_ship_inspectTime.setText(data.getInspectTime());
            et_ship_deeSea.setText(data.getDeepSea());
            et_ship_time.setText(data.getDeepSeaTime());
            et_ship_num.setText(data.getDeepSeaNum());
            et_carrier_ship.setText(data.getCarrierShip());
            et_carrier_people.setText(data.getCarrierAgent());
            et_voyage_number.setText(data.getVoyageId());
            et_consignment_people.setText(data.getConsignAgent());
            et_order_num.setText(data.getBillNumber());
            et_box_num.setText(data.getCaseNumber());
            et_weight_book_id.setText(data.getWeightInspectNotice());
            et_goods_weight.setText(data.getWeight());
            et_zhifa_people1.setText(data.getInspector());
            et_zhifa_cardNum1.setText(data.getInspectorCode());
//            et_autograph.setText(data.getConsignAgentName());

            String ckMethod = data.getItemWeight();
            if (ckMethod.equals("1")) {
                ck_whole.setChecked(true);
            } else if (ckMethod.equals("2")) {
                ck_add.setChecked(true);
            }
            ck_whole.setClickable(false);
            ck_add.setClickable(false);

            String ckitemGood = data.getItemGood();
            if (ckitemGood.equals("1")) {
                ck_isInspect_1.setChecked(true);
            }
            ck_isInspect_1.setClickable(false);

            String ckItemScene = data.getItemScene();
            if (ckItemScene.equals("1")) {
                ck_isInspect_2.setChecked(true);
            }
            ck_isInspect_2.setClickable(false);

            String ck1 = data.getItemGood1();
            if (ck1.equals("1")) {
                ck_1_yes.setChecked(true);
            } else if (ck1.equals("0")) {
                ck_1_no.setChecked(true);
            }
            ck_1_yes.setClickable(false);
            ck_1_no.setClickable(false);

            String ck2 = data.getItemScene2();
            if (ck2.equals("1")) {
                ck_2_yes.setChecked(true);
            } else if (ck2.equals("0")) {
                ck_2_no.setChecked(true);
            }
            ck_2_yes.setClickable(false);

            String ck3 = data.getItemScene3();
            if (ck3.equals("1")) {
                ck_3_yes.setChecked(true);
            } else if (ck3.equals("0")) {
                ck_3_no.setChecked(true);
            }
            ck_3_yes.setClickable(false);
            ck_3_no.setClickable(false);

            String ckItem1 = data.getItemExce();
            if (ckItem1.equals("1")) {
                ck_item1.setChecked(true);
            } else if (ckItem1.equals("2")) {
                ck_item2.setChecked(true);
            } else if (ckItem1.equals("3")) {
                ck_item3.setChecked(true);
            }
            ck_item1.setClickable(false);
            ck_item2.setClickable(false);
            ck_item3.setClickable(false);

            String ckItem2 = data.getItemHandle();
            if (ckItem2.equals("1")) {
                ck_item4.setChecked(true);
            } else if (ckItem2.equals("2")) {
                ck_item5.setChecked(true);
            } else if (ckItem2.equals("3")) {
                ck_item6.setChecked(true);
            }
            ck_item4.setClickable(false);
            ck_item5.setClickable(false);
            ck_item6.setClickable(false);
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
        String url = ServerReqAddress.BASE_ADDRESS + "sz/ctCaseWeightInspectRestService/downAutograph?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params;
        Glide.with(this).load(url).into(iv_qm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.btn_print_pdf:
                String name = "货物集装箱重量验证检查记录表" + "_" + id + ".pdf";
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
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctCaseWeightInspectRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(DetailContainerWeightInspectActivity.this, Activity_PrintPdf.class);
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
