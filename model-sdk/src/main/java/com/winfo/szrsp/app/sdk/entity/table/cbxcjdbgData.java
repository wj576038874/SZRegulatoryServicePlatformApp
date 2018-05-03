package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.table
 * @Filename: cbxcjdbgData
 * @Author: lsj
 * @Date: 2017/12/7  14:13
 * @Description:
 * @Version:
 */
public class cbxcjdbgData {

    private info info;
    private List<detail> detail;
    private List<TSiteSupervisionList> list;

    public cbxcjdbgData.info getInfo() {
        return info;
    }

    public void setInfo(cbxcjdbgData.info info) {
        this.info = info;
    }

    public List<cbxcjdbgData.detail> getDetail() {
        return detail;
    }

    public void setDetail(List<cbxcjdbgData.detail> detail) {
        this.detail = detail;
    }

    public List<TSiteSupervisionList> getList() {
        return list;
    }

    public void setList(List<TSiteSupervisionList> list) {
        this.list = list;
    }

    public static class info {
        private String inspectNo;
        private String initialInspectNo;//初查编号

        private String busiNo;
        private int unclosedDefectNum;

        public int getUnclosedDefectNum() {
            return unclosedDefectNum;
        }

        public void setUnclosedDefectNum(int unclosedDefectNum) {
            this.unclosedDefectNum = unclosedDefectNum;
        }

        private String taskId;

        private String voyageId;

        private String shipNo;
        private String initialInspectMark;

        private String shipNameCn;

        private String shipOperator;

        private String shipOperatorAddr;

        private String shipOperatorFax;

        private String shipManager;

        private String shipManagerAddr;

        private String shipManagerFax;

        private String captainName;

        private String inspectOrg;

        private String berthCode;

        private String berthName;

        private String inspectDate;

        private String inspectorCode;

        private String inspectorName;

        private String inspectTimeLimit;

        private String reason;

        private String ifProblem;

        private String remark;

        private String creatorCode;

        private String createTime;

        private String operatorCode;

        private String operateTime;

        private String operateMark;

        private String spotBusiNo;

        private String priorityOrder;

        private String riskAttribute;

        private String shipId;

        private String praticeInspectorCode;

        private String praticeInspectorName;

        private String regrortCode;

        private String regportName;

        private String shipTypeCode;

        private String shipTypeNameCn;

        private String inspectOrgAutograph;

        private String captainNameAutograph;

        private String isSpecialInspect;

        private String specialInspectType;
        private String specialInspectName;

        public String getInitialInspectNo() {
            return initialInspectNo;
        }

        public void setInitialInspectNo(String initialInspectNo) {
            this.initialInspectNo = initialInspectNo;
        }

        public String getInitialInspectMark() {
            return initialInspectMark;
        }

        public void setInitialInspectMark(String initialInspectMark) {
            this.initialInspectMark = initialInspectMark;
        }

        public String getSpecialInspectName() {
            return specialInspectName;
        }

        public void setSpecialInspectName(String specialInspectName) {
            this.specialInspectName = specialInspectName;
        }

        private String isPassed;

        private String isInspect;

        private String isApp;

        private String inspectItem1;

        private String inspectItem2;

        private String inspectItem3;

        private String inspectItem4;

        private String inspectItem5;

        private String inspectItem6;

        private String inspectItem7;

        private String inspectItem8;

        private String inspectItem9;

        private String num;

        private int width;

        private int height;

        public String getBerthName() {
            return berthName;
        }

