package com.winfo.szrsp.app.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;


/**
 * @项目名: gdmsaec-app
 * @包名: com.winfo.gdmsaec.app.mvp.model.entity
 * @类名: SearchShip
 * @创建者: yanfeijun
 * @创建时间: 2016-4-12	上午10:25:00
 * @描述: TODO
 * @svn版本: $Rev: 1298 $
 * @更新人: $Author: qianmanman $
 * @更新时间: $Date: 2016-07-28 17:31:37 +0800 (星期四, 28 七月 2016) $
 * @更新描述: TODO 搜索历史的记录
 */
@Table(name = "historyAis")
public class HistoryAis {
    @Id
    private int id;

    @Column(column = "mmsi")
    private String mmsi;

    @Column(column = "ywcm")
    private String ywcm;

    @Column(column = "zwcm")
    private String zwcm;

    @Column(column = "pycm")
    private String pycm;

    public String getYwcm() {
        return ywcm;
    }

    public void setYwcm(String ywcm) {
        this.ywcm = ywcm;
    }

    public String getZwcm() {
        return zwcm;
    }

    public void setZwcm(String zwcm) {
        this.zwcm = zwcm;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPycm() {
        return pycm;
    }

    public void setPycm(String pycm) {
        this.pycm = pycm;
    }
}
