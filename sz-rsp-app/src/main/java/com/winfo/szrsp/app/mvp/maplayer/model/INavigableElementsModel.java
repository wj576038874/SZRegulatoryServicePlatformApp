package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.model
 * @Filename: INavigableElementsModel
 * @Author: lsj
 * @Date: 2017/12/20  18:47
 * @Description:
 * @Version:
 */
public interface INavigableElementsModel {
    void getThysData(Dialog dialog, NavigableElementsModel.OnLoadNavigableElementsListener navigableElementsListener);
}
