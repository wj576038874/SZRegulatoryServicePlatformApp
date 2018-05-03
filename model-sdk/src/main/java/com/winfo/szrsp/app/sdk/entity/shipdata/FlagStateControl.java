package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FlagStateControl implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 业务编码，非空23位;string(yyyymmddhhmmssfff)+6位发证机构编码,可=inspect_no
	 */
	private String busi_no;
	/**
	 * 船长证书号码
	 */
	private String captain_cert_no;
	/**
	 * 船长联系方式
	 */
	private String captain_phone;
	/**
	 * 纠正标志	"0:未纠正,1:纠正"
	 */
	private String correct_mark;
	/**
	 * 缺陷数
	 */
	private int defect_num;
	/**
	 * 滞留标志:0-否，1-是
	 */
	private String detention_mark;
	/**
	 * 选船业务编号
	 */
	private String fsc_busi_no;
	/**
	 *非空,初查复查标志:0：初查，1：复查
	 */
	private String initial_inspect_mark;
	/**
	 * 初查国内安检编号，非空23位;string(yyyymmddhhmmssfff)+6位发证机构编码,上次检查的inspect_no
	 */
	private String initial_inspect_no;
	/**
	 * 安全检查行动	"40-通知下一港,50-通知船籍港海事管理机构,70-通知船舶检验机构 ,152-通知船员组织,155-通知船东代表"
	 */
	private String inspect_action;
	/**
	 * 检查日期 yyyymmdd hhmmss
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date inspect_date;
	/**
	 * 国内安检编号，非空23位;string(yyyymmddhhmmssfff)+6位发证机构编码"
	 */
	private String inspect_no;
	/**
	 * 安检类型代码;4位机构代码＋2位年＋2位顺序号
	 */
	private String inspect_type;
	/**
	 * 检查员代码(),多个逗号隔开
	 */
	private String inspector_code;
	/**
	 * 多个逗号隔开
	 */
	private String inspector_name;
	/**
	 * 是否通过专项安检;0：否，1：是。
	 */
	private String is_passed;
	/**
	 * 是否专项安检;0：否，1：是。
	 */
	private String is_special_inspect;
	/**
	 * 机构代码
	 */
	private String org_code;
	/**
	 * 其他说明
	 */
	private String other_desc;
	/**
	 * 港口代码
	 */
	private String port_code;
	/**
	 * 实习检查员代码,多个用逗号隔开
	 */
	private String pratice_inspector_code;
	/**
	 * 实习检查员姓名,多个用逗号隔开
	 */
	private String pratice_inspector_name;
	/**
	 * 优先等级
	 */
	private String priority_order;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 船舶风险属性
	 */
	private String risk_attribute;
	/**
	 *  非空，船舶编号："中国籍船舶：A+初次登记号。外国籍船舶：B+IMO编号。港澳船舶：C+牌簿号。台湾船舶：D+船舶号数"
	 */
	private String ship_no;
	/**
	 * 专项安检类型
	 */
	private String special_inspect_type;
	/**
	 * 任务编码
	 */
	private String task_id;
	/**
	 * 未关闭缺陷数
	 */
	private String unclosed_defect_num;
	/**
	 * 航次编码
	 */
	private String voyage_id;
	
	
	public String getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}
	public String getCaptain_cert_no() {
		return captain_cert_no;
	}
	public void setCaptain_cert_no(String captain_cert_no) {
		this.captain_cert_no = captain_cert_no;
	}
	public String getCaptain_phone() {
		return captain_phone;
	}
	public void setCaptain_phone(String captain_phone) {
		this.captain_phone = captain_phone;
	}
	public String getCorrect_mark() {
		return correct_mark;
	}
	public void setCorrect_mark(String correct_mark) {
		this.correct_mark = correct_mark;
	}
	public int getDefect_num() {
		return defect_num;
	}
	public void setDefect_num(int defect_num) {
		this.defect_num = defect_num;
	}
	public String getDetention_mark() {
		return detention_mark;
	}
	public void setDetention_mark(String detention_mark) {
		this.detention_mark = detention_mark;
	}
	public String getFsc_busi_no() {
		return fsc_busi_no;
	}
	public void setFsc_busi_no(String fsc_busi_no) {
		this.fsc_busi_no = fsc_busi_no;
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
	public String getInspect_action() {
		return inspect_action;
	}
	public void setInspect_action(String inspect_action) {
		this.inspect_action = inspect_action;
	}
	public Date getInspect_date() {
		return inspect_date;
	}
	public void setInspect_date(Date inspect_date) {
		this.inspect_date = inspect_date;
	}
	public String getInspect_no() {
		return inspect_no;
	}
	public void setInspect_no(String inspect_no) {
		this.inspect_no = inspect_no;
	}
	public String getInspect_type() {
		return inspect_type;
	}
	public void setInspect_type(String inspect_type) {
		this.inspect_type = inspect_type;
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
	public String getIs_passed() {
		return is_passed;
	}
	public void setIs_passed(String is_passed) {
		this.is_passed = is_passed;
	}
	public String getIs_special_inspect() {
		return is_special_inspect;
	}
	public void setIs_special_inspect(String is_special_inspect) {
		this.is_special_inspect = is_special_inspect;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getOther_desc() {
		return other_desc;
	}
	public void setOther_desc(String other_desc) {
		this.other_desc = other_desc;
	}
	public String getPort_code() {
		return port_code;
	}
	public void setPort_code(String port_code) {
		this.port_code = port_code;
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
	public String getPriority_order() {
		return priority_order;
	}
	public void setPriority_order(String priority_order) {
		this.priority_order = priority_order;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRisk_attribute() {
		return risk_attribute;
	}
	public void setRisk_attribute(String risk_attribute) {
		this.risk_attribute = risk_attribute;
	}
	public String getShip_no() {
		return ship_no;
	}
	public void setShip_no(String ship_no) {
		this.ship_no = ship_no;
	}
	public String getSpecial_inspect_type() {
		return special_inspect_type;
	}
	public void setSpecial_inspect_type(String special_inspect_type) {
		this.special_inspect_type = special_inspect_type;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getUnclosed_defect_num() {
		return unclosed_defect_num;
	}
	public void setUnclosed_defect_num(String unclosed_defect_num) {
		this.unclosed_defect_num = unclosed_defect_num;
	}
	public String getVoyage_id() {
		return voyage_id;
	}
	public void setVoyage_id(String voyage_id) {
		this.voyage_id = voyage_id;
	}
	
	
}
