package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipBaseInfo.java
 * Date: 2017/12/21 14:40
 * Description:
 */

public class ShipBaseInfo implements Serializable {

    private String certificate_flag;
    private String mmsi;
    private String nation_code;
    private String org_code;
    private String orig_regport_name;
    private String orig_ship_name_cn;
    private String orig_ship_name_en;
    private String orig_ship_reg_no;
    private String regport_code;
    private String Regport_name;

    public String getRegport_name() {
        return Regport_name;
    }

    public void setRegport_name(String Regport_name) {
        this.Regport_name = Regport_name;
    }

    private String remark;
    private BigDecimal rescue_equipment_num;
    private BigDecimal ship_breadth;
    private String ship_built_addr_cn;
    private String ship_built_addr_en;
    private String ship_built_date;
    private String ship_callsign;
    private String ship_card_no;
    private String ship_class;
    private BigDecimal ship_container_num;
    private BigDecimal ship_depth;
    private BigDecimal ship_dwt;
    private BigDecimal ship_engine_num;
    private BigDecimal ship_engine_power;
    private String ship_engine_type_code;
    private String ship_firstreg_no;
    private BigDecimal ship_grosston;
    private String ship_hull_material_code;
    private String ship_id;
    private String ship_imo;
    private String ship_inspect_no;
    private BigDecimal ship_length;
    private String ship_manager;
    private BigDecimal ship_minimum_freeboard;
    private String ship_name_cn;
    private String ship_name_en;
    private String ship_netton;
    private String ship_no;
    private String ship_operator;
    private String ship_owner;
    private BigDecimal ship_parking_num;
    private BigDecimal ship_passenger_num;
    private String ship_propeller_kind_code;
    private BigDecimal ship_propeller_num;
    private String ship_rebuilt_addr_cn;
    private String ship_rebuilt_addr_en;
    private String ship_rebuilt_date;
    private String ship_reg_no;
    private String ship_region_type;
    private String ship_route_code;
    private BigDecimal ship_speed;
    private BigDecimal ship_summer_draft;
    private String ship_type_code;
    private String ship_value;
    private String ship_wind_level;
    private String shipyard_cn;
    private String shipyard_en;


    public String getCertificate_flag() {
        switch (certificate_flag) {
            case "1":
                return "1、船舶国籍证书";
            case "2":
                return "2、船舶检验证书";
            case "3":
                return "无线电牌照";
            default:
                return "没有经过人工核对";

        }
    }

