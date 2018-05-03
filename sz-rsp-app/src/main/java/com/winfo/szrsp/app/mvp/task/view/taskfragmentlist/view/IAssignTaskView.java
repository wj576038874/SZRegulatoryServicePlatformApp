package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipCompanyInfo;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType;
import com.winfo.szrsp.app.sdk.entity.task.AllTaskType2;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskShipData;
import com.winfo.szrsp.app.sdk.entity.user.UserInfo;

import java.util.List;

/**
 * Created by winfo053 on 2018/3/7.
 */

public interface IAssignTaskView extends IBaseMvpView {

    /**
     * 数据加载失败
     *
     * @param error 提示信息
     */
    void onLoadFaild(String error);

//    /**
//     * 把任务类型传递给view
//     *
//     * @param allTaskTypeList
//     */
////    void setAllTaskType(List<AllTaskType> allTaskTypeList);

    void setdeptUser(List<UserInfo> userInfos);

    void setTaskInspectionAll(List<TaskInspectionItemData> taskInspectionAll);

    void setTaskInspectionTaskTypeId(List<TaskInspectionItemData> taskInspectionAll);

    void setTaskShipData(List<TaskShipData> taskShipData);

    void loginExpired(String msg);

    void setAllTaskType(AllTaskType2 allTaskType);
}
