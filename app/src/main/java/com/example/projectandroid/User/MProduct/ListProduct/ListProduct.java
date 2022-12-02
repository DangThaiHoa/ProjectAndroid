package com.example.projectandroid.User.MProduct.ListProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.Product.ListProductAdapter;
import com.example.projectandroid.HelperClasses.Product.ListProductHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {

    ImageView btnBack;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView listProductRecycle;
    SqlDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list_product);

        listProductRecycle = findViewById(R.id.List_Product_recycler);
        btnBack = findViewById(R.id.back_btn);

        db = new SqlDatabaseHelper(this);
        listProductRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        btnBack();
        readData();
    }

    private void readData() {

        Cursor cursor = db.ReadData_Product();
        ArrayList<ListProductHelperClass> listProductHelperClassArrayList = new ArrayList<>();
        if(cursor == null){
            Toast.makeText(this, "Không Có Sản Phẩm ", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()) {
                String productName = cursor.getString(1);
                String productQuality = cursor.getString(2);
                byte[] productImage = cursor.getBlob(5);
                listProductHelperClassArrayList.add(new ListProductHelperClass(productName, productQuality, productImage));

            }
            ListProductAdapter listProductAdapter = new ListProductAdapter(this, R.layout.list_product_card_desgin, listProductHelperClassArrayList, sqLiteDatabase);
            listProductRecycle.setAdapter(listProductAdapter);

        }

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListProduct.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}