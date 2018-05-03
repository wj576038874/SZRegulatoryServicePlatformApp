package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.DetailjdbgData;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.view
 * @Filename: ISupervisionActivity
 * @Author: lsj
 * @Date: 2017/12/6  16:07
 * @Description:
 * @Version:
 */
public interface ISupervisionActivity {
    Dialog getDialog();

    void onSuccess(String msg);

    void onLoadInspectorInfoSuccess(List<SecurityInspectorInformation> inspectorInformationList);

    void onFaile(String msg);

    void setData(DetailjdbgData data);

    void setDefectCodeData(List<DefectCode> defectCodes);

    void loginExpired(String msg);

    void onLoadShipInfoData(TaskInfoDetails infoDetails);
}
