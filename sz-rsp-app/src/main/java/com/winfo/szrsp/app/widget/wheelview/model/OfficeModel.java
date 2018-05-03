package com.winfo.szrsp.app.widget.wheelview.model;

public class OfficeModel {
	private String name;
	private String officeid;
	public OfficeModel() {
		super();
	}

	public OfficeModel(String name,String officeid) {
		super();
		this.name = name;
		this.officeid=officeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	
}
