package com.winfo.szrsp.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownState;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.version.VersionInfo;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.MobileService;
import com.winfo.szrsp.app.sdk.utils.NetworkUtil;
import com.winfo.szrsp.app.service.UpdateVersionService;
import java.io.File;
import java.lang.reflect.Method;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wenjie 版本更新的工具类
 */
public class UpdateVersionUtil extends BaseModel<MobileService>{

    //单利对象
    @SuppressLint("StaticFieldLeak")
    private volatile static UpdateVersionUtil instence;

    private Subscription mSubscription;

    private UpdateVersionUtil() {
        super(SzRspApplication.getContext());
    }

    /**
     * 获取单例模式类
     */
    public static UpdateVersionUtil getInstence() {
        if (instence == null) {
            synchronized (HttpDownManager.class) {
                if (instence == null) {
                    instence = new UpdateVersionUtil();
                }
            }
        }
        return instence;
    }


    /**
     * 接口回调
     *
     * @author wenjie
     */
    public interface UpdateListener {
        void onUpdateReturned(int updateStatus, VersionInfo versionInfo);
    }

    /**
     * 检测版本
     *
     * @param context 上下文
     */
    public void checkVersion(final Context context, Dialog dialog, final UpdateListener updateListener) {
        Observable<VersionInfo> observable = mService.checkVersionInfo();
        Subscriber<VersionInfo> subscriber = new DialogSubscriber<VersionInfo>(dialog) {
            @Override
            public void onSuccess(VersionInfo versionInfo) {
                int clientVersionCode = ApkUtils.getVersionCode(context);
                int serverVersionCode = versionInfo.getVersionCode();
                //有新版本
                if (clientVersionCode < serverVersionCode) {
                    int i = NetworkUtil.checkedNetWorkType(context);
                    if (i == NetworkUtil.NOWIFI) {
                        updateListener.onUpdateReturned(UpdateStatus.NOWIFI, versionInfo);
                    } else if (i == NetworkUtil.WIFI) {
                        updateListener.onUpdateReturned(UpdateStatus.YES, versionInfo);
                    }
                } else {
                    //无新本
                    updateListener.onUpdateReturned(UpdateStatus.NO, null);
                }
            }

            @Override
            public void onFailure(String msg) {
                updateListener.onUpdateReturned(UpdateStatus.TIMEOUT, null);
            }
        };
        mSubscription = observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 弹出新版本提示
     */
    //安装文件
    private File updateFile;
    private TextView tv_msg;
    private ProgressBar bar;
    private TextView tv_jd;
    private LinearLayout ly_titlebar;
    private Button btnOk;
    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    private DownInfo apkApi;

    @SuppressLint("SetTextI18n")
    public void showDialog(final Context context, final VersionInfo versionInfo) {
        final Dialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);// 可以用“返回键”取消
        dialog.setCanceledOnTouchOutside(false);//
        dialog.show();
        View view = LayoutInflater.from(context).inflate(R.layout.version_update_dialog, null);
        dialog.setContentView(view);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = (int) (dm.widthPixels * 0.8);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        assert dialog.getWindow() != null;
        dialog.getWindow().setLayout(width, height);

        btnOk = view.findViewById(R.id.btn_update_id_ok);
        Button btnCancel = view.findViewById(R.id.btn_update_id_cancel);
        TextView tvContent = view.findViewById(R.id.tv_update_content);
        TextView tvUpdateTile = view.findViewById(R.id.tv_update_title);
        ly_titlebar = view.findViewById(R.id.update_titlebar);
        final TextView tvUpdateMsgSize = view.findViewById(R.id.tv_update_msg_size);
        tv_msg = view.findViewById(R.id.msg);
        bar = view.findViewById(R.id.progressBar1);
        tv_jd = view.findViewById(R.id.bartext);

        updateFile = new File(SDCardUtils.getRootDirectory() + "/updateVersion/sz-rsp-app.apk");
        //文件存储位置
        File outputFile = new File(SDCardUtils.getRootDirectory() + "/updateVersion/sz-rsp-app.apk");
        apkApi = new DownInfo(versionInfo.getDownloadUrl());
        apkApi.setState(DownState.START);
        apkApi.setSavePath(outputFile.getAbsolutePath());
        apkApi.setListener(new HttpProgressOnNextListener() {
            @Override
            public void onNext(Object o) {
            }

            @Override
            public void onStart() {
                PreferenceUtils.setBoolean(context, "isDown", false);//下载开始
                btnOk.setText("正在下载");
                tv_msg.setTextColor(ContextCompat.getColor(context, R.color.black));
                tv_msg.setText("开始下载：深海监管");
            }

            @Override
            public void onComplete() {
                PreferenceUtils.setBoolean(context, "isDown", true);//下载完成
                tv_msg.setTextColor(ContextCompat.getColor(context, R.color.black));
                tv_msg.setText("下载完成！点击安装！");
                ly_titlebar.setVisibility(View.GONE);
                btnOk.setText("立即安装");
                //btnOk.setBackgroundDrawable(ContextCompat.getDrawable(context , R.drawable.dialog_ok_btn_bg));
                //自动安装新版本
                Intent installIntent = ApkUtils.getInstallIntent(updateFile);
                SzRspApplication.getContext().startActivity(installIntent);
            }

            @Override
            public void onError(Throwable e) {
                tv_msg.setText("服务连接超时，稍后重试！");
                tv_msg.setTextColor(ContextCompat.getColor(context, R.color.red));
                bar.setVisibility(View.GONE);
                tv_jd.setVisibility(View.GONE);
                btnOk.setText("重新下载");
                httpDownManager.stopDown(apkApi);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void updateProgress(long readLength, long countLength) {
                try {
                    long l = readLength * 100 / countLength;
                    tv_msg.setTextColor(ContextCompat.getColor(context, R.color.black));
                    tv_msg.setText("正在下载：深海监管，请勿退出应用");
                    tv_jd.setText(l + "%");
                    bar.setProgress((int) l);
                } catch (Exception e) {
                    bar.setVisibility(View.GONE);
                    tv_jd.setVisibility(View.GONE);
                    tv_msg.setText("服务器异常");
                    tv_msg.setTextColor(ContextCompat.getColor(context, R.color.red));
                }
            }
        });
        tvContent.setText(versionInfo.getVersionDesc());
        tvUpdateTile.setText("最新版本：" + versionInfo.getVersionName());
        tvUpdateMsgSize.setText("新版本大小：" + versionInfo.getVersionSize());


        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (versionInfo.getIsUpdate().equals("false")) {
                    if (apkApi.getState() == DownState.START) {
                        if (updateFile.exists()) {
                            updateFile.delete();
                        }
                        //开始下载
                        bar.setVisibility(View.VISIBLE);
                        ly_titlebar.setVisibility(View.VISIBLE);
                        httpDownManager.startDown(apkApi);
                    } else if (apkApi.getState() == DownState.DOWN) {
//                        //正在下载
//                    httpDownManager.pause(apkApi);
//                    btnOk.setText("继续下载");
                        //dialog.dismiss();
//                       httpDownManager.stopDown(apkApi);
                        //((Activity) context).finish();
                    } else if (apkApi.getState() == DownState.PAUSE) {
//                        //暂停
//                    btnOk.setText("暂停下载");
//                    httpDownManager.startDown(apkApi);
                    } else if (apkApi.getState() == DownState.FINISH) {
                        //下载完成
                        Intent installIntent = ApkUtils.getInstallIntent(updateFile);
                        SzRspApplication.getContext().startActivity(installIntent);
                    } else if (apkApi.getState() == DownState.ERROR || apkApi.getState() == DownState.STOP) {//下载出错
                        btnOk.setText("立即更新");
                        bar.setVisibility(View.VISIBLE);
                        ly_titlebar.setVisibility(View.VISIBLE);
                        httpDownManager.startDown(apkApi);
                        tv_jd.setVisibility(View.VISIBLE);
                    }
                } else {
                    dialog.dismiss();
                    //没有下载，则开启服务下载新版本
                    Intent intent = new Intent(context, UpdateVersionService.class);
                    intent.putExtra("downloadUrl", versionInfo.getDownloadUrl());
//                intent.putExtra("downloadUrl", "http://121.8.249.13:8081/gdmsaec-app/twodcode/gdmsaec-app.apk");
                    context.startService(intent);
                }
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (versionInfo.getIsUpdate().equals("false")) {
                    ((Activity) context).finish();
                    System.exit(0);
                } else {
                    dialog.dismiss();
                }
            }
        });
    }


    public static void collapsingNotification(Context context) {
        Object service = context.getSystemService("statusbar");
        if (null == service)
            return;
        try {
            Class<?> clazz = Class.forName("android.app.StatusBarManager");
            int sdkVersion = android.os.Build.VERSION.SDK_INT;
            Method collapse;
            if (sdkVersion <= 16) {
                collapse = clazz.getMethod("collapse");
            } else {
                collapse = clazz.getMethod("collapsePanels");
            }
            collapse.setAccessible(true);
            collapse.invoke(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 取消订阅，取消请求
     */
    public void unSubscribeRequest() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
