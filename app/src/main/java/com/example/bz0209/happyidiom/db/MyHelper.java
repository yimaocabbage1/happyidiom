package com.example.bz0209.happyidiom.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MyHelper extends SQLiteOpenHelper{
    public static final String DB_NAME="collect.db";
    public static final String CREATE="create table collect(id integer primary key autoincrement,name text,pronounce text,explain text,antonym text,homoionym text,derivation text,examples text)";

    public MyHelper(Context context) {
        super(context,DB_NAME, null,2);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE);
        Log.d("DBHelper","onCreate");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists collect");
        onCreate(sqLiteDatabase);

    }
}
