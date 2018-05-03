package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.table
 * @Filename: DetailcbxcjdbgData
 * @Author: lsj
 * @Date: 2017/12/8  11:06
 * @Description:
 * @Version:
 */
public class DetailcbxcjdbgData {

    private List<list> list;
    private tsite tsite;
    private List<TSiteSupervisionList> listNew;

    public List<TSiteSupervisionList> getListNew() {
        return listNew;
    }

    public void setListNew(List<TSiteSupervisionList> listNew) {
        this.listNew = listNew;
    }

    public List<DetailcbxcjdbgData.list> getList() {
        return list;
    }

    public void setList(List<DetailcbxcjdbgData.list> list) {
        this.list = list;
    }

    public DetailcbxcjdbgData.tsite getTsite() {
        return tsite;
    }

    public void setTsite(DetailcbxcjdbgData.tsite tsite) {
        this.tsite = tsite;
    }

    public class list {
        private String commentCode;
        private String commentDesc;
        private String contentCode;
        private String contentDesc;
        private String createTime;
        private String creatorCode;
        private String description;
        private String ifProblem;
        private String inspectNo;
        private String isApp;
        private String operateMark;
        private String operateTime;
        private String operatorCode;
        private String result;
        private int seqNo;

        public String getCommentCode() {
            return commentCode;
        }

        public void setCommentCode(String commentCode) {
            this.commentCode = commentCode;
        }

        public String getCommentDesc() {
            return commentDesc;
        }

        public void setCommentDesc(String commentDesc) {
            this.commentDesc = commentDesc;
        }

        public String getContentCode() {
            return contentCode;
        }

        public void setContentCode(String contentCode) {
            this.contentCode = contentCode;
        }

        public String getContentDesc() {
            return contentDesc;
        }

        public void setContentDesc(String contentDesc) {
            this.contentDesc = contentDesc;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIfProblem() {
            return ifProblem;
        }

        public void setIfProblem(String ifProblem) {
            this.ifProblem = ifProblem;
        }

        public String getInspectNo() {
            return inspectNo;
        }

        public void setInspectNo(String inspectNo) {
            this.inspectNo = inspectNo;
        }

        public String getIsApp() {
            return isApp;
        }

        public void setIsApp(String isApp) {
            this.isApp = isApp;
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

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(int seqNo) {
            this.seqNo = seqNo;
        }
    }

    public class tsite {
        private String berthCode;
        private String berthName;
        private String busiNo;
        private String captainName;
        private String captainNameAutograph;
        private String createTime;
        private String creatorCode;
        private String ifProblem;
        private String inspectDate;
        private String inspectItem1;
        private String inspectItem2;
        private String inspectItem3;
        private String inspectItem4;
        private String inspectItem5;
        private String inspectItem6;
        private String inspectItem7;
        private String inspectItem8;
        private String inspectItem9;
        private String inspectNo;
        private String inspectOrg;
        private String inspectOrgAutograph;
        private String inspectTimeLimit;
        private String inspectorCode;
        private String inspectorName;
        private String isApp;
        private String isInspect;
        private String isPassed;
        private String isSpecialInspect;
        private String operateMark;
        private String operateTime;
        private String operatorCode;
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
        private String num;
        private String specialInspectName;


        public String getSpecialInspectName() {
            return specialInspectName;
        }

        public void setSpecialInspectName(String specialInspectName) {
            this.specialInspectName = specialInspectName;
        }

        public String getBerthName() {
            return berthName;
        }

        public void setBerthName(String berthName) {
            this.berthName = berthName;
        }

        public String getInspectItem9() {
            return inspectItem9;
        }

        public void setInspectItem9(String inspectItem9) {
            this.inspectItem9 = inspectItem9;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }


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

        public void setInspectItem1(String inspectItem1) {
            this.inspectItem1 = inspectItem1;
        }

        public String getInspectItem1() {
            return inspectItem1;
        }

        public void setInspectItem2(String inspectItem2) {
            this.inspectItem2 = inspectItem2;
        }

        public String getInspectItem2() {
            return inspectItem2;
        }

        public void setInspectItem3(String inspectItem3) {
            this.inspectItem3 = inspectItem3;
        }

        public String getInspectItem3() {
            return inspectItem3;
        }

        public void setInspectItem4(String inspectItem4) {
            this.inspectItem4 = inspectItem4;
        }

        public String getInspectItem4() {
            return inspectItem4;
        }

        public void setInspectItem5(String inspectItem5) {
            this.inspectItem5 = inspectItem5;
        }

        public String getInspectItem5() {
            return inspectItem5;
        }

        public void setInspectItem6(String inspectItem6) {
            this.inspectItem6 = inspectItem6;
        }

        public String getInspectItem6() {
            return inspectItem6;
        }

        public void setInspectItem7(String inspectItem7) {
            this.inspectItem7 = inspectItem7;
        }

        public String getInspectItem7() {
            return inspectItem7;
        }

        public void setInspectItem8(String inspectItem8) {
            this.inspectItem8 = inspectItem8;
        }

        public String getInspectItem8() {
            return inspectItem8;
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

        public void setIsApp(String isApp) {
            this.isApp = isApp;
        }

        public String getIsApp() {
            return isApp;
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

    }

}