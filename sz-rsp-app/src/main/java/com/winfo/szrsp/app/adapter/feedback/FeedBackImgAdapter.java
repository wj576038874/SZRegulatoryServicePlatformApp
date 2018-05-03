package com.winfo.szrsp.app.adapter.feedback;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.certificate.view.ImagePagerActivity;
import com.winfo.szrsp.app.mvp.certificate.view.PictureConfig;
import java.util.ArrayList;
import java.util.List;


public class FeedBackImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private  List<String> mDatas;
    private List<String> list;
    FeedBackImgAdapter(@Nullable List<String> data) {
        super(R.layout.item_img_view, data);
        mDatas=data;
    }

    @Override
    protected void convert(final BaseViewHolder holder, String item) {
        ImageView imageView = holder.getView(R.id.iv);
        Glide.with(SzRspApplication.getContext()).load(item)
                .apply(new RequestOptions().error(R.mipmap.failed)
                        .fitCenter()
                        .placeholder(R.mipmap.failed)
                        .error(R.mipmap.failed)
                ).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    list=new ArrayList<>();
                    list.addAll(mDatas);
                    PictureConfig config = new PictureConfig.Builder()
                            .setListData((ArrayList<String>) list)//图片数据List<String> list
                            .setPosition(holder.getAdapterPosition())//图片下标（从第position张图片开始浏览）
                            .setDownloadPath("pictureviewer")//图片下载文件夹地址
                            .setIsShowNumber(true)//是否显示数字下标
                            .needDownload(true)//是否支持图片下载
                            .setPlacrHolder(R.mipmap.failed)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                            .build();
                    ImagePagerActivity.startActivity(mContext, config);
                }
        });
    }
}
