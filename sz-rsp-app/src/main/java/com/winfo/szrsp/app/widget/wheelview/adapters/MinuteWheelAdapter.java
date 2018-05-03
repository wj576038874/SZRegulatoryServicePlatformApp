package com.winfo.szrsp.app.widget.wheelview.adapters;


/**
 * HalfHour Wheel adapter.
 */
public class MinuteWheelAdapter implements WheelAdapter {

	/** The default min value */
	public static final int DEFAULT_MAX_VALUE = 9;

	/** The default max value */
	private static final int DEFAULT_MIN_VALUE = 0;
	private static final int DEFAULT_TIMES_VALUE = 1;

	// Values
	private int minValue;
	private int maxValue;
	private int timesValue;

	/**
	 * Default constructor
	 */
	public MinuteWheelAdapter() {
		this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE,DEFAULT_TIMES_VALUE);
	}

	/**
	 * Constructor
	 * @param minValue the wheel min value
	 * @param maxValue the wheel max value
	 */
	public MinuteWheelAdapter(int minValue, int maxValue, int timesValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.timesValue = timesValue;
	}

	@Override
	public Object getItem(int index) {
		if (index >= 0 && index < getItemsCount()) {
			int value = (minValue + index)*timesValue;
			return value;
		}
		return 0;
	}

	@Override
	public int getItemsCount() {
		return (maxValue - minValue + 1)/timesValue;
	}
	
	@Override
	public int indexOf(Object o){
		return (Integer)o - minValue*timesValue;
	}
}
