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
import android.util.Log;
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

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view.WFXCChuliDialog;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.InspectorInfoDialog;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.ZXJCDialog;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.cljdLayout;
import com.winfo.szrsp.app.mvp.table.fragmentlist.IChecklistMainActvity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.presenter.ChecklistPresenter;
import com.winfo.szrsp.app.mvp.task.view.LevelItem;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
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
 * @Filename: ShipflagFragment
 * @Author: lsj
 * @Date: 2017/12/19  13:06
 * @Description:
 * @Version:船旗国监督检查报告
 */
public class ShipflagFragment extends Fragment implements View.OnClickListener, IChecklistMainActvity {

    private View title;
    private Button btn_save;
    private LinearLayout ll_layout;
    private com.winfo.szrsp.app.mvp.table.cqgjdbg.view.cljdLayout cljdLayout;
    private ImageView img_add;
    private ImageView img_del;
    private ImageView qm_img;
    private TextView tv_qm;
    private String qm_path;

    private EditText edt_cm;
    private EditText edt_gj;
    private EditText edt_cbzl;
    private EditText edt_imo;
    private EditText edt_cbbh;
    private EditText edt_cbglr;
    private CheckBox ck_zxjc;
    private TextView edt_zxjc;
    private CheckBox ck_1;
    private CheckBox ck_2;
    private CheckBox ck_3;
    private CheckBox ck_4;
    private CheckBox ck_5;
    private EditText edt_jcjg;
    private EditText edt_fcqz1;
    private EditText edt_fcqz2;

    private ScrollView ll_sc;
    private ImageView jcry_qm_img;
    private TextView jcry_tv_qm;
    private String jcry_qm_path;
    private TextView tv_jcsj;
    private TimePickerView timePickerView;
    private List<LevelItem> level1Items = new ArrayList<>();
    private List<TaskInfoDetails> taskInfoDetails;
    private ChecklistPresenter presenter;
    private boolean isLoadData = false;// 是否加载过数据
    private boolean isInitFinish;
    private List<DefectCode> defectCodes;
    private List<TaskDefectItemData> taskDefectItemData;
    private List<TaskAssign> taskAssignList;
    private String specialInspectType = "0000000001";
    private ZXJCDialog zxjcDialog;
    private EditText edt_czxm;

