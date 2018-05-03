package com.winfo.szrsp.app.mvp.task.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.TaskAdapter;
import com.winfo.szrsp.app.mvp.task.presenter.TaskListPresenter;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskListData;
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

public class TaskListActivity extends Activity implements View.OnClickListener,ITaskListActivity {

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
    private TaskAdapter taskAdapter;
    private PopupWindow popupWindow;
    private View popView;

    private TaskListPresenter  presenter;
    private TaskListData taskListData;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;

    private List<TaskInfo> list;


    private int  page=1;
    private boolean bol= false;//判断加载全部数据
    private boolean showFaile = false;//判断是否加载失败

    private boolean isReflash=false;
    @Override
    protected void onStart() {
        super.onStart();
        if (taskAdapter!=null){
            taskAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        page=1;
        this.list.clear();
        presenter.getTaskListData("1","20","2,7",false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ButterKnife.bind(this);
        initData();
        initEvent();
        alertPop();
    }

    private void initData() {
        taskListData=new TaskListData();
        list=new ArrayList<>();
        taskListData.setList(list);


        mRefreshLayout.setEnableRefresh(true);
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader)mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time = format.format(new Date());
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于"+time));
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);
        presenter=new TaskListPresenter(this);
        presenter.getTaskListData(""+page,"20","2,7",true);
    }

    private void initEvent() {

        titleBar_imgbtn_back.setOnClickListener(this);

        //titleBar_titleText.setOnClickListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    Intent intent = new Intent(TaskListActivity.this,TaskdetailsActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("task_detail_data", taskListData.getList().get(i));
                    startActivity(intent);

            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                    presenter.getTaskListData(""+page,"20","2,7",false);

            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isReflash=true;
                presenter.getTaskListData("1","20","2,7",false);
            }
        });


        loadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.getTaskListData(""+page,"20","2,7",true);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
//            case R.id.titleBar_titleText:
//
//                popupWindow.showAsDropDown(titleBar_imgbtn_back, 0, 0);
//                this.activityAlphaParams = getWindow().getAttributes();
//                if (popupWindow.isShowing()) {
//                    activityAlphaParams.alpha = 0.5f;
//                    getWindow().setAttributes(activityAlphaParams);
//                }
//                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                    @Override
//                    public void onDismiss() {
//                        activityAlphaParams.alpha = 1f;
//                        getWindow().setAttributes(activityAlphaParams);
//                    }
//                });
//
//                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void alertPop() {
        //初始化视图
        LayoutInflater from = LayoutInflater.from(TaskListActivity.this);
        popView = from.inflate(R.layout.pop_tasklx, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.pyxc_popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
    }

    @Override
    public void showOnFaile(String msg) {
        notfoundView.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.GONE);
        loadFailedView.setVisibility(View.VISIBLE);
        tv_loadfile.setText(msg);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void notFound() {
        ToastUtils.showToast(this,"无任务");
    }

    @Override
    public Dialog getDailog() {
        Dialog dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        return dialog;
    }

    @Override
    public void setTaskListData(TaskListData data) {

        if(isReflash){
            showFaile = true;
            int page=data.getPageNum();//当前页
            int pagenum=data.getPages();//总页数
            isReflash=false;
            taskListData=data;
            this.list = data.getList();
            notfoundView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            loadFailedView.setVisibility(View.GONE);
            mRefreshLayout.finishRefresh();
            this.page=2;
            taskAdapter = new TaskAdapter(this,this.list,R.layout.gettask_item);
            lv.setAdapter(taskAdapter);
            if (page == pagenum){
                //ToastUtils.showToast(this,"数据全部加载完毕！");
                mRefreshLayout.setLoadmoreFinished(true);
                bol = true;
            }
            taskAdapter.notifyDataSetChanged();
        }else {
            showFaile = true;
            int page=data.getPageNum();//当前页
            int pagenum=data.getPages();//总页数
            if (this.list.size() == 0){
                taskListData=data;
                this.list = data.getList();
                notfoundView.setVisibility(View.GONE);
                mRefreshLayout.setVisibility(View.VISIBLE);
                loadFailedView.setVisibility(View.GONE);
                mRefreshLayout.finishLoadmore();
                this.page=page+1;
                taskAdapter = new TaskAdapter(this,this.list,R.layout.gettask_item);
                lv.setAdapter(taskAdapter);
                if (page == pagenum){
                    //ToastUtils.showToast(this,"数据全部加载完毕！");
                    mRefreshLayout.setLoadmoreFinished(true);
                    bol = true;
                }
                taskAdapter.notifyDataSetChanged();
            }else {
                this.list.addAll(data.getList()) ;
                taskListData.setList(list);
                notfoundView.setVisibility(View.GONE);
                mRefreshLayout.setVisibility(View.VISIBLE);
                loadFailedView.setVisibility(View.GONE);
                this.page=page+1;
                mRefreshLayout.finishLoadmore();
                if (page == pagenum){
                    //ToastUtils.showToast(this,"数据全部加载完毕！");
                    mRefreshLayout.setLoadmoreFinished(true);
                    bol = true;
                }
                taskAdapter.notifyDataSetChanged();
            }
        }



    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }
}
