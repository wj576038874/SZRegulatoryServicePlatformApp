package com.winfo.szrsp.app.mvp.table.fragmentlist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.task.view.LevelItem;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.fragmentlist.view
 * @Filename: ShipbulkcargoFragment
 * @Author: lsj
 * @Date: 2017/12/19  13:07
 * @Description:
 * @Version:大型散货船
 */
public class ShipbulkcargoFragment extends Fragment implements View.OnClickListener {

    private View title;

    private Button dxshcjcb_sub;

    @BindView(R.id.dxchcjcb_tv_time)
    TextView dxchcjcb_tv_time;
    @BindView(R.id.dxshcjcb_et_cm)
    EditText dxshcjcb_et_cm;
    @BindView(R.id.dxshcjcb_et_imo)
    EditText dxshcjcb_et_imo;
    @BindView(R.id.dxshcjcb_et_gk)
    EditText dxshcjcb_et_gk;
    @BindView(R.id.dxchcjcb_check1_1_1)
    CheckBox dxchcjcb_check1_1_1;
    @BindView(R.id.dxchcjcb_check1_1_2)
    CheckBox dxchcjcb_check1_1_2;
    @BindView(R.id.dxchcjcb_check1_1_3)
    CheckBox dxchcjcb_check1_1_3;
    @BindView(R.id.dxchcjcb_check1_2_1)
    CheckBox dxchcjcb_check1_2_1;
    @BindView(R.id.dxchcjcb_check1_2_2)
    CheckBox dxchcjcb_check1_2_2;
    @BindView(R.id.dxchcjcb_check1_2_3)
    CheckBox dxchcjcb_check1_2_3;

    @BindView(R.id.dxchcjcb_check2_1_1)
    CheckBox dxchcjcb_check2_1_1;
    @BindView(R.id.dxchcjcb_check2_1_2)
    CheckBox dxchcjcb_check2_1_2;
    @BindView(R.id.dxchcjcb_check2_1_3)
    CheckBox dxchcjcb_check2_1_3;
    @BindView(R.id.dxchcjcb_check2_2_1)
    CheckBox dxchcjcb_check2_2_1;
    @BindView(R.id.dxchcjcb_check2_2_2)
    CheckBox dxchcjcb_check2_2_2;
    @BindView(R.id.dxchcjcb_check2_2_3)
    CheckBox dxchcjcb_check2_2_3;

    @BindView(R.id.dxchcjcb_check3_1_1)
    CheckBox dxchcjcb_check3_1_1;
    @BindView(R.id.dxchcjcb_check3_1_2)
    CheckBox dxchcjcb_check3_1_2;
    @BindView(R.id.dxchcjcb_check3_1_3)
    CheckBox dxchcjcb_check3_1_3;
    @BindView(R.id.dxchcjcb_check3_2_1)
    CheckBox dxchcjcb_check3_2_1;
    @BindView(R.id.dxchcjcb_check3_2_2)
    CheckBox dxchcjcb_check3_2_2;
    @BindView(R.id.dxchcjcb_check3_2_3)
    CheckBox dxchcjcb_check3_2_3;
    @BindView(R.id.dxchcjcb_check3_3_1)
    CheckBox dxchcjcb_check3_3_1;
    @BindView(R.id.dxchcjcb_check3_3_2)
    CheckBox dxchcjcb_check3_3_2;
    @BindView(R.id.dxchcjcb_check3_3_3)
    CheckBox dxchcjcb_check3_3_3;

    @BindView(R.id.dxchcjcb_check4_1_1)
    CheckBox dxchcjcb_check4_1_1;
    @BindView(R.id.dxchcjcb_check4_1_2)
    CheckBox dxchcjcb_check4_1_2;
    @BindView(R.id.dxchcjcb_check4_1_3)
    CheckBox dxchcjcb_check4_1_3;
    @BindView(R.id.dxchcjcb_check4_2_1)
    CheckBox dxchcjcb_check4_2_1;
    @BindView(R.id.dxchcjcb_check4_2_2)
    CheckBox dxchcjcb_check4_2_2;
    @BindView(R.id.dxchcjcb_check4_2_3)
    CheckBox dxchcjcb_check4_2_3;

