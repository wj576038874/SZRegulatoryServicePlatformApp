package com.winfo.szrsp.app.mvp.task.view;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.task.view
 * @Filename: Level4Item
 * @Author: lsj
 * @Date: 2017/12/23  10:58
 * @Description:
 * @Version:
 */
public class Level4Item implements MultiItemEntity {

    private String fscCode;
    private String isMustCheck;
    private String itemFuseDetails;
    private String itemFuseFaterId;
    private String itemFuseId;
    private String itemFuseName;
    private String remarks;
    private String input;
    private String img_path;
    private String video_path;
    private String album_path;
    private String checked;
    private boolean canClick=true;;
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

    public String getInput() {
        if (input == null){
            return "";
        }else {
            return input;
        }
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getImg_path() {
        if (img_path == null){
            return "";
        }else {
            return img_path;
        }
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getVideo_path() {
        if (video_path == null){
            return "";
        }else {
            return video_path;
        }
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getAlbum_path() {
        if (album_path == null){
            return "";
        }else {
            return album_path;
        }
    }

    public void setAlbum_path(String album_path) {
        this.album_path = album_path;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_4;
    }
}
