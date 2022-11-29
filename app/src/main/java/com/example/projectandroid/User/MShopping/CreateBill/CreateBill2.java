package com.example.projectandroid.User.MShopping.CreateBill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.User.Shopping;

public class CreateBill2 extends AppCompatActivity {

    ImageView btnBack, ImageProduct;
    Button btnNext, btnCancel;
    TextView CNameProduct, QualityProduct, PriceProduct, CTypeProduct, TotalPriceProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_create_bill2);

        btnBack = findViewById(R.id.back_btn);
        btnNext = findViewById(R.id.comfirm_btn);
        btnCancel = findViewById(R.id.cancel_btn);
        CTypeProduct = findViewById(R.id.type_product_CcreateBill);
        CNameProduct = findViewById(R.id.name_product_CcreateBill);
        QualityProduct = findViewById(R.id.quality_product_CcreateBill);
        PriceProduct = findViewById(R.id.price_product_CcreateBill);
        ImageProduct = findViewById(R.id.image_product_CcreateBill);
        TotalPriceProduct = findViewById(R.id.totalprice_product_CcreateBill);

        Intent intent = getIntent();

        int Price = intent.getIntExtra("PriceProduct",0);
        int Quality = intent.getIntExtra("QualityProduct",0);
        int TotalPrice = Price * Quality;

        CTypeProduct.setText(intent.getStringExtra("TypeProduct"));
        CNameProduct.setText(intent.getStringExtra("NameProduct"));
        PriceProduct.setText(String.valueOf(Price));
        QualityProduct.setText(String.valueOf(Quality));
        TotalPriceProduct.setText(String.valueOf(TotalPrice));
        ImageProduct.setImageResource(intent.getIntExtra("ImageProduct",0));

        btnBack();
        btnComfim();
        btnCancel();

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateBill2.super.onBackPressed();
            }
        });
    }

    private void btnComfim() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompleteCreateBill.class);
                startActivity(intent);
            }
        });
    }

    private void btnCancel() {

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Shopping.class);
                startActivity(intent);
            }
        });
    }
}