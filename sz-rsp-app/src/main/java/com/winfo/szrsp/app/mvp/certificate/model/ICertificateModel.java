package com.winfo.szrsp.app.mvp.certificate.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.certificate.model
 * @Filename: ICertificateModel
 * @Author: lsj
 * @Date: 2018/3/26  15:05
 * @Description:
 * @Version:
 */
public interface ICertificateModel {
    void loadCertificateData(Dialog dialog ,boolean isShowDialog, String mmsi,String message, CertificateModel.OnloadCertificateDataListener onloadCertificateDataListener);
    void subObtain(Dialog dialog , Ais ais, List<String> list1, List<String> list2,List<String> list3,List<String> list4, CertificateModel.UploadCertificateListener uploadCertificateListener);
    void deleteCertificateData(Dialog dialog , String itemId,boolean isShowDialog,CertificateModel.OnDeleteCertificateListener onDeleteCertificateListener);

}
