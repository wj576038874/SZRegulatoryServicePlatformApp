package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
//PSC  from  B 循环表的信息
public class CtPscFromB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String inspectNo;

    private BigDecimal seqNo;
	
    private String deficiencyCode;

    private String deficiencyNature;

    private String concenyionReference;

    private String isResponsible;

    private String takenAction;
    
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

    public String getDeficiencyCode() {
        return deficiencyCode;
    }

    public void setDeficiencyCode(String deficiencyCode) {
        this.deficiencyCode = deficiencyCode == null ? null : deficiencyCode.trim();
    }

    public String getDeficiencyNature() {
        return deficiencyNature;
    }

    public void setDeficiencyNature(String deficiencyNature) {
        this.deficiencyNature = deficiencyNature == null ? null : deficiencyNature.trim();
    }

    public String getConcenyionReference() {
        return concenyionReference;
    }

    public void setConcenyionReference(String concenyionReference) {
        this.concenyionReference = concenyionReference == null ? null : concenyionReference.trim();
    }

    public String getIsResponsible() {
        return isResponsible;
    }

    public void setIsResponsible(String isResponsible) {
        this.isResponsible = isResponsible == null ? null : isResponsible.trim();
    }

    public String getTakenAction() {
        return takenAction;
    }

    public void setTakenAction(String takenAction) {
        this.takenAction = takenAction == null ? null : takenAction.trim();
    }

	public String getIsApp() {
		return isApp;
	}

	public void setIsApp(String isApp) {
		this.isApp = isApp== null ? null : isApp.trim();
	}
}