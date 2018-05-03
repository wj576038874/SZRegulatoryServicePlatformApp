package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.xhgzjl.presenter.CruiseWorkRecordPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HoBo on 2018/4/13.
 */

public class DetailCruisrWorkRecordActivity extends FragmentActivity implements ICruiseWorkRecordActivity {
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton imgBack;
    @BindView(R.id.etShipName)
    TextView etShipName;
    @BindView(R.id.etShipPeople)
    TextView etShipPeople;
    @BindView(R.id.etCruiseDate)
    TextView etCruiseDate;
    @BindView(R.id.etCruiseTime)
    TextView etCruiseTime;
    @BindView(R.id.etCruiseWeather)
    TextView etCruiseWeather;
    @BindView(R.id.etCruiseSea)
    TextView etCruiseSea;
    @BindView(R.id.ck_rcxh)
    CheckBox ck_rcxh;
    @BindView(R.id.ck_jjhh)
    CheckBox ck_jjhh;
    @BindView(R.id.ck_hsdc)
    CheckBox ck_hsdc;
    @BindView(R.id.ck_yjjy)
    CheckBox ck_yjjy;
    @BindView(R.id.ck_zs)
    CheckBox ck_zs;
    @BindView(R.id.ck_qt)
    CheckBox ck_qt;
    @BindView(R.id.ck_hq)
    CheckBox ck_hq;
    @BindView(R.id.ck_gq)
    CheckBox ck_gq;
    @BindView(R.id.ck_kxq)
    CheckBox ck_kxq;
    @BindView(R.id.etItem1)
    TextView etItem1;
    @BindView(R.id.etItem2)
    TextView etItem2;
    @BindView(R.id.etItem3)
    TextView etItem3;
    @BindView(R.id.etItem4)
    TextView etItem4;
    @BindView(R.id.etItem5)
    TextView etItem5;
    @BindView(R.id.etItem6)
    TextView etItem6;
    @BindView(R.id.etItem7)
    TextView etItem7;
    @BindView(R.id.etOther)
    TextView etOther;
    @BindView(R.id.ckItem1_0)
    CheckBox ckItem1_0;
    @BindView(R.id.ckItem1_1)
    CheckBox ckItem1_1;
    @BindView(R.id.ckItem1_2)
    CheckBox ckItem1_2;
    @BindView(R.id.ckItem2_0)
    CheckBox ckItem2_0;
    @BindView(R.id.ckItem2_1)
    CheckBox ckItem2_1;
    @BindView(R.id.ckItem2_2)
    CheckBox ckItem2_2;
    @BindView(R.id.ckItem3_0)
    CheckBox ckItem3_0;
    @BindView(R.id.ckItem3_1)
    CheckBox ckItem3_1;
    @BindView(R.id.ckItem3_2)
    CheckBox ckItem3_2;
    @BindView(R.id.ckItem4_0)
    CheckBox ckItem4_0;
    @BindView(R.id.ckItem4_1)
    CheckBox ckItem4_1;
    @BindView(R.id.ckItem4_2)
    CheckBox ckItem4_2;
    @BindView(R.id.ckItem5_0)
    CheckBox ckItem5_0;
    @BindView(R.id.ckItem5_1)
    CheckBox ckItem5_1;
    @BindView(R.id.ckItem5_2)
    CheckBox ckItem5_2;
    @BindView(R.id.ckItem6_0)
    CheckBox ckItem6_0;
    @BindView(R.id.ckItem6_1)
    CheckBox ckItem6_1;
    @BindView(R.id.ckItem6_2)
    CheckBox ckItem6_2;
    @BindView(R.id.ckItem7_0)
    CheckBox ckItem7_0;
    @BindView(R.id.ckItem7_1)
    CheckBox ckItem7_1;
    @BindView(R.id.ckItem7_2)
    CheckBox ckItem7_2;
    @BindView(R.id.ckItem8_0)
    CheckBox ckItem8_0;
    @BindView(R.id.ckItem8_1)
    CheckBox ckItem8_1;
    @BindView(R.id.ckItem8_2)
    CheckBox ckItem8_2;
    @BindView(R.id.etItemOther)
    TextView etItemOther;
    @BindView(R.id.etCruiseNum)
    TextView etCruiseNum;
    @BindView(R.id.etCruiseHour)
    TextView etCruiseHour;
    @BindView(R.id.etCruiseVoyage)
    TextView etCruiseVoyage;
    @BindView(R.id.etCruisePeopleNum)
    TextView etCruisePeopleNum;
    @BindView(R.id.etCruiseGarrisonNum)
    TextView etCruiseGarrisonNum;
    @BindView(R.id.etCruiseGarrisonTime)
    TextView etCruiseGarrisonTime;
    @BindView(R.id.etCruiseInspectShipNum)
    TextView etCruiseInspectShipNum;
    @BindView(R.id.etCruiseFindPeccancyNum)
    TextView etCruiseFindPeccancyNum;
    @BindView(R.id.etCruisePunishNum)
    TextView etCruisePunishNum;
    @BindView(R.id.etCruisePunishMoney)
    TextView etCruisePunishMoney;
    @BindView(R.id.etCruiseEscortNum)
    TextView etCruiseEscortNum;
    @BindView(R.id.etCruiseRescueNum)
    TextView etCruiseRescueNum;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.lin_xhjy)
    LinearLayout lin_xhjy;
    @BindView(R.id.lin_ycwt)
    LinearLayout lin_ycwt;
    @BindView(R.id.imgAdd1)
    ImageView imgAdd1;
    @BindView(R.id.imgDel1)
    ImageView imgDel1;
    @BindView(R.id.imgAdd2)
    ImageView imgAdd2;
    @BindView(R.id.imgDel2)
    ImageView imgDel2;

    private String id;
    private CruiseWorkRecordPresenter presenter;
    private Dialog dialog;
    private Dialog downloadDialog;
    private DetailCruiseSummaryLayout detailCruiseSummaryLayout;
    private DetailCruiseSituationLayout detailCruiseSituationLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_xhgzjl_table);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        presenter = new CruiseWorkRecordPresenter();
        presenter.attachMvpView(this);
    }

    private void initData() {
        String primaryKey = getIntent().getStringExtra("CruiseWorkData");
        presenter.findDataByPrimaryKey(primaryKey);
        id = primaryKey;
    }

    private void initEvent() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "巡航工作记录表" + "_" + id + ".pdf";
                namepath = path + name;
                File file = new File(namepath);
                if (file.exists()) {
                    //直接打印
                    Intent intent = new Intent(DetailCruisrWorkRecordActivity.this, Activity_PrintPdf.class);
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
        dialog.dismiss();
    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
    }

    @Override
    public CruiseWorkData getData() {
        return null;
    }

    @Override
    public void setDetailData(String msg, CruiseWorkData data) {
        etShipName.setText(data.getCtCruisingRecore().getShipName());
        etShipPeople.setText(data.getCtCruisingRecore().getLawEnforcementName());
        etCruiseDate.setText(data.getCtCruisingRecore().getDateYear() + "年" + data.getCtCruisingRecore().getDateMonth() + "月" + data.getCtCruisingRecore().getDateDay() + "日" + data.getCtCruisingRecore().getWeekNum());
        etCruiseTime.setText("从" + data.getCtCruisingRecore().getBeginTime() + "时到" + data.getCtCruisingRecore().getEndTime() + "时止");
        etCruiseWeather.setText(data.getCtCruisingRecore().getWeather());
        etCruiseSea.setText(data.getCtCruisingRecore().getSeaState());
        if (data.getCtCruisingRecore().getTemp1().equals("1")) {
            ck_rcxh.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp2().equals("1")) {
            ck_jjhh.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp3().equals("1")) {
            ck_hsdc.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp4().equals("1")) {
            ck_yjjy.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp5().equals("1")) {
            ck_zs.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp6().equals("1")) {
            ck_qt.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp7().equals("1")) {
            ck_hq.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp8().equals("1")) {
            ck_gq.setChecked(true);
        }
        if (data.getCtCruisingRecore().getTemp9().equals("1")) {
            ck_kxq.setChecked(true);
        }

        etItem1.setText(data.getCtCruisingRecore().getSeaRoute());
        etItem2.setText(data.getCtCruisingRecore().getAnchorageArea());
        etItem3.setText(data.getCtCruisingRecore().getRoadWorkArea());
        etItem4.setText(data.getCtCruisingRecore().getWharf());
        etItem5.setText(data.getCtCruisingRecore().getMudDumpingArea());
        etItem6.setText(data.getCtCruisingRecore().getRiskWharf());
        etItem7.setText(data.getCtCruisingRecore().getBridge());
        etOther.setText(data.getCtCruisingRecore().getOtherArea());

        if (data.getCtCruisingRecore().getItem1().equals("1")) {
            ckItem1_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem1().equals("2")) {
            ckItem1_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem1().equals("3")) {
            ckItem1_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem2().equals("1")) {
            ckItem2_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem2().equals("2")) {
            ckItem2_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem2().equals("3")) {
            ckItem2_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem3().equals("1")) {
            ckItem3_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem3().equals("2")) {
            ckItem3_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem3().equals("3")) {
            ckItem3_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem4().equals("1")) {
            ckItem4_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem4().equals("2")) {
            ckItem4_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem4().equals("3")) {
            ckItem4_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem5().equals("1")) {
            ckItem5_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem5().equals("2")) {
            ckItem5_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem5().equals("3")) {
            ckItem5_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem6().equals("1")) {
            ckItem6_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem6().equals("2")) {
            ckItem6_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem6().equals("3")) {
            ckItem6_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem7().equals("1")) {
            ckItem7_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem7().equals("2")) {
            ckItem7_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem7().equals("3")) {
            ckItem7_2.setChecked(true);
        }

        if (data.getCtCruisingRecore().getItem8().equals("1")) {
            ckItem8_0.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem8().equals("2")) {
            ckItem8_1.setChecked(true);
        } else if (data.getCtCruisingRecore().getItem8().equals("3")) {
            ckItem8_2.setChecked(true);
        }

        etItemOther.setText(data.getCtCruisingRecore().getOtherAbnormal());
        etCruiseNum.setText(data.getCtCruisingRecore().getCruiseTimes());
        etCruiseHour.setText(data.getCtCruisingRecore().getCruiseHours());
        etCruiseVoyage.setText(data.getCtCruisingRecore().getCruiseSeamile());
        etCruisePeopleNum.setText(data.getCtCruisingRecore().getLawEnforcementNumber());
        etCruiseGarrisonNum.setText(data.getCtCruisingRecore().getGarrisonTimes());
        etCruiseGarrisonTime.setText(data.getCtCruisingRecore().getGarrisonHours());
        etCruiseInspectShipNum.setText(data.getCtCruisingRecore().getVoyageTimes());
        etCruiseFindPeccancyNum.setText(data.getCtCruisingRecore().getViolationTimes());
        etCruiseEscortNum.setText(data.getCtCruisingRecore().getEscortTimes());
        etCruiseRescueNum.setText(data.getCtCruisingRecore().getSalvationTimes());
        if (data.getCtCruisingSummaryDatailList() != null) {
            for (int i = 0; i < data.getCtCruisingSummaryDatailList().size(); i++) {
                detailCruiseSummaryLayout = new DetailCruiseSummaryLayout(this, data.getCtCruisingSummaryDatailList().get(i));
                lin_xhjy.addView(detailCruiseSummaryLayout);
            }
        }
        if (data.getCtCruisingRiskDetailList() != null) {
            for (int i = 0; i < data.getCtCruisingRiskDetailList().size(); i++) {
                detailCruiseSituationLayout = new DetailCruiseSituationLayout(this, data.getCtCruisingRiskDetailList().get(i));
                lin_ycwt.addView(detailCruiseSituationLayout);
            }
        }
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }

    @Override
    public void setInspectionAll(List<TaskInspectionItemData> taskInspectionItemData) {

    }

    @Override
    public void onLoadFaild(String msg) {

    }

    @Override
    public void setShip(List<CruiseShipData> data) {

    }

    @Override
    public void onFindShipFaile(String msg) {

    }

    @Override
    public void setInspectionByName(List<TaskInspectionItemByNameData> data) {

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
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctCruisingRecoreRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                Intent intent = new Intent(DetailCruisrWorkRecordActivity.this, Activity_PrintPdf.class);
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
}
