package com.winfo.szrsp.app.mvp.task.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.DateFormatUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.BadgeView;

import java.io.File;
import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.task.view.ExpandableItemAdapter.java
 * Date: 2017/12/12 10:21
 * Description:
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>{
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

    private Context mContext;
    public ExpandableItemAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_lv2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_lv3);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable_lv4);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case TYPE_LEVEL_0:
                final Level0Item level0Item = (Level0Item) item;
                holder.setText(R.id.title, level0Item.getItemFuseName()).setImageResource(R.id.iv, level0Item.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
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
                break;
            case TYPE_LEVEL_1:
                final Level1Item level1Item = (Level1Item) item;
                holder.setText(R.id.title, level1Item.getItemFuseName()).setImageResource(R.id.iv, level1Item.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.getView(R.id.edt_qxms).setTag(level1Item);
                TextView tv_title = holder.getView(R.id.title);
                ImageView iv = holder.getView(R.id.iv);
                ImageView iv_icon = holder.getView(R.id.img_icon);
                final ImageView iv_photo = holder.getView(R.id.img_photo);
                if(level1Item.getSubItems() ==null){
                    //现场巡航类不显示问题描述输入框
                    if (level1Item.getItemFuseId().startsWith("C_")){
                        holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
                    }else {
                        holder.getView(R.id.edt_qxms).setVisibility(View.VISIBLE);
                    }
                    iv.setVisibility(View.GONE);
                    iv_photo.setVisibility(View.VISIBLE);
                    tv_title.setTextColor(Color.parseColor("#3286fe"));
                    tv_title.setTextSize(14);
//                    iv_icon.setImageResource(R.mipmap.icon_task_acceptable);
                }else{
                    iv.setVisibility(View.VISIBLE);
                    iv_photo.setVisibility(View.GONE);
                    tv_title.setTextSize(16);
                    tv_title.setTextColor(Color.parseColor("#999999"));
                    holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
//                    iv_icon.setImageResource(R.mipmap.icon_task_unaccepted);
                }

                final EditText edt_qxms = holder.getView(R.id.edt_qxms);
                final int posint = holder.getLayoutPosition();
                iv_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onTakePhotoListener.photo(posint,iv_photo);
                    }
                });

//                if (level1Item.getNum() !=0 && level1Item.getSubItems() == null){
//                    BadgeView badgeView = new BadgeView(mContext,iv_photo);
//                    if (!badgeView.isShown()){
//                        badgeView.setText(level1Item.getNum()+"");
//                        badgeView.setTextSize(5);
//                        badgeView.show();
//                    }
//                }

                edt_qxms.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Level1Item Level1Item = (Level1Item) edt_qxms.getTag();
                        Level1Item.setInput(s + "");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                if (!TextUtils.isEmpty(level1Item.getInput())) {
                    holder.setText(R.id.edt_qxms, level1Item.getInput());
                } else {
                    holder.setText(R.id.edt_qxms, "");
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
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
                break;
            case TYPE_LEVEL_2:
                final Level2Item level2Item = (Level2Item) item;
                holder.setText(R.id.title, level2Item.getItemFuseName()).setImageResource(R.id.iv, level2Item.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.getView(R.id.edt_qxms).setTag(level2Item);
                TextView tv_title2 = holder.getView(R.id.title);
                ImageView iv2 = holder.getView(R.id.iv);
                ImageView iv_icon2 = holder.getView(R.id.img_icon);
                final ImageView iv_photo2 = holder.getView(R.id.img_photo);
                if(level2Item.getSubItems() ==null){
//                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    layout.setMargins(100, 0, 40, 0);
//                    iv_icon2.setLayoutParams(layout);
                    //现场巡航类不显示问题描述输入框
                    if (level2Item.getItemFuseId().startsWith("C_")){
                        holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
                    }else {
                        holder.getView(R.id.edt_qxms).setVisibility(View.VISIBLE);
                    }
                    iv2.setVisibility(View.GONE);
                    iv_photo2.setVisibility(View.VISIBLE);
                    tv_title2.setTextColor(Color.parseColor("#3286fe"));
                    tv_title2.setTextSize(14);
//                    iv_icon2.setImageResource(R.mipmap.icon_task_acceptable);
                }else{
                    iv2.setVisibility(View.VISIBLE);
                    iv_photo2.setVisibility(View.GONE);
                    tv_title2.setTextSize(16);
                    tv_title2.setTextColor(Color.parseColor("#999999"));
                    holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
//                    iv_icon2.setImageResource(R.mipmap.icon_task_unaccepted);
                }

                final EditText edt_qxms2 = holder.getView(R.id.edt_qxms);
                final int posint2 = holder.getLayoutPosition();
                iv_photo2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onTakePhotoListener.photo(posint2,iv_photo2);
                    }
                });

