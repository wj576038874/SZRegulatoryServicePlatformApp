//package com.winfo.szrsp.app.mvp.certificate.view;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.OrientationHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.util.DisplayMetrics;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
//import com.scwang.smartrefresh.layout.header.ClassicsHeader;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//import com.winfo.szrsp.app.activity.MainActivity;
//import com.winfo.szrsp.app.adapter.CertificateAdapter;
//import com.winfo.szrsp.app.R;
//import com.winfo.szrsp.app.adapter.PhotoAdapter;
//import com.winfo.szrsp.app.mvp.certificate.presenter.CertificatePresenter;
//import com.winfo.szrsp.app.mvp.mine.login.view.impl.UserLoginActivity;
//import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
//import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
//import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;
//import com.winfo.szrsp.app.utils.DialogUtils;
//import com.winfo.szrsp.app.utils.LoginUtils;
//import com.winfo.szrsp.app.utils.PreferenceUtils;
//import com.winfo.szrsp.app.utils.ToastUtils;
//import com.winfo.szrsp.app.widget.MyGridView;
//import com.winfo.szrsp.app.widget.MyIconButton;
//
//import java.lang.ref.WeakReference;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Random;
//
//import me.iwf.photopicker.PhotoPicker;
//
///**
// * @ProjectName: SZRegulatoryServicePlatformApp
// * @Package: com.winfo.szrsp.app.mvp.certificate.view
// * @Filename: CertificateActvity
// * @Author: lsj
// * @Date: 2018/3/26  14:39
// * @Description:
// * @Version: 证书列表
// */
//public class CertificateActvity extends Activity implements ICertificateActvity,View.OnClickListener{
//    private TextView tv_title;
//    private TextView tv_upload;
//    private ImageButton img_back;
//    private MyGridView gridView;
//    private View faile_view;
//    private TextView tv_faile;
//    private Dialog dialog;
//    private  CertificatePresenter presenter;
//    private  Ais ais;
//    private CertificateAdapter adapter;
//
//    private TextView table_titleBar_tx_edit;
//    private TextView table_titleBar_tx_delete;
//    private SmartRefreshLayout mRefreshLayout;
//    private ClassicsHeader mClassicsHeader;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_certificate);
//        initView();
//        initData();
//        initEvent();
//    }
//
//    private void initEvent() {
//        img_back.setOnClickListener(this);
//        tv_upload.setOnClickListener(this);
//        table_titleBar_tx_edit.setOnClickListener(this);
//        table_titleBar_tx_delete.setOnClickListener(this);
//        faile_view.setOnClickListener(this);
//        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                presenter.loadCertificateData(ais.getID(),"ais",false);
//            }
//        });
//    }
//
//    private void initData() {
//        tv_upload.setVisibility(View.VISIBLE);
//        tv_upload.setText("上传");
//        tv_title.setText("证书");
//        ais = (Ais) getIntent().getSerializableExtra("aisdata");
//        presenter.loadCertificateData(ais.getID(),"ais",true);
//        mRefreshLayout.setEnableRefresh(true);
//    }
//
//    private void initView() {
//        presenter = new CertificatePresenter(this);
//        dialog = DialogUtils.createLoadingDialog(this,"加载中...");
//        tv_title = findViewById(R.id.table_titleBar_titleText);
//        tv_upload = findViewById(R.id.table_titleBar_tx_refresh);
//        img_back = findViewById(R.id.table_titleBar_imgbtn_back);
//        gridView = findViewById(R.id.gd_view);
//        faile_view = findViewById(R.id.layout_faile);
//        tv_faile = faile_view.findViewById(R.id.id_tv_data_load_faild);
//        table_titleBar_tx_edit =findViewById(R.id.table_titleBar_tx_edit);
//        table_titleBar_tx_delete=findViewById(R.id.table_titleBar_tx_delete);
//        mRefreshLayout=findViewById(R.id.refreshLayout);
//        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
//        mClassicsHeader = (ClassicsHeader)mRefreshLayout.getRefreshHeader();
//        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
//        String time = format.format(new Date());
//        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于"+time));
//        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.table_titleBar_imgbtn_back:
//                finish();
//                break;
//            case R.id.table_titleBar_tx_refresh:
//                ObtainevidenceDialog();//上传
//                break;
//            case R.id.table_titleBar_tx_edit:
//                if(table_titleBar_tx_edit.getText().toString().equals("编辑")){
//                    table_titleBar_tx_edit.setText("取消");
//                    table_titleBar_tx_delete.setVisibility(View.VISIBLE);
//                    if(adapter!=null){
//                        adapter.setIsShowCheck(true);
//                    }
//
//                }else {
//                    table_titleBar_tx_edit.setText("编辑");
//                    table_titleBar_tx_delete.setVisibility(View.GONE);
//                    if(adapter!=null){
//                        adapter.setIsShowCheck(false);
//                        adapter.setDatasAllNoCheck();
//                    }
//
//                }
//
//                break;
//            case R.id.table_titleBar_tx_delete:
//               List<CertificateDetatils> certificateDetatilsList= adapter.getDatas();
//                String itemId="";
//                for (int i = 0; i < certificateDetatilsList.size(); i++) {
//
//                   if(certificateDetatilsList.get(i).isSelect()){
//                       itemId=itemId+certificateDetatilsList.get(i).getId()+",";
//                   }
//                }
//                if(itemId.length()==0){
//                    ToastUtils.showToast(this,"请勾选删除");
//                    return;
//                }
//                String id=itemId.substring(0,itemId.length()-1);
////                ToastUtils.showToast(this,taskTypeId);
//                presenter.deleteCertificate(id,true);
//                break;
//            case R.id.img_sub_photo1:
//                PhotoPicker.builder()
//                        .setPhotoCount(9)
//                        .setGridColumnCount(4)
//                        .setSelected(selectedPhotos1)
//                        .start(this, 8001);
//                break;
//            case R.id.img_sub_photo2:
//                PhotoPicker.builder()
//                        .setPhotoCount(9)
//                        .setGridColumnCount(4)
//                        .setSelected(selectedPhotos2)
//                        .start(this, 8002);
//                break;
//            case R.id.img_sub_photo3:
//                PhotoPicker.builder()
//                        .setPhotoCount(9)
//                        .setGridColumnCount(4)
//                        .setSelected(selectedPhotos3)
//                        .start(this, 8003);
//                break;
//            case R.id.btn_sub_photo:
//                if (PreferenceUtils.getBoolean(this, "islogin", false)) {
//                    if (selectedPhotos1.size() > 0 || selectedPhotos2.size() > 0|| selectedPhotos3.size() > 0) {
//                        dialog.show();
//                        presenter.uolpadCertificate(ais,selectedPhotos1,selectedPhotos2,selectedPhotos3);
//                    } else {
//                        ToastUtils.showToast(this, "请先选中照片再点击上传！");
//                    }
//                } else {
//                    startActivity(new Intent(this, UserLoginActivity.class));
//                }
//                break;
//            case R.id.imgbtn_close:
//                obtainevidencedialog.dismiss();
//                break;
//            case R.id.layout_faile:
//                presenter.loadCertificateData(ais.getID(),"ais",true);
//                break;
//
//
//        }
//    }
//
//    @Override
//    public Dialog getDialog() {
//        return dialog;
//    }
//
//    @Override
//    public void OnSucess(CertificateInfo certificateInfo) {
//        mRefreshLayout.finishRefresh();
//        mRefreshLayout.setLoadmoreFinished(true);
//        if(certificateInfo!=null&&certificateInfo.getAis().size()>0){
//            gridView.setVisibility(View.VISIBLE);
//            faile_view.setVisibility(View.GONE);
//            adapter = new CertificateAdapter(CertificateActvity.this,certificateInfo.getAis(),R.layout.certificate_item);
//            gridView.setAdapter(adapter);
//            table_titleBar_tx_edit.setVisibility(View.VISIBLE);
//        }
//
//
//    }
//
//    @Override
//    public void OnFaile(String msg) {
//        gridView.setVisibility(View.GONE);
//        faile_view.setVisibility(View.VISIBLE);
//        table_titleBar_tx_edit.setVisibility(View.GONE);
//        tv_faile.setText(msg);
//    }
//
//    @Override
//    public void OnLoginExpired(String msg) {
//        LoginUtils.loginOutShowDialog(this,msg,msg);
//    }
//    private Handler mHandler = new Handler(new Handler.Callback() {
//
//        @Override
//        public boolean handleMessage(Message msg) {
//            if(msg.what==1000){
//                table_titleBar_tx_delete.setVisibility(View.GONE);
//                table_titleBar_tx_edit.setText("编辑");
//                dialog.dismiss();
//                ToastUtils.showToast(CertificateActvity.this,"删除成功");
//                presenter.loadCertificateData(ais.getID(),"ais",false);
//            } else if(msg.what==1001){
//                dialog.dismiss();
//                ToastUtils.showToast(CertificateActvity.this,"上传成功");
//                obtainevidencedialog.dismiss();
//                selectedPhotos1.clear();
//                selectedPhotos2.clear();
//                selectedPhotos3.clear();
//                presenter.loadCertificateData(ais.getID(),"ais",false);
//            }
//            return false;
//        }
//    });
//    @Override
//    public void upLoadSuccess(String msg) {
//        mHandler.sendEmptyMessage(1001);
//    }
//
//    @Override
//    public void deleteSuccess(String msg) {
//        mHandler.sendEmptyMessage(1000);
//    }
//
//    @Override
//    public void upLoadFaile(String msg) {
//        ToastUtils.showToast(this,msg);
//        dialog.dismiss();
//    }
//
//    @Override
//    public void deleteFaile(String msg) {
//        ToastUtils.showToast(this,msg);
//        dialog.dismiss();
//    }
//
//
//    private RecyclerView recyclerView;
//    private PhotoAdapter photoAdapter;
//    private RecyclerView recyclerView2;
//    private PhotoAdapter photoAdapter2;
//    private RecyclerView recyclerView3;
//    private PhotoAdapter photoAdapter3;
//    private Dialog obtainevidencedialog;
//    private ArrayList<String> selectedPhotos1 = new ArrayList<>();//已选中的图片
//    private ArrayList<String> selectedPhotos2 = new ArrayList<>();//已选中的图片
//    private ArrayList<String> selectedPhotos3 = new ArrayList<>();//已选中的图片
//    //上传照片的dialog
//    private void ObtainevidenceDialog() {
//        obtainevidencedialog = new AlertDialog.Builder(this).create();
//        View view = LayoutInflater.from(this).inflate(R.layout.obtainevidence_dialog, null);
//        ImageView img_photo1 = view.findViewById(R.id.img_sub_photo1);
//        ImageView img_photo2 = view.findViewById(R.id.img_sub_photo2);
//        ImageView img_photo3 = view.findViewById(R.id.img_sub_photo3);
//        MyIconButton btn_sub = view.findViewById(R.id.btn_sub_photo);
//        ImageButton btn_close = view.findViewById(R.id.imgbtn_close);
//        recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView2 = view.findViewById(R.id.recycler_view2);
//        recyclerView3 = view.findViewById(R.id.recycler_view3);
//        img_photo1.setOnClickListener(this);
//        img_photo2.setOnClickListener(this);
//        img_photo3.setOnClickListener(this);
//        btn_sub.setOnClickListener(this);
//        btn_close.setOnClickListener(this);
//        obtainevidencedialog.show();
//        obtainevidencedialog.setContentView(view);
//        DisplayMetrics dm = this.getResources().getDisplayMetrics();
//        int width = (int) (dm.widthPixels * 0.7);
//        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        obtainevidencedialog.getWindow().setLayout(width, height);
//
////        Window window = obtainevidencedialog.getWindow();
////        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
////        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//
//
//        photoAdapter = new PhotoAdapter(this, selectedPhotos1);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
//        recyclerView.setAdapter(photoAdapter);
//        photoAdapter2 = new PhotoAdapter(this, selectedPhotos2);
//        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
//        recyclerView2.setAdapter(photoAdapter2);
//
//        photoAdapter3 = new PhotoAdapter(this, selectedPhotos3);
//        recyclerView3.setLayoutManager(new StaggeredGridLayoutManager(9, OrientationHelper.VERTICAL));
//        recyclerView3.setAdapter(photoAdapter3);
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 8001 && resultCode == Activity.RESULT_OK) {
//            List<String> photos = null;
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            }
//            setPhoto_paths(photos, 8001);
//        } else if (requestCode == 8002 && resultCode == Activity.RESULT_OK) {
//            List<String> photos = null;
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            }
//            setPhoto_paths(photos, 8002);
//        }else if (requestCode == 8003 && resultCode == Activity.RESULT_OK) {
//            List<String> photos = null;
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            }
//            setPhoto_paths(photos, 8003);
//        }
//    }
//
//    private void setPhoto_paths(List<String> list, int result) {
//        if (result == 8001) {
//            selectedPhotos1.clear();
//            selectedPhotos1.addAll(list);
//            photoAdapter.notifyDataSetChanged();
//        } else if (result == 8002) {
//            selectedPhotos2.clear();
//            selectedPhotos2.addAll(list);
//            photoAdapter2.notifyDataSetChanged();
//        } else if (result == 8003) {
//            selectedPhotos3.clear();
//            selectedPhotos3.addAll(list);
//            photoAdapter3.notifyDataSetChanged();
//        }
//    }
//}
