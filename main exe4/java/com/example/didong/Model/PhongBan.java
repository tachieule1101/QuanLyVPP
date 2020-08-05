package com.example.didong.Model;

import androidx.annotation.NonNull;

public class PhongBan {
    String ma, ten;

    @Override
    public String toString() {
        return "PhongBan{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
