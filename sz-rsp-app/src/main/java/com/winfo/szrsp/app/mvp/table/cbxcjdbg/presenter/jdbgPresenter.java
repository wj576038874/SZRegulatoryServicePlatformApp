package com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.table.cbxcjdbg.model.IjdbgModel;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.model.jdbgModel;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.IJdbgActivity;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.model.SupervisionModel;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.presenter
 * @Filename: FindAllTablePresenter
 * @Author: lsj
 * @Date: 2017/12/7  14:20
 * @Description:
 * @Version:
 */
public class jdbgPresenter {
    private IJdbgActivity activity;
    private IjdbgModel model;

    public jdbgPresenter(IJdbgActivity activity) {
        this.activity = activity;
        this.model = new jdbgModel();
    }

    public void subData(String qm_path, cbxcjdbgData data) {
        activity.getDialog().show();
        model.subData(activity.getDialog(), qm_path, data, new jdbgModel.OnSubjdbgDataListener() {
            @Override
            public void OnSuccess(String msg) {
                activity.getDialog().dismiss();
                activity.OnSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                activity.getDialog().dismiss();
                activity.OnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void findData(String id) {
        model.findData(activity.getDialog(), id, new jdbgModel.OnFindjdbgDataListener() {
            @Override
            public void OnSuccess(DetailcbxcjdbgData detailcbxcjdbgData) {
                activity.setDetailData(detailcbxcjdbgData);
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

    public void getBerthData(Dialog dialog, String name, boolean b) {
        model.getBerthData(dialog, name, b, new jdbgModel.OnGetBerthDataListenter() {
            @Override
            public void OnSuccess(List<ShipBerthData> datas) {
                activity.setShipBerthData(datas);
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
     */
    public void getInspectors(String uuid) {
        model.getInspectorInfo(uuid, activity.getDialog(), new jdbgModel.OnLoadInspectorsListener() {
            @Override
            public void onSuccess(List<SecurityInspectorInformation> inspectorInfoList) {
                if (inspectorInfoList.size() > 0) {
                    activity.onLoadInspectorInfoSuccess(inspectorInfoList);
                } else {
                    activity.OnFaile("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                activity.OnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });


    }

    public void findShipInfo(String mmsi, String cm) {
        model.findShipInfo(mmsi, cm, activity.getDialog(), new jdbgModel.OnLoadShipInfoListener() {
            @Override
            public void onSuccess(TaskInfoDetails infoDetails) {
                activity.onLoadShipInfoData(infoDetails);
            }

            @Override
            public void onFailure(String msg) {
                activity.OnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
}
