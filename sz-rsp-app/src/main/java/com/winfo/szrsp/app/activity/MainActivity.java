package com.winfo.szrsp.app.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.kyleduo.switchbutton.SwitchButton;
import com.winfo.dnc.sdk.MapLayersType;
import com.winfo.dnc.sdk.Point;
import com.winfo.dnc.sdk.WinfoDNCView;
import com.winfo.dnc.sdk.utils.PositionUtil;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.phone.MobileTerminalInfo;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.listener.ChangeSwitchButtonState;
import com.winfo.szrsp.app.mvp.map.searchais.view.SearchShipFragment;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.presenter.NavigableElementsPresenter;
import com.winfo.szrsp.app.mvp.maplayer.presenter.ZhifaPersonPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.mvp.mobile.view.IMobileView;
import com.winfo.szrsp.app.mvp.mobile.presenter.MobilePresenter;
import com.winfo.szrsp.app.mvp.nearby.view.NearByItemResultFragment;
import com.winfo.szrsp.app.mvp.nearby.view.NearByListFragment;
import com.winfo.szrsp.app.mvp.nearby.view.NearByListMoreFragment;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.thys.ShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysData;
import com.winfo.szrsp.app.sdk.entity.version.VersionInfo;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.utils.Constants;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.UpdateStatus;
import com.winfo.szrsp.app.utils.UpdateVersionUtil;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends FragmentActivity implements IWinfoDNCView, CompoundButton.OnCheckedChangeListener, MainFragment.LocationBntClickListener, MainFragment.IChangeToSearchFragment
        , IMapLayerView, SearchShipFragment.BackSearchShipFragment, SearchShipFragment.SearchOnItemClickListener, EasyPermissions.PermissionCallbacks, MainFragment.NearbyBtnClickListener
        , IMobileView, SearchShipFragment.SearchThysOnItemClickListener, NearByListFragment.LoadMoreFragment, NearByListMoreFragment.backthysFragment, NearByListFragment.backThisFragment
        , NearByListFragment.ShipItemBtnClickListner, NearByListFragment.ThysItemBtnClickListner, NearByItemResultFragment.backThisFragment
        , NearByListMoreFragment.ShipItemBtnClickListner, NearByListMoreFragment.ThysItemBtnClickListner {

    private WinfoDNCView winfoDNCView;
    private DrawerLayout mDrawerLayout;
    private MainFragment mainFragment;//主页面fragment
    private SearchShipFragment searchShipFragment;//主页面搜索
    private NearByListFragment nearByListFragment;//附近页面
    private NearByListMoreFragment nearByListMoreFragment;//通航要素船舶更多页面
    private NearByItemResultFragment nearByItemResultFragment;//点击结果页面
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private double longitude;
    private double latitude;

    // 切换图层按钮
    private RadioButton rbChangeSeaMap;
    private RadioButton rbChangeDituMap;
    private RadioButton rbChangeWeixingMap;

    //开关按钮
//    private SwitchButton sbShowShip;
//    private SwitchButton sb_showShip_ke;
//    private SwitchButton sb_showShip_gaosu;
//    private SwitchButton sb_showShip_huo;
//    private SwitchButton sb_showShip_wei;
//    private SwitchButton sb_showShip_zhifa;
//    private SwitchButton sb_showShip_qita;
    private SwitchButton sbShowShip;
    private SwitchButton sb_showShip_yehuo;
    private SwitchButton sb_showShip_ke;
    private SwitchButton sb_showShip_putonghuo;
    private SwitchButton sb_showShip_gongcheng;
    private SwitchButton sb_showShip_gongzuo;
    private SwitchButton sb_showShip_tuo;
    private SwitchButton sb_showShip_yu;
    private SwitchButton sb_showShip_qita;

    private SwitchButton sbShowThys;
    private SwitchButton sbShowZhifaPerson;
    private SwitchButton sb_showShip_all_zhifa;
    private AisPresenter aisPresenter;
    private NavigableElementsPresenter navigableElementsPresenter;
    private ZhifaPersonPresenter zhifaPersonPresenter;
    private LinearLayout left_ll;
    private MyHandler mHandler;
    private MobilePresenter mobilePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
        //初始化定位
        initLocation();
        //开始定位
        startLocation();
        //申请权限
        permissions();
        //TODO 任务获取服务
//        Intent intent = new Intent(this, TimerService.class);
//        startService(intent);
    }

    private void initData() {
        Boolean islogin = PreferenceUtils.getBoolean(this, "islogin", false);
        if (islogin) {
            winfoDNCView.isShowaAllZhifa = true;
            sb_showShip_all_zhifa.setChecked(true);
            sbShowZhifaPerson.setChecked(true);
        }
    }

    private void permissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(this, "授予必要的权限", 0, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    private void initView() {
        mHandler = new MyHandler(this);
        mobilePresenter = new MobilePresenter(this);
        aisPresenter = new AisPresenter(this);
        navigableElementsPresenter = new NavigableElementsPresenter(this);

        zhifaPersonPresenter = new ZhifaPersonPresenter(this);
        winfoDNCView = findViewById(R.id.winfoDNCView);
        SzRspApplication.winfoDNCView = winfoDNCView;
        mDrawerLayout = findViewById(R.id.drawerLayout);
        rbChangeSeaMap = findViewById(R.id.rb_layer_haitu);
        rbChangeDituMap = findViewById(R.id.rb_layer_ditu);
        rbChangeWeixingMap = findViewById(R.id.rb_layer_weixingtu);

        sbShowShip = findViewById(R.id.sb_showShip);
        sb_showShip_yehuo = findViewById(R.id.sb_showShip_yehuo);
        sb_showShip_ke = findViewById(R.id.sb_showShip_ke);
        sb_showShip_putonghuo = findViewById(R.id.sb_showShip_putonghuo);
        sb_showShip_gongcheng = findViewById(R.id.sb_showShip_gongcheng);
        sb_showShip_gongzuo = findViewById(R.id.sb_showShip_gongzuo);
        sb_showShip_tuo = findViewById(R.id.sb_showShip_tuo);
        sb_showShip_yu = findViewById(R.id.sb_showShip_yu);
        sb_showShip_qita = findViewById(R.id.sb_showShip_qita);

        sbShowThys = findViewById(R.id.sb_showThys);

        sbShowZhifaPerson = findViewById(R.id.sb_showzhifa_person);
        sb_showShip_all_zhifa = findViewById(R.id.sb_showShip_all_zhifa);
        left_ll = findViewById(R.id.ll_right);

        mainFragment = new MainFragment();
        //设置map操作的事件的回调
        mainFragment.setWinfoDNCView(this);
        mainFragment.setNearbyBtnClickListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.id_main_frameLayout, mainFragment);
        ft.commit();

        mainFragment.setChangeToSearchFragment(this);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int windowsWight = metric.widthPixels;
//        int windowsHeight = metric.heightPixels;
//        View leftMenu = findViewById(R.id.ll_right);
        ViewGroup.LayoutParams leftParams = left_ll.getLayoutParams();
        leftParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        leftParams.width = windowsWight / 2;
        left_ll.setLayoutParams(leftParams);

        updateVersion();
    }


    /**
     * 自动检测版本更新
     */
    private void updateVersion() {
        UpdateVersionUtil.getInstence().checkVersion(this, null, new UpdateVersionUtil.UpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus, final VersionInfo versionInfo) {
                switch (updateStatus) {
                    case UpdateStatus.YES:
                        //弹出更新提示
                        UpdateVersionUtil.getInstence().showDialog(MainActivity.this, versionInfo);
                        break;
                    case UpdateStatus.NOWIFI:

                        DialogUtils.showDialog(MainActivity.this, "温馨提示", "当前非wifi网络,下载会消耗手机流量!", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                            @Override
                            public void btnConfirmClick(Dialog dialog) {
                                dialog.dismiss();
                                UpdateVersionUtil.getInstence().showDialog(MainActivity.this, versionInfo);
                            }

                            @Override
                            public void btnCancelClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
                        break;
                }
            }
        });
    }

    private void saveDeviceParams() {
        MobileTerminalInfo mobileTerminalInfo = new MobileTerminalInfo();
        mobileTerminalInfo.setDeviceImei(DeviceUtils.getImei(this));
        mobileTerminalInfo.setLongitude(new BigDecimal(longitude));
        mobileTerminalInfo.setLatitude(new BigDecimal(latitude));
        mobileTerminalInfo.setAndroidVersion(DeviceUtils.getAndroidVersion());
//        mobileTerminalInfo.setBluetoothAddress(DeviceUtils.getBluetoothAddress());
        mobileTerminalInfo.setDeviceMeid(DeviceUtils.getDeviceMeid(this));
        mobileTerminalInfo.setDeviceCpu(DeviceUtils.getCpuName());
        mobileTerminalInfo.setDeviceName(DeviceUtils.getDeviceName());
        mobileTerminalInfo.setDeviceModel(DeviceUtils.getDeviceModel());
        mobileTerminalInfo.setDeviceRam(DeviceUtils.getDeviceRam(this));
        mobileTerminalInfo.setDeviceStorage(DeviceUtils.getDeviceStorage(this));
        mobileTerminalInfo.setWlanMacAddress(DeviceUtils.getMACAddress(this));
        mobileTerminalInfo.setIpAddress(DeviceUtils.getIpAddress(this));
        mobileTerminalInfo.setDeviceResolvingPower(DeviceUtils.getScreenSize(this)[0] + "*" + DeviceUtils.getScreenSize(this)[1]);
        mobileTerminalInfo.setNetworkOperatorName(DeviceUtils.getNetworkOperatorName(this));
        mobileTerminalInfo.setNetworkType(DeviceUtils.getNetworkType(this));
        mobileTerminalInfo.setSerialNumber(DeviceUtils.getSerialNumber());
        mobileTerminalInfo.setPhoneNumber(DeviceUtils.getPhoneNumber(this));
        String str = FastJsonUtil.beanToJsonStr(mobileTerminalInfo);
        mobilePresenter.saveDeviceParams(str);
    }

    private void updateMobileLocation() {
        MobileTerminalInfo mobileTerminalInfo = new MobileTerminalInfo();
        mobileTerminalInfo.setDeviceImei(DeviceUtils.getImei(this));
        mobileTerminalInfo.setLongitude(new BigDecimal(longitude));
        mobileTerminalInfo.setLatitude(new BigDecimal(latitude));
        String str = FastJsonUtil.beanToJsonStr(mobileTerminalInfo);
        mobilePresenter.updateDeviceLocation(str);
    }


    private void initEvent() {
        rbChangeSeaMap.setOnCheckedChangeListener(this);
        rbChangeDituMap.setOnCheckedChangeListener(this);
        rbChangeWeixingMap.setOnCheckedChangeListener(this);
        sbShowShip.setOnCheckedChangeListener(this);
        sb_showShip_yehuo.setOnCheckedChangeListener(this);
        sb_showShip_ke.setOnCheckedChangeListener(this);
        sb_showShip_putonghuo.setOnCheckedChangeListener(this);
        sb_showShip_gongcheng.setOnCheckedChangeListener(this);
        sb_showShip_gongzuo.setOnCheckedChangeListener(this);
        sb_showShip_tuo.setOnCheckedChangeListener(this);
        sb_showShip_yu.setOnCheckedChangeListener(this);
        sb_showShip_qita.setOnCheckedChangeListener(this);

        sbShowThys.setOnCheckedChangeListener(this);
        sbShowZhifaPerson.setOnCheckedChangeListener(this);
        sb_showShip_all_zhifa.setOnCheckedChangeListener(this);
        left_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(3000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (location != null) {
                if (location.getErrorCode() == 0) {
                    Message msg = mHandler.obtainMessage();
                    msg.obj = location;
                    msg.what = Constants.MSG_LOCATION_FINISH;
                    mHandler.sendMessage(msg);
                }
            }
        }
    };


    public boolean isRequestLocation = false;

    @Override
    public void onDeviceParamsSaveSuccess() {
        mHandler.sendEmptyMessageDelayed(Constants.UPDATE_LOCATION, 60 * 1000);
    }

    @Override
    public void backFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    public void showConfirmPlotting() {
        mainFragment.showConfirmPlotting();
    }
    public void dissmissConfirmPlotting() {
        mainFragment.dissmissConfirmPlotting();
    }

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> mainActivityWeakReference;

        MyHandler(MainActivity mainActivity) {
            mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity != null) {
                switch (msg.what) {
                    case Constants.MSG_LOCATION_FINISH:
                        AMapLocation location = (AMapLocation) msg.obj;
                        mainActivity.longitude = PositionUtil.gcj_To_Gps84(location.getLatitude(), location.getLongitude()).getWgLon();
                        mainActivity.latitude = PositionUtil.gcj_To_Gps84(location.getLatitude(), location.getLongitude()).getWgLat();
                        mainActivity.mainFragment.setValues(mainActivity.longitude, mainActivity.latitude, location.getSpeed());
                        mainActivity.winfoDNCView.winfoDNCParams.myLocationPoint = new Point(mainActivity.longitude, mainActivity.latitude);
                        if (mainActivity.winfoDNCView.winfoDNCParams.isLockAngle) {
                            mainActivity.showStates(mainActivity.longitude, mainActivity.latitude, true);
                        } else {
                            mainActivity.showStates(mainActivity.longitude, mainActivity.latitude, false);
                        }
                        if (!mainActivity.isRequestLocation) {
                            mainActivity.saveDeviceParams();
                            mainActivity.isRequestLocation = true;
                        }
                        break;

                    case Constants.MSG_REFRESHAIS:
                        if (mainActivity.winfoDNCView.winfoDNCParams.mapViewInfo.vp.Level >= 14) {
                            mainActivity.aisPresenter.refreshAis(null, false, null);
                        }
                        //在发送一个间隔10s的消息
//                        sendEmptyMessageDelayed(Constants.MSG_REFRESHAIS, 10 * 1000);
                        break;
                    case Constants.MSG_REFRESHALLZHIFA:
                        mainActivity.aisPresenter.refreshZhifa(null, false);
                        //在发送一个间隔10s的消息
//                        sendEmptyMessageDelayed(Constants.MSG_REFRESHALLZHIFA, 10 * 1000);
                        break;
                    case Constants.UPDATE_LOCATION:
                        mainActivity.updateMobileLocation();
                        sendEmptyMessageDelayed(Constants.UPDATE_LOCATION, 60 * 1000);
                        break;
                    case Constants.MSG_LOCATION_START:

                        break;
                    case Constants.MSG_LOCATION_STOP:

                        break;
                }
            }
        }
    }

    /**
     * 显示定位和坐标信息
     *
     * @param bol 是否锁定视角
     */
    private void showStates(double longitude, double latitude, boolean bol) {
        // 设置实时船舶状态背景半透明
        if (bol) {
            winfoDNCView.winfoDNCParams.centerPoint = new Point(longitude, latitude);
            winfoDNCView.changeMyLocation(winfoDNCView.winfoDNCParams.centerPoint);
        } else {
            Point point = new Point(longitude, latitude);
            winfoDNCView.changeMyLocation(point);
        }
        winfoDNCView.refreshMap();
    }

    /**
     * 显示船舶信息
     *
     * @param aisData ais数据
     */
    public void showAisData(Ais aisData) {
        mainFragment.showAisData(aisData);
    }

    /**
     * 显示船舶电话
     */
    @Override
    public void setShipPhone(String msg, TelephoneMmsi data) {
        mainFragment.showShipPhone(data);
    }

    /**
     * 根据MMSI查找电话
     */
    public void showShipPhone(String mmsi) {
        if (PreferenceUtils.getBoolean(this, "islogin", false)) {
            aisPresenter.getPhoneByMmsi(mmsi, false);
        }
    }

    public void refrshAis(Ais aisData) {
        mainFragment.refreshAisData(aisData);
    }

    public void dissmissAisData() {
        mainFragment.dissmissAisData();
    }


    public void showDrawLayout() {//打开侧滑菜单
        mDrawerLayout.openDrawer(Gravity.START);
    }

    public void openDrawLayouy() {//可以侧滑
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        winfoDNCView.refreshMap();
        startOrientationChangeListener();
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /*
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public WinfoDNCView getWinfoDNCView() {
        return winfoDNCView;
    }

    @Override
    public void onCheckedChanged(final CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.rb_layer_ditu://地图
                if (isChecked) {
                    winfoDNCView.SwichLayer(MapLayersType.DZDT);
                    rbChangeSeaMap.setChecked(false);
                    rbChangeWeixingMap.setChecked(false);
                    winfoDNCView.refreshMap();
                }
                break;
            case R.id.rb_layer_haitu://海图
                if (isChecked) {
                    winfoDNCView.SwichLayer(MapLayersType.DZHT);
                    rbChangeDituMap.setChecked(false);
                    rbChangeWeixingMap.setChecked(false);
                    winfoDNCView.refreshMap();
                }
                break;
            case R.id.rb_layer_weixingtu://卫星图
                if (isChecked) {
                    winfoDNCView.SwichLayer(MapLayersType.WXT);
                    rbChangeDituMap.setChecked(false);
                    rbChangeSeaMap.setChecked(false);
                    winfoDNCView.refreshMap();
                }
                break;
            case R.id.sb_showShip://船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis = true;
                    winfoDNCView.isShowShipAis_yehuo = true;
                    winfoDNCView.isShowShipAis_ke = true;
                    winfoDNCView.isShowShipAis_putonghuo = true;
                    winfoDNCView.isShowShipAis_gongcheng = true;
                    winfoDNCView.isShowShipAis_gongzuo = true;
                    winfoDNCView.isShowShipAis_tuo = true;
                    winfoDNCView.isShowShipAis_yu = true;
                    winfoDNCView.isShowShipAis_qita = true;
                    if (sb_showShip_yehuo.isChecked() || sb_showShip_ke.isChecked() || sb_showShip_putonghuo.isChecked()
                            || sb_showShip_gongcheng.isChecked() || sb_showShip_gongzuo.isChecked() || sb_showShip_tuo.isChecked()
                            || sb_showShip_yu.isChecked() || sb_showShip_qita.isChecked()) {
                        sb_showShip_yehuo.setChecked(true);
                        sb_showShip_ke.setChecked(true);
                        sb_showShip_putonghuo.setChecked(true);
                        sb_showShip_gongcheng.setChecked(true);
                        sb_showShip_gongzuo.setChecked(true);
                        sb_showShip_tuo.setChecked(true);
                        sb_showShip_yu.setChecked(true);
                        sb_showShip_qita.setChecked(true);

                    } else {
                        getAisData(compoundButton);
                        sb_showShip_yehuo.setChecked(true);
                        sb_showShip_ke.setChecked(true);
                        sb_showShip_putonghuo.setChecked(true);
                        sb_showShip_gongcheng.setChecked(true);
                        sb_showShip_gongzuo.setChecked(true);
                        sb_showShip_tuo.setChecked(true);
                        sb_showShip_yu.setChecked(true);
                        sb_showShip_qita.setChecked(true);
                    }
                } else {
                    dismissAisData();
                    winfoDNCView.winfoDNCParams.screenAisDatas.clear();
                    winfoDNCView.winfoDNCParams.screenYehuoAis.clear();
                    winfoDNCView.winfoDNCParams.screenKeAis.clear();
                    winfoDNCView.winfoDNCParams.screenPutongAis.clear();
                    winfoDNCView.winfoDNCParams.screenGongchengAis.clear();
                    winfoDNCView.winfoDNCParams.screenGongzuoAis.clear();
                    winfoDNCView.winfoDNCParams.screenTuoAis.clear();
                    winfoDNCView.winfoDNCParams.screenYuAis.clear();
                    winfoDNCView.winfoDNCParams.screenOtherAis.clear();
                }
                break;
            case R.id.sb_showShip_yehuo://液货船船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_yehuo = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenYehuoAis.clear();
                    winfoDNCView.isShowShipAis_yehuo = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_ke://客船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_ke = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenKeAis.clear();
                    winfoDNCView.isShowShipAis_ke = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_putonghuo://普通货船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_putonghuo = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenPutongAis.clear();
                    winfoDNCView.isShowShipAis_putonghuo = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_gongcheng://工程船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_gongcheng = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenGongchengAis.clear();
                    winfoDNCView.isShowShipAis_gongcheng = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_gongzuo://工作船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_gongzuo = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenGongzuoAis.clear();
                    winfoDNCView.isShowShipAis_gongzuo = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_tuo://拖船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_tuo = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }

                } else {
                    winfoDNCView.winfoDNCParams.screenTuoAis.clear();
                    winfoDNCView.isShowShipAis_tuo = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_yu://渔船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_yu = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenYuAis.clear();
                    winfoDNCView.isShowShipAis_yu = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showShip_qita://客船船舶AIS
                if (isChecked) {
                    winfoDNCView.isShowShipAis_qita = true;
                    if (winfoDNCView.isShowShipAis) {
                        winfoDNCView.invalidate();
                    } else {
                        winfoDNCView.isShowShipAis = true;
                        getAisData(compoundButton);
                    }
                } else {
                    winfoDNCView.winfoDNCParams.screenOtherAis.clear();
                    winfoDNCView.isShowShipAis_qita = false;
                    winfoDNCView.invalidate();
                    if (!winfoDNCView.isShowShipAis_yehuo && !winfoDNCView.isShowShipAis_ke
                            && !winfoDNCView.isShowShipAis_putonghuo && !winfoDNCView.isShowShipAis_gongcheng
                            && !winfoDNCView.isShowShipAis_gongzuo && !winfoDNCView.isShowShipAis_tuo
                            && !winfoDNCView.isShowShipAis_yu && !winfoDNCView.isShowShipAis_qita) {
                        dismissAisData();
                    }
                }
                break;
            case R.id.sb_showThys://通航要素
                if (isChecked) {
                    navigableElementsPresenter.getThysData(new ChangeSwitchButtonState() {
                        @Override
                        public void changeState(boolean bol) {
                            compoundButton.setChecked(bol);
                        }
                    }, null, null);
                } else {
                    winfoDNCView.isShowThys = false;
                    winfoDNCView.getNavigableElementsData().clear();
                    winfoDNCView.BoolShowTip();
                    winfoDNCView.invalidate();
                }
                break;
            case R.id.sb_showzhifa_person://执法人员
                if (PreferenceUtils.getBoolean(this, "islogin", false)) {
                    if (isChecked) {
                        zhifaPersonPresenter.getZhifaPersonData(new ChangeSwitchButtonState() {
                            @Override
                            public void changeState(boolean bol) {
                                compoundButton.setChecked(bol);
                            }
                        });
                    } else {
                        winfoDNCView.isShowZhifaPerson = false;
                        winfoDNCView.getZhifaPersonData().clear();
                        winfoDNCView.winfoDNCParams.searchThys = null;
                        winfoDNCView.isShowSearchThys = false;
                        winfoDNCView.BoolShowTip();
                        winfoDNCView.invalidate();
                    }
                } else {
                    ToastUtils.showToast(this, "请登录！");
                    sbShowZhifaPerson.setChecked(false);
                }
                break;
            case R.id.sb_showShip_all_zhifa://执法船舶所有
                if (PreferenceUtils.getBoolean(this, "islogin", false)) {
                    if (isChecked) {
                        winfoDNCView.isShowaAllZhifa = true;
                        getAllZhifaData(compoundButton);
                    } else {
                        winfoDNCView.isShowaAllZhifa = false;
                        winfoDNCView.showaistip = false;
                        winfoDNCView.getAllZhifaData().clear();
                        winfoDNCView.BoolShowTip();
                        mainFragment.dissmissAisData();
                        mHandler.removeMessages(Constants.MSG_REFRESHALLZHIFA);
                        winfoDNCView.invalidate();
                    }
                } else {
                    ToastUtils.showToast(this, "请登录！");
                    sb_showShip_all_zhifa.setChecked(false);
                }
                break;
        }
    }

    private void getAllZhifaData(final CompoundButton compoundButton) {

        //显示加载对话框
        aisPresenter.getAllZhifaData(new ChangeSwitchButtonState() {
            @Override
            public void changeState(boolean bol) {
                compoundButton.setChecked(bol);
            }
        }, true);
//        // 如果屏幕没有移动10秒刷新一次AIS数据
//        mHandler.sendEmptyMessageDelayed(Constants.MSG_REFRESHALLZHIFA, 10 * 1000);
    }

    private void dismissAisData() {
        winfoDNCView.showaistip = false;

        winfoDNCView.isShowShipAis = false;
        winfoDNCView.isShowShipAis_yehuo = false;
        winfoDNCView.isShowShipAis_ke = false;
        winfoDNCView.isShowShipAis_putonghuo = false;
        winfoDNCView.isShowShipAis_gongcheng = false;
        winfoDNCView.isShowShipAis_gongzuo = false;
        winfoDNCView.isShowShipAis_tuo = false;
        winfoDNCView.isShowShipAis_yu = false;
        winfoDNCView.isShowShipAis_qita = false;


        winfoDNCView.getScreenAisData().clear();
        mainFragment.dissmissAisData();
        winfoDNCView.BoolShowTip();
        mHandler.removeMessages(Constants.MSG_REFRESHAIS);
        winfoDNCView.invalidate();
        sbShowShip.setChecked(false);
        sb_showShip_yehuo.setChecked(false);
        sb_showShip_ke.setChecked(false);
        sb_showShip_putonghuo.setChecked(false);
        sb_showShip_gongcheng.setChecked(false);
        sb_showShip_gongzuo.setChecked(false);
        sb_showShip_tuo.setChecked(false);
        sb_showShip_yu.setChecked(false);
        sb_showShip_qita.setChecked(false);
    }

    private void getAisData(final CompoundButton compoundButton) {
        if (winfoDNCView.getLevel() > 13) {//14-18
            //显示加载对话框
            aisPresenter.getShipAisData(new ChangeSwitchButtonState() {
                @Override
                public void changeState(boolean bol) {
                    compoundButton.setChecked(bol);
                }
            }, true, null, null);
            // 如果屏幕没有移动10秒刷新一次AIS数据
            //mHandler.sendEmptyMessageDelayed(Constants.MSG_REFRESHAIS, 10 * 1000);
        } else if (winfoDNCView.getLevel() > 11 && winfoDNCView.getLevel() < 14) {//12 13
            aisPresenter.loadNetAisPoint(null, new ChangeSwitchButtonState() {
                @Override
                public void changeState(boolean bol) {
                    compoundButton.setChecked(bol);
                }
            }, true, null);
        } else {//1-11
            aisPresenter.loadNetAisPoint(null, new ChangeSwitchButtonState() {
                @Override
                public void changeState(boolean bol) {
                    compoundButton.setChecked(bol);
                }
            }, true, null);
        }
    }

    @Override
    public void location() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showToast(this, "please open this location permission");
            return;
        }
        showStates(longitude, latitude, true);
    }

    public void changeImgbtnBg() {
        mainFragment.change();
    }

    @Override
    public void toSearchFragment() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if (searchShipFragment == null) {
            searchShipFragment = new SearchShipFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.hide(mainFragment);
        ft.add(R.id.id_main_frameLayout, searchShipFragment, "searchShipFragment");
        ft.addToBackStack("searchShipFragment");
        ft.commit();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public AisRequestModel getAisRequestModel() {
        AisRequestModel aisRequestModel = new AisRequestModel();
        Point pointScreenStart = winfoDNCView.getPointScreen()[0];
        Point pointScreenEnd = winfoDNCView.getPointScreen()[1];
        String point1 = String.valueOf(pointScreenStart.lon) + "," + pointScreenStart.lat;
        String point2 = String.valueOf(pointScreenEnd.lon) + "," + pointScreenEnd.lat;
        aisRequestModel.setHeight(winfoDNCView.winfoDNCParams.mapViewInfo.vp.Height + "");
        aisRequestModel.setStartPoint(point1);
        aisRequestModel.setStopPoint(point2);
        aisRequestModel.setMinX(winfoDNCView.winfoDNCParams.mapViewInfo.vp.MinX + "");
        aisRequestModel.setMinY(winfoDNCView.winfoDNCParams.mapViewInfo.vp.MinY + "");
        aisRequestModel.setPageSize("2000");
        aisRequestModel.setSpanPerPixel(winfoDNCView.winfoDNCParams.mapViewInfo.vp.SpanPerPixel + "");
        aisRequestModel.setLevel(winfoDNCView.winfoDNCParams.mapViewInfo.vp.Level + "");
        return aisRequestModel;
    }

    @Override
    public void setAisData(List<Ais> aisDatas, String typeCode, String shipTypeCode) {
        winfoDNCView.addScreenAisDatas(aisDatas);
        // 如果屏幕没有移动10秒刷新一次AIS数据
        mHandler.sendEmptyMessageDelayed(Constants.MSG_REFRESHAIS, 10 * 1000);
    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {
        winfoDNCView.addAllZhifaDatas(aisData);
        // 如果屏幕没有移动10秒刷新一次AIS数据
        mHandler.sendEmptyMessageDelayed(Constants.MSG_REFRESHALLZHIFA, 10 * 1000);
    }

    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {
        winfoDNCView.isShowThys = true;
        winfoDNCView.addNavigationElementDatas(navigationElementsData);
    }

    @Override
    public void setIsShowAis(boolean bol) {
        winfoDNCView.isShowShipAis = bol;
        winfoDNCView.isShowShipAis_yehuo = bol;
        winfoDNCView.isShowShipAis_ke = bol;
        winfoDNCView.isShowShipAis_putonghuo = bol;
        winfoDNCView.isShowShipAis_gongcheng = bol;
        winfoDNCView.isShowShipAis_gongzuo = bol;
        winfoDNCView.isShowShipAis_tuo = bol;
        winfoDNCView.isShowShipAis_yu = bol;
        winfoDNCView.isShowShipAis_qita = bol;
        sbShowShip.setChecked(false);
        sb_showShip_yehuo.setChecked(false);
        sb_showShip_ke.setChecked(false);
        sb_showShip_putonghuo.setChecked(false);
        sb_showShip_gongcheng.setChecked(false);
        sb_showShip_gongzuo.setChecked(false);
        sb_showShip_tuo.setChecked(false);
        sb_showShip_yu.setChecked(false);
        sb_showShip_qita.setChecked(false);
    }

    @Override
    public void setIsShowAllZhifa(boolean bol) {
        winfoDNCView.isShowaAllZhifa = bol;
        sb_showShip_all_zhifa.setChecked(false);
    }

    @Override
    public void setIsShowThys(boolean bol) {
        winfoDNCView.isShowThys = bol;
        sbShowThys.setChecked(false);
    }

    @Override
    public void setIsShowZhifaPerson(boolean bol) {
        winfoDNCView.isShowZhifaPerson = bol;
        sbShowZhifaPerson.setChecked(false);

    }

    @Override
    public Dialog getDialog() {
        return DialogUtils.createLoadingDialog(this, "加载中...");
    }

    @Override
    public void setZhifaPerson(List<MobileTerminalInfo2> list) {
        winfoDNCView.isShowZhifaPerson = true;
        winfoDNCView.addZhifaPersonDatas(list);
    }

    @Override
    public void loginExpired(String msg) {
//        LoginUtils.loginOutShowDialog(this, msg, msg);
        LoginUtils.loginOut(this);
    }


    @Override
    public void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void locationSearchShip(Ais aisData) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        winfoDNCView.type = 1;
        winfoDNCView.isShowSearchAis = true;
        winfoDNCView.locationShip(aisData);
        openDrawLayouy();
    }

    @Override
    public void locationSearchThys(NavigableElementsData navigableElementsData) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        winfoDNCView.isShowSearchThys = true;
        winfoDNCView.locationThsy(navigableElementsData);
        openDrawLayouy();
    }

    //附近点击切换页面
    @Override
    public void onNearbyBtnClick(double longitude, double latitude) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if (nearByListFragment == null) {
            nearByListFragment = new NearByListFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(mainFragment);

        Bundle bundle = new Bundle();
        bundle.putDouble("longitude", longitude);
        bundle.putDouble("latitude", latitude);
        nearByListFragment.setArguments(bundle);
        ft.add(R.id.id_main_frameLayout, nearByListFragment, "nearByListFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    //附近更多点击切换页面
    @Override
    public void toMoreFragment(List<ThysData> thysData, List<ShipData> shipData, int i, double longitude, double longitude1) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if (nearByListMoreFragment == null) {
            nearByListMoreFragment = new NearByListMoreFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(nearByListFragment);

        Bundle bundle = new Bundle();
        bundle.putDouble("longitude", longitude);
        bundle.putDouble("latitude", latitude);
        bundle.putInt("type", i);
        if (i == 1) {
            bundle.putSerializable("thysList", (Serializable) thysData);
        } else if (i == 2) {
            bundle.putSerializable("shipList", (Serializable) shipData);
        }
        nearByListMoreFragment.setArguments(bundle);
        ft.add(R.id.id_main_frameLayout, nearByListMoreFragment, "nearByListMoreFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    //附近船舶Item点击切换页面
    @Override
    public void onShipItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String shipCode, String shipTypeNameCn) {
        if (nearByItemResultFragment == null) {
            nearByItemResultFragment = new NearByItemResultFragment();
            nearByItemResultFragment.setiGetWinfoDNCView(this);
        }
        if (!nearByItemResultFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(nearByListFragment);
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            bundle.putString("shipTypeNameCn", shipTypeNameCn);
            bundle.putString("shipCode", shipCode);
            winfoDNCView.winfoDNCParams.nearShipData=nearShipData;
//            bundle.putSerializable("aisList", (Serializable) nearShipData);
            nearByItemResultFragment.setArguments(bundle);
            ft.add(R.id.id_main_frameLayout, nearByItemResultFragment, "nearByItemResultFragment");
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //附近通航要素Item点击切换页面
    @Override
    public void onThysItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String typeName, String typeId) {
        if (nearByItemResultFragment == null) {
            nearByItemResultFragment = new NearByItemResultFragment();
            nearByItemResultFragment.setiGetWinfoDNCView(this);
        }
        if (!nearByItemResultFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(nearByListFragment);
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            bundle.putString("typeName", typeName);
            bundle.putString("typeId", typeId);
//            bundle.putSerializable("nearthysData", (Serializable) nearthysData);
            winfoDNCView.winfoDNCParams.thysjsonData=nearthysData;
            nearByItemResultFragment.setArguments(bundle);
            ft.add(R.id.id_main_frameLayout, nearByItemResultFragment, "nearByItemResultFragment");
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //附近更多页面船舶Item点击切换页面
    @Override
    public void onShipMoreItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String shipCode, String shipTypeNameCn) {
        if (nearByItemResultFragment == null) {
            nearByItemResultFragment = new NearByItemResultFragment();
            nearByItemResultFragment.setiGetWinfoDNCView(this);
        }
        if (!nearByItemResultFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(nearByListMoreFragment);
            ft.hide(nearByListFragment);
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            bundle.putString("shipTypeNameCn", shipTypeNameCn);
            bundle.putString("shipCode", shipCode);
//            bundle.putSerializable("aisList", (Serializable) nearShipData);
            winfoDNCView.winfoDNCParams.nearShipData=nearShipData;
            nearByItemResultFragment.setArguments(bundle);
            ft.add(R.id.id_main_frameLayout, nearByItemResultFragment, "nearByItemResultFragment");
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //附近更多页面通航要素Item点击切换页面
    @Override
    public void onThysMoreItemClick(List<Ais> nearShipData, List<NavigableElementsData> nearthysData, int type, String typeName, String typeId) {
        if (nearByItemResultFragment == null) {
            nearByItemResultFragment = new NearByItemResultFragment();
            nearByItemResultFragment.setiGetWinfoDNCView(this);
        }
        if (!nearByItemResultFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(nearByListMoreFragment);
            ft.hide(nearByListFragment);
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            bundle.putString("typeName", typeName);
            bundle.putString("typeId", typeId);
//            bundle.putSerializable("nearthysData", (Serializable) nearthysData);
            winfoDNCView.winfoDNCParams.thysjsonData=nearthysData;
            nearByItemResultFragment.setArguments(bundle);
            ft.add(R.id.id_main_frameLayout, nearByItemResultFragment, "nearByItemResultFragment");
            ft.addToBackStack(null);
            ft.commit();
        }
    }
    /**
     * 点击附近通航要素弹出弹窗
     */
    public void showNearThys(NavigableElementsData data) {
        nearByItemResultFragment.showNearThys(data);
    }

//    /**
//     * 点击附近船舶弹出AIS
//     */
//    public void showNearShipAis(Ais data) {
//        nearByItemResultFragment.showNearShipAis(data);
//        ToastUtils.showToast(this, data.getCM());
////        mainFragment.showAisData(data);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 8001 && resultCode == Activity.RESULT_OK) {
//            List<String> photos = null;
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            }
//            mainFragment.setPhoto_paths(photos, 8001);
//        } else if (requestCode == 8002 && resultCode == Activity.RESULT_OK) {
//            List<String> photos = null;
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            }
//            mainFragment.setPhoto_paths(photos, 8002);
//        }
    }

    private void startOrientationChangeListener() {
        OrientationEventListener mOrientationListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int rotation) {
                int status = 0;
                try {
                    status = android.provider.Settings.System.getInt(MainActivity.this.getContentResolver(),
                            android.provider.Settings.System.ACCELEROMETER_ROTATION);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (status == 0) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    return;
                }
                if (((rotation >= 0) && (rotation <= 45)) || (rotation >= 315) || ((rotation >= 135) && (rotation <= 225))) {//portrait
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (((rotation > 45) && (rotation < 135)) || ((rotation > 225) && (rotation < 315))) {//landscape
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        };
        mOrientationListener.enable();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        winfoDNCView.winfoDNCParams.setDp();
        winfoDNCView.refreshMap();
//        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
//        int ori = mConfiguration.orientation; //获取屏幕方向
//        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
//            //横屏
//            if (nearByDataFragment != null) {
//                nearByDataFragment.setPopHorizontal();
//            }
//        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
//            //竖屏
//            if (nearByDataFragment != null) {
//                nearByDataFragment.setPopHorizontal();
//            }
//        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            return super.onKeyDown(keyCode, event);
        }
        if (mainFragment.getStartStopTimePick().isShowing()) {
            mainFragment.getStartStopTimePick().dismiss();
            return false;
        }

        if (mainFragment.getBehavior().getState() == BottomSheetBehavior.STATE_HIDDEN) {
            exit();
            return false;
        } else if (mainFragment.getBehavior().getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mainFragment.getBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
            return true;
        } else if (mainFragment.getBehavior().getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mainFragment.getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showToast(this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            PreferenceUtils.setString(MainActivity.this, "last_lat", latitude + "");
            PreferenceUtils.setString(MainActivity.this, "last_lon", longitude + "");
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(Constants.UPDATE_LOCATION);
            mHandler.removeMessages(Constants.MSG_REFRESHAIS);
            mHandler.removeMessages(Constants.MSG_REFRESHALLZHIFA);
            mHandler.removeCallbacksAndMessages(null);
        }
        stopLocation();
        destroyLocation();
    }
}
