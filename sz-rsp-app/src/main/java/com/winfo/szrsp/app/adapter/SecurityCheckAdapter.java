package com.winfo.szrsp.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.shipinfo.view.ShipInspectDefectInfoLayout;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.FuChaAnJianActivity;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;

import java.io.Serializable;
import java.util.List;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.adapter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.adapter.SecurityCheckAdapter.java
 * Date: 2017/12/22 17:17
 * Description:
 */

public class SecurityCheckAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private StateControlData stateControlData;
    private List<StateControlData.FlagStateControlDetailBean> detailBeanList;
    private String mmsi, ywcm;

    public SecurityCheckAdapter(Context mContext, StateControlData stateControlData, List<StateControlData.FlagStateControlDetailBean> detailBeanList, String mmsi, String ywcm) {
        this.mContext = mContext;
        this.stateControlData = stateControlData;
        this.detailBeanList = detailBeanList;
        this.mmsi = mmsi;
        this.ywcm = ywcm;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.security_check_item2, null));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int i) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_jcrq.setText(stateControlData.getFlagStateControlList().get(i).getInspect_date());
        viewHolder.tv_jcjg.setText(stateControlData.getFlagStateControlList().get(i).getOrg_code());
        viewHolder.tv_qxsl.setText(stateControlData.getFlagStateControlList().get(i).getDefect_num() + "");
        viewHolder.tv_ajlx.setText(stateControlData.getFlagStateControlList().get(i).getInspect_type());
        viewHolder.tv_qxs.setText(stateControlData.getFlagStateControlList().get(i).getDefect_num() + "");
        viewHolder.tv_wgbqxs.setText(stateControlData.getFlagStateControlList().get(i).getUnclosed_defect_num() + "");
        viewHolder.tv_jcgk.setText(stateControlData.getFlagStateControlList().get(i).getPortName());

        if(stateControlData.getFlagStateControlList().get(i).getDefect_num()>0){
            viewHolder.tvFuCha.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvFuCha.setVisibility(View.INVISIBLE);
        }
        if ("1".equals(stateControlData.getFlagStateControlList().get(i).getDetention_mark())) {
            viewHolder.tv_zlbz.setText("是");
        } else if ("0".equals(stateControlData.getFlagStateControlList().get(i).getDetention_mark())) {
            viewHolder.tv_zlbz.setText("否");
        }
        if ("1".equals(stateControlData.getFlagStateControlList().get(i).getCorrect_mark())) {
            viewHolder.tv_jzbz.setText("纠正");
        } else if ("0".equals(stateControlData.getFlagStateControlList().get(i).getCorrect_mark())) {
            viewHolder.tv_jzbz.setText("未纠正");
        }

        if ("1".equals(stateControlData.getFlagStateControlList().get(i).getInitial_inspect_mark())) {
            viewHolder.tv_ccfcbz.setText("复查");
        } else if ("0".equals(stateControlData.getFlagStateControlList().get(i).getInitial_inspect_mark())) {
            viewHolder.tv_ccfcbz.setText("初查");
        }

        viewHolder.tv_aqjcxd.setText(stateControlData.getFlagStateControlList().get(i).getInspect_action());
        viewHolder.tv_qtsm.setText(stateControlData.getFlagStateControlList().get(i).getOther_desc());
        viewHolder.tv_jcy.setText(stateControlData.getFlagStateControlList().get(i).getInspector_name());
        viewHolder.tv_sxjcy.setText(stateControlData.getFlagStateControlList().get(i).getPratice_inspector_name());
        viewHolder.tv_bz.setText(stateControlData.getFlagStateControlList().get(i).getRemark());
        if ("1".equals(stateControlData.getFlagStateControlList().get(i).getIs_special_inspect())) {
            viewHolder.tv_sfzxaj.setText("是");
        } else if ("0".equals(stateControlData.getFlagStateControlList().get(i).getIs_special_inspect())) {
            viewHolder.tv_sfzxaj.setText("否");
        }

        viewHolder.tv_zxajlx.setText(stateControlData.getFlagStateControlList().get(i).getSpecial_inspect_type());
        if ("1".equals(stateControlData.getFlagStateControlList().get(i).getIs_passed())) {
            viewHolder.tv_tgzxaj.setText("是");
        } else if ("0".equals(stateControlData.getFlagStateControlList().get(i).getIs_passed())) {
            viewHolder.tv_tgzxaj.setText("否");
        }

