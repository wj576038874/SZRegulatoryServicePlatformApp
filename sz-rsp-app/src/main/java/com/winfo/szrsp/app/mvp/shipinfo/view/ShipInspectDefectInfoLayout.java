package com.winfo.szrsp.app.mvp.shipinfo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.shipdata.FlagStateControl;
import com.winfo.szrsp.app.sdk.entity.shipdata.FlagStateControlDetail;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 船舶安检信息缺陷信息
 * Created by HoBo on 2018/4/19.
 */

public class ShipInspectDefectInfoLayout extends LinearLayout {
    @BindView(R.id.etQxdm)
    TextView etQxdm;
    @BindView(R.id.etQxms)
    TextView etQxms;
    @BindView(R.id.etQxyj)
    TextView etQxyj;
    @BindView(R.id.etQxcljd)
    TextView etQxcljd;

    private StateControlData.FlagStateControlDetailBean detailBean;

    private Context mCOntext;

    public ShipInspectDefectInfoLayout(Context context, StateControlData.FlagStateControlDetailBean detailBean) {
        super(context);
        this.mCOntext = context;
        this.detailBean = detailBean;
        LayoutInflater.from(context).inflate(R.layout.item_ajxx_qxxx, this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etQxdm.setText(detailBean.getDefect_code());
        etQxms.setText(detailBean.getDefect_desc());
        etQxyj.setText(detailBean.getEnforce_foundation());
        etQxcljd.setText(detailBean.getComment_desc());
    }
}
