package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipCompanyInfo.java
 * Date: 2017/11/25 13:39
 * Description: 船公司信息
 */

public class ShipCompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dwjgdm;//单位机构代码

    private String zwjc;//中文简称

    private String zwqc;//中文全称

    private String ywjc;//英文简称

    private String ywqc;//英文全称

    private String gjdm;//国籍代码

    private String ssdm;//省份代码

    private String csdm;//城市代码

    private String dz;//主地址

    private String dzyw;//主地址英文

    private String yzbm;//邮政编码

    private String wzdz;//网站地址

    private String bgdh;//办公电话

    private String yjdz;//邮件地址

    private String czhm;//传真号码

    private BigDecimal jd;//经度

    private BigDecimal wd;//纬度

    private String bz;//备注

    private String sjdwjgdm;//上级单位机构代码

    private String logozj;//LOGO图片主键

    private String cjsj;//创建时间

    private String cjr;//创建人

    private String cjjgdm;//创建机构代码

    private String cjbmdm;//创建部门代码

    private String zhxgsj;//最后修改时间

    private String zhxgr;//最后修改人

    private BigDecimal rouNum;//ROU_NUM

    private String gxdwjgdm;//未知

    private String lxdm;//未知

    public String getDwjgdm() {
        return dwjgdm;
    }

    public void setDwjgdm(String dwjgdm) {
        this.dwjgdm = dwjgdm;
    }

    public String getZwjc() {
        return zwjc;
    }

    public void setZwjc(String zwjc) {
        this.zwjc = zwjc;
    }

    public String getZwqc() {
        return zwqc;
    }

    public void setZwqc(String zwqc) {
        this.zwqc = zwqc;
    }

    public String getYwjc() {
        return ywjc;
    }

    public void setYwjc(String ywjc) {
        this.ywjc = ywjc;
    }

    public String getYwqc() {
        return ywqc;
    }

    public void setYwqc(String ywqc) {
        this.ywqc = ywqc;
    }

    public String getGjdm() {
        return gjdm;
    }

    public void setGjdm(String gjdm) {
        this.gjdm = gjdm;
    }

    public String getSsdm() {
        return ssdm;
    }

    public void setSsdm(String ssdm) {
        this.ssdm = ssdm;
    }

    public String getCsdm() {
        return csdm;
    }

    public void setCsdm(String csdm) {
        this.csdm = csdm;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getDzyw() {
        return dzyw;
    }

    public void setDzyw(String dzyw) {
        this.dzyw = dzyw;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getWzdz() {
        return wzdz;
    }

    public void setWzdz(String wzdz) {
        this.wzdz = wzdz;
    }

    public String getBgdh() {
        return bgdh;
    }

    public void setBgdh(String bgdh) {
        this.bgdh = bgdh;
    }

    public String getYjdz() {
        return yjdz;
    }

    public void setYjdz(String yjdz) {
        this.yjdz = yjdz;
    }

    public String getCzhm() {
        return czhm;
    }

    public void setCzhm(String czhm) {
        this.czhm = czhm;
    }

    public BigDecimal getJd() {
        return jd;
    }

    public void setJd(BigDecimal jd) {
        this.jd = jd;
    }

    public BigDecimal getWd() {
        return wd;
    }

    public void setWd(BigDecimal wd) {
        this.wd = wd;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSjdwjgdm() {
        return sjdwjgdm;
    }

    public void setSjdwjgdm(String sjdwjgdm) {
        this.sjdwjgdm = sjdwjgdm;
    }

    public String getLogozj() {
        return logozj;
    }

    public void setLogozj(String logozj) {
        this.logozj = logozj;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getCjjgdm() {
        return cjjgdm;
    }

    public void setCjjgdm(String cjjgdm) {
        this.cjjgdm = cjjgdm;
    }

    public String getCjbmdm() {
        return cjbmdm;
    }

    public void setCjbmdm(String cjbmdm) {
        this.cjbmdm = cjbmdm;
    }

    public String getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(String zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getZhxgr() {
        return zhxgr;
    }

    public void setZhxgr(String zhxgr) {
        this.zhxgr = zhxgr;
    }

    public BigDecimal getRouNum() {
        return rouNum;
    }

    public void setRouNum(BigDecimal rouNum) {
        this.rouNum = rouNum;
    }

    public String getGxdwjgdm() {
        return gxdwjgdm;
    }

    public void setGxdwjgdm(String gxdwjgdm) {
        this.gxdwjgdm = gxdwjgdm;
    }

    public String getLxdm() {
        return lxdm;
    }

    public void setLxdm(String lxdm) {
        this.lxdm = lxdm;
    }
}
