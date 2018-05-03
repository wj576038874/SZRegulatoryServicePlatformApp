package com.winfo.szrsp.app.mvp.table.ssxh.presenter;

import com.winfo.szrsp.app.mvp.table.ssxh.model.DetailWatersPatrolModel;
import com.winfo.szrsp.app.mvp.table.ssxh.model.IDetailWatersPatrolModel;
import com.winfo.szrsp.app.mvp.table.ssxh.view.IDetailWatersPatrolActivity;
import com.winfo.szrsp.app.sdk.entity.table.CtWaterCruiseRecordObject;

/**
 * Created by ChengQi on 2017/12/9.
 *
 */

public class DetailWatersPatrolPresenter {

    private IDetailWatersPatrolModel iDetailWatersPatrolModel;
    private IDetailWatersPatrolActivity iDetailWatersPatrolActivity;

    public DetailWatersPatrolPresenter(IDetailWatersPatrolActivity iDetailWatersPatrolActivity){
        this.iDetailWatersPatrolActivity=iDetailWatersPatrolActivity;
        iDetailWatersPatrolModel=new DetailWatersPatrolModel();
    }

    public void loadDetailWatersPatrolData(String inspectNo){
        iDetailWatersPatrolModel.findWatersPatrolData(iDetailWatersPatrolActivity.getDialog(), inspectNo, new DetailWatersPatrolModel.OnLoadWatersPatrolDataListener() {
            @Override
            public void onSuccess(CtWaterCruiseRecordObject ctWaterCruiseRecordObject) {
                iDetailWatersPatrolActivity.onSuccess(ctWaterCruiseRecordObject);
            }

            @Override
            public void onFailure(String msg) {
                iDetailWatersPatrolActivity.onFailure(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                iDetailWatersPatrolActivity.loginExpired(msg);
            }
        });

    }


}
