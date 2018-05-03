package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.util.List;
/**
 * 船舶危防现场监督检查记录表 完整实体类
 * @author Administrator
 *
 */
public class CtSafeInspectNoticeObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CtSafetySceneInspect info;
	
	private List<CtSafetySceneInspectDetail> details;

	public CtSafetySceneInspect getInfo() {
		return info;
	}

	public void setInfo(CtSafetySceneInspect info) {
		this.info = info;
	}

	public List<CtSafetySceneInspectDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CtSafetySceneInspectDetail> details) {
		this.details = details;
	}

}
