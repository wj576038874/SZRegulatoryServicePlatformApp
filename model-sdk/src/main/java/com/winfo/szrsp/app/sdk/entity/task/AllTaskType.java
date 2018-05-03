package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by winfo053 on 2018/3/7.
 */

public class AllTaskType implements Serializable {
   private String  checked;	//1
    private String   id	;//RE_00000005
    private String   name;//	涉嫌违法行为实施现场调查取证
    private String   pid;//	RE
    private List<AllTaskType> child= new ArrayList<>();;

    public List<AllTaskType> getChild() {
        return child;
    }

    public void setChild(List<AllTaskType> child) {
        this.child = child;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

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
}
