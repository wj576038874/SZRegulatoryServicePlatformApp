package com.winfo.szrsp.app.widget.wheelview.lib;

import android.view.MotionEvent;

final class MinuteLoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final MinuteWheelView loopView;

    MinuteLoopViewGestureListener(MinuteWheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
