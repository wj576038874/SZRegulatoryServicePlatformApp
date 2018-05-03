package com.winfo.szrsp.app.widget.wheelview.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.widget.wheelview.StartStopTimePickerView;
import com.winfo.szrsp.app.widget.wheelview.adapters.NumericWheelAdapter;
import com.winfo.szrsp.app.widget.wheelview.lib.StartStopWheelView;
import com.winfo.szrsp.app.widget.wheelview.listenner.OnItemSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StartStopWheelTime {
	@SuppressLint("SimpleDateFormat")
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private View view;
	private View view_stop;
	private StartStopWheelView wv_year;
	private StartStopWheelView wv_month;
	private StartStopWheelView wv_day;
	private StartStopWheelView wv_hours;
	private StartStopWheelView wv_mins;


	private StartStopWheelView wv_year_stop;
	private StartStopWheelView wv_month_stop;
	private StartStopWheelView wv_day_stop;
	private StartStopWheelView wv_hours_stop;
	private StartStopWheelView wv_mins_stop;

	private StartStopTimePickerView.Type type;
	public static final int DEFULT_START_YEAR = 1990;
	public static final int DEFULT_END_YEAR = 2100;
	private int startYear = DEFULT_START_YEAR;
	private int endYear = DEFULT_END_YEAR;



	public StartStopWheelTime(View view,View view_stop) {
		super();
		this.view = view;
		this.view_stop = view_stop;
		type = StartStopTimePickerView.Type.ALL;
		setView(view,view_stop);
	}
	public StartStopWheelTime(View view,View view_stop, StartStopTimePickerView.Type type) {
		super();
		this.view = view;
		this.view_stop = view_stop;
		this.type = type;
		setView(view,view_stop);
	}
	public void setPicker(int year ,int month,int day,int year_stop ,int month_stop,int day_stop){
		this.setPicker(year, month, day, 0, 0,year_stop, month_stop, day_stop, 0, 0);
	}
	
	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void setPicker(int year ,int month ,int day,int h,int m,int year_stop ,int month_stop ,int day_stop,int h_stop,int m_stop) {
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		Context context = view.getContext();
		// 年
		wv_year = (StartStopWheelView) view.findViewById(R.id.year);
//		wv_year.setGravity(Gravity.LEFT);
		wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据
		wv_year.setLabel(context.getString(R.string.pickerview_year));// 添加文字
		wv_year.setCurrentItem(year - startYear);// 初始化时显示的数据

		// 月
		wv_month = (StartStopWheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setLabel(context.getString(R.string.pickerview_month));
		wv_month.setCurrentItem(month);

		// 日
		wv_day = (StartStopWheelView) view.findViewById(R.id.day);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel(context.getString(R.string.pickerview_day));
		wv_day.setCurrentItem(day - 1);


        wv_hours = (StartStopWheelView)view.findViewById(R.id.hour);
		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setLabel(context.getString(R.string.pickerview_hours));// 添加文字
		wv_hours.setCurrentItem(h);

		wv_mins = (StartStopWheelView)view.findViewById(R.id.min);
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins.setLabel(context.getString(R.string.pickerview_minutes));// 添加文字
		wv_mins.setCurrentItem(m);

		// 添加"年"监听
		OnItemSelectedListener wheelListener_year = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				int year_num = index + startYear;
				// 判断大小月及是否闰年,用来确定"日"的数据
				int maxItem = 30;
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					maxItem = 31;
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					maxItem = 30;
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0){
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						maxItem = 29;
					}
					else{
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						maxItem = 28;
					}
				}
				if (wv_day.getCurrentItem() > maxItem - 1){
					wv_day.setCurrentItem(maxItem - 1);
				}
			}
		};
		// 添加"月"监听
		OnItemSelectedListener wheelListener_month = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				int month_num = index + 1;
				int maxItem = 30;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					maxItem = 31;
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					maxItem = 30;
				} else {
					if (((wv_year.getCurrentItem() + startYear) % 4 == 0 && (wv_year
							.getCurrentItem() + startYear) % 100 != 0)
							|| (wv_year.getCurrentItem() + startYear) % 400 == 0){
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						maxItem = 29;
					}
					else{
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						maxItem = 28;
					}
				}
				if (wv_day.getCurrentItem() > maxItem - 1){
					wv_day.setCurrentItem(maxItem - 1);
				}
			}
		};
		wv_year.setOnItemSelectedListener(wheelListener_year);
		wv_month.setOnItemSelectedListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)


		int textSize = 12;
		switch(type){
		case ALL:
//			textSize = textSize * 4;
			break;
		case YEAR_MONTH_DAY:
//			textSize = textSize * 4;
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
			break;
		case HOURS_MINS:
//			textSize = textSize * 4;
			wv_year.setVisibility(View.GONE);
			wv_month.setVisibility(View.GONE);
			wv_day.setVisibility(View.GONE);
			break;
		case MONTH_DAY_HOUR_MIN:
//			textSize = textSize * 4;
			wv_year.setVisibility(View.GONE);
			break;
        case YEAR_MONTH:
//            textSize = textSize * 4;
            wv_day.setVisibility(View.GONE);
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
		}
		wv_day.setTextSize(textSize);
		wv_month.setTextSize(textSize);
		wv_year.setTextSize(textSize);
		wv_hours.setTextSize(textSize);
		wv_mins.setTextSize(textSize);
