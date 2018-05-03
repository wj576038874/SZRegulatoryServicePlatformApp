package com.winfo.szrsp.app.mvp.setting.model;

import android.app.Dialog;

/**
 * ProjectName: gdmsaecApp
 * PackageNmae: com.winfo.gdmsaec.app.mvp.model.system
 * Author: wenjie
 * FileName: com.winfo.gdmsaec.app.mvp.model.system.IMapCacheModel.java
 * Date: 2018-03-26 10:23
 * Description:
 */
public interface IMapCacheModel {
    void loadMapCacheSize(MapCacheModel.OnLoadMapCacheSizeListener onLoadMapCacheSizeListener);
    void deleteMapCache(Dialog dialog, MapCacheModel.OnDeleteMapCacheListener onDeleteMapCacheListener);
}
