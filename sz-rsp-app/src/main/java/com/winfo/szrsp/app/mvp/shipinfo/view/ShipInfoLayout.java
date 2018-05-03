package com.winfo.szrsp.app.mvp.shipinfo.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.dnc.sdk.utils.TransformUtil;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CrewInfoAdapter;
import com.winfo.szrsp.app.adapter.SecurityCheckAdapter;
import com.winfo.szrsp.app.adapter.XianChangAdapter;
import com.winfo.szrsp.app.dialog.ExceptionShipDialog;
import com.winfo.szrsp.app.mvp.certificate.view.CertificateMainActivity;
import com.winfo.szrsp.app.mvp.exceptionship.presenter.ExceptionShipOperatePresenter;
import com.winfo.szrsp.app.mvp.exceptionship.view.IExceptionShipOperateView;
import com.winfo.szrsp.app.mvp.maplayer.presenter.ObtainPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IObtainView;
import com.winfo.szrsp.app.mvp.maplayer.view.UpdataTelDialog;
import com.winfo.szrsp.app.mvp.mine.login.view.impl.UserLoginActivity;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.CrewInfoPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.LoydsInfoPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.SecurityCheckPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipCompanyInfoPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipInfoPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipRegisterInfoPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipSelectionRiskPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipTrackPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.presenter.ShipXianChangPresenter;
import com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.AssignTaskActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipCompanyInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomationNew;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRiskInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;
import com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoShiShip;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.SZMSAUtils;
import com.winfo.szrsp.app.utils.TimeUtil;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.StartStopTimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

@SuppressLint("ViewConstructor")
public class ShipInfoLayout extends ConstraintLayout implements IShipInfoView, ICompanyInfoView, IShipRegisterInfoView, IShipTrackView, IObtainView, IExceptionShipOperateView, IShipSelectionRiskView, IShipXianChangView, ISecurityCheckView, ILoydsInfoView, ICrewInfoView, View.OnClickListener {

    private RelativeLayout rl_top;//上传证书行
//    private ArrayList<String> selectedPhotos1 = new ArrayList<>();//已选中的图片
//    private ArrayList<String> selectedPhotos2 = new ArrayList<>();//已选中的图片
    private List<StateControlData.FlagStateControlDetailBean> detailBeanList;//安检缺陷信息列表
    private List<ShipXianChangInfo.SiteSupervisionDetailBean> xcDetailBeanList;//现场监督问题列表
    private CircularProgressButton
            //AIS信息
            butSeeAisinfo,
    //基本信息
    btnSeeShipinfo,
    //二级基本信息
    btnShipinfo,
    //登记信息
    btnSeeShipRegisterInfo,
    //公司信息
    btnSeeShipCompanyInfo,
    //劳氏数据
    btnSeeShipLoydsInfo,
    //安检信息
    btnSeeShipSecurityCheckInfo,
    //现场监督
    btnSeeShipXianChangInfo,
    //船员信息
    btnSeeShipCrewInfo,
    //船检信息
    btnSeeShipInspectionInfo,
    //申报信息
    btnSeeShipDeclareInfo,
    //选船风险值
    btnSeeShipSelectionRiskInfo,
    //异常船舶
    btnSeeShipExceptionInfo,
    //行政处罚
    btnSeeShipXzcfInfo;
    private TableLayout
            //ais信息
            shipAisinfoTableLayout,
    //基本信息布局
    shipinfoTablelayout,
            shipinfoTablelayout_level,
    //登记信息布局
    shipRegisterInfoTableLayout,
    //公司信息布局
    shipCompanyInfoTableLayout,
    //劳氏数据
    shipLoydsInfoTableLayout,
    //异常船舶
    shipExceptionTableLayout,
    //选船风险值
    shipSelectionRiskTablelayout;

    //船员列表
    private RecyclerView rvCrewinfos;

    private RelativeLayout rl_crew_info_layout;

    //安检列表
    private RecyclerView rvSecurityCheck;
    private RelativeLayout rl_security_check_data_layout;
    //现场监督列表
    private RecyclerView rvXianChangLayout;
    private RelativeLayout rl_xianchang_data_layput;

    private TextView
            //Ais信息加载提示
            tvShipAisinfoMsg,
    //基本信息加载提示
    tvShipinfoMsg,
            tvShipinfoMsg_level,
    //公司信息加载提示
    tvShipCompanyinfoMsg,
    //登记信息加载提示
    tvShipRegisterInfoMsg,
    //劳氏数据信息加载提示
    tvShipLoydsInfoMsg,
    //安检信息加载提示
    tvShipSecurityCheckInfoMsg,
    //现场监督信息加载提示
    tvShipXianChangInfoMsg,
    //船员信息加载提示
    tvShipCrewInfoMsg,
    //船检信息加载提示
    tvShipInspectionInfoMsg,
    //申报信息加载提示
    tvShipDeclareInfoMsg,
    //选船风险值信息加载提示
    tvShipSelectionRiskInfoMsg,
    //异常船舶信息加载提示
    tvShipExceptionInfoMsg;
    private ShipInfoPresenter shipInfoPresenter;
    private ShipCompanyInfoPresenter shipCompanyInfoPresenter;
    private ShipRegisterInfoPresenter shipRegisterInfoPresenter;
    private LoydsInfoPresenter loydsInfoPresenter;
    private ExceptionShipOperatePresenter exceptionShipOperatePresenter;
    private CrewInfoPresenter crewInfoPresenter;
    private SecurityCheckPresenter securityCheckPresenter;
    private ShipXianChangPresenter shipXianChangPresenter;
    private ShipSelectionRiskPresenter shipSelectionRiskPresenter;
    private ShipTrackPresenter shipTrackPresenter;
    private ObtainPresenter obtainPresenter;

    /**
     * ais数据
     */
    private TextView tvAISName, tvMMSI, tvHH, tvIMO,
            tvLon, tvLat, tvCsx, tvCjx, tvHs, tvCblx,
            tvAISLx, tvCc, tvCk, tvMdd, tvDhsblx, tvCs,
            tvHxzt, tvYdsj, tvUpdateTime, tvPhone, tvPhoneName, but_updataTel;
    private ImageButton butCall, butSms;
    private RelativeLayout rl_ais_info;
    /**
     * 轨迹
     */
    private TextView tv_ship_trajectory;
    private StartStopTimePickerView startStopTimePickerView;

    private TextView tv_phpto;//上传证书
    private TextView tv_task;//任务

    private TextView tv_ship_id, tv_ship_name_cn, tv_ship_name_en, tv_mmsi, tv_ship_imo,
            tv_regport_code, tv_ship_type_code, tv_ship_length, tv_ship_grosston,
            tv_ship_netton, tv_ship_region, tv_nation_code, tv_sailingarea_code,
            tv_ship_built_date, tv_ship_wind_level, tv_ship_owner, tv_ship_operator,
            tv_rescue_equipment_num, tv_ship_card_no, tv_ship_dwt, tv_ship_value,
            tv_ship_callsign, tv_ship_breadth, tv_ship_depth, tv_ship_reg_no,
            tv_ship_firstreg_no,
    //            tv_org_code,
    tv_ship_engine_type_code, tv_ship_engine_num,
            tv_ship_engine_power, tv_ship_route_code, tv_ship_rebuilt_date, tv_shipyard_cn,
            tv_ship_built_addr_cn, tv_ship_rebuilt_addr_cn, tv_ship_hull_material_code,
            tv_ship_propeller_kind_code, tv_ship_propeller_num, tv_ship_summer_draft,
            tv_ship_inspect_no, tv_orig_ship_name_cn, tv_orig_ship_name_en, tv_orig_ship_reg_no,
            tv_orig_regport_name, tv_ship_container_num, tv_ship_parking_num,
    //        tv_ship_passenger_num,
    tv_shipyard_en, tv_ship_built_addr_en, tv_ship_rebuilt_addr_en, tv_ship_no,
            tv_ship_class, tv_ship_manager, tv_ship_speed, tv_ship_minimum_freeboard, tv_remark,
            tv_certificate_flag;
    private RelativeLayout shipInfoRelativeLayout;

    private TextView tv_ship_id_level, tv_ship_name_cn_level, tv_ship_name_en_level, tv_mmsi_level, tv_ship_imo_level,
            tv_regport_code_level, tv_ship_type_code_level, tv_ship_length_level, tv_ship_grosston_level,
            tv_ship_netton_level, tv_ship_region_type_level, tv_nation_code_level,
    //            tv_sailingarea_code_level,
    tv_ship_built_date_level, tv_ship_wind_level_level, tv_ship_owner_level, tv_ship_operator_level,
            tv_rescue_equipment_num_level, tv_ship_card_no_level, tv_ship_dwt_level, tv_ship_value_level,
            tv_ship_callsign_level, tv_ship_breadth_level, tv_ship_depth_level, tv_ship_reg_no_level,
            tv_ship_firstreg_no_level, tv_org_code_level, tv_ship_engine_type_code_level, tv_ship_engine_num_level,
            tv_ship_engine_power_level, tv_ship_route_code_level, tv_ship_rebuilt_date_level, tv_shipyard_cn_level,
            tv_ship_built_addr_cn_level, tv_ship_rebuilt_addr_cn_level, tv_ship_hull_material_code_level,
            tv_ship_propeller_kind_code_level, tv_ship_propeller_num_level, tv_ship_summer_draft_level,
            tv_ship_inspect_no_level, tv_orig_ship_name_cn_level, tv_orig_ship_name_en_level, tv_orig_ship_reg_no_level,
            tv_orig_regport_name_level, tv_ship_container_num_level, tv_ship_parking_num_level, tv_ship_passenger_num_level,
            tv_shipyard_en_level, tv_ship_built_addr_en_level, tv_ship_rebuilt_addr_en_level, tv_ship_no_level,
            tv_ship_class_level, tv_ship_manager_level, tv_ship_speed_level, tv_ship_minimum_freeboard_level, tv_remark_level,
            tv_certificate_flag_level;
    private RelativeLayout shipInfoRelativeLayout_level;

    /**
     * 公司信息
     */
    private TextView tv_dwjgdm,
            tv_zwjc, tv_zwqc,
            tv_ywjc, tv_ywqc,
            tv_gjdm, tv_ssdm,
            tv_csdm, tv_dz, tv_dzyw,
            tv_yzbm, tv_wzdz, tv_bgdh,
            tv_yjdz, tv_czhm, tv_jd,
            tv_wd, tv_bz, tv_sjdwjgdm,
            tv_cjsj, tv_cjr, tv_cjjgdm,
            tv_cjbmdm, tv_zhxgsj, tv_zhxgr;
    private RelativeLayout rl_company_info;


    /**
     * 异常船舶
     */
    private TextView tv_ship_exception_time, tv_ship_exception_dec;
    private RelativeLayout rl_ship_exception_Info;
    private LinearLayout ll_anjian_btn;

    /**
     * 行政处罚
     */
    private RelativeLayout rl_ship_xzcf_Info;
    /**
     * 船舶登记信息
     */
    private TextView
            tv_ship_register_info_cbdjh, tv_ship_register_info_cbsbh, tv_ship_register_info_ccdjh,
            tv_ship_register_info_cjdjh, tv_ship_register_info_zwcm, tv_ship_register_info_ywcm,
            tv_ship_register_info_mmsi, tv_ship_register_info_imo, tv_ship_register_info_hh,
            tv_ship_register_info_cjgdm, tv_ship_register_info_cqgdm, tv_ship_register_info_hcnhcbsdm,
            tv_ship_register_info_cbzldm, tv_ship_register_info_zdw, tv_ship_register_info_jdw,
            tv_ship_register_info_ckzzd, tv_ship_register_info_zjzgl, tv_ship_register_info_cbzc,
            tv_ship_register_info_cbxk, tv_ship_register_info_cbxs, tv_ship_register_info_hxqy,
            tv_ship_register_info_jyr, tv_ship_register_info_cyzwcm, tv_ship_register_info_pbh,
            tv_ship_register_info_hdhxdm, tv_ship_register_info_hdhqdm, tv_ship_register_info_ctcldm,
            tv_ship_register_info_csys, tv_ship_register_info_cbjz, tv_ship_register_info_jzrq,
            tv_ship_register_info_zccmc, tv_ship_register_info_zccywmc, tv_ship_register_info_zcdd,
            tv_ship_register_info_zcddywmc, tv_ship_register_info_gjcmc, tv_ship_register_info_gjcywmc,
            tv_ship_register_info_gjrq, tv_ship_register_info_gjdd, tv_ship_register_info_gjddywmc,
            tv_ship_register_info_aflgrq, tv_ship_register_info_lgyszdgd, tv_ship_register_info_zjzldm,
            tv_ship_register_info_zjsm, tv_ship_register_info_zjzzcmc, tv_ship_register_info_zjxh,
            tv_ship_register_info_tjqzldm, tv_ship_register_info_tjqsl, tv_ship_register_info_xjmzcs,
            tv_ship_register_info_mzcs, tv_ship_register_info_kzcs, tv_ship_register_info_mzpsl,
            tv_ship_register_info_kzpsl, tv_ship_register_info_hdkfdj, tv_ship_register_info_gx,
            tv_ship_register_info_edxw, tv_ship_register_info_edcw, tv_ship_register_info_ckdezj,
            tv_ship_register_info_jsdy, tv_ship_register_info_zdhs, tv_ship_register_info_cxjc,
            tv_ship_register_info_jbcl, tv_ship_register_info_jbcs, tv_ship_register_info_smhcbs,
            tv_ship_register_info_scdwz, tv_ship_register_info_zcbsl, tv_ship_register_info_jczdhcddm,
            tv_ship_register_info_dskx, tv_ship_register_info_cjjgbm, tv_ship_register_info_hsjgdm,
            tv_ship_register_info_bz, tv_ship_register_info_cjsj, tv_ship_register_info_cjr,
            tv_ship_register_info_cjjgdm, tv_ship_register_info_cjbmdm, tv_ship_register_info_zhxgsj,
            tv_ship_register_info_zhxgr, tv_ship_register_info_hhcbzdm, tv_ship_register_info_syr;
    private RelativeLayout rl_ship_register_info;

    /**
     * 劳氏数据
     */
    private TextView tv_auxiliarypower, tv_ballast, tv_boiler, tv_boilerbuild, tv_boilerdate,
            tv_boilerplace, tv_breadth, tv_buckheads, tv_builder, tv_buildplace, tv_callsign,
            tv_classnotation, tv_collectionnum, tv_datebuild, tv_deadweight, tv_deckerections, tv_decks,
            tv_depth, tv_displacement, tv_draught, tv_feedborard, tv_flag, tv_formeren, tv_formername,
            tv_gross, tv_hatch, tv_hatchsize, tv_height, tv_holds, tv_hullconnections, tv_hullmaterial,
            tv_imono, tv_lengthbp, tv_lengthoverall, tv_liftingdevices, tv_mainpower,
    //            tv_managercountry,
    tv_mmsino, tv_nameen, tv_net, tv_officialno, tv_ownerid, tv_port, tv_powerbore, tv_powerbuilder,
            tv_powerdate, tv_powerplace, tv_powertype, tv_regowner, tv_rpm, tv_shipmanager, tv_shipname,
            tv_shipoperator, tv_shiptype, tv_speed, tv_status, tv_voltage;
    private RelativeLayout rl_ship_loyds_Info;

    /**
     * 选船风险值
     */
    private TextView tv_ship_selection_risk_ship_no, tv_ship_selection_risk_ship_id,
            tv_ship_selection_risk_mmsi, tv_ship_selection_risk_imo,
            tv_ship_selection_risk_ship_callsign, tv_ship_selection_risk_ship_name_cn,
            tv_ship_selection_risk_ship_name_en, tv_ship_selection_risk_ship_region_type,
            tv_ship_selection_risk_nation_code, tv_ship_selection_risk_ship_type_value,
            tv_ship_selection_risk_ship_build_date, tv_ship_selection_risk_ship_age_value,
            tv_ship_selection_risk_risk_attribute, tv_ship_selection_risk_last_inspect_date,
            tv_ship_selection_risk_risk_attribute_spot, tv_ship_selection_risk_last_inspect_date_spot,
            tv_ship_selection_risk_priority_order, tv_ship_selection_risk_priority_order_spot,
            tv_ship_selection_risk_ship_inspect_org_performance, tv_ship_selection_risk_ship_inspect_org_value,
            tv_ship_selection_risk_company_performance, tv_ship_selection_risk_company_value,
            tv_ship_selection_risk_is_report, tv_ship_selection_risk_is_design,
            tv_ship_selection_risk_is_investigate, tv_ship_selection_risk_depect_value,
            tv_ship_selection_risk_is_tracking_ship, tv_ship_selection_risk_tracking_ship_value,
            tv_ship_selection_risk_detention_value, tv_ship_selection_risk_total_value,
            tv_ship_selection_risk_depect_average_num, tv_ship_selection_risk_detention_num,
            tv_ship_selection_risk_penalty_num, tv_ship_selection_risk_accident_num, tv_xcbzyxsx, tv_xcbzfxsx;
    RelativeLayout rl_ship_selection_risk;


