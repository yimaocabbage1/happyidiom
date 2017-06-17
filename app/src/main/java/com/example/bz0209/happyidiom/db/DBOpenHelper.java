package com.example.bz0209.happyidiom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.bz0209.happyidiom.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/27.
 */

public class DBOpenHelper {
    private final int BUFFER_SIZE = 400000;//缓冲区大小
    public static final String DB_NAME = "idioms.db"; // 保存的数据库文件名
    public static final String PACKAGE_NAME = "com.example.bz0209.happyidiom";// 应用的包名
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() +"/"
            + PACKAGE_NAME+ "/databases"; // 在手机里存放数据库的位置

    private Context context;
    public DBOpenHelper(Context context) {

        this.context = context;
    }
    public SQLiteDatabase openDatabase() {
        try {
            File myDataPath = new File(DB_PATH);
            if (!myDataPath.exists()){
                myDataPath.mkdirs();// 如果没有这个目录则创建
            }
            String dbfile=myDataPath+"/"+DB_NAME;
            if (!(new File(dbfile).exists())) {//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = context.getResources().openRawResource(
                        R.raw.idioms);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                    null);
            return db;
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }


}
