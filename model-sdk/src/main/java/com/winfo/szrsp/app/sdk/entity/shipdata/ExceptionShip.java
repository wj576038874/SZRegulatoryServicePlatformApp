package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExceptionShip implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String mmsi;

    private String shipClassification;

    private String shipNameCn;

    private String shipNameEn;

    private String shipCallSign;

    private String imo;

    private String portOfRegistry;

    private String shipType;

    private String nationality;

    private String shipSeaRiverSign;

    private String shregistrationNumber;

    private String identificationNumber;

    private String deptCode;

    private String orgId;

    private String createDate;

    private String exceptionRemark;

    private BigDecimal status;

    private String manageRemark;

    private String createUserName;

    private String createUserUuid;

    private BigDecimal longitude;

    private BigDecimal latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi == null ? null : mmsi.trim();
    }

    public String getShipClassification() {
        return shipClassification;
    }

    public void setShipClassification(String shipClassification) {
        this.shipClassification = shipClassification == null ? null : shipClassification.trim();
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

    public String getShipCallSign() {
        return shipCallSign;
    }

    public void setShipCallSign(String shipCallSign) {
        this.shipCallSign = shipCallSign == null ? null : shipCallSign.trim();
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo == null ? null : imo.trim();
    }

    public String getPortOfRegistry() {
        return portOfRegistry;
    }

    public void setPortOfRegistry(String portOfRegistry) {
        this.portOfRegistry = portOfRegistry == null ? null : portOfRegistry.trim();
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType == null ? null : shipType.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getShipSeaRiverSign() {
        return shipSeaRiverSign;
    }

    public void setShipSeaRiverSign(String shipSeaRiverSign) {
        this.shipSeaRiverSign = shipSeaRiverSign == null ? null : shipSeaRiverSign.trim();
    }

    public String getShregistrationNumber() {
        return shregistrationNumber;
    }

    public void setShregistrationNumber(String shregistrationNumber) {
        this.shregistrationNumber = shregistrationNumber == null ? null : shregistrationNumber.trim();
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber == null ? null : identificationNumber.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExceptionRemark() {
        return exceptionRemark;
    }

    public void setExceptionRemark(String exceptionRemark) {
        this.exceptionRemark = exceptionRemark == null ? null : exceptionRemark.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getManageRemark() {
        return manageRemark;
    }

    public void setManageRemark(String manageRemark) {
        this.manageRemark = manageRemark == null ? null : manageRemark.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}