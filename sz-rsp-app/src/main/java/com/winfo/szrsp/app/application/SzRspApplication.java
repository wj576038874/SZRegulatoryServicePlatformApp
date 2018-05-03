package com.winfo.szrsp.app.application;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.squareup.leakcanary.RefWatcher;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.entity.HistoryThsy;
import com.winfo.szrsp.app.entity.user.HistoryUser;

import java.io.File;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @PackageName: com.winfo.szrsp.app.application
 * @FileName: com.winfo.szrsp.app.application.SzRspApplication.java
 * @Author: wenjie
 * @Date: 2017-10-16 17:23
 * @Description:
 * @Version:
 */
public class SzRspApplication extends MultiDexApplication {
    private static final String TAG = "JPush";
    private static Context mContext;
    public static String cachePath;
    public static ImageLoaderConfiguration config;
    public static DbUtils db;
    public static WinfoDNCView winfoDNCView;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        JAnalyticsInterface.setDebugMode(true);
        JAnalyticsInterface.init(this);
        JAnalyticsInterface.initCrashHandler(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, "onActivityCreated() called with: activity = [" + activity + "], savedInstanceState = [" + savedInstanceState + "]");
            }


            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "onActivityStarted() called with: activity = [" + activity + "]");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "onActivityResumed() called with: activity = [" + activity + "]");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "onActivityPaused() called with: activity = [" + activity + "]");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "onActivityStopped() called with: activity = [" + activity + "]");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d(TAG, "onActivitySaveInstanceState() called with: activity = [" + activity + "], outState = [" + outState + "]");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "onActivityDestroyed() called with: activity = [" + activity + "]");

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
//        refWatcher = LeakCanary.install(this);
        mContext = getApplicationContext();
        cachePath = "WFTCache";
        // 初始化imageloader
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), cachePath);
        config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCache(
                        new LRULimitedMemoryCache((int) Runtime.getRuntime()
                                .maxMemory() / 8)) // 设置内存缓存，默认应用可用内存的1/8
                .memoryCacheSize((int) Runtime.getRuntime().maxMemory() / 4) // 设置内存缓存的大小
                .diskCache(new UnlimitedDiskCache(cacheDir)) // 设置自己的磁盘缓存路径，不限制缓存的大小
                .build();
        // ImageLoaderConfiguration configuration = ImageLoaderConfiguration
        // .createDefault(this);
        ImageLoader.getInstance().init(config);

        /*
         * 应用启动时 就创建数据库，和一些要用到的表
         */
        db = DbUtils.create(mContext, "database_szmsaec.db", 20171216, new DbUtils.DbUpgradeListener() {

            @Override
            public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                try {
                    db.dropTable(HistoryAis.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            db.createTableIfNotExist(HistoryAis.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        try {
            db.createTableIfNotExist(HistoryThsy.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        try {
            db.createTableIfNotExist(HistoryUser.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static RefWatcher getRefWatcher(Context context) {
        SzRspApplication application = (SzRspApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static Context getContext() {
        return mContext;
    }

    public static WinfoDNCView getWinfoDNCView() {
        return winfoDNCView;
    }

}
