package com.example.projectandroid.User.MShopping.Promotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projectandroid.R;

public class CreatePromotion extends AppCompatActivity {

    Button btnConfirm;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_create_promotion);

        btnConfirm = findViewById(R.id.confirm_btn);
        btnBack = findViewById(R.id.back_btn);

        btnConfirm();
        btnBack();
    }

    private void btnBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePromotion.super.onBackPressed();
            }
        });
    }

    private void btnConfirm() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompleteCreatePromotion.class);
                startActivity(intent);
            }
        });
    }
}