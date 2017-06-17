package cn.edu.bztc.happyidiom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String DB_COLLECT_NAME = "collect.db";// ���ݿ���
	public static final int COLLECT_VERSION = 1;// ���ݿ�汾��
	public static final String TABLE_COLLECT = "collect";// ����
	public static final String COLLECT_ID = "id";// id�ֶ�
	public static final String COLLECT_NAME = "name";// ��������
	public static final String COLLECT_PRO = "pronounce";// ���﷢��
	public static final String COLLECT_EXP = "explain";// �������
	public static final String COLLECT_ANT = "antonym";// �����
	public static final String COLLECT_HOM = "homoionym";// ͬ���
	public static final String COLLECT_DER = "derivation";// Դ��
	public static final String COLLECT_EXA = "examples";// ����

	public static final String CREATE_COLLECT = "create table collect (id integer primary key autoincrement,name text,pronounce text,explain text,antonym text,homoionym text,derivation text,examples text)";

	public MyDatabaseHelper(Context context) {
		super(context, DB_COLLECT_NAME, null, COLLECT_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_COLLECT);// ִ�д������ݿ��SQL���
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
