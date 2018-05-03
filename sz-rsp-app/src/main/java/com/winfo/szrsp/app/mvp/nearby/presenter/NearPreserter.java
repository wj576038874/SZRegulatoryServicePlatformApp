package com.winfo.szrsp.app.mvp.nearby.presenter;

import com.winfo.szrsp.app.mvp.nearby.model.INearModel;
import com.winfo.szrsp.app.mvp.nearby.model.NearModel;
import com.winfo.szrsp.app.mvp.nearby.view.INearView;
import com.winfo.szrsp.app.sdk.entity.thys.ThysAndShipData;

/**
 * Created by HoBo on 2018/4/2.
 */

public class NearPreserter {
    private INearView view;
    private INearModel model;

    public NearPreserter(INearView nearByListFragment) {
        this.view = nearByListFragment;
        this.model = new NearModel();
    }

    public void getData(boolean isLogin) {
        model.loadData(isLogin, view.getDialog(), new NearModel.OnloadDatasListener() {
            @Override
            public void onSuccess(ThysAndShipData data) {
                view.OnSuccess(data);
            }

            @Override
            public void onFailure(String error) {
                view.OnFaile(error);
            }
        });
    }
}
