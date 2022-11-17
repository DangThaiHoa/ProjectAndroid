package com.example.projectandroid.User.MShopping.ListPromotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.R;

public class DetailPromotion extends AppCompatActivity {

    TextView percentPromotion,startDayPromotion,endDayPromotion;
    ImageView imagePromotion,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_detail_promotion);

        percentPromotion = findViewById(R.id.detail_promotion_percent);
        startDayPromotion = findViewById(R.id.detail_promotion_startDay);
        endDayPromotion = findViewById(R.id.detail_promotion_endDay);
        imagePromotion = findViewById(R.id.detail_promotion_image);
        btnBack = findViewById(R.id.back_btn);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        ListPromotionHelperClass listPromotionHelperClass = (ListPromotionHelperClass) bundle.get("Id_Promotion");

        percentPromotion.setText(listPromotionHelperClass.getPresent());
        startDayPromotion.setText(listPromotionHelperClass.getStartDay());
        endDayPromotion.setText(listPromotionHelperClass.getEndDay());
        imagePromotion.setImageResource(listPromotionHelperClass.getImage());

        btnBack();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailPromotion.super.onBackPressed();
            }
        });

    }
}