    public void setCertificate_flag(String certificate_flag) {
        this.certificate_flag = certificate_flag;
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

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getOrig_regport_name() {
        return orig_regport_name;
    }

    public void setOrig_regport_name(String orig_regport_name) {
        this.orig_regport_name = orig_regport_name;
    }

    public String getOrig_ship_name_cn() {
        return orig_ship_name_cn;
    }

    public void setOrig_ship_name_cn(String orig_ship_name_cn) {
        this.orig_ship_name_cn = orig_ship_name_cn;
    }

    public String getOrig_ship_name_en() {
        return orig_ship_name_en;
    }

    public void setOrig_ship_name_en(String orig_ship_name_en) {
        this.orig_ship_name_en = orig_ship_name_en;
    }

    public String getOrig_ship_reg_no() {
        return orig_ship_reg_no;
    }

    public void setOrig_ship_reg_no(String orig_ship_reg_no) {
        this.orig_ship_reg_no = orig_ship_reg_no;
    }

    public String getRegport_code() {
        return regport_code;
    }

    public void setRegport_code(String regport_code) {
        this.regport_code = regport_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getRescue_equipment_num() {
        return rescue_equipment_num;
    }

    public void setRescue_equipment_num(BigDecimal rescue_equipment_num) {
        this.rescue_equipment_num = rescue_equipment_num;
    }

    public BigDecimal getShip_breadth() {
        return ship_breadth;
    }

    public void setShip_breadth(BigDecimal ship_breadth) {
        this.ship_breadth = ship_breadth;
    }

    public String getShip_built_addr_cn() {
        return ship_built_addr_cn;
    }

    public void setShip_built_addr_cn(String ship_built_addr_cn) {
        this.ship_built_addr_cn = ship_built_addr_cn;
    }

    public String getShip_built_addr_en() {
        return ship_built_addr_en;
    }

    public void setShip_built_addr_en(String ship_built_addr_en) {
        this.ship_built_addr_en = ship_built_addr_en;
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

    public String getShip_card_no() {
        return ship_card_no;
    }

    public void setShip_card_no(String ship_card_no) {
        this.ship_card_no = ship_card_no;
    }

    public String getShip_class() {
        switch (ship_class) {
            case "A":
                return "国内";
            case "B":
                return "外国";
            case "C":
                return "港澳";
            case "D":
                return "台湾";
            default:
                return ship_class;
        }
    }

    public void setShip_class(String ship_class) {
        this.ship_class = ship_class;
    }

    public BigDecimal getShip_container_num() {
        return ship_container_num;
    }

    public void setShip_container_num(BigDecimal ship_container_num) {
        this.ship_container_num = ship_container_num;
    }

    public BigDecimal getShip_depth() {
        return ship_depth;
    }

    public void setShip_depth(BigDecimal ship_depth) {
        this.ship_depth = ship_depth;
    }

    public BigDecimal getShip_dwt() {
        return ship_dwt;
    }

    public void setShip_dwt(BigDecimal ship_dwt) {
        this.ship_dwt = ship_dwt;
    }

    public BigDecimal getShip_engine_num() {
        return ship_engine_num;
    }

    public void setShip_engine_num(BigDecimal ship_engine_num) {
        this.ship_engine_num = ship_engine_num;
    }

    public BigDecimal getShip_engine_power() {
        return ship_engine_power;
    }

    public void setShip_engine_power(BigDecimal ship_engine_power) {
        this.ship_engine_power = ship_engine_power;
    }

    public String getShip_engine_type_code() {
        switch (ship_engine_type_code) {
            case "01":
                return "内燃机";
            case "02":
                return "汽轮机";
            case "03":
                return "蒸汽机";
            case "04":
                return "其他";
            default:
                return ship_engine_type_code;
        }
    }

    public void setShip_engine_type_code(String ship_engine_type_code) {
        this.ship_engine_type_code = ship_engine_type_code;
    }

    public String getShip_firstreg_no() {
        return ship_firstreg_no;
    }

    public void setShip_firstreg_no(String ship_firstreg_no) {
        this.ship_firstreg_no = ship_firstreg_no;
    }

    public BigDecimal getShip_grosston() {
        return ship_grosston;
    }

    public void setShip_grosston(BigDecimal ship_grosston) {
        this.ship_grosston = ship_grosston;
    }

    public String getShip_hull_material_code() {
        switch (ship_hull_material_code) {
            case "01":
                return "钢质";
            case "02":
                return "木质";
            case "03":
                return "水泥";
            case "04":
                return "铝合金";
            case "05":
                return "玻璃钢";
            case "06":
                return "橡胶";
            case "07":
                return "增强纤维";
            default:
                return ship_hull_material_code;
        }
    }

    public void setShip_hull_material_code(String ship_hull_material_code) {
        this.ship_hull_material_code = ship_hull_material_code;
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

    public String getShip_inspect_no() {
        return ship_inspect_no;
    }

    public void setShip_inspect_no(String ship_inspect_no) {
        this.ship_inspect_no = ship_inspect_no;
    }

    public BigDecimal getShip_length() {
        return ship_length;
    }

    public void setShip_length(BigDecimal ship_length) {
        this.ship_length = ship_length;
    }

    public String getShip_manager() {
        return ship_manager;
    }

    public void setShip_manager(String ship_manager) {
        this.ship_manager = ship_manager;
    }

    public BigDecimal getShip_minimum_freeboard() {
        return ship_minimum_freeboard;
    }

    public void setShip_minimum_freeboard(BigDecimal ship_minimum_freeboard) {
        this.ship_minimum_freeboard = ship_minimum_freeboard;
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

    public String getShip_netton() {
        return ship_netton;
    }

    public void setShip_netton(String ship_netton) {
        this.ship_netton = ship_netton;
    }

    public String getShip_no() {
        return ship_no;
    }

    public void setShip_no(String ship_no) {
        this.ship_no = ship_no;
    }

    public String getShip_operator() {
        return ship_operator;
    }

    public void setShip_operator(String ship_operator) {
        this.ship_operator = ship_operator;
    }

    public String getShip_owner() {
        return ship_owner;
    }

    public void setShip_owner(String ship_owner) {
        this.ship_owner = ship_owner;
    }

    public BigDecimal getShip_parking_num() {
        return ship_parking_num;
    }

    public void setShip_parking_num(BigDecimal ship_parking_num) {
        this.ship_parking_num = ship_parking_num;
    }

    public BigDecimal getShip_passenger_num() {
        return ship_passenger_num;
    }

    public void setShip_passenger_num(BigDecimal ship_passenger_num) {
        this.ship_passenger_num = ship_passenger_num;
    }

    public String getShip_propeller_kind_code() {
        switch (ship_propeller_kind_code) {
            case "01":
                return "螺旋桨";
            case "02":
                return "喷水";
            case "03":
                return "空气动力";
            case "04":
                return "其它";
            default:
                return ship_propeller_kind_code;
        }
    }

    public void setShip_propeller_kind_code(String ship_propeller_kind_code) {
        this.ship_propeller_kind_code = ship_propeller_kind_code;
    }

    public BigDecimal getShip_propeller_num() {
        return ship_propeller_num;
    }

    public void setShip_propeller_num(BigDecimal ship_propeller_num) {
        this.ship_propeller_num = ship_propeller_num;
    }

    public String getShip_rebuilt_addr_cn() {
        return ship_rebuilt_addr_cn;
    }

    public void setShip_rebuilt_addr_cn(String ship_rebuilt_addr_cn) {
        this.ship_rebuilt_addr_cn = ship_rebuilt_addr_cn;
    }

    public String getShip_rebuilt_addr_en() {
        return ship_rebuilt_addr_en;
    }

    public void setShip_rebuilt_addr_en(String ship_rebuilt_addr_en) {
        this.ship_rebuilt_addr_en = ship_rebuilt_addr_en;
    }

    public String getShip_rebuilt_date() {
        return ship_rebuilt_date;
    }

    public void setShip_rebuilt_date(String ship_rebuilt_date) {
        this.ship_rebuilt_date = ship_rebuilt_date;
    }

    public String getShip_reg_no() {
        return ship_reg_no;
    }

    public void setShip_reg_no(String ship_reg_no) {
        this.ship_reg_no = ship_reg_no;
    }

    public String getShip_region_type() {
        return ship_region_type;
    }

    public void setShip_region_type(String ship_region_type) {
        this.ship_region_type = ship_region_type;
    }

    public String getShip_route_code() {
        switch (ship_route_code) {
            case "1":
                return "国内";
            case "2":
                return "国际";
            case "3":
                return "港澳";
            default:
                return ship_route_code;
        }
    }

    public void setShip_route_code(String ship_route_code) {
        this.ship_route_code = ship_route_code;
    }

    public BigDecimal getShip_speed() {
        return ship_speed;
    }

    public void setShip_speed(BigDecimal ship_speed) {
        this.ship_speed = ship_speed;
    }

    public BigDecimal getShip_summer_draft() {
        return ship_summer_draft;
    }

    public void setShip_summer_draft(BigDecimal ship_summer_draft) {
        this.ship_summer_draft = ship_summer_draft;
    }

    public String getShip_type_code() {
        return ship_type_code;
    }

    public void setShip_type_code(String ship_type_code) {
        this.ship_type_code = ship_type_code;
    }

    public String getShip_value() {
        return ship_value;
    }

    public void setShip_value(String ship_value) {
        this.ship_value = ship_value;
    }

    public String getShip_wind_level() {
        return ship_wind_level;
    }

    public void setShip_wind_level(String ship_wind_level) {
        this.ship_wind_level = ship_wind_level;
    }

    public String getShipyard_cn() {
        return shipyard_cn;
    }

    public void setShipyard_cn(String shipyard_cn) {
        this.shipyard_cn = shipyard_cn;
    }

    public String getShipyard_en() {
        return shipyard_en;
    }

    public void setShipyard_en(String shipyard_en) {
        this.shipyard_en = shipyard_en;
    }
}
