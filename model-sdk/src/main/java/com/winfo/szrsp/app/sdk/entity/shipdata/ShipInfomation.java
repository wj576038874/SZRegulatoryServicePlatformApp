package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation.java
 * Date: 2017/12/21 14:51
 * Description: 船舶基本信息
 */

public class ShipInfomation implements Serializable {

    private CheckDataInfo checkDataInfo;
    private ShipBaseInfo shipInfo;

    public CheckDataInfo getCheckDataInfo() {
        return checkDataInfo;
    }

    public void setCheckDataInfo(CheckDataInfo checkDataInfo) {
        this.checkDataInfo = checkDataInfo;
    }

    public ShipBaseInfo getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(ShipBaseInfo shipInfo) {
        this.shipInfo = shipInfo;
    }
}
