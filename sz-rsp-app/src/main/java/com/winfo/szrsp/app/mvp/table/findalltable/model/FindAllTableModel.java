package com.winfo.szrsp.app.mvp.table.findalltable.model;

import android.app.Dialog;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.ListCBWFXCData;
import com.winfo.szrsp.app.sdk.entity.table.ListContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.ListCruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.ListDXSHCData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.ListElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.ListGoodSecneOutRestServiceData;
import com.winfo.szrsp.app.sdk.entity.table.ListPSCData;
import com.winfo.szrsp.app.sdk.entity.table.ListWatersPatrolData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbjdjgData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbxcData;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.ListXHTJData;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.TableService;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import cn.jiguang.analytics.android.api.BrowseEvent;
import rx.Observable;
import rx.Subscriber;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.model
 * @Filename: FindAllTableModel
 * @Author: lsj
 * @Date: 2017/12/7  9:43
 * @Description:
 * @Version:
 */
public class FindAllTableModel extends BaseModel<TableService> implements IFindAllTableModel {
    public FindAllTableModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void findJDBGData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllJDBGListener onLoadFindAllJDBGListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListcbjdjgData>> observable = mService.findJDBGData(params);
        Subscriber<ResponseResult<ListcbjdjgData>> subscriber = new DialogSubscriber<ResponseResult<ListcbjdjgData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListcbjdjgData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllJDBGListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllJDBGListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllJDBGListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllJDBGListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findCBXCData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllCBXCListener onLoadFindAllCBXCListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListcbxcData>> observable = mService.findCBXCData(params);
        Subscriber<ResponseResult<ListcbxcData>> subscriber = new DialogSubscriber<ResponseResult<ListcbxcData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListcbxcData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllCBXCListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllCBXCListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllCBXCListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllCBXCListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findDXSHCData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllDXSHCListener onLoadFindAllDXSHCListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListDXSHCData>> observable = mService.findDXSHCData(params);
        Subscriber<ResponseResult<ListDXSHCData>> subscriber = new DialogSubscriber<ResponseResult<ListDXSHCData>>(dialog, true) {
            @Override
            public void onSuccess(ResponseResult<ListDXSHCData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllDXSHCListener.OnSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllDXSHCListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllDXSHCListener.OnFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllDXSHCListener.OnFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findWatersPatrolData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllWatersPatrolListener onLoadFindAllWatersPatrolListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListWatersPatrolData>> observable = mService.findWatersPatrolData(params);
        Subscriber<ResponseResult<ListWatersPatrolData>> subscriber = new DialogSubscriber<ResponseResult<ListWatersPatrolData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListWatersPatrolData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllWatersPatrolListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllWatersPatrolListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllWatersPatrolListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllWatersPatrolListener.onFailure(msg);

            }
        };
        toSubscribe(observable, subscriber);

    }

    @Override
    public void findPSCData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllPSCDataListener onLoadFindAllPSCDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListPSCData>> observable = mService.findPSCData(params);
        Subscriber<ResponseResult<ListPSCData>> subscriber = new DialogSubscriber<ResponseResult<ListPSCData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListPSCData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllPSCDataListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllPSCDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllPSCDataListener.onFialure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllPSCDataListener.onFialure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findXhtjDatya(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllXHTJDataListener onLoadFindAllXHTJDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListXHTJData>> observable = mService.findXHTJData(params);
        Subscriber<ResponseResult<ListXHTJData>> subscriber = new DialogSubscriber<ResponseResult<ListXHTJData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListXHTJData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllXHTJDataListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllXHTJDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllXHTJDataListener.onFaile(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllXHTJDataListener.onFaile(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findElectronicCruiseException(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllElectronicCruiseExceptionListener onLoadFindAllElectronicCruiseExceptionListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListElectronicCruiseException>> observable = mService.findElectronicCruiseException(params);
        Subscriber<ResponseResult<ListElectronicCruiseException>> subscriber = new DialogSubscriber<ResponseResult<ListElectronicCruiseException>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListElectronicCruiseException> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllElectronicCruiseExceptionListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllElectronicCruiseExceptionListener.OnLoginExpired(responseResult.getMsg());
                        break;

                    default:
                        onLoadFindAllElectronicCruiseExceptionListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllElectronicCruiseExceptionListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findCBWFData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllCBWFXCDataListener onLoadFindAllCBWFXCDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListCBWFXCData>> observable = mService.findCBWFData(params);
        Subscriber<ResponseResult<ListCBWFXCData>> subscriber = new DialogSubscriber<ResponseResult<ListCBWFXCData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListCBWFXCData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllCBWFXCDataListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllCBWFXCDataListener.OnLoginExpired(responseResult.getMsg());
                        break;

                    default:
                        onLoadFindAllCBWFXCDataListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllCBWFXCDataListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findGoodSecneOutRestServiceData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllGoodSecneOutRestServiceListener onLoadFindAllGoodSecneOutRestServiceListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListGoodSecneOutRestServiceData>> observable = mService.findGoodSecneOutRestServiceData(params);
        Subscriber<ResponseResult<ListGoodSecneOutRestServiceData>> subscriber = new DialogSubscriber<ResponseResult<ListGoodSecneOutRestServiceData>>(dialog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListGoodSecneOutRestServiceData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllGoodSecneOutRestServiceListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllGoodSecneOutRestServiceListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllGoodSecneOutRestServiceListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllGoodSecneOutRestServiceListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);


    }

    @Override
    public void findDangerousGoodsXianChangData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllDangerousGoodsXianChangListener onLoadFindAllDangerousGoodsXianChangListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListDangerousGoodsXianChangData>> observable = mService.findDangerusGoodsXianChangData(params);
        Subscriber<ResponseResult<ListDangerousGoodsXianChangData>> subscriber = new DialogSubscriber<ResponseResult<ListDangerousGoodsXianChangData>>(dailog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListDangerousGoodsXianChangData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllDangerousGoodsXianChangListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllDangerousGoodsXianChangListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllDangerousGoodsXianChangListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllDangerousGoodsXianChangListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findDangerousGoodsKaiXiangData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, final OnLoadFindAllDangerousGoodsKaiXiangListener onLoadFindAllDangerousGoodsKaiXiangListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListDangerousGoodsKaiXiangData>> observable = mService.findDangerusGoodsKaiXiangData(params);
        Subscriber<ResponseResult<ListDangerousGoodsKaiXiangData>> subscriber = new DialogSubscriber<ResponseResult<ListDangerousGoodsKaiXiangData>>(dailog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListDangerousGoodsKaiXiangData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadFindAllDangerousGoodsKaiXiangListener.onSuccess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadFindAllDangerousGoodsKaiXiangListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadFindAllDangerousGoodsKaiXiangListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadFindAllDangerousGoodsKaiXiangListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void findContainerWeightInspectData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, final OnFindAllContainerWeightInspectListener onFindAllContainerWeightInspectListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListContainerWeightInspectData>> observable = mService.findContainerWeightInspectData(params);
        Subscriber<ResponseResult<ListContainerWeightInspectData>> subscriber = new DialogSubscriber<ResponseResult<ListContainerWeightInspectData>>(dailog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListContainerWeightInspectData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onFindAllContainerWeightInspectListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onFindAllContainerWeightInspectListener.OnLoginExpired(responseResult.getMsg());
                        break;

                    default:
                        onFindAllContainerWeightInspectListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onFindAllContainerWeightInspectListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);


    }

    @Override
    public void findCruiseWorkData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, final OnFindAllCruiseWorkListener onFindAllCruiseWorkListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("pageNum", pageNum);
        mapParams.put("pageSize", pageSize);
        mapParams.put("isUser", isUser);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        Observable<ResponseResult<ListCruiseWorkData>> observable = mService.findCruiseWorkData(params);
        Subscriber<ResponseResult<ListCruiseWorkData>> subscriber = new DialogSubscriber<ResponseResult<ListCruiseWorkData>>(dailog, bol) {
            @Override
            public void onSuccess(ResponseResult<ListCruiseWorkData> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onFindAllCruiseWorkListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onFindAllCruiseWorkListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onFindAllCruiseWorkListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onFindAllCruiseWorkListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    public interface OnLoadFindAllCBXCListener {
        void OnSuccess(ListcbxcData data);

        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllJDBGListener {
        void OnSuccess(ListcbjdjgData data);

        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllDXSHCListener {
        void OnSuccess(ListDXSHCData data);

        void OnFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllWatersPatrolListener {
        void onSuccess(ListWatersPatrolData data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllPSCDataListener {
        void onSuccess(ListPSCData data);

        void onFialure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllXHTJDataListener {
        void onSuccess(ListXHTJData data);

        void onFaile(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllElectronicCruiseExceptionListener {
        void onSuccess(ListElectronicCruiseException data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllCBWFXCDataListener {
        void onSuccess(ListCBWFXCData data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllGoodSecneOutRestServiceListener {
        void onSuccess(ListGoodSecneOutRestServiceData data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllDangerousGoodsXianChangListener {
        void onSuccess(ListDangerousGoodsXianChangData data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnLoadFindAllDangerousGoodsKaiXiangListener {
        void onSuccess(String msg, ListDangerousGoodsKaiXiangData data);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }


    public interface OnFindAllContainerWeightInspectListener {
        void onSuccess(ListContainerWeightInspectData listContainerWeightInspectData);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }

    public interface OnFindAllCruiseWorkListener {
        void onSuccess(ListCruiseWorkData listCruiseWorkData);

        void onFailure(String msg);

        void OnLoginExpired(String msg);
    }
}
