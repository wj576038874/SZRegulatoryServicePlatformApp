package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import static com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.ExpandableTaskInspectionAdapter.TYPE_LEVEL_0;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.task.view.ExpandableItemAdapter.java
 * Date: 2017/12/12 10:21
 * Description:
 */

public class ExpandableAllInspectionAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    public static final int TYPE_LEVEL_3 = 3;
    public static final int TYPE_LEVEL_4 = 4;
    List<MultiItemEntity> mData;
//    private int[] selectPosition=new int[]{10086,10086,10086,10086};
    private Context mContext;
    public ExpandableAllInspectionAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        mData=data;
//        this.selectPosition=selectPosition;
        this.mContext = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_all_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_all_lv1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_all_lv2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_all_lv3);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable_all_lv4);
        setSelectData();
    }



    String selectFuseName="",selectFuseId="";
    @SuppressLint("NewApi")
    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case TYPE_LEVEL_0:
                final Level0Item level0Item = (Level0Item) item;
                holder.setImageResource(R.id.iv, level0Item.isExpanded() ? R.mipmap.crew_collected : R.mipmap.crew_open)
                        .setOnClickListener(R.id.iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                if (level0Item.isExpanded()) {
                                    collapse(pos, true);
                                } else {
                                    expand(pos, true);
                                }
                            }
                        });
//                if(level0Item.isExpanded()){
//                    expand(holder.getAdapterPosition(), false,false);
//                }
//                if(selectPosition[0]!=10086){
//                    expand(selectPosition[0], false,false);
//                }
                holder.setText(R.id.text, level0Item.getItemFuseName());
                break;
            case TYPE_LEVEL_1:
                final Level1Item level1Item = (Level1Item) item;
                holder.setImageResource(R.id.iv, level1Item.isExpanded() ? R.mipmap.crew_collected : R.mipmap.crew_open)
                        .setOnClickListener(R.id.iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                if (level1Item.isExpanded()) {
                                    collapse(pos, true);
                                } else {
                                    expand(pos, true);
                                }
                            }
                        });
//                if(level1Item.isExpanded()){
//                    expand(holder.getAdapterPosition(), false,false);
//                }
//                if(selectPosition[1]!=10086){
//                    expand(selectPosition[1], false,false);
//                }
                holder.setText(R.id.text, level1Item.getItemFuseName());
                holder.setVisible(R.id.iv,level1Item.hasSubItem()?true:false);
                holder.setChecked(R.id.check,level1Item.isChecked()?true:false);
                if(level1Item.isChecked()){
                    selectFuseId=level1Item.getItemFuseId();
                    selectFuseName=level1Item.getItemFuseName();
                }
                final CheckBox check1 = holder.getView(R.id.check);
                final TextView text1 = holder.getView(R.id.text);
                if(level1Item.hasSubItem()){
                    check1.setVisibility(View.GONE);
                }else {
                    check1.setVisibility(View.VISIBLE);

                    check1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check1.isChecked()){
                                //设置当前勾选 其他不勾选
                                setDialogSelect(level1Item.getItemFuseId());
                                selectFuseName=level1Item.getItemFuseName();
                                selectFuseId=level1Item.getItemFuseId();
                            }else {
                                level1Item.setChecked(false);
                                selectFuseName="";
                                selectFuseId="";
                            }
                            notyfyData();
                        }
                    });
                    text1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check1.isChecked()){
                                //设置当前勾选 其他不勾选
                                level1Item.setChecked(false);
                                selectFuseName="";
                                selectFuseId="";

                            }else {
                                setDialogSelect(level1Item.getItemFuseId());
                                selectFuseName=level1Item.getItemFuseName();
                                selectFuseId=level1Item.getItemFuseId();
                            }
                            notyfyData();
                        }
                    });
                }
                break;
            case TYPE_LEVEL_2:
                final Level2Item level2Item = (Level2Item) item;
                holder.setImageResource(R.id.iv, level2Item.isExpanded() ? R.mipmap.crew_collected : R.mipmap.crew_open)
                        .setOnClickListener(R.id.iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                if (level2Item.isExpanded()) {
                                    collapse(pos, true);
                                } else {
                                    expand(pos, true);
                                }
                            }
                        });
