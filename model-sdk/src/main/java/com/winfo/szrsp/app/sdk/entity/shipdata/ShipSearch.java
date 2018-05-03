package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;

public class ShipSearch implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mmsi;

    private String ywcm;

    private String zwcm;

    private String pycm;

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi == null ? null : mmsi.trim();
    }

    public String getYwcm() {
        return ywcm;
    }

    public void setYwcm(String ywcm) {
        this.ywcm = ywcm == null ? null : ywcm.trim();
    }

    public String getZwcm() {
        return zwcm;
    }

    public void setZwcm(String zwcm) {
        this.zwcm = zwcm == null ? null : zwcm.trim();
    }

    public String getPycm() {
        return pycm;
    }

    public void setPycm(String pycm) {
        this.pycm = pycm == null ? null : pycm.trim();
    }
}