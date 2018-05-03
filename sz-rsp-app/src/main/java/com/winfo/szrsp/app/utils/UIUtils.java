package com.winfo.szrsp.app.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.winfo.szrsp.app.application.SzRspApplication;

/**
 * @项目名: gdmsaec-app
 * @包名: com.winfo.gdmsaec.app.utils
 * @类名: UIUtils
 * @创建者: yanfeijun
 * @创建时间: 2015-10-16	下午1:25:02
 * @描述: 和ui相关的一些静态工具方法
 * @svn版本: $Rev: 1718 $
 * @更新人: $Author: guanliusheng $
 * @更新时间: $Date: 2016-03-18 17:59:46 +0800 (Fri, 18 Mar 2016) $
 * @更新描述: TODO
 */
public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return SzRspApplication.getContext();
    }

    /**
     * 得到resouce对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    // string.xml-->string-->arr

    /**
     * 得到string.xml中的一个字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }


    /**
     * 得到color.xml中的颜色值
     */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }


    /**
     * dp-->px
     *
     * @param dp
     * @return
     */
    public static int dip2Px(int dp) {
        // px/dp = density;
        float density = getResources().getDisplayMetrics().density;
        //System.out.println("density:" + density);
        int px = (int) (dp * density + .5f);
        return px;
    }

    /**
     * px-->dp
     *
     * @param
     * @return
     */
    public static int px2Dp(int px) {
        // px/dp = density;
        float density = getResources().getDisplayMetrics().density;
        //System.out.println("density:" + density);
        int dp = (int) (px / density + .5f);
        return px;
    }

    private static boolean state_flag;

    /**
     * 按钮的点击状态控制方法
     *
     * @param view       按钮
     * @param id1        正常状态的id
     * @param id2        点击状态的id
     * @param id3        点击后状态的id
     * @param statesView 按钮是否需要控制显示view，无需控制则为null
     */
    public static void clickStates(final View view, final int id1, final int id2, final int id3, final View statesView) {
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (state_flag) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        view.setBackgroundResource(id2);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.setBackgroundResource(id1);
                        if (statesView != null)
                            statesView.setVisibility(View.GONE);
                        state_flag = false;
                    }
                } else {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        view.setBackgroundResource(id2);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.setBackgroundResource(id3);
                        if (statesView != null)
                            statesView.setVisibility(View.VISIBLE);
                        state_flag = true;
                    }
                }
                return false;
            }
        });
    }

    public static float dip2Px(double d) {
        // px/dp = density;
        float density = getResources().getDisplayMetrics().density;
        //System.out.println("density:" + density);
        int px = (int) (d * density + .5f);
        return px;
    }
}
