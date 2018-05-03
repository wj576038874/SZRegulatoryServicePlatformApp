package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.table
 * @Filename: ListcbjdjgData
 * @Author: lsj
 * @Date: 2017/12/7  19:00
 * @Description:
 * @Version:
 */
public class ListcbjdjgData {
    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private int navigatePages;
    private List<Integer> navigatepageNums;
    private int nextPage;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;

    private List<list> list;

    public List<ListcbjdjgData.list> getList() {
        return list;
    }

    public void setList(List<ListcbjdjgData.list> list) {
        this.list = list;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public class list{
        private String busiNo;
        private String captainCertNo;
        private String captainName;
        private String captainNameAutograph;
        private String captainPhone;
        private String correctMark;
        private String createTime;
        private String creatorCode;
        private int defectNum;
        private String detentionMark;
        private String fscBusiNo;
        private String initialInspectMark;
        private String initialInspectNo;
        private String inspectAction;//检查行动
        private String inspectDate;//检查日期
        private String inspectNo;
        private String inspectOrg;//检查机构
        private String inspectOrgAutograph;
        private String inspectType;
        private String inspectorCode;
        private String inspectorName;
        private String isApp;
        private String isPassed;
        private String isSpecialInspect;//是否专项检查
        private String operateMark;
        private String operateTime;
        private String operatorCode;
        private String orgCode;
        private String otherDesc;
        private String portCode;
        private String praticeInspectorCode;
        private String praticeInspectorName;
        private String priorityOrder;
        private String regportCode;
        private String regportName;//船籍港
        private String remark;
        private String riskAttribute;
        private String shipManager;//船舶管理人
        private String shipManagerNo;
        private String shipNameCn;//船名
        private String shipNo;
        private String shipTypeCode;
        private String shipTypeNameCn;//船舶类型
        private String shopId;//船舶识别号
        private String specialInspectType;//专项检查
        private String taskId;
        private String unclosedDefectNum;
        private String voyageId;

        public String getBusiNo() {
            return busiNo;
        }

        public void setBusiNo(String busiNo) {
            this.busiNo = busiNo;
        }

        public String getCaptainCertNo() {
            return captainCertNo;
        }

        public void setCaptainCertNo(String captainCertNo) {
            this.captainCertNo = captainCertNo;
        }

        public String getCaptainName() {
            return captainName;
        }

        public void setCaptainName(String captainName) {
            this.captainName = captainName;
        }

        public String getCaptainNameAutograph() {
            return captainNameAutograph;
        }

        public void setCaptainNameAutograph(String captainNameAutograph) {
            this.captainNameAutograph = captainNameAutograph;
        }

        public String getCaptainPhone() {
            return captainPhone;
        }

        public void setCaptainPhone(String captainPhone) {
            this.captainPhone = captainPhone;
        }

        public String getCorrectMark() {
            return correctMark;
        }

        public void setCorrectMark(String correctMark) {
            this.correctMark = correctMark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreatorCode() {
            return creatorCode;
        }

        public void setCreatorCode(String creatorCode) {
            this.creatorCode = creatorCode;
        }

        public int getDefectNum() {
            return defectNum;
        }

        public void setDefectNum(int defectNum) {
            this.defectNum = defectNum;
        }

        public String getDetentionMark() {
            return detentionMark;
        }

        public void setDetentionMark(String detentionMark) {
            this.detentionMark = detentionMark;
        }

        public String getFscBusiNo() {
            return fscBusiNo;
        }

        public void setFscBusiNo(String fscBusiNo) {
            this.fscBusiNo = fscBusiNo;
        }

        public String getInitialInspectMark() {
            return initialInspectMark;
        }

        public void setInitialInspectMark(String initialInspectMark) {
            this.initialInspectMark = initialInspectMark;
        }

        public String getInitialInspectNo() {
            return initialInspectNo;
        }

        public void setInitialInspectNo(String initialInspectNo) {
            this.initialInspectNo = initialInspectNo;
        }

        public String getInspectAction() {
            return inspectAction;
        }

        public void setInspectAction(String inspectAction) {
            this.inspectAction = inspectAction;
        }

        public String getInspectDate() {
            return inspectDate;
        }

        public void setInspectDate(String inspectDate) {
            this.inspectDate = inspectDate;
        }

        public String getInspectNo() {
            return inspectNo;
        }

        public void setInspectNo(String inspectNo) {
            this.inspectNo = inspectNo;
        }

        public String getInspectOrg() {
            return inspectOrg;
        }

        public void setInspectOrg(String inspectOrg) {
            this.inspectOrg = inspectOrg;
        }

        public String getInspectOrgAutograph() {
            return inspectOrgAutograph;
        }

        public void setInspectOrgAutograph(String inspectOrgAutograph) {
            this.inspectOrgAutograph = inspectOrgAutograph;
        }

        public String getInspectType() {
            return inspectType;
        }

        public void setInspectType(String inspectType) {
            this.inspectType = inspectType;
        }

        public String getInspectorCode() {
            return inspectorCode;
        }

        public void setInspectorCode(String inspectorCode) {
            this.inspectorCode = inspectorCode;
        }

        public String getInspectorName() {
            return inspectorName;
        }

        public void setInspectorName(String inspectorName) {
            this.inspectorName = inspectorName;
        }

        public String getIsApp() {
            return isApp;
        }

        public void setIsApp(String isApp) {
            this.isApp = isApp;
        }

        public String getIsPassed() {
            return isPassed;
        }

        public void setIsPassed(String isPassed) {
            this.isPassed = isPassed;
        }

        public String getIsSpecialInspect() {
            return isSpecialInspect;
        }

        public void setIsSpecialInspect(String isSpecialInspect) {
            this.isSpecialInspect = isSpecialInspect;
        }

        public String getOperateMark() {
            return operateMark;
        }

        public void setOperateMark(String operateMark) {
            this.operateMark = operateMark;
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }

        public String getOperatorCode() {
            return operatorCode;
        }

        public void setOperatorCode(String operatorCode) {
            this.operatorCode = operatorCode;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getOtherDesc() {
            return otherDesc;
        }

        public void setOtherDesc(String otherDesc) {
            this.otherDesc = otherDesc;
        }

        public String getPortCode() {
            return portCode;
        }

        public void setPortCode(String portCode) {
            this.portCode = portCode;
        }

        public String getPraticeInspectorCode() {
            return praticeInspectorCode;
        }

        public void setPraticeInspectorCode(String praticeInspectorCode) {
            this.praticeInspectorCode = praticeInspectorCode;
        }

        public String getPraticeInspectorName() {
            return praticeInspectorName;
        }

        public void setPraticeInspectorName(String praticeInspectorName) {
            this.praticeInspectorName = praticeInspectorName;
        }

        public String getPriorityOrder() {
            return priorityOrder;
        }

        public void setPriorityOrder(String priorityOrder) {
            this.priorityOrder = priorityOrder;
        }

        public String getRegportCode() {
            return regportCode;
        }

        public void setRegportCode(String regportCode) {
            this.regportCode = regportCode;
        }

        public String getRegportName() {
            return regportName;
        }

        public void setRegportName(String regportName) {
            this.regportName = regportName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRiskAttribute() {
            return riskAttribute;
        }

        public void setRiskAttribute(String riskAttribute) {
            this.riskAttribute = riskAttribute;
        }

        public String getShipManager() {
            return shipManager;
        }

        public void setShipManager(String shipManager) {
            this.shipManager = shipManager;
        }

        public String getShipManagerNo() {
            return shipManagerNo;
        }

        public void setShipManagerNo(String shipManagerNo) {
            this.shipManagerNo = shipManagerNo;
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

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getSpecialInspectType() {
            return specialInspectType;
        }

        public void setSpecialInspectType(String specialInspectType) {
            this.specialInspectType = specialInspectType;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getUnclosedDefectNum() {
            return unclosedDefectNum;
        }

        public void setUnclosedDefectNum(String unclosedDefectNum) {
            this.unclosedDefectNum = unclosedDefectNum;
        }

        public String getVoyageId() {
            return voyageId;
        }

        public void setVoyageId(String voyageId) {
            this.voyageId = voyageId;
        }
    }
}
