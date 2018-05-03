package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: DetailjdbgwtmsLayout
 * @Author: lsj
 * @Date: 2017/12/8  13:29
 * @Description:
 * @Version:
 */
public class DetailjdbgwtmsLayout extends LinearLayout {
    private DetailcbxcjdbgData.list data;

    public DetailjdbgwtmsLayout(Context context, DetailcbxcjdbgData.list data) {
        super(context);
        this.data = data;
        LayoutInflater.from(context).inflate(R.layout.item_jdbg_detail, this);
        initView();
    }

    private void initView() {
        TextView tv_index = findViewById(R.id.tv_index);
        TextView tv_wtms = findViewById(R.id.tv_wtms);
        TextView tv_cljd = findViewById(R.id.tv_cljd);
        TextView tv_bz = findViewById(R.id.tv_bz);
        tv_bz.setText(data.getCommentDesc());
        tv_index.setText(data.getSeqNo() + "");
        tv_wtms.setText(data.getDescription());
        if (data.getCommentCode().equals("19") || data.getCommentCode().equals("99")) {
            tv_cljd.setText(data.getCommentCode() + "(" + data.getCommentDesc() + ")");
        } else {
            tv_cljd.setText(data.getCommentCode());
        }
    }
}
