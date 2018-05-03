package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

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
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter.jdbgPresenter;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.InspectorInfoDialog;
import com.winfo.szrsp.app.mvp.table.fragmentlist.IChecklistMainActvity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.presenter.ChecklistPresenter;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.InspectionAllDialog;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.TaskAcceptDialog;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.table.TSiteSupervisionList;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.task.TaskResources;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
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
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: JdbgActivity
 * @Author: lsj
 * @Date: 2017/12/4  10:02
 * @Description:船舶现场监督报告
 * @Version:
 */
public class JdbgActivity extends Activity implements IJdbgActivity, CompoundButton.OnCheckedChangeListener, View.OnClickListener, IChecklistMainActvity, jdbgjcxmLayout.DelItem {
    private View titie_view;
    private ImageButton back_btn;
    private TextView tv_title;
    private TextView tv_jcy_no;
    private EditText edt_czxm;
    private EditText edt_cm;
    private EditText edt_gj;
    private EditText edt_cbzl;
    private EditText edt_imo;
    private EditText edt_cbbh;
    private EditText edt_cbglr;
    private CheckBox ck_zxjc;
    private EditText edt_zxjc;
    private CheckBox ck_aqjc;
    private EditText edt_jcjg;
    private EditText tv_zfry;
    private TextView edt_bowei;
    //    private CheckBox item1_ck1, item1_ck2, item1_ck3;
//    private CheckBox item2_ck1, item2_ck2, item2_ck3;
//    private CheckBox item3_ck1, item3_ck2, item3_ck3;
//    private CheckBox item4_ck1, item4_ck2, item4_ck3;
//    private CheckBox item5_ck1, item5_ck2, item5_ck3;
//    private CheckBox item6_ck1, item6_ck2, item6_ck3;
//    private CheckBox item7_ck1, item7_ck2, item7_ck3;
//    private CheckBox item8_ck1, item8_ck2, item8_ck3;
//    private CheckBox item9_ck1, item9_ck2, item9_ck3;
    private TextView edt_cz_qm;
    private ImageView img_qm;
    private TextView tv_jcsj;
    private Button btn_save;
    private String qm_path;
    //    private String ck_item1, ck_item2, ck_item3, ck_item4, ck_item5, ck_item6, ck_item7, ck_item8 = "";
//    private String ck_item9 = "1";
    private String isStart = "";
    private int num = 9;
    private LinearLayout wtms_layout;
    private LinearLayout jcxmLin;
    private jdbgwtmsLayout jdbgwtmsLayout;
    private jdbgjcxmLayout jdbgjcxmLayout;
    private ImageView img_add;
    private ImageView img_del;
    private ImageView addImg;
    private ImageView delImg;

    private TimePickerView timePickerView;
    private jdbgPresenter presenter;
    private Dialog downloadDialog;
    private ChecklistPresenter checklistPresenter;
    private MyHandler myHandler;
    private Dialog dialog;
    private ShipBerthDialog shipBerthDialog;
    String selectFuseName = "", selectFuseId = "";

