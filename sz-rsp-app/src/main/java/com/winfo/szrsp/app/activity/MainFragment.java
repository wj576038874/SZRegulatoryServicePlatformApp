package com.winfo.szrsp.app.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.lidroid.xutils.ViewUtils;
import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.listener.ZoomControlClickListenner;
import com.winfo.szrsp.app.mvp.exceptionship.view.ExceptionShipListActivity;
import com.winfo.szrsp.app.mvp.mine.login.view.impl.UserLoginActivity;
import com.winfo.szrsp.app.mvp.mine.usercenter.view.UserCenterActivity;
import com.winfo.szrsp.app.mvp.plotting.view.PlottingAddView;
import com.winfo.szrsp.app.mvp.plotting.view.PlottingInfoView;
import com.winfo.szrsp.app.mvp.shipinfo.view.ShipInfoLayout;
import com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.TaskMainActivity;
import com.winfo.szrsp.app.mvp.zhifatrack.presenter.ZhifaTrackPresenter;
import com.winfo.szrsp.app.mvp.zhifatrack.view.IZhifaTrackView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalHistory;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.MyIconButton;
import com.winfo.szrsp.app.widget.ZoomControlsView;
import com.winfo.szrsp.app.widget.wheelview.StartStopTimePickerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//import com.winfo.szrsp.app.mvp.certificate.view.CertificateActvity;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * Package: com.winfo.szrsp.app.activity
 * Filename: MainFragment
 * Author: lsj
 * Date: 2017/10/17  13:43
 * Description:
 */
public class MainFragment extends Fragment implements  View.OnClickListener, IZhifaTrackView {
    private ImageButton location;
    private ImageButton exception;
    private ImageButton id_persion_guiji;
    private ImageButton id_persion_tuli;

    private ImageButton plotting;//标绘新增
    private ImageButton manage;//标绘查看
    PlottingInfoView plottingInfoView;
    PlottingAddView plottingAddView;
    private Button btn_plotting_confirm;
    private Button btn_plotting_cancel;
    // 主布局
    private CoordinatorLayout mainLayout;
    // 关闭轨迹
    ImageButton closeTrackBtn;

    private ImageButton map_switch;
    private ZoomControlsView zoomControlsView;
    private IWinfoDNCView iGetWinfoDNCView;
    private WinfoDNCView winfoDNCView;
    private MyIconButton id_renwu;
    private MyIconButton btnNearby;
    // 定位按钮
    public ImageButton imgBtnlocation;
    private LinearLayout mainRightMenu;
    private LinearLayout mainLeftMenu;
    private Button id_wode;
    private double lon;
    private double lat;


    private Ais aisData;
    private ImageView MainSearchImgBtnClose;
    private EditText mainSearchEdit;
    private View mainSearchViewLine;
    private boolean isVertical = true;
    private int screenHeight;//设备的总高度
    private int statusBarHeight;//状态栏高度
    private int screenWidth;//设备宽度

    private ZhifaTrackPresenter zhifaTrackPresenter;
    private StartStopTimePickerView startStopTimePickerView;


    SensorManager manager;
    MySensorListener listener;
    Sensor sensor;
    String[] direction;

