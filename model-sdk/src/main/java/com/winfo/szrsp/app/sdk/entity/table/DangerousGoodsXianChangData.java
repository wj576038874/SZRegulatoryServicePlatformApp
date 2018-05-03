package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HoBo on 2018/3/12.
 */

public class DangerousGoodsXianChangData implements Serializable {
    private ctDangerPolluteScene ctDangerPolluteScene;
    private List<ctDangerPolluteSceneDetail> ctDangerPolluteSceneDetail;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public DangerousGoodsXianChangData.ctDangerPolluteScene getCtDangerPolluteScene() {
        return ctDangerPolluteScene;
    }

    public void setCtDangerPolluteScene(DangerousGoodsXianChangData.ctDangerPolluteScene ctDangerPolluteScene) {
        this.ctDangerPolluteScene = ctDangerPolluteScene;
    }

    public List<DangerousGoodsXianChangData.ctDangerPolluteSceneDetail> getCtDangerPolluteSceneDetail() {
        return ctDangerPolluteSceneDetail;
    }

    public void setCtDangerPolluteSceneDetail(List<DangerousGoodsXianChangData.ctDangerPolluteSceneDetail> ctDangerPolluteSceneDetail) {
        this.ctDangerPolluteSceneDetail = ctDangerPolluteSceneDetail;
    }

    public static class ctDangerPolluteScene {
        private String inspectNo;
        private String shipNameCn;
        private String shipNameEn;
        private String regportName;
        private String shipGrosston;
        private String buildDate;
        private String flowTo;
        private String lastPort;
        private String nextPort;
        private String taskPort;
        private String route;
        private String agent;
        private String berthCode;
        private String companyName;
        private String inspector;
        private String inspectDate;

        public String getInspectNo() {
            return inspectNo;
        }

        public void setInspectNo(String inspectNo) {
            this.inspectNo = inspectNo;
        }

        public String getShipNameCn() {
            return shipNameCn;
        }

        public void setShipNameCn(String shipNameCn) {
            this.shipNameCn = shipNameCn;
        }

        public String getShipNameEn() {
            return shipNameEn;
        }

        public void setShipNameEn(String shipNameEn) {
            this.shipNameEn = shipNameEn;
        }

        public String getRegportName() {
            return regportName;
        }

        public void setRegportName(String regportName) {
            this.regportName = regportName;
        }

        public String getShipGrosston() {
            return shipGrosston;
        }

        public void setShipGrosston(String shipGrosston) {
            this.shipGrosston = shipGrosston;
        }

        public String getBuildDate() {
            return buildDate;
        }

        public void setBuildDate(String buildDate) {
            this.buildDate = buildDate;
        }

        public String getFlowTo() {
            return flowTo;
        }

        public void setFlowTo(String flowTo) {
            this.flowTo = flowTo;
        }

        public String getLastPort() {
            return lastPort;
        }

        public void setLastPort(String lastPort) {
            this.lastPort = lastPort;
        }

        public String getNextPort() {
            return nextPort;
        }

        public void setNextPort(String nextPort) {
            this.nextPort = nextPort;
        }

        public String getTaskPort() {
            return taskPort;
        }

        public void setTaskPort(String taskPort) {
            this.taskPort = taskPort;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getBerthCode() {
            return berthCode;
        }

        public void setBerthCode(String berthCode) {
            this.berthCode = berthCode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getInspector() {
            return inspector;
        }

        public void setInspector(String inspector) {
            this.inspector = inspector;
        }

        public String getInspectDate() {
            return inspectDate;
        }

        public void setInspectDate(String inspectDate) {
            this.inspectDate = inspectDate;
        }
    }

    public static class ctDangerPolluteSceneDetail {
        private String containerNumber;
        private String inspectCode;
        private String describe;
        private String handle;
        private String rectificat;

        public String getContainerNumber() {
            return containerNumber;
        }

        public void setContainerNumber(String containerNumber) {
            this.containerNumber = containerNumber;
        }

        public String getInspectCode() {
            return inspectCode;
        }

        public void setInspectCode(String inspectCode) {
            this.inspectCode = inspectCode;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public String getRectificat() {
            return rectificat;
        }

        public void setRectificat(String rectificat) {
            this.rectificat = rectificat;
        }
    }
}
