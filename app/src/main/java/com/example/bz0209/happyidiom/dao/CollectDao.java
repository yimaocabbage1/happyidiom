package com.example.bz0209.happyidiom.dao;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import com.example.bz0209.happyidiom.db.MyHelper;
import com.example.bz0209.happyidiom.entity.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class CollectDao {
    private MyHelper dbHelper;
    private static CollectDao collectDao;


    public  CollectDao(Context context) {

        dbHelper=new MyHelper(context);
    }
    public synchronized static CollectDao getInstance(Context context){
        if(collectDao==null){
            collectDao=new CollectDao(context);
        }
        return collectDao;
    }


    public void AddData(Animal animal){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",animal.getName());
        contentValues.put("pronounce",animal.getPronounce());
        contentValues.put("explain",animal.getExplain());
        contentValues.put("antonym",animal.getAntonym());
        contentValues.put("homoionym",animal.getHomoionym());
        contentValues.put("derivation",animal.getDerivation());
        contentValues.put("examples",animal.getExamples());
        int id= (int) sqLiteDatabase.insert("collect",null,contentValues);
        animal.setId(id);

        sqLiteDatabase.close();
    }
    public List<Animal> QueryData(List<Animal> list){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query("collect",null,null,null,null,null,null);
       // List<Animal> list= new ArrayList<Animal>();
        if(cursor.moveToNext()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
             String pronounce=cursor.getString(cursor.getColumnIndex("pronounce"));
            String explain=cursor.getString(cursor.getColumnIndex("explain"));
             String antonym=cursor.getString(cursor.getColumnIndex("antonym"));
            String homoionym=cursor.getString(cursor.getColumnIndex("homoionym"));
             String derivation=cursor.getString(cursor.getColumnIndex("derivation"));
             String examples=cursor.getString(cursor.getColumnIndex("examples"));
            list.add(new Animal(id,name,pronounce,explain, antonym, homoionym,derivation,examples));
            }while(cursor.moveToNext());
        }cursor.close();
        sqLiteDatabase.close();
        return list;

    }
    public int DeleteData(int id){
        Log. d("DBHelper","click"+id);
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        int account=sqLiteDatabase.delete("collect","id=?",new String[]{id+""});

        sqLiteDatabase.close();
        return account;
    }

}
