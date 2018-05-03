package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;

public class SecurityInspectorInformation implements Serializable {
	private String inspector_no;
	private String idcard_no;
	private String inspector_name;
	private String sex;
	private String title;
	private String inspector_card;
	private String work_dept_code;
	private String work_position;
	private String cert_large_class;
	private String cert_small_class;
	private String cert_status;
	private String inspectors;
	public String getInspector_no() {
		return inspector_no;
	}
	public void setInspector_no(String inspector_no) {
		this.inspector_no = inspector_no;
	}
	public String getIdcard_no() {
		return idcard_no;
	}
	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}
	public String getInspector_name() {
		return inspector_name;
	}
	public void setInspector_name(String inspector_name) {
		this.inspector_name = inspector_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInspector_card() {
		return inspector_card;
	}
	public void setInspector_card(String inspector_card) {
		this.inspector_card = inspector_card;
	}
	public String getWork_dept_code() {
		return work_dept_code;
	}
	public void setWork_dept_code(String work_dept_code) {
		this.work_dept_code = work_dept_code;
	}
	public String getWork_position() {
		return work_position;
	}
	public void setWork_position(String work_position) {
		this.work_position = work_position;
	}
	public String getCert_large_class() {
		return cert_large_class;
	}
	public void setCert_large_class(String cert_large_class) {
		this.cert_large_class = cert_large_class;
	}
	public String getCert_small_class() {
		return cert_small_class;
	}
	public void setCert_small_class(String cert_small_class) {
		this.cert_small_class = cert_small_class;
	}
	public String getCert_status() {
		return cert_status;
	}
	public void setCert_status(String cert_status) {
		this.cert_status = cert_status;
	}
	public String getInspectors() {
		return inspectors;
	}
	public void setInspectors(String inspectors) {
		this.inspectors = inspectors;
	}

}
