package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.presenter.CBWFXCPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspect;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspectDetail;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2018-01-27.
 */

public class CBWFXCJDActivity extends Activity implements View.OnClickListener, ICBWFXCView {
    @BindView(R.id.add_img)
    ImageView add_img;
    @BindView(R.id.del_img)
    ImageView del_img;
    @BindView(R.id.cbwfxcjd_tl_qtfxwt)
    LinearLayout cbwfxcjd_tl_qtfxwt;

    @BindView(R.id.cbwfxcjd_et_zwcm)
    EditText cbwfxcjd_et_zwcm;
    @BindView(R.id.cbwfxcjd_et_imo)
    EditText cbwfxcjd_et_imo;
    @BindView(R.id.cbwfxcjd_et_gj)
    EditText cbwfxcjd_et_gj;
    @BindView(R.id.cbwfxcjd_et_zd)
    EditText cbwfxcjd_et_zd;
    @BindView(R.id.cbwfxcjd_et_place)
    EditText cbwfxcjd_et_place;
    @BindView(R.id.cbwfxcjd_et_zydw)
    EditText cbwfxcjd_et_zydw;
    @BindView(R.id.cbwfxcjd_et_zybw)
    EditText cbwfxcjd_et_zybw;
    @BindView(R.id.cbwfxcjd_et_zhg)
    EditText cbwfxcjd_et_zhg;
    @BindView(R.id.cbwfxcjd_et_xhg)
    EditText cbwfxcjd_et_xhg;
    @BindView(R.id.cbwfxcjd_et_jcry)
    EditText cbwfxcjd_et_jcry;
    @BindView(R.id.cbwfxcjd_sp_zylb)
    Spinner cbwfxcjd_sp_zylb;
    @BindView(R.id.dxchcjcb_tv_time)
    TextView dxchcjcb_tv_time;

