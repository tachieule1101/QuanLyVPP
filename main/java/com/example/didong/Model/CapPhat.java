package com.example.didong.Model;

public class CapPhat {
    String ma, vpp, sl, ngay, nv;

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setVpp(String vpp) {
        this.vpp = vpp;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    public String getMa() {
        return ma;
    }

    public String getVpp() {
        return vpp;
    }

    public String getSl() {
        return sl;
    }

    public String getNgay() {
        return ngay;
    }

    public String getNv() {
        return nv;
    }

    @Override
    public String toString() {
        return "CapPhat{" +
                "ma='" + ma + '\'' +
                ", vpp='" + vpp + '\'' +
                ", sl='" + sl + '\'' +
                ", ngay='" + ngay + '\'' +
                ", nv='" + nv + '\'' +
                '}';
    }
}
