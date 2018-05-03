package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lidroid.xutils.db.annotation.Check;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.mvp.task.view.Level2Item;
import com.winfo.szrsp.app.mvp.task.view.Level3Item;
import com.winfo.szrsp.app.mvp.task.view.Level4Item;
import com.winfo.szrsp.app.mvp.task.view.TaskAcceptDialog;
import com.winfo.szrsp.app.sdk.entity.task.TaskResources;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.task.view.ExpandableItemAdapter.java
 * Date: 2017/12/12 10:21
 * Description:
 */

public class ExpandableTaskInspectionAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>{
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

    private Context mContext;
    public ExpandableTaskInspectionAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        mData=data;
        this.mContext = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_task_all_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_task_all_lv1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_task_all_lv2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_task_all_lv3);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable_task_all_lv4);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case TYPE_LEVEL_0:
                final Level0Item level0Item = (Level0Item) item;
                holder.setText(R.id.check, level0Item.getItemFuseName());
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
                holder.setChecked(R.id.check,level0Item.isChecked()?true:false);
                final CheckBox check0 = holder.getView(R.id.check);
                if(level0Item.isCanClick()){
                    check0.setClickable(true);
                    check0.setEnabled(true);
                    check0.setTextColor(Color.parseColor("#333333"));
                }else {
                    check0.setClickable(false);
                    check0.setEnabled(false);
                    check0.setTextColor(Color.parseColor("#999999"));
                }
                if(level0Item.isCanClick()){
                    check0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check0.isChecked()){
                                level0Item.setChecked(true);
                                int pos = holder.getAdapterPosition();
                                if (level0Item.isExpanded()) {
                                } else {
                                    expand(pos, true);
                                }
                                //遍历第二层级 全部勾选
                                //遍历第二三四五层级 全部勾选
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item = (Level1Item) mData.get(i);
                                            //第二层级 全部勾选
                                            if(level1Item.getItemFuseFaterId().equals((level0Item.getItemFuseId()))){
                                                level1Item.setChecked(true);
                                            }
                                            if(level1Item.hasSubItem()){
                                                for (int j = 0; j <level1Item.getSubItems().size() ; j++) {
                                                    //第三层级 全部勾选
                                                    if(level1Item.getSubItem(j).getItemFuseFaterId().equals((level1Item.getItemFuseId()))){
                                                        level1Item.getSubItem(j).setChecked(true);
                                                    }
                                                    if(level1Item.getSubItem(j).hasSubItem()){
                                                        for (int k = 0; k < level1Item.getSubItem(j).getSubItems().size(); k++) {
                                                            //第四层级 全部勾选
                                                            if(level1Item.getSubItem(j).getSubItem(k).getItemFuseFaterId().equals((level1Item.getSubItem(j).getItemFuseId()))){
                                                                level1Item.getSubItem(j).getSubItem(k).setChecked(true);
                                                            }
                                                            if(level1Item.getSubItem(j).getSubItem(k).hasSubItem()){
                                                                for (int l = 0; l <level1Item.getSubItem(j).getSubItem(k).getSubItems().size() ; l++) {
                                                                    //第五层级 全部勾选
                                                                    if(level1Item.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseFaterId().equals((level1Item.getSubItem(j).getSubItem(k).getItemFuseId()))){
                                                                        level1Item.getSubItem(j).getSubItem(k).getSubItem(l).setChecked(true);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }


                                                }
                                            }

                                            break;
                                    }
                                }
                            }else {
                                level0Item.setChecked(false);
                                //遍历第一层级 全部取消勾选  （需从第一层级开始遍历 原因是mData是展开后的数据）
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            //第一层级 取消勾选
                                            Level0Item level0Item0 = (Level0Item) mData.get(i);
                                            if(level0Item0.getItemFuseId().equals((level0Item.getItemFuseId()))){
                                                level0Item0.setChecked(false);
                                                //第二层级全部勾选
                                                if(level0Item0.hasSubItem()){
                                                    for (int j= 0; j < level0Item0.getSubItems().size(); j++) {
                                                        if(level0Item0.getSubItem(j).getItemFuseFaterId().equals((level0Item0.getItemFuseId()))){
                                                            level0Item0.getSubItem(j).setChecked(false);
                                                        }
                                                        //第三层级全部勾选
                                                        if(level0Item0.getSubItem(j).hasSubItem()){
                                                            for (int k = 0; k < level0Item0.getSubItem(j).getSubItems().size(); k++) {
                                                                if(level0Item0.getSubItem(j).getSubItem(k).getItemFuseFaterId().equals((level0Item0.getSubItem(j).getItemFuseId()))){
                                                                    level0Item0.getSubItem(j).getSubItem(k).setChecked(false);
                                                                }
                                                                //第四层级全部勾选
                                                                if(level0Item0.getSubItem(j).getSubItem(k).hasSubItem()){
                                                                    for (int l = 0; l < level0Item0.getSubItem(j).getSubItem(k).getSubItems().size(); l++) {
                                                                        if(level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseFaterId().equals((level0Item0.getSubItem(j).getSubItem(k).getItemFuseId()))){
                                                                            level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).setChecked(false);
                                                                        }
                                                                        //第五层级全部勾选
                                                                        if(level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).hasSubItem()){
                                                                            for (int m = 0; m < level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).getSubItems().size(); m++) {
                                                                                if(level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).getItemFuseFaterId().equals((level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseId()))){
                                                                                    level0Item0.getSubItem(j).getSubItem(k).getSubItem(l).getSubItem(m).setChecked(false);
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
                                            break;
                                    }
                                }
                            }
                            notyfyData();
                        }
                    });
                }



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
                holder.setVisible(R.id.iv,level1Item.hasSubItem()?true:false);
                holder.setText(R.id.check, level1Item.getItemFuseName());
                holder.setChecked(R.id.check,level1Item.isChecked()?true:false);
                final CheckBox check1 = holder.getView(R.id.check);
                if(level1Item.isCanClick()){
                    check1.setClickable(true);
                    check1.setEnabled(true);
                    check1.setTextColor(Color.parseColor("#333333"));
                }else {
                    check1.setClickable(false);
                    check1.setEnabled(false);
                    check1.setTextColor(Color.parseColor("#999999"));

                }
                if(level1Item.isCanClick()){
                    check1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check1.isChecked()){
                                level1Item.setChecked(true);
                                int pos = holder.getAdapterPosition();
                                if (level1Item.isExpanded()) {
                                } else {
                                    expand(pos, true);
                                }
                                //让第一层级勾选
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item = (Level0Item) mData.get(i);
                                            if(level0Item.getItemFuseId().equals(level1Item.getItemFuseFaterId())){
                                                level0Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }

                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item = (Level2Item) mData.get(i);
                                            //遍历第三层级 全部勾选
                                            if(level2Item.getItemFuseFaterId().equals((level1Item.getItemFuseId()))){
                                                level2Item.setChecked(true);
                                            }
                                            if(level2Item.hasSubItem()){
                                                //遍历第四层级 全部勾选
                                                for (int j = 0; j < level2Item.getSubItems().size(); j++) {

                                                    if(level2Item.getSubItem(j).getItemFuseFaterId().equals(level2Item.getItemFuseId())){
                                                        level2Item.getSubItem(j).setChecked(true);
                                                    }
                                                    if(level2Item.getSubItem(j).hasSubItem()){
                                                        //遍历第五层级 全部勾选
                                                        for (int k = 0; k < level2Item.getSubItem(j).getSubItems().size(); k++) {
                                                            if(level2Item.getSubItem(j).getSubItem(k).getItemFuseFaterId().equals(level2Item.getSubItem(j).getItemFuseId())){
                                                                level2Item.getSubItem(j).getSubItem(k).setChecked(true);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            }else {
                                level1Item.setChecked(false);
                                //遍历第二层级 全部取消勾选  （需从第二层级开始遍历 原因是mData是展开后的数据）
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            //第二层级 勾选
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(level1Item1.getItemFuseId().equals((level1Item.getItemFuseId()))){
                                                level1Item1.setChecked(false);
                                                //第三层级全部勾选
                                                if(level1Item1.hasSubItem()){
                                                    for (int j= 0; j < level1Item1.getSubItems().size(); j++) {
                                                        if(level1Item1.getSubItem(j).getItemFuseFaterId().equals((level1Item1.getItemFuseId()))){
                                                            level1Item1.getSubItem(j).setChecked(false);
                                                        }
                                                        //第四层级全部勾选
                                                        if(level1Item1.getSubItem(j).hasSubItem()){
                                                            for (int k = 0; k < level1Item1.getSubItem(j).getSubItems().size(); k++) {
                                                                if(level1Item1.getSubItem(j).getSubItem(k).getItemFuseFaterId().equals((level1Item1.getSubItem(j).getItemFuseId()))){
                                                                    level1Item1.getSubItem(j).getSubItem(k).setChecked(false);
                                                                }
                                                                //第五层级全部勾选
                                                                if(level1Item1.getSubItem(j).getSubItem(k).hasSubItem()){
                                                                    for (int l = 0; l < level1Item1.getSubItem(j).getSubItem(k).getSubItems().size(); l++) {
                                                                        if(level1Item1.getSubItem(j).getSubItem(k).getSubItem(l).getItemFuseFaterId().equals((level1Item1.getSubItem(j).getSubItem(k).getItemFuseId()))){
                                                                            level1Item1.getSubItem(j).getSubItem(k).getSubItem(l).setChecked(false);
                                                                        }
                                                                    }

                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                                String itemFuseId_1="";//第一层的itemFuseId
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_1){
                                        Level1Item level1Item1 = (Level1Item) mData.get(i);
                                        if(level1Item1.getItemFuseId().equals(level1Item.getItemFuseId())){
                                            itemFuseId_1=level1Item1.getItemFuseFaterId();
                                        }
                                    }
                                }
                                boolean isTwoAllNotSelect=true;//该点击所在的第二层都没有勾选
                                //检查该点击所在的第二层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(level1Item1.getItemFuseFaterId().equals(itemFuseId_1)){
                                                if(level1Item1.isChecked()){
                                                    isTwoAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item1 = (Level0Item) mData.get(i);
                                            if(isTwoAllNotSelect){
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }
                            }

                            notyfyData();
                        }
                    });
                }



                break;
            case TYPE_LEVEL_2:
                final Level2Item level2Item = (Level2Item) item;
                holder.setVisible(R.id.iv,level2Item.hasSubItem()?true:false);
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
                holder.setText(R.id.check, level2Item.getItemFuseName());
                holder.setChecked(R.id.check,level2Item.isChecked()?true:false);
               final CheckBox check2 = holder.getView(R.id.check);
                if(level2Item.isCanClick()){
                    check2.setClickable(true);
                    check2.setEnabled(true);
                    check2.setTextColor(Color.parseColor("#333333"));
                }else {
                    check2.setClickable(false);
                    check2.setEnabled(false);
                    check2.setTextColor(Color.parseColor("#999999"));

                }
                if(level2Item.isCanClick()){
                    check2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check2.isChecked()){
                                level2Item.setChecked(true);
                                int pos = holder.getAdapterPosition();
                                if (level2Item.isExpanded()) {
                                } else {
                                    expand(pos, true);
                                }
                                //让第一 二层级勾选
                                String id_1="";
                                //第二层级
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item = (Level1Item) mData.get(i);
                                            if(level1Item.getItemFuseId().equals(level2Item.getItemFuseFaterId())){
                                                level1Item.setChecked(true);
                                                id_1=level1Item.getItemFuseFaterId();
                                            }
                                            break;
                                    }
                                }
                                //第一层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item = (Level0Item) mData.get(i);
                                            if(level0Item.getItemFuseId().equals(id_1)){
                                                level0Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }



                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_3:
                                            Level3Item level3Item = (Level3Item) mData.get(i);
                                            //遍历第四层级 全部勾选
                                            if(level3Item.getItemFuseFaterId().equals((level2Item.getItemFuseId()))){
                                                level3Item.setChecked(true);
                                            }
                                            if(level3Item.hasSubItem()){
                                                //遍历第五层级 全部勾选
                                                for (int j = 0; j < level3Item.getSubItems().size(); j++) {
                                                    if(level3Item.getSubItem(j).getItemFuseFaterId().equals((level3Item.getItemFuseId()))){
                                                        level3Item.getSubItem(j).setChecked(true);
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            }else {
                                level2Item.setChecked(false);
                                //遍历第三层级 全部取消勾选  （需从第三层级开始遍历 原因是mData是展开后的数据）
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_2:
                                            //第三层级 勾选
                                            Level2Item level2Item2 = (Level2Item) mData.get(i);
                                            if(level2Item2.getItemFuseId().equals((level2Item.getItemFuseId()))){
                                                level2Item2.setChecked(false);
                                                //第四层级全部勾选
                                                if(level2Item2.hasSubItem()){
                                                    for (int j= 0; j < level2Item2.getSubItems().size(); j++) {
                                                        if(level2Item2.getSubItem(j).getItemFuseFaterId().equals((level2Item2.getItemFuseId()))){
                                                            level2Item2.getSubItem(j).setChecked(false);
                                                        }
                                                        //第五层级全部勾选
                                                        if(level2Item2.getSubItem(j).hasSubItem()){
                                                            for (int k = 0; k < level2Item2.getSubItem(j).getSubItems().size(); k++) {
                                                                if(level2Item2.getSubItem(j).getSubItem(k).getItemFuseFaterId().equals((level2Item2.getSubItem(j).getItemFuseId()))){
                                                                    level2Item2.getSubItem(j).getSubItem(k).setChecked(false);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }

                                String itemFuseId_2="";//第二层的itemFuseId
                                String itemFuseId_1="";//第一层的itemFuseId

                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_2){
                                        Level2Item level2Item2 = (Level2Item) mData.get(i);
                                        if(level2Item2.getItemFuseId().equals(level2Item.getItemFuseId())){
                                            itemFuseId_2=level2Item2.getItemFuseFaterId();
                                        }
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_1){
                                        Level1Item level1Item1 = (Level1Item) mData.get(i);
                                        if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                            itemFuseId_1=level1Item1.getItemFuseFaterId();
                                        }
                                    }
                                }
                                boolean isThreeAllNotSelect=true;//该点击所在的第三层都没有勾选
                                //检查该点击所在的第三层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item2 = (Level2Item) mData.get(i);
                                            if(level2Item2.getItemFuseFaterId().equals(itemFuseId_2)){
                                                if(level2Item2.isChecked()){
                                                    isThreeAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(isThreeAllNotSelect){
                                                if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                                    level1Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                                    level1Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }
                                boolean isTwoAllNotSelect=true;//该点击所在的第二层都没有勾选
                                //检查该点击所在的第二层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(level1Item1.getItemFuseFaterId().equals(itemFuseId_1)){
                                                if(level1Item1.isChecked()){
                                                    isTwoAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item1 = (Level0Item) mData.get(i);
                                            if(isTwoAllNotSelect){
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                            notyfyData();
                        }
                    });
                }

                break;
            case TYPE_LEVEL_3:
                final Level3Item level3Item = (Level3Item) item;
                holder.setText(R.id.check, level3Item.getItemFuseName());
                holder.setVisible(R.id.iv,level3Item.hasSubItem()?true:false);
                holder.setChecked(R.id.check,level3Item.isChecked()?true:false);
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
                final CheckBox check3 = holder.getView(R.id.check);
                if(level3Item.isCanClick()){
                    check3.setClickable(true);
                    check3.setEnabled(true);
                    check3.setTextColor(Color.parseColor("#333333"));
                }else {
                    check3.setClickable(false);
                    check3.setEnabled(false);
                    check3.setTextColor(Color.parseColor("#999999"));

                }
                if(level3Item.isCanClick()){
                    check3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check3.isChecked()){
                                level3Item.setChecked(true);
                                int pos = holder.getAdapterPosition();
                                if (level3Item.isExpanded()) {
                                } else {
                                    expand(pos, true);
                                }
                                //让第一 二 三 层级勾选
                                String id_1="";String id_2="";

                                //第三层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item = (Level2Item) mData.get(i);
                                            if(level2Item.getItemFuseId().equals(level3Item.getItemFuseFaterId())){
                                                level2Item.setChecked(true);
                                                id_2=level2Item.getItemFuseFaterId();
                                            }
                                            break;
                                    }
                                }

                                //第二层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item = (Level1Item) mData.get(i);
                                            if(level1Item.getItemFuseId().equals(id_2)){
                                                level1Item.setChecked(true);
                                                id_1=level1Item.getItemFuseFaterId();
                                            }
                                            break;
                                    }
                                }
                                //第一层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item = (Level0Item) mData.get(i);
                                            if(level0Item.getItemFuseId().equals(id_1)){
                                                level0Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_4:
                                            Level4Item level4Item = (Level4Item) mData.get(i);
                                            //遍历第五层级 全部勾选
                                            if(level4Item.getItemFuseFaterId().equals((level3Item.getItemFuseId()))){
                                                level4Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }
                            }else {
                                level3Item.setChecked(false);
                                //遍历第四层级 全部取消勾选  （需从四层级开始遍历 原因是mData是展开后的数据）
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_3:
                                            //第四层级 勾选
                                            Level3Item level3Item3 = (Level3Item) mData.get(i);
                                            if(level3Item3.getItemFuseId().equals((level3Item.getItemFuseId()))){
                                                level3Item3.setChecked(false);
                                                //第五层级全部勾选
                                                if(level3Item3.hasSubItem()){
                                                    for (int j= 0; j < level3Item3.getSubItems().size(); j++) {
                                                        if(level3Item3.getSubItem(j).getItemFuseFaterId().equals((level3Item3.getItemFuseId()))){
                                                            level3Item3.getSubItem(j).setChecked(false);
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                                String itemFuseId_3="";//第三层的itemFuseId
                                String itemFuseId_2="";//第二层的itemFuseId
                                String itemFuseId_1="";//第一层的itemFuseId


                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_3){
                                        Level3Item level3Item3 = (Level3Item) mData.get(i);
                                        if(level3Item3.getItemFuseId().equals(level3Item.getItemFuseId())){
                                            itemFuseId_3=level3Item3.getItemFuseFaterId();
                                        }
                                    }

                                }
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_2){
                                        Level2Item level2Item2 = (Level2Item) mData.get(i);
                                        if(level2Item2.getItemFuseId().equals(itemFuseId_3)){
                                            itemFuseId_2=level2Item2.getItemFuseFaterId();
                                        }
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_1){
                                        Level1Item level1Item1 = (Level1Item) mData.get(i);
                                        if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                            itemFuseId_1=level1Item1.getItemFuseFaterId();
                                        }
                                    }
                                }

                                boolean isForeAllNotSelect=true;//该点击所在的第四层都没有勾选
                                //检查该点击所在的第四层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_3:
                                            Level3Item level3Item3 = (Level3Item) mData.get(i);
                                            if(level3Item3.getItemFuseFaterId().equals(itemFuseId_3)){
                                                if(level3Item3.isChecked()){
                                                    isForeAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item2 = (Level2Item) mData.get(i);
                                            if(isForeAllNotSelect){
                                                if(level2Item2.getItemFuseId().equals(itemFuseId_3)){
                                                    level2Item2.setChecked(false);
                                                }
                                            }else {
                                                if(level2Item2.getItemFuseId().equals(itemFuseId_3)){
                                                    level2Item2.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }

                                boolean isThreeAllNotSelect=true;//该点击所在的第三层都没有勾选
                                //检查该点击所在的第三层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item2 = (Level2Item) mData.get(i);
                                            if(level2Item2.getItemFuseFaterId().equals(itemFuseId_2)){
                                                if(level2Item2.isChecked()){
                                                    isThreeAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(isThreeAllNotSelect){
                                                if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                                    level1Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                                    level1Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }
                                boolean isTwoAllNotSelect=true;//该点击所在的第二层都没有勾选
                                //检查该点击所在的第二层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(level1Item1.getItemFuseFaterId().equals(itemFuseId_1)){
                                                if(level1Item1.isChecked()){
                                                    isTwoAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item1 = (Level0Item) mData.get(i);
                                            if(isTwoAllNotSelect){
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }

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
                final CheckBox check4 = holder.getView(R.id.check);
                if(level4Item.isCanClick()){
                    check4.setClickable(true);
                    check4.setEnabled(true);
                    check4.setTextColor(Color.parseColor("#333333"));
                }else {
                    check4.setClickable(false);
                    check4.setEnabled(false);
                    check4.setTextColor(Color.parseColor("#999999"));

                }
                if(level4Item.isCanClick()){
                    check4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(check4.isChecked()){
                                level4Item.setChecked(true);
                                String id_1="";String id_2="";String id_3="";

                                //第四层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_3:
                                            Level3Item level3Item = (Level3Item) mData.get(i);
                                            if(level3Item.getItemFuseId().equals(level4Item.getItemFuseFaterId())){
                                                level3Item.setChecked(true);
                                                id_3=level3Item.getItemFuseFaterId();
                                            }
                                            break;
                                    }
                                }
                                //第三层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item = (Level2Item) mData.get(i);
                                            if(level2Item.getItemFuseId().equals(id_3)){
                                                level2Item.setChecked(true);
                                                id_2=level2Item.getItemFuseFaterId();
                                            }
                                            break;
                                    }
                                }

                                //第二层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item = (Level1Item) mData.get(i);
                                            if(level1Item.getItemFuseId().equals(id_2)){
                                                level1Item.setChecked(true);
                                                id_1=level1Item.getItemFuseFaterId();
                                            }
                                            break;
                                    }
                                }
                                //第一层
                                for (int i = 0; i <mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item = (Level0Item) mData.get(i);
                                            if(level0Item.getItemFuseId().equals(id_1)){
                                                level0Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }


                            }else {
                                level4Item.setChecked(false);
                                //遍历第五层级 全部取消勾选  （需从五层级开始遍历 原因是mData是展开后的数据）
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_4:
                                            //第五层级 勾选
                                            Level4Item level4Item3 = (Level4Item) mData.get(i);
                                            if(level4Item3.getItemFuseId().equals((level4Item.getItemFuseId()))){
                                                level4Item3.setChecked(false);
                                            }
                                            break;
                                    }
                                }

                                String itemFuseId_4="";//第四层的itemFuseId
                                String itemFuseId_3="";//第三层的itemFuseId
                                String itemFuseId_2="";//第二层的itemFuseId
                                String itemFuseId_1="";//第一层的itemFuseId

                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_4){
                                        Level4Item level4Item4 = (Level4Item) mData.get(i);
                                        if(level4Item4.getItemFuseFaterId().equals(level4Item.getItemFuseFaterId())){
                                            itemFuseId_4=level4Item4.getItemFuseFaterId();
                                        }
                                    }

                                }
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_3){
                                        Level3Item level3Item3 = (Level3Item) mData.get(i);
                                        if(level3Item3.getItemFuseId().equals(itemFuseId_4)){
                                            itemFuseId_3=level3Item3.getItemFuseFaterId();
                                        }
                                    }

                                }
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_2){
                                        Level2Item level2Item2 = (Level2Item) mData.get(i);
                                        if(level2Item2.getItemFuseId().equals(itemFuseId_3)){
                                            itemFuseId_2=level2Item2.getItemFuseFaterId();
                                        }
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++) {
                                    if(mData.get(i).getItemType()==TYPE_LEVEL_1){
                                        Level1Item level1Item1 = (Level1Item) mData.get(i);
                                        if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                            itemFuseId_1=level1Item1.getItemFuseFaterId();
                                        }
                                    }
                                }


                                boolean isFiveAllNotSelect=true;//该点击所在的第五层都没有勾选
                                //检查该点击所在的第五层是否有勾选的

                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_4:
                                            Level4Item level4Item4 = (Level4Item) mData.get(i);
                                            if(level4Item4.getItemFuseFaterId().equals(itemFuseId_4)){
                                                if(level4Item4.isChecked()){
                                                    isFiveAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_3:
                                            Level3Item level3Item3 = (Level3Item) mData.get(i);
                                            if(isFiveAllNotSelect){
                                                if(level3Item3.getItemFuseId().equals(itemFuseId_4)){
                                                    level3Item3.setChecked(false);
                                                }
                                            }else {
                                                if(level3Item3.getItemFuseId().equals(itemFuseId_4)){
                                                    level3Item3.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }

                                boolean isForeAllNotSelect=true;//该点击所在的第四层都没有勾选
                                //检查该点击所在的第四层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_3:
                                            Level3Item level3Item3 = (Level3Item) mData.get(i);
                                            if(level3Item3.getItemFuseFaterId().equals(itemFuseId_3)){
                                                if(level3Item3.isChecked()){
                                                    isForeAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item2 = (Level2Item) mData.get(i);
                                            if(isForeAllNotSelect){
                                                if(level2Item2.getItemFuseId().equals(itemFuseId_3)){
                                                    level2Item2.setChecked(false);
                                                }
                                            }else {
                                                if(level2Item2.getItemFuseId().equals(itemFuseId_3)){
                                                    level2Item2.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }

                                boolean isThreeAllNotSelect=true;//该点击所在的第三层都没有勾选
                                //检查该点击所在的第三层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_2:
                                            Level2Item level2Item2 = (Level2Item) mData.get(i);
                                            if(level2Item2.getItemFuseFaterId().equals(itemFuseId_2)){
                                                if(level2Item2.isChecked()){
                                                    isThreeAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(isThreeAllNotSelect){
                                                if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                                    level1Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level1Item1.getItemFuseId().equals(itemFuseId_2)){
                                                    level1Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }
                                boolean isTwoAllNotSelect=true;//该点击所在的第二层都没有勾选
                                //检查该点击所在的第二层是否有勾选的
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()){
                                        case TYPE_LEVEL_1:
                                            Level1Item level1Item1 = (Level1Item) mData.get(i);
                                            if(level1Item1.getItemFuseFaterId().equals(itemFuseId_1)){
                                                if(level1Item1.isChecked()){
                                                    isTwoAllNotSelect=false;
                                                }
                                            }
                                            break;
                                    }
                                }
                                for (int i = 0; i <mData.size(); i++){
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_0:
                                            Level0Item level0Item1 = (Level0Item) mData.get(i);
                                            if(isTwoAllNotSelect){
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(false);
                                                }
                                            }else {
                                                if(level0Item1.getItemFuseId().equals(itemFuseId_1)){
                                                    level0Item1.setChecked(true);
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                            notyfyData();
                        }
                    });
                }
                break;
        }
    }

    private void notyfyData(){
        try{
            notifyDataSetChanged();
        } catch (Exception e){

        }
    }
    public List<MultiItemEntity> returnData(){
        return this.mData;
    }
}
