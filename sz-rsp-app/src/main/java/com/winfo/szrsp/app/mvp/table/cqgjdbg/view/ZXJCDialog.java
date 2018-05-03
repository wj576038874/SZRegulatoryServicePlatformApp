package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

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

public class ZXJCDialog implements View.OnClickListener{



    private View chuliView;// 证书原因所承载的view
    private int width;
    // 距离两边的像素
    private int magin;
    private Context mContext;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;

    private ImageButton zxjc_cancel_btn;

    private TextView zxjc_tv_save;

    private CheckBox zxjc_check_1;
    private CheckBox zxjc_check_2;
    private CheckBox zxjc_check_3;
    private CheckBox zxjc_check_4;
    private CheckBox zxjc_check_5;
    private CheckBox zxjc_check_6;
    private CheckBox zxjc_check_7;
    private CheckBox zxjc_check_8;
    private CheckBox zxjc_check_9;
    private EditText zxjc_edit;
    // 数据接口
    OnGetData mOnGetData;
    private String chuli="";
    private Dialog dialog;
    public ZXJCDialog(Context context ) {
        this.mContext = context;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(mContext).inflate(R.layout.cqh_dialog_zxjc, null);
        dialog.show();
        dialog.setContentView(chuliView);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width-2*magin;
        // window.setWindowAnimations(R.style.AnimationDialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }


    private void initView() {
        zxjc_check_1=chuliView.findViewById(R.id.zxjc_check_1);
        zxjc_check_2=chuliView.findViewById(R.id.zxjc_check_2);
        zxjc_check_3=chuliView.findViewById(R.id.zxjc_check_3);
        zxjc_check_4=chuliView.findViewById(R.id.zxjc_check_4);
        zxjc_check_5=chuliView.findViewById(R.id.zxjc_check_5);
        zxjc_check_6=chuliView.findViewById(R.id.zxjc_check_6);
        zxjc_check_7=chuliView.findViewById(R.id.zxjc_check_7);
        zxjc_check_8=chuliView.findViewById(R.id.zxjc_check_8);
        zxjc_check_9=chuliView.findViewById(R.id.zxjc_check_9);
        zxjc_edit=chuliView.findViewById(R.id.zxjc_edit);
        zxjc_cancel_btn=chuliView.findViewById(R.id.zxjc_cancel_btn);
        zxjc_tv_save=chuliView.findViewById(R.id.zxjc_tv_save);
    }
    private void initEvent() {
        zxjc_check_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
//                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);

                }

            }
        });
        zxjc_check_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
//                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
//                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
//                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
//                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
//                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
//                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
//                    zxjc_check_8.setChecked(false);
                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_check_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    zxjc_check_1.setChecked(false);
                    zxjc_check_2.setChecked(false);
                    zxjc_check_3.setChecked(false);
                    zxjc_check_4.setChecked(false);
                    zxjc_check_5.setChecked(false);
                    zxjc_check_6.setChecked(false);
                    zxjc_check_7.setChecked(false);
                    zxjc_check_8.setChecked(false);
//                    zxjc_check_9.setChecked(false);
                }

            }
        });
        zxjc_cancel_btn.setOnClickListener(this);
        zxjc_tv_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.zxjc_cancel_btn :
                dialog.dismiss();
                break;
            case R.id.zxjc_tv_save :

                // 先判断是否被勾选了
                if (zxjc_check_9.isChecked() || zxjc_check_8.isChecked() || zxjc_check_7.isChecked()
                        || zxjc_check_6.isChecked() || zxjc_check_5.isChecked() || zxjc_check_4.isChecked()
                        || zxjc_check_3.isChecked() || zxjc_check_2.isChecked() || zxjc_check_1.isChecked()) {
                } else {
                    ToastUtils.showToast(mContext,
                            "请选择专项检查!");
                    return;
                }
                // 如果选中的是 第9个 其他
                if (zxjc_check_9.isChecked()) {
                    String qita = zxjc_edit.getText().toString().trim();
                    if (qita.length() < 1) {
                        ToastUtils.showToast(mContext,
                                "请输入其他");
                        return;
                    } else {
                        chuli=qita;
                        mOnGetData.onDataCallBack("0000000001",chuli);
                        dialog.dismiss();
                    }
                } else if(zxjc_check_1.isChecked()){
                    mOnGetData.onDataCallBack("0000000001",zxjc_check_1.getText().toString());
                    dialog.dismiss();
                }else if(zxjc_check_2.isChecked()){
                    mOnGetData.onDataCallBack("0000000002",zxjc_check_2.getText().toString());
                    dialog.dismiss();
                }else if(zxjc_check_3.isChecked()){
                   mOnGetData.onDataCallBack("0000000005",zxjc_check_3.getText().toString());
                    dialog.dismiss();
                }else if(zxjc_check_4.isChecked()){
                   mOnGetData.onDataCallBack("0000000008",zxjc_check_4.getText().toString());
                   dialog.dismiss();
               }else if(zxjc_check_5.isChecked()){
                   mOnGetData.onDataCallBack("0000000009",zxjc_check_5.getText().toString());
                   dialog.dismiss();
               }else if(zxjc_check_6.isChecked()){
                   mOnGetData.onDataCallBack("0000000010",zxjc_check_6.getText().toString());
                   dialog.dismiss();
               }else if(zxjc_check_7.isChecked()){
                   mOnGetData.onDataCallBack("0500000001",zxjc_check_7.getText().toString());
                   dialog.dismiss();
               }else if(zxjc_check_8.isChecked()){
                   mOnGetData.onDataCallBack("0500000002",zxjc_check_8.getText().toString());
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
            if(chuli.equals(zxjc_check_1.getText().toString())){
                zxjc_check_1.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_2.getText().toString())){
                zxjc_check_2.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_3.getText().toString())){
                zxjc_check_3.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_4.getText().toString())){
                zxjc_check_4.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_5.getText().toString())){
                zxjc_check_5.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_6.getText().toString())){
                zxjc_check_6.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_7.getText().toString())){
                zxjc_check_7.setChecked(true);
                zxjc_edit.setText("");
            }else if(chuli.equals(zxjc_check_8.getText().toString())){
                zxjc_check_8.setChecked(true);
                zxjc_edit.setText("");
            }else{
                zxjc_check_9.setChecked(true);
                zxjc_edit.setText(chuli);
            }

        }
    }
    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }
    // 数据接口抽象方法
    public interface OnGetData {
        abstract void onDataCallBack(String type,String name);
    }

}
