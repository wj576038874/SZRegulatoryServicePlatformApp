package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType2;
import com.winfo.szrsp.app.sdk.entity.task.TaskDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskListData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.task.TaskShipData;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Guan on 2017-12-09.
 */

public interface TaskService {
    /**
     * 查询个人任务列表
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskAssignRestService/myTask")
    Observable<ResponseResult<TaskListData>> getTaskListData(@Field("parameterJson") String parameterJson);

    /**
     * 根据taskId查询任务所有信息
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/selectByTaskId")
    Observable<ResponseResult<TaskDetails>> getTaskDetData(@Field("parameterJson") String parameterJson);


    /**
     * 接受任务
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/acceptTask")
    Observable<ResponseResult<String>> acceptTask(@Field("parameterJson") String parameterJson);


    /**
     * 退回任务/取消任务
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/cancelTask")
    Observable<ResponseResult<String>> rejectTask(@Field("parameterJson") String parameterJson);

    /**
     * 完成任务
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/achieveTask")
    Observable<ResponseResult<TaskFinishData>> finishTask(@Field("parameterJson") String parameterJson);


    /**
     * 获取部门人员
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskAssignRestService/getUsersListByUserDept")
    Observable<ResponseResult<TaskPersonResouse>> getUsersListByUserDept(@Field("parameterJson") String parameterJson);

    /**
     * 获取ais信息
     * @param
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("http://219.133.95.9:8001/AisVesselTrack")
//    @POST("http://191.254.1.74:8002/AisVesselTrack")
    Observable<List<Ais>> searShipByMMSI(@Field("TYPE") String TYPE, @Field("MMSI") String MMSI, @Field("Check") boolean Check);

//    /**
//     * 获取任务类型
//     * @param parameterJson
//     * @return
//     */
//    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
//    @POST("sz/taskTypeRestService/selectAllByZtree")
//    Observable<ResponseResult<List<AllTaskType>>> getTaskType(@Field("parameterJson") String parameterJson);

    /**
     * 获取任务类型
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskTypeRestService/selectByConditionsToApp")
    Observable<ResponseResult<AllTaskType2>> getTaskType(@Field("parameterJson") String parameterJson);

    /**
     * 获取所有项目库
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskCheckItemFuseService/selectAll")
    Observable<ResponseResult<List<TaskInspectionItemData>>> getTaskInspectionAll(@Field("parameterJson") String parameterJson);

    /**
     * 发布任务
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/createTask")
    Observable<ResponseResult<TaskInfo>> releaseTask(@Field("parameterJson") String parameterJson);

    /**
     * 根据任务id获取项目库
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskCheckItemFuseService/selectTaskCheckItemIdByTaskTypeIdNoPage")
    Observable<ResponseResult<List<TaskInspectionItemData>>> getTaskInspectionTaskTypeId(@Field("parameterJson") String parameterJson);


    /**
     * 获取指派人员信息
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/userRestService/getUserByDeptCode")
    Observable<ResponseResult<List<UserInfo>>> getdeptUser(@Field("parameterJson") String parameterJson);

    /**
     * 获取选船数据
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/shipSearch/text")
    Observable<ResponseResult<List<TaskShipData>>> getShipDataByKey(@Field("parameterJson") String parameterJson);

    /**
     * 获取选船数据
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/shipSearch/mmsi")
    Observable<ResponseResult<List<TaskShipData>>> getShipDataByMMSI(@Field("parameterJson") String parameterJson);

    /**
     * 分发任务
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/assignTask")
    Observable<ResponseResult<String>> distributionTask(@Field("parameterJson") String parameterJson);

    /**
     * 上传证书
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/fileUpload/outPutJpg")
    Observable<ResponseResult<String>> uploadCertificate(@Field("parameterJson") String parameterJson);

    /**
     * 根据mmsi获取证书下载列表数据
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/fileUpload/selectByMmsi")
    Observable<ResponseResult<CertificateInfo>> getCertificateByMmsi(@Field("parameterJson") String parameterJson);


    /**
     * 根据路径删除证书
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/fileUpload/deleteByIdBath")
    Observable<ResponseResult<String>> deleteCertificateByUrl(@Field("parameterJson") String parameterJson);


}
