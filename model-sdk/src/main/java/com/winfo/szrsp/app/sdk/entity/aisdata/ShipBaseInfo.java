package com.winfo.szrsp.app.sdk.entity.aisdata;

import java.io.Serializable;

/**
 * Created by ChengQi on 2017/12/13.
 *
 */

public class ShipBaseInfo implements Serializable{

    private String Callsign;
    private String FormerNameCN;
    private String IMO;
    private String IRN;
    private String KeyID;
    private String MMSI;
    private String NameCN;
    private String NameEN;
    private String RSID;
    private String RSMN;
    private String SRN;
    private String ShipManager;
    private String ShipOwner;
    private String ShipOwnerKey;

    public String getCallsign() {
        return Callsign;
    }

    public void setCallsign(String callsign) {
        Callsign = callsign;
    }

    public String getFormerNameCN() {
        return FormerNameCN;
    }

    public void setFormerNameCN(String formerNameCN) {
        FormerNameCN = formerNameCN;
    }

    public String getIMO() {
        return IMO;
    }

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }

    public String getIRN() {
        return IRN;
    }

    public void setIRN(String IRN) {
        this.IRN = IRN;
    }

    public String getKeyID() {
        return KeyID;
    }

    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        this.MMSI = MMSI;
    }

    public String getNameCN() {
        return NameCN;
    }

    public void setNameCN(String nameCN) {
        NameCN = nameCN;
    }

    public String getNameEN() {
        return NameEN;
    }

    public void setNameEN(String nameEN) {
        NameEN = nameEN;
    }

    public String getRSID() {
        return RSID;
    }

    public void setRSID(String RSID) {
        this.RSID = RSID;
    }

    public String getRSMN() {
        return RSMN;
    }

    public void setRSMN(String RSMN) {
        this.RSMN = RSMN;
    }

    public String getSRN() {
        return SRN;
    }

    public void setSRN(String SRN) {
        this.SRN = SRN;
    }

    public String getShipManager() {
        return ShipManager;
    }

    public void setShipManager(String shipManager) {
        ShipManager = shipManager;
    }

    public String getShipOwner() {
        return ShipOwner;
    }

    public void setShipOwner(String shipOwner) {
        ShipOwner = shipOwner;
    }

    public String getShipOwnerKey() {
        return ShipOwnerKey;
    }

    public void setShipOwnerKey(String shipOwnerKey) {
        ShipOwnerKey = shipOwnerKey;
    }
}
