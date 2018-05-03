package com.winfo.szrsp.app.sdk.entity.task;

import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.entity.table.CtSpecialShipType0203;
import com.winfo.szrsp.app.sdk.entity.table.DetailArea;
import com.winfo.szrsp.app.sdk.entity.table.DetailShip;
import com.winfo.szrsp.app.sdk.entity.table.Info;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;

import java.util.List;

/**
 * Created by Guan on 2017-12-13.
 */

public class TaskFinishSubData {

    private CtSpecialShipType0203 ctSpecialShipType0203;//	Object
    private Info ctWaterCruiseRecordInfo	;//Object
    private List<DetailArea> ctWaterCruiseReportDetail;
    private List<DetailShip>ctWaterCruiseShipInspectDetail;

    private List<jdbgData.detail>  tFlagStateControlDetail;
    private jdbgData.info tFlagStateControlInfo;//	Object

    private  List<cbxcjdbgData.detail> tSiteSupervisionDetail;
    private cbxcjdbgData.info tSiteSupervisionInfo;//	Object
    private String  taskId;//	201712011013434587

    private CtCruiseStatisticsObject ctCruiseStatisticsObject;

    private CtElectronicCruiseException ctElectronicCruiseException;

    private CtSafeInspectNoticeObject  ctSafeInspectNoticeObject;

    public CtSafeInspectNoticeObject getCtSafeInspectNoticeObject() {
        return ctSafeInspectNoticeObject;
    }

    public void setCtSafeInspectNoticeObject(CtSafeInspectNoticeObject ctSafeInspectNoticeObject) {
        this.ctSafeInspectNoticeObject = ctSafeInspectNoticeObject;
    }

    private  String disposalDecision;

    public String getDisposalDecision() {
        return disposalDecision;
    }

    public CtCruiseStatisticsObject getCtCruiseStatisticsObject() {
        return ctCruiseStatisticsObject;
    }

    public void setCtCruiseStatisticsObject(CtCruiseStatisticsObject ctCruiseStatisticsObject) {
        this.ctCruiseStatisticsObject = ctCruiseStatisticsObject;
    }

    public void setDisposalDecision(String disposalDecision) {
        this.disposalDecision = disposalDecision;
    }

    public CtSpecialShipType0203 getCtSpecialShipType0203() {
        return ctSpecialShipType0203;
    }

    public void setCtSpecialShipType0203(CtSpecialShipType0203 ctSpecialShipType0203) {
        this.ctSpecialShipType0203 = ctSpecialShipType0203;
    }

    public Info getCtWaterCruiseRecordInfo() {
        return ctWaterCruiseRecordInfo;
    }

    public void setCtWaterCruiseRecordInfo(Info ctWaterCruiseRecordInfo) {
        this.ctWaterCruiseRecordInfo = ctWaterCruiseRecordInfo;
    }

    public List<DetailArea> getCtWaterCruiseReportDetail() {
        return ctWaterCruiseReportDetail;
    }

    public void setCtWaterCruiseReportDetail(List<DetailArea> ctWaterCruiseReportDetail) {
        this.ctWaterCruiseReportDetail = ctWaterCruiseReportDetail;
    }

    public List<DetailShip> getCtWaterCruiseShipInspectDetail() {
        return ctWaterCruiseShipInspectDetail;
    }

    public void setCtWaterCruiseShipInspectDetail(List<DetailShip> ctWaterCruiseShipInspectDetail) {
        this.ctWaterCruiseShipInspectDetail = ctWaterCruiseShipInspectDetail;
    }

    public List<jdbgData.detail> gettFlagStateControlDetail() {
        return tFlagStateControlDetail;
    }

    public void settFlagStateControlDetail(List<jdbgData.detail> tFlagStateControlDetail) {
        this.tFlagStateControlDetail = tFlagStateControlDetail;
    }

    public jdbgData.info gettFlagStateControlInfo() {
        return tFlagStateControlInfo;
    }

    public void settFlagStateControlInfo(jdbgData.info tFlagStateControlInfo) {
        this.tFlagStateControlInfo = tFlagStateControlInfo;
    }

    public List<cbxcjdbgData.detail> gettSiteSupervisionDetail() {
        return tSiteSupervisionDetail;
    }

    public void settSiteSupervisionDetail(List<cbxcjdbgData.detail> tSiteSupervisionDetail) {
        this.tSiteSupervisionDetail = tSiteSupervisionDetail;
    }

    public cbxcjdbgData.info gettSiteSupervisionInfo() {
        return tSiteSupervisionInfo;
    }

    public void settSiteSupervisionInfo(cbxcjdbgData.info tSiteSupervisionInfo) {
        this.tSiteSupervisionInfo = tSiteSupervisionInfo;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public CtElectronicCruiseException getCtElectronicCruiseException() {
        return ctElectronicCruiseException;
    }

    public void setCtElectronicCruiseException(CtElectronicCruiseException ctElectronicCruiseException) {
        this.ctElectronicCruiseException = ctElectronicCruiseException;
    }
}
