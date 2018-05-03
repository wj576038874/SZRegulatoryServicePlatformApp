package com.winfo.szrsp.app.sdk.entity.aisdata;

import java.io.Serializable;

/**
 * Created by ChengQi on 2017/12/13.
 */

public class Ais implements Serializable {

    public String AISLX;
    public double CC;
    public AisCheckData CHECKDATA;
    public String CJSJ;
    public double CK;
    public String CLX;
    public String CM;
    public int CSX;
    public String DHSBLX;
    public String DHZT;
    public String ETA;
    public String HH;
    public double HS;
    public double HX;//航向
    public String ID;
    public String IMO;
    public double JD;
    public String MDD;
    public int SJBS;
    public double WD;
    public double ZDCS;
    public String SZCLX;
    public Double distance;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getSZCLX() {
        return SZCLX;
    }

    public void setSZCLX(String SZCLX) {
        this.SZCLX = SZCLX;
    }

    public String getAISLX() {
        return AISLX;
    }

    public void setAISLX(String AISLX) {
        this.AISLX = AISLX;
    }

    public double getCC() {
        return CC;
    }

    public void setCC(double CC) {
        this.CC = CC;
    }

    public AisCheckData getCHECKDATA() {
        return CHECKDATA;
    }

    public void setCHECKDATA(AisCheckData CHECKDATA) {
        this.CHECKDATA = CHECKDATA;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public double getCK() {
        return CK;
    }

    public void setCK(double CK) {
        this.CK = CK;
    }

    public String getCLX() {
        return CLX;
    }

    public void setCLX(String CLX) {
        this.CLX = CLX;
    }

    public String getCM() {
        return CM;
    }

    public void setCM(String CM) {
        this.CM = CM;
    }

    public int getCSX() {
        return CSX;
    }

    public void setCSX(int CSX) {
        this.CSX = CSX;
    }

    public String getDHSBLX() {
        return DHSBLX;
    }

    public void setDHSBLX(String DHSBLX) {
        this.DHSBLX = DHSBLX;
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

    public String getHH() {
        return HH;
    }

    public void setHH(String HH) {
        this.HH = HH;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIMO() {
        return IMO;
    }

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }

    public double getJD() {
        return JD;
    }

    public void setJD(double JD) {
        this.JD = JD;
    }

    public String getMDD() {
        return MDD;
    }

    public void setMDD(String MDD) {
        this.MDD = MDD;
    }

    public int getSJBS() {
        return SJBS;
    }

    public void setSJBS(int SJBS) {
        this.SJBS = SJBS;
    }

    public double getWD() {
        return WD;
    }

    public void setWD(double WD) {
        this.WD = WD;
    }

    public double getZDCS() {
        return ZDCS;
    }

    public void setZDCS(double ZDCS) {
        this.ZDCS = ZDCS;
    }
}
