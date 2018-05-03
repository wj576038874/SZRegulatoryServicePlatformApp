package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.table.ShipBerthData;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: IJdbgActivity
 * @Author: lsj
 * @Date: 2017/12/7  14:25
 * @Description:
 * @Version:
 */
public interface IJdbgActivity {
    Dialog getDialog();

    void OnSuccess(String msg);

    void OnFaile(String msg);

    void setDetailData(DetailcbxcjdbgData detailData);

    void setShipBerthData(List<ShipBerthData> datas);

    void loginExpired(String msg);

    void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList);

    void onLoadShipInfoData(TaskInfoDetails infoDetails);
}
