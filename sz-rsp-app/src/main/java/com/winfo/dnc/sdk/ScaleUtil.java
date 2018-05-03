package com.winfo.dnc.sdk;

import android.content.Context;
import android.content.res.Resources;

import com.winfo.szrsp.app.R;


/**
 * 
 * @author winfo-wj
 *
 */
class ScaleUtil {
	
	static String getScaleText(Context context , int level){
		Resources res = context.getResources();
		String scaleText = null;
		switch (level) {
		case 1:
			scaleText = res.getString(R.string.level1);
			break;
		case 2:
			scaleText = res.getString(R.string.level2);
			break;
		case 3:
			scaleText = res.getString(R.string.level3);
			break;
		case 4:
			scaleText = res.getString(R.string.level4);
			break;
		case 5:
			scaleText = res.getString(R.string.level5);
			break;
		case 6:
			scaleText = res.getString(R.string.level6);
			break;
		case 7:
			scaleText = res.getString(R.string.level7);
			break;
		case 8:
			scaleText = res.getString(R.string.level8);
			break;
		case 9:
			scaleText = res.getString(R.string.level9);
			break;
		case 10:
			scaleText = res.getString(R.string.level10);
			break;
		case 11:
			scaleText = res.getString(R.string.level11);
			break;
		case 12:
			scaleText = res.getString(R.string.level12);
			break;
		case 13:
			scaleText = res.getString(R.string.level13);
			break;
		case 14:
			scaleText = res.getString(R.string.level14);
			break;
		case 15:
			scaleText = res.getString(R.string.level15);
			break;
		case 16:
			scaleText = res.getString(R.string.level16);
			break;
		case 17:
			scaleText = res.getString(R.string.level17);
			break;
		case 18:
			scaleText = res.getString(R.string.level18);
			break;
		}
		return scaleText;
	}
}
