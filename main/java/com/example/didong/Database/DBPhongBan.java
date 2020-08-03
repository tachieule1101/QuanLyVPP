package com.example.didong.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.didong.Model.PhongBan;

import java.util.ArrayList;

public class DBPhongBan {
    DBHelper dbHelper;

    public DBPhongBan(Context context){dbHelper=new DBHelper(context);}

    public void Them(PhongBan phongBan)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",phongBan.getMa());
        values.put("ten",phongBan.getTen());
        db.insert("PhongBan",null,values);
    }

    public  void Sua(PhongBan phongBan)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",phongBan.getMa());
        values.put("ten",phongBan.getTen());
        db.update("phongBan",values,"ma ='"+phongBan.getMa() +"'",null);
    }

    public  void Xoa(PhongBan phongBan)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from PhongBan where ma= '"+phongBan.getMa()+"'";
        db.execSQL(sql);
    }

    public ArrayList<PhongBan> LayDL()
    {
        ArrayList<PhongBan> data = new ArrayList<>();
        String sql="select * from PhongBan";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMa(cursor.getString(0));
                phongBan.setTen(cursor.getString(1));
                data.add(phongBan);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }

    public ArrayList<PhongBan> LayDL(String id)
    {
        ArrayList<PhongBan> data = new ArrayList<>();
        String sql="select * from PhongBan where ma = '"+id+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMa(cursor.getString(0));
                phongBan.setTen(cursor.getString(1));
                data.add(phongBan);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
}
