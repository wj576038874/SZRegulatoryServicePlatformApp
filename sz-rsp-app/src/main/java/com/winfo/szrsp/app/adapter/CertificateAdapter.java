package com.winfo.szrsp.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.winfo.szrsp.app.R;

import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.certificate.view.ImagePagerActivity;
import com.winfo.szrsp.app.mvp.certificate.view.PictureConfig;
import com.winfo.szrsp.app.mvp.certificate.view.PicturePreviewActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.LogUtil;
import com.winfo.szrsp.app.widget.smoothcheckbox.SmoothCheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.panpf.sketch.SketchImageView;


/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app
 * @Filename: CertificateAdapter
 * @Author: lsj
 * @Date: 2018/3/26  15:33
 * @Description:
 * @Version:
 */
public class CertificateAdapter extends CommonAdapter<CertificateDetatils> {
    private boolean isShowCheck=false;
    private  List<CertificateDetatils> mDatas;
    private  Context mContext;
    private List<String> list;
    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     */
    public CertificateAdapter(Context context, List<CertificateDetatils> datas, int resource) {
        super(context, datas, resource);
        mDatas=datas;
        mContext=context;
    }

    @Override
    public void convert(ViewHolder holder, final int position) {
        SketchImageView imageView = holder.getView(R.id.img_certificate);
        final SmoothCheckBox ck_del = holder.getView(R.id.ck_del);
        ck_del.setVisibility(View.VISIBLE);
        final CertificateDetatils certificateDetatil=mDatas.get(position);

        String access_token = ACache.get(mContext).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "";
        }
        String dataUrl=certificateDetatil.getPicture();
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("picture", dataUrl);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        //http://192.168.0.105:8081/MSA-RestService-APP-SZ/app/sz/fileUpload/outPutJpg
        // ?projectSu=SZMSA&requestSource=&parameterJson={%22picture%22:%22ais/413775384/ais/ceshi.jpg%22}
        // &accessToken=178E2E81-0EDE361D-795F-4FB1-88AC-EC02D16487A9
        String url= ServerReqAddress.BASE_ADDRESS +
                "sz/fileUpload/outPutJpg?projectSu=SZMSA&requestSource=&accessToken="+access_token+"&parameterJson="+params;
//        imageView.getOptions()
//                .setMaxSize(800, 400)
//                .setResize(800, 400)
//                .setThumbnailMode(true);
//        imageView.displayImage(url);

        Glide.with(SzRspApplication.getContext()).load(url)
                .apply(new RequestOptions().error(R.mipmap.failed)
                        .centerCrop()
                        .placeholder(R.mipmap.failed)
                        .error(R.mipmap.failed)
                ).into(imageView);

        ck_del.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                    certificateDetatil.setSelect(true);
                }else {
                    certificateDetatil.setSelect(false);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isShowCheck){
                    if(certificateDetatil.isSelect()){
                        certificateDetatil.setSelect(false);
                        ck_del.setChecked(false,true);
                    }else {
                        certificateDetatil.setSelect(true);
                        ck_del.setChecked(true,true);
                    }
                }else {
//                    Intent intent12 = new Intent(mContext,PicturePreviewActivity.class);
//                    Bundle bundle12 = new Bundle();
//                    bundle12.putSerializable("certificatedata",certificateDetatil);
//                    intent12.putExtras(bundle12);
//                    mContext.startActivity(intent12);
                    list=new ArrayList<>();
                    for (int i = 0; i < mDatas.size(); i++) {
                        String access_token = ACache.get(mContext).getAsString("access_token");
                        if (access_token == null || access_token.length() == 0) {
                            access_token = "";
                        }
                        String dataUrl=mDatas.get(i).getPicture();
                        Map<String, String> mapParams = new HashMap<>();
                        mapParams.put("picture", dataUrl);
                        String params = FastJsonUtil.beanToJsonStr(mapParams);
                        String url= ServerReqAddress.BASE_ADDRESS +
                                "sz/fileUpload/outPutJpg?projectSu=SZMSA&requestSource=&accessToken="+access_token+"&parameterJson="+params;
                        list.add(url);

                    }
                   

                    PictureConfig config = new PictureConfig.Builder()
                            .setListData((ArrayList<String>) list)//图片数据List<String> list
                            .setPosition(position)//图片下标（从第position张图片开始浏览）
                            .setDownloadPath("pictureviewer")//图片下载文件夹地址
                            .setIsShowNumber(true)//是否显示数字下标
                            .needDownload(true)//是否支持图片下载
                            .setPlacrHolder(R.mipmap.failed)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                            .build();
                    ImagePagerActivity.startActivity(mContext, config);
                }



            }
        });
        if (isShowCheck){
            ck_del.setVisibility(View.VISIBLE);
            ck_del.setChecked(certificateDetatil.isSelect());
        }else {
            ck_del.setVisibility(View.GONE);
        }
    }

    public void setIsShowCheck(boolean isShowCheck){
        this.isShowCheck=isShowCheck;
        notyfyData();
    }
    private void notyfyData(){
        try{
            notifyDataSetChanged();
        } catch (Exception e){
            LogUtil.e(e.toString());
        }
    }
    public void setDatasAllNoCheck(){
        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).setSelect(false);
        }
//        notyfyData();
    }

    public List<CertificateDetatils> getDatas(){
       return mDatas;
    }
}
