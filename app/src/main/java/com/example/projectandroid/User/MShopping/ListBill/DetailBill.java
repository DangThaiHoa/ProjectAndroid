package com.example.projectandroid.User.MShopping.ListBill;

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

import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MShopping.ListPromotion.DetailPromotion;

public class DetailBill extends AppCompatActivity {

    TextView typeProduct, nameProduct, qualityProduct, priceProduct, totalPriceBill, createDayBill, createTimeBill ;
    ImageView imageBill,btnBack;

    SqlDatabaseHelper db;
    int id_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_detail_bill);

        db = new SqlDatabaseHelper(this);

        typeProduct = findViewById(R.id.detail_bill_typeProduct);
        nameProduct = findViewById(R.id.detail_bill_nameProduct);
        qualityProduct = findViewById(R.id.detail_bill_qualityProduct);
        priceProduct = findViewById(R.id.detail_bill_priceProduct);
        totalPriceBill = findViewById(R.id.detail_bill_TotalPriceBill);
        createDayBill = findViewById(R.id.detail_bill_CreateDayBill);
        createTimeBill = findViewById(R.id.detail_bill_CreateTimeBill);
        imageBill = findViewById(R.id.detail_bill_imageProduct);
        btnBack = findViewById(R.id.back_btn);

        Intent i = getIntent();
        Integer id = i.getIntExtra("Id_Bill",0);
        id_bill = id;

        readAllData();
        btnBack();
    }

    private void readAllData() {

        Cursor cursor = db.readAllData_Bill(id_bill);
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Không Có Hóa Đơn", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                typeProduct.setText(cursor.getString(1));
                nameProduct.setText(cursor.getString(2));
                priceProduct.setText(cursor.getString(3));
                qualityProduct.setText(cursor.getString(4));
                totalPriceBill.setText(cursor.getString(5));
                createDayBill.setText(cursor.getString(6));
                createTimeBill.setText(cursor.getString(7));
                byte[] image = cursor.getBlob(8);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageBill.setImageBitmap(bitmap);
            }
        }

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