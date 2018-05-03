package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.AisService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.model
 * @Filename: NavigableElementsModel
 * @Author: lsj
 * @Date: 2017/12/20  18:47
 * @Description:
 * @Version:
 */
public class NavigableElementsModel extends BaseModel<AisService> implements INavigableElementsModel {

    public NavigableElementsModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void getThysData(Dialog dialog, final OnLoadNavigableElementsListener navigableElementsListener) {
        Map<String, String> mapParams = new HashMap<>();
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        rx.Observable<ResponseResult<List<NavigableElementsData>>> observable = mService.getThysData(params);
        Subscriber<ResponseResult<List<NavigableElementsData>>> subscriber = new DialogSubscriber<ResponseResult<List<NavigableElementsData>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<NavigableElementsData>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        navigableElementsListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        navigableElementsListener.OnLoginExpired(responseResult.getMsg());
                        break;

                    default:
                            navigableElementsListener.OnFaile(responseResult.getMsg());
                            break;
                }
            }

            @Override
            public void onFailure(String msg) {
                navigableElementsListener.OnFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    public interface OnLoadNavigableElementsListener{
        void OnSuccess(List<NavigableElementsData> navigableElementsData);
        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }
}
