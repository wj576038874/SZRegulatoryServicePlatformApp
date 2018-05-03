package com.winfo.szrsp.app.utils;

import android.util.Log;

/**
 * @项目名: gdmsaec-app
 * @包名: com.winfo.gdmsaec.app.utils
 * @类名: LogUtil
 * @创建者: wenjie
 * @创建时间: 2015-12-4	下午5:33:40
 * @描述: TODO
 * @svn版本: $Rev: 636 $
 * @更新人: $Author: wenjie $
 * @更新时间: $Date: 2015-12-04 17:53:04 +0800 (Fri, 04 Dec 2015) $
 * @更新描述: TODO
 */
public class LogUtil {
    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "SZapp";

    // 下面四个是默认tag的函数  
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数  
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
}
