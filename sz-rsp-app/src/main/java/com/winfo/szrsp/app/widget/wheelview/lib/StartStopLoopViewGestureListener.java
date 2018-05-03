package com.winfo.szrsp.app.widget.wheelview.lib;

import android.view.MotionEvent;

final class StartStopLoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final StartStopWheelView loopView;

    StartStopLoopViewGestureListener(StartStopWheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
