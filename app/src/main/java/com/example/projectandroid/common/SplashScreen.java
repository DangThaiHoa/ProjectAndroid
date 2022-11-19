package com.example.projectandroid.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;

public class SplashScreen extends AppCompatActivity {

    private static int Splash_Timer = 5000;

    ImageView backgroundImage;
    TextView poweredBy;

    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash_screen);

        backgroundImage = findViewById(R.id.background_image);
        poweredBy = findViewById(R.id.powered_By);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        backgroundImage.setAnimation(sideAnim);
        poweredBy.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                startActivity(intent);
                finish();

            }
        },Splash_Timer);
    }
}