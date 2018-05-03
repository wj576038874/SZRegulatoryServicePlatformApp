package com.winfo.szrsp.app.mvp.table.psca.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.print.Activity_PrintPdf;
import com.winfo.szrsp.app.sdk.download.httpdownload.DownInfo;
import com.winfo.szrsp.app.sdk.download.httpdownload.HttpDownManager;
import com.winfo.szrsp.app.sdk.download.listener.HttpProgressOnNextListener;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFrom;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromB;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by wly on 2017/12/22.
 *
 */

public class PSCFormBFragment extends Fragment implements View.OnClickListener {

    private Button btn_submit;

    private TableLayout tl_psc_b;

    private TextView et_name_of_ship;
    private TextView et_imo_number;
    private TextView et_date_of_inspection;
    private TextView et_place_of_inspection;
    private TextView et_name;

    private ImageView qm_img;

    private boolean isInitFinish;
    private boolean isLoadData=true;

    private Dialog downloadDialog;

    private String id;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInitFinish=true;

        View view=inflater.inflate(R.layout.fragment_psc_b,null);
        initView(view);
        downloadDialog = DialogUtils.createLoadingDialog(getActivity(),"下载中...");
        initEvent();

        return view;
    }

    private void initEvent() {
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser &&isLoadData && isInitFinish) {
            initData();
        }
    }

    //加载签名图片
    private void downqm(String inspectNo,String autograph,ImageView imageView){
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",inspectNo);
        mapParams.put("autograph",autograph);
        final String params = FastJsonUtil.mapToJsonStr(mapParams);
        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }
        String url=ServerReqAddress.BASE_ADDRESS + "sz/ctPscFromRestService/downAutograph?projectSu=SZMSA&accessToken="+ access_token +"&requestSource=&parameterJson=" +params;
        Glide.with(getActivity()).load(url).into(imageView);
    }

    private void initData() {
        isLoadData=false;
        Bundle bundle=getArguments();
        if (bundle!=null){
            CtPscFromObjectDetail ctPscFromObjectDetail= (CtPscFromObjectDetail) bundle.getSerializable("CtPscFromObjectDetail");
            if (ctPscFromObjectDetail!=null){
                CtPscFrom ctPscFrom=ctPscFromObjectDetail.getCtpscfrom();
                id=ctPscFrom.getInspectNo();
                downqm(id,"org",qm_img);

                et_name_of_ship.setText(ctPscFrom.getShipNameEn());
                et_imo_number.setText(ctPscFrom.getShipImo());
                et_date_of_inspection.setText(ctPscFrom.getInspectDate());
                et_place_of_inspection.setText(ctPscFrom.getBerthCode());
                et_name.setText(ctPscFrom.getCaptainName());


                List<CtPscFromB> detailb=ctPscFromObjectDetail.getListb();
                for (int i=0;i<detailb.size();i++){
                    CtPscFromB ctPscFromB=detailb.get(i);
                    addTableRow(ctPscFromB,i+1);
                }

            }
        }
    }

    private void initView(View view) {

        tl_psc_b=view.findViewById(R.id.tl_psc_b);
        et_name_of_ship=view.findViewById(R.id.et_name_of_ship);
        et_imo_number=view.findViewById(R.id.et_imo_number);
        et_date_of_inspection=view.findViewById(R.id.et_date_of_inspection);
        et_place_of_inspection=view.findViewById(R.id.et_place_of_inspection);
        et_name=view.findViewById(R.id.et_name);
        qm_img=view.findViewById(R.id.qm_img);
        btn_submit=view.findViewById(R.id.btn_submit);
    }

    private void addTableRow( CtPscFromB ctPscFromB,int i) {

        TableRow tableRow=new TableRow(getActivity());
        tableRow.setLayoutParams(new LinearLayout.LayoutParams(android.widget.TableRow.LayoutParams.MATCH_PARENT, android.widget.TableRow.LayoutParams.MATCH_PARENT));

        int height= DimensUtils.dp2px(getActivity(), 40);
        int margin=DimensUtils.dp2px(getActivity(), 0.5f);
        int padding=DimensUtils.dp2px(getActivity(), 2);

        //1
        TextView editText=new TextView(getActivity());
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        android.widget.TableRow.LayoutParams params=new android.widget.TableRow.LayoutParams(0, height);
        params.weight=1;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params.setMargins(margin,margin,margin,margin);
        editText.setLayoutParams(params);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        editText.setText(String.valueOf(i));
        tableRow.addView(editText);

        //2
        android.widget.TableRow.LayoutParams params2=new android.widget.TableRow.LayoutParams(0, height);
        editText=new TextView(getActivity());
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        params2.weight=1;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params2.setMargins(0,margin,margin,margin);
        editText.setLayoutParams(params2);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        editText.setText(ctPscFromB.getDeficiencyCode());//21
        tableRow.addView(editText);

        //3
        android.widget.TableRow.LayoutParams params3=new android.widget.TableRow.LayoutParams(0, height);
        editText=new TextView(getActivity());
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        params3.weight=2;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params3.setMargins(0,margin,margin,margin);
        editText.setLayoutParams(params3);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        editText.setText(ctPscFromB.getDeficiencyNature());//4
        tableRow.addView(editText);

        //4
        editText=new TextView(getActivity());
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        params3.weight=2;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params3.setMargins(0,margin,margin,margin);
        editText.setLayoutParams(params3);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        editText.setText(ctPscFromB.getConcenyionReference());//5
        tableRow.addView(editText);

        //5
        android.widget.TableRow.LayoutParams params4=new android.widget.TableRow.LayoutParams(0, height);
        editText=new TextView(getActivity());
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        params4.weight=1;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params4.setMargins(0,margin,margin,margin);
        editText.setLayoutParams(params4);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        editText.setText(ctPscFromB.getIsResponsible());//23
        tableRow.addView(editText);


        //6
        editText=new TextView(getActivity());
        editText.setSingleLine();
        editText.setBackgroundColor(Color.WHITE);
        params4.weight=1;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        params4.setMargins(0,margin,margin,margin);
        editText.setLayoutParams(params4);
        editText.setPadding(padding,padding,padding,padding);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(13);
        editText.setText(ctPscFromB.getTakenAction());//22
        tableRow.addView(editText);

        tl_psc_b.addView(tableRow);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
                File file = new File(namepath);
                if (file.exists()){
                    //直接打印
                    Intent intent=new Intent(getActivity(), Activity_PrintPdf.class);
                    intent.putExtra("namepath2",namepath);
                    startActivity(intent);
                }else{
                    downloadPDF();
                    downloadDialog.show();
                    httpDownManager.startDown(apkApi);
                }
                break;
        }

    }

    private HttpDownManager httpDownManager = HttpDownManager.getInstance();
    //开始判断本地是否已经下载文档了
    //文件夹名称
    private String path = SDCardUtils.getRootDirectory()+ "/DownloadSZMSA_Doc/";
    //文件名称
    private String namepath = "";
    private DownInfo apkApi;

    private void downloadPDF(){

        String access_token = ACache.get(SzRspApplication.getContext()).getAsString("access_token");
        if(access_token==null||access_token.length()==0){
            access_token="5B3F7BB51BE4C055E050007F0100E58F";
        }

        String name =  "PSCForm"+"_"+id+".pdf";

        namepath = path + name;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("inspectNo",id);
        String params = FastJsonUtil.mapToJsonStr(mapParams);
        apkApi = new DownInfo(ServerReqAddress.BASE_ADDRESS+"sz/ctPscFromRestService/outPutPDF?projectSu=SZMSA&accessToken="+access_token+"&requestSource=&parameterJson=" +params);
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
                Intent intent=new Intent(getActivity(), Activity_PrintPdf.class);
                intent.putExtra("namepath2",namepath);
                startActivity(intent);

            }

            @Override
            public void onError(Throwable e) {
                downloadDialog.dismiss();
                super.onError(e);
                File file = new File(namepath);
                if (file.isFile()) {
                    file.delete();
                }
            }

            @Override
            public void updateProgress(long readLength, long countLength) {

            }
        });
    }




}
