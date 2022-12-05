package com.example.projectandroid.User.MShopping.ListPromotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionAdapter;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MShopping.CreatePromotion.CreatePromotion;

import java.util.ArrayList;

public class ListPromotion extends AppCompatActivity {

    ImageView btnBack;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView listPromotionRecycle;
    SqlDatabaseHelper db;

    Button ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list_promotion);

        db = new SqlDatabaseHelper(this);

        listPromotionRecycle = findViewById(R.id.List_Promotion_recycler);
        btnBack = findViewById(R.id.back_btn);

        listPromotionRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));


        btnBack();
        readData();
        ShowDiaLog();
    }

    private void readData() {

        Cursor cursor = db.readData_Promotion();
        ArrayList<ListPromotionHelperClass> listPromotionHelperClassArrayList = new ArrayList<>();
        if(cursor.getCount() == 0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ContentDia.setText("Chưa Có Khuyến Mãi, Bạn Có Muốn Thêm Không?");
                    dialog.show();
                }
            },500);
        }else{
            while (cursor.moveToNext()) {
                Integer IdPromotion = cursor.getInt(0);
                String productName = cursor.getString(1);
                String promotionPercent = cursor.getString(2);
                String promotionStartDay = cursor.getString(3);
                String promotionEndDay = cursor.getString(4);
                byte[] productImage = cursor.getBlob(5);
                listPromotionHelperClassArrayList.add(new ListPromotionHelperClass(productName, promotionPercent, promotionStartDay, promotionEndDay, productImage, IdPromotion));

            }
            ListPromotionAdapter listPromotionAdapter = new ListPromotionAdapter(this, R.layout.list_promotion_card_desgin, listPromotionHelperClassArrayList, sqLiteDatabase);
            listPromotionRecycle.setAdapter(listPromotionAdapter);

        }
    }

    public void ShowDiaLog() {

        dialog = new Dialog(ListPromotion.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        ConfirmBtnDia = dialog.findViewById(R.id.Confirm_dialog_btn);
        CancelBtnDia = dialog.findViewById(R.id.Cancel_dialog_btn);
        ContentDia = dialog.findViewById(R.id.tv_Content_dialog);

        ConfirmBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreatePromotion.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        CancelBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListPromotion.super.onBackPressed();
            }
        });
    }
}