package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CruiseShipSearchAdapter;
import com.winfo.szrsp.app.mvp.table.xhgzjl.presenter.CruiseWorkRecordPresenter;
import com.winfo.szrsp.app.mvp.task.view.Level0Item;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.mvp.task.view.Level2Item;
import com.winfo.szrsp.app.mvp.task.view.Level3Item;
import com.winfo.szrsp.app.mvp.task.view.Level4Item;
import com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.ExpandableTaskInspectionAdapter;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by winfo053 on 2018/3/9.
 */

public class InspectionAllDialog implements ICruiseWorkRecordActivity {
    private View inspectionView;
    private Dialog dialogInspection;
    private EditText etContext;
    private Button butSearch;
    private Dialog dialog;
    private CruiseWorkRecordPresenter presenter;
    private List<TaskInspectionItemByNameData> searchShipTaskList;
    private LinearLayout linSearch;
    private LinearLayout linContent;
    private ListView reSearch;
    Context mContext;
    Button btnCancel, btnSubmit;
    RecyclerView recyclerView;
    List<MultiItemEntity> inspectionAll;
    CruiseShipSearchAdapter searchAdapter;
    ExpandableAllInspectionAdapter adapter;
    List<MultiItemEntity> inspectionSelect;
    // 数据接口
    OnGetData mOnGetData;
    int[] selectPosition;
    private String taskContent;
    private String taskCode;

    public InspectionAllDialog(Context context, List<MultiItemEntity> inspectionAll) {
        this.selectPosition = selectPosition;
        mContext = context;
        this.inspectionAll = inspectionAll;
        dialogInspection = new AlertDialog.Builder(mContext).create();
        dialogInspection.setCancelable(true);//
        dialogInspection.setCanceledOnTouchOutside(false);//
        inspectionView = LayoutInflater.from(mContext).inflate(R.layout.dialog_inspection, null);
        dialogInspection.show();
        dialogInspection.setContentView(inspectionView);
        Window window = dialogInspection.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initEvent();
    }


    private void initView() {
        searchShipTaskList = new ArrayList<>();
        dialog = DialogUtils.createLoadingDialog(mContext, "搜索中...");
        presenter = new CruiseWorkRecordPresenter();
        presenter.attachMvpView(this);
        linContent = inspectionView.findViewById(R.id.ll_content);
        linSearch = inspectionView.findViewById(R.id.ll_search);
        reSearch = inspectionView.findViewById(R.id.rv_search);
        recyclerView = inspectionView.findViewById(R.id.rv);
        btnCancel = inspectionView.findViewById(R.id.btnCancel);
        btnSubmit = inspectionView.findViewById(R.id.btnSubmit);
        etContext = inspectionView.findViewById(R.id.etContext);
        butSearch = inspectionView.findViewById(R.id.butSearch);
        adapter = new ExpandableAllInspectionAdapter(inspectionAll, mContext);
        final GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recyclerView.setAdapter(adapter);
        adapter.expandAll();
//        if(selectPosition!=10086){
//            adapter.expand(selectPosition);
//        }

        recyclerView.setLayoutManager(manager);
    }

    private void initEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInspection.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linContent.isShown() && linSearch.isEnabled()) {
                    Map<String, Object> stringObjectMap = adapter.returnData();
                    if (stringObjectMap.get("name").equals("")) {
                        ToastUtils.showToast(mContext, "未选择！");
                        return;
                    }
                    mOnGetData.onDataCallBack(adapter.returnData());
                    dialogInspection.dismiss();
                } else {
                    if ("".equals(taskContent) && "".equals(taskCode)) {
                        ToastUtils.showToast(mContext, "未选择！");
                        return;
                    } else {
                        if (onSearchShipData != null) {
                            onSearchShipData.onSearchDataCallBack(taskCode, taskContent);
                            dialogInspection.dismiss();
                        }
                        return;
                    }

                }
            }
        });
        butSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(etContext.getText().toString().trim())) {
                    ToastUtils.showToast(mContext, "请输入关键字！");
                    return;
                } else {
                    presenter.getTaskInspectionByName(etContext.getText().toString().trim());
                }
            }
        });
        etContext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ("".equals(etContext.getText().toString().trim())) {
                    linContent.setVisibility(View.VISIBLE);
                    linSearch.setVisibility(View.GONE);
                    taskCode = "";
                    taskContent = "";
                }
            }
        });
    }

    public void showDialog() {
        dialogInspection.show();
    }

    public void dismiss() {
        dialogInspection.dismiss();
    }

    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSuccess(String s, String resultData) {

    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
        ToastUtils.showToast(mContext, msg);
    }

    @Override
    public CruiseWorkData getData() {
        return null;
    }

    @Override
    public void setDetailData(String msg, CruiseWorkData data) {

    }

    @Override
    public void loginExpired(String msg) {

    }

    @Override
    public void setInspectionAll(List<TaskInspectionItemData> taskInspectionItemData) {

    }

    @Override
    public void onLoadFaild(String msg) {

    }

    @Override
    public void setShip(List<CruiseShipData> cruiseShipData) {

    }

    @Override
    public void onFindShipFaile(String msg) {

    }

    @Override
    public void setInspectionByName(List<TaskInspectionItemByNameData> data) {
        dialog.dismiss();
        taskCode = "";
        taskContent = "";
        searchShipTaskList = data;
        linContent.setVisibility(View.GONE);
        linSearch.setVisibility(View.VISIBLE);
        searchAdapter = new CruiseShipSearchAdapter(mContext, searchShipTaskList);
        reSearch.setAdapter(searchAdapter);
        reSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (TaskInspectionItemByNameData bean : searchShipTaskList) {
                    bean.setCheckeds(false);
                }
                searchShipTaskList.get(i).setCheckeds(true);
                searchAdapter.notifyDataSetChanged();
                taskContent = searchShipTaskList.get(i).getItemFuseName();
                taskCode = searchShipTaskList.get(i).getItemFuseId();
            }
        });
    }

    public OnSearchShipData onSearchShipData;


    public interface OnSearchShipData {
        void onSearchDataCallBack(String taskCode, String taskContent);
    }

    public void setOnSearchShipData(OnSearchShipData onSearchShipData) {
        this.onSearchShipData = onSearchShipData;
    }

    public interface OnGetData {
        void onDataCallBack(Map<String, Object> map);
    }
}
