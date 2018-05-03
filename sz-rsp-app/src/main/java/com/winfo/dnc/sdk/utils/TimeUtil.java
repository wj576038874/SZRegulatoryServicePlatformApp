package com.winfo.dnc.sdk.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间间隔的工具类
 * @author 00
 */
public class TimeUtil {
	//一分钟
	private static final int seconds_of_1minute = 60;
	//30分钟
	private static final int seconds_of_30minutes = 30 * 60;
	//1小时
	private static final int seconds_of_1hour = 60 * 60;
	//一天 
	private static final int seconds_of_1day = 24 * 60 * 60;
	//15天 =  一天* 15
	private static final int seconds_of_15days = seconds_of_1day * 15;
	//30天 = 一天*30 = 一个月
	private static final int seconds_of_30days = seconds_of_1day * 30;
	//半年   6个月  = 一个月*6
	private static final int seconds_of_6months = seconds_of_30days * 6;
	//1年
	private static final int seconds_of_1year = seconds_of_30days * 12;

	/**
	 * 判断时间 间隔
	 * @param createTime 接收消息的时间
	 * @return	时间间隔
	 */
	public static String getTimeElapse(long createTime) {
		long nowTime = new Date().getTime();
		// createTime是接收消息的时间毫秒数
		long oldTime = createTime;
		// elapsedTime是接收消息和现在的间隔时间毫秒数  再除于1000  以秒计算
		long elapsedTime = (nowTime - oldTime)/1000;
		
		//间隔时间小于1分钟
		if (elapsedTime < seconds_of_1minute) {
			return "刚刚";
		}
		//间隔时间小于30分钟
		if (elapsedTime < seconds_of_1hour) {
			return elapsedTime / seconds_of_1minute + "分钟前";
		}
//		if (elapsedTime < seconds_of_1hour) {
//			return "半小时前";
//		}
		//间隔时间小于24小时（1天）
		if (elapsedTime < seconds_of_1day) {
			//elapsedTime / seconds_of_1hour + "小时"+(elapsedTime % seconds_of_1hour)/seconds_of_1minute+"分钟前";
			return elapsedTime / seconds_of_1hour + "小时前";
		}
//		if (elapsedTime < seconds_of_15days) {
//			return elapsedTime / seconds_of_1day + "天前";
//		}
		//间隔时间小于30天
		if (elapsedTime < seconds_of_30days) {
			//elapsedTime / seconds_of_1day + "天"+(elapsedTime % seconds_of_1day)/seconds_of_1hour+"小时前";
			return elapsedTime / seconds_of_1day + "天前";
		}
//		if (elapsedTime < seconds_of_6months) {
//			return elapsedTime / seconds_of_30days + "月前";
//		}
		
		//间隔时间小于1年
		if (elapsedTime < seconds_of_1year) {
			return elapsedTime / seconds_of_30days + "月前";
		}
		//间隔时间大于1年
		if (elapsedTime >= seconds_of_1year) {
			return elapsedTime / seconds_of_1year + "年前";
		}
		return "";
	}
	
	/**
	 * 获取返回yyyy-MM-dd格式的日期
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentYMD()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(new Date());
	}
	
	/**
	 * 获取返回yyyy-MM-dd-HH:mm:ss格式的日期
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentYMDHMS()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss"); 
		return sdf.format(new Date());
	}
	
	/**
	 * 根据毫秒值返回 yyyy-MM-dd-HH:mm:ss格式的日期
	 * @param time 毫秒值
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String time2CurrentYMDHMS(long time){
		 Date dat=new Date(time);  
         GregorianCalendar gc = new GregorianCalendar();   
         gc.setTime(dat);  
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
         String sb=format.format(dat);  
         return sb;
		}
	
	public static String time2CurrentYMDHMS(String s){
		if(s==null||"".equals(s)){
			return "";
		}
		long time = Long.parseLong(s);
		Date dat=new Date(time);  
        GregorianCalendar gc = new GregorianCalendar();   
        gc.setTime(dat);  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String sb=format.format(dat);  
        return sb;
		}
	
	/**
	 * 取当前时间年月日
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date getNowDateShort() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(currentTime);
		  ParsePosition pos = new ParsePosition(8);
		  Date currentTime_2 = formatter.parse(dateString, pos);
		  return currentTime_2;
		}


	/**
	 * 将字符串数据转化为毫秒数
	 */
	@SuppressLint("SimpleDateFormat")
	public static long time2Millisecond(String time){

		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		  try {
			long millionSeconds = sdf.parse(time).getTime();
			return millionSeconds;

		  	} catch (ParseException e) {
			e.printStackTrace();
		  }
		return 0;
	}

	/**
	 * 将字符串数据转化为毫秒数
	 */
	@SuppressLint("SimpleDateFormat")
	public static long time3Millisecond(String time){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			long millionSeconds = sdf.parse(time).getTime()/1000;
			return millionSeconds;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 根据毫秒值返回 yyyy-MM-dd-HH
	 * @param time
	 * @return
	 */
	public static String time3CurrentYMDHMS(long time){
		 Date dat=new Date(time);  
        GregorianCalendar gc = new GregorianCalendar();   
        gc.setTime(dat);  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String sb=format.format(dat);  
        return sb;
		}
}
