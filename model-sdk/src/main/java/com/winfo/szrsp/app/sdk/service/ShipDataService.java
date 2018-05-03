package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShipList;
import com.winfo.szrsp.app.sdk.entity.shipdata.ObjFlagStateControl;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipCompanyInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomation;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfomationNew;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipRiskInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSecurityCheckInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipXianChangInfo;
import com.winfo.szrsp.app.sdk.entity.shipdata.StateControlData;
import com.winfo.szrsp.app.sdk.entity.shipdata.TrackData;
import com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoShiShip;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.service
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.service.ShipDataService.java
 * Date: 2017/11/25 13:17
 * Description: 船舶信息接口
 */

public interface ShipDataService {


    /**
     * 根据中文和根据英文来查询
     *
     * @param TYPE 参数
     * @return 数据
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("http://219.133.95.9:8001/AisVesselTrack")
//    @POST("http://191.254.1.74:8002/AisVesselTrack")
    Observable<List<Ais>> searShipByMMSI(@Field("TYPE") String TYPE, @Field("MMSI") String MMSI, @Field("Check") boolean Check);


    /**
     * 根据中文和根据英文来查询
     *
     * @param parameterJson 参数
     * @return 数据
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/shipSearch/text")
    Observable<ResponseResult<List<ShipSearch>>> searchShipByEnNameOrCnName(@Field("parameterJson") String parameterJson);

    /**
     * 根据mmsi获取船舶信息数据
     *
     * @param parameterJson 查询json数据参数 {"mmsi":"412271090"}
     * @return 船舶信息
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/shipBaseDateSZRestService/selectByMmsi")
    Observable<ResponseResult<ShipInfo>> getShipInformationByMMSI(@Field("parameterJson") String parameterJson);


    /**
     * 中创 查询基本信息接口
     *
     * @param PublicType 查询类型
     * @param Check      check
     * @param Jsonstr    参数
     * @return 基本信息
     */
    @GET("http://219.133.95.9:8001/AisVesselTrack")
//    @GET("http://191.254.1.74:8002/AisVesselTrack")
    Observable<ShipInfomation> getShipinfo(@Query("PublicType") String PublicType, @Query("Check") String Check, @Query("Jsonstr") String Jsonstr);

    /**
     * 中创 查询基本信息接口新
     *
     * @param
     * @return
     */
    @GET("http://219.133.95.9:8001/AisVesselTrack")
//    @GET("http://191.254.1.74:8002/AisVesselTrack")
    Observable<List<ShipInfomationNew>> getShipinfoNew(@Query("PublicType") String PublicType, @Query("Check") String Check, @Query("Jsonstr") String Jsonstr);

    /**
     * 根据mmsi和英文船名查询安检数据
     *
     * @param parameterJson 请求数据 {"mmsi":"412472430","ywcm":""}
     * @return 安检列表集合
     */
//    @FormUrlEncoded
//    @POST("sz/firstCloudShipFscRestService/selectByMmsiOrYwcm")
//    Observable<ResponseResult<List<ShipSecurityCheckInfo>>> getSecurityCheckByMmsiOrYwcm(@Field("parameterJson") String parameterJson);
    @FormUrlEncoded
    @POST("sz/cvicseServicesRestService/flagStateControlQuery")
    Observable<ResponseResult<StateControlData>> getSecurityCheckByMmsiOrYwcm(@Field("parameterJson") String parameterJson);

    /**
     * 根据mmsi和英文船名查询现场监督
     */
    @FormUrlEncoded
    @POST("sz/cvicseServicesRestService/siteSupervisionQuery")
    Observable<ResponseResult<ShipXianChangInfo>> getSupervisionByMmsiOrYwcm(@Field("parameterJson") String parameterJson);

