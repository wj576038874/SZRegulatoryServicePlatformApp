package com.winfo.szrsp.app.mvp.table.xhtj.presenter;

import com.winfo.szrsp.app.mvp.table.xhtj.model.CruiseStatisticsModel;
import com.winfo.szrsp.app.mvp.table.xhtj.model.ICruiseStatisticsModel;
import com.winfo.szrsp.app.mvp.table.xhtj.view.ICruiseStatisticsActivity;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.CtCruiseStatisticsObject;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.xhtj.presenter
 * @Filename: CruiseStatisticsPresenter
 * @Author: lsj
 * @Date: 2018/1/23  13:52
 * @Description:
 * @Version:
 */
public class CruiseStatisticsPresenter {
    private ICruiseStatisticsActivity activity;
    private ICruiseStatisticsModel model;

    public CruiseStatisticsPresenter(ICruiseStatisticsActivity activity){
        this.activity = activity;
        model = new CruiseStatisticsModel();
    }

    public void subData(CtCruiseStatisticsObject ctCruiseStatisticsObject){
        model.addData(activity.getDialog(), ctCruiseStatisticsObject, new CruiseStatisticsModel.OnSubCruiseStatisticsListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.onSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.onFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void findData(String id){
        model.findData(activity.getDialog(), id, new CruiseStatisticsModel.OnLoadCruiseStatisticsDataListener() {
            @Override
            public void OnSuccess(CtCruiseStatisticsObject ctCruiseStatisticsObject) {
                activity.setData(ctCruiseStatisticsObject);
            }

            @Override
            public void OnFaile(String msg) {
                activity.onFaile(msg);
            }
        });
    }
}