    private boolean loadAisInfoResult = false;//加载Ais信息是否成功
    private boolean loadShipInfoResult = false;//加载船舶基本信息是否成功
    private boolean loadShipInfoResult_level = false;//加载船舶基本信息是否成功
    private boolean loadShipRegistrationInfoResult = false;//加载登记信息是否成功
    private boolean loadCompanyInfoResult = false;//加载公司信息是否成功
    private boolean loadExceptionShipInfoResult = false;//加载异常船舶是否成功

    private boolean loadLoydsInfoResult = false;//加载劳氏数据是否成功
    private boolean loadCrewInfoResult = false;//加载船员信息是否成功
    private boolean loadSecurityCheckDataResult = false;//加载安检信息是否成功
    private boolean loadXianChangDataResult = false;//加载现场监督信息是否成功
    private boolean loadShipSelectionRiskDataResult = false;//加载选船风险值是否成功


    private Button id_btn_exception_delete;
    private Button id_btn_exception_add;
    private Dialog dialog;
    private ExceptionShipDialog exceptionShipDialog;

    private BottomSheetBehavior behavior;
    private Context mContext;
    private WinfoDNCView winfoDNCView;
    private EditText mainSearchEdit;
    private Button btn_close_track;//关闭轨迹按钮

    public ShipInfoLayout(Context context, WinfoDNCView winfoDNCView, Button btn_close_track, EditText mainSearchEdit) {
        super(context);
        this.mContext = context;
        this.winfoDNCView = winfoDNCView;
        this.btn_close_track = btn_close_track;//关闭轨迹按钮
        this.mainSearchEdit = mainSearchEdit;//
        LayoutInflater.from(context).inflate(R.layout.ship_info_layout, this);
        initView();
        shipInfoPresenter = new ShipInfoPresenter();
        shipInfoPresenter.attachMvpView(this);

        shipCompanyInfoPresenter = new ShipCompanyInfoPresenter();
        shipCompanyInfoPresenter.attachMvpView(this);

        shipRegisterInfoPresenter = new ShipRegisterInfoPresenter();
        shipRegisterInfoPresenter.attachMvpView(this);

        loydsInfoPresenter = new LoydsInfoPresenter();
        loydsInfoPresenter.attachMvpView(this);

        crewInfoPresenter = new CrewInfoPresenter();
        crewInfoPresenter.attachMvpView(this);

        securityCheckPresenter = new SecurityCheckPresenter();
        securityCheckPresenter.attachMvpView(this);

        shipXianChangPresenter = new ShipXianChangPresenter();
        shipXianChangPresenter.attachMvpView(this);

        shipSelectionRiskPresenter = new ShipSelectionRiskPresenter();
        shipSelectionRiskPresenter.attachMvpView(this);

        exceptionShipOperatePresenter = new ExceptionShipOperatePresenter(this);

        obtainPresenter = new ObtainPresenter(this);
        shipTrackPresenter = new ShipTrackPresenter();
        shipTrackPresenter.attachMvpView(this);

        initEvent();
    }

    private void initEvent() {
        butSms.setOnClickListener(this);
        butCall.setOnClickListener(this);
        but_updataTel.setOnClickListener(this);//修改电话号码
        tv_ship_trajectory.setOnClickListener(this);//轨迹
        tv_phpto.setOnClickListener(this);
        tv_task.setOnClickListener(this);
        //Ais信息
        butSeeAisinfo.setOnClickListener(this);
        //基本信息
        btnSeeShipinfo.setOnClickListener(this);
        btnShipinfo.setOnClickListener(this);
        //公司信息
        btnSeeShipCompanyInfo.setOnClickListener(this);
        //登记信息
        btnSeeShipRegisterInfo.setOnClickListener(this);
        //劳氏数据
        btnSeeShipLoydsInfo.setOnClickListener(this);
        //安检信息
        btnSeeShipSecurityCheckInfo.setOnClickListener(this);
        //现场监督
        btnSeeShipXianChangInfo.setOnClickListener(this);
        //船员
        btnSeeShipCrewInfo.setOnClickListener(this);
        //船检
        btnSeeShipInspectionInfo.setOnClickListener(this);
        //申报
        btnSeeShipDeclareInfo.setOnClickListener(this);
        //选船风险值
        btnSeeShipSelectionRiskInfo.setOnClickListener(this);
        btnSeeShipExceptionInfo.setOnClickListener(this);
        btnSeeShipXzcfInfo.setOnClickListener(this);
        id_btn_exception_delete.setOnClickListener(this);
        id_btn_exception_add.setOnClickListener(this);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING://拖动中
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        bottomSheet.scrollTo(0, 0);
                        //当底部再次显示的时候再进行view和presenter的绑定
                        shipInfoPresenter.attachMvpView(ShipInfoLayout.this);
                        shipRegisterInfoPresenter.attachMvpView(ShipInfoLayout.this);
                        shipCompanyInfoPresenter.attachMvpView(ShipInfoLayout.this);
                        loydsInfoPresenter.attachMvpView(ShipInfoLayout.this);
                        crewInfoPresenter.attachMvpView(ShipInfoLayout.this);
                        securityCheckPresenter.attachMvpView(ShipInfoLayout.this);
                        shipXianChangPresenter.attachMvpView(ShipInfoLayout.this);
                        shipSelectionRiskPresenter.attachMvpView(ShipInfoLayout.this);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN://完全隐藏
                        //取消所有正在加载的请求
                        shipInfoPresenter.cancelRequest();
                        shipRegisterInfoPresenter.cancelRequest();
                        shipCompanyInfoPresenter.cancelRequest();
                        loydsInfoPresenter.cancelRequest();
                        crewInfoPresenter.cancelRequest();
                        securityCheckPresenter.cancelRequest();
                        shipXianChangPresenter.cancelRequest();
                        shipSelectionRiskPresenter.cancelRequest();
                        //隐藏所有布局和 重置加载按钮的动画
                        hideAllTableLayout();
                        revertAllAnimation();
                        /*
                            当用户下滑到隐藏状态时，解除所有view层和presenter的绑定
                            避免出现当数据还在加载中时，用户下滑隐藏之后，再点开其他船舶时
                            数据显示成之前请求的数据
                         */
                        shipInfoPresenter.detachMvpView();
                        shipRegisterInfoPresenter.detachMvpView();
                        shipCompanyInfoPresenter.detachMvpView();
                        loydsInfoPresenter.detachMvpView();
                        crewInfoPresenter.detachMvpView();
                        securityCheckPresenter.detachMvpView();
                        shipXianChangPresenter.detachMvpView();
                        shipSelectionRiskPresenter.detachMvpView();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        rl_ais_info.setOnClickListener(this);
        shipInfoRelativeLayout.setOnClickListener(this);
        shipInfoRelativeLayout_level.setOnClickListener(this);
        rl_ship_register_info.setOnClickListener(this);
        rl_company_info.setOnClickListener(this);
        rl_ship_exception_Info.setOnClickListener(this);
        rl_ship_xzcf_Info.setOnClickListener(this);
        rl_ship_loyds_Info.setOnClickListener(this);
        rl_crew_info_layout.setOnClickListener(this);
        rl_security_check_data_layout.setOnClickListener(this);
        rl_xianchang_data_layput.setOnClickListener(this);
        rl_ship_selection_risk.setOnClickListener(this);
    }

    NestedScrollView bottomSheets;

    private void initView() {
        detailBeanList = new ArrayList<>();
        xcDetailBeanList = new ArrayList<>();
        rl_top = findViewById(R.id.rl_top);

        dialog = DialogUtils.createLoadingDialog(mContext, "加载中...");
        //Ais信息
        butSeeAisinfo = findViewById(R.id.btn_see_aisinfo);
        //基本信息
        btnSeeShipinfo = findViewById(R.id.btn_see_shipinfo);
        //二级基本信息
        btnShipinfo = findViewById(R.id.btn_see_shipinfo_level);
        //登记信息
        btnSeeShipRegisterInfo = findViewById(R.id.btn_see_ship_register_info);
        //公司信息
        btnSeeShipCompanyInfo = findViewById(R.id.btn_see_ship_company_info);
        //劳氏数据
        btnSeeShipLoydsInfo = findViewById(R.id.btn_see_ship_loyds_info);
        //安检信息
        btnSeeShipSecurityCheckInfo = findViewById(R.id.btn_see_ship_security_check_info);
        //现场监督
        btnSeeShipXianChangInfo = findViewById(R.id.btn_see_ship_xianchang_info);
        //船员
        btnSeeShipCrewInfo = findViewById(R.id.btn_see_ship_crew_info);
        //船检
        btnSeeShipInspectionInfo = findViewById(R.id.btn_see_ship_inspection_info);
        //申报
        btnSeeShipDeclareInfo = findViewById(R.id.btn_see_ship_declare_info);
        //选船风险值
        btnSeeShipSelectionRiskInfo = findViewById(R.id.btn_see_ship_selection_risk_info);

        //异常船舶
        btnSeeShipExceptionInfo = findViewById(R.id.btn_see_ship_exception_info);
        //行政处罚
        btnSeeShipXzcfInfo = findViewById(R.id.btn_see_ship_xzcf_info);

        shipAisinfoTableLayout = findViewById(R.id.tab_ais);
        shipinfoTablelayout = findViewById(R.id.tablelayout_shipinfo);
        shipinfoTablelayout_level = findViewById(R.id.tablelayout_shipinfo_level);
        shipRegisterInfoTableLayout = findViewById(R.id.tablelayout_ship_register_info);
        shipCompanyInfoTableLayout = findViewById(R.id.tablelayout_ship_companyinfp);
        shipLoydsInfoTableLayout = findViewById(R.id.tablelayout_ship_loyds_info);
        shipSelectionRiskTablelayout = findViewById(R.id.tablelayout_ship_selection_risk);
        shipExceptionTableLayout = findViewById(R.id.tablelayout_ship_exception_info);

        //Ais信息加载提示
        tvShipAisinfoMsg = findViewById(R.id.tv_aisinfo_msg);
        //基本信息加载提示
        tvShipinfoMsg = findViewById(R.id.tv_shipinfo_msg);
        tvShipinfoMsg_level = findViewById(R.id.tv_shipinfo_msg_level);
        //公司信息加载提示
        tvShipCompanyinfoMsg = findViewById(R.id.tv_ship_companyinfo_msg);
        //登记信息加载提示
        tvShipRegisterInfoMsg = findViewById(R.id.tv_ship_register_info_msg);
        //劳氏数据信息加载提示
        tvShipLoydsInfoMsg = findViewById(R.id.tv_ship_loyds_info_msg);
        //安检信息加载提示
        tvShipSecurityCheckInfoMsg = findViewById(R.id.tv_ship_security_check_info_msg);
        //现场监督信息加载提示
        tvShipXianChangInfoMsg = findViewById(R.id.tv_ship_xianchan_info_msg);
        //船员信息加载提示
        tvShipCrewInfoMsg = findViewById(R.id.tv_ship_crew_info_msg);
        //船检信息加载提示
        tvShipInspectionInfoMsg = findViewById(R.id.tv_ship_inspection_info_msg);
        //申报信息加载提示
        tvShipDeclareInfoMsg = findViewById(R.id.tv_ship_declare_info_msg);
        //选船风险值信息加载提示
        tvShipSelectionRiskInfoMsg = findViewById(R.id.tv_ship_selection_risk_info_msg);
        tvShipExceptionInfoMsg = findViewById(R.id.tv_ship_exception_info_msg);
        bottomSheets = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheets);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        id_btn_exception_delete = findViewById(R.id.id_btn_exception_delete);
        id_btn_exception_add = findViewById(R.id.id_btn_exception_add);

        tv_ship_trajectory = findViewById(R.id.tv_ship_trajectory);//轨迹
        tv_phpto = findViewById(R.id.tv_ship_photo);
        tv_task = findViewById(R.id.tv_ship_task);


        startStopTimePickerView = new StartStopTimePickerView(mContext, StartStopTimePickerView.Type.ALL);
        startStopTimePickerView.setCyclic(true);
//        startStopTimePickerView.setGravity(Gravity.LEFT);
        startStopTimePickerView.setCancelable(true);
        long time = System.currentTimeMillis();
        startStopTimePickerView.setTime(new Date(time - 1200000), new Date());
        //ais数据
        initAISView();

        //船舶信息
        initShipInfoView();

        //公司信息
        initShipCompanyInfoView();

        //登记信息
        initShipRegisterInfoView();

        //劳氏数据
        initShipLoydsInfoView();

        //船员信息
        initCrewInfoView();

        //安检信息
        initSecurityCheckView();

        //现场监督
        initShipXianChangInfoView();
        //船检信息
        //申报信息
        //选船风险值
        initShipSelectionRiskView();

