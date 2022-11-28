package com.example.projectandroid.User.MProduct.AddProduct;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.Product.GetImageProductClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.TypeProduct.AddTypeProduct;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    ArrayList<String> itemTypeProduct;
    AutoCompleteTextView TypeProduct;
    ArrayAdapter adapterItemTypeProduct;

    ImageView btnBack, ImageProduct;
    Button ConfirmBtn, choseImageBtn;
    TextView NameProduct, QualityProduct, UnitProduct, PriceProduct;

    SqlDatabaseHelper db;

    ActivityResultLauncher<String> GetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_add_product);

        db = new SqlDatabaseHelper(AddProduct.this);

        final ProgessLoading progessLoading = new ProgessLoading(this);

        btnBack = findViewById(R.id.back_btn);
        ConfirmBtn = findViewById(R.id.confirm_btn);
        TypeProduct = findViewById(R.id.type_product_addProduct);
        NameProduct = findViewById(R.id.name_product_addProduct);
        QualityProduct = findViewById(R.id.quality_product_addProduct);
        UnitProduct = findViewById(R.id.unit_product_addProduct);
        PriceProduct = findViewById(R.id.price_product_addProduct);
        ImageProduct = findViewById(R.id.image_product_addProduct);
        choseImageBtn = findViewById(R.id.chose_image_btn_addProduct);

        GetImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                ImageProduct.setImageURI(result);
            }
        });

        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String typeProductName = TypeProduct.getText().toString();

                Cursor cursor = db.getTypeProductID_Product(typeProductName);
                if (cursor.getCount() == 0) {

                    Toast.makeText(AddProduct.this, "Không có dữ liệu loại sản phẩm", Toast.LENGTH_SHORT).show();

                } else {

                    String gIDTypeProduct = null;
                    String gProductName = NameProduct.getText().toString();
                    String gProductQuality = QualityProduct.getText().toString();
                    String gProductUnit = UnitProduct.getText().toString();
                    String gProductPrice = PriceProduct.getText().toString();
                    while (cursor.moveToNext()) {
                        gIDTypeProduct = cursor.getString(0);

                    }
                    if (gIDTypeProduct.isEmpty() || gProductName.isEmpty() || gProductQuality.isEmpty() || gProductUnit.isEmpty() || gProductPrice.isEmpty() || ImageProduct.getDrawable() == null || GetImage ==null) {

                        Toast.makeText(AddProduct.this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();

                    }else{

//                        Boolean resultInserData = db.insertData_Product(gProductName,gProductQuality,gProductUnit,gProductPrice,new GetImageProductClass(GetImage),gIDTypeProduct);
                        if (true){

                            progessLoading.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddProduct.this, "Thêm Loại Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                                    progessLoading.dismiss();
                                }
                            },2000);

                        }else{

                            progessLoading.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddProduct.this, "Thêm Loại Sản Phẩm Thất Bại", Toast.LENGTH_SHORT).show();
                                    progessLoading.dismiss();
                                }
                            },2000);

                        }

                    }

                }



            }
        });


        btnBack();
        loadDataTypeProduct();
        choseImageBtn();

    }

    private void choseImageBtn() {
        choseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImage.launch("image/*");
            }
        });
    }

    private void loadDataTypeProduct() {

        Cursor cursor = db.readTypeProduct_Product();

        itemTypeProduct = new ArrayList<>();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Không có dữ liệu loại sản phẩm", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                itemTypeProduct.add(cursor.getString(1));
            }

        }

        adapterItemTypeProduct = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu,itemTypeProduct);
        TypeProduct.setAdapter(adapterItemTypeProduct);

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProduct.super.onBackPressed();
            }
        });
    }
}