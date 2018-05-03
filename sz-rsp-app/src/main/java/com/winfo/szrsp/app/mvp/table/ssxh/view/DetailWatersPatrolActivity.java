package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.ssxh.presenter.DetailWatersPatrolPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordInfo;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordObject;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseReport;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseShipInspect;
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
 * Created by ChengQi on 2017/12/8.
 *
 */

public class DetailWatersPatrolActivity extends Activity implements IDetailWatersPatrolActivity, View.OnClickListener {

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;


    private Button track_btn_print_pdf;

    private DetailWatersPatrolPresenter presenter;

    private ScrollView scrollView;
    private View view_faile;
    private TextView tv_faile;
    private String inspectNo;

    private Dialog downloadDialog;
    private LinearLayout syxh_layout;
    private LinearLayout mtax_layout;
    private LinearLayout sgzy_layout;
    private LinearLayout qtcb_layout;
    private TextView tv_date;

    private ImageView iv_qm;

    private CheckBox rb_am,rb_pm,rb_other,rb_mode;

    private CtWaterCruiseRecordObject ctWaterCruiseRecordObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_waters_patrol);

        initView();

        initData();

        initEvent();

    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        track_btn_print_pdf.setOnClickListener(this);
    }

    private void initView() {


        iv_qm=findViewById(R.id.iv_qm);

        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");

        track_btn_print_pdf=findViewById(R.id.track_btn_print_pdf);

        scrollView=findViewById(R.id.scrollView);
        view_faile=findViewById(R.id.view_faile);
        tv_faile = view_faile.findViewById(R.id.id_tv_data_load_faild);

        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        syxh_layout=findViewById(R.id.syxh_layout);
        mtax_layout=findViewById(R.id.mtax_layout);
        sgzy_layout=findViewById(R.id.sgzy_layout);
        qtcb_layout=findViewById(R.id.qtcb_layout);
        tv_date=findViewById(R.id.tv_date);
        rb_mode=findViewById(R.id.rb_mode);
        rb_am=findViewById(R.id.rb_am);
        rb_pm=findViewById(R.id.rb_pm);
        rb_other=findViewById(R.id.rb_other);
    }

    private void initData() {

        table_titleBar_titleText.setText("水上巡航表格");

        presenter=new DetailWatersPatrolPresenter(this);

        Intent intent=getIntent();
        inspectNo=intent.getStringExtra("watersPatrolInspectNo");

        ctWaterCruiseRecordObject=new CtWaterCruiseRecordObject();

        downqm();
        presenter.loadDetailWatersPatrolData(inspectNo);



    }

    //加载签名图片
    private void downqm(){
        Map<String, String> mapParams = new HashMap<>();
        String id = inspectNo;
        mapParams.put("inspectNo", id);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }
        String url=ServerReqAddress.BASE_ADDRESS + "sz/ctWaterCruiseRecordInfoRestService/downAutograph?projectSu=SZMSA&accessToken="+ access_token +"&requestSource=&parameterJson=" +params;
        Glide.with(this).load(url).into(iv_qm);
    }


    @Override
    public Dialog getDialog() {
        return DialogUtils.createLoadingDialog(this,"加载中...");
    }

    @Override
    public void onSuccess(CtWaterCruiseRecordObject ctWaterCruiseRecordObject) {
        scrollView.setVisibility(View.VISIBLE);
        view_faile.setVisibility(View.GONE);
        setData(ctWaterCruiseRecordObject);
    }

    @Override
    public void onFailure(String msg) {
        scrollView.setVisibility(View.GONE);
        view_faile.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
        view_faile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadDetailWatersPatrolData(inspectNo);
            }
        });
    }

    @Override
    public void setData(CtWaterCruiseRecordObject ctWaterCruiseRecordObject) {

        CtWaterCruiseRecordInfo info=ctWaterCruiseRecordObject.getInfo();
        tv_date.setText(info.getCreateTime().substring(0,10));

        if ("0".equals(info.getDayTime())){
            rb_am.setChecked(true);
        }else if ("1".equals(info.getDayTime())){
            rb_pm.setChecked(true);
        }else {
            rb_other.setChecked(true);
        }

        //0现场巡航（默认0） 1：导助航综合应用系统 2：vts终端  3：ais信息服务平台 4：船讯网  5：深圳海事局cctv终端
        if ("0".equals(info.getCruiseType())){
            rb_mode.setChecked(true);
        }

        List<CtWaterCruiseReport> ctWaterCruiseReports=ctWaterCruiseRecordObject.getListArea();

        for (int i=0;i<ctWaterCruiseReports.size();i++){

            CtWaterCruiseReport ctWaterCruiseReport=ctWaterCruiseReports.get(i);
            String time="";
            if(ctWaterCruiseReport.getStartTime().equals("")||ctWaterCruiseReport.getEndTime().equals("")){
            }else {
                time= ctWaterCruiseReport.getStartTime()+"-"+ctWaterCruiseReport.getEndTime();
            }

            String area=ctWaterCruiseReport.getCruiseArea();
            String cruiseState=ctWaterCruiseReport.getCruiseState();//区域状态 0:否   1：是
            String exap=ctWaterCruiseReport.getCruiseExap();
            //巡航位置 0：水域巡航  1：码头 岸线巡航
            String cruiseType=ctWaterCruiseReport.getCruiseType();
            if("0".equals(cruiseType)){
                //水域巡航
                boolean isNormal=false;
                boolean isSelect=true;
                if("0".equals(cruiseState)){
                    isNormal=false;
                }else if("1".equals(cruiseState)){
                    isNormal=true;
                }else {
                    isSelect=false;
                }
                SYXHLayout syxhLayout=new SYXHLayout(this,time,area,isNormal,isSelect,exap,false);
                syxh_layout.addView(syxhLayout);
            }else if("1".equals(cruiseType)){
                //水域巡航
                boolean isNormal=false;
                boolean isSelect=true;
                if("0".equals(cruiseState)){
                    isNormal=false;
                }else if("1".equals(cruiseState)){
                    isNormal=true;
                }else {
                    isSelect=false;
                }
                SYXHLayout syxhLayout=new SYXHLayout(this,time,area,isNormal,isSelect,exap,false);
                mtax_layout.addView(syxhLayout);
            }

        }
        List<CtWaterCruiseShipInspect> ctWaterCruiseShipInspects=ctWaterCruiseRecordObject.getListShip();
        for (int i=0;i<ctWaterCruiseShipInspects.size();i++) {
            CtWaterCruiseShipInspect ctWaterCruiseShipInspect=ctWaterCruiseShipInspects.get(i);
            String shipName=ctWaterCruiseShipInspect.getShipNameCn();
            String time="";
            if(ctWaterCruiseShipInspect.getStartTime().equals("")||ctWaterCruiseShipInspect.getEndTime().equals("")){
            }else {
                time= ctWaterCruiseShipInspect.getStartTime()+"-"+ctWaterCruiseShipInspect.getEndTime();
            }
            String place=ctWaterCruiseShipInspect.getShipPlace();
            String result=ctWaterCruiseShipInspect.getResult();
            String isWork=ctWaterCruiseShipInspect.getIsWork();//是否是作业船 0：否1：是

            if ("1".equals(isWork)){
                String workName=ctWaterCruiseShipInspect.getWorkName();
                SGZYLayout sgzyLayout=new SGZYLayout(this,time,workName,shipName,place,result,false);
                sgzy_layout.addView(sgzyLayout);
            }else {
                String lx=ctWaterCruiseShipInspect.getShipTypeNameCn();
                QTCBLayout qtcbLayout=new QTCBLayout(this,time,shipName,lx,place,result,false);
                qtcb_layout.addView(qtcbLayout);
            }
        }
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.track_btn_print_pdf:
                String name =  "水上巡航表格"+"_"+inspectNo+".pdf";
                namepath = path + name;
                File file = new File(namepath);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(DetailWatersPatrolActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2",namepath);
                    startActivity(intent);
                    finish();
                }else{
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
    String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath = "";
    DownInfo apkApi;

    private void downloadPDF(){

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }


        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",inspectNo);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctWaterCruiseRecordInfoRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
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
                Intent intent=new Intent(DetailWatersPatrolActivity.this, Activity_PrintPdf.class);
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




}
