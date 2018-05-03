package com.winfo.szrsp.app.sdk.entity.task;

/**
 * Created by Guan on 2017-12-12.
 */

public class TaskAcceptData {
//   private List<TaskAssign> taskAssign;
//    private List<ReturnTaskAssign> returnTaskAssign;
    private String taskId;//	201712120913106205

    private String lawEnforcementCar;
    private String lawEnforcementShip;

    private String uuid	;//5FF4EB94DBF64267E050007F01006CEC,577CBB069DDACDBBE050007F01000CA0

    public String getLawEnforcementCar() {
        return lawEnforcementCar;
    }

    public void setLawEnforcementCar(String lawEnforcementCar) {
        this.lawEnforcementCar = lawEnforcementCar;
    }

    public String getLawEnforcementShip() {
        return lawEnforcementShip;
    }

    public void setLawEnforcementShip(String lawEnforcementShip) {
        this.lawEnforcementShip = lawEnforcementShip;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    //    private List<TaskResources> taskResources;

//    public List<TaskAssign> getTaskAssign() {
//        return taskAssign;
//    }
//
//    public void setTaskAssign(List<TaskAssign> acceptTaskAssign) {
//        this.taskAssign = acceptTaskAssign;
//    }

//    public List<ReturnTaskAssign> getReturnTaskAssign() {
//        return returnTaskAssign;
//    }
//
//    public void setReturnTaskAssign(List<ReturnTaskAssign> returnTaskAssign) {
//        this.returnTaskAssign = returnTaskAssign;
//    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

//    public List<TaskResources> getTaskResources() {
//        return taskResources;
//    }
//
//    public void setTaskResources(List<TaskResources> taskResources) {
//        this.taskResources = taskResources;
//    }



}
