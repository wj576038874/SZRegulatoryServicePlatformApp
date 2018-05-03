package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.model
 * @Filename: IObtainModel
 * @Author: lsj
 * @Date: 2018/1/31  16:43
 * @Description:
 * @Version:
 */
public interface IObtainModel {
    void subObtain(Dialog dialog , Ais ais, List<String> list1, List<String> list2, ObtainModel.OnSubObtainListener onSubObtainListener);
}
