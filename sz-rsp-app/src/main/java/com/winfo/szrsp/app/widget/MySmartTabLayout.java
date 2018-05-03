package com.winfo.szrsp.app.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by winfo053 on 2018/4/3.
 */

public class MySmartTabLayout extends SmartTabLayout{
    public MySmartTabLayout(Context context) {
        super(context);
    }
    public MySmartTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySmartTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected TextView createDefaultTabView(CharSequence title) {
        TextView textView = super.createDefaultTabView(title);
        //放开设置标题单行跑马灯
//        textView.setSingleLine();
//        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        textView.setHorizontallyScrolling(true);
//        textView.setMarqueeRepeatLimit(-1);
        textView.setTypeface(Typeface.DEFAULT);
        return textView;
    }
}
