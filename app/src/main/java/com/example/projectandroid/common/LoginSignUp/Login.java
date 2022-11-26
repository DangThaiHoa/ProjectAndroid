package com.example.projectandroid.common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.User.DashBoard;
import com.example.projectandroid.User.Profile.EditProfile;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    ImageView backbtn;

    TextInputEditText username, password;
    Button loginBtn,signupBtn;

    SqlDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        db = new SqlDatabaseHelper(this);

        final ProgessLoading progessLoading = new ProgessLoading(Login.this);

        backbtn = findViewById(R.id.login_back_button);
        loginBtn = findViewById(R.id.button_login);
        signupBtn = findViewById(R.id.button_signup_login);
        username = findViewById(R.id.UserNameorEmail_login);
        password = findViewById(R.id.Password_login);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gUsername = username.getText().toString();
                String gPassword = password.getText().toString();

                if (gUsername.isEmpty() || gPassword.isEmpty()){

                    Toast.makeText(Login.this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();

                }else{

                    Boolean resultUserName = db.checkUsernamePassword(gUsername,gPassword);
                    Boolean resultEmail = db.checkEmailPassword(gUsername,gPassword);
                    Boolean resultPhone = db.checkPhonePassword(gUsername,gPassword);
                    if (resultUserName == true || resultEmail == true || resultPhone == true){

                        progessLoading.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Login.this, DashBoard.class);
                                startActivity(intent);
                                progessLoading.dismiss();
                                finish();
                            }
                        },2000);

                    }else{

                        progessLoading.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "Sai Tên Đăng Nhập Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
                                progessLoading.dismiss();
                            }
                        },2000);

                    }

                }
            }
        });

        backbtn();
    }

    private void backbtn() {

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StartUpScreen.class);
                startActivity(intent);
            }
        });

    }
}