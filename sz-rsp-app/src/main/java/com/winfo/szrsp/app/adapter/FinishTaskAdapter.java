package com.winfo.szrsp.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.task.FinishTaskModel;
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
 * Created by wly on 2017/12/15.
 */

public class FinishTaskAdapter extends CommonAdapter<FinishTaskModel> {

    private Context mContext;


    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     */
    public FinishTaskAdapter(Context context, List<FinishTaskModel> datas, int resource) {
        super(context, datas, resource);
        mContext = context;
    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();

    @Override
    public void convert(ViewHolder holder, int position) {
        TextView tv_table_name = holder.getView(R.id.tv_table_name);
        TextView tv_table_path = holder.getView(R.id.tv_table_path);
        final TextView tv_download = holder.getView(R.id.tv_download);
        final TextView tv_watch_pdf = holder.getView(R.id.tv_watch_pdf);
        final ProgressBar bar = holder.getView(R.id.bar_1);
        final FinishTaskModel data = datas.get(position);
        final String table_name = data.getTableName();
        tv_table_name.setText(data.getTableName());
        tv_table_path.setText(data.getShipName());

        //开始判断本地是否已经下载文档了
        //文件夹名称
        String path = SDCardUtils.getRootDirectory() + "/DownloadSZMSA_Doc//";
        //文件名称
        String name = data.getTableName() + "_" + data.getInspectNo() + ".pdf";
        final String namepath = path + name;
        File file = new File(namepath);
        if (file.exists()) {
            bar.setVisibility(View.GONE);
            tv_download.setText("打印文档");
        } else {
            tv_download.setText("下载文档");
        }

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", data.getInspectNo());
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(mContext).getAsString("access_token");
        if (access_token == null || access_token.length() == 0) {
            access_token = "5B3F7BB51BE4C055E050007F0100E58F";
        }

        String method = "";
        if (table_name.contains("大型散货")) {
            method = "ctSpecialShipType0203RestService";
        } else if (table_name.contains("水上巡航")) {
            method = "ctWaterCruiseRecordInfoRestService";
        } else if (table_name.contains("船旗国")) {
            method = "tFlagStateControlRestService";
        } else if (table_name.contains("船舶现场监督报告")) {
            method = "tSiteSupervisionRestService";
        } else if (table_name.contains("本次巡航工作统计")) {
            method = "ctCruiseStatisticsRestService";
        } else if (table_name.contains("电子巡航异常")) {
            method = "ctElectronicCruiseExceptionRestService";
        } else if (table_name.contains("船舶危防现场")) {
            method = "ctSafetySceneInspectRestService";
        } else if (table_name.contains("集装箱危险货物现场检查记录表")) {
            method = "ctDangerPolluteSceneRestService";
        } else if (table_name.contains("集装箱危险货物开箱检查记录表")) {
            method = "ctDangerPolluteOutRestService";
        } else if (table_name.contains("普通货物集装箱开箱检查记录表")) {
            method = "ctGoodSecneOutRestService";
        } else if (table_name.contains("货物集装箱重量验证检查记录表")) {
            method = "ctCaseWeightInspectRestService";
        }


        final DownInfo apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/" + method + "/outPutPDF?projectSu=SZMSA&accessToken=" + access_token + "&requestSource=&parameterJson=" + params);
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
                tv_download.setText("打印文档");
                bar.setVisibility(View.GONE);
                tv_watch_pdf.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                tv_download.setText("下载失败");
                bar.setVisibility(View.GONE);
                tv_watch_pdf.setVisibility(View.GONE);
                deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
                long l = readLength * 100 / countLength;
                tv_download.setText(l + "%" + "，取消下载");
                bar.setMax((int) countLength);
                bar.setProgress((int) readLength);
            }
        });
        //点击下载事件
        tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_download.getText().equals("下载文档")) {
                    httpDownManager.startDown(apkApi);
                } else if (tv_download.getText().toString().contains("取消下载")) {
                    httpDownManager.stopDown(apkApi);
                    bar.setVisibility(View.GONE);
                    tv_download.setText("下载文档");
                } else if (tv_download.getText().equals("下载失败")) {
                    httpDownManager.startDown(apkApi);
                } else if (tv_download.getText().equals("打印文档")) {
                    printPdfIntent(namepath);
                }
            }
        });

        //查看文档
        tv_watch_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWordFileIntent(namepath);
            }
        });

    }

    // Android获取一个用于打开Word文件的intent
    public void getWordFileIntent(String param) {
        try {
            Intent intent = new Intent();
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            File file = new File(param);
            String type = getMIMEType(file);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, type);
            mContext.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showToast(mContext, "请下载相关软件查看");
        }
    }

    //获取文件mimetype
    private String getMIMEType(File file) {
        String type = "";
        String name = file.getName();
        //文件扩展名
        String end = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        if (end.equals("doc")) {
            type = "application/msword";
        } else if (end.equals("docx")) {
            type = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if (end.equals("zip")) {
            type = "application/zip";
        } else if (end.equals("rar")) {
            type = "application/x-rar-compressed";
        } else if (end.equals("pdf")) {
            type = "application/msword";
        } else {
            //如果无法直接打开，跳出列表由用户选择
            type = "*/*";
        }
        return type;
    }


    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    private boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    //跳转到打印页面
    public void printPdfIntent(String namepath) {
        Intent intent = new Intent(mContext, Activity_PrintPdf.class);
        intent.putExtra("namepath2", namepath);
        Activity activity = (Activity) mContext;
        activity.startActivity(intent);
    }


}
