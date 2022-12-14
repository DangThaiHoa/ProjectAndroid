package com.example.projectandroid.User.MProduct.ListTypeProduct;

import static com.example.projectandroid.User.DashBoard.idUser;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.ListProduct.DetailProduct;
import com.example.projectandroid.User.MProduct.ListProduct.UpdateProduct;
import com.example.projectandroid.User.Product;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateTypeProduct extends AppCompatActivity {

    TextInputEditText nameTypeProduct, descTypeProduct;
    Button btnConfirm;
    ImageView backBtn;
    Toolbar toolbar;

    SqlDatabaseHelper db;

    ProgessLoading progessLoading;

    Button DConfirmBtnDia, DCancelBtnDia;
    TextView DContentDia;
    Dialog Ddialog;

    Integer idTypeProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_type_product);

        db = new SqlDatabaseHelper(this);

        progessLoading = new ProgessLoading(this);

        nameTypeProduct = findViewById(R.id.name_typeProduct);
        descTypeProduct = findViewById(R.id.desc_typeProduct);
        btnConfirm = findViewById(R.id.confirm_btn_modifyTypeProduct);
        backBtn = findViewById(R.id.back_btn);
        toolbar = findViewById(R.id.detail_typeProduct_toolBar);
        toolbar.inflateMenu(R.menu.option_menu_type_product);

        Intent i = getIntent();
        idTypeProduct = i.getIntExtra("id_typeProduct",0);

        toolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    case R.id.delete:
                        DContentDia.setText("B???n C?? Ch???c Ch???n Mu???n X??a Kh??ng?!\n T???t C??? S???n Ph???m Thu???c Lo???i S???n Ph???m N??y S??? B??? X??a V?? T???t c??? H??a ????n V?? Khuy???n M??i Thu???c S???n Ph???m ?????u B??? X??a");
                        Ddialog.show();
                        break;
                }
                return true;
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gName = nameTypeProduct.getText().toString();
                String gDesc = descTypeProduct.getText().toString();
                String gExitsName = null;

                Cursor cursor = db.readNameTypeProductExist_TypeProduct(idTypeProduct);
                while (cursor.moveToNext()) {
                    gExitsName = cursor.getString(0);
                }
                if (gName.equals(gExitsName)) {

                    Boolean resultUpdateData = db.updateData_TypeProduct(gExitsName, gDesc, idTypeProduct);
                    if (resultUpdateData == true) {

                        progessLoading.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UpdateTypeProduct.this, "S???a Lo???i S???n Ph???m Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Product.class);
                                startActivity(intent);
                                finish();
                                progessLoading.dismiss();
                            }
                        }, 2000);

                    } else {

                        progessLoading.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UpdateTypeProduct.this, "S???a Lo???i S???n Ph???m Th???t B???i", Toast.LENGTH_SHORT).show();
                                progessLoading.dismiss();
                            }
                        }, 2000);

                    }

                } else {

                    Boolean checkNameTypeProduct = db.checkNameTypeProduct_TypeProduct(gName, Integer.valueOf(idUser));
                    if (checkNameTypeProduct == true) {

                        Toast.makeText(UpdateTypeProduct.this, "T??n Lo???i S???n Ph???m ???? T???n T???i", Toast.LENGTH_SHORT).show();

                    } else {

                        Boolean resultUpdateData = db.updateData_TypeProduct(gName, gDesc, idTypeProduct);
                        if (resultUpdateData == true) {

                            progessLoading.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpdateTypeProduct.this, "S???a Lo???i S???n Ph???m Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Product.class);
                                    startActivity(intent);
                                    finish();
                                    progessLoading.dismiss();
                                }
                            }, 2000);

                        } else {

                            progessLoading.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpdateTypeProduct.this, "S???a Lo???i S???n Ph???m Th???t B???i", Toast.LENGTH_SHORT).show();
                                    progessLoading.dismiss();
                                }
                            }, 2000);

                        }
                    }
                }
            }
        });

        backBtn();
        readData();
        ShowDiaLogDeleteData();
    }

    private void readData() {

        Cursor cursor = db.readDataUpdate_TypeProduct(idTypeProduct);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Kh??ng C?? ID Lo???i S???n Ph???m", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                nameTypeProduct.setText(cursor.getString(1));
                descTypeProduct.setText(cursor.getString(2));
            }
        }
    }

    private void backBtn() {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTypeProduct.super.onBackPressed();
            }
        });

    }

    public void ShowDiaLogDeleteData() {

        Ddialog = new Dialog(UpdateTypeProduct.this);
        Ddialog.setContentView(R.layout.custom_dialog);
        Ddialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        Ddialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Ddialog.setCancelable(false);

        DConfirmBtnDia = Ddialog.findViewById(R.id.Confirm_dialog_btn);
        DCancelBtnDia = Ddialog.findViewById(R.id.Cancel_dialog_btn);
        DContentDia = Ddialog.findViewById(R.id.tv_Content_dialog);

        DConfirmBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean resultDeleteData = db.deleteData_TypeProduct(idTypeProduct);
                if(resultDeleteData == true){
                    progessLoading.show();
                    Ddialog.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdateTypeProduct.this, "X??a Lo???i S???n Ph???m Th??nh C??ng", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Product.class);
                            startActivity(intent);
                            progessLoading.dismiss();
                        }
                    },2000);

                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdateTypeProduct.this, "X??a Lo???i S???n Ph???m Th???t B???i", Toast.LENGTH_SHORT).show();
                        }
                    },2000);

                }
            }
        });

        DCancelBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ddialog.dismiss();
            }
        });
    }
}