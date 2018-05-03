package com.winfo.szrsp.app.widget.wheelview.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;
import com.winfo.szrsp.app.widget.wheelview.adapters.MinuteWheelAdapter;
import com.winfo.szrsp.app.widget.wheelview.adapters.NumericWheelAdapter;
import com.winfo.szrsp.app.widget.wheelview.lib.MinuteWheelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class MinuteWheelTime {
	@SuppressLint("SimpleDateFormat")
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private View view;

	private MinuteWheelView wv_hours_start;
	private MinuteWheelView wv_mins_start;
	private MinuteWheelView wv_hours_stop;
	private MinuteWheelView wv_mins_stop;
	private MinuteTimePickerView.Type type;
	public static final int DEFULT_START_YEAR = 1990;
	public static final int DEFULT_END_YEAR = 2100;
	private int startYear = DEFULT_START_YEAR;
	private int endYear = DEFULT_END_YEAR;



	public MinuteWheelTime(View view) {
		super();
		this.view = view;
		type = MinuteTimePickerView.Type.ALL;
		setView(view);
	}
	public MinuteWheelTime(View view, MinuteTimePickerView.Type type) {
		super();
		this.view = view;
		this.type = type;
		setView(view);
	}
//	public void setPicker(int year ,int month,int day){
//		this.setPicker(year, month, day, 0, 0);
//	}

	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void setPicker(int h1 ,int m1 ,int h,int m) {
		Context context = view.getContext();

		switch(type){
			case ALL:
				// 开始小时
				wv_hours_start = (MinuteWheelView) view.findViewById(R.id.strat_hour);
				wv_hours_start.setAdapter(new NumericWheelAdapter(0, 23));
				wv_hours_start.setLabel(":");// 添加文字
				wv_hours_start.setCurrentItem(h1);// 初始化时显示的数据

				// 开始分
				wv_mins_start = (MinuteWheelView)view.findViewById(R.id.start_min);
				wv_mins_start.setAdapter(new MinuteWheelAdapter(0, 59,5));
				wv_mins_start.setCurrentItem(m1/5);
				// 结束小时
				wv_hours_stop = (MinuteWheelView) view.findViewById(R.id.stop_hour);
				wv_hours_stop.setAdapter(new NumericWheelAdapter(0, 23));
				wv_hours_stop.setLabel(":");// 添加文字
				wv_hours_stop.setCurrentItem(h);// 初始化时显示的数据

				// 结束分
				wv_mins_stop = (MinuteWheelView)view.findViewById(R.id.stop_min);
				wv_mins_stop.setAdapter(new MinuteWheelAdapter(0, 59,5));
				wv_mins_stop.setCurrentItem(m/5);
				break;
			case MORING:
				// 开始小时
				wv_hours_start = (MinuteWheelView) view.findViewById(R.id.strat_hour);
				wv_hours_start.setAdapter(new NumericWheelAdapter(0, 11));
				wv_hours_start.setLabel(":");// 添加文字
				wv_hours_start.setCurrentItem(h1);// 初始化时显示的数据

				// 开始分
				wv_mins_start = (MinuteWheelView)view.findViewById(R.id.start_min);
				wv_mins_start.setAdapter(new MinuteWheelAdapter(0, 59,5));
				wv_mins_start.setCurrentItem(m1/5);
				// 结束小时
				wv_hours_stop = (MinuteWheelView) view.findViewById(R.id.stop_hour);
				wv_hours_stop.setAdapter(new NumericWheelAdapter(0, 11));
				wv_hours_stop.setLabel(":");// 添加文字
				wv_hours_stop.setCurrentItem(h);// 初始化时显示的数据

				// 结束分
				wv_mins_stop = (MinuteWheelView)view.findViewById(R.id.stop_min);
				wv_mins_stop.setAdapter(new MinuteWheelAdapter(0, 59,5));
				wv_mins_stop.setCurrentItem(m/5);
				break;
			case AFTERNOON:
				// 开始小时
				wv_hours_start = (MinuteWheelView) view.findViewById(R.id.strat_hour);
				wv_hours_start.setAdapter(new NumericWheelAdapter(12, 23));
				wv_hours_start.setLabel(":");// 添加文字
				wv_hours_start.setCurrentItem(h1);// 初始化时显示的数据

				// 开始分
				wv_mins_start = (MinuteWheelView)view.findViewById(R.id.start_min);
				wv_mins_start.setAdapter(new MinuteWheelAdapter(0, 59,5));
				wv_mins_start.setCurrentItem(m1/5);
				// 结束小时
				wv_hours_stop = (MinuteWheelView) view.findViewById(R.id.stop_hour);
				wv_hours_stop.setAdapter(new NumericWheelAdapter(12, 23));
				wv_hours_stop.setLabel(":");// 添加文字
				wv_hours_stop.setCurrentItem(h);// 初始化时显示的数据

				// 结束分
				wv_mins_stop = (MinuteWheelView)view.findViewById(R.id.stop_min);
				wv_mins_stop.setAdapter(new MinuteWheelAdapter(0, 59,5));
				wv_mins_stop.setCurrentItem(m/5);
				break;
		}




		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 4;
		textSize = textSize * 4;
		wv_mins_start.setTextSize(textSize);
		wv_mins_start.setTextSize(textSize);
		wv_mins_start.setTextSize(textSize);
		wv_mins_start.setTextSize(textSize);


	}

	/**
	 * 设置是否循环滚动
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic){

		wv_hours_start.setCyclic(cyclic);
		wv_mins_start.setCyclic(cyclic);
		wv_hours_stop.setCyclic(cyclic);
		wv_mins_stop.setCyclic(cyclic);
	}

	public String getStartTime() {
		StringBuffer sb = new StringBuffer();
		int h=wv_hours_start.getCurrentItem();
		switch (type){
			case ALL:
				break;
			case MORING:
				break;
			case AFTERNOON:
				h+=12;
				break;
		}

//		int h=wv_hours_start.getCurrentItem();
		if(h<10){
			sb.append(0).append(h).append(":");
		}else{
			sb.append(h).append(":");
		}
		int m=wv_mins_start.getCurrentItem();
		if(m<10){
			sb.append(0).append(m);
		}else {
			sb.append(m);
		}

		return sb.toString();
	}
	public String getStopTime() {
		StringBuffer sb = new StringBuffer();
		int h=wv_hours_stop.getCurrentItem();
		switch (type){
			case ALL:
				break;
			case MORING:
				break;
			case AFTERNOON:
				h+=12;
				break;
		}

//		int h=wv_hours_stop.getCurrentItem();
		if(h<10){
			sb.append(0).append(h).append(":");
		}else{
			sb.append(h).append(":");
		}
		int m=wv_mins_stop.getCurrentItem();
		if(m<10){
			sb.append(0).append(m);
		}else {
			sb.append(m);
		}

		return sb.toString();
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public void setType(MinuteTimePickerView.Type type){
		this.type = type;
	}
}
