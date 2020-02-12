package com.example.tugasretrofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fav.db";
    public static final String TABLE_NAME = "fav_table";
    private static final int DATABASE_VERSION = 2;

    public static final String COL_1 = "NIM";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table fav_table (nim text primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //    Add Fav
    public boolean insertData(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, nim);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean removeData(String nim){
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE  nim = " + nim;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return true;
    }

    //    Find Data
    public boolean findData(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM fav_table WHERE nim = " + nim, null);
        int count = result.getCount();
        result.close();

        if (count < 1){
            return false;
        }else {
            return true;
        }
    }



}
