package com.winfo.szrsp.app.sdk.entity.shipdata.loads_info;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoshiBuilder.java
 * Date: 2017/12/21 14:52
 * Description:
 */
public class LaoshiBuilder implements Serializable {
    private BigDecimal id;

    private String builder;

    private String builderaddress;

    private String buildercall;

    private String buildercontact;

    private String buildercountry;

    private String builderemail;

    private String builderport;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder == null ? null : builder.trim();
    }

    public String getBuilderaddress() {
        return builderaddress;
    }

    public void setBuilderaddress(String builderaddress) {
        this.builderaddress = builderaddress == null ? null : builderaddress.trim();
    }

    public String getBuildercall() {
        return buildercall;
    }

    public void setBuildercall(String buildercall) {
        this.buildercall = buildercall == null ? null : buildercall.trim();
    }

    public String getBuildercontact() {
        return buildercontact;
    }

    public void setBuildercontact(String buildercontact) {
        this.buildercontact = buildercontact == null ? null : buildercontact.trim();
    }

    public String getBuildercountry() {
        return buildercountry;
    }

    public void setBuildercountry(String buildercountry) {
        this.buildercountry = buildercountry == null ? null : buildercountry.trim();
    }

    public String getBuilderemail() {
        return builderemail;
    }

    public void setBuilderemail(String builderemail) {
        this.builderemail = builderemail == null ? null : builderemail.trim();
    }

    public String getBuilderport() {
        return builderport;
    }

    public void setBuilderport(String builderport) {
        this.builderport = builderport == null ? null : builderport.trim();
    }
}