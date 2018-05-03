package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.widget.MySmartTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TaskMainActivity extends FragmentActivity implements View.OnClickListener {
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;
    @BindView(R.id.titleBar_newtask)
    TextView titleBar_newtask;
    private String userDeptCode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmain);
        ButterKnife.bind(this);
        userDeptCode  = ACache.get(this).getAsString("deptCode");
//        Ais aisData = (Ais) getIntent().getSerializableExtra("aisdata");
        initView();
        initEvent();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        ExcuteDistributeFragment.reflashData();
//        CompleteBackFragment.reflashData();

        ExcuteFragment.reflashData();
        ReceiveTaskFragment.reflashData();
        DistributeFragment.reflashData();
        CompleteFragment.reflashData();
        BackTaskFragment.reflashData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExcuteDistributeFragment.page = 1;
        CompleteBackFragment.page = 1;
    }

    private void initView() {
        FragmentPagerItems pages = new FragmentPagerItems(this);
//        pages.add(FragmentPagerItem.of("执行/分发", ExcuteDistributeFragment.class,new Bundler().putInt("value",1).get()));
//        pages.add(FragmentPagerItem.of("完成/取消", CompleteBackFragment.class,new Bundler().putInt("value",2).get()));
        pages.add(FragmentPagerItem.of("待分发", DistributeFragment.class,new Bundler().putInt("value",1).get()));
        pages.add(FragmentPagerItem.of("待接收", ReceiveTaskFragment.class,new Bundler().putInt("value",2).get()));
        pages.add(FragmentPagerItem.of("执行中", ExcuteFragment.class,new Bundler().putInt("value",3).get()));
        pages.add(FragmentPagerItem.of("已完成", CompleteFragment.class,new Bundler().putInt("value",4).get()));
        pages.add(FragmentPagerItem.of("已取消", BackTaskFragment.class,new Bundler().putInt("value",5).get()));



//        if (userDeptCode.equals("14010005") || userDeptCode.equals("14010008") ||
//                userDeptCode.equals("14020041") || userDeptCode.equals("14020006") ||
//                userDeptCode.equals("14030006") || userDeptCode.equals("14030041") || userDeptCode.equals("14030005") ||
//                userDeptCode.equals("14040005") || userDeptCode.equals("14040041") ||
//                userDeptCode.equals("14050005") || userDeptCode.equals("14050006") ||userDeptCode.equals("14050041") ||
//                userDeptCode.equals("14060041") || userDeptCode.equals("14060005")){
//            pages.add(FragmentPagerItem.of("派发", AssignTaskFragment.class,new Bundler().putSerializable("aisData",aisData).putInt("value",3).get()));
//        }

        if(userDeptCode==null||userDeptCode.equals("")){
            LoginUtils.loginOutShowDialog(this,"温馨提示","登入过期，请重新登入");
        }else {
            if (userDeptCode.equals("14010005") || userDeptCode.equals("14010008") ||
                    userDeptCode.equals("14020041") || userDeptCode.equals("14020006") ||
                    userDeptCode.equals("14030006") || userDeptCode.equals("14030041") || userDeptCode.equals("14030005") ||
                    userDeptCode.equals("14040005") || userDeptCode.equals("14040041") ||
                    userDeptCode.equals("14050005") || userDeptCode.equals("14050006") ||userDeptCode.equals("14050041") ||
                    userDeptCode.equals("14060041") || userDeptCode.equals("14060005")){
                titleBar_newtask.setVisibility(View.VISIBLE);
            }else {
                titleBar_newtask.setVisibility(View.GONE);
            }
        }



        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(pages.size());
        MySmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    private void initEvent() {
        titleBar_imgbtn_back.setOnClickListener(this);
        titleBar_newtask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.titleBar_newtask:
                startActivity(new Intent(TaskMainActivity.this, AssignTaskActivity.class));
                break;

        }
    }
}