    /**
     * 根据mmsi和英文船名查询船公司信息
     *
     * @param parameterJson 中文简称和英文简称 {"mmsi":"安徽省","ywcm":""}  zwjc为空则全查
     * @return 船公司信息集合
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/firstCloudShipCompanyDataRestService/selectByMmsiOrYwcm")
    Observable<ResponseResult<List<ShipCompanyInfo>>> getShipCompanyInformationByMmsiOrYwcm(@Field("parameterJson") String parameterJson);

    /**
     * 根据查询条件模糊查询船舶登记信息  英文船名不能为空
     *
     * @param parameterJson 条件 {"ywcm":"JIN SHI SI SHI JIU HAO"}	英文船名不可为空，其他可选
     *                      船舶主键：cbzj
     *                      船舶登记号：cbdjh
     *                      船舶识别号：cbsbh
     *                      初次登记号：ccdjh
     *                      船检登记号：cjdjh
     *                      中文船名：zwcm
     *                      英文船名：ywcm
     *                      MMSI：mmsi
     *                      IMO：imo
     *                      船舶呼号：hh
     * @return 船舶登记信息集合
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/firstCloudShipRegistrationRestService/selectByConditions")
    Observable<ResponseResult<List<ShipRegistrationInfo>>> getShipRegistrationInformationByConditions(@Field("parameterJson") String parameterJson);


    /**
     * 根据mmsi和英文 船名查询船员信息
     *
     * @param parameterJson 请求桉树
     * @return 船员列表
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/firstClucleCrewMessageRestService/selectByCbzgOrCbsbh")
    Observable<ResponseResult<List<CrewInfo>>> getCrewInfoByMmsiOrYwcm(@Field("parameterJson") String parameterJson);


    /**
     * 根据身份证查询船员信息
     *
     * @param parameterJson 身份证 {"sfzhm":"350211197510080074"}
     * @return 船员信息
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("FirstCludeCrewMessageRestService/selectBySfzhm")
    Observable<ResponseResult<CrewInfo>> getCrewInformationBySfzhm(@Field("parameterJson") String parameterJson);

    /**
     * 根据通航要素查询通航要素
     *
     * @param parameterJson
     * @return
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/thysRestService/searchThysByName")
    Observable<ResponseResult<List<NavigableElementsData>>> getNavigableElementsData(@Field("parameterJson") String parameterJson);

    /**
     * 查询异常船舶列表
     *
     * @param parameterJson
     * @return 异常船舶列表
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/exceptionShipRestService/selectByAllPage")
    Observable<ResponseResult<ExceptionShipList>> getExceptionShipList(@Field("parameterJson") String parameterJson);


    /**
     * 添加异常船舶
     *
     * @param parameterJson
     * @return 添加异常船舶
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/exceptionShipRestService/insertSelective")
    Observable<ResponseResult<String>> addExceptionShip(@Field("parameterJson") String parameterJson);

    /**
     * 删除异常船舶
     *
     * @param parameterJson
     * @return 删除异常船舶
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/exceptionShipRestService/deleteByMmsi")
    Observable<ResponseResult<String>> deleteExceptionShip(@Field("parameterJson") String parameterJson);


    /**
     * 查询是否为异常船舶
     *
     * @param parameterJson
     * @return 查询是否为异常船舶
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/exceptionShipRestService/selectByMmsi")
    Observable<ResponseResult<ExceptionShip>> queryIsExceptionShip(@Field("parameterJson") String parameterJson);


    /**
     * 根据英文船名和mmsi查询劳氏数据
     *
     * @param parameterJson 请求参数
     * @return 劳氏数据
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/laoShiShipRestService/selectByShipNameAndMmsi")
    Observable<ResponseResult<LaoShiShip>> getShipLoadsInfoByMmsiOrYwcm(@Field("parameterJson") String parameterJson);

    /**
     * 查询选船风险值
     *
     * @param PublicType SZZC
     * @param Check      shipRiskInfo
     * @param Jsonstr    {"arg0":{"ship_id":"","ship_name_cn":"鹏星18","ship_name_en":"","mmsi":"","ship_imo":""}}
     * @return 数据
     */
    @FormUrlEncoded
    @POST("http://219.133.95.9:8001/AisVesselTrack")
//    @POST("http://191.254.1.74:8002/AisVesselTrack")
    Observable<ShipRiskInfo> getShipSelectionRisk(@Field("PublicType") String PublicType, @Field("Check") String Check, @Field("Jsonstr") String Jsonstr);


    //http://219.133.95.9:8001/AisVesselTrack
    // ?TYPE=SingleShip&MMSI=413826973&SJBEGIN=2017-11-09%2013:36:42&SJEND=2017-12-04%2000:18:02&LEVEL=0

    /**
     * 单船轨迹查询
     *
     * @param type      SingleShip单船查询
     * @param mmsi      mmsi
     * @param startTime 开始时间
     * @param stopTime  结束时间
     * @param level     抽稀参数
     * @return
     */
    @FormUrlEncoded
    @POST("http://219.133.95.9:8001/AisVesselTrack")
//    @POST("http://191.254.1.74:8002/AisVesselTrack")
    Observable<List<TrackData>> getShipTrackByMMSI(@Field("TYPE") String type, @Field("MMSI") String mmsi, @Field("SJBEGIN") String startTime, @Field("SJEND") String stopTime, @Field("LEVEL") String level);


}
