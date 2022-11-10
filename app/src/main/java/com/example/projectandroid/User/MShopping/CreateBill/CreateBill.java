package com.example.projectandroid.User.MShopping.CreateBill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.AddProduct.AddProduct;
import com.example.projectandroid.User.MProduct.AddProduct.AddProduct2;

public class CreateBill extends AppCompatActivity {

    String[] itemTypeProduct = {"Đồ Ăn", "Đồ Uống"};
    String[] itemNameProduct = {"Cơm", "Cafe"};
    AutoCompleteTextView TypeProduct,NameProduct;
    ArrayAdapter<String> adapterItemTypeProduct;
    ArrayAdapter<String> adapterItemNameProduct;

    ImageView btnBack, ImageProduct;
    Button btnNext;
    TextView QualityProduct, PriceProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_create_bill);

        btnBack = findViewById(R.id.back_btn);
        btnNext = findViewById(R.id.next_btn);
        TypeProduct = findViewById(R.id.type_product_createBill);
        NameProduct = findViewById(R.id.name_product_createBill);
        QualityProduct = findViewById(R.id.quality_product_createBill);
        PriceProduct = findViewById(R.id.price_product_createBill);
        ImageProduct = findViewById(R.id.image_product_createBill);

        adapterItemTypeProduct = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu,itemTypeProduct);
        adapterItemNameProduct = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu,itemNameProduct);

        TypeProduct.setAdapter(adapterItemTypeProduct);
        NameProduct.setAdapter(adapterItemNameProduct);

        btnBack();
        btnNext();

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateBill.super.onBackPressed();
            }
        });
    }

    private void btnNext() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int IpriceProduct = Integer.parseInt(PriceProduct.getText().toString());
                int IqualityProduct = Integer.parseInt(QualityProduct.getText().toString());
                Intent intent = new Intent(getApplicationContext(), CreateBill2.class);
                intent.putExtra("TypeProduct",TypeProduct.getText().toString());
                intent.putExtra("NameProduct",NameProduct.getText().toString());
                intent.putExtra("PriceProduct",IpriceProduct);
                intent.putExtra("QualityProduct",IqualityProduct);
                intent.putExtra("ImageProduct",R.drawable.image_test);
                startActivity(intent);
            }
        });

    }
}