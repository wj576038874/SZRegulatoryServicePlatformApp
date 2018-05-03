package com.winfo.szrsp.app.sdk.entity.thys;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.thys
 * @Filename: NavigableElementsData
 * @Author: lsj
 * @Date: 2017/12/20  18:42
 * @Description:
 * @Version:
 */
public class NavigableElementsData {
    private String centerLatLon;
    private String company;
    private String companyTel;
    private String deptCode;
    private String drawingType;
    private int id;
    private String latLon;
    private String linkman;
    private String linkmanTel;
    private String nameCn;
    private String nameEn;
    private int navigationTableId;
    private String orgId;
    private String remarks;
    private int status;
    private String typeIcon;
    private String typeId;
    private String typeName;
    private String typePid;
    private String typeTable;
    private String typeTableShowColumns;
    private String userUuid;
    private navigationElementsType navigationElementsType;
    private Double distance;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public NavigableElementsData.navigationElementsType getNavigationElementsType() {
        return navigationElementsType;
    }

    public void setNavigationElementsType(NavigableElementsData.navigationElementsType navigationElementsType) {
        this.navigationElementsType = navigationElementsType;
    }

    public class navigationElementsType {
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
            if (typeName == null) {
                return "";
            } else {
                return typeName;
            }
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

    public String getTypeIcon() {
        if (typeIcon == null) {
            return "";
        } else {
            return typeIcon;
        }
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public String getTypeName() {
        if (typeName == null) {
            return "";
        } else {
            return typeName;
        }
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getCenterLatLon() {
        return centerLatLon;
    }

    public void setCenterLatLon(String centerLatLon) {
        this.centerLatLon = centerLatLon;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDrawingType() {
        return drawingType;
    }

    public void setDrawingType(String drawingType) {
        this.drawingType = drawingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatLon() {
        return latLon;
    }

    public void setLatLon(String latLon) {
        this.latLon = latLon;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getNavigationTableId() {
        return navigationTableId;
    }

    public void setNavigationTableId(int navigationTableId) {
        this.navigationTableId = navigationTableId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTypeId() {
        if (typeId == null) {
            return "";
        } else {
            return typeId;
        }
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypePid() {
        return typePid;
    }

    public void setTypePid(String typePid) {
        this.typePid = typePid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
