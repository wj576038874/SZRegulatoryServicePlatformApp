package com.winfo.dnc.sdk;

import java.io.Serializable;
import java.util.List;

/**
	 * 
	 * @author King
	 *
	 */
	public class AisData implements Serializable
	{
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 7699084343048831192L;
		// -------------AIS DATAS字段begin-------------
		public String ID;
		//经度
		public double	JD;
		//维度
		public double	WD;
		//船名
		public String CM;
		//呼号
		public String HH;
		//
		public String CJSJ;
		//
		public String SJBS;
		//预到时间
	    public String YDSJ;
		//船迹向
	    public String CJX;
	    // 航向
	 	public double	HX;
	 	// 船长
	 	public double	CC;
	 	// 船宽
	 	public double	CK;
	 	// 船类型
	 	public String CLX;
	 	// IMO
	 	public String IMO;
	 	// 目的地
	 	public String MDD;
	 	// ETA
	 	public String ETA;
	 	// 最大吃水
	 	public double	ZDCS;
	 	// 导航状态
	 	public String DHZT;
	 	// AIS类型
	 	public String AISLX;
	 	// 导航设备类型
	 	public String DHSBLX;
	 	// 船首向
		public int		CSX;
		// 航速
		public double	HS;
		
		public double distance;

		private String ZWCM;

		private int SJLY;

		private List<CheckData> CHECKDATA;

		public void setCHECKDATA(List<CheckData> CHECKDATA) {
			this.CHECKDATA = CHECKDATA;
		}

		public List<CheckData> getCHECKDATA() {
			return CHECKDATA;
		}

		public int getSJLY() {
			return SJLY;
		}

		public void setSJLY(int SJLY) {
			this.SJLY = SJLY;
		}

		public String getZWCM() {
			return ZWCM;
		}

		public void setZWCM(String ZWCM) {
			this.ZWCM = ZWCM;
		}

		public double getDistance()
		{
			return distance;
		}

		public void setDistance(double distance)
		{
			this.distance = distance;
		}

		// -------------------------------------------AIS DATAS字段end------------------------------------------
		public String getYDSJ(){
			if(YDSJ == null){
				YDSJ = "";
			}
			return YDSJ;
		}

		public void setYDSJ(String yDSJ){
			YDSJ = yDSJ;
		}

		public String getCJX(){
			if(CJX == null){
				CJX = "";
			}
			return CJX;
		}

		public void setCJX(String cJX){
			CJX = cJX;
		}
		
		public String getID(){
			if(ID == null){
				ID = "";
			}
			return ID;
		}

		public void setID(String iD){
			ID = iD;
		}

		public double getJD(){
			return JD;
		}

		public void setJD(double jD){
			JD = jD;
		}

		public double getWD(){
			return WD;
		}

		public void setWD(double wD){
			WD = wD;
		}

		public String getCM(){
			if(CM ==null){
				CM = "";
			}
			return CM;
		}

		public void setCM(String cM){
			CM = cM;
		}

		public String getHH(){
			return HH;
		}

		public void setHH(String hH){
			HH = hH;
		}

		public String getCJSJ(){
			if(CJSJ == null){
				CJSJ = "";
			}
			return CJSJ;
		}

		public void setCJSJ(String cJSJ){
			CJSJ = cJSJ;
		}

		public String getSJBS(){
			if(SJBS == null){
				SJBS = "";
			}
			return SJBS;
		}

		public void setSJBS(String sJBS){
			SJBS = sJBS;
		}

		public int getCSX(){
			if(CSX <= 360 && CSX >= 0){
				return CSX;
			}else{
				return 0;
			}
		}

		public void setCSX(int cSX){
			CSX = cSX;
		}

		public double getHS(){
			return HS;
		}

		public void setHS(double hS){
			HS = hS;
		}

		public double getHX(){
			return HX;
		}

		public void setHX(double hX){
			HX = hX;
		}

		public double getCC(){
			return CC;
		}

		public void setCC(double cC){
			CC = cC;
		}

		public double getCK(){
			return CK;
		}

		public void setCK(double cK){
			CK = cK;
		}

		public String getCLX(){
			if(CLX == null){
				CLX = "";
			}
			return CLX;
		}

		public void setCLX(String cLX){
			CLX = cLX;
		}

		public String getIMO(){
			if(IMO == null){
				IMO = "";
			}
			return IMO;
		}

		public void setIMO(String iMO){
			IMO = iMO;
		}

		public String getMDD(){
			if(MDD == null){
				MDD = "";
			}
			return MDD;
		}

		public void setMDD(String mDD){
			MDD = mDD;
		}

		public String getETA(){
			return ETA;
		}

		public void setETA(String eTA){
			ETA = eTA;
		}

		public double getZDCS(){
			return ZDCS;
		}

		public void setZDCS(double zDCS){
			ZDCS = zDCS;
		}

		public String getDHZT(){
			if(DHZT == null){
				DHZT = "";
			}
			return DHZT;
		}

		public void setDHZT(String dHZT){
			DHZT = dHZT;
		}

		public String getAISLX(){
			return AISLX;
		}

		public void setAISLX(String aISLX){
			AISLX = aISLX;
		}

		public String getDHSBLX(){
			if(DHSBLX == null){
				DHSBLX = "";
			}
			return DHSBLX;
		}

		public void setDHSBLX(String dHSBLX){
			DHSBLX = dHSBLX;
		}
		// ----------------------------------AIS DATAS getter and setter 方法 end-----------------------------------

		@Override
		public String toString()
		{
			return "DATAS [ID=" + ID
					+ ", JD="
					+ JD
					+ ", WD="
					+ WD
					+ ", CM="
					+ CM
					+ ", HH="
					+ HH
					+ ", CJSJ="
					+ CJSJ
					+ ", SJBS="
					+ SJBS
					+ ", CSX="
					+ CSX
					+ ", HS="
					+ HS
					+ ", HX="
					+ HX
					+ ", CC="
					+ CC
					+ ", CK="
					+ CK
					+ ", CLX="
					+ CLX
					+ ", IMO="
					+ IMO
					+ ", MDD="
					+ MDD
					+ ", ETA="
					+ ETA
					+ ", ZDCS="
					+ ZDCS
					+ ", DHZT="
					+ DHZT
					+ ", AISLX="
					+ AISLX
					+ ", DHSBLX="
					+ DHSBLX
					+",YDSJ="
					+YDSJ
					+",CJX="
					+CJX
					+ "]";
		}

	}
