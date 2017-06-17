package com.example.bz0209.happyidiom.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.adapter.CategoryAdapter;
import com.example.bz0209.happyidiom.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends Activity {
    private List<Category> categoryList;
    private String[] category_names;
    private int[] category_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        initCategories(); // 初始化类别
        CategoryAdapter adapter = new CategoryAdapter(this,
                R.layout.category_item, categoryList);
        ListView listView = (ListView) findViewById(R.id.lvCategories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent= new Intent(StudyActivity.this,StudyAnimalActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void initCategories() {
        categoryList = new ArrayList<Category>();
        Resources resources = getResources();
        category_names = resources.getStringArray(R.array.category);
        category_images = new int[] { R.drawable.category_animal,
                R.drawable.category_nature, R.drawable.category_human,
                R.drawable.category_season, R.drawable.category_number,
                R.drawable.category_fable, R.drawable.category_other };
        for (int i = 0; i < category_names.length; i++) {
            categoryList
                    .add(new Category(category_names[i], category_images[i]));
        }
    }
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.study, menu);
       /// return true;
   // }

}
