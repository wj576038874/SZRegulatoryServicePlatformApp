package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.table
 * @Filename: ListcbxcData
 * @Author: lsj
 * @Date: 2017/12/8  9:18
 * @Description:
 * @Version:
 */
public class ListcbxcData {
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

    public List<ListcbxcData.list> getList() {
        return list;
    }

    public void setList(List<ListcbxcData.list> list) {
        this.list = list;
    }

    public class list{
        private String berthCode;
        private String busiNo;
        private String captainName;
        private String captainNameAutograph;
        private String createTime;
        private String creatorCode;
        private String ifProblem;
        private String inspectDate;
        private String inspectNo;
        private String inspectOrg;
        private String inspectOrgAutograph;
        private String inspectTimeLimit;
        private String inspectorCode;
        private String inspectorName;
        private String isInspect;
        private String isPassed;
        private String isSpecialInspect;
        private String operateMark;
        private String operateTime;
        private String operatorCode;
        private String orgCode;
        private String praticeInspectorCode;
        private String praticeInspectorName;
        private String priorityOrder;
        private String reason;
        private String regportName;
        private String regrortCode;
        private String remark;
        private String riskAttribute;
        private String shipId;
        private String shipManager;
        private String shipManagerAddr;
        private String shipManagerFax;
        private String shipNameCn;
        private String shipNo;
        private String shipOperator;
        private String shipOperatorAddr;
        private String shipOperatorFax;
        private String shipTypeCode;
        private String shipTypeNameCn;
        private String specialInspectType;
        private String spotBusiNo;
        private String taskId;
        private String voyageId;
        private String isApp;
        public void setBerthCode(String berthCode) {
            this.berthCode = berthCode;
        }
        public String getBerthCode() {
            return berthCode;
        }

        public void setBusiNo(String busiNo) {
            this.busiNo = busiNo;
        }
        public String getBusiNo() {
            return busiNo;
        }

        public void setCaptainName(String captainName) {
            this.captainName = captainName;
        }
        public String getCaptainName() {
            return captainName;
        }

