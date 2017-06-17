package com.example.bz0209.happyidiom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.adapter.CollectAdapter;
import com.example.bz0209.happyidiom.dao.CollectDao;
import com.example.bz0209.happyidiom.entity.Animal;
import com.example.bz0209.happyidiom.minterface.ChangeListData;
import com.example.bz0209.happyidiom.util.DialogUtil;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends Activity {
    private List<Animal> collectList = new ArrayList<Animal>();
    private CollectDao collectDao;
    private ListView lvCollect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        initAnimals();
        lvCollect = (ListView) findViewById(R.id.lvCollect);
        final CollectAdapter collectAdapter = new CollectAdapter(this, R.layout.animal_itemdel, collectList,collectDao);
        Log. d("DBHelper","11111111111111111111111");
        lvCollect.setAdapter(collectAdapter);
        lvCollect.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                Log. d("DBHelper","22222222222222");
                Animal animal = collectList.get(position);
				/* 定义对话框中提示语句 d("DBHelper","click");*/
                String result = animal.getName() + "\n" + animal.getPronounce()
                        + "\n【解释】：" + animal.getExplain()
                        + "\n【近义词】："+ animal.getHomoionym()
                        + "\n【反义词】："+ animal.getAntonym()
                        + "\n【来源】："+ animal.getDerivation()
                        + "\n【示例】："+ animal.getExamples();
                DialogUtil.showDialog(result, CollectActivity.this);



            }
        });


        /*  collectAdapter.setChangeListData(new ChangeListData() {

            /*刷新ListView*/

          /*  public void changedata() {
                Log. d("DBHelper","33333333333");
                collectList.clear();
                collectDao.QueryData(collectList);
                collectAdapter.notifyDataSetChanged();
            }
        }); */
    }




    /* 获取数据库中的收藏集合 */
    private void initAnimals() {
        collectDao = CollectDao.getInstance(this);
        collectDao.QueryData(collectList);

    }

}
