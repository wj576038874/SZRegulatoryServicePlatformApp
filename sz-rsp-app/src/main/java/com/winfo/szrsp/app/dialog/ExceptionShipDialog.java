package com.winfo.szrsp.app.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ToastUtils;

/**
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipDialog {

    private View rejectView;// 退回描述所承载的view
    private Dialog dialogReject;
    EditText pop_reject_reason_edt;
    Button btnCancel,btnSubmit;
    Context mContext;
    // 数据接口
    OnGetData mOnGetData;

    public  ExceptionShipDialog(Context context) {
        mContext=context;
        dialogReject = new AlertDialog.Builder(mContext).create();
        dialogReject.setCancelable(true);//
        dialogReject.setCanceledOnTouchOutside(false);//
        rejectView = LayoutInflater.from(mContext).inflate(R.layout.pop_exception_dec, null);
        dialogReject.show();
        dialogReject.setContentView(rejectView);
        Window window = dialogReject.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }
    private void initView() {
        pop_reject_reason_edt = rejectView.findViewById(R.id.pop_reject_reason_edt);
        btnCancel= rejectView.findViewById(R.id.btnCancel);
        btnSubmit= rejectView.findViewById(R.id.btnSubmit);
    }
    private void initEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogReject.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pop_reject_reason_edt.getText().toString().length()<1){
                    ToastUtils.showToast(mContext,"请填写异常描述");
                }else {
                    mOnGetData.onDataCallBack(pop_reject_reason_edt.getText().toString());
                }
            }
        });
    }

    public String getDec(){
        return pop_reject_reason_edt.getText().toString();
    }
    public void showDialog(){
        dialogReject.show();
    }
    public void dismiss(){
        dialogReject.dismiss();
    }

    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }
    // 数据接口抽象方法
    public interface OnGetData {
        abstract void onDataCallBack(String dec);
    }
}
