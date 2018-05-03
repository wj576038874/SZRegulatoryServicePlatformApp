package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SiteSupervisionReport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String inspect_no;
	private String busi_no;
	private String task_id;
	private String voyage_id;
	private String ship_no;
	private String ship_id;
	private String ship_name_cn;
	private String ship_operator;
	private String ship_operator_addr;
	private String ship_operator_fax;
	private String ship_manager;
	private String ship_manager_addr;
	private String ship_manager_fax;
	private String captain_name;
	private String inspect_org;
	private String berth_code;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date inspect_date;
	private String inspector_code;
	private String inspector_name;
	private String inspect_time_limit;
	private String reason;
	private String if_problem;
	private String remark;
	private String org_code;
	private String spot_busi_no;
	private String priority_order;
	private String risk_attribute;
	private String pratice_inspector_code;
	private String pratice_inspector_name;
	private String is_special_inspect;
	private String inspect_type;
	private String if_safeinspect;
	private String initial_inspect_mark;
	private String initial_inspect_no;
	private int defect_num;
	private String correct_mark;
	private int unclosed_num;

	public String getInspect_no() {
		return inspect_no;
	}

	public void setInspect_no(String inspect_no) {
		this.inspect_no = inspect_no;
	}

	public String getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getVoyage_id() {
		return voyage_id;
	}

	public void setVoyage_id(String voyage_id) {
		this.voyage_id = voyage_id;
	}

	public String getShip_no() {
		return ship_no;
	}

	public void setShip_no(String ship_no) {
		this.ship_no = ship_no;
	}

	public String getShip_id() {
		return ship_id;
	}

	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}

	public String getShip_name_cn() {
		return ship_name_cn;
	}

	public void setShip_name_cn(String ship_name_cn) {
		this.ship_name_cn = ship_name_cn;
	}

	public String getShip_operator() {
		return ship_operator;
	}

	public void setShip_operator(String ship_operator) {
		this.ship_operator = ship_operator;
	}

	public String getShip_operator_addr() {
		return ship_operator_addr;
	}

	public void setShip_operator_addr(String ship_operator_addr) {
		this.ship_operator_addr = ship_operator_addr;
	}

	public String getShip_operator_fax() {
		return ship_operator_fax;
	}

	public void setShip_operator_fax(String ship_operator_fax) {
		this.ship_operator_fax = ship_operator_fax;
	}

	public String getShip_manager() {
		return ship_manager;
	}

	public void setShip_manager(String ship_manager) {
		this.ship_manager = ship_manager;
	}

	public String getShip_manager_addr() {
		return ship_manager_addr;
	}

	public void setShip_manager_addr(String ship_manager_addr) {
		this.ship_manager_addr = ship_manager_addr;
	}

	public String getShip_manager_fax() {
		return ship_manager_fax;
	}

	public void setShip_manager_fax(String ship_manager_fax) {
		this.ship_manager_fax = ship_manager_fax;
	}

	public String getCaptain_name() {
		return captain_name;
	}

	public void setCaptain_name(String captain_name) {
		this.captain_name = captain_name;
	}

	public String getInspect_org() {
		return inspect_org;
	}

	public void setInspect_org(String inspect_org) {
		this.inspect_org = inspect_org;
	}

	public String getBerth_code() {
		return berth_code;
	}

	public void setBerth_code(String berth_code) {
		this.berth_code = berth_code;
	}


	public Date getInspect_date() {
		return inspect_date;
	}

	public void setInspect_date(Date inspect_date) {
		this.inspect_date = inspect_date;
	}

	public String getInspector_code() {
		return inspector_code;
	}

	public void setInspector_code(String inspector_code) {
		this.inspector_code = inspector_code;
	}

	public String getInspector_name() {
		return inspector_name;
	}

	public void setInspector_name(String inspector_name) {
		this.inspector_name = inspector_name;
	}

	public String getInspect_time_limit() {
		return inspect_time_limit;
	}

	public void setInspect_time_limit(String inspect_time_limit) {
		this.inspect_time_limit = inspect_time_limit;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIf_problem() {
		return if_problem;
	}

	public void setIf_problem(String if_problem) {
		this.if_problem = if_problem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getSpot_busi_no() {
		return spot_busi_no;
	}

	public void setSpot_busi_no(String spot_busi_no) {
		this.spot_busi_no = spot_busi_no;
	}

	public String getPriority_order() {
		return priority_order;
	}

	public void setPriority_order(String priority_order) {
		this.priority_order = priority_order;
	}

	public String getRisk_attribute() {
		return risk_attribute;
	}

	public void setRisk_attribute(String risk_attribute) {
		this.risk_attribute = risk_attribute;
	}

	public String getPratice_inspector_code() {
		return pratice_inspector_code;
	}

	public void setPratice_inspector_code(String pratice_inspector_code) {
		this.pratice_inspector_code = pratice_inspector_code;
	}

	public String getPratice_inspector_name() {
		return pratice_inspector_name;
	}

	public void setPratice_inspector_name(String pratice_inspector_name) {
		this.pratice_inspector_name = pratice_inspector_name;
	}

	public String getIs_special_inspect() {
		return is_special_inspect;
	}

	public void setIs_special_inspect(String is_special_inspect) {
		this.is_special_inspect = is_special_inspect;
	}

	public String getInspect_type() {
		return inspect_type;
	}

	public void setInspect_type(String inspect_type) {
		this.inspect_type = inspect_type;
	}

	public String getIf_safeinspect() {
		return if_safeinspect;
	}

	public void setIf_safeinspect(String if_safeinspect) {
		this.if_safeinspect = if_safeinspect;
	}

	public String getInitial_inspect_mark() {
		return initial_inspect_mark;
	}

	public void setInitial_inspect_mark(String initial_inspect_mark) {
		this.initial_inspect_mark = initial_inspect_mark;
	}

	public String getInitial_inspect_no() {
		return initial_inspect_no;
	}

	public void setInitial_inspect_no(String initial_inspect_no) {
		this.initial_inspect_no = initial_inspect_no;
	}

	public int getDefect_num() {
		return defect_num;
	}

	public void setDefect_num(int defect_num) {
		this.defect_num = defect_num;
	}

	public String getCorrect_mark() {
		return correct_mark;
	}

	public void setCorrect_mark(String correct_mark) {
		this.correct_mark = correct_mark;
	}

	public int getUnclosed_num() {
		return unclosed_num;
	}

	public void setUnclosed_num(int unclosed_num) {
		this.unclosed_num = unclosed_num;
	}

}
