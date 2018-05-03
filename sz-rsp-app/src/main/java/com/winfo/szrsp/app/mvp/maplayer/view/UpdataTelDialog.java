package com.winfo.szrsp.app.mvp.maplayer.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.presenter.UpdataTelPresenter;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.HandleOpinionDialog;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HoBo on 2018/3/30.
 */

public class UpdataTelDialog implements View.OnClickListener, IUpdataTelView {
    private EditText et_tel, et_nameTel;
    private TextView but_cancel;
    private TextView but_sure;
    private View view;
    private Context mContext;
    private Dialog dialog;
    private Dialog dialog2;
    private int width;
    private int magin;
    private String mmsi;
    private UpdataTelPresenter presenter;

    public UpdataTelDialog(Context context, String mmsi) {
        this.mContext = context;
        this.mmsi = mmsi;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_updata_tel, null);
        dialog.show();
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        // window.setWindowAnimations(R.style.AnimationDialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }

    private void initView() {
        dialog2 = DialogUtils.createLoadingDialog(mContext, "请稍后...");
        presenter = new UpdataTelPresenter(this);
        et_tel = view.findViewById(R.id.et_tel);
        et_nameTel = view.findViewById(R.id.et_nameTel);
        but_cancel = view.findViewById(R.id.but_cancel);
        but_sure = view.findViewById(R.id.but_sure);
    }

    private void initEvent() {
        but_cancel.setOnClickListener(this);
        but_sure.setOnClickListener(this);
    }

    public void show() {
        dialog.show();
//        if (!"".equals(tvPhone)) {
//            et_tel.setText(tvPhone);
//        }
//        if (!"".equals(tvPhoneName)) {
//            et_nameTel.setText(tvPhoneName);
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_cancel:
                dialog.dismiss();
                break;
            case R.id.but_sure:
                if ("".equals(et_tel.getText().toString().trim())) {
                    ToastUtils.showToast(mContext, "请输入电话号码");
                } else if (!isMobileNum(et_tel.getText().toString().trim())) {
                    ToastUtils.showToast(mContext, "请输入正确的电话号码");
                } else if ("".equals(et_nameTel.getText().toString().trim())) {
                    ToastUtils.showToast(mContext, "请输入姓名");
                } else {
                    presenter.updataPhoneByMmsi(mmsi, et_tel.getText().toString().trim(), et_nameTel.getText().toString().trim(), true);
                }
                break;
        }
    }

    public boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    @Override
    public Dialog getDialog() {
        return dialog2;
    }

    @Override
    public void OnSuccess(String s, String resultData) {
        dialog2.dismiss();
        setStrData.setStr(et_tel.getText().toString().trim(), et_nameTel.getText().toString().trim());
        dialog.dismiss();
    }

    @Override
    public void OnFaile(String msg) {
        dialog2.dismiss();
        ToastUtils.showToast(mContext, msg);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(mContext, msg, msg);
    }

    public SetStrData setStrData;

    public interface SetStrData {
        void setStr(String tel, String telName);
    }

    public void setSetStrData(SetStrData setStrData) {
        this.setStrData = setStrData;
    }
}
