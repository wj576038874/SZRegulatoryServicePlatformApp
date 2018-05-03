package com.winfo.szrsp.app.entity.request;

/**
 * 查询当前屏幕ais数据的请求参数模型
 *
 * @author winfo-wj
 */
public class AisRequestModel extends BaseRequestModel {
    private String pageSize;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

}
