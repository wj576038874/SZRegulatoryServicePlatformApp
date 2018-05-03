package com.winfo.szrsp.app.mvp.table.kxjc.view;


import android.annotation.SuppressLint;
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
import android.support.v4.app.Fragment;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.presenter.OrdinaryGoodsKaiXiangPresenter;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.IOrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.OrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 船舶载运普通货物集装箱开箱检查记录表
 * Created by HoBo on 2018/3/19.
 */

public class OrdinaryGoodsKaiXiangFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.table_titleBar)
    RelativeLayout table_titleBar;
    @BindView(R.id.ll_sc)
    ScrollView ll_sc;
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.et_ship_inspectTime)
    TextView et_ship_inspectTime;
    @BindView(R.id.et_ship_deeSea)
    EditText et_ship_deeSea;
    @BindView(R.id.et_ship_time)
    EditText et_ship_time;
    @BindView(R.id.et_ship_num)
    EditText et_ship_num;
    @BindView(R.id.et_carrier_ship)
    EditText et_carrier_ship;
    @BindView(R.id.et_carrier_people)
    EditText et_carrier_people;
    @BindView(R.id.et_voyage_number)
    EditText et_voyage_number;
    @BindView(R.id.et_consignment_people)
    EditText et_consignment_people;
    @BindView(R.id.et_order_num)
    EditText et_order_num;
    @BindView(R.id.et_box_num)
    EditText et_box_num;
    @BindView(R.id.ed_open_box_book_num)
    EditText ed_open_box_book_num;
    @BindView(R.id.et_inspect_before_num)
    EditText et_inspect_before_num;
    @BindView(R.id.et_inspect_after_num)
    EditText et_inspect_after_num;
    @BindView(R.id.et_goods_name)
    EditText et_goods_name;

    @BindView(R.id.ck_1_yes)
    CheckBox ck_1_yes;
    @BindView(R.id.ck_2_yes)
    CheckBox ck_2_yes;
    @BindView(R.id.ck_1_no)
    CheckBox ck_1_no;
    @BindView(R.id.ck_2_no)
    CheckBox ck_2_no;

    @BindView(R.id.et_exceptions_record)
    EditText et_exceptions_record;
    @BindView(R.id.ck_correct)
    CheckBox ck_correct;
    @BindView(R.id.ck_defects_exist)
    CheckBox ck_defects_exist;
    @BindView(R.id.ck_false)
    CheckBox ck_false;
    @BindView(R.id.ck_other)
    CheckBox ck_other;
    @BindView(R.id.et_process_results)
    EditText et_process_results;
    @BindView(R.id.et_zhifa_people1)
    EditText et_zhifa_people1;
    @BindView(R.id.et_zhifa_cardNum1)
    EditText et_zhifa_cardNum1;
    //    @BindView(R.id.et_autograph)
