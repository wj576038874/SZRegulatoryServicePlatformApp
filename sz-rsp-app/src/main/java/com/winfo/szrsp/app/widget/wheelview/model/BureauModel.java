package com.winfo.szrsp.app.widget.wheelview.model;

import java.util.List;


public class BureauModel {
	private String name;
	private String bureauid;
	private List<OfficeModel> officeList;
	public BureauModel() {
		super();
	}

	public BureauModel(String name, String bureauid,List<OfficeModel> officeList) {
		super();
		this.name = name;
		this.bureauid = bureauid;
		this.officeList = officeList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OfficeModel> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<OfficeModel> officeList) {
		this.officeList = officeList;
	}

	public String getBureauid() {
		return bureauid;
	}

	public void setBureauid(String bureauid) {
		this.bureauid = bureauid;
	}
	
	
}
