package com.winfo.szrsp.app.sdk.entity.version;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.version
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.version.VersionInfo.java
 * Date: 2018/1/29 16:33
 * Description:
 */
public class VersionInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String versionName;//版本名
    private int versionCode;//版本号
    private String versionDesc;//版本描述信息内容
    private String downloadUrl;//新版本的下载路径
    private String versionSize;//版本大小
    private String isUpdate;//是否强制更新


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(String versionSize) {
        this.versionSize = versionSize;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }
}

