package com.winfo.szrsp.app.mvp.table.pscb.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.psca.view.PSCFormAActivity;
import com.winfo.szrsp.app.mvp.table.pscb.presenter.PSCFormBPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFrom;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromB;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObject;
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
import com.winfo.szrsp.app.widget.LinePathView;
import com.winfo.szrsp.app.widget.SignatureView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wly on 2017/12/19.
 *
 */

public class PSCFormBActivity extends Activity implements View.OnClickListener, IPSCFormBActivity {

    private ImageButton imgbtnBack;

    private TextView tvTitleText;

    private ImageView qm_img;

    private Button btn_submit;

    private TextView et_name_of_ship;
    private TextView et_imo_number;
    private TextView et_date_of_inspection;
    private TextView et_place_of_inspection;

    private TextView et_name;

    private TableLayout tl_psc_b;

    private ImageView iv_add_table_row;

    private ImageView iv_delete_table_row;


    private String qm_path="";
    private String qm_path2="";

    private CtPscFromObject ctPscFromObject;

    private PSCFormBPresenter presenter;

    private Dialog dialog;

    private Dialog downloadDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psc_b);

        initView();

        initData();

        initEvent();

    }

    private void initData() {


        tvTitleText.setText("Execution Form");
        Intent intent=getIntent();
        ctPscFromObject= (CtPscFromObject) intent.getSerializableExtra("CtPscFromObject");
        qm_path= (String) intent.getSerializableExtra("qm_path");
        qm_path2= (String) intent.getSerializableExtra("qm_path2");

        BitmapUtils.getImageThumbnail(qm_path2, 80, 80);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(qm_path2, options);
        qm_img.setImageBitmap(bm);

        if (ctPscFromObject!=null){
            CtPscFrom ctPscFrom= ctPscFromObject.getInfo();
            et_name_of_ship.setText(ctPscFrom.getShipNameEn());
            et_imo_number.setText(ctPscFrom.getShipImo());
            et_date_of_inspection.setText(ctPscFrom.getInspectDate());
            et_place_of_inspection.setText(ctPscFrom.getBerthCode());
            et_name.setText(ctPscFrom.getCaptainName());

            List<CtPscFromB> detailb =ctPscFromObject.getDetailb();
            if (detailb!=null){
                for (int i=0;i<detailb.size();i++){
                    CtPscFromB ctPscFromB=detailb.get(i);
                    addTableRow(ctPscFromB);
                }
            }else {
                addTableRow(null);
            }


        }

    }

    private void initEvent() {
        imgbtnBack.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        iv_add_table_row.setOnClickListener(this);
        iv_delete_table_row.setOnClickListener(this);

    }

    private void initView() {

        tl_psc_b=findViewById(R.id.tl_psc_b);

        iv_add_table_row=findViewById(R.id.iv_add_table_row);
        iv_delete_table_row=findViewById(R.id.iv_delete_table_row);

        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");

        presenter=new PSCFormBPresenter(this);

        dialog= DialogUtils.createLoadingDialog(this, "请稍后...");



        imgbtnBack=findViewById(R.id.table_titleBar_imgbtn_back);
        tvTitleText=findViewById(R.id.table_titleBar_titleText);
        qm_img=findViewById(R.id.qm_img);

        et_name_of_ship=findViewById(R.id.et_name_of_ship);
        et_imo_number=findViewById(R.id.et_imo_number);
        et_date_of_inspection=findViewById(R.id.et_date_of_inspection);
        et_place_of_inspection=findViewById(R.id.et_place_of_inspection);
        et_name=findViewById(R.id.et_name);


        btn_submit=findViewById(R.id.btn_submit);

    }

    private void addTableRow(CtPscFromB ctPscFromB) {

        TableRow tableRow=new TableRow(this);
        tableRow.setLayoutParams(new LinearLayout.LayoutParams(android.widget.TableRow.LayoutParams.MATCH_PARENT, android.widget.TableRow.LayoutParams.MATCH_PARENT));

        int height=DimensUtils.dp2px(this, 40);
        int margin=DimensUtils.dp2px(this, 0.5f);
        int padding=DimensUtils.dp2px(this, 2);

        EditText editText=new EditText(this);
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        android.widget.TableRow.LayoutParams params=new android.widget.TableRow.LayoutParams(0, height);
        params.weight=1;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params.setMargins(margin,margin,margin,margin);
        editText.setLayoutParams(params);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        tableRow.addView(editText);

        android.widget.TableRow.LayoutParams params2=new android.widget.TableRow.LayoutParams(0, height);
        EditText editText2=new EditText(this);
        editText2.setSingleLine();
        editText2.setBackgroundColor(Color.WHITE);
        params2.weight=1;
        editText2.setGravity(Gravity.CENTER_VERTICAL);
        params2.setMargins(0,margin,margin,margin);
        editText2.setLayoutParams(params2);
        editText2.setPadding(padding,padding,padding,padding);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText2.setTextSize(13);
        editText2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2){
        }});
        tableRow.addView(editText2);

        android.widget.TableRow.LayoutParams params3=new android.widget.TableRow.LayoutParams(0, height);

        EditText editText3=new EditText(this);
        editText3.setSingleLine();
        editText3.setBackgroundColor(Color.WHITE);
        params3.weight=2;
        editText3.setGravity(Gravity.CENTER_VERTICAL);
        params3.setMargins(0,margin,margin,margin);
        editText3.setLayoutParams(params3);
        editText3.setPadding(padding,padding,padding,padding);
        editText3.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText3.setTextSize(13);
        tableRow.addView(editText3);

        EditText editText4=new EditText(this);
        editText4.setSingleLine();
        editText4.setBackgroundColor(Color.WHITE);
        params3.weight=2;
        editText4.setGravity(Gravity.CENTER_VERTICAL);
        params3.setMargins(0,margin,margin,margin);
        editText4.setLayoutParams(params3);
        editText4.setPadding(padding,padding,padding,padding);
        editText4.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText4.setTextSize(13);
        tableRow.addView(editText4);

        android.widget.TableRow.LayoutParams params4=new android.widget.TableRow.LayoutParams(0, height);
        EditText editText5=new EditText(this);
        editText5.setSingleLine();
        editText5.setBackgroundColor(Color.WHITE);
        params.weight=1;
        editText5.setGravity(Gravity.CENTER_VERTICAL);
        params4.setMargins(0,margin,margin,margin);
        editText5.setLayoutParams(params4);
        editText5.setPadding(padding,padding,padding,padding);
        editText5.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText5.setTextSize(13);
        tableRow.addView(editText5);

        EditText editText6=new EditText(this);
        editText6.setSingleLine();
        editText6.setBackgroundColor(Color.WHITE);
        params4.weight=1;
        editText6.setGravity(Gravity.CENTER_VERTICAL);
        params4.setMargins(0,margin,margin,margin);
        editText6.setLayoutParams(params4);
        editText6.setPadding(padding,padding,padding,padding);
        editText6.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText6.setTextSize(13);
        tableRow.addView(editText6);

        if (ctPscFromB!=null){
            editText2.setText(ctPscFromB.getDeficiencyCode());//21
            editText3.setText(ctPscFromB.getDeficiencyNature());//4
            editText4.setText(ctPscFromB.getConcenyionReference());//5
            editText5.setText(ctPscFromB.getIsResponsible());//23
            editText6.setText(ctPscFromB.getTakenAction());//22
        }
        tl_psc_b.addView(tableRow);
        if (tl_psc_b.getChildCount()>2){
            iv_delete_table_row.setVisibility(View.VISIBLE);
        }else {
            iv_delete_table_row.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.table_titleBar_imgbtn_back:
                Intent intent=new Intent(this, PSCFormAActivity.class);
                intent.putExtra("CtPscFromObject",getCtPscFromObject());
                setResult(300,intent);
                finish();
                break;


            case R.id.iv_add_table_row:
                addTableRow(null);
                break;

            case R.id.iv_delete_table_row:
                deleteTableRow();
                break;


            case R.id.btn_submit:

                for (int i=1;i<tl_psc_b.getChildCount();i++){
                    TableRow tableRow= (TableRow) tl_psc_b.getChildAt(i);
                    TextView editText= (TextView) tableRow.getChildAt(1);
                    if ("".equals(editText.getText().toString().trim())){
                        ToastUtils.showToast(this,"请将表格填写完整");
                        return;
                    }
                }

                presenter.subPSCFormBData( qm_path ,qm_path2);
                break;


        }
    }

    private void deleteTableRow() {
        int index=tl_psc_b.getChildCount()-1;
        tl_psc_b.removeViewAt(index);
        if (tl_psc_b.getChildCount()>2){
            iv_delete_table_row.setVisibility(View.VISIBLE);
        }else {
            iv_delete_table_row.setVisibility(View.GONE);
        }
    }



    @Override
    public CtPscFromObject getCtPscFromObject() {

        List<CtPscFromB> detailb=new ArrayList<>();
        CtPscFromB ctPscFromB;
        for (int i=1;i<tl_psc_b.getChildCount();i++){
            TableRow tableRow= (TableRow) tl_psc_b.getChildAt(i);
            ctPscFromB=new CtPscFromB();
            ctPscFromB.setDeficiencyCode(((TextView )tableRow.getChildAt(1)).getText().toString().trim());//21
            ctPscFromB.setDeficiencyNature(((TextView )tableRow.getChildAt(2)).getText().toString().trim());//4
            ctPscFromB.setConcenyionReference(((TextView )tableRow.getChildAt(3)).getText().toString().trim());//5
            ctPscFromB.setIsResponsible(((TextView )tableRow.getChildAt(4)).getText().toString().trim());//23
            ctPscFromB.setTakenAction(((TextView )tableRow.getChildAt(5)).getText().toString().trim());//22
            detailb.add(ctPscFromB);


        }

        ctPscFromObject.setDetailb(detailb);
        return ctPscFromObject;
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    private String sub_msg;
    private String id;
    @Override
    public void onSuccess(String msg, String inspectNo) {
        this.sub_msg=msg;
        this.id=inspectNo;
        this.namepath="";
        dialog.dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(500);
            }
        }).start();
    }

    @Override
    public void onFailure(String msg) {
        this.sub_msg=msg;
        dialog.dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(600);
            }
        }).start();
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(PSCFormBActivity.this, "温馨提示", sub_msg+",是否打印水域巡航表格？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        File file = new File(namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(PSCFormBActivity.this, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",namepath);
                            startActivity(intent);
                            finish();
                        }else{
                            downloadPDF();
                            downloadDialog.show();
                            httpDownManager.startDown(apkApi);
                        }


                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
//                        dialog.dismiss();
                        finish();
                    }
                });
            }

            if(what == 600){

                DialogUtils.showDialog(PSCFormBActivity.this, "温馨提示", sub_msg+",是否重新提交？", "重新提交","返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        presenter.subPSCFormBData(qm_path,qm_path2);

                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();

                    }

                });
            }
        }
    };

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    private String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    private String namepath = "";
    private DownInfo apkApi;

    private void downloadPDF(){

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name =  "PSCForm"+"_"+id+".pdf";

        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctPscFromRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
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
                Intent intent=new Intent(PSCFormBActivity.this, Activity_PrintPdf.class);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent=new Intent(this, PSCFormAActivity.class);
            intent.putExtra("CtPscFromObject",getCtPscFromObject());
            setResult(300,intent);
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

}
