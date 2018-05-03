package com.winfo.szrsp.app.entity.request;

/**
 * 四个自动站和城乡天气接口调用请求模型
 *
 * @author zhufeng
 * @version 2016年3月1日
 */
public class WeatherRequestModel {

    private String starttime;
    private String endtime;
    private String check;
    private String cityname;

    /**
     * 站号
     */
    private String zh;
    /**
     * 开始时间
     */
    private String kssj;
    /**
     * 结束时间
     */
    private String jssj;
    /**
     * 区县
     */
    private String qx;
    /**
     * 城市
     */
    private String cs;
    /**
     * 省份
     */
    private String sf;
    /**
     * 从第几个开始查询
     */
    private String start;
    /**
     * 查询数据总数
     */
    private String rows;

    /**
     * 区域名称
     */
    private String qymc;

    /**
     * 发布时间
     */
    private String fbsj;

    /**
     * 预报地址
     */
    private String ybdz;

    /**
     * 发布者
     */
    private String fbz;


    /**
     * 左下角
     */
    private Double startLatitude;
    private Double startLongitude;
    /**
     * 右上角
     */
    private Double endLatitude;
    private Double endLongitude;

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getFbsj() {
        return fbsj;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getYbdz() {
        return ybdz;
    }

    public void setYbdz(String ybdz) {
        this.ybdz = ybdz;
    }

    public String getFbz() {
        return fbz;
    }

    public void setFbz(String fbz) {
        this.fbz = fbz;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(Double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public Double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(Double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }


}
