package com.winfo.szrsp.app.adapter.searchship;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.entity.HistoryThsy;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.adapter.searchship
 * @Filename: SearchHistoryThsyAdapter
 * @Author: lsj
 * @Date: 2017/12/21  15:36
 * @Description:
 * @Version:
 */
public class SearchHistoryThsyAdapter extends BaseQuickAdapter<HistoryThsy, BaseViewHolder> {
    public SearchHistoryThsyAdapter(int layoutResId, @Nullable List<HistoryThsy> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, HistoryThsy item) {
        holder.setText(R.id.tv_history_ywcm, item.getName());
        holder.setText(R.id.tv_history_mmsi, item.getTypename());
    }
}
