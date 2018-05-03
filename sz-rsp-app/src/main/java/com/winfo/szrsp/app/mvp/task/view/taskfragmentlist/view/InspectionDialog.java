package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.task.view.TaskRejectDialog;

import java.util.List;

/**
 * Created by winfo053 on 2018/3/9.
 */

public class InspectionDialog {
    private View inspectionView;
    private Dialog dialogInspection;
    Context mContext;
    Button btnCancel,btnSubmit;
    RecyclerView recyclerView;
    List<MultiItemEntity> inspectionAll;
    ExpandableTaskInspectionAdapter adapter;
    List<MultiItemEntity> inspectionSelect;
    // 数据接口
    OnGetData mOnGetData;
    public  InspectionDialog(Context context,List<MultiItemEntity> inspectionAll) {
        mContext=context;
        this.inspectionAll=inspectionAll;
        dialogInspection = new AlertDialog.Builder(mContext).create();
        dialogInspection.setCancelable(true);//
        dialogInspection.setCanceledOnTouchOutside(false);//
        inspectionView = LayoutInflater.from(mContext).inflate(R.layout.dialog_taskassign_inspection, null);
        dialogInspection.show();
        dialogInspection.setContentView(inspectionView);
        Window window = dialogInspection.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width =ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }


    private void initView() {
        recyclerView=inspectionView.findViewById(R.id.rv);
        btnCancel= inspectionView.findViewById(R.id.btnCancel);
        btnSubmit= inspectionView.findViewById(R.id.btnSubmit);
        adapter=new ExpandableTaskInspectionAdapter(inspectionAll,mContext);
        final GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
    private void initEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInspection.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnGetData.onDataCallBack(adapter.returnData());
                dialogInspection.dismiss();
            }
        });

    }
    public void showDialog(){
        dialogInspection.show();
    }
    public void dismiss(){
        dialogInspection.dismiss();
    }
    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }
    public interface OnGetData {
        void onDataCallBack(List<MultiItemEntity> data);
    }
}
