package com.winfo.szrsp.app.mvp.table.xhgzjl.presenter;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.table.xhgzjl.model.CruiseWorkRecordModel;
import com.winfo.szrsp.app.mvp.table.xhgzjl.model.ICruiseWorkRecordModel;
import com.winfo.szrsp.app.mvp.table.xhgzjl.model.CruiseWorkRecordModel;
import com.winfo.szrsp.app.mvp.table.xhgzjl.model.ICruiseWorkRecordModel;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.CruiseWorkRecordActivity;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.ICruiseWorkRecordActivity;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.mvp.task.model.IAassigTaskModel;
import com.winfo.szrsp.app.mvp.task.model.imp.AssignTaskModel;
import com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view.IAssignTaskView;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;

import java.util.List;

import com.winfo.szrsp.app.mvp.table.xhgzjl.view.ICruiseWorkRecordActivity;

/**
 * Created by HoBo on 2018/4/13.
 */

public class CruiseWorkRecordPresenter extends BaseMvpPresenter<ICruiseWorkRecordActivity> {
    private ICruiseWorkRecordModel cruiseWorkRecordModel;

    public CruiseWorkRecordPresenter() {
        this.cruiseWorkRecordModel = new CruiseWorkRecordModel();
    }

    public void getTaskInspectionAll(Dialog dialog, boolean isShowDialog) {
        if (mView == null) return;
        cruiseWorkRecordModel.getInspectionAll(dialog, isShowDialog, new CruiseWorkRecordModel.OnLoadInspectionAllListener() {
            @Override
            public void onSueecss(List<TaskInspectionItemData> taskInspectionItemData) {
                mView.setInspectionAll(taskInspectionItemData);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadFaild(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void getTaskInspectionByName(String name) {
        if (mView == null) return;
        cruiseWorkRecordModel.getTaskInspectionByName(mView.getDialog(), name, new CruiseWorkRecordModel.OnLoadInspectionByNameListenner() {
            @Override
            public void onSucess(List<TaskInspectionItemByNameData> data) {
                mView.setInspectionByName(data);
            }

            @Override
            public void onFaile(String error) {
                mView.OnFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void subData() {
        if (mView == null) return;
        cruiseWorkRecordModel.subData(mView.getDialog(), mView.getData(), new CruiseWorkRecordModel.OnSubDataListenner() {
            @Override
            public void onSucess(String msg, String resultData) {
                mView.OnSuccess(msg, resultData);
            }

            @Override
            public void onFaile(String error) {
                mView.OnFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void findDataByPrimaryKey(String id) {
        if (mView == null) return;
        cruiseWorkRecordModel.findDataByPrimaryKey(mView.getDialog(), id, new CruiseWorkRecordModel.OnFindDataByPrimaryKeyListenner() {
            @Override
            public void onSucess(String msg, CruiseWorkData detailData) {
                mView.setDetailData(msg, detailData);
            }

            @Override
            public void onFaile(String error) {
                mView.OnFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void findShip() {
        if (mView == null) return;
        cruiseWorkRecordModel.findShip(mView.getDialog(), new CruiseWorkRecordModel.OnFindShipListenner() {
            @Override
            public void onSucess(String msg, List<CruiseShipData> cruiseShipData) {
                mView.setShip(cruiseShipData);
            }

            @Override
            public void onFaile(String error) {
                mView.onFindShipFaile(error);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }
}
