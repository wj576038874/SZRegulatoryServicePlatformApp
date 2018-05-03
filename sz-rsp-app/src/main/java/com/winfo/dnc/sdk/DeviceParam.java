package com.winfo.dnc.sdk;


/**
 * 
 * @author King
 *
 */
class DeviceParam{
	@Override
	public String toString() {
		return "DeviceParam [screenWidth=" + screenWidth + ", screenHeight="
				+ screenHeight + ", densityDpi=" + densityDpi + ", scale="
				+ scale + ", fontScale=" + fontScale + ", screenOrientation="
				+ screenOrientation + "]";
	}
	int screenWidth;
	int screenHeight;
	int densityDpi;
	float scale;
	float fontScale;
	final static int SCREEN_ORIENTATION_VERTICAL = 1;
	final static int SCREEN_ORIENTATION_HORIZONTAL = 2;
	int screenOrientation = SCREEN_ORIENTATION_VERTICAL;
	
}