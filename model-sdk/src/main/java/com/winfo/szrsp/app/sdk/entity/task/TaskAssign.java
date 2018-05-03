package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TaskAssign implements Serializable,Cloneable  {
    private String taskId;

    private String shipNameCn;

    private String shipNameEn;

    private BigDecimal taskAssignStatus;

    private String taskAssignOrgId;

    private String taskAssignOrgName;

    private String taskAssignUserUuid;

    private String taskAssignUserName;

    private String taskAssignTime;

    private String taskArriveOrgId;

    private String taskArriveOrgName;

    private String taskArriveDeptCode;

    private String taskArriveDeptName;

    private String taskAssignDeptCode;

    private String taskAssignDeptName;

    private String taskArriveTime;

    private BigDecimal isSendSms;

    private String smsMessage;

    private String taskArriveUserUuid;

    private String taskArriveUserName;

    private String taskAcceptTime;

    private String taskReturnTime;

    private BigDecimal acceptType;

    private String taskChangeAssignUserUuid;

    private String taskReturnRemark;
    
    private String taskName;
    
    private List<TaskType> taskTypeList;


    public Object clone()
    {
        Object o=null;
        try
        {
            o=(TaskAssign)super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println(e.toString());
        }
        return o;
    }

    public String getShipNameCn() {
        return shipNameCn;
    }

    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn;
    }

    public String getShipNameEn() {
        return shipNameEn;
    }

    public void setShipNameEn(String shipNameEn) {
        this.shipNameEn = shipNameEn;
    }

    public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}

	public void setTaskTypeList(List<TaskType> taskTypeList) {
        taskTypeList = taskTypeList;
	}

	private static final long serialVersionUID = 1L;

    public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public BigDecimal getTaskAssignStatus() {
        return taskAssignStatus;
    }

    public void setTaskAssignStatus(BigDecimal taskAssignStatus) {
        this.taskAssignStatus = taskAssignStatus;
    }

    public String getTaskAssignOrgId() {
        return taskAssignOrgId;
    }

    public void setTaskAssignOrgId(String taskAssignOrgId) {
        this.taskAssignOrgId = taskAssignOrgId == null ? null : taskAssignOrgId.trim();
    }

    public String getTaskAssignOrgName() {
        return taskAssignOrgName;
    }

    public void setTaskAssignOrgName(String taskAssignOrgName) {
        this.taskAssignOrgName = taskAssignOrgName == null ? null : taskAssignOrgName.trim();
    }

    public String getTaskAssignUserUuid() {
        return taskAssignUserUuid;
    }

    public void setTaskAssignUserUuid(String taskAssignUserUuid) {
        this.taskAssignUserUuid = taskAssignUserUuid == null ? null : taskAssignUserUuid.trim();
    }

    public String getTaskAssignUserName() {
        return taskAssignUserName;
    }

    public void setTaskAssignUserName(String taskAssignUserName) {
        this.taskAssignUserName = taskAssignUserName == null ? null : taskAssignUserName.trim();
    }

    public String getTaskAssignTime() {
        return taskAssignTime;
    }

    public void setTaskAssignTime(String taskAssignTime) {
        this.taskAssignTime = taskAssignTime;
    }

    public String getTaskArriveOrgId() {
        return taskArriveOrgId;
    }

    public void setTaskArriveOrgId(String taskArriveOrgId) {
        this.taskArriveOrgId = taskArriveOrgId == null ? null : taskArriveOrgId.trim();
    }

    public String getTaskArriveOrgName() {
        return taskArriveOrgName;
    }

    public void setTaskArriveOrgName(String taskArriveOrgName) {
        this.taskArriveOrgName = taskArriveOrgName == null ? null : taskArriveOrgName.trim();
    }

    public String getTaskArriveDeptCode() {
        return taskArriveDeptCode;
    }

    public void setTaskArriveDeptCode(String taskArriveDeptCode) {
        this.taskArriveDeptCode = taskArriveDeptCode == null ? null : taskArriveDeptCode.trim();
    }

    public String getTaskArriveDeptName() {
        return taskArriveDeptName;
    }

    public void setTaskArriveDeptName(String taskArriveDeptName) {
        this.taskArriveDeptName = taskArriveDeptName == null ? null : taskArriveDeptName.trim();
    }

    public String getTaskAssignDeptCode() {
        return taskAssignDeptCode;
    }

    public void setTaskAssignDeptCode(String taskAssignDeptCode) {
        this.taskAssignDeptCode = taskAssignDeptCode == null ? null : taskAssignDeptCode.trim();
    }

    public String getTaskAssignDeptName() {
        return taskAssignDeptName;
    }

    public void setTaskAssignDeptName(String taskAssignDeptName) {
        this.taskAssignDeptName = taskAssignDeptName == null ? null : taskAssignDeptName.trim();
    }

    public String getTaskArriveTime() {
        return taskArriveTime;
    }

    public void setTaskArriveTime(String taskArriveTime) {
        this.taskArriveTime = taskArriveTime;
    }

    public BigDecimal getIsSendSms() {
        return isSendSms;
    }

    public void setIsSendSms(BigDecimal isSendSms) {
        this.isSendSms = isSendSms;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage == null ? null : smsMessage.trim();
    }

    public String getTaskArriveUserUuid() {
        return taskArriveUserUuid;
    }

    public void setTaskArriveUserUuid(String taskArriveUserUuid) {
        this.taskArriveUserUuid = taskArriveUserUuid == null ? null : taskArriveUserUuid.trim();
    }

    public String getTaskArriveUserName() {
        return taskArriveUserName;
    }

    public void setTaskArriveUserName(String taskArriveUserName) {
        this.taskArriveUserName = taskArriveUserName == null ? null : taskArriveUserName.trim();
    }

    public String getTaskAcceptTime() {
        return taskAcceptTime;
    }

    public void setTaskAcceptTime(String taskAcceptTime) {
        this.taskAcceptTime = taskAcceptTime;
    }

    public String getTaskReturnTime() {
        return taskReturnTime;
    }

    public void setTaskReturnTime(String taskReturnTime) {
        this.taskReturnTime = taskReturnTime;
    }

    public BigDecimal getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(BigDecimal acceptType) {
        this.acceptType = acceptType;
    }

    public String getTaskChangeAssignUserUuid() {
        return taskChangeAssignUserUuid;
    }

    public void setTaskChangeAssignUserUuid(String taskChangeAssignUserUuid) {
        this.taskChangeAssignUserUuid = taskChangeAssignUserUuid == null ? null : taskChangeAssignUserUuid.trim();
    }

    public String getTaskReturnRemark() {
        return taskReturnRemark;
    }

    public void setTaskReturnRemark(String taskReturnRemark) {
        this.taskReturnRemark = taskReturnRemark == null ? null : taskReturnRemark.trim();
    }
}