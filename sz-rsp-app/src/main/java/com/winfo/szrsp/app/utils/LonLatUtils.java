package com.winfo.szrsp.app.utils;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

/**
 * Created by winfo053 on 2018/4/11.
 */

public class LonLatUtils {
    /**
     * 功能：         度-->度分秒
     * @param d 传入待转化格式的经度或者纬度
     */
    public static String  DDtoDMS(Double d){
        double gpsInfo = Math.abs(d);
        String dms = Location.convert(gpsInfo, Location.FORMAT_SECONDS);
        String[] splits = dms.split(":");
        String[] secnds = (splits[2]).split("\\.");
        String seconds;
        if (secnds.length == 0) {
            seconds = splits[2];
        } else {
            seconds = secnds[0];
        }
        return splits[0]+"/"+splits[1]+"/"+seconds;

    }


    public static double  DMStoDD(String stringDMS){
        String[] DMS=stringDMS.split("/");
        return parseFloat(DMS[0]) + ((parseFloat(DMS[1]) / 60) + (parseFloat(DMS[2]) / 3600));



//        Float result = null;
//        String[] DMS = stringDMS.split("/", 3);
//
//        String[] stringD = DMS[0].split("/", 2);
//        Double D0 = new Double(stringD[0]);
//        Double D1 = new Double(stringD[1]);
//
//        Double FloatD;
//        if(D1==0){
//            FloatD= D0;
//        }else {
//           FloatD = D0/D1;
//
//        }
//
//        String[] stringM = DMS[1].split("/", 2);
//        Double M0 = new Double(stringM[0]);
//        Double M1 = new Double(stringM[1]);
//        Double FloatM = M0/M1;
//
//        String[] stringS = DMS[2].split("/", 2);
//        Double S0 = new Double(stringS[0]);
//        Double S1 = new Double(stringS[1]);
//        Double FloatS = S0/S1;
//
//        result = new Float(FloatD + (FloatM/60) + (FloatS/3600));
//
//        return (double) result;
    }
}
