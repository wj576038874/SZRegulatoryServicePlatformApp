package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.DetailOrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.presenter.DangerousGoodsXianChangPresenter;
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


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 舶载运集装箱危险货物/污染危害性货物现场检查记录表详情
 * Created by HoBo on 2018/3/13.
 */

public class DetailDangerousGoodsXianChangActivity extends Activity implements IDangerousGoodsXianChangActivity {
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_chinaName)
    TextView et_ship_chinaName;
    @BindView(R.id.et_ship_englishName)
    TextView et_ship_englishName;
    @BindView(R.id.et_ship_nationality)
    TextView et_ship_nationality;
    @BindView(R.id.et_ship_weight)
    TextView et_ship_weight;
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
    TextView et_ship_loading_port;
    @BindView(R.id.et_ship_unloading_port)
    TextView et_ship_unloading_port;
    @BindView(R.id.et_ship_berth)
    TextView et_ship_berth;
    @BindView(R.id.et_ship_route)
    TextView et_ship_route;
    @BindView(R.id.et_ship_agent)
    TextView et_ship_agent;
    @BindView(R.id.et_ship_inspect)
    TextView et_ship_inspect;
    @BindView(R.id.et_ship_company)
    TextView et_ship_company;
    @BindView(R.id.tableLin2)
    TableLayout tableLin2;
    @BindView(R.id.tabLin)
    TableLayout tabLin;
    @BindView(R.id.et_ship_zhifa1)
    TextView et_ship_zhifa1;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.img_close_up)
    ImageView img_close_up;
    @BindView(R.id.img_close_down)
    ImageView img_close_down;
    private Dialog dialog;
    private Dialog downloadDialog;
    private DetailDangerousGoodsLayout detailDangerousGoodsLayout;
    private DangerousGoodsXianChangPresenter presenter;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbzy_wxhwxcjc_detail);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "加载中...");
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        presenter = new DangerousGoodsXianChangPresenter(this);
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "集装箱危险货物现场检查记录表" + "_" + id + ".pdf";
                namepath = path + name;
                File file = new File(namepath);
                if (file.exists()) {
                    //直接打印
                    Intent intent = new Intent(DetailDangerousGoodsXianChangActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2", namepath);
                    startActivity(intent);
                    finish();
                } else {
                    downloadPDF();
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi);
                }
            }
        });
        img_close_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLin.setVisibility(View.GONE);
                img_close_up.setVisibility(View.GONE);
                img_close_down.setVisibility(View.VISIBLE);
            }
        });
        img_close_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLin.setVisibility(View.VISIBLE);
                img_close_up.setVisibility(View.VISIBLE);
                img_close_down.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        String primaryKey = getIntent().getStringExtra("DetailDangerousGoodsXianChangData");
        presenter.findDataByPrimaryKey(primaryKey);
        id = primaryKey;
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSuccess(String msg, String resultData) {
        dialog.dismiss();
    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
    }

    @Override
    public DangerousGoodsXianChangData getData() {
        return null;
    }

    @Override
    public void setDetailData(String msg, DangerousGoodsXianChangData data) {
        et_ship_chinaName.setText(data.getCtDangerPolluteScene().getShipNameCn());
        et_ship_englishName.setText(data.getCtDangerPolluteScene().getShipNameEn());
        et_ship_nationality.setText(data.getCtDangerPolluteScene().getRegportName());
        et_ship_weight.setText(data.getCtDangerPolluteScene().getShipGrosston());
        et_ship_buildTime.setText(data.getCtDangerPolluteScene().getBuildDate());
        if (data.getCtDangerPolluteScene().getFlowTo().equals("1")) {
            ck_imported.setChecked(true);
        } else if (data.getCtDangerPolluteScene().getFlowTo().equals("2")) {
            ck_transit.setChecked(true);
        } else if (data.getCtDangerPolluteScene().getFlowTo().equals("3")) {
            ck_exit.setChecked(true);
        } else if (data.getCtDangerPolluteScene().getFlowTo().equals("4")) {
            ck_transfer.setChecked(true);
        }
        et_ship_loading_port.setText(data.getCtDangerPolluteScene().getLastPort());
        et_ship_unloading_port.setText(data.getCtDangerPolluteScene().getNextPort());
        et_ship_berth.setText(data.getCtDangerPolluteScene().getTaskPort());
        et_ship_route.setText(data.getCtDangerPolluteScene().getRoute());
        et_ship_agent.setText(data.getCtDangerPolluteScene().getAgent());
        et_ship_inspect.setText(data.getCtDangerPolluteScene().getBerthCode());
        et_ship_company.setText(data.getCtDangerPolluteScene().getCompanyName());
        et_ship_zhifa1.setText(data.getCtDangerPolluteScene().getInspector());
        et_ship_inspectTime.setText(data.getCtDangerPolluteScene().getInspectDate());
        if (data.getCtDangerPolluteSceneDetail() != null) {
            for (int i = 0; i < data.getCtDangerPolluteSceneDetail().size(); i++) {
                detailDangerousGoodsLayout = new DetailDangerousGoodsLayout(this, data.getCtDangerPolluteSceneDetail().get(i));
                tableLin2.addView(detailDangerousGoodsLayout);
            }
        }
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
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
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteSceneRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(DetailDangerousGoodsXianChangActivity.this, Activity_PrintPdf.class);
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
