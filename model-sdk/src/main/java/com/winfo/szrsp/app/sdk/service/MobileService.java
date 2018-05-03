package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.version.VersionInfo;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalHistory;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.service
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.service.MobileService.java
 * Date: 2017/12/15 11:50
 * Description:
 */

public interface MobileService {

    /**
     * 保存设备参数
     *
     * @param parameterJson 参数
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/mobileTerminalInfoRestService/saveOrUpdate")
    Observable<ResponseResult<String>> add(@Field("parameterJson") String parameterJson);

    /**
     * 更新设备位置
     *
     * @param parameterJson 参数
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/mobileTerminalInfoRestService/update")
    Observable<ResponseResult<String>> updateLocation(@Field("parameterJson") String parameterJson);

    /**
     * 版本更新
     * @return 版本信息
     */
    @GET("http://219.133.95.9:8001/AppDownLoad/version.json")
    Observable<VersionInfo> checkVersionInfo();


    /**
     * 获取执法轨迹
     * @param parameterJson 请求参数
     * @return 执法轨迹
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/mobileTerminalHistoryRestService/selectByTrack")
    Observable<ResponseResult<List<MobileTerminalHistory>>> getZhifaTrack(@Field("parameterJson") String parameterJson);

}
