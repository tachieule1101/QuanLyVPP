package com.example.didong.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.didong.Model.VPP;

import java.util.ArrayList;

public class DBVPP {
    DBHelper dbHelper;

    public DBVPP(Context context){dbHelper=new DBHelper(context);}

    public void Them(VPP vpp)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaVPP",vpp.getMa());
        values.put("TenVPP",vpp.getTen());
        values.put("DVT",vpp.getDvt());
        values.put("GiaNhap",vpp.getGia());
        db.insert("VPP",null,values);
    }

    public void Sua(VPP vpp)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenVPP",vpp.getTen());
        values.put("DVT",vpp.getDvt());
        values.put("GiaNhap",vpp.getGia());
        db.update("VPP",values,"MaVPP = '" + vpp.getMa() + "'",null);
    }

    public  void Xoa(VPP vpp)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from VPP where MaVPP = '"+vpp.getMa()+"'";
        db.execSQL(sql);

        String sql2 ="Delete from CAPNHATVPP where MaVPP = '"+vpp.getMa()+"'";
        db.execSQL(sql2);
    }

    public ArrayList<VPP> LayDL()
    {
        ArrayList<VPP> data = new ArrayList<>();
        String sql="select * from VPP";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                VPP vpp = new VPP();
                vpp.setMa(cursor.getString(0));
                vpp.setTen(cursor.getString(1));
                vpp.setDvt(cursor.getString(2));
                vpp.setGia(cursor.getString(3));
                data.add(vpp);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }

    public ArrayList<VPP> LayDL(String id)
    {
        ArrayList<VPP> data = new ArrayList<>();
        String sql="select * from VPP where MaVPP = '"+id+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                VPP vpp = new VPP();
                vpp.setMa(cursor.getString(0));
                vpp.setTen(cursor.getString(1));
                vpp.setDvt(cursor.getString(2));
                vpp.setGia(cursor.getString(3));
                data.add(vpp);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
}
