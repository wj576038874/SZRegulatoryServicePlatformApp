package com.winfo.szrsp.app.mvp.setting.presenter;


import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.setting.model.IMapCacheModel;
import com.winfo.szrsp.app.mvp.setting.model.MapCacheModel;
import com.winfo.szrsp.app.mvp.setting.view.IMapChacheSizeView;

/**
 * ProjectName: gdmsaecApp
 * PackageNmae: com.winfo.gdmsaec.app.mvp.view.system
 * Author: wenjie
 * FileName: com.winfo.gdmsaec.app.mvp.view.system.MapCacheSizePresenter.java
 * Date: 2018-03-26 10:28
 * Description:
 */
public class MapCacheSizePresenter {

    private IMapCacheModel mapCacheModel;
    private IMapChacheSizeView mView;

    public MapCacheSizePresenter(IMapChacheSizeView mView) {
        mapCacheModel = new MapCacheModel();
        this.mView=mView;
    }

    public void getMapCacheSize() {

        mapCacheModel.loadMapCacheSize(new MapCacheModel.OnLoadMapCacheSizeListener() {
            @Override
            public void onSuccess(String size) {
                mView.setMapCacheSzie(size);
            }

            @Override
            public void onFailure(String msg) {
                mView.onFailure(msg);
            }
        });
    }

    public void deleteMapCache(){
        mapCacheModel.deleteMapCache(mView.getLoadingDialog(),new MapCacheModel.OnDeleteMapCacheListener() {
            @Override
            public void onSuccess(String msg) {
                mView.onDeleteMapCacheSuccess(msg);
            }

            @Override
            public void onFailure(String msg) {
                mView.onDeleteMapCacheFailure(msg);
            }
        });
    }
}
