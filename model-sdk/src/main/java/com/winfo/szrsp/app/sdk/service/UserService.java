package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.user.UserData;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.service
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.service.UserService.java
 * Date: 2017/11/27 15:05
 * Description: 用户接口
 */

public interface UserService {

    /**
     * 用户登录
     *
     * @param parameterJson 用户名和密码
     * @return 用户信息
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/userLoginRestService/login")
    Observable<ResponseResult<UserData>> login(@Field("parameterJson") String parameterJson);
}
