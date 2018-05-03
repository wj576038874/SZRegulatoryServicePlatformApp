package com.winfo.dnc.sdk.model;

public class AisPoint {
	public long getMMSI() {
		return MMSI;
	}
	public void setMMSI(long mMSI) {
		MMSI = mMSI;
	}
	public String getMC() {
		return MC;
	}
	public void setMC(String mC) {
		MC = mC;
	}
	public double getWD() {
		return WD;
	}
	public void setWD(double wD) {
		WD = wD;
	}
	public double getJD() {
		return JD;
	}
	public void setJD(double jD) {
		JD = jD;
	}
	public String getHX() {
		return HX;
	}
	public void setHX(String hX) {
		HX = hX;
	}
	public double getHS() {
		return HS;
	}
	public void setHS(double hS) {
		HS = hS;
	}
	public long getSJ() {
		return SJ;
	}
	public void setSJ(long sJ) {
		SJ = sJ;
	}
	public String getETA() {
		return ETA;
	}
	public void setETA(String eTA) {
		ETA = eTA;
	}
	public String getDHZT() {
		return DHZT;
	}
	public void setDHZT(String dHZT) {
		DHZT = dHZT;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public long MMSI;
	public String MC;
	public double WD;
	public double JD;
	public String HX;
	public double HS;
	public long SJ;
	public String ETA;
	public String DHZT;
	public String ID;
	public double X;
	public double Y;
	public String dateTime;
	/*
	"MMSI":"413906465",
    "MC":" ",
    "WD":"22.062305",
    "JD":"114.11128",
    "HX":"360",
    "HS":"0.1",
    "SJ":1440761289,
    "ETA":" ",
    "DHZT":"用主机航行",
    "ID":"ec71897c-b62c-4e1d-b8f2-c1fce3078d53",
    "_version_":1510717515860279298},*/
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
