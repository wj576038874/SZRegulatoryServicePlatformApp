package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordObject;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.ListCBWFXCData;
import com.winfo.szrsp.app.sdk.entity.table.ListContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.ListCruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.ListDXSHCData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.ListElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.ListGoodSecneOutRestServiceData;
import com.winfo.szrsp.app.sdk.entity.table.ListPSCData;
import com.winfo.szrsp.app.sdk.entity.table.ListWatersPatrolData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbjdjgData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbxcData;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.ListXHTJData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.FourTableSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
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
 * FileName: com.winfo.szrsp.app.sdk.service.UserService.java
 * Date: 2017/11/27 15:05
 * Description: 用户接口
 */

public interface TableService {

    /**
     * 大型散货船检查表提交
     *
     * @param parameterJson 提交数据
     * @return 用户信息
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/ctSpecialShipType0203RestService/insertSelective")
    Observable<ResponseResult<String>> insertSelectiveDXSHCJCB(@Field("parameterJson") String parameterJson);

    /**
     * 查询大型散货船检查表数据列表
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/ctSpecialShipType0203RestService/getData")
    Observable<ResponseResult<ListDXSHCData>> findDXSHCData(@Field("parameterJson") String parameterJson);

    /**
     * 查询水上巡航表格数据列表
     */
    @FormUrlEncoded
    @POST("sz/ctWaterCruiseRecordInfoRestService/getData")
    Observable<ResponseResult<ListWatersPatrolData>> findWatersPatrolData(@Field("parameterJson") String parameterJson);

    /**
     * 查询PSC表格数据列表
     */
    @FormUrlEncoded
    @POST("sz/ctPscFromRestService/getData")
    Observable<ResponseResult<ListPSCData>> findPSCData(@Field("parameterJson") String parameterJson);

    /**
     * 查询巡航统计表格数据列表
     */
    @FormUrlEncoded
    @POST("sz/ctCruiseStatisticsRestService/getData")
    Observable<ResponseResult<ListXHTJData>> findXHTJData(@Field("parameterJson") String parameterJson);

    /**
     * 查询电子巡航异常表格数据列表
     */
    @FormUrlEncoded
    @POST("sz/ctElectronicCruiseExceptionRestService/getData")
    Observable<ResponseResult<ListElectronicCruiseException>> findElectronicCruiseException(@Field("parameterJson") String parameterJson);

    /**
     * 查询电子巡航异常表格数据列表
     */
    @FormUrlEncoded
    @POST("sz/ctSafetySceneInspectRestService/getData")
    Observable<ResponseResult<ListCBWFXCData>> findCBWFData(@Field("parameterJson") String parameterJson);

    /**
     * 查询船舶载运普通货物集装箱开箱检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctGoodSecneOutRestService/getData")
    Observable<ResponseResult<ListGoodSecneOutRestServiceData>> findGoodSecneOutRestServiceData(@Field("parameterJson") String parameterJson);

    /**
     * 查询舶载运集装箱危险货物污染危害性货物现场检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctDangerPolluteSceneRestService/getData")
    Observable<ResponseResult<ListDangerousGoodsXianChangData>> findDangerusGoodsXianChangData(@Field("parameterJson") String parameterJson);

    @FormUrlEncoded
    @POST("sz/ctCaseWeightInspectRestService/getData")
    Observable<ResponseResult<ListContainerWeightInspectData>> findContainerWeightInspectData(@Field("parameterJson") String parameterJson);


    /**
     * 查询舶载运集装箱危险货物污染危害性货物开箱检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctDangerPolluteOutRestService/getData")
    Observable<ResponseResult<ListDangerousGoodsKaiXiangData>> findDangerusGoodsKaiXiangData(@Field("parameterJson") String parameterJson);

    /**
     * 查询巡航工作记录表
     */
    @FormUrlEncoded
    @POST("sz/ctCruisingRecoreRestService/getData")
    Observable<ResponseResult<ListCruiseWorkData>> findCruiseWorkData(@Field("parameterJson") String parameterJson);

