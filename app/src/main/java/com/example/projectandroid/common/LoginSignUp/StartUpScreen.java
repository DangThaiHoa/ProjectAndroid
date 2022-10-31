package com.example.projectandroid.common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class StartUpScreen extends AppCompatActivity {

    ImageView iconmode, imageStart;
    Boolean isDarkModeOn = false;
    Button btnLogin, btnSignUp;
    TextView textViewWE, textViewDE, textViewWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up_screen);

        iconmode = findViewById(R.id.iconthememode);
        imageStart = findViewById(R.id.imageStart);
        btnLogin = findViewById(R.id.login_btn);
        btnSignUp = findViewById(R.id.signup_btn);
        textViewDE = findViewById(R.id.textviewDE);
        textViewWE = findViewById(R.id.textViewWE);
        textViewWork = findViewById(R.id.textviewWork);

        iconmode.setTranslationY(300);
        imageStart.setTranslationX(1000);
        btnLogin.setTranslationY(300);
        btnSignUp.setTranslationY(300);
        textViewDE.setTranslationX(1000);
        textViewWE.setTranslationX(1000);
        textViewWork.setTranslationY(300);

        iconmode.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        imageStart.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        btnLogin.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1500).start();
        btnSignUp.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1500).start();
        textViewDE.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(900).start();
        textViewWE.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        textViewWork.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1600).start();

        isDarkModeOn = getDarkModeStatus();
        if (isDarkModeOn) {
            iconmode.setImageResource(R.drawable.moon);
        } else {
            iconmode.setImageResource(R.drawable.sun);
        }

        iconmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    iconmode.setImageResource(R.drawable.moon);
                    isDarkModeOn = false;
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    iconmode.setImageResource(R.drawable.sun);
                    isDarkModeOn = true;
                }
            }
        });
    }
    @Override
    public void recreate(){
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public Boolean getDarkModeStatus() {
        int nightmodeFlags = StartUpScreen.this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightmodeFlags){
            case Configuration.UI_MODE_NIGHT_YES:
                return true;
            case Configuration.UI_MODE_NIGHT_NO:
                return false;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return false;
        }
        return false;
    }

    public void callLoginSrceen(View view){
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

    public void callSignUpSrceen(View view){
        Intent intent = new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent);
    }
}