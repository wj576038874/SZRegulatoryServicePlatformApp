package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
import com.winfo.szrsp.app.sdk.entity.table.TSiteSupervisionList;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: jdbgwtmsLayout
 * @Author: lsj
 * @Date: 2017/12/4  13:18
 * @Description:
 * @Version:
 */
public class jdbgjcxmLayout2 extends LinearLayout {
    private Context mContext;
    private TextView tvIndex;
    private TextView tvDec;
    private CheckBox ckItem1, ckItem2, ckItem3;
    ShipXianChangInfo.SiteSupervisionDetailBean siteSupervisionDetailBean;

    public jdbgjcxmLayout2(Context context, ShipXianChangInfo.SiteSupervisionDetailBean siteSupervisionDetailBean) {
        super(context);
        this.siteSupervisionDetailBean = siteSupervisionDetailBean;
        LayoutInflater.from(context).inflate(R.layout.item_jcxm2, this);
        this.mContext = context;
        initView();
        initData();
    }

    private void initView() {
        tvIndex = findViewById(R.id.tvIndex);
        tvDec = findViewById(R.id.tvDec);

        ckItem1 = findViewById(R.id.ckItem1);
        ckItem2 = findViewById(R.id.ckItem2);
        ckItem3 = findViewById(R.id.ckItem3);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        tvIndex.setText("" + siteSupervisionDetailBean.getSeq_no());
        tvDec.setText(siteSupervisionDetailBean.getContent_desc());
        if ("0".equals(siteSupervisionDetailBean.getResult())) {
            ckItem2.setChecked(true);
        } else if ("1".equals(siteSupervisionDetailBean.getResult())) {
            ckItem1.setChecked(true);
        } else if ("2".equals(siteSupervisionDetailBean.getResult())) {
            ckItem3.setChecked(true);
        }
    }

    public TSiteSupervisionList getData() {
        TSiteSupervisionList data = new TSiteSupervisionList();
        data.setSeqNo(siteSupervisionDetailBean.getSeq_no() + "");
        data.setContentDesc(siteSupervisionDetailBean.getContent_desc());
        data.setInspectionResult(siteSupervisionDetailBean.getResult());
        return data;
    }
}
