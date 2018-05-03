package com.winfo.szrsp.app.mvp.map.searchais.presenter;

import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.entity.HistoryThsy;
import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.map.searchais.model.ISearchShipModel;
import com.winfo.szrsp.app.mvp.map.searchais.model.SearchShipModel;
import com.winfo.szrsp.app.mvp.map.searchais.view.ISearchShipView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.map.searchais.presenter.SearchShipPresenter.java
 * Date: 2017/12/15 22:03
 * Description:
 */

public class SearchShipPresenter extends BaseMvpPresenter<ISearchShipView> {
    private ISearchShipModel searchShipModel;

    public SearchShipPresenter() {
        this.searchShipModel = new SearchShipModel();
    }


    public void getShipsByName(String params) {
        if (mView == null) return;
        searchShipModel.searchShipsByName(mView.getLoadingDialog(), params, new SearchShipModel.OnSearchShipsListener() {
            @Override
            public void onSuccess(List<ShipSearch> shipSearches) {
                mView.setSearchShips(shipSearches);
            }

            @Override
            public void onFailure(String msg) {
                mView.ShowMsg(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }


    public void searchShipBySearchShip(ShipSearch shipSearch) {
        if (mView == null) return;
        searchShipModel.searchShipBySearchShip(mView.getLoadingDialog(), shipSearch, new SearchShipModel.OnSearchShipByMmsiListener() {
            @Override
            public void onSuccess(Ais ais) {
                mView.setSearchShipByMMSI(ais);
            }

            @Override
            public void onFailure(String msg) {
                mView.ShowMsg(msg);
            }
        });
    }


    public void searShipByMmsi(String mmsi) {
        if (mView == null) return;
        if (mmsi.equals("")) {
            mView.ShowMsg("mmsi不能为空");
            return;
        }
        searchShipModel.searchShipByMmsi(mView.getLoadingDialog(), mmsi, new SearchShipModel.OnSearchShipByMmsiListener() {
            @Override
            public void onSuccess(Ais ais) {
                mView.setSearchShipByMMSI(ais);
            }

            @Override
            public void onFailure(String msg) {
                mView.ShowMsg(msg);
            }
        });
    }

    public void serachNavigableElementsDataByNameCn(String name){
        if (mView == null) return;
        if (name.equals("")){
            mView.ShowMsg("请输入要素名称");
            return;
        }
        searchShipModel.serachNavigableElementsDataByNameCn(mView.getLoadingDialog(), name, new SearchShipModel.OnSearchNavigableElementsDataListener() {
            @Override
            public void onSuccess(List<NavigableElementsData> navigableElementsData) {
                mView.setNavigableElementsData(navigableElementsData);
            }

            @Override
            public void onFaile(String msg) {
                mView.ShowMsg(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }

        });
    }

    public void getHistory() {
        if (mView == null) return;
        searchShipModel.loadHistoryAis(mView.getLoadingDialog(), new SearchShipModel.OnLoadHistoryListener() {
            @Override
            public void onSuccess(List<HistoryAis> historyAis) {
                mView.setHistoryAis(historyAis);
            }

            @Override
            public void onFailure(String msg) {
                mView.ShowMsg(msg);
            }
        });
    }

    public void getHistoryThsy() {
        if (mView == null) return;
        searchShipModel.loadHistoryThys(mView.getLoadingDialog(), new SearchShipModel.OnLoadHistoryThysDataListenet() {
            @Override
            public void onSuccess(List<HistoryThsy> historyThsies) {
                mView.setHistoryThys(historyThsies);
            }

            @Override
            public void onFaile(String msg) {
                mView.ShowMsg(msg);
            }
        });
    }

}
