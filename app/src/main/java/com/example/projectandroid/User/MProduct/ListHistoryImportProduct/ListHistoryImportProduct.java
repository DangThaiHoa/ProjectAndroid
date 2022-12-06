package com.example.projectandroid.User.MProduct.ListHistoryImportProduct;

import androidx.appcompat.app.AppCompatActivity;
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

import com.example.projectandroid.HelperClasses.Product.ListHistoryImportProduct.ListHistoryImportProductAdapter;
import com.example.projectandroid.HelperClasses.Product.ListHistoryImportProduct.ListHistoryImportProductHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillAdapter;
import com.example.projectandroid.HelperClasses.Shopping.ListBill.ListBillHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.AddProduct.AddProduct;
import com.example.projectandroid.User.MProduct.ImportProduct.ImportProduct;
import com.example.projectandroid.User.MShopping.ListBill.ListBill;

import java.util.ArrayList;

public class ListHistoryImportProduct extends AppCompatActivity {

    ImageView btnBack;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView listImportProductRecycle;
    SqlDatabaseHelper db;

    Button ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history_import_product);

        listImportProductRecycle = findViewById(R.id.List_History_Import_Product_recycler);
        btnBack = findViewById(R.id.back_btn);

        db = new SqlDatabaseHelper(this);
        listImportProductRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        btnBack();
        readData();
        ShowDiaLog();
    }

    private void readData() {

        Cursor cursor = db.readData_ImportProduct();
        ArrayList<ListHistoryImportProductHelperClass> listHistoryImportProductHelperClassArrayList = new ArrayList<>();
        if(cursor.getCount() == 0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ContentDia.setText("Chưa Lịch Sử Nhập Hàng, Bạn Có Muốn Nhập Hàng Không?");
                    dialog.show();
                }
            },500);
        }else{
            while (cursor.moveToNext()){
                Integer IdImportProduct = cursor.getInt(0);
                String ProductName = cursor.getString(1);
                String ProductOldQuality = cursor.getString(2);
                String ProductNewQuality = cursor.getString(3);
                String ImportProductCreateDay = cursor.getString(4);
                byte[] ProductImage = cursor.getBlob(5);
                listHistoryImportProductHelperClassArrayList.add(new ListHistoryImportProductHelperClass(ProductName,ImportProductCreateDay,ProductOldQuality,ProductNewQuality,ProductImage,IdImportProduct));

            }
            ListHistoryImportProductAdapter listHistoryImportProductAdapter = new ListHistoryImportProductAdapter(this, R.layout.list_history_import_product_card_desgin, listHistoryImportProductHelperClassArrayList, sqLiteDatabase);
            listImportProductRecycle.setAdapter(listHistoryImportProductAdapter);
        }

    }

    public void ShowDiaLog() {

        dialog = new Dialog(ListHistoryImportProduct.this);
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
                Intent intent = new Intent(getApplicationContext(), ImportProduct.class);
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
                ListHistoryImportProduct.super.onBackPressed();
            }
        });

    }
}