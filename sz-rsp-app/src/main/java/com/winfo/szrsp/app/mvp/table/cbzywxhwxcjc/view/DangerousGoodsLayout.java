package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HoBo on 2018/3/13.
 */

public class DangerousGoodsLayout extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.et_size)
    EditText et_size;
    @BindView(R.id.et_jcdm)
    TextView et_jcdm;
    @BindView(R.id.et_msqx)
    EditText et_msqx;
    @BindView(R.id.et_clyj)
    TextView et_clyj;
    @BindView(R.id.et_zgjg)
    EditText et_zgjg;
    private Context mContext;
    private HandleOpinionDialog handleOpinionDialog;
    private CheckCodeDialog checkCodeDialog;

    public DangerousGoodsLayout(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_danger_goods, this);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        et_jcdm.setOnClickListener(this);
        et_clyj.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_jcdm:
                checkCodeDialog = new CheckCodeDialog(mContext);
                checkCodeDialog.show(et_jcdm.getText().toString());
                checkCodeDialog.setSetStrData(new CheckCodeDialog.SetStrData() {
                    @Override
                    public void setNum(String s) {
                        et_jcdm.setText(s);
                    }
                });
                break;
            case R.id.et_clyj:
                handleOpinionDialog = new HandleOpinionDialog(mContext);
                handleOpinionDialog.show(et_clyj.getText().toString());
                handleOpinionDialog.setSetStrData(new HandleOpinionDialog.SetStrData() {
                    @Override
                    public void setNum(String s) {
                        et_clyj.setText(s);
                    }
                });

        }
    }

    public DangerousGoodsXianChangData.ctDangerPolluteSceneDetail getDetail() {
        DangerousGoodsXianChangData.ctDangerPolluteSceneDetail detail = new DangerousGoodsXianChangData.ctDangerPolluteSceneDetail();
        if ("".equals(et_size.getText().toString().trim())
                && "".equals(et_jcdm.getText().toString().trim())
                && "".equals(et_msqx.getText().toString().trim())
                && "".equals(et_clyj.getText().toString().trim())
                && "".equals(et_zgjg.getText().toString().trim())) {
            return null;
        } else {
            detail.setContainerNumber(et_size.getText().toString().trim());
            detail.setInspectCode(et_jcdm.getText().toString().trim());
            detail.setDescribe(et_msqx.getText().toString().trim());
            detail.setHandle(et_clyj.getText().toString().trim());
            detail.setRectificat(et_zgjg.getText().toString().trim());
            return detail;
        }

    }

}
