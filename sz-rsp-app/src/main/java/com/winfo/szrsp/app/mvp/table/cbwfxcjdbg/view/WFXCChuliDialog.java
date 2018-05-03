package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view;

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
 * Created by Guan on 2018-01-29.
 */

public class WFXCChuliDialog implements View.OnClickListener{



    private View chuliView;// 证书原因所承载的view
    private int width;
    // 距离两边的像素
    private int magin;
    private Context mContext;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;

    private ImageButton cbwfxc_cancel_btn;

    private TextView cbwfxc_tv_save;

    private CheckBox cbwfxc_check_1;
    private CheckBox cbwfxc_check_2;
    private CheckBox cbwfxc_check_3;
    private CheckBox cbwfxc_check_4;
    private EditText cbwfxc_edit;
    // 数据接口
    OnGetData mOnGetData;
    private String chuli="";
    private Dialog dialog;
    public WFXCChuliDialog(Context context ) {
        this.mContext = context;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(mContext).inflate(R.layout.cbwfxc_dialog_chuli, null);
        dialog.show();
        dialog.setContentView(chuliView);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width-2*magin;
        // window.setWindowAnimations(R.style.AnimationDialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }


    private void initView() {
        cbwfxc_check_1=chuliView.findViewById(R.id.cbwfxc_check_1);
        cbwfxc_check_2=chuliView.findViewById(R.id.cbwfxc_check_2);
        cbwfxc_check_3=chuliView.findViewById(R.id.cbwfxc_check_3);
        cbwfxc_check_4=chuliView.findViewById(R.id.cbwfxc_check_4);
        cbwfxc_edit=chuliView.findViewById(R.id.cbwfxc_edit);
        cbwfxc_cancel_btn=chuliView.findViewById(R.id.cbwfxc_cancel_btn);
        cbwfxc_tv_save=chuliView.findViewById(R.id.cbwfxc_tv_save);
    }
    private void initEvent() {
        cbwfxc_check_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbwfxc_check_2.setChecked(false);
                    cbwfxc_check_3.setChecked(false);
                    cbwfxc_check_4.setChecked(false);
                }

            }
        });
        cbwfxc_check_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbwfxc_check_1.setChecked(false);
                    cbwfxc_check_3.setChecked(false);
                    cbwfxc_check_4.setChecked(false);
                }

            }
        });
        cbwfxc_check_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbwfxc_check_1.setChecked(false);
                    cbwfxc_check_2.setChecked(false);
                    cbwfxc_check_4.setChecked(false);
                }

            }
        });
        cbwfxc_check_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbwfxc_check_1.setChecked(false);
                    cbwfxc_check_2.setChecked(false);
                    cbwfxc_check_3.setChecked(false);
                }

            }
        });
        cbwfxc_cancel_btn.setOnClickListener(this);
        cbwfxc_tv_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.cbwfxc_cancel_btn :
                dialog.dismiss();
                break;
            case R.id.cbwfxc_tv_save :

                // 先判断是否被勾选了
                if (cbwfxc_check_4.isChecked() || cbwfxc_check_3.isChecked() || cbwfxc_check_2.isChecked()
                        || cbwfxc_check_1.isChecked()) {
                } else {
                    ToastUtils.showToast(mContext,
                            "请选择处理意见!");
                    return;
                }
                // 如果选中的是 第4个 其他
                if (cbwfxc_check_4.isChecked()) {
                    String qita = cbwfxc_edit.getText().toString().trim();
                    if (qita.length() < 1) {
                        ToastUtils.showToast(mContext,
                                "请输入其他处理意见");
                        return;
                    } else {
                        chuli=qita;
                        mOnGetData.onDataCallBack(chuli);
                        dialog.dismiss();
                    }

                }else if(cbwfxc_check_1.isChecked()){
                    mOnGetData.onDataCallBack("01");
                    dialog.dismiss();
                }else if(cbwfxc_check_2.isChecked()){
                    mOnGetData.onDataCallBack("02");
                    dialog.dismiss();
                }else if(cbwfxc_check_3.isChecked()){
                    mOnGetData.onDataCallBack("03");
                    dialog.dismiss();
                }
                break;

        }
    }

    /**
     * 弹出消息列表
     */
    public void show(String chuli) {
        dialog.show();
        if (dialog.isShowing()) {

            if ("".equals(chuli)) {
                cbwfxc_check_1.setChecked(false);
                cbwfxc_check_2.setChecked(false);
                cbwfxc_check_3.setChecked(false);
                cbwfxc_check_4.setChecked(false);
                cbwfxc_edit.setText("");
                return;
            }
            if(chuli.equals("01")){
                cbwfxc_check_1.setChecked(true);
                cbwfxc_edit.setText("");
            }else if(chuli.equals("02")){
                cbwfxc_check_2.setChecked(true);
                cbwfxc_edit.setText("");
            }else if(chuli.equals("03")){
                cbwfxc_check_3.setChecked(true);
                cbwfxc_edit.setText("");
            }else {
                cbwfxc_check_4.setChecked(true);
                cbwfxc_edit.setText(chuli);
            }

        }
    }
    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }
    // 数据接口抽象方法
    public interface OnGetData {
        abstract void onDataCallBack(String chuli);
    }

}
