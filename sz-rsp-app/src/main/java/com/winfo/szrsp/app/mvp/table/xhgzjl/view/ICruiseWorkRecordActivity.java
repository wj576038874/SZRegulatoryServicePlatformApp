package com.winfo.szrsp.app.mvp.table.xhgzjl.view;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.table.CruiseShipData;
import com.winfo.szrsp.app.sdk.entity.table.CruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemByNameData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;

import java.util.List;

/**
 * Created by HoBo on 2018/4/13.
 */

public interface ICruiseWorkRecordActivity extends IBaseMvpView {
    Dialog getDialog();

    void OnSuccess(String s, String resultData);

    void OnFaile(String msg);

    CruiseWorkData getData();

    void setDetailData(String msg, CruiseWorkData data);

    void loginExpired(String msg);

    void setInspectionAll(List<TaskInspectionItemData> taskInspectionItemData);

    void onLoadFaild(String msg);

    void setShip(List<CruiseShipData> cruiseShipData);

    void onFindShipFaile(String msg);

    void setInspectionByName(List<TaskInspectionItemByNameData> data);
}
