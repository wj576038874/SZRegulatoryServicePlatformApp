package com.winfo.szrsp.app.mvp.table.fragmentlist.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter.jdbgPresenter;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.BerthLevelItem0;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.BerthLevelItem1;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.IJdbgActivity;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.ShipBerthDialog;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.jdbgjcxmLayout;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.jdbgwtmsLayout;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.InspectorInfoDialog;
import com.winfo.szrsp.app.mvp.table.fragmentlist.IChecklistMainActvity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.presenter.ChecklistPresenter;
import com.winfo.szrsp.app.mvp.task.view.LevelItem;
import com.winfo.szrsp.app.mvp.task.view.TaskAcceptDialog;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.table.TSiteSupervisionList;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.BitmapUtils;
import com.winfo.szrsp.app.utils.DateFormatUtils;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.LinePathView;
import com.winfo.szrsp.app.widget.SignatureView;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.fragmentlist.view
 * @Filename: ShipsiteFragment
 * @Author: lsj
 * @Date: 2017/12/19  13:05
 * @Description:
 * @Version:船舶现场监督报告
 */
public class ShipsiteFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, IJdbgActivity, IChecklistMainActvity, jdbgjcxmLayout.DelItem {
    private View title;
    private Button btn_save;
    private CheckBox ck_aqjc;
    private CheckBox ck_zxjc;
    private TextView edt_bowei;
    //    private CheckBox item1_ck1, item1_ck2, item1_ck3;
//    private CheckBox item2_ck1, item2_ck2, item2_ck3;
//    private CheckBox item3_ck1, item3_ck2, item3_ck3;
//    private CheckBox item4_ck1, item4_ck2, item4_ck3;
//    private CheckBox item5_ck1, item5_ck2, item5_ck3;
//    private CheckBox item6_ck1, item6_ck2, item6_ck3;
//    private CheckBox item7_ck1, item7_ck2, item7_ck3;
//    private CheckBox item8_ck1, item8_ck2, item8_ck3;
//    private CheckBox item9_ck1, item9_ck2, item9_ck3;
    private EditText edt_zxjc;
    private EditText edt_imo;
    private EditText edt_cbbh;
    private EditText edt_cbglr;
    private EditText edt_cm;
    private EditText edt_gj;
    private EditText edt_cbzl;
    private EditText edt_jcjg;
    private EditText tv_zfry;
    private TextView tv_jcsj;
    private TextView edt_cz_qm;
    private TextView tv_jcy_no;
    private EditText edt_czxm;
    private ImageView img_qm;
    private ImageView img_add;
    private ImageView img_del;
    private ImageView addImg;
    private ImageView delImg;
    private LinearLayout wtms_layout;
    private LinearLayout jcxmLin;
    private jdbgjcxmLayout jdbgjcxmLayout;
    private jdbgwtmsLayout jdbgwtmsLayout;
    private ScrollView ll_sc;
    private TimePickerView timePickerView;
    private int num = 9;
    private String qm_path;
    //    private String ck_item1, ck_item2, ck_item3, ck_item4, ck_item5, ck_item6, ck_item7, ck_item8 = "";
//    private String ck_item9 = "1";
    private String isStart = "";
    private List<LevelItem> level1Items = new ArrayList<>();
    private boolean isLoadData = true;// 是否加载过数据
    private boolean isInitFinish;
    private ChecklistPresenter presenter;
    private List<TaskInfoDetails> shipinfo;
    private List<TaskAssign> taskAssignList;
    private ShipBerthDialog shipBerthDialog;
    private jdbgPresenter jdbgPresenter;
    String selectFuseName = "", selectFuseId = "";
    private Dialog dialog;

