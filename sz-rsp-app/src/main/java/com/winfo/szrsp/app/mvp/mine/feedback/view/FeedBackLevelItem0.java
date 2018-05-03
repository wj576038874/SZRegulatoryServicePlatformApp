package com.winfo.szrsp.app.mvp.mine.feedback.view;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by winfo053 on 2018/4/8.
 */

public class FeedBackLevelItem0 extends AbstractExpandableItem<FeedBackLevelItem1> implements MultiItemEntity {

    private String  title;
    private String  time;
    private int status;//0为未处理 1已处理

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
