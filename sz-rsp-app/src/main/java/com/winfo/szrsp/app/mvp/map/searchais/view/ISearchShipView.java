package com.winfo.szrsp.app.mvp.map.searchais.view;

import android.app.Dialog;

import com.winfo.dnc.sdk.AisData;
import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.entity.HistoryThsy;
import com.winfo.szrsp.app.entity.LocalAisData;
import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.mvp.IBaseView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;

import java.util.List;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.map.searchais.view.ISearchShipView.java
 * Date: 2017/12/15 21:59
 * Description:
 */
public interface ISearchShipView extends IBaseMvpView{


    void ShowMsg(String msg);

    Dialog getLoadingDialog();

    /**
     * 模糊查询出来的实时数据
     */
    void setSearchShips(List<ShipSearch> shipSearches);

    void setSearchShipByMMSI(Ais ais);

    void setHistoryAis(List<HistoryAis> historyAis);

    void setHistoryThys(List<HistoryThsy> historyAis);

    void setNavigableElementsData(List<NavigableElementsData> navigableElementsData);

    void loginExpired(String msg);
}
