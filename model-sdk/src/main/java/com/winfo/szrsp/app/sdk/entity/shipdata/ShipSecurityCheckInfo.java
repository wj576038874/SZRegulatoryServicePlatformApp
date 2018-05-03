package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipSecurityCheckInfo.java
 * Date: 2017/11/27 19:16
 * Description: 船检信息实体类
 */

public class ShipSecurityCheckInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 缺陷主键
     */
    private String qxzj;

    /**
     * 检查主键
     */
    private String jczj;

    /**
     * 序号
     */
    private BigDecimal xh;

    /**
     * 项目编号
     */
    private String xmbh;

    /**
     * 缺陷描述
     */
    private String qxms;

    /**
     * 缺陷
     */
    private BigDecimal qxyg;

    /**
     * 初次处置代码
     */
    private String ccczdm;

    /**
     * 操作标记
     */
    private String databasetablerowoperation;

    /**
     * 最后修改时间
     */
    private String zhxgsj;

    /**
     * 船舶主键
     */
    private String cbzj;

    /**
     * 检查日期
     */
    private String jcrq;

    /**
     * 缺陷数量
     */
    private String count;

    public String getQxzj() {
        return qxzj;
    }

    public void setQxzj(String qxzj) {
        this.qxzj = qxzj;
    }

    public String getJczj() {
        return jczj;
    }

    public void setJczj(String jczj) {
        this.jczj = jczj;
    }

    public BigDecimal getXh() {
        return xh;
    }

    public void setXh(BigDecimal xh) {
        this.xh = xh;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getQxms() {
        return qxms;
    }

    public void setQxms(String qxms) {
        this.qxms = qxms;
    }

    public BigDecimal getQxyg() {
        return qxyg;
    }

    public void setQxyg(BigDecimal qxyg) {
        this.qxyg = qxyg;
    }

    public String getCcczdm() {
        return ccczdm;
    }

    public void setCcczdm(String ccczdm) {
        this.ccczdm = ccczdm;
    }

    public String getDatabasetablerowoperation() {
        return databasetablerowoperation;
    }

    public void setDatabasetablerowoperation(String databasetablerowoperation) {
        this.databasetablerowoperation = databasetablerowoperation;
    }

    public String getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(String zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getCbzj() {
        return cbzj;
    }

    public void setCbzj(String cbzj) {
        this.cbzj = cbzj;
    }

    public String getJcrq() {
        return jcrq;
    }

    public void setJcrq(String jcrq) {
        this.jcrq = jcrq;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