    private Dialog dialog;
    private Button btn_close_track;//关闭轨迹按钮


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        initView(view);
        zhifaTrackPresenter = new ZhifaTrackPresenter();
        zhifaTrackPresenter.attachMvpView(this);
        screenHeight = DeviceUtils.getScreenSize(getActivity())[1];
        screenWidth = DeviceUtils.getScreenSize(getActivity())[0];
        statusBarHeight = DeviceUtils.getStatusBarHeight(getActivity());
        initEvent();
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mainSearchEdit.setHint("搜索船舶、通航要素");
        if (winfoDNCView.winfoDNCParams.searchAisData != null) {
            MainSearchImgBtnClose.setVisibility(View.VISIBLE);
            mainSearchViewLine.setVisibility(View.VISIBLE);
            MainSearchImgBtnClose.setImageResource(R.mipmap.main_search_close);
            mainSearchEdit.setHint(winfoDNCView.winfoDNCParams.searchAisData.getCM());
        } else if (winfoDNCView.winfoDNCParams.searchThys != null) {
            MainSearchImgBtnClose.setVisibility(View.VISIBLE);
            mainSearchViewLine.setVisibility(View.VISIBLE);
            MainSearchImgBtnClose.setImageResource(R.mipmap.main_search_close);
            mainSearchEdit.setHint(winfoDNCView.winfoDNCParams.searchThys.getNameCn());
        }
        if (winfoDNCView.winfoDNCParams.trackPointList != null) {
            mainSearchEdit.setHint(winfoDNCView.winfoDNCParams.trackPointList.get(0).getMMSI() + "");
            mainSearchViewLine.setVisibility(View.VISIBLE);
            MainSearchImgBtnClose.setImageResource(R.mipmap.main_search_close);
        }
        MainSearchImgBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_close_track.setVisibility(View.GONE);
                winfoDNCView.refreshMap();
                winfoDNCView.winfoDNCParams.searchAisData = null;
                winfoDNCView.winfoDNCParams.searchThys = null;
                winfoDNCView.winfoDNCParams.isLocationShip = false;
                winfoDNCView.BoolShowTip();
                MainSearchImgBtnClose.setImageResource(R.mipmap.search);
                mainSearchEdit.setHint("搜索船舶、通航要素");
                winfoDNCView.isOpenShipTrack = false;
                winfoDNCView.winfoDNCParams.trackPointList = null;
                winfoDNCView.isShowShipTrack = false;
                winfoDNCView.refreshMap();
                dissmissAisData();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            isVertical = false;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            isVertical = true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            isVertical = false;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            isVertical = true;
        }
    }
    ShipInfoLayout shipInfoLayout;
    private void initView(View view) {
        dialog = DialogUtils.createLoadingDialog(getActivity(), "加载中...");
        File dir = new File(SDCardUtils.getRootDirectory() + "/szrsp/scene/");
        if (!dir.exists()) {
            //如果文件夹不存在 则创建
            dir.mkdirs();
        }
        ViewUtils.inject(getActivity());
        plotting = view.findViewById(R.id.plotting);
        manage = view.findViewById(R.id.manage);
        mainLayout = view.findViewById(R.id.main);
        btn_close_track = view.findViewById(R.id.btn_close_track);
        map_switch = view.findViewById(R.id.swipch);
        exception = view.findViewById(R.id.exceptionshiplist);
        btn_plotting_confirm = view.findViewById(R.id.btn_plotting_confirm);
        btn_plotting_cancel= view.findViewById(R.id.btn_plotting_cancel);
        id_persion_guiji = view.findViewById(R.id.id_persion_guiji);
        closeTrackBtn = view.findViewById(R.id.close_track);
        id_renwu = view.findViewById(R.id.id_renwu);
        imgBtnlocation = view.findViewById(R.id.imgbtn_main_location);
        zoomControlsView = view.findViewById(R.id.zoom);
        mainRightMenu = view.findViewById(R.id.main_right_menu_layout);
        mainLeftMenu = view.findViewById(R.id.main_left_menu_layout);
        //获取电子海图控件
        winfoDNCView = iGetWinfoDNCView.getWinfoDNCView();
        id_wode = view.findViewById(R.id.id_wode);
        id_persion_tuli=view.findViewById(R.id.id_persion_tuli);

        if (winfoDNCView.winfoDNCParams.isLockAngle) {
            imgBtnlocation.setImageResource(R.mipmap.location_pressedl_rp);
        }
        btnNearby = view.findViewById(R.id.id_fujin);
        MainSearchImgBtnClose = view.findViewById(R.id.main_search_close);
        mainSearchViewLine = view.findViewById(R.id.main_search_right_line);
        mainSearchEdit = view.findViewById(R.id.etSearch);


        listener = new MySensorListener();
        manager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        assert manager != null;
        sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        // 应用在前台时候注册传感器的监听
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);

        location = view.findViewById(R.id.location);
         shipInfoLayout=new ShipInfoLayout(getActivity(),winfoDNCView,btn_close_track,mainSearchEdit);
         mainLayout.addView(shipInfoLayout);


        startStopTimePickerView = new StartStopTimePickerView(getActivity(), StartStopTimePickerView.Type.ALL);
        startStopTimePickerView.setCyclic(true);
        startStopTimePickerView.setCancelable(true);
        long time = System.currentTimeMillis();
        startStopTimePickerView.setTime(new Date(time - 1200000), new Date());

    }



    /**
     * 初始化事件
     */
    private void initEvent() {
        btn_plotting_confirm.setOnClickListener(this);
        btn_plotting_cancel.setOnClickListener(this);
        plotting.setOnClickListener(this);
        manage.setOnClickListener(this);

        btnNearby.setOnClickListener(this);
        map_switch.setOnClickListener(this);
        exception.setOnClickListener(this);
        id_renwu.setOnClickListener(this);
        zoomControlsView.setOnZoomControlClickListenner(new ZoomControlClickListenner(winfoDNCView, getActivity()));
        id_wode.setOnClickListener(this);
        imgBtnlocation.setOnClickListener(this);
        mainSearchEdit.setOnClickListener(this);
        location.setOnClickListener(this);

        id_persion_guiji.setOnClickListener(this);
        closeTrackBtn.setOnClickListener(this);
        btn_close_track.setOnClickListener(this);

        id_persion_tuli.setOnClickListener(this);

    }

    public void setWinfoDNCView(IWinfoDNCView iGetWinfoDNCView) {
        this.iGetWinfoDNCView = iGetWinfoDNCView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_plotting_confirm:
                if(winfoDNCView.isEditPlottingPoint){
                    plottingInfoView.showEditDialog(winfoDNCView.winfoDNCParams.plottingPoint);
                }else {
                    plottingAddView.showAddPlotting(winfoDNCView.winfoDNCParams.plottingPoint);
                }
                dissmissConfirmPlotting();
                break;
            case R.id.btn_plotting_cancel:
                winfoDNCView.isEditPlottingPoint=false;
                winfoDNCView.is_select_plotting_point=false;
                winfoDNCView.winfoDNCParams.plottingPoint=null;
                winfoDNCView.refreshMap();
                dissmissConfirmPlotting();
                break;

            case R.id.plotting:
                if (plottingAddView == null) {
                    plottingAddView = new PlottingAddView(getActivity(), winfoDNCView, manage);
                }
                winfoDNCView.is_select_plotting_point=false;
                winfoDNCView.winfoDNCParams.plottingPoint=null;
                winfoDNCView.refreshMap();
                dissmissConfirmPlotting();
                plottingAddView.showAddPlotting();

                break;
            case R.id.manage:
                // TODO 显示标绘信息表格
                if (plottingInfoView == null) {
                    plottingInfoView = new PlottingInfoView(getActivity(), winfoDNCView, manage, mainLayout);
                }



                winfoDNCView.is_select_plotting_point=false;
                winfoDNCView.winfoDNCParams.plottingPoint=null;
                winfoDNCView.refreshMap();
                dissmissConfirmPlotting();
                plottingInfoView.showControlArea();
                break;
            case R.id.location:
                //startActivity(new Intent(getActivity(), DXSHCJCBActivity.class));
                break;
            case R.id.exceptionshiplist:
//                TODO 放开异常船舶列表
                startActivity(new Intent(getActivity(), ExceptionShipListActivity.class));

//                Intent intent12 = new Intent(getActivity(), CertificateMainActivity.class);
//                Bundle bundle12 = new Bundle();
//                Ais ais=new Ais();
//                ais.setID("413775384");
//                bundle12.putSerializable("aisdata",ais);
//                intent12.putExtras(bundle12);
//                startActivity(intent12);

                break;
            case R.id.id_persion_guiji:
                Boolean islogin = PreferenceUtils.getBoolean(getActivity(), "islogin", false);
                if (islogin) {
                    //轨迹
                    startStopTimePickerView.setOnTimeSelectListener(new StartStopTimePickerView.OnTimeSelectListener() {

                        @Override
                        public void onTimeSelect(Date date, Date date_stop) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                            String time = format.format(date) + ":00";
                            String time_stop = format.format(date_stop) + ":00";

                            zhifaTrackPresenter.getZhifaTrack(dialog, true, ACache.get(getActivity()).getAsString("uuid"), time, time_stop);

                        }
                    });
                    startStopTimePickerView.show();
                } else {
                    startActivity(new Intent(getActivity(), UserLoginActivity.class));
                }
                break;
            case R.id.close_track:
                winfoDNCView.isShowZhifaTrajectory = false;
                closeTrackBtn.setVisibility(View.GONE);
                id_persion_guiji.setVisibility(View.VISIBLE);
                winfoDNCView.invalidate();
                break;
            case R.id.btn_close_track:
                btn_close_track.setVisibility(View.GONE);
                winfoDNCView.isOpenShipTrack = false;
                winfoDNCView.winfoDNCParams.searchAisData = null;
                winfoDNCView.winfoDNCParams.isLocationShip = false;
                winfoDNCView.winfoDNCParams.trackPointList = null;
                winfoDNCView.isShowShipTrack = false;
                winfoDNCView.BoolShowTip();
                MainSearchImgBtnClose.setImageResource(R.mipmap.search);
                mainSearchEdit.setHint("搜索船舶、通航要素");
                winfoDNCView.refreshMap();
                break;
            case R.id.swipch:
                MainActivity activity = (MainActivity) getActivity();
                activity.showDrawLayout();
                break;
            case R.id.id_renwu:
                if (PreferenceUtils.getBoolean(getActivity(), "islogin", false)) {
                    startActivity(new Intent(getActivity(), TaskMainActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), UserLoginActivity.class));
                }
                break;
            case R.id.id_wode:
                if (PreferenceUtils.getBoolean(getActivity(), "islogin", false)) {
                    startActivity(new Intent(getActivity(), UserCenterActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), UserLoginActivity.class));
                }
                break;
            case R.id.imgbtn_main_location:
                if (lon == 0.0 && lat == 0.0) {
                    ToastUtils.showToast(getActivity(), "请检查网络设置或GPS");
                }
                //没有锁定 则锁定并更换图标为锁定图标
                if (!winfoDNCView.winfoDNCParams.isLockAngle) {
                    winfoDNCView.winfoDNCParams.isLockAngle = true;
                    imgBtnlocation.setImageResource(R.mipmap.location_pressedl_rp);
                    if (getActivity() instanceof LocationBntClickListener) {
                        ((LocationBntClickListener) getActivity()).location();
                    }
                    winfoDNCView.requestLayout();
                } else {
                    winfoDNCView.winfoDNCParams.isLockAngle = false;
                    imgBtnlocation.setImageResource(R.mipmap.leftbar_location);
                }
