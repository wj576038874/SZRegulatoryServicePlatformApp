package com.winfo.szrsp.app.mvp.maplayer.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.mvp.IBaseView;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;

import java.util.List;

public interface IMapLayerView extends IBaseView {


    /**
     * 获取查询当前屏幕内ais数据的请求参数模型
     *
     * @return AIS请求参数模型
     */
    AisRequestModel getAisRequestModel();


    /**
     * 把获取到的AIS数据都给海图控件
     */
    void setAisData(List<Ais> aisData, String shipTypeCode, String shipTypeNameCn);

    /**
     * 把获取到的AIS数据都给海图控件
     */
    void setAllZhifaData(List<Ais> aisData);

    /**
     * 把通航要素显示在海图上
     */
    void setNavigationElementsData(List<NavigableElementsData> navigationElementsData, String typeName, String typeId);


    /**
     * 设置船舶ais数据是否显示
     *
     * @param bol 真假
     */
    void setIsShowAis(boolean bol);

    void setIsShowAllZhifa(boolean bol);

    /**
     * 是否显示通航要素的数据
     *
     * @param bol
     */
    void setIsShowThys(boolean bol);

    void setIsShowZhifaPerson(boolean bol);

    Dialog getDialog();

    void setZhifaPerson(List<MobileTerminalInfo2> list);

    void loginExpired(String msg);

    void setShipPhone(String msg, TelephoneMmsi data);
}
