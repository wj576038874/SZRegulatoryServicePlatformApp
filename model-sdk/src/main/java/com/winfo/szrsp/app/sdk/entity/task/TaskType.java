package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;

public class TaskType implements Serializable {
	  private String taskTypeId;

	    private String taskTypeName;

	    private String taskTypeTitleTemp;

	    private BigDecimal status;

	    private String taskTypeTitle;

	    private String typeFather;

	    private String typeFatherName;

	    private static final long serialVersionUID = 1L;

	    public String getTaskTypeId() {
	        return taskTypeId;
	    }

	    public void setTaskTypeId(String taskTypeId) {
	        this.taskTypeId = taskTypeId == null ? null : taskTypeId.trim();
	    }

	    public String getTaskTypeName() {
	        return taskTypeName;
	    }

	    public void setTaskTypeName(String taskTypeName) {
	        this.taskTypeName = taskTypeName == null ? null : taskTypeName.trim();
	    }

	    public String getTaskTypeTitleTemp() {
	        return taskTypeTitleTemp;
	    }

	    public void setTaskTypeTitleTemp(String taskTypeTitleTemp) {
	        this.taskTypeTitleTemp = taskTypeTitleTemp == null ? null : taskTypeTitleTemp.trim();
	    }

	    public BigDecimal getStatus() {
	        return status;
	    }

	    public void setStatus(BigDecimal status) {
	        this.status = status;
	    }

	    public String getTaskTypeTitle() {
	        return taskTypeTitle;
	    }

	    public void setTaskTypeTitle(String taskTypeTitle) {
	        this.taskTypeTitle = taskTypeTitle == null ? null : taskTypeTitle.trim();
	    }

	    public String getTypeFather() {
	        return typeFather;
	    }

	    public void setTypeFather(String typeFather) {
	        this.typeFather = typeFather == null ? null : typeFather.trim();
	    }

	    public String getTypeFatherName() {
	        return typeFatherName;
	    }

	    public void setTypeFatherName(String typeFatherName) {
	        this.typeFatherName = typeFatherName == null ? null : typeFatherName.trim();
	    }
	}