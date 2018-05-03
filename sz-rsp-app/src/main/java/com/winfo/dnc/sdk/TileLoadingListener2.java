package com.winfo.dnc.sdk;

import android.graphics.Bitmap;
import android.view.View;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 切片下载监听
 *
 * @author King
 */
class TileLoadingListener2 extends SimpleTarget<Bitmap> {
    private TileInfo tf;
    private WinfoDNCView winfoDNCView;
    private int layerNum;

    TileLoadingListener2(TileInfo tf, WinfoDNCView winfoDNCView, int layerNum) {
        this.tf = tf;
        this.layerNum = layerNum;
        this.winfoDNCView = winfoDNCView;
    }

    @Override
    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        if (layerNum == 0) {
            tf.bitmap = resource;
        } else if (layerNum == 1) {
            tf.bitmap2 = resource;
        }
        winfoDNCView.invalidate();
    }
}