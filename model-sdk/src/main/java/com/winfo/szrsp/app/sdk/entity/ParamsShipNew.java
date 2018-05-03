package com.winfo.szrsp.app.sdk.entity;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity
 * @Filename: ParamsShipNew
 * @Author: lsj
 * @Date: 2018/2/2  15:02
 * @Description:
 * @Version:
 */
public class ParamsShipNew {
    private String type;
    private String check;
    private String dimexact;
    private String basedb;
    private String table;
    private Jsonselect jsonselect;
    private Jsonaddorup jsonaddorup;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDimexact() {
        return dimexact;
    }

    public void setDimexact(String dimexact) {
        this.dimexact = dimexact;
    }

    public String getBasedb() {
        return basedb;
    }

    public void setBasedb(String basedb) {
        this.basedb = basedb;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Jsonselect getJsonselect() {
        return jsonselect;
    }

    public void setJsonselect(Jsonselect jsonselect) {
        this.jsonselect = jsonselect;
    }

    public Jsonaddorup getJsonaddorup() {
        return jsonaddorup;
    }

    public void setJsonaddorup(Jsonaddorup jsonaddorup) {
        this.jsonaddorup = jsonaddorup;
    }

    public static class Jsonselect{
        private String MMSI;
        private String SHIP_NAME_EN;
        private String SHIP_NAME_CN;
        private String SHIP_NO;
        private String SHIP_ID;

        public String getMMSI() {
            return MMSI;
        }

        public void setMMSI(String MMSI) {
            this.MMSI = MMSI;
        }

        public String getSHIP_NAME_EN() {
            return SHIP_NAME_EN;
        }

        public void setSHIP_NAME_EN(String SHIP_NAME_EN) {
            this.SHIP_NAME_EN = SHIP_NAME_EN;
        }

        public String getSHIP_NAME_CN() {
            return SHIP_NAME_CN;
        }

        public void setSHIP_NAME_CN(String SHIP_NAME_CN) {
            this.SHIP_NAME_CN = SHIP_NAME_CN;
        }

        public String getSHIP_NO() {
            return SHIP_NO;
        }

        public void setSHIP_NO(String SHIP_NO) {
            this.SHIP_NO = SHIP_NO;
        }

        public String getSHIP_ID() {
            return SHIP_ID;
        }

        public void setSHIP_ID(String SHIP_ID) {
            this.SHIP_ID = SHIP_ID;
        }
    }

    public static class Jsonaddorup{

    }
}
