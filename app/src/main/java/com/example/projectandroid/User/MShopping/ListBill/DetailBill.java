package com.example.projectandroid.User.MShopping.ListBill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MShopping.ListPromotion.DetailPromotion;

public class DetailBill extends AppCompatActivity {

    TextView nameBill, createDay;
    ImageView imageBill,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_detail_bill);

        nameBill = findViewById(R.id.detail_bill_name);
        createDay = findViewById(R.id.detail_bill_create_day);
        imageBill = findViewById(R.id.detail_bill_image);
        btnBack = findViewById(R.id.back_btn);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        ListBillHelperClass listBillHelperClass = (ListBillHelperClass) bundle.get("Id_Bill");

        nameBill.setText(listBillHelperClass.getNameBill());
        createDay.setText(listBillHelperClass.getCreateDay());
        imageBill.setImageResource(listBillHelperClass.getImage());

        btnBack();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailBill.super.onBackPressed();
            }
        });

    }
}