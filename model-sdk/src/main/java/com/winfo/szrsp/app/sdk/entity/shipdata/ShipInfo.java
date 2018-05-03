package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfo.java
 * Date: 2017/11/25 13:47
 * Description: 船舶信息
 */

public class ShipInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * MMSI
     */
    private String mmsi;

    /**
     * 船舶分类（中国/外国）
     */
    private String shipClassification;

    /**
     * 中文船名
     */
    private String shipNameCn;

    /**
     * 英文船名
     */
    private String shipNameEn;

    /**
     * 船舶呼号
     */
    private String shipCallSign;

    /**
     * IMO编号
     */
    private String imo;

    /**
     * 船籍港
     */
    private String portOfRegistry;

    /**
     * 船舶种类
     */
    private String shipType;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 海河船标志
     */
    private String shipSeaRiverSign;

    /**
     * 航线标志
     */
    private String routeSign;

    /**
     * 航区
     */
    private String navigationArea;

    /**
     * 建成日期
     */
    private String completionDate;

    /**
     * 船舶长度
     */
    private BigDecimal shipLength;

    /**
     * 船舶型宽
     */
    private BigDecimal shipWide;

    /**
     * 船舶型深
     */
    private BigDecimal shipDepth;

    /**
     * 总吨位
     */
    private BigDecimal totalTonnage;

    /**
     * 净吨位
     */
    private BigDecimal netTonnage;

    /**
     * 载重吨位
     */
    private BigDecimal deadWeightTonnage;

    /**
     * 主机功率
     */
    private BigDecimal hostPower;

    /**
     * 船舶经营人
     */
    private String shipOperator;

    /**
     * 船舶所有人
     */
    private String shipOwner;

    /**
     * 是否有效
     */
    private String isValid;

    /**
     * 船舶主键
     */
    private String shipMajorKey;

    /**
     * 船舶登记号
     */
    private String registrationNumber;

    /**
     * 船舶识别号
     */
    private String identificationNumber;

    /**
     * 初次登记号
     */
    private String initialRegistrationNumber;

    /**
     * 船检登记号
     */
    private String inspectRegistrationNumber;

    /**
     * 曾用中文船名
     */
    private String oldShipNameCn;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 主键
     */
    private BigDecimal id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核状态  1通过，0审核中
     */
    private String status;

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getShipClassification() {
        return shipClassification;
    }

    public void setShipClassification(String shipClassification) {
        this.shipClassification = shipClassification;
    }

    public String getShipNameCn() {
        return shipNameCn;
    }

    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn;
    }

    public String getShipNameEn() {
        return shipNameEn;
    }

    public void setShipNameEn(String shipNameEn) {
        this.shipNameEn = shipNameEn;
    }

    public String getShipCallSign() {
        return shipCallSign;
    }

    public void setShipCallSign(String shipCallSign) {
        this.shipCallSign = shipCallSign;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getPortOfRegistry() {
        return portOfRegistry;
    }

    public void setPortOfRegistry(String portOfRegistry) {
        this.portOfRegistry = portOfRegistry;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getShipSeaRiverSign() {
        return shipSeaRiverSign;
    }

    public void setShipSeaRiverSign(String shipSeaRiverSign) {
        this.shipSeaRiverSign = shipSeaRiverSign;
    }

    public String getRouteSign() {
        return routeSign;
    }

    public void setRouteSign(String routeSign) {
        this.routeSign = routeSign;
    }

    public String getNavigationArea() {
        return navigationArea;
    }

    public void setNavigationArea(String navigationArea) {
        this.navigationArea = navigationArea;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public BigDecimal getShipLength() {
        return shipLength;
    }

    public void setShipLength(BigDecimal shipLength) {
        this.shipLength = shipLength;
    }

    public BigDecimal getShipWide() {
        return shipWide;
    }

    public void setShipWide(BigDecimal shipWide) {
        this.shipWide = shipWide;
    }

    public BigDecimal getShipDepth() {
        return shipDepth;
    }

    public void setShipDepth(BigDecimal shipDepth) {
        this.shipDepth = shipDepth;
    }

    public BigDecimal getTotalTonnage() {
        return totalTonnage;
    }

    public void setTotalTonnage(BigDecimal totalTonnage) {
        this.totalTonnage = totalTonnage;
    }

    public BigDecimal getNetTonnage() {
        return netTonnage;
    }

    public void setNetTonnage(BigDecimal netTonnage) {
        this.netTonnage = netTonnage;
    }

    public BigDecimal getDeadWeightTonnage() {
        return deadWeightTonnage;
    }

    public void setDeadWeightTonnage(BigDecimal deadWeightTonnage) {
        this.deadWeightTonnage = deadWeightTonnage;
    }

    public BigDecimal getHostPower() {
        return hostPower;
    }

    public void setHostPower(BigDecimal hostPower) {
        this.hostPower = hostPower;
    }

    public String getShipOperator() {
        return shipOperator;
    }

    public void setShipOperator(String shipOperator) {
        this.shipOperator = shipOperator;
    }

    public String getShipOwner() {
        return shipOwner;
    }

    public void setShipOwner(String shipOwner) {
        this.shipOwner = shipOwner;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getShipMajorKey() {
        return shipMajorKey;
    }

    public void setShipMajorKey(String shipMajorKey) {
        this.shipMajorKey = shipMajorKey;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getInitialRegistrationNumber() {
        return initialRegistrationNumber;
    }

    public void setInitialRegistrationNumber(String initialRegistrationNumber) {
        this.initialRegistrationNumber = initialRegistrationNumber;
    }

    public String getInspectRegistrationNumber() {
        return inspectRegistrationNumber;
    }

    public void setInspectRegistrationNumber(String inspectRegistrationNumber) {
        this.inspectRegistrationNumber = inspectRegistrationNumber;
    }

    public String getOldShipNameCn() {
        return oldShipNameCn;
    }

    public void setOldShipNameCn(String oldShipNameCn) {
        this.oldShipNameCn = oldShipNameCn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
