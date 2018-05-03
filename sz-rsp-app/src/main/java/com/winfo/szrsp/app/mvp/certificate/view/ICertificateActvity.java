package com.winfo.szrsp.app.mvp.certificate.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.certificate.view
 * @Filename: ICertificateActvity
 * @Author: lsj
 * @Date: 2018/3/26  15:11
 * @Description:
 * @Version:
 */
public interface ICertificateActvity {
    Dialog getDialog();
    void OnSucess(CertificateInfo certificateInfo);
    void OnFaile(String msg);
    void OnLoginExpired(String msg);
    //void ToastMsg(String msg);

    void upLoadSuccess(String msg);

    void deleteSuccess(String msg);

    void upLoadFaile(String msg);

    void deleteFaile(String msg);
}
