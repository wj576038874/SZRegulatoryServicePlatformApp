package com.winfo.szrsp.app.mvp.table.fragmentlist.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.task.view.LevelItem;
import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wly on 2018/1/27.
 *
 */

public class ElectronicCruiseAbnormalFragment extends  Fragment implements View.OnClickListener {

    private TextView tv_date;

    private TextView tv_abnormality_time;

    private TextView tv_handleTime;

    private Button btn_submit;

    private EditText et_noticeOrg;

    private EditText et_exceptionType;

    private EditText et_exceptionDescribe;

    private EditText et_handleResult;

    private EditText et_remarks;

    private EditText et_inspectOrg;

    private EditText et_inspector;

    private EditText et_telephone;

    private View table_xqdk_titleBar;

    private TimePickerView timePickerView;

    private TimePickerView timePickerView2;

    private TimePickerView timePickerView3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_electronic_cruise_abnormal,null);

        initView(view);

        initData();

        initEvent();

        return view;
    }

    private void initEvent() {

        tv_date.setOnClickListener(this);
        tv_abnormality_time.setOnClickListener(this);
        tv_handleTime.setOnClickListener(this);
    }

    private void initData() {
        List<TaskInfoDetails> taskInfoDetails = (List<TaskInfoDetails>) getArguments().getSerializable("taskInfoDetails");
        List<LevelItem> level1Items = (List<LevelItem>) getArguments().getSerializable("level1Items");
        StringBuilder sbExceptionDescribe=new StringBuilder();
        for (LevelItem levelItem: level1Items){
            sbExceptionDescribe.append(levelItem.getInput());
            sbExceptionDescribe.append(",");
        }
        String exceptionDescribe=sbExceptionDescribe.toString();
        if (exceptionDescribe.endsWith(",")){
            et_exceptionDescribe.setText(exceptionDescribe.substring(0,exceptionDescribe.length()-1));
        }else {
            et_exceptionDescribe.setText(exceptionDescribe);
        }
        table_xqdk_titleBar.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_date.setText(time);
        tv_handleTime.setText(time);

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time2 = format2.format(new Date());
        tv_abnormality_time.setText(time2);

        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());

        timePickerView2 = new TimePickerView(getActivity(), TimePickerView.Type.ALL);
        timePickerView2.setCyclic(true);
        timePickerView2.setTime(new Date());

        timePickerView3 = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView3.setCyclic(true);
        timePickerView3.setTime(new Date());

    }

    private void initView(View view) {

        table_xqdk_titleBar=view.findViewById(R.id.table_xqdk_titleBar);

        tv_date=view.findViewById(R.id.tv_date);
        tv_abnormality_time=view.findViewById(R.id.tv_abnormality_time);

        btn_submit=view.findViewById(R.id.btn_submit);

        et_noticeOrg=view.findViewById(R.id.et_noticeOrg);
        et_exceptionType=view.findViewById(R.id.et_exceptionType);
        et_exceptionDescribe=view.findViewById(R.id.et_exceptionDescribe);
        et_handleResult=view.findViewById(R.id.et_handleResult);
        tv_handleTime=view.findViewById(R.id.tv_handleTime);
        et_remarks=view.findViewById(R.id.et_remarks);
        et_inspectOrg=view.findViewById(R.id.et_inspectOrg);
        et_inspector=view.findViewById(R.id.et_inspector);
        et_telephone=view.findViewById(R.id.et_telephone);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_date:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_date.setText(time);
                    }
                });
                timePickerView.show();
                break;

            case R.id.tv_abnormality_time:
                timePickerView2.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                        String time = format.format(date);
                        tv_abnormality_time.setText(time);
                    }
                });
                timePickerView2.show();
                break;

            case R.id.tv_handleTime:
                timePickerView3.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_handleTime.setText(time);
                    }
                });
                timePickerView3.show();
                break;
        }
    }

    //获取当前Fragment页面中数据
    public interface GetCtElectronicCruiseException{
        void getResult(CtElectronicCruiseException data);
    }

    public void getCtElectronicCruiseException(GetCtElectronicCruiseException callback){
        CtElectronicCruiseException ctElectronicCruiseException=new CtElectronicCruiseException();
        ctElectronicCruiseException.setNoticeOrg(et_noticeOrg.getText().toString().trim());
        ctElectronicCruiseException.setExceptionType(et_exceptionType.getText().toString().trim());
        ctElectronicCruiseException.setExceptionTime(tv_abnormality_time.getText().toString());
        ctElectronicCruiseException.setExceptionDescribe(et_exceptionDescribe.getText().toString().trim());
        ctElectronicCruiseException.setHandleResult(et_handleResult.getText().toString().trim());
        ctElectronicCruiseException.setHandleTime(tv_handleTime.getText().toString());
        ctElectronicCruiseException.setRemarks(et_remarks.getText().toString().trim());
        ctElectronicCruiseException.setInspectOrg(et_inspectOrg.getText().toString().trim());
        ctElectronicCruiseException.setInspector(et_inspector.getText().toString().trim());
        ctElectronicCruiseException.setTelephone(et_telephone.getText().toString().trim());
        ctElectronicCruiseException.setInspectTime(tv_date.getText().toString());
        callback.getResult(ctElectronicCruiseException);
    }

}
