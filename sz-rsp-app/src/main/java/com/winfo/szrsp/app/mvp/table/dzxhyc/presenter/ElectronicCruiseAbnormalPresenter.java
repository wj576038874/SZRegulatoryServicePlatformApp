package com.winfo.szrsp.app.mvp.table.dzxhyc.presenter;

import com.winfo.szrsp.app.mvp.table.dzxhyc.model.ElectronicCruiseAbnormalModel;
import com.winfo.szrsp.app.mvp.table.dzxhyc.model.IElectronicCruiseAbnormalModel;
import com.winfo.szrsp.app.mvp.table.dzxhyc.view.IElectronicCruiseAbnormalActivity;

/**
 * Created by wly on 2018/1/26.
 *
 */

public class ElectronicCruiseAbnormalPresenter {

    private IElectronicCruiseAbnormalModel iElectronicCruiseAbnormalModel;
    private IElectronicCruiseAbnormalActivity iElectronicCruiseAbnormalActivity;

    public ElectronicCruiseAbnormalPresenter(IElectronicCruiseAbnormalActivity iElectronicCruiseAbnormalActivity){
        this.iElectronicCruiseAbnormalActivity=iElectronicCruiseAbnormalActivity;
        iElectronicCruiseAbnormalModel=new ElectronicCruiseAbnormalModel();
    }


    public void subCtElectronicCruiseException(){
        iElectronicCruiseAbnormalModel.subCtElectronicCruiseException(iElectronicCruiseAbnormalActivity.getDialog(), iElectronicCruiseAbnormalActivity.getCtElectronicCruiseException(), new ElectronicCruiseAbnormalModel.OnSubCtElectronicCruiseExceptionListener() {
            @Override
            public void onSuccess(String msg, String resultData) {
                iElectronicCruiseAbnormalActivity.onSuccess(msg,resultData);
            }

            @Override
            public void onFailure(String msg) {
                iElectronicCruiseAbnormalActivity.onFailure(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                iElectronicCruiseAbnormalActivity.loginExpired(msg);

            }
        });


    }

}
