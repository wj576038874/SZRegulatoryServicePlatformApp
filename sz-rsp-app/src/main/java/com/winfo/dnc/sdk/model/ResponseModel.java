package com.winfo.dnc.sdk.model;

import java.util.List;

public class ResponseModel {
	public int getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<AisPoint> getDocs() {
		return docs;
	}

	public void setDocs(List<AisPoint> docs) {
		this.docs = docs;
	}

	private int numFound;
	private int start;
	private List<AisPoint> docs;
}
