package com.example.bz0209.happyidiom.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bz0209.happyidiom.R;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GameActivity extends Activity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void gamePlay(View view) {
        switch (view.getId()) {
            case R.id.tvGameGuess:
                intent=new Intent(this,GameGuessActivity.class);
                startActivity(intent);
                break;




            default:
                break;
        }
    }

}