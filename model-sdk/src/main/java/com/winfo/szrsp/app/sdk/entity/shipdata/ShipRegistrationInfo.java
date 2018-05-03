package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.ShipRegistrationInfo.java
 * Date: 2017/11/25 13:59
 * Description: 船舶登记信息
 */

public class ShipRegistrationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cbzj; //船舶主键

    private String cbdjh;//船舶登记号

    private String cbsbh;//船舶识别号

    private String ccdjh;//初次登记号

    private String cjdjh;//船检登记号

    private String cyzwcm;//曾用中文船名

    private String pbh;//牌簿号

    private String zwcm;//中文船名

    private String ywcm;//英文船名

    private String mmsi;//MMSI

    private String imo;//IMO

    private String hh;//船舶呼号

    private String cjgdm;//船籍港代码

    private String cqgdm;//船旗国代码

    private String hcnhcbsdm;//海船/内河船标识代码

    private String cbzldm;//船舶种类代码

    private BigDecimal zdw;//总吨位

    private BigDecimal jdw;//净吨位

    private BigDecimal ckzzd;//参考载重吨

    private BigDecimal zjzgl;//主机总功率

    private BigDecimal cbzc;//船舶总长

    private BigDecimal cbxk;//船舶型宽

    private BigDecimal cbxs;//船舶型深

    private String hdhxdm;//核定航线代码

    private String hdhqdm;//核定航区代码

    private String hxqy;//航行区域

    private String ctcldm;//船体材料代码

    private String csys;//船身颜色

    private String cbjz;//船舶价值

    private String jzrq;//建造日期

    private String zccmc;//造船厂名称

    private String zccywmc;//造船厂英文名称

    private String zcdd;//造船地点

    private String zcddywmc;//造船地点英文名称

    private String gjcmc;//改建厂名称

    private String gjcywmc;//改建厂英文名称

    private String gjrq;//改建日期

    private String gjdd;//改建地点

    private String gjddywmc;//改建地点英文名称

    private String aflgrq;//安放龙骨日期

    private BigDecimal lgyszdgd;//龙骨以上最大高度

    private String zjzldm;//主机种类代码

    private BigDecimal zjsm;//主机数目

    private String zjzzcmc;//主机制造厂名称

    private String zjxh;//主机型号

    private String tjqzldm;//主机编号

    private String tjqsl;//推进器种类代码

    private String xjmzcs;//夏季满载吃水

    private BigDecimal mzcs;//满载吃水

    private BigDecimal kzcs;//空载吃水

    private BigDecimal mzpsl;//满载排水量

    private BigDecimal kzpsl;//空载排水量

    private String hdkfdj;//核定抗风等级

    private String gx;//干舷

    private BigDecimal edxw;//参考载箱量

    private BigDecimal edcw;//额定车位

    private BigDecimal ckdezj;//乘客定额总计

    private BigDecimal jsdy;//救生定员

    private String zdhs;//最大航速

    private String cxjc;//满载水线长

    private String jbcl;//甲板材料

    private BigDecimal jbcs;//甲板层数

    private String smhcbs;//水密横舱壁数

    private String scdwz;//双层底位置

    private BigDecimal zcbsl;//纵舱壁数量

    private String jczdhcddm;//机舱自动化程度代码

    private String dskx;//淡水宽限

    private String cjjgbm;//船检机构代码

    private String hsjgdm;//登记机构代码

    private String bz;//备注

    private BigDecimal sfzx;//是否注销

    private String tpzj;//船舶图片主键

    private String syrzj;//所有人主键

    private String jyrzj;//经营人主键

    private String glrzj;//管理人主键

    private String cjsj;//创建时间

    private String cjr;//创建人

    private String cjjgdm;//创建机构代码

    private String cjbmdm;//创建部门代码

    private String zhxgsj;//最后修改时间

    private String zhxgr;//最后修改人

    private String hhcbzdm;//海河船代码

    private String syr;//所有人

    private String jyr;//经营人

    public String getCbzj() {
        return cbzj;
    }

    public void setCbzj(String cbzj) {
        this.cbzj = cbzj;
    }

    public String getCbdjh() {
        return cbdjh;
    }

    public void setCbdjh(String cbdjh) {
        this.cbdjh = cbdjh;
    }

    public String getCbsbh() {
        return cbsbh;
    }

    public void setCbsbh(String cbsbh) {
        this.cbsbh = cbsbh;
    }

    public String getCcdjh() {
        return ccdjh;
    }

    public void setCcdjh(String ccdjh) {
        this.ccdjh = ccdjh;
    }

    public String getCjdjh() {
        return cjdjh;
    }

    public void setCjdjh(String cjdjh) {
        this.cjdjh = cjdjh;
    }

    public String getCyzwcm() {
        return cyzwcm;
    }

    public void setCyzwcm(String cyzwcm) {
        this.cyzwcm = cyzwcm;
    }

    public String getPbh() {
        return pbh;
    }

    public void setPbh(String pbh) {
        this.pbh = pbh;
    }

    public String getZwcm() {
        return zwcm;
    }

    public void setZwcm(String zwcm) {
        this.zwcm = zwcm;
    }

    public String getYwcm() {
        return ywcm;
    }

    public void setYwcm(String ywcm) {
        this.ywcm = ywcm;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getCjgdm() {
        return cjgdm;
    }

    public void setCjgdm(String cjgdm) {
        this.cjgdm = cjgdm;
    }

    public String getCqgdm() {
        return cqgdm;
    }

    public void setCqgdm(String cqgdm) {
        this.cqgdm = cqgdm;
    }

    public String getHcnhcbsdm() {
        return hcnhcbsdm;
    }

    public void setHcnhcbsdm(String hcnhcbsdm) {
        this.hcnhcbsdm = hcnhcbsdm;
    }

    public String getCbzldm() {
        return cbzldm;
    }

    public void setCbzldm(String cbzldm) {
        this.cbzldm = cbzldm;
    }

    public BigDecimal getZdw() {
        return zdw;
    }

    public void setZdw(BigDecimal zdw) {
        this.zdw = zdw;
    }

    public BigDecimal getJdw() {
        return jdw;
    }

    public void setJdw(BigDecimal jdw) {
        this.jdw = jdw;
    }

    public BigDecimal getCkzzd() {
        return ckzzd;
    }

    public void setCkzzd(BigDecimal ckzzd) {
        this.ckzzd = ckzzd;
    }

    public BigDecimal getZjzgl() {
        return zjzgl;
    }

    public void setZjzgl(BigDecimal zjzgl) {
        this.zjzgl = zjzgl;
    }

    public BigDecimal getCbzc() {
        return cbzc;
    }

    public void setCbzc(BigDecimal cbzc) {
        this.cbzc = cbzc;
    }

    public BigDecimal getCbxk() {
        return cbxk;
    }

    public void setCbxk(BigDecimal cbxk) {
        this.cbxk = cbxk;
    }

    public BigDecimal getCbxs() {
        return cbxs;
    }

    public void setCbxs(BigDecimal cbxs) {
        this.cbxs = cbxs;
    }

    public String getHdhxdm() {
        return hdhxdm;
    }

    public void setHdhxdm(String hdhxdm) {
        this.hdhxdm = hdhxdm;
    }

    public String getHdhqdm() {
        return hdhqdm;
    }

    public void setHdhqdm(String hdhqdm) {
        this.hdhqdm = hdhqdm;
    }

    public String getHxqy() {
        return hxqy;
    }

    public void setHxqy(String hxqy) {
        this.hxqy = hxqy;
    }

    public String getCtcldm() {
        return ctcldm;
    }

    public void setCtcldm(String ctcldm) {
        this.ctcldm = ctcldm;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getCbjz() {
        return cbjz;
    }

    public void setCbjz(String cbjz) {
        this.cbjz = cbjz;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getZccmc() {
        return zccmc;
    }

    public void setZccmc(String zccmc) {
        this.zccmc = zccmc;
    }

    public String getZccywmc() {
        return zccywmc;
    }

    public void setZccywmc(String zccywmc) {
        this.zccywmc = zccywmc;
    }

    public String getZcdd() {
        return zcdd;
    }

    public void setZcdd(String zcdd) {
        this.zcdd = zcdd;
    }

    public String getZcddywmc() {
        return zcddywmc;
    }

    public void setZcddywmc(String zcddywmc) {
        this.zcddywmc = zcddywmc;
    }

    public String getGjcmc() {
        return gjcmc;
    }

    public void setGjcmc(String gjcmc) {
        this.gjcmc = gjcmc;
    }

    public String getGjcywmc() {
        return gjcywmc;
    }

    public void setGjcywmc(String gjcywmc) {
        this.gjcywmc = gjcywmc;
    }

    public String getGjrq() {
        return gjrq;
    }

    public void setGjrq(String gjrq) {
        this.gjrq = gjrq;
    }

    public String getGjdd() {
        return gjdd;
    }

    public void setGjdd(String gjdd) {
        this.gjdd = gjdd;
    }

    public String getGjddywmc() {
        return gjddywmc;
    }

    public void setGjddywmc(String gjddywmc) {
        this.gjddywmc = gjddywmc;
    }

    public String getAflgrq() {
        return aflgrq;
    }

    public void setAflgrq(String aflgrq) {
        this.aflgrq = aflgrq;
    }

    public BigDecimal getLgyszdgd() {
        return lgyszdgd;
    }

    public void setLgyszdgd(BigDecimal lgyszdgd) {
        this.lgyszdgd = lgyszdgd;
    }

    public String getZjzldm() {
        return zjzldm;
    }

    public void setZjzldm(String zjzldm) {
        this.zjzldm = zjzldm;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getZjsm() {
        return zjsm;
    }

    public void setZjsm(BigDecimal zjsm) {
        this.zjsm = zjsm;
    }

    public String getZjzzcmc() {
        return zjzzcmc;
    }

    public void setZjzzcmc(String zjzzcmc) {
        this.zjzzcmc = zjzzcmc;
    }

    public String getZjxh() {
        return zjxh;
    }

    public void setZjxh(String zjxh) {
        this.zjxh = zjxh;
    }

    public String getTjqzldm() {
        return tjqzldm;
    }

    public void setTjqzldm(String tjqzldm) {
        this.tjqzldm = tjqzldm;
    }

    public String getTjqsl() {
        return tjqsl;
    }

    public void setTjqsl(String tjqsl) {
        this.tjqsl = tjqsl;
    }

    public String getXjmzcs() {
        return xjmzcs;
    }

    public void setXjmzcs(String xjmzcs) {
        this.xjmzcs = xjmzcs;
    }

    public BigDecimal getMzcs() {
        return mzcs;
    }

    public void setMzcs(BigDecimal mzcs) {
        this.mzcs = mzcs;
    }

    public BigDecimal getKzcs() {
        return kzcs;
    }

    public void setKzcs(BigDecimal kzcs) {
        this.kzcs = kzcs;
    }

    public BigDecimal getMzpsl() {
        return mzpsl;
    }

    public void setMzpsl(BigDecimal mzpsl) {
        this.mzpsl = mzpsl;
    }

    public BigDecimal getKzpsl() {
        return kzpsl;
    }

    public void setKzpsl(BigDecimal kzpsl) {
        this.kzpsl = kzpsl;
    }

    public String getHdkfdj() {
        return hdkfdj;
    }

    public void setHdkfdj(String hdkfdj) {
        this.hdkfdj = hdkfdj;
    }

    public String getGx() {
        return gx;
    }

    public void setGx(String gx) {
        this.gx = gx;
    }

    public BigDecimal getEdxw() {
        return edxw;
    }

    public void setEdxw(BigDecimal edxw) {
        this.edxw = edxw;
    }

    public BigDecimal getEdcw() {
        return edcw;
    }

    public void setEdcw(BigDecimal edcw) {
        this.edcw = edcw;
    }

    public BigDecimal getCkdezj() {
        return ckdezj;
    }

    public void setCkdezj(BigDecimal ckdezj) {
        this.ckdezj = ckdezj;
    }

    public BigDecimal getJsdy() {
        return jsdy;
    }

    public void setJsdy(BigDecimal jsdy) {
        this.jsdy = jsdy;
    }

    public String getZdhs() {
        return zdhs;
    }

    public void setZdhs(String zdhs) {
        this.zdhs = zdhs;
    }

    public String getCxjc() {
        return cxjc;
    }

    public void setCxjc(String cxjc) {
        this.cxjc = cxjc;
    }

    public String getJbcl() {
        return jbcl;
    }

    public void setJbcl(String jbcl) {
        this.jbcl = jbcl;
    }

    public BigDecimal getJbcs() {
        return jbcs;
    }

    public void setJbcs(BigDecimal jbcs) {
        this.jbcs = jbcs;
    }

    public String getSmhcbs() {
        return smhcbs;
    }

    public void setSmhcbs(String smhcbs) {
        this.smhcbs = smhcbs;
    }

    public String getScdwz() {
        return scdwz;
    }

    public void setScdwz(String scdwz) {
        this.scdwz = scdwz;
    }

    public BigDecimal getZcbsl() {
        return zcbsl;
    }

    public void setZcbsl(BigDecimal zcbsl) {
        this.zcbsl = zcbsl;
    }

    public String getJczdhcddm() {
        return jczdhcddm;
    }

    public void setJczdhcddm(String jczdhcddm) {
        this.jczdhcddm = jczdhcddm;
    }

    public String getDskx() {
        return dskx;
    }

    public void setDskx(String dskx) {
        this.dskx = dskx;
    }

    public String getCjjgbm() {
        return cjjgbm;
    }

    public void setCjjgbm(String cjjgbm) {
        this.cjjgbm = cjjgbm;
    }

    public String getHsjgdm() {
        return hsjgdm;
    }

    public void setHsjgdm(String hsjgdm) {
        this.hsjgdm = hsjgdm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public BigDecimal getSfzx() {
        return sfzx;
    }

    public void setSfzx(BigDecimal sfzx) {
        this.sfzx = sfzx;
    }

    public String getTpzj() {
        return tpzj;
    }

    public void setTpzj(String tpzj) {
        this.tpzj = tpzj;
    }

    public String getSyrzj() {
        return syrzj;
    }

    public void setSyrzj(String syrzj) {
        this.syrzj = syrzj;
    }

    public String getJyrzj() {
        return jyrzj;
    }

    public void setJyrzj(String jyrzj) {
        this.jyrzj = jyrzj;
    }

    public String getGlrzj() {
        return glrzj;
    }

    public void setGlrzj(String glrzj) {
        this.glrzj = glrzj;
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

    public String getHhcbzdm() {
        return hhcbzdm;
    }

    public void setHhcbzdm(String hhcbzdm) {
        this.hhcbzdm = hhcbzdm;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getJyr() {
        return jyr;
    }

    public void setJyr(String jyr) {
        this.jyr = jyr;
    }
}
