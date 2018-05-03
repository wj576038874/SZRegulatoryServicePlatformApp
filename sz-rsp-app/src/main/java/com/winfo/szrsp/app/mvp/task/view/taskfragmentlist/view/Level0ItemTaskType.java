package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.mvp.task.view.Level1Item;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winfo053 on 2018/3/7.
 */

public class Level0ItemTaskType extends AbstractExpandableItem<Level1ItemTaskType> implements MultiItemEntity {
    private String  checked;
    private String   id	;
    private String   name;
    private String   pid;
    private boolean isChecked;
    private boolean canClick;

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

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ExpandableItemTaskTypeAdapter.TYPE_LEVEL_0;
    }
}
