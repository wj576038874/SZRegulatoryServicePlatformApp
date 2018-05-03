package com.winfo.szrsp.app.mvp.table.kxjc.presenter;

import com.winfo.szrsp.app.mvp.table.kxjc.model.IKxjcModel;
import com.winfo.szrsp.app.mvp.table.kxjc.model.KxjcModel;
import com.winfo.szrsp.app.mvp.table.kxjc.view.IKxjcActivity;
import com.winfo.szrsp.app.sdk.entity.table.KxjcData;
import com.winfo.szrsp.app.sdk.entity.task.FourTableSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;

/**
 * Created by HoBo on 2018/3/26.
 */

public class KxjcPresenter {
    private IKxjcActivity view;
    private IKxjcModel model;

    public KxjcPresenter(IKxjcActivity activity) {
        this.view = activity;
        this.model = new KxjcModel();
    }

    public void subData() {
        model.subData(view.getDialog(), view.getData(), new KxjcModel.OnSubDataListenner() {
            @Override
            public void onSucess(String msg, FourTableSubData data) {
                view.OnSuccess(msg, data);
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
