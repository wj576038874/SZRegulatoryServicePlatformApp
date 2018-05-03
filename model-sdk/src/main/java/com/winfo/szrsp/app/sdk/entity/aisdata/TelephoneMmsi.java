package com.winfo.szrsp.app.sdk.entity.aisdata;

import java.io.Serializable;

public class TelephoneMmsi implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private String mmsi;

    private String telephoneNum;

    private String teleName;

    private String remark;

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi == null ? null : mmsi.trim();
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum == null ? null : telephoneNum.trim();
    }

    public String getTeleName() {
        return teleName;
    }

    public void setTeleName(String teleName) {
        this.teleName = teleName == null ? null : teleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}