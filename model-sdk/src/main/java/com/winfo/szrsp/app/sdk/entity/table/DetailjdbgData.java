package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.table
 * @Filename: DetailjdbgData
 * @Author: lsj
 * @Date: 2017/12/8  13:59
 * @Description:
 * @Version:船舶现场监督报告单条详情接受模型
 */
public class DetailjdbgData {

    private List<list> list;
    private tflag tflag;

    public List<DetailjdbgData.list> getList() {
        return list;
    }

    public void setList(List<DetailjdbgData.list> list) {
        this.list = list;
    }

    public DetailjdbgData.tflag getTflag() {
        return tflag;
    }

    public void setTflag(DetailjdbgData.tflag tflag) {
        this.tflag = tflag;
    }

    public class list{
        private String commentCode;
        private String commentDesc;
        private String correctMark;
        private String createTime;
        private String creatorCode;
        private String defectCode;
        private String defectDesc;
        private String defectMark;
        private String enforceFoundation;
        private String initialInspectNo;
        private String inspectNo;
        private String isApp;
        private String isInspectOrgRelated;
        private String operateMark;
        private String operateTime;
        private String operatorCode;
        private String remark;
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

        public String getDefectCode() {
            return defectCode;
        }

        public void setDefectCode(String defectCode) {
            this.defectCode = defectCode;
        }

        public String getDefectDesc() {
            return defectDesc;
        }

        public void setDefectDesc(String defectDesc) {
            this.defectDesc = defectDesc;
        }

        public String getDefectMark() {
            return defectMark;
        }

        public void setDefectMark(String defectMark) {
            this.defectMark = defectMark;
        }

        public String getEnforceFoundation() {
            return enforceFoundation;
        }

        public void setEnforceFoundation(String enforceFoundation) {
            this.enforceFoundation = enforceFoundation;
        }

        public String getInitialInspectNo() {
            return initialInspectNo;
        }

        public void setInitialInspectNo(String initialInspectNo) {
            this.initialInspectNo = initialInspectNo;
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

        public String getIsInspectOrgRelated() {
            return isInspectOrgRelated;
        }

        public void setIsInspectOrgRelated(String isInspectOrgRelated) {
            this.isInspectOrgRelated = isInspectOrgRelated;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(int seqNo) {
            this.seqNo = seqNo;
        }
    }

    public class tflag{
        private String busiNo;
        private String captainCertNo;
        private String captainName;
        private String captainNameAutograph;
        private String captainPhone;
        private String correctMark;
        private String createTime;
        private String creatorCode;
        private String defectNum;
        private String detentionMark;
        private String fscBusiNo;
        private String initialInspectMark;
        private String initialInspectNo;
        private String inspectAction;
        private String inspectDate;
        private String inspectNo;
        private String inspectOrg;
        private String inspectOrgAutograph;
        private String inspectType;
        private String inspectorCode;
        private String inspectorName;
        private String isApp;
        private String isPassed;
        private String isSpecialInspect;
        private String operateMark;
        private String operateTime;
        private String operatorCode;
        private String otherDesc;
        private String portCode;
        private String praticeInspectorCode;
        private String praticeInspectorName;
        private String priorityOrder;
        private String regportCode;
        private String regportName;
        private String remark;
        private String riskAttribute;
        private String shipManager;
        private String shipManagerNo;
        private String shipNameCn;
        private String shipNo;
        private String shipTypeCode;
        private String shipTypeNameCn;
        private String shopId;
        private String specialInspectType;
        private String specialInspectName;

        public String getSpecialInspectName() {
            return specialInspectName;
        }

        public void setSpecialInspectName(String specialInspectName) {
            this.specialInspectName = specialInspectName;
        }

        private String taskId;
        private String unclosedDefectNum;
        private String voyageId;

        public tflag() {
        }

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

        public String getDefectNum() {
            return defectNum;
        }

        public void setDefectNum(String defectNum) {
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
