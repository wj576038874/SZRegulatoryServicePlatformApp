package com.winfo.szrsp.app.sdk.entity.shipdata;

/**
 * Created by Guan on 2018-01-19.
 */

public class TrackData {

    private String  DHZT;//	用主机航行
    private String ETA;
    private double HS;//	60
    private double HX;//	93.0
    private double JD;//	118.306415
    private String MC;//	JIANG HAI TONG 278
    private String MCN;//	JIANGHAITONG278
    private String MMSI;//	413826973
    private String SJ;//	2017-11-09 13:38:41
    private double WD;//	31.302597
    public double X;
    public double Y;

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

    public String getDHZT() {
        return DHZT;
    }

    public void setDHZT(String DHZT) {
        this.DHZT = DHZT;
    }

    public String getETA() {
        return ETA;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }

    public double getHS() {
        return HS;
    }

    public void setHS(double HS) {
        this.HS = HS;
    }

    public double getHX() {
        return HX;
    }

    public void setHX(double HX) {
        this.HX = HX;
    }

    public double getJD() {
        return JD;
    }

    public void setJD(double JD) {
        this.JD = JD;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getMCN() {
        return MCN;
    }

    public void setMCN(String MCN) {
        this.MCN = MCN;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        this.MMSI = MMSI;
    }

    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }

    public double getWD() {
        return WD;
    }

    public void setWD(double WD) {
        this.WD = WD;
    }
}
