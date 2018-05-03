package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.maplayer.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.maplayer.model.IZhifaPersonModel.java
 * Date: 2017/12/27 13:20
 * Description:
 */

public interface IZhifaPersonModel {
    void getZhifaPersonData(Dialog dialog, ZhifaPersonModel.OnLoadZhiFapPersonListener onLoadZhiFapPersonListener);
}
