package com.winfo.szrsp.app.mvp.plotting.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.adapter.TaskAdapter;
import com.winfo.szrsp.app.adapter.plotting.PlottingAdapter;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.plotting.presenter.PlottingPresenter;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.sdk.entity.plotting.PlottingListData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskListData;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.winfo.szrsp.app.R2.attr.height;


public class PlottingInfoView implements IPlottingView {
    private Context mContext;
    //标绘dialog
    private Dialog plottingInfoDialog;
    private Button plotting_btn_search;
    private EditText plotting_edit;

    //主界面标绘按钮
    private ImageButton imgbtnPlotting;
    private SmartRefreshLayout mRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private TextView tv_loadfile;
    private View loadFailedView;
    private View notfoundView;
    /**
     * 显示数据的recyclerView
     */
    private RecyclerView recyclerView;
    private PlottingAdapter adapter;
    private WinfoDNCView winfoDNCView;
    private PlottingPresenter plottingPresenter;
    Dialog dialog ;
    private Spinner sp_bhlx;
    private List<String> sp_data;
    public int  page=1;
    private  List<Plotting> list;//所有数据

    String drawingType="";
    boolean isSpinnerNeedLoad=false;//防止spinner 在弹出dilog时就加载
    //列表显示所依附的view主界面传过来
    private CoordinatorLayout layout;
    //private AlertDialog descInfoDialog;
    @SuppressLint("CutPasteId")
    public PlottingInfoView(Context context, WinfoDNCView winfoDNCView, ImageButton imgbtnPlotting, CoordinatorLayout layout) {
        this.mContext = context;
        this.winfoDNCView = winfoDNCView;
        this.imgbtnPlotting = imgbtnPlotting;
        this.layout = layout;
        // 初始化列表的视图
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mContext).inflate(R.layout.plotting_info_view, null);
        initView(view);
        initData();
        initEvent();

    }



    @SuppressLint("CutPasteId")
    private void initView(View view) {
        int height = DeviceUtils.getScreenSize((Activity) mContext)[1]; // 屏幕宽度（像素）
        int width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
        plottingInfoDialog = new AlertDialog.Builder(mContext).create();
        plottingInfoDialog.setCancelable(true);//
        plottingInfoDialog.setCanceledOnTouchOutside(true);//
        plottingInfoDialog.show();
        plottingInfoDialog.setContentView(view);
        dialog = DialogUtils.createLoadingDialog(mContext,"加载中...");
        Window window = plottingInfoDialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height =height/2;
        windowparams.width = width;
        window.setWindowAnimations(R.style.photo_popup_anim_style);
        window.setGravity( Gravity.BOTTOM);
        window.setDimAmount(0);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        plotting_edit=view.findViewById(R.id.plotting_edit);
        plotting_btn_search=view.findViewById(R.id.plotting_btn_search);
        sp_bhlx=view.findViewById(R.id.spinner_bhlx);
        sp_bhlx.setDropDownWidth(DimensUtils.dp2px(mContext,80));
        sp_bhlx.setDropDownVerticalOffset(DimensUtils.dp2px(mContext,35));

        // 初始化recycleview
        recyclerView = view.findViewById(R.id.plotting_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRefreshLayout= view.findViewById(R.id.refreshLayout);
        tv_loadfile= view.findViewById(R.id.id_tv_data_load_faild);
        loadFailedView=view.findViewById(R.id.id_tv_data_load_faild);
        notfoundView=view.findViewById(R.id.area_data_notfound);
        mRefreshLayout.setEnableRefresh(false);
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader)mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time = format.format(new Date());
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于"+time));
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);

    }
    private String bhlx="";
    private void initData() {
        list=new ArrayList<>();
        sp_data = new ArrayList<>();
        sp_data.add("全部");
        sp_data.add("点");
        sp_data.add("线");
        sp_data.add("面");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,R.layout.spinner_item_1,sp_data);
        adapter.setDropDownViewResource(R.layout.dropdown_style_1);
        sp_bhlx.setAdapter(adapter);
        plottingPresenter=new PlottingPresenter();
        plottingPresenter.attachMvpView(this);
        //plottingPresenter.getPlottingListData(""+page,"10","","",true);

    }

    private void clearData(){
        list=new ArrayList<>();
        page=1;
        mRefreshLayout.setLoadmoreFinished(false);

    }

    private void initEvent() {
        plotting_btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                plottingPresenter.getPlottingListData(""+page,"10",plotting_edit.getText().toString(),drawingType,true);
            }
        });
        sp_bhlx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearData();
                bhlx = sp_data.get(i);
                if(bhlx.equals("点")){
                    drawingType="1";
                }else if(bhlx.equals("线")){
                    drawingType="2";
                }else if(bhlx.equals("面")){
                    drawingType="3";
                }else {
                    drawingType="";
                }
                if(isSpinnerNeedLoad){
                    plottingPresenter.getPlottingListData(""+page,"10",plotting_edit.getText().toString(),drawingType,true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                plottingPresenter.getPlottingListData(""+page,"10",plotting_edit.getText().toString(),drawingType,false);

            }
        });
        loadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plottingPresenter.getPlottingListData(""+page,"10",plotting_edit.getText().toString(),drawingType,true);
            }
        });
        notfoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plottingPresenter.getPlottingListData(""+page,"10",plotting_edit.getText().toString(),drawingType,true);
            }
        });

    }

    public void dismiss() {
        plottingInfoDialog.dismiss();
    }

    /**
     * 显示标绘的列表数据
     */
    public void showControlArea() {
        String tag = (String) imgbtnPlotting.getTag();
        clearData();
        plottingPresenter.getPlottingListData(""+page,"10",plotting_edit.getText().toString(),drawingType,true);

        plottingInfoDialog.show();
    }

    @Override
    public Dialog getDailog() {
        return dialog;
    }

    @Override
    public void showOnFaile(String msg) {
        isSpinnerNeedLoad=true;
        notfoundView.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.GONE);
        loadFailedView.setVisibility(View.VISIBLE);
        tv_loadfile.setText(msg);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void notFound() {
        ToastUtils.showToast(mContext,"无数据");
    }

    @Override
    public void setPlottingListData(PlottingListData data) {
        isSpinnerNeedLoad=true;
            int page=data.getPageNum();//当前页
            int pagenum=data.getPages();//总页数
            if (data.getList().size() == 0){
                this.list = data.getList();
                notfoundView.setVisibility(View.VISIBLE);
                mRefreshLayout.setVisibility(View.GONE);
                loadFailedView.setVisibility(View.GONE);
                mRefreshLayout.finishLoadmore();
            }else {
                this.list.addAll(data.getList()) ;
                if(winfoDNCView.winfoDNCParams.showPlotting.size()>0){
                    for (int i = 0; i < winfoDNCView.winfoDNCParams.showPlotting.size(); i++) {
                        for (int j = 0; j <list.size() ; j++) {
                            if(winfoDNCView.winfoDNCParams.showPlotting.get(i).getId().equals(list.get(j).getId())){
                                list.get(j).setShowOnDNCView(true);
                            }
                        }

                    }
                }
                notfoundView.setVisibility(View.GONE);
                mRefreshLayout.setVisibility(View.VISIBLE);
                loadFailedView.setVisibility(View.GONE);
                this.page=page+1;
                mRefreshLayout.finishLoadmore();
                if (page == pagenum){
                    //ToastUtils.showToast(this,"数据全部加载完毕！");
                    mRefreshLayout.setLoadmoreFinished(true);
                }
                adapter =new PlottingAdapter(this.list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                setAdapterChildClickListener();
            }
    }
    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(mContext,msg,msg);
    }

    @Override
    public void deleteSuccess(String msg) {
        ToastUtils.showToast(mContext,msg);
//        clearData();
        list.remove(deletePlotting);
        adapter.notifyDataSetChanged();
        if(list.size()==0){
            notfoundView.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishLoadmore();
        }
    }

    @Override
    public void deleteFaile(String msg) {
        ToastUtils.showToast(mContext,msg);
    }

    @Override
    public void addSuccess(String msg) {

    }

    @Override
    public void addFaile(String msg) {

    }

    @Override
    public void editSuccess(String msg) {

    }

    @Override
    public void editFaile(String msg) {

    }

    PlottingEditView plottingEditView;

    public void showEditDialog(Point plottingPoint){
        if(plottingEditView!=null){
            plottingEditView.showEditPlotting(plottingPoint);
        }

    }
    public Dialog getEditDialog(){
        if(plottingEditView==null){
            return null;
        }else {
            return plottingEditView.getEditDailog() ;
        }
    }

    public  boolean   isEditDialogShowing(){

        if(plottingEditView!=null){
            return plottingEditView.isDialogShowing();
        }else {
            return false;
        }

    }
