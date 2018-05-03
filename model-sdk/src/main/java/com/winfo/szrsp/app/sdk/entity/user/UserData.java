package com.winfo.szrsp.app.sdk.entity.user;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.user
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.user.UserData.java
 * Date: 2017/11/25 14:25
 * Description: 用户数据
 */

public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户令牌
     */
    private String access_token;


    /**
     * 权限列表
     */
    //private List<PermissionObj> permissionObjList;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

//    public List<PermissionObj> getPermissionObjList() {
//        return permissionObjList;
//    }
//
//    public void setPermissionObjList(List<PermissionObj> permissionObjList) {
//        this.permissionObjList = permissionObjList;
//    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
