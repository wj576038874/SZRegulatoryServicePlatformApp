package com.winfo.szrsp.app.mvp.setting.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.setting.presenter.MapCacheSizePresenter;
import com.winfo.szrsp.app.sdk.entity.version.VersionInfo;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.UpdateStatus;
import com.winfo.szrsp.app.utils.UpdateVersionUtil;

/**
 * Created by wly on 2018/4/2.
 *
 */

public class SettingActivity extends Activity implements View.OnClickListener ,IMapChacheSizeView{

    private LinearLayout ll_clear_map;

    private LinearLayout ll_update_version;

    private LinearLayout ll_version_info;

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;

    private TextView tv_map_cache_size;

    private Dialog dialog;

    private Dialog loadingDialog;

    private MapCacheSizePresenter  mPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();

        initData();

        initEvent();

    }

    private void initData() {
        table_titleBar_titleText.setText("系统设置");
        initMapCache();
    }

    private void initEvent() {
        ll_clear_map.setOnClickListener(this);
        ll_update_version.setOnClickListener(this);
        ll_version_info.setOnClickListener(this);
        table_titleBar_imgbtn_back.setOnClickListener(this);
    }

    private void initView() {

        mPresenter=new MapCacheSizePresenter(this);

        dialog = DialogUtils.createLoadingDialog(this, "请稍后..");
        loadingDialog = DialogUtils.createLoadingDialog(this, "请稍后...");

        tv_map_cache_size=findViewById(R.id.tv_map_cache_size);
        ll_clear_map=findViewById(R.id.ll_clear_map);
        ll_update_version=findViewById(R.id.ll_update_version);
        ll_version_info=findViewById(R.id.ll_version_info);
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

            case R.id.ll_clear_map:

                DialogUtils.showDialog(this, "温馨提示", "确定要清理离线海图缓存吗？", "果断清理", "再考虑下", new DialogUtils.DialogOnClickListenner() {

                    @Override
                    public void btnConfirmClick(final Dialog dialog) {
                        dialog.dismiss();
                        mPresenter.deleteMapCache();

                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });


                break;

            case R.id.ll_update_version:

                UpdateVersionUtil.getInstence().checkVersion(this, dialog, new UpdateVersionUtil.UpdateListener() {

                    @Override
                    public void onUpdateReturned(int updateStatus, final VersionInfo versionInfo) {
                        switch (updateStatus) {
                            case UpdateStatus.YES:
                                //弹出更新提示
                                UpdateVersionUtil.getInstence().showDialog(SettingActivity.this, versionInfo);
                                break;
                            case UpdateStatus.NOWIFI:

                                DialogUtils.showDialog(SettingActivity.this, "温馨提示", "当前非wifi网络,下载会消耗手机流量!", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                                    @Override
                                    public void btnConfirmClick(Dialog dialog) {
                                        dialog.dismiss();
                                        UpdateVersionUtil.getInstence().showDialog(SettingActivity.this, versionInfo);
                                    }

                                    @Override
                                    public void btnCancelClick(Dialog dialog) {
                                        dialog.dismiss();
                                    }
                                });
                                break;
                            case UpdateStatus.NO:
                                ToastUtils.showToast(SettingActivity.this,"您已经是最新版本！");
                                break;
                            case UpdateStatus.TIMEOUT:
                                ToastUtils.showToast(SettingActivity.this,"连接超时，请稍后重试！");
                                break;
                        }
                    }
                });

                break;

            case R.id.ll_version_info:
                startActivity(new Intent(this,VersionInfoActivity.class));
                break;

        }
    }

    /**
     * 刷新海图缓存大小数据
     */
    private void initMapCache() {
        tv_map_cache_size.setText("正在读取缓存...");
        mPresenter.getMapCacheSize();
    }


    @Override
    public void setMapCacheSzie(String size) {
        tv_map_cache_size.setText(size);
    }

    @Override
    public void onFailure(String msg) {
        tv_map_cache_size.setText("缓存读取失败");
    }

    @Override
    public void onDeleteMapCacheSuccess(String msg) {
        initMapCache();
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void onDeleteMapCacheFailure(String msg) {
        ToastUtils.showToast(SettingActivity.this, "清理成功！");
    }

    @Override
    public Dialog getLoadingDialog() {
        return loadingDialog;
    }

}
