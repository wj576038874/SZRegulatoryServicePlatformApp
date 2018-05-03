package com.winfo.szrsp.app.mvp.table.psca.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromA;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;
import com.winfo.szrsp.app.sdk.utils.ServerReqAddress;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wly on 2017/12/22.
 *
 */

public class PSCFormAFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.psc_a_ck_1_no)
    ImageView psc_a_ck_1_no;
    @BindView(R.id.psc_a_ck_1_yes)
    ImageView psc_a_ck_1_yes;
    @BindView(R.id.psc_a_ck_2_no)
    ImageView psc_a_ck_2_no;
    @BindView(R.id.psc_a_ck_2_yes)
    ImageView psc_a_ck_2_yes;
    @BindView(R.id.psc_a_ck_3_no)
    ImageView psc_a_ck_3_no;
    @BindView(R.id.psc_a_ck_3_yes)
    ImageView psc_a_ck_3_yes;

    @BindView(R.id.qm_img)
    ImageView qm_img;
    @BindView(R.id.qm_img2)
    ImageView qm_img2;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.et_name_of_reporting)
    TextView et_name_of_reporting;

    @BindView(R.id.et_name_of_ship)
    TextView et_name_of_ship;

    @BindView(R.id.et_flag_of_ship)
    TextView et_flag_of_ship;

    @BindView(R.id.et_type_of_ship)
    TextView et_type_of_ship;

    @BindView(R.id.et_a_call_sign)
    TextView et_a_call_sign;

    @BindView(R.id.et_mmsi)
    TextView et_mmsi;

    @BindView(R.id.et_imo_number)
    TextView et_imo_number;

    @BindView(R.id.et_gross_tonnage)
    TextView et_gross_tonnage;

    @BindView(R.id.et_dead_weight)
    TextView et_dead_weight;

    @BindView(R.id.et_year_keel_laid)
    TextView et_year_keel_laid;

    @BindView(R.id.et_date_of_inspection)
    TextView et_date_of_inspection;

    @BindView(R.id.et_place_of_inspection)
    TextView et_place_of_inspection;

    @BindView(R.id.et_classification_society)
    TextView et_classification_society;

    @BindView(R.id.et_date_of_release)
    TextView et_date_of_release;

    @BindView(R.id.et_imo_company_number)
    TextView et_imo_company_number;

    @BindView(R.id.et_particulars_of_company)
    TextView et_particulars_of_company;

    @BindView(R.id.et_name)
    TextView et_name;

    @BindView(R.id.et_issuing_authority_1)
    TextView et_issuing_authority_1;
    @BindView(R.id.et_issuing_authority_2)
    TextView et_issuing_authority_2;
    @BindView(R.id.et_issuing_authority_3)
    TextView et_issuing_authority_3;
    @BindView(R.id.et_issuing_authority_4)
    TextView et_issuing_authority_4;
    @BindView(R.id.et_issuing_authority_5)
    TextView et_issuing_authority_5;
    @BindView(R.id.et_issuing_authority_6)
    TextView et_issuing_authority_6;
    @BindView(R.id.et_issuing_authority_7)
    TextView et_issuing_authority_7;
    @BindView(R.id.et_issuing_authority_8)
    TextView et_issuing_authority_8;
    @BindView(R.id.et_issuing_authority_9)
    TextView et_issuing_authority_9;
    @BindView(R.id.et_issuing_authority_10)
    TextView et_issuing_authority_10;
    @BindView(R.id.et_issuing_authority_11)
    TextView et_issuing_authority_11;
    @BindView(R.id.et_issuing_authority_12)
    TextView et_issuing_authority_12;
    @BindView(R.id.et_issuing_authority_13)
    TextView et_issuing_authority_13;
    @BindView(R.id.et_issuing_authority_14)
    TextView et_issuing_authority_14;
    @BindView(R.id.et_issuing_authority_15)
    TextView et_issuing_authority_15;
    @BindView(R.id.et_issuing_authority_16)
    TextView et_issuing_authority_16;



    @BindView(R.id.et_date_of_issue_1)
    TextView et_date_of_issue_1;
    @BindView(R.id.et_date_of_issue_2)
    TextView et_date_of_issue_2;
    @BindView(R.id.et_date_of_issue_3)
    TextView et_date_of_issue_3;
    @BindView(R.id.et_date_of_issue_4)
    TextView et_date_of_issue_4;
    @BindView(R.id.et_date_of_issue_5)
    TextView et_date_of_issue_5;
    @BindView(R.id.et_date_of_issue_6)
    TextView et_date_of_issue_6;
    @BindView(R.id.et_date_of_issue_7)
    TextView et_date_of_issue_7;
    @BindView(R.id.et_date_of_issue_8)
    TextView et_date_of_issue_8;
    @BindView(R.id.et_date_of_issue_9)
    TextView et_date_of_issue_9;
    @BindView(R.id.et_date_of_issue_10)
    TextView et_date_of_issue_10;
    @BindView(R.id.et_date_of_issue_11)
    TextView et_date_of_issue_11;
    @BindView(R.id.et_date_of_issue_12)
    TextView et_date_of_issue_12;
    @BindView(R.id.et_date_of_issue_13)
    TextView et_date_of_issue_13;
    @BindView(R.id.et_date_of_issue_14)
    TextView et_date_of_issue_14;
    @BindView(R.id.et_date_of_issue_15)
    TextView et_date_of_issue_15;
    @BindView(R.id.et_date_of_issue_16)
    TextView et_date_of_issue_16;



    @BindView(R.id.et_and_expiry_1)
    TextView et_and_expiry_1;
    @BindView(R.id.et_and_expiry_2)
    TextView et_and_expiry_2;
    @BindView(R.id.et_and_expiry_3)
    TextView et_and_expiry_3;
    @BindView(R.id.et_and_expiry_4)
    TextView et_and_expiry_4;
    @BindView(R.id.et_and_expiry_5)
    TextView et_and_expiry_5;
    @BindView(R.id.et_and_expiry_6)
    TextView et_and_expiry_6;
    @BindView(R.id.et_and_expiry_7)
    TextView et_and_expiry_7;
    @BindView(R.id.et_and_expiry_8)
    TextView et_and_expiry_8;
    @BindView(R.id.et_and_expiry_9)
    TextView et_and_expiry_9;
    @BindView(R.id.et_and_expiry_10)
    TextView et_and_expiry_10;
    @BindView(R.id.et_and_expiry_11)
    TextView et_and_expiry_11;
    @BindView(R.id.et_and_expiry_12)
    TextView et_and_expiry_12;
    @BindView(R.id.et_and_expiry_13)
    TextView et_and_expiry_13;
    @BindView(R.id.et_and_expiry_14)
    TextView et_and_expiry_14;
    @BindView(R.id.et_and_expiry_15)
    TextView et_and_expiry_15;
    @BindView(R.id.et_and_expiry_16)
    TextView et_and_expiry_16;



    @BindView(R.id.et_date_1)
    TextView et_date_1;
    @BindView(R.id.et_date_2)
    TextView et_date_2;
    @BindView(R.id.et_date_3)
    TextView et_date_3;
    @BindView(R.id.et_date_4)
    TextView et_date_4;
    @BindView(R.id.et_date_5)
    TextView et_date_5;
    @BindView(R.id.et_date_6)
    TextView et_date_6;
    @BindView(R.id.et_date_7)
    TextView et_date_7;
    @BindView(R.id.et_date_8)
    TextView et_date_8;
    @BindView(R.id.et_date_9)
    TextView et_date_9;
    @BindView(R.id.et_date_10)
    TextView et_date_10;
    @BindView(R.id.et_date_11)
    TextView et_date_11;
    @BindView(R.id.et_date_12)
    TextView et_date_12;
    @BindView(R.id.et_date_13)
    TextView et_date_13;
    @BindView(R.id.et_date_14)
    TextView et_date_14;
    @BindView(R.id.et_date_15)
    TextView et_date_15;
    @BindView(R.id.et_date_16)
    TextView et_date_16;



    @BindView(R.id.et_surveying_authority_1)
    TextView et_surveying_authority_1;
    @BindView(R.id.et_surveying_authority_2)
    TextView et_surveying_authority_2;
    @BindView(R.id.et_surveying_authority_3)
    TextView et_surveying_authority_3;
    @BindView(R.id.et_surveying_authority_4)
    TextView et_surveying_authority_4;
    @BindView(R.id.et_surveying_authority_5)
    TextView et_surveying_authority_5;
    @BindView(R.id.et_surveying_authority_6)
    TextView et_surveying_authority_6;
    @BindView(R.id.et_surveying_authority_7)
    TextView et_surveying_authority_7;
    @BindView(R.id.et_surveying_authority_8)
    TextView et_surveying_authority_8;
    @BindView(R.id.et_surveying_authority_9)
    TextView et_surveying_authority_9;
    @BindView(R.id.et_surveying_authority_10)
    TextView et_surveying_authority_10;
    @BindView(R.id.et_surveying_authority_11)
    TextView et_surveying_authority_11;
    @BindView(R.id.et_surveying_authority_12)
    TextView et_surveying_authority_12;
    @BindView(R.id.et_surveying_authority_13)
    TextView et_surveying_authority_13;
    @BindView(R.id.et_surveying_authority_14)
    TextView et_surveying_authority_14;
    @BindView(R.id.et_surveying_authority_15)
    TextView et_surveying_authority_15;
    @BindView(R.id.et_surveying_authority_16)
    TextView et_surveying_authority_16;




    @BindView(R.id.et_place_1)
    TextView et_place_1;
    @BindView(R.id.et_place_2)
    TextView et_place_2;
    @BindView(R.id.et_place_3)
    TextView et_place_3;
    @BindView(R.id.et_place_4)
    TextView et_place_4;
    @BindView(R.id.et_place_5)
    TextView et_place_5;
    @BindView(R.id.et_place_6)
    TextView et_place_6;
    @BindView(R.id.et_place_7)
    TextView et_place_7;
    @BindView(R.id.et_place_8)
    TextView et_place_8;
    @BindView(R.id.et_place_9)
    TextView et_place_9;
    @BindView(R.id.et_place_10)
    TextView et_place_10;
    @BindView(R.id.et_place_11)
    TextView et_place_11;
    @BindView(R.id.et_place_12)
    TextView et_place_12;
    @BindView(R.id.et_place_13)
    TextView et_place_13;
    @BindView(R.id.et_place_14)
    TextView et_place_14;
    @BindView(R.id.et_place_15)
    TextView et_place_15;
    @BindView(R.id.et_place_16)
    TextView et_place_16;

    @BindView(R.id.et_issuing_office)
    TextView et_issuing_office;
    @BindView(R.id.et_name_2)
    TextView et_name_2;
    @BindView(R.id.et_telephone)
    TextView et_telephone;
    @BindView(R.id.et_telefax)
    TextView et_telefax;
    @BindView(R.id.et_Email)
    TextView et_Email;

    private Dialog downloadDialog;

    private String id;

    private boolean isLoadData=true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_psc_a,null);
        downloadDialog = DialogUtils.createLoadingDialog(getActivity(),"下载中...");
        ButterKnife.bind(this,view);
        initData();
        initEvent();
        return view;
    }


    private void initEvent() {

        btn_submit.setOnClickListener(this);

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

    private void initData(){
        isLoadData=false;

        Bundle bundle=getArguments();
        if (bundle!=null){
            CtPscFromObjectDetail ctPscFromObjectDetail= (CtPscFromObjectDetail) bundle.getSerializable("CtPscFromObjectDetail");

            if (ctPscFromObjectDetail!=null){
                CtPscFrom ctpscfrom=ctPscFromObjectDetail.getCtpscfrom();
                id=ctpscfrom.getInspectNo();
                downqm(id,"captain",qm_img);
                downqm(id,"org",qm_img2);

                et_date_of_inspection.setText(ctpscfrom.getInspectDate());//10
                et_name_of_reporting.setText(ctpscfrom.getAuthorityName());//1
                et_name_of_ship.setText(ctpscfrom.getShipNameEn());//2
                et_flag_of_ship.setText(ctpscfrom.getShipFlag());//3
                et_type_of_ship.setText(ctpscfrom.getShipTypeNameEn());//4
                et_a_call_sign.setText(ctpscfrom.getSignCall());//5a
                et_mmsi.setText(ctpscfrom.getMmsi());//5b
                et_imo_number.setText(ctpscfrom.getShipImo());//6
                et_gross_tonnage.setText(String.valueOf(ctpscfrom.getShipGrosston()));//7
                et_dead_weight.setText(String.valueOf(ctpscfrom.getShipDwt()));//8
                et_year_keel_laid.setText(String.valueOf(ctpscfrom.getKeelYear()));//9
                et_place_of_inspection.setText(ctpscfrom.getBerthCode());//11
                et_classification_society.setText(ctpscfrom.getSociety());//12
                et_date_of_release.setText(ctpscfrom.getReleaseTime());//13
                et_imo_company_number.setText(ctpscfrom.getCompanyImo());//14a
                et_particulars_of_company.setText(ctpscfrom.getCompanyPariculars());//14b
                et_name.setText(ctpscfrom.getCaptainName());//15

                et_issuing_office.setText(ctpscfrom.getIssuingOffice());
                et_name_2.setText(ctpscfrom.getIssuingName());
                et_telephone.setText(ctpscfrom.getOrgTelephone());
                et_telefax.setText(ctpscfrom.getOrgTelefax());
                et_Email.setText(ctpscfrom.getOrgMail());

                //17
                if (ctpscfrom.getIsDefect().equals("0")){//0：no 1:yes
                    psc_a_ck_1_no.setImageResource(R.mipmap.checked);
                }else {
                    psc_a_ck_1_yes.setImageResource(R.mipmap.checked);
                }

                //18
                if ( ctpscfrom.getDetentionMark().equals("0")){//0：no 1:yes
                    psc_a_ck_2_no.setImageResource(R.mipmap.checked);
                }else {
                    psc_a_ck_2_yes.setImageResource(R.mipmap.checked);
                }

                //19
                if (ctpscfrom.getIsDocumentat().equals("0")){
                    psc_a_ck_3_no.setImageResource(R.mipmap.checked);
                }else {
                    psc_a_ck_3_yes.setImageResource(R.mipmap.checked);
                }


                //1
                List<CtPscFromA> ctPscFromAs=ctPscFromObjectDetail.getLista();
                CtPscFromA ctPscFromA=ctPscFromAs.get(0);
                et_issuing_authority_1.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_1.setText(ctPscFromA.getIssueTime());
                et_and_expiry_1.setText(ctPscFromA.getIssuExpiry());
                et_date_1.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_1.setText(ctPscFromA.getSurveyingAuthority());
                et_place_1.setText(ctPscFromA.getSurveyingPlace());

                //2
                ctPscFromA=ctPscFromAs.get(1);
                et_issuing_authority_2.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_2.setText(ctPscFromA.getIssueTime());
                et_and_expiry_2.setText(ctPscFromA.getIssuExpiry());
                et_date_2.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_2.setText(ctPscFromA.getSurveyingAuthority());
                et_place_2.setText(ctPscFromA.getSurveyingPlace());

                //3
                ctPscFromA=ctPscFromAs.get(2);
                et_issuing_authority_3.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_3.setText(ctPscFromA.getIssueTime());
                et_and_expiry_3.setText(ctPscFromA.getIssuExpiry());
                et_date_3.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_3.setText(ctPscFromA.getSurveyingAuthority());
                et_place_3.setText(ctPscFromA.getSurveyingPlace());

                //4
                ctPscFromA=ctPscFromAs.get(3);
                et_issuing_authority_4.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_4.setText(ctPscFromA.getIssueTime());
                et_and_expiry_4.setText(ctPscFromA.getIssuExpiry());
                et_date_4.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_4.setText(ctPscFromA.getSurveyingAuthority());
                et_place_4.setText(ctPscFromA.getSurveyingPlace());

                //5
                ctPscFromA=ctPscFromAs.get(4);
                et_issuing_authority_5.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_5.setText(ctPscFromA.getIssueTime());
                et_and_expiry_5.setText(ctPscFromA.getIssuExpiry());
                et_date_5.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_5.setText(ctPscFromA.getSurveyingAuthority());
                et_place_5.setText(ctPscFromA.getSurveyingPlace());


                //6
                ctPscFromA=ctPscFromAs.get(5);
                et_issuing_authority_6.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_6.setText(ctPscFromA.getIssueTime());
                et_and_expiry_6.setText(ctPscFromA.getIssuExpiry());
                et_date_6.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_6.setText(ctPscFromA.getSurveyingAuthority());
                et_place_6.setText(ctPscFromA.getSurveyingPlace());

                //7
                ctPscFromA=ctPscFromAs.get(6);
                et_issuing_authority_7.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_7.setText(ctPscFromA.getIssueTime());
                et_and_expiry_7.setText(ctPscFromA.getIssuExpiry());
                et_date_7.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_7.setText(ctPscFromA.getSurveyingAuthority());
                et_place_7.setText(ctPscFromA.getSurveyingPlace());

                //8
                ctPscFromA=ctPscFromAs.get(7);
                et_issuing_authority_8.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_8.setText(ctPscFromA.getIssueTime());
                et_and_expiry_8.setText(ctPscFromA.getIssuExpiry());
                et_date_8.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_8.setText(ctPscFromA.getSurveyingAuthority());
                et_place_8.setText(ctPscFromA.getSurveyingPlace());

                //9
                ctPscFromA=ctPscFromAs.get(8);
                et_issuing_authority_9.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_9.setText(ctPscFromA.getIssueTime());
                et_and_expiry_9.setText(ctPscFromA.getIssuExpiry());
                et_date_9.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_9.setText(ctPscFromA.getSurveyingAuthority());
                et_place_9.setText(ctPscFromA.getSurveyingPlace());

                //10
                ctPscFromA=ctPscFromAs.get(9);
                et_issuing_authority_10.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_10.setText(ctPscFromA.getIssueTime());
                et_and_expiry_10.setText(ctPscFromA.getIssuExpiry());
                et_date_10.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_10.setText(ctPscFromA.getSurveyingAuthority());
                et_place_10.setText(ctPscFromA.getSurveyingPlace());

                //11
                ctPscFromA=ctPscFromAs.get(10);
                et_issuing_authority_11.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_11.setText(ctPscFromA.getIssueTime());
                et_and_expiry_11.setText(ctPscFromA.getIssuExpiry());
                et_date_11.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_11.setText(ctPscFromA.getSurveyingAuthority());
                et_place_11.setText(ctPscFromA.getSurveyingPlace());

                //12
                ctPscFromA=ctPscFromAs.get(11);
                et_issuing_authority_12.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_12.setText(ctPscFromA.getIssueTime());
                et_and_expiry_12.setText(ctPscFromA.getIssuExpiry());
                et_date_12.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_12.setText(ctPscFromA.getSurveyingAuthority());
                et_place_12.setText(ctPscFromA.getSurveyingPlace());

                //13
                ctPscFromA=ctPscFromAs.get(12);
                et_issuing_authority_13.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_13.setText(ctPscFromA.getIssueTime());
                et_and_expiry_13.setText(ctPscFromA.getIssuExpiry());
                et_date_13.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_13.setText(ctPscFromA.getSurveyingAuthority());
                et_place_13.setText(ctPscFromA.getSurveyingPlace());

                //14
                ctPscFromA=ctPscFromAs.get(13);
                et_issuing_authority_14.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_14.setText(ctPscFromA.getIssueTime());
                et_and_expiry_14.setText(ctPscFromA.getIssuExpiry());
                et_date_14.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_14.setText(ctPscFromA.getSurveyingAuthority());
                et_place_14.setText(ctPscFromA.getSurveyingPlace());

                //15
                ctPscFromA=ctPscFromAs.get(14);
                et_issuing_authority_15.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_15.setText(ctPscFromA.getIssueTime());
                et_and_expiry_15.setText(ctPscFromA.getIssuExpiry());
                et_date_15.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_15.setText(ctPscFromA.getSurveyingAuthority());
                et_place_15.setText(ctPscFromA.getSurveyingPlace());


                //16
                ctPscFromA=ctPscFromAs.get(15);
                et_issuing_authority_16.setText(ctPscFromA.getIssuingAuthority());
                et_date_of_issue_16.setText(ctPscFromA.getIssueTime());
                et_and_expiry_16.setText(ctPscFromA.getIssuExpiry());
                et_date_16.setText(ctPscFromA.getSurveyDate());
                et_surveying_authority_16.setText(ctPscFromA.getSurveyingAuthority());
                et_place_16.setText(ctPscFromA.getSurveyingPlace());


                if (ctPscFromObjectDetail.getListb().size()>0){
                    btn_submit.setVisibility(View.GONE);
                }else {
                    btn_submit.setVisibility(View.VISIBLE);
                }

            }
        }
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
