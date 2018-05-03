package com.winfo.szrsp.app.sdk.entity.table.xhtj;

import java.io.Serializable;
/**
 * 水域巡航统计表 之详细表
 *
 */
public class CtCruiseStatisticsDetail implements Serializable {
	
	private String inspectNo;

	private String seqNo;
	    
    private String hour;

    private String minute;

    private String outLine;

    private static final long serialVersionUID = 1L;
    
    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour == null ? null : hour.trim();
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute == null ? null : minute.trim();
    }

    public String getOutLine() {
        return outLine;
    }

    public void setOutLine(String outLine) {
        this.outLine = outLine == null ? null : outLine.trim();
    }
}