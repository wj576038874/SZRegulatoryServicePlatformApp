package com.winfo.szrsp.app.adapter.nearby;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.utils.PreferenceUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 附近的通航要素的适配器
 *
 * @author 00
 */
public class NearbyThysAdapter extends CommonAdapter<NavigableElementsData> {


    public NearbyThysAdapter(Context context, List<NavigableElementsData> datas, int resource) {
        super(context, datas, resource);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        final NavigableElementsData elementsModel = datas.get(position);
        TextView textView = holder.getView(R.id.nearby_list_item_name_thy);
        TextView tv_jl = holder.getView(R.id.near_juli_thys);
        TextView tv_lx = holder.getView(R.id.near_thys_lx);
        TextView tv_jg = holder.getView(R.id.near_thys_jg);
        TextView tv_click = holder.getView(R.id.near_thys_click);
        tv_click.setVisibility(View.GONE);
        textView.setText((position + 1) + "." + elementsModel.getNameCn());
        double jl = elementsModel.getDistance() / 1000;
        DecimalFormat df = new DecimalFormat("0.00");
        String a = df.format(jl);
        tv_jl.setText(a + "千米");
        if (elementsModel.getTypeId().equals("NE0001000004")) {
            tv_lx.setText("CCTV");
        } else if (elementsModel.getTypeId().equals("NE0001000005")) {
            tv_lx.setText("DSC基站");
        } else if (elementsModel.getTypeId().equals("NE0001000006")) {
            tv_lx.setText("VHF基站");
        } else if (elementsModel.getTypeId().equals("NE0001000007")) {
            tv_lx.setText("雷达站");
        } else if (elementsModel.getTypeId().equals("NE0001000008")) {
            tv_lx.setText("VTS中心");
        } else if (elementsModel.getTypeId().equals("NE0002000002")) {
            tv_lx.setText("泊位");
        } else if (elementsModel.getTypeId().equals("NE0002000003")) {
            tv_lx.setText("码头");
        } else if (elementsModel.getTypeId().equals("NE0002000004")) {
            tv_lx.setText("桥梁");
        } else if (elementsModel.getTypeId().equals("NE0002000005")) {
            tv_lx.setText("通航孔");
        } else if (elementsModel.getTypeId().equals("NE0002000006")) {
            tv_lx.setText("船厂");
        } else if (elementsModel.getTypeId().equals("NE0002000007")) {
            tv_lx.setText("水下管线");
        } else if (elementsModel.getTypeId().equals("NE0002000008")) {
            tv_lx.setText("穿越段");
        } else if (elementsModel.getTypeId().equals("NE0002000009")) {
            tv_lx.setText("渡口");
        } else if (elementsModel.getTypeId().equals("NE00020000010")) {
            tv_lx.setText("渡线");
        } else if (elementsModel.getTypeId().equals("NE0002000011")) {
            tv_lx.setText("船台");
        } else if (elementsModel.getTypeId().equals("NE0002000012")) {
            tv_lx.setText("船坞");
        } else if (elementsModel.getTypeId().equals("NE0002000013")) {
            tv_lx.setText("水上管线");
        } else if (elementsModel.getTypeId().equals("NE0002000014")) {
            tv_lx.setText("跨越段");
        } else if (elementsModel.getTypeId().equals("NE002000015")) {
            tv_lx.setText("沉船沉物");
        } else if (elementsModel.getTypeId().equals("NE0002000016")) {
            tv_lx.setText("石油平台");
        } else if (elementsModel.getTypeId().equals("NE0002000017")) {
            tv_lx.setText("闸坝");
        } else if (elementsModel.getTypeId().equals("NE0002000020")) {
            tv_lx.setText("安全作业区");
        } else if (elementsModel.getTypeId().equals("NE0002000021")) {
            tv_lx.setText("航路");
        } else if (elementsModel.getTypeId().equals("NE0002000022")) {
            tv_lx.setText("航道");
        } else if (elementsModel.getTypeId().equals("NE0002000023")) {
            tv_lx.setText("锚地");
        } else if (elementsModel.getTypeId().equals("NE0002000024")) {
            tv_lx.setText("VTS报告线");
        } else if (elementsModel.getTypeId().equals("NE0003000001")) {
            tv_lx.setText("取排水口");
        } else if (elementsModel.getTypeId().equals("NE0004000001")) {
            tv_lx.setText("测试");
        } else if (elementsModel.getTypeId().equals("NE0005000001")) {
            tv_lx.setText("交通管制区");
        } else if (elementsModel.getTypeId().equals("NE0005000002")) {
            tv_lx.setText("停泊区");
        } else if (elementsModel.getTypeId().equals("NE0005000003")) {
            tv_lx.setText("船舶定线制区域");
        } else if (elementsModel.getTypeId().equals("NE0005000004")) {
            tv_lx.setText("禁锚区");
        } else if (elementsModel.getTypeId().equals("NE0005000005")) {
            tv_lx.setText("禁航区");
        } else if (elementsModel.getTypeId().equals("NE0005000006")) {
            tv_lx.setText("倾废区");
        }

        if (elementsModel.getCompany() == null || elementsModel.getCompany().equals("")) {
            tv_jg.setText("未知");
        } else {
            tv_jg.setText(elementsModel.getCompany());
        }
        textView.setTag(elementsModel);
        int i = PreferenceUtils.getInt(mContext, "nearbyposition", 0);
        if (position == i) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
        }
        //进入详情页面
        tv_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    public void chengeDatas(List<NavigableElementsData> datas) {
        if (datas != null) {
            this.datas = datas;
        }
    }
}
