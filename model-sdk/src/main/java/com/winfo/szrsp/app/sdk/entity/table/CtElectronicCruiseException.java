package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;

public class CtElectronicCruiseException implements Serializable {
    private String inspectNo;

    private String noticeOrg;//通报单位

    private String exceptionType;//异常情况类型

    private String exceptionTime;

    private String exceptionDescribe;

    private String handleResult;

    private String handleTime;

    private String remarks;

    private String inspectOrg;

    private String inspector;

    private String telephone;

    private String inspectTime;


    private String creatorUuid;

    private String deptCode;

    private String orgCode;

    private static final long serialVersionUID = 1L;

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public String getNoticeOrg() {
        return noticeOrg;
    }

    public void setNoticeOrg(String noticeOrg) {
        this.noticeOrg = noticeOrg == null ? null : noticeOrg.trim();
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType == null ? null : exceptionType.trim();
    }

    public String getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(String exceptionTime) {
        this.exceptionTime = exceptionTime == null ? null : exceptionTime.trim();
    }

    public String getExceptionDescribe() {
        return exceptionDescribe;
    }

    public void setExceptionDescribe(String exceptionDescribe) {
        this.exceptionDescribe = exceptionDescribe == null ? null : exceptionDescribe.trim();
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime == null ? null : handleTime.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getInspectOrg() {
        return inspectOrg;
    }

    public void setInspectOrg(String inspectOrg) {
        this.inspectOrg = inspectOrg == null ? null : inspectOrg.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime == null ? null : inspectTime.trim();
    }


    public String getCreatorUuid() {
        return creatorUuid;
    }

    public void setCreatorUuid(String creatorUuid) {
        this.creatorUuid = creatorUuid == null ? null : creatorUuid.trim();
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
}