package cn.edu.bztc.happyidion.activity;

import java.util.List;

import cn.edu.bztc.happyidiom.adapter.AnimalAdapter;
import cn.edu.bztc.happyidiom.dao.AnimalDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidiom.util.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class StudyAnimalActivity extends Activity{
	private List<Animal> animalList;
	private AnimalDao animalDao;
	private ListView lvAnimalList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animal);
		initAnimals();
		lvAnimalList=(ListView)findViewById(R.id.lvAnimalList);
		AnimalAdapter animalAdapter=new AnimalAdapter(this,R.layout.animal_item,animalList);
		lvAnimalList.setAdapter(animalAdapter);
		lvAnimalList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				Animal animal=animalList.get(position);
				/*����Ի�������ʾ���*/
				String result=animal.getName()+"\n"+
				animal.getPronounce()+
				"\n�����͡���"+animal.getExplain()+
				"\n������ʡ���"+animal.getHomoionym()+
				"\n������ʡ���"+animal.getAntonym()+
				"\n����Դ����"+animal.getDerivation()+
				"\n��ʾ������"+animal.getExamples();
				DialogUtil.showDialog(result,StudyAnimalActivity.this);
			}
		});
	}
	/*��ȡ��������*/
	private void initAnimals() {
		animalDao=AnimalDao.getInstance(this);
		animalList=animalDao.getAllAnimals();
	}
}
