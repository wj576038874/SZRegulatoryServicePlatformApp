package com.winfo.szrsp.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class DividerGridSpanSizeLookupItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    public DividerGridSpanSizeLookupItemDecoration(Context context, int drawableId) {
        mDivider = ContextCompat.getDrawable(context, drawableId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);

    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private List<Integer> s = new ArrayList<>();
    private int b = 0;

    private boolean isLastColum(RecyclerView parent, View view, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int a = layoutManager.getItemViewType(view);
            if (a == 1) {
                //标题
                if (!s.contains(pos)) {
                    s.add(pos);
                }
                return true;
            } else {
                switch (s.size()) {
                    case 1:
                        b = s.get(0);
                        break;
                    case 2:
                        if (pos > s.get(0) && pos < s.get(1)) {
                            b = s.get(0);
                        } else {
                            b = s.get(1);
                        }
                        break;
                    case 3:
                        if (pos > s.get(0) && pos < s.get(1)) {
                            b = s.get(0);
                        } else if (pos > s.get(1) && pos < s.get(2)) {
                            b = s.get(1);
                        } else {
                            b = s.get(2);
                        }
                        break;
                    case 4:
                        if (pos > s.get(0) && pos < s.get(1)) {//>0 <14
                            b = s.get(0);
                        } else if (pos > s.get(1) && pos < s.get(2)) {//>14  <19
                            b = s.get(1);
                        } else if (pos > s.get(2) && pos < s.get(3)) {//>19 <25
                            b = s.get(2);
                        } else {
                            b = s.get(3);
                        }
                        break;
                    case 5:
                        if (pos > s.get(0) && pos < s.get(1)) {//>0 <14
                            b = s.get(0);
                        } else if (pos > s.get(1) && pos < s.get(2)) {//>14  <19
                            b = s.get(1);
                        } else if (pos > s.get(2) && pos < s.get(3)) {//>19 <25
                            b = s.get(2);
                        } else if (pos > s.get(3) && pos < s.get(4)) {//>25 <41
                            b = s.get(3);
                        } else {
                            b = s.get(4);
                        }
                        break;
                }
                if ((pos - b) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
                    return true;
                }
            }

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, View view, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount) {// 如果是最后一行，则不需要绘制底部
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else {
                // StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

//    @Override
//    public void getItemOffsets(Rect outRect, int itemPosition,RecyclerView parent) {
//        int spanCount = getSpanCount(parent);
//        int childCount = parent.getAdapter().getItemCount();
//        if (isLastRaw(parent, itemPosition, spanCount, childCount)) {// 如果是最后一行，则不需要绘制底部
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//        } else if (isLastColum(parent, itemPosition, spanCount, childCount)) {// 如果是最后一列，则不需要绘制右边
//            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//        } else {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//        }
//    }

    private boolean isTitle(RecyclerView parent, View view) {
        return parent.getLayoutManager().getItemViewType(view) == 1;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, view, parent.getChildLayoutPosition(view), spanCount, childCount)) {// 如果是最后一行，则不需要绘制底部
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, view, parent.getChildLayoutPosition(view), spanCount, childCount)) {// 如果是最后一列，则不需要绘制右边
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else if(isTitle(parent , view)){
            outRect.set(0, 0, 0, 0);
        }else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }
    }
}
