package com.winfo.szrsp.app.mvp.shipinfo.model.param;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.model.param
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.model.param.ParamsShip.java
 * Date: 2018/1/13 16:51
 * Description:
 */
public class ParamsShip implements Serializable {
    private String ship_name_en;
    private String ship_id;
    private String ship_name_cn;
    private String mmsi;
    private String ship_imo;

    public String getShip_name_en() {
        return ship_name_en;
    }

    public void setShip_name_en(String ship_name_en) {
        this.ship_name_en = ship_name_en;
    }

    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name_cn() {
        return ship_name_cn;
    }

    public void setShip_name_cn(String ship_name_cn) {
        this.ship_name_cn = ship_name_cn;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getShip_imo() {
        return ship_imo;
    }

    public void setShip_imo(String ship_imo) {
        this.ship_imo = ship_imo;
    }
}
