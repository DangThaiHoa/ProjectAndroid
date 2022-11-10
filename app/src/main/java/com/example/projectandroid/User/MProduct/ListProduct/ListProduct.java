package com.example.projectandroid.User.MProduct.ListProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.projectandroid.HelperClasses.Product.ListProductAdapter;
import com.example.projectandroid.HelperClasses.Product.ListProductHelperClass;
import com.example.projectandroid.HelperClasses.Product.ListProductInterface;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {

    ImageView btnBack;

    RecyclerView listProductRecycle;
    RecyclerView.Adapter lproductdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list_product);

        listProductRecycle = findViewById(R.id.List_Product_recycler);
        btnBack = findViewById(R.id.back_btn);


        btnBack();

        listProductRecycle();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListProduct.super.onBackPressed();
            }
        });
    }

    private void listProductRecycle() {

        listProductRecycle.setHasFixedSize(true);
        listProductRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ListProductHelperClass> lProductLocation = new ArrayList<>();

        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","20"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.ls_startupimage,"Tên Sản Phẩm 2","30"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm 3","100"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));
        lProductLocation.add(new ListProductHelperClass(R.drawable.image_test,"Tên Sản Phẩm","Tên Sản Phẩm"));

        lproductdapter = new ListProductAdapter(lProductLocation, new ListProductInterface() {
            @Override
            public void onClickItemProduct(ListProductHelperClass listProductHelperClass) {
                onclickGoToDetail(listProductHelperClass);
            }
        });
        listProductRecycle.setAdapter(lproductdapter);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void onclickGoToDetail(ListProductHelperClass listProductHelperClass){
        Intent intent = new Intent(this, DetailProduct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Id_Product", listProductHelperClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}