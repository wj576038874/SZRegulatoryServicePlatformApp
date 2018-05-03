package com.winfo.dnc.sdk;


import android.app.Dialog;
import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.winfo.dnc.sdk.utils.AISUtils;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.mvp.maplayer.presenter.AisPresenter;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.mvp.task.view.ShipMapActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.PreferenceUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.List;


/**
 * 手势监听
 *
 * @author King
 */
public class GestureListener extends SimpleOnGestureListener implements IMapLayerView {
    // 海图控件视图
    private WinfoDNCView winfoDNCView;
    private Context mContext;
    private AisPresenter aisPresenter;

    /**
     * 接口回调
     *
     * @author winfo-wj
     */

    public interface OnClickListenner {
//        /**
//         * 船舶ais点击事件
//         *
//         * @param aisData 船舶的数据
//         */
//        void onShipClick(Ais aisData);

        /**
         * 通航要素的点击事件
         *
         * @param navigableElementsData 通航要素的模型数据
         */
        void onThysClick(NavigableElementsData navigableElementsData);

        void onSearchAisClick(Ais aisData);

        void onZhiFaDataClick(MobileTerminalInfo2 mobileTerminalInfo2);
    }

    private OnClickListenner onClickListenner;

    void setOnClickListenner(OnClickListenner onClickListenner) {
        this.onClickListenner = onClickListenner;
    }

//    public interface AisDataOnClickListenner {
//        /**
//         * 船舶ais点击事件
//         *
//         * @param aisData 船舶的数据
//         */
//        void onShipClick(Ais aisData);
//    }
//
//    AisDataOnClickListenner aisDataOnClickListenner;
//
//    void setAisDataOnClickListenner(AisDataOnClickListenner aisDataOnClickListenner) {
//        this.aisDataOnClickListenner = aisDataOnClickListenner;
//    }

