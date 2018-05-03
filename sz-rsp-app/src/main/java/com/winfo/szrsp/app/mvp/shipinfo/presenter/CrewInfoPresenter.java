package com.winfo.szrsp.app.mvp.shipinfo.presenter;

import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.shipinfo.model.ICrewInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.model.impl.CrewInfoModel;
import com.winfo.szrsp.app.mvp.shipinfo.view.ICrewInfoView;
import com.winfo.szrsp.app.sdk.entity.shipdata.CrewInfo;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.shipinfo.presenter
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.shipinfo.presenter.CrewInfoPresenter.java
 * Date: 2017/12/22 10:47
 * Description:
 */

public class CrewInfoPresenter extends BaseMvpPresenter<ICrewInfoView> {

    private ICrewInfoModel crewInfoModel;

    public CrewInfoPresenter() {
        this.crewInfoModel = new CrewInfoModel();
    }

    public void getCrewInfosByMmsiOrYwcm(String mmsi, String ywcm) {
        if (mView == null) return;
        crewInfoModel.loadCrewInfo(mmsi, ywcm, new CrewInfoModel.OnLoadCrewInfoListener() {
            @Override
            public void onSuccess(List<CrewInfo> crewInfos) {
                mView.setCrewInfoData(crewInfos);
            }

            @Override
            public void onFailure(String msg) {
                mView.onLoadCrewInfoFail(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void cancelRequest() {
        crewInfoModel.onRequestCancel();
    }

}
