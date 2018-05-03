package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.presenter.CBWFXCPresenter;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;
import com.winfo.szrsp.app.sdk.entity.table.CtSafetySceneInspectDetail;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guan on 2018-01-30.
 */

public class DetailCBWFXCActivity extends Activity implements ICBWFXCView, View.OnClickListener {
    @BindView(R.id.cbwfxcjd_tv_zwcm)
    TextView cbwfxcjd_tv_zwcm;
    @BindView(R.id.cbwfxcjd_tv_imo)
    TextView cbwfxcjd_tv_imo;
    @BindView(R.id.cbwfxcjd_tv_gj)
    TextView cbwfxcjd_tv_gj;
    @BindView(R.id.cbwfxcjd_tv_zd)
    TextView cbwfxcjd_tv_zd;
    @BindView(R.id.cbwfxcjd_tv_place)
    TextView cbwfxcjd_tv_place;
    @BindView(R.id.cbwfxcjd_tv_zydw)
    TextView cbwfxcjd_tv_zydw;
    @BindView(R.id.cbwfxcjd_tv_zybw)
    TextView cbwfxcjd_tv_zybw;
    @BindView(R.id.cbwfxcjd_tv_zhg)
    TextView cbwfxcjd_tv_zhg;
    @BindView(R.id.cbwfxcjd_tv_xhg)
    TextView cbwfxcjd_tv_xhg;
    @BindView(R.id.cbwfxcjd_tv_jcry)
    TextView cbwfxcjd_tv_jcry;
    @BindView(R.id.cbwfxcjd_tv_zylb)
    TextView cbwfxcjd_tv_zylb;
    @BindView(R.id.dxchcjcb_tv_time)
    TextView dxchcjcb_tv_time;

    @BindView(R.id.cbwfxcjd_check_item_1)
    LinearLayout cbwfxcjd_check_item_1;
    @BindView(R.id.cbwfxcjd_check_item_2)
    LinearLayout cbwfxcjd_check_item_2;
    @BindView(R.id.cbwfxcjd_check_item_3)
    LinearLayout cbwfxcjd_check_item_3;
    @BindView(R.id.cbwfxcjd_check_item_4)
    LinearLayout cbwfxcjd_check_item_4;

    @BindView(R.id.ll_sc)
    ScrollView ll_sc;
    @BindView(R.id.view_faile)
    View faile_view;
    private TextView tv_faile;

    @BindView(R.id.cbwfxc_sub)
    Button cbwfxc_sub;
    @BindView(R.id.title)
    View title;
    private ImageButton back_btn;
    private TextView tv_title;


