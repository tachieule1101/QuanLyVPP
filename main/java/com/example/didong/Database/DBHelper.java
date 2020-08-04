package com.example.didong.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "QLVPP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlPB = "create table PHONGBAN(MaPB text,TenPB text,CONSTRAINT phongban_pk PRIMARY KEY (MaPB))";
        db.execSQL(sqlPB);

        String sqlVPP = "create table VPP(MaVPP text,TenVPP text,DVT text,GiaNhap text,CONSTRAINT vpp_pk PRIMARY KEY (MaVPP))";
        db.execSQL(sqlVPP);

        String sqlNV = "create table NHANVIEN(MaNV text,HoTen text,NgaySinh text,MaPB text,CONSTRAINT nhanvien_pk PRIMARY KEY (MaNV), CONSTRAINT fk_nhanvien_phongban FOREIGN KEY (MaPB) REFERENCES PHONGBAN (MaPB))";
        db.execSQL(sqlNV);

        String sqlCapNhat = "create table CAPNHATVPP(SoPhieu text,NgayCap text,MaVPP text,MaNV text,Sl text,CONSTRAINT nhanvien_pk PRIMARY KEY (SoPhieu),CONSTRAINT fk_capnhatvpp_vpp FOREIGN KEY (MaVPP) REFERENCES VPP (MaVPP),CONSTRAINT fk_capnhatvpp_nhanvien FOREIGN KEY (MaNV) REFERENCES NHANVIEN (MaNV))";
        db.execSQL(sqlCapNhat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}