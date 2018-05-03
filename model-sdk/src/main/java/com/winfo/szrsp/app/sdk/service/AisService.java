package com.winfo.szrsp.app.sdk.service;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.sdk.entity.zhifa.MobileTerminalInfo2;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ChengQi on 2017/12/13.
 */

public interface AisService {

    /**
     * 加载Ais数据（根据新接口修改）
     */
    @FormUrlEncoded
    @POST("http://219.133.95.9:8001/AisVesselTrack")
//    @POST("http://191.254.1.74:8002/AisVesselTrack")
    Observable<List<Ais>> loadAisData(@Field("TYPE") String TYPE, @Field("BOX") String BOX, @Field("Check") boolean Check);

    /**
     * 根据mmsi加载随船电话
     */
    @FormUrlEncoded
    @POST("sz/fileUpload/selectPhoneByMmsi")
    Observable<ResponseResult<TelephoneMmsi>> selectPhoneByMmsi(@Field("parameterJson") String parameterJson);

    /**
     * 加载所有执法船包括关机数据
     */
    @FormUrlEncoded
    @POST("http://219.133.95.9:8001/AisVesselTrack?TYPE=AISFewShip")
//    @POST("http://191.254.1.74:8002/AisVesselTrack?TYPE=AISFewShip")
    Observable<List<Ais>> loadZhifaData(@Field("MMSI") String mmsi, @Field("Check") boolean Check);


    /**
     * 获取所有海巡船的mmsi数据
     */
    @FormUrlEncoded
    @POST("sz/lawEnforcementShipRestService/getDataMmsi")
    Observable<ResponseResult<String>> loadHaiXunMmsiList(@Field("parameterJson") String parameterJson);

    /**
     * 加载通航要素
     *
     * @param parameterJson 请求参数
     * @return 集合
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/thysRestService/getAll")
    Observable<ResponseResult<List<NavigableElementsData>>> getThysData(@Field("parameterJson") String parameterJson);


    /**
     * 加载执法人员
     *
     * @param parameterJson 请求参数
     * @return 集合
     */
    @FormUrlEncoded   //如果表单post提交这个注解一定要加上
    @POST("sz/mobileTerminalInfoRestService/selectAllByOrg")
    Observable<ResponseResult<List<MobileTerminalInfo2>>> getZhifaPersonData(@Field("parameterJson") String parameterJson);

    /**
     * 修改电话
     */
    @FormUrlEncoded
    @POST("sz/fileUpload/insertOrUpdateMmsiPhone")
    Observable<ResponseResult<String>> insertOrUpdateMmsiPhone(@Field("parameterJson") String parameterJson);
}
