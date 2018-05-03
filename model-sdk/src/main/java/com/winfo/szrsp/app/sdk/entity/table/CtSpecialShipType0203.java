package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;

//大型散货船专项检查表
public class CtSpecialShipType0203 implements Serializable {
	
//	private static final long serialVersionUID = 1L;
	
    private String inspectNo;

    private String voyageId;

    private String bustNo;

    private String taskId;

    private String shipNo;

    private String shipNameCn;

    private String shipImo;

    private String berthCode;
    
//    @JSONField (format="yyyy-MM-dd")
    private String inspectDate;

    private String inspectOrg;

    private String inspectorCode;

    private String inspectorName;

    private String ipspectItem10;

    private String ipspectItem11;

    private String ipspectItem20;

    private String ipspectItem21;

    private String ipspectItem30;

    private String ipspectItem31;

    private String ipspectItem32;

    private String ipspectItem40;

    private String ipspectItem41;

    private String ipspectItem50;

    private String ipspectItem51;

    private String ipspectItem60;

    private String ipspectItem61;

    private String ipspectItem62;

    private String ipspectItem70;

    private String ipspectItem71;

    private String ipspectItem72;

    private String ipspectItem80;

    private String ipspectItem81;

    private String ipspectItem82;

    private String ipspectItem90;

    private String ipspectItem91;

    private String ipspectItem92;

    private String ipspectItem100;

    private String ipspectItem101;

    private String ipspectItem110;

    private String ipspectItem111;

    private String ipspectItem112;

    private String ipspectItem120;

    private String ipspectItem121;

    private String ipspectItem122;

    private String ipspectItem130;

    private String ipspectItem131;

    private String createUserUuid;
    
    //@JSONField (format="yyyy-MM-dd HH:mm")
    private String createTime;

    private String deptCode;

    private String orgCode;
    
    private String isApp;

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

    public String getBustNo() {
        return bustNo;
    }

    public void setBustNo(String bustNo) {
        this.bustNo = bustNo == null ? null : bustNo.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo == null ? null : shipNo.trim();
    }

