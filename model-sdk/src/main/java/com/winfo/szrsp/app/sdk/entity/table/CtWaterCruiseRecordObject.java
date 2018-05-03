package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.util.List;

public class CtWaterCruiseRecordObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CtWaterCruiseRecordInfo info;
	
	private List<CtWaterCruiseReport> listArea;
	
	private List<CtWaterCruiseShipInspect> listShip;


	public CtWaterCruiseRecordInfo getInfo() {
		return info;
	}

	public void setInfo(CtWaterCruiseRecordInfo info) {
		this.info = info;
	}

	public List<CtWaterCruiseReport> getListArea() {
		return listArea;
	}

	public void setListArea(List<CtWaterCruiseReport> listArea) {
		this.listArea = listArea;
	}

	public List<CtWaterCruiseShipInspect> getListShip() {
		return listShip;
	}

	public void setListShip(List<CtWaterCruiseShipInspect> listShip) {
		this.listShip = listShip;
	}
	
}
