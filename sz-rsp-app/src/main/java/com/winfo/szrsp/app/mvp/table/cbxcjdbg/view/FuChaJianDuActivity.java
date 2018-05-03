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
import com.winfo.szrsp.app.mvp.shipinfo.view.ShipInspectSupervisionInfoLayout;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter.jdbgPresenter;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.InspectorInfoDialog;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.table.TSiteSupervisionList;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
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
 * 现场监督复查表
 * Created by HoBo on 2018/4/24.
 */

public class FuChaJianDuActivity extends Activity implements View.OnClickListener, IJdbgActivity {
    @BindView(R.id.title)
    View titie_view;
    @BindView(R.id.tv_jcy_no)
    EditText tv_jcy_no;
    @BindView(R.id.edt_czxm)
    EditText edt_czxm;
    @BindView(R.id.edt_cm)
    EditText edt_cm;
    @BindView(R.id.edt_gj)
    EditText edt_gj;
    @BindView(R.id.edt_cbzl)
    EditText edt_cbzl;
    @BindView(R.id.edt_imo)
    EditText edt_imo;
    @BindView(R.id.edt_cbbh)
    EditText edt_cbbh;
    @BindView(R.id.edt_cbglr)
    EditText edt_cbglr;
    @BindView(R.id.ck_zxjc)
    CheckBox ck_zxjc;
    @BindView(R.id.edt_zxjc)
    EditText edt_zxjc;
    @BindView(R.id.ck_qdcbaqjc)
    CheckBox ck_aqjc;
    @BindView(R.id.edt_jcjg)
    EditText edt_jcjg;
    @BindView(R.id.tv_zfry)
    EditText tv_zfry;
    @BindView(R.id.edt_bowei)
    TextView edt_bowei;
    @BindView(R.id.edt_cz_qm)
    TextView edt_cz_qm;
    @BindView(R.id.cz_qm)
    ImageView img_qm;
    @BindView(R.id.tv_sj)
    TextView tv_jcsj;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.jcxmLin)
    LinearLayout jcxmLin;
    @BindView(R.id.wtms_layout)
    LinearLayout wtms_layout;
    String qm_path;


    private TextView tv_title;
    private ImageButton back_btn;
    private jdbgPresenter presenter;
    private Dialog dialog;
    private Dialog downloadDialog;
    private ShipBerthDialog shipBerthDialog;
    String selectFuseName = "", selectFuseId = "";
    private List<SecurityInspectorInformation> securityInspectorInformationList;
    private InspectorInfoDialog inspectorInfoDialog;
    private TimePickerView timePickerView;
    private String mmsi, cm;
    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdbg_fucha);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getBerthData(dialog, "", false);
    }

    private List<ShipXianChangInfo.SiteSupervisionDetailBean> xcDetailBeanList;
    private ShipXianChangInfo.SiteSupervisionReportListBean siteSupervisionReportListBean;

    private void initData() {
        securityInspectorInformationList = new ArrayList<>();
        siteSupervisionReportListBean = (ShipXianChangInfo.SiteSupervisionReportListBean) getIntent().getSerializableExtra("siteSupervisionReportListBean");
        xcDetailBeanList = (List<ShipXianChangInfo.SiteSupervisionDetailBean>) getIntent().getSerializableExtra("xcDetailBeanList");
        mmsi = getIntent().getStringExtra("mmsi");
        cm = getIntent().getStringExtra("cm");
        presenter.findShipInfo(mmsi, cm);
        edt_jcjg.setText(ACache.get(this).getAsString("orgName"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_jcsj.setText(time);
        if ("1".equals(siteSupervisionReportListBean.getIs_special_inspect())) {
            ck_zxjc.setChecked(true);
        } else if ("0".equals(siteSupervisionReportListBean.getIs_special_inspect())) {
            ck_zxjc.setChecked(false);
        } else {
            ck_zxjc.setChecked(false);
        }

        if (xcDetailBeanList.size() > 0) {
            for (int i = 0; i < xcDetailBeanList.size(); i++) {
                if (siteSupervisionReportListBean.getInspect_no().equals(xcDetailBeanList.get(i).getInspect_no())) {
                    jdbgjcxmLayout2 jdbgjcxmLayout = new jdbgjcxmLayout2(this, xcDetailBeanList.get(i));
                    jcxmLin.addView(jdbgjcxmLayout);
                    if (xcDetailBeanList.get(i).getResult().equals("0")) {
                        jdbgwtmsLayout2 jdbgwtmsLayout = new jdbgwtmsLayout2(this, xcDetailBeanList.get(i));
                        wtms_layout.addView(jdbgwtmsLayout);
                    }
                }
            }
        }
    }

    private void initView() {
        myHandler = new MyHandler(this);
        back_btn = titie_view.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = titie_view.findViewById(R.id.table_titleBar_titleText);
        tv_title.setText("船舶现场监督复查报告");

        downloadDialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        presenter = new jdbgPresenter(this);

    }

    private void initEvent() {
        back_btn.setOnClickListener(this);
        edt_cz_qm.setOnClickListener(this);
        img_qm.setOnClickListener(this);
        edt_bowei.setOnClickListener(this);
        tv_zfry.setOnClickListener(this);
        tv_jcsj.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
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
            case R.id.tv_zfry:
                if (securityInspectorInformationList.size() > 0) {
                    showInspectorInfoDialog();
                } else {
                    String uuid = ACache.get(this).getAsString("uuid");
                    presenter.getInspectors(uuid);
                }
                break;
            case R.id.cz_qm:
                showQMDialog(qm_path, 101);
                break;
            case R.id.edt_cz_qm:
                writeLine();
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
            case R.id.btn_save:
                if (edt_cm.getText().toString().trim().equals("") ||
                        edt_gj.getText().toString().trim().equals("") ||
                        edt_cbzl.getText().toString().trim().equals("") ||
                        edt_imo.getText().toString().trim().equals("") ||
                        edt_cbbh.getText().toString().trim().equals("") ||
                        edt_cbglr.getText().toString().trim().equals("") ||
                        edt_czxm.getText().toString().trim().equals("") ||
                        edt_jcjg.getText().toString().trim().equals("")
//                        ||edt_bowei.getText().toString().trim().equals("")
                        ) {
                    ToastUtils.showToast(this, "请填写完表格再提交！");
                } else if (!ACache.get(this).getAsString("userNum").contains("test") && !ACache.get(this).getAsString("userNum").contains("winfo")) {
                    if (tv_zfry.getText().toString().equals("")) {
                        ToastUtils.showToast(this, "请选择执法员");
                    } else if (tv_jcy_no.getText().toString().trim().equals("")) {
                        ToastUtils.showToast(this, "请输入执法证号");
                    }
                } else {
                    cbxcjdbgData cbxcjdbgData = new cbxcjdbgData();
                    cbxcjdbgData.info info = new cbxcjdbgData.info();
                    List<cbxcjdbgData.detail> detail = new ArrayList<>();
                    int correctMark = 0;
                    for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                        jdbgwtmsLayout2 jdbgwtmsLayout2 = (jdbgwtmsLayout2) wtms_layout.getChildAt(i);
                        if (jdbgwtmsLayout2.getData() == null) {
                            return;
                        } else {
                            cbxcjdbgData.detail detail1 = jdbgwtmsLayout2.getData();
                            if (detail1.getCorrectMark().equals("0")) {
                                correctMark++;
                            }
                            detail.add(detail1);
                        }
                    }

                    info.setUnclosedDefectNum(correctMark);

                    List<TSiteSupervisionList> lists = new ArrayList<>();
                    for (int i = 0; i < jcxmLin.getChildCount(); i++) {
                        jdbgjcxmLayout2 jdbgjcxmLayout2 = (jdbgjcxmLayout2) jcxmLin.getChildAt(i);
                        if (jdbgjcxmLayout2.getData() == null) {
                            return;
                        } else {
                            lists.add(jdbgjcxmLayout2.getData());
                        }
                    }
                    cbxcjdbgData.setList(lists);
                    info.setInitialInspectMark("0");//设置初查复查   初查0   复查1
                    info.setInitialInspectNo(siteSupervisionReportListBean.getInspect_no());
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
                    info.setNum(jcxmLin.getChildCount() + "");
                    info.setInspectorName(tv_zfry.getText().toString());
//                    info.setIsInspect(isStart);
                    if (qm_path != null) {
                        info.setWidth(img_qm.getWidth());
                        info.setHeight(img_qm.getHeight());
                        cbxcjdbgData.setInfo(info);
                        cbxcjdbgData.setDetail(detail);
                        presenter.subData(qm_path, cbxcjdbgData);
                    } else {
                        ToastUtils.showToast(this, "请签名！");
                    }
                }
                break;
        }
    }

    private void showInspectorInfoDialog() {
        inspectorInfoDialog.showCheckBoxDialog();

    }

    private List<ShipBerthData> shipBerthData;

    private List<ShipBerthData> getData() {
        return shipBerthData;
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
    private String sub_msg;

    @Override
    public void OnSuccess(String msg) {
        ispNO = msg;
        this.namepath = "";
        myHandler.sendEmptyMessage(500);
    }

    @Override
    public void OnFaile(String msg) {
        this.sub_msg = msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public void setDetailData(DetailcbxcjdbgData detailData) {

    }

    @Override
    public void setShipBerthData(List<ShipBerthData> datas) {
        this.shipBerthData = bulid(datas);
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
                    tv_zfry.setText("点击查找");
                    tv_jcy_no.setText("");
                }
            }
        });
        showInspectorInfoDialog();
    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {
        dialog.dismiss();
        if (infoDetails != null) {
            String cm = "";
            if (infoDetails.getShipNameCn().equals("")) {
                cm = infoDetails.getShipNameEn();
            } else {
                cm = infoDetails.getShipNameCn();
            }
            edt_cm.setText(cm);
            if ("".equals(edt_cm.getText().toString().trim())) {
                edt_cm.setFocusable(true);
            } else {
                edt_cm.setFocusable(false);
            }
            if (infoDetails.getNationality().equals("")) {
                edt_gj.setText(infoDetails.getPortOfRegistry());
            } else if (infoDetails.getPortOfRegistry().equals("")) {
                edt_gj.setText(infoDetails.getNationality());
            } else if (!infoDetails.getNationality().equals("") && !infoDetails.getNationality().equals("")) {
                edt_gj.setText(infoDetails.getNationality() + "/" + infoDetails.getPortOfRegistry());
            }
            if ("".equals(edt_gj.getText().toString().trim())) {
                edt_gj.setFocusable(true);
            } else {
                edt_gj.setFocusable(false);
            }
            edt_cbzl.setText(infoDetails.getShipType());
            if ("".equals(edt_cbzl.getText().toString().trim())) {
                edt_cbzl.setFocusable(true);
            } else {
                edt_cbzl.setFocusable(false);
            }
//            if (!infoDetails.getShipImo().equals("") && !infoDetails.getIdentificationNumber().equals("")) {
//                edt_imo.setText(infoDetails.getShipImo() + "/" + infoDetails.getIdentificationNumber());
//            } else if (infoDetails.getShipImo().equals("")) {
//                edt_imo.setText(infoDetails.getIdentificationNumber());
//            } else if (infoDetails.getIdentificationNumber().equals("")) {
//                edt_imo.setText(infoDetails.getShipImo());
//            }
            edt_imo.setText(infoDetails.getIdentificationNumber());
            if ("".equals(edt_imo.getText().toString().trim())) {
                edt_imo.setFocusable(true);
            } else {
                edt_imo.setFocusable(false);
            }
            edt_cbbh.setText(infoDetails.getShipNo());
            if ("".equals(edt_cbbh.getText().toString().trim())) {
                edt_cbbh.setFocusable(true);
            } else {
                edt_cbbh.setFocusable(false);
            }
            if (infoDetails.getShipCompanyName().equals("") || infoDetails.getShipCompanyName() == null) {
                edt_cbglr.setText(infoDetails.getShipOperator());
            } else {
                edt_cbglr.setText(infoDetails.getShipCompanyName());
            }
            if ("".equals(edt_cbglr.getText().toString().trim())) {
                edt_cbglr.setFocusable(true);
            } else {
                edt_cbglr.setFocusable(false);
            }
            edt_czxm.setText(infoDetails.getBerthName());
            if ("".equals(edt_czxm.getText().toString().trim())) {
                edt_czxm.setFocusable(true);
            } else {
                edt_czxm.setFocusable(false);
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

        WeakReference<FuChaJianDuActivity> fuChaJianDuActivityWeakReference;

        MyHandler(FuChaJianDuActivity fuChaJianDuActivity) {
            this.fuChaJianDuActivityWeakReference = new WeakReference<>(fuChaJianDuActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final FuChaJianDuActivity fuChaJianDuActivity = fuChaJianDuActivityWeakReference.get();
            int what = msg.what;
            switch (what) {
                case 500:
                    DialogUtils.showDialog(fuChaJianDuActivity, "温馨提示", "上传成功！是否打印船舶现场监督复查报告！", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                        @Override
                        public void btnConfirmClick(Dialog dialog) {
                            File file = new File(fuChaJianDuActivity.namepath);
                            if (file.exists()) {
                                //直接打印
                                Intent intent = new Intent(fuChaJianDuActivity, Activity_PrintPdf.class);
                                intent.putExtra("namepath2", fuChaJianDuActivity.namepath);
                                fuChaJianDuActivity.startActivity(intent);
                            } else {
                                fuChaJianDuActivity.downloadPDF();
                                fuChaJianDuActivity.downloadDialog.show();
                                fuChaJianDuActivity.httpDownManager.startDown(fuChaJianDuActivity.apkApi);
                            }
                        }

                        @Override
                        public void btnCancelClick(Dialog dialog) {
                            fuChaJianDuActivity.finish();
                        }
                    });
                    break;

                case 600:
                    ToastUtils.showToast(fuChaJianDuActivity, fuChaJianDuActivity.sub_msg);
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
                Intent intent = new Intent(FuChaJianDuActivity.this, Activity_PrintPdf.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
    }
}
