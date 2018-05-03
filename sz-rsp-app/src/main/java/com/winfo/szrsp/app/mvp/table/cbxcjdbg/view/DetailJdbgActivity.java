package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter.jdbgPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: DetailJdbgActivity
 * @Author: lsj
 * @Date: 2017/12/8  10:22
 * @Description:
 * @Version:船舶现场监督报告详情页面
 */
public class DetailJdbgActivity extends Activity implements IJdbgActivity {
    private View titie_view;
    private ImageButton back_btn;
    private TextView tv_title;
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
    private EditText edt_zfry;
    private TextView edt_czxm;
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
    private TextView tv_jcsj;
    private TextView tv_jcy_no;
    private LinearLayout wtms_layout;
    private LinearLayout jcxm_layout;
    private DetailjdbgwtmsLayout jdbgwtmsLayout;
    private DetailjdbgjcxmLayout jdbgjcxmLayout;
    private jdbgPresenter presenter;
    private ScrollView ll_sc;
    private View faile_view;
    private TextView tv_faile;
    private Button btn_print;
    private Dialog downloadDialog;
    private String qm_path;
    private ImageView img_qm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdbg_detail);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(namepath);
                if (file.exists()) {
                    //直接打印
                    Intent intent = new Intent(DetailJdbgActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2", namepath);
                    startActivity(intent);
                } else {
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi);
                }
            }
        });
    }

    private void initData() {
        tv_title.setText("船舶现场监督报告");
        String id = getIntent().getStringExtra("cbxcShipNo");
        presenter.findData(id);
    }

    private void initView() {
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        presenter = new jdbgPresenter(this);
        titie_view = findViewById(R.id.title);
        back_btn = titie_view.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = titie_view.findViewById(R.id.table_titleBar_titleText);
        edt_cm = findViewById(R.id.edt_cm);
        edt_gj = findViewById(R.id.edt_gj);
        edt_cbzl = findViewById(R.id.edt_cbzl);
        edt_imo = findViewById(R.id.edt_imo);
        edt_cbbh = findViewById(R.id.edt_cbbh);
        img_qm = findViewById(R.id.img_cz_qm);
        edt_cbglr = findViewById(R.id.edt_cbglr);
        ck_zxjc = findViewById(R.id.ck_zxjc);
        edt_zxjc = findViewById(R.id.edt_zxjc);
        ck_aqjc = findViewById(R.id.ck_qdcbaqjc);
        edt_jcjg = findViewById(R.id.edt_jcjg);
        edt_zfry = findViewById(R.id.edt_zfry);
        edt_czxm = findViewById(R.id.edt_czxm);
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
        tv_jcsj = findViewById(R.id.tv_sj);
        wtms_layout = findViewById(R.id.wtms_layout);
        jcxm_layout = findViewById(R.id.jcxm_layout);
        ll_sc = findViewById(R.id.ll_sc);
        faile_view = findViewById(R.id.view_faile);
        tv_faile = faile_view.findViewById(R.id.id_tv_data_load_faild);
        btn_print = findViewById(R.id.btn_save);
        tv_jcy_no = findViewById(R.id.tv_jcy_no);
    }

    @Override
    public Dialog getDialog() {
        Dialog dialog = DialogUtils.createLoadingDialog(this, "加载中...");
        return dialog;
    }

    @Override
    public void OnSuccess(String msg) {

    }

    @Override
    public void OnFaile(String msg) {
        ll_sc.setVisibility(View.GONE);
        faile_view.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
        faile_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getIntent().getStringExtra("cbxcShipNo");
                presenter.findData(id);
            }
        });
    }

    @Override
    public void setDetailData(DetailcbxcjdbgData detailData) {
        ll_sc.setVisibility(View.VISIBLE);
        faile_view.setVisibility(View.GONE);
        edt_czxm.setText(detailData.getTsite().getCaptainName());
        edt_bowei.setText(detailData.getTsite().getBerthName());
        edt_cm.setText(detailData.getTsite().getShipNameCn());
        edt_gj.setText(detailData.getTsite().getRegportName());
        edt_cbzl.setText(detailData.getTsite().getShipTypeNameCn());
        edt_imo.setText(detailData.getTsite().getShipId());
        edt_cbbh.setText(detailData.getTsite().getShipNo());
        edt_cbglr.setText(detailData.getTsite().getShipManager());
        if (detailData.getTsite().getIsSpecialInspect().equals("1")) {
            ck_zxjc.setChecked(true);
        } else {
            ck_zxjc.setChecked(false);
        }
        edt_zxjc.setText(detailData.getTsite().getSpecialInspectName());
        edt_zxjc.setText(detailData.getTsite().getSpecialInspectName());
//        edt_zxjc.setText(detailData.getTsite().getSpecialInspectType());
        edt_jcjg.setText(detailData.getTsite().getInspectOrg());
        edt_zfry.setText(detailData.getTsite().getInspectorName());
        tv_jcy_no.setText(detailData.getTsite().getInspectorCode());
        tv_jcsj.setText(detailData.getTsite().getInspectDate());


        if (detailData.getTsite().getIsInspect().equals("1")) {
            ck_aqjc.setChecked(true);
        }

        for (int i = 0; i < detailData.getListNew().size(); i++) {
            jdbgjcxmLayout = new DetailjdbgjcxmLayout(this, detailData.getListNew().get(i).getSeqNo(), detailData.getListNew().get(i).getContentDesc(), detailData.getListNew().get(i).getInspectionResult());
            jcxm_layout.addView(jdbgjcxmLayout);
        }


        for (int i = 0; i < detailData.getList().size(); i++) {
            jdbgwtmsLayout = new DetailjdbgwtmsLayout(this, detailData.getList().get(i));
            wtms_layout.addView(jdbgwtmsLayout);
        }
        downqm();
        downloadPDF();
    }

    @Override
    public void setShipBerthData(List<ShipBerthData> datas) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList) {

    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {

    }

    private void downqm() {
        qm_path = SDCardUtils.getRootDirectory() + "/szmsaec/";
        File dir = new File(qm_path);
        if (!dir.exists()) {
            //如果文件夹不存在 则创建
            dir.mkdirs();
        }
        String id = getIntent().getStringExtra("cbxcShipNo");
        String name = id + ".jpg";
        final String namepath = qm_path + name;
        File file = new File(namepath);
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(namepath, options);
            img_qm.setImageBitmap(bm);
        } else {
            Map<String, String> mapParams = new HashMap<>();
            mapParams.put("inspectNo", id);
            final String params = FastJsonUtil.mapToJsonStr(mapParams);
            String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
            if (access_token == null || access_token.length() == 0) {
                access_token = "5B3F7BB51BE4C055E050007F0100E58F";
            }
            File outputFile = new File(namepath);
            apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/tSiteSupervisionRestService/downAutograph?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(namepath, options);
                    img_qm.setImageBitmap(bm);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    ToastUtils.showToast(DetailJdbgActivity.this, "签名加载失败！");
                }

                @Override
                public void updateProgress(long readLength, long countLength) {

                }
            });
            httpDownManager.startDown(apkApi);
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
        String id = getIntent().getStringExtra("cbxcShipNo");
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }
        String name = edt_cm.getText() + "_" + id + ".pdf";
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
                Intent intent = new Intent(DetailJdbgActivity.this, Activity_PrintPdf.class);
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
