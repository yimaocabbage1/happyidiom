package cn.edu.bztc.happyidiom.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.bztc.happyidiom.db.DBOpenHelper;
import cn.edu.bztc.happyidiom.entity.Animal;

public class AnimalQueryDao {
	
	private static AnimalQueryDao animalQueryDao;
	private SQLiteDatabase db;
	/*�����췽��˽�л�*/
	private AnimalQueryDao(Context context){
		DBOpenHelper dbHelper=new DBOpenHelper(context);
		db=dbHelper.openDatabase();
	}
	/*��ȡAnimalDao��ʵ��*/
	public synchronized static AnimalQueryDao getInstance(Context context){
		if(animalQueryDao==null){
			animalQueryDao=new AnimalQueryDao(context);
		}
		return animalQueryDao;
	}
	public List<Animal> queryAnimal(String elem){
		List<Animal> animals=new ArrayList<Animal>();
		/*
		 * tableΪ��ѯ�ı���
		 * columnsΪ��ѯ�󷵻ص��У�Ϊnullʱ�򷵻����е���
		 * selectionָ����Ҫ������Щ�е�where������䣬Ϊnull��ʾ����������
		 * selectionArgs��ʾwhere����б��ʽ�ģ�ռλ�����б�����ֻ��ΪString��
		 * groupBy��ʾ�Խ�������з����group by��䣬Ϊnull�����Խ�������з���
		 * having��ʾ�Է�����������������having��䣬�������groupBy����ʹ�ã�Ϊnull�����Է���������������
		 * orderBy��ʾ�Խ�������������orderBy��䣬Ϊnull���Խ����ʹ��Ĭ�ϵ����򣨲�����
		 */
		Cursor cursor=db.query("animal",null,"name like ?",new String[]{"%"+elem+"%"},null,null,null);
		if(cursor.moveToFirst()){
			do{
				Animal animal=new Animal();
				animal.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				animal.setName(cursor.getString(cursor.getColumnIndex("name")));
				animal.setPronounce(cursor.getString(cursor.getColumnIndex("pronounce")));
				animal.setAntonym(cursor.getString(cursor.getColumnIndex("antonym")));
				animal.setHomoionym(cursor.getString(cursor.getColumnIndex("homoionym")));
				animal.setDerivation(cursor.getString(cursor.getColumnIndex("derivation")));
				animal.setExamples(cursor.getString(cursor.getColumnIndex("examples")));
				animal.setExamples(cursor.getString(cursor.getColumnIndex("explain")));
				animals.add(animal);
			}while(cursor.moveToNext());
		}
		cursor.close();
		return animals;
	}
}
