package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.ssxh.presenter.WatersPatrolActivityPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DetailArea;
import com.winfo.szrsp.app.sdk.entity.table.DetailShip;
import com.winfo.szrsp.app.sdk.entity.table.Info;
import com.winfo.szrsp.app.sdk.entity.table.WatersPatrol;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.BitmapUtils;
import com.winfo.szrsp.app.utils.DateFormatUtils;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.LandscapeActivity;
import com.winfo.szrsp.app.widget.LinePathView;
import com.winfo.szrsp.app.widget.SignatureView;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by ChengQi on 2017/12/2.
 * 水上巡航表格
 */

public class WatersPatrolActivity extends Activity implements View.OnClickListener,IWaterPatrolActivity {

    private ImageButton table_titleBar_imgbtn_back;
    private TextView table_titleBar_titleText;

    private ImageView  syxh_add_img;
    private ImageView  syxh_del_img;
    private LinearLayout syxh_layout;
    private SYXHLayout syxhLayout;

    private LinearLayout mtax_layout;
    private SYXHLayout mtaxLayout;
    private ImageView  mtax_add_img;
    private ImageView  mtax_del_img;

    private LinearLayout sgzy_layout;
    private SGZYLayout sgzyLayout;
    private ImageView  sgzy_add_img;
    private ImageView  sgzy_del_img;


    private LinearLayout qtcb_layout;
    private QTCBLayout qtcbLayout;
    private ImageView  qtcb_add_img;
    private ImageView  qtcb_del_img;

    private TextView tv_date;

    private ImageView iv_qm;

    private Button track_btn_ok;

    private String qm_path;

    private TextView tv_xhry_qm;

    private LinearLayout ll_name_signature;

    private WatersPatrolActivityPresenter presenter;

    private Dialog downloadDialog;

    private Dialog dialog;
    private MyHandler myHandler;

