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
import com.winfo.szrsp.app.mvp.shipinfo.view.ShipInspectSupervisionInfoLayout;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.FuChaJianDuActivity;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;

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

public class XianChangAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ShipXianChangInfo shipXianChangInfo;
    private List<ShipXianChangInfo.SiteSupervisionDetailBean> xcDetailBeanList;
    private String mmsi, cm;

    public XianChangAdapter(Context mContext, ShipXianChangInfo shipXianChangInfos, List<ShipXianChangInfo.SiteSupervisionDetailBean> xcDetailBeanList, String mmsi, String cm) {
        this.mContext = mContext;
        this.shipXianChangInfo = shipXianChangInfos;
        this.xcDetailBeanList = xcDetailBeanList;
        this.mmsi = mmsi;
        this.cm = cm;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.xianchang_check_item, null));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int i) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_jcrq.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getInspect_date());
        viewHolder.tv_jcjg.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getOrg_code());
        viewHolder.tv_qxsl.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getDefect_num() + "");
        viewHolder.tv_ajlx.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getInspect_type());
        viewHolder.tv_qxs.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getDefect_num() + "");
        viewHolder.tv_wgbqxs.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getUnclosed_num() + "");
//        viewHolder.tv_jcgk.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
//        if ("1".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i))) {
//            viewHolder.tv_zlbz.setText("是");
//        } else if ("0".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i))) {
//            viewHolder.tv_zlbz.setText("否");
//        }

        if (shipXianChangInfo.getSiteSupervisionReportList().get(i).getDefect_num() > 0) {
            viewHolder.tvFuCha.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvFuCha.setVisibility(View.INVISIBLE);
        }
        if ("1".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i).getCorrect_mark())) {
            viewHolder.tv_jzbz.setText("纠正");
        } else if ("0".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i).getCorrect_mark())) {
            viewHolder.tv_jzbz.setText("未纠正");
        } else {
            viewHolder.tv_jzbz.setText("");
        }

        if ("1".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i).getInitial_inspect_mark())) {
            viewHolder.tv_ccfcbz.setText("复查");
        } else if ("0".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i).getInitial_inspect_mark())) {
            viewHolder.tv_ccfcbz.setText("初查");
        } else {
            viewHolder.tv_ccfcbz.setText("");
        }

//        viewHolder.tv_aqjcxd.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
//        viewHolder.tv_qtsm.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
        viewHolder.tv_jcy.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getInspector_name());
//        viewHolder.tv_sxjcy.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
        viewHolder.tv_bz.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getRemark());
        if ("1".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i).getIs_special_inspect())) {
            viewHolder.tv_sfzxaj.setText("是");
        } else if ("0".equals(shipXianChangInfo.getSiteSupervisionReportList().get(i).getIs_special_inspect())) {
            viewHolder.tv_sfzxaj.setText("否");
        } else {
            viewHolder.tv_sfzxaj.setText("");
        }

//        viewHolder.tv_zxajlx.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getInspect_type());
//        viewHolder.tv_tgzxaj.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
        viewHolder.tv_czxm.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getCaptain_name());
