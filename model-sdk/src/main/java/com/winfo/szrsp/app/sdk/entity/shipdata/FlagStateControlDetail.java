package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;

public class FlagStateControlDetail  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 处理意见代码;"06  船籍港纠正          ,10  缺陷已纠正          ,15  下一港纠正          ,16  十四天内纠正,17  开航前纠正                   ,18  三个月纠正（ISM或NSM）,30  禁止离港（滞留）          ,41  限制操作                   ,42  责令船舶驶向指定区域
	,43 责令船舶离港,44 禁止船舶进港  ,46 驶往经同意的修理港纠正滞留缺陷              ,49 经同意的缺陷纠正计划,99 其他措施（详细说明）"
	 */
	private String comment_code;
	/**
	 * 处理意见说明
	 */
	private String comment_desc;
	/**
	 * 纠正标志;0未纠正,1纠正
	 */
	private String correct_mark;
	/**
	 * 非空,缺陷代码
	 */
	private String defect_code;
	/**
	 * 缺陷描述
	 */
	private String defect_desc;
	/**
	 * 缺陷标志
	 */
	private String defect_mark;
	/**
	 * 执法依据
	 */
	private String enforce_foundation;
	/**
	 * 初查国内安检编号，非空23位;string(yyyymmddhhmmssfff)+6位发证机构编码,上次检查的inspect_no
	 */
	private String initial_inspect_no;
	/**
	 * 非空,国内安检编号，非空23位;string(yyyymmddhhmmssfff)+6位发证机构编码"
	 */
	private String inspect_no;
	/**
	 * 非空 ,滞留缺陷是否与检验机构有关;	0-无关；1-有关  
	 */

	private String is_inspect_org_related;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 非空,安检序号 ,以安检标号为单位从1开始的顺序号自增
	 */
	private int  seq_no;
	
	
	
	
	public String getComment_code() {
		return comment_code;
	}
	public void setComment_code(String comment_code) {
		this.comment_code = comment_code;
	}
	public String getComment_desc() {
		return comment_desc;
	}
	public void setComment_desc(String comment_desc) {
		this.comment_desc = comment_desc;
	}
	public String getCorrect_mark() {
		return correct_mark;
	}
	public void setCorrect_mark(String correct_mark) {
		this.correct_mark = correct_mark;
	}
	public String getDefect_code() {
		return defect_code;
	}
	public void setDefect_code(String defect_code) {
		this.defect_code = defect_code;
	}
	public String getDefect_desc() {
		return defect_desc;
	}
	public void setDefect_desc(String defect_desc) {
		this.defect_desc = defect_desc;
	}
	public String getDefect_mark() {
		return defect_mark;
	}
	public void setDefect_mark(String defect_mark) {
		this.defect_mark = defect_mark;
	}
	public String getEnforce_foundation() {
		return enforce_foundation;
	}
	public void setEnforce_foundation(String enforce_foundation) {
		this.enforce_foundation = enforce_foundation;
	}
	public String getInitial_inspect_no() {
		return initial_inspect_no;
	}
	public void setInitial_inspect_no(String initial_inspect_no) {
		this.initial_inspect_no = initial_inspect_no;
	}
	public String getInspect_no() {
		return inspect_no;
	}
	public void setInspect_no(String inspect_no) {
		this.inspect_no = inspect_no;
	}
	public String getIs_inspect_org_related() {
		return is_inspect_org_related;
	}
	public void setIs_inspect_org_related(String is_inspect_org_related) {
		this.is_inspect_org_related = is_inspect_org_related;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
	
	
	
	
}
