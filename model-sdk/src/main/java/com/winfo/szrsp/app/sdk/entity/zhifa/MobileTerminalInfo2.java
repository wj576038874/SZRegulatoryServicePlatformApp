package com.winfo.szrsp.app.sdk.entity.zhifa;

import java.io.Serializable;
import java.math.BigDecimal;

public class MobileTerminalInfo2 implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String deviceImei;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String deviceName;

    private String deviceModel;

    private String deviceVersion;

    private String androidVersion;

    private String deviceMeid;

    private String deviceCpu;

    private String deviceRam;

    private String deviceStorage;

    private String deviceResolvingPower;

    private String ipAddress;

    private String bluetoothAddress;

    private String serialNumber;

    private String wlanMacAddress;

    private String phoneNumber;

    private String networkType;

    private String networkOperatorName;

    private String userUuid;

    private String userName;

    private String orgId;

    private String deptCode;

    private String createTime;
    
    private int count;
    
    

    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDeviceImei() {
        return deviceImei;
    }

    public void setDeviceImei(String deviceImei) {
        this.deviceImei = deviceImei == null ? null : deviceImei.trim();
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion == null ? null : androidVersion.trim();
    }

    public String getDeviceMeid() {
        return deviceMeid;
    }

    public void setDeviceMeid(String deviceMeid) {
        this.deviceMeid = deviceMeid == null ? null : deviceMeid.trim();
    }

    public String getDeviceCpu() {
        return deviceCpu;
    }

    public void setDeviceCpu(String deviceCpu) {
        this.deviceCpu = deviceCpu == null ? null : deviceCpu.trim();
    }

    public String getDeviceRam() {
        return deviceRam;
    }

    public void setDeviceRam(String deviceRam) {
        this.deviceRam = deviceRam == null ? null : deviceRam.trim();
    }

    public String getDeviceStorage() {
        return deviceStorage;
    }

    public void setDeviceStorage(String deviceStorage) {
        this.deviceStorage = deviceStorage == null ? null : deviceStorage.trim();
    }

    public String getDeviceResolvingPower() {
        return deviceResolvingPower;
    }

    public void setDeviceResolvingPower(String deviceResolvingPower) {
        this.deviceResolvingPower = deviceResolvingPower == null ? null : deviceResolvingPower.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress == null ? null : bluetoothAddress.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getWlanMacAddress() {
        return wlanMacAddress;
    }

    public void setWlanMacAddress(String wlanMacAddress) {
        this.wlanMacAddress = wlanMacAddress == null ? null : wlanMacAddress.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType == null ? null : networkType.trim();
    }

    public String getNetworkOperatorName() {
        return networkOperatorName;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName == null ? null : networkOperatorName.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}