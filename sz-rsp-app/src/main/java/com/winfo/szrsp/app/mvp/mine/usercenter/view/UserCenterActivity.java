package com.winfo.szrsp.app.mvp.mine.usercenter.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.mine.feedback.view.FeedBackMainActivity;
import com.winfo.szrsp.app.mvp.mine.login.view.impl.UserLoginActivity;
import com.winfo.szrsp.app.mvp.mine.userinfo.UserInfoActivity;
import com.winfo.szrsp.app.mvp.setting.view.SettingActivity;
import com.winfo.szrsp.app.mvp.table.TableListActivity;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.JdbgActivity;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.SupervisionActivity;
import com.winfo.szrsp.app.mvp.table.cqgjdjcqktzs.view.CqgjdjcqktzsActivity;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.view.DXSHCJCBActivity;
import com.winfo.szrsp.app.mvp.table.findalltable.view.AllTableActivity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.ChecklistMainActvity;
import com.winfo.szrsp.app.mvp.table.psca.view.PSCFormAActivity;
import com.winfo.szrsp.app.mvp.table.ssxh.view.WatersPatrolActivity;
import com.winfo.szrsp.app.mvp.task.view.TaskInspectionActivity;
import com.winfo.szrsp.app.sdk.entity.version.VersionInfo;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.UpdateStatus;
import com.winfo.szrsp.app.utils.UpdateVersionUtil;

/**
 * Created by ChengQi on 2017/10/17.
 */

public class UserCenterActivity extends Activity implements View.OnClickListener {

    private ImageButton table_titleBar_imgbtn_back;

    private TextView table_titleBar_titleText;

    private LinearLayout ll_user_info;

    private Button btn_cqg;
    private Button btn_cbxc;
    private Button btn_findtable;
    private Button btn_waters_patrol;
    private Button btn_dxshcjcb;
    private Button btn_tzs;
    private Button btn_renuw;
    private Button btn_table_list;
    private Button btn_table_psc_a;
    private LinearLayout ll_loginout;
    private LinearLayout ll_updateversion;
    private LinearLayout ll_table_list;
    private LinearLayout ll_search_table;
    private LinearLayout ll_setting;
    private LinearLayout ll_feedback;
    private Dialog dialog;
    private TextView tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        table_titleBar_titleText.setText("个人中心");
        String username = ACache.get(this).getAsString("userName");
        tv_username.setText(username);
    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
        btn_cqg.setOnClickListener(this);
        btn_cbxc.setOnClickListener(this);
        btn_findtable.setOnClickListener(this);
        btn_waters_patrol.setOnClickListener(this);
        ll_user_info.setOnClickListener(this);
        ll_loginout.setOnClickListener(this);
        btn_dxshcjcb.setOnClickListener(this);
        btn_tzs.setOnClickListener(this);
        btn_renuw.setOnClickListener(this);
        btn_table_list.setOnClickListener(this);
        btn_table_psc_a.setOnClickListener(this);
        ll_table_list.setOnClickListener(this);
        ll_search_table.setOnClickListener(this);
        ll_updateversion.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "请稍后..");
        table_titleBar_titleText = findViewById(R.id.table_titleBar_titleText);
        table_titleBar_imgbtn_back = findViewById(R.id.table_titleBar_imgbtn_back);
        btn_cqg = findViewById(R.id.btn_cqg);
        btn_cbxc = findViewById(R.id.btn_cbxc);
        btn_findtable = findViewById(R.id.btn_find);
        btn_waters_patrol = findViewById(R.id.btn_waters_patrol);
        ll_user_info = findViewById(R.id.ll_user_info);
        btn_dxshcjcb = findViewById(R.id.btn_dxshcjcb);
        ll_loginout = findViewById(R.id.ll_loginout);
        btn_tzs = findViewById(R.id.btn_tzs);
        btn_renuw = findViewById(R.id.btn_renwu);
        btn_table_list = findViewById(R.id.btn_table_list);
        btn_table_psc_a = findViewById(R.id.btn_table_psc_a);
        ll_table_list = findViewById(R.id.ll_table_list);
        ll_search_table = findViewById(R.id.ll_search_table);
        ll_updateversion = findViewById(R.id.ll_updateversion);
        tv_username = findViewById(R.id.tv_username);
        ll_setting=findViewById(R.id.ll_setting);
        ll_feedback=findViewById(R.id.ll_feedback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting:
                startActivity(new Intent(UserCenterActivity.this, SettingActivity.class));
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(UserCenterActivity.this, FeedBackMainActivity.class));
                break;
            case R.id.btn_renwu:
                startActivity(new Intent(UserCenterActivity.this, TaskInspectionActivity.class));
                break;
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.btn_cqg:
                startActivity(new Intent(UserCenterActivity.this, SupervisionActivity.class));
                break;
            case R.id.btn_cbxc:
                startActivity(new Intent(UserCenterActivity.this, JdbgActivity.class));
                break;
            case R.id.btn_find:
                startActivity(new Intent(UserCenterActivity.this, AllTableActivity.class));
                break;
            case R.id.btn_waters_patrol:
                startActivity(new Intent(UserCenterActivity.this, WatersPatrolActivity.class));
                break;
            case R.id.ll_user_info:
                startActivity(new Intent(UserCenterActivity.this, UserInfoActivity.class));
                break;
            case R.id.ll_loginout:

                LoginUtils.loginOut(this);
