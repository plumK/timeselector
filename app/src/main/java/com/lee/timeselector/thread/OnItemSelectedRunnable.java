package com.lee.timeselector.thread;


import com.lee.timeselector.view.WheelView;

public final class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    public OnItemSelectedRunnable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
