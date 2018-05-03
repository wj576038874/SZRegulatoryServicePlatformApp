package com.winfo.szrsp.app.mvp.table.psca.presenter;

import com.winfo.szrsp.app.mvp.table.psca.model.IPSCFormModel;
import com.winfo.szrsp.app.mvp.table.psca.model.PSCFormModel;
import com.winfo.szrsp.app.mvp.table.psca.view.IPSCFormAActivity;

/**
 * Created by wly on 2017/12/20.
 *
 */

public class PSCFormAPresenter {

    private IPSCFormAActivity ipscFormAActivity;
    private IPSCFormModel ipscFormAModel;

    public PSCFormAPresenter(IPSCFormAActivity ipscFormAActivity){
        this.ipscFormAActivity=ipscFormAActivity;
        ipscFormAModel=new PSCFormModel();
    }


    public void subPSCFormAData(String qm_path ,String qm_path2){
        ipscFormAActivity.getDialog().show();
        ipscFormAModel.subPSCFormAData(ipscFormAActivity.getDialog(), ipscFormAActivity.getCtPscFromObject(),qm_path,qm_path2, new PSCFormModel.OnSubPSCFormADataListener() {
            @Override
            public void onSuccess(String msg, String resultData) {
                ipscFormAActivity.onSuccess(msg,resultData);
                ipscFormAActivity.getDialog().dismiss();
            }

            @Override
            public void onFailure(String msg) {
                ipscFormAActivity.onFailure(msg);
                ipscFormAActivity.getDialog().dismiss();
            }

            @Override
            public void OnLoginExpired(String msg) {
                ipscFormAActivity.loginExpired(msg);
            }
        });
    }


}
