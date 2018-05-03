package com.winfo.szrsp.app.widget.wheelview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.widget.wheelview.views.MinuteBasePickerView;
import com.winfo.szrsp.app.widget.wheelview.views.MinuteWheelTime;

import java.util.Calendar;
import java.util.Date;

public class MinuteTimePickerView extends MinuteBasePickerView implements View.OnClickListener {
    public enum Type {
        ALL, MORING, AFTERNOON
    }// 三种选择模式，全天 上午 下午

    MinuteWheelTime wheelTime;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnTimeSelectListener timeSelectListener;

    private String pinjie="-";

    public MinuteTimePickerView(Context context, Type type) {
        super(context);

        LayoutInflater.
                from(context).
                inflate(R.layout.wheelview_pickerview_minutetime, contentContainer);
        // -----确定和取消按钮
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        // ----时间转轮
        final View timepickerview = findViewById(R.id.timepicker);
        wheelTime = new MinuteWheelTime(timepickerview,type);
        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(hours, minute, hours, minute);

    }

    /**
     * 设置可以选择的时间范围
     *
     * @param startYear
     * @param endYear
     */
    public void setRange(int startYear, int endYear) {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);
    }

    /**
     * 设置选中时间
     * @param date
     */
    public void setTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null)
            calendar.setTimeInMillis(System.currentTimeMillis());
        else
            calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(hours, minute, hours, minute);
    }


    public void setPicker(String time) {

        if(time.length()>1&&time.contains(pinjie)){

           String[] startstop= time.split(pinjie);

           String[] start= startstop[0].split(":");
           String[] stop= startstop[1].split(":");
            wheelTime.setPicker(Integer.parseInt(start[0]),Integer.parseInt(start[1]),Integer.parseInt(stop[0]),Integer.parseInt(stop[1]));
        }else {
            Calendar calendar = Calendar.getInstance();
            Date date=new Date();
            calendar.setTime(date);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            wheelTime.setPicker(hours, minute, hours, minute);
        }

        //wheelTime.setPicker(h1, m1, h, m);
    }

//    /**
//     * 指定选中的时间，显示选择器
//     *
//     * @param date
//     */
//    public void show(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        if (date == null)
//            calendar.setTimeInMillis(System.currentTimeMillis());
//        else
//            calendar.setTime(date);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        wheelTime.setPicker(year, month, day, hours, minute);
//        show();
//    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wheelTime.setCyclic(cyclic);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (timeSelectListener != null) {
                timeSelectListener.onTimeSelect(wheelTime.getStartTime()+pinjie+wheelTime.getStopTime());
            }
            dismiss();
            return;
        }
    }

    public interface OnTimeSelectListener {
        public void onTimeSelect(String time);
    }

    public void setOnTimeSelectListener(OnTimeSelectListener timeSelectListener) {
        this.timeSelectListener = timeSelectListener;
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setType(Type tpye){
        wheelTime.setType(tpye);
    }
}
