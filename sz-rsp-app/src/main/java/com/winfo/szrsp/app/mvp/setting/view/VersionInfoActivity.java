package com.winfo.szrsp.app.mvp.setting.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ApkUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wly on 2018/4/2.
 *
 */

public class VersionInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;

    private TextView tv_version;

    private ImageView iv_code;

    private RelativeLayout rl_down_apk;

    private  String downApkUrl="http://219.133.95.9:8001/AppDownLoad/sz-rsp-app.apk";//apk下载地址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_version_info);

        initView();

        initEvent();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initData();

    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        rl_down_apk.setOnClickListener(this);
    }

    private void initData() {
        table_titleBar_titleText.setText("版本信息");
        String versionName = ApkUtils.getVersionName(getApplicationContext());
        tv_version.setText(versionName);



        Bitmap bitmap=generateBitmap(downApkUrl,iv_code.getWidth(),iv_code.getHeight());
        if (bitmap!=null){
            iv_code.setImageBitmap(bitmap);
        }



    }

    private void initView() {
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);
        tv_version=findViewById(R.id.tv_version);
        iv_code=findViewById(R.id.iv_code);
        rl_down_apk=findViewById(R.id.rl_down_apk);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.rl_down_apk:

                final Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(downApkUrl));
                if (intent.resolveActivity(this.getPackageManager()) != null) {
                   this.startActivity(Intent.createChooser(intent, "请选择浏览器"));
                } else {
                    ToastUtils.showToast(this,"请下载浏览器");
                }

                break;
        }
    }

    /**
     * 生成二维码
     * @param content 生成二维码的文本内容
     * @param width  生成二维码图片的宽
     * @param height 生成二维码图片的高
     * @return 返回生成的二维码图片
     */
    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();//获取实例
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


}