//    EditText et_autograph;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.tv_autograph)
    TextView tv_autograph;
    @BindView(R.id.iv_qm)
    ImageView iv_qm;
    @BindView(R.id.ed_other)
    EditText ed_other;
    private String qm_path;

    private TimePickerView timePickerView;
    private String ckItem1, ckItem2;
    private String ckOpinion;
    private OrdinaryGoodsKaiXiangData kaiXiangData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_cbzy_pthwkxjc, null);
        ButterKnife.bind(this, view);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        btn_save.setVisibility(View.GONE);
        table_titleBar.setVisibility(View.GONE);
        kaiXiangData = new OrdinaryGoodsKaiXiangData();

        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        et_ship_inspectTime.setText(time);
        et_zhifa_people1.setText(ACache.get(getActivity()).getAsString("userName"));
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        et_ship_inspectTime.setOnClickListener(this);
        ck_1_yes.setOnCheckedChangeListener(this);
        ck_2_yes.setOnCheckedChangeListener(this);
        ck_1_no.setOnCheckedChangeListener(this);
        ck_2_no.setOnCheckedChangeListener(this);
        ck_correct.setOnCheckedChangeListener(this);
        ck_defects_exist.setOnCheckedChangeListener(this);
        ck_false.setOnCheckedChangeListener(this);
        ck_other.setOnCheckedChangeListener(this);
        btn_save.setOnClickListener(this);
        tv_autograph.setOnClickListener(this);
        iv_qm.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ck_1_yes:
                if (isCheck) {
                    ck_1_yes.setChecked(true);
                    ck_1_no.setChecked(false);
                    ckItem1 = "1";
                }
                break;
            case R.id.ck_2_yes:
                if (isCheck) {
                    ck_2_yes.setChecked(true);
                    ck_2_no.setChecked(false);
                    ckItem2 = "1";
                }
                break;
            case R.id.ck_1_no:
                if (isCheck) {
                    ck_1_no.setChecked(true);
                    ck_1_yes.setChecked(false);
                    ckItem1 = "0";
                }
                break;
            case R.id.ck_2_no:
                if (isCheck) {
                    ck_2_no.setChecked(true);
                    ck_2_yes.setChecked(false);
                    ckItem2 = "0";
                }
                break;
            case R.id.ck_correct:
                if (isCheck) {
                    ck_correct.setChecked(true);
                    ck_defects_exist.setChecked(false);
                    ck_false.setChecked(false);
                    ck_other.setChecked(false);
                    ckOpinion = "1";
                    ed_other.setEnabled(false);
                    ed_other.setText("");
                }
                break;
            case R.id.ck_defects_exist:
                if (isCheck) {
                    ck_correct.setChecked(false);
                    ck_defects_exist.setChecked(true);
                    ck_false.setChecked(false);
                    ck_other.setChecked(false);
                    ckOpinion = "2";
                    ed_other.setEnabled(false);
                    ed_other.setText("");
                }
                break;
            case R.id.ck_false:
                if (isCheck) {
                    ck_correct.setChecked(false);
                    ck_defects_exist.setChecked(false);
                    ck_false.setChecked(true);
                    ck_other.setChecked(false);
                    ckOpinion = "3";
                    ed_other.setEnabled(false);
                    ed_other.setText("");
                }
                break;
            case R.id.ck_other:
                if (isCheck) {
                    ck_correct.setChecked(false);
                    ck_defects_exist.setChecked(false);
                    ck_false.setChecked(false);
                    ck_other.setChecked(true);
                    ckOpinion = "4";
                    ed_other.setEnabled(true);
                }
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.tv_autograph:
                writeLine(iv_qm, tv_autograph);
                break;

            case R.id.iv_qm:
                showQMDialog(qm_path);
                break;
        }
    }

    private Dialog qm_dialog;

    @SuppressLint("InflateParams")
    private void showQMDialog(String path) {
        int width = DeviceUtils.getScreenSize(getActivity())[0];
        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        int margin = DimensUtils.dp2px(getActivity(), 10);

        qm_dialog = new AlertDialog.Builder(getActivity()).create();
        qm_dialog.setCancelable(true);
        qm_dialog.setCanceledOnTouchOutside(true);
        View qmView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_qianming, null);
        qm_dialog.show();
        qm_dialog.setContentView(qmView);

        Window window = qm_dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        assert window != null;
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * margin;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        ImageView dialog_record_pen_iv = qmView.findViewById(R.id.dialog_record_pen_iv);
        Uri uri = Uri.fromFile(new File(path));
        dialog_record_pen_iv.setImageURI(uri);

        Button dialog_record_ok = qmView.findViewById(R.id.dialog_record_ok);
        Button dialog_record_reautograph = qmView.findViewById(R.id.dialog_record_reautograph);

        dialog_record_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
            }
        });
        dialog_record_reautograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLine(iv_qm, tv_autograph);
                qm_dialog.dismiss();
            }
        });
    }


    /**
     * 签名
     */
    private void writeLine(final ImageView imageView, final TextView textView) {
        SignatureView view1 = new SignatureView(getActivity(), ll_sc);
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
                imageView.setImageBitmap(bm);
                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }

    public interface GetDataCallBack {
        void getResult(OrdinaryGoodsKaiXiangData data);
    }

    public void getData(GetDataCallBack callBack) {
        kaiXiangData.setInspectTime(et_ship_inspectTime.getText().toString().trim());
        kaiXiangData.setDeepSea(et_ship_deeSea.getText().toString().trim());
        kaiXiangData.setDeepSea(et_ship_deeSea.getText().toString().trim());
        kaiXiangData.setDeepSeaTime(et_ship_time.getText().toString().trim());
        kaiXiangData.setDeepSeaNum(et_ship_num.getText().toString().toString().trim());
        kaiXiangData.setCarrierShip(et_carrier_ship.getText().toString().trim());
        kaiXiangData.setCarrierAgent(et_carrier_people.getText().toString().trim());
        kaiXiangData.setVoyageId(et_voyage_number.getText().toString().trim());
        kaiXiangData.setConsignAgent(et_consignment_people.getText().toString().trim());
        kaiXiangData.setBillNumber(et_order_num.getText().toString().trim());
        kaiXiangData.setCaseNumber(et_box_num.getText().toString().trim());
        kaiXiangData.setOutInspectNotice(ed_open_box_book_num.getText().toString().trim());
        kaiXiangData.setBeforeInspectNum(et_inspect_before_num.getText().toString().trim());
        kaiXiangData.setAfterInspectNum(et_inspect_after_num.getText().toString().trim());
        kaiXiangData.setGoodName(et_goods_name.getText().toString().trim());
        kaiXiangData.setExceptionDescribe(et_exceptions_record.getText().toString().trim());
        kaiXiangData.setHandleResult(et_process_results.getText().toString().trim());
        kaiXiangData.setInspector(et_zhifa_people1.getText().toString().trim());
        kaiXiangData.setInspectorCode(et_zhifa_cardNum1.getText().toString().trim());
        kaiXiangData.setRemark(ed_other.getText().toString().trim());
        kaiXiangData.setItem1(ckItem1);
        kaiXiangData.setItem2(ckItem2);
        kaiXiangData.setHandle(ckOpinion);
        callBack.getResult(kaiXiangData);
    }

}
