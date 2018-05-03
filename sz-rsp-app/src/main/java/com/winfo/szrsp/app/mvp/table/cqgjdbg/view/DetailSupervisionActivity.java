package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.present.supervisionPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.view
 * @Filename: DetailSupervisionActivity
 * @Author: lsj
 * @Date: 2017/12/8  13:48
 * @Description:
 * @Version:船旗国监督检查报告
 */
public class DetailSupervisionActivity extends Activity implements ISupervisionActivity{
    private View titie_view;
    private ImageButton back_btn;
    private TextView tv_title;
    private LinearLayout ll_layout;
    private DetailcljdLayout detailcljdLayout;
    private ImageView img_add;
    private ImageView img_del;
    private ImageView qm_img;
    private TextView tv_qm;
    private String qm_path;

    private EditText edt_cm;
    private EditText edt_gj;
    private EditText edt_cbzl;
    private EditText edt_imo;
    private EditText edt_cbbh;
    private EditText edt_cbglr;
    private CheckBox ck_zxjc;
    private EditText edt_zxjc;
    private CheckBox ck_1;
    private CheckBox ck_2;
    private CheckBox ck_3;
    private CheckBox ck_4;
    private CheckBox ck_5;
    private EditText edt_jcjg;
    private EditText edt_fcqz1;
    private EditText edt_fcqz2;
    private Button btn_save;

