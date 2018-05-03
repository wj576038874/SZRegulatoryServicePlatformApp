package com.winfo.szrsp.app.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.adapter
 * @Filename: SearchNavigableElementsAdapter
 * @Author: lsj
 * @Date: 2017/12/21  15:09
 * @Description:
 * @Version:
 */
public class SearchNavigableElementsAdapter extends BaseQuickAdapter<NavigableElementsData,BaseViewHolder> {
    public SearchNavigableElementsAdapter(int layoutResId, @Nullable List<NavigableElementsData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, NavigableElementsData item) {
        holder.setImageResource(R.id.icon,R.mipmap.search_thys);
        holder.setText(R.id.ship_ywcm, item.getNameCn());
        holder.setText(R.id.ship_mmsi, item.getTypeName());
    }
}
