package com.winfo.szrsp.app.widget.wheelview.lib;

final class StartStopOnItemSelectedRunnable implements Runnable {
    final StartStopWheelView loopView;

    StartStopOnItemSelectedRunnable(StartStopWheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