//                }
                break;
            case R.id.etSearch:
                changeToSearchFragment.toSearchFragment();
                break;
            case R.id.id_fujin:
                if (nearbyBtnClickListener != null) {
                    nearbyBtnClickListener.onNearbyBtnClick(lat, lon);
                }
                break;


            case R.id.id_persion_tuli:
                showTuliPopWindow();
                break;

        }
    }


    private PopupWindow tuliPopupWindow;
    //窗体距离屏幕最右端或是最左端10dp所对应的像素（把10dp转化成px）
    private int tuliPopViewMargin;
    private void showTuliPopWindow() {
        if (tuliPopupWindow!=null){
            tuliPopupWindow.showAsDropDown(id_persion_guiji,0,tuliPopViewMargin);
            return;
        }
        View tuliPopView = View.inflate(getActivity(), R.layout.pw_tuli, null);
        //初始化PopWindow
        tuliPopViewMargin = DimensUtils.dp2px(getActivity(), 10);
        tuliPopupWindow=new PopupWindow(tuliPopView,DimensUtils.dp2px(getActivity(), 250),ViewGroup.LayoutParams.WRAP_CONTENT);
        tuliPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tuliPopupWindow.setFocusable(true);
        tuliPopupWindow.setAnimationStyle(R.style.tuli_popwin_anim_style);//动画效果
        tuliPopupWindow.setOutsideTouchable(true);//关闭弹出窗体
        tuliPopupWindow.showAsDropDown(id_persion_guiji,0,tuliPopViewMargin);
    }


    public void setValues(double longitude, double latitude, float f) {
        lon = longitude;
        lat = latitude;
    }


    @SuppressLint("SetTextI18n")
    public void showAisData(Ais aisData) {
        this.aisData = aisData;
        shipInfoLayout.setShowAisData(aisData);

    }

    public void showShipPhone(TelephoneMmsi data) {
       shipInfoLayout.showShipPhone(data);
    }

    /**
     * 当锁定一艘船时，只更新数据
     *
     * @param aisData ais数据
     */
    @SuppressLint("SetTextI18n")
    public void refreshAisData(Ais aisData) {
        shipInfoLayout.refreshShowAisData(aisData);
    }

    public void dissmissAisData() {
        shipInfoLayout.getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
    }


    @Override
    public void onLoadZhifaTrackFail(String error) {
        ToastUtils.showToast(getActivity(), error);
        dialog.dismiss();
    }

    @Override
    public void setZhifaTrack(List<MobileTerminalHistory> trackData) {
        dialog.dismiss();

        winfoDNCView.winfoDNCParams.zhifaPointList = trackData;
        winfoDNCView.isShowZhifaTrajectory = true;

        closeTrackBtn.setVisibility(View.VISIBLE);
        id_persion_guiji.setVisibility(View.GONE);
        if (winfoDNCView.winfoDNCParams.zhifaPointList.size() > 2) {
            Point centerPoint = new Point();
            centerPoint.lon = winfoDNCView.winfoDNCParams.zhifaPointList.get(2).getLongitude().doubleValue();
            centerPoint.lat = winfoDNCView.winfoDNCParams.zhifaPointList.get(2).getLatitude().doubleValue();
            winfoDNCView.winfoDNCParams.centerPoint = centerPoint;
            winfoDNCView.winfoDNCParams.level = 16;
            winfoDNCView.invalidate();
        }
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(getActivity(), msg, msg);
    }

    public void showConfirmPlotting() {
        if (plottingAddView != null && !plottingAddView.isDialogShowing()) {
            btn_plotting_confirm.setVisibility(View.VISIBLE);
            btn_plotting_cancel.setVisibility(View.VISIBLE);
        }
        if(plottingInfoView!=null){
            if(!plottingInfoView.isEditDialogShowing()){
                btn_plotting_confirm.setVisibility(View.VISIBLE);
                btn_plotting_cancel.setVisibility(View.VISIBLE);
            }

        }
    }

    public void dissmissConfirmPlotting() {
        if (plottingAddView != null) {
            btn_plotting_confirm.setVisibility(View.GONE);
            btn_plotting_cancel.setVisibility(View.GONE);
        }
        if(plottingInfoView!=null&&plottingInfoView.getEditDialog()!=null){
            btn_plotting_confirm.setVisibility(View.GONE);
        }

    }


    /**
     * 定位按钮的事件接口回调
     *
     * @author winfo-wj
     */
    public interface LocationBntClickListener {
        /**
         * 定位
         */
        void location();
    }

    /**
     * 切换定位按钮的状态
     */
    public void change() {
        imgBtnlocation.setImageResource(R.mipmap.leftbar_location);
    }

    /**
     * 切换至主页面搜索的fragment页面
     */
    public interface IChangeToSearchFragment {
        /**
         * 点击搜索框进入搜索页面fragment
         */
        void toSearchFragment();
    }


    public IChangeToSearchFragment changeToSearchFragment;

    public void setChangeToSearchFragment(IChangeToSearchFragment changeToSearchFragment) {
        this.changeToSearchFragment = changeToSearchFragment;
    }

    /**
     * 设置附近按钮点击的回调
     */
    public interface NearbyBtnClickListener {
        /**
         * 主界面 中 底部导航菜单的 附近按钮的点击事件
         */
        void onNearbyBtnClick(double longitude, double latitude);
    }

    public NearbyBtnClickListener nearbyBtnClickListener;

    public void setNearbyBtnClickListener(NearbyBtnClickListener nearbyBtnClickListener) {
        this.nearbyBtnClickListener = nearbyBtnClickListener;
    }

    private float rotate;

    private class MySensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
