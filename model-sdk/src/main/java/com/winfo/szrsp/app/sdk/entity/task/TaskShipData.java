package com.winfo.szrsp.app.sdk.entity.task;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.task
 * @Filename: TaskShipData
 * @Author: lsj
 * @Date: 2018/3/9  13:57
 * @Description:
 * @Version:
 */
public class TaskShipData {
    private String mmsi;
    private String pycm;
    private String ywcm;
    private String zwcm;

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getPycm() {
        return pycm;
    }

    public void setPycm(String pycm) {
        this.pycm = pycm;
    }

    public String getYwcm() {
        return ywcm;
    }

    public void setYwcm(String ywcm) {
        this.ywcm = ywcm;
    }

    public String getZwcm() {
        return zwcm;
    }

    public void setZwcm(String zwcm) {
        this.zwcm = zwcm;
    }
}
