package com.winfo.szrsp.app.sdk.entity.plotting;

import java.io.Serializable;
/*
 * 标绘
 * id序列SEQ_PLOTTING_ID
 */
public class Plotting implements Serializable {
    private boolean isShowOnDNCView;//是否显示在海图上

    public boolean isShowOnDNCView() {
        return isShowOnDNCView;
    }

    public void setShowOnDNCView(boolean showOnDNCView) {
        isShowOnDNCView = showOnDNCView;
    }

    private String id;

    private String bhname;

    private String drawingType;

    private String latLon;

    private String remarks;

    private String color;

    private String style;

    private String thickness;

    private String display;

    private String userUuid;

    private String visible;

    private String orgId;

    private String deptCode;
    private String userName;//创建人

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBhname() {
        return bhname;
    }

    public void setBhname(String bhname) {
        this.bhname = bhname == null ? null : bhname.trim();
    }

    public String getDrawingType() {
        return drawingType;
    }

    public void setDrawingType(String drawingType) {
        this.drawingType = drawingType == null ? null : drawingType.trim();
    }

    public String getLatLon() {
        return latLon;
    }

    public void setLatLon(String latLon) {
        this.latLon = latLon == null ? null : latLon.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness == null ? null : thickness.trim();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible == null ? null : visible.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
}