package com.example.didong.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.didong.Model.CapPhat;

import java.util.ArrayList;

public class DBCapPhat {
    DBHelper dbHelper;

    public DBCapPhat(Context context){dbHelper=new DBHelper(context);}

    public void Them(CapPhat capPhat)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SoPhieu",capPhat.getMa());
        values.put("NgayCap",capPhat.getNgay());
        values.put("MaVPP",capPhat.getVpp());
        values.put("MaNV",capPhat.getNv());
        values.put("Sl",capPhat.getSl());
        db.insert("CAPNHATVPP",null,values);
    }

    public void Sua(CapPhat capPhat)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NgayCap",capPhat.getNgay());
        values.put("MaVPP",capPhat.getVpp());
        values.put("MaNV",capPhat.getNv());
        values.put("Sl",capPhat.getSl());
        db.update("CAPNHATVPP",values,"SoPhieu = '" + capPhat.getMa() + "'",null);
    }

    public  void Xoa(CapPhat capPhat)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from CAPNHATVPP where SoPhieu = '"+capPhat.getMa()+"'";
        db.execSQL(sql);
    }

    public ArrayList<CapPhat> LayDL()
    {
        ArrayList<CapPhat> data = new ArrayList<>();
        String sql="select * from CAPNHATVPP";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                CapPhat capPhat = new CapPhat();
                capPhat.setMa(cursor.getString(0));
                capPhat.setNgay(cursor.getString(1));
                capPhat.setVpp(cursor.getString(2));
                capPhat.setNv(cursor.getString(3));
                capPhat.setSl(cursor.getString(4));
                data.add(capPhat);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }

    public ArrayList<CapPhat> LayDL(String id)
    {
        ArrayList<CapPhat> data = new ArrayList<>();
        String sql="select * from CAPNHATVPP where SoPhieu = '"+id+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                CapPhat capPhat = new CapPhat();
                capPhat.setMa(cursor.getString(0));
                capPhat.setNgay(cursor.getString(1));
                capPhat.setVpp(cursor.getString(2));
                capPhat.setNv(cursor.getString(3));
                capPhat.setSl(cursor.getString(4));
                data.add(capPhat);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
}