//                if (level1Item.getNum() !=0 && level1Item.getSubItems() == null){
//                    BadgeView badgeView = new BadgeView(mContext,iv_photo);
//                    if (!badgeView.isShown()){
//                        badgeView.setText(level1Item.getNum()+"");
//                        badgeView.setTextSize(5);
//                        badgeView.show();
//                    }
//                }

                edt_qxms2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Level2Item Level2Item = (Level2Item) edt_qxms2.getTag();
                        Level2Item.setInput(s + "");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                if (!TextUtils.isEmpty(level2Item.getInput())) {
                    holder.setText(R.id.edt_qxms, level2Item.getInput());
                } else {
                    holder.setText(R.id.edt_qxms, "");
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
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
                break;
            case TYPE_LEVEL_3:
                final Level3Item level3Item = (Level3Item) item;
                holder.setText(R.id.title, level3Item.getItemFuseName()).setImageResource(R.id.iv, level3Item.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.getView(R.id.edt_qxms).setTag(level3Item);
                TextView tv_title3 = holder.getView(R.id.title);
                ImageView iv3 = holder.getView(R.id.iv);
                ImageView iv_icon3 = holder.getView(R.id.img_icon);
                final ImageView iv_photo3 = holder.getView(R.id.img_photo);
                if(level3Item.getSubItems() ==null){
//                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    layout.setMargins(100, 0, 40, 0);
//                    iv_icon3.setLayoutParams(layout);
                    //现场巡航类不显示问题描述输入框
                    if (level3Item.getItemFuseId().startsWith("C_")){
                        holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
                    }else {
                        holder.getView(R.id.edt_qxms).setVisibility(View.VISIBLE);
                    }
                    iv3.setVisibility(View.GONE);
                    iv_photo3.setVisibility(View.VISIBLE);
                    tv_title3.setTextColor(Color.parseColor("#3286fe"));
                    tv_title3.setTextSize(14);
//                    iv_icon3.setImageResource(R.mipmap.icon_task_acceptable);
                }else{
                    iv3.setVisibility(View.VISIBLE);
                    iv_photo3.setVisibility(View.GONE);
                    tv_title3.setTextSize(16);
                    tv_title3.setTextColor(Color.parseColor("#999999"));
                    holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
//                    iv_icon3.setImageResource(R.mipmap.icon_task_unaccepted);
                }

                final EditText edt_qxms3 = holder.getView(R.id.edt_qxms);
                final int posint3 = holder.getLayoutPosition();
                iv_photo3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onTakePhotoListener.photo(posint3,iv_photo3);
                    }
                });

//                if (level1Item.getNum() !=0 && level1Item.getSubItems() == null){
//                    BadgeView badgeView = new BadgeView(mContext,iv_photo);
//                    if (!badgeView.isShown()){
//                        badgeView.setText(level1Item.getNum()+"");
//                        badgeView.setTextSize(5);
//                        badgeView.show();
//                    }
//                }

                edt_qxms3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Level3Item Level3Item = (Level3Item) edt_qxms3.getTag();
                        Level3Item.setInput(s + "");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                if (!TextUtils.isEmpty(level3Item.getInput())) {
                    holder.setText(R.id.edt_qxms, level3Item.getInput());
                } else {
                    holder.setText(R.id.edt_qxms, "");
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
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
                break;
            case TYPE_LEVEL_4:
                final Level4Item level4Item = (Level4Item) item;
                holder.setText(R.id.title, level4Item.getItemFuseName());
                holder.getView(R.id.edt_qxms).setTag(level4Item);
                TextView tv_title4 = holder.getView(R.id.title);
                ImageView iv4 = holder.getView(R.id.iv);
                ImageView iv_icon4 = holder.getView(R.id.img_icon);
                final ImageView iv_photo4 = holder.getView(R.id.img_photo);
//                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                layout.setMargins(100, 0, 40, 0);
//                iv_icon4.setLayoutParams(layout);
                if (level4Item.getItemFuseId().startsWith("C_")){
                    holder.getView(R.id.edt_qxms).setVisibility(View.GONE);
                }else {
                    holder.getView(R.id.edt_qxms).setVisibility(View.VISIBLE);
                }
                iv4.setVisibility(View.GONE);
                iv_photo4.setVisibility(View.VISIBLE);
                tv_title4.setTextColor(Color.parseColor("#3286fe"));
                tv_title4.setTextSize(14);
//                iv_icon4.setImageResource(R.mipmap.icon_task_acceptable);

                final EditText edt_qxms4 = holder.getView(R.id.edt_qxms);
                //现场巡航类不显示问题描述输入框
                final int posint4 = holder.getLayoutPosition();
                iv_photo4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onTakePhotoListener.photo(posint4,iv_photo4);
                    }
                });

//                if (level1Item.getNum() !=0 && level1Item.getSubItems() == null){
//                    BadgeView badgeView = new BadgeView(mContext,iv_photo);
//                    if (!badgeView.isShown()){
//                        badgeView.setText(level1Item.getNum()+"");
//                        badgeView.setTextSize(5);
//                        badgeView.show();
//                    }
//                }

                edt_qxms4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Level4Item Level4Item = (Level4Item) edt_qxms4.getTag();
                        Level4Item.setInput(s + "");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                if (!TextUtils.isEmpty(level4Item.getInput())) {
                    holder.setText(R.id.edt_qxms, level4Item.getInput());
                } else {
                    holder.setText(R.id.edt_qxms, "");
                }
                break;
        }
    }


    public interface OnTakePhotoListener{
        void photo(int pos,ImageView imageView);
    }

    public OnTakePhotoListener onTakePhotoListener;

    public void setOnTakePhotoListener(OnTakePhotoListener onTakePhotoListener) {
        this.onTakePhotoListener = onTakePhotoListener;
    }

}
