package com.winfo.szrsp.app.adapter.feedback;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.mine.feedback.view.FeedBackLevelItem0;
import com.winfo.szrsp.app.mvp.mine.feedback.view.FeedBackLevelItem1;

import java.util.List;


public class FeedBackMultipleAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FeedBackMultipleAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.feedback_item_0);
        addItemType(TYPE_LEVEL_1, R.layout.feedback_item_1);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final FeedBackLevelItem0 lv0 = (FeedBackLevelItem0) item;
                LinearLayout feedback_ll_title =helper.getView(R.id.feedback_ll_title);
                View feedback_iv_line =helper.getView(R.id.feedback_iv_line);
                ImageView feedback_iv_close=helper.getView(R.id.feedback_iv_close);
                if(lv0.isExpanded()){
                    feedback_ll_title.setBackgroundResource(R.drawable.item_border_nobottom);
                    feedback_iv_line.setVisibility(View.VISIBLE);
                    feedback_iv_close.setImageResource(R.mipmap.close_up);
                }else {
                    feedback_ll_title.setBackgroundResource(R.drawable.item_border_normal);
                    feedback_iv_line.setVisibility(View.GONE);
                    feedback_iv_close.setImageResource(R.mipmap.close_down);
                }

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos,true,true);
                        } else {
                            expand(pos,true,true);
                        }
                    }

                });
                break;
            case TYPE_LEVEL_1:
                final FeedBackLevelItem1 lv1 = (FeedBackLevelItem1) item;
                RecyclerView mRecyclerView = helper.getView(R.id.feedback_rv_problem);
                FeedBackImgAdapter adapter=new FeedBackImgAdapter(lv1.getProblemImgUrlList());
                final GridLayoutManager manager = new GridLayoutManager(helper.itemView.getContext(), 8);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);

                RecyclerView mRecyclerView2 = helper.getView(R.id.feedback_rv_solution);
                FeedBackImgAdapter adapter2=new FeedBackImgAdapter(lv1.getSolutionImgUrlList());
                final GridLayoutManager manager2 = new GridLayoutManager(helper.itemView.getContext(), 8);
                mRecyclerView2.setLayoutManager(manager2);
                mRecyclerView2.setAdapter(adapter2);
                break;
        }
    }

}