    @BindView(R.id.cbwfxcjd_tv_clyj_1_1)
    TextView cbwfxcjd_tv_clyj_1_1;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_2)
    TextView cbwfxcjd_tv_clyj_1_2;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_3)
    TextView cbwfxcjd_tv_clyj_1_3;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_4)
    TextView cbwfxcjd_tv_clyj_1_4;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_5)
    TextView cbwfxcjd_tv_clyj_1_5;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_6)
    TextView cbwfxcjd_tv_clyj_1_6;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_7)
    TextView cbwfxcjd_tv_clyj_1_7;

    @BindView(R.id.cbwfxcjd_tv_clyj_2_1)
    TextView cbwfxcjd_tv_clyj_2_1;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_2)
    TextView cbwfxcjd_tv_clyj_2_2;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_3)
    TextView cbwfxcjd_tv_clyj_2_3;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_4)
    TextView cbwfxcjd_tv_clyj_2_4;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_5)
    TextView cbwfxcjd_tv_clyj_2_5;

    @BindView(R.id.cbwfxcjd_tv_clyj_3_1)
    TextView cbwfxcjd_tv_clyj_3_1;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_2)
    TextView cbwfxcjd_tv_clyj_3_2;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_3)
    TextView cbwfxcjd_tv_clyj_3_3;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_4)
    TextView cbwfxcjd_tv_clyj_3_4;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_5)
    TextView cbwfxcjd_tv_clyj_3_5;

    @BindView(R.id.cbwfxcjd_et_qxms_1_1)
    EditText cbwfxcjd_et_qxms_1_1;
    @BindView(R.id.cbwfxcjd_et_qxms_1_2)
    EditText cbwfxcjd_et_qxms_1_2;
    @BindView(R.id.cbwfxcjd_et_qxms_1_3)
    EditText cbwfxcjd_et_qxms_1_3;
    @BindView(R.id.cbwfxcjd_et_qxms_1_4)
    EditText cbwfxcjd_et_qxms_1_4;
    @BindView(R.id.cbwfxcjd_et_qxms_1_5)
    EditText cbwfxcjd_et_qxms_1_5;
    @BindView(R.id.cbwfxcjd_et_qxms_1_6)
    EditText cbwfxcjd_et_qxms_1_6;
    @BindView(R.id.cbwfxcjd_et_qxms_1_7)
    EditText cbwfxcjd_et_qxms_1_7;

    @BindView(R.id.cbwfxcjd_et_qxms_2_1)
    EditText cbwfxcjd_et_qxms_2_1;
    @BindView(R.id.cbwfxcjd_et_qxms_2_2)
    EditText cbwfxcjd_et_qxms_2_2;
    @BindView(R.id.cbwfxcjd_et_qxms_2_3)
    EditText cbwfxcjd_et_qxms_2_3;
    @BindView(R.id.cbwfxcjd_et_qxms_2_4)
    EditText cbwfxcjd_et_qxms_2_4;
    @BindView(R.id.cbwfxcjd_et_qxms_2_5)
    EditText cbwfxcjd_et_qxms_2_5;

    @BindView(R.id.cbwfxcjd_et_qxms_3_1)
    EditText cbwfxcjd_et_qxms_3_1;
    @BindView(R.id.cbwfxcjd_et_qxms_3_2)
    EditText cbwfxcjd_et_qxms_3_2;
    @BindView(R.id.cbwfxcjd_et_qxms_3_3)
    EditText cbwfxcjd_et_qxms_3_3;
    @BindView(R.id.cbwfxcjd_et_qxms_3_4)
    EditText cbwfxcjd_et_qxms_3_4;
    @BindView(R.id.cbwfxcjd_et_qxms_3_5)
    EditText cbwfxcjd_et_qxms_3_5;

    @BindView(R.id.cbwfxcjd_et_zgjg_1_1)
    EditText cbwfxcjd_et_zgjg_1_1;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_2)
    EditText cbwfxcjd_et_zgjg_1_2;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_3)
    EditText cbwfxcjd_et_zgjg_1_3;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_4)
    EditText cbwfxcjd_et_zgjg_1_4;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_5)
    EditText cbwfxcjd_et_zgjg_1_5;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_6)
    EditText cbwfxcjd_et_zgjg_1_6;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_7)
    EditText cbwfxcjd_et_zgjg_1_7;

    @BindView(R.id.cbwfxcjd_et_zgjg_2_1)
    EditText cbwfxcjd_et_zgjg_2_1;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_2)
    EditText cbwfxcjd_et_zgjg_2_2;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_3)
    EditText cbwfxcjd_et_zgjg_2_3;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_4)
    EditText cbwfxcjd_et_zgjg_2_4;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_5)
    EditText cbwfxcjd_et_zgjg_2_5;

    @BindView(R.id.cbwfxcjd_et_zgjg_3_1)
    EditText cbwfxcjd_et_zgjg_3_1;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_2)
    EditText cbwfxcjd_et_zgjg_3_2;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_3)
    EditText cbwfxcjd_et_zgjg_3_3;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_4)
    EditText cbwfxcjd_et_zgjg_3_4;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_5)
    EditText cbwfxcjd_et_zgjg_3_5;

    @BindView(R.id.cbwfxc_sub)
    Button cbwfxc_sub;

    @BindView(R.id.title)
    View title;
    private ImageButton back_btn;
    private TextView tv_title;

    private CBWFQTFXLayout cbwfqtfxLayout;
    private List<String> sp_data;
    private String zylb="";
    int number=1;
    private TimePickerView timePickerView;
    private WFXCChuliDialog wfxcChuliDialog;
    private CBWFXCPresenter cbwfxcPresenter;
    private Dialog dialog;
    private Dialog downloadDialog;
    private MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbwfxcjd);
        ButterKnife.bind(this);
        myHandler = new MyHandler(this);
        cbwfqtfxLayout = new CBWFQTFXLayout(this,"4."+number);
        cbwfxcjd_tl_qtfxwt.addView(cbwfqtfxLayout);

        title.setVisibility(View.VISIBLE);
        back_btn = title.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = title.findViewById(R.id.table_titleBar_titleText);
        initData();
        initEvent();
    }
    private void initData() {
        tv_title.setText("船舶危防现场监督检查记录表");
        sp_data=new ArrayList<>();
        sp_data.add("---请选择---");
        sp_data.add("WF01:船舶残油、油污水排放现场监督检查");
        sp_data.add("WF02:船舶洗舱水排放现场监督检查");
        sp_data.add("WF03:船舶压载水排放现场监督检查");
        sp_data.add("WF04:船舶生活污水处置现场监督检查");
        sp_data.add("WF05 :船舶垃圾排放现场监督检查");
        sp_data.add("WF06:船舶清舱作业现场监督检查");
        sp_data.add("WF07:化学品船洗舱作业现场监督检查");
        sp_data.add("WF08:船舶原油洗舱作业现场监督检查");
        sp_data.add("WF09:船舶驱气作业现场监督检查");
        sp_data.add("WF10:液货船装卸作业现场监督检查");
        sp_data.add("WF11:液货船水上过驳作业现场监督检查");
        sp_data.add("WF12:船舶载运固体散装货物作业现场监督检查");
        sp_data.add("WF13:客滚船（汽渡）载运危险货物现场监督检查");
        sp_data.add("WF14:船舶修、造作业现场监督检查");
        sp_data.add("WF15:沉船打捞现场防污染监督检查");
        sp_data.add("WF16:沿海港口船舶舷外拷铲、油漆作业现场监督检查");
        sp_data.add("WF17:船舶冲洗沾有污染物、有毒有害物质的甲板作业现场监督检查");
        sp_data.add("WF18:船舶铅封现场监督检查、船舶铅封现场监督检查");
        sp_data.add("WF19:船舶供、受油作业现场监督检查");
        sp_data.add("WF20:船舶燃油质量监督检查");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SzRspApplication.getContext(),R.layout.spinner_item_1,sp_data);
        adapter.setDropDownViewResource(R.layout.dropdown_style_1);
        cbwfxcjd_sp_zylb.setAdapter(adapter);
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        dxchcjcb_tv_time.setText(time);
        cbwfxcPresenter=new CBWFXCPresenter(this);
        dialog= DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");
        cbwfxcjd_et_jcry.setText(ACache.get(this).getAsString("userName"));
    }
    private void initEvent() {
        cbwfxcjd_sp_zylb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    zylb="";
                }else {
                    zylb = sp_data.get(i).substring(0,4);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        add_img.setOnClickListener(this);
        del_img.setOnClickListener(this);
        dxchcjcb_tv_time.setOnClickListener(this);

        cbwfxcjd_tv_clyj_1_1.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_2.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_3.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_4.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_5.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_6.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_7.setOnClickListener(this);

        cbwfxcjd_tv_clyj_2_1.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_2.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_3.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_4.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_5.setOnClickListener(this);

        cbwfxcjd_tv_clyj_3_1.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_2.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_3.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_4.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_5.setOnClickListener(this);

        back_btn.setOnClickListener(this);
        cbwfxc_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.add_img:
                number+=1;
                cbwfqtfxLayout=new CBWFQTFXLayout(this,"4."+number);
                cbwfxcjd_tl_qtfxwt.addView(cbwfqtfxLayout);
                break;
            case R.id.del_img:
                if(cbwfxcjd_tl_qtfxwt.getChildCount()>1){
                    cbwfxcjd_tl_qtfxwt.removeViewAt(cbwfxcjd_tl_qtfxwt.getChildCount()-1);
                    number-=1;
                }
                break;
            case R.id.dxchcjcb_tv_time:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        dxchcjcb_tv_time.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.cbwfxcjd_tv_clyj_1_1:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_1);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_2:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_2);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_3:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_3);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_4:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_4);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_5:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_5);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_6:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_6);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_7:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_7);
                break;

            case R.id.cbwfxcjd_tv_clyj_2_1:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_1);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_2:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_2);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_3:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_3);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_4:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_4);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_5:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_5);
                break;


            case R.id.cbwfxcjd_tv_clyj_3_1:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_1);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_2:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_2);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_3:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_3);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_4:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_4);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_5:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_5);
                break;
            case R.id.cbwfxc_sub:
                if(cbwfxcjd_et_zwcm.getText().toString().trim().equals("")
                        ||cbwfxcjd_et_imo.getText().toString().trim().equals("")){
                    ToastUtils.showToast(CBWFXCJDActivity.this,"中英文船名或IMO不能空！");
                    return;
                }
                if(cbwfxcjd_et_gj.getText().toString().trim().equals("")){
                    ToastUtils.showToast(CBWFXCJDActivity.this,"国籍/船籍港不能空！");
                    return;
                }
                if(cbwfxcjd_et_jcry.getText().toString().trim().equals("")){
                    ToastUtils.showToast(CBWFXCJDActivity.this,"检察人员不能空！");
                    return;
                }
                if(cbwfxcjd_et_place.getText().toString().trim().equals("")){
                    ToastUtils.showToast(CBWFXCJDActivity.this,"检察地点不能空！");
                    return;
                }

                cbwfxcPresenter.subData();

                break;
        }
    }
    private void showWFXCChuliDialog(final TextView textView) {
        if (wfxcChuliDialog==null){
            wfxcChuliDialog=new WFXCChuliDialog(this);
        }

        wfxcChuliDialog.show(textView.getText().toString());
        wfxcChuliDialog.setOnData(new WFXCChuliDialog.OnGetData() {
            @Override
            public void onDataCallBack(String chuli_1) {
                textView.setText(chuli_1);
            }
        });
    }

    @Override
    public CtSafeInspectNoticeObject getCtSafeInspectNoticeObject() {
        CtSafeInspectNoticeObject ctSafeInspectNoticeObject=new CtSafeInspectNoticeObject();
        //基本信息
        CtSafetySceneInspect ctSafetySceneInspect=new CtSafetySceneInspect();
        ctSafetySceneInspect.setShipNameCn(cbwfxcjd_et_zwcm.getText().toString());
        ctSafetySceneInspect.setShipNameEn(cbwfxcjd_et_imo.getText().toString());
        ctSafetySceneInspect.setRegportName(cbwfxcjd_et_gj.getText().toString());
        ctSafetySceneInspect.setShipGrosston(cbwfxcjd_et_zd.getText().toString());
        ctSafetySceneInspect.setBerthCode(cbwfxcjd_et_place.getText().toString());
        ctSafetySceneInspect.setInspectDate(dxchcjcb_tv_time.getText().toString());
        ctSafetySceneInspect.setTaskOrg(cbwfxcjd_et_zydw.getText().toString());
        ctSafetySceneInspect.setTaskTypeName(zylb);
        ctSafetySceneInspect.setTaskPort(cbwfxcjd_et_zybw.getText().toString());
        ctSafetySceneInspect.setLastPort(cbwfxcjd_et_zhg.getText().toString());
        ctSafetySceneInspect.setNextPort(cbwfxcjd_et_xhg.getText().toString());
        ctSafetySceneInspect.setInspectorName(cbwfxcjd_et_jcry.getText().toString());
        ctSafeInspectNoticeObject.setInfo(ctSafetySceneInspect);
        //  表格数据
        List<CtSafetySceneInspectDetail> list=new ArrayList<>();

        CtSafetySceneInspectDetail ct_1_0=new CtSafetySceneInspectDetail();
        ct_1_0.setSex("1.0");
        ct_1_0.setItemNum("1");
        ct_1_0.setItemName("船舶证书、文书、资料");
        ct_1_0.setDescribe("");
        ct_1_0.setHandle("");
        ct_1_0.setRectificat("");
        list.add(ct_1_0);

        CtSafetySceneInspectDetail ct_1_1=new CtSafetySceneInspectDetail();
        ct_1_1.setSex("1.1");
        ct_1_1.setItemNum("1.1");
        ct_1_1.setItemName("船舶油污应急计划（船上海洋污染应急计划）");
        if(cbwfxcjd_et_qxms_1_1.getText().toString().equals("")){
            ct_1_1.setDescribe("00");
        }else {
            ct_1_1.setDescribe(cbwfxcjd_et_qxms_1_1.getText().toString());
        }
        ct_1_1.setHandle(cbwfxcjd_tv_clyj_1_1.getText().toString());
        ct_1_1.setRectificat(cbwfxcjd_et_zgjg_1_1.getText().toString());
        list.add(ct_1_1);


        CtSafetySceneInspectDetail ct_1_2=new CtSafetySceneInspectDetail();
        ct_1_2.setSex("1.2");
        ct_1_2.setItemNum("1.2");
        ct_1_2.setItemName("船舶垃圾管理计划");
        if(cbwfxcjd_et_qxms_1_2.getText().toString().equals("")){
            ct_1_2.setDescribe("00");
        }else {
            ct_1_2.setDescribe(cbwfxcjd_et_qxms_1_2.getText().toString());
        }
        ct_1_2.setHandle(cbwfxcjd_tv_clyj_1_2.getText().toString());
        ct_1_2.setRectificat(cbwfxcjd_et_zgjg_1_2.getText().toString());
        list.add(ct_1_2);

        CtSafetySceneInspectDetail ct_1_3=new CtSafetySceneInspectDetail();
        ct_1_3.setSex("1.3");
        ct_1_3.setItemNum("1.3");
        ct_1_3.setItemName("船舶油类记录簿");
        if(cbwfxcjd_et_qxms_1_3.getText().toString().equals("")){
            ct_1_3.setDescribe("00");
        }else {
            ct_1_3.setDescribe(cbwfxcjd_et_qxms_1_3.getText().toString());
        }
        ct_1_3.setHandle(cbwfxcjd_tv_clyj_1_3.getText().toString());
        ct_1_3.setRectificat(cbwfxcjd_et_zgjg_1_3.getText().toString());
        list.add(ct_1_3);


        CtSafetySceneInspectDetail ct_1_4=new CtSafetySceneInspectDetail();
        ct_1_4.setSex("1.4");
        ct_1_4.setItemNum("1.4");
        ct_1_4.setItemName("船舶垃圾记录簿");
        if(cbwfxcjd_et_qxms_1_4.getText().toString().equals("")){
            ct_1_4.setDescribe("00");
        }else {
            ct_1_4.setDescribe(cbwfxcjd_et_qxms_1_4.getText().toString());
        }
        ct_1_4.setHandle(cbwfxcjd_tv_clyj_1_4.getText().toString());
        ct_1_4.setRectificat(cbwfxcjd_et_zgjg_1_4.getText().toString());
        list.add(ct_1_4);


        CtSafetySceneInspectDetail ct_1_5=new CtSafetySceneInspectDetail();
        ct_1_5.setSex("1.5");
        ct_1_5.setItemNum("1.5");
        ct_1_5.setItemName("船舶燃油质量报告单");
        if(cbwfxcjd_et_qxms_1_5.getText().toString().equals("")){
            ct_1_5.setDescribe("00");
        }else {
            ct_1_5.setDescribe(cbwfxcjd_et_qxms_1_5.getText().toString());
        }
        ct_1_5.setHandle(cbwfxcjd_tv_clyj_1_5.getText().toString());
        ct_1_5.setRectificat(cbwfxcjd_et_zgjg_1_5.getText().toString());
        list.add(ct_1_5);

        CtSafetySceneInspectDetail ct_1_6=new CtSafetySceneInspectDetail();
        ct_1_6.setSex("1.6");
        ct_1_6.setItemNum("1.6");
        ct_1_6.setItemName("作业审批单是否已经过审批或报备");
        if(cbwfxcjd_et_qxms_1_6.getText().toString().equals("")){
            ct_1_6.setDescribe("00");
        }else {
            ct_1_6.setDescribe(cbwfxcjd_et_qxms_1_6.getText().toString());
        }
        ct_1_6.setHandle(cbwfxcjd_tv_clyj_1_6.getText().toString());
        ct_1_6.setRectificat(cbwfxcjd_et_zgjg_1_6.getText().toString());
        list.add(ct_1_6);

        CtSafetySceneInspectDetail ct_1_7=new CtSafetySceneInspectDetail();
        ct_1_7.setSex("1.7");
        ct_1_7.setItemNum("1.7");
        ct_1_7.setItemName("其他船舶证书、文书、资料及记录");
        if(cbwfxcjd_et_qxms_1_7.getText().toString().equals("")){
            ct_1_7.setDescribe("00");
        }else {
            ct_1_7.setDescribe(cbwfxcjd_et_qxms_1_7.getText().toString());
        }
        ct_1_7.setHandle(cbwfxcjd_tv_clyj_1_7.getText().toString());
        ct_1_7.setRectificat(cbwfxcjd_et_zgjg_1_7.getText().toString());
        list.add(ct_1_7);


        CtSafetySceneInspectDetail ct_2_0=new CtSafetySceneInspectDetail();
        ct_2_0.setSex("2.0");
        ct_2_0.setItemNum("2");
        ct_2_0.setItemName("设备设施检查");
        ct_2_0.setDescribe("");
        ct_2_0.setHandle("");
        ct_2_0.setRectificat("");
        list.add(ct_2_0);

        CtSafetySceneInspectDetail ct_2_1=new CtSafetySceneInspectDetail();
        ct_2_1.setSex("2.1");
        ct_2_1.setItemNum("2.1");
        ct_2_1.setItemName("油水分离器");
        if(cbwfxcjd_et_qxms_2_1.getText().toString().equals("")){
            ct_2_1.setDescribe("00");
        }else {
            ct_2_1.setDescribe(cbwfxcjd_et_qxms_2_1.getText().toString());
        }
        ct_2_1.setHandle(cbwfxcjd_tv_clyj_2_1.getText().toString());
        ct_2_1.setRectificat(cbwfxcjd_et_zgjg_2_1.getText().toString());
        list.add(ct_2_1);


        CtSafetySceneInspectDetail ct_2_2=new CtSafetySceneInspectDetail();
        ct_2_2.setSex("2.2");
        ct_2_2.setItemNum("2.2");
        ct_2_2.setItemName("渣油的储存、排放与焚烧");
        if(cbwfxcjd_et_qxms_2_2.getText().toString().equals("")){
            ct_2_2.setDescribe("00");
        }else {
            ct_2_2.setDescribe(cbwfxcjd_et_qxms_2_2.getText().toString());
        }
        ct_2_2.setHandle(cbwfxcjd_tv_clyj_2_2.getText().toString());
        ct_2_2.setRectificat(cbwfxcjd_et_zgjg_2_2.getText().toString());
        list.add(ct_2_2);

        CtSafetySceneInspectDetail ct_2_3=new CtSafetySceneInspectDetail();
        ct_2_3.setSex("2.3");
        ct_2_3.setItemNum("2.3");
        ct_2_3.setItemName("机舱舱底水排放管系及有关泵");
        if(cbwfxcjd_et_qxms_2_3.getText().toString().equals("")){
            ct_2_3.setDescribe("00");
        }else {
            ct_2_3.setDescribe(cbwfxcjd_et_qxms_2_3.getText().toString());
        }
        ct_2_3.setHandle(cbwfxcjd_tv_clyj_2_3.getText().toString());
        ct_2_3.setRectificat(cbwfxcjd_et_zgjg_2_3.getText().toString());
        list.add(ct_2_3);


        CtSafetySceneInspectDetail ct_2_4=new CtSafetySceneInspectDetail();
        ct_2_4.setSex("2.4");
        ct_2_4.setItemNum("2.4");
        ct_2_4.setItemName("焚烧炉");
        if(cbwfxcjd_et_qxms_2_4.getText().toString().equals("")){
            ct_2_4.setDescribe("00");
        }else {
            ct_2_4.setDescribe(cbwfxcjd_et_qxms_2_4.getText().toString());
        }
        ct_2_4.setHandle(cbwfxcjd_tv_clyj_2_4.getText().toString());
        ct_2_4.setRectificat(cbwfxcjd_et_zgjg_2_4.getText().toString());
        list.add(ct_2_4);


        CtSafetySceneInspectDetail ct_2_5=new CtSafetySceneInspectDetail();
        ct_2_5.setSex("2.5");
        ct_2_5.setItemNum("2.5");
        ct_2_5.setItemName("其他设备设施");
        if(cbwfxcjd_et_qxms_2_5.getText().toString().equals("")){
            ct_2_5.setDescribe("00");
        }else {
            ct_2_5.setDescribe(cbwfxcjd_et_qxms_2_5.getText().toString());
        }
        ct_2_5.setHandle(cbwfxcjd_tv_clyj_2_5.getText().toString());
        ct_2_5.setRectificat(cbwfxcjd_et_zgjg_2_5.getText().toString());
        list.add(ct_2_5);



        CtSafetySceneInspectDetail ct_3_0=new CtSafetySceneInspectDetail();
        ct_3_0.setSex("3.0");
        ct_3_0.setItemNum("3");
        ct_3_0.setItemName("作业操作性检查");
        ct_3_0.setDescribe("");
        ct_3_0.setHandle("");
        ct_3_0.setRectificat("");
        list.add(ct_3_0);

        CtSafetySceneInspectDetail ct_3_1=new CtSafetySceneInspectDetail();
        ct_3_1.setSex("3.1");
        ct_3_1.setItemNum("3.1");
        ct_3_1.setItemName("作业人员是否经过培训或持有相关证书");
        if(cbwfxcjd_et_qxms_3_1.getText().toString().equals("")){
            ct_3_1.setDescribe("00");
        }else {
            ct_3_1.setDescribe(cbwfxcjd_et_qxms_3_1.getText().toString());
        }
        ct_3_1.setHandle(cbwfxcjd_tv_clyj_3_1.getText().toString());
        ct_3_1.setRectificat(cbwfxcjd_et_zgjg_3_1.getText().toString());
        list.add(ct_3_1);


        CtSafetySceneInspectDetail ct_3_2=new CtSafetySceneInspectDetail();
        ct_3_2.setSex("3.2");
        ct_3_2.setItemNum("3.2");
        ct_3_2.setItemName("作业双方是否安排并保持有效的值班");
        if(cbwfxcjd_et_qxms_3_2.getText().toString().equals("")){
            ct_3_2.setDescribe("00");
        }else {
            ct_3_2.setDescribe(cbwfxcjd_et_qxms_3_2.getText().toString());
        }
        ct_3_2.setHandle(cbwfxcjd_tv_clyj_3_2.getText().toString());
        ct_3_2.setRectificat(cbwfxcjd_et_zgjg_3_2.getText().toString());
        list.add(ct_3_2);

        CtSafetySceneInspectDetail ct_3_3=new CtSafetySceneInspectDetail();
        ct_3_3.setSex("3.3");
        ct_3_3.setItemNum("3.3");
        ct_3_3.setItemName("作业前双方按要求填写安全检查表或确认书（适用时）");
        if(cbwfxcjd_et_qxms_3_3.getText().toString().equals("")){
            ct_3_3.setDescribe("00");
        }else {
            ct_3_3.setDescribe(cbwfxcjd_et_qxms_3_3.getText().toString());
        }
        ct_3_3.setHandle(cbwfxcjd_tv_clyj_3_3.getText().toString());
        ct_3_3.setRectificat(cbwfxcjd_et_zgjg_3_3.getText().toString());
        list.add(ct_3_3);


        CtSafetySceneInspectDetail ct_3_4=new CtSafetySceneInspectDetail();
        ct_3_4.setSex("3.4");
        ct_3_4.setItemNum("3.4");
        ct_3_4.setItemName("是否按要求采取安全与防污染防范措施");
        if(cbwfxcjd_et_qxms_3_4.getText().toString().equals("")){
            ct_3_4.setDescribe("00");
        }else {
            ct_3_4.setDescribe(cbwfxcjd_et_qxms_3_4.getText().toString());
        }
        ct_3_4.setHandle(cbwfxcjd_tv_clyj_3_4.getText().toString());
        ct_3_4.setRectificat(cbwfxcjd_et_zgjg_3_4.getText().toString());
        list.add(ct_3_4);


        CtSafetySceneInspectDetail ct_3_5=new CtSafetySceneInspectDetail();
        ct_3_5.setSex("3.5");
        ct_3_5.setItemNum("3.5");
        ct_3_5.setItemName("其他操作性检查");
        if(cbwfxcjd_et_qxms_3_5.getText().toString().equals("")){
            ct_3_5.setDescribe("00");
        }else {
            ct_3_5.setDescribe(cbwfxcjd_et_qxms_3_5.getText().toString());
        }
        ct_3_5.setHandle(cbwfxcjd_tv_clyj_3_5.getText().toString());
        ct_3_5.setRectificat(cbwfxcjd_et_zgjg_3_5.getText().toString());
        list.add(ct_3_5);


        CtSafetySceneInspectDetail ct_4_0=new CtSafetySceneInspectDetail();
        ct_4_0.setSex("4.0");
        ct_4_0.setItemNum("4");
        ct_4_0.setItemName("其他发现问题描述");
        ct_4_0.setDescribe("");
        ct_4_0.setHandle("");
        ct_4_0.setRectificat("");
        list.add(ct_4_0);
        for (int i = 0; i <cbwfxcjd_tl_qtfxwt.getChildCount() ; i++) {
            CBWFQTFXLayout cbwfqtfxLayout = (CBWFQTFXLayout) cbwfxcjd_tl_qtfxwt.getChildAt(i);
            CtSafetySceneInspectDetail ct=cbwfqtfxLayout.getData();
            if(ct.getItemName().equals("")&&ct.getHandle().equals("")&&ct.getRectificat().equals("")){

            }else {
                list.add(ct);
            }


        }
        ctSafeInspectNoticeObject.setDetails(list);
        return ctSafeInspectNoticeObject;
    }
    private String sub_msg;
    private String id;
    @Override
    public void subSuccessfully(String msg, String inspectNo) {
        this.sub_msg=msg;
        dialog.dismiss();
        this.id=inspectNo;
        this.namepath="";
        myHandler.sendEmptyMessage(500);
    }

    private static class MyHandler extends Handler {

        WeakReference<CBWFXCJDActivity> cbwfxcjdActivityWeakReference;

        MyHandler(CBWFXCJDActivity cbwfxcjdActivity) {
            this.cbwfxcjdActivityWeakReference = new WeakReference<>(cbwfxcjdActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final CBWFXCJDActivity cbwfxcjdActivity = cbwfxcjdActivityWeakReference.get();
            int what = msg.what;
            if(what == 500){
                DialogUtils.showDialog(cbwfxcjdActivity, "温馨提示", cbwfxcjdActivity.sub_msg+"船舶危防现场监督检查记录表？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        String name =  "船舶危防现场_"+cbwfxcjdActivity.cbwfxcjd_et_zwcm.getText().toString()+"_"+cbwfxcjdActivity.id+".pdf";
                        cbwfxcjdActivity.namepath = cbwfxcjdActivity.path + name;
                        File file = new File(cbwfxcjdActivity.namepath);
                        if (file.exists()){
                            //直接打印
                            Intent intent=new Intent(cbwfxcjdActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2",cbwfxcjdActivity.namepath);
                            cbwfxcjdActivity.startActivity(intent);
                        }else{
                            cbwfxcjdActivity.downloadPDF();
                            cbwfxcjdActivity.downloadDialog.show();
                            cbwfxcjdActivity.httpDownManager.startDown(cbwfxcjdActivity.apkApi);
                        }


                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                        //finish();
                    }
                });
            }
            if(what == 600){

                DialogUtils.showDialog(cbwfxcjdActivity, "温馨提示", cbwfxcjdActivity.sub_msg+"是否重新提交？", "重新提交","返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        cbwfxcjdActivity.cbwfxcPresenter.subData();
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
        String name =  "船舶危防现场_"+cbwfxcjd_et_zwcm.getText().toString()+"_"+id+".pdf";
        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctSafetySceneInspectRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params
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
//                taskDataPresenter.finishTask(userId,taskId,token);
                downloadDialog.dismiss();
                Intent intent=new Intent(CBWFXCJDActivity.this, Activity_PrintPdf.class);
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
                //           deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }
    @Override
    public void subOnFail(String msg) {
        dialog.dismiss();
        this.sub_msg=msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void setDetailData(CtSafeInspectNoticeObject ctSafeInspectNoticeObject) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }
}
