package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HoBo on 2018/4/12.
 */

public class CruiseWorkData implements Serializable {
    private ctCruisingRecore ctCruisingRecore;
    private List<ctCruisingRiskDetail> ctCruisingRiskDetailList;
    private List<ctCruisingSummaryDatail> ctCruisingSummaryDatailList;

    public CruiseWorkData.ctCruisingRecore getCtCruisingRecore() {
        return ctCruisingRecore;
    }

    public void setCtCruisingRecore(CruiseWorkData.ctCruisingRecore ctCruisingRecore) {
        this.ctCruisingRecore = ctCruisingRecore;
    }

    public List<ctCruisingRiskDetail> getCtCruisingRiskDetailList() {
        return ctCruisingRiskDetailList;
    }

    public void setCtCruisingRiskDetailList(List<ctCruisingRiskDetail> ctCruisingRiskDetailList) {
        this.ctCruisingRiskDetailList = ctCruisingRiskDetailList;
    }

    public List<ctCruisingSummaryDatail> getCtCruisingSummaryDatailList() {
        return ctCruisingSummaryDatailList;
    }

    public void setCtCruisingSummaryDatailList(List<ctCruisingSummaryDatail> ctCruisingSummaryDatailList) {
        this.ctCruisingSummaryDatailList = ctCruisingSummaryDatailList;
    }

    public static class ctCruisingRecore {
        private String taskId;
        private String shipName;
        private String mmsi;
        private String lawEnforcementName;
        private String dateYear;
        private String dateMonth;
        private String dateDay;
        private String weekNum;
        private String beginTime;
        private String endTime;
        private String weather;
        private String seaState;
        private String temp1;
        private String temp2;
        private String temp3;
        private String temp4;
        private String temp5;
        private String temp6;
        private String temp7;
        private String temp8;
        private String temp9;
        private String seaRoute;
        private String anchorageArea;
        private String roadWorkArea;
        private String wharf;
        private String mudDumpingArea;
        private String riskWharf;
        private String bridge;
        private String otherArea;
        private String item1;
        private String item2;
        private String item3;
        private String item4;
        private String item5;
        private String item6;
        private String item7;
        private String item8;
        private String otherAbnormal;

        private String cruiseTimes;
        private String cruiseHours;
        private String cruiseSeamile;
        private String lawEnforcementNumber;
        private String garrisonTimes;
        private String garrisonHours;
        private String voyageTimes;
        private String violationTimes;
        private String escortTimes;
        private String salvationTimes;


        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getMmsi() {
            return mmsi;
        }

        public void setMmsi(String mmsi) {
            this.mmsi = mmsi;
        }

        public String getShipName() {
            return shipName;
        }

        public void setShipName(String shipName) {
            this.shipName = shipName;
        }

        public String getLawEnforcementName() {
            return lawEnforcementName;
        }

        public void setLawEnforcementName(String lawEnforcementName) {
            this.lawEnforcementName = lawEnforcementName;
        }

        public String getDateYear() {
            return dateYear;
        }

        public void setDateYear(String dateYear) {
            this.dateYear = dateYear;
        }

        public String getDateMonth() {
            return dateMonth;
        }

        public void setDateMonth(String dateMonth) {
            this.dateMonth = dateMonth;
        }

        public String getDateDay() {
            return dateDay;
        }

        public void setDateDay(String dateDay) {
            this.dateDay = dateDay;
        }

        public String getWeekNum() {
            return weekNum;
        }

