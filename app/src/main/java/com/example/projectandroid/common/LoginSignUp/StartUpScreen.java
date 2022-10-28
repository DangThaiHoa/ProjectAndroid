package com.example.projectandroid.common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectandroid.MainActivity;
import com.example.projectandroid.R;

public class StartUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up_screen);

    }

    public void callLoginSrceen(View view){
        Intent intent = new Intent(getApplicationContext(),Login.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn),"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartUpScreen.this,pairs);
        startActivity(intent,options.toBundle());
    }

//    public void callSignUpSrceen(View view){
//        Intent intent = new Intent(getApplicationContext(),Signup.class);
//
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.signup_btn),"transition_login/back/signup");
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartUpScreen.this,pairs);
//        startActivity(intent,options.toBundle());
//    }
}