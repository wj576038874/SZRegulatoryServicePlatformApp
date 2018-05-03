package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TaskInfo implements Serializable {
	private String taskId;

	private String taskName;

	private String taskDesc;

	private String taskOriginId;

	private BigDecimal status;

	private String taskReleaseOrgId;

	private String taskReleaseOrgName;

	private String taskReleaseDeptCode;

	private String taskReleaseDeptName;

	private String estimatedStartTime;

	private String estimatedEndTime;

	private String taskReleaseUserUuid;

	private String createUserUuid;

	private String createTime;

	private String taskReleaseTime;

	private String taskStartTime;

	private String taskEndTime;

	private String spotDesc;

	private String taskTypeId;

	private String remark;

	private String enforceStaffId;

	private String enforceStaffName;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private String address;

	private String checkFormIds;

	private String checkFormTypeIds;

	private String disposalDecision;

	private String captainsAdvise;
	private String taskAssignStatus;

	private String taskTypeName;
	private String taskTypeTitleTemp;
	private String taskResourcesStr;
	private String controlType;

	private String controlId;
	private String taskLevel;

	private String taskOriginTypeId;
	private String taskGoalYearsId;

	private String taskGoalMonthId;

	private String taskGoalSpecialId;

	private String taskOrigin;
	private String taskArriveOrgId;

	private String taskArriveOrgName;

	private String taskArriveDeptCode;

	private String taskArriveDeptName;

	private String taskGoalName;

	private List<TaskType> taskTypeList;
	private List<TaskAssign> taskAssignList;
	private List<TaskResources> taskResources;
	private static final long serialVersionUID = 1L;

	public String getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	public String getTaskArriveOrgId() {
		return taskArriveOrgId;
	}

	public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}

	public void setTaskTypeList(List<TaskType> taskTypeList) {
		taskTypeList = taskTypeList;
	}

	public void setTaskArriveOrgId(String taskArriveOrgId) {
		this.taskArriveOrgId = taskArriveOrgId;
	}

	public String getTaskArriveOrgName() {
		return taskArriveOrgName;
	}

	public void setTaskArriveOrgName(String taskArriveOrgName) {
		this.taskArriveOrgName = taskArriveOrgName;
	}

	public String getTaskArriveDeptCode() {
		return taskArriveDeptCode;
	}

	public void setTaskArriveDeptCode(String taskArriveDeptCode) {
		this.taskArriveDeptCode = taskArriveDeptCode;
	}

	public String getTaskArriveDeptName() {
		return taskArriveDeptName;
	}

	public void setTaskArriveDeptName(String taskArriveDeptName) {
		this.taskArriveDeptName = taskArriveDeptName;
	}

	public String getTaskGoalName() {
		return taskGoalName;
	}

	public void setTaskGoalName(String taskGoalName) {
		this.taskGoalName = taskGoalName;
	}

	public String getTaskOriginTypeId() {
		return taskOriginTypeId;
	}

	public String getTaskGoalYearsId() {
		return taskGoalYearsId;
	}

	public void setTaskGoalYearsId(String taskGoalYearsId) {
		this.taskGoalYearsId = taskGoalYearsId;
	}

	public String getTaskGoalMonthId() {
		return taskGoalMonthId;
	}

	public void setTaskGoalMonthId(String taskGoalMonthId) {
		this.taskGoalMonthId = taskGoalMonthId;
	}

	public String getTaskGoalSpecialId() {
		return taskGoalSpecialId;
	}

	public void setTaskGoalSpecialId(String taskGoalSpecialId) {
		this.taskGoalSpecialId = taskGoalSpecialId;
	}

	public String getTaskOrigin() {
		return taskOrigin;
	}

	public void setTaskOrigin(String taskOrigin) {
		this.taskOrigin = taskOrigin;
	}

	public void setTaskOriginTypeId(String taskOriginTypeId) {
		this.taskOriginTypeId = taskOriginTypeId;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public String getTaskTypeTitleTemp() {
		return taskTypeTitleTemp;
	}

	public String getTaskResourcesStr() {
		return taskResourcesStr;
	}

	public void setTaskResourcesStr(String taskResourcesStr) {
		this.taskResourcesStr = taskResourcesStr;
	}

	public String getTaskAssignStatus() {
		return taskAssignStatus;
	}

	public void setTaskAssignStatus(String taskAssignStatus) {
		this.taskAssignStatus = taskAssignStatus;
	}

	public void setTaskTypeTitleTemp(String taskTypeTitleTemp) {
		this.taskTypeTitleTemp = taskTypeTitleTemp;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getCaptainsAdvise() {
		return captainsAdvise;
	}

	public String getCheckFormIds() {
		return checkFormIds;
	}

	public void setCheckFormIds(String checkFormIds) {
		this.checkFormIds = checkFormIds;
	}

	public String getCheckFormTypeIds() {
		return checkFormTypeIds;
	}

	public void setCheckFormTypeIds(String checkFormTypeIds) {
		this.checkFormTypeIds = checkFormTypeIds;
	}

	public void setCaptainsAdvise(String captainsAdvise) {
		this.captainsAdvise = captainsAdvise;
	}

	public String getDisposalDecision() {
		return disposalDecision;
	}

	public void setDisposalDecision(String disposalDecision) {
		this.disposalDecision = disposalDecision;
	}

	public List<TaskResources> getTaskResources() {
		return taskResources;
	}

	public void setTaskResources(List<TaskResources> taskResources) {
		this.taskResources = taskResources;
	}

	public List<TaskAssign> getTaskAssignList() {
		return taskAssignList;
	}

	public void setTaskAssignList(List<TaskAssign> taskAssignList) {
		this.taskAssignList = taskAssignList;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc == null ? null : taskDesc.trim();
	}

	public String getTaskOriginId() {
		return taskOriginId;
	}

	public void setTaskOriginId(String taskOriginId) {
		this.taskOriginId = taskOriginId == null ? null : taskOriginId.trim();
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTaskReleaseOrgId() {
		return taskReleaseOrgId;
	}

	public void setTaskReleaseOrgId(String taskReleaseOrgId) {
		this.taskReleaseOrgId = taskReleaseOrgId == null ? null : taskReleaseOrgId.trim();
	}

	public String getTaskReleaseOrgName() {
		return taskReleaseOrgName;
	}

	public void setTaskReleaseOrgName(String taskReleaseOrgName) {
		this.taskReleaseOrgName = taskReleaseOrgName == null ? null : taskReleaseOrgName.trim();
	}

	public String getTaskReleaseDeptCode() {
		return taskReleaseDeptCode;
	}

	public void setTaskReleaseDeptCode(String taskReleaseDeptCode) {
		this.taskReleaseDeptCode = taskReleaseDeptCode == null ? null : taskReleaseDeptCode.trim();
	}

	public String getTaskReleaseDeptName() {
		return taskReleaseDeptName;
	}

	public void setTaskReleaseDeptName(String taskReleaseDeptName) {
		this.taskReleaseDeptName = taskReleaseDeptName == null ? null : taskReleaseDeptName.trim();
	}

	public String getEstimatedStartTime() {
		return estimatedStartTime;
	}

	public void setEstimatedStartTime(String estimatedStartTime) {
		this.estimatedStartTime = estimatedStartTime;
	}

	public String getEstimatedEndTime() {
		return estimatedEndTime;
	}

	public void setEstimatedEndTime(String estimatedEndTime) {
		this.estimatedEndTime = estimatedEndTime;
	}

	public String getTaskReleaseUserUuid() {
		return taskReleaseUserUuid;
	}

	public void setTaskReleaseUserUuid(String taskReleaseUserUuid) {
		this.taskReleaseUserUuid = taskReleaseUserUuid == null ? null : taskReleaseUserUuid.trim();
	}

	public String getCreateUserUuid() {
		return createUserUuid;
	}

	public void setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTaskReleaseTime() {
		return taskReleaseTime;
	}

	public void setTaskReleaseTime(String taskReleaseTime) {
		this.taskReleaseTime = taskReleaseTime;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getSpotDesc() {
		return spotDesc;
	}

	public void setSpotDesc(String spotDesc) {
		this.spotDesc = spotDesc == null ? null : spotDesc.trim();
	}

	public String getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId == null ? null : taskTypeId.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getEnforceStaffId() {
		return enforceStaffId;
	}

	public void setEnforceStaffId(String enforceStaffId) {
		this.enforceStaffId = enforceStaffId == null ? null : enforceStaffId.trim();
	}

	public String getEnforceStaffName() {
		return enforceStaffName;
	}

	public void setEnforceStaffName(String enforceStaffName) {
		this.enforceStaffName = enforceStaffName == null ? null : enforceStaffName.trim();
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}
}