package com.winfo.szrsp.app.utils;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @PackageName: com.winfo.szrsp.app.utils
 * @FileName: com.winfo.szrsp.app.utils.Constants.java
 * @Author: wenjie
 * @Date: 2017-10-17 15:29
 * @Description:
 * @Version:
 */
public interface Constants {


    /**
     * 开始定位
     */
    int MSG_LOCATION_START = 100;

    /**
     * 定位完成
     */
    int MSG_LOCATION_FINISH = 101;

    /**
     * 停止定位
     */
    int MSG_LOCATION_STOP = 102;

    /**
     * 10秒钟 刷新一次ais数据
     */
    int MSG_REFRESHAIS = 0x10000;

    int MSG_REFRESHALLZHIFA = 0x10001;

    int UPDATE_LOCATION = 0x1003;

    /**
     * 返回的json数据
     */
    String JSON_DATA = "data";

    /**
     * 附近查询 range
     */
    String NEARBY_SEARCH_RANGE = "5000";


    String RESULT = "result";
}
