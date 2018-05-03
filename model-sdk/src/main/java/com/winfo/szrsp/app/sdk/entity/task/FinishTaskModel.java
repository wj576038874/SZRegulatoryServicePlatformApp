package com.winfo.szrsp.app.sdk.entity.task;

/**
 * Created by wly on 2017/12/15.
 */

public class FinishTaskModel {

    private String tableName;

    private String inspectNo;

    private String shipName;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo;
    }
}
