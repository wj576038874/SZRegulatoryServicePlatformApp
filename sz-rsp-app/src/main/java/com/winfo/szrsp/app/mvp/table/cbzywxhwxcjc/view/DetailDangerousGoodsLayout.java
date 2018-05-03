package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HoBo on 2018/3/13.
 */

public class DetailDangerousGoodsLayout extends LinearLayout {

    @BindView(R.id.et_size)
    TextView et_size;
    @BindView(R.id.et_jcdm)
    TextView et_jcdm;
    @BindView(R.id.et_msqx)
    TextView et_msqx;
    @BindView(R.id.et_clyj)
    TextView et_clyj;
    @BindView(R.id.et_zgjg)
    TextView et_zgjg;
    private DangerousGoodsXianChangData.ctDangerPolluteSceneDetail data;

    public DetailDangerousGoodsLayout(Context context, DangerousGoodsXianChangData.ctDangerPolluteSceneDetail data) {
        super(context);
        this.data = data;
        LayoutInflater.from(context).inflate(R.layout.item_danger_goods_detail, this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        et_size.setText(data.getContainerNumber());
        et_jcdm.setText(data.getInspectCode());
        et_msqx.setText(data.getDescribe());
        et_clyj.setText(data.getHandle());
        et_zgjg.setText(data.getRectificat());
    }
}
