package com.example.projectandroid.User.MProduct.ListTypeProduct;

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

import com.example.projectandroid.HelperClasses.Product.ListTypeProduct.ListTypeProductAdapter;
import com.example.projectandroid.HelperClasses.Product.ListTypeProduct.ListTypeProductHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.AddTypeProduct.AddTypeProduct;

import java.util.ArrayList;

public class ListTypeProduct extends AppCompatActivity {

    ImageView btnBack;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView listProductRecycle;
    SqlDatabaseHelper db;

    Button ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list_type_product);


        listProductRecycle = findViewById(R.id.List_Type_Product_recycler);
        btnBack = findViewById(R.id.back_btn);

        db = new SqlDatabaseHelper(this);
        listProductRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        btnBack();
        readData();
        ShowDiaLog();

    }

    private void readData() {

        Cursor cursor = db.readData_TypeProduct();
        ArrayList<ListTypeProductHelperClass> listTypeProductHelperClassArrayList = new ArrayList<>();
        if(cursor.getCount() == 0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ContentDia.setText("Chưa Có Loại Sản Phẩm, Bạn Có Muốn Thêm Không?");
                    dialog.show();
                }
            },500);
        }else{
            while (cursor.moveToNext()) {
                String typeProductName = cursor.getString(1);
                String typeProductDesc = cursor.getString(2);
                listTypeProductHelperClassArrayList.add(new ListTypeProductHelperClass(typeProductName, typeProductDesc));

            }
            ListTypeProductAdapter listTypeProductAdapter = new ListTypeProductAdapter(this, R.layout.list_type_product_card_desgin, listTypeProductHelperClassArrayList, sqLiteDatabase);
            listProductRecycle.setAdapter(listTypeProductAdapter);

        }

    }

    public void ShowDiaLog() {

        dialog = new Dialog(ListTypeProduct.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        ConfirmBtnDia = dialog.findViewById(R.id.Confirm_dialog_btn);
        CancelBtnDia = dialog.findViewById(R.id.Cancel_dialog_btn);
        ContentDia = dialog.findViewById(R.id.tv_Content_dialog);

        ConfirmBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTypeProduct.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListTypeProduct.super.onBackPressed();
            }
        });

    }
}