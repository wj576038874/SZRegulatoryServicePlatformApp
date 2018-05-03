package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.view
 * @Filename: DetailcljdLayout
 * @Author: lsj
 * @Date: 2017/12/8  15:02
 * @Description:
 * @Version:
 */
public class DetailcljdLayout extends LinearLayout {
    private DetailjdbgData.list data;
    public DetailcljdLayout(Context context, DetailjdbgData.list data) {
        super(context);
        this.data = data;
        LayoutInflater.from(context).inflate(R.layout.item_cljd_detail,this);
        initView();
    }

    private void initView() {
        TextView tv_qxdm = findViewById(R.id.tv_qxdm);
        TextView tv_qxms = findViewById(R.id.tv_qxms);
        TextView tv_yj = findViewById(R.id.tv_yj);
        TextView tv_cljd = findViewById(R.id.tv_cljd);
        TextView tv_cjzr = findViewById(R.id.tv_cjzr);
        tv_qxdm.setText(data.getDefectCode());
        tv_qxms.setText(data.getDefectDesc());
        tv_yj.setText(data.getEnforceFoundation());
        tv_cljd.setText(data.getCommentCode());
        tv_cjzr.setText(data.getIsInspectOrgRelated());
    }
}
