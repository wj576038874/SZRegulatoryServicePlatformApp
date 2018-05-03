package com.winfo.szrsp.app.mvp.shipinfo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 船舶现场监督问题缺陷信息
 * Created by HoBo on 2018/4/19.
 */

public class ShipInspectSupervisionInfoLayout extends LinearLayout {
    @BindView(R.id.etWtms)
    TextView etWtms;
    @BindView(R.id.tvYes)
    TextView tvYes;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvNull)
    TextView tvNull;
    private ShipXianChangInfo.SiteSupervisionDetailBean detailBean;

    private Context mCOntext;

    public ShipInspectSupervisionInfoLayout(Context context, ShipXianChangInfo.SiteSupervisionDetailBean siteSupervisionDetailBean) {
        super(context);
        this.mCOntext = context;
        this.detailBean = siteSupervisionDetailBean;
        LayoutInflater.from(context).inflate(R.layout.item_xcjd_wtjc, this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etWtms.setText(detailBean.getContent_desc());
        if ("0".equals(detailBean.getResult())) {
            tvNo.setText("✔");
            tvNo.setTextColor(getResources().getColor(R.color.blue));
            tvYes.setText("");
            tvNull.setText("");
        } else if ("1".equals(detailBean.getResult())) {
            tvNo.setText("");
            tvYes.setText("✔");
            tvYes.setTextColor(getResources().getColor(R.color.blue));
            tvNull.setText("");
        } else if ("2".equals(detailBean.getResult())) {
            tvNo.setText("");
            tvYes.setText("");
            tvNull.setText("✔");
            tvNull.setTextColor(getResources().getColor(R.color.blue));
        }
    }
}
