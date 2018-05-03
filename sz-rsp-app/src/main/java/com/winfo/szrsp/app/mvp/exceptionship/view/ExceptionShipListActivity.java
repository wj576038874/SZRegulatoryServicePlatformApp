package com.winfo.szrsp.app.mvp.exceptionship.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.ship.ExceptionShipAdapter;
import com.winfo.szrsp.app.mvp.exceptionship.presenter.ExceptionShipPresenter;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShipList;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipListActivity extends Activity implements IExceptionShipView,View.OnClickListener {
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;
//    @BindView(R.id.titleBar_titleText)
//    TextView titleBar_titleText;
    @BindView(R.id.area_data_notfound)
    View notfoundView;
    @BindView(R.id.area_data_load_faild)
    View loadFailedView;
    @BindView(R.id.id_tv_data_load_faild)
    TextView tv_loadfile;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_area)
    ListView lv;

    private ClassicsHeader mClassicsHeader;

    private int  page=1;
    private ExceptionShipPresenter presenter;
    private Dialog dialog;

    private List<ExceptionShip> list;
    ExceptionShipAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exceptionship_list);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initData() {
        presenter=new ExceptionShipPresenter(this);
        dialog= DialogUtils.createLoadingDialog(this,"加载中...");
        list=new ArrayList<>();

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(false);
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader)mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time = format.format(new Date());
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于"+time));
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);

        presenter.getExceptionShipListData(""+page,"20",true);
    }

    private void initEvent() {
        titleBar_imgbtn_back.setOnClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                presenter.getExceptionShipListData(""+page,"20",false);

            }
        });
        loadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.getExceptionShipListData(""+page,"20",true);

            }
        });
        notfoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getExceptionShipListData(""+page,"20",true);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
        }
    }
    @Override
    public void showOnFaile(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void notFound() {
        notfoundView.setVisibility(View.VISIBLE);
        mRefreshLayout.setVisibility(View.GONE);
        loadFailedView.setVisibility(View.GONE);
    }

    @Override
    public Dialog getDailog() {
        return dialog;
    }

    @Override
    public void setExceptionShipListData(ExceptionShipList data) {
        int page=data.getPageNum();//当前页
        int pagenum=data.getPages();//总页数

        if(this.list.size()>0){
            this.list.addAll(data.getList());
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            this.page=page+1;
//            adapter=new ExceptionShipAdapter(this,this.list,R.layout.exceptionship_item);
//            lv.setAdapter(adapter);
            if (page == pagenum){
                ToastUtils.showToast(this,"数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
            }
            adapter.notifyDataSetChanged();
        }else {
            this.list = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
            this.page=page+1;
            adapter=new ExceptionShipAdapter(this,this.list,R.layout.exceptionship_item);
            lv.setAdapter(adapter);
            if (page == pagenum){
                ToastUtils.showToast(this,"数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
            }
            adapter.notifyDataSetChanged();
        }



    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }
}
