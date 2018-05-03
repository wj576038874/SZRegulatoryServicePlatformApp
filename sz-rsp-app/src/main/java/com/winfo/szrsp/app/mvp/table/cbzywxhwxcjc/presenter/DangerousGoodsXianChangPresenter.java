package com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.presenter;

import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.model.DangerousGoodsXianChangModel;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.model.IDangerousGoodsXianChangModel;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.IDangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.DetailDangerousGoodsXianChangData;

/**
 * Created by HoBo on 2018/3/12.
 */

public class DangerousGoodsXianChangPresenter {
    private IDangerousGoodsXianChangActivity view;
    private IDangerousGoodsXianChangModel model;

    public DangerousGoodsXianChangPresenter(IDangerousGoodsXianChangActivity activity) {
        this.view = activity;
        this.model = new DangerousGoodsXianChangModel();
    }

    public void subData() {
        model.subData(view.getDialog(), view.getData(), new DangerousGoodsXianChangModel.OnSubDataListenner() {
            @Override
            public void onSucess(String msg, String resultData) {
                view.OnSuccess(msg, resultData);
            }

            @Override
            public void onFaile(String error) {
                view.OnFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                view.loginExpired(msg);
            }
        });
    }

    public void findDataByPrimaryKey(String id) {
        model.findDataByPrimaryKey(view.getDialog(), id, new DangerousGoodsXianChangModel.OnFindDataByPrimaryKeyListenner() {
            @Override
            public void onSucess(String msg, DangerousGoodsXianChangData detailData) {
                view.setDetailData(msg, detailData);
            }

            @Override
            public void onFaile(String error) {
                view.OnFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                view.loginExpired(msg);
            }
        });
    }
}
