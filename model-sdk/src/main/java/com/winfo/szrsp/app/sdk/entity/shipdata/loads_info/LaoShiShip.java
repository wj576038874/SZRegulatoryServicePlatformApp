package com.winfo.szrsp.app.sdk.entity.shipdata.loads_info;

import java.io.Serializable;


/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.shipdata.loads_info.LaoShiShip.java
 * Date: 2017/12/21 14:52
 * Description:
 */
public class LaoShiShip implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String mmsino;

	private String officialno;

	private String shipname;

	private String classnotation;

	private String shiptype;

	private String builder;

	private String datebuild;

	private String imono;

	private String callsign;

	private String status;

	private String gross;

	private String deadweight;

	private String net;

	private String port;

	private String flag;

	private String hullmaterial;

	private String ownerid;

	private String lengthoverall;

	private String lengthbp;

	private String breadth;

	private String depth;

	private String draught;

	private String height;

	private String decks;

	private String ballast;

	private String hatch;

	private String powertype;

	private String powerbore;

	private String mainpower;

	private String powerbuilder;

	private String rpm;

	private String auxiliarypower;

	private String speed;

	private String shipmanager;

	private String shipoperator;

	private String hatchsize;

	private String liftingdevices;

	private String voltage;

	private String boiler;

	private String buildplace;

	private String powerplace;

	private String powerdate;

	private String boilerbuild;

	private String boilerdate;

	private String deckerections;

	private String regowner;

	private String collectionnum;

	private String nameen;

	private String formername;

	private String formeren;

	private String feedborard;

	private String holds;

	private String displacement;

	private String buckheads;

	private String boilerplace;

	private String managercountry;

	private String hullconnections;

	private LaoshiOwner laoshiOwner;
	private LaoshiManager laoshiManager;
	private LaoshiBuilder laoshiBuilder;

	

	public LaoshiOwner getLaoshiOwner() {
		return laoshiOwner;
	}

	public void setLaoshiOwner(LaoshiOwner laoshiOwner) {
		this.laoshiOwner = laoshiOwner;
	}

	public LaoshiManager getLaoshiManager() {
		return laoshiManager;
	}

	public void setLaoshiManager(LaoshiManager laoshiManager) {
		this.laoshiManager = laoshiManager;
	}

	public LaoshiBuilder getLaoshiBuilder() {
		return laoshiBuilder;
	}

	public void setLaoshiBuilder(LaoshiBuilder laoshiBuilder) {
		this.laoshiBuilder = laoshiBuilder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMmsino() {
		return mmsino;
	}

	public void setMmsino(String mmsino) {
		this.mmsino = mmsino == null ? null : mmsino.trim();
	}

	public String getOfficialno() {
		return officialno;
	}

	public void setOfficialno(String officialno) {
		this.officialno = officialno == null ? null : officialno.trim();
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname == null ? null : shipname.trim();
	}

	public String getClassnotation() {
		return classnotation;
	}

	public void setClassnotation(String classnotation) {
		this.classnotation = classnotation == null ? null : classnotation.trim();
	}

	public String getShiptype() {
		return shiptype;
	}

	public void setShiptype(String shiptype) {
		this.shiptype = shiptype == null ? null : shiptype.trim();
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder == null ? null : builder.trim();
	}

	public String getDatebuild() {
		return datebuild;
	}

	public void setDatebuild(String datebuild) {
		this.datebuild = datebuild == null ? null : datebuild.trim();
	}

	public String getImono() {
		return imono;
	}

	public void setImono(String imono) {
		this.imono = imono == null ? null : imono.trim();
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign == null ? null : callsign.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross == null ? null : gross.trim();
	}

	public String getDeadweight() {
		return deadweight;
	}

	public void setDeadweight(String deadweight) {
		this.deadweight = deadweight == null ? null : deadweight.trim();
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net == null ? null : net.trim();
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port == null ? null : port.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	public String getHullmaterial() {
		return hullmaterial;
	}

	public void setHullmaterial(String hullmaterial) {
		this.hullmaterial = hullmaterial == null ? null : hullmaterial.trim();
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid == null ? null : ownerid.trim();
	}

	public String getLengthoverall() {
		return lengthoverall;
	}

	public void setLengthoverall(String lengthoverall) {
		this.lengthoverall = lengthoverall == null ? null : lengthoverall.trim();
	}

	public String getLengthbp() {
		return lengthbp;
	}

	public void setLengthbp(String lengthbp) {
		this.lengthbp = lengthbp == null ? null : lengthbp.trim();
	}

	public String getBreadth() {
		return breadth;
	}

	public void setBreadth(String breadth) {
		this.breadth = breadth == null ? null : breadth.trim();
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth == null ? null : depth.trim();
	}

	public String getDraught() {
		return draught;
	}

	public void setDraught(String draught) {
		this.draught = draught == null ? null : draught.trim();
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height == null ? null : height.trim();
	}

	public String getDecks() {
		return decks;
	}

	public void setDecks(String decks) {
		this.decks = decks == null ? null : decks.trim();
	}

	public String getBallast() {
		return ballast;
	}

	public void setBallast(String ballast) {
		this.ballast = ballast == null ? null : ballast.trim();
	}

	public String getHatch() {
		return hatch;
	}

	public void setHatch(String hatch) {
		this.hatch = hatch == null ? null : hatch.trim();
	}

	public String getPowertype() {
		return powertype;
	}

	public void setPowertype(String powertype) {
		this.powertype = powertype == null ? null : powertype.trim();
	}

	public String getPowerbore() {
		return powerbore;
	}

	public void setPowerbore(String powerbore) {
		this.powerbore = powerbore == null ? null : powerbore.trim();
	}

	public String getMainpower() {
		return mainpower;
	}

	public void setMainpower(String mainpower) {
		this.mainpower = mainpower == null ? null : mainpower.trim();
	}

	public String getPowerbuilder() {
		return powerbuilder;
	}

	public void setPowerbuilder(String powerbuilder) {
		this.powerbuilder = powerbuilder == null ? null : powerbuilder.trim();
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm == null ? null : rpm.trim();
	}

	public String getAuxiliarypower() {
		return auxiliarypower;
	}

	public void setAuxiliarypower(String auxiliarypower) {
		this.auxiliarypower = auxiliarypower == null ? null : auxiliarypower.trim();
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed == null ? null : speed.trim();
	}

	public String getShipmanager() {
		return shipmanager;
	}

	public void setShipmanager(String shipmanager) {
		this.shipmanager = shipmanager == null ? null : shipmanager.trim();
	}

	public String getShipoperator() {
		return shipoperator;
	}

	public void setShipoperator(String shipoperator) {
		this.shipoperator = shipoperator == null ? null : shipoperator.trim();
	}

	public String getHatchsize() {
		return hatchsize;
	}

	public void setHatchsize(String hatchsize) {
		this.hatchsize = hatchsize == null ? null : hatchsize.trim();
	}

	public String getLiftingdevices() {
		return liftingdevices;
	}

	public void setLiftingdevices(String liftingdevices) {
		this.liftingdevices = liftingdevices == null ? null : liftingdevices.trim();
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage == null ? null : voltage.trim();
	}

	public String getBoiler() {
		return boiler;
	}

	public void setBoiler(String boiler) {
		this.boiler = boiler == null ? null : boiler.trim();
	}

	public String getBuildplace() {
		return buildplace;
	}

	public void setBuildplace(String buildplace) {
		this.buildplace = buildplace == null ? null : buildplace.trim();
	}

	public String getPowerplace() {
		return powerplace;
	}

	public void setPowerplace(String powerplace) {
		this.powerplace = powerplace == null ? null : powerplace.trim();
	}

	public String getPowerdate() {
		return powerdate;
	}

	public void setPowerdate(String powerdate) {
		this.powerdate = powerdate == null ? null : powerdate.trim();
	}

	public String getBoilerbuild() {
		return boilerbuild;
	}

	public void setBoilerbuild(String boilerbuild) {
		this.boilerbuild = boilerbuild == null ? null : boilerbuild.trim();
	}

	public String getBoilerdate() {
		return boilerdate;
	}

	public void setBoilerdate(String boilerdate) {
		this.boilerdate = boilerdate == null ? null : boilerdate.trim();
	}

	public String getDeckerections() {
		return deckerections;
	}

	public void setDeckerections(String deckerections) {
		this.deckerections = deckerections == null ? null : deckerections.trim();
	}

	public String getRegowner() {
		return regowner;
	}

	public void setRegowner(String regowner) {
		this.regowner = regowner == null ? null : regowner.trim();
	}

	public String getCollectionnum() {
		return collectionnum;
	}

	public void setCollectionnum(String collectionnum) {
		this.collectionnum = collectionnum == null ? null : collectionnum.trim();
	}

	public String getNameen() {
		return nameen;
	}

	public void setNameen(String nameen) {
		this.nameen = nameen == null ? null : nameen.trim();
	}

	public String getFormername() {
		return formername;
	}

	public void setFormername(String formername) {
		this.formername = formername == null ? null : formername.trim();
	}

	public String getFormeren() {
		return formeren;
	}

	public void setFormeren(String formeren) {
		this.formeren = formeren == null ? null : formeren.trim();
	}

	public String getFeedborard() {
		return feedborard;
	}

	public void setFeedborard(String feedborard) {
		this.feedborard = feedborard == null ? null : feedborard.trim();
	}

	public String getHolds() {
		return holds;
	}

	public void setHolds(String holds) {
		this.holds = holds == null ? null : holds.trim();
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement == null ? null : displacement.trim();
	}

	public String getBuckheads() {
		return buckheads;
	}

	public void setBuckheads(String buckheads) {
		this.buckheads = buckheads == null ? null : buckheads.trim();
	}

	public String getBoilerplace() {
		return boilerplace;
	}

	public void setBoilerplace(String boilerplace) {
		this.boilerplace = boilerplace == null ? null : boilerplace.trim();
	}

	public String getManagercountry() {
		return managercountry;
	}

	public void setManagercountry(String managercountry) {
		this.managercountry = managercountry == null ? null : managercountry.trim();
	}

	public String getHullconnections() {
		return hullconnections;
	}

	public void setHullconnections(String hullconnections) {
		this.hullconnections = hullconnections == null ? null : hullconnections.trim();
	}
}