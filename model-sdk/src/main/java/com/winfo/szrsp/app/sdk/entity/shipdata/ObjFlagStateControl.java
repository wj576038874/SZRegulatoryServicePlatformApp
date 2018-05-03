package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.util.List;

public class ObjFlagStateControl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 安检信息
	 */
	private List<FlagStateControl> flagStateControlList;
	private FlagStateControl flagStateControl;
	/**
	 * 缺陷详情集合
	 */
	private List<FlagStateControlDetail> flagStateControlDetail;

	public List<FlagStateControl> getFlagStateControlList() {
		return flagStateControlList;
	}

	public void setFlagStateControlList(List<FlagStateControl> flagStateControlList) {
		this.flagStateControlList = flagStateControlList;
	}

	public FlagStateControl getFlagStateControl() {
		return flagStateControl;
	}

	public void setFlagStateControl(FlagStateControl flagStateControl) {
		this.flagStateControl = flagStateControl;
	}

	public List<FlagStateControlDetail> getFlagStateControlDetail() {
		return flagStateControlDetail;
	}

	public void setFlagStateControlDetail(List<FlagStateControlDetail> flagStateControlDetail) {
		this.flagStateControlDetail = flagStateControlDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
