package com.winfo.szrsp.app.sdk.entity.table;

/**
 * Created by ChengQi on 2017/12/7.
 * 检查船舶
 */

public class DetailShip {

    private String endTime;//结束时间
    private String isWork;//是否作业船
    private String result;//检查情况
    private String shipNameCn;//中文船名
    private String shipNo;//船舶编号
    private String shipPlace;//检查位置
    private String shipTypeCode;//船舶类型代码
    private String shipTypeNameCn;//船舶类型中文
    private String startTime;//开始时间
    private String workName;//作业名称

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsWork() {
        return isWork;
    }

    public void setIsWork(String isWork) {
        this.isWork = isWork;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getShipNameCn() {
        return shipNameCn;
    }

    public void setShipNameCn(String shipNameCn) {
        this.shipNameCn = shipNameCn;
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    public String getShipPlace() {
        return shipPlace;
    }

    public void setShipPlace(String shipPlace) {
        this.shipPlace = shipPlace;
    }

    public String getShipTypeCode() {
        return shipTypeCode;
    }

    public void setShipTypeCode(String shipTypeCode) {
        this.shipTypeCode = shipTypeCode;
    }

    public String getShipTypeNameCn() {
        return shipTypeNameCn;
    }

    public void setShipTypeNameCn(String shipTypeNameCn) {
        this.shipTypeNameCn = shipTypeNameCn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
