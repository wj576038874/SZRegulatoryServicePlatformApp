package com.winfo.szrsp.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;

import java.util.List;

/**
 * Created by HoBo on 2018/4/16.
 */

public class CruiseShipSearchAdapter extends BaseAdapter {
    private Context mContext;
    private List<TaskInspectionItemByNameData> data;

    public CruiseShipSearchAdapter(Context mContext, List<TaskInspectionItemByNameData> inspectionItemData) {
        this.mContext = mContext;
        this.data = inspectionItemData;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cruise_search_ship, null);
            viewHolder = new ViewHolder();
            viewHolder.tvFuseName = convertView.findViewById(R.id.tvFuseName);
            viewHolder.ckItem = convertView.findViewById(R.id.ckItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvFuseName.setText(data.get(position).getItemFuseName());
        if (data.get(position).isCheckeds()) {
            viewHolder.ckItem.setChecked(true);
        } else {
            viewHolder.ckItem.setChecked(false);
        }


        return convertView;
    }

    static class ViewHolder {
        public CheckBox ckItem;
        public TextView tvFuseName;
    }

}
