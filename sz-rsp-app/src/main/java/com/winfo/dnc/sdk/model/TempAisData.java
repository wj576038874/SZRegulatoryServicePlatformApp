package com.winfo.dnc.sdk.model;

import java.util.Date;

public class TempAisData {
	public String id;
	public String mmsi;
	public String shipName;
	public String callSign;
	public Double longitude;
	public Double latitude;
	public Double heading;
	public Double speed;
	public Double turningRate;
	public Double navStatus;
	public String positionPrecision;
	public Integer status;
	public Integer isDeleted;
	public Date addTime;
	public double X;
	public double Y;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getCallSign() {
		return callSign;
	}
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getHeading() {
		return heading;
	}
	public void setHeading(Double heading) {
		this.heading = heading;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getTurningRate() {
		return turningRate;
	}
	public void setTurningRate(Double turningRate) {
		this.turningRate = turningRate;
	}
	public Double getNavStatus() {
		return navStatus;
	}
	public void setNavStatus(Double navStatus) {
		this.navStatus = navStatus;
	}
	public String getPositionPrecision() {
		return positionPrecision;
	}
	public void setPositionPrecision(String positionPrecision) {
		this.positionPrecision = positionPrecision;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getAisDate() {
		return aisDate;
	}
	public void setAisDate(Date aisDate) {
		this.aisDate = aisDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getMileage() {
		return mileage;
	}
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	public Integer getSailingTime() {
		return sailingTime;
	}
	public void setSailingTime(Integer sailingTime) {
		this.sailingTime = sailingTime;
	}
	public Integer getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	public Double getAddLongitude() {
		return addLongitude;
	}
	public void setAddLongitude(Double addLongitude) {
		this.addLongitude = addLongitude;
	}
	public Double getAddLatitude() {
		return addLatitude;
	}
	public void setAddLatitude(Double addLatitude) {
		this.addLatitude = addLatitude;
	}
	public Date aisDate;
	public String location;
	public Integer mileage;
	public Integer sailingTime;
	public Integer workTime;
	public Double addLongitude;
	public Double addLatitude;

	
}
