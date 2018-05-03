package com.winfo.szrsp.app.mvp.shipinfo.model.impl;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.ILoydsInfoModel;
import com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoShiShip;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model.impl
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl;.LoydsInfoModel.java
 * Date: 2017/12/21 14:52
 * Description:
 */
public class LoydsInfoModel extends BaseModel<ShipDataService> implements ILoydsInfoModel {

    public LoydsInfoModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getLowsData(String mmsi, String ywcm, final OnLoadLowsDataListenner onLoadLowsDataListenner) {
        Map<String, String> map = new HashMap<>();
        map.put("mmsi", mmsi);
        map.put("shipName", ywcm);
        String params = FastJsonUtil.mapToJsonStr(map);
        Observable<ResponseResult<LaoShiShip>> observable = mService.getShipLoadsInfoByMmsiOrYwcm(params);
        Subscriber<ResponseResult<LaoShiShip>> subscriber = new DialogSubscriber<ResponseResult<LaoShiShip>>(null) {
            @Override
            public void onSuccess(ResponseResult<LaoShiShip> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadLowsDataListenner.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadLowsDataListenner.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadLowsDataListenner.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadLowsDataListenner.onFailure(msg);
            }
        };

        toSubscribe(observable, subscriber);
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    public interface OnLoadLowsDataListenner {

        void onSuccess(LaoShiShip laoShiShip);

        void onFailure(String msg);
        void OnLoginExpired(String msg);
    }
}
