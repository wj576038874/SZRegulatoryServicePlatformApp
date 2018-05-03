package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;

/**
 * Created by Guan on 2017-12-09.
 */

public class TaskFinishData implements Serializable{

    private String ctSpecialShipType0203Return;//大型散货
    private String ctWaterCruiseRecordReturn;//水上巡航
    private String tFlagStateControlReturn;//船旗国
    private String tSiteSupervisionReturn;//船舶现场
    private String ctCruiseStatisticsReturn;//巡航统计
    private String ctElectronicCruiseExceptionReturn;//电子巡航异常情况
    private String ctSafetySceneInspectReturn;//船舶危防现场

    public String getCtSafetySceneInspectReturn() {
        return ctSafetySceneInspectReturn;
    }

    public void setCtSafetySceneInspectReturn(String ctSafetySceneInspectReturn) {
        this.ctSafetySceneInspectReturn = ctSafetySceneInspectReturn;
    }

    public String getCtCruiseStatisticsReturn() {
        return ctCruiseStatisticsReturn;
    }

    public void setCtCruiseStatisticsReturn(String ctCruiseStatisticsReturn) {
        this.ctCruiseStatisticsReturn = ctCruiseStatisticsReturn;
    }

    public String getCtSpecialShipType0203Return() {
        return ctSpecialShipType0203Return;
    }

    public void setCtSpecialShipType0203Return(String ctSpecialShipType0203Return) {
        this.ctSpecialShipType0203Return = ctSpecialShipType0203Return;
    }

    public String getCtWaterCruiseRecordReturn() {
        return ctWaterCruiseRecordReturn;
    }

    public void setCtWaterCruiseRecordReturn(String ctWaterCruiseRecordReturn) {
        this.ctWaterCruiseRecordReturn = ctWaterCruiseRecordReturn;
    }

    public String gettFlagStateControlReturn() {
        return tFlagStateControlReturn;
    }

    public void settFlagStateControlReturn(String tFlagStateControlReturn) {
        this.tFlagStateControlReturn = tFlagStateControlReturn;
    }

    public String gettSiteSupervisionReturn() {
        return tSiteSupervisionReturn;
    }

    public void settSiteSupervisionReturn(String tSiteSupervisionReturn) {
        this.tSiteSupervisionReturn = tSiteSupervisionReturn;
    }

    public String getCtElectronicCruiseExceptionReturn() {
        return ctElectronicCruiseExceptionReturn;
    }

    public void setCtElectronicCruiseExceptionReturn(String ctElectronicCruiseExceptionReturn) {
        this.ctElectronicCruiseExceptionReturn = ctElectronicCruiseExceptionReturn;
    }
}
