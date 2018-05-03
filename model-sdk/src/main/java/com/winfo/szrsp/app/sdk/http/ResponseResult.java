package com.winfo.szrsp.app.sdk.http;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.http
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.http.ResponseResult.java
 * Date: 2017/12/2 11:18
 * Description:
 */

public class ResponseResult<T> {
    /**
     * 服务器返回标识
     */
    private int code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 请求成功之后的数据
     */
    private T datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
