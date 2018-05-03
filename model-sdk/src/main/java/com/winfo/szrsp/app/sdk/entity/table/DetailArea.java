package com.winfo.szrsp.app.sdk.entity.table;

/**
 * Created by ChengQi on 2017/12/7.
 * 巡查区域
 */

public class DetailArea {


    private String cruiseArea;//巡查区域
    private String cruiseExap;//异常描述
    private String cruiseState;//状态
    private String cruiseType;//巡航类型
    private String endTime;//结束时间
    private String startTime;//开始时间


    public String getCruiseArea() {
        return cruiseArea;
    }

    public void setCruiseArea(String cruiseArea) {
        this.cruiseArea = cruiseArea;
    }

    public String getCruiseExap() {
        return cruiseExap;
    }

    public void setCruiseExap(String cruiseExap) {
        this.cruiseExap = cruiseExap;
    }

    public String getCruiseState() {
        return cruiseState;
    }

    public void setCruiseState(String cruiseState) {
        this.cruiseState = cruiseState;
    }

    public String getCruiseType() {
        return cruiseType;
    }

    public void setCruiseType(String cruiseType) {
        this.cruiseType = cruiseType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
