package com.winfo.szrsp.app.mvp.table.xhtj.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsDetail;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj
 * @Filename: CruiseSummaryLayout
 * @Author: lsj
 * @Date: 2018/1/22  16:13
 * @Description:
 * @Version:巡航摘要
 */
public class CruiseSummaryLayout extends LinearLayout{
    private TextView tv_time;
    private EditText edt_zy;
    private TimePickerView timePickerView;
    private Context mContext;

    public CruiseSummaryLayout(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_xhzy,this);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.CHINA);
                        String time = format.format(date);
                        tv_time.setText(time);
                    }
                });
                timePickerView.show();
            }
        });
    }

    private void initView() {
        tv_time = findViewById(R.id.tv_time);
        edt_zy = findViewById(R.id.edt_zy);
        timePickerView = new TimePickerView(mContext, TimePickerView.Type.HOURS_MINS);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
    }

    public CtCruiseStatisticsDetail getData(){
        if (edt_zy.getText().toString().equals("") || tv_time.getText().toString().equals("")){
            ToastUtils.showToast(SzRspApplication.getContext(),"请将时间和巡航纪要填写完整！");
            return null;
        }else {
            CtCruiseStatisticsDetail cruiseStatisticsDetail = new CtCruiseStatisticsDetail();
            String str = tv_time.getText().toString();
            cruiseStatisticsDetail.setHour(str.substring(0, str.indexOf(":")));
            cruiseStatisticsDetail.setMinute(str.substring(str.indexOf(":")+1));
            cruiseStatisticsDetail.setOutLine(edt_zy.getText().toString());
            return cruiseStatisticsDetail;
        }
    }
}
