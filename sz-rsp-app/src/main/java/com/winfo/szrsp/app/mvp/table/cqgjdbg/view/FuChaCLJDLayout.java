package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.utils.ToastUtils;


/**
 * 复查处理决定
 * Created by HoBo on 2018/4/26.
 */

public class FuChaCLJDLayout extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    private TextView tv_qxdm, tv_qxms, tv_yj, tv_cljd, tv_cjzr;
    private Context mContext;
    private LinearLayout zlLin, gbLin;
    private StateControlData.FlagStateControlDetailBean data;
    private String code, cljd;
    private CheckBox ckRetention1, ckRetention2, ckClose1, ckClose2;
    private String str = "";

    public FuChaCLJDLayout(Context context, StateControlData.FlagStateControlDetailBean detailBean) {
        super(context);
        this.data = detailBean;
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_cljd_fucha, this);
        initView();
        initEvent();
    }

    private void initView() {
        zlLin = findViewById(R.id.zlLin);
        gbLin = findViewById(R.id.gbLin);
        if (30 != Integer.parseInt(data.getComment_code())) {
            zlLin.setVisibility(GONE);
            gbLin.setVisibility(VISIBLE);
        } else {
            zlLin.setVisibility(VISIBLE);
            gbLin.setVisibility(GONE);
        }
        ckRetention1 = findViewById(R.id.ckRetention1);
        ckRetention2 = findViewById(R.id.ckRetention2);
        ckClose1 = findViewById(R.id.ckClose1);
        ckClose2 = findViewById(R.id.ckClose2);
        tv_qxdm = findViewById(R.id.tv_qxdm);
        tv_qxms = findViewById(R.id.tv_qxms);
        tv_yj = findViewById(R.id.tv_yj);
        tv_cljd = findViewById(R.id.tv_cljd);
        tv_cjzr = findViewById(R.id.tv_cjzr);
        tv_qxms.setText(data.getDefect_desc());
        tv_qxdm.setText(data.getDefect_code());
        tv_yj.setText(data.getEnforce_foundation());
        tv_cljd.setText(data.getComment_desc());
        code = data.getDefect_code();
        cljd = data.getComment_code();
    }

    private void initEvent() {
        ckRetention1.setOnCheckedChangeListener(this);
        ckRetention2.setOnCheckedChangeListener(this);
        ckClose1.setOnCheckedChangeListener(this);
        ckClose2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
            case R.id.ckRetention1:
                if (isCheck) {
                    ckRetention1.setChecked(true);
                    ckRetention2.setChecked(false);
                    str = "1";
                    cljd = "10";
                } else {
                    str = "";
                    cljd = data.getComment_code();
                }
                break;
            case R.id.ckRetention2:
                if (isCheck) {
                    ckRetention1.setChecked(false);
                    ckRetention2.setChecked(true);
                    str = "0";
                    cljd = data.getComment_code();
                } else {
                    str = "";
                    cljd = data.getComment_code();
                }
                break;
            case R.id.ckClose1:
                if (isCheck) {
                    ckClose1.setChecked(true);
                    ckClose2.setChecked(false);
                    str = "1";
                    cljd = "10";
                } else {
                    str = "";
                    cljd = data.getComment_code();
                }
                break;
            case R.id.ckClose2:
                if (isCheck) {
                    ckClose1.setChecked(false);
                    ckClose2.setChecked(true);
                    str = "0";
                    cljd = data.getComment_code();
                } else {
                    str = "";
                    cljd = data.getComment_code();
                }
                break;

        }
    }

    public jdbgData.detail getData() {
        jdbgData.detail detail = new jdbgData.detail();
        if ("30".equals(data.getComment_code())) {
            if (!ckRetention1.isChecked() && !ckRetention2.isChecked()) {
                ToastUtils.showToast(mContext, "请勾选复查决定");
                return null;
            }
        } else {
            if (!ckClose1.isChecked() && !ckClose2.isChecked()) {
                ToastUtils.showToast(mContext, "请勾选复查决定");
                return null;
            }
        }
        detail.setDefectCode(code);
        detail.setDefectDesc(tv_qxms.getText().toString());
        detail.setEnforceFoundation(tv_yj.getText().toString());
        detail.setCorrectMark(str);
        detail.setCommentCode(cljd);
        return detail;
    }

}
