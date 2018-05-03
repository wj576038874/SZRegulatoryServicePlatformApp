package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
//水上巡航   之    水工作业船舶和其他船舶检查纪录表
public class CtWaterCruiseShipInspect implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String inspectNo;

    private BigDecimal seqNo;

    private String isWork;
	    
    private String shipNo;

    private String shipNameCn;

    private String startTime;

    private String endTime;

    private String shipPlace;

    private String shipTypeCode;

    private String shipTypeNameCn;

    private String result;

    private String workName;
    
    private String isApp;

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public BigDecimal getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(BigDecimal seqNo) {
        this.seqNo = seqNo;
    }

    public String getIsWork() {
        return isWork;
    }

    public void setIsWork(String isWork) {
        this.isWork = isWork == null ? null : isWork.trim();
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo == null ? null : shipNo.trim();
    }

    public String getShipNameCn() {
        return shipNameCn;
    }

    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn == null ? null : shipNameCn.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getShipPlace() {
        return shipPlace;
    }

    public void setShipPlace(String shipPlace) {
        this.shipPlace = shipPlace == null ? null : shipPlace.trim();
    }

    public String getShipTypeCode() {
        return shipTypeCode;
    }

    public void setShipTypeCode(String shipTypeCode) {
        this.shipTypeCode = shipTypeCode == null ? null : shipTypeCode.trim();
    }

    public String getShipTypeNameCn() {
        return shipTypeNameCn;
    }

    public void setShipTypeNameCn(String shipTypeNameCn) {
        this.shipTypeNameCn = shipTypeNameCn == null ? null : shipTypeNameCn.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

	public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp== null ? null : isApp.trim();
	}
}