package com.winfo.szrsp.app.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.winfo.szrsp.app.R;


/**
 * 放大缩小的view
 *
 * @author 00
 */
public class ZoomControlsView extends LinearLayout {
    /**
     * 放大按钮
     */
    public static ImageButton inBtn;
    /**
     * 缩小按钮
     */
    public static ImageButton outBtn;
    /**
     * 接口变量
     */
    public OnZoomControlClickListenner onZoomControlClickListenner;


    public ZoomControlsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZoomControlsView(Context context) {
        this(context, null);
    }

    /**
     * 初始化自定义控件
     *
     * @param context
     */
    private void init(Context context) {
        /*
         * 获取到自定义控件需要的的布局
		 */
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.zoom_in_out, null);
        /*
         * 放大按钮
		 */
        inBtn = (ImageButton) linearLayout.findViewById(R.id.btn_zoom_in);
        /*
         * 缩小按钮
		 */
        outBtn = (ImageButton) linearLayout.findViewById(R.id.btn_zoom_out);
		/*
		 * 将布局转换之后的view添加到自定义控件中
		 */
        addView(linearLayout);
//		inBtn.setEnabled(false);
//		inBtn.setImageResource(R.drawable.main_icon_zoomin_dis);
        inBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onZoomControlClickListenner != null){
                    onZoomControlClickListenner.in();
                }
            }
        });

        outBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onZoomControlClickListenner != null){
                    onZoomControlClickListenner.out();
                }
            }
        });
    }

    /**
     * 接口回调
     */
    public interface OnZoomControlClickListenner {
        /**
         * 放大
         */
        void in();

        /**
         * 缩小
         */
        void out();
    }

    /**
     * 设置接口回调
     */
    public void setOnZoomControlClickListenner(OnZoomControlClickListenner onZoomControlClickListenner) {
        this.onZoomControlClickListenner = onZoomControlClickListenner;
    }

}
