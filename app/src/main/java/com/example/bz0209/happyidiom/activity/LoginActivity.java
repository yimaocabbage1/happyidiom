package com.example.bz0209.happyidiom.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bz0209.happyidiom.R;

public class LoginActivity extends Activity {

    private EditText etUser,etPassword;//定义用户名和密码
    private CheckBox cbRemember;//定义记住密码
    private Button btnLogin;//登录按钮

    private SharedPreferences mSpSettings=null;//声明一个sharedPreferences用于保存数据
    private static final String PREPS_NAME="NamePwd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录界面");
        findById();//获取控件
        setListener();//绑定事件
        getData();
    }
    private void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUser.getText().toString().equals("yhy")&&etPassword.getText().toString().equals("123")){
                    //判断是否记住密码,复选框是否选中
                    if(cbRemember.isChecked()){
                        mSpSettings=getSharedPreferences(PREPS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor edit=mSpSettings.edit();//得到Editor对象
                        edit.putBoolean("isKeep", true);//记录保存标记
                        edit.putString("username", etUser.getText().toString());//记录用户名
                        edit.putString("password", etPassword.getText().toString());//记录密码
                        edit.commit();//**提交
                    }else {
                        mSpSettings=getSharedPreferences(PREPS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor edit=mSpSettings.edit();
                        edit.putBoolean("isKeep", true);//保存的文件名isKeep
                        edit.putString("username", "");
                        edit.putString("password", "");
                        edit.commit();
                    }

                    //跳转
                    Intent intent=new Intent(LoginActivity.this, StudyActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "密码或者用户名错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();//在界面显示数据之前得到之前存储的数据
    }
    private void getData() {
        mSpSettings=getSharedPreferences(PREPS_NAME, MODE_PRIVATE);
        if(mSpSettings.getBoolean("isKeep", false)){
            //如果之前存储过,则显示在相应文本框内
            etUser.setText(mSpSettings.getString("username", ""));
            etPassword.setText(mSpSettings.getString("password", ""));
        }else{//否则显示为空
            etUser.setText("");
            etPassword.setText("");
        }
    }
    private void findById() {
        etUser=(EditText) findViewById(R.id.etUser);
        etPassword=(EditText) findViewById(R.id.etPassword);
        cbRemember=(CheckBox) findViewById(R.id.cbRemember);
        btnLogin=(Button) findViewById(R.id.btnLogin);
    }
}
