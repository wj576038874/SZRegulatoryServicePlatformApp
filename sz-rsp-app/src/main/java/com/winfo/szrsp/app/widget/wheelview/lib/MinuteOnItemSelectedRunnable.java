package com.winfo.szrsp.app.widget.wheelview.lib;

final class MinuteOnItemSelectedRunnable implements Runnable {
    final MinuteWheelView loopView;

    MinuteOnItemSelectedRunnable(MinuteWheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