    private List<SecurityInspectorInformation> securityInspectorInformationList;
    private InspectorInfoDialog inspectorInfoDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdbg);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getBerthData(dialog, "", false);
    }

    private void initData() {
        securityInspectorInformationList = new ArrayList<>();
        tv_title.setText("船舶现场监督报告");
        tv_title.setVisibility(View.VISIBLE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_jcsj.setText(time);
        checklistPresenter = new ChecklistPresenter(this);
//        tv_zfry.setText(ACache.get(this).getAsString("userName"));
        edt_jcjg.setText(ACache.get(this).getAsString("orgName"));
    }

    private void initEvent() {
        edt_bowei.setOnClickListener(this);
//        item1_ck1.setOnCheckedChangeListener(this);
//        item1_ck2.setOnCheckedChangeListener(this);
//        item1_ck3.setOnCheckedChangeListener(this);
//        item2_ck1.setOnCheckedChangeListener(this);
//        item2_ck2.setOnCheckedChangeListener(this);
//        item2_ck3.setOnCheckedChangeListener(this);
//        item3_ck1.setOnCheckedChangeListener(this);
//        item3_ck2.setOnCheckedChangeListener(this);
//        item3_ck3.setOnCheckedChangeListener(this);
//        item4_ck1.setOnCheckedChangeListener(this);
//        item4_ck2.setOnCheckedChangeListener(this);
//        item4_ck3.setOnCheckedChangeListener(this);
//        item5_ck1.setOnCheckedChangeListener(this);
//        item5_ck2.setOnCheckedChangeListener(this);
//        item5_ck3.setOnCheckedChangeListener(this);
//        item6_ck1.setOnCheckedChangeListener(this);
//        item6_ck2.setOnCheckedChangeListener(this);
//        item6_ck3.setOnCheckedChangeListener(this);
//        item7_ck1.setOnCheckedChangeListener(this);
//        item7_ck2.setOnCheckedChangeListener(this);
//        item7_ck3.setOnCheckedChangeListener(this);
//        item8_ck1.setOnCheckedChangeListener(this);
//        item8_ck2.setOnCheckedChangeListener(this);
//        item8_ck3.setOnCheckedChangeListener(this);
//        item9_ck1.setOnCheckedChangeListener(this);
//        item9_ck2.setOnCheckedChangeListener(this);
//        item9_ck3.setOnCheckedChangeListener(this);
        ck_aqjc.setOnCheckedChangeListener(this);
        tv_zfry.setOnClickListener(this);
        img_add.setOnClickListener(this);
        img_del.setOnClickListener(this);
        addImg.setOnClickListener(this);
        delImg.setOnClickListener(this);
        tv_jcsj.setOnClickListener(this);
        edt_cz_qm.setOnClickListener(this);
        img_qm.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        ck_zxjc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    edt_zxjc.setEnabled(true);
                } else {
                    edt_zxjc.setEnabled(false);
                }
            }
        });

    }

    private void initView() {
        myHandler = new MyHandler(this);
        downloadDialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        presenter = new jdbgPresenter(this);
        titie_view = findViewById(R.id.title);
        titie_view.setVisibility(View.VISIBLE);
        back_btn = titie_view.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = titie_view.findViewById(R.id.table_titleBar_titleText);
        edt_czxm = findViewById(R.id.edt_czxm);
        edt_cm = findViewById(R.id.edt_cm);
        edt_gj = findViewById(R.id.edt_gj);
        edt_cbzl = findViewById(R.id.edt_cbzl);
        edt_imo = findViewById(R.id.edt_imo);
        edt_cbbh = findViewById(R.id.edt_cbbh);
        edt_cbglr = findViewById(R.id.edt_cbglr);
        ck_zxjc = findViewById(R.id.ck_zxjc);
        edt_zxjc = findViewById(R.id.edt_zxjc);
        ck_aqjc = findViewById(R.id.ck_qdcbaqjc);
        edt_jcjg = findViewById(R.id.edt_jcjg);
        tv_zfry = findViewById(R.id.tv_zfry);
        edt_bowei = findViewById(R.id.edt_bowei);
//        item1_ck1 = findViewById(R.id.item1_ck1);
//        item1_ck2 = findViewById(R.id.item1_ck2);
//        item1_ck3 = findViewById(R.id.item1_ck3);
//        item2_ck1 = findViewById(R.id.item2_ck1);
//        item2_ck2 = findViewById(R.id.item2_ck2);
//        item2_ck3 = findViewById(R.id.item2_ck3);
//        item3_ck1 = findViewById(R.id.item3_ck1);
//        item3_ck2 = findViewById(R.id.item3_ck2);
//        item3_ck3 = findViewById(R.id.item3_ck3);
//        item4_ck1 = findViewById(R.id.item4_ck1);
//        item4_ck2 = findViewById(R.id.item4_ck2);
//        item4_ck3 = findViewById(R.id.item4_ck3);
//        item5_ck1 = findViewById(R.id.item5_ck1);
//        item5_ck2 = findViewById(R.id.item5_ck2);
//        item5_ck3 = findViewById(R.id.item5_ck3);
//        item6_ck1 = findViewById(R.id.item6_ck1);
//        item6_ck2 = findViewById(R.id.item6_ck2);
//        item6_ck3 = findViewById(R.id.item6_ck3);
//        item7_ck1 = findViewById(R.id.item7_ck1);
//        item7_ck2 = findViewById(R.id.item7_ck2);
//        item7_ck3 = findViewById(R.id.item7_ck3);
//        item8_ck1 = findViewById(R.id.item8_ck1);
//        item8_ck2 = findViewById(R.id.item8_ck2);
//        item8_ck3 = findViewById(R.id.item8_ck3);
//        item9_ck1 = findViewById(R.id.item9_ck1);
//        item9_ck2 = findViewById(R.id.item9_ck2);
//        item9_ck3 = findViewById(R.id.item9_ck3);
        wtms_layout = findViewById(R.id.wtms_layout);
        jcxmLin = findViewById(R.id.jcxmLin);
//        jdbgjcxmLayout = new jdbgjcxmLayout(this, num);
//        jcxmLin.addView(jdbgjcxmLayout);
//        jdbgjcxmLayout.setDelItem(this);
        img_add = findViewById(R.id.add_img);
        img_del = findViewById(R.id.del_img);
        addImg = findViewById(R.id.addImg);
        delImg = findViewById(R.id.delImg);
        btn_save = findViewById(R.id.btn_save);
//        jdbgwtmsLayout = new jdbgwtmsLayout(this, "9", null);
//        jdbgwtmsLayout.setTag(9);
//        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
        tv_jcsj = findViewById(R.id.tv_sj);
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        edt_cz_qm = findViewById(R.id.edt_cz_qm);
        img_qm = findViewById(R.id.cz_qm);
        tv_jcy_no = findViewById(R.id.tv_jcy_no);


        jdbgjcxmLayout jdbgjcxmLayout1 = new jdbgjcxmLayout(this, 1, "中国籍船舶是否开展开航前自查");
        jdbgjcxmLayout1.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout1);
        jdbgjcxmLayout jdbgjcxmLayout2 = new jdbgjcxmLayout(this, 2, "法定证书文书配备及记录是否齐全");
        jdbgjcxmLayout2.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout2);
        jdbgjcxmLayout jdbgjcxmLayout3 = new jdbgjcxmLayout(this, 3, "船员配备是否满足要求");
        jcxmLin.addView(jdbgjcxmLayout3);
        jdbgjcxmLayout3.setDelItem(this);
        jdbgjcxmLayout jdbgjcxmLayout4 = new jdbgjcxmLayout(this, 4, "客货载运及货物系固绑扎是否符合规定");
        jdbgjcxmLayout4.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout4);
        jdbgjcxmLayout jdbgjcxmLayout5 = new jdbgjcxmLayout(this, 5, "船舶防污染措施是否落实");
        jdbgjcxmLayout5.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout5);
        jdbgjcxmLayout jdbgjcxmLayout6 = new jdbgjcxmLayout(this, 6, "船舶航行、停泊、作业是否符合规定");
        jdbgjcxmLayout6.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout6);
        jdbgjcxmLayout jdbgjcxmLayout7 = new jdbgjcxmLayout(this, 7, "船舶是否按要求进行进出港报告或者办理进出港手续");
        jdbgjcxmLayout7.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout7);
        jdbgjcxmLayout jdbgjcxmLayout8 = new jdbgjcxmLayout(this, 8, "船舶是否按照相关规定缴纳相关费税");
        jdbgjcxmLayout8.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout8);
        jdbgjcxmLayout jdbgjcxmLayout9 = new jdbgjcxmLayout(this, 9, "其他");
        jdbgjcxmLayout9.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout9);
        jdbgjcxmLayout9.setCkItem2Checked();
    }

    private int getAddViewIndex(LinearLayout wtms_layout, jdbgwtmsLayout jdbgwtmsLayout) {
        if (wtms_layout.getChildCount() > 0) {
            for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                if ((int) jdbgwtmsLayout.getTag() < (int) wtms_layout.getChildAt(i).getTag()) {
                    return i;
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
//            case R.id.item1_ck1:
//                if (isCheck) {
//                    item1_ck1.setChecked(true);
//                    item1_ck2.setChecked(false);
//                    item1_ck3.setChecked(false);
//                    ck_item1 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("1".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item1 = "";
//                }
//                break;
//            case R.id.item1_ck2:
//                if (isCheck) {
//                    item1_ck1.setChecked(false);
//                    item1_ck2.setChecked(true);
//                    item1_ck3.setChecked(false);
//                    ck_item1 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "1", null);
//                    jdbgwtmsLayout.setTag(1);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item1 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("1".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//
//                break;
//            case R.id.item1_ck3:
//                if (isCheck) {
//                    item1_ck1.setChecked(false);
//                    item1_ck2.setChecked(false);
//                    item1_ck3.setChecked(true);
//                    ck_item1 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("1".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item1 = "";
//                }
//                break;
//
//            case R.id.item2_ck1:
//                if (isCheck) {
//                    item2_ck1.setChecked(true);
//                    item2_ck2.setChecked(false);
//                    item2_ck3.setChecked(false);
//                    ck_item2 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("2".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item2 = "";
//                }
//                break;
//            case R.id.item2_ck2:
//                if (isCheck) {
//                    item2_ck1.setChecked(false);
//                    item2_ck2.setChecked(true);
//                    item2_ck3.setChecked(false);
//                    ck_item2 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "2", null);
//                    jdbgwtmsLayout.setTag(2);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item2 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("2".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item2_ck3:
//                if (isCheck) {
//                    item2_ck1.setChecked(false);
//                    item2_ck2.setChecked(false);
//                    item2_ck3.setChecked(true);
//                    ck_item2 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("2".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item2 = "";
//                }
//                break;
//
//            case R.id.item3_ck1:
//                if (isCheck) {
//                    item3_ck1.setChecked(true);
//                    item3_ck2.setChecked(false);
//                    item3_ck3.setChecked(false);
//                    ck_item3 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("3".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item3 = "";
//                }
//                break;
//            case R.id.item3_ck2:
//                if (isCheck) {
//                    item3_ck1.setChecked(false);
//                    item3_ck2.setChecked(true);
//                    item3_ck3.setChecked(false);
//                    ck_item3 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "3", null);
//                    jdbgwtmsLayout.setTag(3);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item3 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("3".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item3_ck3:
//                if (isCheck) {
//                    item3_ck1.setChecked(false);
//                    item3_ck2.setChecked(false);
//                    item3_ck3.setChecked(true);
//                    ck_item3 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("3".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item3 = "";
//                }
//                break;
//
//            case R.id.item4_ck1:
//                if (isCheck) {
//                    item4_ck1.setChecked(true);
//                    item4_ck2.setChecked(false);
//                    item4_ck3.setChecked(false);
//                    ck_item4 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("4".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item4 = "";
//                }
//                break;
//            case R.id.item4_ck2:
//                if (isCheck) {
//                    item4_ck1.setChecked(false);
//                    item4_ck2.setChecked(true);
//                    item4_ck3.setChecked(false);
//                    ck_item4 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "4", null);
//                    jdbgwtmsLayout.setTag(4);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item4 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("4".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item4_ck3:
//                if (isCheck) {
//                    item4_ck1.setChecked(false);
//                    item4_ck2.setChecked(false);
//                    item4_ck3.setChecked(true);
//                    ck_item4 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("4".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item4 = "";
//                }
//                break;
//
//            case R.id.item5_ck1:
//                if (isCheck) {
//                    item5_ck1.setChecked(true);
//                    item5_ck2.setChecked(false);
//                    item5_ck3.setChecked(false);
//                    ck_item5 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("5".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item5 = "";
//                }
//                break;
//            case R.id.item5_ck2:
//                if (isCheck) {
//                    item5_ck1.setChecked(false);
//                    item5_ck2.setChecked(true);
//                    item5_ck3.setChecked(false);
//                    ck_item5 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "5", null);
//                    jdbgwtmsLayout.setTag(5);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item5 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("5".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item5_ck3:
//                if (isCheck) {
//                    item5_ck1.setChecked(false);
//                    item5_ck2.setChecked(false);
//                    item5_ck3.setChecked(true);
//                    ck_item5 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("5".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item5 = "";
//                }
//                break;
//
//            case R.id.item6_ck1:
//                if (isCheck) {
//                    item6_ck1.setChecked(true);
//                    item6_ck2.setChecked(false);
//                    item6_ck3.setChecked(false);
//                    ck_item6 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("6".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item6 = "";
//                }
//                break;
//            case R.id.item6_ck2:
//                if (isCheck) {
//                    item6_ck1.setChecked(false);
//                    item6_ck2.setChecked(true);
//                    item6_ck3.setChecked(false);
//                    ck_item6 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "6", null);
//                    jdbgwtmsLayout.setTag(6);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item6 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("6".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item6_ck3:
//                if (isCheck) {
//                    item6_ck1.setChecked(false);
//                    item6_ck2.setChecked(false);
//                    item6_ck3.setChecked(true);
//                    ck_item6 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("6".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item6 = "";
//                }
//                break;
//
//            case R.id.item7_ck1:
//                if (isCheck) {
//                    item7_ck1.setChecked(true);
//                    item7_ck2.setChecked(false);
//                    item7_ck3.setChecked(false);
//                    ck_item7 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("7".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item6 = "";
//                }
//                break;
//            case R.id.item7_ck2:
//                if (isCheck) {
//                    item7_ck1.setChecked(false);
//                    item7_ck2.setChecked(true);
//                    item7_ck3.setChecked(false);
//                    ck_item7 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "7", null);
//                    jdbgwtmsLayout.setTag(7);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item7 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("7".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item7_ck3:
//                if (isCheck) {
//                    item7_ck1.setChecked(false);
//                    item7_ck2.setChecked(false);
//                    item7_ck3.setChecked(true);
//                    ck_item7 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("7".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item7 = "";
//                }
//                break;
//
//            case R.id.item8_ck1:
//                if (isCheck) {
//                    item8_ck1.setChecked(true);
//                    item8_ck2.setChecked(false);
//                    item8_ck3.setChecked(false);
//                    ck_item8 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("8".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item8 = "";
//                }
//                break;
//            case R.id.item8_ck2:
//                if (isCheck) {
//                    item8_ck1.setChecked(false);
//                    item8_ck2.setChecked(true);
//                    item8_ck3.setChecked(false);
//                    ck_item8 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "8", null);
//                    jdbgwtmsLayout.setTag(8);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item8 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("8".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item8_ck3:
//                if (isCheck) {
//                    item8_ck1.setChecked(false);
//                    item8_ck2.setChecked(false);
//                    item8_ck3.setChecked(true);
//                    ck_item8 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("8".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item8 = "";
//                }
//                break;
//            case R.id.item9_ck1:
//                if (isCheck) {
//                    item9_ck1.setChecked(true);
//                    item9_ck2.setChecked(false);
//                    item9_ck3.setChecked(false);
//                    ck_item9 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("9".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item9 = "";
//                }
//                break;
//            case R.id.item9_ck2:
//                if (isCheck) {
//                    item9_ck1.setChecked(false);
//                    item9_ck2.setChecked(true);
//                    item9_ck3.setChecked(false);
//                    ck_item9 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(this, "9", null);
//                    jdbgwtmsLayout.setTag(9);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item9 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("9".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item9_ck3:
//                if (isCheck) {
//                    item9_ck1.setChecked(false);
//                    item9_ck2.setChecked(false);
//                    item9_ck3.setChecked(true);
//                    ck_item9 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("9".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item9 = "";
//                }
//                break;
            case R.id.ck_qdcbaqjc:
                if (isCheck) {
                    ck_aqjc.setChecked(true);
                    isStart = "1";
                } else {
                    isStart = "";
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_zfry:
                if (securityInspectorInformationList.size() > 0) {
                    showInspectorInfoDialog();
                } else {
                    String uuid = ACache.get(this).getAsString("uuid");
                    presenter.getInspectors(uuid);
                }
                break;

            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.cz_qm:
                showQMDialog(qm_path, 101);
                break;
            case R.id.edt_cz_qm:
                writeLine();
                break;
            case R.id.tv_sj:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_jcsj.setText(time);
                    }
                });
                timePickerView.show();
                break;
//            case R.id.add_img:
//                jdbgwtmsLayout = new jdbgwtmsLayout(this, "9");
//                wtms_layout.addView(jdbgwtmsLayout);
//                break;
//            case R.id.del_img:
//                if (wtms_layout.getChildCount() != 0) {
//                    wtms_layout.removeViewAt(wtms_layout.getChildCount() - 1);
//                }
//                break;
//            case R.id.tv_zfry:
//                checklistPresenter.getUsersListByUserDept(true);
//                break;
            case R.id.addImg:
                num += 1;
                jdbgjcxmLayout = new jdbgjcxmLayout(this, num, "其他");
                jcxmLin.addView(jdbgjcxmLayout);
                jdbgjcxmLayout.setDelItem(this);
                jdbgwtmsLayout = new jdbgwtmsLayout(this, num + "", null);
                jdbgwtmsLayout.setTag(num);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                wtms_layout.addView(jdbgwtmsLayout);
                break;
            case R.id.delImg:
                if (jcxmLin.getChildCount() != 0 && jcxmLin.getChildCount() > 9) {
                    jcxmLin.removeViewAt(jcxmLin.getChildCount() - 1);
                    num -= 1;
                    if (wtms_layout.getChildCount() != 0) {
                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                            View view1 = wtms_layout.getChildAt(i);
                            TextView tv = view1.findViewById(R.id.tvIndex);
                            if (String.valueOf(num + 1).equals(tv.getText().toString().trim())) {
                                wtms_layout.removeView(view1);
                            }
                        }
                    }
                }
                break;
            case R.id.btn_save:
                if (edt_cm.getText().toString().trim().equals("") ||
                        edt_gj.getText().toString().trim().equals("") ||
                        edt_cbzl.getText().toString().trim().equals("") ||
                        edt_imo.getText().toString().trim().equals("") ||
                        edt_cbbh.getText().toString().trim().equals("") ||
                        edt_cbglr.getText().toString().trim().equals("") ||
                        edt_czxm.getText().toString().trim().equals("") ||
                        edt_jcjg.getText().toString().trim().equals("") ||
                        edt_bowei.getText().toString().trim().equals("")
                        ) {
                    ToastUtils.showToast(this, "请填写完表格再提交！");
                } else if (!ACache.get(this).getAsString("userNum").contains("test") && !ACache.get(this).getAsString("userNum").contains("winfo")) {
                    if (tv_zfry.getText().toString().equals("")) {
                        ToastUtils.showToast(this, "请选择执法员");
                    } else if (tv_jcy_no.getText().toString().trim().equals("")) {
                        ToastUtils.showToast(this, "请输入执法证号");
                    }
                } else {
                    cbxcjdbgData data = new cbxcjdbgData();
                    cbxcjdbgData.info info = new cbxcjdbgData.info();
                    List<cbxcjdbgData.detail> detail = new ArrayList<>();
                    for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                        jdbgwtmsLayout jdbgwtmsLayout = (jdbgwtmsLayout) wtms_layout.getChildAt(i);
                        if (jdbgwtmsLayout.getData() == null) {
                            return;
                        } else {
                            detail.add(jdbgwtmsLayout.getData());
                        }
                    }

                    List<TSiteSupervisionList> lists = new ArrayList<>();
                    for (int i = 0; i < jcxmLin.getChildCount(); i++) {
                        jdbgjcxmLayout jdbgjcxmLayout = (jdbgjcxmLayout) jcxmLin.getChildAt(i);
                        if (jdbgjcxmLayout.getData() == null) {
                            return;
                        } else {
                            lists.add(jdbgjcxmLayout.getData());
                        }
                    }
                    data.setList(lists);

                    info.setInitialInspectMark("0");//设置初查复查   初查0   复查1
                    info.setInspectorName(tv_zfry.getText().toString());
                    if (tv_jcy_no.getText().toString().contains("，")) {
                        tv_jcy_no.getText().toString().replace("，", ",");
                    }
                    info.setInspectorCode(tv_jcy_no.getText().toString());
                    info.setCaptainName(edt_czxm.getText().toString());
                    info.setShipId(edt_imo.getText().toString());
                    info.setShipNo(edt_cbbh.getText().toString());
                    info.setShipNameCn(edt_cm.getText().toString());
                    info.setShipManager(edt_cbglr.getText().toString());
                    info.setRegportName(edt_gj.getText().toString());
                    info.setInspectDate(tv_jcsj.getText().toString());
                    info.setInspectOrg(edt_jcjg.getText().toString());
                    info.setShipTypeNameCn(edt_cbzl.getText().toString());
                    info.setBerthName(edt_bowei.getText().toString());
                    info.setBerthCode(selectFuseId);
                    if (ck_zxjc.isChecked()) {
                        info.setIsSpecialInspect("1");
                    } else {
                        info.setIsSpecialInspect("0");
                    }
                    info.setSpecialInspectName(edt_zxjc.getText().toString());
