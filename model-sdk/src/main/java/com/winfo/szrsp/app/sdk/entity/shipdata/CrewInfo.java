package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo.java
 * Date: 2017/11/25 14:06
 * Description: 在船船员信息
 */

public class CrewInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 身份证号码
     */
    private String sfzhm;

    /**
     * 船员卡号
     */
    private String cykh;

    /**
     * 船员姓名
     */
    private String cyxm;

    /**
     * 船员性别代码
     */
    private String cyxbdm;

    /**
     * 出生日期
     */
    private String csrq;

    /**
     * 国籍代码
     */
    private String gjdm;

    /**
     * 船员联系地址
     */
    private String cylxdz;

    /**
     * 船员联系电话
     */
    private String cylxdh;

    /**
     * 适任证书类别
     */
    private String srzslb;

    /**
     * 船员证书号码
     */
    private String cyzshm;

    /**
     * 证书职位代码
     */
    private String zszwdm;

    /**
     * 证书职务名称
     */
    private String zszwmc;

    /**
     * 船舶识别号
     */
    private String cbsbh;

    /**
     * 船舶主键
     */
    private String cbzj;

    /**
     * 适任专业代码
     */
    private String srzydm;

    /**
     * 证书截止日期
     */
    private String jzrq;

    /**
     * 纠正标识
     */
    private String jzbz;

    /**
     * 更新标识
     */
    private String gxbz;

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getCykh() {
        return cykh;
    }

    public void setCykh(String cykh) {
        this.cykh = cykh;
    }

    public String getCyxm() {
        return cyxm;
    }

    public void setCyxm(String cyxm) {
        this.cyxm = cyxm;
    }

    public String getCyxbdm() {
        return cyxbdm;
    }

    public void setCyxbdm(String cyxbdm) {
        this.cyxbdm = cyxbdm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getGjdm() {
        return gjdm;
    }

    public void setGjdm(String gjdm) {
        this.gjdm = gjdm;
    }

    public String getCylxdz() {
        return cylxdz;
    }

    public void setCylxdz(String cylxdz) {
        this.cylxdz = cylxdz;
    }

    public String getCylxdh() {
        return cylxdh;
    }

    public void setCylxdh(String cylxdh) {
        this.cylxdh = cylxdh;
    }

    public String getSrzslb() {
        return srzslb;
    }

    public void setSrzslb(String srzslb) {
        this.srzslb = srzslb;
    }

    public String getCyzshm() {
        return cyzshm;
    }

    public void setCyzshm(String cyzshm) {
        this.cyzshm = cyzshm;
    }

    public String getZszwdm() {
        return zszwdm;
    }

    public void setZszwdm(String zszwdm) {
        this.zszwdm = zszwdm;
    }

    public String getZszwmc() {
        return zszwmc;
    }

    public void setZszwmc(String zszwmc) {
        this.zszwmc = zszwmc;
    }

    public String getCbsbh() {
        return cbsbh;
    }

    public void setCbsbh(String cbsbh) {
        this.cbsbh = cbsbh;
    }

    public String getCbzj() {
        return cbzj;
    }

    public void setCbzj(String cbzj) {
        this.cbzj = cbzj;
    }

    public String getSrzydm() {
        return srzydm;
    }

    public void setSrzydm(String srzydm) {
        this.srzydm = srzydm;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getJzbz() {
        return jzbz;
    }

    public void setJzbz(String jzbz) {
        this.jzbz = jzbz;
    }

    public String getGxbz() {
        return gxbz;
    }

    public void setGxbz(String gxbz) {
        this.gxbz = gxbz;
    }
}
