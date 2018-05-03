package com.winfo.szrsp.app.mvp.maplayer.presenter;

import com.winfo.szrsp.app.listener.ChangeSwitchButtonState;
import com.winfo.szrsp.app.mvp.maplayer.model.INavigableElementsModel;
import com.winfo.szrsp.app.mvp.maplayer.model.NavigableElementsModel;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.presenter
 * @Filename: NavigableElementsPresenter
 * @Author: lsj
 * @Date: 2017/12/20  18:46
 * @Description:
 * @Version:
 */
public class NavigableElementsPresenter {
    private IMapLayerView mapLayerView;
    private INavigableElementsModel model;

    public NavigableElementsPresenter(IMapLayerView mapLayerView) {
        this.mapLayerView = mapLayerView;
        this.model = new NavigableElementsModel();
    }

    public void getThysData(final ChangeSwitchButtonState changeSwitchButtonState, final String typeName, final String typeId) {
        model.getThysData(mapLayerView.getDialog(), new NavigableElementsModel.OnLoadNavigableElementsListener() {
            @Override
            public void OnSuccess(List<NavigableElementsData> navigableElementsData) {
                mapLayerView.setNavigationElementsData(navigableElementsData, typeName, typeId);
            }

            @Override
            public void OnFaile(String msg) {
                mapLayerView.showMsg(msg);
                mapLayerView.setIsShowThys(false);
                if (changeSwitchButtonState != null) {
                    changeSwitchButtonState.changeState(false);
                }
            }

            @Override
            public void OnLoginExpired(String msg) {
                mapLayerView.showMsg(msg);
                mapLayerView.setIsShowThys(false);
                if (changeSwitchButtonState != null) {
                    changeSwitchButtonState.changeState(false);
                }
                mapLayerView.loginExpired(msg);
            }
        });
    }
}
