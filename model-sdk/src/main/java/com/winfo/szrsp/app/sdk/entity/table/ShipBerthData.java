package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * Created by HoBo on 2018/4/25.
 */

public class ShipBerthData {

    /**
     * checked :
     * id : 140400005
     * name : 机场油码头
     * pid : 1404
     */

    private String checked;
    private String id;
    private String name;
    private String pid;
    private List<ShipBerthData> child;
    private boolean isChecked;
    private boolean canClick = true;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public List<ShipBerthData> getChild() {
        return child;
    }

    public void setChild(List<ShipBerthData> child) {
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