    @BindView(R.id.dxchcjcb_check5_1_1)
    CheckBox dxchcjcb_check5_1_1;
    @BindView(R.id.dxchcjcb_check5_1_2)
    CheckBox dxchcjcb_check5_1_2;
    @BindView(R.id.dxchcjcb_check5_1_3)
    CheckBox dxchcjcb_check5_1_3;
    @BindView(R.id.dxchcjcb_check5_2_1)
    CheckBox dxchcjcb_check5_2_1;
    @BindView(R.id.dxchcjcb_check5_2_2)
    CheckBox dxchcjcb_check5_2_2;
    @BindView(R.id.dxchcjcb_check5_2_3)
    CheckBox dxchcjcb_check5_2_3;


    @BindView(R.id.dxchcjcb_check6_1_1)
    CheckBox dxchcjcb_check6_1_1;
    @BindView(R.id.dxchcjcb_check6_1_2)
    CheckBox dxchcjcb_check6_1_2;
    @BindView(R.id.dxchcjcb_check6_1_3)
    CheckBox dxchcjcb_check6_1_3;
    @BindView(R.id.dxchcjcb_check6_2_1)
    CheckBox dxchcjcb_check6_2_1;
    @BindView(R.id.dxchcjcb_check6_2_2)
    CheckBox dxchcjcb_check6_2_2;
    @BindView(R.id.dxchcjcb_check6_2_3)
    CheckBox dxchcjcb_check6_2_3;
    @BindView(R.id.dxchcjcb_check6_3_1)
    CheckBox dxchcjcb_check6_3_1;
    @BindView(R.id.dxchcjcb_check6_3_2)
    CheckBox dxchcjcb_check6_3_2;
    @BindView(R.id.dxchcjcb_check6_3_3)
    CheckBox dxchcjcb_check6_3_3;

    @BindView(R.id.dxchcjcb_check7_1_1)
    CheckBox dxchcjcb_check7_1_1;
    @BindView(R.id.dxchcjcb_check7_1_2)
    CheckBox dxchcjcb_check7_1_2;
    @BindView(R.id.dxchcjcb_check7_1_3)
    CheckBox dxchcjcb_check7_1_3;
    @BindView(R.id.dxchcjcb_check7_2_1)
    CheckBox dxchcjcb_check7_2_1;
    @BindView(R.id.dxchcjcb_check7_2_2)
    CheckBox dxchcjcb_check7_2_2;
    @BindView(R.id.dxchcjcb_check7_2_3)
    CheckBox dxchcjcb_check7_2_3;
    @BindView(R.id.dxchcjcb_check7_3_1)
    CheckBox dxchcjcb_check7_3_1;
    @BindView(R.id.dxchcjcb_check7_3_2)
    CheckBox dxchcjcb_check7_3_2;
    @BindView(R.id.dxchcjcb_check7_3_3)
    CheckBox dxchcjcb_check7_3_3;

    @BindView(R.id.dxchcjcb_check8_1_1)
    CheckBox dxchcjcb_check8_1_1;
    @BindView(R.id.dxchcjcb_check8_1_2)
    CheckBox dxchcjcb_check8_1_2;
    @BindView(R.id.dxchcjcb_check8_1_3)
    CheckBox dxchcjcb_check8_1_3;
    @BindView(R.id.dxchcjcb_check8_2_1)
    CheckBox dxchcjcb_check8_2_1;
    @BindView(R.id.dxchcjcb_check8_2_2)
    CheckBox dxchcjcb_check8_2_2;
    @BindView(R.id.dxchcjcb_check8_2_3)
    CheckBox dxchcjcb_check8_2_3;
    @BindView(R.id.dxchcjcb_check8_3_1)
    CheckBox dxchcjcb_check8_3_1;
    @BindView(R.id.dxchcjcb_check8_3_2)
    CheckBox dxchcjcb_check8_3_2;
    @BindView(R.id.dxchcjcb_check8_3_3)
    CheckBox dxchcjcb_check8_3_3;

