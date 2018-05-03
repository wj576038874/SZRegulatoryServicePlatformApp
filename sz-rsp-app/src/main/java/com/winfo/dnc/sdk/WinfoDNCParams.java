package com.winfo.dnc.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.nearby.view.CustomTarget;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalHistory;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数模型
 *
 * @author King
 */
public class WinfoDNCParams {
    /**
     * 构造函数
     *
     * @param context context
     */
    WinfoDNCParams(Context context) {
        this.mContext = context;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        this.dp = DeviceParameters.getDeviceParameters(dm);
        //初始化加载中的背景位图
        Bitmap loadingBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.load_bg);
        this.basicAlgorithm = new BasicAlgorithm(this, loadingBitmap);
        this.screenCenterPoint = new Point();
        this.screenCenterPoint.X = this.dp.screenWidth / 2;
        this.screenCenterPoint.Y = this.dp.screenHeight / 2;

        double recentlyLongitude = Double.valueOf(PreferenceUtils.getString(context, "last_lon", "116.38"));
        double recentlyLatitude = Double.valueOf(PreferenceUtils.getString(context, "last_lat", "39.9"));
        this.centerPoint = new Point(recentlyLongitude, recentlyLatitude);
        this.mapViewInfo = this.basicAlgorithm.calculateViewInfo(layer, this.dp.screenWidth, this.dp.screenHeight, this.centerPoint.lon, this.centerPoint.lat, level);
//        float bitmapCale = this.basicAlgorithm.bitmapScale;
        Point myLocal = new Point();
        this.myLocation = myLocal;
        this.nearbyPoint = myLocal;
        this.screenAisDatas = new ArrayList<>();
        this.screenYehuoAis = new ArrayList<>();
        this.screenKeAis = new ArrayList<>();
        this.screenPutongAis = new ArrayList<>();
        this.screenGongchengAis = new ArrayList<>();
        this.screenGongzuoAis = new ArrayList<>();
        this.screenTuoAis = new ArrayList<>();
        this.screenYuAis = new ArrayList<>();
        this.screenOtherAis = new ArrayList<>();

        this.navigableElementsData = new ArrayList<>();
        this.zhifaPersonData = new ArrayList<>();
        this.allZhifaDatas = new ArrayList<>();
        this.showPlotting= new ArrayList<>();
        this.ct = new CustomTarget();
        nearShipData=new ArrayList<>();
        thysjsonData=new ArrayList<>();
    }
    public List<NavigableElementsData> thysjsonData;
    public List<Ais> nearShipData;
    public List<Plotting> showPlotting;//显示标绘的集合
//    public Plotting editPlottingPoint;//编辑的标绘点
    public Context mContext;

    public Point myLocationPoint = new Point();

    //中心点
    public Point centerPoint;

    public String[] directionDNC;
    //图层
    public int layer = 1;
    //层级
    public int level = 14;
    //最小层级
    int minLevel = 3;
    //最大层级
    int maxLevel = 18;
    //本地磁盘切片缓存路径
//	private String localStorageDir;
    //视图信息
    public MapViewInfo mapViewInfo;
    //屏幕中心点
    Point screenCenterPoint;
    //基础算法
    BasicAlgorithm basicAlgorithm;
    //设备参数
    DeviceParam dp;
    /**
     * ------------
     */
//	public AisData aisDataShip;  //定位船舶的数据点
    public boolean isLocationShip = false;//是否开启定位

//	boolean isLocationNavigationElement=false;//是否定位消息通知的通航要素
//
//	boolean isLocationMsgRegionalControl=false;//是否定位消息通知的管控区域

    public Ais searchAisData;//搜索ais

    public Ais taskAisData;//任务ais

    public NavigableElementsData searchThys;//搜索通航要素

    public Ais showAisData;

    //船舶轨迹点
//	public List<TempAisData> aisPointList;


    //执法轨迹点
    public List<MobileTerminalHistory> zhifaPointList;

    //船舶轨迹点
    public List<TrackData> trackPointList;
    //我的位置点
    public Point myLocation;
    //沿X轴方向移动的位移
    public double scale;

    //该值的变化  可以改变中心点在屏幕上的位置  0.5  位屏幕的中间  0.25 屏幕下方的1/4  0.75屏幕的上方的1/4   默认为0.5屏幕的中间
    //只有在0.5的时候 滑动地图 才不会出现问题
//	public double centerPointScale = 0.5;

    //长按 定点
    public Point nearbyPoint;

    //标绘 定点
    public Point plottingPoint;

    //是否锁定自己的视角
    public boolean isLockAngle = true;

    Point tipPoint = new Point();
    CustomTarget ct;
    /**
     * 屏幕范围 AIS数据
     */
    public List<Ais> screenAisDatas;

    public List<Ais> screenYehuoAis;
    public List<Ais> screenKeAis;
    public List<Ais> screenPutongAis;
    public List<Ais> screenGongchengAis;
    public List<Ais> screenGongzuoAis;
    public List<Ais> screenTuoAis;
    public List<Ais> screenYuAis;
    public List<Ais> screenOtherAis;

//    public List<Ais> screenGaosuAis;
    /**
     * 所有附近船舶数据
     */
    public List<Ais> nearShipList;
    /**
     * 所以附近要素数据
     */
    public List<NavigableElementsData> nearThysList;
    /**
     * 所有执法船
     */
    List<Ais> allZhifaDatas;


    /**
     * 全部通航要素数据
     */
    List<NavigableElementsData> navigableElementsData;

    /**
     * 全部执法人员数据
     */
    List<MobileTerminalInfo2> zhifaPersonData;


    public void setDp() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        this.dp = DeviceParameters.getDeviceParameters(dm);
        this.screenCenterPoint = new Point();
        this.screenCenterPoint.X = this.dp.screenWidth / 2;
        this.screenCenterPoint.Y = this.dp.screenHeight / 2;
        this.ct = new CustomTarget();
    }

}
