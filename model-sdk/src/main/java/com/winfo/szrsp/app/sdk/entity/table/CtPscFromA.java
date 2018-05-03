package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
//PSC FROM A 循环表中信息
public class CtPscFromA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String inspectNo;

    private BigDecimal seqNo;

    private String titleCode;

    private String titleDesc;

    private String issuingAuthority;

    private String issueTime;

    private String issuExpiry;

    private String surveyDate;

    private String surveyingAuthority;

    private String surveyingPlace;
    
    private String isApp;

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public BigDecimal getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(BigDecimal seqNo) {
        this.seqNo = seqNo;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode == null ? null : titleCode.trim();
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc == null ? null : titleDesc.trim();
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority == null ? null : issuingAuthority.trim();
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssuExpiry() {
        return issuExpiry;
    }

    public void setIssuExpiry(String issuExpiry) {
        this.issuExpiry = issuExpiry;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getSurveyingAuthority() {
        return surveyingAuthority;
    }

    public void setSurveyingAuthority(String surveyingAuthority) {
        this.surveyingAuthority = surveyingAuthority == null ? null : surveyingAuthority.trim();
    }

    public String getSurveyingPlace() {
        return surveyingPlace;
    }

    public void setSurveyingPlace(String surveyingPlace) {
        this.surveyingPlace = surveyingPlace == null ? null : surveyingPlace.trim();
    }

	public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp== null ? null : isApp.trim();
	}
}