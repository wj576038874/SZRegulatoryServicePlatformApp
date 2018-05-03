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
 * 危险货物现场检查-----处理意见Dialog
 * Created by HoBo on 2018/3/16.
 */

public class HandleOpinionDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageButton but_close;
    private CheckBox check_0;
    private CheckBox check_1;
    private CheckBox check_2;
    private CheckBox check_3;
    private CheckBox check_4;
    private CheckBox check_5;
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

    public HandleOpinionDialog(Context context) {
        this.mContext = context;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(context).inflate(R.layout.dialog_dangerous_chuli, null);
        dialog.show();
        dialog.setContentView(chuliView);
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
        but_close = chuliView.findViewById(R.id.but_close);
        check_0 = chuliView.findViewById(R.id.check_0);
        check_1 = chuliView.findViewById(R.id.check_1);
        check_2 = chuliView.findViewById(R.id.check_2);
        check_3 = chuliView.findViewById(R.id.check_3);
        check_4 = chuliView.findViewById(R.id.check_4);
        check_5 = chuliView.findViewById(R.id.check_5);
        edit_opinion = chuliView.findViewById(R.id.edit_opinion);
        but_save = chuliView.findViewById(R.id.but_save);
    }

    private void initEvent() {
        but_close.setOnClickListener(this);
        but_save.setOnClickListener(this);
        check_0.setOnCheckedChangeListener(this);
        check_1.setOnCheckedChangeListener(this);
        check_2.setOnCheckedChangeListener(this);
        check_3.setOnCheckedChangeListener(this);
        check_4.setOnCheckedChangeListener(this);
        check_5.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_close:
                dialog.dismiss();
                break;
            case R.id.but_save:
                if (!check_0.isChecked() && !check_1.isChecked() && !check_2.isChecked() && !check_3.isChecked() && !check_4.isChecked() && !check_5.isChecked()) {
                    ToastUtils.showToast(mContext, "请选择处理意见!");
                    return;
                } else {
                    if (check_0.isChecked()) {
                        str = "00";
                    } else if (check_1.isChecked()) {
                        str = "01";
                    } else if (check_2.isChecked()) {
                        str = "02";
                    } else if (check_3.isChecked()) {
                        str = "03";
                    } else if (check_4.isChecked()) {
                        str = "04";
                    } else if (check_5.isChecked()) {
                        otherText = edit_opinion.getText().toString().trim();
                        if ("".equals(otherText)) {
                            ToastUtils.showToast(mContext, "请输入其他意见!");
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
            case R.id.check_0:
                if (isCheck) {
                    check_1.setChecked(false);
                    check_2.setChecked(false);
                    check_3.setChecked(false);
                    check_4.setChecked(false);
                    check_5.setChecked(false);
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_1:
                if (isCheck) {
                    check_0.setChecked(false);
                    check_2.setChecked(false);
                    check_3.setChecked(false);
                    check_4.setChecked(false);
                    check_5.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_2:
                if (isCheck) {
                    check_0.setChecked(false);
                    check_1.setChecked(false);
                    check_3.setChecked(false);
                    check_4.setChecked(false);
                    check_5.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_3:
                if (isCheck) {
                    check_0.setChecked(false);
                    check_1.setChecked(false);
                    check_2.setChecked(false);
                    check_4.setChecked(false);
                    check_5.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_4:
                if (isCheck) {
                    check_0.setChecked(false);
                    check_1.setChecked(false);
                    check_2.setChecked(false);
                    check_3.setChecked(false);
                    check_5.setChecked(false);
                    edit_opinion.setText("");
                    edit_opinion.setEnabled(false);
                }
                break;
            case R.id.check_5:
                if (isCheck) {
                    check_0.setChecked(false);
                    check_1.setChecked(false);
                    check_2.setChecked(false);
                    check_3.setChecked(false);
                    check_5.setChecked(true);
                    edit_opinion.setEnabled(true);
                }
                break;
        }
    }

    public void show(String str) {
        dialog.show();
        if ("".equals(str)) {
            check_0.setChecked(false);
            check_1.setChecked(false);
            check_2.setChecked(false);
            check_3.setChecked(false);
            check_4.setChecked(false);
            check_5.setChecked(false);
            edit_opinion.setText("");
        } else if ("00".equals(str)) {
            check_0.setChecked(true);
            check_1.setChecked(false);
            check_2.setChecked(false);
            check_3.setChecked(false);
            check_4.setChecked(false);
            check_5.setChecked(false);
            edit_opinion.setText("");
        } else if ("01".equals(str)) {
            check_0.setChecked(false);
            check_1.setChecked(true);
            check_2.setChecked(false);
            check_3.setChecked(false);
            check_4.setChecked(false);
            check_5.setChecked(false);
            edit_opinion.setText("");
        } else if ("02".equals(str)) {
            check_0.setChecked(false);
            check_1.setChecked(false);
            check_2.setChecked(true);
            check_3.setChecked(false);
            check_4.setChecked(false);
            check_5.setChecked(false);
            edit_opinion.setText("");
        } else if ("03".equals(str)) {
            check_0.setChecked(false);
            check_1.setChecked(false);
            check_2.setChecked(false);
            check_3.setChecked(true);
            check_4.setChecked(false);
            check_5.setChecked(false);
            edit_opinion.setText("");
        } else if ("04".equals(str)) {
            check_0.setChecked(false);
            check_1.setChecked(false);
            check_2.setChecked(false);
            check_3.setChecked(false);
            check_4.setChecked(true);
            check_5.setChecked(false);
            edit_opinion.setText("");
        } else {
            check_0.setChecked(false);
            check_1.setChecked(false);
            check_2.setChecked(false);
            check_3.setChecked(false);
            check_4.setChecked(false);
            check_5.setChecked(true);
            edit_opinion.setText(str);
        }
    }


    public interface SetStrData {
        void setNum(String s);
    }

    public void setSetStrData(SetStrData setStrData) {
        this.setStrData = setStrData;
    }
}
