package com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.presenter;

import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.model.IOrdinaryGoodsKaiXiangModel;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.model.OrdinaryGoodsKaiXiangModel;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.IOrdinaryGoodsKaiXiangActivity;

/**
 * Created by HoBo on 2018/3/9.
 *
 */

public class OrdinaryGoodsKaiXiangPresenter {
    private IOrdinaryGoodsKaiXiangActivity view;
    private IOrdinaryGoodsKaiXiangModel model;

    public OrdinaryGoodsKaiXiangPresenter(IOrdinaryGoodsKaiXiangActivity activity) {
        this.view = activity;
        this.model = new OrdinaryGoodsKaiXiangModel();
    }

    public void subData(String qmPath) {
        view.getDialog().show();
        model.subData(view.getDialog(), view.getData(),qmPath,new OrdinaryGoodsKaiXiangModel.OnSubDataListenner() {
            @Override
            public void onSucess(String msg, String resultData) {
                view.OnSuccess(msg, resultData);
                view.getDialog().dismiss();
            }

            @Override
            public void onFaile(String error) {
                view.OnFaile(error);
                view.getDialog().dismiss();
            }

            @Override
            public void OnLoginExpired(String msg) {
                view.loginExpired(msg);
            }
        });
    }
}
