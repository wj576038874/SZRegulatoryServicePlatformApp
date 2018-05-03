package com.winfo.szrsp.app.mvp.mine.userinfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.utils.ACache;

/**
 * Created by ChengQi on 2017/12/8.
 *
 */

public class UserInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton table_titleBar_imgbtn_back;
    private TextView table_titleBar_titleText;
    private TextView tv_username;
    private TextView tv_deptname;
    private TextView tv_orgname;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initView();
        iniData();
        iniEvent();

    }

    private void iniData() {
        table_titleBar_titleText.setText("个人信息");
        String userName = ACache.get(this).getAsString("userName");
        String deptName = ACache.get(this).getAsString("deptName");
        String orgName = ACache.get(this).getAsString("orgName");
        tv_username.setText(userName);
        tv_deptname.setText(orgName);
        tv_orgname.setText(deptName);
    }

    private void iniEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
    }

    private void initView() {
        table_titleBar_imgbtn_back=(ImageButton)findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=(TextView)findViewById(R.id.table_titleBar_titleText);
        tv_username = findViewById(R.id.tv_username);
        tv_deptname = findViewById(R.id.tv_deptname);
        tv_orgname = findViewById(R.id.tv_orgname);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
        }
    }
}