    public String getShipNameCn() {
        return shipNameCn;
    }

    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn == null ? null : shipNameCn.trim();
    }

    public String getShipImo() {
        return shipImo;
    }

    public void setShipImo(String shipImo) {
        this.shipImo = shipImo == null ? null : shipImo.trim();
    }

    public String getBerthCode() {
        return berthCode;
    }

    public void setBerthCode(String berthCode) {
        this.berthCode = berthCode == null ? null : berthCode.trim();
    }

    public String getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(String inspectDate) {
        this.inspectDate = inspectDate;
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

    public String getIpspectItem10() {
        return ipspectItem10;
    }

    public void setIpspectItem10(String ipspectItem10) {
        this.ipspectItem10 = ipspectItem10 == null ? null : ipspectItem10.trim();
    }

    public String getIpspectItem11() {
        return ipspectItem11;
    }

    public void setIpspectItem11(String ipspectItem11) {
        this.ipspectItem11 = ipspectItem11 == null ? null : ipspectItem11.trim();
    }

    public String getIpspectItem20() {
        return ipspectItem20;
    }

    public void setIpspectItem20(String ipspectItem20) {
        this.ipspectItem20 = ipspectItem20 == null ? null : ipspectItem20.trim();
    }

    public String getIpspectItem21() {
        return ipspectItem21;
    }

    public void setIpspectItem21(String ipspectItem21) {
        this.ipspectItem21 = ipspectItem21 == null ? null : ipspectItem21.trim();
    }

    public String getIpspectItem30() {
        return ipspectItem30;
    }

    public void setIpspectItem30(String ipspectItem30) {
        this.ipspectItem30 = ipspectItem30 == null ? null : ipspectItem30.trim();
    }

    public String getIpspectItem31() {
        return ipspectItem31;
    }

    public void setIpspectItem31(String ipspectItem31) {
        this.ipspectItem31 = ipspectItem31 == null ? null : ipspectItem31.trim();
    }

    public String getIpspectItem32() {
        return ipspectItem32;
    }

    public void setIpspectItem32(String ipspectItem32) {
        this.ipspectItem32 = ipspectItem32 == null ? null : ipspectItem32.trim();
    }

    public String getIpspectItem40() {
        return ipspectItem40;
    }

    public void setIpspectItem40(String ipspectItem40) {
        this.ipspectItem40 = ipspectItem40 == null ? null : ipspectItem40.trim();
    }

    public String getIpspectItem41() {
        return ipspectItem41;
    }

    public void setIpspectItem41(String ipspectItem41) {
        this.ipspectItem41 = ipspectItem41 == null ? null : ipspectItem41.trim();
    }

    public String getIpspectItem50() {
        return ipspectItem50;
    }

    public void setIpspectItem50(String ipspectItem50) {
        this.ipspectItem50 = ipspectItem50 == null ? null : ipspectItem50.trim();
    }

    public String getIpspectItem51() {
        return ipspectItem51;
    }

    public void setIpspectItem51(String ipspectItem51) {
        this.ipspectItem51 = ipspectItem51 == null ? null : ipspectItem51.trim();
    }

    public String getIpspectItem60() {
        return ipspectItem60;
    }

    public void setIpspectItem60(String ipspectItem60) {
        this.ipspectItem60 = ipspectItem60 == null ? null : ipspectItem60.trim();
    }

    public String getIpspectItem61() {
        return ipspectItem61;
    }

    public void setIpspectItem61(String ipspectItem61) {
        this.ipspectItem61 = ipspectItem61 == null ? null : ipspectItem61.trim();
    }

    public String getIpspectItem62() {
        return ipspectItem62;
    }

    public void setIpspectItem62(String ipspectItem62) {
        this.ipspectItem62 = ipspectItem62 == null ? null : ipspectItem62.trim();
    }

    public String getIpspectItem70() {
        return ipspectItem70;
    }

    public void setIpspectItem70(String ipspectItem70) {
        this.ipspectItem70 = ipspectItem70 == null ? null : ipspectItem70.trim();
    }

    public String getIpspectItem71() {
        return ipspectItem71;
    }

    public void setIpspectItem71(String ipspectItem71) {
        this.ipspectItem71 = ipspectItem71 == null ? null : ipspectItem71.trim();
    }

    public String getIpspectItem72() {
        return ipspectItem72;
    }

    public void setIpspectItem72(String ipspectItem72) {
        this.ipspectItem72 = ipspectItem72 == null ? null : ipspectItem72.trim();
    }

    public String getIpspectItem80() {
        return ipspectItem80;
    }

    public void setIpspectItem80(String ipspectItem80) {
        this.ipspectItem80 = ipspectItem80 == null ? null : ipspectItem80.trim();
    }

    public String getIpspectItem81() {
        return ipspectItem81;
    }

    public void setIpspectItem81(String ipspectItem81) {
        this.ipspectItem81 = ipspectItem81 == null ? null : ipspectItem81.trim();
    }

    public String getIpspectItem82() {
        return ipspectItem82;
    }

    public void setIpspectItem82(String ipspectItem82) {
        this.ipspectItem82 = ipspectItem82 == null ? null : ipspectItem82.trim();
    }

    public String getIpspectItem90() {
        return ipspectItem90;
    }

    public void setIpspectItem90(String ipspectItem90) {
        this.ipspectItem90 = ipspectItem90 == null ? null : ipspectItem90.trim();
    }

    public String getIpspectItem91() {
        return ipspectItem91;
    }

    public void setIpspectItem91(String ipspectItem91) {
        this.ipspectItem91 = ipspectItem91 == null ? null : ipspectItem91.trim();
    }

    public String getIpspectItem92() {
        return ipspectItem92;
    }

    public void setIpspectItem92(String ipspectItem92) {
        this.ipspectItem92 = ipspectItem92 == null ? null : ipspectItem92.trim();
    }

    public String getIpspectItem100() {
        return ipspectItem100;
    }

    public void setIpspectItem100(String ipspectItem100) {
        this.ipspectItem100 = ipspectItem100 == null ? null : ipspectItem100.trim();
    }

    public String getIpspectItem101() {
        return ipspectItem101;
    }

    public void setIpspectItem101(String ipspectItem101) {
        this.ipspectItem101 = ipspectItem101 == null ? null : ipspectItem101.trim();
    }

    public String getIpspectItem110() {
        return ipspectItem110;
    }

    public void setIpspectItem110(String ipspectItem110) {
        this.ipspectItem110 = ipspectItem110 == null ? null : ipspectItem110.trim();
    }

    public String getIpspectItem111() {
        return ipspectItem111;
    }

    public void setIpspectItem111(String ipspectItem111) {
        this.ipspectItem111 = ipspectItem111 == null ? null : ipspectItem111.trim();
    }

    public String getIpspectItem112() {
        return ipspectItem112;
    }

    public void setIpspectItem112(String ipspectItem112) {
        this.ipspectItem112 = ipspectItem112 == null ? null : ipspectItem112.trim();
    }

    public String getIpspectItem120() {
        return ipspectItem120;
    }

    public void setIpspectItem120(String ipspectItem120) {
        this.ipspectItem120 = ipspectItem120 == null ? null : ipspectItem120.trim();
    }

    public String getIpspectItem121() {
        return ipspectItem121;
    }

    public void setIpspectItem121(String ipspectItem121) {
        this.ipspectItem121 = ipspectItem121 == null ? null : ipspectItem121.trim();
    }

    public String getIpspectItem122() {
        return ipspectItem122;
    }

    public void setIpspectItem122(String ipspectItem122) {
        this.ipspectItem122 = ipspectItem122 == null ? null : ipspectItem122.trim();
    }

    public String getIpspectItem130() {
        return ipspectItem130;
    }

    public void setIpspectItem130(String ipspectItem130) {
        this.ipspectItem130 = ipspectItem130 == null ? null : ipspectItem130.trim();
    }

    public String getIpspectItem131() {
        return ipspectItem131;
    }

    public void setIpspectItem131(String ipspectItem131) {
        this.ipspectItem131 = ipspectItem131 == null ? null : ipspectItem131.trim();
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

	public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp== null ? null : isApp.trim();
	}
}