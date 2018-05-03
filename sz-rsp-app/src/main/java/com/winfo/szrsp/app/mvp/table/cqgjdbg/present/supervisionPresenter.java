package com.winfo.szrsp.app.mvp.table.cqgjdbg.present;


import com.winfo.szrsp.app.mvp.table.cqgjdbg.model.ISupervisionModel;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.model.SupervisionModel;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.ISupervisionActivity;
import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.present
 * @Filename: supervisionPresenter
 * @Author: lsj
 * @Date: 2017/12/6  15:40
 * @Description:
 * @Version:
 */
public class supervisionPresenter {
    private ISupervisionActivity activity;
    private ISupervisionModel model;

    public supervisionPresenter(ISupervisionActivity activity) {
        this.activity = activity;
        model = new SupervisionModel();
    }

    public void subData(jdbgData data, String qm_path, String jcry_qm_path) {
        activity.getDialog().show();
        model.SubSupervisionData(data, qm_path, jcry_qm_path, activity.getDialog(), new SupervisionModel.OnLoadSubSupervisionListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.getDialog().dismiss();
                activity.onSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.getDialog().dismiss();
                activity.onFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void findData(String inspectNo) {
        model.FindSupervisionData(activity.getDialog(), inspectNo, new SupervisionModel.OnLoadFindSupervisionListener() {
            @Override
            public void OnSuccess(DetailjdbgData detailjdbgData) {
                activity.setData(detailjdbgData);
            }

            @Override
            public void OnFaile(String msg) {
                activity.onFaile(msg);
            }
        });
    }

    public void getDefectCodeData() {
        model.getDefectCodeData(activity.getDialog(), new SupervisionModel.OnLoadDefectCodeListener() {
            @Override
            public void OnSuccess(List<DefectCode> defectCodes) {
                activity.setDefectCodeData(defectCodes);
            }

            @Override
            public void OnFaile(String msg) {
                activity.onFaile(msg);
            }
        });
    }

    /**
     * 根据UUID获取检查人员
     */
    public void getInspectors(String uuid) {
        model.getInspectorInfo(uuid, activity.getDialog(), new SupervisionModel.OnLoadInspectorsListener() {
            @Override
            public void onSuccess(List<SecurityInspectorInformation> inspectorInfoList) {
                if (inspectorInfoList.size() > 0) {
                    activity.onLoadInspectorInfoSuccess(inspectorInfoList);
                } else {
                    activity.onFaile("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                activity.onFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void findShipInfo(String mmsi, String ywcm) {
        model.findShipInfo(mmsi, ywcm, activity.getDialog(), new SupervisionModel.OnLoadShipInfoListener() {
            @Override
            public void onSuccess(TaskInfoDetails infoDetails) {
                activity.onLoadShipInfoData(infoDetails);
            }

            @Override
            public void onFailure(String msg) {
                activity.onFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
}
