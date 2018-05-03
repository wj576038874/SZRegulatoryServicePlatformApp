package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;

import java.util.Date;

/**
 * Created by Guan on 2017-12-22.
 */

public class SGZYLayout extends LinearLayout implements View.OnClickListener {
    private TextView tv_sgzy_time;
    private  EditText edt_sg_work_name;
    private  EditText  edt_sg_cb_name;
    private  EditText edt_sgzy_place;
    private  EditText  edt_sgzy_result;
    private MinuteTimePickerView mTimePickerView;
    private Context mContext;

    public SGZYLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_sgzy,this);
        this.mContext=context;
        initView();
        initData();
        initEvent();
    }

    /**
     *
     * @param context
     * @param time
     * @param work_name
     * @param cb_name
     * @param sgzy_place
     * @param sgzy_result
     * @param isEdit 是否可点击编辑
     */
    public SGZYLayout(Context context, String time, String work_name,String cb_name ,String sgzy_place,String sgzy_result,boolean isEdit ) {
        this(context);

        tv_sgzy_time.setText(time);
        edt_sg_work_name.setText(work_name);
        edt_sg_cb_name.setText(cb_name);
        edt_sgzy_place.setText(sgzy_place);
        edt_sgzy_result.setText(sgzy_result);
        if(!isEdit){
            tv_sgzy_time.setFocusable(false);
            tv_sgzy_time.setClickable(false);

            edt_sg_work_name.setFocusable(false);
            edt_sg_work_name.setClickable(false);

            edt_sg_cb_name.setFocusable(false);
            edt_sg_cb_name.setClickable(false);

            edt_sgzy_place.setFocusable(false);
            edt_sgzy_place.setClickable(false);

            edt_sgzy_result.setFocusable(false);
            edt_sgzy_result.setClickable(false);
        }


    }
    private void initView() {
        tv_sgzy_time=findViewById(R.id.tv_sgzy_time);
        edt_sg_work_name=findViewById(R.id.edt_sg_work_name);
        edt_sg_cb_name=findViewById(R.id.edt_sg_cb_name);
        edt_sgzy_place=findViewById(R.id.edt_sgzy_place);
        edt_sgzy_result=findViewById(R.id.edt_sgzy_result);
    }

    private void initData() {
        mTimePickerView= new MinuteTimePickerView(mContext, MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());

    }
    private void initEvent() {
        tv_sgzy_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sgzy_time:
                showTimePickView();
                break;
        }
    }
    public void setTimerType(MinuteTimePickerView.Type type){
        mTimePickerView.setType(type);
    }
    public  SGZY getData(){
        SGZY sgsy=new SGZY();
        sgsy.setTime(tv_sgzy_time.getText().toString());
        sgsy.setWork_name(edt_sg_work_name.getText().toString());
        sgsy.setCb_name(edt_sg_cb_name.getText().toString());
        sgsy.setSgzy_place(edt_sgzy_place.getText().toString());
        sgsy.setSgzy_result(edt_sgzy_result.getText().toString());
        return sgsy;
    }

    public class SGZY{
        private String time;
        private String work_name;
        private String cb_name ;
        private String sgzy_place;
        private String sgzy_result;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWork_name() {
            return work_name;
        }

        public void setWork_name(String work_name) {
            this.work_name = work_name;
        }

        public String getCb_name() {
            return cb_name;
        }

        public void setCb_name(String cb_name) {
            this.cb_name = cb_name;
        }

        public String getSgzy_place() {
            return sgzy_place;
        }

        public void setSgzy_place(String sgzy_place) {
            this.sgzy_place = sgzy_place;
        }

        public String getSgzy_result() {
            return sgzy_result;
        }

        public void setSgzy_result(String sgzy_result) {
            this.sgzy_result = sgzy_result;
        }
    }

    /**
     *
     *  当前选择时间的TextView
     */
    private  void  showTimePickView(){
        String trim = tv_sgzy_time.getText().toString().trim();
        mTimePickerView.setPicker(trim);
        mTimePickerView.show();
        mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                tv_sgzy_time.setText(time);
            }
        });
    }

}
