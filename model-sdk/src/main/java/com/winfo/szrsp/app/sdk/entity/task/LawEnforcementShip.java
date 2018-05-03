package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;
//执法船
public class LawEnforcementShip implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mmsi;
	
	private String shipNameEn;//英文船名
	
	private String orgId;//机构编号
	
	private String deptCode;//部门编号
	
	private String remark;//备注
	
	private String shipType;//类型
	
	private String baseName;//基地名称
	
	private String baseAddress;//基地地址
	
	private BigDecimal length;//长度米
	
	private String contacts;//联系人
	
	private String contactsPhoneNum;//联系电话
	
	private BigDecimal state;//状态 0，正常等待；1，被申请；2，出勤中；3，已损坏；4，维修中]
	
	private String servicingTime;//所需维修时间
	
	private String shipNameCn;//中文船名

	public String getMmsi() {
		return mmsi;
	}

	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}

	public String getShipNameEn() {
		return shipNameEn;
	}

	public void setShipNameEn(String shipNameEn) {
		this.shipNameEn = shipNameEn;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getBaseAddress() {
		return baseAddress;
	}

	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhoneNum() {
		return contactsPhoneNum;
	}

	public void setContactsPhoneNum(String contactsPhoneNum) {
		this.contactsPhoneNum = contactsPhoneNum;
	}

	public BigDecimal getState() {
		return state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public String getServicingTime() {
		return servicingTime;
	}

	public void setServicingTime(String servicingTime) {
		this.servicingTime = servicingTime;
	}

	public String getShipNameCn() {
		return shipNameCn;
	}

	public void setShipNameCn(String shipNameCn) {
		this.shipNameCn = shipNameCn;
	}
	
	
	
}