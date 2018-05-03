package com.winfo.szrsp.app.mvp.table.psca.view;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.psca.presenter.DetailPSCFormPresenter;
import com.winfo.szrsp.app.mvp.table.psca.view.fragment.PSCFormAFragment;
import com.winfo.szrsp.app.mvp.table.psca.view.fragment.PSCFormBFragment;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.widget.MySmartTabLayout;

import java.io.Serializable;

/**
 * Created by wly on 2017/12/21.
 *
 */

public class DetailPSCFormActivity extends FragmentActivity implements IDetailPSCFormActivity, View.OnClickListener {

    private DetailPSCFormPresenter presenter;

    private String inspectNo;

    private LinearLayout ll_psc_form;

    private View view_faile;
    private TextView tv_faile;
    private TextView table_titleBar_titleText;

    private ImageButton table_titleBar_imgbtn_back;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_psc_form);

        initView();

        initData();

        initEvent();


    }

    private void initEvent() {
        view_faile.setOnClickListener(this);
        table_titleBar_imgbtn_back.setOnClickListener(this);
    }

    private void initView() {
        ll_psc_form=findViewById(R.id.ll_psc_form);
        view_faile=findViewById(R.id.view_faile);
        tv_faile = view_faile.findViewById(R.id.id_tv_data_load_faild);
        table_titleBar_imgbtn_back=findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText=findViewById(R.id.table_titleBar_titleText);

    }

    private void initData() {
        table_titleBar_titleText.setText("PSC");

        inspectNo = getIntent().getStringExtra("inspectNo");
        presenter=new DetailPSCFormPresenter(this);
        presenter.loadPSCFormDetailData(inspectNo);


    }

    @Override
    public Dialog getDialog() {
        return DialogUtils.createLoadingDialog(this,"加载中...");
    }

    @Override
    public void onSuccess(CtPscFromObjectDetail ctPscFromObjectDetail) {
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("FormA", PSCFormAFragment.class,new Bundler().putSerializable("CtPscFromObjectDetail",ctPscFromObjectDetail).get()));
        if (ctPscFromObjectDetail.getListb().size()>0){
            pages.add(FragmentPagerItem.of("FormB", PSCFormBFragment.class,new Bundler().putSerializable("CtPscFromObjectDetail",ctPscFromObjectDetail).get()));
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        MySmartTabLayout viewPagerTab = findViewById(R.id.view_pager_tab);
        viewPagerTab.setViewPager(viewPager);
    }


    @Override
    public void onFailure(String msg) {
        ll_psc_form.setVisibility(View.GONE);
        view_faile.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
        view_faile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadPSCFormDetailData(inspectNo);
            }
        });
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
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
