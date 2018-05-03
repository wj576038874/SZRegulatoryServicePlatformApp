package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
//水上巡航 之   水域巡航和码头岸线巡航
public class CtWaterCruiseReport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String inspectNo;

    private BigDecimal seqNo;

    private String cruiseType;

    private String startTime;

    private String cruiseArea;

    private String cruiseState;

    private String cruiseExap;

    private String endTime;
    
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

    public String getCruiseType() {
        return cruiseType;
    }

    public void setCruiseType(String cruiseType) {
        this.cruiseType = cruiseType == null ? null : cruiseType.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getCruiseArea() {
        return cruiseArea;
    }

    public void setCruiseArea(String cruiseArea) {
        this.cruiseArea = cruiseArea == null ? null : cruiseArea.trim();
    }

    public String getCruiseState() {
        return cruiseState;
    }

    public void setCruiseState(String cruiseState) {
        this.cruiseState = cruiseState == null ? null : cruiseState.trim();
    }

    public String getCruiseExap() {
        return cruiseExap;
    }

    public void setCruiseExap(String cruiseExap) {
        this.cruiseExap = cruiseExap == null ? null : cruiseExap.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

	public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp== null ? null : isApp.trim();
	}
}