package com.winfo.szrsp.app.mvp.certificate.presenter;

import com.winfo.szrsp.app.mvp.certificate.model.CertificateModel;
import com.winfo.szrsp.app.mvp.certificate.model.ICertificateModel;
import com.winfo.szrsp.app.mvp.certificate.view.ICertificateActvity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateDetatils;
import com.winfo.szrsp.app.sdk.entity.certificate.CertificateInfo;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.certificate.presenter
 * @Filename: CertificatePresenter
 * @Author: lsj
 * @Date: 2018/3/26  15:11
 * @Description:
 * @Version:
 */
public class CertificatePresenter {
    private ICertificateActvity actvity;
    private ICertificateModel model;

    public CertificatePresenter(ICertificateActvity actvity){
        this.actvity = actvity;
        this.model = new CertificateModel();
    }

    //查看证书
    public void loadCertificateData(String mmsi,String message,boolean isShowDialog){
        model.loadCertificateData(actvity.getDialog(),isShowDialog, mmsi,message, new CertificateModel.OnloadCertificateDataListener() {
            @Override
            public void OnSuccess(CertificateInfo certificateInfo) {
                actvity.OnSucess(certificateInfo);
            }

            @Override
            public void OnFaile(String msg) {
                actvity.OnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                actvity.OnLoginExpired(msg);
            }
        });
    }

    //上传证书
    public void uolpadCertificate(Ais ais, List<String> list1, List<String> list2,List<String> list3,List<String> list4){
        model.subObtain(actvity.getDialog(), ais, list1, list2,list3, list4,new CertificateModel.UploadCertificateListener() {
            @Override
            public void OnSuccess(String msg) {
                actvity.upLoadSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                actvity.upLoadFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                actvity.OnLoginExpired(msg);
            }
        });
    }
    //删除证书
    public void deleteCertificate(String idItem,boolean isShowDialog){
        model.deleteCertificateData(actvity.getDialog(), idItem,isShowDialog, new CertificateModel.OnDeleteCertificateListener() {
            @Override
            public void OnSuccess(String msg) {
                actvity.deleteSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                actvity.deleteFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                actvity.OnLoginExpired(msg);
            }
        });
    }

}
