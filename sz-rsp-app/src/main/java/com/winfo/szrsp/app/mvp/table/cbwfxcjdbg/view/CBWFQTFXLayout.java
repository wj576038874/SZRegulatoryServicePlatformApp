package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspectDetail;

/**
 * Created by Guan on 2018-01-27.
 */

public class CBWFQTFXLayout extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private TextView tv_number;
    private EditText et_jcxm;
    private EditText tv_qxms_cbwf;
    private TextView tv_clyj;
    private EditText et_zgjg;

    String number;

    private WFXCChuliDialog wfxcChuliDialog;
    public CBWFQTFXLayout(Context context,String number) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_cbwfqtfx,this);
        this.mContext=context;
        this.number=number;
        initView();
        initData(number);
        initEvent();
    }
    private void initView() {
        tv_number=findViewById(R.id.tv_number);
        et_jcxm=findViewById(R.id.et_jcxm);
        tv_qxms_cbwf=findViewById(R.id.tv_qxms_cbwf);
        tv_clyj=findViewById(R.id.tv_clyj);
        et_zgjg=findViewById(R.id.et_zgjg);
    }


    private void initData(String number) {
        tv_number.setText(number);
    }

    private void initEvent() {
        tv_clyj.setOnClickListener(this);
    }
    public CBWFQTFXLayout(Context context,String number,String title,String qxms,String clyj,String zgjg,boolean isEdit) {
        this(context,number);
        tv_number.setText(number);
        et_jcxm.setText(title);
        tv_qxms_cbwf.setText(qxms);
        tv_clyj.setText(clyj);
        et_zgjg.setText(zgjg);

        if(!isEdit){
            et_jcxm.setFocusable(false);
            et_jcxm.setClickable(false);

            et_zgjg.setFocusable(false);
            et_zgjg.setClickable(false);

            tv_qxms_cbwf.setFocusable(false);
            tv_qxms_cbwf.setClickable(false);

            tv_clyj.setFocusable(false);
            tv_clyj.setClickable(false);

            et_zgjg.setFocusable(false);
            et_zgjg.setClickable(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_clyj:
                showWFXCChuliDialog(tv_clyj);
                break;
        }
    }

    private void showWFXCChuliDialog(final TextView textView) {
        if (wfxcChuliDialog==null){
            wfxcChuliDialog=new WFXCChuliDialog(mContext);
        }

        wfxcChuliDialog.show(textView.getText().toString());
        wfxcChuliDialog.setOnData(new WFXCChuliDialog.OnGetData() {
            @Override
            public void onDataCallBack(String chuli_1) {
                textView.setText(chuli_1);
            }
        });
    }


    public CtSafetySceneInspectDetail getData(){
        CtSafetySceneInspectDetail ctSafetySceneInspectDetail = new CtSafetySceneInspectDetail();
        ctSafetySceneInspectDetail.setSex(number);
        ctSafetySceneInspectDetail.setItemNum(number);
        ctSafetySceneInspectDetail.setItemName(et_jcxm.getText().toString());
        if(tv_qxms_cbwf.getText().toString().equals("")){
            ctSafetySceneInspectDetail.setDescribe("00");
        }else {
            ctSafetySceneInspectDetail.setDescribe(tv_qxms_cbwf.getText().toString());
        }
        ctSafetySceneInspectDetail.setHandle(tv_clyj.getText().toString());
        ctSafetySceneInspectDetail.setRectificat(et_zgjg.getText().toString());

        return ctSafetySceneInspectDetail;
    }
}
