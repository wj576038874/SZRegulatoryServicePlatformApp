package com.winfo.szrsp.app.mvp.table.ssxh.presenter;

import com.winfo.szrsp.app.mvp.table.ssxh.model.IWaterPatrolModel;
import com.winfo.szrsp.app.mvp.table.ssxh.model.WatersPatrolModel;
import com.winfo.szrsp.app.mvp.table.ssxh.view.IWaterPatrolActivity;

/**
 * Created by ChengQi on 2017/12/7.
 *
 */

public class WatersPatrolActivityPresenter {

    private IWaterPatrolModel iWaterPatrolModel;
    private IWaterPatrolActivity iWaterPatrolActivity;

    public WatersPatrolActivityPresenter(IWaterPatrolActivity iWaterPatrolActivity){
        this.iWaterPatrolActivity=iWaterPatrolActivity;
        iWaterPatrolModel=new WatersPatrolModel();
    }

    public void subWaterPatrolData(String qmPath){
        iWaterPatrolActivity.getDialog().show();
        iWaterPatrolModel.subWatersPatrolData(iWaterPatrolActivity.getDialog(), iWaterPatrolActivity.getWaterPatrol(), qmPath,new WatersPatrolModel.OnSubWatersPatrolDataListener() {
            @Override
            public void onSuccess(String msg,String resultData) {
                iWaterPatrolActivity.onSuccess(msg,resultData);
                iWaterPatrolActivity.getDialog().dismiss();
            }

            @Override
            public void onFailure(String msg) {
                iWaterPatrolActivity.onFailure(msg);
                iWaterPatrolActivity.getDialog().dismiss();
            }

            @Override
            public void OnLoginExpired(String msg) {
                iWaterPatrolActivity.loginExpired(msg);

            }
        });

    }


}
