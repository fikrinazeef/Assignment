package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Starting extends AppCompatActivity {

    private ImageView Splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        Splash = (ImageView) findViewById(R.id.splash);

        Animation TMGeoOps = AnimationUtils.loadAnimation(this,R.anim.fade);
        Splash.startAnimation(TMGeoOps);
//        getSupportActionBar().setShowHideAnimationEnabled(false);

        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread() {

            public void run () {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}