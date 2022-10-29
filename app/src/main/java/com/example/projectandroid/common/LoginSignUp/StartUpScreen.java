package com.example.projectandroid.common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.projectandroid.MainActivity;
import com.example.projectandroid.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;

public class StartUpScreen extends AppCompatActivity {

    SwitchMaterial switchMode;
    ImageView iconmode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up_screen);

        switchMode = findViewById(R.id.thememode);
        iconmode = findViewById(R.id.iconthememode);

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("Night",false);

        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                }
            }
        });
//
//        boolean IsNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
//        switchMode.setChecked(IsNightModeOn);
//        if(IsNightModeOn){
//            iconmode.setImageResource(R.drawable.moon);
//        }else{
//            iconmode.setImageResource(R.drawable.sun);
//        }

    }

    @Override
    public void recreate(){
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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