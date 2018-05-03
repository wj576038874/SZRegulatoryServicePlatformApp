package com.winfo.szrsp.app.mvp.table.dzxhydgz.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wly on 2018/1/22.
 * 电子巡航月度工作统计表
 */

public class ElectronicCruiseWorkActivity extends Activity implements View.OnClickListener {

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;

    private TextView tv_date;

    private TextView tv_submit_date;

    private TimePickerView timePickerView;

    private TimePickerView timePickerView2;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electornic_cruise_work);


        initView();

        initEvent();

        initData();


    }

    private void initData() {
        table_titleBar_titleText.setText("执行表格");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        String time = format.format(new Date());
        tv_date.setText(time);

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());



        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time2 = format.format(new Date());
        tv_submit_date.setText(time2);
        timePickerView2 = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView2.setCyclic(true);
        timePickerView2.setTime(new Date());

    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        tv_date.setOnClickListener(this);
        tv_submit_date.setOnClickListener(this);
    }

    private void initView() {
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        tv_date=findViewById(R.id.tv_date);
        tv_submit_date=findViewById(R.id.tv_submit_date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.tv_date:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
                        String time = format.format(date);
                        tv_date.setText(time);
                    }
                });
                timePickerView.show();
                break;

            case R.id.tv_submit_date:
                timePickerView2.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_date.setText(time);
                    }
                });
                timePickerView2.show();
                break;

        }
    }
}
