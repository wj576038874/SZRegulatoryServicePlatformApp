package com.winfo.szrsp.app.mvp.table.ssxh.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;

import java.util.Date;

/**
 * Created by Guan on 2017-12-22.
 */

public class SYXHLayout extends LinearLayout implements View.OnClickListener {
    private TextView tv_syxh_time;
    private  EditText edt_area;
    private  EditText  edt_exap;
    private CheckBox cb_isnormal_1;
    private CheckBox cb_isnormal_2;
    private MinuteTimePickerView mTimePickerView;
    private Context mContext;

    public SYXHLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_syxh,this);
        this.mContext=context;
        initView();
        initData();
        initEvent();
    }

    /**
     *
     * @param context
     * @param time
     * @param area
     * @param isnormal
     * @param exap
     * @param isEdit 是否可点击编辑
     */
    public SYXHLayout(Context context,String time,String area,boolean isnormal,boolean isSelect,String exap,boolean isEdit) {
        this(context);

        tv_syxh_time.setText(time);
        edt_area.setText(area);
        edt_exap.setText(exap);

        if(isSelect){
            if(isnormal){
                cb_isnormal_1.setChecked(true);
            }else {
                cb_isnormal_2.setChecked(true);
            }
        }
        if(!isEdit){
            tv_syxh_time.setFocusable(false);
            tv_syxh_time.setClickable(false);

            edt_area.setFocusable(false);
            edt_area.setClickable(false);

            edt_exap.setFocusable(false);
            edt_exap.setClickable(false);

            cb_isnormal_1.setFocusable(false);
            cb_isnormal_1.setClickable(false);

            cb_isnormal_2.setFocusable(false);
            cb_isnormal_2.setClickable(false);
        }

    }

    private void initView() {
        tv_syxh_time=findViewById(R.id.tv_syxh_time);
        edt_area=findViewById(R.id.edt_area);
        edt_exap=findViewById(R.id.edt_exap);
        cb_isnormal_1=findViewById(R.id.cb_isnormal_1);
        cb_isnormal_2=findViewById(R.id.cb_isnormal_2);
    }

    private void initData() {
        mTimePickerView= new MinuteTimePickerView(mContext, MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());

    }
    private void initEvent() {
        tv_syxh_time.setOnClickListener(this);
        setOnlyOneCheck(cb_isnormal_1,cb_isnormal_2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_syxh_time:
                showTimePickView();
                break;
        }
    }
    public void setTimerType(MinuteTimePickerView.Type type){
        mTimePickerView.setType(type);
    }
    public  SYXH getData(){
        SYXH syxh=new SYXH();
        syxh.setTime(tv_syxh_time.getText().toString());
        syxh.setArea(edt_area.getText().toString());
        syxh.setExap(edt_exap.getText().toString());

        if(cb_isnormal_1.isChecked()||cb_isnormal_2.isChecked()){
            syxh.setSelect(true);
        }else {
            syxh.setSelect(false);
        }

        if(cb_isnormal_1.isChecked()){
            syxh.setNormal(true);
        }
        if(cb_isnormal_2.isChecked()){
            syxh.setNormal(false);
        }
        return syxh;
    }
    public class SYXH{
     private  String time;
     private  String area;
     private  String exap;
     private  boolean isSelect;
     private boolean isNormal =true;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getExap() {
            return exap;
        }

        public void setExap(String exap) {
            this.exap = exap;
        }

        public boolean isNormal() {
            return isNormal;
        }

        public void setNormal(boolean normal) {
            isNormal = normal;
        }
    }


    /**
     *
     *  当前选择时间的TextView
     */
    private  void  showTimePickView(){
        String trim = tv_syxh_time.getText().toString().trim();
        mTimePickerView.setPicker(trim);
        mTimePickerView.show();
        mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                tv_syxh_time.setText(time);
            }
        });
    }

    /**
     * 设置两个checkbox只能单选
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     */
    private void setOnlyOneCheck(final CheckBox dxchcjcb_check1, final CheckBox dxchcjcb_check2) {
        dxchcjcb_check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);

                }

            }
        });
        dxchcjcb_check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check1.setChecked(false);
                }
            }
        });
    }
}