    Info info;
    private CheckBox rb_am,rb_pm,rb_other;
    private MinuteTimePickerView.Type timeType= MinuteTimePickerView.Type.ALL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waters_patrol);

        initView();

        initData();

        initEvent();

    }

    private void initData() {
        info=new Info();
        table_titleBar_titleText.setText("执行表格");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        tv_date.setText(simpleDateFormat.format(date));
        presenter=new WatersPatrolActivityPresenter(this);
    }

    private void initEvent() {

        table_titleBar_imgbtn_back.setOnClickListener(this);
        syxh_add_img.setOnClickListener(this);
        syxh_del_img.setOnClickListener(this);
        mtax_add_img.setOnClickListener(this);
        mtax_del_img.setOnClickListener(this);
        sgzy_add_img.setOnClickListener(this);
        sgzy_del_img.setOnClickListener(this);
        qtcb_add_img.setOnClickListener(this);
        qtcb_del_img.setOnClickListener(this);
        tv_xhry_qm.setOnClickListener(this);

        iv_qm.setOnClickListener(this);

        track_btn_ok.setOnClickListener(this);

        rb_am.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rb_am.setClickable(false);
                    rb_pm.setClickable(true);
                    rb_other.setClickable(true);
                    setTimerType(MinuteTimePickerView.Type.MORING);
                    timeType= MinuteTimePickerView.Type.MORING;
                    rb_pm.setChecked(false);
                    rb_other.setChecked(false);
                }

            }
        });
        rb_pm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rb_am.setClickable(true);
                    rb_pm.setClickable(false);
                    rb_other.setClickable(true);
                    setTimerType(MinuteTimePickerView.Type.AFTERNOON);
                    timeType= MinuteTimePickerView.Type.AFTERNOON;
                    rb_am.setChecked(false);
                    rb_other.setChecked(false);
                }

            }
        });
        rb_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rb_am.setClickable(true);
                    rb_pm.setClickable(true);
                    rb_other.setClickable(false);
                    setTimerType(MinuteTimePickerView.Type.ALL);
                    timeType= MinuteTimePickerView.Type.ALL;
                    rb_am.setChecked(false);
                    rb_pm.setChecked(false);
                }
            }
        });
        rb_other.setChecked(true);
    }

    private void initView() {
        myHandler = new MyHandler(this);
        ll_name_signature= findViewById(R.id.ll_name_signature);
        ll_name_signature.setVisibility(View.VISIBLE);
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        syxh_add_img= findViewById(R.id.syxh_add_img);
        syxh_del_img=findViewById(R.id.syxh_del_img);
        syxh_layout=findViewById(R.id.syxh_layout);
        syxhLayout=new SYXHLayout(this);
        syxh_layout.addView(syxhLayout);

        mtax_add_img= findViewById(R.id.mtax_add_img);
        mtax_del_img= findViewById(R.id.mtax_del_img);
        mtax_layout= findViewById(R.id.mtax_layout);
        mtaxLayout=new SYXHLayout(this);
        mtax_layout.addView(mtaxLayout);

        sgzy_add_img= findViewById(R.id.sgzy_add_img);
        sgzy_del_img= findViewById(R.id.sgzy_del_img);
        sgzy_layout= findViewById(R.id.sgzy_layout);
        sgzyLayout=new SGZYLayout(this);
        sgzy_layout.addView(sgzyLayout);

        qtcb_add_img= findViewById(R.id.qtcb_add_img);
        qtcb_del_img= findViewById(R.id.qtcb_del_img);
        qtcb_layout= findViewById(R.id.qtcb_layout);
        qtcbLayout=new QTCBLayout(this);
        qtcb_layout.addView(qtcbLayout);

        rb_am= findViewById(R.id.rb_am);
        rb_pm= findViewById(R.id.rb_pm);
        rb_other= findViewById(R.id.rb_other);

        tv_xhry_qm= findViewById(R.id.tv_xhry_qm);
        tv_date= findViewById(R.id.tv_date);

        iv_qm= findViewById(R.id.iv_qm);

        track_btn_ok= findViewById(R.id.track_btn_ok);

        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");

        dialog= DialogUtils.createLoadingDialog(this, "请稍后...");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.syxh_add_img:
                syxhLayout=new SYXHLayout(this);
                syxhLayout.setTimerType(timeType);
                syxh_layout.addView(syxhLayout);
                break;
            case R.id.syxh_del_img:
                if(syxh_layout.getChildCount()>1){
                    syxh_layout.removeViewAt(syxh_layout.getChildCount()-1);
                }
                break;
            case R.id.mtax_add_img:
                mtaxLayout=new SYXHLayout(this);
                mtaxLayout.setTimerType(timeType);
                mtax_layout.addView(mtaxLayout);
                break;
            case R.id.mtax_del_img:
                if(mtax_layout.getChildCount()>1){
                    mtax_layout.removeViewAt(mtax_layout.getChildCount()-1);
                }
                break;
            case R.id.sgzy_add_img:
                sgzyLayout=new SGZYLayout(this);
                sgzyLayout.setTimerType(timeType);
                sgzy_layout.addView(sgzyLayout);
                break;
            case R.id.sgzy_del_img:
                if(sgzy_layout.getChildCount()>1){
                    sgzy_layout.removeViewAt(sgzy_layout.getChildCount()-1);
                }
                break;


            case R.id.qtcb_add_img:
                qtcbLayout=new QTCBLayout(this);
                qtcbLayout.setTimerType(timeType);
                qtcb_layout.addView(qtcbLayout);
                break;
            case R.id.qtcb_del_img:
                if(qtcb_layout.getChildCount()>1){
                    qtcb_layout.removeViewAt(qtcb_layout.getChildCount()-1);
                }
                break;

            case R.id.tv_xhry_qm:
                writeLine(iv_qm,tv_xhry_qm);
                break;
            case R.id.iv_qm:
                showQMDialog(qm_path);
                break;

            case R.id.track_btn_ok:
                boolean isEmpty= judgeSubData();
                if(isEmpty){
                    ToastUtils.showToast(this,"水域巡航至少检查一项！");
                    return;
                }
                if (qm_path==null){
                    ToastUtils.showToast(this,"请点击签名");
                    return;
                }


                presenter.subWaterPatrolData(qm_path);
                break;

        }
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public WatersPatrol getWaterPatrol() {
        WatersPatrol watersPatrol=new WatersPatrol();
        info.setCreateUserAutograph("");
        info.setCruiseType("0");
        if (rb_am.isChecked()){
            info.setDayTime("0");
        }else if (rb_pm.isChecked()){
            info.setDayTime("1");
        }else {
            info.setDayTime("2");
        }

        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        Date date=new Date();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w<0){
            w=0;
        }
        info.setWeekNum(weekDays[w]);

        List<DetailArea> detailAreas=new ArrayList<>();
        //水域巡航表格

        for (int i = 0; i <syxh_layout.getChildCount(); i++) {
            SYXHLayout syxhLayout= (SYXHLayout) syxh_layout.getChildAt(i);
            SYXHLayout.SYXH syxh =syxhLayout.getData();

            if((syxh.getTime()==null||syxh.getTime().equals(""))
                    &&(syxh.getArea()==null||syxh.getArea().equals(""))
                    &&(syxh.getExap()==null||syxh.getExap().equals(""))
                    &&!syxh.isSelect()
                    ){
            }else {
                DetailArea detailArea=new DetailArea();
                String []syxh_time=syxh.getTime().split("-");
                if(syxh_time.length>1){
                    detailArea.setStartTime(syxh_time[0]);
                    detailArea.setEndTime(syxh_time[1]);
                }


                detailArea.setCruiseArea(syxh.getArea());//巡航区域
                if(syxh.isSelect()){
                    if (syxh.isNormal()){
                        detailArea.setCruiseState("1");
                    }else {
                        detailArea.setCruiseState("0");
                    }
                }else {
                    detailArea.setCruiseState("2");
                }
                detailArea.setCruiseExap(syxh.getExap());
                detailArea.setCruiseType("0");//0表示水域巡航
                detailAreas.add(detailArea);
            }
        }

        //码头、岸线检查
        for (int i = 0; i <mtax_layout.getChildCount(); i++) {
            SYXHLayout syxhLayout= (SYXHLayout) mtax_layout.getChildAt(i);
            SYXHLayout.SYXH syxh =syxhLayout.getData();

            if((syxh.getTime()==null||syxh.getTime().equals(""))
                    &&(syxh.getArea()==null||syxh.getArea().equals(""))
                    &&(syxh.getExap()==null||syxh.getExap().equals(""))
                    &&!syxh.isSelect()
                    ){
            }else {
                DetailArea detailArea=new DetailArea();
                String []syxh_time=syxh.getTime().split("-");
                if(syxh_time.length>1){
                    detailArea.setStartTime(syxh_time[0]);
                    detailArea.setEndTime(syxh_time[1]);
                }


                detailArea.setCruiseArea(syxh.getArea());//巡航区域

                if(syxh.isSelect()){
                    if (syxh.isNormal()){
                        detailArea.setCruiseState("1");
                    }else {
                        detailArea.setCruiseState("0");
                    }
                }else {
                    detailArea.setCruiseState("2");
                }

                detailArea.setCruiseExap(syxh.getExap());
                detailArea.setCruiseType("1");//1代表码头岸线
                detailAreas.add(detailArea);
            }
        }




        List<DetailShip>detailShips=new ArrayList<>();

        //水工作业船舶记录

        for (int i = 0; i <sgzy_layout.getChildCount(); i++) {
            SGZYLayout sgzyLayout= (SGZYLayout) sgzy_layout.getChildAt(i);
            SGZYLayout.SGZY sgzy =sgzyLayout.getData();
            if((sgzy.getTime()==null||sgzy.getTime().equals(""))
                    &&(sgzy.getCb_name()==null||sgzy.getCb_name().equals(""))
                    &&(sgzy.getSgzy_place()==null||sgzy.getSgzy_place().equals(""))
                    &&(sgzy.getSgzy_result()==null||sgzy.getSgzy_result().equals(""))
                    &&(sgzy.getWork_name()==null||sgzy.getWork_name().equals(""))
                    ){
            }else {
                DetailShip detailShip=new DetailShip();
                String []sgzy_time=sgzy.getTime().split("-");
                if(sgzy_time.length>1){
                    detailShip.setStartTime(sgzy_time[0]);
                    detailShip.setEndTime(sgzy_time[1]);

                }

                detailShip.setWorkName(sgzy.getWork_name());
                detailShip.setShipNameCn(sgzy.getCb_name());
                detailShip.setShipPlace(sgzy.getSgzy_place());
                detailShip.setResult(sgzy.getSgzy_result());
                detailShip.setIsWork("1");//1代表水工作业船
                detailShips.add(detailShip);
            }
        }

        //其他船舶检查记录

        for (int i = 0; i <qtcb_layout.getChildCount(); i++) {
            QTCBLayout qtcbLayout= (QTCBLayout) qtcb_layout.getChildAt(i);
            QTCBLayout.QTCB qtcb =qtcbLayout.getData();
            if((qtcb.getTime()==null||qtcb.getTime().equals(""))
                    &&(qtcb.getLx()==null||qtcb.getLx().equals(""))
                    &&(qtcb.getQtcb_place()==null||qtcb.getQtcb_place().equals(""))
                    &&(qtcb.getQtcb_cb_name()==null||qtcb.getQtcb_cb_name().equals(""))
                    &&(qtcb.getQtcb_cb_result()==null||qtcb.getQtcb_cb_result().equals(""))
                    ){
            }else {

                DetailShip detailShip=new DetailShip();
                String []sgzy_time=qtcb.getTime().split("-");
                if(sgzy_time.length>1){
                    detailShip.setStartTime(sgzy_time[0]);
                    detailShip.setEndTime(sgzy_time[1]);
                }


                detailShip.setShipTypeNameCn(qtcb.getLx());
                detailShip.setShipNameCn(qtcb.getQtcb_cb_name());
                detailShip.setShipPlace(qtcb.getQtcb_place());
                detailShip.setResult(qtcb.getQtcb_cb_result());
                detailShip.setIsWork("0");//1代表水工作业船
                detailShips.add(detailShip);
            }

        }
        watersPatrol.setInfo(info);
        watersPatrol.setDetailArea(detailAreas);
        watersPatrol.setDetailShip(detailShips);
        return watersPatrol;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(this,msg);
    }

    private String sub_msg;
    private String id;
    @Override
    public void onSuccess(String msg,String inspectNo) {
        this.sub_msg=msg;
        this.id=inspectNo;
        this.namepath="";
        dialog.dismiss();
       myHandler.sendEmptyMessage(500);

    }

    private static class MyHandler extends Handler {

        WeakReference<WatersPatrolActivity> watersPatrolActivityWeakReference;

        MyHandler(WatersPatrolActivity watersPatrolActivity) {
            this.watersPatrolActivityWeakReference = new WeakReference<>(watersPatrolActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final WatersPatrolActivity supervisionActivity = watersPatrolActivityWeakReference.get();
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg+",是否打印水域巡航表格？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        File file = new File(supervisionActivity.namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(supervisionActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",supervisionActivity.namepath);
                            supervisionActivity.startActivity(intent);
                            supervisionActivity.finish();
                        }else{
                            supervisionActivity.downloadPDF();
                            supervisionActivity.downloadDialog.show();
                            supervisionActivity.httpDownManager.startDown(supervisionActivity.apkApi);
                        }


                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        supervisionActivity.finish();
                    }
                });
            }

            if(what == 600){
                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg+",是否重新提交？", "重新提交","返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        supervisionActivity.presenter.subWaterPatrolData(supervisionActivity.qm_path);
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

        String name =  "水上巡航表格"+"_"+id+".pdf";

        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",id);
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
                Intent intent=new Intent(WatersPatrolActivity.this, Activity_PrintPdf.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
    }

    public void setTimerType(MinuteTimePickerView.Type type){
        for (int i = 0; i < mtax_layout.getChildCount(); i++) {
            SYXHLayout syxhLayout= (SYXHLayout) mtax_layout.getChildAt(i);
            syxhLayout.setTimerType(type);

        }
        for (int i = 0; i <syxh_layout.getChildCount(); i++) {
            SYXHLayout syxhLayout= (SYXHLayout) syxh_layout.getChildAt(i);
            syxhLayout.setTimerType(type);
        }
        for (int i = 0; i < qtcb_layout.getChildCount(); i++) {
            QTCBLayout qtcbLayout= (QTCBLayout) qtcb_layout.getChildAt(i);
            qtcbLayout.setTimerType(type);
        }
        for (int i = 0; i < sgzy_layout.getChildCount(); i++) {
            SGZYLayout sgzyLayout= (SGZYLayout) sgzy_layout.getChildAt(i);
            sgzyLayout.setTimerType(type);
        }
    }

    private boolean syxh_layout_is_empty=false;
    private boolean mtax_layout_is_empty=false;
    private boolean sgzy_layout_is_empty=false;
    private boolean qtcb_layout_is_empty=false;
    private boolean judgeSubData() {

        if (!(rb_am.isChecked()||rb_pm.isChecked()||rb_other.isChecked())){
            showMsg("请选择检查时间");
            return true;
        }
        for (int i = 0; i <syxh_layout.getChildCount(); i++) {
            SYXHLayout syxhLayout= (SYXHLayout) syxh_layout.getChildAt(i);
            SYXHLayout.SYXH syxh =syxhLayout.getData();
            if(syxh.getTime()==null||syxh.getTime().equals("")
                    ||syxh.getArea()==null||syxh.getArea().equals("")
                    ||syxh.getExap()==null||syxh.getExap().equals("")
                    ||!syxh.isSelect()
                    ){
                syxh_layout_is_empty=false;
            }else {
                syxh_layout_is_empty=true;
                break;
            }
        }
        for (int i = 0; i <mtax_layout.getChildCount(); i++) {
            SYXHLayout syxhLayout= (SYXHLayout) mtax_layout.getChildAt(i);
            SYXHLayout.SYXH syxh =syxhLayout.getData();
            if(syxh.getTime()==null||syxh.getTime().equals("")
                    ||syxh.getArea()==null||syxh.getArea().equals("")
                    ||syxh.getExap()==null||syxh.getExap().equals("")
                    ||!syxh.isSelect()
                    ){
                mtax_layout_is_empty=false;
            }else {
                mtax_layout_is_empty=true;
                break;
            }
        }
        for (int i = 0; i <sgzy_layout.getChildCount(); i++) {
            SGZYLayout sgzyLayout= (SGZYLayout) sgzy_layout.getChildAt(i);
            SGZYLayout.SGZY sgzy =sgzyLayout.getData();
            if(sgzy.getTime()==null||sgzy.getTime().equals("")
                    ||sgzy.getCb_name()==null||sgzy.getCb_name().equals("")
                    ||sgzy.getSgzy_place()==null||sgzy.getSgzy_place().equals("")
                    ||sgzy.getSgzy_result()==null||sgzy.getSgzy_result().equals("")
                    ||sgzy.getWork_name()==null||sgzy.getWork_name().equals("")
                    ){
                sgzy_layout_is_empty=false;
            }else {
                sgzy_layout_is_empty=true;
                break;
            }
        }
        for (int i = 0; i <qtcb_layout.getChildCount(); i++) {
            QTCBLayout qtcbLayout= (QTCBLayout) qtcb_layout.getChildAt(i);
            QTCBLayout.QTCB qtcb =qtcbLayout.getData();
            if(qtcb.getTime()==null||qtcb.getTime().equals("")
                    ||qtcb.getLx()==null||qtcb.getLx().equals("")
                    ||qtcb.getQtcb_place()==null||qtcb.getQtcb_place().equals("")
                    ||qtcb.getQtcb_cb_name()==null||qtcb.getQtcb_cb_name().equals("")
                    ||qtcb.getQtcb_cb_result()==null||qtcb.getQtcb_cb_result().equals("")
                    ){
                qtcb_layout_is_empty=false;
            }else {
                qtcb_layout_is_empty=true;
                break;
            }
        }

        if(!syxh_layout_is_empty&&!mtax_layout_is_empty&&!sgzy_layout_is_empty&&!qtcb_layout_is_empty){
            return true;
        }


        return false;
    }

    private int width;
    // 距离两边的像素
    private int magin;
    private View qmView;// 签名所承载的view
    private Dialog qm_dialog;
    private ImageView dialog_record_pen_iv;
    private Button dialog_record_ok;
    private Button dialog_record_reautograph;
    @SuppressLint("InflateParams")
    private void showQMDialog(String path) {
        width = DeviceUtils.getScreenSize(this)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(this, 10);

        qm_dialog = new AlertDialog.Builder(this).create();
        qm_dialog.setCancelable(true);
        qm_dialog.setCanceledOnTouchOutside(true);
        qmView = LayoutInflater.from(this).inflate(R.layout.dialog_qianming, null);
        qm_dialog.show();
        qm_dialog.setContentView(qmView);

        Window window = qm_dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        assert window != null;
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        dialog_record_pen_iv = qmView.findViewById(R.id.dialog_record_pen_iv);
        Uri uri = Uri.fromFile(new File(path));
        dialog_record_pen_iv.setImageURI(uri);

        dialog_record_ok = qmView.findViewById(R.id.dialog_record_ok);
        dialog_record_reautograph = qmView.findViewById(R.id.dialog_record_reautograph);

        dialog_record_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
            }
        });
        dialog_record_reautograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLine(iv_qm,tv_xhry_qm);
                qm_dialog.dismiss();
            }
        });
    }


    /**
     * 签名
     */
    private void writeLine(final ImageView imageView, final TextView textView){
        ScrollView ll_sc = findViewById(R.id.ll_sc);
        SignatureView view1 = new SignatureView(this,ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                    qm_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate()+"qm.png";
                    try {
                        linePathView.save(qm_path,false,10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BitmapUtils.getImageThumbnail(qm_path, 80, 80);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(qm_path, options);
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    info.setWidth(new BigDecimal(bm.getWidth()));
                    info.setHeight(new BigDecimal(bm.getHeight()));
            }
        });
    }

}
