package com.example.projectandroid.User.MProduct.AddProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    ArrayList<String> itemTypeProduct;
    AutoCompleteTextView TypeProduct;
    ArrayAdapter adapterItemTypeProduct;

    ImageView btnBack, ImageProduct;
    Button btnNext;
    TextView NameProduct, QualityProduct, UnitProduct, PriceProduct;

    SqlDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_product);

        db = new SqlDatabaseHelper(AddProduct.this);

        btnBack = findViewById(R.id.back_btn);
        btnNext = findViewById(R.id.next_btn);
        TypeProduct = findViewById(R.id.type_product_addProduct);
        NameProduct = findViewById(R.id.name_product_addProduct);
        QualityProduct = findViewById(R.id.quality_product_addProduct);
        UnitProduct = findViewById(R.id.unit_product_addProduct);
        PriceProduct = findViewById(R.id.price_product_addProduct);
        ImageProduct = findViewById(R.id.image_product_addProduct);

        btnBack();
        btnNext();
        loadDataTypeProduct();

    }

    private void loadDataTypeProduct() {

        Cursor cursor = db.readTypeProduct_AddProduct();

        itemTypeProduct = new ArrayList<>();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Không có dữ liệu loại sản phẩm", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                itemTypeProduct.add(cursor.getString(1));
            }

        }

        adapterItemTypeProduct = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu,itemTypeProduct);
        TypeProduct.setAdapter(adapterItemTypeProduct);

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProduct.super.onBackPressed();
            }
        });
    }

    private void btnNext() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddProduct2.class);
                intent.putExtra("TypeProduct",TypeProduct.getText().toString());
                intent.putExtra("NameProduct",NameProduct.getText().toString());
                intent.putExtra("QualityProduct",QualityProduct.getText().toString());
                intent.putExtra("UnitProduct", UnitProduct.getText().toString());
                intent.putExtra("PriceProduct",PriceProduct.getText().toString());
                intent.putExtra("ImageProduct",R.drawable.image_test);
                startActivity(intent);
            }
        });

    }
}