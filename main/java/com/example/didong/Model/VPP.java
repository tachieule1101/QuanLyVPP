package com.example.didong.Model;

public class VPP {
    String ma, ten, dvt, gia;

    @Override
    public String toString() {
        return "VPP{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", dvt='" + dvt + '\'' +
                ", gia='" + gia + '\'' +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getDvt() {
        return dvt;
    }

    public String getGia() {
        return gia;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