//TODO


		// 年
		wv_year_stop = (StartStopWheelView) view_stop.findViewById(R.id.year_stop);
//		wv_year_stop.setGravity(Gravity.LEFT);
		wv_year_stop.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据
		wv_year_stop.setLabel(context.getString(R.string.pickerview_year));// 添加文字
		wv_year_stop.setCurrentItem(year_stop - startYear);// 初始化时显示的数据

		// 月
		wv_month_stop = (StartStopWheelView) view_stop.findViewById(R.id.month_stop);
		wv_month_stop.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month_stop.setLabel(context.getString(R.string.pickerview_month));
		wv_month_stop.setCurrentItem(month_stop);

		// 日
		wv_day_stop = (StartStopWheelView) view_stop.findViewById(R.id.day_stop);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month_stop + 1))) {
			wv_day_stop.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month_stop + 1))) {
			wv_day_stop.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day_stop.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day_stop.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day_stop.setLabel(context.getString(R.string.pickerview_day));
		wv_day_stop.setCurrentItem(day_stop - 1);


		wv_hours_stop = (StartStopWheelView)view_stop.findViewById(R.id.hour_stop);
		wv_hours_stop.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours_stop.setLabel(context.getString(R.string.pickerview_hours));// 添加文字
		wv_hours_stop.setCurrentItem(h_stop);

		wv_mins_stop = (StartStopWheelView)view_stop.findViewById(R.id.min_stop);
		wv_mins_stop.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins_stop.setLabel(context.getString(R.string.pickerview_minutes));// 添加文字
		wv_mins_stop.setCurrentItem(m_stop);

		// 添加"年"监听
		OnItemSelectedListener wheelListener_year_stop = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				int year_num = index + startYear;
				// 判断大小月及是否闰年,用来确定"日"的数据
				int maxItem = 30;
				if (list_big
						.contains(String.valueOf(wv_month_stop.getCurrentItem() + 1))) {
					wv_day_stop.setAdapter(new NumericWheelAdapter(1, 31));
					maxItem = 31;
				} else if (list_little.contains(String.valueOf(wv_month_stop
						.getCurrentItem() + 1))) {
					wv_day_stop.setAdapter(new NumericWheelAdapter(1, 30));
					maxItem = 30;
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0){
						wv_day_stop.setAdapter(new NumericWheelAdapter(1, 29));
						maxItem = 29;
					}
					else{
						wv_day_stop.setAdapter(new NumericWheelAdapter(1, 28));
						maxItem = 28;
					}
				}
				if (wv_day_stop.getCurrentItem() > maxItem - 1){
					wv_day_stop.setCurrentItem(maxItem - 1);
				}
			}
		};
		// 添加"月"监听
		OnItemSelectedListener wheelListener_month_stop = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				int month_num = index + 1;
				int maxItem = 30;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day_stop.setAdapter(new NumericWheelAdapter(1, 31));
					maxItem = 31;
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day_stop.setAdapter(new NumericWheelAdapter(1, 30));
					maxItem = 30;
				} else {
					if (((wv_year_stop.getCurrentItem() + startYear) % 4 == 0 && (wv_year
							.getCurrentItem() + startYear) % 100 != 0)
							|| (wv_year_stop.getCurrentItem() + startYear) % 400 == 0){
						wv_day_stop.setAdapter(new NumericWheelAdapter(1, 29));
						maxItem = 29;
					}
					else{
						wv_day_stop.setAdapter(new NumericWheelAdapter(1, 28));
						maxItem = 28;
					}
				}
				if (wv_day_stop.getCurrentItem() > maxItem - 1){
					wv_day_stop.setCurrentItem(maxItem - 1);
				}
			}
		};
		wv_year_stop.setOnItemSelectedListener(wheelListener_year_stop);
		wv_month_stop.setOnItemSelectedListener(wheelListener_month_stop);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		switch(type){
			case ALL:
				break;
			case YEAR_MONTH_DAY:
				wv_hours.setVisibility(View.GONE);
				wv_mins.setVisibility(View.GONE);

				wv_hours_stop.setVisibility(View.GONE);
				wv_mins_stop.setVisibility(View.GONE);
				break;
			case HOURS_MINS:
				wv_year.setVisibility(View.GONE);
				wv_month.setVisibility(View.GONE);
				wv_day.setVisibility(View.GONE);

				wv_year_stop.setVisibility(View.GONE);
				wv_month_stop.setVisibility(View.GONE);
				wv_day_stop.setVisibility(View.GONE);
				break;
			case MONTH_DAY_HOUR_MIN:
				wv_year.setVisibility(View.GONE);
				wv_year_stop.setVisibility(View.GONE);
				break;
			case YEAR_MONTH:

				wv_day.setVisibility(View.GONE);
				wv_hours.setVisibility(View.GONE);
				wv_mins.setVisibility(View.GONE);

				wv_day_stop.setVisibility(View.GONE);
				wv_hours_stop.setVisibility(View.GONE);
				wv_mins_stop.setVisibility(View.GONE);
		}
		wv_day_stop.setTextSize(textSize);
		wv_month_stop.setTextSize(textSize);
		wv_year_stop.setTextSize(textSize);
		wv_hours_stop.setTextSize(textSize);
		wv_mins_stop.setTextSize(textSize);


	}

	/**
	 * 设置是否循环滚动
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic){
		wv_year_stop.setCyclic(cyclic);
		wv_month_stop.setCyclic(cyclic);
		wv_day_stop.setCyclic(cyclic);
		wv_hours_stop.setCyclic(cyclic);
		wv_mins_stop.setCyclic(cyclic);

		wv_year.setCyclic(cyclic);
		wv_month.setCyclic(cyclic);
		wv_day.setCyclic(cyclic);
		wv_hours.setCyclic(cyclic);
		wv_mins.setCyclic(cyclic);
	}
	
	public List<String> getTime() {
		StringBuffer sb = new StringBuffer();
		sb.append((wv_year.getCurrentItem() + startYear)).append("-")
			.append((wv_month.getCurrentItem() + 1)).append("-")
			.append((wv_day.getCurrentItem() + 1)).append(" ")
			.append(wv_hours.getCurrentItem()).append(":")
			.append(wv_mins.getCurrentItem());

		StringBuffer sb2 = new StringBuffer();
		sb2.append((wv_year_stop.getCurrentItem() + startYear)).append("-")
				.append((wv_month_stop.getCurrentItem() + 1)).append("-")
				.append((wv_day_stop.getCurrentItem() + 1)).append(" ")
				.append(wv_hours_stop.getCurrentItem()).append(":")
				.append(wv_mins_stop.getCurrentItem());

		List<String> list=new ArrayList<>();
		list.add(sb.toString());
		list.add(sb2.toString());
		return list;
	}

	public List<View> getView() {
		List<View> list=new ArrayList<>();
		list.add(view);
		list.add(view_stop);
		return list;
	}

	public void setView(View view,View view_stop) {
		this.view = view;
		this.view_stop = view_stop;
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

	public void setGravity(int center) {
		wv_year.setGravity(center);
		wv_month.setGravity(center);
		wv_day.setGravity(center);
		wv_hours.setGravity(center);
		wv_mins.setGravity(center);

		wv_year_stop.setGravity(center);
		wv_month_stop.setGravity(center);
		wv_day_stop.setGravity(center);
		wv_hours_stop.setGravity(center);
		wv_mins_stop.setGravity(center);
	}
}
