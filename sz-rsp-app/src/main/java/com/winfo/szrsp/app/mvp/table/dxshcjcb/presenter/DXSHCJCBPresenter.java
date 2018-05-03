package com.winfo.szrsp.app.mvp.table.dxshcjcb.presenter;

import com.winfo.szrsp.app.mvp.table.dxshcjcb.model.DXSHCJCBModel;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.model.IDXSHCJCBModel;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.view.IDXSHCJCBView;

/**
 * Created by Guan on 2017-12-06.
 */

public class DXSHCJCBPresenter {

    private IDXSHCJCBModel model;

    private IDXSHCJCBView view;

    public DXSHCJCBPresenter(IDXSHCJCBView idxshcjcbView){
        this.model=new DXSHCJCBModel();
        this.view=idxshcjcbView;
    }
    public void subData(){

        model.subData( view.getDialog(),view.getCtSpecialShipType0203(), new DXSHCJCBModel.OnSubDataListenner() {
            @Override
            public void onSucess(String msg,String resultData) {
                view.subSuccessfully(msg,resultData);
            }

            @Override
            public void onFaile(String error) {
                view.subOnFail(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                view.loginExpired(msg);
            }
        });

    }
}