    @BindView(R.id.dxchcjcb_check9_1_1)
    CheckBox dxchcjcb_check9_1_1;
    @BindView(R.id.dxchcjcb_check9_1_2)
    CheckBox dxchcjcb_check9_1_2;
    @BindView(R.id.dxchcjcb_check9_1_3)
    CheckBox dxchcjcb_check9_1_3;
    @BindView(R.id.dxchcjcb_check9_2_1)
    CheckBox dxchcjcb_check9_2_1;
    @BindView(R.id.dxchcjcb_check9_2_2)
    CheckBox dxchcjcb_check9_2_2;
    @BindView(R.id.dxchcjcb_check9_2_3)
    CheckBox dxchcjcb_check9_2_3;
    @BindView(R.id.dxchcjcb_check9_3_1)
    CheckBox dxchcjcb_check9_3_1;
    @BindView(R.id.dxchcjcb_check9_3_2)
    CheckBox dxchcjcb_check9_3_2;
    @BindView(R.id.dxchcjcb_check9_3_3)
    CheckBox dxchcjcb_check9_3_3;

    @BindView(R.id.dxchcjcb_check10_1_1)
    CheckBox dxchcjcb_check10_1_1;
    @BindView(R.id.dxchcjcb_check10_1_2)
    CheckBox dxchcjcb_check10_1_2;
    @BindView(R.id.dxchcjcb_check10_1_3)
    CheckBox dxchcjcb_check10_1_3;
    @BindView(R.id.dxchcjcb_check10_2_1)
    CheckBox dxchcjcb_check10_2_1;
    @BindView(R.id.dxchcjcb_check10_2_2)
    CheckBox dxchcjcb_check10_2_2;
    @BindView(R.id.dxchcjcb_check10_2_3)
    CheckBox dxchcjcb_check10_2_3;

    @BindView(R.id.dxchcjcb_check11_1_1)
    CheckBox dxchcjcb_check11_1_1;
    @BindView(R.id.dxchcjcb_check11_1_2)
    CheckBox dxchcjcb_check11_1_2;
    @BindView(R.id.dxchcjcb_check11_1_3)
    CheckBox dxchcjcb_check11_1_3;
    @BindView(R.id.dxchcjcb_check11_2_1)
    CheckBox dxchcjcb_check11_2_1;
    @BindView(R.id.dxchcjcb_check11_2_2)
    CheckBox dxchcjcb_check11_2_2;
    @BindView(R.id.dxchcjcb_check11_2_3)
    CheckBox dxchcjcb_check11_2_3;
    @BindView(R.id.dxchcjcb_check11_3_1)
    CheckBox dxchcjcb_check11_3_1;
    @BindView(R.id.dxchcjcb_check11_3_2)
    CheckBox dxchcjcb_check11_3_2;
    @BindView(R.id.dxchcjcb_check11_3_3)
    CheckBox dxchcjcb_check11_3_3;

    @BindView(R.id.dxchcjcb_check12_1_1)
    CheckBox dxchcjcb_check12_1_1;
    @BindView(R.id.dxchcjcb_check12_1_2)
    CheckBox dxchcjcb_check12_1_2;
    @BindView(R.id.dxchcjcb_check12_1_3)
    CheckBox dxchcjcb_check12_1_3;
    @BindView(R.id.dxchcjcb_check12_2_1)
    CheckBox dxchcjcb_check12_2_1;
    @BindView(R.id.dxchcjcb_check12_2_2)
    CheckBox dxchcjcb_check12_2_2;
    @BindView(R.id.dxchcjcb_check12_2_3)
    CheckBox dxchcjcb_check12_2_3;
    @BindView(R.id.dxchcjcb_check12_3_1)
    CheckBox dxchcjcb_check12_3_1;
    @BindView(R.id.dxchcjcb_check12_3_2)
    CheckBox dxchcjcb_check12_3_2;
    @BindView(R.id.dxchcjcb_check12_3_3)
    CheckBox dxchcjcb_check12_3_3;

