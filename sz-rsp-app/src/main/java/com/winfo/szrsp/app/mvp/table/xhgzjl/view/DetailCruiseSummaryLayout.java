package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 巡航纪要
 * Created by HoBo on 2018/4/11.
 */

public class DetailCruiseSummaryLayout extends LinearLayout {
    @BindView(R.id.etTime)
    TextView etTime;
    @BindView(R.id.etContext)
    TextView etContext;
    private MinuteTimePickerView mTimePickerView;
    private Context mContext;
    private CruiseWorkData.ctCruisingSummaryDatail datail;

    public DetailCruiseSummaryLayout(Context context, CruiseWorkData.ctCruisingSummaryDatail datail) {
        super(context);
        this.mContext = context;
        this.datail = datail;
        LayoutInflater.from(context).inflate(R.layout.item_xhgzjl_xhjy, this);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        etTime.setText(datail.getDateTime());
        etContext.setText(datail.getCruisingContent());
    }


}
