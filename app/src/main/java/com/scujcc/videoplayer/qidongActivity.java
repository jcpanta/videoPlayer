package com.scujcc.videoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class qidongActivity extends AppCompatActivity {

    private  final int SPLASH_DISPLAY_LENGHT = 2000;//两秒后进入系统，时间可自行调整
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qidong);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(qidongActivity.this,MainActivity.class);
                qidongActivity.this.startActivity(mainIntent);
                qidongActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);

    }
}