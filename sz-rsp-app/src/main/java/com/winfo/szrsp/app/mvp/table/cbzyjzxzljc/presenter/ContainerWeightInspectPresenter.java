package com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.presenter;

import com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.model.ContainerWeightInspectModel;
import com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.model.IContainerWeightInspectModel;
import com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.view.IContainerWeightInspectActivity;

/**
 * Created by HoBo on 2018/3/9.
 */

public class ContainerWeightInspectPresenter {
    private IContainerWeightInspectActivity view;
    private IContainerWeightInspectModel model;

    public ContainerWeightInspectPresenter(IContainerWeightInspectActivity activity) {
        this.view = activity;
        this.model = new ContainerWeightInspectModel();
    }

    public void subData(String path) {
        view.getDialog().show();
        model.subData(view.getDialog(), view.getData(), path,new ContainerWeightInspectModel.OnSubDataListenner() {
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
