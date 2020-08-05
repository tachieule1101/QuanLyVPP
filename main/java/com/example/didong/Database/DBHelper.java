package com.example.didong.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

        String sqlAddVPP1 = "insert into VPP values ('VPP01', 'Giấy A4', 'Gram', '70000')";
        db.execSQL(sqlAddVPP1);

        String sqlAddVPP2 = "insert into VPP values ('VPP02', 'Kẹp giấy', 'Hộp', '10000')";
        db.execSQL(sqlAddVPP2);

        String sqlAddVPP3 = "insert into VPP values ('VPP03', 'Bút bi', 'Cái', '5000')";
        db.execSQL(sqlAddVPP3);

        String sqlAddPB1 = "insert into PHONGBAN values ('PB01', 'Phòng Kỹ thuật')";
        db.execSQL(sqlAddPB1);

        String sqlAddPB2 = "insert into PHONGBAN values ('PB02', 'Phòng Tài chính')";
        db.execSQL(sqlAddPB2);

        String sqlAddPB3 = "insert into PHONGBAN values ('PB03', 'Phòng Thiết kế')";
        db.execSQL(sqlAddPB3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}