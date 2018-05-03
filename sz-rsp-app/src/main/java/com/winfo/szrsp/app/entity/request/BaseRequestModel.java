package com.winfo.szrsp.app.entity.request;

/**
 * 通航要素和ais数据请求参数模型的基类
 *
 * @author winfo-wj
 */
public class BaseRequestModel {
    protected String minX;
    protected String minY;
    protected String height;
    protected String startPoint;
    protected String stopPoint;
    protected String spanPerPixel;
    protected String level;

    public String getMinX() {
        return minX;
    }

    public void setMinX(String minX) {
        this.minX = minX;
    }

    public String getMinY() {
        return minY;
    }

    public void setMinY(String minY) {
        this.minY = minY;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(String stopPoint) {
        this.stopPoint = stopPoint;
    }

    public String getSpanPerPixel() {
        return spanPerPixel;
    }

    public void setSpanPerPixel(String spanPerPixel) {
        this.spanPerPixel = spanPerPixel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
