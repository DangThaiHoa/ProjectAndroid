package com.example.projectandroid.User.MShopping.ListPromotion;

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

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;

public class DetailPromotion extends AppCompatActivity {

    TextView typeProduct, nameProduct, priceProduct, priceAfter, percentPromotion, startDayPromotion, endDayPromotion;
    ImageView imageProduct,btnBack;

    SqlDatabaseHelper db;
    int id_Promotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_detail_promotion);
        
        db = new SqlDatabaseHelper(this);

        typeProduct = findViewById(R.id.detail_promotion_typeProduct);
        nameProduct = findViewById(R.id.detail_promotion_nameProduct);
        priceProduct = findViewById(R.id.detail_promotion_priceBefore);
        percentPromotion = findViewById(R.id.detail_promotion_percent);
        priceAfter = findViewById(R.id.detail_promotion_priceAfter);
        startDayPromotion = findViewById(R.id.detail_promotion_startDay);
        endDayPromotion = findViewById(R.id.detail_promotion_endDay);
        imageProduct = findViewById(R.id.detail_promotion_imageProduct);

        Intent i = getIntent();
        Integer id = i.getIntExtra("Id_Promotion",0);
        id_Promotion = id;

        btnBack = findViewById(R.id.back_btn);

        readAllData();
        btnBack();
    }

    private void readAllData() {

        Cursor cursor = db.readAllData_Promotion(id_Promotion);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Không Có Khuyến Mãi", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                typeProduct.setText(cursor.getString(1));
                nameProduct.setText(cursor.getString(2));
                priceProduct.setText(cursor.getString(3));
                percentPromotion.setText(cursor.getString(4) + "%");
                priceAfter.setText(cursor.getString(5));
                startDayPromotion.setText(cursor.getString(6));
                endDayPromotion.setText(cursor.getString(7));
                byte[] image = cursor.getBlob(8);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageProduct.setImageBitmap(bitmap);
            }
        }
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