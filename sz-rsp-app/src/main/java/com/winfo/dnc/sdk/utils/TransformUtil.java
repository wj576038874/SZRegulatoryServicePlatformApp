package com.winfo.dnc.sdk.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.dnc.sdk.utils
 * Author: wenjie
 * FileName: com.winfo.dnc.sdk.utils.TransformUtil.java
 * Date: 2017/12/11 18:38
 * Description: 经纬度转换工具类
 */
public class TransformUtil {

    /**
     * 根据 两个点的经纬度 计算出这两个点之间的距离 单位 m
     *
     * @param lng1 第一个点的经度
     * @param lat1 第一个点的维度
     * @param lng2 第二个点的经度
     * @param lat2 第二个点的经度
     * @return 距离
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 已知A点的经纬度 和A点到B点的距离和角度，求B点的经纬度  有误差
     *
     * @param distance 距离 单位km
     * @param lng1     经度1
     * @param lat1     维度1
     * @param angle    角度
     * @return point[0]：经度 point[1]:维度
     */
    public static double[] ConvertDistanceToLogLat(double distance, double lng1,
                                                   double lat1, double angle) {
        double lon = lng1 + (distance * Math.sin(angle * Math.PI / 180))
                / (111 * Math.cos(lat1 * Math.PI / 180));// 将距离转换成经度的计算公式
        double lat = lat1 + (distance * Math.cos(angle * Math.PI / 180)) / 111;// 将距离转换成纬度的计算公式
        double point[] = new double[2];
        point[0] = lon;
        point[1] = lat;
        return point;
    }


    private static double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 已知AB两个点的经纬度 求出B相对于A的角度
     *
     * @param lon1
     *            A的经度
     * @param lat1
     *            A的纬度
     * @param lon2
     *            B的经度
     * @param lat2
     *            B的纬度
     * @return B相对于A的角度
     */
    public static double getAngle(double lon1, double lat1, double lon2,
                                  double lat2) {
        double range = 0;
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(0);
        double c_lat = lat1;
        double c_lon = lon2;
        double b = getDistance(lon1, lat1, c_lon, c_lat);
        double c = getDistance(lon1, lat1, lon2, lat2);
        if (lat2 > lat1 && lon2 > lon1) {// 第一象限
            range = 90 - arccos(b / c);
        } else if (lat2 > lat1 && lon2 == lon1) {// 北方
            range = 0.0;
        } else if (lat2 == lat1 && lon2 > lon1) {// 东方
            range = 90.0;
        } else if (lat2 < lat1 && lon2 > lon1) {// 第二象限
            b = getDistance(lon1, lat1, c_lon, c_lat);
            c = getDistance(lon1, lat1, lon2, lat2);
            range = 90 + arccos(b / c);
        } else if (lat2 < lat1 && lon2 == lon1) {// 南方
            range = 180.0;
        } else if (lat2 < lat1 && lon2 < lon1) {// 第三象限
            range = 270 - arccos(b / c);
        } else if (lat2 == lat1 && lon2 < lon1) {// 西方
            range = 270.0;
        } else if (lat2 > lat1 && lon2 < lon1) {// 第四象限
            range = 270 + arccos(b / c);
        }
        return Double.valueOf(format.format(range));
    }

    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 根据余弦值 求出角度
     *
     * @param a
     * @return 角度
     */
    public static double arccos(double a) {
        double b = 90.0, c0 = 0.0, c1 = 180.0;
        if (a < 1 && a > -1) {
            do {
                if (Math.cos(b * Math.PI / 180) >= a) {
                    c0 = b;
                    b = (c0 + c1) / 2;
                }
                if (Math.cos(b * Math.PI / 180) <= a) {
                    c1 = b;
                    b = (c0 + c1) / 2;
                }
            } while (Math.abs(c0 - c1) > 0.00001);
        }
        return b;
    }

    /**
     * @param d 小数形式的度数
     * @return 返回度分秒形式
     */
    public static String trandu2m(double d) { //gisoracle 编号
        try {
            String str = "" + d;
            int p = str.indexOf(".");
            int dt = Integer.parseInt(str.substring(0, p));
            d = d - dt;
            double M = d * 60;
            int mt = (int) M;
            M = (M - mt) * 60;
            if (Math.abs(M - 60) < 0.001) {
                M = 0;
                mt = mt + 1;
            }
            if (mt == 60) {
                dt = dt + 1;
                mt = 0;
            }
            double doubleValue = new BigDecimal("" + M).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            return "" + dt + "°" + mt + "′" + doubleValue + "″";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    /**
     * @param s 传入小数形式度数
     * @return 返回度分秒形式
     */
    public static String trandu2m(String s) { //gisoracle 编号
        try {
            double d = Double.parseDouble(s);
            String str = "" + d;
            int p = str.indexOf(".");
            int dt = Integer.parseInt(str.substring(0, p));
            d = d - dt;
            double M = d * 60;
            int mt = (int) M;
            M = (M - mt) * 60;
            if (Math.abs(M - 60) < 0.001) {
                M = 0;
                mt = mt + 1;
            }
            if (mt == 60) {
                dt = dt + 1;
                mt = 0;
            }
            double doubleValue = new BigDecimal("" + M).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            return "" + dt + "°" + mt + "′" + doubleValue + "″";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public static String thysJW2m(String s) {
        if (s.equals("")) {
            return "";
        }
        if (!s.contains(",")) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        String replace = s.replaceAll(";", ",");
        String[] split = replace.split(",");

        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {//偶数
                String trandu2m1 = trandu2m(split[i]);
                sb.append(trandu2m1).append(" N");
            } else {//奇数
                String trandu2m2 = trandu2m(split[1]);
                sb.append(trandu2m2).append(" E");
            }
        }
        return sb.toString();
    }

}
