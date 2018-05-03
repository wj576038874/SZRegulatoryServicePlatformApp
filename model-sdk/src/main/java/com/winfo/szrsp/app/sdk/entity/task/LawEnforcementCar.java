package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
//执法车
public class LawEnforcementCar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String plateNum;//车牌号

    private String orgId;//机构编码

    private String deptCode;//部门代码

    private String remark;//备注

    private String baseName;//基地名称

    private String baseAddress;//基地地址

    private String contacts;//联系人

    private String contactsPhoneNum;//联系电话

    private int state;//状态 0，正常等待；1，被申请；2，出勤中；3，已损坏；4，维修中

    private String servicingTime;//需要维修的时间

    private String brand;//品牌

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum == null ? null : plateNum.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName == null ? null : baseName.trim();
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress == null ? null : baseAddress.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactsPhoneNum() {
        return contactsPhoneNum;
    }

    public void setContactsPhoneNum(String contactsPhoneNum) {
        this.contactsPhoneNum = contactsPhoneNum == null ? null : contactsPhoneNum.trim();
    }

    public int getState() {
        return state;
    }


    public void setState(int state) {
		this.state = state;
	}

	public String getServicingTime() {
        return servicingTime;
    }

    public void setServicingTime(String servicingTime) {
        this.servicingTime = servicingTime == null ? null : servicingTime.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }
}