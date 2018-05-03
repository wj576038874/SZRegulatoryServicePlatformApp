package com.winfo.szrsp.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @项目名: 	gdmsaec-app
 * @包名:	com.winfo.gdmsaec.app.utils
 * @类名:	DateFormatUtils
 * @创建者:	wenjie
 * @创建时间:	2016-1-20	下午5:17:03 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev: 235 $
 * @更新人:	$Author: qianmanman $
 * @更新时间:	$Date: 2017-12-04 15:18:12 +0800 (周一, 04 十二月 2017) $
 * @更新描述:	TODO
 */
public class DateFormatUtils {
	
	/**
	 * 格式化当前时间
	 * @return
	 */
	public static String getFormatDate(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		return format.format(date);
	}
	
	/**
	 * 根据当前时间 格式化 照片  语音  视频的文件名 
	 * @return
	 */
	public static String getPVAFormatDate(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
		return format.format(date);
	}
}
