package com.winfo.szrsp.app.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ApkUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.UpdateVersionUtil;

import java.io.File;


/**
 * @author wenjie
 *         下载新版本的服务类
 */
public class UpdateVersionService extends Service {

    private NotificationManager nm;
    private NotificationCompat.Builder builder;
    private Notification notification;
    //标题标识
    private int titleId = 1;
    //安装文件
    private File updateFile;

    private HttpUtils httpUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        httpUtils = new HttpUtils();
        updateFile = new File(SDCardUtils.getRootDirectory() + "/updateVersion/sz-rsp-app.apk");
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1","Channel1", NotificationManager.IMPORTANCE_NONE);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            nm.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(getApplicationContext(),"1");//与channelId对应
            notification = builder
                    .setContentTitle("正在下载：深海监管")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setOngoing(true)
                    .setNumber(3)//久按桌面图标时允许的此条通知的数量
                    .build();
            notification.tickerText = "开始下载";
        }else {
            builder = new NotificationCompat.Builder(getApplicationContext());
            notification = builder
                    .setContentTitle("正在下载：深海监管")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .build();
            notification.tickerText = "开始下载";
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (updateFile.exists()) {
            updateFile.delete();
        }
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        String url = bundle.getString("downloadUrl");
        nm.notify(titleId, notification);
        downLoadFile(url);
        startForeground(titleId, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    public void downLoadFile(String url) {
        httpUtils.download(url, updateFile.getAbsolutePath(), true, false, new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> response) {
                // 发送消息
                notification = builder
                        .setContentTitle("下载完成!点击安装")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                nm.notify(titleId, notification);
                UpdateVersionUtil.collapsingNotification(UpdateVersionService.this);
                stopSelf();
                //自动安装新版本
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);//适配android8.0提示用户安装apk
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(updateFile.getAbsolutePath())),"application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    Intent installIntent = ApkUtils.getInstallIntent(updateFile);
                    startActivity(installIntent);
                }
                nm.cancel(titleId);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                stopSelf();
                nm.cancel(titleId);
                ToastUtils.showToast(getApplicationContext(), "下载失败，请检查网络！");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                long l = current * 100 / total;
                // 发送消息
                notification = builder
                        .setContentTitle("正在下载：深海监管")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText((int) l + "%")
                        .setProgress(100, (int) l, false)
                        .setOngoing(true)
                        .setOnlyAlertOnce(true)
                        .build();
                nm.notify(titleId, notification);
            }

            @Override
            public void onStart() {
                notification = builder
                        .setContentTitle("开始下载：深海监管")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setOngoing(true)
                        .build();
                nm.notify(titleId, notification);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
