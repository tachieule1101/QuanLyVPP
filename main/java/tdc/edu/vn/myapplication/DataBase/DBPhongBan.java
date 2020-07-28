package tdc.edu.vn.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import tdc.edu.vn.myapplication.Model.PhongBan;

public class DBPhongBan {
    DBHelper dbHelper;

    public DBPhongBan(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void Them(PhongBan PhongBan)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",PhongBan.getMa());
        values.put("ten",PhongBan.getTen());
        db.insert("PBActivity",null,values);
    }

    public  void Sua(PhongBan PhongBan)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",PhongBan.getMa());
        values.put("ten",PhongBan.getTen());
        db.update("PBActivity",values,"ma ='"+PhongBan.getMa() +"'",null);
    }


    public  void Xoa(PhongBan PhongBan)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from PhongBan where ma= '"+PhongBan.getMa()+"'";
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
                PhongBan PhongBan = new PhongBan();
                PhongBan.setMa(cursor.getString(0));
                PhongBan.setTen(cursor.getString(1));
                data.add(PhongBan);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }


        return  data;
    }


    public ArrayList<PhongBan> LayDL(String ma)
    {
        ArrayList<PhongBan> data = new ArrayList<>();
        String sql="select * from PhongBan where ma = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                PhongBan PhongBan = new PhongBan();
                PhongBan.setMa(cursor.getString(0));
                PhongBan.setTen(cursor.getString(1));

                data.add(PhongBan);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }


        return  data;
    }
}