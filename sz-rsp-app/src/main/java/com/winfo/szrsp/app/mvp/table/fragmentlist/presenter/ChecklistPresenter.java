package com.winfo.szrsp.app.mvp.table.fragmentlist.presenter;

import com.winfo.szrsp.app.mvp.table.fragmentlist.IChecklistMainActvity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.model.ChecklistModel;
import com.winfo.szrsp.app.mvp.table.fragmentlist.model.IChecklistModel;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskDefectItemData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskPersonResouse;

import java.util.List;

/**
 * Created by ChengQi on 2017/12/12.
 */

public class ChecklistPresenter {

    IChecklistMainActvity activity;
    IChecklistModel model;

    public ChecklistPresenter(IChecklistMainActvity actvity){
        this.activity=actvity;
        model =new ChecklistModel();
    }

    //获取检查项代码
    public void getTaskDefectItemData(String taskTypeId){
        model.getTaskDefectItemData(taskTypeId, activity.getDiaole(), new ChecklistModel.OnLoadTaskDefectItemDataListener() {
            @Override
            public void OnSuccess(List<TaskDefectItemData> taskDefectItemData) {
                activity.setTaskDefectItemData(taskDefectItemData);
            }

            @Override
            public void OnFaile(String msg) {
                activity.OnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    //火气检查人员
    public  void getUsersListByUserDept(boolean bol){
        model.getUsersListByUserDept(activity.getDiaole(),bol,new ChecklistModel.OnLoadUsersListByUserDeptListener(){
            @Override
            public void OnSuccess(TaskPersonResouse taskPersonResouse) {
                activity.setUsersListSucceed(taskPersonResouse);
            }

            @Override
            public void OnFaile(String msg) {
                activity.OnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    //获取缺陷代码
    public void getDefectCodeData(){
        model.getDefectCodeData(new ChecklistModel.OnLoadDefectCodeListener() {
            @Override
            public void OnSuccess(List<DefectCode> defectCodes) {
                activity.setDefectCodeData(defectCodes);
            }

            @Override
            public void OnFaile(String msg) {
                activity.OnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    //完成任务
    public void finishTask(boolean bol,String siteCaptain,String fileCaptain,String fileInspector){
        model.finishTask(activity.getDiaole(), bol, activity.getTaskFinishSubData(),siteCaptain,fileCaptain,fileInspector, new ChecklistModel.FinishTaskListener() {
            @Override
            public void OnSuccess(TaskFinishData data, String msg) {
                activity.finishSucceed(data,msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.OnFaile(msg);
            }
            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    /**
     * 根据UUID获取检查人员
     *
     */
    public void getInspectors(String uuid) {
        model.getInspectorInfo(uuid, activity.getDiaole(), new ChecklistModel.OnLoadInspectorsListener() {
            @Override
            public void onSuccess(List<SecurityInspectorInformation> inspectorInfoList) {
                if (inspectorInfoList.size()>0){
                    activity.onLoadInspectorInfoSuccess(inspectorInfoList);
                }else {
                    activity.OnFaile("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                activity.OnFaile(msg);
            }
        });


    }

}
