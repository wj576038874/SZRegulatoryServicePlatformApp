package com.winfo.szrsp.app.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;


/**
 * @项目名: 	gdmsaec-app-gz
 * @包名:	com.winfo.gdmsaec.app.gz.entity
 * @类名:	LocalAisData
 * @创建者:	yanfeijun
 * @创建时间:	2016-7-28	上午10:38:23 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev: 1298 $
 * @更新人:	$Author: qianmanman $
 * @更新时间:	$Date: 2016-07-28 17:31:37 +0800 (星期四, 28 七月 2016) $
 * @更新描述:	TODO
 */
@Table(name = "localAisData")
public class LocalAisData {
	@Id
	private int id;
	
	@Column(column = "cm")
	private String cm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}
	
	
}

