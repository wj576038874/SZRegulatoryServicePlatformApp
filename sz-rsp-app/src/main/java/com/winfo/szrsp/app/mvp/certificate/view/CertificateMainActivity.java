package com.winfo.szrsp.app.mvp.certificate.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CertificateAdapter;
import com.winfo.szrsp.app.adapter.PhotoAdapter;
import com.winfo.szrsp.app.mvp.certificate.presenter.CertificatePresenter;
import com.winfo.szrsp.app.mvp.mine.login.view.impl.UserLoginActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.MyIconButton;
import com.winfo.szrsp.app.widget.MySmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;


public class CertificateMainActivity extends FragmentActivity implements ICertificateActvity,View.OnClickListener, NoticeCerMain{
    @BindView(R.id.table_titleBar_imgbtn_back)
    ImageButton table_titleBar_imgbtn_back;
    @BindView(R.id.table_titleBar_tx_edit)
    TextView table_titleBar_tx_edit;
    @BindView(R.id.table_titleBar_tx_delete)
    TextView table_titleBar_tx_delete;
    @BindView(R.id.table_titleBar_tx_upload)
    TextView table_titleBar_tx_upload;

    private Dialog dialog;
    private CertificatePresenter presenter;
    private  Ais ais;
    private boolean isShowOtherCertificate=false;//控制其他证书是否显示开关 TODO
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificatemain);
        ButterKnife.bind(this);
        ais = (Ais) getIntent().getSerializableExtra("aisdata");
        initView();
        initEvent();
    }
    FragmentPagerItemAdapter adapter;
    int fragmentposition;
    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        presenter = new CertificatePresenter(this);
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("AIS(无线电)", CertificateAisFragment.class,new Bundler().putSerializable("aisdata",  ais).putInt("value",1).get()));
        pages.add(FragmentPagerItem.of("登记证书", CertificateCertificateFragment.class,new Bundler().putSerializable("aisdata", ais).putInt("value",2).get()));
        pages.add(FragmentPagerItem.of("国轮船检证书", CertificateNationalShipFragment.class,new Bundler().putSerializable("aisdata",  ais).putInt("value",3).get()));

        if(isShowOtherCertificate){
          pages.add(FragmentPagerItem.of("其他证书", CertificateOtherFragment.class,new Bundler().putSerializable("aisdata",  ais).putInt("value",4).get()));
        }




        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(pages.size());
        MySmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                fragmentposition=position;
                CertificateAisFragment aisFragment = (CertificateAisFragment)adapter.getPage(0);
                CertificateAdapter aisAdapter=aisFragment.getAdapter();
                CertificateCertificateFragment certificateFragment =(CertificateCertificateFragment) adapter.getPage(1);
                CertificateAdapter certificateAdapter=certificateFragment.getAdapter();
                CertificateNationalShipFragment certificateNationalShipFragment = (CertificateNationalShipFragment)adapter.getPage(2);
                CertificateAdapter certificateNationalShipFragmentAdapter=certificateNationalShipFragment.getAdapter();
                CertificateAdapter otherAdapter = null;
                if(isShowOtherCertificate){
                    CertificateOtherFragment otherFragment = (CertificateOtherFragment)adapter.getPage(3);
                    otherAdapter=otherFragment.getAdapter();
                }
                if(table_titleBar_tx_delete.getVisibility()==View.VISIBLE){
                    if(aisAdapter!=null){
                        aisAdapter.setIsShowCheck(true);
                    }
                    if(certificateAdapter!=null){
                        certificateAdapter.setIsShowCheck(true);
                    }
                    if(certificateNationalShipFragmentAdapter!=null){
                        certificateNationalShipFragmentAdapter.setIsShowCheck(true);
                    }
                    if(isShowOtherCertificate){

                        if(otherAdapter!=null){
                            otherAdapter.setIsShowCheck(true);
                        }
                    }

                }else {
                    if(aisAdapter!=null){
                        aisAdapter.setIsShowCheck(false);
                        aisAdapter.setDatasAllNoCheck();
                    }
                    if(certificateAdapter!=null){
                        certificateAdapter.setIsShowCheck(false);
                        certificateAdapter.setDatasAllNoCheck();
                    }
                    if(certificateNationalShipFragmentAdapter!=null){
                        certificateNationalShipFragmentAdapter.setIsShowCheck(false);
                        certificateNationalShipFragmentAdapter.setDatasAllNoCheck();
                    }
                    if(isShowOtherCertificate){
                        if(otherAdapter!=null){
                            otherAdapter.setIsShowCheck(false);
                            otherAdapter.setDatasAllNoCheck();
                        }
                    }

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPagerTab.setViewPager(viewPager);
    }

    private void initEvent() {
       table_titleBar_imgbtn_back.setOnClickListener(this);
        table_titleBar_tx_upload.setOnClickListener(this);
        table_titleBar_tx_edit.setOnClickListener(this);
        table_titleBar_tx_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.table_titleBar_tx_upload:
                ObtainevidenceDialog();
                break;
            case R.id.table_titleBar_tx_edit:
                if(table_titleBar_tx_edit.getText().toString().equals("编辑")){
                    table_titleBar_tx_edit.setText("取消");
                    table_titleBar_tx_delete.setVisibility(View.VISIBLE);

                    CertificateAisFragment aisFragment = (CertificateAisFragment)adapter.getPage(0);
                    CertificateAdapter aisAdapter=aisFragment.getAdapter();
                    CertificateCertificateFragment certificateFragment =(CertificateCertificateFragment) adapter.getPage(1);
                    CertificateAdapter certificateAdapter=certificateFragment.getAdapter();
                    CertificateNationalShipFragment certificateNationalShipFragment = (CertificateNationalShipFragment)adapter.getPage(2);
                    CertificateAdapter certificateNationalShipFragmentAdapter=certificateNationalShipFragment.getAdapter();
                    CertificateAdapter otherAdapter = null;
                    if(isShowOtherCertificate){
                        CertificateOtherFragment otherFragment = (CertificateOtherFragment)adapter.getPage(3);
                        otherAdapter=otherFragment.getAdapter();
                    }
                    if(aisAdapter!=null){
                        aisAdapter.setIsShowCheck(true);
                    }
                    if(certificateAdapter!=null){
                        certificateAdapter.setIsShowCheck(true);
                    }
                    if(certificateNationalShipFragmentAdapter!=null){
                        certificateNationalShipFragmentAdapter.setIsShowCheck(true);
                    }
                    if(isShowOtherCertificate){
                        if(otherAdapter!=null){
                            otherAdapter.setIsShowCheck(true);
                        }
                    }

                }else {
                    table_titleBar_tx_edit.setText("编辑");
                    table_titleBar_tx_delete.setVisibility(View.GONE);

                    CertificateAisFragment aisFragment = (CertificateAisFragment)adapter.getPage(0);
                    CertificateAdapter aisAdapter=aisFragment.getAdapter();
                    CertificateCertificateFragment certificateFragment =(CertificateCertificateFragment) adapter.getPage(1);
                    CertificateAdapter certificateAdapter=certificateFragment.getAdapter();
                    CertificateNationalShipFragment certificateNationalShipFragment = (CertificateNationalShipFragment)adapter.getPage(2);
                    CertificateAdapter certificateNationalShipFragmentAdapter=certificateNationalShipFragment.getAdapter();
                    CertificateAdapter otherAdapter = null;
                    if(isShowOtherCertificate){
                        CertificateOtherFragment otherFragment = (CertificateOtherFragment)adapter.getPage(3);
                        otherAdapter=otherFragment.getAdapter();
                    }

                    if(aisAdapter!=null){
                        aisAdapter.setIsShowCheck(false);
                        aisAdapter.setDatasAllNoCheck();
                    }
                    if(certificateAdapter!=null){
                        certificateAdapter.setIsShowCheck(false);
                        certificateAdapter.setDatasAllNoCheck();
                    }
                    if(certificateNationalShipFragmentAdapter!=null){
                        certificateNationalShipFragmentAdapter.setIsShowCheck(false);
                        certificateNationalShipFragmentAdapter.setDatasAllNoCheck();
                    }
                    if(isShowOtherCertificate){
                        if(otherAdapter!=null){
                            otherAdapter.setIsShowCheck(false);
                            otherAdapter.setDatasAllNoCheck();
                        }
                    }

                }

                break;
            case R.id.table_titleBar_tx_delete:
                CertificateAisFragment aisFragment = (CertificateAisFragment)adapter.getPage(0);
                CertificateAdapter aisAdapter=aisFragment.getAdapter();
                CertificateCertificateFragment certificateFragment =(CertificateCertificateFragment) adapter.getPage(1);
                CertificateAdapter certificateAdapter=certificateFragment.getAdapter();
                CertificateNationalShipFragment certificateNationalShipFragment = (CertificateNationalShipFragment)adapter.getPage(2);
                CertificateAdapter certificateNationalShipFragmentAdapter=certificateNationalShipFragment.getAdapter();
                CertificateAdapter otherAdapter = null;
                if(isShowOtherCertificate){
                    CertificateOtherFragment otherFragment = (CertificateOtherFragment)adapter.getPage(3);
                    otherAdapter=otherFragment.getAdapter();
                }

                List<CertificateDetatils> aisAdapterDatas=new ArrayList<>();
                List<CertificateDetatils> certificateAdapterDatas=new ArrayList<>();
                List<CertificateDetatils> nationalShipAdapterDatas=new ArrayList<>();
                List<CertificateDetatils> otherAdapterDatas=new ArrayList<>();
                if(aisAdapter!=null){
                    aisAdapterDatas= aisAdapter.getDatas();
                }
                if(certificateAdapter!=null){
                   certificateAdapterDatas= certificateAdapter.getDatas();
                }
                if(certificateNationalShipFragmentAdapter!=null){
                    nationalShipAdapterDatas= certificateNationalShipFragmentAdapter.getDatas();
                }
                if(isShowOtherCertificate){
                    if(otherAdapter!=null){
                        otherAdapterDatas= otherAdapter.getDatas();
                    }
                }

                StringBuilder itemId= new StringBuilder();
                if(aisAdapterDatas.size()>0){
                    for (int i = 0; i < aisAdapterDatas.size(); i++) {
                         if(aisAdapterDatas.get(i).isSelect()){
                            itemId.append(aisAdapterDatas.get(i).getId()).append(",");
                         }
                     }
                }
                if(certificateAdapterDatas.size()>0){
                    for (int i = 0; i < certificateAdapterDatas.size(); i++) {
                        if(certificateAdapterDatas.get(i).isSelect()){
                            itemId.append(certificateAdapterDatas.get(i).getId()).append(",");
                        }
                    }
                }
                if(nationalShipAdapterDatas.size()>0){
                    for (int i = 0; i < nationalShipAdapterDatas.size(); i++) {
                        if(nationalShipAdapterDatas.get(i).isSelect()){
                            itemId.append(nationalShipAdapterDatas.get(i).getId()).append(",");
                        }
                    }
                }
                if(isShowOtherCertificate){
                    if(otherAdapterDatas.size()>0){
                        for (int i = 0; i < otherAdapterDatas.size(); i++) {
                            if(otherAdapterDatas.get(i).isSelect()){
                                itemId.append(otherAdapterDatas.get(i).getId()).append(",");
                            }
                        }
                    }
                }

                if(itemId.length()==0){
                    ToastUtils.showToast(this,"请勾选删除");
                    return;
                }
                String id=itemId.substring(0,itemId.length()-1);
                presenter.deleteCertificate(id,true);
                break;
            case R.id.img_sub_photo1:
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setSelected(selectedPhotos1)
                        .start(this, 8001);
                break;
            case R.id.img_sub_photo2:
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setSelected(selectedPhotos2)
                        .start(this, 8002);
                break;
            case R.id.img_sub_photo3:
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setSelected(selectedPhotos3)
                        .start(this, 8003);
                break;
            case R.id.img_sub_photo4:
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setSelected(selectedPhotos4)
                        .start(this, 8004);
                break;
            case R.id.btn_sub_photo:
                if (PreferenceUtils.getBoolean(this, "islogin", false)) {
                    if (selectedPhotos1.size() > 0 || selectedPhotos2.size() > 0|| selectedPhotos3.size() > 0|| selectedPhotos4.size() > 0) {
                        dialog.show();
                        presenter.uolpadCertificate(ais,selectedPhotos1,selectedPhotos2,selectedPhotos3,selectedPhotos4);
                    } else {
                        ToastUtils.showToast(this, "请先选中照片再点击上传！");
                    }
                } else {
                    startActivity(new Intent(this, UserLoginActivity.class));
                }
                break;
            case R.id.imgbtn_close:
                obtainevidencedialog.dismiss();
                break;

        }
    }
    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what==1000){
                table_titleBar_tx_delete.setVisibility(View.GONE);
                table_titleBar_tx_edit.setText("编辑");
                dialog.dismiss();
                ToastUtils.showToast(CertificateMainActivity.this,"删除成功");
                CertificateAisFragment.reflashData();
                CertificateCertificateFragment.reflashData();
                CertificateNationalShipFragment.reflashData();
                if(isShowOtherCertificate){
                    CertificateOtherFragment.reflashData();
                }

            } else
                if(msg.what==1001){
                dialog.dismiss();
                ToastUtils.showToast(CertificateMainActivity.this,"上传成功");
                obtainevidencedialog.dismiss();
                selectedPhotos1.clear();
                selectedPhotos2.clear();
                selectedPhotos3.clear();
                CertificateAisFragment.reflashData();
                CertificateCertificateFragment.reflashData();
                CertificateNationalShipFragment.reflashData();
                    if(isShowOtherCertificate){
                        selectedPhotos4.clear();
                        CertificateOtherFragment.reflashData();
                    }
            }
            return false;
        }
    });

    @Override
    public Dialog getDialog() {
        return null;
    }

    @Override
    public void OnSucess(CertificateInfo certificateInfo) {

    }

    @Override
    public void OnFaile(String msg) {

    }

    @Override
    public void OnLoginExpired(String msg) {

    }

    @Override
    public void upLoadSuccess(String msg) {
        mHandler.sendEmptyMessage(1001);
    }

    @Override
    public void deleteSuccess(String msg) {
        mHandler.sendEmptyMessage(1000);
    }
    @Override
    public void upLoadFaile(String msg) {
        ToastUtils.showToast(this,msg);
        dialog.dismiss();
    }

    @Override
    public void deleteFaile(String msg) {
        ToastUtils.showToast(this,msg);
        dialog.dismiss();
    }

    private PhotoAdapter photoAdapter;
    private PhotoAdapter photoAdapter2;
    private PhotoAdapter photoAdapter3;
    private PhotoAdapter photoAdapter4;
    private Dialog obtainevidencedialog;
    private ArrayList<String> selectedPhotos1 = new ArrayList<>();//已选中的图片
    private ArrayList<String> selectedPhotos2 = new ArrayList<>();//已选中的图片
    private ArrayList<String> selectedPhotos3 = new ArrayList<>();//已选中的图片
    private ArrayList<String> selectedPhotos4 = new ArrayList<>();//已选中的图片
    //上传照片的dialog
    @SuppressLint("InflateParams")
    private void ObtainevidenceDialog() {
        obtainevidencedialog = new AlertDialog.Builder(this).create();
        View view;
        view = LayoutInflater.from(this).inflate(R.layout.obtainevidence_dialog, null);
        ImageView img_photo1 = view.findViewById(R.id.img_sub_photo1);
        ImageView img_photo2 = view.findViewById(R.id.img_sub_photo2);
        ImageView img_photo3 = view.findViewById(R.id.img_sub_photo3);
        ImageView img_photo4 = view.findViewById(R.id.img_sub_photo4);
        MyIconButton btn_sub = view.findViewById(R.id.btn_sub_photo);
        ImageButton btn_close = view.findViewById(R.id.imgbtn_close);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView recyclerView2 = view.findViewById(R.id.recycler_view2);
        RecyclerView recyclerView3 = view.findViewById(R.id.recycler_view3);
        RecyclerView recyclerView4 = view.findViewById(R.id.recycler_view4);


        View  certificate_viewline_other= view.findViewById(R.id.certificate_viewline_other);

        LinearLayout certificate_ll_other= view.findViewById(R.id.certificate_ll_other);

        if(isShowOtherCertificate){
            certificate_viewline_other.setVisibility(View.VISIBLE);
            certificate_ll_other.setVisibility(View.VISIBLE);
        }else {
            certificate_viewline_other.setVisibility(View.GONE);
            certificate_ll_other.setVisibility(View.GONE);
        }


        img_photo1.setOnClickListener(this);
        img_photo2.setOnClickListener(this);
        img_photo3.setOnClickListener(this);
        img_photo4.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        obtainevidencedialog.show();
        obtainevidencedialog.setContentView(view);
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        int width = (int) (dm.widthPixels * 0.7);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        obtainevidencedialog.getWindow().setLayout(width, height);

        photoAdapter = new PhotoAdapter(this, selectedPhotos1);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);
        photoAdapter2 = new PhotoAdapter(this, selectedPhotos2);
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
        recyclerView2.setAdapter(photoAdapter2);

        photoAdapter3 = new PhotoAdapter(this, selectedPhotos3);
        recyclerView3.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
        recyclerView3.setAdapter(photoAdapter3);

        photoAdapter4 = new PhotoAdapter(this, selectedPhotos4);
        recyclerView4.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
        recyclerView4.setAdapter(photoAdapter4);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8001 && resultCode == Activity.RESULT_OK) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            setPhoto_paths(photos, 8001);
        } else if (requestCode == 8002 && resultCode == Activity.RESULT_OK) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            setPhoto_paths(photos, 8002);
        }else if (requestCode == 8003 && resultCode == Activity.RESULT_OK) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            setPhoto_paths(photos, 8003);
        }else if (requestCode == 8004 && resultCode == Activity.RESULT_OK) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            setPhoto_paths(photos, 8004);
        }
    }
    private void setPhoto_paths(List<String> list, int result) {
        if (result == 8001) {
            selectedPhotos1.clear();
            selectedPhotos1.addAll(list);
            photoAdapter.notifyDataSetChanged();
        } else if (result == 8002) {
            selectedPhotos2.clear();
            selectedPhotos2.addAll(list);
            photoAdapter2.notifyDataSetChanged();
        } else if (result == 8003) {
            selectedPhotos3.clear();
            selectedPhotos3.addAll(list);
            photoAdapter3.notifyDataSetChanged();
        }else if (result == 8004) {
            selectedPhotos4.clear();
            selectedPhotos4.addAll(list);
            photoAdapter4.notifyDataSetChanged();
        }
    }

    private boolean isAisNodata;
    private boolean isCerNodata;
    private boolean isNatNodata;
    private boolean isOtherNodata;
    @Override
    public void noticeCerMain(String fragment, boolean isNodata) {
        if("ais".equals(fragment)){
            isAisNodata=isNodata;
        }
        if("certificate".equals(fragment)){
            isCerNodata=isNodata;
        }
        if("nationalShipCertificate".equals(fragment)){
            isNatNodata=isNodata;
        }
        if(isShowOtherCertificate){
            if("other".equals(fragment)){
                isOtherNodata=isNodata;
            }
            if(isAisNodata&&isCerNodata&&isNatNodata&&isOtherNodata){
                table_titleBar_tx_edit.setVisibility(View.GONE);
            }else {
                table_titleBar_tx_edit.setVisibility(View.VISIBLE);
            }
        }else {
            if(isAisNodata&&isCerNodata&&isNatNodata){
                table_titleBar_tx_edit.setVisibility(View.GONE);
            }else {
                table_titleBar_tx_edit.setVisibility(View.VISIBLE);
            }
        }



    }
}
