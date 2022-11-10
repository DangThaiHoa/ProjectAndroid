package com.example.projectandroid.User.MProduct.AddProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projectandroid.R;
import com.example.projectandroid.User.Product;

public class AddProduct3 extends AppCompatActivity {

    ImageView btnBack;
    Button btnNext, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_product3);

        btnBack = findViewById(R.id.back_btn);
        btnNext = findViewById(R.id.comfirm_btn);
        btnCancel = findViewById(R.id.cancel_btn);

        btnBack();
        btnComfim();
        btnCancel();

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProduct3.super.onBackPressed();
            }
        });
    }

    private void btnComfim() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompleteAddProduct.class);
                startActivity(intent);
            }
        });
    }

    private void btnCancel() {

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Product.class);
                startActivity(intent);
            }
        });
    }
}