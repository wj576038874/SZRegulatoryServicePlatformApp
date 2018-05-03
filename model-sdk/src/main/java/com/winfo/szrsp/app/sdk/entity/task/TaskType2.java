package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 任务类型又称项目类型 实体类
 * @author Albert
 *
 */
public class TaskType2 implements Serializable {
	 private String taskTypeId;

	    private String taskTypeName;

	    private String taskTypeTitleTemp;

	    private String status;

	    private String typeFather;

	    private String isShipType;

	    private String isAddCheckItem;

	    private String createTime;

	    private String createUserUuid;

	    private String comeFrom;
	    
	    private String createUserName;
	    
	    private String remark;
	    private Object url;

	private boolean canClick=true;;
	private boolean isChecked;

	public boolean isCanClick() {
		return canClick;
	}

	public void setCanClick(boolean canClick) {
		this.canClick = canClick;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public Object getUrl() {
			return url;
		}

		public void setUrl(Object url) {
			this.url = url;
		}
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

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status == null ? null : status.trim();
	    }

	    public String getTypeFather() {
	        return typeFather;
	    }

	    public void setTypeFather(String typeFather) {
	        this.typeFather = typeFather == null ? null : typeFather.trim();
	    }

	    public String getIsShipType() {
	        return isShipType;
	    }

	    public void setIsShipType(String isShipType) {
	        this.isShipType = isShipType == null ? null : isShipType.trim();
	    }

	    public String getIsAddCheckItem() {
	        return isAddCheckItem;
	    }

	    public void setIsAddCheckItem(String isAddCheckItem) {
	        this.isAddCheckItem = isAddCheckItem == null ? null : isAddCheckItem.trim();
	    }

	    public String getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(String createTime) {
	        this.createTime = createTime;
	    }

	    public String getCreateUserUuid() {
	        return createUserUuid;
	    }

	    public void setCreateUserUuid(String createUserUuid) {
	        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
	    }

	    public String getComeFrom() {
	        return comeFrom;
	    }

	    public void setComeFrom(String comeFrom) {
	        this.comeFrom = comeFrom == null ? null : comeFrom.trim();
	    }
	    
	    public String getCreateUserName(){
	        return createUserName;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }
	    public String getRemark(){
	        return remark;
	    }

	    public void setCreateUserName(String createUserName) {
	        this.createUserName = createUserName == null ? null : createUserName.trim();
	    }

	    
	}