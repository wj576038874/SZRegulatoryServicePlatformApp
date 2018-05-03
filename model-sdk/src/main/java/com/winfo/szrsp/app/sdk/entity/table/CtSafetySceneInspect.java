package com.winfo.szrsp.app.sdk.entity.table;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
/**
 * 船舶危防现场监督检查记录表 基础表
 * @author Administrator
 *
 */
public class CtSafetySceneInspect implements Serializable {
    private String inspectNo;

    private String busiNo;

    private String taskId;

    private String shipNo;

    private String shipNameCn;

    private String shipNameEn;

    private String regportCode;

    private String regportName;

    private String shipGrosston;

    private String berthCode;

    private String inspectDate;

    private String taskOrg;

    private String taskTypeCode;

    private String taskTypeName;

    private String taskPort;

    private String inspectorName;

    private String lastPort;

    private String nextPort;

    private String deptCode;

    private String orgCode;

    private String createUserUuid;
    
    @JSONField (format="yyyy-MM-dd HH:mm")
    private String creatTime;

    private String isApp;

    private static final long serialVersionUID = 1L;

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
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

    public String getShipNameEn() {
        return shipNameEn;
    }

    public void setShipNameEn(String shipNameEn) {
        this.shipNameEn = shipNameEn == null ? null : shipNameEn.trim();
    }

    public String getRegportCode() {
        return regportCode;
    }

    public void setRegportCode(String regportCode) {
        this.regportCode = regportCode == null ? null : regportCode.trim();
    }

    public String getRegportName() {
        return regportName;
    }

    public void setRegportName(String regportName) {
        this.regportName = regportName == null ? null : regportName.trim();
    }

    public String getShipGrosston() {
        return shipGrosston;
    }

    public void setShipGrosston(String shipGrosston) {
        this.shipGrosston = shipGrosston == null ? null : shipGrosston.trim();
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
        this.inspectDate = inspectDate == null ? null : inspectDate.trim();
    }

    public String getTaskOrg() {
        return taskOrg;
    }

    public void setTaskOrg(String taskOrg) {
        this.taskOrg = taskOrg == null ? null : taskOrg.trim();
    }

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode == null ? null : taskTypeCode.trim();
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName == null ? null : taskTypeName.trim();
    }

    public String getTaskPort() {
        return taskPort;
    }

    public void setTaskPort(String taskPort) {
        this.taskPort = taskPort == null ? null : taskPort.trim();
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName == null ? null : inspectorName.trim();
    }

    public String getLastPort() {
        return lastPort;
    }

    public void setLastPort(String lastPort) {
        this.lastPort = lastPort == null ? null : lastPort.trim();
    }

    public String getNextPort() {
        return nextPort;
    }

    public void setNextPort(String nextPort) {
        this.nextPort = nextPort == null ? null : nextPort.trim();
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

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getIsApp() {
        return isApp;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp == null ? null : isApp.trim();
    }
}