        public void setCaptainNameAutograph(String captainNameAutograph) {
            this.captainNameAutograph = captainNameAutograph;
        }
        public String getCaptainNameAutograph() {
            return captainNameAutograph;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
        public String getCreateTime() {
            return createTime;
        }

        public void setCreatorCode(String creatorCode) {
            this.creatorCode = creatorCode;
        }
        public String getCreatorCode() {
            return creatorCode;
        }

        public void setIfProblem(String ifProblem) {
            this.ifProblem = ifProblem;
        }
        public String getIfProblem() {
            return ifProblem;
        }

        public void setInspectDate(String inspectDate) {
            this.inspectDate = inspectDate;
        }
        public String getInspectDate() {
            return inspectDate;
        }

        public void setInspectNo(String inspectNo) {
            this.inspectNo = inspectNo;
        }
        public String getInspectNo() {
            return inspectNo;
        }

        public void setInspectOrg(String inspectOrg) {
            this.inspectOrg = inspectOrg;
        }
        public String getInspectOrg() {
            return inspectOrg;
        }

        public void setInspectOrgAutograph(String inspectOrgAutograph) {
            this.inspectOrgAutograph = inspectOrgAutograph;
        }
        public String getInspectOrgAutograph() {
            return inspectOrgAutograph;
        }

        public void setInspectTimeLimit(String inspectTimeLimit) {
            this.inspectTimeLimit = inspectTimeLimit;
        }
        public String getInspectTimeLimit() {
            return inspectTimeLimit;
        }

        public void setInspectorCode(String inspectorCode) {
            this.inspectorCode = inspectorCode;
        }
        public String getInspectorCode() {
            return inspectorCode;
        }

        public void setInspectorName(String inspectorName) {
            this.inspectorName = inspectorName;
        }
        public String getInspectorName() {
            return inspectorName;
        }

        public void setIsInspect(String isInspect) {
            this.isInspect = isInspect;
        }
        public String getIsInspect() {
            return isInspect;
        }

        public void setIsPassed(String isPassed) {
            this.isPassed = isPassed;
        }
        public String getIsPassed() {
            return isPassed;
        }

        public void setIsSpecialInspect(String isSpecialInspect) {
            this.isSpecialInspect = isSpecialInspect;
        }
        public String getIsSpecialInspect() {
            return isSpecialInspect;
        }

        public void setOperateMark(String operateMark) {
            this.operateMark = operateMark;
        }
        public String getOperateMark() {
            return operateMark;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }
        public String getOperateTime() {
            return operateTime;
        }

        public void setOperatorCode(String operatorCode) {
            this.operatorCode = operatorCode;
        }
        public String getOperatorCode() {
            return operatorCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }
        public String getOrgCode() {
            return orgCode;
        }

        public void setPraticeInspectorCode(String praticeInspectorCode) {
            this.praticeInspectorCode = praticeInspectorCode;
        }
        public String getPraticeInspectorCode() {
            return praticeInspectorCode;
        }

        public void setPraticeInspectorName(String praticeInspectorName) {
            this.praticeInspectorName = praticeInspectorName;
        }
        public String getPraticeInspectorName() {
            return praticeInspectorName;
        }

        public void setPriorityOrder(String priorityOrder) {
            this.priorityOrder = priorityOrder;
        }
        public String getPriorityOrder() {
            return priorityOrder;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }

        public void setRegportName(String regportName) {
            this.regportName = regportName;
        }
        public String getRegportName() {
            return regportName;
        }

        public void setRegrortCode(String regrortCode) {
            this.regrortCode = regrortCode;
        }
        public String getRegrortCode() {
            return regrortCode;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
        public String getRemark() {
            return remark;
        }

        public void setRiskAttribute(String riskAttribute) {
            this.riskAttribute = riskAttribute;
        }
        public String getRiskAttribute() {
            return riskAttribute;
        }

        public void setShipId(String shipId) {
            this.shipId = shipId;
        }
        public String getShipId() {
            return shipId;
        }

        public void setShipManager(String shipManager) {
            this.shipManager = shipManager;
        }
        public String getShipManager() {
            return shipManager;
        }

        public void setShipManagerAddr(String shipManagerAddr) {
            this.shipManagerAddr = shipManagerAddr;
        }
        public String getShipManagerAddr() {
            return shipManagerAddr;
        }

        public void setShipManagerFax(String shipManagerFax) {
            this.shipManagerFax = shipManagerFax;
        }
        public String getShipManagerFax() {
            return shipManagerFax;
        }

        public void setShipNameCn(String shipNameCn) {
            this.shipNameCn = shipNameCn;
        }
        public String getShipNameCn() {
            return shipNameCn;
        }

        public void setShipNo(String shipNo) {
            this.shipNo = shipNo;
        }
        public String getShipNo() {
            return shipNo;
        }

        public void setShipOperator(String shipOperator) {
            this.shipOperator = shipOperator;
        }
        public String getShipOperator() {
            return shipOperator;
        }

        public void setShipOperatorAddr(String shipOperatorAddr) {
            this.shipOperatorAddr = shipOperatorAddr;
        }
        public String getShipOperatorAddr() {
            return shipOperatorAddr;
        }

        public void setShipOperatorFax(String shipOperatorFax) {
            this.shipOperatorFax = shipOperatorFax;
        }
        public String getShipOperatorFax() {
            return shipOperatorFax;
        }

        public void setShipTypeCode(String shipTypeCode) {
            this.shipTypeCode = shipTypeCode;
        }
        public String getShipTypeCode() {
            return shipTypeCode;
        }

        public void setShipTypeNameCn(String shipTypeNameCn) {
            this.shipTypeNameCn = shipTypeNameCn;
        }
        public String getShipTypeNameCn() {
            return shipTypeNameCn;
        }

        public void setSpecialInspectType(String specialInspectType) {
            this.specialInspectType = specialInspectType;
        }
        public String getSpecialInspectType() {
            return specialInspectType;
        }

        public void setSpotBusiNo(String spotBusiNo) {
            this.spotBusiNo = spotBusiNo;
        }
        public String getSpotBusiNo() {
            return spotBusiNo;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }
        public String getTaskId() {
            return taskId;
        }

        public void setVoyageId(String voyageId) {
            this.voyageId = voyageId;
        }
        public String getVoyageId() {
            return voyageId;
        }

        public void setIsApp(String isApp) {
            this.isApp = isApp;
        }
        public String getIsApp() {
            return isApp;
        }

    }
}
