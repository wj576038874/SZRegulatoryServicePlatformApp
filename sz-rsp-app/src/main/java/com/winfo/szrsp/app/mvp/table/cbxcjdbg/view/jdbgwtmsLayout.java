package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: jdbgwtmsLayout
 * @Author: lsj
 * @Date: 2017/12/4  13:18
 * @Description:
 * @Version:
 */
public class jdbgwtmsLayout extends LinearLayout {
    private TextView tvIndex;
    private Spinner sp_clyj;
    private EditText edt_wtms;
    private EditText edt_bz;
    private List<String> sp_data;
    private String cljd;
    private String itemNum;
    private String inputStr;

    public jdbgwtmsLayout(Context context, String itemNum, String inputStr) {
        super(context);
        this.itemNum = itemNum;
        this.inputStr = inputStr;
        LayoutInflater.from(context).inflate(R.layout.item_jdbg, this);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        sp_clyj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cljd = sp_data.get(i);
                String code = cljd.substring(0, 2);
                if (code.equals("19")) {
                    edt_bz.setEnabled(true);
                    edt_bz.setText("");
                    edt_bz.setHint("请填写期限");
                } else if (code.equals("99")) {
                    edt_bz.setEnabled(true);
                    edt_bz.setText("");
                    edt_bz.setHint("请填写说明");
                } else {
                    edt_bz.setEnabled(false);
                    edt_bz.setText("无");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initData() {
        sp_data = new ArrayList<>();
        sp_data.add("01 警示教育");
        sp_data.add("02 停止作业");
        sp_data.add("03 立案调查");
        sp_data.add("09 违法记分");
        sp_data.add("17 开航前纠正");
        sp_data.add("19 在开航后限定的期限内纠正(需标注期限)");
        sp_data.add("99 文字说明");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SzRspApplication.getContext(), R.layout.spinner_item_1, sp_data);
        adapter.setDropDownViewResource(R.layout.dropdown_style_1);
        sp_clyj.setAdapter(adapter);
    }

    private void initView() {
        tvIndex = findViewById(R.id.tvIndex);
        tvIndex.setText(itemNum);
        sp_clyj = findViewById(R.id.spinner_clyj);
        edt_wtms = findViewById(R.id.edt_wtms);
        edt_wtms.setText(inputStr);
        edt_bz = findViewById(R.id.edt_bz);
    }

    public cbxcjdbgData.detail getData() {
        cbxcjdbgData.detail detail = new cbxcjdbgData.detail();
        if (edt_wtms.getText().toString().trim().equals("")) {
            ToastUtils.showToast(SzRspApplication.getContext(), "请填写船舶现场监督报告问题描述！");
            return null;
        } else {
            detail.setSeqNo(tvIndex.getText().toString());
            detail.setDescription(edt_wtms.getText().toString());
            detail.setCommentCode(cljd.substring(0, 2));
            String code = cljd.substring(0, 2);
            if (code.equals("19") || code.equals("99")) {
                detail.setCommentDesc(edt_bz.getText().toString());
            } else {
                detail.setCommentDesc(cljd.substring(3));
            }
            return detail;
        }
    }
}
