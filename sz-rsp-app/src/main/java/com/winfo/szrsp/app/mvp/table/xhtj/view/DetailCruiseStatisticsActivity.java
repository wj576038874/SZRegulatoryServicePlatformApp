package com.winfo.szrsp.app.mvp.table.xhtj.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.view.DetailDXSHCActivity;
import com.winfo.szrsp.app.mvp.table.xhtj.presenter.CruiseStatisticsPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatistics;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsDetail;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj.view
 * @Filename: DetailCruiseStatisticsActivity
 * @Author: lsj
 * @Date: 2018/1/26  11:46
 * @Description:
 * @Version:
 */
public class DetailCruiseStatisticsActivity extends Activity implements ICruiseStatisticsActivity{
    private Dialog dialog;
    private Toolbar toolbar;
    private LinearLayout ll_xhzy;
    private DeatilCruiseSummaryLayout cruiseSummaryLayout;
    private EditText edt_year;
    private EditText edt_month;
    private EditText edt_day;
    private TextView tv_way;
    private TextView tv_xhsj;
    private CheckBox ck_1,ck_2,ck_3;
    private EditText edt_weather;
    private EditText edt_hk;
    private EditText edt_xhsy;
    private EditText edt_item_11;
    private EditText edt_item_12;
    private EditText edt_item_13;
    private EditText edt_item_14;
    private EditText edt_item_15;
    private EditText edt_item_16;
    private EditText edt_item_21;
    private EditText edt_item_22;
    private EditText edt_item_23;
    private EditText edt_item_24;
    private EditText edt_item_25;
    private EditText edt_item_26;
    private EditText edt_item_27;
    private EditText edt_item_30;
    private EditText edt_item_40;
    private EditText edt_item_50;
    private EditText edt_item_60;
    private EditText edt_item_71;
    private EditText edt_item_72;
    private EditText edt_outline;
    private EditText edt_shipname;
    private EditText edt_zfry;
    private Button btn_sub;
    private CruiseStatisticsPresenter presenter;
    private CtCruiseStatistics info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xhtj_detail);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(namepath_pdf);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(DetailCruiseStatisticsActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2",namepath_pdf);
                    startActivity(intent);
                }else{
                    dialog.show();
                    httpDownManager.startDown(apkApi_pdf);
                }
            }
        });
    }

    private void initData() {
        String id = getIntent().getStringExtra("inspectNo");
        presenter.findData(id);
    }

    private void initView() {
        presenter = new CruiseStatisticsPresenter(this);
        dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        toolbar = findViewById(R.id.toolbar);
        ll_xhzy = findViewById(R.id.layout_xhjy);
        edt_year = findViewById(R.id.edt_year);
        edt_month = findViewById(R.id.edt_month);
        edt_day = findViewById(R.id.edt_day);
        tv_way = findViewById(R.id.tv_way);
        tv_xhsj = findViewById(R.id.tv_xhsj);
        ck_1 = findViewById(R.id.ck_1);
        ck_2 = findViewById(R.id.ck_2);
        ck_3 = findViewById(R.id.ck_3);
        edt_hk = findViewById(R.id.edt_hk);
        edt_weather = findViewById(R.id.edt_weather);
        edt_xhsy = findViewById(R.id.edt_xhsy);
        edt_item_11 = findViewById(R.id.edt_item_11);
        edt_item_12 = findViewById(R.id.edt_item_12);
        edt_item_13 = findViewById(R.id.edt_item_13);
        edt_item_14 = findViewById(R.id.edt_item_14);
        edt_item_15 = findViewById(R.id.edt_item_15);
        edt_item_16 = findViewById(R.id.edt_item_16);
        edt_item_21 = findViewById(R.id.edt_item_21);
        edt_item_22 = findViewById(R.id.edt_item_22);
        edt_item_23 = findViewById(R.id.edt_item_23);
        edt_item_24 = findViewById(R.id.edt_item_24);
        edt_item_25 = findViewById(R.id.edt_item_25);
        edt_item_26 = findViewById(R.id.edt_item_26);
        edt_item_27 = findViewById(R.id.edt_item_27);
        edt_item_30 = findViewById(R.id.edt_item_30);
        edt_item_40 = findViewById(R.id.edt_item_40);
        edt_item_50 = findViewById(R.id.edt_item_50);
        edt_item_60 = findViewById(R.id.edt_item_60);
        edt_item_71 = findViewById(R.id.edt_item_71);
        edt_item_72 = findViewById(R.id.edt_item_72);
        edt_outline = findViewById(R.id.edt_outline);
        edt_shipname = findViewById(R.id.edt_shipname);
        edt_zfry = findViewById(R.id.edt_zfry);
        btn_sub = findViewById(R.id.btn_save);
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFaile(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void setData(CtCruiseStatisticsObject ctCruiseStatisticsObject) {
        info = ctCruiseStatisticsObject.getCruiseStatistics();
        List<CtCruiseStatisticsDetail> list = ctCruiseStatisticsObject.getCruiseStatisticsDetailList();
        String time = info.getCreateTime();
        edt_year.setText(time.substring(0, 4));
        edt_month.setText(time.substring(5, 7));
        edt_day.setText(time.substring(8, 10));
        tv_way.setText("星期"+info.getWeeknum());
        String aear = info.getArea();
        if (aear.contains("1")){
            ck_1.setChecked(true);
        }
        if (aear.contains("2")){
            ck_2.setChecked(true);
        }
        if (aear.contains("3")){
            ck_3.setChecked(true);
        }
        edt_weather.setText(info.getWeather());
        edt_hk.setText(info.getSeaStatic());
        tv_xhsj.setText(info.getStartTime()+":"+info.getEndTime());
        edt_xhsy.setText(info.getCruiseArea());
        edt_item_11.setText(info.getItem11());
        edt_item_12.setText(info.getItem12());
        edt_item_13.setText(info.getItem13());
        edt_item_14.setText(info.getItem14());
        edt_item_15.setText(info.getItem15());
        edt_item_16.setText(info.getItem16());
        edt_item_21.setText(info.getItem21());
        edt_item_22.setText(info.getItem22());
        edt_item_23.setText(info.getItem23());
        edt_item_24.setText(info.getItem24());
        edt_item_25.setText(info.getItem25());
        edt_item_26.setText(info.getItem26());
        edt_item_27.setText(info.getItem27());
        edt_item_30.setText(info.getItem30());
        edt_item_40.setText(info.getItem40());
        edt_item_50.setText(info.getItem50());
        edt_item_60.setText(info.getItem60());
        edt_item_71.setText(info.getItem71());
        edt_item_72.setText(info.getItem72());
        edt_outline.setText(info.getOutLine());
        edt_shipname.setText(info.getShipName());
        edt_zfry.setText(info.getInspectorName());
        for (int i = 0; i < list.size(); i++) {
            CtCruiseStatisticsDetail cruiseStatisticsDetail = list.get(i);
            cruiseSummaryLayout = new DeatilCruiseSummaryLayout(this,cruiseStatisticsDetail);
            ll_xhzy.addView(cruiseSummaryLayout);
        }
        downloadPDF();
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);

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
        mapParams.put("inspectNo", info.getInspectNo());
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name = info.getShipName()+"_"+info.getInspectNo()+".pdf";
        namepath_pdf = path_pdf + name;
        apkApi_pdf = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctCruiseStatisticsRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
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
                Intent intent=new Intent(DetailCruiseStatisticsActivity.this, Activity_PrintPdf.class);
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
                deleteFile(namepath_pdf);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }
}
