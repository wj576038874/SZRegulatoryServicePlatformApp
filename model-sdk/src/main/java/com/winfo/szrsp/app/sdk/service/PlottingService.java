package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.plotting.PlottingListData;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by winfo053 on 2018/4/9.
 */

public interface PlottingService {
    /**
     * 标绘获取列表数据
     *
     * @param parameterJson 提交数据
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/plottingRestService/selectByConditions")
    Observable<ResponseResult<PlottingListData>> selectByConditions(@Field("parameterJson") String parameterJson);

    /**
     * 标绘根据id删除数据
     *
     * @param parameterJson 提交数据
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/plottingRestService/deleteByPrimaryKey")
    Observable<ResponseResult<String>> deleteByPrimaryKey(@Field("parameterJson") String parameterJson);

    /**
     * 标绘添加数据
     *
     * @param parameterJson 提交数据
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/plottingRestService/insertSelective")
    Observable<ResponseResult<String>> insertSelective(@Field("parameterJson") String parameterJson);


    /**
     * 标绘修改数据
     *
     * @param parameterJson 提交数据
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/plottingRestService/updateByPrimaryKeySelective")
    Observable<ResponseResult<String>> updateByPrimaryKeySelective(@Field("parameterJson") String parameterJson);
}
