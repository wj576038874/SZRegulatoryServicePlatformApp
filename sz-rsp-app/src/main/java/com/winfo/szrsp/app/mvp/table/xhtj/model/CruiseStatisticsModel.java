package com.winfo.szrsp.app.mvp.table.xhtj.model;

import android.app.Dialog;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj.model
 * @Filename: CruiseStatisticsModel
 * @Author: lsj
 * @Date: 2018/1/23  13:43
 * @Description:
 * @Version:
 */
public class CruiseStatisticsModel extends BaseModel<TableService> implements ICruiseStatisticsModel{
    public CruiseStatisticsModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void addData(Dialog dialog, CtCruiseStatisticsObject ctCruiseStatisticsObject, final OnSubCruiseStatisticsListener onSubCruiseStatisticsListener) {
        String params = FastJsonUtil.beanToJsonStr(ctCruiseStatisticsObject);
        Observable<ResponseResult<String>> observable = mService.addCruiseStatisticsData(params);
        Subscriber<ResponseResult<String>> subscriber = new DialogSubscriber<ResponseResult<String>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<String> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onSubCruiseStatisticsListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onSubCruiseStatisticsListener.OnLoginExpired(responseResult.getMsg());
                        break;

                    default:
                        onSubCruiseStatisticsListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onSubCruiseStatisticsListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    @Override
    public void findData(Dialog dialog, String id, final OnLoadCruiseStatisticsDataListener onLoadCruiseStatisticsDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<CtCruiseStatisticsObject>> observable = mService.findCruiseStatisticsData(params);
        Subscriber<ResponseResult<CtCruiseStatisticsObject>> subscriber = new DialogSubscriber<ResponseResult<CtCruiseStatisticsObject>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<CtCruiseStatisticsObject> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onLoadCruiseStatisticsDataListener.OnSuccess(responseResult.getDatas());
                        break;
                    default:
                        onLoadCruiseStatisticsDataListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadCruiseStatisticsDataListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    public interface OnSubCruiseStatisticsListener{
        void OnSuccess(String msg);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadCruiseStatisticsDataListener{
        void OnSuccess(CtCruiseStatisticsObject ctCruiseStatisticsObject);
        void OnFaile(String msg);
    }
}
