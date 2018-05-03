package com.winfo.szrsp.app.sdk.entity.certificate;

import java.io.Serializable;
import java.util.List;


public class CertificateInfo implements Serializable{
    private List<CertificateDetatils> ais;
    private List<CertificateDetatils> certificate;
    private List<CertificateDetatils> nationalShipCertificate;
    private List<CertificateDetatils> other;

    public List<CertificateDetatils> getOther() {
        return other;
    }

    public void setOther(List<CertificateDetatils> other) {
        this.other = other;
    }

    public List<CertificateDetatils> getAis() {
        return ais;
    }

    public void setAis(List<CertificateDetatils> ais) {
        this.ais = ais;
    }

    public List<CertificateDetatils> getCertificate() {
        return certificate;
    }

    public void setCertificate(List<CertificateDetatils> certificate) {
        this.certificate = certificate;
    }

    public List<CertificateDetatils> getNationalShipCertificate() {
        return nationalShipCertificate;
    }

    public void setNationalShipCertificate(List<CertificateDetatils> nationalShipCertificate) {
        this.nationalShipCertificate = nationalShipCertificate;
    }

}
