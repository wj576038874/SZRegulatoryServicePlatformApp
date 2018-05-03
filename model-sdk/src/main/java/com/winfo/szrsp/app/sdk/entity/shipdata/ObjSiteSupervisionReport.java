package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.util.List;


public class ObjSiteSupervisionReport implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 船旗国安检信息
	 */
	private List<SiteSupervisionReport> siteSupervisionReportList;
	
	/**
	 * 缺陷详情集合
	 */
	private SiteSupervisionReport siteSupervisionReport;
	private List<SiteSupervisionDetail> siteSupervisionDetail;

	


	public List<SiteSupervisionReport> getSiteSupervisionReportList() {
		return siteSupervisionReportList;
	}


	public void setSiteSupervisionReportList(List<SiteSupervisionReport> siteSupervisionReportList) {
		this.siteSupervisionReportList = siteSupervisionReportList;
	}


	public SiteSupervisionReport getSiteSupervisionReport() {
		return siteSupervisionReport;
	}


	public void setSiteSupervisionReport(SiteSupervisionReport siteSupervisionReport) {
		this.siteSupervisionReport = siteSupervisionReport;
	}


	public List<SiteSupervisionDetail> getSiteSupervisionDetail() {
		return siteSupervisionDetail;
	}


	public void setSiteSupervisionDetail(List<SiteSupervisionDetail> siteSupervisionDetail) {
		this.siteSupervisionDetail = siteSupervisionDetail;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
