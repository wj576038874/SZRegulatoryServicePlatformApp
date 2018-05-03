package com.winfo.szrsp.app.sdk.entity.shipdata.loads_info;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoshiOwner.java
 * Date: 2017/12/21 14:52
 * Description:
 */
public class LaoshiOwner implements Serializable {
    private BigDecimal id;

    private String ownerid;

    private String owneraddress;

    private String ownercall;

    private String ownercountry;

    private String owneremail;

    private String ownerfax;

    private String ownerphone;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
    }

    public String getOwneraddress() {
        return owneraddress;
    }

    public void setOwneraddress(String owneraddress) {
        this.owneraddress = owneraddress == null ? null : owneraddress.trim();
    }

    public String getOwnercall() {
        return ownercall;
    }

    public void setOwnercall(String ownercall) {
        this.ownercall = ownercall == null ? null : ownercall.trim();
    }

    public String getOwnercountry() {
        return ownercountry;
    }

    public void setOwnercountry(String ownercountry) {
        this.ownercountry = ownercountry == null ? null : ownercountry.trim();
    }

    public String getOwneremail() {
        return owneremail;
    }

    public void setOwneremail(String owneremail) {
        this.owneremail = owneremail == null ? null : owneremail.trim();
    }

    public String getOwnerfax() {
        return ownerfax;
    }

    public void setOwnerfax(String ownerfax) {
        this.ownerfax = ownerfax == null ? null : ownerfax.trim();
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone == null ? null : ownerphone.trim();
    }
}