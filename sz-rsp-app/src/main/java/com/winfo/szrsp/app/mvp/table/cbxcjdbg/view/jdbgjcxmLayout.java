package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.TSiteSupervisionList;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: jdbgwtmsLayout
 * @Author: lsj
 * @Date: 2017/12/4  13:18
 * @Description:
 * @Version:
 */
public class jdbgjcxmLayout extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    private Context mContext;
    private int num;
    private TextView tvIndex;
    private TextView tvDec;
    private CheckBox ckItem1, ckItem2, ckItem3;
    public DelItem delItem;
    public String description;
    public jdbgjcxmLayout(Context context, int num,String description) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_jcxm, this);
        this.mContext = context;
        this.num = num;
        this.description=description;
        initView();
        initData();
        initEvent();
    }
//    public jdbgjcxmLayout(Context context, int num) {
//        super(context);
//        LayoutInflater.from(context).inflate(R.layout.item_jcxm, this);
//        this.mContext = context;
//        this.num = num;
//        initView();
//        initData();
//        initEvent();
//    }
    private void initView() {
        tvIndex = findViewById(R.id.tvIndex);
        tvDec = findViewById(R.id.tvDec);

        ckItem1 = findViewById(R.id.ckItem1);
        ckItem2 = findViewById(R.id.ckItem2);
        ckItem3 = findViewById(R.id.ckItem3);
        if (num >= 10) {
            ckItem1.setChecked(false);
            ckItem1.setClickable(false);
            ckItem2.setChecked(true);
            ckItem2.setClickable(false);
            ckItem3.setChecked(false);
            ckItem3.setClickable(false);
        }
    }

    private void initEvent() {
        ckItem1.setOnCheckedChangeListener(this);
        ckItem2.setOnCheckedChangeListener(this);
        ckItem3.setOnCheckedChangeListener(this);
//        if(num==9){
//            ckItem2.setChecked(true);
//        }
    }
    public void setCkItem2Checked(){
        ckItem2.setChecked(true);
    }
    private void initData() {
        tvIndex.setText(num + "");
        tvDec.setText(description);

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ckItem1:
                if (isCheck) {
                    ckItem1.setChecked(true);
                    ckItem2.setChecked(false);
                    ckItem3.setChecked(false);
                    if (delItem != null) {
                        delItem.delItems(tvIndex.getText().toString().trim());
                    }
                }
                break;
            case R.id.ckItem2:
                if (isCheck) {
                    ckItem1.setChecked(false);
                    ckItem2.setChecked(true);
                    ckItem3.setChecked(false);
                    if (delItem != null) {
                        delItem.addItems(tvIndex.getText().toString().trim());
                    }
                } else {
                    if (delItem != null) {
                        delItem.delItems(tvIndex.getText().toString().trim());
                    }
                }
                break;
            case R.id.ckItem3:
                if (isCheck) {
                    ckItem3.setChecked(true);
                    ckItem2.setChecked(false);
                    ckItem1.setChecked(false);
                    if (delItem != null) {
                        delItem.delItems(tvIndex.getText().toString().trim());
                    }
                }
                break;
        }
    }

    public TSiteSupervisionList getData() {
        TSiteSupervisionList data=new TSiteSupervisionList();
        data.setSeqNo(""+num);
        data.setContentDesc(description);
        if(ckItem1.isChecked()){
            data.setInspectionResult("0");
        }
        if(ckItem2.isChecked()){
            data.setInspectionResult("1");
        }
        if(ckItem3.isChecked()){
            data.setInspectionResult("2");
        }
        return data;
    }


    public interface DelItem {
        void addItems(String indexItem);

        void delItems(String indexItem);
    }

    public void setDelItem(DelItem delItem) {
        this.delItem = delItem;
    }
}
