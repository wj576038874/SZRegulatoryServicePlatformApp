package com.winfo.szrsp.app.mvp.table.xhtj.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.view.DXSHCJCBActivity;
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
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj
 * @Filename: CruiseStatisticsActivity
 * @Author: lsj
 * @Date: 2018/1/22  15:17
 * @Description:
 * @Version:
 */
public class CruiseStatisticsActivity extends Activity implements ICruiseStatisticsActivity,View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private Dialog dialog;
    private Dialog loadingdialog;
    private Toolbar toolbar;
    private LinearLayout ll_xhzy;
    private CruiseSummaryLayout cruiseSummaryLayout;
    private ImageView img_add;
    private ImageView img_del;
    private EditText edt_year;
    private EditText edt_month;
    private EditText edt_day;
    private TextView tv_way;
    private TimePickerView timePickerView;
    private MinuteTimePickerView mTimePickerView;
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
    private String area1 = "";
    private String area2 = "";
    private String area3 = "";
    private String xh_time,xh_week;
    private CruiseStatisticsPresenter presenter;
    private String taskTypeId;
    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xhtj);
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

        img_add.setOnClickListener(this);
        img_del.setOnClickListener(this);
        tv_xhsj.setOnClickListener(this);
        edt_year.setOnClickListener(this);
        edt_month.setOnClickListener(this);
        edt_day.setOnClickListener(this);
        ck_1.setOnCheckedChangeListener(this);
        ck_2.setOnCheckedChangeListener(this);
        ck_3.setOnCheckedChangeListener(this);
        btn_sub.setOnClickListener(this);
    }

    private void initData() {
//        Calendar c = Calendar.getInstance();
//        String year = String.valueOf(c.get(Calendar.YEAR));
//        String month = String.valueOf(c.get(Calendar.MONTH)+1);
//        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
//        //根据日期来显示星期
//        c.set(Calendar.YEAR, c.get(Calendar.YEAR));
//        c.set(Calendar.MONTH, c.get(Calendar.MONTH) +1);
//        c.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH);
//        String week = "";
//        String weekIndex = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
//        if("1".equals(weekIndex)){
//            week ="天";
//        }else if("2".equals(weekIndex)){
//            week ="一";
//        }else if("3".equals(weekIndex)){
//            week ="二";
//        }else if("4".equals(weekIndex)){
//            week ="三";
//        }else if("5".equals(weekIndex)){
//            week ="四";
//        }else if("6".equals(weekIndex)){
//            week ="五";
//        }else if("7".equals(weekIndex)){
//            week ="六";
//        }
//        edt_year.setText(year);
//        edt_month.setText(month);
//        edt_day.setText(day);
//        tv_way.setText("星期"+week);
    }

    private void initView() {
        myHandler = new MyHandler(this);
        presenter = new CruiseStatisticsPresenter(this);
        dialog = DialogUtils.createLoadingDialog(this,"提交中...");
        loadingdialog = DialogUtils.createLoadingDialog(this,"下载中...");
        toolbar = findViewById(R.id.toolbar);
        ll_xhzy = findViewById(R.id.layout_xhjy);
        cruiseSummaryLayout = new CruiseSummaryLayout(this);
        ll_xhzy.addView(cruiseSummaryLayout);
        img_add = findViewById(R.id.add_img);
        img_del= findViewById(R.id.del_img);
        edt_year = findViewById(R.id.edt_year);
        edt_month = findViewById(R.id.edt_month);
        edt_day = findViewById(R.id.edt_day);
        tv_way = findViewById(R.id.tv_way);
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        mTimePickerView= new MinuteTimePickerView(this, MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());
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
        try {
            taskTypeId = getIntent().getExtras().getString("taskTypeId");
        }catch (Exception e){
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()){
            case R.id.ck_1:
                if (isCheck){
                    area1 = "1";
                }else {
                    area1 = "";
                }
                break;
            case R.id.ck_2:
                if (isCheck){
                    area2 = "2";
                }else {
                    area2 = "";
                }
                break;
            case R.id.ck_3:
                if (isCheck){
                    area3 = "3";
                }else {
                    area3 = "";
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_img:
                cruiseSummaryLayout = new CruiseSummaryLayout(this);
                ll_xhzy.addView(cruiseSummaryLayout);
                break;
            case R.id.del_img:
                if (ll_xhzy.getChildCount() != 1){
                    ll_xhzy.removeViewAt(ll_xhzy.getChildCount()-1);
                }
                break;
            case R.id.edt_year:
                setXHtime();
                break;
            case R.id.edt_month:
                setXHtime();
                break;
            case R.id.edt_day:
                setXHtime();
                break;
            case R.id.tv_xhsj:
                String trim = tv_xhsj.getText().toString().trim();
                mTimePickerView.setPicker(trim);
                mTimePickerView.show();
                mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String time) {
                        tv_xhsj.setText(time);
                    }
                });
                break;
            case R.id.btn_save:
                if (xh_time != null && !tv_xhsj.getText().toString().equals("")){
                    CtCruiseStatisticsObject ctCruiseStatisticsObject = new CtCruiseStatisticsObject();
                    CtCruiseStatistics info = new CtCruiseStatistics();
                    List<CtCruiseStatisticsDetail> list = new ArrayList<>();
                    info.setCruiseTime(xh_time);
                    info.setWeeknum(xh_week);
                    info.setArea(area1+area2+area3);
                    info.setWeather(edt_weather.getText().toString());
                    info.setSeaStatic(edt_hk.getText().toString());
                    String str = tv_xhsj.getText().toString();
                    if (taskTypeId!=null){
                        info.setTaskId(taskTypeId);
                    }
                    info.setStartTime(str.substring(0, str.indexOf("-")));
                    info.setEndTime(str.substring(str.indexOf("-")+1));
                    info.setCruiseArea(edt_xhsy.getText().toString());
                    info.setItem11(edt_item_11.getText().toString());
                    info.setItem12(edt_item_12.getText().toString());
                    info.setItem13(edt_item_13.getText().toString());
                    info.setItem14(edt_item_14.getText().toString());
                    info.setItem15(edt_item_15.getText().toString());
                    info.setItem16(edt_item_16.getText().toString());
                    info.setItem21(edt_item_21.getText().toString());
                    info.setItem22(edt_item_22.getText().toString());
                    info.setItem23(edt_item_23.getText().toString());
                    info.setItem24(edt_item_24.getText().toString());
                    info.setItem25(edt_item_25.getText().toString());
                    info.setItem26(edt_item_26.getText().toString());
                    info.setItem27(edt_item_27.getText().toString());
                    info.setItem30(edt_item_30.getText().toString());
                    info.setItem40(edt_item_40.getText().toString());
                    info.setItem50(edt_item_50.getText().toString());
                    info.setItem60(edt_item_60.getText().toString());
                    info.setItem71(edt_item_71.getText().toString());
                    info.setItem72(edt_item_72.getText().toString());
                    info.setOutLine(edt_outline.getText().toString());
                    info.setShipName(edt_shipname.getText().toString());
                    info.setInspectorName(edt_zfry.getText().toString());
                    if (!edt_shipname.getText().toString().equals("") && !edt_zfry.getText().toString().equals("")){
                        for (int i = 0; i < ll_xhzy.getChildCount(); i++) {
                            CruiseSummaryLayout cruiseSummaryLayout = (CruiseSummaryLayout) ll_xhzy.getChildAt(i);
                            if (cruiseSummaryLayout.getData() == null){
                                return;
                            }else {
                                list.add(cruiseSummaryLayout.getData());
                            }
                        }
                        ctCruiseStatisticsObject.setCruiseStatistics(info);
                        ctCruiseStatisticsObject.setCruiseStatisticsDetailList(list);
                        presenter.subData(ctCruiseStatisticsObject);
                    }else {
                        ToastUtils.showToast(this,"请将巡航船舶和执法人员填写完整再提交！");
                    }
                }else {
                    ToastUtils.showToast(this,"请将巡航时间填写完整再提交！");
                    return;
                }
                break;
        }
    }

    private void setXHtime(){
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String time = format.format(date);
                xh_time = time;
                edt_year.setText(time.substring(0, 4));
                edt_month.setText(time.substring(5, 7));
                edt_day.setText(time.substring(8, 10));
                Calendar c = Calendar.getInstance();
                //根据日期来显示星期
                int year = Integer.parseInt(time.substring(0, 4));
                int month = Integer.parseInt(time.substring(5, 7));
                int day = Integer.parseInt(time.substring(8, 10));
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month - 1);
                c.set(Calendar.DAY_OF_MONTH, day);
                String week = "";
                String weekIndex = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
                if("1".equals(weekIndex)){
                    week ="天";
                }else if("2".equals(weekIndex)){
                    week ="一";
                }else if("3".equals(weekIndex)){
                    week ="二";
                }else if("4".equals(weekIndex)){
                    week ="三";
                }else if("5".equals(weekIndex)){
                    week ="四";
                }else if("6".equals(weekIndex)){
                    week ="五";
                }else if("7".equals(weekIndex)){
                    week ="六";
                }
                tv_way.setVisibility(View.VISIBLE);
                tv_way.setText("星期"+week);
                xh_week = week;
            }
        });
        timePickerView.show();
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    private String sub_msg;
    private String id;
    @Override
    public void onSuccess(String msg) {
        this.sub_msg=msg;
        dialog.dismiss();
        this.id = msg;
        this.namepath="";
        myHandler.sendEmptyMessage(500);
    }

    @Override
    public void onFaile(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void setData(CtCruiseStatisticsObject ctCruiseStatisticsObject) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);

    }

    private static class MyHandler extends Handler {

        WeakReference<CruiseStatisticsActivity> cruiseStatisticsActivityWeakReference;

        MyHandler(CruiseStatisticsActivity cruiseStatisticsActivity) {
            this.cruiseStatisticsActivityWeakReference = new WeakReference<>(cruiseStatisticsActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final CruiseStatisticsActivity cruiseStatisticsActivity = cruiseStatisticsActivityWeakReference.get();
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(cruiseStatisticsActivity, "温馨提示", "提交成功！是否打印巡航统计检查表？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        String name =  cruiseStatisticsActivity.edt_shipname.getText().toString()+"_"+cruiseStatisticsActivity.id+".pdf";
                        cruiseStatisticsActivity.namepath = cruiseStatisticsActivity.path + name;
                        File file = new File(cruiseStatisticsActivity.namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(cruiseStatisticsActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",cruiseStatisticsActivity.namepath);
                            cruiseStatisticsActivity.startActivity(intent);
                        }else{
                            cruiseStatisticsActivity.downloadPDF();
                            cruiseStatisticsActivity.loadingdialog.show();
                            cruiseStatisticsActivity.httpDownManager.startDown(cruiseStatisticsActivity.apkApi);
                        }
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });
            }
            if(what == 600){
                ToastUtils.showToast(cruiseStatisticsActivity,cruiseStatisticsActivity.sub_msg);
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
        String name =  edt_shipname.getText().toString()+"_"+id+".pdf";
        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctCruiseStatisticsRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params
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
                loadingdialog.dismiss();
                Intent intent=new Intent(CruiseStatisticsActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2",namepath);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                loadingdialog.dismiss();
                super.onError(e);
                File file = new File(namepath);
                if (file.isFile()) {
                    file.delete();
                }
                deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
            }
        });
    }

}
