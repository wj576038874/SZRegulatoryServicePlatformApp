package com.winfo.szrsp.app.adapter.plotting;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.SZMSAUtils;
import java.util.List;


public class PlottingAdapter extends BaseQuickAdapter<Plotting,BaseViewHolder> {
    //private String drawingType="";//1 点 ,  2 线 ,  3 面 , "" 全部
    public PlottingAdapter(@Nullable List<Plotting> data) {
        super(R.layout.item_plotting, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Plotting item) {

        helper.setText(R.id.plotting_name,item.getBhname());

        switch (item.getDrawingType()){
            case "1":
                helper.setText(R.id.plotting_type,"点");

                break;
            case "2":
                helper.setText(R.id.plotting_type,"线");

                break;
            case "3":
                helper.setText(R.id.plotting_type,"面");
                break;
//            case "4":
//                helper.setText(R.id.plotting_type,"面");
//                break;
//            case "5":
//                helper.setText(R.id.plotting_type,"面");
//                break;
        }

        if(item.getDrawingType().equals("1")){
            helper.setVisible(R.id.plotting_iv_edit, true);
        }else {
            helper.setVisible(R.id.plotting_iv_edit, false);
        }
        helper.setText(R.id.plotting_org, SZMSAUtils.getOrgName(item.getOrgId()));
        helper.setText(R.id.plotting_creater, item.getUserUuid());
        helper.setImageResource(R.id.plotting_iv_display,item.isShowOnDNCView()?R.mipmap.icon_display:R.mipmap.icon_hide);
        helper.addOnClickListener(R.id.plotting_iv_display).addOnClickListener(R.id.plotting_iv_query)
                .addOnClickListener(R.id.plotting_iv_edit).addOnClickListener(R.id.plotting_iv_delete);


//        switch (drawingType){
//            case "1":
//                if(item.getDrawingType().equals("1")){
//                    RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                    params.height= DimensUtils.dp2px(mContext,35);//设置当前控件布局的高度
//                    helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                }else {
//                    RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                    params.height=0;//设置当前控件布局的高度
//                    helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                }
//                break;
//            case "2":
//                if(item.getDrawingType().equals("2")){
//                    RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                    params.height= DimensUtils.dp2px(mContext,35);//设置当前控件布局的高度
//                    helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                }else {
//                    RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                    params.height=0;//设置当前控件布局的高度
//                    helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                }
//                break;
//            case "3":
//                if(item.getDrawingType().equals("3")){
//                    RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                    params.height= DimensUtils.dp2px(mContext,35);//设置当前控件布局的高度
//                    helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                }else {
//                    RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                    params.height=0;//设置当前控件布局的高度
//                    helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                }
//                break;
//            case "":
//                RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
//                params.height= DimensUtils.dp2px(mContext,35);//设置当前控件布局的高度
//                helper.itemView.setLayoutParams(params);//将设置好的布局参数应用到控件中
//                break;
//        }


    }
//    public void setShowDrawingType(String type){
//        drawingType=type;
//        notifyDataSetChanged();
//    }

}
