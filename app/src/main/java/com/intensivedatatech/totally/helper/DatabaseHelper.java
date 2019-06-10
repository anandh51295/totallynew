package com.intensivedatatech.totally.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "totally.db";
    public static final String TABLE_NAME = "partytable";
    public static final String COL_01 = "ID";
    public static final String COL_02 = "NAME";
    public static final String COL_03 = "ADDRESS";
    public static final String COL_04 = "MOBILE";

    public static final String TABLE2_NAME = "mattable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME_ADDRESS";
    public static final String COL_3 = "QUANTITY";
    public static final String COL_4 = "PRICE";
    public static final String COL_5 = "TOTALPRICE";
    public static final String COL_6 = "DATE";


    public static final String TABLE3_NAME = "nooltable";
    public static final String COL2_1 = "ID";
    public static final String COL2_2 = "P_ID";
    public static final String COL2_3 = "COTTONQUANTITY";
    public static final String COL2_4 = "COTTONPRICE";
    public static final String COL2_5 = "COLORQUANTITY";
    public static final String COL2_6 = "COLORPRICE";
    public static final String COL2_7 = "TOTALPRICE";
    public static final String COL2_8 = "PAID";
    public static final String COL2_9 = "DATE";
    public static final String COL2_10 = "COTTONMATQUANTITY";
    public static final String COL2_11 = "COTTONMATPRICE";
    public static final String COL2_12 = "COTTONMATTOTALPRICE";
    public static final String COL2_13 = "COLORMATQUANTITY";
    public static final String COL2_14 = "COLORMATPRICE";
    public static final String COL2_15 = "COLORMATTOTALPRICE";
    public static final String COL2_16 = "DESCRIPTION";
    public static final String COL2_17 = "ENTRYPRICE1";
    public static final String COL2_18 = "ENTRYPRICE2";
    public static final String COL2_19 = "TOTALCOTTONPRICE";
    public static final String COL2_20 = "TOTALCOLORPRICE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY,NAME VARACHAR,ADDRESS VARCHAR, MOBILE VARCHAR)");
