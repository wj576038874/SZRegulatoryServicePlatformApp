package com.winfo.szrsp.app.mvp.nearby.view;

import android.content.Context;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.sdk.entity.thys.ThysData;

import java.util.List;

/**
 * 通航要素类型的适配器
 *
 * @author 00
 */
public class ThysAdapter extends CommonAdapter<ThysData> {

    public ThysAdapter(Context context, List<ThysData> datas, int resource) {
        super(context, datas, resource);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        ThysData thysBean = datas.get(position);
        TextView textView = holder.getView(R.id.gv_item_control_area_name);
        textView.setText(thysBean.getTypeName());
        textView.setTag(thysBean);
    }

}
