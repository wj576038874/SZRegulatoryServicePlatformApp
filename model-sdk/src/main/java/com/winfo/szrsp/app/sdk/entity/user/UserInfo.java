package com.winfo.szrsp.app.sdk.entity.user;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.user
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.user.UserInfo.java
 * Date: 2017/11/25 14:20
 * Description:
 */

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *生日
     */
    private String brithday;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 凭证类型
     */
    private String credentialsType;

    /**
     * 部门代码
     */
    private String deptCode;

    private String deptName;
    private String orgName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 职位级别
     */
    private String dutyGrade;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 是否部门长
     */
    private String ismanager;

    /**
     * 是否兼职
     */
    private String ispluralism;

    /**
     * 是否短信验证用户
     */
    private String issms;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 办公室电话
     */
    private String officePhone;

    /**
     * 排序号
     */
    private String orderNum;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 职位
     */
    private String position;

    /**
     * 省份
     */
    private String province;

    /**
     * 电话号码
     */
    private String telephoneNumber;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 用工形式
     */
    private String workType;

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDutyGrade(String dutyGrade) {
        this.dutyGrade = dutyGrade;
    }

    public String getDutyGrade() {
        return dutyGrade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setIsmanager(String ismanager) {
        this.ismanager = ismanager;
    }

    public String getIsmanager() {
        return ismanager;
    }

    public void setIspluralism(String ispluralism) {
        this.ispluralism = ispluralism;
    }

    public String getIspluralism() {
        return ispluralism;
    }

    public void setIssms(String issms) {
        this.issms = issms;
    }

    public String getIssms() {
        return issms;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkType() {
        return workType;
    }

}