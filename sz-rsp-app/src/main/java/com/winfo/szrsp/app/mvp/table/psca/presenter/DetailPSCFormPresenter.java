package com.winfo.szrsp.app.mvp.table.psca.presenter;

import com.winfo.szrsp.app.mvp.table.psca.model.DetailPSCFormModel;
import com.winfo.szrsp.app.mvp.table.psca.model.IDetailPSCFormModel;
import com.winfo.szrsp.app.mvp.table.psca.view.IDetailPSCFormActivity;
import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObjectDetail;

/**
 * Created by wly on 2017/12/22.
 *
 */

public class DetailPSCFormPresenter {

    private IDetailPSCFormModel iDetailPSCFormModel;
    private IDetailPSCFormActivity iDetailPSCFormActivity;

    public DetailPSCFormPresenter(IDetailPSCFormActivity iDetailPSCFormActivity){
        this.iDetailPSCFormActivity=iDetailPSCFormActivity;
        iDetailPSCFormModel=new DetailPSCFormModel();
    }


    public void loadPSCFormDetailData(String inspectNo){
        iDetailPSCFormModel.loadPSCFormDetailData(iDetailPSCFormActivity.getDialog(), inspectNo, new DetailPSCFormModel.OnLoadPSCFormDetailDataListener() {
            @Override
            public void onSuccess(CtPscFromObjectDetail ctPscFromObjectDetail) {
                iDetailPSCFormActivity.onSuccess(ctPscFromObjectDetail);
            }

            @Override
            public void onFailure(String msg) {
                iDetailPSCFormActivity.onFailure(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                iDetailPSCFormActivity.loginExpired(msg);
            }
        });


    }


}
