package com.example.projectandroid.User.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.projectandroid.R;
import com.example.projectandroid.User.MShopping.CreateBill.CreateBill;

public class Profile extends AppCompatActivity {

    ImageView btnBack;
    CardView Setting, changePassword, Information;
    
    RelativeLayout btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.back_btn);
        Setting = findViewById(R.id.card_icon_Setting_profile);
        changePassword = findViewById(R.id.card_icon_Password_profile);
        Information = findViewById(R.id.card_icon_Info_profile);
        btnLogout = findViewById(R.id.logout_btn);

        btnBack();
        Setting();
        changePassword();
        Information();
        btnLogout();
    }

    private void btnLogout() {
    }

    private void Information() {
    }

    private void changePassword() {
    }

    private void Setting() {
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile.super.onBackPressed();
            }
        });
    }
}