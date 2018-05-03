package com.winfo.szrsp.app.sdk.entity.aisdata;

import java.io.Serializable;

/**
 * Created by ChengQi on 2017/12/13.
 */

public class AisCheckData implements Serializable{

    private RegisterShipData RegisterShipData;
    private ShipBaseInfo ShipBaseInfo;

    public com.winfo.szrsp.app.sdk.entity.aisdata.RegisterShipData getRegisterShipData() {
        return RegisterShipData;
    }

    public void setRegisterShipData(com.winfo.szrsp.app.sdk.entity.aisdata.RegisterShipData registerShipData) {
        RegisterShipData = registerShipData;
    }

    public com.winfo.szrsp.app.sdk.entity.aisdata.ShipBaseInfo getShipBaseInfo() {
        return ShipBaseInfo;
    }

    public void setShipBaseInfo(com.winfo.szrsp.app.sdk.entity.aisdata.ShipBaseInfo shipBaseInfo) {
        ShipBaseInfo = shipBaseInfo;
    }
}
