package com.winfo.szrsp.app.mvp.task.view;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.task.view.Level0Item.java
 * Date: 2017/12/12 10:29
 * Description:
 */

public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {


    private String fscCode;
    private String isMustCheck;
    private String itemFuseDetails;
    private String itemFuseFaterId;
    private String itemFuseId;
    private String itemFuseName;
    private String remarks;
    private String checked;
    private boolean canClick=true;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
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

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }
}