    /**
     * 船旗国监督报告提交
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tFlagStateControlRestService/insertSelective")
    Observable<ResponseResult<String>> addjdbgData(@Field("parameterJson") String parameterJson);


    /**
     * 查询船旗国数据列表
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tFlagStateControlRestService/getData")
    Observable<ResponseResult<ListcbjdjgData>> findJDBGData(@Field("parameterJson") String parameterJson);


    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tFlagStateControlRestService/selectByPrimaryKey")
    Observable<ResponseResult<DetailjdbgData>> findCQGDetailData(@Field("parameterJson") String parameterJson);

    /**
     * 船舶现场监督报告提交
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tSiteSupervisionRestService/insertSelective")
    Observable<ResponseResult<String>> addcbjdbgData(@Field("parameterJson") String parameterJson);

    /**
     * 查询船舶现场监督报告数据列表
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tSiteSupervisionRestService/getData")
    Observable<ResponseResult<ListcbxcData>> findCBXCData(@Field("parameterJson") String parameterJson);

    /**
     * 查询船舶现场监督报告单条详情数据
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tSiteSupervisionRestService/selectByPrimaryKey")
    Observable<ResponseResult<DetailcbxcjdbgData>> findCBXCDetailData(@Field("parameterJson") String parameterJson);

    /**
     * 查询所有泊位
     */
    @FormUrlEncoded
    @POST("sz/tDictBerthRestService/selectByConditions")
    Observable<ResponseResult<List<ShipBerthData>>> findShipBerData(@Field("parameterJson") String parameterJson);


    /**
     * 水域巡航检查
     */
    @FormUrlEncoded
    @POST("sz/ctWaterCruiseRecordInfoRestService/insertSelective")
    Observable<ResponseResult<String>> subWatersPatrolData(@Field("parameterJson") String parameterJson);

    /**
     * 查询水域巡航表格单条详情数据
     */
    @FormUrlEncoded
    @POST("sz/ctWaterCruiseRecordInfoRestService/selectByPrimaryKey")
    Observable<ResponseResult<CtWaterCruiseRecordObject>> findWatersPatrolDetailData(@Field("parameterJson") String parameterJson);

    @FormUrlEncoded
    @POST("sz/ctPscFromRestService/selectByPrimaryKey")
    Observable<ResponseResult<CtPscFromObjectDetail>> findPSCFormDetailData(@Field("parameterJson") String parameterJson);


    /**
     * 电子巡航异常表格提交
     */
    @FormUrlEncoded
    @POST("sz/ctElectronicCruiseExceptionRestService/insertSelective")
    Observable<ResponseResult<String>> subCtElectronicCruiseException(@Field("parameterJson") String parameterJson);

    /**
     * 查询任务检查项列表
     */
    @FormUrlEncoded
    @POST("sz/taskCheckItemFuseService/selectItemFuseIdByItemFuseIdListNoPage")
    Observable<ResponseResult<List<TaskInspectionItemData>>> getTaskItemData(@Field("parameterJson") String parameterJson);

    /**
     * 查询任务检查项列表
     */
    @FormUrlEncoded
    @POST("sz/taskCheckItemFuseService/selectItemFuseIdByTaskTypeId")
    Observable<ResponseResult<List<TaskDefectItemData>>> getTaskDefectItemData(@Field("parameterJson") String parameterJson);

    /**
     * 获取部门人员
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskAssignRestService/getUsersListByUserDept")
    Observable<ResponseResult<TaskPersonResouse>> getUsersListByUserDept(@Field("parameterJson") String parameterJson);

    /**
     * 获取缺陷代码
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/tdictFscDefectCodeRestService/selectAllSmallByBig")
    Observable<ResponseResult<List<DefectCode>>> getDefectCode(@Field("parameterJson") String parameterJson);

    /**
     * 完成任务
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskInfoSZRestService/achieveTask")
    Observable<ResponseResult<TaskFinishData>> finishTask(@Field("parameterJson") String parameterJson);

    /**
     * PSCForm提交
     */
    @FormUrlEncoded
    @POST("sz/ctPscFromRestService/insertSelective")
    Observable<ResponseResult<String>> addPSCFormData(@Field("parameterJson") String parameterJson);


    /**
     * 巡航统计表格提交
     */
    @FormUrlEncoded
    @POST("sz/ctCruiseStatisticsRestService/insertSelective")
    Observable<ResponseResult<String>> addCruiseStatisticsData(@Field("parameterJson") String parameterJson);

    /**
     * 巡航统计表格
     * 单条查询详情
     */
    @FormUrlEncoded
    @POST("sz/ctCruiseStatisticsRestService/selectByPrimaryKey")
    Observable<ResponseResult<CtCruiseStatisticsObject>> findCruiseStatisticsData(@Field("parameterJson") String parameterJson);

    /**
     * 船舶危防现场监督检查记录表提交
     */
    @FormUrlEncoded
    @POST("sz/ctSafetySceneInspectRestService/insertSelective")
    Observable<ResponseResult<String>> addCBWFXCData(@Field("parameterJson") String parameterJson);


