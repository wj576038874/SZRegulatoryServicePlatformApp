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
 * Created by HoBo on 2018/4/11.
 */

public class MaoDiDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ImageButton but_close;
    private EditText etText;
    private TextView but_save;
    private CheckBox ckItem1, ckItem2, ckItem3, ckItem4, ckItem5, ckItem6, ckItem7, ckItem8, ckItem9, ckItem10, ckItem11, ckItem12,
            ckItem13, ckItem14, ckItem15, ckItem16, ckItem17, ckItem18, ckItem19, ckItem20, ckItem21;
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

    public MaoDiDialog(Context context) {
        this.mContext = context;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(context).inflate(R.layout.dialog_xhgzjl_maodi, null);
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
        ckItem5 = chuliView.findViewById(R.id.ckItem5);
        ckItem6 = chuliView.findViewById(R.id.ckItem6);
        ckItem7 = chuliView.findViewById(R.id.ckItem7);
        ckItem8 = chuliView.findViewById(R.id.ckItem8);
        ckItem9 = chuliView.findViewById(R.id.ckItem9);
        ckItem10 = chuliView.findViewById(R.id.ckItem10);
        ckItem11 = chuliView.findViewById(R.id.ckItem11);
        ckItem12 = chuliView.findViewById(R.id.ckItem12);
        ckItem13 = chuliView.findViewById(R.id.ckItem13);
        ckItem14 = chuliView.findViewById(R.id.ckItem14);
        ckItem15 = chuliView.findViewById(R.id.ckItem15);
        ckItem16 = chuliView.findViewById(R.id.ckItem16);
        ckItem17 = chuliView.findViewById(R.id.ckItem17);
        ckItem18 = chuliView.findViewById(R.id.ckItem18);
        ckItem19 = chuliView.findViewById(R.id.ckItem19);
        ckItem20 = chuliView.findViewById(R.id.ckItem20);
        ckItem21 = chuliView.findViewById(R.id.ckItem21);

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
        ckItem5.setOnCheckedChangeListener(this);
        ckItem6.setOnCheckedChangeListener(this);
        ckItem7.setOnCheckedChangeListener(this);
        ckItem8.setOnCheckedChangeListener(this);
        ckItem9.setOnCheckedChangeListener(this);
        ckItem10.setOnCheckedChangeListener(this);
        ckItem11.setOnCheckedChangeListener(this);
        ckItem12.setOnCheckedChangeListener(this);
        ckItem13.setOnCheckedChangeListener(this);
        ckItem14.setOnCheckedChangeListener(this);
        ckItem15.setOnCheckedChangeListener(this);
        ckItem16.setOnCheckedChangeListener(this);
        ckItem17.setOnCheckedChangeListener(this);
        ckItem18.setOnCheckedChangeListener(this);
        ckItem19.setOnCheckedChangeListener(this);
        ckItem20.setOnCheckedChangeListener(this);
        ckItem21.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_close:
                dialog.dismiss();
                break;
            case R.id.but_save:
                if (!ckItem1.isChecked() && !ckItem2.isChecked() && !ckItem3.isChecked() && !ckItem4.isChecked() && !ckItem5.isChecked() && !ckItem6.isChecked()
                        && !ckItem7.isChecked() && !ckItem8.isChecked() && !ckItem9.isChecked() && !ckItem10.isChecked() && !ckItem11.isChecked() && !ckItem12.isChecked()
                        && !ckItem13.isChecked() && !ckItem14.isChecked() && !ckItem15.isChecked() && !ckItem16.isChecked() && !ckItem17.isChecked() && !ckItem18.isChecked() && !ckItem19.isChecked()
                        && !ckItem20.isChecked() && !ckItem21.isChecked()) {
                    ToastUtils.showToast(mContext, "请选择检查代码!");
                    return;
                } else {
                    if (ckItem1.isChecked()) {
                        str = "大鹏湾1号锚地";
                    } else if (ckItem2.isChecked()) {
                        str = "大鹏湾2号锚地";
                    } else if (ckItem3.isChecked()) {
                        str = "大鹏湾3号锚地";
                    } else if (ckItem4.isChecked()) {
                        str = "大鹏湾4号锚地";
                    } else if (ckItem5.isChecked()) {
                        str = "大鹏湾5号锚地";
                    } else if (ckItem6.isChecked()) {
                        str = "大亚湾1号锚地";
                    } else if (ckItem7.isChecked()) {
                        str = "大亚湾2号锚地";
                    } else if (ckItem8.isChecked()) {
                        str = "大屿山1号锚地";
                    } else if (ckItem9.isChecked()) {
                        str = "大屿山2号锚地";
                    } else if (ckItem10.isChecked()) {
                        str = "普通货船待泊锚地";
                    } else if (ckItem11.isChecked()) {
                        str = "液货船待泊锚地";
                    } else if (ckItem12.isChecked()) {
                        str = "东角头油轮待泊锚地";
                    } else if (ckItem13.isChecked()) {
                        str = "东角头货轮待泊锚地";
                    } else if (ckItem14.isChecked()) {
                        str = "小型船舶待泊锚地";
                    } else if (ckItem15.isChecked()) {
                        str = "大铲锚地";
                    } else if (ckItem16.isChecked()) {
                        str = "孖洲西危险品锚地";
                    } else if (ckItem17.isChecked()) {
                        str = "黄田1号锚地";
                    } else if (ckItem18.isChecked()) {
                        str = "黄田2号锚地";
                    } else if (ckItem19.isChecked()) {
                        str = "黄田3号锚地";
                    } else if (ckItem20.isChecked()) {
                        str = "龙鼓水道锚地";
                    } else if (ckItem21.isChecked()) {
                        otherText = etText.getText().toString().trim();
                        if ("".equals(otherText)) {
                            ToastUtils.showToast(mContext, "请填写锚地、停泊区、库");
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
            case R.id.ckItem1:
                if (isCheck) {
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem2:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem3:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem4:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem5:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem6:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem7:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem8:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem9:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem10:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem11:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem12:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem13:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem14:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem15:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem16:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem17:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem18:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem19:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem20.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem20:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem21.setChecked(false);
                    etText.setEnabled(false);
                    etText.setText("");
                }
                break;
            case R.id.ckItem21:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    ckItem4.setChecked(false);
                    ckItem5.setChecked(false);
                    ckItem6.setChecked(false);
                    ckItem7.setChecked(false);
                    ckItem8.setChecked(false);
                    ckItem9.setChecked(false);
                    ckItem10.setChecked(false);
                    ckItem11.setChecked(false);
                    ckItem12.setChecked(false);
                    ckItem13.setChecked(false);
                    ckItem14.setChecked(false);
                    ckItem15.setChecked(false);
                    ckItem16.setChecked(false);
                    ckItem17.setChecked(false);
                    ckItem18.setChecked(false);
                    ckItem19.setChecked(false);
                    ckItem20.setChecked(false);
                    etText.setEnabled(true);
                }
                break;
        }
    }

    public void show(String str) {
        if ("大鹏湾1号锚地".equals(str)) {
            ckItem1.setChecked(true);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大鹏湾2号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(true);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大鹏湾3号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(true);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大鹏湾4号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(true);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大鹏湾5号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(true);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大亚湾1号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(true);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大亚湾2号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(true);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大屿山1号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(true);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大屿山2号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(true);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("普通货船待泊锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(true);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("液货船待泊锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(true);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("东角头油轮待泊锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(true);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("东角头货轮待泊锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(true);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("小型船舶待泊锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(true);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("大铲锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(true);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("孖洲西危险品锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(true);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("黄田1号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(true);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("黄田2号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(true);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("黄田3号锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(true);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else if ("龙鼓水道锚地".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(true);
            ckItem21.setChecked(false);
        } else if ("".equals(str)) {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(false);
        } else {
            ckItem1.setChecked(false);
            ckItem2.setChecked(false);
            ckItem3.setChecked(false);
            ckItem4.setChecked(false);
            ckItem5.setChecked(false);
            ckItem6.setChecked(false);
            ckItem7.setChecked(false);
            ckItem8.setChecked(false);
            ckItem9.setChecked(false);
            ckItem10.setChecked(false);
            ckItem11.setChecked(false);
            ckItem12.setChecked(false);
            ckItem13.setChecked(false);
            ckItem14.setChecked(false);
            ckItem15.setChecked(false);
            ckItem16.setChecked(false);
            ckItem17.setChecked(false);
            ckItem18.setChecked(false);
            ckItem19.setChecked(false);
            ckItem20.setChecked(false);
            ckItem21.setChecked(true);
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
