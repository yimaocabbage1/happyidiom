package com.example.bz0209.happyidiom.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.db.MyHelper;

public class MainActivity extends TabActivity {
    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();
        //createCollectDatabase();

        //addTab("study",R.string.title_study,R.drawable.study,StudyActivity.class);
        //addTab("search",R.string.title_search,R.drawable.search,LoginActivity.class);
        //addTab("game",R.string.title_game,R.drawable.game,StudyActivity.class);
        //addTab("save",R.string.title_save,R.drawable.save,StudyActivity.class);
        //addTab("help",R.string.title_help,R.drawable.help,StudyActivity.class);
        tabHost.addTab(tabHost.newTabSpec("help")
                .setIndicator("登录",getResources().getDrawable(R.drawable.help))
                .setContent(new Intent(this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("study")
                .setIndicator("学习",getResources().getDrawable(R.drawable.study))
                .setContent(new Intent(this, StudyActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("search")
                .setIndicator("搜索",getResources().getDrawable(R.drawable.search))
                .setContent(new Intent(this, SearchActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("game")
                .setIndicator("游戏",getResources().getDrawable(R.drawable.game))
                .setContent(new Intent(this, GameActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("save")
                .setIndicator("收藏",getResources().getDrawable(R.drawable.save))
                .setContent(new Intent(this, CollectActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));


    }



    private void addTab(String tag,int title_introduction,int title_icon,Class ActivityClass) {
        tabHost.addTab(tabHost.newTabSpec(tag)
                .setIndicator(getString(title_introduction),
                        getResources().getDrawable(title_icon))
                .setContent(new Intent(this,ActivityClass)));
    }

    /* 创建收藏数据库 */
   /*  private void createCollectDatabase() {
        MyHelper mydatabaseHelper = new MyHelper(MainActivity.this);
        mydatabaseHelper.getWritableDatabase();
    }*/

}