    GestureListener(WinfoDNCView winfoDNCView, Context m_context) {
        this.winfoDNCView = winfoDNCView;
        this.mContext = m_context;
        aisPresenter = new AisPresenter(this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    /**
     * Touch了滑动一点距离后，up时触发。
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

//        if(winfoDNCView.is_select_plotting_point&&winfoDNCView.winfoDNCParams.plottingPoint!=null){
//            return false;
//        }

        winfoDNCView.isOnFling = true;
        double disX = e2.getX() - e1.getX();
        double disY = e2.getY() - e1.getY();

        long downTime = e1.getDownTime();
        long eventTime = e2.getEventTime();
        //时间差
        long disTime = eventTime - downTime;
        //计算速度   每毫秒移动px
        vX = disX / disTime;
        vY = disY / disTime;
//	 vX=	velocityX/1000;
//	 vY=	velocityY/1000;

        //快速滑动时 不是直接 移动坐标 而是 建给速度 然后速度减少
//	LogUtil.e("vX:"+disX/disTime+"vY:"+disY/disTime+"*********velocityX:"+velocityX+"velocityY:"+velocityY);

        //计算 滑动时间
        duration = (float) (Math.sqrt(velocityX * velocityX + velocityY * velocityY) / 10);
        //最多滑动300毫秒
        if (duration > 300) {
            duration = 300;
        }

        //计算加速度
        aX = (float) (vX / duration);
        aY = (float) (vY / duration);


//	
//	winfoDNCView.dragX = winfoDNCView.dragX +duration*vX;
//	winfoDNCView.dragY = winfoDNCView.dragY +duration*vY;

        // 如果屏幕没有移动16毫秒刷新一次AIS数据
        winfoDNCView.postDelayed(mRunnable, 16);
        return false;
    }

    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {

            if (duration > 0) {

                float disX = (float) (vX * 16 + aX * 16 * 8);
                float disY = (float) (vY * 16 + aY * 16 * 8);
                winfoDNCView.dragX = winfoDNCView.dragX + disX;
                winfoDNCView.dragY = winfoDNCView.dragY + disY;

                winfoDNCView.postDelayed(this, 16);
                winfoDNCView.refreshMap();
                winfoDNCView.requestLayout();
                duration -= 16;
                vX = vX - aX * 16;
                vY = vY - aY * 16;
            } else {
                winfoDNCView.getAisDate();
            }

        }
    };
    private float aX;
    private float aY;
    private float duration;
    private double vX;
    private double vY;

    @Override
    public void onLongPress(MotionEvent e) {
        winfoDNCView.winfoDNCParams.isLockAngle = false;
        winfoDNCView.winfoDNCParams.nearbyPoint = winfoDNCView.winfoDNCParams.basicAlgorithm.screenPoint2Coordinate(winfoDNCView.winfoDNCParams.mapViewInfo.vp, e.getX(), e.getY());
//        winfoDNCView.winfoDNCParams.centerPoint = winfoDNCView.winfoDNCParams.nearbyPoint;
        winfoDNCView.showLongPressPoint = true;
        winfoDNCView.refreshMap();
        //保存经纬度
        if (winfoDNCView.winfoDNCParams.nearbyPoint.lon != 0.0 && winfoDNCView.winfoDNCParams.nearbyPoint.lat != 0.0) {
            PreferenceUtils.setString(mContext, "nearbyPointLon", winfoDNCView.winfoDNCParams.nearbyPoint.lon + "");
            PreferenceUtils.setString(mContext, "nearbyPointLat", winfoDNCView.winfoDNCParams.nearbyPoint.lat + "");
        }
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //当用户移动地图的时候  就清除锁定自己的视角
        winfoDNCView.winfoDNCParams.isLockAngle = false;
        if (mContext instanceof MainActivity) {
            MainActivity activity = (MainActivity) mContext;
            activity.changeImgbtnBg();
        }
        winfoDNCView.dragX = winfoDNCView.dragX - distanceX;
        winfoDNCView.dragY = winfoDNCView.dragY - distanceY;
        winfoDNCView.refreshMap();
        winfoDNCView.requestLayout();

//        //放开 可拖动标绘点
//        float clickX = e1.getX();
//        float clickY = e1.getY();
//        float clickX2 = e2.getX();
//        float clickY2 = e2.getY();
//        if(winfoDNCView.is_select_plotting_point&&winfoDNCView.winfoDNCParams.plottingPoint!=null){
//            Point point = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, winfoDNCView.winfoDNCParams.plottingPoint.lon, winfoDNCView.winfoDNCParams.plottingPoint.lat);
//            if (clickX > point.X - 120 && clickX < point.X + 120 && clickY > point.Y - 120 && clickY < point.Y + 120) {
//                winfoDNCView.winfoDNCParams.plottingPoint = winfoDNCView.winfoDNCParams.basicAlgorithm.screenPoint2Coordinate(winfoDNCView.winfoDNCParams.mapViewInfo.vp, e2.getX(), e2.getY());
//                winfoDNCView.refreshMap();
//                winfoDNCView.requestLayout();
//            }
//            else if(clickX2 > point.X - 120 && clickX2 < point.X + 120 && clickY2 > point.Y - 120 && clickY2 < point.Y + 120){
//                winfoDNCView.winfoDNCParams.plottingPoint = winfoDNCView.winfoDNCParams.basicAlgorithm.screenPoint2Coordinate(winfoDNCView.winfoDNCParams.mapViewInfo.vp, e2.getX(), e2.getY());
//                winfoDNCView.refreshMap();
//                winfoDNCView.requestLayout();
//            }
//
//
//            else {
//                winfoDNCView.winfoDNCParams.isLockAngle = false;
//                if (mContext instanceof MainActivity) {
//                    MainActivity activity = (MainActivity) mContext;
//                    activity.changeImgbtnBg();
//                }
//                winfoDNCView.dragX = winfoDNCView.dragX - distanceX;
//                winfoDNCView.dragY = winfoDNCView.dragY - distanceY;
//                winfoDNCView.refreshMap();
//                winfoDNCView.requestLayout();
//            }
//
//        }else {
//            winfoDNCView.winfoDNCParams.isLockAngle = false;
//            if (mContext instanceof MainActivity) {
//                MainActivity activity = (MainActivity) mContext;
//                activity.changeImgbtnBg();
//            }
//            winfoDNCView.dragX = winfoDNCView.dragX - distanceX;
//            winfoDNCView.dragY = winfoDNCView.dragY - distanceY;
//            winfoDNCView.refreshMap();
//            winfoDNCView.requestLayout();
//        }

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent arg0) {
        if (winfoDNCView.winfoDNCParams.level < winfoDNCView.winfoDNCParams.maxLevel) {
            winfoDNCView.changeLevel(1);

            if (winfoDNCView.isShowShipAis) {
                if (winfoDNCView.getLevel() > 13) {
                    aisPresenter.getShipAisData(null, false, null, null);//不显示加载对话框
                } else if (winfoDNCView.getLevel() > 11 && winfoDNCView.getLevel() < 14) {
                    aisPresenter.getShipAisData(null, false, null, null);
                }
            }

            winfoDNCView.requestLayout();

        } else {
            ToastUtils.showToast(mContext, "已经放大到最大级别了");
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        if (!WinfoDNCView.isShowNearby) {
            winfoDNCView.showLongPressPoint = false;
            PreferenceUtils.setString(SzRspApplication.getContext(), "nearbyPointLon", "0.0");
            PreferenceUtils.setString(SzRspApplication.getContext(), "nearbyPointLat", "0.0");
            winfoDNCView.invalidate();
        }
        float clickX = event.getX();
        float clickY = event.getY();


        View v = winfoDNCView.getChildAt(0);
        MainActivity mainActivity = null;
        ShipMapActivity shipMapActivity = null;
        if (mContext instanceof MainActivity) {
            mainActivity = (MainActivity) mContext;
        } else if (mContext instanceof ShipMapActivity) {
            shipMapActivity = (ShipMapActivity) mContext;
        }


        //选择标绘点
        if (winfoDNCView.is_select_plotting_point) {
            winfoDNCView.winfoDNCParams.isLockAngle = false;
            winfoDNCView.winfoDNCParams.plottingPoint = winfoDNCView.winfoDNCParams.basicAlgorithm.screenPoint2Coordinate(winfoDNCView.winfoDNCParams.mapViewInfo.vp, event.getX(), event.getY());
            //winfoDNCView.winfoDNCParams.centerPoint = winfoDNCView.winfoDNCParams.plottingPoint;
            winfoDNCView.refreshMap();
            mainActivity.showConfirmPlotting();
            return super.onSingleTapConfirmed(event);
        }

        //执法船的点击事件----------------------------
        Ais behaviorAisZhifa = null;
        if (winfoDNCView.isShowaAllZhifa && winfoDNCView.winfoDNCParams.level >= 6) {
            for (Ais aisData : winfoDNCView.winfoDNCParams.allZhifaDatas) {
                Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                Point point = new Point();
                point.X = pointOfScreen.X;
                point.Y = pointOfScreen.Y;
                if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                    behaviorAisZhifa = aisData;
                    showShipPhone(behaviorAisZhifa.getID());
                    break;
                } else {
                    behaviorAisZhifa = null;
                }
            }
            if (behaviorAisZhifa != null) {
                winfoDNCView.removeView(v);
                showAisDataView(behaviorAisZhifa);
                winfoDNCView.showaistip = false;
                winfoDNCView.isShowocationFrame = true;
                return false;
            } else {
                winfoDNCView.isShowocationFrame = false;
                winfoDNCView.showaistip = true;
            }
        }

        //屏幕范围的AIS点击事件---------------------------
        Ais behaviorAis = null;
        boolean bol = false;//判断 如果有一艘船舶被点击了 那么后续的判断不再执行以免
        if (winfoDNCView.isShowShipAis && winfoDNCView.winfoDNCParams.level >= 14 && !winfoDNCView.isShowShipTrack) {


            if (!bol && winfoDNCView.isShowShipAis_yehuo) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenYehuoAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }

            if (!bol && winfoDNCView.isShowShipAis_ke) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenKeAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }

            if (!bol && winfoDNCView.isShowShipAis_putonghuo) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenPutongAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }
            if (!bol && winfoDNCView.isShowShipAis_gongcheng) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenGongchengAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }
            if (!bol && winfoDNCView.isShowShipAis_gongzuo) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenGongzuoAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }

            if (!bol && winfoDNCView.isShowShipAis_tuo) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenTuoAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }

            if (!bol && winfoDNCView.isShowShipAis_yu) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenYuAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        bol = true;
                        break;
                    } else {
                        bol = false;
                        behaviorAis = null;
                    }
                }
            }

            if (!bol && winfoDNCView.isShowShipAis_qita) {
                for (Ais aisData : winfoDNCView.winfoDNCParams.screenOtherAis) {
                    Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
                    Point point = new Point();
                    point.X = pointOfScreen.X;
                    point.Y = pointOfScreen.Y;
                    if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                        behaviorAis = aisData;
                        showShipPhone(behaviorAis.getID());
                        break;
                    } else {
                        behaviorAis = null;
                    }
                }
            }

            if (behaviorAis != null) {
                winfoDNCView.removeView(v);

                if (winfoDNCView.isShowShipAis_yehuo) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_YEHUO) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_ke) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_KE) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_putonghuo) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_PUTONGHUO) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_gongcheng) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_GONGCHENG) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_gongzuo) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_GONGZUO) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_tuo) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_TUO) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_yu) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_YU) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
                if (winfoDNCView.isShowShipAis_qita) {
                    if (AISUtils.getShipType(behaviorAis.getSZCLX()) == AISUtils.AIS_QITA) {
                        showAisDataView(behaviorAis);
                        return false;
                    }
                }
            } else {
                winfoDNCView.showaistip = false;
                winfoDNCView.isShowocationFrame = false;
            }
        }

        //锁定一艘船时，所辖到1-14层级的时候的点击事件---------------------------
        if (winfoDNCView.showaistip && winfoDNCView.winfoDNCParams.showAisData != null) {
            Ais aisData = winfoDNCView.winfoDNCParams.showAisData;
            Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, aisData.JD, aisData.WD);
            Point point = new Point();
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                winfoDNCView.showais = true;
                showAisDataView(aisData);
                winfoDNCView.removeView(v);
                winfoDNCView.isShowocationFrame = true;
                return false;
            } else {
                winfoDNCView.isShowocationFrame = false;
                winfoDNCView.showais = false;
            }
        }

        //通航要素的点击事件---------------------------
        NavigableElementsData behaviorNavigableElementsData = null;
        if (winfoDNCView.isShowThys && winfoDNCView.winfoDNCParams.level >= 14) {
            Point point = new Point();
            for (NavigableElementsData navigableElementsData : winfoDNCView.winfoDNCParams.navigableElementsData) {
                String position = navigableElementsData.getCenterLatLon();
                String[] ll = position.split(",");
                if (ll.length == 2) {
                    if (!ll[0].contains("°")) {
                        point.lon = Double.valueOf(ll[0]);
                        point.lat = Double.valueOf(ll[1]);
                        Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, point.lon, point.lat);
                        point.X = pointOfScreen.X;
                        point.Y = pointOfScreen.Y;
                        if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                            behaviorNavigableElementsData = navigableElementsData;
                            break;
                        } else {
                            behaviorNavigableElementsData = null;
                        }
                    }
                }
            }
            if (behaviorNavigableElementsData != null) {
                onClickListenner.onThysClick(behaviorNavigableElementsData);
                winfoDNCView.isShowocationFrame = true;
                assert mainActivity != null;
                mainActivity.dissmissAisData();
                return false;
            } else {
                winfoDNCView.removeView(v);
                winfoDNCView.isShowocationFrame = false;
            }
        }

        //执法人员点击事件---------------------------
        MobileTerminalInfo2 behaviorMobileTerminalInfo = null;
        if (winfoDNCView.isShowZhifaPerson && winfoDNCView.winfoDNCParams.zhifaPersonData != null) {
            Point newpoint = new Point();
            for (MobileTerminalInfo2 mobileTerminalInfo2 : winfoDNCView.winfoDNCParams.zhifaPersonData) {
                newpoint.lon = mobileTerminalInfo2.getLongitude().doubleValue();
                newpoint.lat = mobileTerminalInfo2.getLatitude().doubleValue();
                Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, newpoint.lon, newpoint.lat);
                newpoint.X = pointOfScreen.X;
                newpoint.Y = pointOfScreen.Y;
                if (clickX > newpoint.X - 36 && clickX < newpoint.X + 36 && clickY > newpoint.Y - 36 && clickY < newpoint.Y + 36) {
                    behaviorMobileTerminalInfo = mobileTerminalInfo2;
                    break;
                } else {
                    behaviorMobileTerminalInfo = null;
                    mainActivity.dissmissAisData();
                    winfoDNCView.removeView(v);
                }
            }
            if (behaviorMobileTerminalInfo != null) {
                assert mainActivity != null;
                mainActivity.dissmissAisData();
                winfoDNCView.removeView(v);
                onClickListenner.onZhiFaDataClick(behaviorMobileTerminalInfo);
                winfoDNCView.isShowocationFrame = true;
                return false;
            } else {
                winfoDNCView.isShowocationFrame = false;
            }
        }

        //主界面搜索船舶的点击事件---------------------------
        if (winfoDNCView.winfoDNCParams.searchAisData != null) {
            Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, winfoDNCView.winfoDNCParams.searchAisData.getJD(), winfoDNCView.winfoDNCParams.searchAisData.getWD());
            Point point = new Point();
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                if (!winfoDNCView.isTaskShip) {
                    onClickListenner.onSearchAisClick(winfoDNCView.winfoDNCParams.searchAisData);
                    winfoDNCView.isShowocationFrame = true;
                    assert mainActivity != null;
                    mainActivity.showAisData(winfoDNCView.winfoDNCParams.searchAisData);
                    return false;
                }
            } else {
                winfoDNCView.removeView(v);
                winfoDNCView.isShowocationFrame = false;
                winfoDNCView.isShowocationFrame = false;
            }
        }

        //任务船舶定位点击---------------------------
        if (winfoDNCView.winfoDNCParams.taskAisData != null) {
            Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, winfoDNCView.winfoDNCParams.taskAisData.getJD(), winfoDNCView.winfoDNCParams.taskAisData.getWD());
            Point point = new Point();
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                if (shipMapActivity != null) {
                    shipMapActivity.showAisData();
                }
                winfoDNCView.winfoDNCParams.tipPoint.lon = winfoDNCView.winfoDNCParams.taskAisData.getJD();
                winfoDNCView.winfoDNCParams.tipPoint.lat = winfoDNCView.winfoDNCParams.taskAisData.getWD();
                winfoDNCView.winfoDNCParams.tipPoint.id = winfoDNCView.winfoDNCParams.taskAisData.getID();
                winfoDNCView.isShowocationFrame = true;
                winfoDNCView.refreshMap();
                return false;
            } else {
                if (shipMapActivity != null) {
                    shipMapActivity.dissmissAisData();
                }
                winfoDNCView.removeView(v);
                winfoDNCView.isShowocationFrame = false;
                winfoDNCView.refreshMap();
            }
        }

        //搜索的通航要素的点击事件---------------------------
        if (winfoDNCView.winfoDNCParams.searchThys != null) {
            Point newpoint = new Point();
            String position = winfoDNCView.winfoDNCParams.searchThys.getCenterLatLon();
            String[] strs = position.split(",");
            newpoint.lon = Double.valueOf(strs[0]);
            newpoint.lat = Double.valueOf(strs[1]);
            Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, newpoint.lon, newpoint.lat);
            Point point = new Point();
            point.X = pointOfScreen.X;
            point.Y = pointOfScreen.Y;
            if (clickX > point.X - 36 && clickX < point.X + 36 && clickY > point.Y - 36 && clickY < point.Y + 36) {
                onClickListenner.onThysClick(winfoDNCView.winfoDNCParams.searchThys);
                winfoDNCView.isShowocationFrame = true;
                return false;
            } else {
                winfoDNCView.removeView(v);
                winfoDNCView.isShowocationFrame = false;
            }
        }

        if (mainActivity != null) {
            mainActivity.dissmissAisData();
        }

        //---------------------附近搜索出来的通航要素 点击弹出信息-----------------------
        if (winfoDNCView.winfoDNCParams.nearThysList != null) {
            for (NavigableElementsData data : winfoDNCView.winfoDNCParams.nearThysList) {
                String str = data.getCenterLatLon();
                String[] ll = str.split(",");
                Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, Double.valueOf(ll[0]), Double.valueOf(ll[1]));
                if (clickX > pointOfScreen.X - 24 && clickX < pointOfScreen.X + 24 && clickY > pointOfScreen.Y - 48 && clickY < pointOfScreen.Y) {
                    mainActivity.showNearThys(data);
                }
            }
        }

        //---------------------附近搜索出来的船舶 点击弹出AIS信息-----------------------
//        if (winfoDNCView.winfoDNCParams.nearShipList != null) {
//            for (Ais data : winfoDNCView.winfoDNCParams.nearShipList) {
//                Point pointOfScreen = winfoDNCView.winfoDNCParams.basicAlgorithm.coordinate2ScreenPoint(winfoDNCView.winfoDNCParams.mapViewInfo.vp, data.getJD(), data.getWD());
//                if (clickX > pointOfScreen.X - 24 && clickX < pointOfScreen.X + 24 && clickY > pointOfScreen.Y - 48 && clickY < pointOfScreen.Y) {
//                mainActivity.showNearShipAis(data);
////                    ToastUtils.showToast(mContext, data.getCM());
//                }
//            }
//        }

        return super.onSingleTapConfirmed(event);
    }

    private void showAisDataView(Ais aisData) {
        winfoDNCView.showaistip = true;
        winfoDNCView.winfoDNCParams.showAisData = aisData;
        winfoDNCView.winfoDNCParams.tipPoint.lon = aisData.getJD();
        winfoDNCView.winfoDNCParams.tipPoint.lat = aisData.getWD();
        winfoDNCView.winfoDNCParams.tipPoint.id = aisData.getID();
        winfoDNCView.isShowocationFrame = true;
        winfoDNCView.refreshMap();
        MainActivity activity = (MainActivity) mContext;
        activity.showAisData(aisData);
    }


    private void showShipPhone(String mmsi) {
        MainActivity activity = (MainActivity) mContext;
        activity.showShipPhone(mmsi);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(mContext, msg);
    }

    @Override
    public AisRequestModel getAisRequestModel() {
        AisRequestModel aisRequestModel = new AisRequestModel();
        Point pointScreenStart = winfoDNCView.getPointScreen()[0];
        Point pointScreenEnd = winfoDNCView.getPointScreen()[1];

        String point1 = String.valueOf(pointScreenStart.lon) +
                "," +
                pointScreenStart.lat;

        String point2 = String.valueOf(pointScreenEnd.lon) +
                "," +
                pointScreenEnd.lat;

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
        winfoDNCView.invalidate();
    }

    @Override
    public void setAllZhifaData(List<Ais> aisData) {
        winfoDNCView.addAllZhifaDatas(aisData);
        winfoDNCView.invalidate();
    }


    @Override
    public void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId) {

    }

    @Override
    public void setIsShowAis(boolean bol) {
        winfoDNCView.isShowShipAis = bol;
    }

    @Override
    public void setIsShowAllZhifa(boolean bol) {
        winfoDNCView.isShowaAllZhifa = bol;
    }

    @Override
    public void setIsShowThys(boolean bol) {
        winfoDNCView.isShowThys = bol;
    }

    @Override
    public void setIsShowZhifaPerson(boolean bol) {
        winfoDNCView.isShowZhifaPerson = bol;
    }

    @Override
    public Dialog getDialog() {
        return DialogUtils.createLoadingDialog(mContext, "加载中...");
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

}
