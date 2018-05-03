package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;

public class TaskInfoDetails implements Serializable {
    private String taskId;

    private String mmsi;

    private String shipNameCn;
    private String shipNo;

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    private String shipNameEn;

    private String shipCallsign;

    private String shipImo;

    private String shipType;

    private BigDecimal shipLongitude;

    private BigDecimal shipLatitude;

    private String berthCode;

    private String berthName;

    private String shipCompanyName;

    private String shipCompanyId;

    private String checkFormTypeIds;

    private String checkFormIds;

    private String identificationNumber;//识别号

    private String portOfRegistry;//船旗港

    private String nationality;//国籍

    private String shipOperator;//管理人

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getPortOfRegistry() {
        return portOfRegistry;
    }

    public void setPortOfRegistry(String portOfRegistry) {
        this.portOfRegistry = portOfRegistry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getShipOperator() {
        return shipOperator;
    }

    public void setShipOperator(String shipOperator) {
        this.shipOperator = shipOperator;
    }

    private static final long serialVersionUID = 1L;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi == null ? null : mmsi.trim();
    }

    public String getShipNameCn() {
        return shipNameCn;
    }

    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn == null ? null : shipNameCn.trim();
    }

    public String getShipNameEn() {
        return shipNameEn;
    }

    public void setShipNameEn(String shipNameEn) {
        this.shipNameEn = shipNameEn == null ? null : shipNameEn.trim();
    }

    public String getShipCallsign() {
        return shipCallsign;
    }

    public void setShipCallsign(String shipCallsign) {
        this.shipCallsign = shipCallsign == null ? null : shipCallsign.trim();
    }

    public String getShipImo() {
        return shipImo;
    }

    public void setShipImo(String shipImo) {
        this.shipImo = shipImo == null ? null : shipImo.trim();
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType == null ? null : shipType.trim();
    }

    public BigDecimal getShipLongitude() {
        return shipLongitude;
    }

    public void setShipLongitude(BigDecimal shipLongitude) {
        this.shipLongitude = shipLongitude;
    }

    public BigDecimal getShipLatitude() {
        return shipLatitude;
    }

    public void setShipLatitude(BigDecimal shipLatitude) {
        this.shipLatitude = shipLatitude;
    }

    public String getBerthCode() {
        return berthCode;
    }

    public void setBerthCode(String berthCode) {
        this.berthCode = berthCode == null ? null : berthCode.trim();
    }

    public String getBerthName() {
        return berthName;
    }

    public void setBerthName(String berthName) {
        this.berthName = berthName == null ? null : berthName.trim();
    }

    public String getShipCompanyName() {
        return shipCompanyName;
    }

    public void setShipCompanyName(String shipCompanyName) {
        this.shipCompanyName = shipCompanyName == null ? null : shipCompanyName.trim();
    }

    public String getShipCompanyId() {
        return shipCompanyId;
    }

    public void setShipCompanyId(String shipCompanyId) {
        this.shipCompanyId = shipCompanyId == null ? null : shipCompanyId.trim();
    }

    public String getCheckFormTypeIds() {
        return checkFormTypeIds;
    }

    public void setCheckFormTypeIds(String checkFormTypeIds) {
        this.checkFormTypeIds = checkFormTypeIds == null ? null : checkFormTypeIds.trim();
    }

    public String getCheckFormIds() {
        return checkFormIds;
    }

    public void setCheckFormIds(String checkFormIds) {
        this.checkFormIds = checkFormIds == null ? null : checkFormIds.trim();
    }
}