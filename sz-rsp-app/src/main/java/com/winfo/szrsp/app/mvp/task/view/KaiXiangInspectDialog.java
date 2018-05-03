package com.winfo.szrsp.app.mvp.task.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.kxjc.view.KxjcActivity;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.ToastUtils;


/**
 * Created by HoBo on 2018/3/23.
 */

public class KaiXiangInspectDialog implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private CheckBox rb_1;
    private CheckBox rb_2;
    private CheckBox rb_3;
    private CheckBox rb_4;
    private TextView but_cancel;
    private TextView but_sure;

    private View view;
    private Context mContext;
    private Dialog dialog;
    private int width;
    private int magin;
    private String taskId, shipNameCn, shipNameEn;
    private String check1 = "0";
    private String check2 = "0";
    private String check3 = "0";
    private String check4 = "0";

    public KaiXiangInspectDialog(Context context, String taskId, String shipNameCn, String shipNameEn) {
        this.mContext = context;
        this.taskId = taskId;
        this.shipNameCn = shipNameCn;
        this.shipNameEn = shipNameEn;

        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        view = LayoutInflater.from(context).inflate(R.layout.dialog_kaixiang_inspect, null);
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
        rb_1 = view.findViewById(R.id.rb_1);
        rb_2 = view.findViewById(R.id.rb_2);
        rb_3 = view.findViewById(R.id.rb_3);
        rb_4 = view.findViewById(R.id.rb_4);
        but_cancel = view.findViewById(R.id.but_cancel);
        but_sure = view.findViewById(R.id.but_sure);
    }

    private void initEvent() {
        rb_1.setOnCheckedChangeListener(this);
        rb_2.setOnCheckedChangeListener(this);
        rb_3.setOnCheckedChangeListener(this);
        rb_4.setOnCheckedChangeListener(this);
        but_cancel.setOnClickListener(this);
        but_sure.setOnClickListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.rb_1:
                if (isCheck) {
                    rb_1.setBackgroundResource(R.drawable.btn_normal_bg);
                    check1 = "1";
                } else {
                    rb_1.setBackgroundResource(R.drawable.btn_normal_bg_gray);
                    check1 = "0";
                }
                break;
            case R.id.rb_2:
                if (isCheck) {
                    rb_2.setBackgroundResource(R.drawable.btn_normal_bg);
                    check2 = "1";
                } else {
                    rb_2.setBackgroundResource(R.drawable.btn_normal_bg_gray);
                    check2 = "0";
                }
                break;
            case R.id.rb_3:
                if (isCheck) {
                    rb_3.setBackgroundResource(R.drawable.btn_normal_bg);
                    check3 = "1";
                } else {
                    rb_3.setBackgroundResource(R.drawable.btn_normal_bg_gray);
                    check3 = "0";
                }
                break;
            case R.id.rb_4:
                if (isCheck) {
                    rb_4.setBackgroundResource(R.drawable.btn_normal_bg);
                    check4 = "1";
                } else {
                    rb_4.setBackgroundResource(R.drawable.btn_normal_bg_gray);
                    check4 = "0";
                }
                break;


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_cancel:
                rb_1.setChecked(false);
                rb_2.setChecked(false);
                rb_3.setChecked(false);
                rb_4.setChecked(false);
                check1 = "0";
                check2 = "0";
                check3 = "0";
                check4 = "0";
                dialog.dismiss();
                break;
            case R.id.but_sure:
                if (rb_1.isChecked() || rb_2.isChecked() || rb_3.isChecked() || rb_4.isChecked()) {
                    Intent intent = new Intent(mContext, KxjcActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("shipNameCn", shipNameCn);
                    intent.putExtra("shipNameEn", shipNameEn);
                    intent.putExtra("check1", check1);
                    intent.putExtra("check2", check2);
                    intent.putExtra("check3", check3);
                    intent.putExtra("check4", check4);
                    mContext.startActivity(intent);
                    rb_1.setChecked(false);
                    rb_2.setChecked(false);
                    rb_3.setChecked(false);
                    rb_4.setChecked(false);
                    check1 = "0";
                    check2 = "0";
                    check3 = "0";
                    check4 = "0";
                    dialog.dismiss();
                } else {
                    ToastUtils.showToast(mContext, "请选择需要填写的表单");
                }
                break;
        }
    }

    public void showDialog() {
        dialog.show();
    }
}
