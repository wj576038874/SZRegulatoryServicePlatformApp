package com.winfo.szrsp.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.winfo.szrsp.app.R;

/**
 * 
 * @项目名: 	gdmsaec-app
 * @包名:	com.winfo.gdmsaec.app.view
 * @类名:	MyIconButton
 * @创建者:	yanfeijun
 * @创建时间:	2015-10-12	下午5:47:02 
 * @描述:	图文居中按钮控件
 * 
 * @svn版本:	$Rev: 8 $
 * @更新人:	$Author: qianmanman $
 * @更新时间:	$Date: 2017-10-17 15:34:09 +0800 (周二, 17 十月 2017) $
 * @更新描述:	TODO
 */
public class MyIconButton extends android.support.v7.widget.AppCompatButton {
    protected int drawableWidth;
    protected DrawablePositions drawablePosition;
    protected int iconPadding;

    Rect bounds;

    private enum DrawablePositions {
        NONE,
        LEFT_AND_RIGHT,
        LEFT,
        RIGHT
    }

    public MyIconButton(Context context) {
        super(context);
        bounds = new Rect();
    }

    public MyIconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        bounds = new Rect();
        applyAttributes(attrs);
    }

    protected void applyAttributes(AttributeSet attrs) {
        if (null == bounds) {
            bounds = new Rect();
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyIconButton);
        int paddingId = typedArray.getDimensionPixelSize(R.styleable.MyIconButton_iconPadding, 0);
        setIconPadding(paddingId);
        typedArray.recycle();
    }

    public void setIconPadding(int padding) {
        iconPadding = padding;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Paint textPaint = getPaint();
        String text = getText().toString();
        textPaint.getTextBounds(text, 0, text.length(), bounds);

        int textWidth = bounds.width();
        int factor = (drawablePosition == DrawablePositions.LEFT_AND_RIGHT) ? 2 : 1;
        int contentWidth = drawableWidth + iconPadding * factor + textWidth;
        int horizontalPadding = (int) ((getWidth() / 2.0) - (contentWidth / 2.0));

        setCompoundDrawablePadding(-horizontalPadding + iconPadding);

        switch (drawablePosition) {
            case LEFT:
                setPadding(horizontalPadding, getPaddingTop(), 0, getPaddingBottom());
                break;

            case RIGHT:
                setPadding(0, getPaddingTop(), horizontalPadding, getPaddingBottom());
                break;

            case LEFT_AND_RIGHT:
                setPadding(horizontalPadding, getPaddingTop(), horizontalPadding, getPaddingBottom());
                break;

            default:
                setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        if (left != null && right != null) {
            drawableWidth = left.getIntrinsicWidth() + right.getIntrinsicWidth();
            drawablePosition = DrawablePositions.LEFT_AND_RIGHT;
        } else if (left != null) {
            drawableWidth = left.getIntrinsicWidth();
            drawablePosition = DrawablePositions.LEFT;
        } else if (right != null) {
            drawableWidth = right.getIntrinsicWidth();
            drawablePosition = DrawablePositions.RIGHT;
        } else {
            drawablePosition = DrawablePositions.NONE;
        }

        requestLayout();
    }
}