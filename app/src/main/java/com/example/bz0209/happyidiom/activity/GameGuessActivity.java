package com.example.bz0209.happyidiom.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bz0209.happyidiom.R;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GameGuessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guess);


    }
    public void startGame(View view){
        switch (view.getId()) {
            case R.id.ibStartGame:
                Intent intent=new Intent(this,PlayGuessActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }
}