//        viewHolder.tv_czxm.setText(stateControlData.getFlagStateControlList().get(i).getInspect_date());
        viewHolder.tv_czlxfs.setText(stateControlData.getFlagStateControlList().get(i).getCaptain_phone());
        viewHolder.tv_czzsbh.setText(stateControlData.getFlagStateControlList().get(i).getCaptain_cert_no());
        viewHolder.tv_yxdj.setText(stateControlData.getFlagStateControlList().get(i).getPriority_order());
        viewHolder.tv_cbfxsx.setText(stateControlData.getFlagStateControlList().get(i).getRisk_attribute());
        viewHolder.tv_hcbm.setText(stateControlData.getFlagStateControlList().get(i).getVoyage_id());
        viewHolder.butImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.tabLin.getVisibility() == View.VISIBLE) {
                    viewHolder.tabLin.setVisibility(View.GONE);
                    viewHolder.butImg.setImageResource(R.mipmap.ic_item_down);
                } else {
                    viewHolder.tabLin.setVisibility(View.VISIBLE);
                    viewHolder.butImg.setImageResource(R.mipmap.ic_item_up);
                }
            }
        });
        if (detailBeanList != null) {
            for (int j = 0; j < detailBeanList.size(); j++) {
                if (stateControlData.getFlagStateControlList().get(i).getInspect_no().equals(detailBeanList.get(j).getInspect_no())) {
                    ShipInspectDefectInfoLayout layout = new ShipInspectDefectInfoLayout(mContext, detailBeanList.get(j));
                    viewHolder.lin_qxxx.addView(layout);
                }
            }
        }
        viewHolder.tvFuCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FuChaAnJianActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mmsi", mmsi);
                bundle.putString("ywcm", ywcm);
                bundle.putString("inspect_no", stateControlData.getFlagStateControlList().get(i).getInspect_no());
                bundle.putSerializable("detailList", (Serializable) stateControlData.getFlagStateControlDetail());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return stateControlData.getFlagStateControlList() == null ? 0 : stateControlData.getFlagStateControlList().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_jcrq;
        TextView tv_jcjg;
        TextView tv_qxsl;
        TextView tv_ajlx;
        TextView tv_qxs;
        TextView tv_wgbqxs;
        TextView tv_jcgk;
        TextView tv_zlbz;
        TextView tv_jzbz;
        TextView tv_ccfcbz;
        TextView tv_aqjcxd;
        TextView tv_qtsm;
        TextView tv_jcy;
        TextView tv_sxjcy;
        TextView tv_bz;
        TextView tv_sfzxaj;
        TextView tv_zxajlx;
        TextView tv_tgzxaj;
        TextView tv_czxm;
        TextView tv_czlxfs;
        TextView tv_czzsbh;
        TextView tv_yxdj;
        TextView tv_cbfxsx;
        TextView tv_hcbm;
        LinearLayout lin_qxxx;
        ImageButton butImg;
        public TableLayout tabLin;
        TextView tvFuCha;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_jcrq = itemView.findViewById(R.id.tv_jcrq);
            tv_jcjg = itemView.findViewById(R.id.tv_jcjg);
            tv_qxsl =  itemView.findViewById(R.id.tv_qxsl);
            tv_ajlx = itemView.findViewById(R.id.tv_ajlx);
            tv_qxs = itemView.findViewById(R.id.tv_qxs);
            tv_wgbqxs =  itemView.findViewById(R.id.tv_wgbqxs);
            tv_jcgk =itemView.findViewById(R.id.tv_jcgk);
            tv_zlbz =  itemView.findViewById(R.id.tv_zlbz);
            tv_jzbz =itemView.findViewById(R.id.tv_jzbz);
            tv_ccfcbz = itemView.findViewById(R.id.tv_ccfcbz);
            tv_aqjcxd = itemView.findViewById(R.id.tv_aqjcxd);
            tv_qtsm =  itemView.findViewById(R.id.tv_qtsm);
            tv_jcy =  itemView.findViewById(R.id.tv_jcy);
            tv_sxjcy =  itemView.findViewById(R.id.tv_sxjcy);
            tv_bz =  itemView.findViewById(R.id.tv_bz);
            tv_sfzxaj =  itemView.findViewById(R.id.tv_sfzxaj);
            tv_zxajlx =  itemView.findViewById(R.id.tv_zxajlx);
            tv_tgzxaj =  itemView.findViewById(R.id.tv_tgzxaj);
            tv_czxm =  itemView.findViewById(R.id.tv_czxm);
            tv_czlxfs = itemView.findViewById(R.id.tv_czlxfs);
            tv_czzsbh = itemView.findViewById(R.id.tv_czzsbh);
            tv_yxdj =  itemView.findViewById(R.id.tv_yxdj);
            tv_cbfxsx = itemView.findViewById(R.id.tv_cbfxsx);
            tv_hcbm =  itemView.findViewById(R.id.tv_hcbm);
            lin_qxxx = itemView.findViewById(R.id.lin_qxxx);
            butImg = itemView.findViewById(R.id.imgbtn_security_check_open);
            tabLin = itemView.findViewById(R.id.tablelayout_security_detail);
            tvFuCha = itemView.findViewById(R.id.tvFuCha);
        }
    }
}
