package com.winfo.dnc.sdk;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.winfo.dnc.sdk.GestureListener.OnClickListenner;
import com.winfo.dnc.sdk.utils.AISUtils;
import com.winfo.dnc.sdk.utils.TransformUtil;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.mvp.nearby.view.CustomTarget;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalHistory;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.UIUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 闻迅电子海图控件
 *
 * @author King
 */
public class WinfoDNCView extends ViewGroup implements OnClickListener, OnClickListenner, IMapLayerView {
    // 所在上下文
    public Context m_context;
    // 电子海图参数
    public WinfoDNCParams winfoDNCParams;
    // 开始拖动的屏幕坐标点X
    private double dragStartX;
    // 开始拖动的屏幕坐标点Y
    public double dragEndX;
    // 停止拖动的屏幕坐标点X
    private double dragStartY;
    // 停止拖动的屏幕坐标点Y
    public double dragEndY;
    // 沿X轴方向移动的位移
    public double dragX;
    // 沿Y轴方向移动的位移
    public double dragY;
    // 0:不变;1:放大;2:缩小;
    public int isZoom = 0;
    public int layerType = 1;
    public boolean isAddPlottingPoint = false;//是否新增标绘点
    public boolean isEditPlottingPoint = false;//是否在编辑标绘点

    public boolean isShowShipAis = false;// 是否显示船舶动态
//    public boolean isShowShipAis_zhifa = false;// 是否只显示执法船


//    public boolean isShowShipAis_ke = false;// 是否只显示客船
//    public boolean isShowShipAis_gaosu = false;//是否显示高速船
//    public boolean isShowShipAis_huo = false;// 是否只显示货船
//    public boolean isShowShipAis_wei = false;// 是否只显示危险船
//    public boolean isShowShipAis_qita = false;// 是否只显示其他船

    public boolean isShowShipAis_yehuo = false;// 是否只显示液货船
    public boolean isShowShipAis_ke = false;// 是否只显示客船
    public boolean isShowShipAis_putonghuo = false;// 是否只显示普通货船
    public boolean isShowShipAis_gongcheng = false;// 是否只显示工程船
    public boolean isShowShipAis_gongzuo = false;// 是否只显示工作船
    public boolean isShowShipAis_tuo = false;// 是否只显示拖船
    public boolean isShowShipAis_yu = false;// 是否只显示渔船
    public boolean isShowShipAis_qita = false;// 是否只显示其他船


    public boolean isShowaAllZhifa;// 是否只显示所有执法船

    public boolean isShowZhifaTrajectory = false;// 执法轨迹
    // 是否显示长按 定位的点
    public boolean showLongPressPoint = false;
    public boolean isShowThys = false;// 是否显示通航要素
    public boolean isShowocationFrame;// 是否显示定位的红色边框
    public boolean isShowShipTrack = false;// 查询出的轨迹
    public String SZ_guiji = "";//查询轨迹时，需要船舶类型来画最后一个点的船舶图标
    public boolean isOpenShipTrack = false;

    public boolean isShowZhifaPerson = false;// 是否显示执法人员
    public static boolean isShowNearby = false;
    public boolean isShowSearchAis = false;
    public boolean isShowSearchThys = false;

    // 是否显示搜索ais tip
    public boolean showSearchtip = true;

    public boolean showaistip = false;
    public boolean showais = false;
    private Paint mPaint;
    private Bitmap shipBitmapIcon;
    public int type;
    private GestureDetector mDetector;// 手势识别
    GestureListener simpleGestureListener;
    private Matrix matrixScale = new Matrix();
    private Bitmap myLocalBitmap;

//    private Bitmap iconGaosuShip;
//    private Bitmap iconHuoShip;
//    private Bitmap iconKeShip;
//    private Bitmap iconWxShip;
//    private Bitmap iconGwcShip;

    private Bitmap iconGwcShip;

    private Bitmap iconKeShip;
    private Bitmap iconPuTongHuoShip;
    private Bitmap iconYeHuoShip;
    private Bitmap iconGongChengShip;
    private Bitmap iconGongZuoShip;
    private Bitmap iconTuoShip;
    private Bitmap iconYuShip;
    private Bitmap iconOtherShip;
    private Bitmap iconLxShip;
//    private Bitmap fishShip;

    //    private Bitmap iconGaosuShip_50;
//    private Bitmap iconGaosuShip_50_100;
//    private Bitmap iconGaosuShip_100_200;
//    private Bitmap iconGaosuShip_200_300;
//    private Bitmap iconGaosuShip_300;
//
//    private Bitmap iconHuoShip_50;
//    private Bitmap iconHuoShip_50_100;
//    private Bitmap iconHuoShip_100_200;
//    private Bitmap iconHuoShip_200_300;
//    private Bitmap iconHuoShip_300;
//
//    private Bitmap iconKeShip_50;
//    private Bitmap iconKeShip_50_100;
//    private Bitmap iconKeShip_100_200;
//    private Bitmap iconKeShip_200_300;
//    private Bitmap iconKeShip_300;
//
//    private Bitmap iconWxShip_50;
//    private Bitmap iconWxShip_50_100;
//    private Bitmap iconWxShip_100_200;
//    private Bitmap iconWxShip_200_300;
//    private Bitmap iconWxShip_300;
//
//    private Bitmap iconOtherShip_50;
//    private Bitmap iconOtherShip_50_100;
//    private Bitmap iconOtherShip_100_200;
//    private Bitmap iconOtherShip_200_300;
//    private Bitmap iconOtherShip_300;
    public String plotting_point_type = "1";//1其他 2沉船 3溢油 4事故
    public boolean is_select_plotting_point = false;

    private Bitmap longPressIcon;// 长按点的图标
    private Bitmap plotting_point_other;//标绘其他点
    private Bitmap plotting_point_wreck;//标绘沉船点
    private Bitmap plotting_point_oil;//标绘溢油点
    private Bitmap plotting_point_accident;//标绘事故点
    private Bitmap ne0001000004;
    private Bitmap ne0001000005;
    private Bitmap ne0001000006;
    private Bitmap ne0001000007;
    private Bitmap ne0001000008;
    private Bitmap ne0001000009;
    private Bitmap ne0002000002;
    private Bitmap ne0002000003;
    private Bitmap ne0002000004;
    private Bitmap ne0002000005;
    private Bitmap ne0002000006;
    private Bitmap ne0002000007;
    private Bitmap ne0002000008;
    private Bitmap ne0002000009;
    private Bitmap ne0002000010;
    private Bitmap ne0002000011;
    private Bitmap ne0002000012;
    private Bitmap ne0002000013;
    private Bitmap ne0002000014;
    private Bitmap ne0002000015;
    private Bitmap ne0002000016;
    private Bitmap ne0002000017;
    private Bitmap ne0002000020;
    private Bitmap ne0002000021;
    private Bitmap ne0002000022;
    private Bitmap ne0002000023;
    private Bitmap ne0002000024;
    private Bitmap ne0002000025;
    private Bitmap ne0002000026;
    private Bitmap ne0002000027;
    private Bitmap ne0002000028;
    private Bitmap ne0002000029;
    private Bitmap ne0002000030;
    private Bitmap ne0002000031;
    private Bitmap ne0002000032;
    private Bitmap ne0002000033;
    private Bitmap ne0002000034;
    private Bitmap ne0002000035;
    private Bitmap ne0002000036;
    private Bitmap ne0002000037;
    private Bitmap ne0003000001;
    private Bitmap ne0003000002;
    private Bitmap ne0003000003;
    private Bitmap ne0003000004;
    private Bitmap ne0003000005;
    private Bitmap ne0003000006;
    private Bitmap ne0003000007;
    private Bitmap ne0004000001;
    private Bitmap ne0004000002;
    private Bitmap ne0004000003;
    private Bitmap ne0004000004;
    private Bitmap ne0004000005;
    private Bitmap ne0004000006;
    private Bitmap ne0004000007;
    private Bitmap ne0004000008;
    private Bitmap ne0004000009;
    private Bitmap ne0004000010;
    private Bitmap ne0004000011;
    private Bitmap ne0004000012;
    private Bitmap ne0004000013;
    private Bitmap ne0004000014;
    private Bitmap ne0004000015;
    private Bitmap ne0004000016;
    private Bitmap ne0005000001;
    private Bitmap ne0005000002;
    private Bitmap ne0005000003;
    private Bitmap ne0005000004;
    private Bitmap ne0005000005;
    private Bitmap ne0005000006;
    private Bitmap ne0005000007;
    private Bitmap ne0005000008;

    private Resources mRes;
    private Bitmap self_icon;
    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG
            | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
            | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
            | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    private AisPresenter aisPresenter;
    public View shipTipView, thysTipView, stationRTipView, weatherTipView,
            OceanWeatherTipView, CityWeatherTipView, stationITipView,
            stationNTipView, stationBTipView, trackTipView;
    private TextView tvShipName, tvMMSI, tvHxzt, tvHS, tvUpdateTime;
    private TextView tvThysName, tvThysType, tvThysJg;


    int aisWidth;
    int aisHeight;
    Bitmap mBitmapRotate;

    Bitmap mBitmapZhifaRotate;
    Bitmap mZhifaPerson;
    Paint forecastPaint = null;
    // Matrix mMatrix = new Matrix();
    //Point pointOfScreen;
    //Point pointForecastOfScreen;
    public boolean isOnFling = false;
    public boolean isTaskShip = false;
    public boolean isShipMapActivity = true;
    public boolean isShowNearbyShips = false;//是否显示附近船舶
    public boolean isShowNearbyThys = false;//是否显示附近要素
    private List<Bitmap> bitmapIconsSelect = new ArrayList<>();
    private List<Bitmap> bitmapIcons = new ArrayList<>();

    public WinfoDNCView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_context = context;
        mRes = getResources();
        //  初始化view
        initView();
        initBitmap();
        // 初始化公用的bitmap对象
//        initBitmap();

        // 初始化护画笔
        paintScaleLine.setColor(Color.BLACK);
        paintScaleLine.setAntiAlias(true);
        paintScaleLine.setStrokeWidth(3);

        paintScaleText.setColor(Color.BLACK);
        paintScaleText.setTextSize(28);
        paintScaleText.setStrokeWidth(3);
        paintScaleText.setAntiAlias(true);
        aisPaint.setColor(Color.YELLOW);

