package com.winfo.szrsp.app.sdk.entity.thys;

import java.io.Serializable;

/**
 * Created by HoBo on 2018/4/2.
 */

public class ShipData implements Serializable {

    /**
     * operateMark : 0
     * operateTime : 2001-01-01 00:00:00
     * operatorCode : 0000000001
     * shipTypeCode : 0903
     * shipTypeNameCn : 救助船
     * shipTypeNameEn : RESCUE BOAT
     * valid : 1
     */
    private static final long serialVersionUID = 1L;
    private String operateMark;
    private String operateTime;
    private String operatorCode;
    private String shipTypeCode;
    private String shipTypeNameCn;
    private String shipTypeNameEn;
    private String valid;

    public String getOperateMark() {
        return operateMark;
    }

    public void setOperateMark(String operateMark) {
        this.operateMark = operateMark;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getShipTypeCode() {
        return shipTypeCode;
    }

    public void setShipTypeCode(String shipTypeCode) {
        this.shipTypeCode = shipTypeCode;
    }

    public String getShipTypeNameCn() {
        return shipTypeNameCn;
    }

    public void setShipTypeNameCn(String shipTypeNameCn) {
        this.shipTypeNameCn = shipTypeNameCn;
    }

    public String getShipTypeNameEn() {
        return shipTypeNameEn;
    }

    public void setShipTypeNameEn(String shipTypeNameEn) {
        this.shipTypeNameEn = shipTypeNameEn;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
