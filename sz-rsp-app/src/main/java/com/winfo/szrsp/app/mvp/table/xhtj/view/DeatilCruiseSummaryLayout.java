package com.winfo.szrsp.app.mvp.table.xhtj.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsDetail;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.util.Date;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj.view
 * @Filename: DeatilCruiseSummaryLayout
 * @Author: lsj
 * @Date: 2018/1/26  13:53
 * @Description:
 * @Version:
 */
public class DeatilCruiseSummaryLayout extends LinearLayout {
    private TextView tv_time;
    private EditText edt_zy;
    private CtCruiseStatisticsDetail ctCruiseStatisticsDetail;

    public DeatilCruiseSummaryLayout(Context context, CtCruiseStatisticsDetail ctCruiseStatisticsDetail) {
        super(context);
        this.ctCruiseStatisticsDetail = ctCruiseStatisticsDetail;
        LayoutInflater.from(context).inflate(R.layout.item_xhzy,this);
        initView();
    }

    private void initView() {
        tv_time = findViewById(R.id.tv_time);
        edt_zy = findViewById(R.id.edt_zy);
        edt_zy.setFocusable(false);
        tv_time.setText(ctCruiseStatisticsDetail.getHour()+":"+ctCruiseStatisticsDetail.getMinute());
        edt_zy.setText(ctCruiseStatisticsDetail.getOutLine());
    }

}