    private List<SecurityInspectorInformation> securityInspectorInformationList;
    private InspectorInfoDialog inspectorInfoDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_jdbg, null);
        isInitFinish = true;
        initView(view);
        int value = getArguments().getInt("value");
        if (value == 1) {
            initData();
        }
        initEvent();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        jdbgPresenter.getBerthData(dialog, "", false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isLoadData && isInitFinish) {
            initData();
        }
    }

    private void initEvent() {
        tv_zfry.setOnClickListener(this);
        edt_bowei.setOnClickListener(this);
        tv_jcsj.setOnClickListener(this);
//        item1_ck1.setOnCheckedChangeListener(this);
//        item1_ck2.setOnCheckedChangeListener(this);
//        item1_ck3.setOnCheckedChangeListener(this);
//        item2_ck1.setOnCheckedChangeListener(this);
//        item2_ck2.setOnCheckedChangeListener(this);
//        item2_ck3.setOnCheckedChangeListener(this);
//        item3_ck1.setOnCheckedChangeListener(this);
//        item3_ck2.setOnCheckedChangeListener(this);
//        item3_ck3.setOnCheckedChangeListener(this);
//        item4_ck1.setOnCheckedChangeListener(this);
//        item4_ck2.setOnCheckedChangeListener(this);
//        item4_ck3.setOnCheckedChangeListener(this);
//        item5_ck1.setOnCheckedChangeListener(this);
//        item5_ck2.setOnCheckedChangeListener(this);
//        item5_ck3.setOnCheckedChangeListener(this);
//        item6_ck1.setOnCheckedChangeListener(this);
//        item6_ck2.setOnCheckedChangeListener(this);
//        item6_ck3.setOnCheckedChangeListener(this);
//        item7_ck1.setOnCheckedChangeListener(this);
//        item7_ck2.setOnCheckedChangeListener(this);
//        item7_ck3.setOnCheckedChangeListener(this);
//        item8_ck1.setOnCheckedChangeListener(this);
//        item8_ck2.setOnCheckedChangeListener(this);
//        item8_ck3.setOnCheckedChangeListener(this);
//        item9_ck1.setOnCheckedChangeListener(this);
//        item9_ck2.setOnCheckedChangeListener(this);
//        item9_ck3.setOnCheckedChangeListener(this);
        ck_aqjc.setOnCheckedChangeListener(this);
        img_add.setOnClickListener(this);
        img_del.setOnClickListener(this);
        addImg.setOnClickListener(this);
        delImg.setOnClickListener(this);
        edt_cz_qm.setOnClickListener(this);
        img_qm.setOnClickListener(this);
        ck_zxjc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    edt_zxjc.setEnabled(true);
                } else {
                    edt_zxjc.setEnabled(false);
                }
            }
        });
    }

    private void initData() {
        securityInspectorInformationList = new ArrayList<>();
        Bundle bundle = getArguments();
        shipinfo = (List<TaskInfoDetails>) bundle.getSerializable("taskInfoDetails");
        taskAssignList = (List<TaskAssign>) bundle.getSerializable("taskAssignList");
        String zfry = "";
        for (int i = 0; i < taskAssignList.size(); i++) {
            if (taskAssignList.get(i).getTaskAssignStatus().intValue() == 1) {
                zfry = zfry + taskAssignList.get(i).getTaskArriveUserName() + ",";
            }
        }
//        tv_zfry.setText(zfry.substring(0, zfry.length() - 1));
        title.setVisibility(View.GONE);
        btn_save.setVisibility(View.GONE);
        //presenter.getTaskDefectItemData("RS_00000001");
        level1Items = (List<LevelItem>) getArguments().getSerializable("level1Items");

        jdbgjcxmLayout jdbgjcxmLayout1 = new jdbgjcxmLayout(getActivity(), 1, "中国籍船舶是否开展开航前自查");
        jdbgjcxmLayout1.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout1);
        jdbgjcxmLayout jdbgjcxmLayout2 = new jdbgjcxmLayout(getActivity(), 2, "法定证书文书配备及记录是否齐全");
        jdbgjcxmLayout2.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout2);
        jdbgjcxmLayout jdbgjcxmLayout3 = new jdbgjcxmLayout(getActivity(), 3, "船员配备是否满足要求");
        jcxmLin.addView(jdbgjcxmLayout3);
        jdbgjcxmLayout3.setDelItem(this);
        jdbgjcxmLayout jdbgjcxmLayout4 = new jdbgjcxmLayout(getActivity(), 4, "客货载运及货物系固绑扎是否符合规定");
        jdbgjcxmLayout4.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout4);
        jdbgjcxmLayout jdbgjcxmLayout5 = new jdbgjcxmLayout(getActivity(), 5, "船舶防污染措施是否落实");
        jdbgjcxmLayout5.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout5);
        jdbgjcxmLayout jdbgjcxmLayout6 = new jdbgjcxmLayout(getActivity(), 6, "船舶航行、停泊、作业是否符合规定");
        jdbgjcxmLayout6.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout6);
        jdbgjcxmLayout jdbgjcxmLayout7 = new jdbgjcxmLayout(getActivity(), 7, "船舶是否按要求进行进出港报告或者办理进出港手续");
        jdbgjcxmLayout7.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout7);
        jdbgjcxmLayout jdbgjcxmLayout8 = new jdbgjcxmLayout(getActivity(), 8, "船舶是否按照相关规定缴纳相关费税");
        jdbgjcxmLayout8.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout8);
        jdbgjcxmLayout jdbgjcxmLayout9 = new jdbgjcxmLayout(getActivity(), 9, "其他");
        jdbgjcxmLayout9.setDelItem(this);
        jcxmLin.addView(jdbgjcxmLayout9);
        jdbgjcxmLayout9.setCkItem2Checked();
        for (int i = 0; i < level1Items.size(); i++) {
            if (level1Items.get(i).getItemFuseId().equals("S_01000000")) {
                jdbgjcxmLayout6.setCkItem2Checked();
                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "6", level1Items.get(i).getInput());
                jdbgwtmsLayout.setTag(6);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
            } else if (level1Items.get(i).getItemFuseId().equals("S_02000000")) {
                jdbgjcxmLayout1.setCkItem2Checked();
                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "1", level1Items.get(i).getInput());
                jdbgwtmsLayout.setTag(1);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
            } else if (level1Items.get(i).getItemFuseId().equals("S_03000000")) {
                jdbgjcxmLayout7.setCkItem2Checked();
                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "7", level1Items.get(i).getInput());
                jdbgwtmsLayout.setTag(7);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
            } else if (level1Items.get(i).getItemFuseId().equals("S_04000000")) {

            } else if (level1Items.get(i).getItemFuseId().equals("S_05000000")) {
                jdbgjcxmLayout8.setCkItem2Checked();
                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "8", level1Items.get(i).getInput());
                jdbgwtmsLayout.setTag(8);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
            } else if (level1Items.get(i).getItemFuseId().equals("S_06000000")) {
                jdbgjcxmLayout2.setCkItem2Checked();
                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "2", level1Items.get(i).getInput());
                jdbgwtmsLayout.setTag(2);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
            }
        }


        List<TaskInfoDetails> taskInfoDetails = shipinfo;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        if (taskInfoDetails != null) {
            tv_jcsj.setText(time);
            String cm = "";
            if (taskInfoDetails.get(0).getShipNameCn().equals("")) {
                cm = taskInfoDetails.get(0).getShipNameEn();
            } else {
                cm = taskInfoDetails.get(0).getShipNameCn();
            }
            edt_cm.setText(cm);
            if (taskInfoDetails.get(0).getNationality().equals("")) {
                edt_gj.setText(taskInfoDetails.get(0).getPortOfRegistry());
            } else if (taskInfoDetails.get(0).getPortOfRegistry().equals("")) {
                edt_gj.setText(taskInfoDetails.get(0).getNationality());
            } else if (!taskInfoDetails.get(0).getNationality().equals("") && !taskInfoDetails.get(0).getNationality().equals("")) {
                edt_gj.setText(taskInfoDetails.get(0).getNationality() + "/" + taskInfoDetails.get(0).getPortOfRegistry());
            }
            edt_cbzl.setText(taskInfoDetails.get(0).getShipType());
//            if (!taskInfoDetails.get(0).getShipImo().equals("") && !taskInfoDetails.get(0).getIdentificationNumber().equals("")) {
//                edt_imo.setText(taskInfoDetails.get(0).getShipImo() + "/" + taskInfoDetails.get(0).getIdentificationNumber());
//                edt_imo.setText(taskInfoDetails.get(0).getIdentificationNumber());
//            } else if (taskInfoDetails.get(0).getShipImo().equals("")) {
//                edt_imo.setText(taskInfoDetails.get(0).getIdentificationNumber());
//            } else if (taskInfoDetails.get(0).getIdentificationNumber().equals("")) {
//                edt_imo.setText(taskInfoDetails.get(0).getShipImo());
//                edt_imo.setText("");
//            }
            edt_imo.setText(taskInfoDetails.get(0).getIdentificationNumber());
            edt_cbbh.setText(taskInfoDetails.get(0).getShipNo());

            edt_cbglr.setText(taskInfoDetails.get(0).getShipOperator());
//            edt_zxjc.setText("");
            //tv_zfry.setText(taskAssign.getTaskArriveUserName());

//            String zfry="";
//            for (int i = 0; i <taskAssignList.size() ; i++) {
//                if(taskAssignList.get(i).getTaskAssignStatus().intValue()==1){
//                    zfry=zfry+taskAssignList.get(i).getTaskArriveUserName()+",";
//                }
//            }
//            tv_zfry.setText(zfry.substring(0,zfry.length()-1));
            //edt_jcjg.setText(taskInfo.getTaskArriveOrgName());
            String orgName = ACache.get(getActivity()).getAsString("orgName");
            edt_jcjg.setText(orgName);

        } else {
            ToastUtils.showToast(getActivity(), "没有获取到船舶信息，请手动填写！");
        }
    }

    private void initView(View view) {
        jdbgPresenter = new jdbgPresenter(this);
        presenter = new ChecklistPresenter(this);
        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        tv_jcsj = view.findViewById(R.id.tv_sj);
        wtms_layout = view.findViewById(R.id.wtms_layout);
        jcxmLin = view.findViewById(R.id.jcxmLin);
//        jdbgjcxmLayout = new jdbgjcxmLayout(getActivity(), num);
//        jcxmLin.addView(jdbgjcxmLayout);
//        jdbgjcxmLayout.setDelItem(this);
//        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "9", "");
//        jdbgwtmsLayout.setTag(9);
//        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
        img_add = view.findViewById(R.id.add_img);
        img_del = view.findViewById(R.id.del_img);
        addImg = view.findViewById(R.id.addImg);
        delImg = view.findViewById(R.id.delImg);
        ck_aqjc = view.findViewById(R.id.ck_qdcbaqjc);
//        item1_ck1 = view.findViewById(R.id.item1_ck1);
//        item1_ck2 = view.findViewById(R.id.item1_ck2);
//        item1_ck3 = view.findViewById(R.id.item1_ck3);
//        item2_ck1 = view.findViewById(R.id.item2_ck1);
//        item2_ck2 = view.findViewById(R.id.item2_ck2);
//        item2_ck3 = view.findViewById(R.id.item2_ck3);
//        item3_ck1 = view.findViewById(R.id.item3_ck1);
//        item3_ck2 = view.findViewById(R.id.item3_ck2);
//        item3_ck3 = view.findViewById(R.id.item3_ck3);
//        item4_ck1 = view.findViewById(R.id.item4_ck1);
//        item4_ck2 = view.findViewById(R.id.item4_ck2);
//        item4_ck3 = view.findViewById(R.id.item4_ck3);
//        item5_ck1 = view.findViewById(R.id.item5_ck1);
//        item5_ck2 = view.findViewById(R.id.item5_ck2);
//        item5_ck3 = view.findViewById(R.id.item5_ck3);
//        item6_ck1 = view.findViewById(R.id.item6_ck1);
//        item6_ck2 = view.findViewById(R.id.item6_ck2);
//        item6_ck3 = view.findViewById(R.id.item6_ck3);
//        item7_ck1 = view.findViewById(R.id.item7_ck1);
//        item7_ck2 = view.findViewById(R.id.item7_ck2);
//        item7_ck3 = view.findViewById(R.id.item7_ck3);
//        item8_ck1 = view.findViewById(R.id.item8_ck1);
//        item8_ck2 = view.findViewById(R.id.item8_ck2);
//        item8_ck3 = view.findViewById(R.id.item8_ck3);
//        item9_ck1 = view.findViewById(R.id.item9_ck1);
//        item9_ck2 = view.findViewById(R.id.item9_ck2);
//        item9_ck3 = view.findViewById(R.id.item9_ck3);
        edt_bowei = view.findViewById(R.id.edt_bowei);
        edt_czxm = view.findViewById(R.id.edt_czxm);
        edt_imo = view.findViewById(R.id.edt_imo);
        edt_cbbh = view.findViewById(R.id.edt_cbbh);
        edt_cbglr = view.findViewById(R.id.edt_cbglr);
        edt_cm = view.findViewById(R.id.edt_cm);
        edt_gj = view.findViewById(R.id.edt_gj);
        edt_cbzl = view.findViewById(R.id.edt_cbzl);
        tv_zfry = view.findViewById(R.id.tv_zfry);

        title = view.findViewById(R.id.title);
        btn_save = view.findViewById(R.id.btn_save);
        ck_zxjc = view.findViewById(R.id.ck_zxjc);
        edt_zxjc = view.findViewById(R.id.edt_zxjc);
        edt_jcjg = view.findViewById(R.id.edt_jcjg);
        edt_cz_qm = view.findViewById(R.id.edt_cz_qm);
        ll_sc = view.findViewById(R.id.ll_sc);
        img_qm = view.findViewById(R.id.cz_qm);
        tv_jcy_no = view.findViewById(R.id.tv_jcy_no);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_cz_qm:
                writeLine();
                break;
            case R.id.cz_qm:
                showQMDialog(qm_path, 101);
                break;
//            case R.id.add_img:
//                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "");
//                wtms_layout.addView(jdbgwtmsLayout);
//                break;
//            case R.id.del_img:
//                if (wtms_layout.getChildCount() != 0) {
//                    wtms_layout.removeViewAt(wtms_layout.getChildCount() - 1);
//                }
//                break;
            case R.id.tv_sj:
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String time = format.format(date);
                        tv_jcsj.setText(time);
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_zfry:
//                presenter.getUsersListByUserDept(true);


                if (securityInspectorInformationList.size() > 0) {
                    showInspectorInfoDialog();
                } else {
                    StringBuilder uuid = new StringBuilder();
                    int count = taskAssignList.size();
                    for (int i = 0; i < count; i++) {
                        uuid.append(taskAssignList.get(i).getTaskArriveUserUuid());
                        if (i < count - 1) {
                            uuid.append(",");
                        }
                    }
                    presenter.getInspectors(uuid.toString());
                }
                break;
            case R.id.addImg:
                num += 1;
                jdbgjcxmLayout = new jdbgjcxmLayout(getActivity(), num, "其他");
                jcxmLin.addView(jdbgjcxmLayout);
                jdbgjcxmLayout.setDelItem(this);
                jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), num + "", "");
                jdbgwtmsLayout.setTag(num);
                int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
                wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
                break;
            case R.id.delImg:
                if (jcxmLin.getChildCount() != 0 && jcxmLin.getChildCount() > 9) {
                    jcxmLin.removeViewAt(jcxmLin.getChildCount() - 1);
                    num -= 1;
                    if (wtms_layout.getChildCount() != 0) {
                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                            View view1 = wtms_layout.getChildAt(i);
                            TextView tv = view1.findViewById(R.id.tvIndex);
                            if (String.valueOf(num + 1).equals(tv.getText().toString().trim())) {
                                wtms_layout.removeView(view1);
                            }
                        }
                    }
                }
                break;

            case R.id.edt_bowei:
                List<ShipBerthData> data = getData();
                if (data == null) {
                    jdbgPresenter.getBerthData(dialog, "", true);
                    return;
                }
                List<MultiItemEntity> datas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    BerthLevelItem0 berthLevelItem0 = new BerthLevelItem0();
                    berthLevelItem0.setId(data.get(i).getId());
                    berthLevelItem0.setName(data.get(i).getName());
                    berthLevelItem0.setPid(data.get(i).getPid());
                    if (data.get(i).getName().equals(selectFuseName)) {
                        berthLevelItem0.setChecked(true);
                    } else {
                        berthLevelItem0.setChecked(false);
                    }
                    berthLevelItem0.setCanClick(data.get(i).isCanClick());
                    for (int j = 0; j < data.get(i).getChild().size(); j++) {
                        if (data.get(i).getChild().size() > 0 && data.get(i).getChild() != null) {
                            BerthLevelItem1 berthLevelItem1 = new BerthLevelItem1();
                            berthLevelItem1.setId(data.get(i).getChild().get(j).getId());
                            berthLevelItem1.setName(data.get(i).getChild().get(j).getName());
                            berthLevelItem1.setPid(data.get(i).getChild().get(j).getPid());
                            if (data.get(i).getChild().get(j).getName().equals(selectFuseName)) {
                                berthLevelItem1.setChecked(true);
                            } else {
                                berthLevelItem1.setChecked(false);
                            }
                            berthLevelItem1.setCanClick(data.get(i).getChild().get(j).isCanClick());
                            berthLevelItem0.addSubItem(berthLevelItem1);
                        }
                    }
                    datas.add(berthLevelItem0);
                }

                shipBerthDialog = new ShipBerthDialog(getActivity(), datas);
                shipBerthDialog.show();
                shipBerthDialog.setOnData(new ShipBerthDialog.OnGetData() {
                    @Override
                    public void onDataCallBack(Map<String, Object> map) {
                        edt_bowei.setText(map.get("name").toString());
                        selectFuseName = map.get("name").toString();
                        selectFuseId = map.get("id").toString();
                    }
                });
                shipBerthDialog.setOnSearchShipData(new ShipBerthDialog.OnSearchShipData() {
                    @Override
                    public void onSearchDataCallBack(String taskCode, String taskContent) {
                        edt_bowei.setText(taskContent);
                        selectFuseName = taskContent;
                        selectFuseId = taskCode;
                    }
                });
                break;
        }
    }

    private void showInspectorInfoDialog() {
        inspectorInfoDialog.showCheckBoxDialog();
    }


    private int width;
    // 距离两边的像素
    private int magin;
    private View qmView;// 签名所承载的view
    private Dialog qm_dialog;
    private ImageView dialog_record_pen_iv;
    private Button dialog_record_ok;
    private Button dialog_record_reautograph;

    @SuppressLint("InflateParams")
    private void showQMDialog(String path, final int result) {

        width = DeviceUtils.getScreenSize(getActivity())[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(getActivity(), 10);

        qm_dialog = new AlertDialog.Builder(getActivity()).create();
        qm_dialog.setCancelable(true);
        qm_dialog.setCanceledOnTouchOutside(true);
        qmView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_qianming, null);
        qm_dialog.show();
        qm_dialog.setContentView(qmView);
        Window window = qm_dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        assert window != null;
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(windowparams);
        dialog_record_pen_iv = (ImageView) qmView.findViewById(R.id.dialog_record_pen_iv);
        Uri uri = Uri.fromFile(new File(path));
        dialog_record_pen_iv.setImageURI(uri);

        dialog_record_ok = (Button) qmView.findViewById(R.id.dialog_record_ok);
        dialog_record_reautograph = (Button) qmView.findViewById(R.id.dialog_record_reautograph);

        dialog_record_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
            }
        });
        dialog_record_reautograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qm_dialog.dismiss();
                writeLine();
            }
        });
    }

    /**
     * 签名
     */
    private void writeLine() {

        SignatureView view1 = new SignatureView(getActivity(), ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                qm_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate() + "qm.png";
                try {
                    linePathView.save(qm_path, false, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BitmapUtils.getImageThumbnail(qm_path, 80, 80);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(qm_path, options);
                img_qm.setImageBitmap(bm);
                edt_cz_qm.setVisibility(View.GONE);
                img_qm.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()) {
//            case R.id.item1_ck1:
//                if (isCheck) {
//                    item1_ck1.setChecked(true);
//                    item1_ck2.setChecked(false);
//                    item1_ck3.setChecked(false);
//                    ck_item1 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("1".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item1 = "";
//                }
//                break;
//            case R.id.item1_ck2:
//                if (isCheck) {
//                    item1_ck1.setChecked(false);
//                    item1_ck2.setChecked(true);
//                    item1_ck3.setChecked(false);
//                    ck_item1 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "1", null);
//                    jdbgwtmsLayout.setTag(1);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item1 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("1".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//
//                break;
//            case R.id.item1_ck3:
//                if (isCheck) {
//                    item1_ck1.setChecked(false);
//                    item1_ck2.setChecked(false);
//                    item1_ck3.setChecked(true);
//                    ck_item1 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("1".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item1 = "";
//                }
//                break;
//
//            case R.id.item2_ck1:
//                if (isCheck) {
//                    item2_ck1.setChecked(true);
//                    item2_ck2.setChecked(false);
//                    item2_ck3.setChecked(false);
//                    ck_item2 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("2".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item2 = "";
//                }
//                break;
//            case R.id.item2_ck2:
//                if (isCheck) {
//                    item2_ck1.setChecked(false);
//                    item2_ck2.setChecked(true);
//                    item2_ck3.setChecked(false);
//                    ck_item2 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "2", null);
//                    jdbgwtmsLayout.setTag(2);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item2 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("2".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item2_ck3:
//                if (isCheck) {
//                    item2_ck1.setChecked(false);
//                    item2_ck2.setChecked(false);
//                    item2_ck3.setChecked(true);
//                    ck_item2 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("2".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item2 = "";
//                }
//                break;
//
//            case R.id.item3_ck1:
//                if (isCheck) {
//                    item3_ck1.setChecked(true);
//                    item3_ck2.setChecked(false);
//                    item3_ck3.setChecked(false);
//                    ck_item3 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("3".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item3 = "";
//                }
//                break;
//            case R.id.item3_ck2:
//                if (isCheck) {
//                    item3_ck1.setChecked(false);
//                    item3_ck2.setChecked(true);
//                    item3_ck3.setChecked(false);
//                    ck_item3 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "3", null);
//                    jdbgwtmsLayout.setTag(3);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item3 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("3".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item3_ck3:
//                if (isCheck) {
//                    item3_ck1.setChecked(false);
//                    item3_ck2.setChecked(false);
//                    item3_ck3.setChecked(true);
//                    ck_item3 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("3".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item3 = "";
//                }
//                break;
//
//            case R.id.item4_ck1:
//                if (isCheck) {
//                    item4_ck1.setChecked(true);
//                    item4_ck2.setChecked(false);
//                    item4_ck3.setChecked(false);
//                    ck_item4 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("4".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item4 = "";
//                }
//                break;
//            case R.id.item4_ck2:
//                if (isCheck) {
//                    item4_ck1.setChecked(false);
//                    item4_ck2.setChecked(true);
//                    item4_ck3.setChecked(false);
//                    ck_item4 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "4", null);
//                    jdbgwtmsLayout.setTag(4);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item4 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("4".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item4_ck3:
//                if (isCheck) {
//                    item4_ck1.setChecked(false);
//                    item4_ck2.setChecked(false);
//                    item4_ck3.setChecked(true);
//                    ck_item4 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("4".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item4 = "";
//                }
//                break;
//
//            case R.id.item5_ck1:
//                if (isCheck) {
//                    item5_ck1.setChecked(true);
//                    item5_ck2.setChecked(false);
//                    item5_ck3.setChecked(false);
//                    ck_item5 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("5".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item5 = "";
//                }
//                break;
//            case R.id.item5_ck2:
//                if (isCheck) {
//                    item5_ck1.setChecked(false);
//                    item5_ck2.setChecked(true);
//                    item5_ck3.setChecked(false);
//                    ck_item5 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "5", null);
//                    jdbgwtmsLayout.setTag(5);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item5 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("5".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item5_ck3:
//                if (isCheck) {
//                    item5_ck1.setChecked(false);
//                    item5_ck2.setChecked(false);
//                    item5_ck3.setChecked(true);
//                    ck_item5 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("5".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item5 = "";
//                }
//                break;
//
//            case R.id.item6_ck1:
//                if (isCheck) {
//                    item6_ck1.setChecked(true);
//                    item6_ck2.setChecked(false);
//                    item6_ck3.setChecked(false);
//                    ck_item6 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("6".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item6 = "";
//                }
//                break;
//            case R.id.item6_ck2:
//                if (isCheck) {
//                    item6_ck1.setChecked(false);
//                    item6_ck2.setChecked(true);
//                    item6_ck3.setChecked(false);
//                    ck_item6 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "6", null);
//                    jdbgwtmsLayout.setTag(6);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item6 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("6".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item6_ck3:
//                if (isCheck) {
//                    item6_ck1.setChecked(false);
//                    item6_ck2.setChecked(false);
//                    item6_ck3.setChecked(true);
//                    ck_item6 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("6".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item6 = "";
//                }
//                break;
//
//            case R.id.item7_ck1:
//                if (isCheck) {
//                    item7_ck1.setChecked(true);
//                    item7_ck2.setChecked(false);
//                    item7_ck3.setChecked(false);
//                    ck_item7 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("7".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item6 = "";
//                }
//                break;
//            case R.id.item7_ck2:
//                if (isCheck) {
//                    item7_ck1.setChecked(false);
//                    item7_ck2.setChecked(true);
//                    item7_ck3.setChecked(false);
//                    ck_item7 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "7", null);
//                    jdbgwtmsLayout.setTag(7);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item7 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("7".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item7_ck3:
//                if (isCheck) {
//                    item7_ck1.setChecked(false);
//                    item7_ck2.setChecked(false);
//                    item7_ck3.setChecked(true);
//                    ck_item7 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("7".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item7 = "";
//                }
//                break;
//
//            case R.id.item8_ck1:
//                if (isCheck) {
//                    item8_ck1.setChecked(true);
//                    item8_ck2.setChecked(false);
//                    item8_ck3.setChecked(false);
//                    ck_item8 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("8".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item8 = "";
//                }
//                break;
//            case R.id.item8_ck2:
//                if (isCheck) {
//                    item8_ck1.setChecked(false);
//                    item8_ck2.setChecked(true);
//                    item8_ck3.setChecked(false);
//                    ck_item8 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "8", null);
//                    jdbgwtmsLayout.setTag(8);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item8 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("8".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item8_ck3:
//                if (isCheck) {
//                    item8_ck1.setChecked(false);
//                    item8_ck2.setChecked(false);
//                    item8_ck3.setChecked(true);
//                    ck_item8 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("8".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item8 = "";
//                }
//                break;
//            case R.id.item9_ck1:
//                if (isCheck) {
//                    item9_ck1.setChecked(true);
//                    item9_ck2.setChecked(false);
//                    item9_ck3.setChecked(false);
//                    ck_item9 = "0";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("9".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item9 = "";
//                }
//                break;
//            case R.id.item9_ck2:
//                if (isCheck) {
//                    item9_ck1.setChecked(false);
//                    item9_ck2.setChecked(true);
//                    item9_ck3.setChecked(false);
//                    ck_item9 = "1";
//                    jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "9", null);
//                    jdbgwtmsLayout.setTag(9);
//                    int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                    wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                } else {
//                    ck_item9 = "";
//                    if (wtms_layout.getChildCount() != 0) {
//                        for (int i = 0; i < wtms_layout.getChildCount(); i++) {
//                            View view = wtms_layout.getChildAt(i);
//                            TextView tv = view.findViewById(R.id.tvIndex);
//                            if ("9".equals(tv.getText().toString().trim())) {
//                                wtms_layout.removeView(view);
//                            }
//                        }
//                    }
//                }
//                break;
//            case R.id.item9_ck3:
//                if (isCheck) {
//                    item9_ck1.setChecked(false);
//                    item9_ck2.setChecked(false);
//                    item9_ck3.setChecked(true);
//                    ck_item9 = "2";
//                    if (wtms_layout.getChildCount() != 0) {
//                        TextView tv = jdbgwtmsLayout.findViewById(R.id.tvIndex);
//                        if ("9".equals(tv.getText().toString().trim())) {
//                            wtms_layout.removeView(tv);
//                        }
//                    }
//                } else {
//                    ck_item9 = "";
//                }
//                break;
            case R.id.ck_qdcbaqjc:
                if (isCheck) {
                    ck_aqjc.setChecked(true);
                    isStart = "1";
                } else {
                    isStart = "";
                }
                break;
        }
    }

    private int getAddViewIndex(LinearLayout wtms_layout, jdbgwtmsLayout jdbgwtmsLayout) {
        if (wtms_layout.getChildCount() > 0) {
            for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                if ((int) jdbgwtmsLayout.getTag() < (int) wtms_layout.getChildAt(i).getTag()) {
                    return i;
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    @Override
    public Dialog getDiaole() {
        Dialog dialog = DialogUtils.createLoadingDialog(getActivity(), "加载中...");
        return dialog;
    }

    @Override
    public void setTaskDefectItemData(List<TaskDefectItemData> taskDefectItemData) {
        isLoadData = true;
//        level1Items = (List<LevelItem>) getArguments().getSerializable("level1Items");
//        for (int i = 0; i < taskDefectItemData.size(); i++) {
//            for (int j = 0; j < level1Items.size(); j++) {
//                if (level1Items.get(j).getItemFuseId().equals(taskDefectItemData.get(i).getItemFuseId())) {
//                    if (taskDefectItemData.get(i).getSpotCode().equals("1")) {
//                        item1_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "1", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("2")) {
//                        item2_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "2", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("3")) {
//                        item3_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "3", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("4")) {
//                        item4_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "4", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("5")) {
//                        item5_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "5", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("6")) {
//                        item6_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "6", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("7")) {
//                        item7_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "7", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    } else if (taskDefectItemData.get(i).getSpotCode().equals("8")) {
//                        item8_ck2.setChecked(true);
//                        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), "8", level1Items.get(j).getInput());
//                        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
//                        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
//                    }
//                }
//            }
//        }
        List<TaskInfoDetails> taskInfoDetails = shipinfo;
        // TaskInfo taskInfo = (TaskInfo) getArguments().getSerializable("taskInfo");


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        if (taskInfoDetails != null) {
            tv_jcsj.setText(time);
            String cm = "";
            if (taskInfoDetails.get(0).getShipNameCn().equals("")) {
                cm = taskInfoDetails.get(0).getShipNameEn();
            } else {
                cm = taskInfoDetails.get(0).getShipNameCn();
            }
            edt_cm.setText(cm);
            if (taskInfoDetails.get(0).getNationality().equals("")) {
                edt_gj.setText(taskInfoDetails.get(0).getPortOfRegistry());
            } else if (taskInfoDetails.get(0).getPortOfRegistry().equals("")) {
                edt_gj.setText(taskInfoDetails.get(0).getNationality());
            } else if (!taskInfoDetails.get(0).getNationality().equals("") && !taskInfoDetails.get(0).getNationality().equals("")) {
                edt_gj.setText(taskInfoDetails.get(0).getNationality() + "/" + taskInfoDetails.get(0).getPortOfRegistry());
            }
            edt_cbzl.setText(taskInfoDetails.get(0).getShipType());
            if (!taskInfoDetails.get(0).getShipImo().equals("") && !taskInfoDetails.get(0).getIdentificationNumber().equals("")) {
                edt_imo.setText(taskInfoDetails.get(0).getShipImo() + "/" + taskInfoDetails.get(0).getIdentificationNumber());
            } else if (taskInfoDetails.get(0).getShipImo().equals("")) {
                edt_imo.setText(taskInfoDetails.get(0).getIdentificationNumber());
            } else if (taskInfoDetails.get(0).getIdentificationNumber().equals("")) {
                edt_imo.setText(taskInfoDetails.get(0).getShipImo());
            }
            edt_cbbh.setText(taskInfoDetails.get(0).getShipNo());

            edt_cbglr.setText(taskInfoDetails.get(0).getShipOperator());
            edt_zxjc.setText("");
            //tv_zfry.setText(taskAssign.getTaskArriveUserName());

//            String zfry="";
//            for (int i = 0; i <taskAssignList.size() ; i++) {
//                if(taskAssignList.get(i).getTaskAssignStatus().intValue()==1){
//                    zfry=zfry+taskAssignList.get(i).getTaskArriveUserName()+",";
//                }
//            }
//            tv_zfry.setText(zfry.substring(0,zfry.length()-1));
            //edt_jcjg.setText(taskInfo.getTaskArriveOrgName());
            String orgName = ACache.get(getActivity()).getAsString("orgName");
            edt_jcjg.setText(orgName);

        } else {
            ToastUtils.showToast(getActivity(), "没有获取到船舶信息，请手动填写！");
        }
    }

    @Override
    public Dialog getDialog() {
        return null;
    }

    @Override
    public void OnSuccess(String msg) {

    }

    @Override
    public void OnFaile(String msg) {
        isLoadData = true;
        ToastUtils.showToast(getActivity(), msg);
    }

    @Override
    public void setDetailData(DetailcbxcjdbgData detailData) {

    }

    private List<ShipBerthData> getData() {
        return shipBerthData;
    }

    @Override
    public void setShipBerthData(List<ShipBerthData> datas) {
        this.shipBerthData = bulid(datas);
    }

    List<ShipBerthData> shipBerthData;

    public List<ShipBerthData> bulid(List<ShipBerthData> allTaskTypeList) {
        List<ShipBerthData> shipBerthData = new ArrayList<>();
        for (ShipBerthData allTaskType : allTaskTypeList) {
            if ("ROOT".equals(allTaskType.getPid())) {
                shipBerthData.add(allTaskType);
            }
            for (ShipBerthData it : allTaskTypeList) {
                if (it.getPid().equals(allTaskType.getId())) {
                    if (allTaskType.getChild() == null) {
                        allTaskType.setChild(new ArrayList<ShipBerthData>());
                    }
                    allTaskType.getChild().add(it);
                }
            }
        }
        return shipBerthData;
    }

    TaskAcceptDialog taskAcceptDialog;

    @Override
    public void setUsersListSucceed(TaskPersonResouse taskPersonResouse) {
//        if (taskAcceptDialog == null) {
//            taskAcceptDialog = new TaskAcceptDialog(getActivity(), taskPersonResouse, ACache.get(SzRspApplication.getContext()).getAsString("uuid"), "", false);
//            taskAcceptDialog.showDialog();
//            taskAcceptDialog.setOnData(new TaskAcceptDialog.OnGetData() {
//                @Override
//                public void onDataCallBack(List<UserInfo> userInfoListReturn, List<TaskResources> taskResourcesListReturn) {
//                    taskAcceptDialog.dismiss();
//                    List<String> strings = new ArrayList<>();
//                    for (int i = 0; i < userInfoListReturn.size(); i++) {
//                        strings.add(userInfoListReturn.get(i).getUserName());
//                    }
//                    StringBuilder text = new StringBuilder();
//                    for (int i = 0; i < strings.size(); i++) {
//                        if (i < strings.size() - 1) {
//                            text.append(strings.get(i));
//                            text.append(",");
//                        } else {
//                            text.append(strings.get(i));
//                        }
//                    }
//                    String str = text.toString();
//                    edt_zfry.setText(str);
//                }
//            });
//        } else {
//            taskAcceptDialog.showDialog();
//        }
    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {

    }

    @Override
    public TaskFinishSubData getTaskFinishSubData() {
        return null;
    }

    @Override
    public void finishSucceed(TaskFinishData data, String msg) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(getActivity(), msg, msg);
    }

    @Override
    public void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList) {

        securityInspectorInformationList.addAll(inspectorInformationList);
        inspectorInfoDialog = new InspectorInfoDialog("检查员列表", getActivity(), securityInspectorInformationList, new InspectorInfoDialog.GetCheckBoxDialogDataCallBack() {
            @Override
            public void getDataCallBack(Map<Integer, Boolean> map) {
                int count = map.size();
                if (count > 0) {
                    int pointer = 0;
                    StringBuilder name = new StringBuilder();
                    StringBuilder no = new StringBuilder();
                    for (Integer index : map.keySet()) {
                        SecurityInspectorInformation securityInspectorInformation = securityInspectorInformationList.get(index);
                        name.append(securityInspectorInformation.getInspector_name());
                        no.append(securityInspectorInformation.getInspector_card());
                        if (pointer < count - 1) {
                            name.append(",");
                            no.append(",");
                        }
                    }
                    tv_zfry.setText(name.toString());
                    tv_jcy_no.setText(no.toString());
                } else {
                    tv_zfry.setText("");
                    tv_jcy_no.setText("");
                }
            }
        });
        showInspectorInfoDialog();

    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {

    }

    @Override
    public void addItems(String indexItem) {
        jdbgwtmsLayout = new jdbgwtmsLayout(getActivity(), indexItem, "");
        jdbgwtmsLayout.setTag(Integer.parseInt(indexItem));
        int addViewIndex = getAddViewIndex(wtms_layout, jdbgwtmsLayout);
        wtms_layout.addView(jdbgwtmsLayout, addViewIndex);
    }

    @Override
    public void delItems(String indexItem) {
        if (wtms_layout.getChildCount() != 0) {
            for (int i = 0; i < wtms_layout.getChildCount(); i++) {
                View view = wtms_layout.getChildAt(i);
                TextView tv = view.findViewById(R.id.tvIndex);
                if (indexItem.equals(tv.getText().toString().trim())) {
                    wtms_layout.removeView(view);
                }
            }
        }
    }


    //获取当前Fragment页面数据的接口回调
    public interface GetJdbgDataCallBack {
        void getResult(cbxcjdbgData data, String siteCaptain);
    }

    public void getJdbgData(GetJdbgDataCallBack callBack) {

        cbxcjdbgData.info info = new cbxcjdbgData.info();
        List<cbxcjdbgData.detail> detail = new ArrayList<>();
        int count = wtms_layout.getChildCount();
        for (int i = 0; i < count; i++) {
            jdbgwtmsLayout jdbgwtmsLayout = (jdbgwtmsLayout) wtms_layout.getChildAt(i);
            if (jdbgwtmsLayout.getData() == null) {
                return;
            } else {
                detail.add(jdbgwtmsLayout.getData());
            }
        }

        List<TSiteSupervisionList> lists = new ArrayList<>();
        for (int i = 0; i < jcxmLin.getChildCount(); i++) {
            jdbgjcxmLayout jdbgjcxmLayout = (jdbgjcxmLayout) jcxmLin.getChildAt(i);
            if (jdbgjcxmLayout.getData() == null) {
                return;
            } else {
                lists.add(jdbgjcxmLayout.getData());
            }
        }


        if (edt_cbbh.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写舶现场监督船舶编号！");
            callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (edt_czxm.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写舶现场监督船长姓名！");
            callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (qm_path == null || qm_path.equals("")) {
            ToastUtils.showToast(getActivity(), "船舶现场监督缺少船长签名！");
            callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (!ACache.get(getActivity()).getAsString("userNum").contains("test") && !ACache.get(getActivity()).getAsString("userNum").contains("winfo")) {
            if (tv_zfry.getText().toString().equals("")) {
                ToastUtils.showToast(getActivity(), "请选择舶现场监督执法员");
                callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
                return;
            }

            if (tv_jcy_no.getText().toString().equals("")) {
                ToastUtils.showToast(getActivity(), "请输入执法证号");
                callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
                return;
            }
        }

        if (edt_jcjg.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写舶现场监督检查机构！");
            callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
            return;
        }
        info.setInitialInspectMark("0");//设置初查复查   初查0   复查1
        info.setShipNo(edt_cbbh.getText().toString());
        info.setShipId(edt_imo.getText().toString());
        info.setShipNameCn(edt_cm.getText().toString());
        info.setShipManager(edt_cbglr.getText().toString());
        info.setRegportName(edt_gj.getText().toString());
        info.setInspectDate(tv_jcsj.getText().toString());
        info.setInspectOrg(edt_jcjg.getText().toString());
        info.setShipTypeNameCn(edt_cbzl.getText().toString());
        info.setShipOperator(edt_cbglr.getText().toString());
        info.setCaptainName(edt_czxm.getText().toString());
        info.setNum(jcxmLin.getChildCount() + "");
        info.setBerthName(edt_bowei.getText().toString().trim());
        info.setBerthCode(selectFuseId);
        info.setIsInspect(isStart);
        if (ck_zxjc.isChecked()) {
            info.setIsSpecialInspect("1");
        } else {
            info.setIsSpecialInspect("0");
        }
        info.setSpecialInspectName(edt_zxjc.getText().toString());
//        info.setInspectItem1(ck_item1);
//        info.setInspectItem2(ck_item2);
//        info.setInspectItem3(ck_item3);
//        info.setInspectItem4(ck_item4);
//        info.setInspectItem5(ck_item5);
//        info.setInspectItem6(ck_item6);
//        info.setInspectItem7(ck_item7);
//        info.setInspectItem8(ck_item8);
//        info.setInspectItem9(ck_item9);
        info.setInspectorName(tv_zfry.getText().toString());

        String inspectorCodeOld = tv_jcy_no.getText().toString();
        String inspectorCodeNew;
        if (inspectorCodeOld.contains("，")) {//将中文字符的逗号转换成英文字符的逗号
            inspectorCodeNew = inspectorCodeOld.replace("，", ",");
        } else {
            inspectorCodeNew = inspectorCodeOld;
        }
        info.setInspectorCode(inspectorCodeNew);
        if (edt_gj.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请将船舶现场监督报告表船籍港填写完整！");
            callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (edt_cbglr.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请将舶现场监督报告表船舶管理人填写完整！");
            callBack.getResult(null, qm_path);//表格没有填写完整，所以传null
            return;
        }
        cbxcjdbgData data = new cbxcjdbgData();
        data.setInfo(info);
        data.setDetail(detail);
        data.setList(lists);
        callBack.getResult(data, qm_path);
    }

}
