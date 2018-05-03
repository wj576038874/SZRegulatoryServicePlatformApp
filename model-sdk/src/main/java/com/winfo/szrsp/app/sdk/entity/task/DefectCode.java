package com.winfo.szrsp.app.sdk.entity.task;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.task
 * @Filename: DefectCode
 * @Author: lsj
 * @Date: 2017/12/17  17:56
 * @Description:
 * @Version:
 */
public class DefectCode {
    private String inspectItemCode;
    private String inspectItemLevel;
    private String inspectItemName;
    private String inspectItemSuperCode;
    private String operateMark;
    private String operateTime;
    private String operatorCode;
    private String valid;
    private List<SmallList> smallList;

    public List<SmallList> getSmallLists() {
        return smallList;
    }

    public void setSmallLists(List<SmallList> smallLists) {
        this.smallList = smallLists;
    }

   public class SmallList{
        private String inspectItemCode;
        private String inspectItemLevel;
        private String inspectItemName;
        private String inspectItemSuperCode;
        private String operateMark;
        private String operateTime;
        private String operatorCode;
        private List<DefectCode> smallList;
        private String valid;

        public String getInspectItemCode() {
            return inspectItemCode;
        }

        public void setInspectItemCode(String inspectItemCode) {
            this.inspectItemCode = inspectItemCode;
        }

        public String getInspectItemLevel() {
            return inspectItemLevel;
        }

        public void setInspectItemLevel(String inspectItemLevel) {
            this.inspectItemLevel = inspectItemLevel;
        }

        public String getInspectItemName() {
            return inspectItemName;
        }

        public void setInspectItemName(String inspectItemName) {
            this.inspectItemName = inspectItemName;
        }

        public String getInspectItemSuperCode() {
            return inspectItemSuperCode;
        }

        public void setInspectItemSuperCode(String inspectItemSuperCode) {
            this.inspectItemSuperCode = inspectItemSuperCode;
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

        public List<DefectCode> getSmallList() {
            return smallList;
        }

        public void setSmallList(List<DefectCode> smallList) {
            this.smallList = smallList;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }
    }
    public String getInspectItemCode() {
        return inspectItemCode;
    }

    public void setInspectItemCode(String inspectItemCode) {
        this.inspectItemCode = inspectItemCode;
    }

    public String getInspectItemLevel() {
        return inspectItemLevel;
    }

    public void setInspectItemLevel(String inspectItemLevel) {
        this.inspectItemLevel = inspectItemLevel;
    }

    public String getInspectItemName() {
        return inspectItemName;
    }

    public void setInspectItemName(String inspectItemName) {
        this.inspectItemName = inspectItemName;
    }

    public String getInspectItemSuperCode() {
        return inspectItemSuperCode;
    }

    public void setInspectItemSuperCode(String inspectItemSuperCode) {
        this.inspectItemSuperCode = inspectItemSuperCode;
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

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
