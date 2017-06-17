package com.example.bz0209.happyidiom.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.adapter.AnimalAdapter;
import com.example.bz0209.happyidiom.dao.AnimalDao;
import com.example.bz0209.happyidiom.entity.Animal;
import com.example.bz0209.happyidiom.util.DialogUtil;

import java.util.List;

public class StudyAnimalActivity extends Activity {
    private List<Animal> animalList;
    private AnimalDao animalDao;
    private ListView lvAnimalList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        initAnimals();
        lvAnimalList = (ListView) findViewById(R.id.lvAnimalList);
        AnimalAdapter animalAdapter = new AnimalAdapter(this,
                R.layout.animal_item, animalList);
        lvAnimalList.setAdapter(animalAdapter);
        lvAnimalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position,
                                    long id) {
                Animal animal = animalList.get(position);
                String result = animal.getName() + "\n" +
                        animal.getPronounce() +
                        "\n【解释】: "+ animal.getExplain()
                        + "\n【近义词】: " +animal.getHomoionym()+
                        "\n【反义词】: " + animal.getAntonym() +
                        "\n【来源】: "+ animal.getDerivation() +
                        "\n【示例】: " + animal.getExamples();
                DialogUtil.showDialog(result,StudyAnimalActivity.this);
            }
        });

    }

    private void initAnimals() {
        animalDao = AnimalDao.getInstance(this);
        animalList = animalDao.getAllAnimals();
    }
}

