package com.winfo.szrsp.app.mvp.table.cqgjdbg.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.jdbgData;


/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.model
 * @Filename: ISupervisionModel
 * @Author: lsj
 * @Date: 2017/12/6  10:30
 * @Description:
 * @Version:
 */
public interface ISupervisionModel {
    void SubSupervisionData(jdbgData jdbgData,String qm_path,String jcry_qm_path, Dialog dialog, SupervisionModel.OnLoadSubSupervisionListener onLoadSubSupervisionListener);
    void FindSupervisionData(Dialog dialog, String inspectNo, SupervisionModel.OnLoadFindSupervisionListener onLoadFindSupervisionListener);
    void getDefectCodeData(Dialog dialog, SupervisionModel.OnLoadDefectCodeListener onLoadDefectCodeListener);
    void getInspectorInfo(String uuid,Dialog dialog, SupervisionModel.OnLoadInspectorsListener listener);

    void findShipInfo(String mmsi, String ywcm, Dialog dialog, SupervisionModel.OnLoadShipInfoListener onLoadShipInfoListener);
}
