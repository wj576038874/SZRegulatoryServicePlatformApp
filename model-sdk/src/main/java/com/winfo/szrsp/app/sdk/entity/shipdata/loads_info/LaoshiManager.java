package com.winfo.szrsp.app.sdk.entity.shipdata.loads_info;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoshiManager.java
 * Date: 2017/12/21 14:52
 * Description:
 */
public class LaoshiManager implements Serializable {
    private BigDecimal id;

    private String shipmanager;

    private String manageraddress;

    private String managercall;

    private String managercountry;

    private String manageremail;

    private String managerfax;

    private String managerphone;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getShipmanager() {
        return shipmanager;
    }

    public void setShipmanager(String shipmanager) {
        this.shipmanager = shipmanager == null ? null : shipmanager.trim();
    }

    public String getManageraddress() {
        return manageraddress;
    }

    public void setManageraddress(String manageraddress) {
        this.manageraddress = manageraddress == null ? null : manageraddress.trim();
    }

    public String getManagercall() {
        return managercall;
    }

    public void setManagercall(String managercall) {
        this.managercall = managercall == null ? null : managercall.trim();
    }

    public String getManagercountry() {
        return managercountry;
    }

    public void setManagercountry(String managercountry) {
        this.managercountry = managercountry == null ? null : managercountry.trim();
    }

    public String getManageremail() {
        return manageremail;
    }

    public void setManageremail(String manageremail) {
        this.manageremail = manageremail == null ? null : manageremail.trim();
    }

    public String getManagerfax() {
        return managerfax;
    }

    public void setManagerfax(String managerfax) {
        this.managerfax = managerfax == null ? null : managerfax.trim();
    }

    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone == null ? null : managerphone.trim();
    }
}