//            // 指南Maker移动的方向值
//            float offset = event.values[0];
//            Point p = new Point();
//            if (isVertical) {
//                p.direction = offset;
//                direction = DeviceUtils.getDirection(offset);
//            } else {
//                p.direction = offset + 90;
//                direction = DeviceUtils.getDirection(offset + 90);
//            }
//            winfoDNCView.winfoDNCParams.directionDNC = direction;
//            winfoDNCView.winfoDNCParams.directionDNC = direction;
//            if (isVertical) {
//                if (winfoDNCView.winfoDNCParams.isLockAngle) {
//                    //判断 旋转角度 大于 5度刷新
//                    if ((Math.abs(event.values[0] - rotate)) > 5) {
//                        rotate = event.values[0];
//                        winfoDNCView.invalidate();
//                    }
//                } else {
//                    //判断 旋转角度 大于 15度刷新
//                    if ((Math.abs(event.values[0] - rotate)) > 15) {
//                        rotate = event.values[0];
//                        winfoDNCView.invalidate();
//                    }
//                }
//            } else {
//                if (winfoDNCView.winfoDNCParams.isLockAngle) {
//                    //判断 旋转角度 大于 5度刷新
//                    if ((Math.abs(event.values[0] - rotate)) > 5) {
//                        rotate = event.values[0] + 90;
//                        winfoDNCView.invalidate();
//                    }
//                } else {
//                    //判断 旋转角度 大于 15度刷新
//                    if ((Math.abs(event.values[0] - rotate)) > 15) {
//                        rotate = event.values[0] + 90;
//                        winfoDNCView.invalidate();
//                    }
//                }
//            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }


    public BottomSheetBehavior getBehavior() {
        return shipInfoLayout.getBehavior();
    }

    public StartStopTimePickerView getStartStopTimePick() {
        return shipInfoLayout.getStartStopTimePick();
    }


}