    private CBWFXCPresenter cbwfxcPresenter;
    private Dialog dialog;
    private Dialog downloadDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbwfxcjd_detail);
        ButterKnife.bind(this);
        cbwfxcPresenter=new CBWFXCPresenter(this);
        back_btn = title.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = title.findViewById(R.id.table_titleBar_titleText);
        tv_faile=faile_view.findViewById(R.id.id_tv_data_load_faild);
        initData();
        dialog = DialogUtils.createLoadingDialog(this,"加载中...");
        downloadDialog= DialogUtils.createLoadingDialog(this,"下载中...");
        initEvent();

    }



    private void initData() {
        String id = getIntent().getStringExtra("inspectNo");
        cbwfxcPresenter.getDetailData(id);
    }
    private void initEvent() {
        back_btn.setOnClickListener(this);
        cbwfxc_sub.setOnClickListener(this);
    }

    @Override
    public CtSafeInspectNoticeObject getCtSafeInspectNoticeObject() {
        return null;
    }

    @Override
    public void subSuccessfully(String msg, String inspectNo) {

    }

    @Override
    public void subOnFail(String msg) {
        ll_sc.setVisibility(View.GONE);
        faile_view.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
        faile_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getIntent().getStringExtra("inspectNo");
                cbwfxcPresenter.getDetailData(id);
            }
        });
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }
    CtSafeInspectNoticeObject ctSafeInspectNoticeObject;
    @Override
    public void setDetailData(CtSafeInspectNoticeObject ctSafeInspectNoticeObject) {
        this.ctSafeInspectNoticeObject=ctSafeInspectNoticeObject;

         cbwfxcjd_tv_zwcm.setText(ctSafeInspectNoticeObject.getInfo().getShipNameCn());

         cbwfxcjd_tv_imo.setText(ctSafeInspectNoticeObject.getInfo().getShipNameEn());

         cbwfxcjd_tv_gj.setText(ctSafeInspectNoticeObject.getInfo().getRegportName());

         cbwfxcjd_tv_zd.setText(ctSafeInspectNoticeObject.getInfo().getShipGrosston());

         cbwfxcjd_tv_place.setText(ctSafeInspectNoticeObject.getInfo().getBerthCode());

         cbwfxcjd_tv_zydw.setText(ctSafeInspectNoticeObject.getInfo().getTaskOrg());

         cbwfxcjd_tv_zybw.setText(ctSafeInspectNoticeObject.getInfo().getTaskPort());

         cbwfxcjd_tv_zhg.setText(ctSafeInspectNoticeObject.getInfo().getLastPort());

         cbwfxcjd_tv_xhg.setText(ctSafeInspectNoticeObject.getInfo().getNextPort());

         cbwfxcjd_tv_jcry.setText(ctSafeInspectNoticeObject.getInfo().getInspectorName());

         cbwfxcjd_tv_zylb.setText(ctSafeInspectNoticeObject.getInfo().getTaskTypeName());

         dxchcjcb_tv_time.setText(ctSafeInspectNoticeObject.getInfo().getInspectDate());

        List<CtSafetySceneInspectDetail> list=ctSafeInspectNoticeObject.getDetails();

        for (int i = 0; i <list.size() ; i++) {
            CtSafetySceneInspectDetail ctSafetySceneInspectDetail=list.get(i);
            if(ctSafetySceneInspectDetail.getItemNum().startsWith("1.")){
                CBWFQTFXLayout cbwfqtfxLayout=new CBWFQTFXLayout(this,ctSafetySceneInspectDetail.getItemNum(),
                        ctSafetySceneInspectDetail.getItemName(),
                        ctSafetySceneInspectDetail.getDescribe(),
                        ctSafetySceneInspectDetail.getHandle(),
                        ctSafetySceneInspectDetail.getRectificat(),
                        false);
                cbwfxcjd_check_item_1.addView(cbwfqtfxLayout);

            }else if(ctSafetySceneInspectDetail.getItemNum().startsWith("2.")){
                CBWFQTFXLayout cbwfqtfxLayout=new CBWFQTFXLayout(this,ctSafetySceneInspectDetail.getItemNum(),
                        ctSafetySceneInspectDetail.getItemName(),
                        ctSafetySceneInspectDetail.getDescribe(),
                        ctSafetySceneInspectDetail.getHandle(),
                        ctSafetySceneInspectDetail.getRectificat(),
                        false);
                cbwfxcjd_check_item_2.addView(cbwfqtfxLayout);

            }else if(ctSafetySceneInspectDetail.getItemNum().startsWith("3.")){
                CBWFQTFXLayout cbwfqtfxLayout=new CBWFQTFXLayout(this,ctSafetySceneInspectDetail.getItemNum(),
                        ctSafetySceneInspectDetail.getItemName(),
                        ctSafetySceneInspectDetail.getDescribe(),
                        ctSafetySceneInspectDetail.getHandle(),
                        ctSafetySceneInspectDetail.getRectificat(),
                        false);
                cbwfxcjd_check_item_3.addView(cbwfqtfxLayout);

            }else if(ctSafetySceneInspectDetail.getItemNum().startsWith("4.")){
                CBWFQTFXLayout cbwfqtfxLayout=new CBWFQTFXLayout(this,ctSafetySceneInspectDetail.getItemNum(),
                        ctSafetySceneInspectDetail.getItemName(),
                        ctSafetySceneInspectDetail.getDescribe(),
                        ctSafetySceneInspectDetail.getHandle(),
                        ctSafetySceneInspectDetail.getRectificat(),
                        false);
                cbwfxcjd_check_item_4.addView(cbwfqtfxLayout);

            }
        }

        downloadPDF();
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.cbwfxc_sub:
                File file = new File(namepath_pdf);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(DetailCBWFXCActivity.this, Activity_PrintPdf.class);
                    intent.putExtra("namepath2",namepath_pdf);
                    startActivity(intent);
                }else{
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi_pdf);
                }
                break;
        }
    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    String path_pdf = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    String namepath_pdf = "";
    DownInfo apkApi_pdf;
    private void downloadPDF(){

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo", ctSafeInspectNoticeObject.getInfo().getInspectNo());
        String params = FastJsonUtil.mapToJsonStr(mapParams);

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name =  "船舶危防现场_"+ctSafeInspectNoticeObject.getInfo().getShipNameCn()+"_"+ctSafeInspectNoticeObject.getInfo().getInspectNo()+".pdf";
        namepath_pdf = path_pdf + name;
        apkApi_pdf = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctSafetySceneInspectRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
        File outputFile = new File(namepath_pdf);

        apkApi_pdf.setSavePath(outputFile.getAbsolutePath());
        apkApi_pdf.setListener(new HttpProgressOnNextListener() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onComplete() {
                dialog.dismiss();
                Intent intent=new Intent(DetailCBWFXCActivity.this, Activity_PrintPdf.class);
                intent.putExtra("namepath2",namepath_pdf);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
                super.onError(e);
                File file = new File(namepath_pdf);
                if (file.isFile()) {
                    file.delete();
                }
//                deleteFile(namepath_pdf);
            }

            @Override
            public void updateProgress(long readLength, long countLength) {
//                long l = readLength * 100 / countLength;
            }
        });
    }
}
