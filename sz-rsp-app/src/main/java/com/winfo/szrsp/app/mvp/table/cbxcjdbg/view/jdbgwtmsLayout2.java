package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.annotation.SuppressLint;
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
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
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
public class jdbgwtmsLayout2 extends LinearLayout {
    private TextView tv_index;
    private TextView tv_wtms;
    private TextView tv_cljd;
    private TextView tv_bz;
    private CheckBox check_yes;
    private CheckBox check_no;
    private String str = "";
    private String cljd;
    private Context mContext;
    ShipXianChangInfo.SiteSupervisionDetailBean siteSupervisionDetailBean;

    public jdbgwtmsLayout2(Context context, ShipXianChangInfo.SiteSupervisionDetailBean siteSupervisionDetailBean) {
        super(context);
        this.mContext = context;
        this.siteSupervisionDetailBean = siteSupervisionDetailBean;
        LayoutInflater.from(context).inflate(R.layout.item_jdbg2, this);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        tv_index = findViewById(R.id.tv_index);
        tv_wtms = findViewById(R.id.tv_wtms);
        tv_cljd = findViewById(R.id.tv_cljd);
        tv_bz = findViewById(R.id.tv_bz);
        check_yes = findViewById(R.id.check_yes);
        check_no = findViewById(R.id.check_no);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        tv_index.setText("" + siteSupervisionDetailBean.getSeq_no());
        tv_wtms.setText(siteSupervisionDetailBean.getDescription());
        if (siteSupervisionDetailBean.getComment_code().equals("19") || siteSupervisionDetailBean.getComment_code().equals("99")) {
            cljd = siteSupervisionDetailBean.getComment_code();
            tv_cljd.setText(siteSupervisionDetailBean.getComment_code() + "(" + siteSupervisionDetailBean.getComment_code() + ")");
        } else {
            cljd = siteSupervisionDetailBean.getComment_code();
            tv_cljd.setText(siteSupervisionDetailBean.getComment_code());
        }
        tv_bz.setText(siteSupervisionDetailBean.getComment_desc());
    }

    private void initEvent() {
        check_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check_yes.setChecked(true);
                    check_no.setChecked(false);
                    str = "1";
                    cljd = "10";
                }
            }
        });
        check_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check_yes.setChecked(false);
                    check_no.setChecked(true);
                    str = "0";
                    cljd = siteSupervisionDetailBean.getComment_code();
                }
            }
        });
    }


    public cbxcjdbgData.detail getData() {
        cbxcjdbgData.detail detail = new cbxcjdbgData.detail();
        if (!check_yes.isChecked() && !check_no.isChecked()) {
            ToastUtils.showToast(mContext, "请选择复查决定");
            return null;
        } else {
            detail.setSeqNo("" + siteSupervisionDetailBean.getSeq_no());
            detail.setDescription(siteSupervisionDetailBean.getDescription());
            detail.setCommentDesc(siteSupervisionDetailBean.getComment_desc());
            detail.setCommentCode(cljd);
            detail.setCorrectMark(str);
            return detail;
        }
    }
}
