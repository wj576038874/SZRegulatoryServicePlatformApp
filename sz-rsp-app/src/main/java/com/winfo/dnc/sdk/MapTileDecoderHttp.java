package com.winfo.dnc.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.winfo.szrsp.app.R;

class MapTileDecoderHttp {
    static ImageLoader loader = ImageLoader.getInstance();

    static void decode(String TilePath, ImageLoadingListener listener) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        loader.loadImage(TilePath, options, listener);
    }

    private static RequestOptions options = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.load_bg);

    static void loadBitmap(Context context , String tilePath, SimpleTarget<Bitmap> listener){
        Glide.with(context).asBitmap().apply(options).load(tilePath).into(listener);
    }
}
