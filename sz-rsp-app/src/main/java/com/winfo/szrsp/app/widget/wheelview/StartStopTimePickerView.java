package com.winfo.szrsp.app.widget.wheelview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.views.StartStopBasePickerView;
import com.winfo.szrsp.app.widget.wheelview.views.StartStopWheelTime;
import com.winfo.szrsp.app.widget.wheelview.views.WheelTime;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class StartStopTimePickerView extends StartStopBasePickerView implements View.OnClickListener {

    public enum Type {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN , YEAR_MONTH
    }// 四种选择模式，年月日时分，年月日，时分，月日时分

    StartStopWheelTime wheelTime;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnTimeSelectListener timeSelectListener;
    private Context mContext;

    public StartStopTimePickerView(Context context, Type type) {
        super(context);
        this.mContext=context;
        LayoutInflater.
                from(context).
                inflate(R.layout.wheelview_pickerview_startstoptime, contentContainer);
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
        final View timepickerview_stop = findViewById(R.id.timepicker_stop);
        wheelTime = new StartStopWheelTime(timepickerview,timepickerview_stop, type);
        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(year, month, day, hours, minute,year, month, day, hours, minute);

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
    public void setTime(Date date,Date date_stop) {
        Calendar calendar = Calendar.getInstance();
        if (date == null)
            calendar.setTimeInMillis(System.currentTimeMillis());
        else
            calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Calendar calendar2 = Calendar.getInstance();
        if (date_stop == null)
            calendar2.setTimeInMillis(System.currentTimeMillis());
        else
            calendar2.setTime(date_stop);
        int year_stop = calendar2.get(Calendar.YEAR);
        int month_stop = calendar2.get(Calendar.MONTH);
        int day_stop = calendar2.get(Calendar.DAY_OF_MONTH);
        int hours_stop = calendar2.get(Calendar.HOUR_OF_DAY);
        int minute_stop = calendar2.get(Calendar.MINUTE);
        wheelTime.setPicker(year, month, day, hours, minute,year_stop, month_stop, day_stop, hours_stop, minute_stop);

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
                try {
                    Date date = WheelTime.dateFormat.parse(wheelTime.getTime().get(0));
                    Date date_stop = WheelTime.dateFormat.parse(wheelTime.getTime().get(1));
                    if(date_stop.getTime()-date.getTime()>604800000){
                        ToastUtils.showToast(mContext, "起止时间大于7天，请重新选择");
                        return;
                    }
                    if(date_stop.getTime()<date.getTime()){
                        ToastUtils.showToast(mContext,"起止时间错误");
                        return;
                    }
                    timeSelectListener.onTimeSelect(date,date_stop);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            dismiss();
            return;
        }
    }

    public interface OnTimeSelectListener {
        public void onTimeSelect(Date date,Date date_stop);
    }

    public void setOnTimeSelectListener(OnTimeSelectListener timeSelectListener) {
        this.timeSelectListener = timeSelectListener;
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }
    public void setGravity(int center) {

        wheelTime.setGravity(center);

    }



}
