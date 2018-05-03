package com.winfo.szrsp.app.sdk.entity.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoBo on 2018/4/16.
 */

public class TaskInspectionItemByNameData {

    /**
     * checked :
     * fscCode :
     * isMustCheck : 0
     * itemFuseDetails :
     * itemFuseFaterId : C_00000000
     * itemFuseId : C_25010100
     * itemFuseName : 水上巡检
     * remarks :
     * spotCode :
     */

    private String checked;
    private String fscCode;
    private String isMustCheck;
    private String itemFuseDetails;
    private String itemFuseFaterId;
    private String itemFuseId;
    private String itemFuseName;
    private String remarks;
    private String spotCode;
    private boolean isCheckeds;

    public TaskInspectionItemByNameData() {

    }

    public TaskInspectionItemByNameData(boolean isCheckeds) {
        this.isCheckeds = isCheckeds;
    }

    public boolean isCheckeds() {
        return isCheckeds;
    }

    public void setCheckeds(boolean checkeds) {
        isCheckeds = checkeds;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
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
