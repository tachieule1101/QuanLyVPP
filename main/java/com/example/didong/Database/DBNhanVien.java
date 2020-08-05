package com.example.didong.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.didong.Model.NhanVien;
import com.example.didong.Model.VPP;

import java.util.ArrayList;

public class DBNhanVien {
    DBHelper dbHelper;

    public DBNhanVien(Context context){dbHelper=new DBHelper(context);}

    public void Them(NhanVien nhanVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaNV",nhanVien.getMa());
        values.put("HoTen",nhanVien.getTen());
        values.put("NgaySinh",nhanVien.getNgaySinh());
        values.put("MaPB",nhanVien.getPhongBan());
        db.insert("NHANVIEN",null,values);
    }

    public void Sua(NhanVien nhanVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HoTen",nhanVien.getTen());
        values.put("NgaySinh",nhanVien.getNgaySinh());
        values.put("MaPB",nhanVien.getPhongBan());
        db.update("NHANVIEN",values,"MaNV = '" + nhanVien.getMa() + "'",null);
    }

    public  void Xoa(NhanVien nhanVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from NHANVIEN where MaNV = '"+nhanVien.getMa()+"'";
        db.execSQL(sql);

        String sql2 ="Delete from CAPNHATVPP where MaNV = '"+nhanVien.getMa()+"'";
        db.execSQL(sql2);
    }

    public ArrayList<NhanVien> LayDL()
    {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql="select * from NHANVIEN";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMa(cursor.getString(0));
                nhanVien.setTen(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setPhongBan(cursor.getString(3));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }

    public ArrayList<NhanVien> LayDL(String id)
    {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql="select * from NHANVIEN where MaNV = '"+id+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMa(cursor.getString(0));
                nhanVien.setTen(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setPhongBan(cursor.getString(3));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
}
