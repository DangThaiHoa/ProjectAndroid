package com.example.projectandroid.User.MShopping.ListBill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillAdapter;
import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillInterface;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionAdapter;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionInterface;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MShopping.ListPromotion.DetailPromotion;
import com.example.projectandroid.User.MShopping.ListPromotion.ListPromotion;

import java.util.ArrayList;

public class ListBill extends AppCompatActivity {

    ImageView btnBack;

    RecyclerView listBillRecycle;
    RecyclerView.Adapter lbilladapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list_bill);

        listBillRecycle = findViewById(R.id.List_Bill_recycler);
        btnBack = findViewById(R.id.back_btn);


        btnBack();

        listProductRecycle();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListBill.super.onBackPressed();
            }
        });

    }

    private void listProductRecycle() {

        listBillRecycle.setHasFixedSize(true);
        listBillRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ListBillHelperClass> lBillLocation = new ArrayList<>();

        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));
        lBillLocation.add(new ListBillHelperClass(R.drawable.image_test,"Bill01","20/11/2022"));

        lbilladapter = new ListBillAdapter(lBillLocation, new ListBillInterface() {
            @Override
            public void onClickItemBill(ListBillHelperClass listBillHelperClass) {
                onclickGoToDetail(listBillHelperClass);
            }
        });
        listBillRecycle.setAdapter(lbilladapter);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void onclickGoToDetail(ListBillHelperClass listBillHelperClass){
        Intent intent = new Intent(this, DetailBill.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Id_Bill", listBillHelperClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}