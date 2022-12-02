package com.example.projectandroid.User.MProduct.ListProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.Product.ListProductHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;

public class DetailProduct extends AppCompatActivity {

    TextView nameProduct, qualityProduct, typeProduct, unitProduct, priceProduct;
    ImageView imageProduct, btnBack;

    SqlDatabaseHelper db;
    int id_Product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_detail_product);

        db = new SqlDatabaseHelper(this);

        nameProduct = findViewById(R.id.detail_product_name);
        qualityProduct = findViewById(R.id.detail_product_quality);
        imageProduct = findViewById(R.id.detail_product_image);
        typeProduct = findViewById(R.id.detail_product_type);
        unitProduct = findViewById(R.id.detail_product_unit);
        priceProduct = findViewById(R.id.detail_product_price);
        btnBack = findViewById(R.id.back_btn);

        Intent i = getIntent();
        String id = i.getStringExtra("Id_Product");
        id_Product = Integer.parseInt(id);

        ReadAllData();
        btnBack();
    }

    private void ReadAllData() {
        Cursor cursor = db.ReadAllData_Product(id_Product);
        if(cursor == null){
            Toast.makeText(this, "Không Có Id Sản Phẩm", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                typeProduct.setText(cursor.getString(1));
                nameProduct.setText(cursor.getString(2));
                qualityProduct.setText(cursor.getString(3));
                unitProduct.setText(cursor.getString(4));
                priceProduct.setText(cursor.getString(5));
                byte[] image = cursor.getBlob(6);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageProduct.setImageBitmap(bitmap);
            }

        }
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