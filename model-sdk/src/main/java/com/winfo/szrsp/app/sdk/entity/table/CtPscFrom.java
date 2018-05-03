package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;

public class CtPscFrom implements Serializable {
    private String inspectNo;

    private String voyageId;

    private String shipNo;

    private String inspectOrg;

    private String inspectorCode;

    private String inspectorName;

    private String authorityName;

    private String shipId;

    private String shipNameEn;

    private String shipFlag;

    private String shipTypeCode;

    private String shipTypeNameEn;

    private String signCall;

    private String mmsi;

    private String shipImo;

    private BigDecimal shipGrosston;

    private BigDecimal shipDwt;

    private BigDecimal keelYear;

    private String berthCode;

    private String society;

    private String releaseTime;

    private String companyImo;

    private String companyPariculars;

    private String captainName;

    private String isDefect;

    private String detentionMark;

    private String isDocumentat;

    private String issuingOffice;

    private String issuingName;

    private String orgTelephone;

    private String orgTelefax;

    private String orgMail;

    private String createUserUuid;
    

    private String createTime;

    private String deptCode;
    
    private String busiNo;

    private String taskId;

    private String isApp;

    private String inspectDate;
//  只是为了获取前端传送的签名路径
    private String captainNameAutograph;
    
    private String orgSignature;

    private static final long serialVersionUID = 1L;


    private BigDecimal captainWidth;

    private BigDecimal captainHeight;

    private BigDecimal orgWidth;

    private BigDecimal orgHeight;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getCaptainWidth() {
        return captainWidth;
    }

    public void setCaptainWidth(BigDecimal captainWidth) {
        this.captainWidth = captainWidth;
    }

    public BigDecimal getCaptainHeight() {
        return captainHeight;
    }

    public void setCaptainHeight(BigDecimal captainHeight) {
        this.captainHeight = captainHeight;
    }

    public BigDecimal getOrgWidth() {
        return orgWidth;
    }

    public void setOrgWidth(BigDecimal orgWidth) {
        this.orgWidth = orgWidth;
    }

    public BigDecimal getOrgHeight() {
        return orgHeight;
    }

    public void setOrgHeight(BigDecimal orgHeight) {
        this.orgHeight = orgHeight;
    }

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public String getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId == null ? null : voyageId.trim();
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo == null ? null : shipNo.trim();
    }

    public String getInspectOrg() {
        return inspectOrg;
    }

    public void setInspectOrg(String inspectOrg) {
        this.inspectOrg = inspectOrg == null ? null : inspectOrg.trim();
    }

    public String getInspectorCode() {
        return inspectorCode;
    }

    public void setInspectorCode(String inspectorCode) {
        this.inspectorCode = inspectorCode == null ? null : inspectorCode.trim();
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName == null ? null : inspectorName.trim();
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName == null ? null : authorityName.trim();
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId == null ? null : shipId.trim();
    }

    public String getShipNameEn() {
        return shipNameEn;
    }

    public void setShipNameEn(String shipNameEn) {
        this.shipNameEn = shipNameEn == null ? null : shipNameEn.trim();
    }

    public String getShipFlag() {
        return shipFlag;
    }

    public void setShipFlag(String shipFlag) {
        this.shipFlag = shipFlag == null ? null : shipFlag.trim();
    }

    public String getShipTypeCode() {
        return shipTypeCode;
    }

    public void setShipTypeCode(String shipTypeCode) {
        this.shipTypeCode = shipTypeCode == null ? null : shipTypeCode.trim();
    }

    public String getShipTypeNameEn() {
        return shipTypeNameEn;
    }

    public void setShipTypeNameEn(String shipTypeNameEn) {
        this.shipTypeNameEn = shipTypeNameEn == null ? null : shipTypeNameEn.trim();
    }

    public String getSignCall() {
        return signCall;
    }

    public void setSignCall(String signCall) {
        this.signCall = signCall == null ? null : signCall.trim();
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi == null ? null : mmsi.trim();
    }

    public String getShipImo() {
        return shipImo;
    }

    public void setShipImo(String shipImo) {
        this.shipImo = shipImo == null ? null : shipImo.trim();
    }

    public BigDecimal getShipGrosston() {
        return shipGrosston;
    }

    public void setShipGrosston(BigDecimal shipGrosston) {
        this.shipGrosston = shipGrosston;
    }

    public BigDecimal getShipDwt() {
        return shipDwt;
    }

    public void setShipDwt(BigDecimal shipDwt) {
        this.shipDwt = shipDwt;
    }

    public BigDecimal getKeelYear() {
        return keelYear;
    }

    public void setKeelYear(BigDecimal keelYear) {
        this.keelYear = keelYear;
    }

    public String getBerthCode() {
        return berthCode;
    }

    public void setBerthCode(String berthCode) {
        this.berthCode = berthCode == null ? null : berthCode.trim();
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society == null ? null : society.trim();
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCompanyImo() {
        return companyImo;
    }

    public void setCompanyImo(String companyImo) {
        this.companyImo = companyImo == null ? null : companyImo.trim();
    }

    public String getCompanyPariculars() {
        return companyPariculars;
    }

    public void setCompanyPariculars(String companyPariculars) {
        this.companyPariculars = companyPariculars == null ? null : companyPariculars.trim();
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName == null ? null : captainName.trim();
    }

    public String getIsDefect() {
        return isDefect;
    }

    public void setIsDefect(String isDefect) {
        this.isDefect = isDefect == null ? null : isDefect.trim();
    }

    public String getDetentionMark() {
        return detentionMark;
    }

    public void setDetentionMark(String detentionMark) {
        this.detentionMark = detentionMark == null ? null : detentionMark.trim();
    }

    public String getIsDocumentat() {
        return isDocumentat;
    }

    public void setIsDocumentat(String isDocumentat) {
        this.isDocumentat = isDocumentat == null ? null : isDocumentat.trim();
    }

    public String getIssuingOffice() {
        return issuingOffice;
    }

    public void setIssuingOffice(String issuingOffice) {
        this.issuingOffice = issuingOffice == null ? null : issuingOffice.trim();
    }

    public String getIssuingName() {
        return issuingName;
    }

    public void setIssuingName(String issuingName) {
        this.issuingName = issuingName == null ? null : issuingName.trim();
    }

    public String getOrgTelephone() {
        return orgTelephone;
    }

    public void setOrgTelephone(String orgTelephone) {
        this.orgTelephone = orgTelephone == null ? null : orgTelephone.trim();
    }

    public String getOrgTelefax() {
        return orgTelefax;
    }

    public void setOrgTelefax(String orgTelefax) {
        this.orgTelefax = orgTelefax == null ? null : orgTelefax.trim();
    }

    public String getOrgMail() {
        return orgMail;
    }

    public void setOrgMail(String orgMail) {
        this.orgMail = orgMail == null ? null : orgMail.trim();
    }

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
    
    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo == null ? null : busiNo.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getIsApp() {
        return isApp;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp == null ? null : isApp.trim();
    }

    public String getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(String inspectDate) {
        this.inspectDate = inspectDate == null ? null : inspectDate.trim();
    }

	public String getOrgSignature() {
		return orgSignature;
	}

	public void setOrgSignature(String orgSignature) {
		this.orgSignature = orgSignature == null ? null : orgSignature.trim();
	}

	public String getCaptainNameAutograph() {
		return captainNameAutograph;
	}

	public void setCaptainNameAutograph(String captainNameAutograph) {
		this.captainNameAutograph = captainNameAutograph == null ? null : captainNameAutograph.trim();
	}
}