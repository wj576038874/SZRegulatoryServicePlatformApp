package com.winfo.szrsp.app.mvp.mobile.presenter;

import com.winfo.szrsp.app.mvp.mobile.model.IMobileModel;
import com.winfo.szrsp.app.mvp.mobile.view.IMobileView;
import com.winfo.szrsp.app.mvp.mobile.model.impl.MobileModel;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.mobile.model
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.mobile.presenter.MobilePresenter.java
 * Date: 2017/12/15 13:39
 * Description:
 */

public class MobilePresenter {

    private IMobileModel mobileModel;
    private IMobileView mobileView;

    /**
     * 初始化
     *
     * @param mobileView v
     */
    public MobilePresenter(IMobileView mobileView) {
        this.mobileModel = new MobileModel();
        this.mobileView = mobileView;
    }

    /**
     * 保存设备参数
     *
     * @param params 设备参数
     */
    public void saveDeviceParams(String params) {
        mobileModel.setDeviceParams(params, new MobileModel.OnSaveDeviceParams() {
            @Override
            public void onSuccess() {
                mobileView.onDeviceParamsSaveSuccess();
            }

            @Override
            public void OnLoginExpired(String msg) {
                mobileView.loginExpired(msg);
            }
        });
    }

    /**
     * 更新设备位置
     *
     * @param location 位置
     */
    public void updateDeviceLocation(String location) {
        mobileModel.updateDeviceLocation(location);
    }

}