    private TextView tv_find_jcry;
    private TextView tv_jcry_no;
    private List<SecurityInspectorInformation> securityInspectorInformationList;
    private InspectorInfoDialog inspectorInfoDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_supervision, null);
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isLoadData && isInitFinish) {
            initData();
        }
    }

    private void initEvent() {
        img_add.setOnClickListener(this);
        img_del.setOnClickListener(this);
        qm_img.setOnClickListener(this);
        tv_qm.setOnClickListener(this);
        jcry_qm_img.setOnClickListener(this);
        jcry_tv_qm.setOnClickListener(this);
        tv_jcsj.setOnClickListener(this);
        edt_zxjc.setOnClickListener(this);
        tv_find_jcry.setOnClickListener(this);

//        ck_zxjc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    edt_zxjc.setEnabled(true);
//                }else {
//                    edt_zxjc.setEnabled(false);
//                }
//            }
//        });
    }

    private void initData() {
        securityInspectorInformationList = new ArrayList<>();
        taskAssignList = (List<TaskAssign>) getArguments().getSerializable("taskAssignList");
        level1Items = (List<LevelItem>) getArguments().getSerializable("level1Items");
        taskInfoDetails = (List<TaskInfoDetails>) getArguments().getSerializable("taskInfoDetails");
        // TaskInfo taskInfo = (TaskInfo) getArguments().getSerializable("taskInfo");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String time = format.format(new Date());
        tv_jcsj.setText(time);
        if (taskInfoDetails != null) {
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
            if (taskInfoDetails.get(0).getShipCompanyName().equals("") || taskInfoDetails.get(0).getShipCompanyName() == null) {
                edt_cbglr.setText(taskInfoDetails.get(0).getShipOperator());
            } else {
                edt_cbglr.setText(taskInfoDetails.get(0).getShipCompanyName());
            }
            //edt_jcjg.setText(taskInfo.getTaskArriveOrgName());
            String orgName = ACache.get(getActivity()).getAsString("orgName");
            edt_jcjg.setText(orgName);
        } else {
            ToastUtils.showToast(getActivity(), "没有获取到船舶信息，请手动填写！");
        }
        presenter.getTaskDefectItemData("RS_00000002");
    }

    private void initView(View view) {
        presenter = new ChecklistPresenter(this);
        ll_sc = view.findViewById(R.id.ll_sc);
        title = view.findViewById(R.id.title);
        btn_save = view.findViewById(R.id.btn_save);
        ll_layout = view.findViewById(R.id.cljd_layout);

        img_add = view.findViewById(R.id.add_img);
        img_del = view.findViewById(R.id.del_img);
        qm_img = view.findViewById(R.id.qm_img);
        tv_qm = view.findViewById(R.id.tv_qm);
        jcry_qm_img = view.findViewById(R.id.img_jcry_qm);
        jcry_tv_qm = view.findViewById(R.id.tv_jcry);
        tv_jcsj = view.findViewById(R.id.tv_jcsj);
        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        edt_cm = view.findViewById(R.id.edt_cm);
        edt_gj = view.findViewById(R.id.edt_gj);
        edt_cbzl = view.findViewById(R.id.edt_cbzl);
        edt_imo = view.findViewById(R.id.edt_imo);
        edt_cbbh = view.findViewById(R.id.edt_cbbh);
        edt_cbglr = view.findViewById(R.id.edt_cbglr);
        ck_zxjc = view.findViewById(R.id.ck_zxjc);
        edt_zxjc = view.findViewById(R.id.edt_zxjc);
        ck_1 = view.findViewById(R.id.ck_1);
        ck_2 = view.findViewById(R.id.ck_2);
        ck_3 = view.findViewById(R.id.ck_3);
        ck_4 = view.findViewById(R.id.ck_4);
        ck_5 = view.findViewById(R.id.ck_5);
        edt_jcjg = view.findViewById(R.id.edt_jcjg);
        edt_fcqz1 = view.findViewById(R.id.edt_fcqz);
        edt_fcqz2 = view.findViewById(R.id.edt_fcqz2);
        title.setVisibility(View.GONE);
        btn_save.setVisibility(View.GONE);
        edt_czxm = view.findViewById(R.id.edt_czxm);
        tv_find_jcry = view.findViewById(R.id.tv_find_jcry);
        tv_jcry_no = view.findViewById(R.id.tv_jcry_no);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_find_jcry:
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

            case R.id.tv_jcsj:
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
            case R.id.tv_qm:
                writeLine(101, qm_img, tv_qm);
                break;
            case R.id.qm_img:
                showQMDialog(qm_path, 101);
                break;
            case R.id.tv_jcry:
                writeLine(102, jcry_qm_img, jcry_tv_qm);
                break;
            case R.id.img_jcry_qm:
                showQMDialog(jcry_qm_path, 102);
                break;
            case R.id.add_img:
                if (defectCodes != null) {
                    cljdLayout = new cljdLayout(getActivity(), "", "", defectCodes);
                    ll_layout.addView(cljdLayout);
                }
                break;
            case R.id.del_img:
                if (ll_layout.getChildCount() != 0) {
                    ll_layout.removeViewAt(ll_layout.getChildCount() - 1);
                }
                break;
            case R.id.edt_zxjc:
                showZXJCDialog(edt_zxjc);
                break;

        }
    }


    private void showInspectorInfoDialog() {
        inspectorInfoDialog.showCheckBoxDialog();

    }

    private void showZXJCDialog(final TextView edt_zxjc) {
        if (zxjcDialog == null) {
            zxjcDialog = new ZXJCDialog(getActivity());
        }
        zxjcDialog.show(edt_zxjc.getText().toString());
        zxjcDialog.setOnData(new ZXJCDialog.OnGetData() {
            @Override
            public void onDataCallBack(String type, String name) {
                edt_zxjc.setText(name);
                specialInspectType = type;
            }
        });

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
                if (result == 101) {
                    writeLine(101, qm_img, tv_qm);
                } else {
                    writeLine(102, jcry_qm_img, jcry_tv_qm);
                }
                qm_dialog.dismiss();
            }
        });
    }

    /**
     * 签名
     */
    private void writeLine(final int result, final ImageView imageView, final TextView textView) {

        SignatureView view1 = new SignatureView(getActivity(), ll_sc);
        view1.showSignatureView();
        view1.setOnBtnSureClickListener(new SignatureView.OnBtnSureClickListener() {
            @Override
            public void onSave(LinePathView linePathView) {
                if (result == 101) {
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
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    jcry_qm_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate() + "qm.png";
                    try {
                        linePathView.save(jcry_qm_path, false, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BitmapUtils.getImageThumbnail(jcry_qm_path, 80, 80);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(jcry_qm_path, options);
                    imageView.setImageBitmap(bm);
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public Dialog getDiaole() {
        return DialogUtils.createLoadingDialog(getActivity(), "加载中...");
    }

    @Override
    public void setTaskDefectItemData(List<TaskDefectItemData> taskDefectItemData) {
        isLoadData = true;
        this.taskDefectItemData = taskDefectItemData;

//        for (int i = 0; i < taskDefectItemData.size(); i++) {
//            for (int j = 0; j < level1Items.size() ; j++) {
//                if (level1Items.get(j).getItemFuseId().equals(taskDefectItemData.get(i).getItemFuseId())){
//                    cljdLayout = new cljdLayout(getActivity(),level1Items.get(j).getFscCode(),level1Items.get(j).getInput());
//                    ll_layout.addView(cljdLayout);
//                }
//            }
//        }

        presenter.getDefectCodeData();
    }

    @Override
    public void OnFaile(String msg) {
        isLoadData = true;
        ToastUtils.showToast(getActivity(), msg);
    }

    @Override
    public void setUsersListSucceed(TaskPersonResouse taskPersonResouse) {

    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {
        this.defectCodes = defectCodes;
        for (int i = 0; i < taskDefectItemData.size(); i++) {
            for (int j = 0; j < level1Items.size(); j++) {
                if (level1Items.get(j).getItemFuseId().equals(taskDefectItemData.get(i).getItemFuseId())) {
                    cljdLayout = new cljdLayout(getActivity(), level1Items.get(j).getFscCode(), level1Items.get(j).getInput(), defectCodes);
                    ll_layout.addView(cljdLayout);
                }
            }
        }
    }

    @Override
    public TaskFinishSubData getTaskFinishSubData() {
        return null;
    }

    @Override
    public void finishSucceed(TaskFinishData data, String msg) {
        LoginUtils.loginOutShowDialog(getActivity(), msg, msg);
    }

    @Override
    public void loginExpired(String msg) {

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
                    tv_find_jcry.setText(name.toString());
                    tv_jcry_no.setText(no.toString());
                } else {
                    tv_find_jcry.setText("点击查找");
                    tv_jcry_no.setText("");
                }
            }
        });
        showInspectorInfoDialog();
    }

    //获取当前Fragment页面数据的回调
    public interface GetSupervisionDataCallback {
        void getResult(jdbgData data, String fileCaptain, String fileInspector);
    }


    public void getSupervisionData(GetSupervisionDataCallback callback) {

        List<jdbgData.detail> detail = new ArrayList<>();
        jdbgData.info info = new jdbgData.info();
        info.setShipNameCn(edt_cm.getText().toString());
        info.setRegportName(edt_gj.getText().toString());
        info.setShipTypeNameCn(edt_cbzl.getText().toString());
        info.setPortCode("440266");
        if (edt_cm.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写船旗国监督检查船名！");
            callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (edt_gj.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写船旗国监督检查船籍港！");
            callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (edt_cbzl.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写船旗国监督检查船舶种类！");
            callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (!ACache.get(getActivity()).getAsString("userNum").contains("test") && !ACache.get(getActivity()).getAsString("userNum").contains("winfo")) {
            if (edt_imo.getText().toString().equals("")) {
                ToastUtils.showToast(getActivity(), "请填写船旗国监督检查IMO/船舶识别号！");
                callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
                return;
            }
            if (edt_cbbh.getText().toString().equals("")) {
                ToastUtils.showToast(getActivity(), "请填写船旗国监督检查船舶编号！");
                callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
                return;
            }
        }

        if (edt_cbglr.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写船旗国监督检查报告表船舶管理人！");
            callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (edt_czxm.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写船旗国监督检查报告表船长姓名！");
            callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (edt_jcjg.getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写船旗国监督检查报告表检查机构！");
            callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
            return;
        }
        if (!ACache.get(getActivity()).getAsString("userNum").contains("test") && !ACache.get(getActivity()).getAsString("userNum").contains("winfo")) {
            if (tv_find_jcry.getText().toString().equals("")) {
                ToastUtils.showToast(getActivity(), "请选择船旗国监督检查报告检查员！");
                callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
                return;
            }

            if (tv_jcry_no.getText().toString().equals("")) {
                ToastUtils.showToast(getActivity(), "请填写执法证号！");
                callback.getResult(null, qm_path, jcry_qm_path);//表格没有填写完整，所以传null
                return;
            }
        }

        info.setShipNo(edt_cbbh.getText().toString());
        info.setShopId(edt_imo.getText().toString());
        info.setShipManager(edt_cbglr.getText().toString());
        if (ck_zxjc.isChecked()) {
            info.setIsSpecialInspect("1");
        } else {
            info.setIsSpecialInspect("0");
        }
        info.setInitialInspectMark("0");//设置初查复查   初查0   复查1
        info.setCaptainName(edt_czxm.getText().toString());
        info.setSpecialInspectName(edt_zxjc.getText().toString());
        info.setSpecialInspectType(specialInspectType);
        List<String> strings = new ArrayList<>();
        if (ck_1.isChecked()) {
            strings.add("40");
        } else {
            strings.remove("40");
        }
        if (ck_2.isChecked()) {
            strings.add("50");
        } else {
            strings.remove("50");
        }
        if (ck_3.isChecked()) {
            strings.add("70");
        } else {
            strings.remove("70");
        }
        if (ck_4.isChecked()) {
            strings.add("152");
        } else {
            strings.remove("152");
        }
        if (ck_5.isChecked()) {
            strings.add("155");
        } else {
            strings.remove("155");
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (i < strings.size() - 1) {
                text.append(strings.get(i));
                text.append("-");
            } else {
                text.append(strings.get(i));
            }
        }
        info.setInspectAction(text.toString());
        info.setInspectOrg(edt_jcjg.getText().toString());
        info.setInspectDate(tv_jcsj.getText().toString());
        info.setInspectOrg(edt_jcjg.getText().toString());

        info.setInspectorName(tv_find_jcry.getText().toString());
        String inspectorCodeOld = tv_jcry_no.getText().toString();
        String inspectorCodeNew;
        if (inspectorCodeOld.contains("，")) {//将中文字符的逗号转换成英文字符的逗号
            inspectorCodeNew = inspectorCodeOld.replace("，", ",");
        } else {
            inspectorCodeNew = inspectorCodeOld;
        }
        info.setInspectorCode(inspectorCodeNew);
        info.setInspectorCode(tv_jcry_no.getText().toString());
        for (int i = 0; i < ll_layout.getChildCount(); i++) {
            cljdLayout cljdLayout = (cljdLayout) ll_layout.getChildAt(i);
            if (cljdLayout.getData() == null) {
                return;
            }
            detail.add(cljdLayout.getData());
        }
        jdbgData data = new jdbgData();
        data.setInfo(info);
        data.setDetail(detail);

        callback.getResult(data, qm_path, jcry_qm_path);
    }

}
