package com.winfo.szrsp.app.mvp.nearby.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;
import com.winfo.dnc.sdk.AisData;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.mvp.task.view.ShipMapActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.utils.DataHolder;
import com.winfo.szrsp.app.utils.PreferenceUtils;


import java.text.DecimalFormat;
import java.util.List;

/**
 * @项目名: gdmsaec-app
 * @包名: com.winfo.gdmsaec.app.adapter.nearby
 * @类名: NearbyShipAapter
 * @创建者: wenjie
 * @创建时间: 2015-11-10	下午2:24:33
 * @描述: TODO
 * @svn版本: $Rev: 1770 $
 * @更新人: $Author: wenjie $
 * @更新时间: $Date: 2016-03-22 17:18:14 +0800 (Tue, 22 Mar 2016) $
 * @更新描述: TODO
 */
public class NearbyShipAapter extends CommonAdapter<Ais> {

    public NearbyShipAapter(Context context, List<Ais> datas, int resource) {
        super(context, datas, resource);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        final Ais ship = datas.get(position);
        TextView textView = holder.getView(R.id.nearby_list_item_names);
        TextView tvclickView = holder.getView(R.id.near_data_click);
        TextView tc_jl = holder.getView(R.id.near_juli);
        TextView tc_mmsi = holder.getView(R.id.near_boat_mmsi);
        TextView tc_hxzt = holder.getView(R.id.near_boat_hxzt);
        TextView tc_hs = holder.getView(R.id.near_boat_hs);
        textView.setText(position + 1 + "." + ship.getCM());
        double jl = ship.getDistance() / 1000;
        DecimalFormat df = new DecimalFormat("0.00");
        String a = df.format(jl);
        tc_jl.setText(a + "千米");
        tc_mmsi.setText(ship.getID());
        tc_hxzt.setText(ship.getDHZT());
        String hs = String.valueOf(ship.getHS());
        tc_hs.setText(hs + "海里/小时");
        textView.setTag(ship);

        int i = PreferenceUtils.getInt(mContext, "nearbyposition", -1);
        if (position == i) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
        }
        //点击详情
        tvclickView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                DataHolder.getInstance().save("AisData", ship);
                Intent intent = new Intent(mContext, ShipMapActivity.class);
//                String mmsi=ship.getID();
                intent.putExtra("AisData", ship);
                mContext.startActivity(intent);
            }
        });
    }

    public void chengeDatas(List<Ais> datasList) {
        if (datasList != null) {
            this.datas = datasList;
        }
    }

}
