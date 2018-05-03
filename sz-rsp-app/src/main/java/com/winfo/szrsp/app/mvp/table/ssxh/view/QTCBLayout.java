package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;

import java.util.Date;

/**
 * Created by Guan on 2017-12-22.
 */

public class QTCBLayout extends LinearLayout implements View.OnClickListener {
    private TextView tv_qtcb_time;
    private  EditText edt_qtcb_cb_name;
    private  TextView  tv_lx;
    private  EditText edt_qtcb_place;
    private  EditText  edt_qtcb_cb_result;
    private MinuteTimePickerView mTimePickerView;
    private Context mContext;

    public QTCBLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_qtcb,this);
        this.mContext=context;
        initView();
        initData();
        initEvent();
    }

    /**
     *
     * @param context
     * @param time
     * @param cb_name
     * @param lx
     * @param qtcb_place
     * @param qtcb_result
     * @param isEdit 是否可点击编辑
     */
    public QTCBLayout(Context context, String time,  String cb_name ,String lx, String qtcb_place, String qtcb_result ,boolean isEdit) {
        this(context);
        tv_qtcb_time.setText(time);
        edt_qtcb_cb_name.setText(cb_name);
        tv_lx.setText(lx);
        edt_qtcb_place.setText(qtcb_place);
        edt_qtcb_cb_result.setText(qtcb_result);

        if(!isEdit){
            tv_qtcb_time.setFocusable(false);
            tv_qtcb_time.setClickable(false);

            edt_qtcb_cb_name.setFocusable(false);
            edt_qtcb_cb_name.setClickable(false);

            tv_lx.setFocusable(false);
            tv_lx.setClickable(false);

            edt_qtcb_place.setFocusable(false);
            edt_qtcb_place.setClickable(false);

            edt_qtcb_cb_result.setFocusable(false);
            edt_qtcb_cb_result.setClickable(false);
        }


    }
    private void initView() {
        tv_qtcb_time=findViewById(R.id.tv_qtcb_time);
        edt_qtcb_cb_name=findViewById(R.id.edt_qtcb_cb_name);
        tv_lx=findViewById(R.id.tv_lx);
        edt_qtcb_place=findViewById(R.id.edt_qtcb_place);
        edt_qtcb_cb_result=findViewById(R.id.edt_qtcb_cb_result);
    }

    private void initData() {
        mTimePickerView= new MinuteTimePickerView(mContext, MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());

    }
    private void initEvent() {
        tv_qtcb_time.setOnClickListener(this);
        tv_lx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_qtcb_time:
                showTimePickView();
                break;
            case R.id.tv_lx:
                showDailog();
                break;
        }
    }
    Dialog ShipLXDialog;
    int magin;
    int width;
    private void showDailog() {
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
        magin = DimensUtils.dp2px(mContext, 160);
        ShipLXDialog = new AlertDialog.Builder(mContext).create();
        ShipLXDialog.setCancelable(true);
        ShipLXDialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(mContext).inflate(R.layout.shiplx_layout, null);
        final RadioButton rb1 = view.findViewById(R.id.rb_1);
        final RadioButton rb2 = view.findViewById(R.id.rb_2);
        final RadioButton rb3 =  view.findViewById(R.id.rb_3);
        final RadioButton rb4 =  view.findViewById(R.id.rb_4);
        final RadioButton rb5 =  view.findViewById(R.id.rb_5);
        final RadioButton rb6 =  view.findViewById(R.id.rb_6);
        Button btn_qx = view.findViewById(R.id.btnCancel);
        Button btn_qr = view.findViewById(R.id.btnSubmit);
        ShipLXDialog.show();
        ShipLXDialog.setContentView(view);
        Window window = ShipLXDialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width-2*magin;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((android.view.WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()){
                    tv_lx.setText("客船");
                }else if (rb2.isChecked()){
                    tv_lx.setText("油船");
                }else if (rb3.isChecked()){
                    tv_lx.setText("货船");
                }else if (rb4.isChecked()){
                    tv_lx.setText("公务船");
                }else if (rb5.isChecked()){
                    tv_lx.setText("捕捞船");
                }else if (rb6.isChecked()){
                    tv_lx.setText("其它");
                }
                ShipLXDialog.dismiss();
            }
        });

        btn_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShipLXDialog.dismiss();
            }
        });
    }

    public void setTimerType(MinuteTimePickerView.Type type){
        mTimePickerView.setType(type);
    }

    public  QTCB getData(){
        QTCB qtcb=new QTCB();
        qtcb.setLx(tv_lx.getText().toString());
        qtcb.setQtcb_cb_name(edt_qtcb_cb_name.getText().toString());
        qtcb.setQtcb_place(edt_qtcb_place.getText().toString());
        qtcb.setQtcb_cb_result(edt_qtcb_cb_result.getText().toString());
        qtcb.setTime(tv_qtcb_time.getText().toString());
        return qtcb;
    }

    public class QTCB{
        private String time;
        private String qtcb_cb_name;
        private String lx;
        private String qtcb_place;
        private String qtcb_cb_result;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getQtcb_cb_name() {
            return qtcb_cb_name;
        }

        public void setQtcb_cb_name(String qtcb_cb_name) {
            this.qtcb_cb_name = qtcb_cb_name;
        }

        public String getLx() {
            return lx;
        }

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getQtcb_place() {
            return qtcb_place;
        }

        public void setQtcb_place(String qtcb_place) {
            this.qtcb_place = qtcb_place;
        }

        public String getQtcb_cb_result() {
            return qtcb_cb_result;
        }

        public void setQtcb_cb_result(String qtcb_cb_result) {
            this.qtcb_cb_result = qtcb_cb_result;
        }
    }

    /**
     *
     *  当前选择时间的TextView
     */
    private  void  showTimePickView(){
        String trim = tv_qtcb_time.getText().toString().trim();
        mTimePickerView.setPicker(trim);
        mTimePickerView.show();
        mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                tv_qtcb_time.setText(time);
            }
        });
    }

}
