package com.winfo.szrsp.app.mvp.table.xhgzjl.view;


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
 * 处理决定
 * Created by HoBo on 2018/4/12.
 */

public class ProcessingDecisionDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ImageButton but_close;
    private EditText etText;
    private TextView but_save;
    private CheckBox ckItem1, ckItem2, ckItem3, ckItem4;

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

    public ProcessingDecisionDialog(Context context) {
        this.mContext = context;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(context).inflate(R.layout.dialog_xhgzjl_cljd, null);
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
        ckItem1 = chuliView.findViewById(R.id.ckItem1);
        ckItem2 = chuliView.findViewById(R.id.ckItem2);
        ckItem3 = chuliView.findViewById(R.id.ckItem3);
        ckItem4 = chuliView.findViewById(R.id.ckItem4);
        etText = chuliView.findViewById(R.id.etText);
        but_save = chuliView.findViewById(R.id.but_save);
    }

    private void initEvent() {
        but_close.setOnClickListener(this);
        but_save.setOnClickListener(this);
        ckItem1.setOnCheckedChangeListener(this);
        ckItem2.setOnCheckedChangeListener(this);
        ckItem3.setOnCheckedChangeListener(this);
        ckItem4.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ckItem1:
                if (isCheck) {
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem2:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem3:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem4.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem4:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    etText.setEnabled(true);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_close:
                dialog.dismiss();
                break;
            case R.id.but_save:
                if (!ckItem1.isChecked() && !ckItem2.isChecked() && !ckItem3.isChecked() && !ckItem4.isChecked()) {
                    ToastUtils.showToast(mContext, "请选择处理决定!");
                    return;
                } else {
                    if (ckItem1.isChecked()) {
                        str = "01";
                    } else if (ckItem2.isChecked()) {
                        str = "02";
                    } else if (ckItem3.isChecked()) {
                        str = "03";
                    } else if (ckItem4.isChecked()) {
                        otherText = etText.getText().toString().trim();
                        if ("".equals(otherText)) {
                            ToastUtils.showToast(mContext, "请填写处理决定!");
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

    public void show(String str) {
        if ("警示教育".equals(str)) {
            ckItem1.setChecked(true);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
        } else if ("立案调查".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(true);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
        } else if ("通报有关部门".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(true);
            ckItem4.setChecked(false);
        } else {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(true);
            etText.setText(str);
        }

    }

    public interface SetStrData {
        void setNum(String s);
    }

    public void setSetStrData(SetStrData setStrData) {
        this.setStrData = setStrData;
    }
}
