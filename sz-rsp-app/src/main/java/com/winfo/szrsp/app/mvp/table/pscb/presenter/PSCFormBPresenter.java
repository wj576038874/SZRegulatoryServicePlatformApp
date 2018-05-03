package com.winfo.szrsp.app.mvp.table.pscb.presenter;

import com.winfo.szrsp.app.mvp.table.psca.model.IPSCFormModel;
import com.winfo.szrsp.app.mvp.table.psca.model.PSCFormModel;
import com.winfo.szrsp.app.mvp.table.pscb.view.IPSCFormBActivity;

/**
 * Created by wly on 2017/12/21.
 *
 */

public class PSCFormBPresenter {

    private IPSCFormModel ipscFormModel;
    private IPSCFormBActivity ipscFormBActivity;

    public PSCFormBPresenter(IPSCFormBActivity ipscFormBActivity){
        this.ipscFormBActivity=ipscFormBActivity;
        ipscFormModel=new PSCFormModel();
    }

    public void subPSCFormBData(String qm_path ,String qm_path2){
        ipscFormBActivity.getDialog().show();
        ipscFormModel.subPSCFormAData(ipscFormBActivity.getDialog(), ipscFormBActivity.getCtPscFromObject(), qm_path , qm_path2 ,new PSCFormModel.OnSubPSCFormADataListener() {
            @Override
            public void onSuccess(String msg, String resultData) {
                ipscFormBActivity.onSuccess(msg,resultData);
                ipscFormBActivity.getDialog().dismiss();
            }

            @Override
            public void onFailure(String msg) {
                ipscFormBActivity.onFailure(msg);
                ipscFormBActivity.getDialog().dismiss();
            }

            @Override
            public void OnLoginExpired(String msg) {
                ipscFormBActivity.loginExpired(msg);
            }
        });
    }



}