//                if(level2Item.isExpanded()){
//                    final int adapterPosition = holder.getAdapterPosition();
//                    expand(holder.getAdapterPosition(), false,false);
//                }
//                if(selectPosition[2]!=10086){
//                    expand(selectPosition[2], false,false);
//                }
                holder.setText(R.id.text, level2Item.getItemFuseName());
                holder.setVisible(R.id.iv,level2Item.hasSubItem()?true:false);
                holder.setChecked(R.id.check,level2Item.isChecked()?true:false);
                if(level2Item.isChecked()){
                    selectFuseId=level2Item.getItemFuseId();
                    selectFuseName=level2Item.getItemFuseName();
                }
                final CheckBox check2 = holder.getView(R.id.check);
                final TextView text2 = holder.getView(R.id.text);
                if(level2Item.hasSubItem()){
                    check2.setVisibility(View.GONE);
                }else {
                    check2.setVisibility(View.VISIBLE);

                    check2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check2.isChecked()){
                                //设置当前勾选 其他不勾选
                                setDialogSelect(level2Item.getItemFuseId());
                                selectFuseName=level2Item.getItemFuseName();
                                selectFuseId=level2Item.getItemFuseId();
                            }else {
                                level2Item.setChecked(false);
                                selectFuseName="";
                                selectFuseId="";
                            }
                            notyfyData();
                        }
                    });
                    text2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check2.isChecked()){
                                //设置当前勾选 其他不勾选
                                level2Item.setChecked(false);
                                selectFuseName="";
                                selectFuseId="";

                            }else {
                                setDialogSelect(level2Item.getItemFuseId());
                                selectFuseName=level2Item.getItemFuseName();
                                selectFuseId=level2Item.getItemFuseId();
                            }
                            notyfyData();
                        }
                    });
                }
                break;
            case TYPE_LEVEL_3:
                final Level3Item level3Item = (Level3Item) item;
                holder.setImageResource(R.id.iv, level3Item.isExpanded() ? R.mipmap.crew_collected : R.mipmap.crew_open)
                        .setOnClickListener(R.id.iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getAdapterPosition();
                                if (level3Item.isExpanded()) {
                                    collapse(pos, true);
                                } else {
                                    expand(pos, true);
                                }
                            }
                        });
//                if(level3Item.isExpanded()){
//                    expand(holder.getAdapterPosition(), false,false);
//                }
//                if(selectPosition[3]!=10086){
//                    expand(selectPosition[3], false,false);
//                }
                holder.setText(R.id.text, level3Item.getItemFuseName());
                holder.setVisible(R.id.iv,level3Item.hasSubItem()?true:false);
                holder.setChecked(R.id.check,level3Item.isChecked()?true:false);
