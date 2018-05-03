package com.winfo.szrsp.app.mvp.setting.view;


import android.app.Dialog;

import com.winfo.szrsp.app.mvp.IBaseMvpView;

/**
 * ProjectName: gdmsaecApp
 * PackageNmae: com.winfo.gdmsaec.app.mvp.view.system
 * Author: wenjie
 * FileName: com.winfo.gdmsaec.app.mvp.view.system.IMapChacheSizeView.java
 * Date: 2018-03-26 10:29
 * Description:
 */
public interface IMapChacheSizeView extends IBaseMvpView {
    void setMapCacheSzie(String size);
    void onFailure(String msg);
    void onDeleteMapCacheSuccess(String msg);
    void onDeleteMapCacheFailure(String msg);
    Dialog getLoadingDialog();
}
