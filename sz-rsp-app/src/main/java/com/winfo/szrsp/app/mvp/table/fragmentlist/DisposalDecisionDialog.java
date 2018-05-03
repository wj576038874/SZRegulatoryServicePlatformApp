package com.winfo.szrsp.app.mvp.table.fragmentlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ToastUtils;

/**
 * Created by Guan on 2017-12-13.
 */

public class DisposalDecisionDialog {
    private View rejectView;// 退回描述所承载的view
    private Dialog dialogReject;
    Button btnCancel,btnSubmit;
    Context mContext;
    // 数据接口
    OnGetData mOnGetData;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;

    public DisposalDecisionDialog(Context context) {
        mContext=context;
        dialogReject = new AlertDialog.Builder(mContext).create();
        dialogReject.setCancelable(true);//
        dialogReject.setCanceledOnTouchOutside(false);//
        rejectView = LayoutInflater.from(mContext).inflate(R.layout.pop_disposal_decision, null);
        dialogReject.show();
        dialogReject.setContentView(rejectView);
        Window window = dialogReject.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width =ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }
    private void initView() {

        btnCancel= rejectView.findViewById(R.id.btnCancel);
        btnSubmit= rejectView.findViewById(R.id.btnSubmit);
        checkBox1=rejectView.findViewById(R.id.disposal_decision_ck1);
        checkBox2=rejectView.findViewById(R.id.disposal_decision_ck2);
        checkBox3=rejectView.findViewById(R.id.disposal_decision_ck3);
        checkBox4=rejectView.findViewById(R.id.disposal_decision_ck4);

        setOnlyOneCheck(checkBox1,checkBox2,checkBox3,checkBox4);
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
                if(mOnGetData!=null){
                    if(!checkBox1.isChecked()&&!checkBox2.isChecked()&&!checkBox3.isChecked()&&!checkBox4.isChecked()){
                        ToastUtils.showToast(mContext,"请勾选");
                    }else {
                        mOnGetData.onDataCallBack(getCheckedText(checkBox1,checkBox2,checkBox3,checkBox4));
                    }



                }
            }
        });
    }
    public void showDialog(){
        dialogReject.show();
    }
    public void dismiss(){
        dialogReject.dismiss();
    }



    /**
     * 设置四个checkbox只能单选
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     * @param dxchcjcb_check3
     * @param dxchcjcb_check4
     */
    private void setOnlyOneCheck(final CheckBox dxchcjcb_check1, final CheckBox dxchcjcb_check2,final CheckBox dxchcjcb_check3,final CheckBox dxchcjcb_check4) {
        dxchcjcb_check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                    dxchcjcb_check4.setChecked(false);
                }

            }
        });
        dxchcjcb_check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check1.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                    dxchcjcb_check4.setChecked(false);
                }

            }
        });
        dxchcjcb_check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check1.setChecked(false);
                    dxchcjcb_check4.setChecked(false);
                }

            }
        });
        dxchcjcb_check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check1.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                }

            }
        });
    }
    /**
     * 获得checkbox选中的值
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     * @param dxchcjcb_check3
     * @param dxchcjcb_check4
     * @return
     */
    private String getCheckedText(CheckBox dxchcjcb_check1, CheckBox dxchcjcb_check2, CheckBox dxchcjcb_check3, CheckBox dxchcjcb_check4) {

        if(dxchcjcb_check1.isChecked()){
            return dxchcjcb_check1.getText().toString();
        }else if(dxchcjcb_check2.isChecked()){
            return dxchcjcb_check2.getText().toString();
        }else if(dxchcjcb_check3.isChecked()){
            return dxchcjcb_check3.getText().toString();
        }else if(dxchcjcb_check4.isChecked()){
            return dxchcjcb_check4.getText().toString();
        }else {
            return "";
        }

    }

    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }
    // 数据接口抽象方法
    public interface OnGetData {
        abstract void onDataCallBack(String disposalDecision);
    }

}
