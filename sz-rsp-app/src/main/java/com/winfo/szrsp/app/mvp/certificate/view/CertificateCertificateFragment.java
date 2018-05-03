package com.winfo.szrsp.app.mvp.certificate.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CertificateAdapter;
import com.winfo.szrsp.app.mvp.certificate.presenter.CertificatePresenter;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.widget.MyGridView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CertificateCertificateFragment extends Fragment implements ICertificateActvity,View.OnClickListener{
    private MyGridView gridView;
    private View faile_view;
    private TextView tv_faile;
    private Dialog dialog;
    private static CertificatePresenter presenter;
    private static Ais ais;
    CertificateAdapter adapter;
    private SmartRefreshLayout mRefreshLayout;
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_certificate, null);

        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        faile_view.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.loadCertificateData(ais.getID(),"certificate",false);
            }
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        ais =(Ais) bundle.getSerializable("aisdata");
        presenter.loadCertificateData(ais.getID(),"certificate",false);

        mRefreshLayout.setEnableRefresh(true);
    }

    @SuppressLint("SimpleDateFormat")
    private void initView(View view) {
        presenter = new CertificatePresenter(this);
        dialog = DialogUtils.createLoadingDialog(getActivity(),"加载中...");
        gridView = view.findViewById(R.id.gd_view);
        faile_view = view.findViewById(R.id.layout_faile);
        tv_faile = faile_view.findViewById(R.id.id_tv_data_load_faild);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        ClassicsHeader mClassicsHeader = (ClassicsHeader) mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time = format.format(new Date());
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于" + time));
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);
    }
    NoticeCerMain noticeCerMain;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        noticeCerMain = (NoticeCerMain) context;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_faile:
                presenter.loadCertificateData(ais.getID(),"certificate",true);
                break;
        }
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSucess(CertificateInfo certificateInfo) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.setLoadmoreFinished(true);
        if(certificateInfo.getCertificate()!=null&&certificateInfo.getCertificate().size()>0){
            noticeCerMain.noticeCerMain("certificate",false);
            mRefreshLayout.setVisibility(View.VISIBLE);
            faile_view.setVisibility(View.GONE);
            adapter = new CertificateAdapter(getActivity(), certificateInfo.getCertificate(), R.layout.certificate_item);
            gridView.setAdapter(adapter);
        }else {
            noticeCerMain.noticeCerMain("certificate",true);
            mRefreshLayout.setVisibility(View.GONE);
            faile_view.setVisibility(View.VISIBLE);
            tv_faile.setText("没有查询到数据");
        }


    }

    @Override
    public void OnFaile(String msg) {
        noticeCerMain.noticeCerMain("certificate",true);
        mRefreshLayout.setVisibility(View.GONE);
        faile_view.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
    }

    @Override
    public void OnLoginExpired(String msg) {
        LoginUtils.loginOutShowDialog(getActivity(),msg,msg);
    }

    @Override
    public void upLoadSuccess(String msg) {
    }

    @Override
    public void deleteSuccess(String msg) {
    }

    @Override
    public void upLoadFaile(String msg) {
    }

    @Override
    public void deleteFaile(String msg) {

    }

    public static void reflashData() {
        presenter.loadCertificateData(ais.getID(),"certificate",false);
    }
    public CertificateAdapter getAdapter(){
        return adapter;
    }


}
