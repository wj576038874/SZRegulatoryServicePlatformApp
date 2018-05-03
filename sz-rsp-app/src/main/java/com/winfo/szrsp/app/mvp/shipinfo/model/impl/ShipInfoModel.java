package com.winfo.szrsp.app.mvp.shipinfo.model.impl;


import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.IShipInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.param.Params;
import com.winfo.szrsp.app.mvp.shipinfo.model.param.ParamsShip;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomationNew;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model.impl
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl.IShipInfoModel.java
 * Date: 2017/11/27 19:55
 * Description:
 */

public class ShipInfoModel extends BaseModel<ShipDataService> implements IShipInfoModel {

    public ShipInfoModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getShipInfo(final String mmsi, final String ywcm, final OnLoadShipStaticDatalitener loadShipStaticDatalitener) {
        ParamsShip paramsShip = new ParamsShip();
        paramsShip.setMmsi(mmsi);
        paramsShip.setShip_name_en(ywcm);
        paramsShip.setShip_id("");
        paramsShip.setShip_imo("");
        paramsShip.setShip_name_cn("");
        Params params = new Params();
        params.setArg0(paramsShip);
        String jsontr = FastJsonUtil.beanToJsonStr(params);
        Observable<ShipInfomation> observable = mService.getShipinfo("ZCFU", "true", jsontr);
        Subscriber<ShipInfomation> subscriber = new DialogSubscriber<ShipInfomation>(null) {
            @Override
            public void onSuccess(ShipInfomation responseResult) {
                if (responseResult != null && responseResult.getShipInfo() !=null) {
                    loadShipStaticDatalitener.onSuccess(responseResult);
                } else {
                    //如果根据mmsi和ywcm查询不到数据，再单独根据ywcm英文船名查询
                   // getShipInfoByYwcm(mmsi, ywcm, loadShipStaticDatalitener);
                    loadShipStaticDatalitener.onFailure("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                loadShipStaticDatalitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void getShipInfoNew(String mmsi, String ywcm, final OnLoadShipStaticNewDatalitener loadShipStaticNewDatalitener) {
        ParamsShip paramsShip = new ParamsShip();
        paramsShip.setMmsi(mmsi);
        paramsShip.setShip_name_en(ywcm);
        paramsShip.setShip_id("");
        paramsShip.setShip_imo("");
        paramsShip.setShip_name_cn("");
        Params params = new Params();
        params.setArg0(paramsShip);
        String jsontr = FastJsonUtil.beanToJsonStr(params);
        Observable<List<ShipInfomationNew>> observable = mService.getShipinfoNew("SZZC", "true", jsontr);
        Subscriber<List<ShipInfomationNew>> subscriber = new DialogSubscriber<List<ShipInfomationNew>>(null) {
            @Override
            public void onSuccess(List<ShipInfomationNew> responseResult) {
                if (responseResult.size() > 0) {
                    loadShipStaticNewDatalitener.onSuccess(responseResult);
                } else {
                    loadShipStaticNewDatalitener.onFailure("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                loadShipStaticNewDatalitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

//    /**
//     * 根据英文船名查询基本信息
//     *
//     * @param ywcm                      请求英文船名参数
//     * @param loadShipStaticDatalitener 接口回调
//     * @param mmsi                      mmsi如果查询不到数据 是需要传递给{getShipInfoByMmsi}作为查询参数
//     * @see #getShipInfoByMmsi(String, OnLoadShipStaticDatalitener)
//     */
//    private void getShipInfoByYwcm(final String mmsi, String ywcm, final OnLoadShipStaticDatalitener loadShipStaticDatalitener) {
//        ParamsShip paramsShip = new ParamsShip();
//        paramsShip.setShip_name_en(ywcm);
//        Params params = new Params();
//        params.setArg0(paramsShip);
//        String jsontr = FastJsonUtil.beanToJsonStr(params);
//        Observable<ShipInfomation> observable = mService.getShipinfo("SZZC", "true", jsontr);
//        Subscriber<ShipInfomation> subscriber = new DialogSubscriber<ShipInfomation>(null) {
//            @Override
//            public void onSuccess(ShipInfomation responseResult) {
//                if (responseResult.getShipInfo() != null) {
//                    loadShipStaticDatalitener.onSuccess(responseResult);
//                } else {
//                    //如果根据ywcm也查询不到数据，再单独根据mmsi查询
//                    getShipInfoByMmsi(mmsi, loadShipStaticDatalitener);
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                loadShipStaticDatalitener.onFailure(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
//    }
//
//    /**
//     * 根据mmsi查询基本信息
//     *
//     * @param mmsi                      mmsi
//     * @param loadShipStaticDatalitener 回调
//     */
//    private void getShipInfoByMmsi(String mmsi, final OnLoadShipStaticDatalitener loadShipStaticDatalitener) {
//        ParamsShip paramsShip = new ParamsShip();
//        paramsShip.setMmsi(mmsi);
//        Params params = new Params();
//        params.setArg0(paramsShip);
//        String jsontr = FastJsonUtil.beanToJsonStr(params);
//        Observable<ShipInfomation> observable = mService.getShipinfo("SZZC", "true", jsontr);
//        Subscriber<ShipInfomation> subscriber = new DialogSubscriber<ShipInfomation>(null) {
//            @Override
//            public void onSuccess(ShipInfomation responseResult) {
//                if (responseResult.getShipInfo() != null) {
//                    loadShipStaticDatalitener.onSuccess(responseResult);
//                } else {
//                    //如果根据mmsi也查询不到数据那就是没查询到数据 返回给view层显示
//                    loadShipStaticDatalitener.onFailure("没有查询到数据");
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                loadShipStaticDatalitener.onFailure(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
//    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    /**
     * 加载船舶资料的数据回调接口
     *
     * @author winfo-wj
     */
    public interface OnLoadShipStaticDatalitener {
        /**
         * 成功
         */
        void onSuccess(ShipInfomation shipInfomation);

        /**
         * 失败
         *
         * @param error 错误信息
         */
        void onFailure(String error);
    }

    /**
     * 加载船舶资料的数据回调接口
     *
     * @author winfo-wj
     */
    public interface OnLoadShipStaticNewDatalitener {
        /**
         * 成功
         */
        void onSuccess(List<ShipInfomationNew> shipInfomationNews);

        /**
         * 失败
         *
         * @param error 错误信息
         */
        void onFailure(String error);
    }
}

