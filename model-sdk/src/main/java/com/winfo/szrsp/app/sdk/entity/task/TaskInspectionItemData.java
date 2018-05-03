package com.winfo.szrsp.app.sdk.entity.task;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.task
 * @Filename: TaskInspectionItemData
 * @Author: lsj
 * @Date: 2017/12/9  21:38
 * @Description:
 * @Version:
 */
public class TaskInspectionItemData {
    private String checked;
    private String fscCode;
    private String isMustCheck;
    private String itemFuseDetails;
    private String itemFuseFaterId;
    private String itemFuseId;
    private String itemFuseName;
    private String remarks;
    private boolean isChecked;
    private boolean canClick=true;;

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    private List<TaskInspectionItemData> child = new ArrayList<>();

    public List<TaskInspectionItemData> getChild() {
        return child;
    }

    public void setChild(List<TaskInspectionItemData> child) {
        this.child = child;
    }

    public String getFscCode() {
        return fscCode;
    }

    public void setFscCode(String fscCode) {
        this.fscCode = fscCode;
    }

    public String getIsMustCheck() {
        return isMustCheck;
    }

    public void setIsMustCheck(String isMustCheck) {
        this.isMustCheck = isMustCheck;
    }

    public String getItemFuseDetails() {
        return itemFuseDetails;
    }

    public void setItemFuseDetails(String itemFuseDetails) {
        this.itemFuseDetails = itemFuseDetails;
    }

    public String getItemFuseFaterId() {
        return itemFuseFaterId;
    }

    public void setItemFuseFaterId(String itemFuseFaterId) {
        this.itemFuseFaterId = itemFuseFaterId;
    }

    public String getItemFuseId() {
        return itemFuseId;
    }

    public void setItemFuseId(String itemFuseId) {
        this.itemFuseId = itemFuseId;
    }

    public String getItemFuseName() {
        if (itemFuseName == null){
            return "";
        }else {
            return itemFuseName;
        }
    }

    public void setItemFuseName(String itemFuseName) {
        this.itemFuseName = itemFuseName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
