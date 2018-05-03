package com.winfo.szrsp.app.mvp.nearby.view;

import android.content.Context;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.sdk.entity.thys.ShipData;

import java.util.List;

/**
 * 船舶类型适配器
 *
 * @author 00
 */
public class ShipAdapter extends CommonAdapter<ShipData> {

    public ShipAdapter(Context context, List<ShipData> datas, int resource) {
        super(context, datas, resource);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        ShipData shipData = datas.get(position);
        TextView textView = holder.getView(R.id.gv_item_control_area_name);
        textView.setText(shipData.getShipTypeNameCn());
        textView.setTag(shipData.getShipTypeNameCn());
    }

}