//        viewHolder.tv_czlxfs.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
//        viewHolder.tv_czzsbh.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i));
        viewHolder.tv_yxdj.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getPriority_order());
        viewHolder.tv_cbfxsx.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getRisk_attribute());
        viewHolder.tv_hcbm.setText(shipXianChangInfo.getSiteSupervisionReportList().get(i).getVoyage_id());
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
        viewHolder.tvFuCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FuChaJianDuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mmsi", mmsi);
                bundle.putString("cm", cm);
                bundle.putSerializable("siteSupervisionReportListBean", shipXianChangInfo.getSiteSupervisionReportList().get(i));
                bundle.putSerializable("xcDetailBeanList", (Serializable) xcDetailBeanList);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        if (xcDetailBeanList != null) {
            for (int j = 0; j < xcDetailBeanList.size(); j++) {
                if (shipXianChangInfo.getSiteSupervisionReportList().get(i).getInspect_no().equals(xcDetailBeanList.get(j).getInspect_no())) {
                    ShipInspectSupervisionInfoLayout layout = new ShipInspectSupervisionInfoLayout(mContext, xcDetailBeanList.get(j));
                    viewHolder.lin_wt.addView(layout);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return shipXianChangInfo.getSiteSupervisionReportList() == null ? 0 : shipXianChangInfo.getSiteSupervisionReportList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_jcrq;
        TextView tv_jcjg;
        TextView tv_qxsl;
        TextView tv_ajlx;
        TextView tv_qxs;
        TextView tv_wgbqxs;
        //        public TextView tv_jcgk;
        TextView tv_zlbz;
        TextView tv_jzbz;
        TextView tv_ccfcbz;
        //        public TextView tv_aqjcxd;
//        public TextView tv_qtsm;
        TextView tv_jcy;
        //        public TextView tv_sxjcy;
        public TextView tv_bz;
        TextView tv_sfzxaj;
        //        public TextView tv_zxajlx;
//        public TextView tv_tgzxaj;
        TextView tv_czxm;
        //        public TextView tv_czlxfs;
//        public TextView tv_czzsbh;
        TextView tv_yxdj;
        TextView tv_cbfxsx;
        TextView tv_hcbm;
        LinearLayout lin_wt;
        ImageButton butImg;
        public TableLayout tabLin;
        TextView tvFuCha;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_jcrq = itemView.findViewById(R.id.tv_jcrq);
            tv_jcjg = itemView.findViewById(R.id.tv_jcjg);
            tv_qxsl = itemView.findViewById(R.id.tv_qxsl);
            tv_ajlx = itemView.findViewById(R.id.tv_ajlx);
            tv_qxs = itemView.findViewById(R.id.tv_qxs);
            tv_wgbqxs = itemView.findViewById(R.id.tv_wgbqxs);
//            tv_jcgk = (TextView) itemView.findViewById(R.id.tv_jcgk);
            tv_zlbz = itemView.findViewById(R.id.tv_zlbz);
            tv_jzbz = itemView.findViewById(R.id.tv_jzbz);
            tv_ccfcbz = itemView.findViewById(R.id.tv_ccfcbz);
//            tv_aqjcxd = (TextView) itemView.findViewById(R.id.tv_aqjcxd);
//            tv_qtsm = (TextView) itemView.findViewById(R.id.tv_qtsm);
            tv_jcy = itemView.findViewById(R.id.tv_jcy);
//            tv_sxjcy = (TextView) itemView.findViewById(R.id.tv_sxjcy);
            tv_bz = itemView.findViewById(R.id.tv_bz);
            tv_sfzxaj = itemView.findViewById(R.id.tv_sfzxaj);
//            tv_zxajlx = (TextView) itemView.findViewById(R.id.tv_zxajlx);
//            tv_tgzxaj = (TextView) itemView.findViewById(R.id.tv_tgzxaj);
            tv_czxm = itemView.findViewById(R.id.tv_czxm);
//            tv_czlxfs = (TextView) itemView.findViewById(R.id.tv_czlxfs);
//            tv_czzsbh = (TextView) itemView.findViewById(R.id.tv_czzsbh);
            tv_yxdj = itemView.findViewById(R.id.tv_yxdj);
            tv_cbfxsx = itemView.findViewById(R.id.tv_cbfxsx);
            tv_hcbm = itemView.findViewById(R.id.tv_hcbm);
            lin_wt = itemView.findViewById(R.id.lin_wt);
            butImg = itemView.findViewById(R.id.imgbtn_security_check_open);
            tabLin = itemView.findViewById(R.id.tablelayout_xianchang_detail);
            tvFuCha = itemView.findViewById(R.id.tvFuCha);
        }
    }
}
