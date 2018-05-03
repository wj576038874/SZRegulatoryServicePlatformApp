package com.winfo.szrsp.app.mvp.table.cbzy.view;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.DetailJdbgActivity;
import com.winfo.szrsp.app.mvp.table.cbzy.presenter.DangerousGoodsKaiXiangPresenter;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.DetailDangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 船舶载运危险货物/污染危害性货物集装箱开箱检查记录表详情
 * Created by HoBo on 2018/3/13.
 */

public class DetailDangerousGoodsKaiXiangActivity extends Activity implements IDangerousGoodsKaiXiangActivity {
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.et_ship_deeSea)
    TextView et_ship_deeSea;
    @BindView(R.id.et_ship_time)
    TextView et_ship_time;
    @BindView(R.id.et_ship_num)
    TextView et_ship_num;
    @BindView(R.id.et_packing_company)
    TextView et_packing_company;
    @BindView(R.id.et_packing_inspector)
    TextView et_packing_inspector;
    @BindView(R.id.et_packing_tel)
    TextView et_packing_tel;
    @BindView(R.id.et_carrier_ship)
    TextView et_carrier_ship;
    @BindView(R.id.et_carrier_people)
    TextView et_carrier_people;
    @BindView(R.id.et_voyage_number)
    TextView et_voyage_number;
    @BindView(R.id.et_consignment_people)
    TextView et_consignment_people;
    @BindView(R.id.et_order_num)
    TextView et_order_num;
    @BindView(R.id.et_box_num)
    TextView et_box_num;
    @BindView(R.id.ed_open_box_book_num)
    TextView ed_open_box_book_num;
    @BindView(R.id.et_inspect_before_num)
    TextView et_inspect_before_num;
    @BindView(R.id.et_inspect_after_num)
    TextView et_inspect_after_num;
    @BindView(R.id.et_goods_name)
    TextView et_goods_name;
    @BindView(R.id.et_un_num)
    TextView et_un_num;
    @BindView(R.id.et_packing_form)
    TextView et_packing_form;
    @BindView(R.id.et_goods_type)
    TextView et_goods_type;
    @BindView(R.id.et_goods_danger)
    TextView et_goods_danger;
    @BindView(R.id.et_goods_contaminants)
    TextView et_goods_contaminants;

    @BindView(R.id.ck_1_yes)
    CheckBox ck_1_yes;
    @BindView(R.id.ck_2_yes)
    CheckBox ck_2_yes;
    @BindView(R.id.ck_3_yes)
    CheckBox ck_3_yes;
    @BindView(R.id.ck_4_yes)
    CheckBox ck_4_yes;
    @BindView(R.id.ck_5_yes)
    CheckBox ck_5_yes;
    @BindView(R.id.ck_6_yes)
    CheckBox ck_6_yes;
    @BindView(R.id.ck_7_yes)
    CheckBox ck_7_yes;
    @BindView(R.id.ck_8_yes)
    CheckBox ck_8_yes;
    @BindView(R.id.ck_9_yes)
    CheckBox ck_9_yes;
    @BindView(R.id.ck_10_yes)
    CheckBox ck_10_yes;
    @BindView(R.id.ck_11_yes)
    CheckBox ck_11_yes;
    @BindView(R.id.ck_12_yes)
    CheckBox ck_12_yes;
    @BindView(R.id.ck_13_yes)
    CheckBox ck_13_yes;
    @BindView(R.id.ck_14_yes)
    CheckBox ck_14_yes;
    @BindView(R.id.ck_15_yes)
    CheckBox ck_15_yes;
    @BindView(R.id.ck_16_yes)
    CheckBox ck_16_yes;
    @BindView(R.id.ck_17_yes)
    CheckBox ck_17_yes;
    @BindView(R.id.ck_18_yes)
    CheckBox ck_18_yes;
    @BindView(R.id.ck_19_yes)
    CheckBox ck_19_yes;


    @BindView(R.id.ck_1_no)
    CheckBox ck_1_no;
    @BindView(R.id.ck_2_no)
    CheckBox ck_2_no;
    @BindView(R.id.ck_3_no)
    CheckBox ck_3_no;
    @BindView(R.id.ck_4_no)
    CheckBox ck_4_no;
    @BindView(R.id.ck_5_no)
    CheckBox ck_5_no;
    @BindView(R.id.ck_6_no)
    CheckBox ck_6_no;
    @BindView(R.id.ck_7_no)
    CheckBox ck_7_no;
    @BindView(R.id.ck_8_no)
    CheckBox ck_8_no;
    @BindView(R.id.ck_9_no)
    CheckBox ck_9_no;
    @BindView(R.id.ck_10_no)
    CheckBox ck_10_no;
    @BindView(R.id.ck_11_no)
    CheckBox ck_11_no;
    @BindView(R.id.ck_12_no)
    CheckBox ck_12_no;
    @BindView(R.id.ck_13_no)
    CheckBox ck_13_no;
    @BindView(R.id.ck_14_no)
    CheckBox ck_14_no;
    @BindView(R.id.ck_15_no)
    CheckBox ck_15_no;
    @BindView(R.id.ck_16_no)
    CheckBox ck_16_no;
    @BindView(R.id.ck_17_no)
    CheckBox ck_17_no;
    @BindView(R.id.ck_18_no)
    CheckBox ck_18_no;
    @BindView(R.id.ck_19_no)
    CheckBox ck_19_no;


    @BindView(R.id.et_exceptions_record)
    TextView et_exceptions_record;
    @BindView(R.id.ck_correct)
    CheckBox ck_correct;
    @BindView(R.id.ck_defects_exist)
    CheckBox ck_defects_exist;
    @BindView(R.id.ck_false)
    CheckBox ck_false;
    @BindView(R.id.ck_other)
    CheckBox ck_other;
    @BindView(R.id.et_other)
    TextView et_other;
    @BindView(R.id.et_process_results)
    TextView et_process_results;
    @BindView(R.id.et_zhifa_people1)
    TextView et_zhifa_people1;
    @BindView(R.id.et_zhifa_cardNum1)
    TextView et_zhifa_cardNum1;
    @BindView(R.id.et_autograph)
    TextView et_autograph;
    @BindView(R.id.img_autograph)
    ImageView img_autograph;
    @BindView(R.id.btn_save)
    Button btn_save;

    private Dialog dialog;
    private Dialog downloadDialog;
    private DangerousGoodsKaiXiangPresenter presenter;
    private String id;
    private String qm_path;
    String primaryKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbzy_wxhwkxjc_detail);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
        downqm(primaryKey, img_autograph);
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "加载中...");
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        presenter = new DangerousGoodsKaiXiangPresenter(this);

        table_titleBar_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        primaryKey = getIntent().getStringExtra("DetailDangerousGoodsKaiXiangData");
        presenter.findDataByPrimaryKey(primaryKey);
        id = primaryKey;
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "集装箱危险货物开箱检查记录表" + "_" + id + ".pdf";
                namepath = path + name;
                File file = new File(namepath);
                if (file.exists()) {
                    //直接打印
                    Intent intent = new Intent(DetailDangerousGoodsKaiXiangActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2", namepath);
                    startActivity(intent);
                    finish();
                } else {
                    downloadPDF();
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi);
                }
            }
        });
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSuccess(String s, String resultData) {

    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
        ToastUtils.showToast(this, msg);
    }

    @Override
    public DangerousGoodsKaiXiangData getData() {
        return null;
    }

    @Override
    public void setDetailData(String msg, DangerousGoodsKaiXiangData data) {
        dialog.dismiss();
        et_ship_inspectTime.setText(data.getInspectTime());
        et_ship_deeSea.setText(data.getDeepSea());
        et_ship_time.setText(data.getDeepSeaTime());
        et_ship_num.setText(data.getDeepSeaNum());
        et_packing_company.setText(data.getPackOrg());
        et_packing_inspector.setText(data.getPackInspector());
        et_packing_tel.setText(data.getTelephone());
        et_carrier_ship.setText(data.getCarrierShip());
        et_carrier_people.setText(data.getCarrierAgent());
        et_voyage_number.setText(data.getVoyageId());
        et_consignment_people.setText(data.getConsignAgent());
        et_order_num.setText(data.getBillNumber());
        et_box_num.setText(data.getCaseNumber());
        ed_open_box_book_num.setText(data.getOutInspectNotice());
        et_inspect_before_num.setText(data.getBeforeInspectNum());
        et_inspect_after_num.setText(data.getAfterInspectNum());
        et_goods_name.setText(data.getGoodName());
        et_un_num.setText(data.getUnNum());
        et_packing_form.setText(data.getPackType());
        et_goods_type.setText(data.getTypeName());
        et_goods_danger.setText(data.getDanger());
        et_goods_contaminants.setText(data.getPollute());
        if (data.getItemCase1().equals("1")) {
            ck_1_yes.setChecked(true);
        } else if (data.getItemCase1().equals("0")) {
            ck_1_no.setChecked(true);
        }

        if (data.getItemCase2().equals("1")) {
            ck_2_yes.setChecked(true);
        } else if (data.getItemCase2().equals("0")) {
            ck_2_no.setChecked(true);
        }

        if (data.getItemCase31().equals("1")) {
            ck_3_yes.setChecked(true);
        } else if (data.getItemCase31().equals("0")) {
            ck_3_no.setChecked(true);
        }

        if (data.getItemCase32().equals("1")) {
            ck_4_yes.setChecked(true);
        } else if (data.getItemCase32().equals("0")) {
            ck_4_no.setChecked(true);
        }

        if (data.getItemCase33().equals("1")) {
            ck_5_yes.setChecked(true);
        } else if (data.getItemCase33().equals("0")) {
            ck_5_no.setChecked(true);
        }

        if (data.getItemCase34().equals("1")) {
            ck_6_yes.setChecked(true);
        } else if (data.getItemCase34().equals("0")) {
            ck_6_no.setChecked(true);
        }

        if (data.getItemCase41().equals("1")) {
            ck_7_yes.setChecked(true);
        } else if (data.getItemCase41().equals("0")) {
            ck_7_no.setChecked(true);
        }

        if (data.getItemCase42().equals("1")) {
            ck_8_yes.setChecked(true);
        } else if (data.getItemCase42().equals("0")) {
            ck_8_no.setChecked(true);
        }

        if (data.getItemDanger1().equals("1")) {
            ck_9_yes.setChecked(true);
        } else if (data.getItemDanger1().equals("0")) {
            ck_9_no.setChecked(true);
        }

        if (data.getItemDanger2().equals("1")) {
            ck_10_yes.setChecked(true);
        } else if (data.getItemDanger2().equals("0")) {
            ck_10_no.setChecked(true);
        }

        if (data.getItemDanger3().equals("1")) {
            ck_11_yes.setChecked(true);
        } else if (data.getItemDanger3().equals("0")) {
            ck_11_no.setChecked(true);
        }

        if (data.getItemDanger4().equals("1")) {
            ck_12_yes.setChecked(true);
        } else if (data.getItemDanger4().equals("0")) {
            ck_12_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_13_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_13_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_14_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_14_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_15_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_15_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_16_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_16_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_17_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_17_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_18_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_18_no.setChecked(true);
        }

        if (data.getItemPack1().equals("1")) {
            ck_19_yes.setChecked(true);
        } else if (data.getItemPack1().equals("0")) {
            ck_19_no.setChecked(true);
        }

        et_exceptions_record.setText(data.getExceptionDescribe());

        if (data.getItemHandle().equals("1")) {
            ck_correct.setChecked(true);
        } else if (data.getItemHandle().equals("2")) {
            ck_defects_exist.setChecked(true);
        } else if (data.getItemHandle().equals("3")) {
            ck_false.setChecked(true);
        } else if (data.getItemHandle().equals("4")) {
            ck_other.setChecked(true);
            et_other.setText(data.getRemark());
        }

        et_process_results.setText(data.getHandleResult());
        et_zhifa_people1.setText(data.getInspector());
        et_zhifa_cardNum1.setText(data.getInspectorCode());
//        et_autograph.setText(data.getConsignAgentName());

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    String path = SDCardUtils.getRootDirectory() + "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath = "";
    DownInfo apkApi;

    private void downloadPDF() {

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }


        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteOutRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(DetailDangerousGoodsKaiXiangActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2", namepath);
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

    //加载签名图片
    private void downqm(String id, ImageView imageView) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }
        String url = ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteOutRestService/downAutograph?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params;
        Glide.with(this).load(url).into(imageView);
    }
