package cn.edu.bztc.happyidiom.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.edu.bztc.happyidion.activity.R;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

/*ʵ�ֽ����ݿ��ļ���rawĿ¼�������ֻ��������ݿ��λ��*/
public class DBOpenHelper {
	private final int BUFFER_SIZE=400000;//��������С
	public static final String DB_NAME="idioms.db";//��������ݿ��ļ���
	public static final String PACKAGE_NAME="cn.edu.bztc.happyidion.activity";//Ӧ�õİ���
	public static final String DB_PATH="/data"+Environment.getDataDirectory().getAbsolutePath()+"/"+PACKAGE_NAME+"/databases";//���ֻ��������ݿ��λ��
	private Context context;
	public DBOpenHelper(Context context){
		this.context=context;
	}
	public SQLiteDatabase openDatabase(){
		try {
			File myDataPath=new File(DB_PATH);
			if(!myDataPath.exists()){
				myDataPath.mkdirs();//���û�����Ŀ¼�򴴽�
			}
			String dbfile=myDataPath+"/"+DB_NAME;
			if(!(new File(dbfile).exists())){//�ж����ݿ��ļ��Ƿ���ڣ������������ִ�е��룬����ֱ�Ӵ����ݿ�
				InputStream is=context.getResources().openRawResource(R.raw.idioms);
				FileOutputStream fos=new FileOutputStream(dbfile);
				byte[] buffer=new byte[BUFFER_SIZE];
				int count=0;
				while((count=is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(dbfile,null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("MainActivity","File not found");
			e.printStackTrace();
		}catch (IOException e) {
			Log.e("MainActivity","IO exception");
			e.printStackTrace();
		}
		return null;
	}
}
