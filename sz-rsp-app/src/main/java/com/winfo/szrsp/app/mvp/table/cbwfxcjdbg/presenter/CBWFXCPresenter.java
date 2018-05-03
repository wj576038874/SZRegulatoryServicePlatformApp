package com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.presenter;

import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.model.CBWFXCModel;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.model.ICBWFXCModel;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view.ICBWFXCView;
import com.winfo.szrsp.app.sdk.entity.table.CtSafeInspectNoticeObject;

/**
 * Created by Guan on 2018-01-29.
 */

public class CBWFXCPresenter {
    private ICBWFXCModel model;
    private ICBWFXCView view;
    public CBWFXCPresenter(ICBWFXCView icbwfxcView){
        this.model=new CBWFXCModel();
        this.view=icbwfxcView;
    }
    public void subData(){

        model.subData( view.getDialog(),view.getCtSafeInspectNoticeObject(), new CBWFXCModel.OnSubDataListenner() {
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

    public void getDetailData(String id){

        model.getDetailData( view.getDialog(),id, new CBWFXCModel.OnGetDetailDataListenner() {
            @Override
            public void onSucess(CtSafeInspectNoticeObject ctSafeInspectNoticeObject) {
                view.setDetailData(ctSafeInspectNoticeObject);
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
