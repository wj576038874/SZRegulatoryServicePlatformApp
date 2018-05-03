package com.winfo.szrsp.app.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;

import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.task.view.TaskListActivity;
import com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.TaskMainActivity;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/**
 * Created by Guan on 2017/7/6.
 */

public class TimerService extends Service {
    private Timer timer;
    private TimerTask task;
//    private static NotificationManager nm;
//    private static NotificationCompat.Builder builder;
//    private static Notification notification;
    // 数据库的操作对象
    OkHttpClient okHttpClient ;
    WsManager wsManager;
    private static int number=0;

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    public void onDestroy() {
        stopTimer();
        Intent localIntent = new Intent();
        localIntent.setClass(this, TimerService.class);  //销毁时重新启动Service
        this.startService(localIntent);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTimer();
        return START_STICKY;

    }

    /**
     * 启动定时器
     */
    public void startTimer() {
        

        okHttpClient = new OkHttpClient().newBuilder()
                .pingInterval(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        wsManager= new WsManager.Builder(this)
                .wsUrl("ws://192.168.0.56:8002/MessagePush/websocket/1401/140111/SZMSA999")
                .needReconnect(true)
                .client(okHttpClient)
                .build();
        setListener();

        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    if(wsManager.getCurrentStatus()==-1){
                        wsManager.startConnect();
                    }else {
                        wsManager.sendMessage("0") ;
                    }
                }
            };
            timer.schedule(task, 1000, 1000 * 10);
        }
    }
    /**
     * 停止定时器
     */
    public void stopTimer() {
        if (timer != null) {
            task.cancel();
            timer.cancel();
            task = null;
            timer = null;
        }
    }
    private List<Integer> notifyNew=new ArrayList<>();
    private List<Integer> notifyBack=new ArrayList<>();
    @SuppressLint("NewApi")
    private void setListener() {
        wsManager.setWsStatusListener(new WsStatusListener() {
            @Override
            public void onOpen(Response response) {
                super.onOpen(response);
            }

            @Override
            public void onMessage(String text) {
                super.onMessage(text);
                if(!text.equals("0")){
                    showNotification(text);
                }else {
                }

            }

            @Override
            public void onMessage(ByteString bytes) {
                super.onMessage(bytes);

            }

            @Override
            public void onReconnect() {
                super.onReconnect();

            }

            @Override
            public void onClosing(int code, String reason) {
                super.onClosing(code, reason);
            }

            @Override
            public void onClosed(int code, String reason) {
                super.onClosed(code, reason);
            }

            @Override
            public void onFailure(Throwable t, Response response) {
                super.onFailure(t, response);
            }
        });
        wsManager.startConnect();
    }
    int item=1;
    private void showNotification(String json) {

        MpMessageQueue mpMessageQueue=  FastJsonUtil.jsonStrToBean(json,MpMessageQueue.class);

        ContentBean contentBean=FastJsonUtil.jsonStrToBean(mpMessageQueue.getMsgContent(),ContentBean.class);

        item=+1;
        long[] vibrates = { 0, 1000, 1000, 1000 };
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(isForeground(SzRspApplication.getContext())){
            NotificationManager nm;
            NotificationCompat.Builder builder;
            Notification notification;
            PendingIntent contentIntent;
            Intent intent = new Intent(SzRspApplication.getContext(), TaskMainActivity.class);
            Bundle bundle = new Bundle();
            //TODO
//            bundle.putInt("taskId",allNewTask.get(i).getId());
//            bundle.putInt("taskUserId",allNewTask.get(i).getTaskUserId());
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            contentIntent= PendingIntent.getActivity(SzRspApplication.getContext(), item,  intent, PendingIntent.FLAG_CANCEL_CURRENT);
            nm = (NotificationManager) SzRspApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
            builder = new NotificationCompat.Builder(SzRspApplication.getContext());
//            int status=allNewTask.get(i).getStatus();
//            String title;
//            if(status==0){
//                title ="您有新任务消息";
//            }else {
//                title ="您有退回任务消息";
//            }
            String title;
            title =contentBean.getContent();

            notification = builder
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)

                    .setContentIntent(contentIntent)
                    .setContentTitle(title)
                    .setContentText(contentBean.getTime())
                    .setAutoCancel(true)
                    .build();
//            if(!notifyNew.contains(10086)){
//                notification.sound=uri;
//                notification.vibrate=vibrates;
//            }
            notification.sound=uri;
            notification.vibrate=vibrates;
            nm.notify(item, notification);
            //notifyNew.add(item);


        }else {
            NotificationManager nm;
            NotificationCompat.Builder builder;
            Notification notification;
            PendingIntent contentIntent;
            Intent[] appIntent=null;
            appIntent=makeIntentStack(SzRspApplication.getContext());//上面有改方法
            Bundle bundle = new Bundle();
            String title;
            title =contentBean.getContent();;
            appIntent[1].putExtras(bundle);
            appIntent[1].setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);//关键的一步，设置启动模式
            contentIntent =PendingIntent.getActivities(SzRspApplication.getContext(), item,appIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            nm = (NotificationManager) SzRspApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
            builder = new NotificationCompat.Builder(SzRspApplication.getContext());
            notification = builder
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(contentIntent)
                    .setContentTitle(title)
                    .setContentText(contentBean.getTime())
                    .setAutoCancel(true)
                    .build();
//            if(!notifyBack.contains(10086)){
//                notification.sound=uri;
//                notification.vibrate=vibrates;
//
//            }
            notification.sound=uri;
            notification.vibrate=vibrates;
            nm.notify(item, notification);
            //notifyBack.add(item);
        }


    }

    //任务栈
    @SuppressLint("NewApi")
    static Intent[] makeIntentStack(Context context) {
        Intent[] intents = new Intent[2];
        intents[0] = Intent.makeRestartActivityTask(new ComponentName(context, MainActivity.class));
        intents[1] = new Intent(context, TaskMainActivity.class);
        return intents;
    }


    //当前应用是否处于前台
    private static  boolean isForeground(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            String currentPackageName = cn.getPackageName();
            if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
                return true;
            }
            return false;
        }
        return false;
    }


}