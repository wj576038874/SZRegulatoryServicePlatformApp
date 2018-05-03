package com.winfo.szrsp.app.sdk.http;

/**
 * ProjectName: gdmsaecApp
 * PackageNmae: com.winfo.gdmsaec.app.request
 * Author: wenjie
 * FileName: com.winfo.gdmsaec.app.request.RequestCancelExpection.java
 * Date: 2018-02-09 11:28
 * Description: 请求被取消
 */
public class RequestCancelExpection extends Exception {
    private String msg;

    public RequestCancelExpection() {
    }

    public RequestCancelExpection(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
