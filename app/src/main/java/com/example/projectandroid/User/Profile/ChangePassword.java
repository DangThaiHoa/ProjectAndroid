package com.example.projectandroid.User.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectandroid.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePassword extends AppCompatActivity {

    ImageView btnBack;
    TextInputEditText EOldPassword, ENewPassword, EConfirmPassword;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_change_password);

        btnBack = findViewById(R.id.back_btn);
        EOldPassword = findViewById(R.id.Txt_old_Password_Profile);
        ENewPassword = findViewById(R.id.Txt_new_Password_Profile);
        EConfirmPassword = findViewById(R.id.Txt_confirm_Password_Profile);
        submitBtn = findViewById(R.id.btn_Submit_Change_Password_profile);

        submitBtn();
        btnBack();
    }

    public void CheckPassword(){

        if(EOldPassword.getText().toString().equals("123")){
            if(ENewPassword.getText().toString().equals(EConfirmPassword.getText().toString())){
                Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Vui lòng nhập hai mật khẩu giống nhau", Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(this, "Vui lòng nhập đúng mật khẩu cũ", Toast.LENGTH_SHORT).show();
    }

    private void submitBtn() {

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPassword();
            }
        });

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword.super.onBackPressed();
            }
        });
    }
}