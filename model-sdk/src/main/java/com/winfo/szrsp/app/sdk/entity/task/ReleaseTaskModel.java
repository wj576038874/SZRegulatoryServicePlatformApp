package com.winfo.szrsp.app.sdk.entity.task;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.task
 * @Filename: ReleaseTaskModel
 * @Author: lsj
 * @Date: 2018/3/12  10:16
 * @Description:
 * @Version:
 */
public class ReleaseTaskModel {
    private TaskAssign taskAssign;
    private TaskInfo taskInfo;
    private TaskInfoDetails taskInfoDetails;
    private String taskAssignUuid;

    public String getTaskAssignUuid() {
        return taskAssignUuid;
    }

    public void setTaskAssignUuid(String taskAssignUuid) {
        this.taskAssignUuid = taskAssignUuid;
    }

    public TaskAssign getTaskAssign() {
        return taskAssign;
    }

    public void setTaskAssign(TaskAssign taskAssign) {
        this.taskAssign = taskAssign;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public TaskInfoDetails getTaskInfoDetails() {
        return taskInfoDetails;
    }

    public void setTaskInfoDetails(TaskInfoDetails taskInfoDetails) {
        this.taskInfoDetails = taskInfoDetails;
    }
}
