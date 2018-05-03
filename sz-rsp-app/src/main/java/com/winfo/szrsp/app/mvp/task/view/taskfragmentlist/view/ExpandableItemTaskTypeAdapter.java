package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.mvp.task.view.Level2Item;

import java.util.List;

/**
 * Created by winfo053 on 2018/3/7.
 */

public class ExpandableItemTaskTypeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private Context mContext;
    private List<MultiItemEntity> mData;
    private boolean isShipType=false;

    private boolean isGKGJDJCCheck;//船舶类 港口国监督检查表是否被选中
    private boolean isGKGJDJCCanCheck=true;//船舶类 港口国监督检查表是否能点击选择


    private boolean isKXCheck;//船舶类 开箱检查检查表是否被选中
    private boolean isKXCanCheck=true;//船舶类 开箱检查检查表是否能点击选择

    private boolean isXCJCCheck;//船舶类 现场检查是否被选中
    private boolean isXCJCCanCheck=true;//船舶类 现场检查是否能点击选择

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemTaskTypeAdapter(List<MultiItemEntity> data , Context context) {
        super(data);
        this.mContext = context;
        this.mData=data;
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_task_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_task_lv1);
        for (int i = 0; i <data.size() ; i++) {
            Level0ItemTaskType level0Item = (Level0ItemTaskType) data.get(i);
            if(level0Item.getId().equals("RS")){
                isShipType=true;
            }
        }
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case TYPE_LEVEL_0:
                final Level0ItemTaskType level0Item = (Level0ItemTaskType) item;
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
                final CheckBox checkBox_0 = holder.getView(R.id.check);
                checkBox_0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkBox_0.isChecked()){
                            level0Item.setChecked(true);
                            int pos = holder.getAdapterPosition();
                            if (level0Item.isExpanded()) {
                            } else {
                                expand(pos, true);
                            }

                            //是否是非船舶类
                            if(level0Item.getId().equals("RO")){
                                //勾选 水上巡航 电子巡航 巡航工作统计
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1ItemTaskType level1Item = (Level1ItemTaskType) mData.get(i);
                                            if(level1Item.getId().equals("RO_00000001")||level1Item.getId().equals("RO_00000002")||level1Item.getId().equals("RO_00000003")){
                                                level1Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }

                            }else {
                                //遍历第二层级 全部勾选
                                for (int i = 0; i < mData.size(); i++) {
                                    switch (mData.get(i).getItemType()) {
                                        case TYPE_LEVEL_1:
                                            Level1ItemTaskType level1Item = (Level1ItemTaskType) mData.get(i);
                                            if(level1Item.getPid().equals((level0Item.getId()))){
                                                level1Item.setChecked(true);
                                            }
                                            break;
                                    }
                                }
                            }


                        }else {
                            level0Item.setChecked(false);
                            //遍历第一层级 全部取消勾选  （需从第一层级开始遍历 原因是mData是展开后的数据）
                            for (int i = 0; i < mData.size(); i++) {
                                switch (mData.get(i).getItemType()) {
                                    case TYPE_LEVEL_0:
                                        //第一层级 取消勾选
                                        Level0ItemTaskType level0Item0 = (Level0ItemTaskType) mData.get(i);
                                        if(level0Item0.getId().equals((level0Item.getId()))){
                                            level0Item0.setChecked(false);
                                            //第二层级全部勾选
                                            if(level0Item0.hasSubItem()){
                                                for (int j= 0; j < level0Item0.getSubItems().size(); j++) {
                                                    if(level0Item0.getSubItem(j).getPid().equals((level0Item0.getId()))){
                                                        level0Item0.getSubItem(j).setChecked(false);
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        onCheckChangListener.setData(mData);
                        notyfyData();
                    }
                });
                holder.setChecked(R.id.check,level0Item.isChecked());
                //船舶类第一层级没有checkbox
                if(level0Item.getId().equals("RS")){
                    holder.setGone(R.id.title, true);
                    holder.setText(R.id.title, level0Item.getName());
                    holder.setGone(R.id.check ,false);
                }else {
                    holder.setGone(R.id.title, false);
                    holder.setGone(R.id.check ,true);
                    holder.setText(R.id.check, level0Item.getName());
                }
//                //其他任务第一层级没有展开
//                if(level0Item.getId().equals("RO")){
//                    holder.setVisible(R.id.iv ,false);
//                }else {
//                    holder.setVisible(R.id.iv, true);
//                }

//                if(isShipType){
//                    //船舶类 下其他不可点击
//                    if(level0Item.getId().equals("RO")){
//                        level0Item.setChecked("0");
//                    }
//                }else {
//                    //非船舶类 下其他可点击
//                    if(level0Item.getId().equals("RO")){
//                        level0Item.setChecked("1");
//                    }
//                    //非船舶类 下非船舶可点击
//                    if(level0Item.getId().equals("RC")){
//                        level0Item.setChecked("1");
//                    }
//                }

                if(level0Item.getChecked().equals("1")){
                    holder.getView(R.id.check).setClickable(true);
                    holder.getView(R.id.check).setEnabled(true);
                    holder.setTextColor(R.id.title,Color.parseColor("#333333"));
                    holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                }else {
                    holder.getView(R.id.check).setClickable(false);
                    holder.getView(R.id.check).setEnabled(false);
                    holder.setTextColor(R.id.title,Color.parseColor("#999999"));
                    holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                }
                if(isShipType){
                    //整体是船舶类
                    // 应急类和巡航类均不可点击选择
//                    level0Item.getId().equals("RE")||level0Item.getId().equals("RO")
                    if(level0Item.getId().equals("RE")||level0Item.getId().equals("RO")){
                        holder.getView(R.id.check).setClickable(false);
                        holder.getView(R.id.check).setEnabled(false);
                        holder.setTextColor(R.id.title,Color.parseColor("#999999"));
                        holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                    }else {
                        holder.getView(R.id.check).setClickable(true);
                        holder.getView(R.id.check).setEnabled(true);
                        holder.setTextColor(R.id.title,Color.parseColor("#333333"));
                        holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                    }
                }else {
                    //整体是非船舶类
                    //应急类和非船舶类均不可点击选择
//                    level0Item.getId().equals("RC")||level0Item.getId().equals("RE")
                    if(level0Item.getId().equals("RC")||level0Item.getId().equals("RE")){
                        holder.getView(R.id.check).setClickable(false);
                        holder.getView(R.id.check).setEnabled(false);
                        holder.setTextColor(R.id.title,Color.parseColor("#999999"));
                        holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                    }else {
                        holder.getView(R.id.check).setClickable(true);
                        holder.getView(R.id.check).setEnabled(true);
                        holder.setTextColor(R.id.title,Color.parseColor("#333333"));
                        holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                    }
                }
                break;
            case TYPE_LEVEL_1:
                final Level1ItemTaskType level1Item = (Level1ItemTaskType) item;
                holder.setText(R.id.check, level1Item.getName());
                final CheckBox checkBox_1 = holder.getView(R.id.check);
//                if(level1Item.getId().equals("RS_00000006")||level1Item.getId().equals("RS_00000009")){
//                    level1Item.setChecked("1");
//                }

//                if(isShipType){
//
//                }else {
//                    //非船舶类 水上巡航 电子巡航 巡航工作统计可点击
//                    if(level1Item.getId().equals("RO_00000001")||level1Item.getId().equals("RO_00000002")||level1Item.getId().equals("RO_00000003")){
//                        level1Item.setChecked("1");
//                    }
//
//                }
                checkBox_1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(isShipType){
                           if(checkBox_1.isChecked()){
                               level1Item.setChecked(true);
                               //船舶类下的船舶表格选择
                               if(level1Item.getId().startsWith("RS")){
                                   if(level1Item.getId().equals("RS_00000003")){
                                       //港口国监督检查表选中 其他均不可以被选中
                                       isGKGJDJCCheck=true;
                                       isGKGJDJCCanCheck=true;
                                       isKXCheck=false;
                                       isKXCanCheck=false;
                                       isXCJCCheck=false;
                                       isXCJCCanCheck=false;
                                       for (int i = 0; i < mData.size(); i++) {
                                           switch (mData.get(i).getItemType()) {
                                               case TYPE_LEVEL_1:
                                                   Level1ItemTaskType level1Item = (Level1ItemTaskType) mData.get(i);
                                                   if( !level1Item.getId().equals("RS_00000003")){
                                                       level1Item.setChecked(false);
                                                   }
                                                   break;
                                           }
                                       }
                                   }else if(level1Item.getId().equals("RS_00000006")){
                                       //开箱检查选中 其他均不可以被选中
                                       isGKGJDJCCheck=false;
                                       isGKGJDJCCanCheck=false;
                                       isKXCheck=true;
                                       isKXCanCheck=true;
                                       isXCJCCheck=false;
                                       isXCJCCanCheck=false;
                                       for (int i = 0; i < mData.size(); i++) {
                                           switch (mData.get(i).getItemType()) {
                                               case TYPE_LEVEL_1:
                                                   Level1ItemTaskType level1Item = (Level1ItemTaskType) mData.get(i);
                                                   if( !level1Item.getId().equals("RS_00000006")){
                                                       level1Item.setChecked(false);
                                                   }
                                                   break;
                                           }
                                       }

                                   }else if(level1Item.getId().equals("RS_00000011")){
                                       //开箱检查选中 其他均不可以被选中
                                       isGKGJDJCCheck=false;
                                       isGKGJDJCCanCheck=false;
                                       isKXCheck=false;
                                       isKXCanCheck=false;
                                       isXCJCCheck=true;
                                       isXCJCCanCheck=true;
                                       for (int i = 0; i < mData.size(); i++) {
                                           switch (mData.get(i).getItemType()) {
                                               case TYPE_LEVEL_1:
                                                   Level1ItemTaskType level1Item = (Level1ItemTaskType) mData.get(i);
                                                   if( !level1Item.getId().equals("RS_00000011")){
                                                       level1Item.setChecked(false);
                                                   }
                                                   break;
                                           }
                                       }

                                   } else {
                                       //其他选中（开箱检查和港口国监督检查表未选中 开箱检查和港口国监督检查表不可点击选中）
                                       if(level1Item.getPid().equals("RS")){
                                           isGKGJDJCCanCheck=false;
                                           isGKGJDJCCheck=false;
                                           isKXCheck=false;
                                           isKXCanCheck=false;
                                           isXCJCCheck=false;
                                           isXCJCCanCheck=false;
                                       }
                                   }
                               }else {
                                   for (int i = 0; i < mData.size(); i++) {
                                       switch (mData.get(i).getItemType()) {
                                           case TYPE_LEVEL_0:
                                               Level0ItemTaskType level0Item = (Level0ItemTaskType) mData.get(i);
                                               if(level1Item.getPid().equals((level0Item.getId()))){
                                                   if(!level0Item.isChecked()){
                                                       level0Item.setChecked(true);
                                                   }
                                               }
                                               break;
                                       }
                                   }
                               }

                           }else {
                               level1Item.setChecked(false);
                               if(level1Item.getId().startsWith("RS")){
                                   if(level1Item.getId().equals("RS_00000003")){
                                       //港口国监督检查表取消选中 其他表可点击选中
                                       isGKGJDJCCheck=false;
                                       isKXCanCheck=true;
                                       isGKGJDJCCanCheck=true;
                                       isXCJCCanCheck=true;
                                   }else if(level1Item.getId().equals("RS_00000006")){
                                       //开箱取消选中 其他表可点击选中
                                       isKXCheck=false;
                                       isKXCanCheck=true;
                                       isGKGJDJCCanCheck=true;
                                       isXCJCCanCheck=true;
                                   }else if(level1Item.getId().equals("RS_00000011")){
                                       //现场检查取消选中 其他表可点击选中
                                       isXCJCCheck=false;
                                       isKXCanCheck=true;
                                       isGKGJDJCCanCheck=true;
                                       isXCJCCanCheck=true;
                                   }else {
                                       //其他表取消选中 当所有都取消选中时港口国监督检查表才能点击选中
                                       boolean RS_00000005=false;
                                       boolean RS_00000009=false;
                                       boolean RS_00000002=false;
                                       boolean RS_00000001=false;
//                                       boolean RS_00000011=false;
                                       for (int i = 0; i < mData.size(); i++) {
                                           switch (mData.get(i).getItemType()) {
                                               case TYPE_LEVEL_1:
                                                   Level1ItemTaskType level1Item = (Level1ItemTaskType) mData.get(i);
                                                   if( level1Item.getId().equals("RS_00000005")){
                                                       if(level1Item.isChecked()){
                                                           RS_00000005=true;
                                                       }else {
                                                           RS_00000005=false;
                                                       }
                                                   }
                                                   if( level1Item.getId().equals("RS_00000009")){
                                                       if(level1Item.isChecked()){
                                                           RS_00000009=true;
                                                       }else {
                                                           RS_00000009=false;
                                                       }
                                                   }
                                                   if( level1Item.getId().equals("RS_00000002")){
                                                       if(level1Item.isChecked()){
                                                           RS_00000002=true;
                                                       }else {
                                                           RS_00000002=false;
                                                       }
                                                   }
                                                   if( level1Item.getId().equals("RS_00000001")){
                                                       if(level1Item.isChecked()){
                                                           RS_00000001=true;
                                                       }else {
                                                           RS_00000001=false;
                                                       }
                                                   }
//                                                   if( level1Item.getId().equals("RS_00000011")){
//                                                       if(level1Item.isChecked()){
//                                                           RS_00000011=true;
//                                                       }else {
//                                                           RS_00000011=false;
//                                                       }
//                                                   }
                                                   break;
                                           }
                                       }
                                       if(!RS_00000001&&!RS_00000002&&!RS_00000009&&!RS_00000005){
                                           isGKGJDJCCheck=false;
                                           isGKGJDJCCanCheck=true;
                                           isKXCheck=false;
                                           isKXCanCheck=true;
                                           isXCJCCheck=false;
                                           isXCJCCanCheck=true;

                                       }else {
                                           isGKGJDJCCheck=false;
                                           isGKGJDJCCanCheck=false;

                                           isKXCheck=false;
                                           isKXCanCheck=false;

                                           isXCJCCheck=false;
                                           isXCJCCanCheck=false;
                                       }
                                   }
                               }else {
                                   boolean isAllNotSelect=true;
                                   //如果都没勾选，将第一层级取消勾选
                                   for (int i = 0; i < mData.size(); i++) {
                                       switch (mData.get(i).getItemType()){
                                           case TYPE_LEVEL_0:
                                               Level0ItemTaskType level0Item = (Level0ItemTaskType) mData.get(i);
                                               for (int j = 0; j < level0Item.getSubItems().size(); j++) {
                                                   if(level0Item.getSubItems().get(j).isChecked()){
                                                       isAllNotSelect=false;
                                                       break;
                                                   }else {
                                                       isAllNotSelect=true;
                                                   }
                                               }
                                               if(isAllNotSelect){
                                                   level0Item.setChecked(false);
                                               }else {
                                                   level0Item.setChecked(true);
                                               }
                                               break;
                                       }
                                   }
                               }
                           }
                       }else {
                           if(checkBox_1.isChecked()){
                               level1Item.setChecked(true);
                               //勾选了一个将第一层级勾选
                               for (int i = 0; i < mData.size(); i++) {
                                   switch (mData.get(i).getItemType()) {
                                       case TYPE_LEVEL_0:
                                           Level0ItemTaskType level0Item = (Level0ItemTaskType) mData.get(i);
                                           if(level1Item.getPid().equals((level0Item.getId()))){
                                               if(!level0Item.isChecked()){
                                                   level0Item.setChecked(true);
                                               }
                                           }
                                           break;
                                   }
                               }
                           }else {
                               level1Item.setChecked(false);

                               boolean isAllNotSelect=true;
                               //如果都没勾选，将第一层级取消勾选
                               for (int i = 0; i < mData.size(); i++) {
                                   switch (mData.get(i).getItemType()){
                                       case TYPE_LEVEL_0:
                                           Level0ItemTaskType level0Item = (Level0ItemTaskType) mData.get(i);
                                           if(level0Item.hasSubItem()){
                                               for (int j = 0; j < level0Item.getSubItems().size(); j++) {
                                                   if(level0Item.getSubItems().get(j).isChecked()){
                                                       isAllNotSelect=false;
                                                       break;
                                                   }else {
                                                       isAllNotSelect=true;
                                                   }
                                               }
                                               if(isAllNotSelect){
                                                   level0Item.setChecked(false);
                                               }else {
                                                   level0Item.setChecked(true);
                                               }
                                           }
                                           break;
                                   }
                               }
                           }
                       }
                       onCheckChangListener.setData(mData);
                       notyfyData();

                   }
               });
                holder.setChecked(R.id.check,level1Item.isChecked());

                if(level1Item.getChecked().equals("1")){
                    holder.getView(R.id.check).setClickable(true);
                    holder.getView(R.id.check).setEnabled(true);
                    holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                }else {
                    holder.getView(R.id.check).setClickable(false);
                    holder.getView(R.id.check).setEnabled(true);
                    holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                }



                if(isShipType){
                    //船舶类
                    if(level1Item.getId().startsWith("RS")){
                        if(isGKGJDJCCheck){
                            if(level1Item.getId().equals("RS_00000003")){
                                holder.getView(R.id.check).setClickable(true);
                                holder.getView(R.id.check).setEnabled(true);
                                holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                            }  else {
                                holder.getView(R.id.check).setClickable(false);
                                holder.getView(R.id.check).setEnabled(false);
                                holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                            }
                        }else {
                            if(isGKGJDJCCanCheck){
                                if(level1Item.getId().equals("RS_00000003")){
                                    holder.getView(R.id.check).setClickable(true);
                                    holder.getView(R.id.check).setEnabled(true);
                                    holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                                }
                            }else {
                                if(level1Item.getId().equals("RS_00000003")){
                                    holder.getView(R.id.check).setClickable(false);
                                    holder.getView(R.id.check).setEnabled(false);
                                    holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                                }
                            }
                        }
                        if(isKXCheck){
                            if(level1Item.getId().equals("RS_00000006")){
                                holder.getView(R.id.check).setClickable(true);
                                holder.getView(R.id.check).setEnabled(true);
                                holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                            }  else {
                                holder.getView(R.id.check).setClickable(false);
                                holder.getView(R.id.check).setEnabled(false);
                                holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                            }
                        }else {
                            if(isKXCanCheck){
                                if(level1Item.getId().equals("RS_00000006")){
                                    holder.getView(R.id.check).setClickable(true);
                                    holder.getView(R.id.check).setEnabled(true);
                                    holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                                }
                            }else {
                                if(level1Item.getId().equals("RS_00000006")){
                                    holder.getView(R.id.check).setClickable(false);
                                    holder.getView(R.id.check).setEnabled(false);
                                    holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                                }
                            }
                        }
                        if(isXCJCCheck){
                            if(level1Item.getId().equals("RS_00000011")){
                                holder.getView(R.id.check).setClickable(true);
                                holder.getView(R.id.check).setEnabled(true);
                                holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                            }  else {
                                holder.getView(R.id.check).setClickable(false);
                                holder.getView(R.id.check).setEnabled(false);
                                holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                            }
                        }else {
                            if(isXCJCCanCheck){
                                if(level1Item.getId().equals("RS_00000011")){
                                    holder.getView(R.id.check).setClickable(true);
                                    holder.getView(R.id.check).setEnabled(true);
                                    holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                                }
                            }else {
                                if(level1Item.getId().equals("RS_00000011")){
                                    holder.getView(R.id.check).setClickable(false);
                                    holder.getView(R.id.check).setEnabled(false);
                                    holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                                }
                            }
                        }
                    }else {
//                        level1Item.getId().contains("RE")||level1Item.getId().contains("RO")
                        if(level1Item.getId().contains("RE")||level1Item.getId().contains("RO")){
                            holder.getView(R.id.check).setClickable(false);
                            holder.getView(R.id.check).setEnabled(false);
                            holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                        }else {
                            holder.getView(R.id.check).setClickable(true);
                            holder.getView(R.id.check).setEnabled(true);
                            holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                        }
                    }
                }else {
//                    level1Item.getId().contains("RE")||level1Item.getId().contains("RC")
                    if(level1Item.getId().contains("RE")||level1Item.getId().contains("RC")){
                        holder.getView(R.id.check).setClickable(false);
                        holder.getView(R.id.check).setEnabled(false);
                        holder.setTextColor(R.id.check,Color.parseColor("#999999"));
                    }else {
                        holder.getView(R.id.check).setClickable(true);
                        holder.getView(R.id.check).setEnabled(true);
                        holder.setTextColor(R.id.check,Color.parseColor("#333333"));
                    }
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

    public interface OnCheckChangListener{
        void setData(List<MultiItemEntity> data);
    }
    public OnCheckChangListener onCheckChangListener;
    public void  setOnCheckChangListener( OnCheckChangListener onCheckChangListener){
        this.onCheckChangListener=onCheckChangListener;
    }
}
