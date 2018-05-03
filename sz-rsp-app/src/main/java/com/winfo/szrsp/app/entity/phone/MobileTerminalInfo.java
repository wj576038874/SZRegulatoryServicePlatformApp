package com.winfo.szrsp.app.entity.phone;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileTerminalInfo implements Serializable{

    /**
     * 设备imei号
     */
    private String deviceImei;

    /**
     * 设备纬度
     */
    private BigDecimal latitude;

    /**
     * 设备经度
     */
    private BigDecimal longitude;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 型号
     */
    private String deviceModel;

    /**
     * 设备版本号
     */
    private String deviceVersion;

    /**
     * 系统版本
     */
    private String androidVersion;

    /**
     * 设备meid
     */
    private String deviceMeid;

    /**
     * 设备处理器
     */
    private String deviceCpu;

    /**
     * 设备运行内存
     */
    private String deviceRam;

    /**
     * 设备存储
     */
    private String deviceStorage;

    /**
     * 设备分辨率
     */
    private String deviceResolvingPower;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 蓝牙地址
     */
    private String bluetoothAddress;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * WLAN MAC地址
     */
    private String wlanMacAddress;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 网络类型
     */
    private String networkType;

    /**
     * 运营商
     */
    private String networkOperatorName;


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
}