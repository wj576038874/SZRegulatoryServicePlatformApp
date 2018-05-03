package com.winfo.szrsp.app.sdk.entity.table.xhtj;

import java.io.Serializable;
import java.util.List;
/**
 * 水域巡航之完整实体类
 */
public class CtCruiseStatisticsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CtCruiseStatistics cruiseStatistics;
	
	private List<CtCruiseStatisticsDetail> cruiseStatisticsDetailList ;

	public CtCruiseStatistics getCruiseStatistics() {
		return cruiseStatistics;
	}

	public void setCruiseStatistics(CtCruiseStatistics cruiseStatistics) {
		this.cruiseStatistics = cruiseStatistics;
	}

	public List<CtCruiseStatisticsDetail> getCruiseStatisticsDetailList() {
		return cruiseStatisticsDetailList;
	}

	public void setCruiseStatisticsDetailList(List<CtCruiseStatisticsDetail> cruiseStatisticsDetailList) {
		this.cruiseStatisticsDetailList = cruiseStatisticsDetailList;
	}
}
