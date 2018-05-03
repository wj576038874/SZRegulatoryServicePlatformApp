package com.winfo.dnc.sdk;

import android.util.DisplayMetrics;

/**
 * 
 * @author King
 *
 */
class DeviceParameters {

	static DeviceParam getDeviceParameters(DisplayMetrics dm){
		DeviceParam dp=new DeviceParam();
		dp.screenWidth = dm.widthPixels;
		dp.screenHeight = dm.heightPixels;
		dp.densityDpi = dm.densityDpi;
		dp.scale = dm.density;
		dp.fontScale = dm.scaledDensity;
		 
		dp.screenOrientation = dp.screenHeight > dp.screenWidth ? DeviceParam.SCREEN_ORIENTATION_VERTICAL
		: DeviceParam.SCREEN_ORIENTATION_HORIZONTAL;
	     
		return dp;
	}
}

