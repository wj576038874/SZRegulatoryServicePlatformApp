package com.winfo.szrsp.app.mvp.certificate.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;

import java.util.HashMap;
import java.util.Map;

import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.decode.ImageAttrs;
import me.panpf.sketch.request.CancelCause;
import me.panpf.sketch.request.DisplayListener;
import me.panpf.sketch.request.ErrorCause;
import me.panpf.sketch.request.ImageFrom;

public class PicturePreviewActivity extends Activity {
    private ImageButton img_back;
    private CertificateDetatils certificateDetatil;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        img_back=findViewById(R.id.table_titleBar_imgbtn_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        certificateDetatil=(CertificateDetatils) getIntent().getSerializableExtra("certificatedata");
        String access_token = ACache.get(this).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "";
        }
        String dataUrl=certificateDetatil.getPicture();
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("picture", dataUrl);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        String url= ServerReqAddress.BASE_ADDRESS +
                "sz/fileUpload/outPutJpg?projectSu=SZMSA&requestSource=&accessToken="+access_token+"&parameterJson="+params;

        SketchImageView sketchImageView = (SketchImageView) findViewById(R.id.image_main_background);
        sketchImageView.setZoomEnabled(true);
        sketchImageView.setShowDownloadProgressEnabled(true);
        sketchImageView.getZoomer().zoom(8f, false);
        sketchImageView.setDisplayListener(new DisplayListener() {
            @Override
            public void onStarted() {
                // 只有在需要进入非主线程加载图片时才会回调 onStarted() 方法
                dialog.show();
            }

            @Override
            public void onCompleted(Drawable drawable, ImageFrom imageFrom, ImageAttrs imageAttrs) {
                dialog.dismiss();
            }

            @Override
            public void onError(ErrorCause errorCause) {
                dialog.dismiss();
            }

            @Override
            public void onCanceled(CancelCause cancelCause) {
                dialog.dismiss();
            }
        });
        sketchImageView.displayImage(url);
    }
}
