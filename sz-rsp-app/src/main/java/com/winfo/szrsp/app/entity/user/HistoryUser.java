package com.winfo.szrsp.app.entity.user;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * @ProjectName: gdmsaecApp
 * @PackageName: com.winfo.gdmsaec.app.adapter
 * @FileName: com.winfo.gdmsaec.app.mvp.model.entity.user.HistoryUser.java
 * @Author: wenjie
 * @Date: 2017-10-20 15:48
 * @Description:
 * @Version:
 */
@Table(name = "HistoryUser")
public class HistoryUser {

    @Id
    private int id;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public HistoryUser(){

    }

    public HistoryUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
