package com.winfo.szrsp.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.adapter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.adapter.CrewInfoAdapter.java
 * Date: 2017/12/22 13:40
 * Description:
 */

public class CrewInfoAdapter extends BaseQuickAdapter<CrewInfo, BaseViewHolder> {


    public CrewInfoAdapter(int layoutResId, @Nullable List<CrewInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, CrewInfo item) {
        holder.setText(R.id.tv_crew_cyxm, item.getCyxm());
        holder.setText(R.id.tv_crew_cykh, item.getCykh());
        holder.setText(R.id.tv_crew_sfzhm, item.getSfzhm());
        holder.setText(R.id.tv_crew_cyxbdm, item.getCyxbdm());
        holder.setText(R.id.tv_crew_csrq, item.getCsrq());
        holder.setText(R.id.tv_crew_gjdm, item.getGjdm());
        holder.setText(R.id.tv_crew_cylxdz, item.getCylxdz());
        holder.setText(R.id.tv_crew_cylxdh, item.getCylxdh());
        holder.setText(R.id.tv_crew_srzslb, item.getSrzslb());
        holder.setText(R.id.tv_crew_cyzshm, item.getCyzshm());
        holder.setText(R.id.tv_crew_zszwdm, item.getZszwdm());
        holder.setText(R.id.tv_crew_zszwmc, item.getZszwmc());
        holder.setText(R.id.tv_crew_cbsbh, item.getCbsbh());
        holder.setText(R.id.tv_crew_cbzj, item.getCbzj());
        holder.setText(R.id.tv_crew_srzydm, item.getSrzydm());
        holder.setText(R.id.tv_crew_jzrq, item.getJzrq());
        holder.setText(R.id.tv_crew_jzbz, item.getJzbz());
        holder.setText(R.id.tv_crew_gxbz, item.getGxbz());
        holder.addOnClickListener(R.id.imgbtn_crew_open);
    }
}
