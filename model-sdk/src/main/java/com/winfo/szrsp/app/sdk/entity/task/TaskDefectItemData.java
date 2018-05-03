package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.task.view.Level0Item.java
 * Date: 2017/12/12 10:29
 * Description:
 */

public class TaskDefectItemData implements Serializable {

    private String fscCode;
    private String isMustCheck;
    private String itemFuseDetails;
    private String itemFuseFaterId;
    private String itemFuseId;
    private String itemFuseName;
    private String remarks;
    private String spotCode;

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
        return itemFuseName;
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

    public String getSpotCode() {
        return spotCode;
    }

    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }
}
