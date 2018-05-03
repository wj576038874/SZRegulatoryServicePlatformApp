package com.winfo.szrsp.app.mvp.maplayer.presenter;

import com.winfo.szrsp.app.listener.ChangeSwitchButtonState;
import com.winfo.szrsp.app.mvp.maplayer.model.INavigableElementsModel;
import com.winfo.szrsp.app.mvp.maplayer.model.IZhifaPersonModel;
import com.winfo.szrsp.app.mvp.maplayer.model.NavigableElementsModel;
import com.winfo.szrsp.app.mvp.maplayer.model.ZhifaPersonModel;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;

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
public class ZhifaPersonPresenter {
    private IMapLayerView mapLayerView;
    private IZhifaPersonModel model;

    public ZhifaPersonPresenter(IMapLayerView mapLayerView){
        this.mapLayerView = mapLayerView;
        this.model = new ZhifaPersonModel();
    }

    public void getZhifaPersonData(final ChangeSwitchButtonState changeSwitchButtonState){
        model.getZhifaPersonData(mapLayerView.getDialog(), new ZhifaPersonModel.OnLoadZhiFapPersonListener() {
            @Override
            public void OnSuccess(List<MobileTerminalInfo2> mobileTerminalInfo2s) {
                mapLayerView.setZhifaPerson(mobileTerminalInfo2s);
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
