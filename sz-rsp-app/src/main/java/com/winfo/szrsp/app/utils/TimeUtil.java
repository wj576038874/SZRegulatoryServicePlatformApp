package com.winfo.szrsp.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间间隔的工具类
 *
 * @author 00
 */
public class TimeUtil {


    /**
     * 获取当前日期
     *
     * @return 返回yyyy-MM-dd格式的日期
     *
     */
    public static String getFormatYMD() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return sdf.format(new Date());
    }

    /**
     * 获取 当前日期和时间
     *
     * @return 返回yyyy-MM-dd-HH:mm:ss格式的日期
     */
    public static String getFormatYMDHMS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CANADA);
        return sdf.format(new Date());
    }

    /**
     * 根据毫秒值返回 yyyy-MM-dd-HH:mm:ss格式的日期和时间
     *
     * @param time 毫秒值
     * @return 格式化日期时间字符串
     */
    public static String millisecond2FormatYMDHMS(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        return format.format(date.getTime());
    }

    /**
     * 根据毫秒值返回 yyyy-MM-dd格式的日期
     *
     * @param time 毫秒值
     * @return yyyy-MM-dd格式的日期
     */
    public static String time2FormatYMD(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return format.format(date.getTime());
    }


    /**
     * 将时间字符串yyyy/MM/dd HH:mm:ss数据 转化为毫秒数
     * @param time 日期和时间字符串
     * @return 毫秒数
     */
    public static long time2Millisecond2(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CANADA);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将字符串yyyy-MM-dd HH:mm:ss数据 转化为毫秒数
     *
     * @param time 时间
     * @return 毫秒数
     */
    public static long time2Millisecond(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