//    public void showEditDialog(){
//        if(plottingEditView!=null){
//            plottingEditView.showEditPlotting();
//        }
//
//    }
    private void setAdapterChildClickListener(){
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.plotting_iv_display:
                            if(list.get(position).isShowOnDNCView()){
                                list.get(position) .setShowOnDNCView(false);
                                winfoDNCView.removePlotting(list.get(position));
                            }else {
                                list.get(position) .setShowOnDNCView(true);
                                winfoDNCView.addPlotting(list.get(position));
                            }
                        adapter.notifyDataSetChanged();

                        break;
                    case R.id.plotting_iv_query:
//                        ToastUtils.showToast(mContext,"点击了第"+position+"的查看详情");
                        PlottingDetailView plottingDetailView=new PlottingDetailView(mContext,list.get(position));
                        plottingDetailView.showAddPlotting();


                        break;
                    case R.id.plotting_iv_edit:
                       // ToastUtils.showToast(mContext,"点击了第"+position+"的编辑");
                        String[] ll = list.get(position).getLatLon().split(",");
                        Point point=new Point();
                        point.lat=Double.valueOf(ll[0]);
                        point.lon=Double.valueOf(ll[1]);
                        winfoDNCView.setCenter(point);
                        winfoDNCView.winfoDNCParams.plottingPoint=point;
                        plottingEditView=new PlottingEditView(mContext,winfoDNCView,plottingInfoDialog,list.get(position));
                        plottingEditView.showEditPlotting();
                        plottingInfoDialog.dismiss();
                        winfoDNCView.isEditPlottingPoint=true;
                        break;
                    case R.id.plotting_iv_delete:
//                        ToastUtils.showToast(mContext,"点击了第"+position+"的删除");

                        DialogUtils.showDialog(mContext, "温馨提示", "确认删除吗？", "确认", "取消", new DialogUtils.DialogOnClickListenner() {
                            @Override
                            public void btnConfirmClick(Dialog dialog) {
                                dialog.dismiss();

                                plottingPresenter.deletePlottingById(list.get(position).getId(),true);
                                deletePlotting=list.get(position);
                            }

                            @Override
                            public void btnCancelClick(Dialog dialog) {
                                dialog.dismiss();

                            }
                        });
                        break;
                }
            }
        });
    }
    private  Plotting deletePlotting;

//    //去重
//    public  List removeDuplicate(List list){
//        List listTemp = new ArrayList();
//        for(int i=0;i<list.size();i++){
//            if(!listTemp.contains(list.get(i))){
//                listTemp.add(list.get(i));
//            }
//        }
//        return listTemp;
//    }
}
