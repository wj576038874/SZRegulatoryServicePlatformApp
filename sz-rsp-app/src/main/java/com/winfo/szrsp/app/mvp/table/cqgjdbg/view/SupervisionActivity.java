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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
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

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.view
 * @Filename: SupervisionActivity
 * @Author: lsj
 * @Date: 2017/12/2  14:58
 * @Description:
 * @Version:船旗国监督检查报告
 */
public class SupervisionActivity extends Activity implements View.OnClickListener, ISupervisionActivity {
    private View titie_view;
    private ImageButton back_btn;
    private TextView tv_title;
    private LinearLayout ll_layout;
    private cljdLayout cljdLayout;
    private ImageView img_add;
    private ImageView img_del;
    private ImageView qm_img;
    private TextView tv_qm;
    private String qm_path;

    private EditText edt_cm;
    private EditText edt_gj;
    private EditText edt_cbzl;
    private EditText edt_imo;
    private EditText edt_cbbh;
    private EditText edt_cbglr;
    private CheckBox ck_zxjc;
    private TextView edt_zxjc;
    private CheckBox ck_1;
    private CheckBox ck_2;
    private CheckBox ck_3;
    private CheckBox ck_4;
    private CheckBox ck_5;
    private EditText edt_jcjg;
    private EditText edt_fcqz1;
    private EditText edt_fcqz2;
    private Button btn_save;
    private EditText edt_czxm;

    private ImageView jcry_qm_img;
    private TextView jcry_tv_qm;
    private String jcry_qm_path;
    private TextView tv_jcsj;
    private TimePickerView timePickerView;
    private supervisionPresenter presenter;
    private Dialog downloadDialog;
    private List<DefectCode> defectCodes;
    private MyHandler myHandler;
    private String specialInspectType = "0000000001";
    private ZXJCDialog zxjcDialog;

    private List<SecurityInspectorInformation> securityInspectorInformationList;

