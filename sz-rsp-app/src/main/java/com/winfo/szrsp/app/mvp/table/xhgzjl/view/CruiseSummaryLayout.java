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

public class CruiseSummaryLayout extends LinearLayout {
    @BindView(R.id.etTime)
    TextView etTime;
    @BindView(R.id.etContext)
    TextView etContext;
    private MinuteTimePickerView mTimePickerView;
    private Context mContext;

    public CruiseSummaryLayout(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_xhgzjl_xhjy, this);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        mTimePickerView = new MinuteTimePickerView(mContext, MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());
    }

    private void initEvent() {
        etTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickView();
            }
        });
    }


    public CruiseWorkData.ctCruisingSummaryDatail getSummaryDatail() {
        CruiseWorkData.ctCruisingSummaryDatail datail = new CruiseWorkData.ctCruisingSummaryDatail();
        if ("".equals(etTime.getText().toString().trim()) && "".equals(etContext.getText().toString().trim())) {
            return null;
        } else {
            datail.setDateTime(etTime.getText().toString().trim());
            datail.setCruisingContent(etContext.getText().toString().trim());
            return datail;
        }
    }

    /**
     * 当前选择时间的TextView
     */
    private void showTimePickView() {
        String trim = etTime.getText().toString().trim();
        mTimePickerView.setPicker(trim);
        mTimePickerView.show();
        mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                etTime.setText(time);
            }
        });
    }
}
