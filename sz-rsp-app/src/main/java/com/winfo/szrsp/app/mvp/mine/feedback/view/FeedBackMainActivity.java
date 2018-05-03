package com.winfo.szrsp.app.mvp.mine.feedback.view;

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
import com.winfo.szrsp.app.widget.MySmartTabLayout;


import butterknife.BindView;
import butterknife.ButterKnife;


public class FeedBackMainActivity extends FragmentActivity implements View.OnClickListener {
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;
    @BindView(R.id.titleBar_newtask)
    TextView titleBar_newtask;
    @BindView(R.id.titleBar_titleText)
    TextView titleBar_titleText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmain);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("我的反馈", MyFeedBackFregment.class,new Bundler().putInt("value",1).get()));
        pages.add(FragmentPagerItem.of("全部反馈", MyFeedBackFregment.class,new Bundler().putInt("value",2).get()));
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(pages.size());
        MySmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
    private void initData() {
        titleBar_newtask.setVisibility(View.VISIBLE);
        titleBar_newtask.setText("添加");
        titleBar_titleText.setText("反馈信息");
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
                //startActivity(new Intent(FeedBackMainActivity.this, .class));
                break;

        }
    }
}
