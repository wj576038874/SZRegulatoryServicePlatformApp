package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.thys.ShipData;
import com.winfo.szrsp.app.sdk.entity.thys.ThysData;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by HoBo on 2018/4/2.
 */

public interface ApiService {
    /**
     * 附近列表通航要素数据（全部）
     */
    @GET("sz/thysRestService/getAllThysType")
    Observable<ResponseResult<List<ThysData>>> loadThysData();

    /**
     * 附近列表船舶数据
     */
    @GET("sz/tDictShipTypeRestService/selectAll")
    Observable<ResponseResult<List<ShipData>>> loadBoatsData();
}
