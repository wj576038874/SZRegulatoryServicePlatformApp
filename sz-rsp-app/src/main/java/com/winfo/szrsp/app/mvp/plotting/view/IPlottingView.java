package com.winfo.szrsp.app.mvp.plotting.view;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.plotting.PlottingListData;


public interface IPlottingView extends IBaseMvpView {
    Dialog getDailog();
    void showOnFaile(String msg);
    void notFound();
    void setPlottingListData(PlottingListData data);
    void loginExpired(String msg);

    void deleteSuccess(String msg);

    void deleteFaile(String msg);

    void addSuccess(String msg);

    void addFaile(String msg);

    void editSuccess(String msg);

    void editFaile(String msg);
}
