package com.winfo.szrsp.app.mvp.table.findalltable.model;

import android.app.Dialog;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.model
 * @Filename: IFindAllTableModel
 * @Author: lsj
 * @Date: 2017/12/7  9:43
 * @Description:
 * @Version:
 */
public interface IFindAllTableModel {
    //查询船旗国监督报告列表数据
    void findJDBGData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllJDBGListener onLoadFindAllJDBGListener);

    //查询船舶现场监督报告列表数据
    void findCBXCData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllCBXCListener onLoadFindAllCBXCListener);

    //查询大型散货船列表数据
    void findDXSHCData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllDXSHCListener onLoadFindAllDXSHCListener);

    //查询水上巡航表格数据
    void findWatersPatrolData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllWatersPatrolListener onLoadFindAllWatersPatrolListener);

    //查询PSC表格数据
    void findPSCData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllPSCDataListener onLoadFindAllPSCDataListener);

    //查询巡航统计数据
    void findXhtjDatya(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllXHTJDataListener onLoadFindAllXHTJDataListener);

    void findElectronicCruiseException(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllElectronicCruiseExceptionListener onLoadFindAllElectronicCruiseExceptionListener);

    //查询船舶危防现场
    void findCBWFData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllCBWFXCDataListener onLoadFindAllCBWFXCDataListener);

    //查询船舶载运普通货物集装箱开箱检查记录表
    void findGoodSecneOutRestServiceData(Dialog dialog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllGoodSecneOutRestServiceListener onLoadFindAllGoodSecneOutRestServiceListener);

    //查询舶载运集装箱危险货物污染危害性货物现场检查记录表
    void findDangerousGoodsXianChangData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllDangerousGoodsXianChangListener onLoadFindAllDangerousGoodsXianChangListener);

    //查询舶载运集装箱危险货物污染危害性货物开箱检查记录表
    void findDangerousGoodsKaiXiangData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnLoadFindAllDangerousGoodsKaiXiangListener onLoadFindAllDangerousGoodsKaiXiangListener);

    //查询船舶载运货物集装箱重量验证检查记录表
    void findContainerWeightInspectData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnFindAllContainerWeightInspectListener onFindAllContainerWeightInspectListener);

    //查询巡航工作记录表
    void findCruiseWorkData(Dialog dailog, String pageNum, String pageSize, String isUser, boolean bol, FindAllTableModel.OnFindAllCruiseWorkListener onFindAllCruiseWorkListener);
}
