package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
//水上巡航工作纪录表 基础表
public class CtWaterCruiseRecordInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String inspectNo;

    private String voyageId;

    private String bustNo;

    private String taskId;

    private String inspectOrg;

    private String inspectDate;

    private BigDecimal weekNum;

    private String dayTime;

    private String createUserAutograph;

    private String cruiseType;

    private String deptCode;

    private String createUserUuid;

    private String createTime;

    private String isApp;
    
    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public String getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId == null ? null : voyageId.trim();
    }

    public String getBustNo() {
        return bustNo;
    }

    public void setBustNo(String bustNo) {
        this.bustNo = bustNo == null ? null : bustNo.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getInspectOrg() {
        return inspectOrg;
    }

    public void setInspectOrg(String inspectOrg) {
        this.inspectOrg = inspectOrg == null ? null : inspectOrg.trim();
    }

    public String getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(String inspectDate) {
        this.inspectDate = inspectDate;
    }

    public BigDecimal getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(BigDecimal weekNum) {
        this.weekNum = weekNum;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime == null ? null : dayTime.trim();
    }

    public String getCreateUserAutograph() {
        return createUserAutograph;
    }

    public void setCreateUserAutograph(String createUserAutograph) {
        this.createUserAutograph = createUserAutograph == null ? null : createUserAutograph.trim();
    }

    public String getCruiseType() {
        return cruiseType;
    }

    public void setCruiseType(String cruiseType) {
        this.cruiseType = cruiseType == null ? null : cruiseType.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp== null ? null : isApp.trim();
	}
}