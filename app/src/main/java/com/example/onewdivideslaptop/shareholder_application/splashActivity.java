package com.example.onewdivideslaptop.shareholder_application;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView playtorium_logo = (ImageView) findViewById(R.id.playtorium_logo);
        TextView present_by = (TextView) findViewById(R.id.presentBy);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        playtorium_logo.startAnimation(myanim);
        present_by.startAnimation(myanim);
        final Intent intent = new Intent(splashActivity.this,LoginPage.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
