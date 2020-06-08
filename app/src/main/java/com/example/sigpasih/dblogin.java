package com.example.sigpasih;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dblogin extends SQLiteOpenHelper {
    private static String DB_NAME ="login.db";
    private static int DB_VERSION =1;
    public static final String TABLE_NAME="TB_LOGIN";
    public static final String COL_1="Username";
    public static final String COL_2="Password";
    private SQLiteDatabase db;

    public dblogin(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(Username CHAR PRIMARY KEY, Password CHAR )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS "+TABLE_NAME);

    }
    public void SimpanData(String Username, String Password){
        ContentValues values = new ContentValues();
        values.put(COL_1,Username);
        values.put(COL_2,Password);
        db.insert(TABLE_NAME, null,values);
    }
    public Cursor LihatData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return res;
    }
    public void HapusData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
