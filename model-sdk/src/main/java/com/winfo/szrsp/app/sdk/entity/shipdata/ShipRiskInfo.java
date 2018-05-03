package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipRiskInfo.java
 * Date: 2018/1/13 17:30
 * Description:选船风险值实体类
 */

public class ShipRiskInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 过去36个月的船舶事故数量
     */
    private String accident_num;

    /**
     * 航运公司绩效
     */
    private String company_performance;

    /**
     * 航运公司绩效权值
     */
    private String company_value;

    /**
     *过去36个月的缺陷平均数
     */
    private String defect_average_num;

    /**
     *缺陷权值
     */
    private String defect_value;

    /**
     *过去36个月的滞留次数
     */
    private String detention_num;

    /**
     *滞留权值
     */
    private String detention_value;

    /**
     *综合展示顺序
     */
    private String general_order;

    /**
     *是否部局指定类型船舶
     */
    private String is_design;

    /**
     *是否协查船舶
     */
    private String is_investigate;

    /**
     *是否实名举报船舶
     */
    private String is_report;

    /**
     *是否重点跟踪船舶
     */
    private String is_tracking_ship;

    /**
     *FSC上次检查日期
     */
    private String last_inspect_date;

    /**
     *现场监督上次检查日期
     */
    private String last_inspect_date_spot;

    /**
     * mmsi
     */
    private String mmsi;

    /**
     *国籍代码
     */
    private String nation_code;

    /**
     *过去36个月的行政处罚数量
     */
    private String penalty_num;

    /**
     *FSC优先顺序
     */
    private String priority_order;

    /**
     *现场监督优先顺序
     */
    private String priority_order_spot;

    /**
     *FSC船舶风险属性
     */
    private String risk_attribute;

    /**
     *现场监督船舶风险属性
     */
    private String risk_attribute_spot;

    /**
     *船龄权值
     */
    private String ship_age_value;

    /**
     *建成日期
     */
    private String ship_built_date;

    /**
     * 船舶呼号
     */
    private String ship_callsign;

    /**
     * 船舶识别号
     */
    private String ship_id;

    /**
     * imo
     */
    private String ship_imo;

    /**
     *船检机构绩效
     */
    private String ship_inspect_org_performance;

    /**
     *船检机构绩效权值
     */
    private String ship_inspect_org_value;

    /**
     * 中文船名
     */
    private String ship_name_cn;

    /**
     * 英文船名
     */
    private String ship_name_en;

    /**
     * 船舶编号
     */
    private String ship_no;

    private int grade;

    /**
     * 海船内河船标志
     */
    private String ship_region_type;

    /**
     * 船舶种类代码
     */
    private String ship_type_code;

    /**
     *船型权值
     */
    private String ship_type_value;

    /**
     *权重值之和
     */
    private String total_value;

    /**
     *重点跟踪权值
     */
    private String tracking_ship_value;

    public String getAccident_num() {
        return accident_num;
    }

    public void setAccident_num(String accident_num) {
        this.accident_num = accident_num;
    }

    public String getCompany_performance() {
        return company_performance;
    }

    public void setCompany_performance(String company_performance) {
        this.company_performance = company_performance;
    }

    public String getCompany_value() {
        return company_value;
    }

    public void setCompany_value(String company_value) {
        this.company_value = company_value;
    }

    public String getDefect_average_num() {
        return defect_average_num;
    }

    public void setDefect_average_num(String defect_average_num) {
        this.defect_average_num = defect_average_num;
    }

    public String getDefect_value() {
        return defect_value;
    }

    public void setDefect_value(String defect_value) {
        this.defect_value = defect_value;
    }

    public String getDetention_num() {
        return detention_num;
    }

    public void setDetention_num(String detention_num) {
        this.detention_num = detention_num;
    }

    public String getDetention_value() {
        return detention_value;
    }

    public void setDetention_value(String detention_value) {
        this.detention_value = detention_value;
    }

    public String getGeneral_order() {
        return general_order;
    }

    public void setGeneral_order(String general_order) {
        this.general_order = general_order;
    }

    public String getIs_design() {
        return is_design;
    }

    public void setIs_design(String is_design) {
        this.is_design = is_design;
    }

    public String getIs_investigate() {
        return is_investigate;
    }

    public void setIs_investigate(String is_investigate) {
        this.is_investigate = is_investigate;
    }

    public String getIs_report() {
        return is_report;
    }

    public void setIs_report(String is_report) {
        this.is_report = is_report;
    }

    public String getIs_tracking_ship() {
        return is_tracking_ship;
    }

    public void setIs_tracking_ship(String is_tracking_ship) {
        this.is_tracking_ship = is_tracking_ship;
    }

    public String getLast_inspect_date() {
        return last_inspect_date;
    }

    public void setLast_inspect_date(String last_inspect_date) {
        this.last_inspect_date = last_inspect_date;
    }

    public String getLast_inspect_date_spot() {
        return last_inspect_date_spot;
    }

    public void setLast_inspect_date_spot(String last_inspect_date_spot) {
        this.last_inspect_date_spot = last_inspect_date_spot;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getNation_code() {
        return nation_code;
    }

    public void setNation_code(String nation_code) {
        this.nation_code = nation_code;
    }

    public String getPenalty_num() {
        return penalty_num;
    }

    public void setPenalty_num(String penalty_num) {
        this.penalty_num = penalty_num;
    }

    public String getPriority_order() {
        return priority_order;
    }

    public void setPriority_order(String priority_order) {
        this.priority_order = priority_order;
    }

    public String getPriority_order_spot() {
        return priority_order_spot;
    }

    public void setPriority_order_spot(String priority_order_spot) {
        this.priority_order_spot = priority_order_spot;
    }

    public String getRisk_attribute() {
        return risk_attribute;
    }

    public void setRisk_attribute(String risk_attribute) {
        this.risk_attribute = risk_attribute;
    }

    public String getRisk_attribute_spot() {
        return risk_attribute_spot;
    }

    public void setRisk_attribute_spot(String risk_attribute_spot) {
        this.risk_attribute_spot = risk_attribute_spot;
    }

    public String getShip_age_value() {
        return ship_age_value;
    }

    public void setShip_age_value(String ship_age_value) {
        this.ship_age_value = ship_age_value;
    }

    public String getShip_built_date() {
        return ship_built_date;
    }

    public void setShip_built_date(String ship_built_date) {
        this.ship_built_date = ship_built_date;
    }

    public String getShip_callsign() {
        return ship_callsign;
    }

    public void setShip_callsign(String ship_callsign) {
        this.ship_callsign = ship_callsign;
    }

    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_imo() {
        return ship_imo;
    }

    public void setShip_imo(String ship_imo) {
        this.ship_imo = ship_imo;
    }

    public String getShip_inspect_org_performance() {
        return ship_inspect_org_performance;
    }

    public void setShip_inspect_org_performance(String ship_inspect_org_performance) {
        this.ship_inspect_org_performance = ship_inspect_org_performance;
    }

    public String getShip_inspect_org_value() {
        return ship_inspect_org_value;
    }

    public void setShip_inspect_org_value(String ship_inspect_org_value) {
        this.ship_inspect_org_value = ship_inspect_org_value;
    }

    public String getShip_name_cn() {
        return ship_name_cn;
    }

    public void setShip_name_cn(String ship_name_cn) {
        this.ship_name_cn = ship_name_cn;
    }

    public String getShip_name_en() {
        return ship_name_en;
    }

    public void setShip_name_en(String ship_name_en) {
        this.ship_name_en = ship_name_en;
    }

    public String getShip_no() {
        return ship_no;
    }

    public void setShip_no(String ship_no) {
        this.ship_no = ship_no;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getShip_region_type() {
        return ship_region_type;
    }

    public void setShip_region_type(String ship_region_type) {
        this.ship_region_type = ship_region_type;
    }

    public String getShip_type_code() {
        return ship_type_code;
    }

    public void setShip_type_code(String ship_type_code) {
        this.ship_type_code = ship_type_code;
    }

    public String getShip_type_value() {
        return ship_type_value;
    }

    public void setShip_type_value(String ship_type_value) {
        this.ship_type_value = ship_type_value;
    }

    public String getTotal_value() {
        return total_value;
    }

    public void setTotal_value(String total_value) {
        this.total_value = total_value;
    }

    public String getTracking_ship_value() {
        return tracking_ship_value;
    }

    public void setTracking_ship_value(String tracking_ship_value) {
        this.tracking_ship_value = tracking_ship_value;
    }
}
