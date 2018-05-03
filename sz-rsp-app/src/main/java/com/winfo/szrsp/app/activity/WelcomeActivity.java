package com.winfo.szrsp.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ApkUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;


/**
 * @项目名: gdmsaec-app
 * @包名: com.winfo.gdmsaec.app.activity
 * @类名: WelcomeActivity
 * @创建者: yanfeijun
 * @创建时间: 2015-10-18 下午6:51:23
 * @描述: 欢迎logo界面
 * @svn版本: $Rev: 1052 $
 * @更新人: $Author: guanliusheng $
 * @更新时间: $Date: 2016-01-19 09:17:44 +0800 (Tue, 19 Jan 2016) $
 * @更新描述: TODO
 */
public class WelcomeActivity extends Activity {
    // 用户是否第一次进入应用
    public static final String KEY_IS_FIRST = "is_first";
    public static final String LOCAL_VERSIONCODE = "localVersionCode";
    // 动画时长
    private final static long DUARATION = 1500;
    //	private ProgressBar pb_pown;
//	private TextView tv_check;
    LinearLayout mLayout;
    private View decorView;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= 21) {
            decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextAction();
            }
        }, 3000);
    }

    /**
     * 进入主界面的方法
     */
    private void loadMainUI() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler = null;
        decorView = null;
    }

    /*
         * 动画播放监听
         */
    private class WelcomeAnimationListener implements AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
//			//TODO  模糊查询 数据 并保存 暂时 不放在这里访问保存
//			List<LocalAisData> localAisDatas = null;
//			try {
//				localAisDatas = GdmsaecApplication.db.findAll(LocalAisData.class);
//			} catch (DbException e) {
//				e.printStackTrace();
//			}
//			if(localAisDatas.size() > 0){
//				nextAction();
//			}else{
//				//获取 模糊查询 数据 并保存
//				getAisData();
//			}
            nextAction();
        }


        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void nextAction() {
        // 用户如果是第一次使用应用程序,进入引导页面true为第一次  false不是第一次 如果没有取到值则默认是true
        boolean isFirst = PreferenceUtils.getBoolean(WelcomeActivity.this, KEY_IS_FIRST, true);
        int clientVersionCode = ApkUtils.getVersionCode(WelcomeActivity.this);
        int localVersionCode = PreferenceUtils.getInt(WelcomeActivity.this, LOCAL_VERSIONCODE, 1);
        if (localVersionCode < clientVersionCode) {//更新了
            File file = new File(SDCardUtils.getRootDirectory() + "/updateVersion/gdmsaec-app.apk");
            if (file.exists() && file.getName().equals("gdmsaec-app.apk")) {
                //删除
                file.delete();
            }
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            if (isFirst) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                loadMainUI();
            }

        }
    }
}
