package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 巡航异常情况
 * Created by HoBo on 2018/4/11.
 */

public class DetailCruiseSituationLayout extends LinearLayout {
    @BindView(R.id.etContext)
    TextView etContext;
    @BindView(R.id.etDecision)
    TextView etDecision;
    private Context mContext;
    private ProcessingDecisionDialog dialog;
    private CruiseWorkData.ctCruisingRiskDetail detail;

    public DetailCruiseSituationLayout(Context context, CruiseWorkData.ctCruisingRiskDetail detail) {
        super(context);
        this.mContext = context;
        this.detail = detail;
        LayoutInflater.from(context).inflate(R.layout.item_xhgzjl_ycqk, this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etDecision.setText(detail.getTheDecision());
        etContext.setText(detail.getTaskTypeDetail());
    }
}
