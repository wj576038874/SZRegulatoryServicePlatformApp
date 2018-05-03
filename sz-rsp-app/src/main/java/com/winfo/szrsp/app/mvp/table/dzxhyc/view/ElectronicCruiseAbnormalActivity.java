package com.winfo.szrsp.app.mvp.table.dzxhyc.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.dzxhyc.presenter.ElectronicCruiseAbnormalPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by wly on 2018/1/22.
 * 电子巡航异常情况通报表
 */

public class ElectronicCruiseAbnormalActivity extends Activity implements View.OnClickListener,IElectronicCruiseAbnormalActivity {

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;

    private TextView tv_date;

    private TextView tv_abnormality_time;

    private TextView tv_handleTime;

    private TimePickerView timePickerView;

    private TimePickerView timePickerView2;

    private TimePickerView timePickerView3;

    private Dialog dialog;

    private Dialog downloadDialog;

    private Button btn_submit;

    private ElectronicCruiseAbnormalPresenter presenter;

    private EditText et_noticeOrg;

    private EditText et_exceptionType;

    private EditText et_exceptionDescribe;

    private EditText et_handleResult;

    private EditText et_remarks;

    private EditText et_inspectOrg;

    private EditText et_inspector;

    private EditText et_telephone;

    private CtElectronicCruiseException ctElectronicCruiseException;

    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_cruise_abnormal);

        initView();

        initData();

        initEvent();
    }

    private void initData() {
        table_titleBar_titleText.setText("执行表格");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_date.setText(time);
        tv_handleTime.setText(time);

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time2 = format2.format(new Date());
        tv_abnormality_time.setText(time2);

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());

        timePickerView2 = new TimePickerView(this, TimePickerView.Type.ALL);
        timePickerView2.setCyclic(true);
        timePickerView2.setTime(new Date());


        timePickerView3 = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView3.setCyclic(true);
        timePickerView3.setTime(new Date());

    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        tv_date.setOnClickListener(this);
        tv_abnormality_time.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        tv_handleTime.setOnClickListener(this);
    }

    private void initView() {
        myHandler = new MyHandler(this);
        presenter=new ElectronicCruiseAbnormalPresenter(this);
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        tv_date=findViewById(R.id.tv_date);
        tv_abnormality_time=findViewById(R.id.tv_abnormality_time);

        dialog= DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");

        btn_submit=findViewById(R.id.btn_submit);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.tv_date:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_date.setText(time);
                    }
                });
                timePickerView.show();
                break;

            case R.id.tv_abnormality_time:
                timePickerView2.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                        String time = format.format(date);
                        tv_abnormality_time.setText(time);
                    }
                });
                timePickerView2.show();
                break;

            case R.id.tv_handleTime:
                timePickerView3.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_handleTime.setText(time);
                    }
                });
                timePickerView3.show();
                break;


            case R.id.btn_submit:

                if ("".equals(et_noticeOrg.getText().toString().trim())){
                    showMsg("请填写通报单位");
                    return;
                }

                if ("".equals(et_exceptionType.getText().toString().trim())){
                    showMsg("请填写异常情况类型");
                    return;
                }

                if ("".equals(et_inspectOrg.getText().toString().trim())){
                    showMsg("请填写填报单位");
                    return;
                }



                presenter.subCtElectronicCruiseException();
                break;

        }
    }

    @Override
    public void showMsg(String msg){
        ToastUtils.showToast(this,msg);
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public CtElectronicCruiseException getCtElectronicCruiseException() {

        ctElectronicCruiseException=new CtElectronicCruiseException();
        ctElectronicCruiseException.setNoticeOrg(et_noticeOrg.getText().toString().trim());
        ctElectronicCruiseException.setExceptionType(et_exceptionType.getText().toString().trim());
        ctElectronicCruiseException.setExceptionTime(tv_abnormality_time.getText().toString());
        ctElectronicCruiseException.setExceptionDescribe(et_exceptionDescribe.getText().toString().trim());
        ctElectronicCruiseException.setHandleResult(et_handleResult.getText().toString().trim());
        ctElectronicCruiseException.setHandleTime(tv_handleTime.getText().toString());
        ctElectronicCruiseException.setRemarks(et_remarks.getText().toString().trim());
        ctElectronicCruiseException.setInspectOrg(et_inspectOrg.getText().toString().trim());
        ctElectronicCruiseException.setInspector(et_inspector.getText().toString().trim());
        ctElectronicCruiseException.setTelephone(et_telephone.getText().toString().trim());
        ctElectronicCruiseException.setInspectTime(tv_date.getText().toString());

        return ctElectronicCruiseException;
    }

    private static class MyHandler extends Handler {

        WeakReference<ElectronicCruiseAbnormalActivity> electronicCruiseAbnormalActivityWeakReference;

        MyHandler(ElectronicCruiseAbnormalActivity electronicCruiseAbnormalActivity) {
            this.electronicCruiseAbnormalActivityWeakReference = new WeakReference<>(electronicCruiseAbnormalActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final ElectronicCruiseAbnormalActivity electronicCruiseAbnormalActivity = electronicCruiseAbnormalActivityWeakReference.get();
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(electronicCruiseAbnormalActivity, "温馨提示", electronicCruiseAbnormalActivity.sub_msg+",是否打印水域巡航表格？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        File file = new File(electronicCruiseAbnormalActivity.namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(electronicCruiseAbnormalActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",electronicCruiseAbnormalActivity.namepath);
                            electronicCruiseAbnormalActivity.startActivity(intent);
                            electronicCruiseAbnormalActivity.finish();
                        }else{
                            electronicCruiseAbnormalActivity.downloadPDF();
                            electronicCruiseAbnormalActivity.downloadDialog.show();
                            electronicCruiseAbnormalActivity.httpDownManager.startDown(electronicCruiseAbnormalActivity.apkApi);
                        }
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        electronicCruiseAbnormalActivity.finish();
                    }
                });
            }

            if(what == 600){
                DialogUtils.showDialog(electronicCruiseAbnormalActivity, "温馨提示", electronicCruiseAbnormalActivity.sub_msg+",是否重新提交？", "重新提交","返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        electronicCruiseAbnormalActivity.presenter.subCtElectronicCruiseException();
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

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name =  "电子巡航异常"+"_"+id+".pdf";

        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",id);
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
                Intent intent=new Intent(ElectronicCruiseAbnormalActivity.this, Activity_PrintPdf.class);
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
}
