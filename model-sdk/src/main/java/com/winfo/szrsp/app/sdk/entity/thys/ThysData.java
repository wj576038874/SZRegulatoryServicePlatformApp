package com.winfo.szrsp.app.sdk.entity.thys;

import java.io.Serializable;

/**
 * Created by HoBo on 2018/4/2.
 */

public class ThysData implements Serializable {

    /**
     * drawingType :
     * remarks :
     * typeIcon :
     * typeId : NE0001
     * typeName : 岸基支持数据
     * typePid : NE
     * typeTable :
     * typeTableShowColumns :
     */
    private static final long serialVersionUID = 1L;
    private String drawingType;
    private String remarks;
    private String typeIcon;
    private String typeId;
    private String typeName;
    private String typePid;
    private String typeTable;
    private String typeTableShowColumns;

    public String getDrawingType() {
        return drawingType;
    }

    public void setDrawingType(String drawingType) {
        this.drawingType = drawingType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypePid() {
        return typePid;
    }

    public void setTypePid(String typePid) {
        this.typePid = typePid;
    }

    public String getTypeTable() {
        return typeTable;
    }

    public void setTypeTable(String typeTable) {
        this.typeTable = typeTable;
    }

    public String getTypeTableShowColumns() {
        return typeTableShowColumns;
    }

    public void setTypeTableShowColumns(String typeTableShowColumns) {
        this.typeTableShowColumns = typeTableShowColumns;
    }
}
