package com.example.didong.Model;

public class NhanVien {
    String ma, ten, ngaySinh, phongBan;

    @Override
    public String toString() {
        return "NhanVien{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", phongBan='" + phongBan + '\'' +
                '}';
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getPhongBan() {
        return phongBan;
    }
}
