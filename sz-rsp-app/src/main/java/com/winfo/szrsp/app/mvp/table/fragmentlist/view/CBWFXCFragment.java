package com.winfo.szrsp.app.mvp.table.fragmentlist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view.CBWFQTFXLayout;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view.WFXCChuliDialog;
import com.winfo.szrsp.app.mvp.task.view.LevelItem;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspect;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspectDetail;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2018-01-30.
 */

public class CBWFXCFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.add_img)
    ImageView add_img;
    @BindView(R.id.del_img)
    ImageView del_img;
    @BindView(R.id.cbwfxcjd_tl_qtfxwt)
    LinearLayout cbwfxcjd_tl_qtfxwt;

    @BindView(R.id.cbwfxcjd_et_zwcm)
    EditText cbwfxcjd_et_zwcm;
    @BindView(R.id.cbwfxcjd_et_imo)
    EditText cbwfxcjd_et_imo;
    @BindView(R.id.cbwfxcjd_et_gj)
    EditText cbwfxcjd_et_gj;
    @BindView(R.id.cbwfxcjd_et_zd)
    EditText cbwfxcjd_et_zd;
    @BindView(R.id.cbwfxcjd_et_place)
    EditText cbwfxcjd_et_place;
    @BindView(R.id.cbwfxcjd_et_zydw)
    EditText cbwfxcjd_et_zydw;
    @BindView(R.id.cbwfxcjd_et_zybw)
    EditText cbwfxcjd_et_zybw;
    @BindView(R.id.cbwfxcjd_et_zhg)
    EditText cbwfxcjd_et_zhg;
    @BindView(R.id.cbwfxcjd_et_xhg)
    EditText cbwfxcjd_et_xhg;
    @BindView(R.id.cbwfxcjd_et_jcry)
    EditText cbwfxcjd_et_jcry;
    @BindView(R.id.cbwfxcjd_sp_zylb)
    Spinner cbwfxcjd_sp_zylb;
    @BindView(R.id.dxchcjcb_tv_time)
    TextView dxchcjcb_tv_time;

    @BindView(R.id.cbwfxcjd_tv_clyj_1_1)
    TextView cbwfxcjd_tv_clyj_1_1;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_2)
    TextView cbwfxcjd_tv_clyj_1_2;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_3)
    TextView cbwfxcjd_tv_clyj_1_3;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_4)
    TextView cbwfxcjd_tv_clyj_1_4;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_5)
    TextView cbwfxcjd_tv_clyj_1_5;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_6)
    TextView cbwfxcjd_tv_clyj_1_6;
    @BindView(R.id.cbwfxcjd_tv_clyj_1_7)
    TextView cbwfxcjd_tv_clyj_1_7;

    @BindView(R.id.cbwfxcjd_tv_clyj_2_1)
    TextView cbwfxcjd_tv_clyj_2_1;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_2)
    TextView cbwfxcjd_tv_clyj_2_2;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_3)
    TextView cbwfxcjd_tv_clyj_2_3;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_4)
    TextView cbwfxcjd_tv_clyj_2_4;
    @BindView(R.id.cbwfxcjd_tv_clyj_2_5)
    TextView cbwfxcjd_tv_clyj_2_5;

    @BindView(R.id.cbwfxcjd_tv_clyj_3_1)
    TextView cbwfxcjd_tv_clyj_3_1;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_2)
    TextView cbwfxcjd_tv_clyj_3_2;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_3)
    TextView cbwfxcjd_tv_clyj_3_3;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_4)
    TextView cbwfxcjd_tv_clyj_3_4;
    @BindView(R.id.cbwfxcjd_tv_clyj_3_5)
    TextView cbwfxcjd_tv_clyj_3_5;

    @BindView(R.id.cbwfxcjd_et_qxms_1_1)
    EditText cbwfxcjd_et_qxms_1_1;
    @BindView(R.id.cbwfxcjd_et_qxms_1_2)
    EditText cbwfxcjd_et_qxms_1_2;
    @BindView(R.id.cbwfxcjd_et_qxms_1_3)
    EditText cbwfxcjd_et_qxms_1_3;
    @BindView(R.id.cbwfxcjd_et_qxms_1_4)
    EditText cbwfxcjd_et_qxms_1_4;
    @BindView(R.id.cbwfxcjd_et_qxms_1_5)
    EditText cbwfxcjd_et_qxms_1_5;
    @BindView(R.id.cbwfxcjd_et_qxms_1_6)
    EditText cbwfxcjd_et_qxms_1_6;
    @BindView(R.id.cbwfxcjd_et_qxms_1_7)
    EditText cbwfxcjd_et_qxms_1_7;

    @BindView(R.id.cbwfxcjd_et_qxms_2_1)
    EditText cbwfxcjd_et_qxms_2_1;
    @BindView(R.id.cbwfxcjd_et_qxms_2_2)
    EditText cbwfxcjd_et_qxms_2_2;
    @BindView(R.id.cbwfxcjd_et_qxms_2_3)
    EditText cbwfxcjd_et_qxms_2_3;
    @BindView(R.id.cbwfxcjd_et_qxms_2_4)
    EditText cbwfxcjd_et_qxms_2_4;
    @BindView(R.id.cbwfxcjd_et_qxms_2_5)
    EditText cbwfxcjd_et_qxms_2_5;

    @BindView(R.id.cbwfxcjd_et_qxms_3_1)
    EditText cbwfxcjd_et_qxms_3_1;
    @BindView(R.id.cbwfxcjd_et_qxms_3_2)
    EditText cbwfxcjd_et_qxms_3_2;
    @BindView(R.id.cbwfxcjd_et_qxms_3_3)
    EditText cbwfxcjd_et_qxms_3_3;
    @BindView(R.id.cbwfxcjd_et_qxms_3_4)
    EditText cbwfxcjd_et_qxms_3_4;
    @BindView(R.id.cbwfxcjd_et_qxms_3_5)
    EditText cbwfxcjd_et_qxms_3_5;

    @BindView(R.id.cbwfxcjd_et_zgjg_1_1)
    EditText cbwfxcjd_et_zgjg_1_1;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_2)
    EditText cbwfxcjd_et_zgjg_1_2;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_3)
    EditText cbwfxcjd_et_zgjg_1_3;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_4)
    EditText cbwfxcjd_et_zgjg_1_4;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_5)
    EditText cbwfxcjd_et_zgjg_1_5;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_6)
    EditText cbwfxcjd_et_zgjg_1_6;
    @BindView(R.id.cbwfxcjd_et_zgjg_1_7)
    EditText cbwfxcjd_et_zgjg_1_7;

    @BindView(R.id.cbwfxcjd_et_zgjg_2_1)
    EditText cbwfxcjd_et_zgjg_2_1;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_2)
    EditText cbwfxcjd_et_zgjg_2_2;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_3)
    EditText cbwfxcjd_et_zgjg_2_3;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_4)
    EditText cbwfxcjd_et_zgjg_2_4;
    @BindView(R.id.cbwfxcjd_et_zgjg_2_5)
    EditText cbwfxcjd_et_zgjg_2_5;

    @BindView(R.id.cbwfxcjd_et_zgjg_3_1)
    EditText cbwfxcjd_et_zgjg_3_1;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_2)
    EditText cbwfxcjd_et_zgjg_3_2;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_3)
    EditText cbwfxcjd_et_zgjg_3_3;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_4)
    EditText cbwfxcjd_et_zgjg_3_4;
    @BindView(R.id.cbwfxcjd_et_zgjg_3_5)
    EditText cbwfxcjd_et_zgjg_3_5;

    @BindView(R.id.cbwfxc_sub)
    Button cbwfxc_sub;

    private List<LevelItem> level1Items = new ArrayList<>();
    private List<TaskInfoDetails> taskInfoDetails;
    private boolean isLoadData = false;// 是否加载过数据
    private boolean isInitFinish;

    private CBWFQTFXLayout cbwfqtfxLayout;
    private List<String> sp_data;
    private String zylb="";
    int number=1;
    private TimePickerView timePickerView;
    private WFXCChuliDialog wfxcChuliDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cbwfxcjd, null);
        isInitFinish = true;
        ButterKnife.bind(this,view);
        initView(view);
        //int value = getArguments().getInt("value");
        //if (value == 1){
            initData();
        //}
        initEvent();

        return view;
    }

    private void initData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        dxchcjcb_tv_time.setText(time);
        cbwfxcjd_et_jcry.setText(ACache.get(getActivity()).getAsString("userName"));

        level1Items = (List<LevelItem>) getArguments().getSerializable("level1Items");
        taskInfoDetails = (List<TaskInfoDetails>) getArguments().getSerializable("taskInfoDetails");

        if (taskInfoDetails!=null){
            cbwfxcjd_et_zwcm.setText(taskInfoDetails.get(0).getShipNameCn());
            if(taskInfoDetails.get(0).getShipNameEn().equals("")){
                cbwfxcjd_et_imo.setText(taskInfoDetails.get(0).getShipImo());
            }  else {
                cbwfxcjd_et_imo.setText(taskInfoDetails.get(0).getShipNameEn());
            }

            if(taskInfoDetails.get(0).getNationality().equals("")){
                cbwfxcjd_et_gj.setText(taskInfoDetails.get(0).getPortOfRegistry());
            }else {
                cbwfxcjd_et_gj.setText(taskInfoDetails.get(0).getNationality());
            }


        }else {
            ToastUtils.showToast(getActivity(),"没有获取到船舶信息，请手动填写！");
        }

        if(level1Items!=null){
            for (int i = 0; i < level1Items.size(); i++) {
                switch (level1Items.get(i).getItemFuseId()){
                    case "S_06010800":
                        cbwfxcjd_et_qxms_1_1.setText(level1Items.get(i).getInput());
                        break;
                    case "S_06011400":
                        cbwfxcjd_et_qxms_1_2.setText(level1Items.get(i).getInput());
                        break;
                    case "S_06011500":
                        cbwfxcjd_et_qxms_1_3.setText(level1Items.get(i).getInput());
                        break;
                    case "S_06011600":
                        cbwfxcjd_et_qxms_1_4.setText(level1Items.get(i).getInput());
                        break;
                    case "S_06011700":
                        cbwfxcjd_et_qxms_1_5.setText(level1Items.get(i).getInput());
                        break;
                    case "S_06011800":
                        cbwfxcjd_et_qxms_1_6.setText(level1Items.get(i).getInput());
                        break;
                    case "S_06011900":
                        cbwfxcjd_et_qxms_1_7.setText(level1Items.get(i).getInput());
                        break;
                    case "S_09050100":
                        cbwfxcjd_et_qxms_2_1.setText(level1Items.get(i).getInput());
                        break;
                    case "S_09050200":
                        cbwfxcjd_et_qxms_2_2.setText(level1Items.get(i).getInput());
                        break;
                    case "S_09050300":
                        cbwfxcjd_et_qxms_2_3.setText(level1Items.get(i).getInput());
                        break;
                    case "S_09050400":
                        cbwfxcjd_et_qxms_2_4.setText(level1Items.get(i).getInput());
                        break;
                    case "S_09050500":
                        cbwfxcjd_et_qxms_2_5.setText(level1Items.get(i).getInput());
                        break;
                    case "S_10042300":
                        cbwfxcjd_et_qxms_3_1.setText(level1Items.get(i).getInput());
                        break;
                    case "S_10042400":
                        cbwfxcjd_et_qxms_3_2.setText(level1Items.get(i).getInput());
                        break;
                    case "S_10042500":
                        cbwfxcjd_et_qxms_3_3.setText(level1Items.get(i).getInput());
                        break;
                    case "S_10042600":
                        cbwfxcjd_et_qxms_3_4.setText(level1Items.get(i).getInput());
                        break;
                    case "S_10042200":
                        cbwfxcjd_et_qxms_3_5.setText(level1Items.get(i).getInput());
                        break;

                }
            }
        }

    }

    private void initView(View view) {
        cbwfxc_sub.setVisibility(View.GONE);
        cbwfqtfxLayout=new CBWFQTFXLayout(getActivity(),"4."+number);
        cbwfxcjd_tl_qtfxwt.addView(cbwfqtfxLayout);
        sp_data=new ArrayList<>();
        sp_data.add("---请选择---");
        sp_data.add("WF01:船舶残油、油污水排放现场监督检查");
        sp_data.add("WF02:船舶洗舱水排放现场监督检查");
        sp_data.add("WF03:船舶压载水排放现场监督检查");
        sp_data.add("WF04:船舶生活污水处置现场监督检查");
        sp_data.add("WF05 :船舶垃圾排放现场监督检查");
        sp_data.add("WF06:船舶清舱作业现场监督检查");
        sp_data.add("WF07:化学品船洗舱作业现场监督检查");
        sp_data.add("WF08:船舶原油洗舱作业现场监督检查");
        sp_data.add("WF09:船舶驱气作业现场监督检查");
        sp_data.add("WF10:液货船装卸作业现场监督检查");
        sp_data.add("WF11:液货船水上过驳作业现场监督检查");
        sp_data.add("WF12:船舶载运固体散装货物作业现场监督检查");
        sp_data.add("WF13:客滚船（汽渡）载运危险货物现场监督检查");
        sp_data.add("WF14:船舶修、造作业现场监督检查");
        sp_data.add("WF15:沉船打捞现场防污染监督检查");
        sp_data.add("WF16:沿海港口船舶舷外拷铲、油漆作业现场监督检查");
        sp_data.add("WF17:船舶冲洗沾有污染物、有毒有害物质的甲板作业现场监督检查");
        sp_data.add("WF18:船舶铅封现场监督检查、船舶铅封现场监督检查");
        sp_data.add("WF19:船舶供、受油作业现场监督检查");
        sp_data.add("WF20:船舶燃油质量监督检查");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SzRspApplication.getContext(),R.layout.spinner_item_1,sp_data);
        adapter.setDropDownViewResource(R.layout.dropdown_style_1);
        cbwfxcjd_sp_zylb.setAdapter(adapter);
        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());

    }
    private void initEvent() {
        cbwfxcjd_sp_zylb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    zylb="";
                }else {
                    zylb = sp_data.get(i).substring(0,4);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        add_img.setOnClickListener(this);
        del_img.setOnClickListener(this);
        dxchcjcb_tv_time.setOnClickListener(this);

        cbwfxcjd_tv_clyj_1_1.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_2.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_3.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_4.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_5.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_6.setOnClickListener(this);
        cbwfxcjd_tv_clyj_1_7.setOnClickListener(this);

        cbwfxcjd_tv_clyj_2_1.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_2.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_3.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_4.setOnClickListener(this);
        cbwfxcjd_tv_clyj_2_5.setOnClickListener(this);

        cbwfxcjd_tv_clyj_3_1.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_2.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_3.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_4.setOnClickListener(this);
        cbwfxcjd_tv_clyj_3_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_img:
                number+=1;
                cbwfqtfxLayout=new CBWFQTFXLayout(getActivity(),"4."+number);
                cbwfxcjd_tl_qtfxwt.addView(cbwfqtfxLayout);
                break;
            case R.id.del_img:
                if(cbwfxcjd_tl_qtfxwt.getChildCount()>1){
                    cbwfxcjd_tl_qtfxwt.removeViewAt(cbwfxcjd_tl_qtfxwt.getChildCount()-1);
                    number-=1;
                }
                break;
            case R.id.dxchcjcb_tv_time:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        dxchcjcb_tv_time.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.cbwfxcjd_tv_clyj_1_1:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_1);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_2:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_2);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_3:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_3);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_4:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_4);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_5:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_5);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_6:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_6);
                break;
            case R.id.cbwfxcjd_tv_clyj_1_7:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_1_7);
                break;

            case R.id.cbwfxcjd_tv_clyj_2_1:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_1);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_2:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_2);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_3:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_3);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_4:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_4);
                break;
            case R.id.cbwfxcjd_tv_clyj_2_5:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_2_5);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_1:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_1);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_2:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_2);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_3:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_3);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_4:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_4);
                break;
            case R.id.cbwfxcjd_tv_clyj_3_5:
                showWFXCChuliDialog(cbwfxcjd_tv_clyj_3_5);
                break;
        }
    }
    private void showWFXCChuliDialog(final TextView textView) {
        if (wfxcChuliDialog==null){
            wfxcChuliDialog=new WFXCChuliDialog(getActivity());
        }
        wfxcChuliDialog.show(textView.getText().toString());
        wfxcChuliDialog.setOnData(new WFXCChuliDialog.OnGetData() {
            @Override
            public void onDataCallBack(String chuli_1) {
                textView.setText(chuli_1);
            }
        });
    }

    //获取当前Fragment页面数据的回调
    public interface GetSupervisionDataCallback{
        void getResult(CtSafeInspectNoticeObject data);
    }

    public void getCBEFXCData(GetSupervisionDataCallback callback){
        if(cbwfxcjd_et_zwcm.getText().toString().trim().equals("")
                ||cbwfxcjd_et_imo.getText().toString().trim().equals("")){
            ToastUtils.showToast(getActivity(),"中英文船名或IMO不能空！");
            callback.getResult(null);//表格未填写完整，所以返回null
            return;
        }
        if(cbwfxcjd_et_gj.getText().toString().trim().equals("")){
            ToastUtils.showToast(getActivity(),"国籍/船籍港不能空！");
            callback.getResult(null);//表格未填写完整，所以返回null
            return;
        }
        if(cbwfxcjd_et_jcry.getText().toString().trim().equals("")){
            ToastUtils.showToast(getActivity(),"检察人员不能空！");
            callback.getResult(null);//表格未填写完整，所以返回null
            return;
        }
        if(cbwfxcjd_et_place.getText().toString().trim().equals("")){
            ToastUtils.showToast(getActivity(),"检察地点不能空！");
            callback.getResult(null);//表格未填写完整，所以返回null
            return;
        }
        CtSafeInspectNoticeObject ctSafeInspectNoticeObject=new CtSafeInspectNoticeObject();
        //基本信息
        CtSafetySceneInspect ctSafetySceneInspect=new CtSafetySceneInspect();
        ctSafetySceneInspect.setShipNameCn(cbwfxcjd_et_zwcm.getText().toString());
        ctSafetySceneInspect.setShipNameEn(cbwfxcjd_et_imo.getText().toString());
        ctSafetySceneInspect.setRegportName(cbwfxcjd_et_gj.getText().toString());
        ctSafetySceneInspect.setShipGrosston(cbwfxcjd_et_zd.getText().toString());
        ctSafetySceneInspect.setBerthCode(cbwfxcjd_et_place.getText().toString());
        ctSafetySceneInspect.setInspectDate(dxchcjcb_tv_time.getText().toString());
        ctSafetySceneInspect.setTaskOrg(cbwfxcjd_et_zydw.getText().toString());
        ctSafetySceneInspect.setTaskTypeName(zylb);
        ctSafetySceneInspect.setTaskPort(cbwfxcjd_et_zybw.getText().toString());
        ctSafetySceneInspect.setLastPort(cbwfxcjd_et_zhg.getText().toString());
        ctSafetySceneInspect.setNextPort(cbwfxcjd_et_xhg.getText().toString());
        ctSafetySceneInspect.setInspectorName(cbwfxcjd_et_jcry.getText().toString());
        ctSafeInspectNoticeObject.setInfo(ctSafetySceneInspect);
        //  表格数据
        List<CtSafetySceneInspectDetail> list=new ArrayList<>();

        CtSafetySceneInspectDetail ct_1_0=new CtSafetySceneInspectDetail();
        ct_1_0.setSex("1.0");
        ct_1_0.setItemNum("1");
        ct_1_0.setItemName("船舶证书、文书、资料");
        ct_1_0.setDescribe("");
        ct_1_0.setHandle("");
        ct_1_0.setRectificat("");
        list.add(ct_1_0);

        CtSafetySceneInspectDetail ct_1_1=new CtSafetySceneInspectDetail();
        ct_1_1.setSex("1.1");
        ct_1_1.setItemNum("1.1");
        ct_1_1.setItemName("船舶油污应急计划（船上海洋污染应急计划）");
        if(cbwfxcjd_et_qxms_1_1.getText().toString().equals("")){
            ct_1_1.setDescribe("00");
        }else {
            ct_1_1.setDescribe(cbwfxcjd_et_qxms_1_1.getText().toString());
        }
        ct_1_1.setHandle(cbwfxcjd_tv_clyj_1_1.getText().toString());
        ct_1_1.setRectificat(cbwfxcjd_et_zgjg_1_1.getText().toString());
        list.add(ct_1_1);


        CtSafetySceneInspectDetail ct_1_2=new CtSafetySceneInspectDetail();
        ct_1_2.setSex("1.2");
        ct_1_2.setItemNum("1.2");
        ct_1_2.setItemName("船舶垃圾管理计划");
        if(cbwfxcjd_et_qxms_1_2.getText().toString().equals("")){
            ct_1_2.setDescribe("00");
        }else {
            ct_1_2.setDescribe(cbwfxcjd_et_qxms_1_2.getText().toString());
        }
        ct_1_2.setHandle(cbwfxcjd_tv_clyj_1_2.getText().toString());
        ct_1_2.setRectificat(cbwfxcjd_et_zgjg_1_2.getText().toString());
        list.add(ct_1_2);

        CtSafetySceneInspectDetail ct_1_3=new CtSafetySceneInspectDetail();
        ct_1_3.setSex("1.3");
        ct_1_3.setItemNum("1.3");
        ct_1_3.setItemName("船舶油类记录簿");
        if(cbwfxcjd_et_qxms_1_3.getText().toString().equals("")){
            ct_1_3.setDescribe("00");
        }else {
            ct_1_3.setDescribe(cbwfxcjd_et_qxms_1_3.getText().toString());
        }
        ct_1_3.setHandle(cbwfxcjd_tv_clyj_1_3.getText().toString());
        ct_1_3.setRectificat(cbwfxcjd_et_zgjg_1_3.getText().toString());
        list.add(ct_1_3);


        CtSafetySceneInspectDetail ct_1_4=new CtSafetySceneInspectDetail();
        ct_1_4.setSex("1.4");
        ct_1_4.setItemNum("1.4");
        ct_1_4.setItemName("船舶垃圾记录簿");
        if(cbwfxcjd_et_qxms_1_4.getText().toString().equals("")){
            ct_1_4.setDescribe("00");
        }else {
            ct_1_4.setDescribe(cbwfxcjd_et_qxms_1_4.getText().toString());
        }
        ct_1_4.setHandle(cbwfxcjd_tv_clyj_1_4.getText().toString());
        ct_1_4.setRectificat(cbwfxcjd_et_zgjg_1_4.getText().toString());
        list.add(ct_1_4);


        CtSafetySceneInspectDetail ct_1_5=new CtSafetySceneInspectDetail();
        ct_1_5.setSex("1.5");
        ct_1_5.setItemNum("1.5");
        ct_1_5.setItemName("船舶燃油质量报告单");
        if(cbwfxcjd_et_qxms_1_5.getText().toString().equals("")){
            ct_1_5.setDescribe("00");
        }else {
            ct_1_5.setDescribe(cbwfxcjd_et_qxms_1_5.getText().toString());
        }
        ct_1_5.setHandle(cbwfxcjd_tv_clyj_1_5.getText().toString());
        ct_1_5.setRectificat(cbwfxcjd_et_zgjg_1_5.getText().toString());
        list.add(ct_1_5);

        CtSafetySceneInspectDetail ct_1_6=new CtSafetySceneInspectDetail();
        ct_1_6.setSex("1.6");
        ct_1_6.setItemNum("1.6");
        ct_1_6.setItemName("作业审批单是否已经过审批或报备");
        if(cbwfxcjd_et_qxms_1_6.getText().toString().equals("")){
            ct_1_6.setDescribe("00");
        }else {
            ct_1_6.setDescribe(cbwfxcjd_et_qxms_1_6.getText().toString());
        }
        ct_1_6.setHandle(cbwfxcjd_tv_clyj_1_6.getText().toString());
        ct_1_6.setRectificat(cbwfxcjd_et_zgjg_1_6.getText().toString());
        list.add(ct_1_6);

        CtSafetySceneInspectDetail ct_1_7=new CtSafetySceneInspectDetail();
        ct_1_7.setSex("1.7");
        ct_1_7.setItemNum("1.7");
        ct_1_7.setItemName("其他船舶证书、文书、资料及记录");
        if(cbwfxcjd_et_qxms_1_7.getText().toString().equals("")){
            ct_1_7.setDescribe("00");
        }else {
            ct_1_7.setDescribe(cbwfxcjd_et_qxms_1_7.getText().toString());
        }
        ct_1_7.setHandle(cbwfxcjd_tv_clyj_1_7.getText().toString());
        ct_1_7.setRectificat(cbwfxcjd_et_zgjg_1_7.getText().toString());
        list.add(ct_1_7);


        CtSafetySceneInspectDetail ct_2_0=new CtSafetySceneInspectDetail();
        ct_2_0.setSex("2.0");
        ct_2_0.setItemNum("2");
        ct_2_0.setItemName("设备设施检查");
        ct_2_0.setDescribe("");
        ct_2_0.setHandle("");
        ct_2_0.setRectificat("");
        list.add(ct_2_0);

        CtSafetySceneInspectDetail ct_2_1=new CtSafetySceneInspectDetail();
        ct_2_1.setSex("2.1");
        ct_2_1.setItemNum("2.1");
        ct_2_1.setItemName("油水分离器");
        if(cbwfxcjd_et_qxms_2_1.getText().toString().equals("")){
            ct_2_1.setDescribe("00");
        }else {
            ct_2_1.setDescribe(cbwfxcjd_et_qxms_2_1.getText().toString());
        }
        ct_2_1.setHandle(cbwfxcjd_tv_clyj_2_1.getText().toString());
        ct_2_1.setRectificat(cbwfxcjd_et_zgjg_2_1.getText().toString());
        list.add(ct_2_1);


        CtSafetySceneInspectDetail ct_2_2=new CtSafetySceneInspectDetail();
        ct_2_2.setSex("2.2");
        ct_2_2.setItemNum("2.2");
        ct_2_2.setItemName("渣油的储存、排放与焚烧");
        if(cbwfxcjd_et_qxms_2_2.getText().toString().equals("")){
            ct_2_2.setDescribe("00");
        }else {
            ct_2_2.setDescribe(cbwfxcjd_et_qxms_2_2.getText().toString());
        }
        ct_2_2.setHandle(cbwfxcjd_tv_clyj_2_2.getText().toString());
        ct_2_2.setRectificat(cbwfxcjd_et_zgjg_2_2.getText().toString());
        list.add(ct_2_2);

        CtSafetySceneInspectDetail ct_2_3=new CtSafetySceneInspectDetail();
        ct_2_3.setSex("2.3");
        ct_2_3.setItemNum("2.3");
        ct_2_3.setItemName("机舱舱底水排放管系及有关泵");
        if(cbwfxcjd_et_qxms_2_3.getText().toString().equals("")){
            ct_2_3.setDescribe("00");
        }else {
            ct_2_3.setDescribe(cbwfxcjd_et_qxms_2_3.getText().toString());
        }
        ct_2_3.setHandle(cbwfxcjd_tv_clyj_2_3.getText().toString());
        ct_2_3.setRectificat(cbwfxcjd_et_zgjg_2_3.getText().toString());
        list.add(ct_2_3);


        CtSafetySceneInspectDetail ct_2_4=new CtSafetySceneInspectDetail();
        ct_2_4.setSex("2.4");
        ct_2_4.setItemNum("2.4");
        ct_2_4.setItemName("焚烧炉");
        if(cbwfxcjd_et_qxms_2_4.getText().toString().equals("")){
            ct_2_4.setDescribe("00");
        }else {
            ct_2_4.setDescribe(cbwfxcjd_et_qxms_2_4.getText().toString());
        }
        ct_2_4.setHandle(cbwfxcjd_tv_clyj_2_4.getText().toString());
        ct_2_4.setRectificat(cbwfxcjd_et_zgjg_2_4.getText().toString());
        list.add(ct_2_4);


        CtSafetySceneInspectDetail ct_2_5=new CtSafetySceneInspectDetail();
        ct_2_5.setSex("2.5");
        ct_2_5.setItemNum("2.5");
        ct_2_5.setItemName("其他设备设施");
        if(cbwfxcjd_et_qxms_2_5.getText().toString().equals("")){
            ct_2_5.setDescribe("00");
        }else {
            ct_2_5.setDescribe(cbwfxcjd_et_qxms_2_5.getText().toString());
        }
        ct_2_5.setHandle(cbwfxcjd_tv_clyj_2_5.getText().toString());
        ct_2_5.setRectificat(cbwfxcjd_et_zgjg_2_5.getText().toString());
        list.add(ct_2_5);



        CtSafetySceneInspectDetail ct_3_0=new CtSafetySceneInspectDetail();
        ct_3_0.setSex("3.0");
        ct_3_0.setItemNum("3");
        ct_3_0.setItemName("作业操作性检查");
        ct_3_0.setDescribe("");
        ct_3_0.setHandle("");
        ct_3_0.setRectificat("");
        list.add(ct_3_0);

        CtSafetySceneInspectDetail ct_3_1=new CtSafetySceneInspectDetail();
        ct_3_1.setSex("3.1");
        ct_3_1.setItemNum("3.1");
        ct_3_1.setItemName("作业人员是否经过培训或持有相关证书");
        if(cbwfxcjd_et_qxms_3_1.getText().toString().equals("")){
            ct_3_1.setDescribe("00");
        }else {
            ct_3_1.setDescribe(cbwfxcjd_et_qxms_3_1.getText().toString());
        }
        ct_3_1.setHandle(cbwfxcjd_tv_clyj_3_1.getText().toString());
        ct_3_1.setRectificat(cbwfxcjd_et_zgjg_3_1.getText().toString());
        list.add(ct_3_1);


        CtSafetySceneInspectDetail ct_3_2=new CtSafetySceneInspectDetail();
        ct_3_2.setSex("3.2");
        ct_3_2.setItemNum("3.2");
        ct_3_2.setItemName("作业双方是否安排并保持有效的值班");
        if(cbwfxcjd_et_qxms_3_2.getText().toString().equals("")){
            ct_3_2.setDescribe("00");
        }else {
            ct_3_2.setDescribe(cbwfxcjd_et_qxms_3_2.getText().toString());
        }
        ct_3_2.setHandle(cbwfxcjd_tv_clyj_3_2.getText().toString());
        ct_3_2.setRectificat(cbwfxcjd_et_zgjg_3_2.getText().toString());
        list.add(ct_3_2);

        CtSafetySceneInspectDetail ct_3_3=new CtSafetySceneInspectDetail();
        ct_3_3.setSex("3.3");
        ct_3_3.setItemNum("3.3");
        ct_3_3.setItemName("作业前双方按要求填写安全检查表或确认书（适用时）");
        if(cbwfxcjd_et_qxms_3_3.getText().toString().equals("")){
            ct_3_3.setDescribe("00");
        }else {
            ct_3_3.setDescribe(cbwfxcjd_et_qxms_3_3.getText().toString());
        }
        ct_3_3.setHandle(cbwfxcjd_tv_clyj_3_3.getText().toString());
        ct_3_3.setRectificat(cbwfxcjd_et_zgjg_3_3.getText().toString());
        list.add(ct_3_3);


        CtSafetySceneInspectDetail ct_3_4=new CtSafetySceneInspectDetail();
        ct_3_4.setSex("3.4");
        ct_3_4.setItemNum("3.4");
        ct_3_4.setItemName("是否按要求采取安全与防污染防范措施");
        if(cbwfxcjd_et_qxms_3_4.getText().toString().equals("")){
            ct_3_4.setDescribe("00");
        }else {
            ct_3_4.setDescribe(cbwfxcjd_et_qxms_3_4.getText().toString());
        }
        ct_3_4.setHandle(cbwfxcjd_tv_clyj_3_4.getText().toString());
        ct_3_4.setRectificat(cbwfxcjd_et_zgjg_3_4.getText().toString());
        list.add(ct_3_4);


        CtSafetySceneInspectDetail ct_3_5=new CtSafetySceneInspectDetail();
        ct_3_5.setSex("3.5");
        ct_3_5.setItemNum("3.5");
        ct_3_5.setItemName("其他操作性检查");
        if(cbwfxcjd_et_qxms_3_5.getText().toString().equals("")){
            ct_3_5.setDescribe("00");
        }else {
            ct_3_5.setDescribe(cbwfxcjd_et_qxms_3_5.getText().toString());
        }
        ct_3_5.setHandle(cbwfxcjd_tv_clyj_3_5.getText().toString());
        ct_3_5.setRectificat(cbwfxcjd_et_zgjg_3_5.getText().toString());
        list.add(ct_3_5);


        CtSafetySceneInspectDetail ct_4_0=new CtSafetySceneInspectDetail();
        ct_4_0.setSex("4.0");
        ct_4_0.setItemNum("4");
        ct_4_0.setItemName("其他发现问题描述");
        ct_4_0.setDescribe("");
        ct_4_0.setHandle("");
        ct_4_0.setRectificat("");
        list.add(ct_4_0);
        for (int i = 0; i <cbwfxcjd_tl_qtfxwt.getChildCount() ; i++) {
            CBWFQTFXLayout cbwfqtfxLayout = (CBWFQTFXLayout) cbwfxcjd_tl_qtfxwt.getChildAt(i);
            CtSafetySceneInspectDetail ct=cbwfqtfxLayout.getData();
            if(ct.getItemName().equals("")&&ct.getHandle().equals("")&&ct.getRectificat().equals("")){

            }else {
                list.add(ct);
            }


        }
        ctSafeInspectNoticeObject.setDetails(list);

        callback.getResult(ctSafeInspectNoticeObject);
    }
}
