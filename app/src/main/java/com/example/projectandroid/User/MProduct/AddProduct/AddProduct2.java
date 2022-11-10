package com.example.projectandroid.User.MProduct.AddProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.User.Product;

import kotlin.Unit;

public class AddProduct2 extends AppCompatActivity {

    ImageView btnBack, ImageProduct;
    Button btnNext, btnCancel;
    TextView NameProduct, QualityProduct, UnitProduct, PriceProduct,CTypeProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_product2);

        btnBack = findViewById(R.id.back_btn);
        btnNext = findViewById(R.id.comfirm_btn);
        btnCancel = findViewById(R.id.cancel_btn);
        CTypeProduct = findViewById(R.id.type_product_CaddProduct);
        NameProduct = findViewById(R.id.name_product_CaddProduct);
        QualityProduct = findViewById(R.id.quality_product_CaddProduct);
        UnitProduct = findViewById(R.id.unit_product_CaddProduct);
        PriceProduct = findViewById(R.id.price_product_CaddProduct);
        ImageProduct = findViewById(R.id.image_product_CaddProduct);

        Intent intent = getIntent();

        CTypeProduct.setText(intent.getStringExtra("TypeProduct"));
        NameProduct.setText(intent.getStringExtra("NameProduct"));
        QualityProduct.setText(intent.getStringExtra("QualityProduct"));
        UnitProduct.setText(intent.getStringExtra("UnitProduct"));
        PriceProduct.setText(intent.getStringExtra("PriceProduct"));
        ImageProduct.setImageResource(intent.getIntExtra("ImageProduct",0));



        btnBack();
        btnComfim();
        btnCancel();

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProduct2.super.onBackPressed();
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