//                    info.setInspectItem1(ck_item1);
//                    info.setInspectItem2(ck_item2);
//                    info.setInspectItem3(ck_item3);
//                    info.setInspectItem4(ck_item4);
//                    info.setInspectItem5(ck_item5);
//                    info.setInspectItem6(ck_item6);
//                    info.setInspectItem7(ck_item7);
//                    info.setInspectItem8(ck_item8);
//                    info.setInspectItem9(ck_item9);
                    info.setNum(jcxmLin.getChildCount() + "");
                    info.setInspectorName(tv_zfry.getText().toString());
                    info.setIsInspect(isStart);
                    if (qm_path != null) {
                        info.setWidth(img_qm.getWidth());
                        info.setHeight(img_qm.getHeight());
                        data.setInfo(info);
                        data.setDetail(detail);
                        presenter.subData(qm_path, data);
                    } else {
                        ToastUtils.showToast(this, "请签名！");
                    }
//                    data.setInfo(info);
//                    data.setDetail(detail);
//                    presenter.subData(qm_path,data);
                }
                break;
            case R.id.edt_bowei:
                List<ShipBerthData> data = getData();
                if (data == null) {
                    presenter.getBerthData(dialog, "", true);
                    return;
                }
                List<MultiItemEntity> datas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    BerthLevelItem0 berthLevelItem0 = new BerthLevelItem0();
                    berthLevelItem0.setId(data.get(i).getId());
                    berthLevelItem0.setName(data.get(i).getName());
                    berthLevelItem0.setPid(data.get(i).getPid());
                    if (data.get(i).getName().equals(selectFuseName)) {
                        berthLevelItem0.setChecked(true);
                    } else {
                        berthLevelItem0.setChecked(false);
                    }
                    berthLevelItem0.setCanClick(data.get(i).isCanClick());
                    for (int j = 0; j < data.get(i).getChild().size(); j++) {
                        if (data.get(i).getChild().size() > 0 && data.get(i).getChild() != null) {
                            BerthLevelItem1 berthLevelItem1 = new BerthLevelItem1();
                            berthLevelItem1.setId(data.get(i).getChild().get(j).getId());
                            berthLevelItem1.setName(data.get(i).getChild().get(j).getName());
                            berthLevelItem1.setPid(data.get(i).getChild().get(j).getPid());
                            if (data.get(i).getChild().get(j).getName().equals(selectFuseName)) {
                                berthLevelItem1.setChecked(true);
                            } else {
                                berthLevelItem1.setChecked(false);
                            }
                            berthLevelItem1.setCanClick(data.get(i).getChild().get(j).isCanClick());
                            berthLevelItem0.addSubItem(berthLevelItem1);
                        }
                    }
                    datas.add(berthLevelItem0);
                }

                shipBerthDialog = new ShipBerthDialog(this, datas);
                shipBerthDialog.show();
                shipBerthDialog.setOnData(new ShipBerthDialog.OnGetData() {
                    @Override
                    public void onDataCallBack(Map<String, Object> map) {
                        edt_bowei.setText(map.get("name").toString());
                        selectFuseName = map.get("name").toString();
                        selectFuseId = map.get("id").toString();
                    }
                });
                shipBerthDialog.setOnSearchShipData(new ShipBerthDialog.OnSearchShipData() {
                    @Override
                    public void onSearchDataCallBack(String taskCode, String taskContent) {
                        edt_bowei.setText(taskContent);
                        selectFuseName = taskContent;
                        selectFuseId = taskCode;
                    }
                });
                break;
        }
    }

    public Map<String, Object> returnData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", selectFuseName);
        map.put("id", selectFuseId);
        return map;
    }

    /**
     * 签名
     */
    private void writeLine() {
        ScrollView ll_sc = findViewById(R.id.ll_sc);
        SignatureView view1 = new SignatureView(this, ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                qm_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate() + "qm.png";
                try {
                    linePathView.save(qm_path, false, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BitmapUtils.getImageThumbnail(qm_path, 80, 80);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(qm_path, options);
                img_qm.setImageBitmap(bm);
                edt_cz_qm.setVisibility(View.GONE);
                img_qm.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
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
    private void showQMDialog(String path, final int result) {

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
        dialog_record_pen_iv = (ImageView) qmView.findViewById(R.id.dialog_record_pen_iv);
        Uri uri = Uri.fromFile(new File(path));
        dialog_record_pen_iv.setImageURI(uri);

        dialog_record_ok = (Button) qmView.findViewById(R.id.dialog_record_ok);
        dialog_record_reautograph = (Button) qmView.findViewById(R.id.dialog_record_reautograph);

        dialog_record_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
            }
        });
        dialog_record_reautograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
                writeLine();
            }
        });
    }

    @Override
    public Dialog getDialog() {
        return downloadDialog;
    }

    private String ispNO;

    @Override
    public void OnSuccess(String msg) {
        ispNO = msg;
        this.namepath = "";
        myHandler.sendEmptyMessage(500);
    }

    private String sub_msg;

    @Override
    public Dialog getDiaole() {
        return downloadDialog;
    }

    @Override
    public void setTaskDefectItemData(List<TaskDefectItemData> taskDefectItemData) {

    }

    @Override
    public void OnFaile(String msg) {
        this.sub_msg = msg;
        myHandler.sendEmptyMessage(600);
    }

    TaskAcceptDialog taskAcceptDialog;

    @Override
    public void setUsersListSucceed(TaskPersonResouse taskPersonResouse) {
        if (taskAcceptDialog == null) {
            taskAcceptDialog = new TaskAcceptDialog(this, taskPersonResouse, ACache.get(SzRspApplication.getContext()).getAsString("uuid"), "", false);
            taskAcceptDialog.showDialog();
            taskAcceptDialog.setOnData(new TaskAcceptDialog.OnGetData() {
                @Override
                public void onDataCallBack(List<UserInfo> userInfoListReturn, List<TaskResources> taskResourcesListReturn) {
                    taskAcceptDialog.dismiss();
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < userInfoListReturn.size(); i++) {
                        strings.add(userInfoListReturn.get(i).getUserName());
                    }
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < strings.size(); i++) {
                        if (i < strings.size() - 1) {
                            text.append(strings.get(i));
                            text.append(",");
                        } else {
                            text.append(strings.get(i));
                        }
                    }
                    String str = text.toString();
                    tv_zfry.setText(str);
                }
            });
        } else {
            taskAcceptDialog.showDialog();
        }

    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {

    }

    @Override
    public TaskFinishSubData getTaskFinishSubData() {
        return null;
    }

    @Override
    public void finishSucceed(TaskFinishData data, String msg) {

    }

    @Override
    public void setDetailData(DetailcbxcjdbgData detailData) {

    }

    @Override
    public void setShipBerthData(List<ShipBerthData> datas) {
        this.shipBerthData = bulid(datas);
    }

    List<ShipBerthData> shipBerthData;

    private List<ShipBerthData> getData() {
        return shipBerthData;
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList) {
        securityInspectorInformationList.addAll(inspectorInformationList);
        inspectorInfoDialog = new InspectorInfoDialog("检查员列表", this, securityInspectorInformationList, new InspectorInfoDialog.GetCheckBoxDialogDataCallBack() {
            @Override
            public void getDataCallBack(Map<Integer, Boolean> map) {
                int count = map.size();
                if (count > 0) {
                    int pointer = 0;
                    StringBuilder name = new StringBuilder();
                    StringBuilder no = new StringBuilder();
                    for (Integer index : map.keySet()) {
                        SecurityInspectorInformation securityInspectorInformation = securityInspectorInformationList.get(index);
                        name.append(securityInspectorInformation.getInspector_name());
                        no.append(securityInspectorInformation.getInspector_card());
                        if (pointer < count - 1) {
                            name.append(",");
                            no.append(",");
                        }
                    }
                    tv_zfry.setText(name.toString());
                    tv_jcy_no.setText(no.toString());
                } else {
                    tv_zfry.setText("");
                    tv_jcy_no.setText("");
                }
            }
        });
        showInspectorInfoDialog();
    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {

    }

    private void showInspectorInfoDialog() {
        inspectorInfoDialog.showCheckBoxDialog();

    }

    @Override
    public void addItems(String indexItem) {
        jdbgwtmsLayout = new jdbgwtmsLayout(this, indexItem, null);
        jdbgwtmsLayout.setTag(Integer.parseInt(indexItem));
        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
    }

    @Override
    public void delItems(String indexItem) {
        if (wtms_layout.getChildCount() != 0) {
            for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                View view = wtms_layout.getChildAt(i);
                TextView tv = view.findViewById(R.id.tvIndex);
                if (indexItem.equals(tv.getText().toString().trim())) {
                    wtms_layout.removeView(view);
                }
            }
        }
    }

    public List<ShipBerthData> bulid(List<ShipBerthData> allTaskTypeList) {
        List<ShipBerthData> shipBerthData = new ArrayList<>();
        for (ShipBerthData allTaskType : allTaskTypeList) {
            if ("ROOT".equals(allTaskType.getPid())) {
                shipBerthData.add(allTaskType);
            }
            for (ShipBerthData it : allTaskTypeList) {
                if (it.getPid().equals(allTaskType.getId())) {
                    if (allTaskType.getChild() == null) {
                        allTaskType.setChild(new ArrayList<ShipBerthData>());
                    }
                    allTaskType.getChild().add(it);
                }
            }
        }
        return shipBerthData;
    }


    private static class MyHandler extends Handler {

        WeakReference<JdbgActivity> jdbgActivityWeakReference;

        MyHandler(JdbgActivity jdbgActivity) {
            this.jdbgActivityWeakReference = new WeakReference<>(jdbgActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final JdbgActivity jdbgActivity = jdbgActivityWeakReference.get();
            int what = msg.what;
            switch (what) {
                case 500:
                    DialogUtils.showDialog(jdbgActivity, "温馨提示", "上传成功！是否打印船舶现场监督报告！", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                        @Override
                        public void btnConfirmClick(Dialog dialog) {
                            File file = new File(jdbgActivity.namepath);
                            if (file.exists()) {
                                //直接打印
                                Intent intent = new Intent(jdbgActivity, Activity_PrintPdf.class);
                                intent.putExtra("namepath2", jdbgActivity.namepath);
                                jdbgActivity.startActivity(intent);
                            } else {
                                jdbgActivity.downloadPDF();
                                jdbgActivity.downloadDialog.show();
                                jdbgActivity.httpDownManager.startDown(jdbgActivity.apkApi);
                            }
                        }

                        @Override
                        public void btnCancelClick(Dialog dialog) {
                            jdbgActivity.finish();
                        }
                    });
                    break;

                case 600:
                    ToastUtils.showToast(jdbgActivity, jdbgActivity.sub_msg);
                    break;
            }


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
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", ispNO);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }
        String name = edt_cm.getText().toString() + "_" + ispNO + ".pdf";
        namepath = path + name;
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/tSiteSupervisionRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(JdbgActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2", namepath);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                downloadDialog.dismiss();
                super.onError(e);
                ToastUtils.showToast(SzRspApplication.getContext(), "下载失败！");
                deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
            }
        });
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }
}
