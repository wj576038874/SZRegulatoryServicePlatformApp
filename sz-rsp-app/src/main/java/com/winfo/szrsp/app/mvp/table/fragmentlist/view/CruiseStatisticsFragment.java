package com.winfo.szrsp.app.mvp.table.fragmentlist.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.xhtj.view.CruiseSummaryLayout;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatistics;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsDetail;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.MinuteTimePickerView;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.fragmentlist.view
 * @Filename: CruiseStatisticsFragment
 * @Author: lsj
 * @Date: 2018/1/26  15:02
 * @Description:
 * @Version:巡航统计
 */
public class CruiseStatisticsFragment extends Fragment implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private Dialog dialog;
    private Dialog loadingdialog;
    private Toolbar toolbar;
    private LinearLayout ll_xhzy;
    private CruiseSummaryLayout cruiseSummaryLayout;
    private ImageView img_add;
    private ImageView img_del;
    private EditText edt_year;
    private EditText edt_month;
    private EditText edt_day;
    private TextView tv_way;
    private TimePickerView timePickerView;
    private MinuteTimePickerView mTimePickerView;
    private TextView tv_xhsj;
    private CheckBox ck_1,ck_2,ck_3;
    private EditText edt_weather;
    private EditText edt_hk;
    private EditText edt_xhsy;
    private EditText edt_item_11;
    private EditText edt_item_12;
    private EditText edt_item_13;
    private EditText edt_item_14;
    private EditText edt_item_15;
    private EditText edt_item_16;
    private EditText edt_item_21;
    private EditText edt_item_22;
    private EditText edt_item_23;
    private EditText edt_item_24;
    private EditText edt_item_25;
    private EditText edt_item_26;
    private EditText edt_item_27;
    private EditText edt_item_30;
    private EditText edt_item_40;
    private EditText edt_item_50;
    private EditText edt_item_60;
    private EditText edt_item_71;
    private EditText edt_item_72;
    private EditText edt_outline;
    private EditText edt_shipname;
    private EditText edt_zfry;
    private Button btn_sub;
    private String area1 = "";
    private String area2 = "";
    private String area3 = "";
    private String xh_time,xh_week;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_xhtj,null);
        ButterKnife.bind(this,view);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        dialog = DialogUtils.createLoadingDialog(getActivity(),"提交中...");
        loadingdialog = DialogUtils.createLoadingDialog(getActivity(),"下载中...");
        toolbar = view.findViewById(R.id.toolbar);
        ll_xhzy = view.findViewById(R.id.layout_xhjy);
        cruiseSummaryLayout = new CruiseSummaryLayout(getActivity());
        ll_xhzy.addView(cruiseSummaryLayout);
        img_add = view.findViewById(R.id.add_img);
        img_del= view.findViewById(R.id.del_img);
        edt_year = view.findViewById(R.id.edt_year);
        edt_month = view.findViewById(R.id.edt_month);
        edt_day = view.findViewById(R.id.edt_day);
        tv_way = view.findViewById(R.id.tv_way);
        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        mTimePickerView= new MinuteTimePickerView(getActivity(), MinuteTimePickerView.Type.ALL);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());
        tv_xhsj = view.findViewById(R.id.tv_xhsj);
        ck_1 = view.findViewById(R.id.ck_1);
        ck_2 = view.findViewById(R.id.ck_2);
        ck_3 = view.findViewById(R.id.ck_3);
        edt_hk = view.findViewById(R.id.edt_hk);
        edt_weather = view.findViewById(R.id.edt_weather);
        edt_xhsy = view.findViewById(R.id.edt_xhsy);
        edt_item_11 = view.findViewById(R.id.edt_item_11);
        edt_item_12 = view.findViewById(R.id.edt_item_12);
        edt_item_13 = view.findViewById(R.id.edt_item_13);
        edt_item_14 = view.findViewById(R.id.edt_item_14);
        edt_item_15 = view.findViewById(R.id.edt_item_15);
        edt_item_16 = view.findViewById(R.id.edt_item_16);
        edt_item_21 = view.findViewById(R.id.edt_item_21);
        edt_item_22 = view.findViewById(R.id.edt_item_22);
        edt_item_23 = view.findViewById(R.id.edt_item_23);
        edt_item_24 = view.findViewById(R.id.edt_item_24);
        edt_item_25 = view.findViewById(R.id.edt_item_25);
        edt_item_26 = view.findViewById(R.id.edt_item_26);
        edt_item_27 = view.findViewById(R.id.edt_item_27);
        edt_item_30 = view.findViewById(R.id.edt_item_30);
        edt_item_40 = view.findViewById(R.id.edt_item_40);
        edt_item_50 = view.findViewById(R.id.edt_item_50);
        edt_item_60 = view.findViewById(R.id.edt_item_60);
        edt_item_71 = view.findViewById(R.id.edt_item_71);
        edt_item_72 = view.findViewById(R.id.edt_item_72);
        edt_outline = view.findViewById(R.id.edt_outline);
        edt_shipname = view.findViewById(R.id.edt_shipname);
        edt_zfry = view.findViewById(R.id.edt_zfry);
        btn_sub = view.findViewById(R.id.btn_save);
        btn_sub.setVisibility(View.INVISIBLE);
        toolbar.setVisibility(View.GONE);
    }


    private void initEvent() {
        img_add.setOnClickListener(this);
        img_del.setOnClickListener(this);
        tv_xhsj.setOnClickListener(this);
        edt_year.setOnClickListener(this);
        edt_month.setOnClickListener(this);
        edt_day.setOnClickListener(this);
        ck_1.setOnCheckedChangeListener(this);
        ck_2.setOnCheckedChangeListener(this);
        ck_3.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_img:
                cruiseSummaryLayout = new CruiseSummaryLayout(getActivity());
                ll_xhzy.addView(cruiseSummaryLayout);
                break;
            case R.id.del_img:
                if (ll_xhzy.getChildCount() != 1){
                    ll_xhzy.removeViewAt(ll_xhzy.getChildCount()-1);
                }
                break;
            case R.id.edt_year:
                setXHtime();
                break;
            case R.id.edt_month:
                setXHtime();
                break;
            case R.id.edt_day:
                setXHtime();
                break;
            case R.id.tv_xhsj:
                String trim = tv_xhsj.getText().toString().trim();
                mTimePickerView.setPicker(trim);
                mTimePickerView.show();
                mTimePickerView.setOnTimeSelectListener(new MinuteTimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String time) {
                        tv_xhsj.setText(time);
                    }
                });
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()){
            case R.id.ck_1:
                if (isCheck){
                    area1 = "1";
                }else {
                    area1 = "";
                }
                break;
            case R.id.ck_2:
                if (isCheck){
                    area2 = "2";
                }else {
                    area2 = "";
                }
                break;
            case R.id.ck_3:
                if (isCheck){
                    area3 = "3";
                }else {
                    area3 = "";
                }
                break;
        }
    }

    private void setXHtime(){
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String time = format.format(date);
                xh_time = time;
                edt_year.setText(time.substring(0, 4));
                edt_month.setText(time.substring(5, 7));
                edt_day.setText(time.substring(8, 10));
                Calendar c = Calendar.getInstance();
                //根据日期来显示星期
                int year = Integer.parseInt(time.substring(0, 4));
                int month = Integer.parseInt(time.substring(5, 7));
                int day = Integer.parseInt(time.substring(8, 10));
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month - 1);
                c.set(Calendar.DAY_OF_MONTH, day);
                String week = "";
                String weekIndex = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
                if("1".equals(weekIndex)){
                    week ="天";
                }else if("2".equals(weekIndex)){
                    week ="一";
                }else if("3".equals(weekIndex)){
                    week ="二";
                }else if("4".equals(weekIndex)){
                    week ="三";
                }else if("5".equals(weekIndex)){
                    week ="四";
                }else if("6".equals(weekIndex)){
                    week ="五";
                }else if("7".equals(weekIndex)){
                    week ="六";
                }
                tv_way.setVisibility(View.VISIBLE);
                tv_way.setText("星期"+week);
                xh_week = week;
            }
        });
        timePickerView.show();
    }

    public interface GetCruiseStatisticsDataListener{
        void GetCruiseStatisticsData(CtCruiseStatisticsObject ctCruiseStatisticsObject);
    }

    public void getXHTJData(GetCruiseStatisticsDataListener getCruiseStatisticsDataListener){
        CtCruiseStatisticsObject ctCruiseStatisticsObject = new CtCruiseStatisticsObject();
        CtCruiseStatistics info = new CtCruiseStatistics();
        List<CtCruiseStatisticsDetail> list = new ArrayList<>();
        if (xh_time != null && !tv_xhsj.getText().toString().equals("")){
            info.setCruiseTime(xh_time);
            info.setWeeknum(xh_week);
            info.setArea(area1+area2+area3);
            info.setWeather(edt_weather.getText().toString());
            info.setSeaStatic(edt_hk.getText().toString());
            String str = tv_xhsj.getText().toString();
            info.setStartTime(str.substring(0, str.indexOf("-")));
            info.setEndTime(str.substring(str.indexOf("-")+1));
            info.setCruiseArea(edt_xhsy.getText().toString());
            info.setItem11(edt_item_11.getText().toString());
            info.setItem12(edt_item_12.getText().toString());
            info.setItem13(edt_item_13.getText().toString());
            info.setItem14(edt_item_14.getText().toString());
            info.setItem15(edt_item_15.getText().toString());
            info.setItem16(edt_item_16.getText().toString());
            info.setItem21(edt_item_21.getText().toString());
            info.setItem22(edt_item_22.getText().toString());
            info.setItem23(edt_item_23.getText().toString());
            info.setItem24(edt_item_24.getText().toString());
            info.setItem25(edt_item_25.getText().toString());
            info.setItem26(edt_item_26.getText().toString());
            info.setItem27(edt_item_27.getText().toString());
            info.setItem30(edt_item_30.getText().toString());
            info.setItem40(edt_item_40.getText().toString());
            info.setItem50(edt_item_50.getText().toString());
            info.setItem60(edt_item_60.getText().toString());
            info.setItem71(edt_item_71.getText().toString());
            info.setItem72(edt_item_72.getText().toString());
            info.setOutLine(edt_outline.getText().toString());
            info.setShipName(edt_shipname.getText().toString());
            info.setInspectorName(edt_zfry.getText().toString());
            if (!edt_shipname.getText().toString().equals("") && !edt_zfry.getText().toString().equals("")){
                for (int i = 0; i < ll_xhzy.getChildCount(); i++) {
                    CruiseSummaryLayout cruiseSummaryLayout = (CruiseSummaryLayout) ll_xhzy.getChildAt(i);
                    if (cruiseSummaryLayout.getData() == null){
                        return;
                    }else {
                        list.add(cruiseSummaryLayout.getData());
                    }
                }
                ctCruiseStatisticsObject.setCruiseStatistics(info);
                ctCruiseStatisticsObject.setCruiseStatisticsDetailList(list);
            }else {
                ToastUtils.showToast(getActivity(),"请将巡航船舶和执法人员填写完整再提交！！");
                getCruiseStatisticsDataListener.GetCruiseStatisticsData(null);
                return;
            }
        }else {
            ToastUtils.showToast(getActivity(),"请将巡航时间填写完整再提交！");
            getCruiseStatisticsDataListener.GetCruiseStatisticsData(null);
            return;
        }
        getCruiseStatisticsDataListener.GetCruiseStatisticsData(ctCruiseStatisticsObject);
    }
}
