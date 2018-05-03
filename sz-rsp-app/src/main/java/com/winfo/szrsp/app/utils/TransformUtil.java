package com.winfo.szrsp.app.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @项目名: gdmsaec-app
 * @包名: com.winfo.gdmsaec.app.utils
 * @类名: TransformUtil
 * @创建者: wenjie
 * @创建时间: 2016-1-6 下午3:53:20
 * @描述: TODO 根据 两个点的经纬度 计算出这两个点之间的距离 单位 m
 * @svn版本: $Rev: 1904 $
 * @更新人: $Author: guanliusheng $
 * @更新时间: $Date: 2016-03-31 14:56:02 +0800 (Thu, 31 Mar 2016) $
 * @更新描述: TODO
 */
public class TransformUtil {



    /**
     * @param polygonzb = 区域坐标(数据格式：lat,lng;....;lat,lng)；zbd = 船舶点坐标(数据格式：lat,lng)
     * @category 判断船舶是否在一个区域内
     * @author 柴永洁
     * @since 2018-1-24
     */
    public boolean IsInside(String polygonzb, String pointzb) {
        String qyzb[] = polygonzb.split(";");//区域所有坐标
        String shipzb[] = pointzb.split(",");//船舶点坐标
        int count = qyzb.length;
        if (count < 3) {
            return false;
        }
        boolean result = false;
        double shipzblat = Double.parseDouble(shipzb[0]);//船舶坐标纬度
        double shipzblng = Double.parseDouble(shipzb[1]);//船舶坐标经度
        for (int i = 0, j = count - 1; i < count; i++) {//遍历每个坐标点
            String qyzb1 = qyzb[i];//第一个点
            String qyzb2 = qyzb[j]; //第二个点
            double p1_lat = Double.parseDouble(qyzb1.split(",")[0]); //第一个坐标点纬度
            double p1_lng = Double.parseDouble(qyzb1.split(",")[1]); //第一个坐标点经度
            double p2_lat = Double.parseDouble(qyzb2.split(",")[0]); //第二个坐标点纬度
            double p2_lng = Double.parseDouble(qyzb2.split(",")[1]); //第二个坐标点经度
            if (p1_lat < shipzblat && p2_lat >= shipzblat || p2_lat < shipzblat && p1_lat >= shipzblat)//判断是否在区域内
            {
                if (p1_lng + (shipzblat - p1_lat) / (p2_lat - p1_lat) * (p2_lng - p1_lng) < shipzblng) {
                    result = !result;
                }
            }
            j = i;
        }
        return result;
    }

    /**
     * 根据 两个点的经纬度 计算出这两个点之间的距离 单位 m
     *
     * @param lng1 第一个点的经度
     * @param lat1 第一个点的维度
     * @param lng2 第二个点的经度
     * @param lat2 第二个点的经度
     * @return 距离
     */
    public static double getDistance(double lng1, double lat1, double lng2,
                                     double lat2) {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 已知A点的经纬度 和A点到B点的距离和角度，求B点的经纬度 有误差
     *
     * @param distance 距离 单位km
     * @param lng1     经度1
     * @param lat1     维度1
     * @param angle    角度
     * @return point[0]：经度 point[1]:维度
     */
    public static double[] ConvertDistanceToLogLat(double distance,
                                                   double lng1, double lat1, double angle) {
        double lon = lng1 + (distance * Math.sin(angle * Math.PI / 180))
                / (111 * Math.cos(lat1 * Math.PI / 180));// 将距离转换成经度的计算公式
        double lat = lat1 + (distance * Math.cos(angle * Math.PI / 180)) / 111;// 将距离转换成纬度的计算公式
        double point[] = new double[2];
        point[0] = lon;
        point[1] = lat;
        return point;
    }

    /**
     * 已知AB两个点的经纬度 求出B相对于A的角度
     *
     * @param lon1 A的经度
     * @param lat1 A的纬度
     * @param lon2 B的经度
     * @param lat2 B的纬度
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
    public static String trandu2m(double d) { // gisoracle 编号
        try {
            // double dd = Convert.ToDouble(str);

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

            double doubleValue = new BigDecimal("" + M).setScale(1,
                    BigDecimal.ROUND_HALF_UP).doubleValue();

            return "" + dt + "°" + mt + "′" + doubleValue + "″";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    /**
     * @param s 传入小数形式度数
     * @return 返回度分秒形式
     */
    public static String trandu2m(String s) { // gisoracle 编号
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
            double doubleValue = new BigDecimal("" + M).setScale(1,
                    BigDecimal.ROUND_HALF_UP).doubleValue();

            return "" + dt + "°" + mt + "′" + doubleValue + "″";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String thysJW2m(String s) {


        if (s.equals("") || s == null) {

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
