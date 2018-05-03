package com.winfo.szrsp.app.sdk.entity.user;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.user
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.user.PermissionObj.java
 * Date: 2017/11/25 14:16
 * Description: 权限实体类
 */

public class PermissionObj implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限主键
     */
    private String id;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 父集权限id
     */
    private String parentId;

    /**
     * 权限图标
     */
    private String permissionIcon;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限说明
     */
    private String permissionNote;

    /**
     * 权限路径
     */
    private String permissionPath;

    /**
     * 权限备注
     */
    private String permissionRemark;

    /**
     * 权限类型
     */
    private String permissionType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionNote() {
        return permissionNote;
    }

    public void setPermissionNote(String permissionNote) {
        this.permissionNote = permissionNote;
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath;
    }

    public String getPermissionRemark() {
        return permissionRemark;
    }

    public void setPermissionRemark(String permissionRemark) {
        this.permissionRemark = permissionRemark;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }
}
