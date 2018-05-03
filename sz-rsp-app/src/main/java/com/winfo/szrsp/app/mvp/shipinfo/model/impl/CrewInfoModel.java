package com.winfo.szrsp.app.mvp.shipinfo.model.impl;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.shipinfo.model.ICrewInfoModel;
import com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.ShipDataService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model.impl
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.impl.CrewInfoModel.java
 * Date: 2017/12/22 10:47
 * Description:
 */

public class CrewInfoModel extends BaseModel<ShipDataService> implements ICrewInfoModel {

    public CrewInfoModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void loadCrewInfo(String mmsi, String ywcm, final OnLoadCrewInfoListener onLoadCrewInfoListener) {
        Map<String, String> map = new HashMap<>();
        map.put("mmsi", mmsi);
        map.put("ywcm", ywcm);
        String params = FastJsonUtil.mapToJsonStr(map);
        Observable<ResponseResult<List<CrewInfo>>> observable = mService.getCrewInfoByMmsiOrYwcm(params);
        Subscriber<ResponseResult<List<CrewInfo>>> subscriber = new DialogSubscriber<ResponseResult<List<CrewInfo>>>(null) {
            @Override
            public void onSuccess(ResponseResult<List<CrewInfo>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadCrewInfoListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadCrewInfoListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadCrewInfoListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadCrewInfoListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void onRequestCancel() {
        super.unSubscribeRequest();
    }

    public interface OnLoadCrewInfoListener {
        void onSuccess(List<CrewInfo> crewInfos);

        void onFailure(String msg);
        void OnLoginExpired(String msg);
    }
}
