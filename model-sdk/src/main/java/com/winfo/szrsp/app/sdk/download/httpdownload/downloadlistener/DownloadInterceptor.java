package com.winfo.szrsp.app.sdk.download.httpdownload.downloadlistener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 成功回调处理
 * Created by pc12 on 2017/2/5.
 */

public class DownloadInterceptor implements Interceptor {
    private DownloadProgressListener listener;
    public DownloadInterceptor(DownloadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

//        String access_token = ACache.get(context).getAsString("access_token");
//        if(access_token==null||access_token.length()==0){
//            access_token="5B3F7BB51BE4C055E050007F0100E58F";
//        }
//        Request request = chain.request();
//        HttpUrl url = request.url().newBuilder()
//                .addQueryParameter("accessToken", "BDE2D5FC-FA493ACE-554B-4A78-922D-174DFB6151A2")
//                .addQueryParameter("projectSu", "SZMSA")
//                .addQueryParameter("requestSource", "")
//                .build();
//        Request request2 = request.newBuilder()
////                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
////                    .addHeader("Connection", "keep-alive")
////                    .method(original.method(), original.body())
//                .url(url)
//                .build();
//
//        Response originalResponse = chain.proceed(request2);
//
//        return originalResponse.newBuilder()
//                .body(new DownloadResponseBody(originalResponse.body(), listener))
//                .build();

        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), listener))
                .build();

    }
}