        public void setWeekNum(String weekNum) {
            this.weekNum = weekNum;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getSeaState() {
            return seaState;
        }

        public void setSeaState(String seaState) {
            this.seaState = seaState;
        }

        public String getTemp1() {
            return temp1;
        }

        public void setTemp1(String temp1) {
            this.temp1 = temp1;
        }

        public String getTemp2() {
            return temp2;
        }

        public void setTemp2(String temp2) {
            this.temp2 = temp2;
        }

        public String getTemp3() {
            return temp3;
        }

        public void setTemp3(String temp3) {
            this.temp3 = temp3;
        }

        public String getTemp4() {
            return temp4;
        }

        public void setTemp4(String temp4) {
            this.temp4 = temp4;
        }

        public String getTemp5() {
            return temp5;
        }

        public void setTemp5(String temp5) {
            this.temp5 = temp5;
        }

        public String getTemp6() {
            return temp6;
        }

        public void setTemp6(String temp6) {
            this.temp6 = temp6;
        }

        public String getTemp7() {
            return temp7;
        }

        public void setTemp7(String temp7) {
            this.temp7 = temp7;
        }

        public String getTemp8() {
            return temp8;
        }

        public void setTemp8(String temp8) {
            this.temp8 = temp8;
        }

        public String getTemp9() {
            return temp9;
        }

        public void setTemp9(String temp9) {
            this.temp9 = temp9;
        }

        public String getSeaRoute() {
            return seaRoute;
        }

        public void setSeaRoute(String seaRoute) {
            this.seaRoute = seaRoute;
        }

        public String getAnchorageArea() {
            return anchorageArea;
        }

        public void setAnchorageArea(String anchorageArea) {
            this.anchorageArea = anchorageArea;
        }

        public String getRoadWorkArea() {
            return roadWorkArea;
        }

        public void setRoadWorkArea(String roadWorkArea) {
            this.roadWorkArea = roadWorkArea;
        }

        public String getWharf() {
            return wharf;
        }

        public void setWharf(String wharf) {
            this.wharf = wharf;
        }

        public String getMudDumpingArea() {
            return mudDumpingArea;
        }

        public void setMudDumpingArea(String mudDumpingArea) {
            this.mudDumpingArea = mudDumpingArea;
        }

        public String getRiskWharf() {
            return riskWharf;
        }

        public void setRiskWharf(String riskWharf) {
            this.riskWharf = riskWharf;
        }

        public String getBridge() {
            return bridge;
        }

        public void setBridge(String bridge) {
            this.bridge = bridge;
        }

        public String getOtherArea() {
            return otherArea;
        }

        public void setOtherArea(String otherArea) {
            this.otherArea = otherArea;
        }

        public String getItem1() {
            return item1;
        }

        public void setItem1(String item1) {
            this.item1 = item1;
        }

        public String getItem2() {
            return item2;
        }

        public void setItem2(String item2) {
            this.item2 = item2;
        }

        public String getItem3() {
            return item3;
        }

        public void setItem3(String item3) {
            this.item3 = item3;
        }

        public String getItem4() {
            return item4;
        }

        public void setItem4(String item4) {
            this.item4 = item4;
        }

        public String getItem5() {
            return item5;
        }

        public void setItem5(String item5) {
            this.item5 = item5;
        }

        public String getItem6() {
            return item6;
        }

        public void setItem6(String item6) {
            this.item6 = item6;
        }

        public String getItem7() {
            return item7;
        }

        public void setItem7(String item7) {
            this.item7 = item7;
        }

        public String getItem8() {
            return item8;
        }

        public void setItem8(String item8) {
            this.item8 = item8;
        }

        public String getOtherAbnormal() {
            return otherAbnormal;
        }

        public void setOtherAbnormal(String otherAbnormal) {
            this.otherAbnormal = otherAbnormal;
        }

        public String getCruiseTimes() {
            return cruiseTimes;
        }

        public void setCruiseTimes(String cruiseTimes) {
            this.cruiseTimes = cruiseTimes;
        }

        public String getCruiseHours() {
            return cruiseHours;
        }

        public void setCruiseHours(String cruiseHours) {
            this.cruiseHours = cruiseHours;
        }

        public String getCruiseSeamile() {
            return cruiseSeamile;
        }

        public void setCruiseSeamile(String cruiseSeamile) {
            this.cruiseSeamile = cruiseSeamile;
        }

        public String getLawEnforcementNumber() {
            return lawEnforcementNumber;
        }

        public void setLawEnforcementNumber(String lawEnforcementNumber) {
            this.lawEnforcementNumber = lawEnforcementNumber;
        }

        public String getGarrisonTimes() {
            return garrisonTimes;
        }

        public void setGarrisonTimes(String garrisonTimes) {
            this.garrisonTimes = garrisonTimes;
        }

        public String getGarrisonHours() {
            return garrisonHours;
        }

        public void setGarrisonHours(String garrisonHours) {
            this.garrisonHours = garrisonHours;
        }

        public String getVoyageTimes() {
            return voyageTimes;
        }

        public void setVoyageTimes(String voyageTimes) {
            this.voyageTimes = voyageTimes;
        }

        public String getViolationTimes() {
            return violationTimes;
        }

        public void setViolationTimes(String violationTimes) {
            this.violationTimes = violationTimes;
        }

        public String getEscortTimes() {
            return escortTimes;
        }

        public void setEscortTimes(String escortTimes) {
            this.escortTimes = escortTimes;
        }

        public String getSalvationTimes() {
            return salvationTimes;
        }

        public void setSalvationTimes(String salvationTimes) {
            this.salvationTimes = salvationTimes;
        }
    }

    public static class ctCruisingRiskDetail {
        private String taskTypeDetail;
        private String theDecision;
        private String taskTypeDetailCode;
        private String theDecisionCode;

        public String getTaskTypeDetail() {
            return taskTypeDetail;
        }

        public void setTaskTypeDetail(String taskTypeDetail) {
            this.taskTypeDetail = taskTypeDetail;
        }

        public String getTheDecision() {
            return theDecision;
        }

        public void setTheDecision(String theDecision) {
            this.theDecision = theDecision;
        }

        public String getTaskTypeDetailCode() {
            return taskTypeDetailCode;
        }

        public void setTaskTypeDetailCode(String taskTypeDetailCode) {
            this.taskTypeDetailCode = taskTypeDetailCode;
        }

        public String getTheDecisionCode() {
            return theDecisionCode;
        }

        public void setTheDecisionCode(String theDecisionCode) {
            this.theDecisionCode = theDecisionCode;
        }
    }

    public static class ctCruisingSummaryDatail {
        private String dateTime;
        private String cruisingContent;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getCruisingContent() {
            return cruisingContent;
        }

        public void setCruisingContent(String cruisingContent) {
            this.cruisingContent = cruisingContent;
        }
    }
}
