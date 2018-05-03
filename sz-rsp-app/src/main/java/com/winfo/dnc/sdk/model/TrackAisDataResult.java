package com.winfo.dnc.sdk.model;

import java.util.List;

public class TrackAisDataResult {

	private int result;
	private List<TempAisData> ShipAisData;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<TempAisData> getShipAisData() {
		return ShipAisData;
	}
	public void setShipAisData(List<TempAisData> shipAisData) {
		ShipAisData = shipAisData;
	}
}
