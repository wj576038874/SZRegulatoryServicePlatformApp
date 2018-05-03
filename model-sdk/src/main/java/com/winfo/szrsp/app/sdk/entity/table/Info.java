package com.winfo.szrsp.app.sdk.entity.table;

import java.math.BigDecimal;

/**
 * Created by ChengQi on 2017/12/7.
 * 水上巡航表格基础表
 */

public class Info {

    private String createUserAutograph;//巡航人员签名
    private String cruiseType;//巡航手段
    private String dayTime;//上下午
    private String weekNum;//周几
    private BigDecimal width;//签名图片宽度
    private BigDecimal height;//签名图片高度


    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getCreateUserAutograph() {
        return createUserAutograph;
    }

    public void setCreateUserAutograph(String createUserAutograph) {
        this.createUserAutograph = createUserAutograph;
    }

    public String getCruiseType() {
        return cruiseType;
    }

    public void setCruiseType(String cruiseType) {
        this.cruiseType = cruiseType;
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }
}
