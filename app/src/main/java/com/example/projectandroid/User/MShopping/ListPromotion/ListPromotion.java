package com.example.projectandroid.User.MShopping.ListPromotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionAdapter;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionInterface;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class ListPromotion extends AppCompatActivity {

    ImageView btnBack;

    RecyclerView listPromotionRecycle;
    RecyclerView.Adapter lpromotionadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list_promotion);

        listPromotionRecycle = findViewById(R.id.List_Promotion_recycler);
        btnBack = findViewById(R.id.back_btn);


        btnBack();

        listProductRecycle();
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListPromotion.super.onBackPressed();
            }
        });
    }

    private void listProductRecycle() {

        listPromotionRecycle.setHasFixedSize(true);
        listPromotionRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ListPromotionHelperClass> lPromotionLocation = new ArrayList<>();

        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));
        lPromotionLocation.add(new ListPromotionHelperClass(R.drawable.image_test,"Giảm 30%","20/11/2022", "30/11/2022"));

        lpromotionadapter = new ListPromotionAdapter(lPromotionLocation, new ListPromotionInterface() {
            @Override
            public void onClickItemPromotion(ListPromotionHelperClass listPromotionHelperClass) {
                onclickGoToDetail(listPromotionHelperClass);
            }
        });
        listPromotionRecycle.setAdapter(lpromotionadapter);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void onclickGoToDetail(ListPromotionHelperClass listPromotionHelperClass){
        Intent intent = new Intent(this, DetailPromotion.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Id_Promotion", listPromotionHelperClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}