        // 设置手势监听事件
        simpleGestureListener = new GestureListener(this, m_context);
        mDetector = new GestureDetector(context, simpleGestureListener);
        /**
         * 给船舶 通航要素 设置点击事件的监听
         */
        simpleGestureListener.setOnClickListenner(this);
        setFocusable(true);
        setLongClickable(true);
        winfoDNCParams = new WinfoDNCParams(context);
        winfoDNCParams.nearbyPoint = winfoDNCParams.centerPoint;
        aisPresenter = new AisPresenter(this);
        shipBitmapIcon = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_gaosu);
        mPaint = new Paint();
    }

    private void initBitmap() {
        mZhifaPerson = BitmapFactory.decodeResource(mRes, R.mipmap.others);

        iconKeShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_ke);
        iconPuTongHuoShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_huo);
        iconYeHuoShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_yehuo);
        iconGongChengShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_gongcheng);
        iconGongZuoShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_gongzuo);
        iconTuoShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_tuo);
        iconYuShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_yuchuan);
        iconOtherShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_other);


        iconGwcShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_gongwu);


        self_icon = BitmapFactory.decodeResource(mRes, R.mipmap.self_guiji);
        iconLxShip = BitmapFactory.decodeResource(mRes, R.mipmap.icon_ship_lx);
        longPressIcon = BitmapFactory.decodeResource(mRes, R.mipmap.b_poi_real);
        plotting_point_other = BitmapFactory.decodeResource(mRes, R.mipmap.spot_other);
        plotting_point_oil = BitmapFactory.decodeResource(mRes, R.mipmap.spot_oil);//标绘溢油点
        plotting_point_wreck = BitmapFactory.decodeResource(mRes, R.mipmap.spot_wreck);//标绘沉船点
        plotting_point_accident = BitmapFactory.decodeResource(mRes, R.mipmap.spot_accident);//标绘事故点

        ne0001000004 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0001000004);
        ne0001000005 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0001000005);
        ne0001000006 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0001000006);
        ne0001000007 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0001000007);
        ne0001000008 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0001000008);
        ne0001000009 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0001000009);
        ne0002000002 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000002);
        ne0002000003 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000003);
        ne0002000004 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000004);
        ne0002000005 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000005);
        ne0002000006 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000006);
        ne0002000007 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000007);
        ne0002000008 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000008);
        ne0002000009 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000009);
        ne0002000010 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000010);
        ne0002000011 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000011);
        ne0002000012 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000012);
        ne0002000013 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000013);
        ne0002000014 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000014);
        ne0002000015 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000015);
        ne0002000016 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000016);
        ne0002000017 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000017);
        ne0002000020 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000020);
        ne0002000021 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000021);
        ne0002000022 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000022);
        ne0002000023 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000023);
        ne0002000024 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000024);
        ne0002000025 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000025);
        ne0002000026 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000026);
        ne0002000027 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000027);
        ne0002000028 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000028);
        ne0002000029 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000029);
        ne0002000030 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000030);
        ne0002000031 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000031);
        ne0002000032 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000032);
        ne0002000033 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000033);
        ne0002000034 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000034);
        ne0002000035 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000035);
        ne0002000036 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000036);
        ne0002000037 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0002000037);
        ne0003000001 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000001);
        ne0003000002 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000002);
        ne0003000003 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000003);
        ne0003000004 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000004);
        ne0003000005 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000005);
        ne0003000006 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000006);
        ne0003000007 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0003000007);
        ne0004000001 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000001);
        ne0004000002 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000002);
        ne0004000003 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000003);
        ne0004000004 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000004);
        ne0004000005 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000005);
        ne0004000006 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000006);
        ne0004000007 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000007);
        ne0004000008 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000008);
        ne0004000009 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000009);
        ne0004000010 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000010);
        ne0004000011 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000011);
        ne0004000012 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000012);
        ne0004000013 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000013);
        ne0004000014 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000014);
        ne0004000015 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000015);
        ne0004000016 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0004000016);
        ne0005000001 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000001);
        ne0005000002 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000002);
        ne0005000003 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000003);
        ne0005000004 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000004);
        ne0005000005 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000005);
        ne0005000006 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000006);
        ne0005000007 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000007);
        ne0005000008 = BitmapFactory.decodeResource(mRes, R.mipmap.ne0005000008);

        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_1));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_2));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_3));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_4));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_5));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_6));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_7));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_8));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_9));
        bitmapIcons.add(BitmapFactory.decodeResource(mRes, R.mipmap.lp_10));

        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_1));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_2));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_3));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_4));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_5));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_6));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_7));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_8));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_9));
        bitmapIconsSelect.add(BitmapFactory.decodeResource(mRes, R.mipmap.l_10));

        aisWidth = iconKeShip.getWidth();

        aisHeight = iconKeShip.getHeight();
        if (forecastPaint == null) {
            forecastPaint = new Paint();
        }
        forecastPaint.setAntiAlias(true);
        forecastPaint.setColor(Color.GRAY);
        forecastPaint.setStrokeWidth(2);

    }

    @SuppressLint("InflateParams")
    private void initView() {

        shipTipView = LayoutInflater.from(m_context).inflate(R.layout.ship_tip, null);
        thysTipView = LayoutInflater.from(m_context).inflate(R.layout.thys_tip, null);
        stationRTipView = LayoutInflater.from(m_context).inflate(R.layout.station_rtip, null);
        stationNTipView = LayoutInflater.from(m_context).inflate(R.layout.station_ntip, null);
        stationITipView = LayoutInflater.from(m_context).inflate(R.layout.station_itip, null);
        stationBTipView = LayoutInflater.from(m_context).inflate(R.layout.station_btip, null);

        weatherTipView = LayoutInflater.from(m_context).inflate(R.layout.weather_tip, null);
        CityWeatherTipView = LayoutInflater.from(m_context).inflate(R.layout.cityweather_tip, null);
        OceanWeatherTipView = LayoutInflater.from(m_context).inflate(R.layout.ocean_tip, null);

        trackTipView = LayoutInflater.from(m_context).inflate(R.layout.track_tip, null);

        tvShipName = shipTipView.findViewById(R.id.tv_shipName);
        tvMMSI = shipTipView.findViewById(R.id.tv_mmsi);
        tvHxzt = shipTipView.findViewById(R.id.tv_hxzt);
        tvHS = shipTipView.findViewById(R.id.tv_hs);
        tvUpdateTime = shipTipView.findViewById(R.id.tv_updatetime);

        tvThysName = thysTipView.findViewById(R.id.tv_thysName);
        tvThysType = thysTipView.findViewById(R.id.tv_thysType);
        tvThysJg = thysTipView.findViewById(R.id.tv_thysjg);
    }

    private static final int NONE = 0; // 初始状态
    private static final int DRAG = 1; // 拖动
    private static final int ZOOM = 2; // 缩放
    private int mode = NONE; // 当前事件
    private float oldDist;
    private PointF startPoint = new PointF();
    private PointF middlePoint = new PointF();
    private Paint paintScaleLine = new Paint();// 比例尺的线画笔
    private Paint paintScaleText = new Paint();// 比例尺的文本画笔
    Paint paint = new Paint();
    private Paint aisPaint = new Paint();// ais数据的画笔

    public void getAisDate() {
        if (isShowShipAis && isOnFling) {
            if (winfoDNCParams.level > 13) {
                //不显示加载对话框
                aisPresenter.getShipAisData(null, false, null, null);
                isOnFling = false;
            } else if (winfoDNCParams.level > 11 && getLevel() < 14) {
                aisPresenter.get_12_13_netPoints(null, false);
                isOnFling = false;
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: // 手指按下
                dragStartX = event.getX();
                dragStartY = event.getY();
                startPoint.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_MOVE:

//                if(is_select_plotting_point&&winfoDNCParams.plottingPoint!=null){
//                    Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,winfoDNCParams.plottingPoint.lon,winfoDNCParams.plottingPoint.lat);
//                    if (dragStartX > point.X - 120 && dragStartX < point.X + 120 && dragStartY > point.Y - 120 && dragStartY < point.Y + 120) {
//                        winfoDNCParams.plottingPoint = winfoDNCParams.basicAlgorithm.screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp, event.getX(), event.getY());
//                        refreshMap();
//                        requestLayout();
//                    }
//                }

                if (mode == ZOOM) { // 缩放
                    float newDist = spacing(event);
                    if (newDist - oldDist > 10f || oldDist - newDist > 10f) {
                        float scale = newDist / oldDist;
                        winfoDNCParams.basicAlgorithm.chengae(scale);
                        refreshMap();
                        oldDist = newDist;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mode == DRAG) {
                    float moveX = (event.getX() - startPoint.x);
                    float moveY = (event.getY() - startPoint.y);
                    if (Math.abs(moveX) > 200 || Math.abs(moveY) > 200) {
                        dragEndX = (double) event.getX();
                        dragEndY = (double) event.getY();
                        if (isZoom == 0) {
                            if (getDistance((int) dragStartX, (int) dragStartY, (int) dragEndX, (int) dragEndY) > 50) {
                                if (isShowShipAis && !isOnFling) {
                                    if (winfoDNCParams.level > 13) {
                                        //不显示加载对话框
                                        aisPresenter.getShipAisData(null, false, null, null);
                                        isOnFling = false;
                                    } else if (winfoDNCParams.level > 11 && getLevel() < 14) {
                                        aisPresenter.getShipAisData(null, false, null, null);
                                        isOnFling = false;
                                    }
                                }
                            }
                        }
                        refreshMap();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event); // 如果两点距离大于10 多点模式
                if (oldDist > 100f) {
                    midPoint(middlePoint, event);
                    mode = ZOOM;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    // 两点中点
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    public void BoolShowTip() {
        View v = getChildAt(0);
        removeView(v);
        isShowocationFrame = false;
    }

    /**
     * 重新加载地图 并画出
     */
    public void refreshMap() {

        if (Math.abs(dragX) > 5 || Math.abs(dragY) > 5) {
            winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                    .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp,
                            winfoDNCParams.screenCenterPoint.X - dragX,
                            winfoDNCParams.screenCenterPoint.Y - dragY);
        }
        dragX = 0;
        dragY = 0;
        winfoDNCParams.mapViewInfo = winfoDNCParams.basicAlgorithm.calculateViewInfo(0, winfoDNCParams.dp.screenWidth, winfoDNCParams.dp.screenHeight, winfoDNCParams.centerPoint.lon, winfoDNCParams.centerPoint.lat, winfoDNCParams.level);

        switch (layerType) {
            case MapLayersType.DZHT://http://124.193.117.132/
                for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
                    String TilePath = "http://www.ais.msa.gov.cn/MapService?service=wmts&request=gettile&tilematrixset=advsearoad&tilematrix="
                            + winfoDNCParams.level
                            + "&tilerow="
                            + tf.Y
                            + "&tilecol="
                            + tf.X
                            + "&format=image/png&layer=default&style=default&version=1.0.0";
                    MapTileDecoderHttp.decode(TilePath, new TileLoadingListener(tf,
                            this, 0));
//                   MapTileDecoderHttp.loadBitmap(m_context , TilePath , new TileLoadingListener2(tf ,this , 0));
                }
                break;

            case MapLayersType.DZDT:
                for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
                    String TilePath = "http://t7.tianditu.com/DataServer?T=vec_w&x="
                            + tf.X + "&y=" + tf.Y + "&l=" + winfoDNCParams.level;
                    MapTileDecoderHttp.decode(TilePath, new TileLoadingListener(tf,
                            this, 0));
                }
                for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
                    String TilePath = "http://t7.tianditu.com/DataServer?T=cva_w&x="
                            + tf.X + "&y=" + tf.Y + "&l=" + winfoDNCParams.level;
                    MapTileDecoderHttp.decode(TilePath, new TileLoadingListener(tf,
                            this, 1));
                }
                break;
            case MapLayersType.WXT:
                for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
                    String TilePath = "http://mt3.google.cn/vt/lyrs=s&x=" + tf.X
                            + "&y=" + tf.Y + "&z=" + winfoDNCParams.level;
                    MapTileDecoderHttp.decode(TilePath, new TileLoadingListener(tf,
                            this, 0));
                }
                for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
                    String TilePath = "http://t7.tianditu.com/DataServer?T=cva_w&x="
                            + tf.X + "&y=" + tf.Y + "&l=" + winfoDNCParams.level;
                    MapTileDecoderHttp.decode(TilePath, new TileLoadingListener(tf,
                            this, 1));
                }
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void dispatchDraw(Canvas canvas) {

        for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
            Rect dst = new Rect();
            dst.left = (int) tf.OffSetX;
            dst.top = (int) tf.OffSetY;
            if (tf.bitmap != null) {
                dst.right = (int) (tf.OffSetX + tf.bitmap.getWidth()
                        * winfoDNCParams.basicAlgorithm.bitmapScale);
                dst.bottom = (int) (tf.OffSetY + tf.bitmap.getHeight()
                        * winfoDNCParams.basicAlgorithm.bitmapScale);
                canvas.drawBitmap(tf.bitmap, null, dst, null);
            }
        }

        canvas.saveLayerAlpha(0, 0, winfoDNCParams.dp.screenWidth,
                winfoDNCParams.dp.screenHeight, 255, LAYER_FLAGS);
        for (TileInfo tf : winfoDNCParams.mapViewInfo.TileInfoList) {
            if (tf.bitmap2 != null) {
                Rect dst = new Rect();
                dst.left = (int) tf.OffSetX;
                dst.top = (int) tf.OffSetY;
                dst.right = (int) (tf.OffSetX + tf.bitmap2.getWidth()
                        * winfoDNCParams.basicAlgorithm.bitmapScale);
                dst.bottom = (int) (tf.OffSetY + tf.bitmap2.getHeight()
                        * winfoDNCParams.basicAlgorithm.bitmapScale);
                canvas.drawBitmap(tf.bitmap2, null, dst, null);
            }
        }
        canvas.restore();
        int flag = 0;
        /*------------------------------计算比例尺-------------------------------------------------------------------*/
        int stx = UIUtils.dip2Px(15);
        int sty = winfoDNCParams.dp.screenHeight - UIUtils.dip2Px(65) - DeviceUtils.getStatusBarHeight(m_context);
        int spx = UIUtils.dip2Px(15) + 100;
        int spy = winfoDNCParams.dp.screenHeight - UIUtils.dip2Px(65) - DeviceUtils.getStatusBarHeight(m_context);
        canvas.drawLine(stx, sty, spx, spy, paintScaleLine);
        canvas.drawLine(stx, sty + 5, stx, sty - 5, paintScaleLine);
        canvas.drawLine(spx, spy + 5, spx, spy - 5, paintScaleLine);

        canvas.drawText(ScaleUtil.getScaleText(m_context, winfoDNCParams.level), stx + (spx - stx) / 8, spy - 4, paintScaleText);

        // ------------------------搜索定位船舶显示出船舶图标的方法---------------------------
        if (winfoDNCParams.searchAisData != null) {
            Point point = new Point();
            Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, winfoDNCParams.searchAisData.getJD(), winfoDNCParams.searchAisData.getWD());
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            Matrix matrix = new Matrix();
            matrix.setRotate((float) winfoDNCParams.searchAisData.getHX(), iconKeShip.getWidth() / 2, iconKeShip.getHeight() / 2);
            int shipWidth = iconKeShip.getWidth();
            int shipHeight = iconKeShip.getHeight();
            Bitmap mBitmapRotate;
            if (type == 1) {
                if (winfoDNCParams.searchAisData.getID().equals("413906172")
                        || winfoDNCParams.searchAisData.getID().equals("413050360")
                        || winfoDNCParams.searchAisData.getID().equals("413050320")
                        || winfoDNCParams.searchAisData.getID().equals("413050340")
                        || winfoDNCParams.searchAisData.getID().equals("413050370")
                        || winfoDNCParams.searchAisData.getID().equals("413050350")
                        || winfoDNCParams.searchAisData.getID().equals("413775379")
                        || winfoDNCParams.searchAisData.getID().equals("413057590")
                        || winfoDNCParams.searchAisData.getID().equals("413905979")
                        || winfoDNCParams.searchAisData.getID().equals("413906171")
                        || winfoDNCParams.searchAisData.getID().equals("413775384")
                        || winfoDNCParams.searchAisData.getID().equals("413050090")
                        || winfoDNCParams.searchAisData.getID().equals("413905978")
                        || winfoDNCParams.searchAisData.getID().equals("413775383")
                        || winfoDNCParams.searchAisData.getID().equals("413050080")
                        || winfoDNCParams.searchAisData.getID().equals("413057550")) {
                    mBitmapRotate = Bitmap.createBitmap(iconGwcShip, 0, 0, iconGwcShip.getWidth(), iconGwcShip.getHeight(), matrix, true);

                } else {
                    mBitmapRotate = getShipBitmap(AISUtils.getShipType(winfoDNCParams.searchAisData.getSZCLX()));
                }

                canvas.drawBitmap(mBitmapRotate, (float) point.X - mBitmapRotate.getWidth() / 2, (float) point.Y - mBitmapRotate.getHeight() / 2, aisPaint);
            } else if (type == 2) {
                mBitmapRotate = Bitmap.createBitmap(iconLxShip, 0, 0, shipWidth, shipHeight, matrix, true);
                canvas.drawBitmap(mBitmapRotate, (float) point.X - mBitmapRotate.getWidth() / 2, (float) point.Y - mBitmapRotate.getHeight() / 2, aisPaint);
            }
        }
        if (isShowSearchAis) {
            onSearchAisClick(winfoDNCParams.searchAisData);
            isShowSearchAis = false;
            isShowocationFrame = true;
        }

        // ------------------------任务船舶定位显示---------------------------
        if (winfoDNCParams.taskAisData != null) {
            Point point = new Point();
            Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, winfoDNCParams.taskAisData.getJD(), winfoDNCParams.taskAisData.getWD());
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            Matrix matrix = new Matrix();
            matrix.setRotate((float) winfoDNCParams.taskAisData.getHX(), iconKeShip.getWidth() / 2, iconKeShip.getHeight() / 2);
            int shipWidth = iconKeShip.getWidth();
            int shipHeight = iconKeShip.getHeight();
            Bitmap mBitmapRotate;
            if (type == 1) {
                if (winfoDNCParams.taskAisData.getID().equals("413906172")
                        || winfoDNCParams.taskAisData.getID().equals("413050360")
                        || winfoDNCParams.taskAisData.getID().equals("413050320")
                        || winfoDNCParams.taskAisData.getID().equals("413050340")
                        || winfoDNCParams.taskAisData.getID().equals("413050370")
                        || winfoDNCParams.taskAisData.getID().equals("413050350")
                        || winfoDNCParams.taskAisData.getID().equals("413775379")
                        || winfoDNCParams.taskAisData.getID().equals("413057590")
                        || winfoDNCParams.taskAisData.getID().equals("413905979")
                        || winfoDNCParams.taskAisData.getID().equals("413906171")
                        || winfoDNCParams.taskAisData.getID().equals("413775384")
                        || winfoDNCParams.taskAisData.getID().equals("413050090")
                        || winfoDNCParams.taskAisData.getID().equals("413905978")
                        || winfoDNCParams.taskAisData.getID().equals("413775383")
                        || winfoDNCParams.taskAisData.getID().equals("413050080")
                        || winfoDNCParams.taskAisData.getID().equals("413057550")) {
                    mBitmapRotate = Bitmap.createBitmap(iconGwcShip, 0, 0, iconGwcShip.getWidth(), iconGwcShip.getHeight(), matrix, true);

                } else {
                    mBitmapRotate = getShipBitmap(AISUtils.getShipType(winfoDNCParams.taskAisData.getSZCLX()));
                }

                canvas.drawBitmap(mBitmapRotate, (float) point.X - mBitmapRotate.getWidth() / 2, (float) point.Y - mBitmapRotate.getHeight() / 2, aisPaint);
            } else if (type == 2) {
                mBitmapRotate = Bitmap.createBitmap(iconLxShip, 0, 0, shipWidth, shipHeight, matrix, true);
                canvas.drawBitmap(mBitmapRotate, (float) point.X - mBitmapRotate.getWidth() / 2, (float) point.Y - mBitmapRotate.getHeight() / 2, aisPaint);
            }
        }

        //--------------------------------------定位搜索出来的通航要素-------------------------------
        if (winfoDNCParams.searchThys != null) {
            Point point = new Point();
            String str = winfoDNCParams.searchThys.getCenterLatLon();
            String[] ll = str.split(",");
            if (ll.length == 2) {
                point.lon = Double.valueOf(ll[0]);
                point.lat = Double.valueOf(ll[1]);
                Class mipmap = R.mipmap.class;
                Field field;
                Bitmap bitmap;
                Bitmap originalBitmap;
                try {
                    String string = winfoDNCParams.searchThys.getTypeIcon();
                    if (!string.equals("")) {
                        String icon = string.substring(0, string.length() - 4);
                        if (icon != null && !icon.equals("")) {
                            field = mipmap.getField(icon);
                            int id = field.getInt(icon);
                            // 通过图标的名字反射
                            originalBitmap = BitmapFactory.decodeResource(mRes, id);
                            matrixScale.setScale(0.7f, 0.7f);
                            bitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrixScale, true);
                            originalBitmap.recycle();
                        } else {
                            bitmap = BitmapFactory.decodeResource(mRes, R.mipmap.traffic_tips);
                        }
                        float width = bitmap.getWidth();
                        float height = bitmap.getHeight();
                        Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);
                        canvas.drawBitmap(bitmap, (float) pointOfScreen.X - width / 2, (float) pointOfScreen.Y - height / 2, paint);
                        bitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (isShowSearchThys) {
            onThysClick(winfoDNCParams.searchThys);
            isShowSearchThys = false;
            isShowocationFrame = true;
        }


        // -----------------------------------定位我的位置----------------------------------------------------
        if (winfoDNCParams.myLocation != null) {
            Point pointOfScreen = winfoDNCParams.basicAlgorithm
                    .coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                            winfoDNCParams.myLocation.lon,
                            winfoDNCParams.myLocation.lat);
            winfoDNCParams.myLocation.X = pointOfScreen.X;
            winfoDNCParams.myLocation.Y = pointOfScreen.Y;

            if (winfoDNCParams.directionDNC == null) {
                winfoDNCParams.myLocation.direction = 0;
            } else {
                winfoDNCParams.myLocation.direction = (float) Integer
                        .parseInt(winfoDNCParams.directionDNC[0]);
            }
            Matrix mMatrix = new Matrix();
            mMatrix.setRotate(winfoDNCParams.myLocation.direction);

            if (myLocalBitmap == null) {
                myLocalBitmap = BitmapFactory.decodeResource(mRes, R.mipmap.me);
            }
            int aisWidth = myLocalBitmap.getWidth();
            int aisHeight = myLocalBitmap.getHeight();
            Bitmap mBitmapRotate = Bitmap.createBitmap(myLocalBitmap, 0, 0,
                    aisWidth, aisHeight, mMatrix, true);
            canvas.drawBitmap(
                    mBitmapRotate,
                    (float) winfoDNCParams.myLocation.X
                            - (mBitmapRotate.getWidth() / 2 + 0.5f),
                    (float) winfoDNCParams.myLocation.Y
                            - (mBitmapRotate.getHeight() / 2 + 0.5f),
                    new Paint());
        }

        // --------------------------------显示长按点------------------------------------
        if (showLongPressPoint) {
            Point pointOfScreen = winfoDNCParams.basicAlgorithm
                    .coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                            winfoDNCParams.nearbyPoint.lon,
                            winfoDNCParams.nearbyPoint.lat);
            canvas.drawBitmap(longPressIcon, (float) pointOfScreen.X - longPressIcon.getWidth() / 2, (float) pointOfScreen.Y
                    - longPressIcon.getHeight(), new Paint());
        }


        if (isShowaAllZhifa && winfoDNCParams.level >= 6) {
            for (Ais point : winfoDNCParams.allZhifaDatas) {
                Matrix mMatrix = new Matrix();
                Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                        winfoDNCParams.mapViewInfo.vp, point.JD,
                        point.WD);
                if (pointOfScreen.X > 0 && pointOfScreen.X < winfoDNCParams.mapViewInfo.Width
                        && pointOfScreen.Y > 0
                        && pointOfScreen.Y < winfoDNCParams.mapViewInfo.Height) {
                    mMatrix.setRotate((float) point.HX, aisWidth / 2, aisHeight / 2);
                    canvas.save();
                    canvas.rotate((float) point.HX, (float) pointOfScreen.X,
                            (float) pointOfScreen.Y);
                    canvas.drawBitmap(iconGwcShip,
                            (float) pointOfScreen.X - iconGwcShip.getWidth() / 2,
                            (float) pointOfScreen.Y - iconGwcShip.getHeight() / 2, paint);
                    canvas.restore();
                }
                // 如果船速大于0才去显示显示预测船舶位置
                if (point.HS > 0) {
                    // 得到1min后船的预测位置
                    drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                }
            }
        }

        // -------------------------显示当前屏幕范围的ais船舶数据--------------------------------------------------------
        if (isShowShipAis && !isOpenShipTrack) {
            flag = 0;
            if (winfoDNCParams.level < 14) {
                // 过滤 ais数据
                Set<String> set = new HashSet<>();
                Point point;
                for (Ais aisPointModel : winfoDNCParams.screenAisDatas) {
                    double JD = aisPointModel.getJD();
                    double WD = aisPointModel.getWD();
                    point = new Point();
                    point.lon = JD;
                    point.lat = WD;
                    Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);
                    // 存入 set集合
                    set.add(pointOfScreen.X + "," + pointOfScreen.Y);
                }
                for (String str : set) {
                    String[] strings = str.split(",");
                    Point pointOfScreenSet = new Point();
                    pointOfScreenSet.X = Double.parseDouble(strings[0]);
                    pointOfScreenSet.Y = Double.parseDouble(strings[1]);
                    canvas.drawCircle((float) pointOfScreenSet.X, (float) pointOfScreenSet.Y, UIUtils.dip2Px(1.5), aisPaint);
                }
                if (showaistip && winfoDNCParams.showAisData != null) {
//                    int aisWidth = iconGaosuShip.getWidth();
//                    int aisHeight = iconGaosuShip.getHeight();
                    Bitmap mBitmapRotate;
                    Paint forecastPaint = null;
//                    Matrix mMatrix = new Matrix();
                    Ais aisData = winfoDNCParams.showAisData;
                    Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    if (pointOfScreen.X > 0
                            && pointOfScreen.X < winfoDNCParams.mapViewInfo.Width
                            && pointOfScreen.Y > 0
                            && pointOfScreen.Y < winfoDNCParams.mapViewInfo.Height) {
                        mBitmapRotate = getShipBitmap(AISUtils.getShipType(aisData.getSZCLX()));
                        canvas.save();
                        canvas.rotate((float) aisData.HX, (float) pointOfScreen.X, (float) pointOfScreen.Y);
                        canvas.drawBitmap(mBitmapRotate,
                                (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                        canvas.restore();

                    }
                    if (aisData.HS > 0) {
                        flag = drawHS(canvas, flag, forecastPaint, aisData, pointOfScreen);
                    }
                }
            } else {
                for (Ais point : winfoDNCParams.screenAisDatas) {
                    Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                            winfoDNCParams.mapViewInfo.vp, point.JD,
                            point.WD);
                    if (pointOfScreen.X > 0 && pointOfScreen.X < winfoDNCParams.mapViewInfo.Width
                            && pointOfScreen.Y > 0
                            && pointOfScreen.Y < winfoDNCParams.mapViewInfo.Height) {
                        mBitmapRotate = getShipBitmap(AISUtils.getShipType(point.getSZCLX()));
                        canvas.save();
                        canvas.rotate((float) point.HX, (float) pointOfScreen.X, (float) pointOfScreen.Y);
                        if (isShowShipAis_yehuo && isShowShipAis_ke && isShowShipAis_putonghuo
                                && isShowShipAis_gongcheng && isShowShipAis_gongzuo && isShowShipAis_tuo
                                && isShowShipAis_yu && isShowShipAis_qita) {
                            canvas.drawBitmap(mBitmapRotate,
                                    (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                    (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                        } else {

                            if (isShowShipAis_yehuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YEHUO) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_ke && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_KE) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_putonghuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_PUTONGHUO) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_gongcheng && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGCHENG) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_gongzuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGZUO) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_tuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_TUO) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_yu && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YU) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                            if (isShowShipAis_qita && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_QITA) {
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) pointOfScreen.X - mBitmapRotate.getWidth() / 2,
                                        (float) pointOfScreen.Y - mBitmapRotate.getHeight() / 2, paint);
                            }
                        }
                        canvas.restore();
                    }
                    if (winfoDNCParams.level > 15) {

                        // 是否画 船名
                        Paint paintText = new Paint();
                        paintText.setTextSize(26);
                        paintText.setStrokeWidth(2);
                        paintText.setColor(ContextCompat.getColor(m_context, R.color.black));

                        String cm = point.getCM();
                        if (cm == null || point.getCM().trim().equals("")) {
                            cm = point.getCM();
                        }

                        if (isShowShipAis_yehuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YEHUO) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_ke && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_KE) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_putonghuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_PUTONGHUO) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_gongcheng && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGCHENG) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_gongzuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGZUO) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_tuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_TUO) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_yu && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YU) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }
                        if (isShowShipAis_qita && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_QITA) {
                            canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                            canvas.drawText(cm, (float) pointOfScreen.X + 55, (float) pointOfScreen.Y - 55, paintText);

                        }

                        Rect bounds = new Rect();
                        paintText.getTextBounds(cm, 0, cm.length(), bounds);
                        int hight = bounds.bottom - bounds.top;
                        int wight = bounds.right - bounds.left;

                        Paint paint = new Paint();
                        paint.setStrokeWidth(2);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setColor(ContextCompat.getColor(m_context, R.color.black));

                        Path path = new Path();
                        path.moveTo((float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50);
                        path.lineTo((float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 60 - hight);

                        path.lineTo((float) pointOfScreen.X + 60 + wight, (float) pointOfScreen.Y - 60 - hight);
                        path.lineTo((float) pointOfScreen.X + 60 + wight, (float) pointOfScreen.Y - 50);
                        path.close();

                        if (isShowShipAis_yehuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YEHUO) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_ke && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_KE) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_putonghuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_PUTONGHUO) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_gongcheng && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGCHENG) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_gongzuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGZUO) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_tuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_TUO) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_yu && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YU) {
                            canvas.drawPath(path, paint);
                        }
                        if (isShowShipAis_qita && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_QITA) {
                            canvas.drawPath(path, paint);
                        }
                    }
                    // 如果船速大于0才去显示显示预测船舶位置
                    if (point.HS > 0) {

                        // 得到1min后船的预测位置
                        if (isShowShipAis_yehuo && isShowShipAis_ke && isShowShipAis_putonghuo
                                && isShowShipAis_gongcheng && isShowShipAis_gongzuo && isShowShipAis_tuo
                                && isShowShipAis_yu && isShowShipAis_qita) {
                            flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                        } else {
                            if (isShowShipAis_yehuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YEHUO) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_ke && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_KE) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_putonghuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_PUTONGHUO) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_gongcheng && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGCHENG) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_gongzuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_GONGZUO) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_tuo && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_TUO) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_yu && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_YU) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                            if (isShowShipAis_qita && AISUtils.getShipType(point.getSZCLX()) == AISUtils.AIS_QITA) {
                                flag = drawHS(canvas, flag, forecastPaint, point, pointOfScreen);
                            }
                        }
                    }
                }
            }
        }

        //--------------------------------------------------------显示通航要素-------------------------------------------------
        if (isShowThys) {
            Paint paintThy = new Paint();
            paintThy.setColor(Color.RED);
            if (winfoDNCParams.level < 14) {
                Point point = null;
                for (NavigableElementsData navigableElementsData : winfoDNCParams.navigableElementsData) {
                    String str = navigableElementsData.getCenterLatLon();
                    if (!str.equals("") && !str.contains("°")) {
                        String[] strs = str.split(";");
                        for (String str1 : strs) {
                            String[] ll = str1.split(",");
                            point = new Point();
                            point.lon = Double.valueOf(ll[0]);
                            point.lat = Double.valueOf(ll[1]);
                        }
                        Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);
                        canvas.drawCircle((float) pointOfScreen.X, (float) pointOfScreen.Y, UIUtils.dip2Px(1.3), paintThy);
                    }
                }
            } else {
                for (NavigableElementsData navigableElementsData : winfoDNCParams.navigableElementsData) {
                    Paint paintText = new Paint();
                    paintText.setTextSize(26);
                    paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                    Point point = new Point();
                    String str = navigableElementsData.getCenterLatLon();
                    String[] ll = str.split(",");
                    if (ll.length == 2) {
                        if (!ll[0].contains("°")) {
                            point.lon = Double.valueOf(ll[0]);
                            point.lat = Double.valueOf(ll[1]);
                            Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);
                            if (pointOfScreen.X > 0 && pointOfScreen.X < winfoDNCParams.mapViewInfo.Width && pointOfScreen.Y > 0 && pointOfScreen.Y < winfoDNCParams.mapViewInfo.Height) {
                                canvas.save();
                                String string = navigableElementsData.getNavigationElementsType().getTypeIcon();
                                String icon = string.substring(0, string.length() - 4);
                                switch (icon) {
                                    case "ne0001000004":
                                        canvas.drawBitmap(ne0001000004, (float) pointOfScreen.X - ne0001000004.getWidth() / 2, (float) pointOfScreen.Y - ne0001000004.getHeight() / 2, paint);
                                        break;
                                    case "ne0001000005":
                                        canvas.drawBitmap(ne0001000005, (float) pointOfScreen.X - ne0001000005.getWidth() / 2, (float) pointOfScreen.Y - ne0001000005.getHeight() / 2, paint);
                                        break;
                                    case "ne0001000006":
                                        canvas.drawBitmap(ne0001000006, (float) pointOfScreen.X - ne0001000006.getWidth() / 2, (float) pointOfScreen.Y - ne0001000006.getHeight() / 2, paint);
                                        break;
                                    case "ne0001000007":
                                        canvas.drawBitmap(ne0001000007, (float) pointOfScreen.X - ne0001000007.getWidth() / 2, (float) pointOfScreen.Y - ne0001000007.getHeight() / 2, paint);
                                        break;
                                    case "ne0001000008":
                                        canvas.drawBitmap(ne0001000008, (float) pointOfScreen.X - ne0001000008.getWidth() / 2, (float) pointOfScreen.Y - ne0001000008.getHeight() / 2, paint);
                                        break;
                                    case "ne0001000009":
                                        canvas.drawBitmap(ne0001000009, (float) pointOfScreen.X - ne0001000009.getWidth() / 2, (float) pointOfScreen.Y - ne0001000009.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000002":
                                        canvas.drawBitmap(ne0002000002, (float) pointOfScreen.X - ne0002000002.getWidth() / 2, (float) pointOfScreen.Y - ne0002000002.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000003":
                                        canvas.drawBitmap(ne0002000003, (float) pointOfScreen.X - ne0002000003.getWidth() / 2, (float) pointOfScreen.Y - ne0002000003.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000004":
                                        canvas.drawBitmap(ne0002000004, (float) pointOfScreen.X - ne0002000004.getWidth() / 2, (float) pointOfScreen.Y - ne0002000004.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000005":
                                        canvas.drawBitmap(ne0002000005, (float) pointOfScreen.X - ne0002000005.getWidth() / 2, (float) pointOfScreen.Y - ne0002000005.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000006":
                                        canvas.drawBitmap(ne0002000006, (float) pointOfScreen.X - ne0002000006.getWidth() / 2, (float) pointOfScreen.Y - ne0002000006.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000007":
                                        canvas.drawBitmap(ne0002000007, (float) pointOfScreen.X - ne0002000007.getWidth() / 2, (float) pointOfScreen.Y - ne0002000007.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000008":
                                        canvas.drawBitmap(ne0002000008, (float) pointOfScreen.X - ne0002000008.getWidth() / 2, (float) pointOfScreen.Y - ne0002000008.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000009":
                                        canvas.drawBitmap(ne0002000009, (float) pointOfScreen.X - ne0002000009.getWidth() / 2, (float) pointOfScreen.Y - ne0002000009.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000010":
                                        canvas.drawBitmap(ne0002000010, (float) pointOfScreen.X - ne0002000010.getWidth() / 2, (float) pointOfScreen.Y - ne0002000010.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000011":
                                        canvas.drawBitmap(ne0002000011, (float) pointOfScreen.X - ne0002000011.getWidth() / 2, (float) pointOfScreen.Y - ne0002000011.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000012":
                                        canvas.drawBitmap(ne0002000012, (float) pointOfScreen.X - ne0002000012.getWidth() / 2, (float) pointOfScreen.Y - ne0002000012.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000013":
                                        canvas.drawBitmap(ne0002000013, (float) pointOfScreen.X - ne0002000013.getWidth() / 2, (float) pointOfScreen.Y - ne0002000013.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000014":
                                        canvas.drawBitmap(ne0002000014, (float) pointOfScreen.X - ne0002000014.getWidth() / 2, (float) pointOfScreen.Y - ne0002000014.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000015":
                                        canvas.drawBitmap(ne0002000015, (float) pointOfScreen.X - ne0002000015.getWidth() / 2, (float) pointOfScreen.Y - ne0002000015.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000016":
                                        canvas.drawBitmap(ne0002000016, (float) pointOfScreen.X - ne0002000016.getWidth() / 2, (float) pointOfScreen.Y - ne0002000016.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000017":
                                        canvas.drawBitmap(ne0002000017, (float) pointOfScreen.X - ne0002000017.getWidth() / 2, (float) pointOfScreen.Y - ne0002000017.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000020":
                                        canvas.drawBitmap(ne0002000020, (float) pointOfScreen.X - ne0002000020.getWidth() / 2, (float) pointOfScreen.Y - ne0002000020.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000021":
                                        canvas.drawBitmap(ne0002000021, (float) pointOfScreen.X - ne0002000021.getWidth() / 2, (float) pointOfScreen.Y - ne0002000021.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000022":
                                        canvas.drawBitmap(ne0002000022, (float) pointOfScreen.X - ne0002000022.getWidth() / 2, (float) pointOfScreen.Y - ne0002000022.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000023":
                                        canvas.drawBitmap(ne0002000023, (float) pointOfScreen.X - ne0002000023.getWidth() / 2, (float) pointOfScreen.Y - ne0002000023.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000024":
                                        canvas.drawBitmap(ne0002000024, (float) pointOfScreen.X - ne0002000024.getWidth() / 2, (float) pointOfScreen.Y - ne0002000024.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000025":
                                        canvas.drawBitmap(ne0002000025, (float) pointOfScreen.X - ne0002000025.getWidth() / 2, (float) pointOfScreen.Y - ne0002000025.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000026":
                                        canvas.drawBitmap(ne0002000026, (float) pointOfScreen.X - ne0002000026.getWidth() / 2, (float) pointOfScreen.Y - ne0002000026.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000027":
                                        canvas.drawBitmap(ne0002000027, (float) pointOfScreen.X - ne0002000027.getWidth() / 2, (float) pointOfScreen.Y - ne0002000027.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000028":
                                        canvas.drawBitmap(ne0002000028, (float) pointOfScreen.X - ne0002000028.getWidth() / 2, (float) pointOfScreen.Y - ne0002000028.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000029":
                                        canvas.drawBitmap(ne0002000029, (float) pointOfScreen.X - ne0002000029.getWidth() / 2, (float) pointOfScreen.Y - ne0002000029.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000030":
                                        canvas.drawBitmap(ne0002000030, (float) pointOfScreen.X - ne0002000030.getWidth() / 2, (float) pointOfScreen.Y - ne0002000030.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000031":
                                        canvas.drawBitmap(ne0002000031, (float) pointOfScreen.X - ne0002000031.getWidth() / 2, (float) pointOfScreen.Y - ne0002000031.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000032":
                                        canvas.drawBitmap(ne0002000032, (float) pointOfScreen.X - ne0002000032.getWidth() / 2, (float) pointOfScreen.Y - ne0002000032.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000033":
                                        canvas.drawBitmap(ne0002000033, (float) pointOfScreen.X - ne0002000033.getWidth() / 2, (float) pointOfScreen.Y - ne0002000033.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000034":
                                        canvas.drawBitmap(ne0002000034, (float) pointOfScreen.X - ne0002000034.getWidth() / 2, (float) pointOfScreen.Y - ne0002000034.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000035":
                                        canvas.drawBitmap(ne0002000035, (float) pointOfScreen.X - ne0002000035.getWidth() / 2, (float) pointOfScreen.Y - ne0002000035.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000036":
                                        canvas.drawBitmap(ne0002000036, (float) pointOfScreen.X - ne0002000036.getWidth() / 2, (float) pointOfScreen.Y - ne0002000036.getHeight() / 2, paint);
                                        break;
                                    case "ne0002000037":
                                        canvas.drawBitmap(ne0002000037, (float) pointOfScreen.X - ne0002000037.getWidth() / 2, (float) pointOfScreen.Y - ne0002000037.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000001":
                                        canvas.drawBitmap(ne0003000001, (float) pointOfScreen.X - ne0003000001.getWidth() / 2, (float) pointOfScreen.Y - ne0003000001.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000002":
                                        canvas.drawBitmap(ne0003000002, (float) pointOfScreen.X - ne0003000002.getWidth() / 2, (float) pointOfScreen.Y - ne0003000002.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000003":
                                        canvas.drawBitmap(ne0003000003, (float) pointOfScreen.X - ne0003000003.getWidth() / 2, (float) pointOfScreen.Y - ne0003000003.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000004":
                                        canvas.drawBitmap(ne0003000004, (float) pointOfScreen.X - ne0003000004.getWidth() / 2, (float) pointOfScreen.Y - ne0003000004.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000005":
                                        canvas.drawBitmap(ne0003000005, (float) pointOfScreen.X - ne0003000005.getWidth() / 2, (float) pointOfScreen.Y - ne0003000005.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000006":
                                        canvas.drawBitmap(ne0003000006, (float) pointOfScreen.X - ne0003000006.getWidth() / 2, (float) pointOfScreen.Y - ne0003000006.getHeight() / 2, paint);
                                        break;
                                    case "ne0003000007":
                                        canvas.drawBitmap(ne0003000007, (float) pointOfScreen.X - ne0003000007.getWidth() / 2, (float) pointOfScreen.Y - ne0003000007.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000001":
                                        canvas.drawBitmap(ne0004000001, (float) pointOfScreen.X - ne0004000001.getWidth() / 2, (float) pointOfScreen.Y - ne0004000001.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000002":
                                        canvas.drawBitmap(ne0004000002, (float) pointOfScreen.X - ne0004000002.getWidth() / 2, (float) pointOfScreen.Y - ne0004000002.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000003":
                                        canvas.drawBitmap(ne0004000003, (float) pointOfScreen.X - ne0004000003.getWidth() / 2, (float) pointOfScreen.Y - ne0004000003.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000004":
                                        canvas.drawBitmap(ne0004000004, (float) pointOfScreen.X - ne0004000004.getWidth() / 2, (float) pointOfScreen.Y - ne0004000004.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000005":
                                        canvas.drawBitmap(ne0004000005, (float) pointOfScreen.X - ne0004000005.getWidth() / 2, (float) pointOfScreen.Y - ne0004000005.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000006":
                                        canvas.drawBitmap(ne0004000006, (float) pointOfScreen.X - ne0004000006.getWidth() / 2, (float) pointOfScreen.Y - ne0004000006.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000007":
                                        canvas.drawBitmap(ne0004000007, (float) pointOfScreen.X - ne0004000007.getWidth() / 2, (float) pointOfScreen.Y - ne0004000007.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000008":
                                        canvas.drawBitmap(ne0004000008, (float) pointOfScreen.X - ne0004000008.getWidth() / 2, (float) pointOfScreen.Y - ne0004000008.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000009":
                                        canvas.drawBitmap(ne0004000009, (float) pointOfScreen.X - ne0004000009.getWidth() / 2, (float) pointOfScreen.Y - ne0004000009.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000010":
                                        canvas.drawBitmap(ne0004000010, (float) pointOfScreen.X - ne0004000010.getWidth() / 2, (float) pointOfScreen.Y - ne0004000010.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000011":
                                        canvas.drawBitmap(ne0004000011, (float) pointOfScreen.X - ne0004000011.getWidth() / 2, (float) pointOfScreen.Y - ne0004000011.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000012":
                                        canvas.drawBitmap(ne0004000012, (float) pointOfScreen.X - ne0004000012.getWidth() / 2, (float) pointOfScreen.Y - ne0004000012.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000013":
                                        canvas.drawBitmap(ne0004000013, (float) pointOfScreen.X - ne0004000013.getWidth() / 2, (float) pointOfScreen.Y - ne0004000013.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000014":
                                        canvas.drawBitmap(ne0004000014, (float) pointOfScreen.X - ne0004000014.getWidth() / 2, (float) pointOfScreen.Y - ne0004000014.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000015":
                                        canvas.drawBitmap(ne0004000015, (float) pointOfScreen.X - ne0004000015.getWidth() / 2, (float) pointOfScreen.Y - ne0004000015.getHeight() / 2, paint);
                                        break;
                                    case "ne0004000016":
                                        canvas.drawBitmap(ne0004000016, (float) pointOfScreen.X - ne0004000016.getWidth() / 2, (float) pointOfScreen.Y - ne0004000016.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000001":
                                        canvas.drawBitmap(ne0005000001, (float) pointOfScreen.X - ne0005000001.getWidth() / 2, (float) pointOfScreen.Y - ne0005000001.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000002":
                                        canvas.drawBitmap(ne0005000002, (float) pointOfScreen.X - ne0005000002.getWidth() / 2, (float) pointOfScreen.Y - ne0005000002.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000003":
                                        canvas.drawBitmap(ne0005000003, (float) pointOfScreen.X - ne0005000003.getWidth() / 2, (float) pointOfScreen.Y - ne0005000003.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000004":
                                        canvas.drawBitmap(ne0005000004, (float) pointOfScreen.X - ne0005000004.getWidth() / 2, (float) pointOfScreen.Y - ne0005000004.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000005":
                                        canvas.drawBitmap(ne0005000005, (float) pointOfScreen.X - ne0005000005.getWidth() / 2, (float) pointOfScreen.Y - ne0005000005.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000006":
                                        canvas.drawBitmap(ne0005000006, (float) pointOfScreen.X - ne0005000006.getWidth() / 2, (float) pointOfScreen.Y - ne0005000006.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000007":
                                        canvas.drawBitmap(ne0005000007, (float) pointOfScreen.X - ne0005000007.getWidth() / 2, (float) pointOfScreen.Y - ne0005000007.getHeight() / 2, paint);
                                        break;
                                    case "ne0005000008":
                                        canvas.drawBitmap(ne0005000008, (float) pointOfScreen.X - ne0005000008.getWidth() / 2, (float) pointOfScreen.Y - ne0005000008.getHeight() / 2, paint);
                                        break;
                                }
                                canvas.restore();
                            }
                        }
                    }
                }
            }
        }
        // ---------------------------------------------执法人员--------------------------------------------------------
        if (isShowZhifaPerson) {
            {
                String uuid = ACache.get(m_context).getAsString("uuid");
                for (MobileTerminalInfo2 point : winfoDNCParams.zhifaPersonData) {
                    Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                            winfoDNCParams.mapViewInfo.vp, point.getLongitude().doubleValue(),
                            point.getLatitude().doubleValue());
                    if (pointOfScreen.X > 0 && pointOfScreen.X < winfoDNCParams.mapViewInfo.Width
                            && pointOfScreen.Y > 0
                            && pointOfScreen.Y < winfoDNCParams.mapViewInfo.Height) {

                        if (!point.getUserUuid().equals(uuid)) {
                            canvas.save();
                            canvas.drawBitmap(mZhifaPerson,
                                    (float) pointOfScreen.X - mZhifaPerson.getWidth() / 2,
                                    (float) pointOfScreen.Y - mZhifaPerson.getHeight() / 2, paint);
                            canvas.restore();
                        }
                    }
                }
            }

        }

        if (isShowShipTrack) {
            // 线
            Paint paintLine = new Paint();
            paintLine.setAntiAlias(true);
            paintLine.setColor(ContextCompat.getColor(m_context, R.color.red));
            paintLine.setStrokeWidth(3);
            // 点
            paint = new Paint();
            paint.setColor(ContextCompat.getColor(m_context, R.color.red));
            paint.setAntiAlias(true);
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);

            Paint paint1 = new Paint();
            paint1.setColor(ContextCompat.getColor(m_context, R.color.white));
            paint1.setStrokeWidth(10);

            Paint paintIn = new Paint();
            paintIn.setColor(ContextCompat.getColor(m_context, R.color.white));
            paintIn.setStrokeWidth(5);

            TrackData trackData1;// 当前轨迹点
            Point pointOfScreen;
            TrackData trackDataMark = null;// 记录(标记上一个)轨迹点

            List<Point> points = new ArrayList<>();

            if (winfoDNCParams.trackPointList != null) {
                int size = winfoDNCParams.trackPointList.size();
                int levelRatio;
                if (winfoDNCParams.level < 8) {
                    levelRatio = winfoDNCParams.level + 1;
                } else if (winfoDNCParams.level < 10
                        && winfoDNCParams.level > 7) {
                    levelRatio = winfoDNCParams.level * 2;
                } else if (winfoDNCParams.level < 13
                        && winfoDNCParams.level > 10) {
                    levelRatio = 10 + 20 * (winfoDNCParams.level - 10);
                } else {
                    levelRatio = 100 + 25 * (winfoDNCParams.level - 13);
                }
                // 计算 过滤 比例
                int ratio = size / levelRatio;
                // 目前 1-18 层级 均显示 线 和 红圈形式
                if (winfoDNCParams.level < 19) {
                    // 比例 大于零 代表 需要过滤
                    if (ratio > 0) {
                        points.clear();
                        for (int i = 0; i < winfoDNCParams.trackPointList.size(); i++) {
                            if (i == winfoDNCParams.trackPointList.size() - 1) {
                                int aisWidth = iconOtherShip.getWidth();
                                int aisHeight = iconOtherShip.getHeight();
                                Matrix mMatrix = new Matrix();
                                mMatrix.setRotate((float) winfoDNCParams.trackPointList.get(i).getHX());
                                Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                        winfoDNCParams.trackPointList.get(i).getJD(),
                                        winfoDNCParams.trackPointList.get(i).getWD());
                                Bitmap mBitmapRotate;
                                mBitmapRotate = getShipBitmap(AISUtils.getShipType(SZ_guiji));
                                // 最后一个船图标到倒数第二个点划线
                                canvas.drawLine((float) point.X, (float) point.Y, (float) trackDataMark.X, (float) trackDataMark.Y, paintLine);
                                canvas.drawBitmap(mBitmapRotate,
                                        (float) point.X - mBitmapRotate.getWidth() / 2,
                                        (float) point.Y - mBitmapRotate.getHeight() / 2,
                                        paint);
                            }
                            if (i == 0) {
                                Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                                        winfoDNCParams.mapViewInfo.vp,
                                        winfoDNCParams.trackPointList.get(i).getJD(),
                                        winfoDNCParams.trackPointList.get(i).getWD());
//								canvas.drawCircle((float) point.X,(float) point.Y, 20, paintIn);
                                canvas.drawCircle((int) point.X, (int) point.Y, 15, paint);
                                canvas.drawCircle((int) point.X, (int) point.Y, 14, paint1);
                                canvas.drawCircle((int) point.X, (int) point.Y, 5, paint);

                                trackDataMark = winfoDNCParams.trackPointList.get(i);
                                trackDataMark.X = point.X;
                                trackDataMark.Y = point.Y;
                            }
                            // 开始过滤 是比例的倍数才显示 在地图上
                            if (i % ratio == 0) {
                                trackData1 = winfoDNCParams.trackPointList.get(i);
                                pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                        trackData1.getJD(), trackData1.getWD());
                                trackData1.X = pointOfScreen.X;
                                trackData1.Y = pointOfScreen.Y;
                                if (i != winfoDNCParams.trackPointList.size() - 1) {
                                    // 否则不是 最后一个点 那么轨迹点就还没结束 就还有结束点 开始画线
//                                    int index = i + ratio;
//                                    if (index >= size) {
//                                        index = size - 1;
//                                    }
                                    // 计算当前轨迹点 与上一个记录的轨迹点的距离
                                    double sqrt = Math.sqrt((trackData1.X - trackDataMark.X) * (trackData1.X - trackDataMark.X) + (trackData1.Y - trackDataMark.Y) * (trackData1.Y - trackDataMark.Y));
                                    if (sqrt > 100) {
                                        // 画出轨迹线 当前点画到标记点
                                        canvas.drawLine((float) trackData1.X, (float) trackData1.Y, (float) trackDataMark.X, (float) trackDataMark.Y, paintLine);
                                        // 画出每个点的 中间点 白色
                                        // canvas.drawCircle((float)
                                        // trackData1.X, (float) trackData1.Y,
                                        // 4,paintIn);
                                        // 画出轨迹点
//										canvas.drawCircle((float) trackData1.X,(float) trackData1.Y, 6, paint);
                                        Point point = new Point();
                                        point.X = trackData1.X;
                                        point.Y = trackData1.Y;
                                        points.add(point);

                                        // 画 日期
                                        Paint paintText = new Paint();
                                        paintText.setTextSize(26);
                                        paintText.setStrokeWidth(2);
                                        paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                                        canvas.drawLine((float) pointOfScreen.X, (float) pointOfScreen.Y, (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
                                        canvas.drawText(trackData1.getSJ(), (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 50, paintText);
//                                        canvas.drawText(trackData1.getMC(), (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 75, paintText);

                                        // 重新 定义 记录的轨迹点
                                        trackDataMark = trackData1;
                                    }
                                }
                            }
                        }
                    } else {// 比例 小于零 不需要过滤
                        points.clear();
                        for (int i = 0; i < winfoDNCParams.trackPointList.size(); i++) {
                            trackData1 = winfoDNCParams.trackPointList.get(i);
                            pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                    trackData1.getJD(), trackData1.getWD());
                            trackData1.X = pointOfScreen.X;
                            trackData1.Y = pointOfScreen.Y;
                            if (i == 0) {
                                Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                        winfoDNCParams.trackPointList.get(i).getJD(),
                                        winfoDNCParams.trackPointList.get(i).getWD());
//								canvas.drawCircle((float) point.X,(float) point.Y, 20, paintIn);
                                canvas.drawCircle((int) point.X, (int) point.Y, 15, paint);
                                canvas.drawCircle((int) point.X, (int) point.Y, 14, paint1);
                                canvas.drawCircle((int) point.X, (int) point.Y, 5, paint);

                                trackDataMark = winfoDNCParams.trackPointList.get(i);
                                trackDataMark.X = point.X;
                                trackDataMark.Y = point.Y;
                            }
                            // 如果当前点是 最后一个点 那就画出船舶的图标 不画轨迹点
                            if (i == winfoDNCParams.trackPointList.size() - 1) {
                                int aisWidth = iconOtherShip.getWidth();
                                int aisHeight = iconOtherShip.getHeight();
                                Matrix mMatrix = new Matrix();
                                mMatrix.setRotate((float) winfoDNCParams.trackPointList.get(i).getHX());
                                Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                        winfoDNCParams.trackPointList.get(i).getJD(),
                                        winfoDNCParams.trackPointList.get(i).getWD());
                                Bitmap mBitmapRotate;
                                mBitmapRotate = getShipBitmap(AISUtils.getShipType(SZ_guiji));
                                // 最后一个船图标到倒数第二个点划线
                                canvas.drawLine((float) point.X, (float) point.Y, (float) trackDataMark.X, (float) trackDataMark.Y, paintLine);
                                canvas.drawBitmap(mBitmapRotate, (float) point.X - mBitmapRotate.getWidth() / 2, (float) point.Y - mBitmapRotate.getHeight() / 2, paint);

                            } else {
                                // 否则不是 最后一个点 那么轨迹点就还没结束 就还有结束点 开始画线
                                double sqrt = Math.sqrt((trackData1.X - trackDataMark.X) * (trackData1.X - trackDataMark.X) + (trackData1.Y - trackDataMark.Y) * (trackData1.Y - trackDataMark.Y));
                                if (sqrt > 100) {
                                    // 画出轨迹线 当前点画到标记点
                                    canvas.drawLine((float) trackData1.X, (float) trackData1.Y, (float) trackDataMark.X, (float) trackDataMark.Y, paintLine);
                                    // 画出每个点的 中间点 白色
                                    // canvas.drawCircle((float) trackData1.X,
                                    // (float) trackData1.Y, 4,paintIn);
                                    // 画出轨迹点
//									canvas.drawCircle((float) trackData1.X,(float) trackData1.Y, 6, paint);
                                    Point point = new Point();
                                    point.X = trackData1.X;
                                    point.Y = trackData1.Y;
                                    points.add(point);

                                    // 是否画 日期
                                    Paint paintText = new Paint();
                                    paintText.setTextSize(26);
                                    paintText.setStrokeWidth(2);
                                    paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                                    canvas.drawLine((float) pointOfScreen.X,
                                            (float) pointOfScreen.Y,
                                            (float) pointOfScreen.X + 50,
                                            (float) pointOfScreen.Y - 50,
                                            paintText);
                                    canvas.drawText(trackData1.getSJ(),
                                            (float) pointOfScreen.X + 50,
                                            (float) pointOfScreen.Y - 50,
                                            paintText);
//                                    canvas.drawText(trackData1.getMC(), (float) pointOfScreen.X + 50, (float) pointOfScreen.Y - 75, paintText);

                                    // 重新 定义 记录的轨迹点
                                    trackDataMark = trackData1;
                                }
                            }
                        }
                    }
                    for (int i = 0; i < points.size(); i++) {
                        canvas.drawCircle((int) points.get(i).X, (int) points.get(i).Y, 6, paint);
                        canvas.drawCircle((int) points.get(i).X, (int) points.get(i).Y, 5, paintIn);
                    }
                } else {
                    // 这里不走 预留不同层级显示不同轨迹效果
                    for (int i = 0; i < winfoDNCParams.trackPointList.size(); i++) {
                        trackData1 = winfoDNCParams.trackPointList.get(i);
                        pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                                winfoDNCParams.mapViewInfo.vp,
                                trackData1.getJD(),
                                trackData1.getWD());
                        trackData1.X = pointOfScreen.X;
                        trackData1.Y = pointOfScreen.Y;
                        // 如果当前点是 最后一个点 那就画出船舶的图标 不画轨迹点
                        if (i == winfoDNCParams.trackPointList.size() - 1) {
                            int aisWidth = iconOtherShip.getWidth();
                            int aisHeight = iconOtherShip.getHeight();
                            Matrix mMatrix = new Matrix();
                            mMatrix.setRotate((float) winfoDNCParams.trackPointList.get(i).getHX());
                            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                                    winfoDNCParams.mapViewInfo.vp,
                                    winfoDNCParams.trackPointList.get(i).getJD(),
                                    winfoDNCParams.trackPointList.get(i).getWD());

                            Bitmap mBitmapRotate;
                            mBitmapRotate = getShipBitmap(AISUtils.getShipType(SZ_guiji));
                            canvas.drawBitmap(mBitmapRotate,
                                    (float) point.X - mBitmapRotate.getWidth() / 2,
                                    (float) point.Y - mBitmapRotate.getHeight() / 2, paint);
                            // 最后一个船图标到倒数第二个点划线
                            canvas.drawLine((float) point.X, (float) point.Y,
                                    (float) trackDataMark.X,
                                    (float) trackDataMark.Y, paintLine);
                        } else {
                            // 否则不是 最后一个点 那么轨迹点就还没结束 就还有结束点 开始画线
                            double sqrt = Math.sqrt((trackData1.X - trackDataMark.X)
                                    * (trackData1.X - trackDataMark.X)
                                    + (trackData1.Y - trackDataMark.Y)
                                    * (trackData1.Y - trackDataMark.Y));
                            if (sqrt > 100) {
                                // 画出轨迹线 当前点画到标记点
                                canvas.drawLine((float) trackData1.X,
                                        (float) trackData1.Y,
                                        (float) trackDataMark.X,
                                        (float) trackDataMark.Y, paintLine);
                                // 画出每个点的 中间点 白色
                                // canvas.drawCircle((float) trackData1.X,
                                // (float) trackData1.Y, 4,paintIn);
                                // 画出轨迹点
                                canvas.drawCircle((float) trackData1.X, (float) trackData1.Y, 6, paint);
                                // 画 日期
                                Paint paintText = new Paint();
                                paintText.setTextSize(26);
                                paintText.setStrokeWidth(2);
                                paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                                canvas.drawLine((float) pointOfScreen.X,
                                        (float) pointOfScreen.Y,
                                        (float) pointOfScreen.X + 50,
                                        (float) pointOfScreen.Y - 50, paintText);
                                canvas.drawText(trackData1.getSJ(),
                                        (float) pointOfScreen.X + 50,
                                        (float) pointOfScreen.Y - 50, paintText);
                                // 重新 定义 记录的轨迹点
                                trackDataMark = trackData1;
                            }
                        }
                    }
                }
            }
        }
        //画用户添加操作  标绘选择的点

        if (is_select_plotting_point && winfoDNCParams.plottingPoint != null) {
            MainActivity activity = (MainActivity) m_context;
            activity.showConfirmPlotting();


            Point pointOfScreen = winfoDNCParams.basicAlgorithm
                    .coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                            winfoDNCParams.plottingPoint.lon,
                            winfoDNCParams.plottingPoint.lat);

            switch (plotting_point_type) {
                case "1":
                    canvas.drawBitmap(plotting_point_other, (float) pointOfScreen.X - plotting_point_other.getWidth() / 2, (float) pointOfScreen.Y
                            - plotting_point_other.getHeight(), new Paint());
                    break;
                case "2":
                    canvas.drawBitmap(plotting_point_wreck, (float) pointOfScreen.X - plotting_point_wreck.getWidth() / 2, (float) pointOfScreen.Y
                            - plotting_point_wreck.getHeight(), new Paint());
                    break;
                case "3":
                    canvas.drawBitmap(plotting_point_oil, (float) pointOfScreen.X - plotting_point_oil.getWidth() / 2, (float) pointOfScreen.Y
                            - plotting_point_oil.getHeight(), new Paint());
                    break;
                case "4":
                    canvas.drawBitmap(plotting_point_accident, (float) pointOfScreen.X - plotting_point_accident.getWidth() / 2, (float) pointOfScreen.Y
                            - plotting_point_accident.getHeight(), new Paint());
                    break;

            }

        }

//        //画编辑的标绘点
//        if (isEditPlottingPoint&&winfoDNCParams.editPlottingPoint!=null) {
//            String position = winfoDNCParams.editPlottingPoint.getLatLon();
//            String[] ll = position.split(",");
//            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
//                    winfoDNCParams.mapViewInfo.vp, Double.valueOf(ll[1]), Double.valueOf(ll[0]));
//
//
//            switch (plotting_point_type){
//                case "1":
//                    canvas.drawBitmap(plotting_point_other, (float) point.X - plotting_point_other.getWidth() / 2, (float) point.Y
//                            - plotting_point_other.getHeight(), new Paint());
//                    break;
//                case "2":
//                    canvas.drawBitmap(plotting_point_wreck, (float) point.X - plotting_point_wreck.getWidth() / 2, (float) point.Y
//                            - plotting_point_wreck.getHeight(), new Paint());
//                    break;
//                case "3":
//                    canvas.drawBitmap(plotting_point_oil, (float) point.X - plotting_point_oil.getWidth() / 2, (float) point.Y
//                            - plotting_point_oil.getHeight(), new Paint());
//                    break;
//                case "4":
//                    canvas.drawBitmap(plotting_point_accident, (float) point.X - plotting_point_accident.getWidth() / 2, (float) point.Y
//                            - plotting_point_accident.getHeight(), new Paint());
//                    break;
//
//            }
//        }

        // 画标绘区域
        if (winfoDNCParams.showPlotting != null && winfoDNCParams.showPlotting.size() > 0) {
            Paint plottingPaint = new Paint();
            plottingPaint.setStrokeWidth(3);
            plottingPaint.setStyle(Paint.Style.STROKE);
            plottingPaint.setAntiAlias(true);
            for (int i = 0; i < winfoDNCParams.showPlotting.size(); i++) {
                Plotting plotting = winfoDNCParams.showPlotting
                        .get(i);
                String position = plotting.getLatLon();

                List<Point> points = new ArrayList<>();
                if (plotting.getDrawingType().equals("1")) {
                    //点
                    String[] ll = position.split(",");
                    Point point = new Point();
                    point.lon = Double.valueOf(ll[1]);
                    point.lat = Double.valueOf(ll[0]);
                    points.add(point);
                } else {
                    //线，面
                    String[] strs = position.split(";");
                    for (String str : strs) {
                        String[] ll = str.split(",");
                        Point point = new Point();
                        point.lon = Double.valueOf(ll[1]);
                        point.lat = Double.valueOf(ll[0]);
                        points.add(point);
                    }
                }

                switch (plotting.getDrawingType()) {
                    case "1": {//点

                        double lat1 = points.get(0).lat;
                        double lon1 = points.get(0).lon;
                        Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                                winfoDNCParams.mapViewInfo.vp, lon1, lat1);
                        canvas.drawBitmap(plotting_point_other,
                                (float) point.X - plotting_point_other.getWidth() / 2,
                                (float) point.Y - plotting_point_other.getHeight() / 2, paint);
                        break;
                    }
                    case "2": {//线
                        Path mPath = new Path();
                        plottingPaint.setColor(Color.parseColor(plotting
                                .getColor()));
                        if (plotting.getStyle().equals("1")) {
                            //实线
                            plottingPaint.setPathEffect(null);
                        } else {
                            plottingPaint.setPathEffect(new DashPathEffect(new float[]{10f, 3f}, 0));
                        }

                        if (plotting.getThickness().equals("1")) {
                            plottingPaint.setStrokeWidth(2);
                        } else if (plotting.getThickness().equals("2")) {
                            plottingPaint.setStrokeWidth(3);
                        } else if (plotting.getThickness().equals("3")) {
                            plottingPaint.setStrokeWidth(4);
                        }
                        Point pointStrart = winfoDNCParams.basicAlgorithm
                                .coordinate2ScreenPoint(
                                        winfoDNCParams.mapViewInfo.vp,
                                        points.get(0).lon, points.get(0).lat);
                        mPath.moveTo((float) pointStrart.X, (float) pointStrart.Y);
//                        Point pointFlag = pointStrart;
                        for (int j = 0; j < points.size(); j++) {
                            Point pointOfScreen = winfoDNCParams.basicAlgorithm
                                    .coordinate2ScreenPoint(
                                            winfoDNCParams.mapViewInfo.vp,
                                            points.get(j).lon, points.get(j).lat);

                            if (j != 0) {
                                mPath.lineTo((float) pointOfScreen.X, (float) pointOfScreen.Y);
                            }

//                            canvas.drawLine((float) pointFlag.X,
//                                    (float) pointFlag.Y,
//                                    (float) pointOfScreen.X,
//                                    (float) pointOfScreen.Y, plottingPaint);
//                            pointFlag = pointOfScreen;

//                            if (j == points.size() - 1) {
//                                canvas.drawLine((float) pointFlag.X,
//                                        (float) pointFlag.Y,
//                                        (float) pointStrart.X,
//                                        (float) pointStrart.Y, plottingPaint);
//                            }
                        }
                        canvas.drawPath(mPath, plottingPaint);
                        break;
                    }
                    case "3":
                        // 画多边形
                        Path mPath = new Path();
                        plottingPaint.setColor(Color.parseColor(plotting
                                .getColor()));
                        if (plotting.getThickness().equals("1")) {
                            plottingPaint.setStrokeWidth(2);
                        } else if (plotting.getThickness().equals("2")) {
                            plottingPaint.setStrokeWidth(3);
                        } else if (plotting.getThickness().equals("3")) {
                            plottingPaint.setStrokeWidth(4);
                        }
                        if (plotting.getStyle().equals("1")) {
                            //实线
                            plottingPaint.setPathEffect(null);
                        } else {
                            plottingPaint.setPathEffect(new DashPathEffect(new float[]{10f, 3f}, 0));
                        }
                        Point pointStrart = winfoDNCParams.basicAlgorithm
                                .coordinate2ScreenPoint(
                                        winfoDNCParams.mapViewInfo.vp,
                                        points.get(0).lon, points.get(0).lat);
                        mPath.moveTo((float) pointStrart.X, (float) pointStrart.Y);
//                        Point pointFlag = pointStrart;
                        for (int j = 0; j < points.size(); j++) {
                            Point pointOfScreen = winfoDNCParams.basicAlgorithm
                                    .coordinate2ScreenPoint(
                                            winfoDNCParams.mapViewInfo.vp,
                                            points.get(j).lon, points.get(j).lat);
                            if (j != 0) {
                                mPath.lineTo((float) pointOfScreen.X, (float) pointOfScreen.Y);
                            }
//                            canvas.drawLine((float) pointFlag.X,
//                                    (float) pointFlag.Y,
//                                    (float) pointOfScreen.X,
//                                    (float) pointOfScreen.Y, plottingPaint);
//                            pointFlag = pointOfScreen;

//                            if (j == points.size() - 1) {
//                                canvas.drawLine((float) pointFlag.X,
//                                        (float) pointFlag.Y,
//                                        (float) pointStrart.X,
//                                        (float) pointStrart.Y, plottingPaint);
//                            }
                        }
                        mPath.lineTo((float) pointStrart.X, (float) pointStrart.Y);
                        canvas.drawPath(mPath, plottingPaint);

                        break;
                }
                if (winfoDNCParams.level > 13) {
                    Point pointOfScreen = winfoDNCParams.basicAlgorithm
                            .coordinate2ScreenPoint(
                                    winfoDNCParams.mapViewInfo.vp,
                                    points.get(0).lon, points.get(0).lat);
                    Paint paintText = new Paint();
                    paintText.setTextSize(26);
                    paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                    canvas.drawLine((float) pointOfScreen.X,
                            (float) pointOfScreen.Y,
                            (float) pointOfScreen.X + 30,
                            (float) pointOfScreen.Y - 30, paintText);
                    canvas.drawText(plotting.getBhname(),
                            (float) pointOfScreen.X + 30,
                            (float) pointOfScreen.Y - 30, paintText);
                }
            }
        }
        //是否显示附近船舶
        if (isShowNearbyShips) {
            Paint paint = new Paint();
            paint.setDither(true);
            int count = winfoDNCParams.nearShipList.size();
            for (int i = 0; i < count; i++) {
                Ais aisData = winfoDNCParams.nearShipList.get(i);
                Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, aisData.getJD(), aisData.getWD());
                /*
                 * 船舶显示的图标
                 */
                Matrix mMatrix = new Matrix();
                mMatrix.setRotate((float) aisData.getHX());// 航向
                int shipWidth = iconOtherShip.getWidth();
                int shipHeight = iconOtherShip.getHeight();
                Bitmap mBitmapRotate = null;
                if (aisData.getSZCLX().contains("0100")
                        || aisData.getSZCLX().contains("0101")
                        || aisData.getSZCLX().contains("0102")
                        || aisData.getSZCLX().contains("0103")
                        || aisData.getSZCLX().contains("0104")
                        || aisData.getSZCLX().contains("0105")
                        || aisData.getSZCLX().contains("0106")
                        || aisData.getSZCLX().contains("0107")
                        || aisData.getSZCLX().contains("0108")
                        || aisData.getSZCLX().contains("0109")
                        || aisData.getSZCLX().contains("0110")
                        || aisData.getSZCLX().contains("0111")
                        || aisData.getSZCLX().contains("0112")
                        || aisData.getCLX().contains("客船类")
                        || aisData.getCLX().contains("普通客船")
                        || aisData.getCLX().contains("客货船")
                        || aisData.getCLX().contains("客渡船")
                        || aisData.getCLX().contains("车客渡船")
                        || aisData.getCLX().contains("旅游渡船")
                        || aisData.getCLX().contains("高速客船")
                        || aisData.getCLX().contains("客驳船")
                        || aisData.getCLX().contains("滚装客船")
                        || aisData.getCLX().contains("客箱船")
                        || aisData.getCLX().contains("火车渡船(客)")
                        || aisData.getCLX().contains("地效翼船")
                        || aisData.getCLX().contains("高速客滚船")
                        ) {
                    mBitmapRotate = Bitmap.createBitmap(iconKeShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (aisData.getSZCLX().contains("0200")
                        || aisData.getSZCLX().contains("0201")
                        || aisData.getSZCLX().contains("0202")
                        || aisData.getSZCLX().contains("0203")
                        || aisData.getSZCLX().contains("0204")
                        || aisData.getSZCLX().contains("0205")
                        || aisData.getSZCLX().contains("0206")
                        || aisData.getSZCLX().contains("0207")
                        || aisData.getSZCLX().contains("0208")
                        || aisData.getSZCLX().contains("0209")
                        || aisData.getSZCLX().contains("0210")
                        || aisData.getSZCLX().contains("0211")
                        || aisData.getSZCLX().contains("0212")
                        || aisData.getSZCLX().contains("0213")
                        || aisData.getSZCLX().contains("0214")
                        || aisData.getSZCLX().contains("0215")
                        || aisData.getSZCLX().contains("0216")
                        || aisData.getSZCLX().contains("0217")
                        || aisData.getCLX().contains("普通货船类")
                        || aisData.getCLX().contains("干货船")
                        || aisData.getCLX().contains("杂货船")
                        || aisData.getCLX().contains("散货船")
                        || aisData.getCLX().contains("散装水泥运输船")
                        || aisData.getCLX().contains("集装箱船")
                        || aisData.getCLX().contains("滚装船")
                        || aisData.getCLX().contains("多用途船")
                        || aisData.getCLX().contains("木材船")
                        || aisData.getCLX().contains("水产品运输")
                        || aisData.getCLX().contains("重大件运输船")
                        || aisData.getCLX().contains("驳船")
                        || aisData.getCLX().contains("汽车渡船")
                        || aisData.getCLX().contains("挂桨机船")
                        || aisData.getCLX().contains("冷藏船")
                        || aisData.getCLX().contains("火车渡船")
                        || aisData.getCLX().contains("矿/散/油船")
                        || aisData.getCLX().contains("半潜船")) {
                    mBitmapRotate = Bitmap.createBitmap(iconPuTongHuoShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (aisData.getSZCLX().contains("0300")
                        || aisData.getSZCLX().contains("0301")
                        || aisData.getSZCLX().contains("0302")
                        || aisData.getSZCLX().contains("0303")
                        || aisData.getSZCLX().contains("0304")
                        || aisData.getSZCLX().contains("0305")
                        || aisData.getSZCLX().contains("0306")
                        || aisData.getSZCLX().contains("0307")
                        || aisData.getCLX().contains("液货船类")
                        || aisData.getCLX().contains("油船")
                        || aisData.getCLX().contains("散装化学品船")
                        || aisData.getCLX().contains("散装化学品船/油船")
                        || aisData.getCLX().contains("液化气船")
                        || aisData.getCLX().contains("散装沥青船")
                        || aisData.getCLX().contains("油驳")
                        || aisData.getCLX().contains("一般液货船")) {
                    mBitmapRotate = Bitmap.createBitmap(iconYeHuoShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (aisData.getSZCLX().contains("0400")
                        || aisData.getSZCLX().contains("0401")
                        || aisData.getSZCLX().contains("0402")
                        || aisData.getSZCLX().contains("0403")
                        || aisData.getSZCLX().contains("0404")
                        || aisData.getSZCLX().contains("0405")
                        || aisData.getSZCLX().contains("0406")
                        || aisData.getSZCLX().contains("0407")
                        || aisData.getSZCLX().contains("0408")
                        || aisData.getSZCLX().contains("0409")
                        || aisData.getSZCLX().contains("0410")
                        || aisData.getSZCLX().contains("0411")
                        || aisData.getSZCLX().contains("0412")
                        || aisData.getSZCLX().contains("0413")
                        || aisData.getSZCLX().contains("0414")
                        || aisData.getCLX().contains("工程船类")
                        || aisData.getCLX().contains("工程船")
                        || aisData.getCLX().contains("测量船")
                        || aisData.getCLX().contains("采沙船")
                        || aisData.getCLX().contains("挖泥船")
                        || aisData.getCLX().contains("疏浚船")
                        || aisData.getCLX().contains("打捞船")
                        || aisData.getCLX().contains("打桩船")
                        || aisData.getCLX().contains("起重船")
                        || aisData.getCLX().contains("搅拌船")
                        || aisData.getCLX().contains("布缆船")
                        || aisData.getCLX().contains("钻井船")
                        || aisData.getCLX().contains("打桩起重船")
                        || aisData.getCLX().contains("吹泥船")
                        || aisData.getCLX().contains("起重驳")) {
                    mBitmapRotate = Bitmap.createBitmap(iconGongChengShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (aisData.getSZCLX().contains("0500")
                        || aisData.getSZCLX().contains("0501")
                        || aisData.getSZCLX().contains("0502")
                        || aisData.getSZCLX().contains("0503")
                        || aisData.getSZCLX().contains("0504")
                        || aisData.getSZCLX().contains("0505")
                        || aisData.getSZCLX().contains("0506")
                        || aisData.getCLX().contains("工作船类")
                        || aisData.getCLX().contains("工作船")
                        || aisData.getCLX().contains("破冰船")
                        || aisData.getCLX().contains("航标船")
                        || aisData.getCLX().contains("油污水处理船")
                        || aisData.getCLX().contains("供给船")
                        || aisData.getCLX().contains("垃圾处理船")) {
                    mBitmapRotate = Bitmap.createBitmap(iconGongZuoShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (aisData.getSZCLX().contains("0600")
                        || aisData.getSZCLX().contains("0601")
                        || aisData.getSZCLX().contains("0602")
                        || aisData.getCLX().contains("拖船类")
                        || aisData.getCLX().contains("拖船")
                        || aisData.getCLX().contains("推轮")) {
                    mBitmapRotate = Bitmap.createBitmap(iconTuoShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (
                        aisData.getSZCLX().equals("")
                                || aisData.getSZCLX().equals("0900")
                                || aisData.getSZCLX().equals("0901")
                                || aisData.getSZCLX().equals("0902")
                                || aisData.getSZCLX().equals("0903")
                                || aisData.getSZCLX().equals("0904")
                                || aisData.getSZCLX().equals("0905")
                                || aisData.getSZCLX().equals("0906")
                                || aisData.getSZCLX().equals("0907")
                                || aisData.getSZCLX().equals("0908")
                                || aisData.getSZCLX().equals("0909")
                                || aisData.getSZCLX().equals("0910")
                                || aisData.getSZCLX().equals("0911")
                                || aisData.getSZCLX().equals("0912")
                                || aisData.getSZCLX().equals("0913")
                                || aisData.getSZCLX().equals("0914")
                                || aisData.getCLX().contains("其他类")
                                || aisData.getCLX().contains("交通艇")
                                || aisData.getCLX().contains("引航船")
                                || aisData.getCLX().contains("救助船")
                                || aisData.getCLX().contains("浮船坞")
                                || aisData.getCLX().contains("公务船")
                                || aisData.getCLX().contains("摩托艇")
                                || aisData.getCLX().contains("帆船")
                                || aisData.getCLX().contains("趸船")
                                || aisData.getCLX().contains("游艇")
                                || aisData.getCLX().contains("特种用途船")
                                || aisData.getCLX().contains("水上平台")
                                || aisData.getCLX().contains("水下观光船")
                                || aisData.getCLX().contains("科学调查船")
                                || aisData.getCLX().contains("勘探船")

                        ) {
                    mBitmapRotate = Bitmap.createBitmap(iconOtherShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                } else if (aisData.getCLX().contains("渔船")) {
                    mBitmapRotate = Bitmap.createBitmap(iconYuShip, 0, 0,
                            shipWidth, shipHeight, mMatrix, true);
                }

                int mBitmapRotateWidth = mBitmapRotate.getWidth();
                int mBitmapRotateHeight = mBitmapRotate.getHeight();
                /*
                 * 船舶和通航要素 上面显示的定位的图标
                 */
                Bitmap b = BitmapFactory.decodeResource(mRes, R.mipmap.lp_1);
                int w = b.getWidth();
                int h = b.getHeight();
                canvas.drawBitmap(mBitmapRotate, (float) pointOfScreen.X
                        - mBitmapRotateWidth / 2, (float) pointOfScreen.Y
                        - mBitmapRotateHeight / 2, paint);
                if (winfoDNCParams.ct.getPosition() == i) {
                    // 画通航要素和船舶上面的 选择定位图标 红色
                    canvas.drawBitmap(bitmapIconsSelect.get(winfoDNCParams.ct
                                    .getPosition()), (float) pointOfScreen.X - w / 2,
                            (float) pointOfScreen.Y - h, paint);
                } else {
                // 画通航要素和船舶上面的 定位图标 蓝色
                canvas.drawBitmap(bitmapIcons.get(i),
                        (float) pointOfScreen.X - w / 2,
                        (float) pointOfScreen.Y - h, paint);
                }
            }
        }

        //是否显示附近要素
        if (isShowNearbyThys) {
            int count = winfoDNCParams.nearThysList.size();
            for (int i = 0; i < count; i++) {
                NavigableElementsData thysData = winfoDNCParams.nearThysList.get(i);
                String str = thysData.getCenterLatLon();
                String[] ll = str.split(",");
                Point point = new Point();
                point.lon = Double.valueOf(ll[0]);
                point.lat = Double.valueOf(ll[1]);
                Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);
                int iconWidth = ne0001000004.getWidth();
                int iconHeight = ne0001000004.getHeight();
                matrixScale.setScale(0.7f, 0.7f);// 缩放
                Bitmap mBitmapRotate = null;
                if (thysData.getTypeId().equals("NE0001000004")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0001000004, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0001000005")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0001000005, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0001000006")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0001000006, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0001000007")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0001000007, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0001000008")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0001000008, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000002")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000002, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000003")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000003, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000004")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000004, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000005")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000005, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000006")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000006, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000007")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000007, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000008")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000008, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000009")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000009, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000010")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000010, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000011")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000011, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000012")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000012, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000013")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000013, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000014")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000014, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000015")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000015, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000016")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000016, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000017")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000017, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000020")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000020, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000021")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000021, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000022")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000022, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000023")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000023, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0002000024")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0002000024, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0003000001")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0003000001, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0004000001")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0004000001, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0005000001")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0005000001, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0005000002")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0005000002, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0005000003")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0005000003, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0005000004")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0005000004, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0005000005")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0005000005, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                } else if (thysData.getTypeId().equals("NE0005000006")) {
                    mBitmapRotate = Bitmap.createBitmap(ne0005000006, 0, 0,
                            iconWidth, iconHeight, matrixScale, true);
                }
                int mBitmapRotateWidth = mBitmapRotate.getWidth();
                int mBitmapRotateHeight = mBitmapRotate.getHeight();
                /*
                 * 船舶和通航要素 上面显示的定位的图标
                 */
                Bitmap b = BitmapFactory.decodeResource(mRes, R.mipmap.lp_1);
                int w = b.getWidth();
                int h = b.getHeight();
                canvas.drawBitmap(mBitmapRotate, (float) pointOfScreen.X
                        - mBitmapRotateWidth / 2, (float) pointOfScreen.Y
                        - mBitmapRotateHeight / 2, paint);
                if (winfoDNCParams.ct.getPosition() == i) {
                    // 画通航要素和船舶上面的 选择定位图标 红色
                    canvas.drawBitmap(bitmapIconsSelect.get(winfoDNCParams.ct
                                    .getPosition()), (float) pointOfScreen.X - w / 2,
                            (float) pointOfScreen.Y - h, paint);
                } else {
                // 画通航要素和船舶上面的 定位图标 蓝色
                canvas.drawBitmap(bitmapIcons.get(i),
                        (float) pointOfScreen.X - w / 2,
                        (float) pointOfScreen.Y - h, paint);
                }
            }
        }
        // 显示轨迹
        if (isShowZhifaTrajectory) {

            // 线
            Paint paintLineTemp = new Paint();
            paintLineTemp.setAntiAlias(true);
            paintLineTemp.setColor(ContextCompat.getColor(m_context, R.color.blue));
            paintLineTemp.setStrokeWidth(3);
            // 点
            paint = new Paint();
            paint.setColor(ContextCompat.getColor(m_context, R.color.blue));
            paint.setAntiAlias(true);
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);

            Paint paintInTemp = new Paint();
            paintInTemp.setColor(ContextCompat.getColor(m_context, R.color.white));


            MobileTerminalHistory tempAisData1;// 当前轨迹点
            Point pointOfScreenTemp;
            MobileTerminalHistory tempAisDataMark = null;// 记录(标记上一个)轨迹点

            List<Point> pointsTemp = new ArrayList<>();

            if (winfoDNCParams.zhifaPointList != null) {

//                int size = winfoDNCParams.aisPointList.size();
//                int levelRatio;
//                if (winfoDNCParams.level < 5) {
//                    levelRatio = 2;
//                } else if (winfoDNCParams.level < 9
//                        && winfoDNCParams.level > 4) {
//                    levelRatio = 3;
//                } else if (winfoDNCParams.level < 14
//                        && winfoDNCParams.level > 8) {
//                    levelRatio = 4;
//                } else {
//                    levelRatio =(new Double(Math.pow(2,((18-winfoDNCParams.level)+1)))).intValue();
//                }
//                // 计算 过滤 比例
//                int ratio = size / levelRatio;

                int size = winfoDNCParams.zhifaPointList.size();
                int levelRatio;
                if (winfoDNCParams.level < 8) {
                    levelRatio = winfoDNCParams.level + 1;
                } else if (winfoDNCParams.level < 10
                        && winfoDNCParams.level > 7) {
                    levelRatio = winfoDNCParams.level * 2;
                } else if (winfoDNCParams.level < 13
                        && winfoDNCParams.level > 10) {
                    levelRatio = 10 + 20 * (winfoDNCParams.level - 10);
                } else {
                    levelRatio = 100 + 25 * (winfoDNCParams.level - 13);
                }
                // 计算 过滤 比例
                int ratio = size / levelRatio;
                if (ratio > 0) {
                    pointsTemp.clear();
                    for (int i = 0; i < winfoDNCParams.zhifaPointList.size(); i++) {
                        if (i == winfoDNCParams.zhifaPointList.size() - 1) {
                            int aisWidth = self_icon.getWidth();
                            int aisHeight = self_icon.getHeight();
                            Matrix mMatrix = new Matrix();
                            Point point = winfoDNCParams.basicAlgorithm
                                    .coordinate2ScreenPoint(
                                            winfoDNCParams.mapViewInfo.vp,
                                            winfoDNCParams.zhifaPointList.get(i)
                                                    .getLongitude().doubleValue(),
                                            winfoDNCParams.zhifaPointList.get(i)
                                                    .getLatitude().doubleValue());
                            if (winfoDNCParams.zhifaPointList.size() == 1) {
                                mMatrix.setRotate(0);
                            } else {
                                double rotate = TransformUtil.getAngle(winfoDNCParams.zhifaPointList.get(i - 1)
                                        .getLongitude().doubleValue(), winfoDNCParams.zhifaPointList.get(i - 1)
                                        .getLatitude().doubleValue(), winfoDNCParams.zhifaPointList.get(i)
                                        .getLongitude().doubleValue(), winfoDNCParams.zhifaPointList.get(i)
                                        .getLatitude().doubleValue());
                                mMatrix.setRotate((float) rotate);
                            }
                            Bitmap mBitmapRotate = Bitmap.createBitmap(
                                    self_icon, 0, 0, aisWidth, aisHeight,
                                    mMatrix, true);

                            // 最后一个船图标到倒数第二个点划线
                            canvas.drawLine((float) point.X, (float) point.Y, (float) tempAisDataMark.X, (float) tempAisDataMark.Y, paintLineTemp);

                            canvas.drawBitmap(mBitmapRotate, (float) point.X
                                    - mBitmapRotate.getWidth() / 2, (float) point.Y
                                    - mBitmapRotate.getHeight() / 2, paint);
                        }
                        if (i == 0) {
                            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                                    winfoDNCParams.mapViewInfo.vp,
                                    winfoDNCParams.zhifaPointList.get(i).getLongitude().doubleValue(),
                                    winfoDNCParams.zhifaPointList.get(i).getLatitude().doubleValue());
//								canvas.drawCircle((float) point.X,(float) point.Y, 20, paintIn);
                            canvas.drawCircle((int) point.X, (int) point.Y, 15, paint);
                            canvas.drawCircle((int) point.X, (int) point.Y, 14, paint);
                            canvas.drawCircle((int) point.X, (int) point.Y, 5, paint);

                            tempAisDataMark = winfoDNCParams.zhifaPointList.get(i);
                            tempAisDataMark.X = point.X;
                            tempAisDataMark.Y = point.Y;
                        }
                        // 开始过滤 是比例的倍数才显示 在地图上
                        if (i % ratio == 0) {
                            tempAisData1 = winfoDNCParams.zhifaPointList.get(i);
                            pointOfScreenTemp = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                    tempAisData1.getLongitude().doubleValue(), tempAisData1.getLatitude().doubleValue());
                            tempAisData1.X = pointOfScreenTemp.X;
                            tempAisData1.Y = pointOfScreenTemp.Y;

                            if (i != winfoDNCParams.zhifaPointList.size() - 1) {
                                // 否则不是 最后一个点 那么轨迹点就还没结束 就还有结束点 开始画线
//                                    int index = i + ratio;
//                                    if (index >= size) {
//                                        index = size - 1;
//                                    }
                                // 计算当前轨迹点 与上一个记录的轨迹点的距离
                                double sqrt = Math.sqrt((tempAisData1.X - tempAisDataMark.X) * (tempAisData1.X - tempAisDataMark.X) + (tempAisData1.Y - tempAisDataMark.Y) * (tempAisData1.Y - tempAisDataMark.Y));
                                if (sqrt > 150) {
                                    // 画出轨迹线 当前点画到标记点
                                    canvas.drawLine((float) tempAisData1.X, (float) tempAisData1.Y, (float) tempAisDataMark.X, (float) tempAisDataMark.Y, paintLineTemp);
                                    // 画出每个点的 中间点 白色
                                    // canvas.drawCircle((float)
                                    // trackData1.X, (float) trackData1.Y,
                                    // 4,paintIn);
                                    // 画出轨迹点
//										canvas.drawCircle((float) trackData1.X,(float) trackData1.Y, 6, paint);
                                    Point point = new Point();
                                    point.X = tempAisData1.X;
                                    point.Y = tempAisData1.Y;
                                    pointsTemp.add(point);

                                    // 画 日期
                                    Paint paintText = new Paint();
                                    paintText.setTextSize(26);
                                    paintText.setStrokeWidth(2);
                                    paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                                    canvas.drawLine((float) pointOfScreenTemp.X, (float) pointOfScreenTemp.Y, (float) pointOfScreenTemp.X + 50, (float) pointOfScreenTemp.Y - 50, paintText);
                                    canvas.drawText(tempAisData1.getCreateTime(), (float) pointOfScreenTemp.X + 50, (float) pointOfScreenTemp.Y - 50, paintText);
                                    // 重新 定义 记录的轨迹点
                                    tempAisDataMark = tempAisData1;
                                }
                            }
                        }
                    }
                } else {

                    pointsTemp.clear();
                    for (int i = 0; i < winfoDNCParams.zhifaPointList.size(); i++) {

                        tempAisData1 = winfoDNCParams.zhifaPointList.get(i);
                        //ToastUtils.showToast(m_context,"sqrt:"+tempAisData1.getLongitude());


                        pointOfScreenTemp = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                tempAisData1.getLongitude().doubleValue(), tempAisData1.getLatitude().doubleValue());
                        tempAisData1.X = pointOfScreenTemp.X;
                        tempAisData1.Y = pointOfScreenTemp.Y;
//                        LogUtil.e("++++++++++++++++++++++"+tempAisData1.X);
//                        LogUtil.e("----------------------"+tempAisData1.Y);
                        if (i == 0) {
                            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp,
                                    winfoDNCParams.zhifaPointList.get(i).getLongitude().doubleValue(),
                                    winfoDNCParams.zhifaPointList.get(i).getLatitude().doubleValue());
                            canvas.drawCircle((int) point.X, (int) point.Y, 15, paint);
                            canvas.drawCircle((int) point.X, (int) point.Y, 14, paint);
                            canvas.drawCircle((int) point.X, (int) point.Y, 5, paint);

                            tempAisDataMark = winfoDNCParams.zhifaPointList.get(i);
                            tempAisDataMark.X = point.X;
                            tempAisDataMark.Y = point.Y;
                        }
                        // 如果当前点是 最后一个点 那就画出船舶的图标 不画轨迹点
                        if (i == winfoDNCParams.zhifaPointList.size() - 1) {
                            int aisWidth = self_icon.getWidth();
                            int aisHeight = self_icon.getHeight();
                            Matrix mMatrix = new Matrix();
                            Point point = winfoDNCParams.basicAlgorithm
                                    .coordinate2ScreenPoint(
                                            winfoDNCParams.mapViewInfo.vp,
                                            winfoDNCParams.zhifaPointList.get(i)
                                                    .getLongitude().doubleValue(),
                                            winfoDNCParams.zhifaPointList.get(i)
                                                    .getLatitude().doubleValue());
                            if (winfoDNCParams.zhifaPointList.size() == 1) {
                                mMatrix.setRotate(0);
                            } else {
                                double rotate = TransformUtil.getAngle(winfoDNCParams.zhifaPointList.get(i - 1)
                                        .getLongitude().doubleValue(), winfoDNCParams.zhifaPointList.get(i - 1)
                                        .getLatitude().doubleValue(), winfoDNCParams.zhifaPointList.get(i)
                                        .getLongitude().doubleValue(), winfoDNCParams.zhifaPointList.get(i)
                                        .getLatitude().doubleValue());
                                mMatrix.setRotate((float) rotate);
                            }
                            Bitmap mBitmapRotate = Bitmap.createBitmap(
                                    self_icon, 0, 0, aisWidth, aisHeight,
                                    mMatrix, true);

                            // 最后一个船图标到倒数第二个点划线
                            canvas.drawLine((float) point.X, (float) point.Y, (float) tempAisDataMark.X, (float) tempAisDataMark.Y, paintLineTemp);

                            canvas.drawBitmap(mBitmapRotate, (float) point.X
                                    - mBitmapRotate.getWidth() / 2, (float) point.Y
                                    - mBitmapRotate.getHeight() / 2, paint);
                        } else {
                            // 否则不是 最后一个点 那么轨迹点就还没结束 就还有结束点 开始画线
                            double sqrt = Math.sqrt((tempAisData1.X - tempAisDataMark.X) * (tempAisData1.X - tempAisDataMark.X) + (tempAisData1.Y - tempAisDataMark.Y) * (tempAisData1.Y - tempAisDataMark.Y));
                            if (sqrt > 150) {
                                // 画出轨迹线 当前点画到标记点
                                canvas.drawLine((float) tempAisData1.X, (float) tempAisData1.Y, (float) tempAisDataMark.X, (float) tempAisDataMark.Y, paintLineTemp);
                                // 画出每个点的 中间点 白色
                                // 画出轨迹点
//
                                Point point = new Point();
                                point.X = tempAisData1.X;
                                point.Y = tempAisData1.Y;
                                pointsTemp.add(point);

                                // 是否画 日期
                                Paint paintText = new Paint();
                                paintText.setTextSize(26);
                                paintText.setStrokeWidth(2);
                                paintText.setColor(ContextCompat.getColor(m_context, R.color.black));
                                canvas.drawLine((float) pointOfScreenTemp.X,
                                        (float) pointOfScreenTemp.Y,
                                        (float) pointOfScreenTemp.X + 50,
                                        (float) pointOfScreenTemp.Y - 50,
                                        paintText);
                                canvas.drawText(tempAisData1.getCreateTime(),
                                        (float) pointOfScreenTemp.X + 50,
                                        (float) pointOfScreenTemp.Y - 50,
                                        paintText);

                                // 重新 定义 记录的轨迹点
                                tempAisDataMark = tempAisData1;
                            }
                        }

                    }
                }
                for (int i = 0; i < pointsTemp.size(); i++) {
                    canvas.drawCircle((int) pointsTemp.get(i).X, (int) pointsTemp.get(i).Y, 6, paint);
                    canvas.drawCircle((int) pointsTemp.get(i).X, (int) pointsTemp.get(i).Y, 5, paintInTemp);
                }
            }
        }


        // ---------------------------------显示红色方框---------------------------------------------
        if (winfoDNCParams.tipPoint != null && isShowocationFrame) {
            Point point = new Point();
            Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, winfoDNCParams.tipPoint.lon, winfoDNCParams.tipPoint.lat);
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            float aisHeight = shipBitmapIcon.getHeight();
            canvas.drawCircle((float) point.X, (float) point.Y, 2, mPaint);
            float a = aisHeight / 2;
            float b = aisHeight / 3;
            Paint paintLine = new Paint();
            paintLine.setColor(Color.RED);
            paintLine.setStrokeWidth(3);

            canvas.drawLine((float) (point.X - a), (float) (point.Y - a),
                    (float) (point.X - a + b), (float) (point.Y - a), paintLine);
            canvas.drawLine((float) (point.X - a), (float) (point.Y - a),
                    (float) (point.X - a), (float) (point.Y - a + b), paintLine);
            canvas.drawLine((float) (point.X + a), (float) (point.Y - a),
                    (float) (point.X + a - b), (float) (point.Y - a), paintLine);
            canvas.drawLine((float) (point.X + a), (float) (point.Y - a),
                    (float) (point.X + a), (float) (point.Y - a + b), paintLine);

            canvas.drawLine((float) (point.X + a), (float) (point.Y + a),
                    (float) (point.X + a - b), (float) (point.Y + a), paintLine);
            canvas.drawLine((float) (point.X + a), (float) (point.Y + a),
                    (float) (point.X + a), (float) (point.Y + a - b), paintLine);

            canvas.drawLine((float) (point.X - a), (float) (point.Y + a),
                    (float) (point.X - a + b), (float) (point.Y + a), paintLine);
            canvas.drawLine((float) (point.X - a), (float) (point.Y + a),
                    (float) (point.X - a), (float) (point.Y + a - b), paintLine);
        }

        super.dispatchDraw(canvas);
    }


    private int drawHS(Canvas canvas, int flag, Paint forecastPaint, Ais aisData, Point pointOfScreen) {
        // 得到1min后船的预测位置
        Point pointForecastOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                winfoDNCParams.mapViewInfo.vp,
                TransformUtil.ConvertDistanceToLogLat(
                        aisData.HS * 1.852 / 60,
                        aisData.JD, aisData.WD, aisData.HX)[0],
                TransformUtil.ConvertDistanceToLogLat(
                        aisData.HS * 1.852 / 60,
                        aisData.JD, aisData.WD, aisData.HX)[1]);

        if (pointForecastOfScreen.X > 0
                && pointForecastOfScreen.X < winfoDNCParams.mapViewInfo.Width
                && pointForecastOfScreen.Y > 0
                && pointForecastOfScreen.Y < winfoDNCParams.mapViewInfo.Height) {
            if (forecastPaint == null) {
                forecastPaint = new Paint();
            }
            forecastPaint.setAntiAlias(true);
            forecastPaint.setColor(Color.GRAY);
            forecastPaint.setStrokeWidth(2);
            canvas.drawLine((float) pointOfScreen.X,
                    (float) pointOfScreen.Y,
                    (float) pointForecastOfScreen.X,
                    (float) pointForecastOfScreen.Y,
                    forecastPaint);
            flag++;
        }
        return flag;
    }

    /**
     * 计算距离
     */
    @SuppressLint("FloatMath")
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 获取两点的距离
     */
    private double getDistance(int x1, int y1, int x2, int y2) {
        double d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        return Math.sqrt(d);
    }

    /***
     * 图层切换
     *
     */
    public void SwichLayer(int layersType) {
        layerType = layersType;
        invalidate();
    }

    /**
     * 获取屏幕的左下角 和右上角的经纬度
     */
    public Point[] getPointScreen() {
        Point[] points = new Point[2];
        Point pointScreenStart = winfoDNCParams.basicAlgorithm
                .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp, 0.0,
                        winfoDNCParams.dp.screenHeight);
        // System.out.println("开始点的维度："+pointScreenStart.lat+"开始的经度："+pointScreenStart.lon);
        Point pointScreenEnd = winfoDNCParams.basicAlgorithm
                .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp,
                        winfoDNCParams.dp.screenWidth, 0.0);
        // System.out.println("结束点的维度："+pointScreenEnd.lat+"结束点的经度："+pointScreenEnd.lon);
        points[0] = pointScreenStart;
        points[1] = pointScreenEnd;
        return points;
    }

    /**
     * 改变位置
     */
    public void changeMyLocation(Point myLocation) {
        winfoDNCParams.myLocation = myLocation;
        invalidate();
    }

    /**
     * 改变层级
     */
    public void changeLevel(int isZoom) {
        if (isZoom == 1) {
            if (winfoDNCParams.level < winfoDNCParams.maxLevel) {
                winfoDNCParams.level = winfoDNCParams.level + 1;
                refreshMap();
                requestLayout();
                if (winfoDNCParams.level < 14 && !showSearchtip && isShipMapActivity) {

                    BoolShowTip();
                    MainActivity activity = (MainActivity) m_context;
                    activity.dissmissAisData();
//                    if (!showais) {
//                        showaistip = false;
//                    }
                }
            } else {
                ToastUtils.showToast(m_context, "已经放大到最大级别了");
            }
        } else if (isZoom == 2) {
            if (winfoDNCParams.level > winfoDNCParams.minLevel) {
                winfoDNCParams.level = winfoDNCParams.level - 1;
                refreshMap();
                requestLayout();
                if (winfoDNCParams.level < 14 && !showais && !showSearchtip && !isShipMapActivity) {
                    BoolShowTip();
                    MainActivity activity = (MainActivity) m_context;
                    activity.dissmissAisData();
//                    if (!showais) {
//                        showaistip = false;
//                    }
                }
            } else {
                ToastUtils.showToast(m_context, "已经缩小到最小级别了");
            }
        }
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        // int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(sizeWidth, sizeHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view = getChildAt(0);
        if (view == null) {
            return;
        }
        if (winfoDNCParams.tipPoint == null) {
            return;
        }
        Point pointOfScreen = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, winfoDNCParams.tipPoint.lon, winfoDNCParams.tipPoint.lat);
        int cWidth = view.getMeasuredWidth();
        int cHeight = view.getMeasuredHeight();
        int lc = (int) (pointOfScreen.X - cWidth / 2);
        int tc = (int) (pointOfScreen.Y - cHeight - 30);
        int rc = lc + cWidth;
        int bc = tc + cHeight;

        view.layout(lc, tc, rc, bc);
    }

    @Override
    public void onClick(View v) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onThysClick(NavigableElementsData navigableElementsData) {
        showSearchtip = true;
        String position = navigableElementsData.getCenterLatLon();
        String[] strs = position.split(",");
        if (strs.length == 2) {
            winfoDNCParams.tipPoint.lon = Double.valueOf(strs[0]);
            winfoDNCParams.tipPoint.lat = Double.valueOf(strs[1]);
        }
        winfoDNCParams.tipPoint.id = null;
        View v = getChildAt(0);
        removeView(v);
        thysTipView.setTag(navigableElementsData);
        thysTipView.getBackground().setAlpha(230);
        tvThysName.setText("名称：" + navigableElementsData.getNameCn());
        if (navigableElementsData.getNavigationElementsType() != null) {//服务来不及改了 如果NavigationElementsType为null则再外面查找数据
            tvThysType.setText("类型：" + navigableElementsData.getNavigationElementsType().getTypeName());
        } else {
            tvThysType.setText("类型：" + navigableElementsData.getTypeName());
        }
        tvThysJg.setText("联系电话：" + navigableElementsData.getLinkmanTel());
        setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(m_context, R.anim.tip_in)));
        addView(thysTipView, 0);
        thysTipView.setOnClickListener(this);
    }

    @Override
    public void onSearchAisClick(Ais aisData) {
        MainActivity activity = (MainActivity) m_context;
        activity.showAisData(aisData);
        winfoDNCParams.tipPoint.lon = aisData.getJD();
        winfoDNCParams.tipPoint.lat = aisData.getWD();
        winfoDNCParams.tipPoint.id = null;
        View v = getChildAt(0);
        removeView(v);
    }

    @Override
    public void onZhiFaDataClick(MobileTerminalInfo2 mobileTerminalInfo2) {
        winfoDNCParams.tipPoint.lon = mobileTerminalInfo2.getLongitude().doubleValue();
        winfoDNCParams.tipPoint.lat = mobileTerminalInfo2.getLatitude().doubleValue();
        winfoDNCParams.tipPoint.id = null;
        View v = getChildAt(0);
        removeView(v);
        thysTipView.setTag(mobileTerminalInfo2);
        thysTipView.getBackground().setAlpha(230);
        tvThysName.setText("姓名：" + mobileTerminalInfo2.getUserName());
        tvThysType.setVisibility(GONE);
        tvThysJg.setText("联系电话：" + mobileTerminalInfo2.getPhoneNumber());
        setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(m_context, R.anim.tip_in)));
        addView(thysTipView, 0);
    }


    public List<NavigableElementsData> getNavigableElementsData() {
        return winfoDNCParams.navigableElementsData;
    }

    public List<MobileTerminalInfo2> getZhifaPersonData() {
        return winfoDNCParams.zhifaPersonData;
    }

    public List<Ais> getScreenAisData() {
        return winfoDNCParams.screenAisDatas;
    }

    public List<Ais> getAllZhifaData() {
        return winfoDNCParams.allZhifaDatas;
    }

    /**
     * 添加屏幕范围的ais数据集合显示
     *
     * @param aisDatas ais集合
     */
    @SuppressLint("SetTextI18n")
    public void addScreenAisDatas(List<Ais> aisDatas) {
        winfoDNCParams.screenAisDatas = aisDatas;
        winfoDNCParams.screenYehuoAis.clear();
        winfoDNCParams.screenKeAis.clear();
        winfoDNCParams.screenPutongAis.clear();
        winfoDNCParams.screenGongchengAis.clear();
        winfoDNCParams.screenGongzuoAis.clear();
        winfoDNCParams.screenTuoAis.clear();
        winfoDNCParams.screenYuAis.clear();
        winfoDNCParams.screenOtherAis.clear();
        for (int i = 0; i < aisDatas.size(); i++) {

            if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_YEHUO) {
                winfoDNCParams.screenYehuoAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_KE) {
                winfoDNCParams.screenKeAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_PUTONGHUO) {
                winfoDNCParams.screenPutongAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_GONGCHENG) {
                winfoDNCParams.screenGongchengAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_GONGZUO) {
                winfoDNCParams.screenGongzuoAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_TUO) {
                winfoDNCParams.screenTuoAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_YU) {
                winfoDNCParams.screenYuAis.add(aisDatas.get(i));
            } else if (AISUtils.getShipType(aisDatas.get(i).getSZCLX()) == AISUtils.AIS_QITA) {
                winfoDNCParams.screenOtherAis.add(aisDatas.get(i));
            }

            if (aisDatas.get(i).getID().equals(winfoDNCParams.tipPoint.id)) {
                MainActivity activity = (MainActivity) m_context;
                activity.refrshAis(aisDatas.get(i));
                winfoDNCParams.tipPoint.lon = aisDatas.get(i).JD;
                winfoDNCParams.tipPoint.lat = aisDatas.get(i).WD;
                // 给shipTipView设置tag 单击事件中v可获取到最新ais数据 点击tip进入ais数据页面时 数据时候最新的
                shipTipView.setTag(aisDatas.get(i));
                // 当用户点击以艘船时 会显示tip 一下代码是为了 让tip上面的数据能够实时更新 无需再次点击才可以看到最新数据
                if (aisDatas.get(i).getCM().trim().equals("")) {
                    tvShipName.setText("船名：" + "未知");
                } else {
                    tvShipName.setText("船名：" + aisDatas.get(i).getCM());
                }
                tvHxzt.setText("航行状态：" + aisDatas.get(i).getDHZT());
                tvHS.setText("船速：" + aisDatas.get(i).getHS() + "海里/小时");
                tvMMSI.setText("MMSI：" + aisDatas.get(i).getID());
                tvUpdateTime.setText("更新时间：" + aisDatas.get(i).getCJSJ());
                tvShipName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));// 加粗
                tvShipName.getPaint().setFakeBoldText(true);// 加粗
                this.requestLayout();
            }
        }
        invalidate();
    }


    /**
     * 添加附近船舶
     */
    public void addNearbyShips(List<Ais> datasList) {
        isShowNearbyShips = true;
        winfoDNCParams.nearShipList = datasList;
        winfoDNCParams.isLockAngle = false;
        if (showLongPressPoint) {
            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                    winfoDNCParams.mapViewInfo.vp,
                    winfoDNCParams.nearbyPoint.lon,
                    winfoDNCParams.nearbyPoint.lat);
            winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                    .screenPoint2Coordinate(
                            winfoDNCParams.mapViewInfo.vp,
                            point.X,
                            point.Y
                                    + (winfoDNCParams.dp.screenHeight - DimensUtils
                                    .dp2px(m_context, 54)) / 4);
        } else {
            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                    winfoDNCParams.mapViewInfo.vp,
                    winfoDNCParams.myLocationPoint.lon,
                    winfoDNCParams.myLocationPoint.lat);
            winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                    .screenPoint2Coordinate(
                            winfoDNCParams.mapViewInfo.vp,
                            point.X,
                            point.Y
                                    + (winfoDNCParams.dp.screenHeight - DimensUtils
                                    .dp2px(m_context, 54)) / 4);
        }
        refreshMap();
    }


    /**
     * 添加附近通航要素
     */
    public void addNearbyNavigationElements(List<NavigableElementsData> thysList) {
        isShowNearbyThys = true;
        winfoDNCParams.isLockAngle = false;
        winfoDNCParams.nearThysList = thysList;
        if (showLongPressPoint) {
            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                    winfoDNCParams.mapViewInfo.vp,
                    winfoDNCParams.nearbyPoint.lon,
                    winfoDNCParams.nearbyPoint.lat);
            winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                    .screenPoint2Coordinate(
                            winfoDNCParams.mapViewInfo.vp,
                            point.X,
                            point.Y
                                    + (winfoDNCParams.dp.screenHeight - DimensUtils
                                    .dp2px(m_context, 54)) / 4);
        } else {
            Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                    winfoDNCParams.mapViewInfo.vp,
                    winfoDNCParams.myLocationPoint.lon,
                    winfoDNCParams.myLocationPoint.lat);
            winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                    .screenPoint2Coordinate(
                            winfoDNCParams.mapViewInfo.vp,
                            point.X,
                            point.Y
                                    + (winfoDNCParams.dp.screenHeight - DimensUtils
                                    .dp2px(m_context, 54)) / 4);
        }
        refreshMap();
    }

    /**
     * 添加执法船所有
     *
     * @param aisDatas ais集合
     */
    @SuppressLint("SetTextI18n")
    public void addAllZhifaDatas(List<Ais> aisDatas) {
        winfoDNCParams.allZhifaDatas = aisDatas;
        for (int i = 0; i < aisDatas.size(); i++) {
            if (aisDatas.get(i).getID().equals(winfoDNCParams.tipPoint.id)) {
                MainActivity activity = (MainActivity) m_context;
                activity.refrshAis(aisDatas.get(i));
                winfoDNCParams.tipPoint.lon = aisDatas.get(i).JD;
                winfoDNCParams.tipPoint.lat = aisDatas.get(i).WD;
            }
        }
        invalidate();
    }

    /**
     * 添加全部通航要素数据
     *
     * @param navigableElementsData 通航要素
     */
    public void addNavigationElementDatas(List<NavigableElementsData> navigableElementsData) {
        winfoDNCParams.navigableElementsData = navigableElementsData;
        invalidate();
    }


    /**
     * 添加执法人员数据
     *
     * @param
     */
    public void addZhifaPersonDatas(List<MobileTerminalInfo2> mobileTerminalInfo2List) {
        winfoDNCParams.zhifaPersonData = mobileTerminalInfo2List;
        invalidate();
    }

    public void addTaskAisData(Ais ais) {
        Point po = new Point();
        po.lat = ais.getWD();
        po.lon = ais.getJD();
        Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCParams.mapViewInfo.vp, po.lon, po.lat);
        winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm.screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp, point.X, point.Y + winfoDNCParams.dp.screenHeight / 4);
        refreshMap();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public AisRequestModel getAisRequestModel() {
        AisRequestModel aisRequestModel = new AisRequestModel();
        Point pointScreenStart = getPointScreen()[0];
        Point pointScreenEnd = getPointScreen()[1];

        String point1 = String.valueOf(pointScreenStart.lon) +
                "," +
                pointScreenStart.lat;

        String point2 = String.valueOf(pointScreenEnd.lon) +
                "," +
                pointScreenEnd.lat;

        aisRequestModel.setHeight(winfoDNCParams.mapViewInfo.vp.Height + "");
        aisRequestModel.setStartPoint(point1);
        aisRequestModel.setStopPoint(point2);
        aisRequestModel.setMinX(winfoDNCParams.mapViewInfo.vp.MinX + "");
        aisRequestModel.setMinY(winfoDNCParams.mapViewInfo.vp.MinY + "");
        aisRequestModel.setPageSize("2000");
        aisRequestModel.setSpanPerPixel(winfoDNCParams.mapViewInfo.vp.SpanPerPixel + "");
        aisRequestModel.setLevel(winfoDNCParams.mapViewInfo.vp.Level + "");
        return aisRequestModel;
    }

    @Override
    public void setAisData(List<Ais> aisDatas, String typeCode, String shipTypeCode) {
        addScreenAisDatas(aisDatas);
    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {
        addAllZhifaDatas(aisData);
    }


    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {

    }

    /**
     * 船舶定位
     *
     * @param aisData ais
     */
    public void locationShip(Ais aisData) {
        if (aisData != null) {
            winfoDNCParams.isLockAngle = false;
            Point newCenterPoint = new Point();
            newCenterPoint.lon = aisData.JD;
            newCenterPoint.lat = aisData.WD;
            winfoDNCParams.centerPoint = newCenterPoint;
            winfoDNCParams.searchAisData = aisData;
            winfoDNCParams.isLocationShip = true;
            showSearchtip = false;
            refreshMap();
        }
    }

    /**
     * 任务船舶定位
     *
     * @param aisData ais
     */
    public void locationTaskShip(Ais aisData) {
        if (aisData != null) {
            winfoDNCParams.isLockAngle = false;
            winfoDNCParams.taskAisData = aisData;
            winfoDNCParams.isLocationShip = true;
            winfoDNCParams.tipPoint.lon = aisData.getJD();
            winfoDNCParams.tipPoint.lat = aisData.getWD();
            winfoDNCParams.tipPoint.id = aisData.getID();
            isShowocationFrame = true;
            refreshMap();
        }
    }

    /**
     * 通航要素定位
     *
     * @param navigableElementsData 通航要素
     */
    public void locationThsy(NavigableElementsData navigableElementsData) {
        if (navigableElementsData != null) {
            winfoDNCParams.isLockAngle = false;
            Point newCenterPoint = new Point();
            String str = navigableElementsData.getCenterLatLon();
            if (!str.equals("") && !str.contains("°")) {
                String[] strings = str.split(",");
                newCenterPoint.lon = Double.valueOf(strings[0]);
                newCenterPoint.lat = Double.valueOf(strings[1]);
                winfoDNCParams.centerPoint = newCenterPoint;
                winfoDNCParams.searchThys = navigableElementsData;
                winfoDNCParams.isLocationShip = true;
                refreshMap();
            }
        }
    }

    public int getLevel() {
        return winfoDNCParams.level;
    }

    @Override
    public void setIsShowAis(boolean bol) {
        isShowShipAis = bol;
    }

    @Override
    public void setIsShowAllZhifa(boolean bol) {

    }

    @Override
    public void setIsShowThys(boolean bol) {
        isShowThys = bol;
    }

    @Override
    public void setIsShowZhifaPerson(boolean bol) {
        isShowZhifaPerson = bol;
    }

    @Override
    public Dialog getDialog() {
        return DialogUtils.createLoadingDialog(m_context, "加载中...");
    }

    @Override
    public void setZhifaPerson(List<MobileTerminalInfo2> list) {

    }

    @Override
    public void loginExpired(String msg) {

    }

    @Override
    public void setShipPhone(String msg, TelephoneMmsi data) {

    }

    private Bitmap getShipBitmap(int shipType) {

        switch (shipType) {
            case AISUtils.AIS_KE:
                return iconKeShip;
            case AISUtils.AIS_PUTONGHUO:
                return iconPuTongHuoShip;
            case AISUtils.AIS_YEHUO:
                return iconYeHuoShip;
            case AISUtils.AIS_GONGCHENG:
                return iconGongChengShip;
            case AISUtils.AIS_GONGZUO:
                return iconGongZuoShip;
            case AISUtils.AIS_TUO:
                return iconTuoShip;
            case AISUtils.AIS_YU:
                return iconYuShip;
            case AISUtils.AIS_QITA:
                return iconOtherShip;
        }
        return null;
    }

    /**
     * 设置选中标记物 变红
     */
    public void setTarget(CustomTarget customTarget) {
        winfoDNCParams.ct = customTarget;
        winfoDNCParams.centerPoint = customTarget.getPoint();
        Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                winfoDNCParams.mapViewInfo.vp, customTarget.getPoint().lon,
                customTarget.getPoint().lat);
        winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp, point.X,
                        point.Y + winfoDNCParams.dp.screenHeight / 4);
        refreshMap();
    }


    public void addPlotting(Plotting plotting) {
        winfoDNCParams.showPlotting.add(plotting);
        winfoDNCParams.isLockAngle = false;
        String position = plotting.getLatLon();
        List<Point> points = new ArrayList<>();
        if (plotting.getDrawingType().equals("1")) {
            //点
            String[] ll = position.split(",");
            Point point = new Point();
            point.lon = Double.valueOf(ll[1]);
            point.lat = Double.valueOf(ll[0]);
            points.add(point);
        } else {
            //线，面
            String[] strs = position.split(";");
            for (String str : strs) {
                String[] ll = str.split(",");
                Point point = new Point();
                point.lon = Double.valueOf(ll[1]);
                point.lat = Double.valueOf(ll[0]);
                points.add(point);
            }
        }

        switch (plotting.getDrawingType()) {
            case "1":
                double lat1 = points.get(0).lat;
                double lon1 = points.get(0).lon;
                Point point = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                        winfoDNCParams.mapViewInfo.vp, lon1, lat1);
                winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                        .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp,
                                point.X, point.Y + winfoDNCParams.dp.screenHeight
                                        / 4);
                break;
            case "2":
                double lat2 = 0.0;
                double lon2 = 0.0;
                for (int i = 0; i < points.size(); i++) {

                    lat2 = lat2 + points.get(i).lat;
                    lon2 = lon2 + points.get(i).lon;

                }
                Point pointP_2 = new Point();
                pointP_2.lon = lon2 / points.size();
                pointP_2.lat = lat2 / points.size();
                Point point2 = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                        winfoDNCParams.mapViewInfo.vp, pointP_2.lon, pointP_2.lat);
                winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                        .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp,
                                point2.X, point2.Y + winfoDNCParams.dp.screenHeight
                                        / 4);
                break;
            case "3":
                double lat3 = 0.0;
                double lon3 = 0.0;
                for (int i = 0; i < points.size(); i++) {

                    lat3 = lat3 + points.get(i).lat;
                    lon3 = lon3 + points.get(i).lon;

                }
                Point pointP_3 = new Point();
                pointP_3.lon = lon3 / points.size();
                pointP_3.lat = lat3 / points.size();
                Point point3 = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                        winfoDNCParams.mapViewInfo.vp, pointP_3.lon, pointP_3.lat);
                winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                        .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp,
                                point3.X, point3.Y + winfoDNCParams.dp.screenHeight
                                        / 4);
                break;
        }
        refreshMap();
    }

    public void removePlotting(Plotting plotting) {
        if (winfoDNCParams.showPlotting.size() > 0) {
            for (int i = 0; i < winfoDNCParams.showPlotting.size(); i++) {
                if (winfoDNCParams.showPlotting.get(i).getId().equals(plotting.getId())) {
                    winfoDNCParams.showPlotting.remove(i);
                }
            }
        }
        refreshMap();
    }

    public void setCenter(Point point) {
        winfoDNCParams.isLockAngle = false;
        Point point2 = winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(
                winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);

        winfoDNCParams.centerPoint = winfoDNCParams.basicAlgorithm
                .screenPoint2Coordinate(winfoDNCParams.mapViewInfo.vp,
                        point2.X, point2.Y);
        refreshMap();
    }

}
