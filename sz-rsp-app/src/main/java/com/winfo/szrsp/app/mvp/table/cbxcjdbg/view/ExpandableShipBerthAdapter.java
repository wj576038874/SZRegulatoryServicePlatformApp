package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.mvp.task.view.Level2Item;
import com.winfo.szrsp.app.mvp.task.view.Level3Item;
import com.winfo.szrsp.app.mvp.task.view.Level4Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.task.view.ExpandableItemAdapter.java
 * Date: 2017/12/12 10:21
 * Description:
 */

public class ExpandableShipBerthAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    List<MultiItemEntity> mData;
    private Context mContext;

    public ExpandableShipBerthAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        mData = data;
        this.mContext = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_all_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_all_lv1);
        setSelectData();
    }


    String selectFuseName = "", selectFuseId = "";

    @SuppressLint("NewApi")
    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case TYPE_LEVEL_0:
                final BerthLevelItem0 berthLevelItem0 = (BerthLevelItem0) item;
                holder.setImageResource(R.id.iv, berthLevelItem0.isExpanded() ? R.mipmap.crew_collected : R.mipmap.crew_open)
                        .setOnClickListener(R.id.iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                if (berthLevelItem0.isExpanded()) {
                                    collapse(pos, true);
                                } else {
                                    expand(pos, true);
                                }
                            }
                        });
                holder.setText(R.id.text, berthLevelItem0.getName());
                break;
            case TYPE_LEVEL_1:
                final BerthLevelItem1 berthLevelItem1 = (BerthLevelItem1) item;
                holder.setText(R.id.check, berthLevelItem1.getName());
                holder.setChecked(R.id.check, berthLevelItem1.isChecked() ? true : false);
                holder.setVisible(R.id.iv, false);
                if (berthLevelItem1.isChecked()) {
                    selectFuseId = berthLevelItem1.getId();
                    selectFuseName = berthLevelItem1.getName();
                }
                final CheckBox check1 = holder.getView(R.id.check);
                final TextView text1 = holder.getView(R.id.text);
                check1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (check1.isChecked()) {
                            //设置当前勾选 其他不勾选
                            setDialogSelect(berthLevelItem1.getId());
                            selectFuseName = berthLevelItem1.getName();
                            selectFuseId = berthLevelItem1.getId();
                        } else {
                            berthLevelItem1.setChecked(false);
                            selectFuseName = "";
                            selectFuseId = "";
                        }
                        notyfyData();
                    }
                });
                text1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (check1.isChecked()) {
                            //设置当前勾选 其他不勾选
                            berthLevelItem1.setChecked(false);
                            selectFuseName = "";
                            selectFuseId = "";
                        } else {
                            setDialogSelect(berthLevelItem1.getId());
                            selectFuseName = berthLevelItem1.getName();
                            selectFuseId = berthLevelItem1.getId();
                        }
                        notyfyData();
                    }
                });
                break;
        }
    }

    private void notyfyData() {
        try {
            notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    public Map<String, Object> returnData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", selectFuseName);
        map.put("id", selectFuseId);
        return map;
    }

    private void setSelectData() {
        for (int i = 0; i < mData.size(); i++) {
            switch (mData.get(i).getItemType()) {
                case TYPE_LEVEL_0:
//                    Level0Item level0Item = (Level0Item) mData.get(i);
                    BerthLevelItem0 berthLevelItem0 = (BerthLevelItem0) mData.get(i);
                    if (berthLevelItem0.hasSubItem()) {
                        //二层
                        for (int j = 0; j < berthLevelItem0.getSubItems().size(); j++) {
                            if (berthLevelItem0.getSubItem(j).isChecked()) {
                                selectFuseName = berthLevelItem0.getSubItem(j).getName();
                                selectFuseId = berthLevelItem0.getSubItem(j).getId();
                            }
                        }
                    }
            }
        }
    }

    private void setDialogSelect(String id) {
        for (int i = 0; i < mData.size(); i++) {
            switch (mData.get(i).getItemType()) {
                case TYPE_LEVEL_0:
//                    Level0Item level0Item = (Level0Item) mData.get(i);
                    BerthLevelItem0 berthLevelItem0 = (BerthLevelItem0) mData.get(i);
                    if (berthLevelItem0.hasSubItem()) {
                        //二层
                        for (int j = 0; j < berthLevelItem0.getSubItems().size(); j++) {
                            if (berthLevelItem0.getSubItem(j).getId().equals(id)) {
                                berthLevelItem0.getSubItem(j).setChecked(true);
                            } else {
                                berthLevelItem0.getSubItem(j).setChecked(false);
                            }
                        }
                    }
            }
        }
    }

}
