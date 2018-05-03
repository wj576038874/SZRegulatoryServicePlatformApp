package com.winfo.szrsp.app.mvp.map.searchais.model;

import android.app.Dialog;

import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.map.searchais.model.ISearchShipModel.java
 * Date: 2017/12/15 22:03
 * Description:
 */

public interface ISearchShipModel {
    void searchShipsByName(Dialog dialog, String name, SearchShipModel.OnSearchShipsListener onSearchShipsListener);

    void searchShipByMmsi(Dialog dialog, String mmsi, SearchShipModel.OnSearchShipByMmsiListener onSearchShipByMmsiListener);

    void loadHistoryAis(Dialog dialog, SearchShipModel.OnLoadHistoryListener onLoadHistoryListener);

    void loadHistoryThys(Dialog dialog, SearchShipModel.OnLoadHistoryThysDataListenet onLoadHistoryThysDataListenet);

    void searchShipBySearchShip(Dialog dialog , ShipSearch shipSearch,SearchShipModel.OnSearchShipByMmsiListener onSearchShipByMmsiListener);

    void serachNavigableElementsDataByNameCn(Dialog dialog, String name, SearchShipModel.OnSearchNavigableElementsDataListener onSearchNavigableElementsDataListener);
}
