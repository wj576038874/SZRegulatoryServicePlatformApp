package com.winfo.szrsp.app.sdk.entity;

import com.winfo.szrsp.app.sdk.entity.shipdata.ShipInfo;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.entity
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.entity.Pagination.java
 * Date: 2017/11/25 14:52
 * Description: 分页查询船舶信息
 */

public class Pagination {

    /**
     * 数据总数
     */
    private int total;

    /**
     * 页面大小
     */
    private int rowNum;

    /**
     * 当前页码
     */
    private int page;

    /**
     * 集合数据
     */
    private List<ShipInfo> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ShipInfo> getRows() {
        return rows;
    }

    public void setRows(List<ShipInfo> rows) {
        this.rows = rows;
    }
}
