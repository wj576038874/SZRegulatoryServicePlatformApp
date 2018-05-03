package com.winfo.szrsp.app.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.entity
 * @Filename: HistoryThsy
 * @Author: lsj
 * @Date: 2017/12/21  15:25
 * @Description:
 * @Version:
 */
@Table(name = "historyYhsy")
public class HistoryThsy {
    @Id
    private int id;

    @Column(column = "name")
    private String name;

    @Column(column = "typename")
    private String typename;

    @Column(column = "tel")
    private String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        if (name == null){
            return "";
        }else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
