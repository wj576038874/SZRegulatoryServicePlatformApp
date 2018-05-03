package com.winfo.szrsp.app.mvp.map.searchais.model;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.entity.HistoryThsy;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
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
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.map.searchais.model.SearchShipModel.java
 * Date: 2017/12/15 22:03
 * Description:
 */

public class SearchShipModel extends BaseModel<ShipDataService> implements ISearchShipModel {


    public SearchShipModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void searchShipsByName(Dialog dialog, String name, final OnSearchShipsListener onSearchShipsListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("search_text", name);
        String params = FastJsonUtil.mapToJsonStr(paramMap);
        Observable<ResponseResult<List<ShipSearch>>> observable = mService.searchShipByEnNameOrCnName(params);
        Subscriber<ResponseResult<List<ShipSearch>>> subscriber = new DialogSubscriber<ResponseResult<List<ShipSearch>>>(dialog) {
            @Override
            public void onSuccess(ResponseResult<List<ShipSearch>> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onSearchShipsListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onSearchShipsListener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onSearchShipsListener.onFailure(responseResult.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onSearchShipsListener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void searchShipByMmsi(Dialog dialog, String mmsi, final OnSearchShipByMmsiListener onSearchShipByMmsiListener) {
        Observable<List<Ais>> observable = mService.searShipByMMSI("AISFewShip", mmsi, true);
        Subscriber<List<Ais>> subscriber = new DialogSubscriber<List<Ais>>(dialog) {
            @Override
            public void onSuccess(List<Ais> responseResult) {
                if (responseResult.size() > 0) {
                    onSearchShipByMmsiListener.onSuccess(responseResult.get(0));
                    HistoryAis historyAis = new HistoryAis();
                    historyAis.setMmsi(responseResult.get(0).getID());
                    historyAis.setZwcm(responseResult.get(0).getCHECKDATA().getRegisterShipData().getNameCN());
                    historyAis.setZwcm(responseResult.get(0).getCHECKDATA().getShipBaseInfo().getNameCN());
                    historyAis.setYwcm(responseResult.get(0).getCM());
                    saveHistoryShip(historyAis);
                } else {
                    onSearchShipByMmsiListener.onFailure("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                onSearchShipByMmsiListener.onFailure("查询失败");
            }
        };
        toSubscribe(observable, subscriber);
    }

    private void saveHistoryShip(final HistoryAis historyAis) {
        Observable<List<HistoryAis>> observable = Observable.create(new Observable.OnSubscribe<List<HistoryAis>>() {
            @Override
            public void call(Subscriber<? super List<HistoryAis>> subscriber) {
                try {
                    List<HistoryAis> historyAisList = SzRspApplication.db.findAll(Selector.from(HistoryAis.class).where(WhereBuilder.b("mmsi", "=", historyAis.getMmsi())));
                    subscriber.onNext(historyAisList);
                    subscriber.onCompleted();
                } catch (DbException e) {
                    subscriber.onError(e);
                }
            }
        });
        Subscriber<List<HistoryAis>> subscriber = new Subscriber<List<HistoryAis>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<HistoryAis> historyAisList) {
                if (historyAisList.size() == 0) {//不存在 则添加  存在则不做处理
                    try {
                        SzRspApplication.db.saveOrUpdate(historyAis);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        toSubscribe(observable, subscriber);
    }


    @Override
    public void loadHistoryAis(Dialog dialog, final OnLoadHistoryListener onLoadHistoryListener) {
        Observable<List<HistoryAis>> observable = Observable.create(new Observable.OnSubscribe<List<HistoryAis>>() {
            @Override
            public void call(Subscriber<? super List<HistoryAis>> subscriber) {
                try {
                    List<HistoryAis> historyAisList = SzRspApplication.db.findAll(HistoryAis.class);
                    subscriber.onNext(historyAisList);
                    subscriber.onCompleted();
                } catch (DbException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });

        Subscriber<List<HistoryAis>> subscriber = new DialogSubscriber<List<HistoryAis>>(dialog) {
            @Override
            public void onSuccess(List<HistoryAis> responseResult) {
                onLoadHistoryListener.onSuccess(responseResult);
            }

            @Override
            public void onFailure(String msg) {
                onLoadHistoryListener.onFailure("历史数据加载失败");
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void loadHistoryThys(Dialog dialog, final OnLoadHistoryThysDataListenet onLoadHistoryThysDataListenet) {
        Observable<List<HistoryThsy>> observable = Observable.create(new Observable.OnSubscribe<List<HistoryThsy>>() {
            @Override
            public void call(Subscriber<? super List<HistoryThsy>> subscriber) {
                try {
                    List<HistoryThsy> historyThsies = SzRspApplication.db.findAll(HistoryThsy.class);
                    subscriber.onNext(historyThsies);
                    subscriber.onCompleted();
                } catch (DbException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });

        Subscriber<List<HistoryThsy>> subscriber = new DialogSubscriber<List<HistoryThsy>>(dialog) {
            @Override
            public void onSuccess(List<HistoryThsy> responseResult) {
                onLoadHistoryThysDataListenet.onSuccess(responseResult);
            }

            @Override
            public void onFailure(String msg) {
                onLoadHistoryThysDataListenet.onFaile("历史数据加载失败");
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void searchShipBySearchShip(Dialog dialog, final ShipSearch shipSearch, final OnSearchShipByMmsiListener onSearchShipByMmsiListener) {
        Observable<List<Ais>> observable = mService.searShipByMMSI("AISFewShip", shipSearch.getMmsi(), true);
        Subscriber<List<Ais>> subscriber = new DialogSubscriber<List<Ais>>(dialog) {
            @Override
            public void onSuccess(List<Ais> responseResult) {
                if (responseResult.size() > 0) {
                    onSearchShipByMmsiListener.onSuccess(responseResult.get(0));
                    HistoryAis historyAis = new HistoryAis();
                    historyAis.setMmsi(shipSearch.getMmsi());
                    historyAis.setZwcm(shipSearch.getZwcm());
                    historyAis.setYwcm(shipSearch.getYwcm());
                    historyAis.setPycm(shipSearch.getPycm());
                    saveHistoryShip(historyAis);
                } else {
                    onSearchShipByMmsiListener.onFailure("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                onSearchShipByMmsiListener.onFailure("查询失败");
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void serachNavigableElementsDataByNameCn(Dialog dialog, String name, final OnSearchNavigableElementsDataListener onSearchNavigableElementsDataListener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("name", name);
        String params = FastJsonUtil.beanToJsonStr(mapParams);
        rx.Observable<ResponseResult<List<NavigableElementsData>>> observable = mService.getNavigableElementsData(params);
        Subscriber<ResponseResult<List<NavigableElementsData>>> subscriber = new DialogSubscriber<ResponseResult<List<NavigableElementsData>>>(dialog,true) {
            @Override
            public void onSuccess(ResponseResult<List<NavigableElementsData>> responseResult) {
                switch (responseResult.getCode()){
                    case 1:
                        onSearchNavigableElementsDataListener.onSuccess(responseResult.getDatas());
                        break;
                    case 10008:
                        onSearchNavigableElementsDataListener.OnLoginExpired(responseResult.getMsg());
                        break;
                        default:
                            onSearchNavigableElementsDataListener.onFaile(responseResult.getMsg());
                            break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onSearchNavigableElementsDataListener.onFaile(msg);
            }
        };
        toSubscribe(observable,subscriber);
    }

    public interface OnLoadHistoryThysDataListenet{
        void onSuccess(List<HistoryThsy> historyThsies);
        void onFaile(String msg);
    }

    public interface OnSearchNavigableElementsDataListener{
        void onSuccess(List<NavigableElementsData> navigableElementsData);
        void onFaile(String msg);
        void OnLoginExpired(String msg);
    }

    public interface OnSearchShipsListener {
        void onSuccess(List<ShipSearch> shipSearches);

        void onFailure(String msg);
        void OnLoginExpired(String msg);
    }

    public interface OnSearchShipByMmsiListener {
        void onSuccess(Ais ais);

        void onFailure(String msg);
    }

    public interface OnLoadHistoryListener {
        void onSuccess(List<HistoryAis> historyAis);

        void onFailure(String msg);
    }
}
