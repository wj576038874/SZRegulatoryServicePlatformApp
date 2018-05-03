package com.winfo.szrsp.app.sdk.entity.task;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.sdk.entity.task
 * @Filename: TaskDeptData
 * @Author: lsj
 * @Date: 2018/3/8  14:08
 * @Description:
 * @Version:
 */
public class TaskDeptData {
    private String id;
    private String name;
    private String pid;
    private String check;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
