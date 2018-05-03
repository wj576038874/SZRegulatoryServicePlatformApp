package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SiteSupervisionDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 23位 6位发证机构编码＋string(yyyymmddhhmmssfff);非空
	 */
	private String inspect_no;
	/**
	 * 以安检标号为单位从1开始的顺序号;非空
	 */
	private int seq_no;
	/**
	 * 非空；缺陷代码； 1101：中国籍船舶是否开展开航前自查 1102：法定证书文书配备及记录是否齐全 1103：船员配备是否满足要求
	 * 1104：客货载运及货物系固绑扎是否符合规定 1105：船舶防污染措施是否落实 1106：船舶航行、停泊、作业是否符合规定
	 * 1107：船舶是否按要求进行进出港报告或者办理进出港手续 1108：船舶是否按照相关规定缴纳相关费税 1109：其他
	 */
	private String content_code;
	/**
	 * 
	 */
	private String content_desc;
	/**
	 * 是否有问题：0：否；1：是；2：不适用
	 */
	private String result;
	/**
	 * 0：否，1：是。非空
	 */
	private String if_problem;
	/**
	 * 
	 */
	private String description;
	/**
	 * "01：警示教育； 02：停止作业 03：立案调查 09：违法记分 17：开航前纠正 19：在开航后限定的期限内纠正（需标注期限） 99：文字说明
	 */
	private String comment_code;
	/**
	 * 
	 */
	private String comment_desc;
	/**
	 * 非空；报告录入人员：身份证号
	 */
	private String creator_code;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	/**
	 * 
	 */
	private String initial_inspect_no;
	/**
	 * 0：否，1：是。非空
	 */
	private String correct_mark;

	public String getInspect_no() {
		return inspect_no;
	}

	public void setInspect_no(String inspect_no) {
		this.inspect_no = inspect_no;
	}

	public int getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}

	public String getContent_code() {
		return content_code;
	}

	public void setContent_code(String content_code) {
		this.content_code = content_code;
	}

	public String getContent_desc() {
		return content_desc;
	}

	public void setContent_desc(String content_desc) {
		this.content_desc = content_desc;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIf_problem() {
		return if_problem;
	}

	public void setIf_problem(String if_problem) {
		this.if_problem = if_problem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public String getCreator_code() {
		return creator_code;
	}

	public void setCreator_code(String creator_code) {
		this.creator_code = creator_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getInitial_inspect_no() {
		return initial_inspect_no;
	}

	public void setInitial_inspect_no(String initial_inspect_no) {
		this.initial_inspect_no = initial_inspect_no;
	}

	public String getCorrect_mark() {
		return correct_mark;
	}

	public void setCorrect_mark(String correct_mark) {
		this.correct_mark = correct_mark;
	}

}
