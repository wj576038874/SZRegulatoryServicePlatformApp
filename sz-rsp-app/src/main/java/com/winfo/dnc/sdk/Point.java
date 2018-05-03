package com.winfo.dnc.sdk;

/**
 * 经纬度坐标点
 * @author King
 *
 */
public class Point {
	
	public Point(){
		
	}
	public Point(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}
//	public double L;
//	public double B;
	//屏幕坐标X
	public double X;
	//屏幕坐标Y
	public double Y;
	//经度
	public double lon;
	//纬度
	public double lat;
	//方向
	public float direction; 
	
	public String id;
}