    @BindView(R.id.dxchcjcb_check13_1_1)
    CheckBox dxchcjcb_check13_1_1;
    @BindView(R.id.dxchcjcb_check13_1_2)
    CheckBox dxchcjcb_check13_1_2;
    @BindView(R.id.dxchcjcb_check13_1_3)
    CheckBox dxchcjcb_check13_1_3;
    @BindView(R.id.dxchcjcb_check13_2_1)
    CheckBox dxchcjcb_check13_2_1;
    @BindView(R.id.dxchcjcb_check13_2_2)
    CheckBox dxchcjcb_check13_2_2;
    @BindView(R.id.dxchcjcb_check13_2_3)
    CheckBox dxchcjcb_check13_2_3;
    private TimePickerView mTimePickerView;
    private List<TaskInfoDetails> taskInfoDetails;
    private List<LevelItem> level1Items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_dxshcjcb,null);
        ButterKnife.bind(this,view);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        dxchcjcb_tv_time.setOnClickListener(this);

        setOnlyOneCheck(dxchcjcb_check1_1_1,dxchcjcb_check1_1_2,dxchcjcb_check1_1_3);
        setOnlyOneCheck(dxchcjcb_check1_2_1,dxchcjcb_check1_2_2,dxchcjcb_check1_2_3);

        setOnlyOneCheck(dxchcjcb_check2_1_1,dxchcjcb_check2_1_2,dxchcjcb_check2_1_3);
        setOnlyOneCheck(dxchcjcb_check2_2_1,dxchcjcb_check2_2_2,dxchcjcb_check2_2_3);

        setOnlyOneCheck(dxchcjcb_check3_1_1,dxchcjcb_check3_1_2,dxchcjcb_check3_1_3);
        setOnlyOneCheck(dxchcjcb_check3_2_1,dxchcjcb_check3_2_2,dxchcjcb_check3_2_3);
        setOnlyOneCheck(dxchcjcb_check3_3_1,dxchcjcb_check3_3_2,dxchcjcb_check3_3_3);

        setOnlyOneCheck(dxchcjcb_check4_1_1,dxchcjcb_check4_1_2,dxchcjcb_check4_1_3);
        setOnlyOneCheck(dxchcjcb_check4_2_1,dxchcjcb_check4_2_2,dxchcjcb_check4_2_3);

        setOnlyOneCheck(dxchcjcb_check5_1_1,dxchcjcb_check5_1_2,dxchcjcb_check5_1_3);
        setOnlyOneCheck(dxchcjcb_check5_2_1,dxchcjcb_check5_2_2,dxchcjcb_check5_2_3);

        setOnlyOneCheck(dxchcjcb_check6_1_1,dxchcjcb_check6_1_2,dxchcjcb_check6_1_3);
        setOnlyOneCheck(dxchcjcb_check6_2_1,dxchcjcb_check6_2_2,dxchcjcb_check6_2_3);
        setOnlyOneCheck(dxchcjcb_check6_3_1,dxchcjcb_check6_3_2,dxchcjcb_check6_3_3);

        setOnlyOneCheck(dxchcjcb_check7_1_1,dxchcjcb_check7_1_2,dxchcjcb_check7_1_3);
        setOnlyOneCheck(dxchcjcb_check7_2_1,dxchcjcb_check7_2_2,dxchcjcb_check7_2_3);
        setOnlyOneCheck(dxchcjcb_check7_3_1,dxchcjcb_check7_3_2,dxchcjcb_check7_3_3);

        setOnlyOneCheck(dxchcjcb_check8_1_1,dxchcjcb_check8_1_2,dxchcjcb_check8_1_3);
        setOnlyOneCheck(dxchcjcb_check8_2_1,dxchcjcb_check8_2_2,dxchcjcb_check8_2_3);
        setOnlyOneCheck(dxchcjcb_check8_3_1,dxchcjcb_check8_3_2,dxchcjcb_check8_3_3);

        setOnlyOneCheck(dxchcjcb_check9_1_1,dxchcjcb_check9_1_2,dxchcjcb_check9_1_3);
        setOnlyOneCheck(dxchcjcb_check9_2_1,dxchcjcb_check9_2_2,dxchcjcb_check9_2_3);
        setOnlyOneCheck(dxchcjcb_check9_3_1,dxchcjcb_check9_3_2,dxchcjcb_check9_3_3);

        setOnlyOneCheck(dxchcjcb_check10_1_1,dxchcjcb_check10_1_2,dxchcjcb_check10_1_3);
        setOnlyOneCheck(dxchcjcb_check10_2_1,dxchcjcb_check10_2_2,dxchcjcb_check10_2_3);

        setOnlyOneCheck(dxchcjcb_check11_1_1,dxchcjcb_check11_1_2,dxchcjcb_check11_1_3);
        setOnlyOneCheck(dxchcjcb_check11_2_1,dxchcjcb_check11_2_2,dxchcjcb_check11_2_3);
        setOnlyOneCheck(dxchcjcb_check11_3_1,dxchcjcb_check11_3_2,dxchcjcb_check11_3_3);

        setOnlyOneCheck(dxchcjcb_check12_1_1,dxchcjcb_check12_1_2,dxchcjcb_check12_1_3);
        setOnlyOneCheck(dxchcjcb_check12_2_1,dxchcjcb_check12_2_2,dxchcjcb_check12_2_3);
        setOnlyOneCheck(dxchcjcb_check12_3_1,dxchcjcb_check12_3_2,dxchcjcb_check12_3_3);

        setOnlyOneCheck(dxchcjcb_check13_1_1,dxchcjcb_check13_1_2,dxchcjcb_check13_1_3);
        setOnlyOneCheck(dxchcjcb_check13_2_1,dxchcjcb_check13_2_2,dxchcjcb_check13_2_3);

    }

    /**
     * 设置三个checkbox只能单选
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     * @param dxchcjcb_check3
     */
    private void setOnlyOneCheck(final CheckBox dxchcjcb_check1, final CheckBox dxchcjcb_check2,final CheckBox dxchcjcb_check3) {
        dxchcjcb_check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                }

            }
        });
        dxchcjcb_check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check1.setChecked(false);
                    dxchcjcb_check3.setChecked(false);
                }

            }
        });
        dxchcjcb_check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dxchcjcb_check2.setChecked(false);
                    dxchcjcb_check1.setChecked(false);
                }

            }
        });
    }

    private void initData() {
        title.setVisibility(View.GONE);
        dxshcjcb_sub.setVisibility(View.GONE);

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        dxchcjcb_tv_time.setText(time);
        taskInfoDetails = (List<TaskInfoDetails>) getArguments().getSerializable("taskInfoDetails");
        level1Items = (List<LevelItem>) getArguments().getSerializable("level1Items");
        for (int i = 0; i < level1Items.size(); i++) {
            if (level1Items.get(i).getItemFuseId().equals("S_98010101")){
                setCheck(dxchcjcb_check1_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010102")){
                setCheck(dxchcjcb_check1_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010201")){
                setCheck(dxchcjcb_check2_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010202")){
                setCheck(dxchcjcb_check2_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010301")){
                setCheck(dxchcjcb_check3_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010302")){
                setCheck(dxchcjcb_check3_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010303")){
                setCheck(dxchcjcb_check3_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010401")){
                setCheck(dxchcjcb_check4_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010402")){
                setCheck(dxchcjcb_check4_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010501")){
                setCheck(dxchcjcb_check5_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010502")){
                setCheck(dxchcjcb_check5_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010601")){
                setCheck(dxchcjcb_check6_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010602")){
                setCheck(dxchcjcb_check6_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010603")){
                setCheck(dxchcjcb_check6_3_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010701")){
                setCheck(dxchcjcb_check7_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010702")){
                setCheck(dxchcjcb_check7_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010703")){
                setCheck(dxchcjcb_check7_3_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010801")){
                setCheck(dxchcjcb_check8_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010802")){
                setCheck(dxchcjcb_check8_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010803")){
                setCheck(dxchcjcb_check8_3_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010901")){
                setCheck(dxchcjcb_check9_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010902")){
                setCheck(dxchcjcb_check9_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98010903")){
                setCheck(dxchcjcb_check9_3_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011001")){
                setCheck(dxchcjcb_check10_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011002")){
                setCheck(dxchcjcb_check10_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011101")){
                setCheck(dxchcjcb_check11_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011102")){
                setCheck(dxchcjcb_check11_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011103")){
                setCheck(dxchcjcb_check11_3_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011201")){
                setCheck(dxchcjcb_check12_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011202")){
                setCheck(dxchcjcb_check12_2_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011203")){
                setCheck(dxchcjcb_check12_3_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011301")){
                setCheck(dxchcjcb_check13_1_2);
            }else if(level1Items.get(i).getItemFuseId().equals("S_98011302")){
                setCheck(dxchcjcb_check13_2_2);
            }
        }

        String cm="";
        if(taskInfoDetails.get(0).getShipNameCn().equals("")){
            cm= taskInfoDetails.get(0).getShipNameEn();
        }else {
            cm= taskInfoDetails.get(0).getShipNameCn();
        }
        dxshcjcb_et_cm.setText(cm);

        String imo="";
        if(taskInfoDetails.get(0).getShipImo().equals("")){
            imo= "";
        }else {
            imo= taskInfoDetails.get(0).getShipImo();
        }
        dxshcjcb_et_imo.setText(imo);
    }

    private void initView(View view) {
        title=view.findViewById(R.id.table_ferry_titleBar);
        dxshcjcb_sub=view.findViewById(R.id.dxshcjcb_sub);

        mTimePickerView= new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setCyclic(true);
        mTimePickerView.setTime(new Date());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dxchcjcb_tv_time:
                showTimePickView(dxchcjcb_tv_time);
                break;
        }
    }

    private  void  showTimePickView(final TextView textView){
        String trim = textView.getText().toString().trim();
        if(trim.length()<1){
            mTimePickerView.setTime(new Date());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date parse = null;
            try {
                parse = format.parse(trim);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mTimePickerView.setTime(parse);
        }


        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(date);
                textView.setText(time);

            }
        });
        mTimePickerView.show();
    }

    public void setCheck(CheckBox check) {
        check.setChecked(true);
    }


    //获取当前页面数据的回调
    public interface GetDXSHCDataCallBack{

        void getResult(CtSpecialShipType0203 data);

    }

    public void getDXSHCData(GetDXSHCDataCallBack callBack){
        CtSpecialShipType0203 ctSpecialShipType0203=new CtSpecialShipType0203();

        ctSpecialShipType0203.setShipNameCn(dxshcjcb_et_cm.getText().toString().trim());
        ctSpecialShipType0203.setInspectDate(dxchcjcb_tv_time.getText().toString().trim());
        ctSpecialShipType0203.setShipImo(dxshcjcb_et_imo.getText().toString().trim());
        ctSpecialShipType0203.setBerthCode(dxshcjcb_et_gk.getText().toString().trim());

        ctSpecialShipType0203.setInspectOrg("090622");


        ctSpecialShipType0203.setIpspectItem10(getCheckedId(dxchcjcb_check1_1_1,dxchcjcb_check1_1_2,dxchcjcb_check1_1_3));
        ctSpecialShipType0203.setIpspectItem11(getCheckedId(dxchcjcb_check1_2_1,dxchcjcb_check1_2_2,dxchcjcb_check1_2_3));

        ctSpecialShipType0203.setIpspectItem20(getCheckedId(dxchcjcb_check2_1_1,dxchcjcb_check2_1_2,dxchcjcb_check2_1_3));
        ctSpecialShipType0203.setIpspectItem21(getCheckedId(dxchcjcb_check2_2_1,dxchcjcb_check2_2_2,dxchcjcb_check2_2_3));

        ctSpecialShipType0203.setIpspectItem30(getCheckedId(dxchcjcb_check3_1_1,dxchcjcb_check3_1_2,dxchcjcb_check3_1_3));
        ctSpecialShipType0203.setIpspectItem31(getCheckedId(dxchcjcb_check3_2_1,dxchcjcb_check3_2_2,dxchcjcb_check3_2_3));
        ctSpecialShipType0203.setIpspectItem32(getCheckedId(dxchcjcb_check3_3_1,dxchcjcb_check3_3_2,dxchcjcb_check3_3_3));

        ctSpecialShipType0203.setIpspectItem40(getCheckedId(dxchcjcb_check4_1_1,dxchcjcb_check4_1_2,dxchcjcb_check4_1_3));
        ctSpecialShipType0203.setIpspectItem41(getCheckedId(dxchcjcb_check4_2_1,dxchcjcb_check4_2_2,dxchcjcb_check4_2_3));

        ctSpecialShipType0203.setIpspectItem50(getCheckedId(dxchcjcb_check5_1_1,dxchcjcb_check5_1_2,dxchcjcb_check5_1_3));
        ctSpecialShipType0203.setIpspectItem51(getCheckedId(dxchcjcb_check5_2_1,dxchcjcb_check5_2_2,dxchcjcb_check5_2_3));

        ctSpecialShipType0203.setIpspectItem60(getCheckedId(dxchcjcb_check6_1_1,dxchcjcb_check6_1_2,dxchcjcb_check6_1_3));
        ctSpecialShipType0203.setIpspectItem61(getCheckedId(dxchcjcb_check6_2_1,dxchcjcb_check6_2_2,dxchcjcb_check6_2_3));
        ctSpecialShipType0203.setIpspectItem62(getCheckedId(dxchcjcb_check6_3_1,dxchcjcb_check6_3_2,dxchcjcb_check6_3_3));

        ctSpecialShipType0203.setIpspectItem70(getCheckedId(dxchcjcb_check7_1_1,dxchcjcb_check7_1_2,dxchcjcb_check7_1_3));
        ctSpecialShipType0203.setIpspectItem71(getCheckedId(dxchcjcb_check7_2_1,dxchcjcb_check7_2_2,dxchcjcb_check7_2_3));
        ctSpecialShipType0203.setIpspectItem72(getCheckedId(dxchcjcb_check7_3_1,dxchcjcb_check7_3_2,dxchcjcb_check7_3_3));

        ctSpecialShipType0203.setIpspectItem80(getCheckedId(dxchcjcb_check8_1_1,dxchcjcb_check8_1_2,dxchcjcb_check8_1_3));
        ctSpecialShipType0203.setIpspectItem81(getCheckedId(dxchcjcb_check8_2_1,dxchcjcb_check8_2_2,dxchcjcb_check8_2_3));
        ctSpecialShipType0203.setIpspectItem82(getCheckedId(dxchcjcb_check8_3_1,dxchcjcb_check8_3_2,dxchcjcb_check8_3_3));

        ctSpecialShipType0203.setIpspectItem90(getCheckedId(dxchcjcb_check9_1_1,dxchcjcb_check9_1_2,dxchcjcb_check9_1_3));
        ctSpecialShipType0203.setIpspectItem91(getCheckedId(dxchcjcb_check9_2_1,dxchcjcb_check9_2_2,dxchcjcb_check9_2_3));
        ctSpecialShipType0203.setIpspectItem92(getCheckedId(dxchcjcb_check9_3_1,dxchcjcb_check9_3_2,dxchcjcb_check9_3_3));

        ctSpecialShipType0203.setIpspectItem100(getCheckedId(dxchcjcb_check10_1_1,dxchcjcb_check10_1_2,dxchcjcb_check10_1_3));
        ctSpecialShipType0203.setIpspectItem101(getCheckedId(dxchcjcb_check10_2_1,dxchcjcb_check10_2_2,dxchcjcb_check10_2_3));

        ctSpecialShipType0203.setIpspectItem110(getCheckedId(dxchcjcb_check11_1_1,dxchcjcb_check11_1_2,dxchcjcb_check11_1_3));
        ctSpecialShipType0203.setIpspectItem111(getCheckedId(dxchcjcb_check11_2_1,dxchcjcb_check11_2_2,dxchcjcb_check11_2_3));
        ctSpecialShipType0203.setIpspectItem112(getCheckedId(dxchcjcb_check11_3_1,dxchcjcb_check11_3_2,dxchcjcb_check11_3_3));

        ctSpecialShipType0203.setIpspectItem120(getCheckedId(dxchcjcb_check12_1_1,dxchcjcb_check12_1_2,dxchcjcb_check12_1_3));
        ctSpecialShipType0203.setIpspectItem121(getCheckedId(dxchcjcb_check12_2_1,dxchcjcb_check12_2_2,dxchcjcb_check12_2_3));
        ctSpecialShipType0203.setIpspectItem122(getCheckedId(dxchcjcb_check12_3_1,dxchcjcb_check12_3_2,dxchcjcb_check12_3_3));

        ctSpecialShipType0203.setIpspectItem130(getCheckedId(dxchcjcb_check13_1_1,dxchcjcb_check13_1_2,dxchcjcb_check13_1_3));
        ctSpecialShipType0203.setIpspectItem131(getCheckedId(dxchcjcb_check13_2_1,dxchcjcb_check13_1_2,dxchcjcb_check13_2_3));


        if(dxshcjcb_et_gk.getText().toString().trim().equals("")){
            ToastUtils.showToast(getActivity(),"请将大型散货船检查表填写完整！");
            callBack.getResult(null);//表格未填写完整，返回null
            return;
        }

        callBack.getResult(ctSpecialShipType0203);
    }

    /**
     * 获得checkbox选中的值
     * @param dxchcjcb_check1
     * @param dxchcjcb_check2
     * @param dxchcjcb_check3
     * @return
     */
    private String getCheckedId(CheckBox dxchcjcb_check1, CheckBox dxchcjcb_check2, CheckBox dxchcjcb_check3) {

        if(dxchcjcb_check1.isChecked()){
            return "0";
        }else if(dxchcjcb_check2.isChecked()){
            return "1";
        }else if(dxchcjcb_check3.isChecked()){
            return "2";
        }else {
            return "";
        }

    }


}
