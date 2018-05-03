package com.winfo.szrsp.app.adapter.table;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.ListcbjdjgData;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.adapter.table
 * @Filename: jdbgTableAdapter
 * @Author: lsj
 * @Date: 2017/12/7  10:58
 * @Description:
 * @Version:
 */
public class jdbgTableAdapter extends CommonAdapter<ListcbjdjgData.list> {
    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     */
    public jdbgTableAdapter(Context context, List<ListcbjdjgData.list> datas, int resource) {
        super(context, datas, resource);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        TextView tv_boat_name = holder.getView(R.id.tv_boat_name);
        TextView tv_ferry_name = holder.getView(R.id.tv_ferry_name);
        TextView tv_sj = holder.getView(R.id.tv_sj);
        final TextView tv_download = holder.getView(R.id.tv_download);
        final ProgressBar bar = holder.getView(R.id.bar_1);
        final ListcbjdjgData.list data = datas.get(position);
        tv_boat_name.setText(data.getShipNameCn());
        tv_ferry_name.setText(data.getShipTypeNameCn());
        tv_sj.setText(data.getInspectDate());

        //开始判断本地是否已经下载文档了
        //文件夹名称
        String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
        //文件名称
        String name = data.getShipNameCn()+"_"+data.getInspectNo()+".pdf";
        final String namepath = path + name;

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", data.getInspectNo());
        final String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        //点击下载事件
        String finalAccess_token = access_token;
        final DownInfo apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/tFlagStateControlRestService/outPutPDF?projectSu=SZMSA&accessToken="+ finalAccess_token +"&requestSource=&parameterJson=" +params);
        //文件存储位置
        File outputFile = new File(namepath);
        apkApi.setSavePath(outputFile.getAbsolutePath());
        apkApi.setListener(new HttpProgressOnNextListener() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onStart() {
                tv_download.setText("取消下载");
                bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onComplete() {
                tv_download.setText("查看文档");
                bar.setVisibility(View.GONE);
                getWordFileIntent(namepath);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                tv_download.setText("下载失败");
                bar.setVisibility(View.GONE);
                httpDownManager.stopDown(apkApi);
                deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
                long l = readLength * 100 / countLength;
                tv_download.setText(l+"%"+"，取消下载");
                bar.setMax((int) countLength);
                bar.setProgress((int) readLength);
            }
        });
        tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_download.getText().equals("查看文档")){
                    File file = new File(namepath);
                    if (file.exists()){
                        getWordFileIntent(namepath);
                    }else {
                        httpDownManager.startDown(apkApi);
                    }
                }else if(tv_download.getText().toString().contains("取消下载")){
                    httpDownManager.stopDown(apkApi);
                    bar.setVisibility(View.GONE);
                    tv_download.setText("下载文档");
                }else if(tv_download.getText().equals("下载失败")){
                    httpDownManager.startDown(apkApi);
                }
            }
        });
    }


    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    private boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    // Android获取一个用于打开Word文件的intent
    public  void getWordFileIntent(String param){
        try {
            Intent intent = new Intent();
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            File file =new File(param);
            String type = getMIMEType(file);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, type);
            mContext.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showToast(mContext, "请下载相关软件查看");
        }
    }

    //获取文件mimetype
    private static String getMIMEType(File file){
        String type = "";
        String name = file.getName();
        //文件扩展名
        String end = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        if (end.equals("doc") ){
            type = "application/msword";
        }
        else if(end.equals("docx")) {
            type = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }
        else if(end.equals("zip")) {
            type = "application/zip";
        }
        else if(end.equals("rar")) {
            type = "application/x-rar-compressed";
        }else if(end.equals("pdf")){
            type = "application/msword";
        }
        else {
            //如果无法直接打开，跳出列表由用户选择
            type = "*/*";
        }
        return type;
    }
}
