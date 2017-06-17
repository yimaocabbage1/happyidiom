package com.example.bz0209.happyidiom.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.ViewHelper.SearchView;
import com.example.bz0209.happyidiom.adapter.AnimalAdapter;
import com.example.bz0209.happyidiom.dao.AnimalDao;
import com.example.bz0209.happyidiom.entity.Animal;
import com.example.bz0209.happyidiom.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity implements SearchView.SearchViewListener{
    /**
     * 搜索结果列表view
     */
    private List<Animal> dbData;
    private AnimalDao animalDao;
    private ListView lvResults;

    /**
     * 搜索view
     */
    private SearchView searchView;


    /**
     * 热搜框列表adapter
     */
    private ArrayAdapter<String> hintAdapter;

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private AnimalAdapter resultAdapter;

    //private List<Bean> dbData;

    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;

    /**
     * 搜索结果的数据
     */
    private List<Animal> resultData;

    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {

        SearchActivity.hintSize = hintSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);


        initData();
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(SearchActivity.this, position + "", Toast.LENGTH_SHORT).show();
                Animal animal = resultData.get(position);
				/* 定义对话框中提示语句 d("DBHelper","click");*/
                String result = animal.getName() + "\n" + animal.getPronounce()
                        + "\n【解释】：" + animal.getExplain()
                        + "\n【近义词】："+ animal.getHomoionym()
                        + "\n【反义词】："+ animal.getAntonym()
                        + "\n【来源】："+ animal.getDerivation()
                        + "\n【示例】："+ animal.getExamples();
                DialogUtil.showDialog(result, SearchActivity.this);

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //从数据库获取数据
        getDbData();
        //初始化热搜版数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    /**
     * 获取db 数据
     */
    private void getDbData() {
       // int size = 100;
      //  dbData = new ArrayList<>(size);
        //for (int i = 0; i < size; i++) {
          //  dbData.add(new Bean(R.drawable.icon, "android开发必备技能" + (i + 1), "Android自定义view——自定义搜索view", i * 20 + 2 + ""));
       // }
        animalDao = AnimalDao.getInstance(this);
        dbData = animalDao.getAllAnimals();
    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);



        for (int i = 1; i <= hintSize; i++) {
            if(i==1){
            hintData.add("热搜版" + i + "火龙麒麟");}
            if(i==2){
                hintData.add("热搜版" + i + "蛟龙戏水");}
            if(i==3){
                hintData.add("热搜版" + i + "呆若木鸡");}
            if(i==4){
                hintData.add("热搜版" + i + "鸡飞蛋打");}

        }
        hintAdapter = new ArrayAdapter<>(this,R.layout.simple, hintData);
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null) {

            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < dbData.size()
                    && count < hintSize; i++) {
                if (dbData.get(i).getName().contains(text.trim())) {
                    Log.d("SEAE","6666666");
                    autoCompleteData.add(dbData.get(i).getName());
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<>(this,R.layout.simple, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        } else {
            resultData.clear();
            for (int i = 0; i < dbData.size(); i++) {
                if (dbData.get(i).getName().contains(text.trim())) {
                    resultData.add(dbData.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new AnimalAdapter(this, R.layout.animal_item,resultData);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        //更新数据
        getAutoCompleteData(text);
    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);
        lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "完成搜素", Toast.LENGTH_SHORT).show();


    }

}