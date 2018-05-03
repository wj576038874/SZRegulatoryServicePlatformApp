package com.winfo.szrsp.app.mvp.table.dzxhyc.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wly on 2018/1/26.
 *
 */

public class DetailElectronicCruiseAbnormalActivity extends Activity implements View.OnClickListener {

    private CtElectronicCruiseException ctElectronicCruiseException;

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;

    private TextView tv_date;

    private TextView tv_abnormality_time;

    private TextView tv_handleTime;

    private Button btn_print_pdf;

    private TextView et_noticeOrg;

    private TextView et_exceptionType;

    private TextView et_exceptionDescribe;

    private TextView et_handleResult;

    private TextView et_remarks;

    private TextView et_inspectOrg;

    private TextView et_inspector;

    private TextView et_telephone;

    private Dialog downloadDialog;

    private String inspectNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_electronic_cruise_abnormal);

        initView();

        initData();

        initEvent();

    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        btn_print_pdf.setOnClickListener(this);
    }

    private void initView() {

        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");

        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);

        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        tv_date=findViewById(R.id.tv_date);
        tv_abnormality_time=findViewById(R.id.tv_abnormality_time);
        btn_print_pdf=findViewById(R.id.btn_print_pdf);
        et_noticeOrg=findViewById(R.id.et_noticeOrg);
        et_exceptionType=findViewById(R.id.et_exceptionType);
        et_exceptionDescribe=findViewById(R.id.et_exceptionDescribe);
        et_handleResult=findViewById(R.id.et_handleResult);
        tv_handleTime=findViewById(R.id.tv_handleTime);
        et_remarks=findViewById(R.id.et_remarks);
        et_inspectOrg=findViewById(R.id.et_inspectOrg);
        et_inspector=findViewById(R.id.et_inspector);
        et_telephone=findViewById(R.id.et_telephone);

    }

    private void initData() {
        table_titleBar_titleText.setText("电子巡航异常");

        ctElectronicCruiseException = (CtElectronicCruiseException) getIntent().getSerializableExtra("CtElectronicCruiseException");
        if (ctElectronicCruiseException !=null){
            inspectNo=ctElectronicCruiseException.getInspectNo();
            et_noticeOrg.setText(ctElectronicCruiseException.getNoticeOrg());
            et_exceptionType.setText(ctElectronicCruiseException.getExceptionType());
            et_exceptionDescribe.setText(ctElectronicCruiseException.getExceptionDescribe());
            et_handleResult.setText(ctElectronicCruiseException.getHandleResult());
            et_remarks.setText(ctElectronicCruiseException.getRemarks());
            et_inspectOrg.setText(ctElectronicCruiseException.getInspectOrg());
            et_inspector.setText(ctElectronicCruiseException.getInspector());
            et_telephone.setText(ctElectronicCruiseException.getTelephone());
            tv_date.setText(ctElectronicCruiseException.getInspectTime());
            tv_abnormality_time.setText(ctElectronicCruiseException.getExceptionTime());
            tv_handleTime.setText(ctElectronicCruiseException.getHandleTime());

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.btn_print_pdf:
                String name =  "电子巡航异常"+"_"+inspectNo+".pdf";
                namepath = path + name;
                File file = new File(namepath);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(DetailElectronicCruiseAbnormalActivity.this, Activity_PrintPdf.class);
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
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctElectronicCruiseExceptionRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
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
                Intent intent=new Intent(DetailElectronicCruiseAbnormalActivity.this, Activity_PrintPdf.class);
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
