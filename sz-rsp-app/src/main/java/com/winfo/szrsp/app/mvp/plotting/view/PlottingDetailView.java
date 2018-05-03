package com.winfo.szrsp.app.mvp.plotting.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.utils.DialogUtils;

/**
 * Created by winfo053 on 2018/4/11.
 */

public class PlottingDetailView {
    private Context mContext;
    private TextView plotting_detail_dissmiss,plotting_name,
            plotting_drawtype,plotting_latlon,plotting_color,plotting_style,plotting_creater,plotting_mark;
    private View line_color,line_style;
    private LinearLayout  ll_color,ll_style;
    //标绘dialog
    private Dialog plottingDetailDialog;
    private Plotting plotting;
    @SuppressLint("CutPasteId")
    public PlottingDetailView(Context context , Plotting plotting) {
        this.mContext = context;
        this. plotting=plotting;
        // 初始化列表的视图
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_plotting_detail, null);
        initView(view);
        initData();
        initEvent();

    }
    private void initView(View view) {

        plottingDetailDialog = new AlertDialog.Builder(mContext).create();
        plottingDetailDialog.setCancelable(true);//
        plottingDetailDialog.setCanceledOnTouchOutside(true);//
        plottingDetailDialog.show();
        plottingDetailDialog.setContentView(view);
        Window window = plottingDetailDialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
//        windowparams.width =  width-DimensUtils.dp2px(mContext,40);
        window.setGravity( Gravity.CENTER);
//        window.setDimAmount(0);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        plotting_detail_dissmiss=view.findViewById(R.id.plotting_detail_dissmiss);
        plotting_name=view.findViewById(R.id.plotting_name);
        plotting_drawtype=view.findViewById(R.id.plotting_drawtype);
        plotting_latlon=view.findViewById(R.id.plotting_latlon);
        plotting_color=view.findViewById(R.id.plotting_color);
        plotting_style=view.findViewById(R.id.plotting_style);
        plotting_creater=view.findViewById(R.id.plotting_creater);
        plotting_mark=view.findViewById(R.id.plotting_mark);
        line_color=view.findViewById(R.id.line_color);
        ll_color=view.findViewById(R.id.ll_color);

        line_style=view.findViewById(R.id.line_style);
        ll_style=view.findViewById(R.id.ll_style);
    }


    private void initData() {
        plotting_name.setText(plotting.getBhname());
        if(plotting.getDrawingType().equals("1")){
            plotting_drawtype.setText("点");
            line_color.setVisibility(View.GONE);
            ll_color.setVisibility(View.GONE);
            line_style.setVisibility(View.GONE);
            ll_style.setVisibility(View.GONE);
        }else if(plotting.getDrawingType().equals("2")){
            plotting_drawtype.setText("线");
            plotting_color.setBackgroundColor(Color.parseColor(plotting.getColor()));
        }else {
            plotting_drawtype.setText("面");
            plotting_color.setBackgroundColor(Color.parseColor(plotting.getColor()));
        }
        plotting_latlon.setText(plotting.getLatLon());



        if(plotting.getStyle().equals("2")){
            plotting_style.setText("虚线线");
        }else {
            plotting_style.setText("实线");
        }

        plotting_creater.setText(plotting.getUserUuid());
        plotting_mark.setText(plotting.getRemarks());


    }
    public void dismiss() {
        plottingDetailDialog.dismiss();
    }

    /**
     * 显示标绘的列表数据
     */
    public void showAddPlotting() {
        plottingDetailDialog.show();
    }
    private void initEvent() {
        plotting_detail_dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plottingDetailDialog.dismiss();
            }
        });
    }


}