//    private void downqm() {
//        qm_path = SDCardUtils.getRootDirectory() + "/szmsaec/";
//        File dir = new File(qm_path);
//        if (!dir.exists()) {
//            //如果文件夹不存在 则创建
//            dir.mkdirs();
//        }
//        String id = getIntent().getStringExtra("DetailDangerousGoodsKaiXiangData");
//        String name = id + ".jpg";
//        final String namepath = qm_path + name;
//        File file = new File(namepath);
//        if (file.exists()) {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 2;
//            Bitmap bm = BitmapFactory.decodeFile(namepath, options);
//            img_autograph.setImageBitmap(bm);
//        } else {
//            Map<String, String> mapParams = new HashMap<>();
//            mapParams.put("inspectNo", id);
//            final String params = FastJsonUtil.mapToJsonStr(mapParams);
//            String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
//            if (access_token == null || access_token.length() == 0) {
//                access_token = "5B3F7BB51BE4C055E050007F0100E58F";
//            }
//            File outputFile = new File(namepath);
//            apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteOutRestService/downAutograph?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
//            apkApi.setSavePath(outputFile.getAbsolutePath());
//            apkApi.setListener(new HttpProgressOnNextListener() {
//                @Override
//                public void onNext(Object o) {
//                }
//
//                @Override
//                public void onStart() {
//                }
//
//                @Override
//                public void onComplete() {
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inSampleSize = 2;
//                    Bitmap bm = BitmapFactory.decodeFile(namepath, options);
//                    img_autograph.setImageBitmap(bm);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    super.onError(e);
//                    ToastUtils.showToast(DetailDangerousGoodsKaiXiangActivity.this, "签名加载失败！");
//                }
//
//                @Override
//                public void updateProgress(long readLength, long countLength) {
//
//                }
//            });
//            httpDownManager.startDown(apkApi);
//        }
//    }
}
