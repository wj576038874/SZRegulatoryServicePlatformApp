package com.winfo.szrsp.app.adapter.searchship;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.entity.HistoryAis;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.adapter.searchship.SearchHistoryShipAdapter.java
 * Date: 2017/12/16 13:27
 * Description:
 */

public class SearchHistoryShipAdapter extends BaseQuickAdapter<HistoryAis, BaseViewHolder> {


    public SearchHistoryShipAdapter(int layoutResId, @Nullable List<HistoryAis> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, HistoryAis item) {
        holder.setText(R.id.tv_history_ywcm, item.getYwcm());
        holder.setText(R.id.tv_history_mmsi, item.getMmsi());
    }
}
