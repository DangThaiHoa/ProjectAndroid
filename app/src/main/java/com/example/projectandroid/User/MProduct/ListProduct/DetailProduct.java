package com.example.projectandroid.User.MProduct.ListProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.HelperClasses.Product.ListProductHelperClass;
import com.example.projectandroid.R;

public class DetailProduct extends AppCompatActivity {

    TextView nameProduct,qualityProduct;
    ImageView imageProduct,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_detail_product);

        nameProduct = findViewById(R.id.detail_product_name);
        qualityProduct = findViewById(R.id.detail_product_quanlity);
        imageProduct = findViewById(R.id.detail_product_image);
        btnBack = findViewById(R.id.back_btn);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        ListProductHelperClass listProductHelperClass = (ListProductHelperClass) bundle.get("Id_Product");

        nameProduct.setText(listProductHelperClass.getName());
        qualityProduct.setText(listProductHelperClass.getQualityItem());
        imageProduct.setImageResource(listProductHelperClass.getImage());

        btnBack();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailProduct.super.onBackPressed();
            }
        });

    }
}