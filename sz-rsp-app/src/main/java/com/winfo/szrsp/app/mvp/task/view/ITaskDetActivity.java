package com.winfo.szrsp.app.mvp.task.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.task.TaskAcceptData;
import com.winfo.szrsp.app.sdk.entity.task.TaskDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;

import java.util.List;

/**
 * Created by Guan on 2017-12-10.
 */

public interface ITaskDetActivity {

    void showOnFaile(String msg);
    Dialog getDailog();
    void setTaskDetData(TaskDetails data);



    void rejectSucceed(String msg);
    void rejectFaile(String msg);

    TaskFinishSubData getTaskFinishSubData ();

    TaskAcceptData getTaskAcceptData ();

    void finishSucceed(TaskFinishData data,String msg);
    void finishSucceedDet(String msg);
    void finishFaile(String msg);
    void acceptSucceed(String msg);
    void acceptFaile(String msg);
    String getTaskid();


    void getUsersListSucceed(TaskPersonResouse taskPersonResouse);

    void getUsersListFaile(String msg);

    void setAisData(Ais aisData);

    void setUsetDatas(List<UserInfo> usetDatas);

    void OnSuccessTask(String msg);

    void loginExpired(String msg);

    void getResouseSucceed(TaskPersonResouse taskPersonResouse);

    void getResouseFaile(String msg);
}
