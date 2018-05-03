package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.util.List;

public class CtPscFromObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CtPscFrom info;
	
	private List<CtPscFromA> detaila;
	
	private List<CtPscFromB> detailb;

	public CtPscFrom getInfo() {
		return info;
	}

	public void setInfo(CtPscFrom info) {
		this.info = info;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<CtPscFromA> getDetaila() {
		return detaila;
	}

	public void setDetaila(List<CtPscFromA> detaila) {
		this.detaila = detaila;
	}

	public List<CtPscFromB> getDetailb() {
		return detailb;
	}

	public void setDetailb(List<CtPscFromB> detailb) {
		this.detailb = detailb;
	}
}
