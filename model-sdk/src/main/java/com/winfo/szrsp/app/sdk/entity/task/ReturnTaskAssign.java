package com.winfo.szrsp.app.sdk.entity.task;

/**
 * Created by Guan on 2017-12-12.
 */

public  class ReturnTaskAssign{
    private  String  taskArriveUserUuid;
    private  String    taskReturnRemark;

    public String getTaskArriveUserUuid() {
        return taskArriveUserUuid;
    }

    public void setTaskArriveUserUuid(String taskArriveUserUuid) {
        this.taskArriveUserUuid = taskArriveUserUuid;
    }

    public String getTaskReturnRemark() {
        return taskReturnRemark;
    }

    public void setTaskReturnRemark(String taskReturnRemark) {
        this.taskReturnRemark = taskReturnRemark;
    }
}