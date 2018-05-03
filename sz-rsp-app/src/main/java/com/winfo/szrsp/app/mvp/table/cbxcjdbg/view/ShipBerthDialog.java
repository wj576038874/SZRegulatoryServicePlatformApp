package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

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
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter.jdbgPresenter;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HoBo on 2018/4/25.
 */

public class ShipBerthDialog implements IJdbgActivity {
    private View inspectionView;
    private Dialog dialogInspection;
    private EditText etContext;
    private Button butSearch;
    private LinearLayout linSearch;
    private LinearLayout linContent;
    private ListView reSearch;
    private Context mContext;
    private Button btnCancel, btnSubmit;
    private RecyclerView recyclerView;
    private List<MultiItemEntity> inspectionAll;
    private ExpandableShipBerthAdapter adapter;
    private jdbgPresenter presenter;
    private String taskContent;
    private String taskCode;
    private OnGetData mOnGetData;
    private Dialog dialog;
    private ShipBerthSearchAdapter searchAdapter;
    private List<ShipBerthData> searchData;

    public ShipBerthDialog(Context context, List<MultiItemEntity> datas) {
        mContext = context;
        this.inspectionAll = datas;
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
        searchData = new ArrayList<>();
        presenter = new jdbgPresenter(this);
        dialog = DialogUtils.createLoadingDialog(mContext, "请稍后...");
        linContent = inspectionView.findViewById(R.id.ll_content);
        linSearch = inspectionView.findViewById(R.id.ll_search);
        reSearch = inspectionView.findViewById(R.id.rv_search);
        recyclerView = inspectionView.findViewById(R.id.rv);
        btnCancel = inspectionView.findViewById(R.id.btnCancel);
        btnSubmit = inspectionView.findViewById(R.id.btnSubmit);
        etContext = inspectionView.findViewById(R.id.etContext);
        butSearch = inspectionView.findViewById(R.id.butSearch);
        adapter = new ExpandableShipBerthAdapter(inspectionAll, mContext);
        final GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recyclerView.setAdapter(adapter);
        adapter.expandAll();
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
                    presenter.getBerthData(dialog, etContext.getText().toString().trim(), true);
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

    public OnSearchShipData onSearchShipData;

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSuccess(String msg) {

    }

    @Override
    public void OnFaile(String msg) {

    }

    @Override
    public void setDetailData(DetailcbxcjdbgData detailData) {

    }

    @Override
    public void setShipBerthData(List<ShipBerthData> datas) {
        dialog.dismiss();
        taskCode = "";
        taskContent = "";
        searchData = datas;
        linContent.setVisibility(View.GONE);
        linSearch.setVisibility(View.VISIBLE);
        searchAdapter = new ShipBerthSearchAdapter(mContext, searchData);
        reSearch.setAdapter(searchAdapter);
        reSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (ShipBerthData bean : searchData) {
                    bean.setChecked(false);
                }
                searchData.get(i).setChecked(true);
                searchAdapter.notifyDataSetChanged();
                taskContent = searchData.get(i).getName();
                taskCode = searchData.get(i).getId();
            }
        });
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(mContext, msg, msg);
    }

    @Override
    public void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList) {

    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {

    }


    public interface OnSearchShipData {
        void onSearchDataCallBack(String taskCode, String taskContent);
    }

    public void setOnSearchShipData(OnSearchShipData onSearchShipData) {
        this.onSearchShipData = onSearchShipData;
    }

    public interface OnGetData {
        void onDataCallBack(Map<String, Object> map);
    }

    public void setOnData(OnGetData gd) {
        mOnGetData = gd;
    }

    public void show() {
        dialogInspection.show();
    }

}