//                if(level3Item.isChecked()){
//                    selectFuseId=level3Item.getItemFuseId();
//                    selectFuseName=level3Item.getItemFuseName();
//                }
                final CheckBox check3 = holder.getView(R.id.check);
                final TextView text3 = holder.getView(R.id.text);
                if(level3Item.hasSubItem()){
                    check3.setVisibility(View.GONE);
                }else {
                    check3.setVisibility(View.VISIBLE);

                    check3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check3.isChecked()){
                                //设置当前勾选 其他不勾选
                                setDialogSelect(level3Item.getItemFuseId());
                                selectFuseName=level3Item.getItemFuseName();
                                selectFuseId=level3Item.getItemFuseId();
                            }else {
                                level3Item.setChecked(false);
                                selectFuseName="";
                                selectFuseId="";
                            }
                            notyfyData();
                        }
                    });
                    text3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check3.isChecked()){
                                //设置当前勾选 其他不勾选
                                level3Item.setChecked(false);
                                selectFuseName="";
                                selectFuseId="";


                            }else {
                                setDialogSelect(level3Item.getItemFuseId());
                                selectFuseName=level3Item.getItemFuseName();
                                selectFuseId=level3Item.getItemFuseId();

                            }
                            notyfyData();
                        }
                    });
                }
                break;
            case TYPE_LEVEL_4:
                final Level4Item level4Item = (Level4Item) item;
                holder.setText(R.id.check, level4Item.getItemFuseName());
                holder.setChecked(R.id.check,level4Item.isChecked()?true:false);
                if(level4Item.isChecked()){
                    selectFuseId=level4Item.getItemFuseId();
                    selectFuseName=level4Item.getItemFuseName();
                }
                final CheckBox check4 = holder.getView(R.id.check);
                final TextView text4 = holder.getView(R.id.text);
                check4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(check4.isChecked()){
                            //设置当前勾选 其他不勾选
                            setDialogSelect(level4Item.getItemFuseId());
                            selectFuseName=level4Item.getItemFuseName();
                            selectFuseId=level4Item.getItemFuseId();
                        }else {
                            level4Item.setChecked(false);
                            selectFuseName="";
                            selectFuseId="";
                        }
                        notyfyData();
                    }
                });
                text4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(check4.isChecked()){
                            //设置当前勾选 其他不勾选
                            level4Item.setChecked(false);
                            selectFuseName="";
                            selectFuseId="";
                        }else {
                            setDialogSelect(level4Item.getItemFuseId());
                            selectFuseName=level4Item.getItemFuseName();
                            selectFuseId=level4Item.getItemFuseId();
                        }
                        notyfyData();
                    }
                });
                break;
        }
    }

    private void notyfyData(){
        try{
            notifyDataSetChanged();
        } catch (Exception e){

        }
    }
    public Map<String,Object> returnData(){
        Map<String, Object> map = new HashMap<>();
        map.put("name",selectFuseName);
        map.put("id", selectFuseId);
        return map;
    }
    private void setSelectData() {
        for (int i = 0; i <mData.size() ; i++) {
            switch (mData.get(i).getItemType()){
                case TYPE_LEVEL_0:
                    Level0Item level0Item = (Level0Item) mData.get(i);
                    if(level0Item.hasSubItem()){
                        //二层
                        for (int j = 0; j < level0Item.getSubItems().size(); j++) {
                            if(level0Item.getSubItem(j).isChecked()){
                                selectFuseName=level0Item.getSubItem(j).getItemFuseName();
                                selectFuseId=level0Item.getSubItem(j).getItemFuseId();
                            }
                            if(level0Item.getSubItem(j).hasSubItem()){//三层
                                for (int k = 0; k < level0Item.getSubItem(j).getSubItems().size(); k++) {
                                    if(level0Item.getSubItem(j).getSubItem(k).isChecked()){
                                        selectFuseName=level0Item.getSubItem(j).getSubItem(k).getItemFuseName();
                                        selectFuseId=level0Item.getSubItem(j).getSubItem(k).getItemFuseId();
                                    }
                                    if(level0Item.getSubItem(j).getSubItem(k).hasSubItem()){//四层
                                        for (int l = 0; l < level0Item.getSubItem(j).getSubItem(k).getSubItems().size(); l++) {
                                            if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).isChecked()){
                                                selectFuseName=level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseName();
                                                selectFuseId=level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseId();
                                            }
                                            if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).hasSubItem()){//五层
                                                for (int m = 0; m <level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItems().size() ; m++) {
                                                    if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).isChecked()){
                                                        selectFuseName=level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).getItemFuseName();
                                                        selectFuseId=level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).getItemFuseId();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }
    private void setDialogSelect(String id){
            for (int i = 0; i <mData.size() ; i++) {
                switch (mData.get(i).getItemType()){
                    case TYPE_LEVEL_0:
                        Level0Item level0Item = (Level0Item) mData.get(i);
                        if(level0Item.hasSubItem()){
                            //二层
                            for (int j = 0; j < level0Item.getSubItems().size(); j++) {
                                    if(level0Item.getSubItem(j).getItemFuseId().equals(id)){
                                        level0Item.getSubItem(j).setChecked(true);
                                    }else {
                                        level0Item.getSubItem(j).setChecked(false);
                                    }
                                    if(level0Item.getSubItem(j).hasSubItem()){//三层
                                        for (int k = 0; k < level0Item.getSubItem(j).getSubItems().size(); k++) {
                                            if(level0Item.getSubItem(j).getSubItem(k).getItemFuseId().equals(id)){
                                                level0Item.getSubItem(j).getSubItem(k).setChecked(true);
                                            }else {
                                                level0Item.getSubItem(j).getSubItem(k).setChecked(false);
                                            }
                                            if(level0Item.getSubItem(j).getSubItem(k).hasSubItem()){//四层
                                                for (int l = 0; l < level0Item.getSubItem(j).getSubItem(k).getSubItems().size(); l++) {
                                                    if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseId().equals(id)){
                                                        level0Item.getSubItem(j).getSubItem(k).getSubItem(l).setChecked(true);
                                                    }else {
                                                        level0Item.getSubItem(j).getSubItem(k).getSubItem(l).setChecked(false);
                                                    }
                                                    if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).hasSubItem()){//五层
                                                        for (int m = 0; m <level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItems().size() ; m++) {
                                                            if(level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).getItemFuseId().equals(id)){
                                                                level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).setChecked(true);
                                                            }else {
                                                                level0Item.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).setChecked(false);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                            }
                        }
                }
            }
    }

}
