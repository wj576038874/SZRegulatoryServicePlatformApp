package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.mvp.task.view.ExpandableItemAdapter;

/**
 * Created by HoBo on 2018/4/25.
 */

public class BerthLevelItem1 implements MultiItemEntity {
    private String checked;
    private String id;
    private String name;
    private String pid;
    private boolean canClick = true;
    private boolean isChecked;

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

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
