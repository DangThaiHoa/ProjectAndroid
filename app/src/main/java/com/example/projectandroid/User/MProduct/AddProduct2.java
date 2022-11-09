package com.example.projectandroid.User.MProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projectandroid.R;

public class AddProduct2 extends AppCompatActivity {

    ImageView btnBack;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_product2);

        btnBack = findViewById(R.id.back_btn);
        btnNext = findViewById(R.id.next_btn);

        btnBack();
        btnNext();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProduct2.super.onBackPressed();
            }
        });
    }

    private void btnNext() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddProduct3.class);
                startActivity(intent);
            }
        });

    }
}