        //异常船舶
        initShipExceptionInfoView();
        //行政处罚
        initShipXzcfInfiView();
    }

    private void initShipXzcfInfiView() {
        rl_ship_xzcf_Info = findViewById(R.id.rl_ship_xzcf_Info);
    }

    private void initShipSelectionRiskView() {
        tv_ship_selection_risk_ship_no = findViewById(R.id.tv_ship_selection_risk_ship_no);
        tv_ship_selection_risk_ship_id = findViewById(R.id.tv_ship_selection_risk_ship_id);
        tv_ship_selection_risk_mmsi = findViewById(R.id.tv_ship_selection_risk_mmsi);
        tv_ship_selection_risk_imo = findViewById(R.id.tv_ship_selection_risk_imo);
        tv_ship_selection_risk_ship_callsign = findViewById(R.id.tv_ship_selection_risk_ship_callsign);
        tv_ship_selection_risk_ship_name_cn = findViewById(R.id.tv_ship_selection_risk_ship_name_cn);
        tv_ship_selection_risk_ship_name_en = findViewById(R.id.tv_ship_selection_risk_ship_name_en);
        tv_ship_selection_risk_ship_region_type = findViewById(R.id.tv_ship_selection_risk_ship_region_type);
        tv_ship_selection_risk_nation_code = findViewById(R.id.tv_ship_selection_risk_nation_code);
        tv_ship_selection_risk_ship_type_value = findViewById(R.id.tv_ship_selection_risk_ship_type_value);
        tv_ship_selection_risk_ship_build_date = findViewById(R.id.tv_ship_selection_risk_ship_build_date);
        tv_ship_selection_risk_ship_age_value = findViewById(R.id.tv_ship_selection_risk_ship_age_value);
        tv_ship_selection_risk_risk_attribute = findViewById(R.id.tv_ship_selection_risk_risk_attribute);
        tv_ship_selection_risk_last_inspect_date = findViewById(R.id.tv_ship_selection_risk_last_inspect_date);
        tv_ship_selection_risk_risk_attribute_spot = findViewById(R.id.tv_ship_selection_risk_risk_attribute_spot);
        tv_ship_selection_risk_last_inspect_date_spot = findViewById(R.id.tv_ship_selection_risk_last_inspect_date_spot);
        tv_ship_selection_risk_priority_order = findViewById(R.id.tv_ship_selection_risk_priority_order);
        tv_ship_selection_risk_priority_order_spot = findViewById(R.id.tv_ship_selection_risk_priority_order_spot);
        tv_ship_selection_risk_ship_inspect_org_performance = findViewById(R.id.tv_ship_selection_risk_ship_inspect_org_performance);
        tv_ship_selection_risk_ship_inspect_org_value = findViewById(R.id.tv_ship_selection_risk_ship_inspect_org_value);
        tv_ship_selection_risk_company_performance = findViewById(R.id.tv_ship_selection_risk_company_performance);
        tv_ship_selection_risk_company_value = findViewById(R.id.tv_ship_selection_risk_company_value);
        tv_ship_selection_risk_is_report = findViewById(R.id.tv_ship_selection_risk_is_report);
        tv_ship_selection_risk_is_design = findViewById(R.id.tv_ship_selection_risk_is_design);
        tv_ship_selection_risk_is_investigate = findViewById(R.id.tv_ship_selection_risk_is_investigate);
        tv_ship_selection_risk_depect_value = findViewById(R.id.tv_ship_selection_risk_depect_value);
        tv_ship_selection_risk_is_tracking_ship = findViewById(R.id.tv_ship_selection_risk_is_tracking_ship);
        tv_ship_selection_risk_tracking_ship_value = findViewById(R.id.tv_ship_selection_risk_tracking_ship_value);
        tv_ship_selection_risk_detention_value = findViewById(R.id.tv_ship_selection_risk_detention_value);
        tv_ship_selection_risk_total_value = findViewById(R.id.tv_ship_selection_risk_total_value);
        tv_ship_selection_risk_depect_average_num = findViewById(R.id.tv_ship_selection_risk_depect_average_num);
        tv_ship_selection_risk_detention_num = findViewById(R.id.tv_ship_selection_risk_detention_num);
        tv_ship_selection_risk_penalty_num = findViewById(R.id.tv_ship_selection_risk_penalty_num);
        tv_ship_selection_risk_accident_num = findViewById(R.id.tv_ship_selection_risk_accident_num);
        rl_ship_selection_risk = findViewById(R.id.rl_ship_selection_risk);
        tv_xcbzyxsx = findViewById(R.id.tv_xcbzyxsx);
        tv_xcbzfxsx = findViewById(R.id.tv_xcbzfxsx);
    }

    /**
     * 初始化安检view
     */
    private void initSecurityCheckView() {
        rvSecurityCheck = findViewById(R.id.rv_security_check);
        rl_security_check_data_layout = findViewById(R.id.rl_security_check_data_layout);
        rvSecurityCheck.setLayoutManager(new LinearLayoutManager(mContext));
        rvSecurityCheck.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化现场监督
     */
    private void initShipXianChangInfoView() {
        rvXianChangLayout = findViewById(R.id.rv_xianchang_check);
        rl_xianchang_data_layput = findViewById(R.id.rl_xianchang_data_layout);
        rvXianChangLayout.setLayoutManager(new LinearLayoutManager(mContext));
        rvXianChangLayout.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化船员列表的view
     */
    private void initCrewInfoView() {
        rvCrewinfos = findViewById(R.id.rv_crewinfos);
        rl_crew_info_layout = findViewById(R.id.rl_crew_info_layout);
        rvCrewinfos.setLayoutManager(new LinearLayoutManager(mContext));
        rvCrewinfos.setNestedScrollingEnabled(false);
    }

    private void initShipExceptionInfoView() {
        tv_ship_exception_time = findViewById(R.id.tv_ship_exception_time);
        tv_ship_exception_dec = findViewById(R.id.tv_ship_exception_dec);
    }


    /**
     * 初始化劳氏数据 的view
     */
    private void initShipLoydsInfoView() {
        tv_auxiliarypower = findViewById(R.id.tv_auxiliarypower);
        tv_ballast = findViewById(R.id.tv_ballast);
        tv_boiler = findViewById(R.id.tv_boiler);
        tv_boilerbuild = findViewById(R.id.tv_boilerbuild);
        tv_boilerdate = findViewById(R.id.tv_boilerdate);
        tv_boilerplace = findViewById(R.id.tv_boilerplace);
        tv_breadth = findViewById(R.id.tv_breadth);
        tv_buckheads = findViewById(R.id.tv_buckheads);
        tv_builder = findViewById(R.id.tv_builder);
        tv_buildplace = findViewById(R.id.tv_buildplace);
        tv_callsign = findViewById(R.id.tv_callsign);
        tv_classnotation = findViewById(R.id.tv_classnotation);
        tv_collectionnum = findViewById(R.id.tv_collectionnum);
        tv_datebuild = findViewById(R.id.tv_datebuild);
        tv_deadweight = findViewById(R.id.tv_deadweight);
        tv_deckerections = findViewById(R.id.tv_deckerections);
        tv_decks = findViewById(R.id.tv_decks);
        tv_depth = findViewById(R.id.tv_depth);
        tv_displacement = findViewById(R.id.tv_displacement);
        tv_draught = findViewById(R.id.tv_draught);
        tv_feedborard = findViewById(R.id.tv_feedborard);
        tv_flag = findViewById(R.id.tv_flag);
        tv_formeren = findViewById(R.id.tv_formeren);
        tv_formername = findViewById(R.id.tv_formername);
        tv_gross = findViewById(R.id.tv_gross);
        tv_hatch = findViewById(R.id.tv_hatch);
        tv_hatchsize = findViewById(R.id.tv_hatchsize);
        tv_height = findViewById(R.id.tv_height);
        tv_holds = findViewById(R.id.tv_holds);
        tv_hullconnections = findViewById(R.id.tv_hullconnections);
        tv_hullmaterial = findViewById(R.id.tv_hullmaterial);
        tv_imono = findViewById(R.id.tv_imono);
        tv_lengthbp = findViewById(R.id.tv_lengthbp);
        tv_lengthoverall = findViewById(R.id.tv_lengthoverall);
        tv_liftingdevices = findViewById(R.id.tv_liftingdevices);
        tv_mainpower = findViewById(R.id.tv_mainpower);
//        tv_managercountry = findViewById(R.id.tv_managercountry);
        tv_mmsino = findViewById(R.id.tv_mmsino);
        tv_nameen = findViewById(R.id.tv_nameen);
        tv_net = findViewById(R.id.tv_net);
        tv_officialno = findViewById(R.id.tv_officialno);
        tv_ownerid = findViewById(R.id.tv_ownerid);
        tv_port = findViewById(R.id.tv_port);
        tv_powerbore = findViewById(R.id.tv_powerbore);
        tv_powerbuilder = findViewById(R.id.tv_powerbuilder);
        tv_powerdate = findViewById(R.id.tv_powerdate);
        tv_powerplace = findViewById(R.id.tv_powerplace);
        tv_powertype = findViewById(R.id.tv_powertype);
        tv_regowner = findViewById(R.id.tv_regowner);
        tv_rpm = findViewById(R.id.tv_rpm);
        tv_shipmanager = findViewById(R.id.tv_shipmanager);
        tv_shipname = findViewById(R.id.tv_shipname);
        tv_shipoperator = findViewById(R.id.tv_shipoperator);
        tv_shiptype = findViewById(R.id.tv_shiptype);
        tv_speed = findViewById(R.id.tv_speed);
        tv_status = findViewById(R.id.tv_status);
        tv_voltage = findViewById(R.id.tv_voltage);
        rl_ship_loyds_Info = findViewById(R.id.rl_ship_loyds_Info);


    }

    /**
     * 初始化 船舶登记信息的view
     */
    private void initShipRegisterInfoView() {
        tv_ship_register_info_zwcm = findViewById(R.id.tv_ship_register_info_zwcm);
        tv_ship_register_info_cbdjh = findViewById(R.id.tv_ship_register_info_cbdjh);
        tv_ship_register_info_cbsbh = findViewById(R.id.tv_ship_register_info_cbsbh);
        tv_ship_register_info_cjdjh = findViewById(R.id.tv_ship_register_info_cjdjh);
        tv_ship_register_info_ccdjh = findViewById(R.id.tv_ship_register_info_ccdjh);
        tv_ship_register_info_ywcm = findViewById(R.id.tv_ship_register_info_ywcm);
        tv_ship_register_info_mmsi = findViewById(R.id.tv_ship_register_info_mmsi);
        tv_ship_register_info_imo = findViewById(R.id.tv_ship_register_info_imo);
        tv_ship_register_info_hh = findViewById(R.id.tv_ship_register_info_hh);
        tv_ship_register_info_cjgdm = findViewById(R.id.tv_ship_register_info_cjgdm);
        tv_ship_register_info_cqgdm = findViewById(R.id.tv_ship_register_info_cqgdm);
        tv_ship_register_info_hcnhcbsdm = findViewById(R.id.tv_ship_register_info_hcnhcbsdm);
        tv_ship_register_info_cbzldm = findViewById(R.id.tv_ship_register_info_cbzldm);
        tv_ship_register_info_zdw = findViewById(R.id.tv_ship_register_info_zdw);
        tv_ship_register_info_jdw = findViewById(R.id.tv_ship_register_info_jdw);
        tv_ship_register_info_ckzzd = findViewById(R.id.tv_ship_register_info_ckzzd);
        tv_ship_register_info_zjzgl = findViewById(R.id.tv_ship_register_info_zjzgl);
        tv_ship_register_info_cbzc = findViewById(R.id.tv_ship_register_info_cbzc);
        tv_ship_register_info_cbxk = findViewById(R.id.tv_ship_register_info_cbxk);
        tv_ship_register_info_cbxs = findViewById(R.id.tv_ship_register_info_cbxs);
        tv_ship_register_info_hxqy = findViewById(R.id.tv_ship_register_info_hxqy);
        tv_ship_register_info_jyr = findViewById(R.id.tv_ship_register_info_jyr);
        tv_ship_register_info_cyzwcm = findViewById(R.id.tv_ship_register_info_cyzwcm);
        tv_ship_register_info_pbh = findViewById(R.id.tv_ship_register_info_pbh);
        tv_ship_register_info_hdhxdm = findViewById(R.id.tv_ship_register_info_hdhxdm);
        tv_ship_register_info_hdhqdm = findViewById(R.id.tv_ship_register_info_hdhqdm);
        tv_ship_register_info_ctcldm = findViewById(R.id.tv_ship_register_info_ctcldm);
        tv_ship_register_info_csys = findViewById(R.id.tv_ship_register_info_csys);
        tv_ship_register_info_cbjz = findViewById(R.id.tv_ship_register_info_cbjz);
        tv_ship_register_info_jzrq = findViewById(R.id.tv_ship_register_info_jzrq);
        tv_ship_register_info_zccmc = findViewById(R.id.tv_ship_register_info_zccmc);
        tv_ship_register_info_zccywmc = findViewById(R.id.tv_ship_register_info_zccywmc);
        tv_ship_register_info_zcdd = findViewById(R.id.tv_ship_register_info_zcdd);
        tv_ship_register_info_zcddywmc = findViewById(R.id.tv_ship_register_info_zcddywmc);
        tv_ship_register_info_gjcmc = findViewById(R.id.tv_ship_register_info_gjcmc);
        tv_ship_register_info_gjcywmc = findViewById(R.id.tv_ship_register_info_gjcywmc);
        tv_ship_register_info_gjrq = findViewById(R.id.tv_ship_register_info_gjrq);
        tv_ship_register_info_gjdd = findViewById(R.id.tv_ship_register_info_gjdd);
        tv_ship_register_info_gjddywmc = findViewById(R.id.tv_ship_register_info_gjddywmc);
        tv_ship_register_info_aflgrq = findViewById(R.id.tv_ship_register_info_aflgrq);
        tv_ship_register_info_lgyszdgd = findViewById(R.id.tv_ship_register_info_lgyszdgd);
        tv_ship_register_info_zjzldm = findViewById(R.id.tv_ship_register_info_zjzldm);
        tv_ship_register_info_zjsm = findViewById(R.id.tv_ship_register_info_zjsm);
        tv_ship_register_info_zjzzcmc = findViewById(R.id.tv_ship_register_info_zjzzcmc);
        tv_ship_register_info_zjxh = findViewById(R.id.tv_ship_register_info_zjxh);
        tv_ship_register_info_tjqzldm = findViewById(R.id.tv_ship_register_info_tjqzldm);
        tv_ship_register_info_tjqsl = findViewById(R.id.tv_ship_register_info_tjqsl);
        tv_ship_register_info_xjmzcs = findViewById(R.id.tv_ship_register_info_xjmzcs);
        tv_ship_register_info_mzcs = findViewById(R.id.tv_ship_register_info_mzcs);
        tv_ship_register_info_kzcs = findViewById(R.id.tv_ship_register_info_kzcs);
        tv_ship_register_info_mzpsl = findViewById(R.id.tv_ship_register_info_mzpsl);
        tv_ship_register_info_kzpsl = findViewById(R.id.tv_ship_register_info_kzpsl);
        tv_ship_register_info_hdkfdj = findViewById(R.id.tv_ship_register_info_hdkfdj);
        tv_ship_register_info_gx = findViewById(R.id.tv_ship_register_info_gx);
        tv_ship_register_info_edxw = findViewById(R.id.tv_ship_register_info_edxw);
        tv_ship_register_info_edcw = findViewById(R.id.tv_ship_register_info_edcw);
        tv_ship_register_info_ckdezj = findViewById(R.id.tv_ship_register_info_ckdezj);
        tv_ship_register_info_jsdy = findViewById(R.id.tv_ship_register_info_jsdy);
        tv_ship_register_info_zdhs = findViewById(R.id.tv_ship_register_info_zdhs);
        tv_ship_register_info_cxjc = findViewById(R.id.tv_ship_register_info_cxjc);
        tv_ship_register_info_jbcl = findViewById(R.id.tv_ship_register_info_jbcl);
        tv_ship_register_info_jbcs = findViewById(R.id.tv_ship_register_info_jbcs);
        tv_ship_register_info_smhcbs = findViewById(R.id.tv_ship_register_info_smhcbs);
        tv_ship_register_info_scdwz = findViewById(R.id.tv_ship_register_info_scdwz);
        tv_ship_register_info_zcbsl = findViewById(R.id.tv_ship_register_info_zcbsl);
        tv_ship_register_info_jczdhcddm = findViewById(R.id.tv_ship_register_info_jczdhcddm);
        tv_ship_register_info_dskx = findViewById(R.id.tv_ship_register_info_dskx);
        tv_ship_register_info_cjjgbm = findViewById(R.id.tv_ship_register_info_cjjgbm);
        tv_ship_register_info_hsjgdm = findViewById(R.id.tv_ship_register_info_hsjgdm);
        tv_ship_register_info_bz = findViewById(R.id.tv_ship_register_info_bz);
        tv_ship_register_info_cjsj = findViewById(R.id.tv_ship_register_info_cjsj);
        tv_ship_register_info_cjr = findViewById(R.id.tv_ship_register_info_cjr);
        tv_ship_register_info_cjjgdm = findViewById(R.id.tv_ship_register_info_cjjgdm);
        tv_ship_register_info_cjbmdm = findViewById(R.id.tv_ship_register_info_cjbmdm);
        tv_ship_register_info_zhxgsj = findViewById(R.id.tv_ship_register_info_zhxgsj);
        tv_ship_register_info_zhxgr = findViewById(R.id.tv_ship_register_info_zhxgr);
        tv_ship_register_info_hhcbzdm = findViewById(R.id.tv_ship_register_info_hhcbzdm);
        tv_ship_register_info_syr = findViewById(R.id.tv_ship_register_info_syr);
        rl_ship_register_info = findViewById(R.id.rl_ship_register_info);


    }

    /**
     * 初始化船公司信息的view
     */
    private void initShipCompanyInfoView() {
        tv_dwjgdm = findViewById(R.id.tv_dwjgdm);
        tv_zwjc = findViewById(R.id.tv_zwjc);
        tv_zwqc = findViewById(R.id.tv_zwqc);
        tv_ywjc = findViewById(R.id.tv_ywjc);
        tv_ywqc = findViewById(R.id.tv_ywqc);
        tv_gjdm = findViewById(R.id.tv_gjdm);
        tv_ssdm = findViewById(R.id.tv_ssdm);
        tv_csdm = findViewById(R.id.tv_csdm);
        tv_dz = findViewById(R.id.tv_dz);
        tv_dzyw = findViewById(R.id.tv_dzyw);
        tv_yzbm = findViewById(R.id.tv_yzbm);
        tv_wzdz = findViewById(R.id.tv_wzdz);
        tv_bgdh = findViewById(R.id.tv_bgdh);
        tv_yjdz = findViewById(R.id.tv_yjdz);
        tv_czhm = findViewById(R.id.tv_czhm);
        tv_jd = findViewById(R.id.tv_jd);
        tv_wd = findViewById(R.id.tv_wd);
        tv_bz = findViewById(R.id.tv_bz);
        tv_sjdwjgdm = findViewById(R.id.tv_sjdwjgdm);
        tv_cjsj = findViewById(R.id.tv_cjsj);
        tv_cjr = findViewById(R.id.tv_cjr);
        tv_cjjgdm = findViewById(R.id.tv_cjjgdm);
        tv_cjbmdm = findViewById(R.id.tv_cjbmdm);
        tv_zhxgsj = findViewById(R.id.tv_zhxgsj);
        tv_zhxgr = findViewById(R.id.tv_zhxgr);
        rl_company_info = findViewById(R.id.rl_company_info);
        rl_ship_exception_Info = findViewById(R.id.rl_ship_exception_Info);
        ll_anjian_btn = findViewById(R.id.ll_anjian_btn);
    }

    /**
     * 初始化船舶信息的view
     */
    private void initShipInfoView() {
        tv_ship_id = findViewById(R.id.tv_ship_id);
        tv_ship_name_cn = findViewById(R.id.tv_ship_name_cn);
        tv_ship_name_en = findViewById(R.id.tv_ship_name_en);
        tv_mmsi = findViewById(R.id.tv_ship_mmsi);
        tv_ship_imo = findViewById(R.id.tv_ship_imo);
        tv_regport_code = findViewById(R.id.tv_regport_code);
        tv_ship_type_code = findViewById(R.id.tv_ship_type_code);
        tv_ship_length = findViewById(R.id.tv_ship_length);
        tv_ship_grosston = findViewById(R.id.tv_ship_grosston);
        tv_ship_netton = findViewById(R.id.tv_ship_netton);
        tv_ship_region = findViewById(R.id.tv_ship_region_type);
        tv_nation_code = findViewById(R.id.tv_nation_code);
        tv_sailingarea_code = findViewById(R.id.tv_sailingarea_code);
        tv_ship_built_date = findViewById(R.id.tv_ship_built_date);
        tv_ship_wind_level = findViewById(R.id.tv_ship_wind_level);
        tv_ship_owner = findViewById(R.id.tv_ship_owner);
        tv_ship_operator = findViewById(R.id.tv_ship_operator);
        tv_rescue_equipment_num = findViewById(R.id.tv_rescue_equipment_num);
        tv_ship_card_no = findViewById(R.id.tv_ship_card_no);
        tv_ship_dwt = findViewById(R.id.tv_ship_dwt);
        tv_ship_value = findViewById(R.id.tv_ship_value);
        tv_ship_callsign = findViewById(R.id.tv_ship_callsign);
        tv_ship_breadth = findViewById(R.id.tv_ship_breadth);
        tv_ship_depth = findViewById(R.id.tv_ship_depth);
        tv_ship_reg_no = findViewById(R.id.tv_ship_reg_no);
        tv_ship_firstreg_no = findViewById(R.id.tv_ship_firstreg_no);
//        tv_org_code =findViewById(R.id.tv_org_code);
        tv_ship_engine_type_code = findViewById(R.id.tv_ship_engine_type_code);
        tv_ship_engine_num = findViewById(R.id.tv_ship_engine_num);
        tv_ship_engine_power = findViewById(R.id.tv_ship_engine_power);
        tv_ship_route_code = findViewById(R.id.tv_ship_route_code);
        tv_ship_rebuilt_date = findViewById(R.id.tv_ship_rebuilt_date);
        tv_shipyard_cn = findViewById(R.id.tv_shipyard_cn);
        tv_ship_built_addr_cn = findViewById(R.id.tv_ship_built_addr_cn);
        tv_ship_rebuilt_addr_cn = findViewById(R.id.tv_ship_rebuilt_addr_cn);
        tv_ship_hull_material_code = findViewById(R.id.tv_ship_hull_material_code);
        tv_ship_propeller_kind_code = findViewById(R.id.tv_ship_propeller_kind_code);
        tv_ship_propeller_num = findViewById(R.id.tv_ship_propeller_num);
        tv_ship_summer_draft = findViewById(R.id.tv_ship_summer_draft);
        tv_ship_inspect_no = findViewById(R.id.tv_ship_inspect_no);
        tv_orig_ship_name_cn = findViewById(R.id.tv_orig_ship_name_cn);
        tv_orig_ship_name_en = findViewById(R.id.tv_orig_ship_name_en);
        tv_orig_ship_reg_no = findViewById(R.id.tv_orig_ship_reg_no);
        tv_orig_regport_name = findViewById(R.id.tv_orig_regport_name);
        tv_ship_container_num = findViewById(R.id.tv_ship_container_num);
        tv_ship_parking_num = findViewById(R.id.tv_ship_parking_num);
//        tv_ship_passenger_num =findViewById(R.id.tv_ship_passenger_num);
        tv_shipyard_en = findViewById(R.id.tv_shipyard_en);
        tv_ship_built_addr_en = findViewById(R.id.tv_ship_built_addr_en);
        tv_ship_rebuilt_addr_en = findViewById(R.id.tv_ship_rebuilt_addr_en);
        tv_ship_no = findViewById(R.id.tv_ship_no);
        tv_ship_class = findViewById(R.id.tv_ship_class);
        tv_ship_manager = findViewById(R.id.tv_ship_manager);
        tv_ship_speed = findViewById(R.id.tv_ship_speed);
        tv_ship_minimum_freeboard = findViewById(R.id.tv_ship_minimum_freeboard);
        tv_remark = findViewById(R.id.tv_remark);
        tv_certificate_flag = findViewById(R.id.tv_certificate_flag);
        shipInfoRelativeLayout = findViewById(R.id.rl_shipInfo);

        tv_ship_id_level = findViewById(R.id.tv_ship_id_level);
        tv_ship_name_cn_level = findViewById(R.id.tv_ship_name_cn_level);
        tv_ship_name_en_level = findViewById(R.id.tv_ship_name_en_level);
        tv_mmsi_level = findViewById(R.id.tv_ship_mmsi_level);
        tv_ship_imo_level = findViewById(R.id.tv_ship_imo_level);
        tv_regport_code_level = findViewById(R.id.tv_regport_code_level);
        tv_ship_type_code_level = findViewById(R.id.tv_ship_type_code_level);
        tv_ship_length_level = findViewById(R.id.tv_ship_length_level);
        tv_ship_grosston_level = findViewById(R.id.tv_ship_grosston_level);
        tv_ship_netton_level = findViewById(R.id.tv_ship_netton_level);
        tv_ship_region_type_level = findViewById(R.id.tv_ship_region_type_level);
        tv_nation_code_level = findViewById(R.id.tv_nation_code_level);
//        tv_sailingarea_code_level =findViewById(R.id.tv_sailingarea_code_level);
        tv_ship_built_date_level = findViewById(R.id.tv_ship_built_date_level);
        tv_ship_wind_level_level = findViewById(R.id.tv_ship_wind_level_l);
        tv_ship_owner_level = findViewById(R.id.tv_ship_owner_level);
        tv_ship_operator_level = findViewById(R.id.tv_ship_operator_level);
        tv_rescue_equipment_num_level = findViewById(R.id.tv_rescue_equipment_num_level);
        tv_ship_card_no_level = findViewById(R.id.tv_ship_card_no_level);
        tv_ship_dwt_level = findViewById(R.id.tv_ship_dwt_level);
        tv_ship_value_level = findViewById(R.id.tv_ship_value_level);
        tv_ship_callsign_level = findViewById(R.id.tv_ship_callsign_level);
        tv_ship_breadth_level = findViewById(R.id.tv_ship_breadth_level);
        tv_ship_depth_level = findViewById(R.id.tv_ship_depth_level);
        tv_ship_reg_no_level = findViewById(R.id.tv_ship_reg_no_level);
        tv_ship_firstreg_no_level = findViewById(R.id.tv_ship_firstreg_no_level);
        tv_org_code_level = findViewById(R.id.tv_org_code_level);
        tv_ship_engine_type_code_level = findViewById(R.id.tv_ship_engine_type_code_level);
        tv_ship_engine_num_level = findViewById(R.id.tv_ship_engine_num_level);
        tv_ship_engine_power_level = findViewById(R.id.tv_ship_engine_power_level);
        tv_ship_route_code_level = findViewById(R.id.tv_ship_route_code_level);
        tv_ship_rebuilt_date_level = findViewById(R.id.tv_ship_rebuilt_date_level);
        tv_shipyard_cn_level = findViewById(R.id.tv_shipyard_cn_level);
        tv_ship_built_addr_cn_level = findViewById(R.id.tv_ship_built_addr_cn_level);
        tv_ship_rebuilt_addr_cn_level = findViewById(R.id.tv_ship_rebuilt_addr_cn_level);
        tv_ship_hull_material_code_level = findViewById(R.id.tv_ship_hull_material_code_level);
        tv_ship_propeller_kind_code_level = findViewById(R.id.tv_ship_propeller_kind_code_level);
        tv_ship_propeller_num_level = findViewById(R.id.tv_ship_propeller_num_level);
        tv_ship_summer_draft_level = findViewById(R.id.tv_ship_summer_draft_level);
        tv_ship_inspect_no_level = findViewById(R.id.tv_ship_inspect_no_level);
        tv_orig_ship_name_cn_level = findViewById(R.id.tv_orig_ship_name_cn_level);
        tv_orig_ship_name_en_level = findViewById(R.id.tv_orig_ship_name_en_level);
        tv_orig_ship_reg_no_level = findViewById(R.id.tv_orig_ship_reg_no_level);
        tv_orig_regport_name_level = findViewById(R.id.tv_orig_regport_name_level);
        tv_ship_container_num_level = findViewById(R.id.tv_ship_container_num_level);
        tv_ship_parking_num_level = findViewById(R.id.tv_ship_parking_num_level);
        tv_ship_passenger_num_level = findViewById(R.id.tv_ship_passenger_num_level);
        tv_shipyard_en_level = findViewById(R.id.tv_shipyard_en_level);
        tv_ship_built_addr_en_level = findViewById(R.id.tv_ship_built_addr_en_level);
        tv_ship_rebuilt_addr_en_level = findViewById(R.id.tv_ship_rebuilt_addr_en_level);
        tv_ship_no_level = findViewById(R.id.tv_ship_no_level);
        tv_ship_class_level = findViewById(R.id.tv_ship_class_level);
        tv_ship_manager_level = findViewById(R.id.tv_ship_manager_level);
        tv_ship_speed_level = findViewById(R.id.tv_ship_speed_level);
        tv_ship_minimum_freeboard_level = findViewById(R.id.tv_ship_minimum_freeboard_level);
        tv_remark_level = findViewById(R.id.tv_remark_level);
        tv_certificate_flag_level = findViewById(R.id.tv_certificate_flag_level);

        shipInfoRelativeLayout_level = findViewById(R.id.rl_shipInfo_level);
    }

    /**
     * 初始化AIS信息的view
     */
    private void initAISView() {
        tvAISName = findViewById(R.id.tv_ais_name);
        tvMMSI = findViewById(R.id.tv_ais_mmsi);
        tvHH = findViewById(R.id.tv_ais_hh);
        tvIMO = findViewById(R.id.tv_ais_imo);
        tvLon = findViewById(R.id.tv_ais_lon);
        tvLat = findViewById(R.id.tv_ais_lat);
        tvCsx = findViewById(R.id.tv_ais_csx);
        tvCjx = findViewById(R.id.tv_ais_cjx);
        tvHs = findViewById(R.id.tv_ais_hs);
        tvCblx = findViewById(R.id.tv_ais_clx);
        tvAISLx = findViewById(R.id.tv_ais_aislx);
        tvCc = findViewById(R.id.tv_ais_cc);
        tvCk = findViewById(R.id.tv_ais_ck);
        tvMdd = findViewById(R.id.tv_ais_mdd);
        tvDhsblx = findViewById(R.id.tv_ais_dhsblx);
        tvCs = findViewById(R.id.tv_ais_cs);
        tvHxzt = findViewById(R.id.tv_ais_hxzt);
        tvYdsj = findViewById(R.id.tv_ais_ydsj);
        tvPhone = findViewById(R.id.tv_ais_phone);
        tvPhoneName = findViewById(R.id.tv_ais_nameTel);
        butCall = findViewById(R.id.imgPhone);
        butSms = findViewById(R.id.imgSms);
        tvUpdateTime = findViewById(R.id.tv_ais_updatetime);
        but_updataTel = findViewById(R.id.but_updataTel);
        rl_ais_info = findViewById(R.id.rl_aisInfo);
    }

    @Override
    public void onLoadShipInfoFail(String error) {
        loadShipInfoResult = false;
        loadFail(btnSeeShipinfo, shipinfoTablelayout, tvShipinfoMsg, error);
    }

    @Override
    public void onLoadShipInfoFail_level(String error) {
        loadShipInfoResult_level = false;
        loadFail(btnShipinfo, shipinfoTablelayout_level, tvShipinfoMsg_level, error);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setShipInfo(ShipInfomation shipInfomation) {
        loadShipInfoResult_level = true;//加载成功
        loadSuccess(btnShipinfo, shipinfoTablelayout_level, tvShipinfoMsg_level);

        tv_ship_id_level.setText(shipInfomation.getShipInfo().getShip_id());
        tv_ship_name_cn_level.setText(shipInfomation.getShipInfo().getShip_name_cn());
        tv_ship_name_en_level.setText(shipInfomation.getShipInfo().getShip_name_en());

        if (shipInfomation.getShipInfo().getMmsi().equals("")) {
            tv_mmsi_level.setText(aisData.getID());
        } else {
            tv_mmsi_level.setText(shipInfomation.getShipInfo().getMmsi());
        }
        if (shipInfomation.getShipInfo().getShip_imo().equals("0") || shipInfomation.getShipInfo().getShip_imo().equals("")) {
            if (aisData.getIMO().equals("0")) {
                tv_ship_imo_level.setText("");
            } else {
                tv_ship_imo_level.setText(aisData.getIMO());
            }
        } else {
            tv_ship_imo_level.setText(shipInfomation.getShipInfo().getShip_imo());
        }

        tv_regport_code_level.setText(shipInfomation.getShipInfo().getRegport_name());
        String code = shipInfomation.getShipInfo().getShip_type_code();
        switch (code) {
            case "9001":
                tv_ship_type_code_level.setText("渔船");
                break;
            case "0100":
                tv_ship_type_code_level.setText("客船类");
                break;
            case "0101":
                tv_ship_type_code_level.setText("普通客船");
                break;
            case "0102":
                tv_ship_type_code_level.setText("客货船");
                break;
            case "0103":
                tv_ship_type_code_level.setText("客渡船");
                break;
            case "0104":
                tv_ship_type_code_level.setText("车客渡船");
                break;
            case "0105":
                tv_ship_type_code_level.setText("旅游客船");
                break;
            case "0106":
                tv_ship_type_code_level.setText("高速客船");
                break;
            case "0107":
                tv_ship_type_code_level.setText("客驳船");
                break;
            case "0108":
                tv_ship_type_code_level.setText("滚装客船");
                break;
            case "0109":
                tv_ship_type_code_level.setText("客箱船");
                break;
            case "0110":
                tv_ship_type_code_level.setText("火车渡船(客)");
                break;
            case "0111":
                tv_ship_type_code_level.setText("地效翼船");
                break;
            case "0112":
                tv_ship_type_code_level.setText("高速客滚船");
                break;
            case "0200":
                tv_ship_type_code_level.setText("普通货船类");
                break;
            case "0201":
                tv_ship_type_code_level.setText("干货船");
                break;
            case "0202":
                tv_ship_type_code_level.setText("杂货船");
                break;
            case "0203":
                tv_ship_type_code_level.setText("散货船");
                break;
            case "0204":
                tv_ship_type_code_level.setText("散装水泥运输船");
                break;
            case "0205":
                tv_ship_type_code_level.setText("集装箱船");
                break;
            case "0206":
                tv_ship_type_code_level.setText("滚装船");
                break;
            case "0207":
                tv_ship_type_code_level.setText("多用途船");
                break;
            case "0208":
                tv_ship_type_code_level.setText("木材船");
                break;
            case "0209":
                tv_ship_type_code_level.setText("水产品运输船");
                break;
            case "0210":
                tv_ship_type_code_level.setText("重大件运输船");
                break;
            case "0211":
                tv_ship_type_code_level.setText("驳船");
                break;
            case "0212":
                tv_ship_type_code_level.setText("汽车渡船");
                break;
            case "0213":
                tv_ship_type_code_level.setText("挂浆机船");
                break;
            case "0214":
                tv_ship_type_code_level.setText("冷藏船");
                break;
            case "0215":
                tv_ship_type_code_level.setText("火车渡船");
                break;
            case "0216":
                tv_ship_type_code_level.setText("矿/散/油船");
                break;
            case "0217":
                tv_ship_type_code_level.setText("半潜船");
                break;
            case "0300":
                tv_ship_type_code_level.setText("液货船类");
                break;
            case "0301":
                tv_ship_type_code_level.setText("油船");
                break;
            case "0302":
                tv_ship_type_code_level.setText("散装化学品船");
                break;
            case "0303":
                tv_ship_type_code_level.setText("散装化学品船/油船");
                break;
            case "0304":
                tv_ship_type_code_level.setText("液化气船");
                break;
            case "0305":
                tv_ship_type_code_level.setText("散装沥青船");
                break;
            case "0306":
                tv_ship_type_code_level.setText("油驳");
                break;
            case "0307":
                tv_ship_type_code_level.setText("一般液货船");
                break;
            case "0400":
                tv_ship_type_code_level.setText("工程船类");
                break;
            case "0401":
                tv_ship_type_code_level.setText("工程船");
                break;
            case "0402":
                tv_ship_type_code_level.setText("测量船");
                break;
            case "0403":
                tv_ship_type_code_level.setText("彩砂船");
                break;
            case "0404":
                tv_ship_type_code_level.setText("挖泥船");
                break;
            case "0405":
                tv_ship_type_code_level.setText("疏浚船");
                break;
            case "0406":
                tv_ship_type_code_level.setText("打捞船");
                break;
            case "0407":
                tv_ship_type_code_level.setText("打桩船");
                break;
            case "0408":
                tv_ship_type_code_level.setText("起重船");
                break;
            case "0409":
                tv_ship_type_code_level.setText("搅拌船");
                break;
            case "0410":
                tv_ship_type_code_level.setText("布缆船");
                break;
            case "0411":
                tv_ship_type_code_level.setText("钻井船");
                break;
            case "0412":
                tv_ship_type_code_level.setText("打桩起重船");
                break;
            case "0413":
                tv_ship_type_code_level.setText("吹泥船");
                break;
            case "0414":
                tv_ship_type_code_level.setText("起重驳");
                break;
            case "0500":
                tv_ship_type_code_level.setText("工作船类");
                break;
            case "0501":
                tv_ship_type_code_level.setText("工作船");
                break;
            case "0502":
                tv_ship_type_code_level.setText("破冰船");
                break;
            case "0503":
                tv_ship_type_code_level.setText("航标船");
                break;
            case "0504":
                tv_ship_type_code_level.setText("油污水处理船");
                break;
            case "0505":
                tv_ship_type_code_level.setText("供给船");
                break;
            case "0506":
                tv_ship_type_code_level.setText("垃圾处理船");
                break;
            case "0600":
                tv_ship_type_code_level.setText("拖船类");
                break;
            case "0601":
                tv_ship_type_code_level.setText("拖船");
                break;
            case "0602":
                tv_ship_type_code_level.setText("推轮");
                break;
            case "0900":
                tv_ship_type_code_level.setText("其它类");
                break;
            case "0901":
                tv_ship_type_code_level.setText("交通艇");
                break;
            case "0902":
                tv_ship_type_code_level.setText("引航船");
                break;
            case "0903":
                tv_ship_type_code_level.setText("救助船");
                break;
            case "0904":
                tv_ship_type_code_level.setText("浮船坞");
                break;
            case "0905":
                tv_ship_type_code_level.setText("公务船");
                break;
            case "0906":
                tv_ship_type_code_level.setText("摩托艇");
                break;
            case "0907":
                tv_ship_type_code_level.setText("帆船");
                break;
            case "0908":
                tv_ship_type_code_level.setText("趸船");
                break;
            case "0909":
                tv_ship_type_code_level.setText("游艇");
                break;
            case "0910":
                tv_ship_type_code_level.setText("特种用途船");
                break;
            case "0911":
                tv_ship_type_code_level.setText("水上平台");
                break;
            case "0912":
                tv_ship_type_code_level.setText("水下观光船");
                break;
            default:
                tv_ship_type_code_level.setText("其它船");
                break;
        }
        tv_ship_length_level.setText(shipInfomation.getShipInfo().getShip_length() + "");
        tv_ship_grosston_level.setText(shipInfomation.getShipInfo().getShip_grosston() + "");
        tv_ship_netton_level.setText(shipInfomation.getShipInfo().getShip_netton());
        tv_ship_region_type_level.setText(shipInfomation.getShipInfo().getShip_region_type());
        tv_nation_code_level.setText(SZMSAUtils.getCountryByCode(shipInfomation.getShipInfo().getNation_code()));
//        tv_sailingarea_code.setText(shipInfomation.getShipInfo().g());
        tv_ship_built_date_level.setText(shipInfomation.getShipInfo().getShip_built_date());
        tv_ship_wind_level_level.setText(shipInfomation.getShipInfo().getShip_wind_level());
        tv_ship_owner_level.setText(shipInfomation.getShipInfo().getShip_owner());
        tv_ship_operator_level.setText(shipInfomation.getShipInfo().getShip_operator());
        tv_rescue_equipment_num_level.setText(shipInfomation.getShipInfo().getRescue_equipment_num() + "");
        tv_ship_card_no_level.setText(shipInfomation.getShipInfo().getShip_card_no());
        tv_ship_dwt_level.setText(shipInfomation.getShipInfo().getShip_dwt() + "");
        tv_ship_value_level.setText(shipInfomation.getShipInfo().getShip_value());
        tv_ship_callsign_level.setText(shipInfomation.getShipInfo().getShip_callsign());
        tv_ship_breadth_level.setText(shipInfomation.getShipInfo().getShip_breadth() + "");
        tv_ship_depth_level.setText(shipInfomation.getShipInfo().getShip_depth() + "");
        tv_ship_reg_no_level.setText(shipInfomation.getShipInfo().getShip_reg_no());
        tv_ship_firstreg_no_level.setText(shipInfomation.getShipInfo().getShip_firstreg_no());
        tv_org_code_level.setText(shipInfomation.getShipInfo().getOrg_code());
        tv_ship_engine_type_code_level.setText(shipInfomation.getShipInfo().getShip_engine_type_code());
        tv_ship_engine_num_level.setText(shipInfomation.getShipInfo().getShip_engine_num() + "");
        tv_ship_engine_power_level.setText(shipInfomation.getShipInfo().getShip_engine_power() + "");
        tv_ship_route_code_level.setText(shipInfomation.getShipInfo().getShip_route_code());
        tv_ship_rebuilt_date_level.setText(shipInfomation.getShipInfo().getShip_rebuilt_date());
        tv_shipyard_cn_level.setText(shipInfomation.getShipInfo().getShipyard_cn());
        tv_ship_built_addr_cn_level.setText(shipInfomation.getShipInfo().getShip_built_addr_cn());
        tv_ship_rebuilt_addr_cn_level.setText(shipInfomation.getShipInfo().getShip_rebuilt_addr_cn());
        tv_ship_hull_material_code_level.setText(shipInfomation.getShipInfo().getShip_hull_material_code());
        tv_ship_propeller_kind_code_level.setText(shipInfomation.getShipInfo().getShip_propeller_kind_code());
        tv_ship_propeller_num_level.setText(shipInfomation.getShipInfo().getShip_propeller_num() + "");
        tv_ship_summer_draft_level.setText(shipInfomation.getShipInfo().getShip_summer_draft() + "");
        tv_ship_inspect_no_level.setText(shipInfomation.getShipInfo().getShip_inspect_no());
        tv_orig_ship_name_cn_level.setText(shipInfomation.getShipInfo().getOrig_ship_name_cn());
        tv_orig_ship_name_en_level.setText(shipInfomation.getShipInfo().getOrig_ship_name_en());
        tv_orig_ship_reg_no_level.setText(shipInfomation.getShipInfo().getOrig_ship_reg_no());
        tv_orig_regport_name_level.setText(shipInfomation.getShipInfo().getOrig_regport_name());
        tv_ship_container_num_level.setText(shipInfomation.getShipInfo().getShip_container_num() + "");
        tv_ship_parking_num_level.setText(shipInfomation.getShipInfo().getShip_parking_num() + "");
        tv_ship_passenger_num_level.setText(shipInfomation.getShipInfo().getShip_passenger_num() + "");
        tv_shipyard_en_level.setText(shipInfomation.getShipInfo().getShipyard_en());
        tv_ship_built_addr_en_level.setText(shipInfomation.getShipInfo().getShip_built_addr_en());
        tv_ship_rebuilt_addr_en_level.setText(shipInfomation.getShipInfo().getShip_rebuilt_addr_en());
        tv_ship_no_level.setText(shipInfomation.getShipInfo().getShip_no());
        tv_ship_class_level.setText(shipInfomation.getShipInfo().getShip_class());
        tv_ship_manager_level.setText(shipInfomation.getShipInfo().getShip_manager());
        tv_ship_speed_level.setText(shipInfomation.getShipInfo().getShip_speed() + "");
        tv_ship_minimum_freeboard_level.setText(shipInfomation.getShipInfo().getShip_minimum_freeboard() + "");
        tv_remark_level.setText(shipInfomation.getShipInfo().getRemark());
        tv_certificate_flag_level.setText(shipInfomation.getShipInfo().getCertificate_flag());
    }

    @Override
    public void setShipInfoNew(List<ShipInfomationNew> shipInfomationNews) {
        loadShipInfoResult = true;//加载成功
        loadSuccess(btnSeeShipinfo, shipinfoTablelayout, tvShipinfoMsg);

        tv_ship_id.setText(shipInfomationNews.get(0).getShipId());
        tv_ship_name_cn.setText(shipInfomationNews.get(0).getShipNameCn());
        tv_ship_name_en.setText(shipInfomationNews.get(0).getShipNameEn());

        if (shipInfomationNews.get(0).getMmsi().equals("")) {
            tv_mmsi.setText(aisData.getID());
        } else {
            tv_mmsi.setText(shipInfomationNews.get(0).getMmsi());
        }
        if (shipInfomationNews.get(0).getShipImo().equals("0") || shipInfomationNews.get(0).getShipImo().equals("")) {
            if (aisData.getIMO().equals("0")) {
                tv_ship_imo.setText("");
            } else {
                tv_ship_imo.setText(aisData.getIMO());
            }
        } else {
            tv_ship_imo.setText(shipInfomationNews.get(0).getShipImo());
        }

        tv_regport_code.setText(shipInfomationNews.get(0).getRegportName());
        tv_ship_type_code.setText(shipInfomationNews.get(0).getShipTypeName());
        tv_ship_length.setText(shipInfomationNews.get(0).getShipLength());
        tv_ship_grosston.setText(shipInfomationNews.get(0).getShipGrosston());
        tv_ship_netton.setText(shipInfomationNews.get(0).getShipNetton());
        tv_ship_region.setText(shipInfomationNews.get(0).getShipRegionName());
        tv_nation_code.setText(shipInfomationNews.get(0).getNationName());
        tv_ship_built_date.setText(shipInfomationNews.get(0).getShipBuiltDate());
        tv_ship_wind_level.setText(shipInfomationNews.get(0).getShipWindLevel());
        tv_ship_owner.setText(shipInfomationNews.get(0).getShipOwner());
        tv_ship_operator.setText(shipInfomationNews.get(0).getShipOperator());
        tv_rescue_equipment_num.setText(shipInfomationNews.get(0).getRescueEquipmentNum());
        tv_ship_card_no.setText(shipInfomationNews.get(0).getShipCardNo());
        tv_ship_dwt.setText(shipInfomationNews.get(0).getShipDwt());
        tv_ship_value.setText(shipInfomationNews.get(0).getShipValue());
        tv_ship_callsign.setText(shipInfomationNews.get(0).getShipCallsign());
        tv_ship_breadth.setText(shipInfomationNews.get(0).getShipBreadth());
        tv_ship_depth.setText(shipInfomationNews.get(0).getShipDepth());
        tv_ship_reg_no.setText(shipInfomationNews.get(0).getShipRegNo());
        tv_ship_firstreg_no.setText(shipInfomationNews.get(0).getShipFirstregNo());
        tv_sailingarea_code.setText(shipInfomationNews.get(0).getSailingareaName());
        tv_ship_engine_type_code.setText(shipInfomationNews.get(0).getShipEngineTypeName());
        tv_ship_engine_num.setText(shipInfomationNews.get(0).getShipEngineNum());
        tv_ship_engine_power.setText(shipInfomationNews.get(0).getShipEnginePower());
        tv_ship_route_code.setText(shipInfomationNews.get(0).getShipRouteCode());
        tv_ship_rebuilt_date.setText(shipInfomationNews.get(0).getShipRebuiltDate());
        tv_shipyard_cn.setText(shipInfomationNews.get(0).getShipyardEn());
        tv_ship_built_addr_cn.setText(shipInfomationNews.get(0).getShipBuiltAddrCn());
        tv_ship_rebuilt_addr_cn.setText(shipInfomationNews.get(0).getShipRebuiltAddrCn());
        tv_ship_hull_material_code.setText(shipInfomationNews.get(0).getShipHullMaterialName());
        tv_ship_propeller_kind_code.setText(shipInfomationNews.get(0).getShipPropellerKindCode());
        tv_ship_propeller_num.setText(shipInfomationNews.get(0).getShipPropellerNum());
        tv_ship_summer_draft.setText(shipInfomationNews.get(0).getShipSummerDraft());
        tv_ship_inspect_no.setText(shipInfomationNews.get(0).getShipInspectNo());
        tv_orig_ship_name_cn.setText(shipInfomationNews.get(0).getOrigShipNameCn());
        tv_orig_ship_name_en.setText(shipInfomationNews.get(0).getOrigShipNameEn());
        tv_orig_ship_reg_no.setText(shipInfomationNews.get(0).getOrigShipRegNo());
        tv_orig_regport_name.setText(shipInfomationNews.get(0).getOrigRegportName());
        tv_ship_container_num.setText(shipInfomationNews.get(0).getShipParkingNum());
        tv_ship_parking_num.setText(shipInfomationNews.get(0).getShipParkingNum());
//        tv_ship_passenger_num.setText(shipInfomationNews.get(0).getShipPassenger_num() + "");
        tv_shipyard_en.setText(shipInfomationNews.get(0).getShipyardEn());
        tv_ship_built_addr_en.setText(shipInfomationNews.get(0).getShipBuiltAddrEn());
        tv_ship_rebuilt_addr_en.setText(shipInfomationNews.get(0).getShipRebuiltAddrEn());
        tv_ship_no.setText(shipInfomationNews.get(0).getShipNo());
        tv_ship_class.setText(shipInfomationNews.get(0).getShipClass());
        tv_ship_manager.setText(shipInfomationNews.get(0).getShipManager());
        tv_ship_speed.setText(shipInfomationNews.get(0).getShipSpeed());
        tv_ship_minimum_freeboard.setText(shipInfomationNews.get(0).getShipMinimumFreeboard());
        tv_remark.setText(shipInfomationNews.get(0).getRemark());
        tv_certificate_flag.setText(shipInfomationNews.get(0).getCertificateFlag());
    }

    @Override
    public void onLoadCompanyInfoFail(String error) {
        loadCompanyInfoResult = false;
        loadFail(btnSeeShipCompanyInfo, shipCompanyInfoTableLayout, tvShipCompanyinfoMsg, error);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setCompanyInfo(ShipCompanyInfo shipCompanyInfo) {
        loadCompanyInfoResult = true;
        loadSuccess(btnSeeShipCompanyInfo, shipCompanyInfoTableLayout, tvShipCompanyinfoMsg);
        tv_dwjgdm.setText(shipCompanyInfo.getDwjgdm());
        tv_zwjc.setText(shipCompanyInfo.getZwjc());
        tv_zwqc.setText(shipCompanyInfo.getZwqc());
        tv_ywjc.setText(shipCompanyInfo.getYwjc());
        tv_ywqc.setText(shipCompanyInfo.getYwqc());
        tv_gjdm.setText(shipCompanyInfo.getGjdm());
        tv_ssdm.setText(shipCompanyInfo.getSsdm());
        tv_csdm.setText(shipCompanyInfo.getCsdm());
        tv_dz.setText(shipCompanyInfo.getDz());
        tv_dzyw.setText(shipCompanyInfo.getDzyw());
        tv_yzbm.setText(shipCompanyInfo.getYzbm());
        tv_wzdz.setText(shipCompanyInfo.getWzdz());
        tv_bgdh.setText(shipCompanyInfo.getBgdh());
        tv_yjdz.setText(shipCompanyInfo.getYjdz());
        tv_czhm.setText(shipCompanyInfo.getCzhm());
        tv_jd.setText(shipCompanyInfo.getJd() + "");
        tv_wd.setText(shipCompanyInfo.getWd() + "");
        tv_bz.setText(shipCompanyInfo.getBz());
        tv_sjdwjgdm.setText(shipCompanyInfo.getSjdwjgdm());
        tv_cjsj.setText(shipCompanyInfo.getCjsj());
        tv_cjr.setText(shipCompanyInfo.getCjr());
        tv_cjjgdm.setText(shipCompanyInfo.getCjjgdm());
        tv_cjbmdm.setText(shipCompanyInfo.getCjbmdm());
        tv_zhxgsj.setText(shipCompanyInfo.getZhxgsj());
        tv_zhxgr.setText(shipCompanyInfo.getZhxgr());
    }

    @Override
    public void onLoadShipRegisterInfoFail(String msg) {
        loadShipRegistrationInfoResult = false;//加载登记信息失败
        loadFail(btnSeeShipRegisterInfo, shipRegisterInfoTableLayout, tvShipRegisterInfoMsg, msg);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setShipRegisterInfo(ShipRegistrationInfo shipRegisterInfo) {
        loadShipRegistrationInfoResult = true;//加载登记信息成功
        loadSuccess(btnSeeShipRegisterInfo, shipRegisterInfoTableLayout, tvShipRegisterInfoMsg);
        tv_ship_register_info_zwcm.setText(shipRegisterInfo.getZwcm());
        tv_ship_register_info_cbdjh.setText(shipRegisterInfo.getCbdjh());
        tv_ship_register_info_cbsbh.setText(shipRegisterInfo.getCbsbh());
        tv_ship_register_info_cjdjh.setText(shipRegisterInfo.getCjdjh());
        tv_ship_register_info_ccdjh.setText(shipRegisterInfo.getCcdjh());
        tv_ship_register_info_ywcm.setText(shipRegisterInfo.getYwcm());
        tv_ship_register_info_mmsi.setText(shipRegisterInfo.getMmsi());
        tv_ship_register_info_imo.setText(shipRegisterInfo.getImo());
        tv_ship_register_info_hh.setText(shipRegisterInfo.getHh());
        tv_ship_register_info_cjgdm.setText(shipRegisterInfo.getCjgdm());
        tv_ship_register_info_cqgdm.setText(shipRegisterInfo.getCqgdm());
        tv_ship_register_info_hcnhcbsdm.setText(shipRegisterInfo.getHcnhcbsdm());
        tv_ship_register_info_cbzldm.setText(shipRegisterInfo.getCbzldm());
        tv_ship_register_info_zdw.setText(shipRegisterInfo.getZdw() + "");
        tv_ship_register_info_jdw.setText(shipRegisterInfo.getJdw() + "");
        tv_ship_register_info_ckzzd.setText(shipRegisterInfo.getCkzzd() + "");
        tv_ship_register_info_zjzgl.setText(shipRegisterInfo.getZjzgl() + "");
        tv_ship_register_info_cbzc.setText(shipRegisterInfo.getCbzc() + "");
        tv_ship_register_info_cbxk.setText(shipRegisterInfo.getCbxk() + "");
        tv_ship_register_info_cbxs.setText(shipRegisterInfo.getCbxs() + "");
        tv_ship_register_info_hxqy.setText(shipRegisterInfo.getHxqy());
        tv_ship_register_info_jyr.setText(shipRegisterInfo.getJyr());
        tv_ship_register_info_cyzwcm.setText(shipRegisterInfo.getCyzwcm());
        tv_ship_register_info_pbh.setText(shipRegisterInfo.getPbh());
        tv_ship_register_info_hdhxdm.setText(shipRegisterInfo.getHdhxdm());
        tv_ship_register_info_hdhqdm.setText(shipRegisterInfo.getHdhqdm());
        tv_ship_register_info_ctcldm.setText(shipRegisterInfo.getCtcldm());
        tv_ship_register_info_csys.setText(shipRegisterInfo.getCsys());
        tv_ship_register_info_cbjz.setText(shipRegisterInfo.getCbjz());
        tv_ship_register_info_jzrq.setText(shipRegisterInfo.getJzrq());
        tv_ship_register_info_zccmc.setText(shipRegisterInfo.getZccmc());
        tv_ship_register_info_zccywmc.setText(shipRegisterInfo.getZccywmc());
        tv_ship_register_info_zcdd.setText(shipRegisterInfo.getZcdd());
        tv_ship_register_info_zcddywmc.setText(shipRegisterInfo.getZcddywmc());
        tv_ship_register_info_gjcmc.setText(shipRegisterInfo.getGjcmc());
        tv_ship_register_info_gjcywmc.setText(shipRegisterInfo.getGjcywmc());
        tv_ship_register_info_gjrq.setText(shipRegisterInfo.getGjrq());
        tv_ship_register_info_gjdd.setText(shipRegisterInfo.getGjdd());
        tv_ship_register_info_gjddywmc.setText(shipRegisterInfo.getGjddywmc());
        tv_ship_register_info_aflgrq.setText(shipRegisterInfo.getAflgrq());
        tv_ship_register_info_lgyszdgd.setText(shipRegisterInfo.getLgyszdgd() + "");
        tv_ship_register_info_zjzldm.setText(shipRegisterInfo.getZjzldm());
        tv_ship_register_info_zjsm.setText(shipRegisterInfo.getZjsm() + "");
        tv_ship_register_info_zjzzcmc.setText(shipRegisterInfo.getZjzzcmc());
        tv_ship_register_info_zjxh.setText(shipRegisterInfo.getZjxh());
        tv_ship_register_info_tjqzldm.setText(shipRegisterInfo.getTjqzldm());
        tv_ship_register_info_tjqsl.setText(shipRegisterInfo.getTjqsl());
        tv_ship_register_info_xjmzcs.setText(shipRegisterInfo.getXjmzcs());
        tv_ship_register_info_mzcs.setText(shipRegisterInfo.getMzcs() + "");
        tv_ship_register_info_kzcs.setText(shipRegisterInfo.getKzcs() + "");
        tv_ship_register_info_mzpsl.setText(shipRegisterInfo.getMzpsl() + "");
        tv_ship_register_info_kzpsl.setText(shipRegisterInfo.getKzpsl() + "");
        tv_ship_register_info_hdkfdj.setText(shipRegisterInfo.getHdkfdj());
        tv_ship_register_info_gx.setText(shipRegisterInfo.getGx());
        tv_ship_register_info_edxw.setText(shipRegisterInfo.getEdxw() + "");
        tv_ship_register_info_edcw.setText(shipRegisterInfo.getEdcw() + "");
        tv_ship_register_info_ckdezj.setText(shipRegisterInfo.getCkdezj() + "");
        tv_ship_register_info_jsdy.setText(shipRegisterInfo.getJsdy() + "");
        tv_ship_register_info_zdhs.setText(shipRegisterInfo.getZdhs());
        tv_ship_register_info_cxjc.setText(shipRegisterInfo.getCxjc());
        tv_ship_register_info_jbcl.setText(shipRegisterInfo.getJbcl());
        tv_ship_register_info_jbcs.setText(shipRegisterInfo.getJbcs() + "");
        tv_ship_register_info_smhcbs.setText(shipRegisterInfo.getSmhcbs());
        tv_ship_register_info_scdwz.setText(shipRegisterInfo.getScdwz());
        tv_ship_register_info_zcbsl.setText(shipRegisterInfo.getZcbsl() + "");
        tv_ship_register_info_jczdhcddm.setText(shipRegisterInfo.getJczdhcddm());
        tv_ship_register_info_dskx.setText(shipRegisterInfo.getDskx());
        tv_ship_register_info_cjjgbm.setText(shipRegisterInfo.getCjjgbm());
        tv_ship_register_info_hsjgdm.setText(shipRegisterInfo.getHsjgdm());
        tv_ship_register_info_bz.setText(shipRegisterInfo.getBz());
        tv_ship_register_info_cjsj.setText(shipRegisterInfo.getCjsj());
        tv_ship_register_info_cjr.setText(shipRegisterInfo.getCjr());
        tv_ship_register_info_cjjgdm.setText(shipRegisterInfo.getCjjgdm());
        tv_ship_register_info_cjbmdm.setText(shipRegisterInfo.getCjbmdm());
        tv_ship_register_info_zhxgsj.setText(shipRegisterInfo.getZhxgsj());
        tv_ship_register_info_zhxgr.setText(shipRegisterInfo.getZhxgr());
        tv_ship_register_info_hhcbzdm.setText(shipRegisterInfo.getHhcbzdm());
        tv_ship_register_info_syr.setText(shipRegisterInfo.getSyr());

    }

    @Override
    public void addFaild(String msg) {
        ToastUtils.showToast(mContext, msg);
        shipExceptionTableLayout.setVisibility(View.GONE);
        id_btn_exception_delete.setVisibility(View.GONE);
        id_btn_exception_add.setVisibility(View.VISIBLE);
        ToastUtils.showToast(mContext, msg);
        loadExceptionShipInfoResult = false;
    }

    @Override
    public void addSucceed(String msg) {
        loadExceptionShipInfoResult = true;
        ToastUtils.showToast(mContext, msg);
        id_btn_exception_delete.setVisibility(View.VISIBLE);
        id_btn_exception_add.setVisibility(View.GONE);
        exceptionShipDialog.dismiss();
        shipExceptionTableLayout.setVisibility(View.VISIBLE);
        tv_ship_exception_dec.setText(exceptionShipDialog.getDec());
        tv_ship_exception_time.setText(TimeUtil.time2FormatYMD(System.currentTimeMillis()));
    }

    @Override
    public void deleteFaild(String msg) {
        loadExceptionShipInfoResult = true;
        ToastUtils.showToast(mContext, msg);
    }

    @Override
    public void deleteSucceed(String msg) {
        loadExceptionShipInfoResult = false;
        ToastUtils.showToast(mContext, msg);
        id_btn_exception_delete.setVisibility(View.GONE);
        id_btn_exception_add.setVisibility(View.VISIBLE);
        shipExceptionTableLayout.setVisibility(View.GONE);
        tv_ship_exception_time.setText("");
        tv_ship_exception_dec.setText("");
    }

    @Override
    public Dialog getDialog() {
        if (dialog == null) {
            dialog = DialogUtils.createLoadingDialog(mContext, "加载中...");
        }
        return dialog;
    }

    @Override
    public void queryFaild(String msg) {
        loadExceptionShipInfoResult = false;
        loadFail(btnSeeShipExceptionInfo, shipExceptionTableLayout, tvShipExceptionInfoMsg, msg);
    }

    @Override
    public void setIsOrNotExceptionShip(ExceptionShip exceptionShip) {
        loadExceptionShipInfoResult = true;
        loadSuccess(btnSeeShipExceptionInfo, shipExceptionTableLayout, tvShipExceptionInfoMsg);
        if (exceptionShip == null) {
            //不是异常船舶 可以添加为异常船舶
            id_btn_exception_delete.setVisibility(View.GONE);
            id_btn_exception_add.setVisibility(View.VISIBLE);
            shipExceptionTableLayout.setVisibility(View.GONE);
            shipExceptionTableLayout.setVisibility(View.GONE);
            loadExceptionShipInfoResult = false;
        } else {
            id_btn_exception_delete.setVisibility(View.VISIBLE);
            id_btn_exception_add.setVisibility(View.GONE);
            shipExceptionTableLayout.setVisibility(View.VISIBLE);
            tv_ship_exception_time.setText(exceptionShip.getCreateDate().substring(0, 10));
            tv_ship_exception_dec.setText(exceptionShip.getExceptionRemark());
        }
    }

    @Override
    public void setAisData(Ais aisData) {

    }

    @Override
    public void searchAsiFaild(String msg) {

    }

    @Override
    public void showMsg(String msg) {
        dialog.dismiss();
    }

    @Override
    public void setXianChangInfo(ShipXianChangInfo shipXianChangInfos, String mmsi, String cm) {
        loadXianChangDataResult = true;
        rvXianChangLayout.setVisibility(View.VISIBLE);
        loadSuccess(btnSeeShipXianChangInfo, null, tvShipXianChangInfoMsg);
        xcDetailBeanList.clear();
        for (int i = 0; i < shipXianChangInfos.getSiteSupervisionReportList().size(); i++) {
            for (int j = 0; j < shipXianChangInfos.getSiteSupervisionDetail().size(); j++) {
                if (shipXianChangInfos.getSiteSupervisionReportList().get(i).getInspect_no().equals(shipXianChangInfos.getSiteSupervisionDetail().get(j).getInspect_no())) {
                    xcDetailBeanList.add(shipXianChangInfos.getSiteSupervisionDetail().get(j));
                }
            }
        }
        XianChangAdapter adapter = new XianChangAdapter(mContext, shipXianChangInfos, xcDetailBeanList,mmsi,cm);
        rvXianChangLayout.setAdapter(adapter);

    }

    @Override
    public void onLoadXianChangFail(String msg) {
        loadXianChangDataResult = false;
        rvXianChangLayout.setVisibility(View.GONE);
        loadFail(btnSeeShipXianChangInfo, null, tvShipXianChangInfoMsg, msg);
    }

    @Override
    public void setSecurityCheckInfo(StateControlData stateControlData, String mmsi, String ywcm) {
        loadSecurityCheckDataResult = true;
        rvSecurityCheck.setVisibility(View.VISIBLE);
        loadSuccess(btnSeeShipSecurityCheckInfo, null, tvShipSecurityCheckInfoMsg);
        detailBeanList.clear();
        for (int j = 0; j < stateControlData.getFlagStateControlList().size(); j++) {
            for (int k = 0; k < stateControlData.getFlagStateControlDetail().size(); k++) {
                if (stateControlData.getFlagStateControlList().get(j).getInspect_no().equals(stateControlData.getFlagStateControlDetail().get(k).getInspect_no())) {
                    detailBeanList.add(stateControlData.getFlagStateControlDetail().get(k));
                }
            }
        }
        SecurityCheckAdapter securityCheckAdapter = new SecurityCheckAdapter(mContext, stateControlData, detailBeanList,mmsi,ywcm);
        rvSecurityCheck.setAdapter(securityCheckAdapter);
    }

    @Override
    public void onLoadSecurityCheckFail(String msg) {
        loadSecurityCheckDataResult = false;
        rvSecurityCheck.setVisibility(View.GONE);
        loadFail(btnSeeShipSecurityCheckInfo, null, tvShipSecurityCheckInfoMsg, msg);
    }

    @Override
    public void setLowsInfoData(LaoShiShip laoShiShip) {
        loadLoydsInfoResult = true;
        loadSuccess(btnSeeShipLoydsInfo, shipLoydsInfoTableLayout, tvShipLoydsInfoMsg);
        tv_auxiliarypower.setText(laoShiShip.getAuxiliarypower());
        tv_ballast.setText(laoShiShip.getBallast());
        tv_boiler.setText(laoShiShip.getBoiler());
        tv_boilerbuild.setText(laoShiShip.getBoilerbuild());
        tv_boilerdate.setText(laoShiShip.getBoilerdate());
        tv_boilerplace.setText(laoShiShip.getBoilerplace());
        tv_breadth.setText(laoShiShip.getBreadth());
        tv_buckheads.setText(laoShiShip.getBuckheads());
        tv_builder.setText(laoShiShip.getBuilder());
        tv_buildplace.setText(laoShiShip.getBuildplace());
        tv_callsign.setText(laoShiShip.getCallsign());
        tv_classnotation.setText(laoShiShip.getClassnotation());
        tv_collectionnum.setText(laoShiShip.getCollectionnum());
        tv_datebuild.setText(laoShiShip.getDatebuild());
        tv_deadweight.setText(laoShiShip.getDraught());
        tv_deckerections.setText(laoShiShip.getDeckerections());
        tv_decks.setText(laoShiShip.getDecks());
        tv_depth.setText(laoShiShip.getDepth());
        tv_displacement.setText(laoShiShip.getDisplacement());
        tv_draught.setText(laoShiShip.getDraught());
        tv_feedborard.setText(laoShiShip.getFeedborard());
        tv_flag.setText(laoShiShip.getFlag());
        tv_formeren.setText(laoShiShip.getFormeren());
        tv_formername.setText(laoShiShip.getFormername());
        tv_gross.setText(laoShiShip.getGross());
        tv_hatch.setText(laoShiShip.getHatch());
        tv_hatchsize.setText(laoShiShip.getHatchsize());
        tv_height.setText(laoShiShip.getHeight());
        tv_holds.setText(laoShiShip.getHolds());
        tv_hullconnections.setText(laoShiShip.getHullconnections());
        tv_hullmaterial.setText(laoShiShip.getAuxiliarypower());
        tv_imono.setText(laoShiShip.getImono());
        tv_lengthbp.setText(laoShiShip.getLengthbp());
        tv_lengthoverall.setText(laoShiShip.getLengthoverall());
        tv_liftingdevices.setText(laoShiShip.getLiftingdevices());
        tv_mainpower.setText(laoShiShip.getMainpower());
        tv_mmsino.setText(laoShiShip.getMmsino());
        tv_nameen.setText(laoShiShip.getNameen());
        tv_net.setText(laoShiShip.getNet());
        tv_officialno.setText(laoShiShip.getOfficialno());
        tv_ownerid.setText(laoShiShip.getOwnerid());
        tv_port.setText(laoShiShip.getPort());
        tv_powerbore.setText(laoShiShip.getPowerbore());
        tv_powerbuilder.setText(laoShiShip.getPowerbuilder());
        tv_powerdate.setText(laoShiShip.getPowerdate());
        tv_powerplace.setText(laoShiShip.getPowerplace());
        tv_powertype.setText(laoShiShip.getPowertype());
        tv_regowner.setText(laoShiShip.getRegowner());
        tv_rpm.setText(laoShiShip.getRpm());
        tv_shipmanager.setText(laoShiShip.getShipmanager());
        tv_shipname.setText(laoShiShip.getShipname());
        tv_shipoperator.setText(laoShiShip.getShipoperator());
        tv_shiptype.setText(laoShiShip.getShiptype());
        tv_speed.setText(laoShiShip.getSpeed());
        tv_status.setText(laoShiShip.getStatus());
        tv_voltage.setText(laoShiShip.getVoltage());
    }

    @Override
    public void onLoadShipLoydsInfoFail(String err) {
        loadLoydsInfoResult = false;
        loadFail(btnSeeShipLoydsInfo, shipLoydsInfoTableLayout, tvShipLoydsInfoMsg, err);
    }

    @Override
    public void setCrewInfoData(List<CrewInfo> crewInfos) {
        loadCrewInfoResult = true;
        rvCrewinfos.setVisibility(View.VISIBLE);
        loadSuccess(btnSeeShipCrewInfo, null, tvShipCrewInfoMsg);
        CrewInfoAdapter crewInfoAdapter = new CrewInfoAdapter(R.layout.crew_info_item, crewInfos);
        rvCrewinfos.setAdapter(crewInfoAdapter);
        crewInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ImageButton imgbtn = (ImageButton) view;
                TableLayout tableLayout = (TableLayout) adapter.getViewByPosition(rvCrewinfos, position, R.id.tablelayout_crew_detail);
                assert tableLayout != null;
                if (tableLayout.getVisibility() == View.VISIBLE) {
                    tableLayout.setVisibility(View.GONE);
                    imgbtn.setImageResource(R.mipmap.crew_open);
                } else {
                    tableLayout.setVisibility(View.VISIBLE);
                    imgbtn.setImageResource(R.mipmap.crew_collected);
                }
            }
        });
    }

    @Override
    public void onLoadCrewInfoFail(String msg) {
        loadCrewInfoResult = false;
        rvCrewinfos.setVisibility(View.GONE);
        loadFail(btnSeeShipCrewInfo, null, tvShipCrewInfoMsg, msg);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(mContext, msg, msg);
        tv_task.setVisibility(View.GONE);
    }

    @Override
    public void onLoadShipTrackFail(String error) {
        ToastUtils.showToast(mContext, error);
        dialog.dismiss();
    }

    @Override
    public void setShipTrack(List<TrackData> trackData) {
        dialog.dismiss();

        winfoDNCView.winfoDNCParams.trackPointList = trackData;
        winfoDNCView.isShowShipTrack = true;
        winfoDNCView.winfoDNCParams.level = 12;
        Point centerPoint = new Point();
        winfoDNCView.isOpenShipTrack = true;
        centerPoint.lon = winfoDNCView.winfoDNCParams.trackPointList.get(0).getJD();
        centerPoint.lat = winfoDNCView.winfoDNCParams.trackPointList.get(0).getWD();
        winfoDNCView.winfoDNCParams.centerPoint = centerPoint;
        winfoDNCView.SZ_guiji = aisData.SZCLX;
        winfoDNCView.invalidate();
        dissmissAisData();
        if (winfoDNCView.winfoDNCParams.trackPointList != null) {
            winfoDNCView.BoolShowTip();
            if (btn_close_track != null) {
                btn_close_track.setVisibility(View.VISIBLE);
            }
            if (mainSearchEdit != null) {
                mainSearchEdit.setHint(winfoDNCView.winfoDNCParams.trackPointList.get(0).getMMSI() + "");
            }

        }
    }

    @Override
    public void onLoadShipSelectionRiskFail(String msg) {
        loadShipSelectionRiskDataResult = false;
        loadFail(btnSeeShipSelectionRiskInfo, shipSelectionRiskTablelayout, tvShipSelectionRiskInfoMsg, msg);
    }

    @Override
    public void setShipRegisterInfo(ShipRiskInfo shipRiskInfo) {
        loadShipSelectionRiskDataResult = true;
        loadSuccess(btnSeeShipSelectionRiskInfo, shipSelectionRiskTablelayout, tvShipSelectionRiskInfoMsg);
        tv_ship_selection_risk_ship_no.setText(shipRiskInfo.getShip_no());
        tv_ship_selection_risk_ship_id.setText(shipRiskInfo.getShip_id());
        tv_ship_selection_risk_mmsi.setText(shipRiskInfo.getMmsi());
        tv_ship_selection_risk_imo.setText(shipRiskInfo.getShip_imo());
        tv_ship_selection_risk_ship_callsign.setText(shipRiskInfo.getShip_callsign());
        tv_ship_selection_risk_ship_name_cn.setText(shipRiskInfo.getShip_name_cn());
        tv_ship_selection_risk_ship_name_en.setText(shipRiskInfo.getShip_name_en());
        //海河船标志 0是河船   1是海船
        switch (shipRiskInfo.getShip_region_type()) {
            case "0":
                tv_ship_selection_risk_ship_region_type.setText("河船");
                break;
            case "1":
                tv_ship_selection_risk_ship_region_type.setText("海船");
                break;
            default:
                tv_ship_selection_risk_ship_region_type.setText(shipRiskInfo.getShip_region_type());
                break;
        }
        tv_ship_selection_risk_nation_code.setText(SZMSAUtils.getCountryByCode(shipRiskInfo.getNation_code()));
        tv_ship_selection_risk_ship_type_value.setText(shipRiskInfo.getShip_type_value());
        tv_ship_selection_risk_ship_build_date.setText(shipRiskInfo.getShip_built_date());
        tv_ship_selection_risk_ship_age_value.setText(shipRiskInfo.getShip_age_value());
        switch (shipRiskInfo.getRisk_attribute()) {
            case "S1":
                tv_ship_selection_risk_risk_attribute.setText("高风险船");
                break;
            case "S2":
                tv_ship_selection_risk_risk_attribute.setText("中等风险船");
                break;
            case "S3":
                tv_ship_selection_risk_risk_attribute.setText("低风险船");
                break;
            default:
                tv_ship_selection_risk_risk_attribute.setText(shipRiskInfo.getRisk_attribute());
                break;
        }
        tv_ship_selection_risk_last_inspect_date.setText(shipRiskInfo.getLast_inspect_date());
        switch (shipRiskInfo.getRisk_attribute_spot()) {
            case "S1":
                tv_ship_selection_risk_risk_attribute_spot.setText("高风险船");
                break;
            case "S2":
                tv_ship_selection_risk_risk_attribute_spot.setText("中等风险船");
                break;
            case "S3":
                tv_ship_selection_risk_risk_attribute_spot.setText("低风险船");
                break;
            default:
                tv_ship_selection_risk_risk_attribute_spot.setText(shipRiskInfo.getRisk_attribute_spot());
                break;
        }
        tv_ship_selection_risk_last_inspect_date_spot.setText(shipRiskInfo.getLast_inspect_date_spot());
        switch (shipRiskInfo.getPriority_order()) {
            case "FP0":
                tv_ship_selection_risk_priority_order.setText("必检船");
                break;
            case "FP1":
                tv_ship_selection_risk_priority_order.setText("应检船");
                break;
            case "FP2":
                tv_ship_selection_risk_priority_order.setText("可检船");
                break;
            case "FP3":
                tv_ship_selection_risk_priority_order.setText("不可检船");
                break;
            default:
                tv_ship_selection_risk_priority_order.setText(shipRiskInfo.getPriority_order());
                break;
        }
        switch (shipRiskInfo.getPriority_order_spot()) {
            case "SP0":
                tv_ship_selection_risk_priority_order_spot.setText("必检船");
                break;
            case "SP1":
                tv_ship_selection_risk_priority_order_spot.setText("应检船");
                break;
            case "SP2":
                tv_ship_selection_risk_priority_order_spot.setText("可检船");
                break;
            case "SP3":
                tv_ship_selection_risk_priority_order_spot.setText("不可检船");
                break;
            default:
                tv_ship_selection_risk_priority_order_spot.setText(shipRiskInfo.getPriority_order_spot());
                break;
        }
        switch (shipRiskInfo.getShip_inspect_org_performance()) {
            case "A":
                tv_ship_selection_risk_ship_inspect_org_performance.setText("极低");
                break;
            case "B":
                tv_ship_selection_risk_ship_inspect_org_performance.setText("低");
                break;
            case "C":
                tv_ship_selection_risk_ship_inspect_org_performance.setText("中");
                break;
            case "D":
                tv_ship_selection_risk_ship_inspect_org_performance.setText("高");
                break;
            default:
                tv_ship_selection_risk_ship_inspect_org_performance.setText(shipRiskInfo.getShip_inspect_org_performance());
                break;
        }
        tv_ship_selection_risk_ship_inspect_org_value.setText(shipRiskInfo.getShip_inspect_org_value());
        switch (shipRiskInfo.getCompany_performance()) {
            case "A":
                tv_ship_selection_risk_company_performance.setText("极低");
                break;
            case "B":
                tv_ship_selection_risk_company_performance.setText("低");
                break;
            case "C":
                tv_ship_selection_risk_company_performance.setText("中");
                break;
            case "D":
                tv_ship_selection_risk_company_performance.setText("高");
                break;
            default:
                tv_ship_selection_risk_company_performance.setText(shipRiskInfo.getCompany_performance());
                break;
        }
        tv_ship_selection_risk_company_value.setText(shipRiskInfo.getCompany_value());
        switch (shipRiskInfo.getIs_report()) {
            case "0":
                tv_ship_selection_risk_is_report.setText("否");
                break;
            case "1":
                tv_ship_selection_risk_is_report.setText("是");
                break;
            default:
                tv_ship_selection_risk_is_report.setText(shipRiskInfo.getIs_report());
                break;
        }
        switch (shipRiskInfo.getIs_design()) {
            case "0":
                tv_ship_selection_risk_is_design.setText("否");
                break;
            case "1":
                tv_ship_selection_risk_is_design.setText("是");
                break;
            default:
                tv_ship_selection_risk_is_design.setText(shipRiskInfo.getIs_design());
                break;
        }
        switch (shipRiskInfo.getIs_investigate()) {
            case "0":
                tv_ship_selection_risk_is_investigate.setText("否");
                break;
            case "1":
                tv_ship_selection_risk_is_investigate.setText("是");
                break;
            default:
                tv_ship_selection_risk_is_investigate.setText(shipRiskInfo.getIs_investigate());
                break;
        }
        tv_ship_selection_risk_depect_value.setText(shipRiskInfo.getDefect_value());
        switch (shipRiskInfo.getIs_tracking_ship()) {
            case "0":
                tv_ship_selection_risk_is_tracking_ship.setText("否");
                break;
            case "1":
                tv_ship_selection_risk_is_tracking_ship.setText("是");
                break;
            default:
                tv_ship_selection_risk_is_tracking_ship.setText(shipRiskInfo.getIs_tracking_ship());
                break;
        }
        tv_xcbzyxsx.setText(shipRiskInfo.getGeneral_order());
        try {
            if (Integer.parseInt(shipRiskInfo.getGeneral_order().trim()) > 8) {
                tv_xcbzfxsx.setText("高风险船");
            } else if (Integer.parseInt(shipRiskInfo.getGeneral_order().trim()) <= 8 && Integer.parseInt(shipRiskInfo.getGeneral_order().trim()) >= 4) {
                tv_xcbzfxsx.setText("中风险船");
            } else if (Integer.parseInt(shipRiskInfo.getGeneral_order().trim()) < 4 && Integer.parseInt(shipRiskInfo.getGeneral_order().trim()) > 0) {
                tv_xcbzfxsx.setText("低风险船");
            }

        } catch (Exception ignored) {

        }


        tv_ship_selection_risk_tracking_ship_value.setText(shipRiskInfo.getTracking_ship_value());
        tv_ship_selection_risk_detention_value.setText(shipRiskInfo.getDetention_value());
        tv_ship_selection_risk_total_value.setText(shipRiskInfo.getTotal_value());
        tv_ship_selection_risk_depect_average_num.setText(shipRiskInfo.getDefect_average_num());
        tv_ship_selection_risk_detention_num.setText(shipRiskInfo.getDetention_num());
        tv_ship_selection_risk_penalty_num.setText(shipRiskInfo.getPenalty_num());
        tv_ship_selection_risk_accident_num.setText(shipRiskInfo.getAccident_num());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_exception_delete:

                DialogUtils.showDialog(mContext, "温馨提示", "是否删除异常船舶", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        dialog.dismiss();
                        exceptionShipOperatePresenter.deleteExceptionShip(aisData.getID(), true);
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();

                    }
                });
                break;
            case R.id.id_btn_exception_add:
                if (exceptionShipDialog == null) {
                    exceptionShipDialog = new ExceptionShipDialog(mContext);
                    exceptionShipDialog.setOnData(new ExceptionShipDialog.OnGetData() {
                        @Override
                        public void onDataCallBack(String dec) {
                            exceptionShipOperatePresenter.addExceptionShip(dec, aisData, true);
                        }
                    });

                } else {
                    exceptionShipDialog.showDialog();
                }

                break;
            case R.id.btn_see_shipinfo:
                tvShipinfoMsg.setVisibility(View.GONE);
                btnSeeShipinfo.startAnimation();
                shipInfoPresenter.getShipInfoNew(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_shipinfo_level:
                tvShipinfoMsg_level.setVisibility(View.GONE);
                btnShipinfo.startAnimation();
                shipInfoPresenter.getShipInfo(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_exception_info:
                tvShipExceptionInfoMsg.setVisibility(View.GONE);
                btnSeeShipExceptionInfo.startAnimation();
                exceptionShipOperatePresenter.queryExceptionShip(aisData.getID(), false);
                break;
            case R.id.btn_see_ship_xzcf_info:
                ToastUtils.showToast(mContext, "接口建设中...");
                break;
            case R.id.btn_see_ship_company_info:
                tvShipCompanyinfoMsg.setVisibility(View.GONE);
                btnSeeShipCompanyInfo.startAnimation();
                shipCompanyInfoPresenter.getShipCompanyInfo(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_register_info:
                tvShipRegisterInfoMsg.setVisibility(View.GONE);
                btnSeeShipRegisterInfo.startAnimation();
                shipRegisterInfoPresenter.getShipRegisterInfo(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_loyds_info:
                tvShipLoydsInfoMsg.setVisibility(View.GONE);
                btnSeeShipLoydsInfo.startAnimation();
                loydsInfoPresenter.getLowsInfoData(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_security_check_info:
                tvShipSecurityCheckInfoMsg.setVisibility(View.GONE);
                btnSeeShipSecurityCheckInfo.startAnimation();
                securityCheckPresenter.getSecurityCheckInfoByMmsiOrYwcm(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_xianchang_info:
                tvShipXianChangInfoMsg.setVisibility(View.GONE);
                btnSeeShipXianChangInfo.startAnimation();
                shipXianChangPresenter.getShipXianChangData(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_crew_info:
                tvShipCrewInfoMsg.setVisibility(View.GONE);
                btnSeeShipCrewInfo.startAnimation();
                crewInfoPresenter.getCrewInfosByMmsiOrYwcm(aisData.getID(), aisData.getCM());
                break;
            case R.id.btn_see_ship_inspection_info:
                ToastUtils.showToast(mContext, "接口建设中...");
                break;
            case R.id.btn_see_ship_declare_info:
                ToastUtils.showToast(mContext, "接口建设中...");
                break;
            case R.id.btn_see_ship_selection_risk_info:
//                ToastUtils.showToast(mContext, "接口建设中...");
                tvShipSelectionRiskInfoMsg.setVisibility(View.GONE);
                btnSeeShipSelectionRiskInfo.startAnimation();
                shipSelectionRiskPresenter.getShipSelectionRisk(aisData.getID(), aisData.getCM());
                break;
            case R.id.imgPhone:
                callUp(tvPhone.getText().toString());
                break;
            case R.id.imgSms:
                sendSMS(tvPhone.getText().toString());
                break;
            case R.id.but_updataTel:
                if (PreferenceUtils.getBoolean(mContext, "islogin", false)) {
                    UpdataTelDialog telDialog = new UpdataTelDialog((FragmentActivity) mContext, mmsi);
                    telDialog.show();
                    telDialog.setSetStrData(new UpdataTelDialog.SetStrData() {
                        @Override
                        public void setStr(String tel, String telName) {
                            tvPhone.setText(tel);
                            tvPhoneName.setText(telName);
                            if (!"".equals(tvPhone.getText().toString().trim())) {
                                butCall.setVisibility(VISIBLE);
                                butSms.setVisibility(VISIBLE);
                            }
                        }
                    });
                } else {
                    mContext.startActivity(new Intent(mContext, UserLoginActivity.class));
                }
                break;
            case R.id.rl_aisInfo:
                switchTableLayout(shipAisinfoTableLayout, loadAisInfoResult);
                break;
            case R.id.rl_shipInfo:
                switchTableLayout(shipinfoTablelayout, loadShipInfoResult);
                break;
            case R.id.rl_shipInfo_level:
                switchTableLayout(shipinfoTablelayout_level, loadShipInfoResult_level);
                break;
            case R.id.rl_ship_register_info:
                switchTableLayout(shipRegisterInfoTableLayout, loadShipRegistrationInfoResult);
                break;

            case R.id.rl_company_info:
                switchTableLayout(shipCompanyInfoTableLayout, loadCompanyInfoResult);
                break;
            case R.id.rl_ship_exception_Info:
                switchTableLayout(shipExceptionTableLayout, loadExceptionShipInfoResult);
                if (ll_anjian_btn.getVisibility() == View.VISIBLE) {
                    ll_anjian_btn.setVisibility(View.GONE);
                } else {
                    ll_anjian_btn.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rl_ship_xzcf_Info:
                break;
            case R.id.rl_ship_loyds_Info:
                switchTableLayout(shipLoydsInfoTableLayout, loadLoydsInfoResult);
                break;
            case R.id.rl_crew_info_layout:
                switchRecyclerView(rvCrewinfos, loadCrewInfoResult);
                break;
            case R.id.rl_security_check_data_layout:
                switchRecyclerView(rvSecurityCheck, loadSecurityCheckDataResult);
                break;
            case R.id.rl_xianchang_data_layout:
                switchRecyclerView(rvXianChangLayout, loadXianChangDataResult);
                break;
            case R.id.tv_ship_trajectory:
                Boolean islogin2 = PreferenceUtils.getBoolean(mContext, "islogin", false);
                if (islogin2) {
                    //轨迹
                    startStopTimePickerView.setOnTimeSelectListener(new StartStopTimePickerView.OnTimeSelectListener() {

                        @Override
                        public void onTimeSelect(Date date, Date date_stop) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                            String time = format.format(date) + ":00";

                            String time_stop = format.format(date_stop) + ":00";
//                       ToastUtils.showToast(mContext,"开始："+time+"结束："+time_stop);
                            shipTrackPresenter.getShipTrack(dialog, true, aisData.getID(), time, time_stop);
//                        shipTrackPresenter.getShipTrack(dialog,true,"413826973","2017-12-11%2022:36:42","2017-12-12%2000:18:02");

                        }
                    });
                    startStopTimePickerView.show();
                } else {
                    mContext.startActivity(new Intent(mContext, UserLoginActivity.class));
                }
                break;
            case R.id.tv_ship_photo:
//                ObtainevidenceDialog();
                if (PreferenceUtils.getBoolean(mContext, "islogin", false)) {
                    Intent intent1 = new Intent(mContext, CertificateMainActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("aisdata", aisData);
                    intent1.putExtras(bundle1);
                    mContext.startActivity(intent1);
                } else {
                    mContext.startActivity(new Intent(mContext, UserLoginActivity.class));
                }
                break;
            case R.id.tv_ship_task:

                Intent intent = new Intent(mContext, AssignTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("aisdata", aisData);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                break;
            case R.id.rl_ship_selection_risk:
                switchTableLayout(shipSelectionRiskTablelayout, loadShipSelectionRiskDataResult);
                break;
//            case R.id.img_sub_photo1:
//                PhotoPicker.builder()
//                        .setPhotoCount(9)
//                        .setGridColumnCount(4)
//                        .setSelected(selectedPhotos1)
//                        .start( (Activity) mContext, 8001);
//                break;
//            case R.id.img_sub_photo2:
//                PhotoPicker.builder()
//                        .setPhotoCount(9)
//                        .setGridColumnCount(4)
//                        .setSelected(selectedPhotos2)
//                        .start((Activity)mContext, 8002);
//                break;
//            case R.id.btn_sub_photo:
//                if (PreferenceUtils.getBoolean(mContext, "islogin", false)) {
//                    if (selectedPhotos1.size() > 0 || selectedPhotos2.size() > 0) {
//                        dialog.show();
//                        obtainPresenter.subObtainData(aisData, selectedPhotos1, selectedPhotos2);
//                    } else {
//                        ToastUtils.showToast(mContext, "请先选中照片再点击上传！");
//                    }
//                } else {
//                    mContext.startActivity(new Intent(mContext, UserLoginActivity.class));
//                }
//                break;
        }
    }

    /**
     * 隐藏所有的tablelayout 和错误消息的textview
     */
    private void hideAllTableLayout() {
        rvCrewinfos.setVisibility(View.GONE);
        rvSecurityCheck.setVisibility(View.GONE);
        rvXianChangLayout.setVisibility(View.GONE);
        shipAisinfoTableLayout.setVisibility(View.VISIBLE);
        shipinfoTablelayout.setVisibility(View.GONE);
        shipinfoTablelayout_level.setVisibility(View.GONE);
        shipCompanyInfoTableLayout.setVisibility(View.GONE);
        shipRegisterInfoTableLayout.setVisibility(View.GONE);
        shipExceptionTableLayout.setVisibility(View.GONE);
        id_btn_exception_add.setVisibility(View.GONE);
        id_btn_exception_delete.setVisibility(View.GONE);
        shipLoydsInfoTableLayout.setVisibility(View.GONE);
        shipSelectionRiskTablelayout.setVisibility(View.GONE);

        //AIS信息加载提示
        tvShipAisinfoMsg.setVisibility(View.GONE);
        //公司信息加载提示
        tvShipCompanyinfoMsg.setVisibility(View.GONE);
        //基本信息加载提示
        tvShipinfoMsg.setVisibility(View.GONE);
        tvShipinfoMsg_level.setVisibility(View.GONE);
        //登记信息加载提示
        tvShipRegisterInfoMsg.setVisibility(View.GONE);
        //劳氏数据信息加载提示
        tvShipLoydsInfoMsg.setVisibility(View.GONE);
        //安检信息加载提示
        tvShipSecurityCheckInfoMsg.setVisibility(View.GONE);
        //现场监督信息加载提示
        tvShipXianChangInfoMsg.setVisibility(View.GONE);
        //船员信息加载提示
        tvShipCrewInfoMsg.setVisibility(View.GONE);
        //船检信息加载提示
        tvShipInspectionInfoMsg.setVisibility(View.GONE);
        //申报信息加载提示
        tvShipDeclareInfoMsg.setVisibility(View.GONE);
        //选船风险值信息加载提示
        tvShipSelectionRiskInfoMsg.setVisibility(View.GONE);
        //异常船舶信息加载提示
        tvShipExceptionInfoMsg.setVisibility(View.GONE);
    }

    /**
     * 重置所有的加载按钮
     */
    private void revertAllAnimation() {
        //AIs信息
        butSeeAisinfo.revertAnimation();
        //基本信息
        btnSeeShipinfo.revertAnimation();
        btnShipinfo.revertAnimation();
        //公司信息
        btnSeeShipCompanyInfo.revertAnimation();
        //登记信息
        btnSeeShipRegisterInfo.revertAnimation();
        //劳氏数据
        btnSeeShipLoydsInfo.revertAnimation();
        //安检信息
        btnSeeShipSecurityCheckInfo.revertAnimation();
        //现场监督
        btnSeeShipXianChangInfo.revertAnimation();
        //船员信息
        btnSeeShipCrewInfo.revertAnimation();
        //船检信息
        btnSeeShipInspectionInfo.revertAnimation();
        //申报信息
        btnSeeShipDeclareInfo.revertAnimation();
        //选船风险值
        btnSeeShipSelectionRiskInfo.revertAnimation();
        //异常船舶
        btnSeeShipExceptionInfo.revertAnimation();

        //AIS信息
        butSeeAisinfo.setText("查看详情");
        //基本信息
        btnSeeShipinfo.setText("查看详情");
        btnShipinfo.setText("查看详情");
        //公司信息
        btnSeeShipCompanyInfo.setText("查看详情");
        //登记信息
        btnSeeShipRegisterInfo.setText("查看详情");
        //劳氏数据
        btnSeeShipLoydsInfo.setText("查看详情");
        //安检信息
        btnSeeShipSecurityCheckInfo.setText("查看详情");
        //现场监督
        btnSeeShipXianChangInfo.setText("查看详情");
        //船员信息
        btnSeeShipCrewInfo.setText("查看详情");
        //船检信息
        btnSeeShipInspectionInfo.setText("查看详情");
        //申报信息
        btnSeeShipDeclareInfo.setText("查看详情");
        //选船风险值
        btnSeeShipSelectionRiskInfo.setText("查看详情");
        //异常船舶
        btnSeeShipExceptionInfo.setText("查看详情");

    }

    /**
     * 折叠TableLayout
     * 只有加载成功时才允许折叠TableLayout
     */
    private void switchTableLayout(TableLayout tableLayout, boolean isLoadSuccess) {
        if (isLoadSuccess) {
            if (tableLayout.getVisibility() == VISIBLE) {
                tableLayout.setVisibility(View.GONE);
            } else {
                tableLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 折叠RecyclerView
     * 只有加载成功时才允许折叠TableLayout
     */
    private void switchRecyclerView(RecyclerView recyclerView, boolean isLoadSuccess) {
        if (isLoadSuccess) {
            if (recyclerView.getVisibility() == VISIBLE) {
                recyclerView.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * 加载成功 显示数据 并且停止按钮加载动画
     *
     * @param circularProgressButton 加载按钮
     * @param tableLayout            数据布局
     * @param textView               显示错误消息的textview
     */
    private void loadSuccess(CircularProgressButton circularProgressButton, TableLayout tableLayout, TextView textView) {
        circularProgressButton.doneLoadingAnimation(
                ContextCompat.getColor(mContext, R.color.blue),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
        circularProgressButton.stopAnimation();
        if (tableLayout != null) tableLayout.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }

    /**
     * 加载失败 隐藏数据 并且停止按钮加载动画
     *
     * @param circularProgressButton 加载按钮
     * @param tableLayout            数据布局
     * @param textView               显示错误消息的textview
     * @param msg                    消息内容
     */
    private void loadFail(final CircularProgressButton circularProgressButton, TableLayout tableLayout, TextView textView, String msg) {
        circularProgressButton.doneLoadingAnimation(
                ContextCompat.getColor(mContext, R.color.blue),
                BitmapFactory.decodeResource(getResources(), R.drawable.icon_down_g_with_48dp));
        if (tableLayout != null) tableLayout.setVisibility(View.GONE);
        circularProgressButton.stopAnimation();
        circularProgressButton.revertAnimation(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                circularProgressButton.setText("重新加载");
            }
        });
        textView.setVisibility(View.VISIBLE);
        textView.setText(msg);
    }

    private String mmsi;
    private Ais aisData;

    @SuppressLint("SetTextI18n")
    public void setShowAisData(Ais aisData) {
        this.aisData = aisData;
        mmsi = aisData.getID();
        if (aisData.getIMO().equals("0")) {
            aisData.setIMO("");
        }
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        tvAISName.setText(aisData.getCM());
        tvMMSI.setText(aisData.getID());
        tvHH.setText(aisData.getHH());

        if (aisData.getIMO().equals("0")) {
            tvIMO.setText("");
        } else {
            tvIMO.setText(aisData.getIMO());
        }

        tvLat.setText(TransformUtil.trandu2m(aisData.getWD()) + "N");
        tvLon.setText(TransformUtil.trandu2m(aisData.getJD()) + "E");
        tvCsx.setText(aisData.getCSX() + "°");
//        tvCjx.setText(aisData.getCJX());
        tvCjx.setText(String.valueOf(aisData.getHX()) + "°");
        tvHs.setText(aisData.getHS() + " kts");
        tvCblx.setText(aisData.getCLX());
        tvAISLx.setText(aisData.getAISLX());
        tvCc.setText(aisData.getCC() + "m");
        tvCk.setText(aisData.getCK() + "m");
        tvMdd.setText(aisData.getMDD());
        tvDhsblx.setText(aisData.getDHSBLX());
        if (aisData.getZDCS() == 0) tvCs.setText("N/A");
        else tvCs.setText(aisData.getZDCS() + "m");
        tvHxzt.setText(aisData.getDHZT());
        tvYdsj.setText(aisData.getETA());
        //将此格式的时间yyyy/MM/dd HH:mm:ss转换成毫秒数
        long time = TimeUtil.time2Millisecond2(aisData.getCJSJ());
        time = time + 3600000 * 8;//加上8小时
        //再转换成格式化的时间yyyy-MM-dd-HH:mm:ss
        String date = TimeUtil.millisecond2FormatYMDHMS(time);
        tvUpdateTime.setText(date);

        hideAllTableLayout();
        revertAllAnimation();

        String userDeptCode = ACache.get(mContext).getAsString("deptCode");

        if (userDeptCode == null || userDeptCode.equals("")) {
            tv_task.setVisibility(View.GONE);
        } else {
            if (userDeptCode.equals("14010005") || userDeptCode.equals("14010008") ||
                    userDeptCode.equals("14020041") || userDeptCode.equals("14020006") ||
                    userDeptCode.equals("14030006") || userDeptCode.equals("14030041") || userDeptCode.equals("14030005") ||
                    userDeptCode.equals("14040005") || userDeptCode.equals("14040041") ||
                    userDeptCode.equals("14050005") || userDeptCode.equals("14050006") || userDeptCode.equals("14050041") ||
                    userDeptCode.equals("14060041") || userDeptCode.equals("14060005")) {
                tv_task.setVisibility(View.VISIBLE);
            } else {
                tv_task.setVisibility(View.GONE);
            }
        }
        //AIS信息默认显示
        tvShipAisinfoMsg.setVisibility(View.GONE);
        butSeeAisinfo.startAnimation();
        shipAisinfoTableLayout.setVisibility(View.VISIBLE);
        loadAisInfoResult = true;//加载成功
        loadSuccess(butSeeAisinfo, shipAisinfoTableLayout, tvShipAisinfoMsg);

    }

    /**
     * 当锁定一艘船时，只更新数据
     *
     * @param aisData ais数据
     */
    @SuppressLint("SetTextI18n")
    public void refreshShowAisData(Ais aisData) {
        tvAISName.setText(aisData.getCM());
        tvMMSI.setText(aisData.getID());
        tvHH.setText(aisData.getHH());

        if (aisData.getIMO().equals("0")) {
            tvIMO.setText("");
        } else {
            tvIMO.setText(aisData.getIMO());
        }

//        tvIMO.setText(aisData.getIMO());
        tvLat.setText(TransformUtil.trandu2m(aisData.getWD()) + "N");
        tvLon.setText(TransformUtil.trandu2m(aisData.getJD()) + "E");
        tvCsx.setText(aisData.getCSX() + "°");
//        tvCjx.setText(aisData.getCJX());
        tvCjx.setText(String.valueOf(aisData.getHX()) + "°");
        tvHs.setText(aisData.getHS() + " kts");
        tvCblx.setText(aisData.getCLX());
        tvAISLx.setText(aisData.getAISLX());
        tvCc.setText(aisData.getCC() + "m");
        tvCk.setText(aisData.getCK() + "m");
        tvMdd.setText(aisData.getMDD());
        tvDhsblx.setText(aisData.getDHSBLX());
        if (aisData.getZDCS() == 0) tvCs.setText("N/A");
        else tvCs.setText(aisData.getZDCS() + "m");
        tvHxzt.setText(aisData.getDHZT());
        tvYdsj.setText(aisData.getETA());
        //将此格式的时间yyyy/MM/dd HH:mm:ss转换成毫秒数
        long time = TimeUtil.time2Millisecond2(aisData.getCJSJ());
        time = time + 3600000 * 8;//加上8小时
        //再转换成格式化的时间yyyy-MM-dd-HH:mm:ss
        String date = TimeUtil.millisecond2FormatYMDHMS(time);
        tvUpdateTime.setText(date);
    }

    public void showShipPhone(TelephoneMmsi data) {
        if (data != null && !"".equals(data.getTelephoneNum())) {
            butCall.setVisibility(VISIBLE);
            butSms.setVisibility(VISIBLE);
            tvPhone.setText(data.getTelephoneNum());
        } else {
            butCall.setVisibility(GONE);
            butSms.setVisibility(GONE);
            tvPhone.setText("没有查到相关数据");
        }
        if (data != null && !"".equals(data.getTeleName())) {
            tvPhoneName.setText(data.getTeleName());
        } else {
            tvPhoneName.setText("没有查到相关数据");
        }
    }

    public void callUp(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() == 11) {
            Intent intent_phone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.showToast(mContext, "please open this permission");
                return;
            }
            mContext.startActivity(intent_phone);
        } else {
            ToastUtils.showToast(mContext, "电话号码不正确!");
            return;
        }
    }

    public void sendSMS(String phoneNumber) {
        if (phoneNumber != null && isMobileNum(phoneNumber)) {
            Intent intent_send_sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
            mContext.startActivity(intent_send_sms);
        } else {
            ToastUtils.showToast(mContext, "电话号码不正确!");
            return;
        }
    }

    public boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    public void dissmissAisData() {
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public BottomSheetBehavior getBehavior() {
        return behavior;
    }

    public StartStopTimePickerView getStartStopTimePick() {
        return startStopTimePickerView;
    }

    public void setRLTopGONE() {
        rl_top.setVisibility(View.GONE);
    }

}
