package com.winfo.szrsp.app.adapter.searchship;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.adapter.searchship.SearchShipAdapter.java
 * Date: 2017/12/16 9:57
 * Description:
 */

public class SearchShipAdapter extends BaseQuickAdapter<ShipSearch, BaseViewHolder> {


    public SearchShipAdapter(int layoutResId, @Nullable List<ShipSearch> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ShipSearch item) {
        holder.setText(R.id.ship_ywcm, item.getYwcm());
        holder.setText(R.id.ship_mmsi, item.getMmsi());
    }
}
