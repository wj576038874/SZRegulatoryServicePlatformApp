package com.winfo.szrsp.app.mvp.mine.login.view.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.login.HistoryUserAdapter;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.user.HistoryUser;
import com.winfo.szrsp.app.mvp.mine.login.presenter.LoginPresenter;
import com.winfo.szrsp.app.mvp.mine.login.view.IUserLoginView;
import com.winfo.szrsp.app.sdk.entity.user.UserData;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.ClearEditText;
import com.winfo.szrsp.app.utils.EditTextUtils;

import java.util.List;

/*
 * Created by ChengQi on 2017/10/17.
 */

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener
        , IUserLoginView {

    private TextInputLayout til_password;
    private RecyclerView save_user_list;
    private Button btn_login;
    private ImageButton imgbtn_showuser;
    private Toolbar toolbar;
    private ClearEditText editTextUsername, editTextPassword;
    private Dialog loadingDialog;
    private LoginPresenter loginPresenter;

    private LinearLayout ll_root;
    private CheckBox cb_remember_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initView();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferenceUtils.getBoolean(this, "islogin")) {
            finish();
        }
    }

    private void initEvent() {
        ll_root.setOnClickListener(this);
        imgbtn_showuser.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        editTextUsername.setOnClickListener(this);
        EditTextUtils.setEditTextInhibitInputSpace(editTextUsername);
        EditTextUtils.setEditTextInhibitInputSpeChat(editTextUsername);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        cb_remember_password = findViewById(R.id.cb_remember_password);
        ll_root = findViewById(R.id.ll_root);
        til_password = findViewById(R.id.til_password);
        save_user_list = findViewById(R.id.save_user_list);
        imgbtn_showuser = findViewById(R.id.imgbtn_showuser);
        loginPresenter = new LoginPresenter(this);
        btn_login = findViewById(R.id.btn_login);
        editTextUsername = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        loadingDialog = DialogUtils.createLoadingDialog(this, "正在登陆...");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//显示返回按钮
            actionBar.setDisplayShowTitleEnabled(false);//不显示标题
        }


    }

    @Override
    public void onClick(View v) {
        String tag;
        switch (v.getId()) {
            case R.id.btn_login:
                loginPresenter.login();
                break;

            case R.id.imgbtn_showuser:

                tag = (String) imgbtn_showuser.getTag();
                if (tag.equals("0")) {
                    loginPresenter.getHistoryUser();
                } else {
                    imgbtn_showuser.setTag("0");
                    save_user_list.setVisibility(View.GONE);
                    til_password.setVisibility(View.VISIBLE);
                    btn_login.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.ll_root:
                tag = (String) imgbtn_showuser.getTag();
                editTextUsername.setFocusable(true);
                editTextUsername.setFocusableInTouchMode(true);
                if (tag.equals("1") && save_user_list.getVisibility() == View.VISIBLE) {
                    imgbtn_showuser.setTag("0");
                    save_user_list.setVisibility(View.GONE);
                    til_password.setVisibility(View.VISIBLE);
                    btn_login.setVisibility(View.VISIBLE);
                    cb_remember_password.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.et_username:
                tag = (String) imgbtn_showuser.getTag();
                editTextUsername.setFocusable(true);
                editTextUsername.setFocusableInTouchMode(true);
                editTextUsername.requestFocus();
                if (tag.equals("1") && save_user_list.getVisibility() == View.VISIBLE) {
                    imgbtn_showuser.setTag("0");
                    save_user_list.setVisibility(View.GONE);
                    til_password.setVisibility(View.VISIBLE);
                    btn_login.setVisibility(View.VISIBLE);
                    cb_remember_password.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    //点击空白区域隐藏键盘
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (this.getCurrentFocus() != null) {
                if (this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public String getUserNmae() {
        return editTextUsername.getText().toString().trim();
    }

    @Override
    public String getUserPwd() {
        return editTextPassword.getText().toString().trim();
    }

    @Override
    public void loginSuccess(UserData userData) {

        if (cb_remember_password.isChecked()) {//记住密码
            try {
                List<HistoryUser> historyUsers = SzRspApplication.db.findAll(Selector.from(HistoryUser.class).where(WhereBuilder.b("username", "=", getUserNmae())));
                if (historyUsers.size() == 0) {//如果这个用户不存在历史记录中 那么添加进去 否则不添加
                    SzRspApplication.db.save(new HistoryUser(getUserNmae(), getUserPwd()));
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        ToastUtils.showToast(this, "登陆成功");
//        Intent intent = new Intent(this, UserCenterActivity.class);
        PreferenceUtils.setBoolean(this, "islogin", true);
//        PreferenceUtils.setString(this,"access_token",userData.getAccess_token());
        ACache.get(this).put("access_token", userData.getAccess_token());
        ACache.get(this).put("uuid", userData.getUserInfo().getUuid());
        ACache.get(this).put("userName", userData.getUserInfo().getUserName());
        ACache.get(this).put("deptName", userData.getUserInfo().getDeptName());
        ACache.get(this).put("orgName", userData.getUserInfo().getOrgName());
        ACache.get(this).put("orgCode", userData.getUserInfo().getOrgCode());
        ACache.get(this).put("deptCode", userData.getUserInfo().getDeptCode());
        ACache.get(this).put("userNum", editTextUsername.getText().toString().trim());
//        startActivity(intent);
        finish();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void setHistoryUser(final List<HistoryUser> historyUsers) {
        if (historyUsers.size() > 0) {
            editTextUsername.setFocusable(false);
            imgbtn_showuser.setTag("1");
            save_user_list.setVisibility(View.VISIBLE);
            til_password.setVisibility(View.GONE);
            btn_login.setVisibility(View.GONE);
            cb_remember_password.setVisibility(View.GONE);
            save_user_list.setLayoutManager(new LinearLayoutManager(this));
            save_user_list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            HistoryUserAdapter historyUserAdapter = new HistoryUserAdapter(this, historyUsers);
            save_user_list.setAdapter(historyUserAdapter);
            historyUserAdapter.setOnItemClickListener(new HistoryUserAdapter.OnItemClickListener() {
                @Override
                public void onClick(HistoryUserAdapter historyUserAdapter, View itemView, int position) {
                    editTextUsername.setFocusable(true);
                    editTextUsername.setFocusableInTouchMode(true);
                    editTextUsername.setText(historyUsers.get(position).getUsername());
                    editTextPassword.setText(historyUsers.get(position).getPassword());
                    save_user_list.setVisibility(View.GONE);
                    til_password.setVisibility(View.VISIBLE);
                    btn_login.setVisibility(View.VISIBLE);
                    cb_remember_password.setVisibility(View.VISIBLE);
                    imgbtn_showuser.setTag("0");
                }
            });
        }
    }

    @Override
    public Dialog getLoadingDialog() {
        return loadingDialog;
    }
}
