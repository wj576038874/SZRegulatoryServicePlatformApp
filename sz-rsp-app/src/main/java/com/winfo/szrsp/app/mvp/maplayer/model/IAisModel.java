package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;


import com.winfo.szrsp.app.entity.request.AisRequestModel;

import java.io.File;

/**
 * AIS业务处理模型接口
 *
 * @author winfo-wj
 */
public interface IAisModel {
    /**
     * 加载当前屏幕范围的ais数据
     */
    void loadAis(Dialog dialog, boolean isShowDialog, AisRequestModel aisRequestModel, AisModel.OnLoadAisDataLitener loadAisDataLitener);


    File getCacheFile();

    /**
     * 记载本地通航要素的点数据
     *
     * @param
     * @param
     */
    void loadLocalAisPoint(Dialog dialog, boolean isShowDialog, File file, AisModel.OnLoadLocalAisPointLitener localAisPointLitener);

    void loadNetAisPoint(Dialog dialog, boolean isShowDialog, AisRequestModel aisRequestModel, AisModel.OnLoadAisDataLitener loadAisDataLitener);

    void loadNetAisPoint_T_T(Dialog dialog, boolean isShowDialog, AisRequestModel aisRequestModel, AisModel.OnLoadNetAisPointLitener loadNetAisPointLitener);

    /**
     * 加载所有执法船包括关机数据
     */
    void loadZhifaAis(Dialog dialog, boolean isShowDialog, AisModel.OnLoadAisDataLitener loadAisDataLitener);


    void getPhoneByMmsi(String mmsi, Dialog dialog, boolean isShowDialog, AisModel.OnLoadPhoneDataLitener onLoadPhoneDataLitener);

}
