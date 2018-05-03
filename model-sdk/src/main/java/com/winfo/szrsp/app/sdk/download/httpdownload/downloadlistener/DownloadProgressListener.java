package com.winfo.szrsp.app.sdk.download.httpdownload.downloadlistener;

/**
 * 成功回调处理
 * Created by pc12 on 2017/2/5.
 */

public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
