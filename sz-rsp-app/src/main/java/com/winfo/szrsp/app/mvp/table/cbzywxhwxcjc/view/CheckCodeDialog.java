package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.ToastUtils;


/**
 * 危险货物现场检查-----检查代码Dialog
 * Created by HoBo on 2018/3/16.
 */

public class CheckCodeDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageButton but_close;
    private CheckBox check_1_1, check_1_2, check_1_3, check_1_4, check_1_5, check_1_6, check_1_7, check_1_8,
            check_2_1, check_2_2, check_2_3, check_2_4, check_2_5, check_2_6, check_2_7,
            check_3_1, check_3_2, check_3_3, check_3_4, check_3_5,
            check_4_1, check_4_2,
            check_5_1, check_5_2,
            check_6_1, check_6_2, check_6_3, check_6_4,
            check_7_1;
    private EditText edit_opinion;
    private TextView but_save;

    private View chuliView;
    private int width;
    // 距离两边的像素
    private int magin;
    private Context mContext;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;
    private Dialog dialog;

    public SetStrData setStrData;
    public String str;
    private String otherText = "";

    public CheckCodeDialog(Context context) {
        this.mContext = context;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(context).inflate(R.layout.dialog_dangerous_jiancha, null);
        dialog.show();
        dialog.setContentView(chuliView);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        // window.setWindowAnimations(R.style.AnimationDialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }

    private void initView() {
        but_close = chuliView.findViewById(R.id.but_close);
        check_1_1 = chuliView.findViewById(R.id.check_1_1);
        check_1_2 = chuliView.findViewById(R.id.check_1_2);
        check_1_3 = chuliView.findViewById(R.id.check_1_3);
        check_1_4 = chuliView.findViewById(R.id.check_1_4);
        check_1_5 = chuliView.findViewById(R.id.check_1_5);
        check_1_6 = chuliView.findViewById(R.id.check_1_6);
        check_1_7 = chuliView.findViewById(R.id.check_1_7);
        check_1_8 = chuliView.findViewById(R.id.check_1_8);
        check_2_1 = chuliView.findViewById(R.id.check_2_1);
        check_2_2 = chuliView.findViewById(R.id.check_2_2);
        check_2_3 = chuliView.findViewById(R.id.check_2_3);
        check_2_4 = chuliView.findViewById(R.id.check_2_4);
        check_2_5 = chuliView.findViewById(R.id.check_2_5);
        check_2_6 = chuliView.findViewById(R.id.check_2_6);
        check_2_7 = chuliView.findViewById(R.id.check_2_7);
        check_3_1 = chuliView.findViewById(R.id.check_3_1);
        check_3_2 = chuliView.findViewById(R.id.check_3_2);
        check_3_3 = chuliView.findViewById(R.id.check_3_3);
        check_3_4 = chuliView.findViewById(R.id.check_3_4);
        check_3_5 = chuliView.findViewById(R.id.check_3_5);
        check_4_1 = chuliView.findViewById(R.id.check_4_1);
        check_4_2 = chuliView.findViewById(R.id.check_4_2);
        check_5_1 = chuliView.findViewById(R.id.check_5_1);
        check_5_2 = chuliView.findViewById(R.id.check_5_2);
        check_6_1 = chuliView.findViewById(R.id.check_6_1);
        check_6_2 = chuliView.findViewById(R.id.check_6_2);
        check_6_3 = chuliView.findViewById(R.id.check_6_3);
        check_6_4 = chuliView.findViewById(R.id.check_6_4);
        check_7_1 = chuliView.findViewById(R.id.check_7_1);
        edit_opinion = chuliView.findViewById(R.id.edit_opinion);
        but_save = chuliView.findViewById(R.id.but_save);
    }

    private void initEvent() {
        but_close.setOnClickListener(this);
        but_save.setOnClickListener(this);
        check_1_1.setOnCheckedChangeListener(this);
        check_1_2.setOnCheckedChangeListener(this);
        check_1_3.setOnCheckedChangeListener(this);
        check_1_4.setOnCheckedChangeListener(this);
        check_1_5.setOnCheckedChangeListener(this);
        check_1_6.setOnCheckedChangeListener(this);
        check_1_7.setOnCheckedChangeListener(this);
        check_1_8.setOnCheckedChangeListener(this);
        check_2_1.setOnCheckedChangeListener(this);
        check_2_2.setOnCheckedChangeListener(this);
        check_2_3.setOnCheckedChangeListener(this);
        check_2_4.setOnCheckedChangeListener(this);
        check_2_5.setOnCheckedChangeListener(this);
        check_2_6.setOnCheckedChangeListener(this);
        check_2_7.setOnCheckedChangeListener(this);
        check_3_1.setOnCheckedChangeListener(this);
        check_3_2.setOnCheckedChangeListener(this);
        check_3_3.setOnCheckedChangeListener(this);
        check_3_4.setOnCheckedChangeListener(this);
        check_3_5.setOnCheckedChangeListener(this);
        check_4_1.setOnCheckedChangeListener(this);
        check_4_2.setOnCheckedChangeListener(this);
        check_5_1.setOnCheckedChangeListener(this);
        check_5_2.setOnCheckedChangeListener(this);
        check_6_1.setOnCheckedChangeListener(this);
        check_6_2.setOnCheckedChangeListener(this);
        check_6_3.setOnCheckedChangeListener(this);
        check_6_4.setOnCheckedChangeListener(this);
        check_7_1.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_close:
                dialog.dismiss();
                break;
            case R.id.but_save:
                if (!check_1_1.isChecked() && !check_1_2.isChecked() && !check_1_3.isChecked() && !check_1_4.isChecked() && !check_1_5.isChecked() && !check_1_6.isChecked() && !check_1_7.isChecked() && !check_1_8.isChecked()
                        && !check_2_1.isChecked() && !check_2_2.isChecked() && !check_2_3.isChecked() && !check_2_4.isChecked() && !check_2_5.isChecked() && !check_2_6.isChecked() && !check_2_7.isChecked()
                        && !check_3_1.isChecked() && !check_3_2.isChecked() && !check_3_3.isChecked() && !check_3_4.isChecked() && !check_3_5.isChecked()
                        && !check_4_1.isChecked() && !check_4_2.isChecked()
                        && !check_5_1.isChecked() && !check_5_2.isChecked()
                        && !check_6_1.isChecked() && !check_6_2.isChecked() && !check_6_3.isChecked() && !check_6_4.isChecked()
                        && !check_7_1.isChecked()) {
                    ToastUtils.showToast(mContext, "请选择检查代码!");
                    return;
                } else {
                    if (check_1_1.isChecked()) {
                        str = "1.1";
                    } else if (check_1_2.isChecked()) {
                        str = "1.2";
                    } else if (check_1_3.isChecked()) {
                        str = "1.3";
                    } else if (check_1_4.isChecked()) {
                        str = "1.4";
                    } else if (check_1_5.isChecked()) {
                        str = "1.5";
                    } else if (check_1_6.isChecked()) {
                        str = "1.6";
                    } else if (check_1_7.isChecked()) {
                        str = "1.7";
                    } else if (check_1_8.isChecked()) {
                        str = "1.8";
                    } else if (check_2_1.isChecked()) {
                        str = "2.1";
                    } else if (check_2_2.isChecked()) {
                        str = "2.2";
                    } else if (check_2_3.isChecked()) {
                        str = "2.3";
                    } else if (check_2_4.isChecked()) {
                        str = "2.4";
                    } else if (check_2_5.isChecked()) {
                        str = "2.5";
                    } else if (check_2_6.isChecked()) {
                        str = "2.6";
                    } else if (check_2_7.isChecked()) {
                        str = "2.7";
                    } else if (check_3_1.isChecked()) {
                        str = "3.1";
                    } else if (check_3_2.isChecked()) {
                        str = "3.2";
                    } else if (check_3_3.isChecked()) {
                        str = "3.3";
                    } else if (check_3_4.isChecked()) {
                        str = "3.4";
                    } else if (check_3_5.isChecked()) {
                        str = "3.5";
                    } else if (check_4_1.isChecked()) {
                        str = "4.1";
                    } else if (check_4_2.isChecked()) {
                        str = "4.2";
                    } else if (check_5_1.isChecked()) {
                        str = "5.1";
                    } else if (check_5_2.isChecked()) {
                        str = "5.2";
                    } else if (check_6_1.isChecked()) {
                        str = "6.1";
                    } else if (check_6_2.isChecked()) {
                        str = "6.2";
                    } else if (check_6_3.isChecked()) {
                        str = "6.3";
                    } else if (check_6_4.isChecked()) {
                        str = "6.4";
                    } else if (check_7_1.isChecked()) {
                        otherText = edit_opinion.getText().toString().trim();
                        if ("".equals(otherText)) {
                            ToastUtils.showToast(mContext, "请选择填写检查项目!");
                            return;
                        } else {
                            str = otherText;
                        }
                    }
                    setStrData.setNum(str);
                    dialog.dismiss();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.check_1_1:
                if (isCheck) {
//                    check_1_1.setChecked(true);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_1_2:
                if (isCheck) {
                    check_1_1.setChecked(false);
//                    check_1_2.setChecked(true);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_1_3:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
//                    check_1_3.setChecked(true);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_1_4:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
//                    check_1_4.setChecked(true);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_1_5:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
//                    check_1_5.setChecked(true);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_1_6:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
//                    check_1_6.setChecked(true);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_1_7:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
//                    check_1_7.setChecked(true);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_1_8:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
//                    check_1_8.setChecked(true);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_1:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
//                    check_2_1.setChecked(true);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_2:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
//                    check_2_2.setChecked(true);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_3:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
//                    check_2_3.setChecked(true);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_4:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
//                    check_2_4.setChecked(true);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_5:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
//                    check_2_5.setChecked(true);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_6:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
//                    check_2_6.setChecked(true);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_2_7:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
//                    check_2_7.setChecked(true);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_3_1:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
//                    check_3_1.setChecked(true);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_3_2:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
//                    check_3_2.setChecked(true);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_3_3:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
//                    check_3_3.setChecked(true);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_3_4:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
//                    check_3_4.setChecked(true);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_3_5:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
//                    check_3_5.setChecked(true);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_4_1:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
//                    check_4_1.setChecked(true);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_4_2:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
//                    check_4_2.setChecked(true);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_5_1:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
//                    check_5_1.setChecked(true);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_5_2:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
//                    check_5_2.setChecked(true);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_6_1:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
//                    check_6_1.setChecked(true);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_6_2:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
//                    check_6_2.setChecked(true);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_6_3:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
//                    check_6_3.setChecked(true);
                    check_6_4.setChecked(false);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_6_4:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
//                    check_6_4.setChecked(true);
                    check_7_1.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }

                break;
            case R.id.check_7_1:
                if (isCheck) {
                    check_1_1.setChecked(false);
                    check_1_2.setChecked(false);
                    check_1_3.setChecked(false);
                    check_1_4.setChecked(false);
                    check_1_5.setChecked(false);
                    check_1_6.setChecked(false);
                    check_1_7.setChecked(false);
                    check_1_8.setChecked(false);
                    check_2_1.setChecked(false);
                    check_2_2.setChecked(false);
                    check_2_3.setChecked(false);
                    check_2_4.setChecked(false);
                    check_2_5.setChecked(false);
                    check_2_6.setChecked(false);
                    check_2_7.setChecked(false);
                    check_3_1.setChecked(false);
                    check_3_2.setChecked(false);
                    check_3_3.setChecked(false);
                    check_3_4.setChecked(false);
                    check_3_5.setChecked(false);
                    check_4_1.setChecked(false);
                    check_4_2.setChecked(false);
                    check_5_1.setChecked(false);
                    check_5_2.setChecked(false);
                    check_6_1.setChecked(false);
                    check_6_2.setChecked(false);
                    check_6_3.setChecked(false);
                    check_6_4.setChecked(false);
                    edit_opinion.setEnabled(true);
                }

                break;
        }
    }

    public void show(String str) {
        dialog.show();
        if ("1.1".equals(str)) {
            check_1_1.setChecked(true);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.2".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(true);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.3".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(true);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.4".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(true);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.5".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(true);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.6".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(true);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.7".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(true);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("1.8".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(true);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.1".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(true);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.2".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(true);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.3".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(true);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.4".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(true);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.5".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(true);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.6".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(true);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("2.7".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(true);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("3.1".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(true);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("3.2".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(true);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("3.3".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(true);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("3.4".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(true);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("3.5".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(true);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("4.1".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(true);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("4.2".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(true);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("5.1".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(true);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("5.2".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(true);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("6.1".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(true);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("6.2".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(true);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("6.3".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(true);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        } else if ("6.4".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(true);
            check_7_1.setChecked(false);
        } else if ("7.1".equals(str)) {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(true);
            edit_opinion.setText(str);
        } else {
            check_1_1.setChecked(false);
            check_1_2.setChecked(false);
            check_1_3.setChecked(false);
            check_1_4.setChecked(false);
            check_1_5.setChecked(false);
            check_1_6.setChecked(false);
            check_1_7.setChecked(false);
            check_1_8.setChecked(false);
            check_2_1.setChecked(false);
            check_2_2.setChecked(false);
            check_2_3.setChecked(false);
            check_2_4.setChecked(false);
            check_2_5.setChecked(false);
            check_2_6.setChecked(false);
            check_2_7.setChecked(false);
            check_3_1.setChecked(false);
            check_3_2.setChecked(false);
            check_3_3.setChecked(false);
            check_3_4.setChecked(false);
            check_3_5.setChecked(false);
            check_4_1.setChecked(false);
            check_4_2.setChecked(false);
            check_5_1.setChecked(false);
            check_5_2.setChecked(false);
            check_6_1.setChecked(false);
            check_6_2.setChecked(false);
            check_6_3.setChecked(false);
            check_6_4.setChecked(false);
            check_7_1.setChecked(false);
        }
    }


    public interface SetStrData {
        void setNum(String s);
    }

    public void setSetStrData(SetStrData setStrData) {
        this.setStrData = setStrData;
    }
}
