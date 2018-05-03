package com.winfo.szrsp.app.mvp.exceptionship.presenter;

import com.winfo.szrsp.app.mvp.exceptionship.model.ExceptionShipModel;
import com.winfo.szrsp.app.mvp.exceptionship.model.IExceptionShipModel;
import com.winfo.szrsp.app.mvp.exceptionship.view.IExceptionShipView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShipList;

/**
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipPresenter {
    private IExceptionShipView activity;
    private IExceptionShipModel model;
    public ExceptionShipPresenter(IExceptionShipView activity){
        this.activity = activity;
        this.model = new ExceptionShipModel();
    }
    public  void getExceptionShipListData(String pageNum,String pageSize,boolean bol){

        model.getExceptionShipListData(activity.getDailog(), pageNum, pageSize, bol, new ExceptionShipModel.OnExceptionShipListListener()
        {
            @Override
            public void OnSuccess(ExceptionShipList data) {
                activity.setExceptionShipListData(data);
            }

            @Override
            public void NotFound() {
                activity.notFound();
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });


    }

}