    private ImageView jcry_qm_img;
    private TextView jcry_tv_qm;
    private String jcry_qm_path;
    private TextView tv_jcsj;
    private supervisionPresenter supervisionPresenter;
    private ScrollView ll_sc;
    private View faile_view;
    private TextView tv_faile;
    private Dialog downloadDialog;
    private TextView tv_find_jcry;
    private TextView tv_jcry_no;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision_detail);
        initView();
        initData();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(namepath);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(DetailSupervisionActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2",namepath);
                    startActivity(intent);
                }else{
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi);
                }
            }
        });
    }

    private void initData() {
        tv_title.setText("船旗国监督检查报告");
        String id = getIntent().getStringExtra("jdbgShipNo");
        supervisionPresenter.findData(id);
    }

    private void initView() {
        downloadDialog = DialogUtils.createLoadingDialog(this,"下载中...");
        supervisionPresenter = new supervisionPresenter(this);
        titie_view = findViewById(R.id.title);
        back_btn = titie_view.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = titie_view.findViewById(R.id.table_titleBar_titleText);
        ll_layout = findViewById(R.id.cljd_layout);
        img_add = findViewById(R.id.add_img);
        img_del= findViewById(R.id.del_img);
        qm_img = findViewById(R.id.qm_img);
        tv_qm = findViewById(R.id.tv_qm);
        jcry_qm_img = findViewById(R.id.img_jcry_qm);
        jcry_tv_qm = findViewById(R.id.tv_jcry);
        tv_jcsj = findViewById(R.id.tv_jcsj);
        edt_cm = findViewById(R.id.edt_cm);
        edt_gj = findViewById(R.id.edt_gj);
        edt_cbzl = findViewById(R.id.edt_cbzl);
        edt_imo = findViewById(R.id.edt_imo);
        edt_cbbh=findViewById(R.id.edt_cbbh);
        edt_cbglr = findViewById(R.id.edt_cbglr);
        ck_zxjc = findViewById(R.id.ck_zxjc);
        edt_zxjc = findViewById(R.id.edt_zxjc);
        ck_1 = findViewById(R.id.ck_1);
        ck_2 = findViewById(R.id.ck_2);
        ck_3 = findViewById(R.id.ck_3);
        ck_4 = findViewById(R.id.ck_4);
        ck_5 = findViewById(R.id.ck_5);
        edt_jcjg = findViewById(R.id.edt_jcjg);
        edt_fcqz1 = findViewById(R.id.edt_fcqz);
        edt_fcqz2 = findViewById(R.id.edt_fcqz2);
        btn_save = findViewById(R.id.btn_save);
        ll_layout = findViewById(R.id.cljd_layout);
        ll_sc = findViewById(R.id.ll_sc);
        faile_view = findViewById(R.id.view_faile);
        tv_faile = faile_view.findViewById(R.id.id_tv_data_load_faild);
        tv_find_jcry=findViewById(R.id.tv_find_jcry);
        tv_jcry_no=findViewById(R.id.tv_jcry_no);
    }

    @Override
    public Dialog getDialog() {
        Dialog dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        return dialog;
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList) {

    }

    @Override
    public void onFaile(String msg) {
        ll_sc.setVisibility(View.GONE);
        faile_view.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
        faile_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getIntent().getStringExtra("jdbgShipNo");
                supervisionPresenter.findData(id);
            }
        });
    }

    @Override
    public void setData(DetailjdbgData data) {
        tv_find_jcry.setText(data.getTflag().getInspectorName());
        tv_jcry_no.setText(data.getTflag().getInspectorCode());
        edt_cm.setText(data.getTflag().getShipNameCn());
        edt_gj.setText(data.getTflag().getRegportName());
        edt_cbzl.setText(data.getTflag().getShipTypeNameCn());
        edt_imo.setText(data.getTflag().getShopId());
        edt_cbbh.setText(data.getTflag().getShipNo());
        edt_cbglr.setText(data.getTflag().getShipManager());
        if (data.getTflag().getIsSpecialInspect().equals("1")){
            ck_zxjc.setChecked(true);
        }else {
            ck_zxjc.setChecked(false);
        }
        edt_zxjc.setText(data.getTflag().getSpecialInspectName());
        String jcxd = data.getTflag().getInspectAction();
        String[] ary = jcxd.split("-");
        if (ary.length >= 1){
            List<String> sts = new ArrayList<>();
            for (int i = 0; i < ary.length; i++) {
                sts.add(ary[i]);
            }
  /*          List<String> sts1 = new ArrayList<>();
            for (int i = 0; i < ary.length; i++) {
                if(ary.length > 1){
                    sts1.add(ary[i]);
                }
            }*/
            for (int i = 0; i < sts.size(); i++) {
                if (sts.get(i).equals("40")){
                    ck_1.setChecked(true);
                }else if (sts.get(i).equals("50")){
                    ck_2.setChecked(true);
                }else if (sts.get(i).equals("70")){
                    ck_3.setChecked(true);
                }else if (sts.get(i).equals("152")){
                    ck_4.setChecked(true);
                }else if (sts.get(i).equals("155")){
                    ck_5.setChecked(true);
                }
            }
        }
        edt_jcjg.setText(data.getTflag().getInspectOrg());
        tv_jcsj.setText(data.getTflag().getInspectDate());

        for (int i = 0; i < data.getList().size(); i++) {
            detailcljdLayout = new DetailcljdLayout(this,data.getList().get(i));
            ll_layout.addView(detailcljdLayout);
        }
        downloadPDF();
        downqm();
    }

    private String zip_path;//解压文件夹路径
    private String unzip_path;//解压后文件夹路径
    private void downqm() {
        String id = getIntent().getStringExtra("jdbgShipNo");
        zip_path = SDCardUtils.getRootDirectory() + "/szmsaec/";
        unzip_path = SDCardUtils.getRootDirectory() + "/szmsaec/scene/"+id+"/";
        File dir = new File(unzip_path);
        if (!dir.exists()) {
            //如果文件夹不存在 则创建
            dir.mkdirs();
        }
        String zip_name = id+".zip";
        final String namepath = zip_path + zip_name;
        final String unzipnamepath = unzip_path;
        File file = new File(namepath);
        if (file.exists()){
            List<File> fileList = getFile(new File(unzip_path));
            if (fileList != null && fileList.size() > 0) {
                String first = unzip_path + fileList.get(0).getName();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(first, options);
                qm_img.setVisibility(View.VISIBLE);
                qm_img.setImageBitmap(bm);

                String img2 = unzip_path + fileList.get(1).getName();
                BitmapFactory.Options options1 = new BitmapFactory.Options();
                options1.inSampleSize = 2;
                Bitmap bm2 = BitmapFactory.decodeFile(img2, options1);
                jcry_qm_img.setVisibility(View.VISIBLE);
                jcry_qm_img.setImageBitmap(bm2);
                jcry_tv_qm.setVisibility(View.GONE);
            }
        }else {
            Map<String, String> mapParams = new HashMap<>();
            mapParams.put("inspectNo", id);
            final String params = FastJsonUtil.mapToJsonStr(mapParams);
            String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
            if(access_token==null||access_token.length()==0){
                access_token="5B3F7BB51BE4C055E050007F0100E58F";
            }
            File outputFile = new File(namepath);
            apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS + "sz/tFlagStateControlRestService/downAutograph?projectSu=SZMSA&accessToken="+ access_token +"&requestSource=&parameterJson=" +params);
            apkApi.setSavePath(outputFile.getAbsolutePath());
            apkApi.setListener(new HttpProgressOnNextListener() {
                @Override
                public void onNext(Object o) {
                }

                @Override
                public void onStart() {
                }

                @Override
                public void onComplete() {
                    Unzip(namepath,unzipnamepath);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    ToastUtils.showToast(DetailSupervisionActivity.this, "签名加载失败！");
                    deleteFile(namepath);
                }

                @Override
                public void updateProgress(long readLength, long countLength) {
                }
            });
            httpDownManager.startDown(apkApi);
        }
    }

    @Override
    public void setDefectCodeData(List<DefectCode> defectCodes) {

    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    @Override
    public void onLoadShipInfoData(TaskInfoDetails infoDetails) {

    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath = "";
    DownInfo apkApi;
    private void downloadPDF(){
        String id = getIntent().getStringExtra("jdbgShipNo");
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", id);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }
        String name = edt_cm.getText()+"_"+id+".pdf";
        namepath = path + name;
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/tFlagStateControlRestService/outPutPDF?projectSu=SZMSA&accessToken="+ access_token +"&requestSource=&parameterJson=" +params);
        File outputFile = new File(namepath);

        apkApi.setSavePath(outputFile.getAbsolutePath());
        apkApi.setListener(new HttpProgressOnNextListener() {
            @Override
            public void onNext(Object o) {
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onComplete() {
                downloadDialog.dismiss();
                Intent intent=new Intent(DetailSupervisionActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2",namepath);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                downloadDialog.dismiss();
                super.onError(e);
                ToastUtils.showToast(SzRspApplication.getContext(),"下载失败！");
                deleteFile(namepath);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
            }
        });
    }

    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    private List<File> mFileList = new ArrayList<>();
    private List<String> qm_name = new ArrayList<>();
    //遍历文件夹下所有的文件
    public List<File> getFile(File file) {
        File[] fileArray = file.listFiles();
        for (File f : fileArray) {
            if (f.isFile()) {
                mFileList.add(f);
            } else {
                getFile(f);
            }
        }
        return mFileList;
    }

    /**
     * 解压文件
     * @param zipFile   解压文件路径
     * @param targetDir  解压后文件保存路径
     */
    private void Unzip(String zipFile, String targetDir) {
        int BUFFER = 4096; //这里缓冲区我们使用4KB，
        String strEntry; //保存每个zip的条目名称
        try {
            BufferedOutputStream dest = null; //缓冲输出流
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry; //每个zip条目的实例
            while ((entry = zis.getNextEntry()) != null) {
                try {
                    int count;
                    byte data[] = new byte[BUFFER];
                    strEntry = entry.getName();
                    qm_name.add(strEntry);
                    File entryFile = new File(targetDir + strEntry);
                    File entryDir = new File(entryFile.getParent());
                    if (!entryDir.exists()) {
                        entryDir.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    dest = new BufferedOutputStream(fos, BUFFER);
                    while ((count = zis.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            zis.close();
            downloadDialog.dismiss();
            if (qm_name.size() != 0){
                setFileName(qm_name);
            }
        } catch (Exception cwj) {
            downloadDialog.dismiss();
            cwj.printStackTrace();
        }
    }

    //显示签名
    private void setFileName(List<String> filenames){
        tv_qm.setVisibility(View.GONE);
        qm_img.setVisibility(View.VISIBLE);
        jcry_qm_img.setVisibility(View.VISIBLE);
        String first = unzip_path+filenames.get(0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(first, options);
        qm_img.setImageBitmap(bm);

        String img2 = unzip_path+filenames.get(1);
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inSampleSize = 2;
        Bitmap bm2 = BitmapFactory.decodeFile(img2, options1);
        jcry_qm_img.setImageBitmap(bm2);
        jcry_tv_qm.setVisibility(View.GONE);
    }
}