    /**
     * 船舶危防现场监督检查记录表单表查询
     */
    @FormUrlEncoded
    @POST("sz/ctSafetySceneInspectRestService/selectByPrimaryKey")
    Observable<ResponseResult<CtSafeInspectNoticeObject>> findCBWFXCDetailData(@Field("parameterJson") String parameterJson);

    /**
     * 舶载运集装箱危险货物/污染危害性货物现场检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctDangerPolluteSceneRestService/insertSelective")
    Observable<ResponseResult<String>> addWXHWXCJCData(@Field("parameterJson") String parameterJson);

    /**
     * 舶载运集装箱危险货物/污染危害性货物现场检查记录表详情
     */
    @FormUrlEncoded
    @POST("sz/ctDangerPolluteSceneRestService/selectByPrimaryKey")
    Observable<ResponseResult<DangerousGoodsXianChangData>> findWXHWXCJCData(@Field("parameterJson") String parameterJson);

    /**
     * 船舶载运危险货物/污染危害性货物集装箱开箱检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctDangerPolluteOutRestService/insertSelective")
    Observable<ResponseResult<String>> addWXHWKXJCData(@Field("parameterJson") String parameterJson);

    /**
     * 船舶载运危险货物/污染危害性货物集装箱开箱检查记录表详情
     */
    @FormUrlEncoded
    @POST("sz/ctDangerPolluteOutRestService/selectByPrimaryKey")
    Observable<ResponseResult<DangerousGoodsKaiXiangData>> findWXHWKXJCData(@Field("parameterJson") String parameterJson);


    /**
     * 船舶载运普通货物集装箱开箱检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctGoodSecneOutRestService/insertSelective")
    Observable<ResponseResult<String>> addPTHWKXJCData(@Field("parameterJson") String parameterJson);

    /**
     * 船舶载运普通货物集装箱开箱检查记录表
     */
    @FormUrlEncoded
    @POST("sz/ctCaseWeightInspectRestService/insertSelective")
    Observable<ResponseResult<String>> addJZXZLJCData(@Field("parameterJson") String parameterJson);

    /**
     * 开箱检查四张表多张提交
     */
    @FormUrlEncoded
    @POST("sz/fourOutOfBoxAuditRestService/insertSelective")
    Observable<ResponseResult<FourTableSubData>> subTable(@Field("parameterJson") String parameterJson);

//    sz/taskCheckItemFuseService/selectAll
//    sz/taskCheckItemFuseService/selectXunHang

    /**
     * 获取所有项目库
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/taskCheckItemFuseService/selectXunHang")
    Observable<ResponseResult<List<TaskInspectionItemData>>> getTaskInspectionAll(@Field("parameterJson") String parameterJson);

    /**
     * 根据搜索查询检查项目库
     */
    @FormUrlEncoded
    @POST("sz/taskCheckItemFuseService/selectByName")
    Observable<ResponseResult<List<TaskInspectionItemByNameData>>> getTaskInspectionByName(@Field("parameterJson") String parameterJson);

    /**
     * 巡航工作记录表
     */
    @FormUrlEncoded
    @POST("sz/ctCruisingRecoreRestService/insertSelective")
    Observable<ResponseResult<String>> addXHGZJLData(@Field("parameterJson") String parameterJson);

    /**
     * 巡航工作记录表详情
     */
    @FormUrlEncoded
    @POST("sz/ctCruisingRecoreRestService/selectByPrimaryKey")
    Observable<ResponseResult<CruiseWorkData>> findXHGZJLData(@Field("parameterJson") String parameterJson);

    /**
     * 获取巡航船列表
     */
    @FormUrlEncoded
    @POST("sz/lawEnforcementShipRestService/selectByOrg")
    Observable<ResponseResult<List<CruiseShipData>>> findShip(@Field("parameterJson") String parameterJson);


    /**
     * 获取巡航船列表
     */
    @FormUrlEncoded
    @POST("sz/cvicseServicesRestService/selectInspectorInfoByUUID")
    Observable<ResponseResult<List<SecurityInspectorInformation>>> getInspectorInfo(@Field("parameterJson") String parameterJson);

    /**
     * 获取复查船信息
     */
    @FormUrlEncoded
    @POST("sz/taskInfoSZRestService/getShipData")
    Observable<ResponseResult<TaskInfoDetails>> getShipData(@Field("parameterJson") String parameterJson);
}
