package com.winfo.szrsp.app.mvp.table.cbzy.presenter;

import com.winfo.szrsp.app.mvp.table.cbzy.model.DangerousGoodsKaiXiangModel;
import com.winfo.szrsp.app.mvp.table.cbzy.model.IDangerousGoodsKaiXiangModel;
import com.winfo.szrsp.app.mvp.table.cbzy.view.IDangerousGoodsKaiXiangActivity;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;

/**
 * Created by HoBo on 2018/3/8.
 */

public class DangerousGoodsKaiXiangPresenter {
    private IDangerousGoodsKaiXiangActivity activity;
    private IDangerousGoodsKaiXiangModel model;

    public DangerousGoodsKaiXiangPresenter(IDangerousGoodsKaiXiangActivity activity) {
        this.activity = activity;
        this.model = new DangerousGoodsKaiXiangModel();
    }

    public void subData(String path) {
        model.subData(activity.getDialog(), path, activity.getData(), new DangerousGoodsKaiXiangModel.OnSubDataListenner() {
            @Override
            public void onSucess(String msg, String resultData) {
                activity.getDialog().dismiss();
                activity.OnSuccess(msg, resultData);
            }

            @Override
            public void onFaile(String error) {
                activity.getDialog().dismiss();
                activity.OnFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void findDataByPrimaryKey(String id) {
        activity.getDialog().show();
        model.findDataByPrimaryKey(activity.getDialog(), id, new DangerousGoodsKaiXiangModel.OnFindDataByPrimaryKeyListenner() {
            @Override
            public void onSucess(String msg, DangerousGoodsKaiXiangData detailData) {
                activity.getDialog().dismiss();
                activity.setDetailData(msg, detailData);
            }

            @Override
            public void onFaile(String error) {
                activity.getDialog().dismiss();
                activity.OnFaile(error);
            }
        });
    }
}
