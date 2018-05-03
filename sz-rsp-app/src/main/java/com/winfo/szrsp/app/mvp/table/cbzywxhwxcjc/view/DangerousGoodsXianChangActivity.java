package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbzy.view.DangerousGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.OrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.presenter.DangerousGoodsXianChangPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.DetailDangerousGoodsXianChangData;
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
 * 舶载运集装箱危险货物/污染危害性货物现场检查记录表
 * Created by HoBo on 2018/3/9.
 */

public class DangerousGoodsXianChangActivity extends Activity implements IDangerousGoodsXianChangActivity, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_chinaName)
    EditText et_ship_chinaName;
    @BindView(R.id.et_ship_englishName)
    EditText et_ship_englishName;
    @BindView(R.id.et_ship_nationality)
    EditText et_ship_nationality;
    @BindView(R.id.et_ship_weight)
    EditText et_ship_weight;
    @BindView(R.id.et_ship_buildTime)
    TextView et_ship_buildTime;
    @BindView(R.id.ck_imported)
    CheckBox ck_imported;
    @BindView(R.id.ck_transit)
    CheckBox ck_transit;
    @BindView(R.id.ck_exit)
    CheckBox ck_exit;
    @BindView(R.id.ck_transfer)
    CheckBox ck_transfer;
    @BindView(R.id.et_ship_loading_port)
    EditText et_ship_loading_port;
    @BindView(R.id.et_ship_unloading_port)
    EditText et_ship_unloading_port;
    @BindView(R.id.et_ship_berth)
    EditText et_ship_berth;
    @BindView(R.id.et_ship_route)
    EditText et_ship_route;
    @BindView(R.id.et_ship_agent)
    EditText et_ship_agent;
    @BindView(R.id.et_ship_inspect)
    EditText et_ship_inspect;
    @BindView(R.id.et_ship_company)
    EditText et_ship_company;
    @BindView(R.id.tableLin2)
    TableLayout tableLin2;
    @BindView(R.id.add_img)
    ImageView add_img;
    @BindView(R.id.del_img)
    ImageView del_img;
    @BindView(R.id.img_close_up)
    ImageView img_close_up;
    @BindView(R.id.img_close_down)
    ImageView img_close_down;
    @BindView(R.id.tabLin)
    TableLayout tabLin;
    @BindView(R.id.et_ship_zhifa1)
    EditText et_ship_zhifa1;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.btn_save)
    Button btn_save;

    private Dialog dialog;
    private Dialog downloadDialog;
    private TimePickerView timePickerView;
    private DangerousGoodsXianChangData data;
    private DangerousGoodsXianChangData.ctDangerPolluteScene sceneData;
    private DangerousGoodsXianChangData.ctDangerPolluteSceneDetail sceneDetailData;
    private List<DangerousGoodsXianChangData.ctDangerPolluteSceneDetail> list;
    private DangerousGoodsXianChangPresenter presenter;
    private DangerousGoodsLayout dangerousGoodsLayout;
    private String str = "";
    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbzy_wxhwxcjc);
        ButterKnife.bind(this);
        myHandler = new MyHandler(this);
        initView();
        initEvent();
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        downloadDialog = DialogUtils.createLoadingDialog(this, "下载中...");
        dangerousGoodsLayout = new DangerousGoodsLayout(this);
        tableLin2.addView(dangerousGoodsLayout);
        presenter = new DangerousGoodsXianChangPresenter(this);

        data = new DangerousGoodsXianChangData();
        sceneData = new DangerousGoodsXianChangData.ctDangerPolluteScene();
        sceneDetailData = new DangerousGoodsXianChangData.ctDangerPolluteSceneDetail();
        list = new ArrayList<>();

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        et_ship_inspectTime.setText(time);
        et_ship_zhifa1.setText(ACache.get(this).getAsString("userName"));
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        et_ship_inspectTime.setOnClickListener(this);
        et_ship_buildTime.setOnClickListener(this);
        img_close_up.setOnClickListener(this);
        img_close_down.setOnClickListener(this);
        ck_imported.setOnCheckedChangeListener(this);
        ck_transit.setOnCheckedChangeListener(this);
        ck_exit.setOnCheckedChangeListener(this);
        ck_transfer.setOnCheckedChangeListener(this);
        add_img.setOnClickListener(this);
        del_img.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ck_imported:
                if (isCheck) {
                    ck_imported.setChecked(true);
                    ck_transit.setChecked(false);
                    ck_exit.setChecked(false);
                    ck_transfer.setChecked(false);
                    str = "1";
                }
                break;
            case R.id.ck_transit:
                if (isCheck) {
                    ck_imported.setChecked(false);
                    ck_transit.setChecked(true);
                    ck_exit.setChecked(false);
                    ck_transfer.setChecked(false);
                    str = "2";
                }
                break;
            case R.id.ck_exit:
                if (isCheck) {
                    ck_imported.setChecked(false);
                    ck_transit.setChecked(false);
                    ck_exit.setChecked(true);
                    ck_transfer.setChecked(false);
                    str = "3";
                }
                break;
            case R.id.ck_transfer:
                if (isCheck) {
                    ck_imported.setChecked(false);
                    ck_transit.setChecked(false);
                    ck_exit.setChecked(false);
                    ck_transfer.setChecked(true);
                    str = "4";
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.et_ship_inspectTime:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        et_ship_inspectTime.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.et_ship_buildTime:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        et_ship_buildTime.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.img_close_up:
                tabLin.setVisibility(View.GONE);
                img_close_up.setVisibility(View.GONE);
                img_close_down.setVisibility(View.VISIBLE);
                break;
            case R.id.img_close_down:
                tabLin.setVisibility(View.VISIBLE);
                img_close_up.setVisibility(View.VISIBLE);
                img_close_down.setVisibility(View.GONE);
                break;
            case R.id.add_img:
                dangerousGoodsLayout = new DangerousGoodsLayout(this);
                tableLin2.addView(dangerousGoodsLayout);
                break;
            case R.id.del_img:
                if (tableLin2.getChildCount() != 1) {
                    tableLin2.removeViewAt(tableLin2.getChildCount() - 1);
                }
                break;
            case R.id.btn_save:
                boolean isDataEmpty=true;
                for (int i = 0; i < tableLin2.getChildCount(); i++) {
                    DangerousGoodsLayout dangerousGoodsLayout = (DangerousGoodsLayout) tableLin2.getChildAt(i);
                    if (dangerousGoodsLayout.getDetail() != null) {
                        isDataEmpty=false;
                        break;
                    }
                }

                if ("".equals(et_ship_chinaName.getText().toString().trim())&&"".equals(et_ship_englishName.getText().toString().trim())) {
                    ToastUtils.showToast(this, "请填写中文船名或英文船名");
                } else if (isDataEmpty){
                    ToastUtils.showToast(this,"至少填写一行整改结果");
                }
//                else if ("".equals(et_ship_nationality.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将国籍/船籍港填写完整");
//                } else if ("".equals(et_ship_weight.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将总吨填写完整");
//                } else if ("".equals(et_ship_buildTime.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将建造日期填写完整");
//                } else if ("".equals(et_ship_loading_port.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将上一港/装货港填写完整");
//                } else if ("".equals(et_ship_unloading_port.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将下一港/卸货港填写完整");
//                } else if ("".equals(et_ship_berth.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将作业泊位填写完整");
//                } else if ("".equals(et_ship_route.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将运营航线填写完整");
//                } else if ("".equals(et_ship_agent.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将代理填写完整");
//                } else if ("".equals(et_ship_inspect.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将检查港填写完整");
//                } else if ("".equals(et_ship_company.getText().toString().trim())) {
//                    ToastUtils.showToast(this, "请将所属公司填写完整");
//                } else if (!ck_imported.isChecked() && !ck_transit.isChecked() && !ck_exit.isChecked() && !ck_transfer.isChecked()) {
//                    ToastUtils.showToast(this, "请选择危险货物流向");
//                    break;
//                }
                else {
                    presenter.subData();
                }
                break;
        }
    }


    @Override
    public Dialog getDialog() {
        return dialog;
    }

    private String sub_msg;
    private String id;

    @Override
    public void OnSuccess(String msg, String resultData) {
        dialog.dismiss();
        this.sub_msg = msg;
        this.id = resultData;
        this.namepath = "";
        myHandler.sendEmptyMessage(500);
    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
        this.sub_msg = msg;
        myHandler.sendEmptyMessage(600);
    }

    @Override
    public DangerousGoodsXianChangData getData() {
        list.clear();
        sceneData.setShipNameCn(et_ship_chinaName.getText().toString().trim());
        sceneData.setShipNameEn(et_ship_englishName.getText().toString().trim());
        sceneData.setRegportName(et_ship_nationality.getText().toString().trim());
        sceneData.setShipGrosston(et_ship_weight.getText().toString().trim());
        sceneData.setBuildDate(et_ship_buildTime.getText().toString().trim());
        sceneData.setFlowTo(str);
        sceneData.setLastPort(et_ship_loading_port.getText().toString().trim());
        sceneData.setNextPort(et_ship_unloading_port.getText().toString().trim());
        sceneData.setTaskPort(et_ship_berth.getText().toString().trim());
        sceneData.setRoute(et_ship_route.getText().toString().trim());
        sceneData.setAgent(et_ship_agent.getText().toString().trim());
        sceneData.setBerthCode(et_ship_inspect.getText().toString().trim());
        sceneData.setCompanyName(et_ship_company.getText().toString().trim());
        sceneData.setInspector(et_ship_zhifa1.getText().toString().trim());
        sceneData.setInspectDate(et_ship_inspectTime.getText().toString().trim());
        for (int i = 0; i < tableLin2.getChildCount(); i++) {
            DangerousGoodsLayout dangerousGoodsLayout = (DangerousGoodsLayout) tableLin2.getChildAt(i);
            if (dangerousGoodsLayout.getDetail() == null) {

            } else {
                list.add(dangerousGoodsLayout.getDetail());
            }
        }

        data.setCtDangerPolluteScene(sceneData);
        data.setCtDangerPolluteSceneDetail(list);
        return data;
    }

    @Override
    public void setDetailData(String msg, DangerousGoodsXianChangData detailData) {

    }

    @Override
    public void loginExpired(String msg) {

        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    private static class MyHandler extends Handler {

        WeakReference<DangerousGoodsXianChangActivity> dangerousGoodsXianChangActivityWeakReference;

        MyHandler(DangerousGoodsXianChangActivity dangerousGoodsXianChangActivity) {
            this.dangerousGoodsXianChangActivityWeakReference = new WeakReference<>(dangerousGoodsXianChangActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final DangerousGoodsXianChangActivity supervisionActivity = dangerousGoodsXianChangActivityWeakReference.get();
            int what = msg.what;
            if (what == 500) {
                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg + "是否打印检查表？", "打印", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        String name = "集装箱危险货物现场检查记录表" + "_" + supervisionActivity.id + ".pdf";
                        supervisionActivity.namepath = supervisionActivity.path + name;
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
                        dialog.dismiss();
                    }
                });
            }
            if (what == 600) {

                DialogUtils.showDialog(supervisionActivity, "温馨提示", supervisionActivity.sub_msg + "是否重新提交？", "重新提交", "返回修改", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        supervisionActivity.presenter.subData();
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
    String path = SDCardUtils.getRootDirectory() + "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath = "";
    DownInfo apkApi;

    private void downloadPDF() {
        String name = "集装箱危险货物现场检查记录表" + "_" + id + ".pdf";
        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }

        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/ctDangerPolluteSceneRestService/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params
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
                Intent intent = new Intent(DangerousGoodsXianChangActivity.this, Activity_PrintPdf.class);
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
                //           deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(500);
        myHandler.removeMessages(600);
    }
}