//        db.execSQL("create table " + TABLE2_NAME + " (ID INTEGER PRIMARY KEY,NAME_ADDRESS VARACHAR,QUANTITY INTEGER, PRICE FLOAT,TOTALPRICE FLOAT,DATE VARCHAR)");
        db.execSQL("create table " + TABLE3_NAME + " (ID INTEGER PRIMARY KEY,P_ID INTEGER,COTTONQUANTITY FLOAT, COTTONPRICE FLOAT,COLORQUANTITY FLOAT,COLORPRICE FLOAT,TOTALPRICE FLOAT,PAID FLOAT,DATE VARCHAR,COTTONMATQUANTITY FLOAT,COTTONMATPRICE FLOAT,COTTONMATTOTALPRICE FLOAT,COLORMATQUANTITY FLOAT,COLORMATPRICE FLOAT,COLORMATTOTALPRICE FLOAT,DESCRIPTION VARCHAR,ENTRYPRICE1 FLOAT,ENTRYPRICE2 FLOAT,TOTALCOLORPRICE FLOAT,TOTALCOTTONPRICE FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
    }

    public boolean insertparty(String name,String address,String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_02, name);
                contentValues.put(COL_03, address);
                contentValues.put(COL_04, number);
                try {
                    db.insert(TABLE_NAME, null, contentValues);
                    return true;
                } catch (Exception rt) {
                    rt.printStackTrace();
                    return false;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getparty() {
        SQLiteDatabase database = this.getWritableDatabase();
//        String query = "select time(sum(strftime('%s',w.video_duration)-strftime('%s','00:00:00')),'unixepoch'),time(sum(strftime('%s',w.video_play)-strftime('%s','00:00:00')),'unixepoch'),ct.course_name from watch_table as w,file_list as f,modules_table as mod,contents_table as con,course_table as ct where watch_status != 'not played' and w.file_id=f.file_id and f.module_id=mod.module_id and mod.content_id=con.content_id and ct.course_id=con.course_id group by ct.course_name";
        String query = "select * from partytable";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
            //cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getbyid(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select NAME  from partytable where ID = " + id + "";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
            //cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean uppaid(float paid,int id){
        SQLiteDatabase database = this.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("UPDATE " + TABLE3_NAME + " SET " + COL2_8 + " = '" + paid + "' WHERE " + COL2_1 + " = " + id, null);
            c.moveToFirst();
            return true;
        } catch (Exception checks) {
            checks.printStackTrace();
            return false;
        }
    }

    public boolean changeparty(int id,String name,String address,String number) {

        SQLiteDatabase database = this.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("UPDATE " + TABLE_NAME + " SET " + COL_02 + " = '" + name + "'," +COL_03+ " = '"+ address + "', "+ COL_04 +" = "+ number
                    + " WHERE " + COL_01 + " = " + id, null);
            c.moveToFirst();
            return true;
        } catch (Exception checks) {
            checks.printStackTrace();
            return false;
        }
    }

    public boolean changemat(int id,String name_address,int quantity,float price,float totalprice) {

        SQLiteDatabase database = this.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("UPDATE " + TABLE2_NAME + " SET " + COL_2 + " = '" + name_address + "'," +COL_3+ " = '"+ quantity + "', "+ COL_4 +" = "+ price
                    +" , "+ COL_5 + " = " + totalprice + " WHERE " + COL_1 + " = " + id, null);
            c.moveToFirst();
            return true;
        } catch (Exception checks) {
            checks.printStackTrace();
            return false;
        }
    }

    public boolean changenool(int id,float p_id,float whitequantity,float whiteprice,float colorquantity,float colorprice,float totalprice,float paid,String date,float cottonmatquantity,float cottonmatprice,float cottonmattotalprice,float colormatquantity,float colormatprice,float colormattotalprice,String details,float entryprice1,float entryprice2, float totalcottonprice, float totalcolorprice) {

        SQLiteDatabase database = this.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("UPDATE " + TABLE3_NAME + " SET " + COL2_2 + " = '" + p_id + "'," +COL2_3+ " = "+ whitequantity + ", "+ COL2_4 +" = "+ whiteprice
                    +" , "+ COL2_5 + " = " + colorquantity + ","+COL2_6 + " = " + colorprice + ","+COL2_7 + " = " + totalprice + ","+ COL2_8 + " = " + paid + ","+ COL2_9 + " = " + date + ","+ COL2_10 + " = " + cottonmatquantity + ","+ COL2_11 + " = " + cottonmatprice + ","+ COL2_12 + " = " + cottonmattotalprice + ","+ COL2_13 + " = " + colormatquantity + ","+ COL2_14 + " = " + colormatprice + ","+ COL2_15 + " = " + colormattotalprice + ","+ COL2_16 + " = '" + details + "',"+ COL2_17 + " = " + entryprice1 + ","+ COL2_18 + " = " + entryprice2 + ","+ COL2_19 + " = " + totalcottonprice + ","+ COL2_20 + " = " + totalcolorprice +" WHERE " + COL2_1 + " = " + id, null);
            c.moveToFirst();
            return true;
        } catch (Exception checks) {
            checks.printStackTrace();
            return false;
        }
    }

    public boolean insertmat(String name_address,int quantity,float price,float totalprice,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put(COL_2, name_address);
            contentValue.put(COL_3, quantity);
            contentValue.put(COL_4, price);
            contentValue.put(COL_5, totalprice);
            contentValue.put(COL_6, date);
            try {
                db.insert(TABLE2_NAME, null, contentValue);
                return true;
            } catch (Exception rt) {
                rt.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getmat() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from mattable";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
            //cursor.moveToFirst();
        }
        return cursor;
    }


    public boolean insertnool(int p_id,float whitequantity,float whiteprice,float colorquantity,float colorprice,float totalprice,float paid,String date,float cottonmatquantity,float cottonmatprice,float cottonmattotalprice,float colormatquantity,float colormatprice,float colormattotalprice,String details,float entryprice1,float entryprice2, float totalcottonprice, float totalcolorprice) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put(COL2_2, p_id);
            contentValue.put(COL2_3, whitequantity);
            contentValue.put(COL2_4, whiteprice);
            contentValue.put(COL2_5, colorquantity);
            contentValue.put(COL2_6, colorprice);
            contentValue.put(COL2_7, totalprice);
            contentValue.put(COL2_8, paid);
            contentValue.put(COL2_9, date);
            contentValue.put(COL2_9, date);
            contentValue.put(COL2_10, cottonmatquantity);
            contentValue.put(COL2_11, cottonmatprice);
            contentValue.put(COL2_12, cottonmattotalprice);
            contentValue.put(COL2_13, colormatquantity);
            contentValue.put(COL2_14, colormatprice);
            contentValue.put(COL2_15, colormattotalprice);
            contentValue.put(COL2_16, details);
            contentValue.put(COL2_17, entryprice1);
            contentValue.put(COL2_18, entryprice2);
            contentValue.put(COL2_19, totalcottonprice);
            contentValue.put(COL2_20, totalcolorprice);


            try {
                db.insert(TABLE3_NAME, null, contentValue);
                return true;
            } catch (Exception rt) {
                rt.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getnool() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from nooltable";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
            //cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getnoolbyid(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from nooltable where p_id = "+id+"";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
//            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getnoolbid(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from nooltable where id = "+id+"";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
//            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor raw() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , new String[]{});

        return res;
    }

//    public Cursor raw1() {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("SELECT * FROM " + TABLE2_NAME , new String[]{});
//
//        return res;
//    }

    public Cursor raw2() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE3_NAME , new String[]{});

        return res;
    }


    public boolean deletemat(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
            try {
                String query = "delete from mattable where id=" + id;
                db.execSQL(query);
                return true;
            } catch (Exception rt) {
                rt.printStackTrace();
                return false;
            }

    }

    public boolean deleteparty(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "delete from partytable where id=" + id;
            db.execSQL(query);
            return true;
        } catch (Exception rt) {
            rt.printStackTrace();
            return false;
        }

    }
    public boolean deletenool(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "delete from nooltable where id=" + id;
            db.execSQL(query);
            return true;
        } catch (Exception rt) {
            rt.printStackTrace();
            return false;
        }

    }
}
