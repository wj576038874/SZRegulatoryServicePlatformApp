package com.winfo.szrsp.app.mvp.table.cbxcjdbg.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.table.cqgjdbg.model.SupervisionModel;
import com.winfo.szrsp.app.sdk.entity.table.cbxcjdbgData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.model
 * @Filename: IjdbgModel
 * @Author: lsj
 * @Date: 2017/12/7  14:20
 * @Description:
 * @Version:
 */
public interface IjdbgModel {
    void subData(Dialog dialog, String qm_path, cbxcjdbgData cbxcjdbgData, jdbgModel.OnSubjdbgDataListener onSubjdbgDataListener);

    void findData(Dialog dialog, String inspectNo, jdbgModel.OnFindjdbgDataListener onFindjdbgDataListener);

    void getBerthData(Dialog dialog, String name, boolean b, jdbgModel.OnGetBerthDataListenter onGetBerthDataListenter);

    void getInspectorInfo(String uuid, Dialog dialog, jdbgModel.OnLoadInspectorsListener listener);

    void findShipInfo(String mmsi, String cm, Dialog dialog, jdbgModel.OnLoadShipInfoListener onLoadShipInfoListener);
}
