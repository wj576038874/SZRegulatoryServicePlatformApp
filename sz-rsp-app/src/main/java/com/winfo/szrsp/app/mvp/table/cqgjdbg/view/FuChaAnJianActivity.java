package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.present.supervisionPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 复查安检信息表
 * Created by HoBo on 2018/4/24.
 */

public class FuChaAnJianActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, ISupervisionActivity {
    @BindView(R.id.table_titleBar_titleText)
    TextView tv_title;
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageView back_btn;
    @BindView(R.id.tvShipName)
    EditText tvShipName;
    @BindView(R.id.tvShipCjg)
    EditText tvShipCjg;
    @BindView(R.id.tvShipZl)
    EditText tvShipZl;
    @BindView(R.id.tvShipImo)
    EditText tvShipImo;
    @BindView(R.id.tvShipBh)
    EditText tvShipBh;
    @BindView(R.id.tvShipGlr)
    EditText tvShipGlr;
    @BindView(R.id.tvShipCzxm)
    EditText tvShipCzxm;
    @BindView(R.id.ck_zxjc)
    CheckBox ck_zxjc;
    @BindView(R.id.edt_zxjc)
    TextView edt_zxjc;
    @BindView(R.id.tv_qm)
    TextView tv_qm;
    @BindView(R.id.qm_img)
    ImageView qm_img;
    @BindView(R.id.cljdLin)
    LinearLayout cljdLin;
    @BindView(R.id.ck_1)
    CheckBox ck1;
    @BindView(R.id.ck_2)
    CheckBox ck2;
    @BindView(R.id.ck_3)
    CheckBox ck3;
    @BindView(R.id.ck_4)
    CheckBox ck4;
    @BindView(R.id.ck_5)
    CheckBox ck5;
    @BindView(R.id.edt_jcjg)
    EditText edt_jcjg;
    @BindView(R.id.tv_find_jcry)
    TextView tv_find_jcry;
    @BindView(R.id.tv_jcry_no)
    EditText tv_jcry_no;
    @BindView(R.id.tv_jcry)
    TextView tv_jcry;
    @BindView(R.id.img_jcry_qm)
    ImageView img_jcry_qm;
    @BindView(R.id.tv_jcsj)
    TextView tv_jcsj;
    @BindView(R.id.edt_fcqz)
    EditText edt_fcqz;
    @BindView(R.id.edt_fcqz2)
    EditText edt_fcqz2;
    @BindView(R.id.btn_save)
    Button btn_save;
    private String inspect_no;
    private List<StateControlData.FlagStateControlDetailBean> detailList;
    private List<StateControlData.FlagStateControlDetailBean> fcDetailList;
    private FuChaCLJDLayout fuChaCLJDLayout;
    private List<SecurityInspectorInformation> securityInspectorInformationList;
    private InspectorInfoDialog inspectorInfoDialog;
    private Dialog downloadDialog;
    private supervisionPresenter presenter;
    private TimePickerView timePickerView;
    private String specialInspectType = "0000000001";
    private String qm_path;
    private String jcry_qm_path;
    private int width;
    // 距离两边的像素
    private int magin;
    private View qmView;// 签名所承载的view
    private Dialog qm_dialog;
    private ImageView dialog_record_pen_iv;
    private Button dialog_record_ok;
    private Button dialog_record_reautograph;
    private ZXJCDialog zxjcDialog;
    private MyHandler myHandler;
    private String mmsi, ywcm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision_fucha);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        tv_title.setText("船旗国监督检查复查报告");
        myHandler = new MyHandler(this);
        downloadDialog = DialogUtils.createLoadingDialog(this, "操作中...");
        presenter = new supervisionPresenter(this);
        securityInspectorInformationList = new ArrayList<>();
        detailList = new ArrayList<>();
        fcDetailList = new ArrayList<>();
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_jcsj.setText(time);
    }

    private void initData() {
        detailList.clear();
        fcDetailList.clear();
        Bundle bundle = getIntent().getExtras();
        detailList = (List<StateControlData.FlagStateControlDetailBean>) bundle.getSerializable("detailList");
        inspect_no = bundle.getString("inspect_no");
        mmsi = bundle.getString("mmsi");
        ywcm = bundle.getString("ywcm");
        presenter.findShipInfo(mmsi, ywcm);
        for (int i = 0; i < detailList.size(); i++) {
            if (inspect_no.equals(detailList.get(i).getInspect_no())) {
                fcDetailList.add(detailList.get(i));
            }
        }
        for (int i = 0; i < fcDetailList.size(); i++) {
            fuChaCLJDLayout = new FuChaCLJDLayout(this, fcDetailList.get(i));
            cljdLin.addView(fuChaCLJDLayout);
        }
    }

    private void initEvent() {
        back_btn.setOnClickListener(this);
        tv_find_jcry.setOnClickListener(this);
        tv_jcsj.setOnClickListener(this);
        tv_qm.setOnClickListener(this);
        qm_img.setOnClickListener(this);
        tv_jcry.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        edt_zxjc.setOnClickListener(this);
        img_jcry_qm.setOnClickListener(this);
        ck1.setOnCheckedChangeListener(this);
        ck2.setOnCheckedChangeListener(this);
        ck3.setOnCheckedChangeListener(this);
        ck4.setOnCheckedChangeListener(this);
        ck5.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.edt_zxjc:
                showZXJCDialog(edt_zxjc);
                break;
            case R.id.tv_qm:
                writeLine(101, qm_img, tv_qm);
                break;
            case R.id.qm_img:
                showQMDialog(qm_path, 101);
                break;
            case R.id.tv_jcry:
                writeLine(102, img_jcry_qm, tv_jcry);
                break;
            case R.id.img_jcry_qm:
                showQMDialog(jcry_qm_path, 102);
                break;
            case R.id.tv_find_jcry:
                if (securityInspectorInformationList.size() > 0) {
                    showInspectorInfoDialog();
                } else {
                    String uuid = ACache.get(this).getAsString("uuid");
                    presenter.getInspectors(uuid);
                }
                break;
            case R.id.tv_jcsj:
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
            case R.id.btn_save:
                if (tvShipName.getText().toString().trim().equals("") ||
                        tvShipCjg.getText().toString().trim().equals("") ||
                        tvShipZl.getText().toString().trim().equals("") ||
                        tvShipImo.getText().toString().trim().equals("") ||
                        tvShipBh.getText().toString().trim().equals("") ||
                        tvShipGlr.getText().toString().trim().equals("") ||
                        tvShipCzxm.getText().toString().trim().equals("") ||
                        tv_find_jcry.getText().toString().trim().equals("") ||
                        edt_jcjg.getText().toString().trim().equals("") ||
                        edt_fcqz.getText().toString().trim().equals("")
                        ) {
                    ToastUtils.showToast(this, "请填写完表格再提交");
                } else if (!ACache.get(this).getAsString("userNum").contains("test") && !ACache.get(this).getAsString("userNum").contains("winfo")) {
                    if (tv_find_jcry.getText().toString().equals("")) {
                        ToastUtils.showToast(this, "请选择执法员");
                    } else if (tv_jcry_no.getText().toString().trim().equals("")) {
                        ToastUtils.showToast(this, "请填写执法证号");
                    }
                } else {
                    jdbgData data = new jdbgData();
                    List<jdbgData.detail> detail = new ArrayList<>();
                    jdbgData.info info = new jdbgData.info();
                    info.setInitialInspectNo(inspect_no);
                    info.setInspectorName(tv_find_jcry.getText().toString());
                    if (tv_jcry_no.getText().toString().contains("，")) {
                        tv_jcry_no.getText().toString().replace("，", ",");
                    }
                    info.setInspectorCode(tv_jcry_no.getText().toString());
                    info.setShipNameCn(tvShipName.getText().toString());
                    info.setRegportName(tvShipCjg.getText().toString());
                    info.setShipTypeNameCn(tvShipZl.getText().toString());
                    info.setShopId(tvShipImo.getText().toString());
                    info.setShipNo(tvShipBh.getText().toString());
                    info.setCaptainName(tvShipCzxm.getText().toString());
                    info.setShipManager(tvShipGlr.getText().toString());
                    info.setReviewEndorsement(edt_fcqz.getText().toString());
                    if (ck_zxjc.isChecked()) {
                        info.setIsSpecialInspect("1");
                    } else {
                        info.setIsSpecialInspect("0");
                    }
                    info.setSpecialInspectName(edt_zxjc.getText().toString());
                    info.setSpecialInspectType(specialInspectType);
                    List<String> strings = new ArrayList<>();
                    if (ck1.isChecked()) {
                        strings.add("40");
                    } else {
                        strings.remove("40");
                    }
                    if (ck2.isChecked()) {
                        strings.add("50");
                    } else {
                        strings.remove("50");
                    }
                    if (ck3.isChecked()) {
                        strings.add("70");
                    } else {
                        strings.remove("70");
                    }
                    if (ck4.isChecked()) {
                        strings.add("152");
                    } else {
                        strings.remove("152");
                    }
                    if (ck5.isChecked()) {
                        strings.add("155");
                    } else {
                        strings.remove("155");
                    }
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < strings.size(); i++) {
                        if (i < strings.size() - 1) {
                            text.append(strings.get(i));
                            text.append("-");
                        } else {
                            text.append(strings.get(i));
                        }
                    }
                    info.setInspectAction(text.toString());
                    info.setInspectOrg(edt_jcjg.getText().toString());
                    info.setInspectDate(tv_jcsj.getText().toString());
                    int correctMark = 0;
                    for (int i = 0; i < cljdLin.getChildCount(); i++) {
                        FuChaCLJDLayout cljdLayout = (FuChaCLJDLayout) cljdLin.getChildAt(i);
                        if (cljdLayout.getData() == null) {
                            return;
                        }
                        jdbgData.detail detail1 = cljdLayout.getData();
                        if (detail1.getCorrectMark().equals("0")) {
                            correctMark++;
                        }
                        detail.add(detail1);
                    }

                    info.setDefectNum(correctMark);
                    if (qm_path == null || jcry_qm_path == null) {
                        ToastUtils.showToast(FuChaAnJianActivity.this, "请签名！");
                    } else {
                        info.setWidth(qm_img.getWidth());
                        info.setHeight(qm_img.getHeight());
                        info.setInspectorWidth(img_jcry_qm.getWidth());
                        info.setInspectorHeight(img_jcry_qm.getHeight());
                        info.setPortCode("440266");
                        data.setInfo(info);
                        data.setDetail(detail);
                        presenter.subData(data, qm_path, jcry_qm_path);
                    }
                }
                break;
        }
    }

    private void showInspectorInfoDialog() {
        inspectorInfoDialog.showCheckBoxDialog();
    }

    @Override
    public Dialog getDialog() {
        return downloadDialog;
    }

    private String ispNO;

    @Override
    public void onSuccess(String msg) {
        ispNO = msg;
        this.namepath = "";
        myHandler.sendEmptyMessage(500);
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
                    tv_find_jcry.setText(name.toString());
                    tv_jcry_no.setText(no.toString());
                } else {
                    tv_find_jcry.setText("点击查找");
                    tv_jcry_no.setText("");
                }

            }
        });
        showInspectorInfoDialog();
    }

    private void showZXJCDialog(final TextView edt_zxjc) {
        if (zxjcDialog == null) {
            zxjcDialog = new ZXJCDialog(this);
        }
        zxjcDialog.show(edt_zxjc.getText().toString());
        zxjcDialog.setOnData(new ZXJCDialog.OnGetData() {
            @Override
            public void onDataCallBack(String type, String name) {
                edt_zxjc.setText(name);
                specialInspectType = type;
            }
        });

    }

    /**
     * 签名
     */
    private void writeLine(final int result, final ImageView imageView, final TextView textView) {
        ScrollView ll_sc = findViewById(R.id.ll_sc);
        SignatureView view1 = new SignatureView(this, ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                if (result == 101) {
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
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    jcry_qm_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate() + "qm.png";
                    try {
                        linePathView.save(jcry_qm_path, false, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BitmapUtils.getImageThumbnail(jcry_qm_path, 80, 80);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(jcry_qm_path, options);
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

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
                if (result == 101) {
                    writeLine(101, qm_img, tv_qm);
                } else {
                    writeLine(102, img_jcry_qm, tv_jcry);
                }
                qm_dialog.dismiss();
            }
        });
    }

    private String sub_msg;

    @Override
    public void onFaile(String msg) {
        downloadDialog.dismiss();
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void setData(DetailjdbgData data) {

    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {
        downloadDialog.dismiss();
        if (infoDetails != null) {
            String cm = "";
            if (infoDetails.getShipNameCn().equals("")) {
                cm = infoDetails.getShipNameEn();
            } else {
                cm = infoDetails.getShipNameCn();
            }
            tvShipName.setText(cm);
            if ("".equals(tvShipName.getText().toString().trim())) {
                tvShipName.setFocusable(true);
            } else {
                tvShipName.setFocusable(false);
            }
            if (infoDetails.getNationality().equals("")) {
                tvShipCjg.setText(infoDetails.getPortOfRegistry());
            } else if (infoDetails.getPortOfRegistry().equals("")) {
                tvShipCjg.setText(infoDetails.getNationality());
            } else if (!infoDetails.getNationality().equals("") && !infoDetails.getNationality().equals("")) {
                tvShipCjg.setText(infoDetails.getNationality() + "/" + infoDetails.getPortOfRegistry());
            }
            if ("".equals(tvShipCjg.getText().toString().trim())) {
                tvShipCjg.setFocusable(true);
            } else {
                tvShipCjg.setFocusable(false);
            }
            tvShipZl.setText(infoDetails.getShipType());
            if ("".equals(tvShipZl.getText().toString().trim())) {
                tvShipZl.setFocusable(true);
            } else {
                tvShipZl.setFocusable(false);
            }
            if (!infoDetails.getShipImo().equals("") && !infoDetails.getIdentificationNumber().equals("")) {
                tvShipImo.setText(infoDetails.getShipImo() + "/" + infoDetails.getIdentificationNumber());
            } else if (infoDetails.getShipImo().equals("")) {
                tvShipImo.setText(infoDetails.getIdentificationNumber());
            } else if (infoDetails.getIdentificationNumber().equals("")) {
                tvShipImo.setText(infoDetails.getShipImo());
            }
            if ("".equals(tvShipImo.getText().toString().trim())) {
                tvShipImo.setFocusable(true);
            } else {
                tvShipImo.setFocusable(false);
            }
            tvShipBh.setText(infoDetails.getShipNo());
            if ("".equals(tvShipBh.getText().toString().trim())) {
                tvShipBh.setFocusable(true);
            } else {
                tvShipBh.setFocusable(false);
            }
            if (infoDetails.getShipCompanyName().equals("") || infoDetails.getShipCompanyName() == null) {
                tvShipGlr.setText(infoDetails.getShipOperator());
            } else {
                tvShipGlr.setText(infoDetails.getShipCompanyName());
            }
            if ("".equals(tvShipGlr.getText().toString().trim())) {
                tvShipGlr.setFocusable(true);
            } else {
                tvShipGlr.setFocusable(false);
            }
            tvShipCzxm.setText(infoDetails.getBerthName());
            if ("".equals(tvShipCzxm.getText().toString().trim())) {
                tvShipCzxm.setFocusable(true);
            } else {
                tvShipCzxm.setFocusable(false);
            }
        }
    }

    private static class MyHandler extends Handler {

        WeakReference<FuChaAnJianActivity> fuChaAnJianActivityWeakReference;

        MyHandler(FuChaAnJianActivity fuChaAnJianActivity) {
            this.fuChaAnJianActivityWeakReference = new WeakReference<>(fuChaAnJianActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final FuChaAnJianActivity fuChaAnJianActivity = fuChaAnJianActivityWeakReference.get();
            int what = msg.what;
            if (what == 500) {
                DialogUtils.showDialog(fuChaAnJianActivity, "温馨提示", "上传成功！是否打印船旗国监督检查报告！", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        File file = new File(fuChaAnJianActivity.namepath);
                        if (file.exists()) {
                            //直接打印
                            Intent intent = new Intent(fuChaAnJianActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2", fuChaAnJianActivity.namepath);
                            fuChaAnJianActivity.startActivity(intent);
                        } else {
                            fuChaAnJianActivity.downloadPDF();
                            fuChaAnJianActivity.downloadDialog.show();
                            fuChaAnJianActivity.httpDownManager.startDown(fuChaAnJianActivity.apkApi);
                        }
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        fuChaAnJianActivity.finish();
                    }
                });
            }
//            if (what == 600) {
//                ToastUtils.showToast(FuChaAnJianActivity.this, fuChaAnJianActivity.sub_msg);
//            }
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
        String name = tvShipName.getText().toString() + "_" + ispNO + ".pdf";
        namepath = path + name;
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/tFlagStateControlRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(FuChaAnJianActivity.this, Activity_PrintPdf.class);
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