//                PreferenceUtils.setBoolean(this, "islogin", false);
//                ACache.get(this).remove("access_token");
//                ACache.get(this).remove("uuid");
//                ACache.get(this).remove("userName");
//                ACache.get(this).remove("deptName");
//                ACache.get(this).remove("orgName");
                finish();
                startActivity(new Intent(this, UserLoginActivity.class));
                break;
            case R.id.ll_table_list:
                startActivity(new Intent(UserCenterActivity.this, TableListActivity.class));
                break;
            case R.id.ll_search_table:
                startActivity(new Intent(UserCenterActivity.this, AllTableActivity.class));
                break;
            case R.id.btn_dxshcjcb:
                startActivity(new Intent(UserCenterActivity.this, DXSHCJCBActivity.class));
                break;
            case R.id.btn_tzs:
                startActivity(new Intent(UserCenterActivity.this, CqgjdjcqktzsActivity.class));
                break;
            case R.id.btn_table_list:
                startActivity(new Intent(UserCenterActivity.this, ChecklistMainActvity.class));
                break;
            case R.id.btn_table_psc_a:
                startActivity(new Intent(UserCenterActivity.this, PSCFormAActivity.class));
                break;
            case R.id.ll_updateversion:
                UpdateVersionUtil.getInstence().checkVersion(this, dialog, new UpdateVersionUtil.UpdateListener() {

                    @Override
                    public void onUpdateReturned(int updateStatus, final VersionInfo versionInfo) {
                        switch (updateStatus) {
                            case UpdateStatus.YES:
                                //弹出更新提示
                                UpdateVersionUtil.getInstence().showDialog(UserCenterActivity.this, versionInfo);
                                break;
                            case UpdateStatus.NOWIFI:

                                DialogUtils.showDialog(UserCenterActivity.this, "温馨提示", "当前非wifi网络,下载会消耗手机流量!", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                                    @Override
                                    public void btnConfirmClick(Dialog dialog) {
                                        dialog.dismiss();
                                        UpdateVersionUtil.getInstence().showDialog(UserCenterActivity.this, versionInfo);
                                    }

                                    @Override
                                    public void btnCancelClick(Dialog dialog) {
                                        dialog.dismiss();
                                    }
                                });
                                break;
                            case UpdateStatus.NO:
                                ToastUtils.showToast(UserCenterActivity.this,"您已经是最新版本！");
                                break;
                            case UpdateStatus.TIMEOUT:
                                ToastUtils.showToast(UserCenterActivity.this,"连接超时，请稍后重试！");
                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UpdateVersionUtil.getInstence().unSubscribeRequest();
    }
}
