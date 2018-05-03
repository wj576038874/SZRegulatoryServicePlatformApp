package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Guan on 2017-12-10.
 */

public class TaskDetails implements Serializable {


    private List<TaskAssign> taskAssignList;

    private TaskInfo taskInfo;
    private List<TaskInfoDetails>  taskInfoDetailsList	;
    private List<TaskResources>  taskResourcesList;
  //  private List<TaskType> taskTypeList;
//    public List<TaskType> getTaskTypeList() {
//        return taskTypeList;
//    }
//
//    public void setTaskTypeList(List<TaskType> taskTypeList) {
//        taskTypeList = taskTypeList;
//    }
//
//
    public List<TaskAssign> getTaskAssignList() {
        return taskAssignList;
    }

    public void setTaskAssignList(List<TaskAssign> taskAssignList) {
        this.taskAssignList = taskAssignList;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public List<TaskInfoDetails> getTaskInfoDetailsList() {
        return taskInfoDetailsList;
    }

    public void setTaskInfoDetailsList(List<TaskInfoDetails> taskInfoDetailsList) {
        this.taskInfoDetailsList = taskInfoDetailsList;
    }

    public List<TaskResources> getTaskResourcesList() {
        return taskResourcesList;
    }

    public void setTaskResourcesList(List<TaskResources> taskResourcesList) {
        this.taskResourcesList = taskResourcesList;
    }
}