        public void setBerthName(String berthName) {
            this.berthName = berthName;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getInspectItem9() {
            return inspectItem9;
        }

        public void setInspectItem9(String inspectItem9) {
            this.inspectItem9 = inspectItem9;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIsSpecialInspect() {
            return isSpecialInspect;
        }

        public void setIsSpecialInspect(String isSpecialInspect) {
            this.isSpecialInspect = isSpecialInspect;
        }

        public String getSpecialInspectType() {
            return specialInspectType;
        }

        public void setSpecialInspectType(String specialInspectType) {
            this.specialInspectType = specialInspectType;
        }

        public String getIsPassed() {
            return isPassed;
        }

        public void setIsPassed(String isPassed) {
            this.isPassed = isPassed;
        }

        public String getIsInspect() {
            return isInspect;
        }

        public void setIsInspect(String isInspect) {
            this.isInspect = isInspect;
        }

        public String getInspectNo() {
            return inspectNo;
        }

        public void setInspectNo(String inspectNo) {
            this.inspectNo = inspectNo == null ? null : inspectNo.trim();
        }

        public String getBusiNo() {
            return busiNo;
        }

        public void setBusiNo(String busiNo) {
            this.busiNo = busiNo == null ? null : busiNo.trim();
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId == null ? null : taskId.trim();
        }

        public String getVoyageId() {
            return voyageId;
        }

        public void setVoyageId(String voyageId) {
            this.voyageId = voyageId == null ? null : voyageId.trim();
        }

        public String getShipNo() {
            return shipNo;
        }

        public void setShipNo(String shipNo) {
            this.shipNo = shipNo == null ? null : shipNo.trim();
        }

        public String getShipNameCn() {
            return shipNameCn;
        }

        public void setShipNameCn(String shipNameCn) {
            this.shipNameCn = shipNameCn == null ? null : shipNameCn.trim();
        }

        public String getShipOperator() {
            return shipOperator;
        }

        public void setShipOperator(String shipOperator) {
            this.shipOperator = shipOperator == null ? null : shipOperator.trim();
        }

        public String getShipOperatorAddr() {
            return shipOperatorAddr;
        }

        public void setShipOperatorAddr(String shipOperatorAddr) {
            this.shipOperatorAddr = shipOperatorAddr == null ? null : shipOperatorAddr.trim();
        }

        public String getShipOperatorFax() {
            return shipOperatorFax;
        }

        public void setShipOperatorFax(String shipOperatorFax) {
            this.shipOperatorFax = shipOperatorFax == null ? null : shipOperatorFax.trim();
        }

        public String getShipManager() {
            return shipManager;
        }

        public void setShipManager(String shipManager) {
            this.shipManager = shipManager == null ? null : shipManager.trim();
        }

        public String getShipManagerAddr() {
            return shipManagerAddr;
        }

        public void setShipManagerAddr(String shipManagerAddr) {
            this.shipManagerAddr = shipManagerAddr == null ? null : shipManagerAddr.trim();
        }

        public String getShipManagerFax() {
            return shipManagerFax;
        }

        public void setShipManagerFax(String shipManagerFax) {
            this.shipManagerFax = shipManagerFax == null ? null : shipManagerFax.trim();
        }

        public String getCaptainName() {
            return captainName;
        }

        public void setCaptainName(String captainName) {
            this.captainName = captainName == null ? null : captainName.trim();
        }

        public String getInspectOrg() {
            return inspectOrg;
        }

        public void setInspectOrg(String inspectOrg) {
            this.inspectOrg = inspectOrg == null ? null : inspectOrg.trim();
        }

        public String getBerthCode() {
            return berthCode;
        }

        public void setBerthCode(String berthCode) {
            this.berthCode = berthCode == null ? null : berthCode.trim();
        }

        public String getInspectDate() {
            return inspectDate;
        }

        public void setInspectDate(String inspectDate) {
            this.inspectDate = inspectDate;
        }

        public String getInspectorCode() {
            return inspectorCode;
        }

        public void setInspectorCode(String inspectorCode) {
            this.inspectorCode = inspectorCode == null ? null : inspectorCode.trim();
        }

        public String getInspectorName() {
            return inspectorName;
        }

        public void setInspectorName(String inspectorName) {
            this.inspectorName = inspectorName == null ? null : inspectorName.trim();
        }

        public String getInspectTimeLimit() {
            return inspectTimeLimit;
        }

        public void setInspectTimeLimit(String inspectTimeLimit) {
            this.inspectTimeLimit = inspectTimeLimit == null ? null : inspectTimeLimit.trim();
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason == null ? null : reason.trim();
        }

        public String getIfProblem() {
            return ifProblem;
        }

        public void setIfProblem(String ifProblem) {
            this.ifProblem = ifProblem == null ? null : ifProblem.trim();
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? null : remark.trim();
        }

        public String getCreatorCode() {
            return creatorCode;
        }

        public void setCreatorCode(String creatorCode) {
            this.creatorCode = creatorCode == null ? null : creatorCode.trim();
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOperatorCode() {
            return operatorCode;
        }

        public void setOperatorCode(String operatorCode) {
            this.operatorCode = operatorCode == null ? null : operatorCode.trim();
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }

        public String getOperateMark() {
            return operateMark;
        }

        public void setOperateMark(String operateMark) {
            this.operateMark = operateMark == null ? null : operateMark.trim();
        }

        public String getSpotBusiNo() {
            return spotBusiNo;
        }

        public void setSpotBusiNo(String spotBusiNo) {
            this.spotBusiNo = spotBusiNo == null ? null : spotBusiNo.trim();
        }

        public String getPriorityOrder() {
            return priorityOrder;
        }

        public void setPriorityOrder(String priorityOrder) {
            this.priorityOrder = priorityOrder == null ? null : priorityOrder.trim();
        }

        public String getRiskAttribute() {
            return riskAttribute;
        }

        public void setRiskAttribute(String riskAttribute) {
            this.riskAttribute = riskAttribute == null ? null : riskAttribute.trim();
        }

        public String getShipId() {
            return shipId;
        }

        public void setShipId(String shipId) {
            this.shipId = shipId == null ? null : shipId.trim();
        }

        public String getPraticeInspectorCode() {
            return praticeInspectorCode;
        }

        public void setPraticeInspectorCode(String praticeInspectorCode) {
            this.praticeInspectorCode = praticeInspectorCode == null ? null : praticeInspectorCode.trim();
        }

        public String getPraticeInspectorName() {
            return praticeInspectorName;
        }

        public void setPraticeInspectorName(String praticeInspectorName) {
            this.praticeInspectorName = praticeInspectorName == null ? null : praticeInspectorName.trim();
        }

        public String getRegrortCode() {
            return regrortCode;
        }

        public void setRegrortCode(String regrortCode) {
            this.regrortCode = regrortCode == null ? null : regrortCode.trim();
        }

        public String getRegportName() {
            return regportName;
        }

        public void setRegportName(String regportName) {
            this.regportName = regportName == null ? null : regportName.trim();
        }

        public String getShipTypeCode() {
            return shipTypeCode;
        }

        public void setShipTypeCode(String shipTypeCode) {
            this.shipTypeCode = shipTypeCode == null ? null : shipTypeCode.trim();
        }

        public String getShipTypeNameCn() {
            return shipTypeNameCn;
        }

        public void setShipTypeNameCn(String shipTypeNameCn) {
            this.shipTypeNameCn = shipTypeNameCn == null ? null : shipTypeNameCn.trim();
        }

        public String getInspectOrgAutograph() {
            return inspectOrgAutograph;
        }

        public void setInspectOrgAutograph(String inspectOrgAutograph) {
            this.inspectOrgAutograph = inspectOrgAutograph == null ? null : inspectOrgAutograph.trim();
        }

        public String getCaptainNameAutograph() {
            return captainNameAutograph;
        }

        public void setCaptainNameAutograph(String captainNameAutograph) {
            this.captainNameAutograph = captainNameAutograph == null ? null : captainNameAutograph.trim();
        }

        public String getIsApp() {
            return isApp;
        }

        public void setIsApp(String isApp) {
            this.isApp = isApp == null ? null : isApp.trim();
        }

        public String getInspectItem1() {
            return inspectItem1;
        }

        public void setInspectItem1(String inspectItem1) {
            this.inspectItem1 = inspectItem1 == null ? null : inspectItem1.trim();
        }

        public String getInspectItem2() {
            return inspectItem2;
        }

        public void setInspectItem2(String inspectItem2) {
            this.inspectItem2 = inspectItem2 == null ? null : inspectItem2.trim();
        }

        public String getInspectItem3() {
            return inspectItem3;
        }

        public void setInspectItem3(String inspectItem3) {
            this.inspectItem3 = inspectItem3 == null ? null : inspectItem3.trim();
        }

        public String getInspectItem4() {
            return inspectItem4;
        }

        public void setInspectItem4(String inspectItem4) {
            this.inspectItem4 = inspectItem4 == null ? null : inspectItem4.trim();
        }

        public String getInspectItem5() {
            return inspectItem5;
        }

        public void setInspectItem5(String inspectItem5) {
            this.inspectItem5 = inspectItem5 == null ? null : inspectItem5.trim();
        }

        public String getInspectItem6() {
            return inspectItem6;
        }

        public void setInspectItem6(String inspectItem6) {
            this.inspectItem6 = inspectItem6 == null ? null : inspectItem6.trim();
        }

        public String getInspectItem7() {
            return inspectItem7;
        }

        public void setInspectItem7(String inspectItem7) {
            this.inspectItem7 = inspectItem7 == null ? null : inspectItem7.trim();
        }

        public String getInspectItem8() {
            return inspectItem8;
        }

        public void setInspectItem8(String inspectItem8) {
            this.inspectItem8 = inspectItem8 == null ? null : inspectItem8.trim();
        }
    }

    public static class detail {
        private String seqNo;

        private String contentCode;

        private String contentDesc;

        private String ifProblem;

        private String description;

        private String commentCode;

        private String commentDesc;

        private String creatorCode;

        private String createTime;

        private String operatorCode;

        private String operateTime;

        private String operateMark;

        private String result;

        private String isApp;

        private String correctMark;

        public String getCorrectMark() {
            return correctMark;
        }

        public void setCorrectMark(String correctMark) {
            this.correctMark = correctMark;
        }

        private static final long serialVersionUID = 1L;

        public String getContentCode() {
            return contentCode;
        }

        public void setContentCode(String contentCode) {
            this.contentCode = contentCode == null ? null : contentCode.trim();
        }

        public String getContentDesc() {
            return contentDesc;
        }

        public void setContentDesc(String contentDesc) {
            this.contentDesc = contentDesc == null ? null : contentDesc.trim();
        }

        public String getIfProblem() {
            return ifProblem;
        }

        public void setIfProblem(String ifProblem) {
            this.ifProblem = ifProblem == null ? null : ifProblem.trim();
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description == null ? null : description.trim();
        }

        public String getCommentCode() {
            return commentCode;
        }

        public void setCommentCode(String commentCode) {
            this.commentCode = commentCode == null ? null : commentCode.trim();
        }

        public String getCommentDesc() {
            return commentDesc;
        }

        public void setCommentDesc(String commentDesc) {
            this.commentDesc = commentDesc == null ? null : commentDesc.trim();
        }

        public String getCreatorCode() {
            return creatorCode;
        }

        public void setCreatorCode(String creatorCode) {
            this.creatorCode = creatorCode == null ? null : creatorCode.trim();
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOperatorCode() {
            return operatorCode;
        }

        public void setOperatorCode(String operatorCode) {
            this.operatorCode = operatorCode == null ? null : operatorCode.trim();
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }

        public String getOperateMark() {
            return operateMark;
        }

        public void setOperateMark(String operateMark) {
            this.operateMark = operateMark == null ? null : operateMark.trim();
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result == null ? null : result.trim();
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public String getIsApp() {
            return isApp;
        }

        public void setIsApp(String isApp) {
            this.isApp = isApp == null ? null : isApp.trim();
        }
    }
}