    private TextView tv_find_jcry;
    private TextView tv_jcry_no;
    private InspectorInfoDialog inspectorInfoDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        myHandler = new MyHandler(this);
        downloadDialog = DialogUtils.createLoadingDialog(this, "操作中...");
        presenter = new supervisionPresenter(this);
        titie_view = findViewById(R.id.title);
        titie_view.setVisibility(View.VISIBLE);
        back_btn = titie_view.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = titie_view.findViewById(R.id.table_titleBar_titleText);
        img_add = findViewById(R.id.add_img);
        img_del = findViewById(R.id.del_img);
        qm_img = findViewById(R.id.qm_img);
        tv_qm = findViewById(R.id.tv_qm);
        jcry_qm_img = findViewById(R.id.img_jcry_qm);
        jcry_tv_qm = findViewById(R.id.tv_jcry);
        ll_layout = findViewById(R.id.cljd_layout);
        tv_jcsj = findViewById(R.id.tv_jcsj);
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        edt_cm = findViewById(R.id.edt_cm);
        edt_gj = findViewById(R.id.edt_gj);
        edt_cbzl = findViewById(R.id.edt_cbzl);
        edt_imo = findViewById(R.id.edt_imo);
        edt_cbbh = findViewById(R.id.edt_cbbh);
        edt_cbglr = findViewById(R.id.edt_cbglr);
        ck_zxjc = findViewById(R.id.ck_zxjc);
        edt_zxjc = findViewById(R.id.edt_zxjc);
        ck_1 = findViewById(R.id.ck_1);
        ck_2 = findViewById(R.id.ck_2);
        ck_3 = findViewById(R.id.ck_3);
        ck_4 = findViewById(R.id.ck_4);
        ck_5 = findViewById(R.id.ck_5);
        edt_jcjg = findViewById(R.id.edt_jcjg);
        edt_fcqz1 = findViewById(R.id.edt_fcqz);
        edt_fcqz2 = findViewById(R.id.edt_fcqz2);
        btn_save = findViewById(R.id.btn_save);
        tv_find_jcry = findViewById(R.id.tv_find_jcry);
        tv_jcry_no = findViewById(R.id.tv_jcry_no);
        edt_czxm = findViewById(R.id.edt_czxm);
    }

    private void initData() {
        securityInspectorInformationList = new ArrayList<>();
        tv_title.setText("船旗国监督检查报告");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_jcsj.setText(time);
        presenter.getDefectCodeData();
        String orgName = ACache.get(this).getAsString("orgName");
        edt_jcjg.setText(orgName);
    }

    private void initEvent() {
        img_add.setOnClickListener(this);
        img_del.setOnClickListener(this);
        qm_img.setOnClickListener(this);
        tv_qm.setOnClickListener(this);
        jcry_qm_img.setOnClickListener(this);
        jcry_tv_qm.setOnClickListener(this);
        tv_jcsj.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        edt_zxjc.setOnClickListener(this);
        tv_find_jcry.setOnClickListener(this);

//        ck_zxjc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    edt_zxjc.setEnabled(true);
//                }else {
//                    edt_zxjc.setEnabled(false);
//                }
//            }
//        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_find_jcry:
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
            case R.id.tv_qm:
                writeLine(101, qm_img, tv_qm);
                break;
            case R.id.qm_img:
                showQMDialog(qm_path, 101);
                break;
            case R.id.tv_jcry:
                writeLine(102, jcry_qm_img, jcry_tv_qm);
                break;
            case R.id.img_jcry_qm:
                showQMDialog(jcry_qm_path, 102);
                break;
            case R.id.add_img:
                if (defectCodes != null) {
                    cljdLayout = new cljdLayout(this, "", "", defectCodes);
                    ll_layout.addView(cljdLayout);
                }
                break;
            case R.id.del_img:
                if (ll_layout.getChildCount() != 0) {
                    ll_layout.removeViewAt(ll_layout.getChildCount() - 1);
                }
                break;
            case R.id.edt_zxjc:
                showZXJCDialog(edt_zxjc);
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
                    info.setInitialInspectMark("0");//设置初查复查   初查0   复查1
                    info.setInspectorName(tv_find_jcry.getText().toString());
                    if (tv_jcry_no.getText().toString().contains("，")) {
                        tv_jcry_no.getText().toString().replace("，", ",");
                    }
                    info.setInspectorCode(tv_jcry_no.getText().toString());
                    info.setShipNameCn(edt_cm.getText().toString());
                    info.setRegportName(edt_gj.getText().toString());
                    info.setShipTypeNameCn(edt_cbzl.getText().toString());
                    info.setShopId(edt_imo.getText().toString());
                    info.setShipNo(edt_cbbh.getText().toString());
                    info.setCaptainName(edt_czxm.getText().toString());
                    info.setShipManager(edt_cbglr.getText().toString());
                    if (ck_zxjc.isChecked()) {
                        info.setIsSpecialInspect("1");
                    } else {
                        info.setIsSpecialInspect("0");
                    }
                    info.setSpecialInspectName(edt_zxjc.getText().toString());
                    info.setSpecialInspectType(specialInspectType);
                    List<String> strings = new ArrayList<>();
                    if (ck_1.isChecked()) {
                        strings.add("40");
                    } else {
                        strings.remove("40");
                    }
                    if (ck_2.isChecked()) {
                        strings.add("50");
                    } else {
                        strings.remove("50");
                    }
                    if (ck_3.isChecked()) {
                        strings.add("70");
                    } else {
                        strings.remove("70");
                    }
                    if (ck_4.isChecked()) {
                        strings.add("152");
                    } else {
                        strings.remove("152");
                    }
                    if (ck_5.isChecked()) {
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
                    for (int i = 0; i < ll_layout.getChildCount(); i++) {
                        cljdLayout cljdLayout = (cljdLayout) ll_layout.getChildAt(i);
                        if (cljdLayout.getData() == null) {
                            return;
                        }
                        detail.add(cljdLayout.getData());
                    }
                    if (qm_path == null || jcry_qm_path == null) {
                        ToastUtils.showToast(SupervisionActivity.this, "请签名！");
                    } else {
                        info.setWidth(qm_img.getWidth());
                        info.setHeight(qm_img.getHeight());
                        info.setInspectorWidth(jcry_qm_img.getWidth());
                        info.setInspectorHeight(jcry_qm_img.getHeight());
                        info.setPortCode("440266");
                        data.setInfo(info);
                        data.setDetail(detail);
                        presenter.subData(data, qm_path, jcry_qm_path);
                    }
//                    data.setInfo(info);
//                    data.setDetail(detail);
//                    presenter.subData(data,qm_path,jcry_qm_path);
                }
                break;
        }
    }

    private void showInspectorInfoDialog() {
        inspectorInfoDialog.showCheckBoxDialog();

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
                if (result == 101) {
                    writeLine(101, qm_img, tv_qm);
                } else {
                    writeLine(102, jcry_qm_img, jcry_tv_qm);
                }
                qm_dialog.dismiss();
            }
        });
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
                    tv_find_jcry.setText("");
                    tv_jcry_no.setText("");
                }

            }
        });
        showInspectorInfoDialog();
    }

    private String sub_msg;

    @Override
    public void onFaile(String msg) {
        this.sub_msg = msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public void setData(DetailjdbgData data) {
    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {
        this.defectCodes = defectCodes;
        cljdLayout = new cljdLayout(this, "", "", defectCodes);
        ll_layout.addView(cljdLayout);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {

    }

    private static class MyHandler extends Handler {

        WeakReference<SupervisionActivity> supervisionActivityWeakReference;

        MyHandler(SupervisionActivity supervisionActivity) {
            this.supervisionActivityWeakReference = new WeakReference<>(supervisionActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final SupervisionActivity supervisionActivity = supervisionActivityWeakReference.get();
            int what = msg.what;
            if (what == 500) {
                DialogUtils.showDialog(supervisionActivity, "温馨提示", "上传成功！是否打印船旗国监督检查报告！", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        File file = new File(supervisionActivity.namepath);
                        if (file.exists()) {
                            //直接打印
                            Intent intent = new Intent(supervisionActivity, Activity_PrintPdf.class);
                            intent.putExtra("namepath2", supervisionActivity.namepath);
                            supervisionActivity.startActivity(intent);
                        } else {
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
            if (what == 600) {
                ToastUtils.showToast(supervisionActivity, supervisionActivity.sub_msg);
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
                Intent intent = new Intent(SupervisionActivity.this, Activity_PrintPdf.class);
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
